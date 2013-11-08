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


import java.io.IOException;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

import example.btsppecho.ClientForm;
import example.btsppecho.LogScreen;

public class ConnectionService
    implements Runnable
{
    private final ClientForm listener;
    private final String url;

    private StreamConnectionNotifier connectionNotifier = null;
    private volatile boolean aborting;


    public ConnectionService(String url,
                             ClientForm listener)
    {
        this.url = url;
        this.listener = listener;

        LogScreen.log("ConnectionService: waiting to " +
                      "accept connections on '" +
                      url + "'\n");

        // start waiting for a connection
        Thread thread = new Thread(this);
        thread.start();
    }


    public String getClientURL()
    {
        return url;
    }


    public void close()
    {
        if (!aborting)
        {
            synchronized(this)
            {
                aborting = true;
            }

            // Ideally, one might want to give the run method's
            // loop a chance to abort before calling the
            // subsequent close, but the run loop is anyways
            // likely to be sitting on the acceptAndOpen
            // (i.e. blocked).

            try
            {
                connectionNotifier.close();
            }
            catch (IOException e)
            {
                // There is nothing very useful that
                // we can do for this case.
            }
        }
    }


    public void run()
    {
        aborting = false;

        try
        {
            connectionNotifier =
                (StreamConnectionNotifier) Connector.open(url);

            // It might useful in some cases to add a service to the
            // 'Public Browse Group'. For example by doing something
            // approximately as follows:
            // -----------------------------------------------------
            // Retrieve the service record template
            // LocalDevice ld = LocalDevice.getLocalDevice();
            // ServiceRecord rec = ld.getRecord(connectionNotifier);
            // DataElement element =
            //             new DataElement(DataElement.DATSEQ);
            //
            // The service class for PublicBrowseGroup (0x1002)
            // is defined in the Bluetooth Assigned Numbers document.
            // element.addElement(new DataElement(DataElement.UUID,
            //                                    new UUID(0x1002)));
            //
            // Add to the public browse group:
            // rec.setAttributeValue(0x0005, element);
            // -----------------------------------------------------
        }
        catch (IOException e)
        {
            // ConnectionNotFoundException is an IOException
            String errorMessage =
                   "Error while starting ConnectionService: " +
                   e.getMessage();

            listener.handleError(null, errorMessage);

            aborting = true;
        }
        catch (SecurityException e)
        {
            String errorMessage =
                "SecurityException while starting ConnectionService: " +
            e.getMessage();

            listener.handleError(null, errorMessage);

            aborting = true;
        }

        while (!aborting)
        {
            try
            {
                // 1. wait to accept & open a new connection
                StreamConnection connection =
                    (StreamConnection)
                    connectionNotifier.acceptAndOpen();

                LogScreen.log("ConnectionService: new connection\n");

                // 2. create a handler to take care of
                // the new connection and inform
                // the listener
                if (!aborting)
                {
                    ClientConnectionHandler handler =
                        new ClientConnectionHandler(this,
                                                    connection,
                                                    listener);
                    listener.handleAcceptAndOpen(handler);
                }

                // One could consider exiting the
                // ConnectionService when the Client
                // reaches the maximum number of allowed
                // open connections. In that case (i.e.
                // when the maximum number of possible
                // connections is already open), the
                // ConnectionService will not be able
                // to accept any new connections and one
                // might possibly want to consider whether
                // or not the ConnectionService thread
                // could then be terminated.
                //
                // However, existing connections can also
                // be disconnected (e.g. the Server is
                // terminated or closes some/all of its
                // existing connections). In that case,
                // one may want to keep the
                // ConnectionService alive and running:
                // in order to accept later new connections
                // without the need to restart the
                // ConnectionService or MIDlet.
                //
                // (This MIDlet uses the latter approach.)
            }
            catch (IOException e)
            {
                if (!aborting)
                {
                    String errorMessage =
                               "IOException occurred during " +
                               "accept and open: " +
                               e.getMessage();

                    listener.handleError(null, errorMessage);
                }
            }
            catch (SecurityException e)
            {
                if (!aborting)
                {
                    String errorMessage =
                               "IOException occurred during " +
                               "accept and open: " +
                               e.getMessage();

                    listener.handleError(null, errorMessage);
                }
            }
        }
    }
}