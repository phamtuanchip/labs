package lab1;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class ExclusiveChoiceList extends MIDlet{
	private Display display;
	private List list;

	public ExclusiveChoiceList() {
		list = new List("Movies", Choice.EXCLUSIVE);
	}

	public void startApp(){
		display = Display.getDisplay(this);
		list.append("The Legend of Bhagat Singh", null);
		list.append("Mother India", null);
		list.append("Lagaan", null);
		list.append("Chak De..", null);
		list.append("Hum Aapke Hain Kaun", null);
		display.setCurrent(list);
	}

    public void pauseApp() {}

    public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}
}