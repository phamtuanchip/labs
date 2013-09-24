package lab3;

import java.io.*;

import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class PostMIDlet
    extends MIDlet
    implements CommandListener, Runnable {
  private Display mDisplay;
  private Form mForm;

  public PostMIDlet() {
    mForm = new Form("Connecting...");
    mForm.addCommand(new Command("Exit", Command.EXIT, 0));
    mForm.setCommandListener(this);
  }

  public void startApp() {
    if (mDisplay == null) mDisplay = Display.getDisplay(this);
    mDisplay.setCurrent(mForm);

    // Do network loading in a separate thread.
    Thread t = new Thread(this);
    t.start();
  }
  public void pauseApp() {}

  public void destroyApp(boolean unconditional) {}

  public void commandAction(Command c, Displayable s) {
    if (c.getCommandType() == Command.EXIT)
      notifyDestroyed();
  }

  public void run() {
    HttpConnection hc = null;
    InputStream in = null;
    OutputStream out = null;

    try {
      String message = "name=Jonathan+Knudsen%21";
      String url = getAppProperty("PostMIDlet-URL");
      hc = (HttpConnection)Connector.open(url);
      hc.setRequestMethod(HttpConnection.POST);
      hc.setRequestProperty("Content-Type",
          "application/x-www-form-urlencoded");
      hc.setRequestProperty("Content-Length",
          Integer.toString(message.length()));
      out = hc.openOutputStream();
      out.write(message.getBytes());
      in = hc.openInputStream();
      int length = (int)hc.getLength();
      byte[] data = new byte[length];
      in.read(data);
      String response = new String(data);
      StringItem stringItem = new StringItem(null, response);
      mForm.append(stringItem);
      mForm.setTitle("Done.");
    }
    catch (IOException ioe) {
      StringItem stringItem = new StringItem(null, ioe.toString());
      mForm.append(stringItem);
      mForm.setTitle("Done.");
    }
    finally {
      try {
        if (out != null) out.close();
        if (in != null) in.close();
        if (hc != null) hc.close();
      }
      catch (IOException ioe) {}
    }
  }
}