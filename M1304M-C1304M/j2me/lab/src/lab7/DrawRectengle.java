package lab7;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class DrawRectengle extends MIDlet {
	public void startApp () {
		Display.getDisplay (this).setCurrent (new DrawingDemoCanvas ()); 
	}

	public void pauseApp () {}

	public void destroyApp (boolean forced) {}
}

class DrawingDemoCanvas extends Canvas {
	public void paint (Graphics g) {
		g.setColor (255, 0, 0);
		g.fillRect (0, 0, getWidth (), getHeight ());
		g.setColor (0, 0, 255);
		g.fillRect (20, 30, 200, 80);
	}
}