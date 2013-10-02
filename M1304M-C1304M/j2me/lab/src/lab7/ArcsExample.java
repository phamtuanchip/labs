package lab7;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class ArcsExample extends MIDlet{
	private Display display;

	public void startApp(){
		Canvas canvas = new ArcsCanvas();
		display = Display.getDisplay(this);
		display.setCurrent(canvas);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){}
}

class ArcsCanvas extends Canvas {
    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        g.setColor(255, 162, 117);
        g.fillRect(0, 0, width, height);

        g.setColor(0, 0, 255);
        g.fillArc(0, 0, width/2, height/2, 0, 90);
        g.setStrokeStyle(Graphics.DOTTED);
        g.setColor(0xffff00);
        g.drawRect(0, 0, width/2, height/2);

        g.setStrokeStyle(Graphics.SOLID);
        g.setColor(0, 0, 255);
        g.fillArc(width/2, 0, width/2, height/2, 0, -90);
        g.setStrokeStyle(Graphics.DOTTED);
        g.setColor(0xffff00);
        g.drawRect(width/2, 0, width/2, height/2);
       
        g.setStrokeStyle(Graphics.SOLID);
        g.setColor(0, 0, 255);
        g.fillArc(0, height/2, width, height/2, -90, -180);
        g.setStrokeStyle(Graphics.DOTTED);
        g.setColor(0xffff00);
        g.drawRect(0, height/2, width, height/2);        
    }
}