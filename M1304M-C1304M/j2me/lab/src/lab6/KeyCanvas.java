package lab6;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class KeyCanvas extends MIDlet{

	private Display display;

	public void startApp(){
		Canvas canvas = new CanvasKey();
		display = Display.getDisplay(this);
		display.setCurrent(canvas);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){}
}

