package lab1;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class EventHandlingMIDlet extends MIDlet implements CommandListener {
	private Display display;
	private List list;
	private TextBox input;
	private Command back, main, exit;
	private String currentItem;
    
    public EventHandlingMIDlet() {
		back = new Command("Back", Command.BACK, 0);
		main = new Command("Main", Command.SCREEN, 1);
		exit = new Command("Exit", Command.STOP, 2);
	}
    
    public void startApp() throws MIDletStateChangeException {
		display = Display.getDisplay(this);
		list = new List("Menu Items", Choice.IMPLICIT);
		list.append("EventItem1", null);
		list.append("EventItem2", null);
		list.append("EventItem3", null);
		list.append("EventItem4", null);
		list.addCommand(exit);
		list.setCommandListener(this);
		mainItem();
	}

	public void pauseApp() {
		display = null;
		list = null;
		input = null;
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	void mainItem() {
		display.setCurrent(list);
		currentItem = "Main"; 
	}

	public void show() {
		input = new TextBox("Enter Text Here: ", "", 10, TextField.ANY);
		input.addCommand(back);
		input.setCommandListener(this);
		input.setString("");
		display.setCurrent(input);
	}

	public void commandAction(Command c, Displayable d) {
		String label = c.getLabel();
		if (label.equals("Exit")) {
			destroyApp(true);
		} else if (label.equals("Back")) {
			if(currentItem.equals("Eventitem1") || currentItem.equals("Eventitem2") || currentItem.equals("Eventitem3") || currentItem.equals("Eventitem4")){
				mainItem();
			} 
		} else {
			List down = (List)display.getCurrent();
			switch(down.getSelectedIndex()) {
				case 0: showItem();break;
				case 1: showItem2();break;
				case 2: showItem3();break;
				case 3: showItem4();break;
			}
		}
	}

	public void showItem() {
		show();
		currentItem = "Eventitem1";
	}

	public void showItem2() {
		show();
		currentItem = "Eventitem2"; 
	}

	public void showItem3() {
		show();
		currentItem = "Eventitem3"; 
	}

	public void showItem4() {
		show();
		currentItem = "Eventitem4"; 
	}
}