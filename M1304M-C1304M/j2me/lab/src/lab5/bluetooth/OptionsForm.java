package lab5.bluetooth;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;

public class OptionsForm extends Form implements CommandListener {

	ChoiceGroup list;
	private MIDlet midlet;
	private Display display;
	Command cmd_ok;

	public OptionsForm(MIDlet midlet, Display display) {
		super("");
		this.midlet = midlet;
		this.display = display;

		list = new ChoiceGroup("Select", ChoiceGroup.EXCLUSIVE);
		list.append("Server", null); // Server
		list.append("Send message", null); // Client

		cmd_ok = new Command("OK", Command.OK, 1);

		this.addCommand(cmd_ok);
		this.append(list);
		setCommandListener(this);

	}

	public void commandAction(Command c, Displayable d) {
		if (c == cmd_ok) {
			switch (list.getSelectedIndex()) {
			case 0: {
				display
						.setCurrent(new NumberClientsForm(midlet, display, this));
				break;
			}

			case 1: {
				new MessageForm(midlet, display, this);
				break;
			}
			}
		}
	}
}
