package lab8;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.RecordControl;
import javax.microedition.midlet.MIDletStateChangeException;

public class Thread_1 implements CommandListener, Runnable{



	private Display display;
	private Form form;
	private Command exitCommand;
	private StringItem message;
	private byte[] recordedAudioArray = null;
	private Player player = null;
	private Player capturePlayer = null;

	private Player playbackPlayer = null;

	private RecordControl rc = null;
	private RecordControl recordControl = null;

	private ByteArrayOutputStream output ;
	private ByteArrayOutputStream bos ;
	private ByteArrayInputStream bis = null;


	public Thread_1()
	{

	 try {

	 capturePlayer = Manager.createPlayer("capture://audio?encoding=pcm");

	  if (capturePlayer != null) {
	    capturePlayer.realize();
	    recordControl = (RecordControl) capturePlayer
	        .getControl("javax.microedition.media.control.RecordControl");

	    if (recordControl == null)
	      throw new Exception("No RecordControl available");
	    bos = new ByteArrayOutputStream();
	    recordControl.setRecordStream(bos);
	  } else {
	    throw new Exception("Capture Audio Player is not available");
	  }
	} catch (Exception e) {

	}

	}

	// run method to record voice........................................................
	public void run()
	{

	  try{   



	  capturePlayer.start();
	  recordControl.startRecord();



	  recordControl.commit();
	  recordedAudioArray = bos.toByteArray();
	                                    capturePlayer.close();
	                                    //getRoots();


	                                    FileConnection fc = null;
	                                    DataOutputStream dos = null;
	                                    fc = (FileConnection)Connector.open("file:///e:/");
	                                    if (!fc.exists())
	                                    {
	                                    fc.mkdir();
	                                    }
	                                    fc = (FileConnection) Connector.open("file:///e:/test.wav");
	                                    if (!fc.exists())
	                                    {
	                                    fc.create();
	                                    }
	                                    dos = fc.openDataOutputStream();
	                                    dos.write( recordedAudioArray);
	                                    //output.close();
	                                    //output = null;
	                                    dos = null;
	                                    fc = null;


	  }catch(Exception e){
	  System.out.print(e);
	  }
	  // Thread_2 th2=new Thread_2();
	  //      th2.start();  
	}
	// close to record voice........................................................
	public void commandAction(Command cmd, Displayable disp) {
	    if (cmd == exitCommand) {



	    }

	}

    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void pauseApp() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void startApp() throws MIDletStateChangeException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
