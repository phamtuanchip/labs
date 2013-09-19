package lab1;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

public class CommandMIDlet extends MIDlet{
	private Form form;
	private Display display;
	private Command screen, back, cancel, ok, help, stop, exit, item;  
	
	public CommandMIDlet(){
		screen = new Command("Screen", Command.SCREEN, 1);
		back = new Command("Back", Command.BACK, 2);
		cancel = new Command("Cancel", Command.CANCEL, 3);
		ok = new Command("Ok", Command.OK, 4);
		help = new Command("Help", Command.HELP, 5);
		stop = new Command("Stop", Command.STOP, 6);
		exit = new Command("Exit", Command.EXIT, 7);
		item = new Command("Item", Command.ITEM, 8);
	}

	public void startApp(){
		display = Display.getDisplay(this);
		Form form = new Form("Command Priority");
		form.addCommand(screen);
		form.addCommand(back);
		form.addCommand(cancel);
		form.addCommand(ok);
		form.addCommand(help);
		form.addCommand(stop);
		form.addCommand(exit);
		form.addCommand(item);
		display.setCurrent(form);
	}

	public void pauseApp(){}

	public void destroyApp(boolean destroy){
		notifyDestroyed();
	}
}