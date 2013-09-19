package lab1;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class AppendItem extends MIDlet{
	private Display display;
	private MIDlet midlet;

	public void startApp(){
		Form form = new MyForm("MY FORM");
		display = Display.getDisplay(this);
		display.setCurrent(form);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){}

	class MyForm extends Form implements CommandListener{
		private StringItem textItem;
		private Command exit;
		
		public MyForm(String title){
			super(title);
			exit = new Command("Exit", Command.BACK, 1);
			this.textItem = new StringItem (null, "Hello Item");
			addCommand(exit);
			append(this.textItem);
			this.setCommandListener(this);
		}

		public void commandAction(Command c, Displayable d){
			String label = c.getLabel();
			if(label.equals("Exit")){
				midlet.notifyDestroyed();           
			}
		}
	}
}