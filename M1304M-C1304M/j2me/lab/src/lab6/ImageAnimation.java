package lab6;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class ImageAnimation extends MIDlet implements CommandListener{
	private Display  display;
	private Command exit;
	
	protected void startApp(){
		display = Display.getDisplay(this);
		ImageCanvas2 canvas = new ImageCanvas2();
		exit = new Command("Exit", Command.EXIT, 1);
        canvas.addCommand(exit);
        canvas.setCommandListener(this);
        display.setCurrent(canvas);
    }
    
    protected void pauseApp(){}
    
    protected void destroyApp(boolean unconditional){
		notifyDestroyed();
	}
    
    public void commandAction(Command c, Displayable d){
		String label = c.getLabel();
		if(label.equals("Exit")) {
			destroyApp(true);
		}
	}
}

