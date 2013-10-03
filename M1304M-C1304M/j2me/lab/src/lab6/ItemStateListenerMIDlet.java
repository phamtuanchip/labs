package lab6;
import java.util.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class ItemStateListenerMIDlet extends MIDlet{
	private Display display;
	private Form form;
	private Date date = new Date();
	private Calendar calendar;
	private DateField today;

	public ItemStateListenerMIDlet(){
		display = Display.getDisplay(this);
	}

	public void startApp(){
        form = new Form("ItemStateListener");
        ItemStateListener listener = new ItemStateListener(){
			Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

			public void itemStateChanged(Item item){
				calendar.setTime(((DateField)item).getDate());
			}
		};
		form.setItemStateListener(listener);        
        today = new DateField("Today's Date:", DateField.DATE);
        today.setDate(date);
        form.append(today);
        display.setCurrent(form);
    }

	public void pauseApp(){
        display = null;
    }

	public void destroyApp(boolean unconditional){
        notifyDestroyed();
    }
}
