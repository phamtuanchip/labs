package lab7;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class LineCanvas extends MIDlet{
	private Display display;

	public void startApp(){
		Canvas canvas = new CanvasLine();
		display = Display.getDisplay(this);
		display.setCurrent(canvas);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){}
}

class CanvasLine extends Canvas {
	public void paint(Graphics g) {
		int width = getWidth();
        int height = getHeight();

        g.setColor(255, 162, 117);
        g.fillRect(0, 0, width, height);

        g.setColor(0, 0, 255);
        g.drawLine(0, height/2, width - 1, height/2);
		
		g.setStrokeStyle(Graphics.DOTTED);
        g.setColor(0xFFFF00);
        g.drawLine(0, height/4, width - 1, height/4);

        g.setColor(0, 0, 255);
        g.setStrokeStyle(Graphics.SOLID);
        g.drawLine(0, 0, width - 1, height - 1);
    }    
}