package lab6;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class CanvasDrawExample extends MIDlet implements CommandListener{
	private Display display;
	private List list;
	private Command ok, exit;
	private CanvasDraw canvasDraw;
	int listItemIndex;

	public CanvasDrawExample(){
		
		display = Display.getDisplay(this);
		list = new List("CanvasDrawList", List.IMPLICIT);
		canvasDraw = new CanvasDraw(listItemIndex, display, list);
		ok = new Command("Draw", Command.OK, 2);
		exit = new Command("Exit", Command.EXIT, 2);
		list.append("Draw Lines", null);
		list.append("Draw Rectangle", null);
		list.append("Draw Rounded Rectangle", null);
		list.append("Draw Arc", null);
		list.append("Draw Triangle", null);
		list.addCommand(ok);
		list.addCommand(exit);
		list.setCommandListener(this);
	}
	
	public void startApp(){
		display.setCurrent(list);
	}

	public void pauseApp(){}
	
	public void destroyApp(boolean unconditional){
		notifyDestroyed ();
	}

	public void commandAction(Command c, Displayable d){
		listItemIndex = list.getSelectedIndex();
		if(c == ok){
			display.setCurrent(canvasDraw);
		}else if(c == exit){
			destroyApp(true);
		}
	}

}