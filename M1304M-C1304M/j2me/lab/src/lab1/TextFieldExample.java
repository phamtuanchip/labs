import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class TextFieldExample extends MIDlet implements CommandListener{
	private Form form;
	private Display display;
	private TextField name, company;
	private Command ok;
	
	public TextFieldExample(){
		name = new TextField("Name:", "", 30, TextField.ANY);
		company = new TextField("Company Name:", "", 30, TextField.ANY);
		ok = new Command("OK", Command.OK, 2);
	}

	public void startApp(){
		display = Display.getDisplay(this);
		Form form = new Form("Text Field");
		form.append(name);
		form.append(company);
		form.addCommand(ok);
		form.setCommandListener(this);
		display.setCurrent(form);
	}

	public void pauseApp(){
	
	}

	public void destroyApp(boolean destroy){
		notifyDestroyed();
	}

	public void showInput(){
		display = Display.getDisplay(this);
		String n = name.getString();
		String c = company.getString();
		Form form = new Form("Input Value");
		form.append(n);
		form.append(c);
		display.setCurrent(form);
	}


	public void commandAction(Command c, Displayable d) {
		String label = c.getLabel();
		if(label.equals("OK")){
			showInput();
		} 
	}
}