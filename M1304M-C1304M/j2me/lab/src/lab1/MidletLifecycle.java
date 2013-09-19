package lab1;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

public class MidletLifecycle extends MIDlet{
	private Form form;
	
	private Display display;

	public MidletLifecycle(){
		System.out.println("MidletLifecycle constructure");
	}

	public void startApp(){
		form = new Form("Midlet Lifecycle");
		display = Display.getDisplay(this);
		String msg = "This is the Lifecycle of Midlet!";
		form.append(msg);
		display.setCurrent(form);
	}

	public void pauseApp(){
		System.out.println("You are in pauseApp()...");
	}

	public void destroyApp(boolean destroy){
		System.out.println("You are in destroyApp()...");
		notifyDestroyed();
	}
}