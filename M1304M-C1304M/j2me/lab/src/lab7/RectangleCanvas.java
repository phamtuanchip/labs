package lab7;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class RectangleCanvas extends MIDlet{
	private Display display;

	public void startApp(){
		Canvas canvas = new CanvasRectangle();
		display = Display.getDisplay(this);
		display.setCurrent(canvas);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){}
}

class CanvasRectangle extends Canvas {
    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        g.setColor(255, 162, 117);
        g.fillRect(0, 0, width, height);

        g.setColor(0, 0, 255);
        g.drawRect(width/4, 0, width/2, height/4);

        g.setStrokeStyle(Graphics.DOTTED);
        g.drawRect(width/4 + 4, 4, width/2 - 8, height/4 - 8);

        g.setStrokeStyle(Graphics.SOLID);
        g.drawRoundRect(width/4, height/2, width/2, height/4, 16, 8);

		g.setStrokeStyle(Graphics.SOLID);
        g.drawRoundRect(width/4 + 4, height/2 + 4, width/2 - 8, height/4 - 8, 16, 8);
    }
}
