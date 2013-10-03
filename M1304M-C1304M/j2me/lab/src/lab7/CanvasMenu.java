package lab7;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class CanvasMenu extends MIDlet implements CommandListener{
	CanvasString canvas;
	private Command exit;
	private Command toggle;

	public CanvasMenu() {
		canvas = new CanvasString();
	}

	public void startApp() throws MIDletStateChangeException {
		Display.getDisplay(this).setCurrent(canvas);
		exit = new Command("Exit", Command.EXIT, 7);
		toggle = new Command("Show", Command.SCREEN, 1);
		canvas.addCommand(exit);
		canvas.addCommand(toggle);
		canvas.setCommandListener(this);
		canvas.repaint();
	}

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void pauseApp(){}

	public void commandAction(Command c, Displayable s){
		String label = c.getLabel();
		if(label.equals("Show")){
			canvas.toggleString();
		} else if(label.equals("Exit")) {
			destroyApp(false);
		}
	}
}

class CanvasString extends Canvas {
	boolean string = true;
	void toggleString() {
		string = !string;
		repaint();
	}

	public void paint(Graphics g) {		
		g.setColor(0xccff66);
		g.fillRect(0, 0, getWidth(), getHeight());
		if(string) {
			Font font = g.getFont();
			int fontHeight = font.getHeight();
			int fontWidth = font.stringWidth("This is the Toggle Message");
			g.setColor(223, 0, 112);
			g.setFont(font);
			g.drawString("This is the Toggle Message", (getWidth()-fontWidth)/2, (getHeight()-fontHeight)/2, g.TOP|g.LEFT);
		}
	}
}