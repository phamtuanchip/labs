package lab7;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;

public class Crossline extends MIDlet{

	private Display display;

	public void startApp(){
		Canvas canvas = new CanvasCrossLine();
		display = Display.getDisplay(this);
		display.setCurrent(canvas);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){}
}

class CanvasCrossLine extends Canvas {
	private Image image;

	public void paint(Graphics g) {
		if (image == null){
			initialize();
		}
		g.drawImage(image, 0, 0, Graphics.TOP | Graphics.LEFT);
	}

	private void initialize() {
		int width = getWidth();
		int height = getHeight();

		image = Image.createImage(width, height);
		Graphics g = image.getGraphics();
		g.setColor(0, 0, 255);
		g.drawRect(0, 0, width - 1, height - 1);

		g.setColor(255, 255, 0);
		g.fillRect(0, 0, width, height);
		
		g.setColor(255, 0, 0);
		g.drawLine(0, 0, width - 1, height - 1);
		g.drawLine(width - 1, 0, 0, height - 1);
	}
}