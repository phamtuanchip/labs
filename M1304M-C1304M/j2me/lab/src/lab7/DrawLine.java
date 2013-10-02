package lab7;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class DrawLine extends MIDlet{
	private Display display;

	public void startApp(){
		display = Display.getDisplay(this);
		display.setCurrent (new DrawingCanvas()); 
	}

	public void pauseApp(){}

	public void destroyApp (boolean forced){}
}

class DrawingCanvas extends Canvas{
	public void paint (Graphics g){
		g.setColor (255, 0, 0);
		g.fillRect (0, 0, getWidth(), getHeight());
		g.setColor (0, 0, 255);
		g.fillRect (20, 30, 200, 80);
		g.setColor (128, 0, 255);
		g.drawLine (0, 0, 100, 200);
	}
}