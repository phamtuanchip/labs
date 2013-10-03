package lab5.bluetooth;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;


public class MyMIDlet extends MIDlet {

	private Display display;


	OptionsForm form;

	public MyMIDlet() {

		
		display = Display.getDisplay(this);
		form = new OptionsForm(this, display);
		
	}

	public void startApp() {
		display.setCurrent(form);
	}

	public void pauseApp() {
	}

	public void destroyApp(boolean unconditional) {
	}

	
	
	

}