package lab5.bluetooth;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;

public class BluetoothServer implements Runnable, CommandListener {

	private MIDlet midlet;
	private Display display;
	private StreamConnectionNotifier con;
	private Form list;
	Vector conections, inputs;
	Thread t;
	private Command cmd_exit;
	private Command cmd_back;
	private int numClients;

	
	public BluetoothServer(MIDlet midlet, Display display, Form previousScreen,
			int numClients) {
		cmd_exit = new Command("exit", Command.EXIT, 1);
		cmd_back = new Command("back", Command.BACK, 1);
		this.numClients = numClients;
		this.midlet = midlet;
		this.display = display;
		this.list = previousScreen;
		conections = new Vector();
		inputs = new Vector();
		t = new Thread(this);
		t.start();
	}

	public void run() {

		Form messageForm = new Form("");
		messageForm.append("Waiting...");
		display.setCurrent(messageForm);

		createConnections();
		messageForm.deleteAll();
		messageForm.append(receiveMessageFromAll());
		display.setCurrent(messageForm);
		try {
			con.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * For each client we open a new connection and a new DataInputStream.
	 * After create the new connection, we put it in a vector called conections. 
	 * After create the new DataInputStream, we put it in a vector called inputs.
	 * 
	 * These two vectors function like a map. 
	 */
	private void createConnections() {
		UUID uuid = new UUID(0x0009);
		LocalDevice localDevice;
		try {
			localDevice = LocalDevice.getLocalDevice();
			localDevice.setDiscoverable(DiscoveryAgent.GIAC);
			con = (StreamConnectionNotifier) Connector
					.open("btspp://localhost:" + uuid
							+ ";name=batalha_emulator;authorize=false");
			for (int i = 0; i < numClients; i++) {

				StreamConnection conn = con.acceptAndOpen();
				DataInputStream in = conn.openDataInputStream();
				
				conections.addElement(conn);
				inputs.addElement(in);
			}
		} catch (Exception e) {
			System.out.println("lancei exceptions!");
		}
	}

	public String receiveMessageFromAll() {
		String answer = "Received messages: \n\n";
		for (int i = 0; i < inputs.size(); i++) {
			try {
				DataInputStream input = (DataInputStream) inputs.elementAt(i);
				answer += input.readUTF()+ "\n\n";
			} catch (IOException e) {
			}
		}
		return answer.trim();
	}
	public void commandAction(Command c, Displayable d) {

		if (c == cmd_back) {
			display.setCurrent(list);
		} else if (c == cmd_exit) {
			midlet.notifyDestroyed();
		}

	}

}
