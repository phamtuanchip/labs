package lab7;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class SimpleGraphics extends MIDlet{
	private Display display;

	public SimpleGraphics(){}

	public void startApp(){
		Canvas canvas = new GraphicsCanvas();
		display = Display.getDisplay(this);
		display.setCurrent(canvas);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){}
}
class GraphicsCanvas extends Canvas{
	public void paint(Graphics g){
		int x = 0, y = 0;
		g.setColor(255, 162, 117);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(0, 0, 255);
		g.drawString("Sandeep Kumar Suman", x, y, g.TOP | g.LEFT);
	}
}