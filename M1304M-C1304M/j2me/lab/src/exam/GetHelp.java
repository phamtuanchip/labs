package exam;
import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class GetHelp extends MIDlet implements CommandListener{
	private Display display;
	private Form form;
	private Command help, exit;
	private Alert alert;

	public GetHelp(){
		display = Display.getDisplay(this);
		help = new Command("Help", Command.SCREEN, 1);
		exit = new Command("Exit", Command.EXIT, 1);    
		form = new Form("New Form");
		form.addCommand(exit);
		form.addCommand(help);
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
		if (label.equals("Help")){
			String string = helpDATA();
			if (string != null){
				alert = new Alert("Help", string, null, null);
				alert.setTimeout(Alert.FOREVER);
				display.setCurrent(alert, form);
			}
		} else if (label.equals("Exit")){
			destroyApp(false);
		} 
	}

	private String helpDATA(){
		InputStream is = getClass().getResourceAsStream("help.txt");
		StringBuffer sb = new StringBuffer();
		try{
			int chars, i = 0;
			while ((chars = is.read()) != -1){
				sb.append((char) chars);
			}
			return sb.toString();
		}catch (Exception e){         
			System.out.println("Can't create Buffers");
		}
		return null;
	}
}