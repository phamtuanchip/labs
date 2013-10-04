package lab6;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import javax.microedition.midlet.*;

public class CanvasGame extends MIDlet{

	private Command back;
	private Display display;
	final SweepGame game = new SweepGame();

	public void startApp() {
		back = new Command("Back", Command.BACK, 0);
		game.start();
		game.addCommand(back);
		game.setCommandListener(new CommandListener(){
			public void commandAction(Command c, Displayable s) {
				game.stop();
				notifyDestroyed();
			}
		});
		display = Display.getDisplay(this);
		display.setCurrent(game);
	}

	public void pauseApp() {}

	public void destroyApp(boolean unconditional) {}
}

