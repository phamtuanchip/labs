package lab1;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class LabelMIDlet extends MIDlet implements CommandListener{
	private Display display; 
	private Form form; 
	private StringItem item; 
	private Command next, exit; 
	
	public LabelMIDlet(){
		display = Display.getDisplay(this);	
		form = new Form("Label Text"); 
		item = new StringItem("UserId: ", "sandeep15284@rediffmail.com");
		next = new Command("Next", Command.SCREEN, 1);
		exit = new Command("Exit", Command.EXIT, 1);
		form.addCommand(exit);
		form.addCommand(next);    
		form.append(item);
		form.setCommandListener(this);   
	}

	public void startApp(){		
		display.setCurrent(form);
	}

	public void pauseApp(){ }

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable s){
		String label = c.getLabel();
		if (label.equals("Next")){
			item.setLabel("E-MAIL ID: ");
			item.setText("sandeep15284@rediffmail.com"); 
			form.removeCommand(next);
		} else if (label.equals("Exit")) {
			destroyApp(true);
		} 
	}
}