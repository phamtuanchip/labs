package lab4;



import java.io.IOException;

import javax.microedition.io.Connector;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

public class Sender implements Runnable{

	private static Sender inst = new Sender();

	private Sender() {
		// TODO Auto-generated constructor stub
	}
	public static Sender getInstance(){
		return inst;
	}
	private String mReceiver = null;
	private String mPort = null;
	private String msgString = null;
	private boolean mSending = false;
	public void sendMsg(String rcrv, String port, String msgText){
		if(mSending) return;
		mReceiver = rcrv;
		mPort = port;
		msgString = msgText;
		Thread th = new Thread(this);
		th.start();
	}

	public boolean isSending(){
		return mSending;
	}

	public void run() {
		// TODO Auto-generated method stub
		mSending = true;
		try {
			sendSMS();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("run() caught: ");
			e.printStackTrace();
		}
		mSending = false;

	}
	private void sendSMS(){
		String address = "sms://" + mReceiver+ ":"+mPort;
		MessageConnection conn = null;
		try {
			conn = (MessageConnection) Connector.open(address);
			TextMessage txtmessage = (TextMessage) conn.newMessage(MessageConnection.TEXT_MESSAGE);
			txtmessage.setAddress(address);
			txtmessage.setPayloadText(msgString);
			conn.send(txtmessage);
		} catch (Throwable e) {
			System.out.println("Send caught: ");
			e.printStackTrace();
			// TODO: handle exception
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (IOException e) {
				// TODO: handle exceptions
				System.out.println("Closing connection caught: ");
				e.printStackTrace();
			}
		}
	}

}
