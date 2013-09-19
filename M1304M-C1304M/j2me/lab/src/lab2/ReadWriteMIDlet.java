package lab2;

import java.io.*;
import javax.microedition.rms.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class ReadWriteMIDlet extends MIDlet implements CommandListener{
	private Display display;
	private Alert alert;
	private Form form; 
	private Command exit, start; 
	private RecordStore recordStore;

	public ReadWriteMIDlet(){
		display = Display.getDisplay(this);
		start = new Command("Start", Command.SCREEN, 1);
		exit = new Command("Exit", Command.SCREEN, 1);
		form = new Form("Record");
		form.addCommand(start);
		form.addCommand(exit);		
		form.setCommandListener(this);
	}

	public void startApp(){
		display.setCurrent(form);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command command, Displayable displayable){
		if(command == exit){
			destroyApp(true);
		}else if(command == start){
			try{
				recordStore = RecordStore.openRecordStore("Sandeep Kumar Suman", true );
			
				String outputData = "First Record Completed...";
				byte[] byteOutputData = outputData.getBytes();
				recordStore.addRecord(byteOutputData, 0, byteOutputData.length);
			
				byte[] byteInputData = new byte[1]; 
				int length = 0;
				for (int x = 1; x <= recordStore.getNumRecords(); x++){
					if (recordStore.getRecordSize(x) > byteInputData.length){
						byteInputData = new byte[recordStore.getRecordSize(x)];
					}
					length = recordStore.getRecord(x, byteInputData, 0);
				}
				alert = new Alert("Reading", new String(byteInputData, 0, length), null, AlertType.WARNING); 
				alert.setTimeout(Alert.FOREVER); 
				display.setCurrent(alert); 
			
				recordStore.closeRecordStore();
			}catch (Exception e){}

			if(RecordStore.listRecordStores() != null){
				try{
					RecordStore.deleteRecordStore("Sandeep Kumar Suman");
				}catch (Exception e){}
			}      
		}
	}
}