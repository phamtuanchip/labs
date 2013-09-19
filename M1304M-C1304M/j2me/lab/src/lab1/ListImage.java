package lab1;
import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class ListImage extends MIDlet implements CommandListener{
	private Display display;
	private List list;
	private Command exit, next;
	private Image car, airplane, hotel, mobile, cartoon;
	String[] stringElements = {"Aero plane", "Car", "Hotel", "Mobile"};
	
	public ListImage(){
		try{
			airplane = Image.createImage("/airplane.png");
			car = Image.createImage("/car1.png");
			hotel = Image.createImage("/hotel1.png");
			mobile = Image.createImage("/mobile_ico.png");
			cartoon = Image.createImage("/cartoon.png");
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

	public void startApp() {
		display = Display.getDisplay(this);
		Image[] imageElements = {airplane, car, hotel, mobile};
		list = new List("List + Image", List.IMPLICIT, stringElements, imageElements);
		next = new Command("Select", Command.SCREEN, 0);
		exit = new Command("Exit", Command.EXIT, 0);
		list.addCommand(next);
		list.addCommand(exit);
		list.setCommandListener(this);
		display.setCurrent(list);
	}

	public void pauseApp() {}

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable s){
		int index = list.getSelectedIndex();
		if (c == next || c == List.SELECT_COMMAND) {
			Alert alert = new Alert("Selected", "You are selected: " + list.getString(index) + ".", cartoon, AlertType.INFO);
			display.setCurrent(alert, list);
		} else if(c == exit){
			destroyApp(true);
		}
	}
}