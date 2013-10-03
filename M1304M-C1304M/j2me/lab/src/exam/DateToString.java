package exam;
import java.util.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class DateToString extends MIDlet implements CommandListener{
	private Display display;
	private Date date;
	private DateField currentDate;
	private Command exit;
	private Form form;
	private int index;
	String dateinstring;
	
	public DateToString(){
		form = new Form("Data and Time");
		date = new Date();
		exit = new Command("Exit", Command.EXIT, 0);
		currentDate = new DateField("", DateField.DATE_TIME);
		currentDate.setDate(date);
		dateinstring = date.toString();
		System.out.println("Date And Time Is In String Format: "+dateinstring);
	}
	
	public void startApp(){
		form.append("CURRENT TIME IS: ");
		index = form.append(currentDate);
		form.addCommand(exit);
		form.setCommandListener(this);
		display = Display.getDisplay(this);
		display.setCurrent(form);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command cmd, Displayable s){
		if (cmd == exit){
			destroyApp(true);
		}
	}
}