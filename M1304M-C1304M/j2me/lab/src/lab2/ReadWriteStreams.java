package lab2;

import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.rms.*;

public class ReadWriteStreams extends MIDlet
{
  private RecordStore rs = null;    // Record store
  static final String REC_STORE = "db_1"; // Name of record store

  public ReadWriteStreams()
  {
    openRecStore();   // Create the record store
    
    writeTestData();  // Write a series of records
    readStream();     // Read back the records
        
    closeRecStore();  // Close record store
    deleteRecStore(); // Remove the record store
  }

  public void destroyApp( boolean unconditional )
  {
  }

  public void startApp()
  {
    // There is no user interface, go ahead and shutdown
    destroyApp(false);
    notifyDestroyed();
  }

  public void pauseApp()
  {
  }

  public void openRecStore()
  {
    try
    {
      // The second parameter indicates that the record store
      // should be created if it does not exist
      rs = RecordStore.openRecordStore(REC_STORE, true );
    }
    catch (Exception e)
    {
      db(e.toString());
    }
  }    
  
  public void closeRecStore()
  {
    try
    {
      rs.closeRecordStore();
    }
    catch (Exception e)
    {
      db(e.toString());
    }
  }

  public void deleteRecStore()
  {
    if (RecordStore.listRecordStores() != null)
    {
      try
      {
        RecordStore.deleteRecordStore(REC_STORE);
      }
      catch (Exception e)
      {
        db(e.toString());
      }
    }      
  }

  /*--------------------------------------------------
  * Create three arrays to write to record store
  *-------------------------------------------------*/
  public void writeTestData()
  {
    String[] strings = {"Text 1", "Text 2"};
    boolean[] booleans = {false, true};    
    int[] integers = {1 , 2};
    
    writeStream(strings, booleans, integers);
  }

  /*--------------------------------------------------
  * Write to record store using streams.
  *-------------------------------------------------*/  
  public void writeStream(String[] sData, boolean[] bData, int[] iData)
  {
    try
    {
       // Write data into an internal byte array
      ByteArrayOutputStream strmBytes = new ByteArrayOutputStream();
      
      // Write Java data types into the above byte array
      DataOutputStream strmDataType = new DataOutputStream(strmBytes);
      
      byte[] record;

      for (int i = 0; i < sData.length; i++)
      {
        // Write Java data types      
        strmDataType.writeUTF(sData[i]);
        strmDataType.writeBoolean(bData[i]);
        strmDataType.writeInt(iData[i]);                
        
        // Clear any buffered data
        strmDataType.flush();

        // Get stream data into byte array and write record
        record = strmBytes.toByteArray();
        rs.addRecord(record, 0, record.length);      

        // Toss any data in the internal array so writes 
        // starts at beginning (of the internal array)
        strmBytes.reset();
      }
      
      strmBytes.close();
      strmDataType.close();
     
    }
    catch (Exception e)
    {
      db(e.toString());
    }
  }

  /*--------------------------------------------------
  * Read from the record store using streams
  *-------------------------------------------------*/
  public void readStream()
  {
    try
    {
      // Careful: Make sure this is big enough!
      // Better yet, test and reallocate if necessary      
      byte[] recData = new byte[50];

      // Read from the specified byte array
      ByteArrayInputStream strmBytes = new ByteArrayInputStream(recData);

      // Read Java data types from the above byte array
      DataInputStream strmDataType = new DataInputStream(strmBytes);

      for (int i = 1; i <= rs.getNumRecords(); i++)
      {
        // Get data into the byte array
        rs.getRecord(i, recData, 0);

        // Read back the data types      
        System.out.println("Record #" + i);        
        System.out.println("UTF: " + strmDataType.readUTF());
        System.out.println("Boolean: " + strmDataType.readBoolean());
        System.out.println("Int: " + strmDataType.readInt());            
        System.out.println("--------------------");        

        // Reset so read starts at beginning of array 
        strmBytes.reset();
      }
      
      strmBytes.close();
      strmDataType.close();
      
    }      
    catch (Exception e)
    {
      db(e.toString());
    }
  }
  
  /*--------------------------------------------------
  * Simple message to console for debug/errors
  * When used with Exceptions we should handle the 
  * error in a more appropriate manner.
  *-------------------------------------------------*/
  private void db(String str)
  {
    System.err.println("Msg: " + str);
  }
}