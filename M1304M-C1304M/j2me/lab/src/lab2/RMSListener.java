package lab2;
import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.rms.*;

public class RMSListener extends MIDlet{
	private RecordStore record = null;
	static final String REC_STORE = "LISTENER";

	public void startApp(){
		openRecord();
		record.addRecordListener(new RMSRecordListener());
		writeRecord("Core J2ME Technology");
		updateRecord("J2ME Wireless Toolkit");
		deleteRecord();
		closeRecord();
		deleteRecStore();
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){}	

	public void openRecord(){
		try{
			record = RecordStore.openRecordStore(REC_STORE, true);
		}catch (Exception e){}
	}
	
	public void writeRecord(String str){
		byte[] rec = str.getBytes();
		try{
			record.addRecord(rec, 0, rec.length);
		}catch (Exception e){}
	}

	public void updateRecord(String str){
		try{
			record.setRecord(1, str.getBytes(), 0, str.length());
		}catch (Exception e){}
	}

	public void deleteRecord(){
		try{
			record.deleteRecord(1);
		}catch (Exception e){}
	}

	public void closeRecord(){
		try{
			record.closeRecordStore();
		}catch (Exception e){}
	}

	public void deleteRecStore(){
		if (RecordStore.listRecordStores() != null){
			try{
				RecordStore.deleteRecordStore(REC_STORE);
			}catch (Exception e){}
		}      
	}
}

class RMSRecordListener implements RecordListener{
	public void recordAdded(RecordStore rs, int id) { 
		try{
			System.out.println("Record with id: " + id + " successfully added to RecordStore: " + rs.getName()); 
		}catch (Exception e){
			System.err.println(e);
		} 
	} 

	public void recordDeleted(RecordStore rs, int id) {
		try{
			System.out.println("Record with id: " + id +  " successfully deleted from RecordStore: " + rs.getName()); 
		} catch (Exception e){
			System.err.println(e);
		}
	}

	public void recordChanged(RecordStore rs, int id) {
		try{
			System.out.println("Record with id: " + id + " successfully update in RecordStore: " + rs.getName()); 
		} catch (Exception e){
			System.err.println(e);
		}
	} 
}