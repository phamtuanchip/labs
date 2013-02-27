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


package example.btsppecho;


import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.ServiceRecord;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

import example.btsppecho.server.ServerConnectionHandler;
import example.btsppecho.server.ServerConnectionHandlerListener;


class ServerForm
    extends Form
    implements ServerConnectionHandlerListener, CommandListener
{
    private final MIDletApplication midlet;
    private final StringItem numConnectionsField;
    private final TextField sendDataField;
    private final StringItem receivedDataField;
    private final StringItem statusField;
    private final Command sendCommand;
    private final Command addConnectionCommand;
    private final Command searchCommand;
    private final Command logCommand;
    private final Command quitCommand;
    private final Command clearStatusCommand;
    private final Vector handlers;

    private int maxConnections;
    private StringItem btAddressField = null;
    private volatile int numReceivedMessages = 0;
    private volatile int numSentMessages = 0;
    private int sendMessageId = 0;


    ServerForm(MIDletApplication midlet)
    {
        super("Server");
        this.midlet = midlet;
        handlers = new Vector();

        String value =
            LocalDevice.getProperty(
                            "bluetooth.connected.devices.max");
        try
        {
            maxConnections = Integer.parseInt(value);
        }
        catch (NumberFormatException e)
        {
            maxConnections = 0;
        }

        // 1. add Form items
        try
        {
            String address = LocalDevice.getLocalDevice()
                                        .getBluetoothAddress();
            btAddressField = new StringItem("My address", address);
            append(btAddressField);
        }
        catch (BluetoothStateException e)
        {
            // nothing we can do, don't add field
        }
        numConnectionsField = new StringItem("Connections", "0");
        append(numConnectionsField);
        statusField = new StringItem("Status", "");
        append(statusField);
        sendDataField = new TextField("Send data",
                                      "Server says: 'Hello, world.'",
                                      64,
                                      TextField.ANY);
        append(sendDataField);
        receivedDataField = new StringItem("Last received data",
                                           null);
        append(receivedDataField);


        // 2. create commands
        sendCommand = new Command("Send", Command.SCREEN, 1);
        searchCommand = new Command("Search for more",
                                    Command.SCREEN,
                                    1);
        addConnectionCommand = new Command("Add connection",
                                           Command.SCREEN,
                                           2);
        logCommand = new Command("View log", Command.SCREEN, 3);
        clearStatusCommand = new Command("Clear status", Command.SCREEN, 4);
        quitCommand = new Command("Quit", Command.EXIT, 1);


        // 3. add commands and set command listener
        addCommand(searchCommand);
        addCommand(addConnectionCommand);
        addCommand(logCommand);
        addCommand(clearStatusCommand);
        addCommand(quitCommand);
        // The 'sendCommand' is added later to screen,
        // if at least one connection is open.
        setCommandListener(this);
    }


    public void makeConnections(Vector serviceRecords, int security)
    {
        for (int i=0; i < serviceRecords.size(); i++)
        {
            ServiceRecord serviceRecord =
                (ServiceRecord) serviceRecords.elementAt(i);
            boolean found = false;
            for (int j=0; j < handlers.size(); j++)
            {
                ServerConnectionHandler old =
                    (ServerConnectionHandler) handlers.elementAt(j);
                String oldAddr = old.getServiceRecord().
                                     getHostDevice().
                                     getBluetoothAddress();
                String newAddr =
                       serviceRecord.getHostDevice()
                                    .getBluetoothAddress();
                if (oldAddr.equals(newAddr))
                {
                     found = true;
                     break;
                }
            }
            if (!found)
            {
                ServerConnectionHandler newHandler =
                    new ServerConnectionHandler(
                            this,
                            serviceRecord,
                            security);
                newHandler.start(); // start reader & writer
            }
        }
    }


    private void removeHandler(ServerConnectionHandler handler)
    {
        if (handlers.contains(handler))
        {
            handlers.removeElement(handler);
            String str = Integer.toString(handlers.size());
            numConnectionsField.setText(str);
            if (handlers.size() == 0)
            {
                removeCommand(sendCommand);
                addCommand(searchCommand);
            }
        }
    }


    void closeAll()
    {
        for (int i=0; i < handlers.size(); i++)
        {
            ServerConnectionHandler handler =
                (ServerConnectionHandler) handlers.elementAt(i);
            handler.close();
            removeHandler(handler);
        }
    }


    public void commandAction(Command cmd, Displayable disp)
    {
        if (cmd == addConnectionCommand)
        {
            Vector v = new Vector();
            for (int i=0; i < handlers.size(); i++)
            {
                ServerConnectionHandler handler =
                    (ServerConnectionHandler) handlers.elementAt(i);
                String btAddress = handler.getServiceRecord()
                                       .getHostDevice()
                                       .getBluetoothAddress();
                v.addElement(btAddress);
            }
            midlet.serverFormAddConnection(v);
        }
        else if (cmd == clearStatusCommand)
        {
            statusField.setText("");
        }
        else if (cmd == logCommand)
        {
            midlet.serverFormViewLog();
        }
        else if (cmd == sendCommand)
        {
            String sendData = sendDataField.getString();
            Integer id = new Integer(sendMessageId++);

            for (int i=0; i < handlers.size(); i++)
            {
                ServerConnectionHandler handler =
                    (ServerConnectionHandler) handlers.elementAt(i);
                try
                {
                    handler.queueMessageForSending(
                                id,
                                sendData.getBytes());
                    statusField.setText(
                                    "Queued a send message request");
                }
                catch(IllegalArgumentException e)
                {
                    // Message length longer than
                    // ServerConnectionHandler.MAX_MESSAGE_LENGTH

                    String errorMessage =
                        "IllegalArgumentException while trying " +
                        "to send a message: " + e.getMessage();
                    handleError(handler, errorMessage);
                }
            }
        }
        else if (cmd == searchCommand)
        {
            midlet.serverFormSearchRequest(handlers.size());
        }
        else if (cmd == quitCommand)
        {
            closeAll();
            midlet.serverFormExitRequest();
        }

        // To keep this MIDlet simple, I didn't add any way
        // to drop individual connections. But you might
        // want to do so.
    }


    // ServerConnectionHandlerListener interface methods

    public void handleOpen(ServerConnectionHandler handler)
    {
        handlers.addElement(handler);
        // for the first open connection
        if (handlers.size() == 1)
        {
            removeCommand(searchCommand);

            removeCommand(sendCommand);
            addCommand(sendCommand);
        }

        // Remove the 'Add connection' command
        // when the device already has open the
        // maximum number of connections it can
        // support.
        if (handlers.size() >= maxConnections)
        {
            removeCommand(addConnectionCommand);
        }

        statusField.setText("Connection opened");
        String str = Integer.toString(handlers.size());
        numConnectionsField.setText(str);
    }


    public void handleOpenError(
                    ServerConnectionHandler handler,
                    String errorMessage)
    {
        statusField.setText("Error opening outbound connection: " +
                            errorMessage);
    }


    public void handleReceivedMessage(
                    ServerConnectionHandler handler,
                    byte[] messageBytes)
    {
        numReceivedMessages++;

        String message = new String(messageBytes);
        receivedDataField.setText(message);

        statusField.setText(
            "# messages read: " + numReceivedMessages + " " +
            "sent: " + numSentMessages);

        // Broadcast message to all clients
        for (int i=0; i < handlers.size(); i++)
        {
            ServerConnectionHandler h =
                (ServerConnectionHandler) handlers.elementAt(i);

            Integer id = new Integer(sendMessageId++);
            try
            {
                h.queueMessageForSending(id, messageBytes);
            }
            catch (IllegalArgumentException e)
            {
                String errorMessage =
                    "IllegalArgumentException while trying to " +
                    "send message: " + e.getMessage();
                handleError(handler, errorMessage);
            }
        }
    }


    public void handleQueuedMessageWasSent(
                    ServerConnectionHandler handler,
                    Integer id)
    {
        numSentMessages++;
        statusField.setText("# messages read: " +
                            numReceivedMessages + " " +
                            "sent: " + numSentMessages);
    }


    public void handleClose(ServerConnectionHandler handler)
    {
        removeHandler(handler);
        if (handlers.size() == 0)
        {
            removeCommand(sendCommand);
            addCommand(searchCommand);
        }

        // If the number of currently open connections
        // drops below the maximum number that this
        // device could have open, restore
        // 'Add connection' to the screen commands.
        if (handlers.size() < maxConnections)
        {
            removeCommand(addConnectionCommand);
            addCommand(addConnectionCommand);
        }

        statusField.setText("Connection closed");
    }


    public void handleErrorClose(ServerConnectionHandler handler,
                                 String errorMessage)
    {
        removeHandler(handler);
        if (handlers.size() == 0)
        {
            removeCommand(sendCommand);
            addCommand(searchCommand);
        }

        statusField.setText("Error (close): '" + errorMessage + "'");
    }


    public void handleError(ServerConnectionHandler handler,
                            String errorMessage)
    {
        statusField.setText("Error: '" + errorMessage + "'");
    }
}