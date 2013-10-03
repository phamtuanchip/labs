package lab8;
import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class ReadFile extends MIDlet implements CommandListener{
	private Display display;
	private Form form;
	private Command read, exit;
	private Alert alert;

	public ReadFile(){
		display = Display.getDisplay(this);
		read = new Command("Read", Command.SCREEN, 1);
		exit = new Command("Exit", Command.EXIT, 1);    
		form = new Form("Read File");
		form.addCommand(exit);
		form.addCommand(read);
		form.setCommandListener(this);
	}

	public void startApp(){
		display.setCurrent(form);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable s){
		String label = c.getLabel();
		if (label.equals("Read")){
			String string = file();
			if (string != null){
				alert = new Alert("Reading", string, null, null);
				alert.setTimeout(Alert.FOREVER);
				display.setCurrent(alert, form);
			}
		} else if (label.equals("Exit")){
			destroyApp(false);
		} 
	}

	private String file(){
		InputStream is = getClass().getResourceAsStream("help.txt");
		StringBuffer sb = new StringBuffer();
		try{
			int chars, i = 0;
			while ((chars = is.read()) != -1){
				sb.append((char) chars);
			}
			return sb.toString();
		}catch (Exception e){}
		return null;
	}
}