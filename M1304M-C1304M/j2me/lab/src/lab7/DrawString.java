package lab7;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class DrawString extends MIDlet{
	private Display display;
	
	public void startApp(){
		display = Display.getDisplay(this);
		display.setCurrent (new TextCanvas()); 
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){}

	public void commandAction(){}
}

class TextCanvas extends Canvas{
	public void paint(Graphics g){
		g.setColor(255, 0, 0);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(0, 0, 255);
		g.drawString("Top/Left", 0, 0, Graphics.TOP | Graphics.LEFT);
		g.drawString("Baseline/Center", getWidth() / 2, getHeight() / 2, Graphics.HCENTER | Graphics.BASELINE);
		g.drawString("Bottom/Right", getWidth(), getHeight(), Graphics.BOTTOM | Graphics.RIGHT);
	}
}