package lab1;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class TextBoxMIDlet extends MIDlet{
	
	private Display display; 

	public TextBoxMIDlet() {
		display = Display.getDisplay(this);
	}

	public void startApp() {
		TextBox t = new TextBox("TextBox Example", "Hello Sandeep Kumar Suman !", 256, 0);
		display.setCurrent(t);
	}

	public void pauseApp() {}

	public void destroyApp(boolean unconditional) {}
}