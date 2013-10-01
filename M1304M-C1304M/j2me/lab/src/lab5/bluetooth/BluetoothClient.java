package lab5.bluetooth;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.util.Vector;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.lcdui.Alert;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;

import javax.microedition.midlet.MIDlet;

public class BluetoothClient implements Runnable, CommandListener,
		DiscoveryListener {

	private LocalDevice local_device;
	private MIDlet midlet;
	private String deviceName;
	private Display display;
	private UUID uuid;
	private DiscoveryAgent disc_agent;
	private Vector devices;
	private Command cmd_ok, cmd_cancel;
	private List deviceList;
	private ServiceRecord service;
	private RemoteDevice otherDevice;
	DataOutputStream output;
	private boolean deviceChosen;
	private boolean connect;
	Form messageForm;
	private Form list;
	Thread t;
	String url;
	StreamConnection conn;
	private Command cmd_exit;
	private Command cmd_back;
	private String message;

	public BluetoothClient(MIDlet midlet, Display display, String message) {
		this.message = message;
		this.midlet = midlet;
		this.display = display;

		devices = new Vector();
		deviceChosen = false;
		messageForm = new Form("");
		connect = false;

		deviceList = new List("Devices discovered", List.EXCLUSIVE);
		cmd_ok = new Command("ok", Command.OK, 1);
		cmd_cancel = new Command("cancel", Command.CANCEL, 1);
		cmd_exit = new Command("exit", Command.EXIT, 1);
		cmd_back = new Command("back", Command.BACK, 1);
		t = new Thread(this);
		t.start();
	}

	public void run() {

		try {
			messageForm.append("Waiting...");
			display.setCurrent(messageForm);
			local_device = LocalDevice.getLocalDevice();
			disc_agent = local_device.getDiscoveryAgent();
			local_device.setDiscoverable(DiscoveryAgent.GIAC);
			disc_agent.startInquiry(DiscoveryAgent.GIAC, this);
			uuid = new UUID(0x0003);
			// wait until the user choose one remote device to be the server
			while (!deviceChosen) {
			}

			//verify the service
			if (connect) {

				int transationID = disc_agent.searchServices(
						new int[] { 0x0100 }, new UUID[] { uuid }, otherDevice,
						this);
			}

		} catch (BluetoothStateException e) {
			e.printStackTrace();
		} catch (IOException f) {
			f.printStackTrace();
		}
	}

	public void deviceDiscovered(RemoteDevice arg0, DeviceClass arg1) {
		devices.addElement(arg0);

	}

	public void inquiryCompleted(int discType) {
		switch (discType) {
		case INQUIRY_COMPLETED:
			if (devices.size() > 0) {

				deviceList.deleteAll();
				for (int i = 0; i < devices.size(); i++) {
					try {
						RemoteDevice rd = (RemoteDevice) devices.elementAt(i);
						deviceList.append(rd.getFriendlyName(false), null);

					} catch (IOException e) {

					}

				}
				deviceList.addCommand(cmd_cancel);
				deviceList.addCommand(cmd_ok);
				deviceList.setCommandListener(this);
				display.setCurrent(deviceList); 

			} else { 
				deviceChosen = true;
				display.setCurrent(new Alert("None device found."),
						new OptionsForm(midlet, display));
			}

			break;

		case INQUIRY_TERMINATED:
			display.setCurrent(
					new Alert("The search for device was cancelled!"),
					new OptionsForm(midlet, display));
			break;

		case INQUIRY_ERROR:
			display.setCurrent(
					new Alert("There was an error on device search!"),
					new OptionsForm(midlet, display));
			break;
		}

	}

	public void serviceSearchCompleted(int transID, int respCode) {

		switch (respCode) {
		case SERVICE_SEARCH_TERMINATED:
			display.setCurrent(new Alert(
					"The search for services was cancelled!"), new OptionsForm(
					midlet, display));
			break;

		case SERVICE_SEARCH_ERROR:
			display.setCurrent(new Alert(
					"There was an error on service search!"), new OptionsForm(
					midlet, display));
			break;

		case SERVICE_SEARCH_NO_RECORDS:
			display.setCurrent(new Alert(
					"The remote device isn't running the application!"),
					new OptionsForm(midlet, display));
			break;

		case SERVICE_SEARCH_DEVICE_NOT_REACHABLE:
			display.setCurrent(new Alert("The remote device isn't reachable!"),
					list);
			break;

		case SERVICE_SEARCH_COMPLETED:

			try {
				
				createConnection();
				output.writeUTF(message);
				output.flush();
				Form messageForm = new Form("");
				messageForm.append("Message sent");
				display.setCurrent(messageForm);
				output.close();
				conn.close();
			} catch (Exception e) {

			}

			break;
		}

	}

	// establish a connection and create an DataOutputStream to send a string
	private void createConnection() {
		try {
			url = service.getConnectionURL(
					ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
			conn = (StreamConnection) Connector.open(url);

			output = conn.openDataOutputStream();
		} catch (Exception e) {
			display.setCurrent(new Alert("Não pode ser enviado"));
		}

	}

	public void servicesDiscovered(int arg0, ServiceRecord[] servRecord) {
		service = servRecord[0];

	}

	public void commandAction(Command arg0, Displayable arg1) {
		if (arg1 == deviceList && arg0 == cmd_ok) {
			otherDevice = (RemoteDevice) devices.elementAt(deviceList
					.getSelectedIndex());

			messageForm.deleteAll();
			messageForm.append("Waitting...");
			display.setCurrent(messageForm);

			deviceChosen = true;
			connect = true;
		} else if (arg1 == messageForm) {
			if (arg0 == cmd_back) {
				display.setCurrent(list);
			} else if (arg0 == cmd_exit) {
				midlet.notifyDestroyed();
			}
		}
	}
}