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

class SweepGame extends GameCanvas implements Runnable {
	private boolean move;
	private int radius;
	private int diameter;
	private int interval;

	public SweepGame() {
		super(true);
		radius = 0;
		diameter = 10;
		interval = 0;
	}
	public void start() {
		move = true;
		Thread t = new Thread(this);
		t.start();
	}
	public void stop() {
		move = false;
	}
	public void render(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		g.setColor(183,251,121);
		g.fillRect(0, 0, width - 1, height - 1);
		int x = diameter;
		int y = diameter;
		int w = width - diameter * 2;
		int h = height - diameter * 2;
		for (int i = 0; i < 17; i=i+2) {
			g.setColor(((17 - i) * 15 - 7),20,((17 - i) * 15 - 7));
			g.fillArc(x, y, w, h, radius + i * 10, 10);
			g.fillArc(x, y, w, h, (radius + 180) % 360 + i * 10, 10);
		}
	}
	public void run() {
		Graphics g = getGraphics();
		while (move) {
			radius = (radius + 1) % 360;
			render(g);
			flushGraphics();
			try {
				Thread.sleep(interval);
			}
			catch (InterruptedException ie) {}
		}
	}
}