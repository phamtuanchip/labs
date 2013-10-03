package lab6;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class UsingRepaintMethod extends MIDlet{
	private Display  display;
	private GameActionCanvas canvas;

	public UsingRepaintMethod(){
		display = Display.getDisplay(this);
		canvas  = new GameActionCanvas(this);
	}

	protected void startApp(){
		display.setCurrent( canvas );
	}

	protected void pauseApp(){ }

	protected void destroyApp( boolean unconditional ){
		notifyDestroyed();
	}

	public void exitMIDlet(){
		destroyApp(true);
	}
}

class GameActionCanvas extends Canvas implements CommandListener{
	private Command exit;
	private String keyValue = null;
	private UsingRepaintMethod midlet;

	public GameActionCanvas(UsingRepaintMethod midlet){
		this.midlet = midlet;

		exit = new Command("Exit", Command.EXIT, 1);
		addCommand(exit);
		setCommandListener(this);
	} 
	
	protected void paint(Graphics g){
		g.setColor(0, 0, 255);
		g.fillRect(0, 0, getWidth(), getHeight());

		if (keyValue != null){
			g.setColor(255, 0, 0);
			g.drawString(keyValue, getWidth()/2, getHeight()/2, Graphics.TOP | Graphics.HCENTER);
		}
	}

	public void commandAction(Command c, Displayable d){
		if (c == exit)
			midlet.exitMIDlet();
	}

	protected void keyPressed(int keyCode){
		switch (getGameAction(keyCode)){
			case FIRE:
			case UP: 
			case DOWN:
			case LEFT:
			case RIGHT:
			case GAME_A:
			case GAME_B:
			case GAME_C:
			case GAME_D:
			default:
			keyValue = getKeyName(keyCode);
		}        
		repaint();
	}
}