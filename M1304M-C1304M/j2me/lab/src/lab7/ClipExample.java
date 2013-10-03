package lab7;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class ClipExample extends MIDlet{
	private Display display;
	
	public void startApp(){
		display = Display.getDisplay(this);
		display.setCurrent (new ClipCanvas()); 
	}

	public void pauseApp(){}

	public void destroyApp (boolean unconditional){}

	public void commandAction(){}
}

class ClipCanvas extends Canvas{
	public void paint(Graphics g){
		g.setColor(255, 0, 0);
		g.fillRect(0, 0, getWidth(), getHeight());

		int map = Math.min(getWidth(), getHeight());    
		g.setColor(0, 0, 255);

		g.setStrokeStyle(Graphics.DOTTED);
		g.drawLine(0, 0, map, map);

		g.setClip(map/4, map/4, map/2, map/2);

		g.setStrokeStyle(Graphics.SOLID);
		g.drawLine(0, 0, map, map);
	}
}