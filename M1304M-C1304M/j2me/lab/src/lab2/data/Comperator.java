package lab2.data;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

import javax.microedition.rms.RecordComparator;

public class Comperator implements RecordComparator{
	private byte[] recData = new byte[10];
	public int compare(byte[] rec1, byte[] rec2) {
		int x1, x2; try {// If either record is larger than our buffer, reallocate  
			int maxsize = Math.max(rec1.length, rec2.length); 
			if (maxsize > recData.length) recData = new byte[maxsize]; // Read record #1  we must read the String and boolean to get to the 
			InputStream strmBytes = new ByteArrayInputStream(rec1); 
			DataInputStream strmDataType = new DataInputStream(strmBytes); 
			strmDataType.readUTF(); 
			strmDataType.readBoolean(); 
			x1 = strmDataType.readInt(); // Here's our data // Read record #2 
			strmBytes = new ByteArrayInputStream(rec2); 
			strmDataType = new DataInputStream(strmBytes); 
			strmDataType.readUTF();  
			strmDataType.readBoolean(); 
			x2 = strmDataType.readInt(); // Here's our data 
			if (x1 == x2) // Compare record #1 and #2 
				return RecordComparator.EQUIVALENT; 
			else if (x1 < x2) return RecordComparator.PRECEDES; 
			else return RecordComparator.FOLLOWS; 
		} catch (Exception e) { 
			return RecordComparator.EQUIVALENT; 
		}

	}

}
