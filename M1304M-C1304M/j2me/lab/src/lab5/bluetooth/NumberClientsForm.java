package lab5.bluetooth;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import javax.microedition.midlet.MIDlet;



public class NumberClientsForm extends Form implements CommandListener{
	private ChoiceGroup numClients;
	private Command cmd_ok, cmd_exit, cmd_back;
	MIDlet midlet;
	Display display;
	Form previousScreen;
	
	
	public NumberClientsForm(MIDlet midlet, Display display, Form gameOptionsForm) {
		super("");
		
		this.midlet = midlet;
		this.display = display;
		previousScreen = gameOptionsForm;
		numClients = new ChoiceGroup("Number of clients", ChoiceGroup.EXCLUSIVE);
		numClients.append("1", null);
		numClients.append("2", null);
		numClients.append("3", null);
		numClients.append("4", null);
		cmd_ok = new Command("OK", Command.OK, 0);
		cmd_exit = new Command("Exit", Command.EXIT, 0);
		cmd_back = new Command("Back", Command.BACK, 0);
		this.addCommand(cmd_ok);
		this.addCommand(cmd_exit);
		this.addCommand(cmd_back);
		this.append(numClients);
		setCommandListener(this);
		
		
	}

	public void commandAction(Command c, Displayable d) {
		if (c == cmd_ok){
			new BluetoothServer(midlet, display, previousScreen, numClients.getSelectedIndex()+1);
		}else if (c== cmd_back){
			display.setCurrent(previousScreen);
		}else if (c== cmd_exit){
			midlet.notifyDestroyed();
		}
		
	}
}
