package lab2;
import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;

public class RecordStoreMIDlet extends MIDlet implements CommandListener{
	private Display display;
	private Alert alert;
	private Form form; 
	private Command exit, start, close; 
	private RecordStore r_store;
	private Image image;
			
	public void startApp(){
		try{image = Image.createImage("/error.png");}catch(Exception e){}
		display = Display.getDisplay(this);
		start = new Command("Start", Command.SCREEN, 1);
		close = new Command("Close", Command.SCREEN, 1);
		exit = new Command("Exit", Command.SCREEN, 1);	
		form = new Form("Record Store");
		form.addCommand(start);
		form.addCommand(close);
		form.addCommand(exit);
		form.setCommandListener(this);
		display.setCurrent(form);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable displayable){
		String label = c.getLabel();
		if(label.equals("Start")){
			try{
				r_store = RecordStore.openRecordStore("Sandeep Kumar Suman", true );
				String name = r_store.getName();
				System.out.println("Name: "+name);
			}catch (Exception error){
				alert = new Alert("Exception", error.toString(), image, AlertType.WARNING); 
				alert.setTimeout(Alert.FOREVER); 
				display.setCurrent(alert);
			}			      
		}else if(label.equals("Close")){
			try{
				r_store.closeRecordStore();
				System.out.println("Successfully Closed Record");
			}catch (Exception error){
				alert = new Alert("Exception", error.toString(), image, AlertType.WARNING); 
				alert.setTimeout(Alert.FOREVER); 
				display.setCurrent(alert);
			}
		}else if (RecordStore.listRecordStores() != null){
			try{
				RecordStore.deleteRecordStore("Sandeep Kumar Suman");
				System.out.println("Successfully Deleted The Record");
			}catch (Exception error){
				alert = new Alert("Exception", error.toString(), image, AlertType.WARNING); 
				alert.setTimeout(Alert.FOREVER); 
				display.setCurrent(alert);
			}
		} else if(label.equals("Exit")){
			destroyApp(true);
		}
	}     
}