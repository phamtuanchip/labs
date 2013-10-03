package lab8;
import java.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.media.*;
import javax.microedition.midlet.*;
//import javax.microedition.media.TimeBase;

public class AudioMIDlet extends MIDlet{

	Player p1, p2;
	
	public void startApp(){
		try {

			p1 = Manager.createPlayer(getClass().getResourceAsStream("/kabhi-alvida-na-kehna.wav"), "audio/x-wav");
			p1.realize();

			p2 = Manager.createPlayer(getClass().getResourceAsStream("/aagepiche.wav"), "audio/x-wav");
			p2.realize();

			//p2.setTimeBase(p1.getTimeBase());

			p1.prefetch();
			p2.prefetch();

			p1.start();
			p2.start();

		}catch(IOException ioe){

		}catch(MediaException me){}
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){}
}