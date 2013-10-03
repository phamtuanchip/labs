package lab5.bluetooth;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;

public class MessageForm  implements CommandListener {
	private MIDlet midlet;
	private Display display;
	private TextBox text;
	private Command cmd_ok;
	private Command cmd_exit;
	Form previousForm;
	

	public MessageForm(MIDlet midlet, Display display, Form previousForm) {
		
		this.midlet = midlet;
		this.display = display;
		this.previousForm = previousForm;
		text = new TextBox("Message:", "", 100, TextField.ANY);
		
		cmd_ok = new Command("OK", Command.OK, 0);
		cmd_exit = new Command("Exit", Command.EXIT, 0);
		
		
		text.addCommand(cmd_ok);
		text.addCommand(cmd_exit);
		
		text.setCommandListener(this);
		display.setCurrent(text);
	}

	public void commandAction(Command c, Displayable d) {
		
		if (c == cmd_ok) {
			if (text.getString().length() > 0) {
				new BluetoothClient(midlet, display, text.getString());
			} else {
				display.setCurrent(new Alert("Insert a message."));
			}
		} else if (c == cmd_exit) {
			midlet.notifyDestroyed();
		}

	}

}