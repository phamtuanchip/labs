package lab4;

import javax.microedition.io.Connector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;
public class SendSms2 extends MIDlet implements CommandListener {
      Display display;
      private TextField toWhom;
      private TextField message;
      private Alert alert;
      private Command send,exit;
      MessageConnection clientConn;
      private Form compose;
      public SendSms2() {
            display=Display.getDisplay(this);
            compose=new Form("Compose Message");
            toWhom=new TextField("To","",10,TextField.PHONENUMBER);
            message=new TextField("Message","",600,TextField.ANY);
            send=new Command("Send",Command.BACK,0);
            exit=new Command("Exit",Command.SCREEN,5);
            compose.append(toWhom);
            compose.append(message);
            compose.addCommand(send);
            compose.addCommand(exit);
            compose.setCommandListener(this);
      }
      public void startApp() {
            display.setCurrent(compose);
      }
      public void pauseApp() {
      }
      public void destroyApp(boolean unconditional) {
            notifyDestroyed();
      }
      public void commandAction(Command cmd,Displayable disp) {
            if(cmd==exit) {
                  destroyApp(false);
            }
            if(cmd==send) {
                  String mno=toWhom.getString();
                  String msg=message.getString();
                  if(mno.equals("")) {
                        alert = new Alert("Alert");
                        alert.setString("Enter Mobile Number!!!");
                        alert.setTimeout(2000);
                        display.setCurrent(alert);
                  }
                  else {
                        try {
                              clientConn=(MessageConnection)Connector.open("sms://"+mno);
                        }
                        catch(Exception e) {
                              alert = new Alert("Alert");
                              alert.setString("Unable to connect to Station because of network problem");
                              alert.setTimeout(2000);
                              display.setCurrent(alert);
                        }
                        try {
                              TextMessage textmessage = (TextMessage) clientConn.newMessage(MessageConnection.TEXT_MESSAGE);
                              textmessage.setAddress("sms://"+mno);
                              textmessage.setPayloadText(msg);
                              clientConn.send(textmessage);
                        }
                        catch(Exception e)
                        {
                              Alert alert=new Alert("Alert","",null,AlertType.INFO);
                              alert.setTimeout(Alert.FOREVER);
                              alert.setString("Unable to send");
                              display.setCurrent(alert);
                        }
                  }
            }
      }
}