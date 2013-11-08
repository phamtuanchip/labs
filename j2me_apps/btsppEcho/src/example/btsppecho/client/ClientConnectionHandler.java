// Copyright 2004 Nokia Corporation.
//
// THIS SOURCE CODE IS PROVIDED 'AS IS', WITH NO WARRANTIES WHATSOEVER,
// EXPRESS OR IMPLIED, INCLUDING ANY WARRANTY OF MERCHANTABILITY, FITNESS
// FOR ANY PARTICULAR PURPOSE, OR ARISING FROM A COURSE OF DEALING, USAGE
// OR TRADE PRACTICE, RELATING TO THE SOURCE CODE OR ANY WARRANTY OTHERWISE
// ARISING OUT OF ANY PROPOSAL, SPECIFICATION, OR SAMPLE AND WITH NO
// OBLIGATION OF NOKIA TO PROVIDE THE LICENSEE WITH ANY MAINTENANCE OR
// SUPPORT. FURTHERMORE, NOKIA MAKES NO WARRANTY THAT EXERCISE OF THE
// RIGHTS GRANTED HEREUNDER DOES NOT INFRINGE OR MAY NOT CAUSE INFRINGEMENT
// OF ANY PATENT OR OTHER INTELLECTUAL PROPERTY RIGHTS OWNED OR CONTROLLED
// BY THIRD PARTIES
//
// Furthermore, information provided in this source code is preliminary,
// and may be changed substantially prior to final release. Nokia Corporation
// retains the right to make changes to this source code at
// any time, without notice. This source code is provided for informational
// purposes only.
//
// Nokia and Nokia Connecting People are registered trademarks of Nokia
// Corporation.
// Java and all Java-based marks are trademarks or registered trademarks of
// Sun Microsystems, Inc.
// Other product and company names mentioned herein may be trademarks or
// trade names of their respective owners.
//
// A non-exclusive, non-transferable, worldwide, limited license is hereby
// granted to the Licensee to download, print, reproduce and modify the
// source code. The licensee has the right to market, sell, distribute and
// make available the source code in original or modified form only when
// incorporated into the programs developed by the Licensee. No other
// license, express or implied, by estoppel or otherwise, to any other
// intellectual property rights is granted herein.


package example.btsppecho.client;


import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;


public class ClientConnectionHandler
    implements Runnable
{
    private final static byte ZERO = (byte) '0';
    private final static int LENGTH_MAX_DIGITS = 5;

    // this is an arbitrarily chosen value:
    private final static int MAX_MESSAGE_LENGTH =
                             65536 - LENGTH_MAX_DIGITS;

    private final ConnectionService ConnectionService;
    private final ClientConnectionHandlerListener listener;
    private final Hashtable sendMessages = new Hashtable();

    private StreamConnection connection;
    private InputStream in;
    private OutputStream out;

    private volatile boolean aborting;


    public ClientConnectionHandler(
        ConnectionService ConnectionService,
        StreamConnection connection,
        ClientConnectionHandlerListener listener)
    {
        this.ConnectionService = ConnectionService;
        this.connection = connection;
        this.listener = listener;
        aborting = false;
        in = null;
        out = null;

        // Our caller uses method 'start' to start the reader
        // and writer. (This allows the ConnectionService a
        // chance to add us to its list of handlers before
        // the reader and writer start running.)
    }


    ClientConnectionHandlerListener getListener()
    {
        return listener;
    }


    public synchronized void start()
    {
        Thread thread = new Thread(this);
        thread.start();
    }


    public void close()
    {
        if (!aborting)
        {
            synchronized(this)
            {
                aborting = true;
            }

            synchronized(sendMessages)
            {
                sendMessages.notify();
            }

            if (out != null)
            {
                try
                {
                    out.close();
                    synchronized (this)
                    {
                        out = null;
                    }
                }
                catch(IOException e)
                {
                    // there is nothing we can do: ignore it
                }
            }

            if (in != null)
            {
                try
                {
                    in.close();
                    synchronized (this)
                    {
                        in = null;
                    }
                }
                catch(IOException e)
                {
                    // there is nothing we can do: ignore it
                }
            }

            if (connection != null)
            {
                try
                {
                    connection.close();
                    synchronized (this)
                    {
                        connection = null;
                    }
                }
                catch (IOException e)
                {
                    // there is nothing we can do: ignore it
                }
            }
        }
    }


    public void queueMessageForSending(Integer id, byte[] data)
    {
        if (data.length > MAX_MESSAGE_LENGTH)
        {
            throw new IllegalArgumentException(
                          "Message too long: limit is " +
                          MAX_MESSAGE_LENGTH + " bytes");
        }

        synchronized(sendMessages)
        {
            sendMessages.put(id, data);
            sendMessages.notify();
        }
    }


    private void sendMessage(byte[] data)
        throws IOException
    {
        byte[] buf = new byte[LENGTH_MAX_DIGITS + data.length];
        writeLength(data.length, buf);
        System.arraycopy(data,
                         0,
                         buf,
                         LENGTH_MAX_DIGITS,
                         data.length);
        out.write(buf);
        out.flush();
    }


    public void run()
    {
        // the reader

        // 1. open the streams, start the writer
        try
        {
            in = connection.openInputStream();
            out = connection.openOutputStream();

            // start the writer
            Writer writer = new Writer(this);
            Thread writeThread = new Thread(writer);
            writeThread.start();

            listener.handleStreamsOpen(this);
        }
        catch(IOException e)
        {
            // open failed: close any connections/streams and
            // inform listener that the open failed

            close(); // also tells listener to delete handler

            listener.handleStreamsOpenError(this, e.getMessage());
            return;
        }


        // 2. wait to receive and read messages
        while (!aborting)
        {
            int length = 0;
            try
            {
                byte[] lengthBuf = new byte[LENGTH_MAX_DIGITS];
                readFully(in, lengthBuf);
                length = readLength(lengthBuf);
                byte[] temp = new byte[length];
                readFully(in, temp);

                listener.handleReceivedMessage(this, temp);
            }
            catch (IOException e)
            {
                close();
                if (length == 0)
                {
                   listener.handleClose(this);
                }
                else
                {
                   // we were in the middle of reading...
                   listener.handleErrorClose(this, e.getMessage());
                }
            }
        }
    }


    private static void readFully(InputStream in, byte[] buffer)
        throws IOException
    {
        int bytesRead = 0;

        while (bytesRead < buffer.length)
        {
            int count = in.read(buffer,
                                bytesRead,
                                buffer.length - bytesRead);

            if (count == -1)
            {
                throw new IOException("Input stream closed");
            }
            bytesRead += count;
        }
    }


    private static int readLength(byte[] buffer)
    {
        int value = 0;

        for (int i = 0; i < LENGTH_MAX_DIGITS; ++i)
        {
            value *= 10;
            value += buffer[i] - ZERO;
        }
        return value;
    }


    private void sendMessage(OutputStream out, byte[] data)
        throws IOException
    {
        if (data.length > MAX_MESSAGE_LENGTH)
        {
            throw new IllegalArgumentException(
                          "Message too long: limit is: " +
                          MAX_MESSAGE_LENGTH + " bytes");
        }
        byte[] buf = new byte[LENGTH_MAX_DIGITS + data.length];
        writeLength(data.length, buf);
        System.arraycopy(data, 0, buf, LENGTH_MAX_DIGITS, data.length);
        out.write(buf);
        out.flush();
    }


    private static void writeLength(int value, byte[] buffer)
    {
        for (int i = LENGTH_MAX_DIGITS -1; i >= 0; --i)
        {
            buffer[i] = (byte) (ZERO + value % 10);
            value = value / 10;
        }
    }


    private class Writer
        implements Runnable
    {
        private final ClientConnectionHandler handler;


        Writer(ClientConnectionHandler handler)
        {
            this.handler = handler;
        }


        public void run()
        {
            while (!aborting)
            {
                synchronized(sendMessages)
                {
                    Enumeration e = sendMessages.keys();
                    if (e.hasMoreElements())
                    {
                        // send any pending messages
                        Integer id = (Integer) e.nextElement();
                        byte[] sendData = (byte[]) sendMessages.get(id);
                        try
                        {
                            sendMessage(out, sendData);

                            // remove sent message from queue
                            sendMessages.remove(id);

                            // inform listener that it was sent
                            listener.handleQueuedMessageWasSent(
                                         handler,
                                         id);
                        }
                        catch (IOException ex)
                        {
                            close(); // stop the networking thread

                            // inform that we got an error close
                            listener.handleErrorClose(
                                         handler,
                                         ex.getMessage());
                        }
                    }

                    if (sendMessages.isEmpty())
                    {
                        try
                        {
                            sendMessages.wait();
                        }
                        catch (InterruptedException ex)
                        {
                            // this can't happen in MIDP: ignore it
                        }
                    }
                }
            }
        }
    }
}
