package lab2;
import java.io.*;
import javax.microedition.rms.*;
import javax.microedition.midlet.*;

public class RMSMIDlet extends MIDlet{
	private RecordStore record;
	static final String REC_STORE = "SORT";

	public void startApp(){		
		openRecord();		
		writeRecord("RAKESH");
		writeRecord("SANDEEP");
		writeRecord("NEELAM");    
		writeRecord("ANUSMITA");
		writeRecord("VIJAY");
		readRecord();
		closeRecord();
		deleteRecord();		
	}

	public void pauseApp(){}
	
	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void openRecord(){
		try{			
			record = RecordStore.openRecordStore(REC_STORE, true );
		}catch (Exception e){
			db(e.toString());
		}
	}    

	public void closeRecord(){
		try{
			record.closeRecordStore();
		}catch (Exception e){
			db(e.toString());
		}
	}

	public void deleteRecord(){
		if (RecordStore.listRecordStores() != null){
			try{
				RecordStore.deleteRecordStore(REC_STORE);
			}catch (Exception e){
				db(e.toString());
			}
		}
	}

	public void writeRecord(String str){
		byte[] rec = str.getBytes();
		try{
			record.addRecord(rec, 0, rec.length);
		}catch (Exception e){
			db(e.toString());
		}
	}

	public void readRecord(){
		try{
			if (record.getNumRecords() > 0){
				Comparator comp = new Comparator();
				RecordEnumeration re = record.enumerateRecords(null, comp, false);

				while (re.hasNextElement()){					
					String str = new String(re.nextRecord());
					System.out.println("------------------------------");
					System.out.println(str);
					System.out.println("------------------------------");                        
				}
			}
		} catch (Exception e){
			db(e.toString());
		}
	}

	private void db(String error){
		System.err.println("Exception: " + error);
	}
}

class Comparator implements RecordComparator{
	public int compare(byte[] rec1, byte[] rec2){
		String str1 = new String(rec1);
		String str2 = new String(rec2);
		int result = str1.compareTo(str2);
		if (result == 0){
			return RecordComparator.EQUIVALENT;
		} else if (result < 0){
			return RecordComparator.PRECEDES;
		} else {
			return RecordComparator.FOLLOWS;
		}
	}
}