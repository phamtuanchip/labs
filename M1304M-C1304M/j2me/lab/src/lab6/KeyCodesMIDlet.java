package lab6;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class KeyCodesMIDlet extends MIDlet{
	private Display  display;
	private KeyCodeCanvas canvas;

	public KeyCodesMIDlet(){
		display = Display.getDisplay(this);
		canvas  = new KeyCodeCanvas(this);
	}

	protected void startApp(){
		display.setCurrent(canvas);
	}

	protected void pauseApp(){}

	protected void destroyApp( boolean unconditional ){
		notifyDestroyed();
	}
}

class KeyCodeCanvas extends Canvas implements CommandListener{
	private Command exit;
	private String keyValue = null;
	private KeyCodesMIDlet midlet;

	public KeyCodeCanvas(KeyCodesMIDlet midlet){
		this.midlet = midlet;

		exit = new Command("Exit", Command.EXIT, 1);
		addCommand(exit);
		setCommandListener(this);
	} 

	protected void paint(Graphics g){
		g.setColor(255, 0, 0);
		g.fillRect(0, 0, getWidth(), getHeight());

		if (keyValue != null){
			g.setColor(0, 0, 255);
			g.drawString(keyValue, getWidth()/2, getHeight()/2, Graphics.TOP | Graphics.HCENTER);
		}
	}

	public void commandAction(Command c, Displayable d){
		String label = c.getLabel();
		if(label.equals("Exit"))
			midlet.destroyApp(true);
	}

	protected void keyPressed(int keyCode){
		keyValue = getKeyName(keyCode);
		repaint();
	}
}