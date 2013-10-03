package exam;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;

public class PhoneBookExample extends MIDlet implements CommandListener {
	private Command exit, next, New;
	private TextBox name, number;
	private Display display;
	private String nam, no;
	private Form form;
	
	public PhoneBookExample(){
		next = new Command("Next", Command.SCREEN, 2);
		exit = new Command("Exit", Command.SCREEN, 2);
		New = new Command("New", Command.SCREEN, 2);
	}

	public void startApp(){
		display = Display.getDisplay(this);
		name = new TextBox("Enter Name", "", 30, TextField.ANY);
		name.addCommand(next);
		name.setCommandListener(this);
		number = new TextBox("Enter Number", "", 13, TextField.PHONENUMBER);
		number.addCommand(New);
		number.addCommand(exit);
		number.setCommandListener(this);
		display.setCurrent(name);
	}

	public void pauseApp() {}

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable s){
		String label = c.getLabel();
		if (label.equals("Exit")){
			nam = name.getString();
			no = number.getString();
			System.out.println("Name = " + name.getString() + ", Number = "+ number.getString());
			destroyApp(false);
		} else if (label.equals("Next")){
			number.setString("");
			display.setCurrent(number);
		} else if (label.equals("New")){
			display.setCurrent(name);
			nam = name.getString();
			no = number.getString();
			System.out.println("Name = " + name.getString() + ", Number = "+ number.getString());
			name.setString("");
		}
	}
}