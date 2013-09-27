package lab4;




import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

public class SMSMIDlet extends MIDlet implements CommandListener, Runnable{

	private Sender mSender = null;
	private Thread mReceiver = null;
	private Command mExitCommand = new Command("Exit", Command.EXIT, 1);
	private Command mRedCommand = new Command("Send Red", Command.SCREEN, 1);
	private Command mBlueCommand = new Command("Send Blue", Command.SCREEN, 1);
	private Display display = null;
	protected ImageItem mColorSquare = null;
	protected Image[] mImages = new Image[2];
	protected Image waitImage = null;
	private String mPort = "1234";
	private TextField mNumberEntry = null;
	private Form mForm = null;
	private String mSenderAddress = null;

	public SMSMIDlet() {
		// TODO Auto-generated constructor stub
		mSender = Sender.getInstance();
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		try {
			mEndNow = true;
			conn.close();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("destroyApp caught: ");
			e.printStackTrace();
		}

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method 
		if(mForm == null){
			mForm = new Form("SMSMIDlet");
			mNumberEntry = new TextField("Connect to: ", "+5550000", 256, TextField.PHONENUMBER);
			try {
				mImages[0] = Image.createImage("/red.png");
				mImages[1] = Image.createImage("/blue.png");
				waitImage = Image.createImage("/wait.png");
			} catch (Exception e) {
				System.out.println("startApp caught: ");
				e.printStackTrace();
				// TODO: handle exception
			}

			mColorSquare = new ImageItem(null, waitImage, ImageItem.LAYOUT_DEFAULT, "colored square");
			mForm.append(mNumberEntry);
			mForm.append(mColorSquare);
			mForm.addCommand(mExitCommand);
			mForm.addCommand(mRedCommand);
			mForm.addCommand(mBlueCommand);
			mForm.setCommandListener(this);
		}
		Display.getDisplay(this).setCurrent(mForm);
		startReceive();

	}
	private void startReceive(){
		if(mReceiver != null)
			return;
		mReceiver = new Thread(this);
		mReceiver.start();
	}
	private boolean mEndNow = false;
	private MessageConnection conn = null;


	public void run() {
		// TODO Auto-generated method stub
		Message msg = null;
		String msgReceived = null;
		conn = null;
		mEndNow = false;
		try {
			conn = (MessageConnection) Connector.open("sms://:"+mPort);
			msg = (Message) conn.receive();
			while((msg!=null)&&(!mEndNow)){
				if(msg instanceof TextMessage){
					msgReceived = ((TextMessage)msg).getPayloadText();
					if(msgReceived.equals("red")){
						Display.getDisplay(this).callSerially(new SetRed());
					}else {
						Display.getDisplay(this).callSerially(new SetBlue());
					}
				}
			}
			msg = (Message) conn.receive();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	class SetRed implements Runnable{

		Display disp = null;
		public void run() {
			// TODO Auto-generated method stub
			mColorSquare.setImage(mImages[0]);
		}

	}
	class SetBlue implements Runnable{
		public void run(){
			mColorSquare.setImage(mImages[1]);
		}
	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		if(arg0 == mExitCommand){
			if(!mSender.isSending()){
				try {
					destroyApp(true);
				} catch (MIDletStateChangeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				notifyDestroyed();
			}
		}else if(arg0 == mRedCommand){
			String dest = mNumberEntry.getString();
			if(dest.length()>0)
				mSender.sendMsg(dest, mPort, "red");
		}else if(arg0 == mBlueCommand){
			String dest = mNumberEntry.getString();
			if(dest.length()>0)
				mSender.sendMsg(dest, mPort, "blue");
		}
	}

}
