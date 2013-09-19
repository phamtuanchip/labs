package lab1;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;


public class CheckBoxExample extends MIDlet implements CommandListener {
	private Display display;
	private Form form;
	private Command exit, choose;
	private ChoiceGroup technology;
	private int index;

	public CheckBoxExample() {
		form = new Form("Technologies");
		technology = new ChoiceGroup("Select Technology Which You Know", Choice.MULTIPLE);
		exit = new Command("Exit", Command.EXIT, 1);
		choose = new Command("Choose", Command.SCREEN, 2);		
	}

	public void startApp() {
		display = Display.getDisplay(this);
		technology.append("JAVA", null);
		technology.append("J2ME", null);
		technology.append("J2EE", null);
		technology.append("JSF", null);

		index = form.append(technology);
		form.addCommand(exit);
		form.addCommand(choose);
		form.setCommandListener(this);
		display.setCurrent(form);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable displayable){
		String label = c.getLabel();
		if (label.equals("Choose")) {
			StringItem message[] = new StringItem[technology.size()];
			boolean get[] = new boolean[technology.size()];
			technology.getSelectedFlags(get);
			for (int i = 0; i < get.length; i++) {
				if (get[i]) {
					message[i] = new StringItem("Your Choice is: ", technology.getString(i));
					form.append(message[i]);
				}
			}
			form.delete(index);
			form.removeCommand(choose);
		} else if (label.equals("Exit")){
			destroyApp(false);
		}
	}
}