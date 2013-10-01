package lab5;

/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import javax.bluetooth.*;
import java.util.*;

/**
* @author test
*/
public class Blue extends MIDlet implements CommandListener,DiscoveryListener {
      private List activeDevices;
      private List activeServices;
      private Command select,exit;
      private Display display;
      private LocalDevice local=null;
      private DiscoveryAgent agent = null;
      private Vector devicesFound = null;
      private ServiceRecord[] servicesFound = null;
      private String connectionURL = null;

      public void startApp() {
            display = Display.getDisplay(this);
            activeDevices = new List("Active Devices", List.IMPLICIT);
            activeServices = new List("Active Services", List.IMPLICIT);
            select = new Command("Select", Command.OK, 0);
            exit = new Command("Exit", Command.EXIT, 0);
            activeDevices.addCommand(exit);
            activeServices.addCommand(exit);
            activeDevices.setCommandListener(this);
            activeServices.setCommandListener(this);
            try {
                  local = LocalDevice.getLocalDevice();
            } catch (Exception e) {
            }
            doDeviceDiscovery();
            display.setCurrent(activeDevices);
      }

      public void pauseApp() {
      }

      public void destroyApp(boolean unconditional) {
            notifyDestroyed();
      }

      public void commandAction(Command cmd, Displayable disp) {
            if (cmd == select && disp == activeDevices) {
                  int device = activeDevices.getSelectedIndex();
                  doServiceSearch((RemoteDevice) devicesFound.elementAt(device));
                  display.setCurrent(activeServices);
                  //doServiceSearch( (RemoteDevice)devicesFound.firstElement());
            }
            if (cmd == select && disp == activeServices) {
                  int service = activeServices.getSelectedIndex();
                  connectionURL = servicesFound[service].getConnectionURL(0, false);
                  try {
                        OutputStream outStream = Connector.openOutputStream(connectionURL);
                        PrintStream pWriter = new PrintStream(outStream);
                        pWriter.print("Response String from SPP Server\r\n");
                        pWriter.write(new String("Response String from SPP Server\r\n").getBytes());
                        pWriter.flush();
                        pWriter.close();
                  } catch (Exception e) {
                        Alert alert = new Alert("");
                        alert.setString(e.toString());
                        display.setCurrent(alert);
                  }
            }
            if (cmd == exit) {
                  destroyApp(false);
            }
      }
      public void inquiryCompleted(int param) {
            switch (param) {
                  case DiscoveryListener.INQUIRY_COMPLETED:
                        /* Inquiry completed normally, add appropriate code
                         * here
                         */
                        if (devicesFound.size() > 0) {
                              activeDevices.addCommand(select);
                              activeDevices.setSelectCommand(select);
                        } else {
                              try {
                                    activeDevices.append("No Devices Found", null);
                                    startServer();
                              } catch (Exception e) {
                              }
                        }
                        break;

                  case DiscoveryListener.INQUIRY_ERROR:
                        // Error during inquiry, add appropriate code here.
                        break;

                  case DiscoveryListener.INQUIRY_TERMINATED:
                        /* Inquiry terminated by agent.cancelInquiry()
                        * Add appropriate code here.
                        */
                        break;
            }
      }

      public void serviceSearchCompleted(int transID, int respCode) {
            switch (respCode) {
                  case DiscoveryListener.SERVICE_SEARCH_COMPLETED:
                        break;
                  case DiscoveryListener.SERVICE_SEARCH_DEVICE_NOT_REACHABLE:
                        break;
                  case DiscoveryListener.SERVICE_SEARCH_ERROR:
                        break;
                  case DiscoveryListener.SERVICE_SEARCH_NO_RECORDS:
                        break;
                  case DiscoveryListener.SERVICE_SEARCH_TERMINATED:
                        break;
            }
      }

      public void servicesDiscovered(int transID, ServiceRecord[] serviceRecord) {
            servicesFound = serviceRecord;
            activeServices.append(servicesFound.toString(), null);
            activeServices.addCommand(select);
            activeServices.setSelectCommand(select);
      }

      public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
            String str = null;
            try {
                  str = remoteDevice.getFriendlyName(true);
            } catch (Exception e) {
            }
            activeDevices.append(str, null);
            devicesFound.addElement(remoteDevice);
      }

      private void doDeviceDiscovery() {
            try {
                  local = LocalDevice.getLocalDevice();
            } catch (BluetoothStateException bse) {
                   // Error handling code here
            }
            agent = local.getDiscoveryAgent();
            devicesFound = new Vector();
            try {
                  if (!agent.startInquiry(DiscoveryAgent.GIAC, this)) {
                        // Inquiry not started, error handling code here
                  }
            } catch (BluetoothStateException bse) {
                  // Error handling code here
            }
      }

      private void doServiceSearch(RemoteDevice device) {
            int[] attributes = {0x100, 0x101, 0x102};
            UUID[] uuids = new UUID[1];
            uuids[0] = new UUID("1101", false);
            try {
                  agent.searchServices(attributes, uuids, device, this);
            } catch (BluetoothStateException e) {
                  Alert alert1 = new Alert("Error");
                  alert1.setString(e.toString());
                  display.setCurrent(alert1);
            }
      }

      public void startServer() throws IOException {
            UUID uuid = new UUID("1101", false);
            //Create the service url
            String connectionString = "btspp://localhost:" + uuid + ";name=xyz";
            //open server url
            StreamConnectionNotifier streamConnNotifier = (StreamConnectionNotifier) Connector.open(connectionString);
            //Wait for client connection
            System.out.println("\nServer Started. Waiting for clients to connect...");
            StreamConnection connection = streamConnNotifier.acceptAndOpen();
            RemoteDevice dev = RemoteDevice.getRemoteDevice(connection);
            System.out.println("Remote device address: " + dev.getBluetoothAddress());
            System.out.println("Remote device name: " + dev.getFriendlyName(true));

            //read string from spp client
            try {
                  DataInputStream in = (DataInputStream) connection.openDataInputStream();
                  /*BufferedReader bReader=new BufferedReader(new InputStreamReader(inStream)); 
                  String lineRead=bReader.readLine(); 
                  System.out.println(lineRead);*/
                  /*//send response to spp client 
                  OutputStream outStream=connection.openOutputStream(); 
                  PrintWriter pWriter=new PrintWriter(new OutputStreamWriter(outStream)); 
                  pWriter.write("Response String from SPP Server\r\n"); 
                  pWriter.flush(); 
                  pWriter.close();*/
                  streamConnNotifier.close();
            } catch (Exception e) {
				e.printStackTrace();
			}
      }
}