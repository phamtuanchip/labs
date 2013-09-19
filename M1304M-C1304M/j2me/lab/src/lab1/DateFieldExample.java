package lab1;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import java.util.Date;
import java.util.TimeZone;

public class DateFieldExample extends MIDlet{
	private Form form;
	private Display display;
	private DateField datein, dateout;	
	private static final int DATE = 0;

	public DateFieldExample(){
		datein = new DateField("Date In:", DateField.DATE, TimeZone.getTimeZone("GMT"));
		dateout = new DateField("Date Out:", DateField.DATE, TimeZone.getTimeZone("GMT"));
	}

	public void startApp(){
		display = Display.getDisplay(this);
		Form form = new Form("Date Field");
		form.append(datein);
		form.append(dateout);
		display.setCurrent(form);
	}

	public void pauseApp(){
	
	}

	public void destroyApp(boolean destroy){
		notifyDestroyed();
	}
}