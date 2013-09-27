package lab4;

/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.MessageListener;

/**
* @author administrator
*/
public class SendSms extends MIDlet implements CommandListener, ItemCommandListener,MessageListener {
      private Display display;
      private Form composeSms;
      private TextField toWhom;
      private TextField message;
      private Alert alert;
      ContactListForm contactlistform;
      private StringItem send;
      private Command select, exit, add;
      HttpConnection httpconnection;

      public SendSms() {
            display = Display.getDisplay(this);
            composeSms = new Form("SMS");
            toWhom = new TextField("To", "", 13, TextField.PHONENUMBER);
            message = new TextField("Message", "", 160, TextField.ANY);
            select = new Command("Select", Command.OK, 0);
            send = new StringItem("", "Send", StringItem.BUTTON);
            exit = new Command("Exit", Command.EXIT, 2);
            add = new Command("Add", Command.OK, 0);
            composeSms.append(toWhom);
            composeSms.append(message);
            composeSms.append(send);
            toWhom.setDefaultCommand(add);
            send.setDefaultCommand(select);
            composeSms.addCommand(exit);
            toWhom.setItemCommandListener(this);
            send.setItemCommandListener(this);
            composeSms.setCommandListener(this);
      }

      public void startApp() {
            Displayable current = Display.getDisplay(this).getCurrent();
            if (current == null) {
                  boolean isAPIAvailable = (System.getProperty("microedition.pim.version") != null);
                  StringBuffer sbuf = new StringBuffer(getAppProperty("MIDlet-Name"));
                  sbuf.append("\n").append(getAppProperty("MIDlet-Vendor")).append(isAPIAvailable ? "" : "\nPIM API not available");
                  Alert alertScreen = new Alert(null, sbuf.toString(), null, AlertType.INFO);
                  alertScreen.setTimeout(3000);
                  if (!isAPIAvailable) {
                        display.setCurrent(alertScreen);
                  } else {
                        display.setCurrent(composeSms);
                  }
            } else {
                  Display.getDisplay(this).setCurrent(current);
            }
            
      }

      public void pauseApp() {
      }

      public void destroyApp(boolean unconditional) {
            notifyDestroyed();
      }

      void showMessage(String message, Displayable displayable) {
            Alert alert = new Alert("");
            alert.setTitle("Error");
            alert.setString(message);
            alert.setType(AlertType.ERROR);
            alert.setTimeout(5000);
            display.setCurrent(alert, displayable);
      }

      void showMain() {
            display.setCurrent(composeSms);
      }

      void showContactsList() {
            contactlistform = new ContactListForm(this);
            contactlistform.LoadContacts();
            display.setCurrent(contactlistform);
      }
      void contactSelected(String telephoneNumber) {
            this.setPhoneNumber(telephoneNumber);
            showMain();
      }

      void setPhoneNumber(String phoneNumber) {
            toWhom.setString(phoneNumber);
      }

      public void commandAction(Command cmd, Displayable disp) {
            if (cmd == exit && disp==composeSms) {
                  destroyApp(false);
            }
      }

      public void commandAction(Command cmd, Item item) {
            if (cmd == add && item==toWhom) {
                  showContactsList();
            }
            if (cmd == select && item==send) {
                  String mno = toWhom.getString();
                  String msg = message.getString();
                  String s = " ";
                  StringBuffer stringbuffer = null;
                  if (mno.equals("") || msg.equals("")) {
                        if (mno.equals("")) {
                              alert = new Alert("Alert");
                              alert.setString("Enter Mobile Number!!!");
                              alert.setTimeout(2000);
                              display.setCurrent(alert);
                        } else {
                              alert = new Alert("Alert");
                              alert.setString("Enter Message!!!");
                              alert.setTimeout(2000);
                              display.setCurrent(alert);
                        }
                  } else {
                        try {
                              httpconnection = (HttpConnection) Connector.open("http://www.....Mno=" + mno + "&Msg=" + msg);
                              int k = httpconnection.getResponseCode();
                              if (k != 200) {
                                    throw new IOException("HTTP response code: " + k);
                              }
                              InputStream inputstream = httpconnection.openInputStream();
                              alert = new Alert("Alert");
                              alert.setString("In Connecting.....");
                              alert.setTimeout(500);
                              int l = (int) httpconnection.getLength();
                              if (l > 0) {
                                    int i1 = 0;
                                    int j1 = 0;
                                    byte abyte0[] = new byte[l];
                                    while (j1 != l && i1 != -1) {
                                          i1 = inputstream.read(abyte0, j1, l - j1);
                                          j1 += i1;
                                          stringbuffer.append(new String(abyte0));
                                    }
                                    s = stringbuffer.toString();
                                    alert = new Alert("Alert");
                                    alert.setString(s);
                                    alert.setTimeout(2000);
                                    display.setCurrent(alert);
                              }
                              try {
                                    if (inputstream != null) {
                                          inputstream.close();
                                    }
                                    if (httpconnection != null) {
                                          httpconnection.close();
                                    }
                              } catch (Exception e) {
                                    s = e.toString();
                                    alert = new Alert("Alert");
                                    alert.setString(s);
                                    alert.setTimeout(2000);
                                    display.setCurrent(alert);
                              }
                        } catch (Exception e) {
                              s = e.toString();
                              alert = new Alert("Alert");
                              alert.setString(s);
                              alert.setTimeout(2000);
                              display.setCurrent(alert);
                        }
                  }
            }
      }

	public void notifyIncomingMessage(MessageConnection arg0) {
		 
		System.out.println("you have new mesasge");
	}
}