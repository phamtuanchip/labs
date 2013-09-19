package lab2;
import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.rms.*;

public class RecordDataBase extends MIDlet {
	static final String DBNAME = "RecordDataBase";
	
    public void startApp(){
		RecordStore rs = null;
		try{
			RecordStore.deleteRecordStore(DBNAME);
		} catch(Exception e){}

		try{
			rs = RecordStore.openRecordStore(DBNAME, true);
			byte[] data1 = "First Record".getBytes();
            byte[] data2 = "Second Record".getBytes();
            byte[] data3 = " Third Record".getBytes();
			data3[0] = 0;
			data3[data3.length-1] = (byte) -1;
			rs.addRecord(data1, 0, data1.length);
            rs.addRecord(data2, 0, data2.length);
            rs.addRecord(data3, 0, data3.length);
			storeData(rs, System.out);
			rs.closeRecordStore();
		}catch(RecordStoreException e){
			System.out.println(e);
		}
		notifyDestroyed();
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){}
	
	public void storeData(RecordStore rs, PrintStream out){
		if(rs == null) return;
		StringBuffer hexLine = new StringBuffer();
        StringBuffer charLine = new StringBuffer();

        try{
			int lastID = rs.getNextRecordID();
			byte[] data = new byte[100];
			int size;

			for(int i = 1; i < lastID; ++i){
                try{
                    size = rs.getRecordSize(i);
                    if(size > data.length){
                        data = new byte[size * 2];
                    }
					out.println("-------------------------------------");
                    out.println("Size = " + size);
					out.println("-----------------------------------");
                    rs.getRecord(i, data, 0);
                    storeRecord(data, size, out, hexLine, charLine, 16);
                    out.println(" ");
                } catch(InvalidRecordIDException e){
                    continue;
                }
            }
        } catch( RecordStoreException e ){
            out.println(e);
        }
    }

    private void storeRecord(byte[] data, int size, PrintStream out, StringBuffer hexLine, StringBuffer charLine, int maxLen ){
		if(size == 0) return;
        hexLine.setLength(0);
        charLine.setLength(0);
        int count = 0;
        for(int i = 0; i < size; ++i){
            char b = (char) (data[i] & 0xFF);

            if(b < 0x10){
                hexLine.append('0');
            }
            hexLine.append(Integer.toHexString(b));
            hexLine.append(' ');

            if((b >= 32 && b <= 127) ||
                Character.isDigit(b) ||
                Character.isLowerCase(b) ||
                Character.isUpperCase(b)){
                charLine.append((char)b);
            } else {
                charLine.append(' ');
            }

            if(++count >= maxLen || i == size-1){
                while(count++ < maxLen){
					hexLine.append(" ");
				}
				hexLine.append(' ');
                hexLine.append(charLine.toString());
                out.println( hexLine.toString());
				hexLine.setLength(0);
                charLine.setLength(0);
                count = 0;
            }
        }
    }
}