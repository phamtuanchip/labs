package lab3;

import javax.microedition.io.Connector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

import com.sun.midp.lcdui.CommandAccess;

public class HowToSendTextSMSMIDlet extends MIDlet implements CommandListener, Runnable
{
	Command send, exit;
	TextField phone, message;
	StringItem smsResult;
	
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException
	{
		Form form = new Form("SMS Test");

        phone = new TextField("Phone number", "", 255, TextField.PHONENUMBER);
        
        form.append(phone);
        
        message = new TextField("Message", "", 160, TextField.ANY);
        
        form.append(message);
        
        smsResult = new StringItem("SMS result", "");

        form.append(smsResult);
        
        form.addCommand(send = new Command("Send", Command.OK, 1));
        form.addCommand(exit = new Command("Exit", Command.EXIT, 1));
        form.setCommandListener(this);

        Display.getDisplay(this).setCurrent(form);
	}
	public boolean sendSms(String number, String message){
		boolean result = true;
		try {
			//sets address to send message
			String addr = "sms://"+number;
			// opens connection
			MessageConnection conn = (MessageConnection) Connector.open(addr);
			// prepares text message
			TextMessage msg =
				(TextMessage)conn.newMessage(MessageConnection.TEXT_MESSAGE);
			//set text
			msg.setPayloadText(message);
			// send message
			conn.send(msg);
			conn.close();
		}
		catch(SecurityException se)
		{
			result = false;
		}
		catch (Exception e)
		{
			result = false;
		}
		return result;
	}
	public void commandAction(Command c, Displayable d)
	{
		if(c == exit)
		{
			notifyDestroyed();
		}
		else
		{
			new Thread(this).start();
		}
	}
	public void run()
	{
		boolean result = sendSms(phone.getString(), message.getString());
		
		smsResult.setText("RESULT: " + result);
	}
}
