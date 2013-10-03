package exam;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

public class KeyEventMIDlet extends MIDlet{
	private Display display;
	private Command exit;

	public void startApp (){
		display = Display.getDisplay(this);
		Canvas keyCanvas = new Canvas(){
			public void paint(Graphics g){}

			protected void keyPressed(int keyCode){
				if (keyCode > 0){
					System.out.println("keyPressed " +((char)keyCode));
					System.out.println("keyReleased " +((char)keyCode));
				} else {
					System.out.println("keyPressed action " +getGameAction(keyCode));
					System.out.println("keyReleased action " +getGameAction(keyCode));
				}                  
			}
		};
		exit = new Command("Exit", Command.STOP, 1);
		keyCanvas.addCommand(exit);
		keyCanvas.setCommandListener(new CommandListener() {
			public void commandAction(Command c, Displayable d) {
				String label = c.getLabel();
				if(label.equals("Exit")) {
					notifyDestroyed();
				}
			}
		});
		display.setCurrent(keyCanvas);
	}

    public void pauseApp(){
		display = null;
	}

	public void destroyApp(boolean unconditional){}	
}