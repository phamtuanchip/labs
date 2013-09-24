package lab1;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class TextFieldExample extends MIDlet implements CommandListener{
	private Form form;
	private Display display;
	private TextField name, company;
	private Command ok;
	private Command back;
	private Command exit;
	
	public TextFieldExample(){
		
		ok = new Command("Signin", Command.OK, 2);
		back = new Command("Back", Command.BACK, 1);
		exit = new Command("exit", Command.EXIT, 0);
		
	}

	public void startApp(){
		
		initForm();
	}
	
	void initForm(){
		display = Display.getDisplay(this);
		Form form = new Form("Text Field");
		name = new TextField("id:", "", 30, TextField.ANY);
		company = new TextField("pass:", "", 30, TextField.PASSWORD);
		form.append(name);
		form.append(company);
		form.addCommand(exit);
		
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
		form.addCommand(back);
		form.setCommandListener(this);
		display.setCurrent(form);
	}


	public void commandAction(Command c, Displayable d) {
		String label = c.getLabel();
		int cType = c.getCommandType();
		if(cType == Command.OK){
			showInput();
		} else if (c.getCommandType() == Command.BACK){
			initForm();
		}  
	}
}