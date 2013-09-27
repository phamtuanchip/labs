package lab4;

import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

/**
 * @author Anuja
 */
public class MySMS extends MIDlet implements CommandListener {
    private Display display;
    private Form smsFrm;
    private TextField phnNoTxtFld;
    private TextField smsTxtFld;
    private Command exitCmd;
    private Command sendCmd;
    private Alert infoAlert;
    private String smsStr;
    private String phnNoStr;

    public void startApp() {
        display = Display.getDisplay(this);

        smsFrm = new Form("Send SMS");
        phnNoTxtFld = new TextField("To : ", "", 20, TextField.PHONENUMBER);
        smsFrm.append(phnNoTxtFld);
        smsTxtFld = new TextField("Message : ", "", 150, TextField.ANY);
        smsFrm.append(smsTxtFld);

        exitCmd = new Command("Exit", "Exit from the app", Command.EXIT, 3);
        smsFrm.addCommand(exitCmd);
        sendCmd = new Command("Send", Command.OK, 4);
        smsFrm.addCommand(sendCmd);

        smsFrm.setCommandListener(this);
        display.setCurrent(smsFrm);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if(c == exitCmd){
            notifyDestroyed();
        }else if(c == sendCmd){

            phnNoStr = phnNoTxtFld.getString();
            smsStr = smsTxtFld.getString();

            // MessageConnection is an optional package. That package named as JSR 120: Wireless Messaging API
            MessageConnection msgCon = null;

            try {
                // Connector class creates new Connection object
                // open() Create and open a Connection.
                msgCon = (MessageConnection) Connector.open("sms://" + phnNoStr);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // A TextMessage object is used to send a message containing a java.lang.String.
            // It inherits from the Message interface and adds a text message body.
            TextMessage txtMsg = (TextMessage) msgCon.newMessage(MessageConnection.TEXT_MESSAGE);

            //set your message payload data in to message object
            txtMsg.setPayloadText(smsStr);

            try {
                // send the message
                msgCon.send(txtMsg);

                clearTxt();
            } catch (IOException ex) {
                ex.printStackTrace();
            }finally{
                if(msgCon != null){
                    try {
                        // Close the connection
                        msgCon.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }            
        }
    }

    public void clearTxt(){
        phnNoTxtFld.setString("");
        smsTxtFld.setString("");
        infoAlert = new Alert("Message sent successfully...!");
        display.setCurrent(infoAlert, smsFrm);
    }
}