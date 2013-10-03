package lab8;

import java.io.InputStream;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.StringItem;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;

public class Thread_2 implements CommandListener, Runnable{

	private StringItem message;

	public Thread_2()
	{

	}

	// Playing voice........................................................
	public void run()
	{
	   try{  
	       InputStream is = getClass().getResourceAsStream("calibartionAudio/sineTone.wav");
	                                    if (is == null)
	                                    {
	                                     message.setText("null stream ");
	                                    }
	                                    Player player = Manager.createPlayer(is,"audio/basic");



	                                    //player = Manager.createPlayer("file:///e:/test.wav");
	                                    player.realize();

	                                    VolumeControl vc = (VolumeControl) player.getControl("VolumeControl");
	                                    if(vc != null)
	                                    {
	                                    vc.setLevel(100);
	                                     //message.setText("playing ");
	                                    }
	                                    player.prefetch();
	                                    player.start();
	   }catch(Exception e){
	    System.out.print(e);}

	 }

	    public void commandAction(Command c, Displayable d) {
	        throw new UnsupportedOperationException("Not supported yet.");
	    }
	}