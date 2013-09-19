package lab2;

import javax.microedition.rms.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.io.*;

public class SearchMixedRecordDataTypeExample 
     extends MIDlet implements CommandListener
{
  private Display display;
  private Alert alert;
  private Form form; 
  private Command exit; 
  private Command start;
  private RecordStore recordstore = null;
  private RecordEnumeration recordEnumeration = null;
  private FilterMix filter = null;
  public SearchMixedRecordDataTypeExample ()
  {
    display = Display.getDisplay(this);
    exit = new Command("Exit", Command.SCREEN, 1);
    start = new Command("Start", Command.SCREEN, 1);
    form = new Form("Mixed RecordEnumeration");
    form.addCommand(exit);
    form.addCommand(start);
    form.setCommandListener(this);
  }
  public void startApp()
  {
    display.setCurrent(form);
  }
  public void pauseApp()
  {
  }
  public void destroyApp( boolean unconditional )
  {
  }
  public void commandAction(Command command, Displayable displayable)
  {
    if (command == exit)
    {
      destroyApp(true);
      notifyDestroyed();
    }
    else if (command == start)
    {
      try
      {
        recordstore = RecordStore.openRecordStore(
                  "myRecordStore", true );
      }
      catch (Exception error)
      {
        alert = new Alert("Error Creating", 
                 error.toString(), null, AlertType.WARNING); 
        alert.setTimeout(Alert.FOREVER); 
        display.setCurrent(alert);
      }
      try
      {
        byte[] outputRecord;
        String outputString[] = {"Adam", "Bob", "Mary"};
        int outputInteger[] = {15, 10, 5};
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream outputDataStream = 
                new DataOutputStream(outputStream);
        for (int x = 0; x < 3; x++)
        {
          outputDataStream.writeUTF(outputString[x]);
          outputDataStream.writeInt(outputInteger[x]); 
          outputDataStream.flush();               
          outputRecord = outputStream.toByteArray();
          recordstore.addRecord(outputRecord, 0, outputRecord.length); 
          outputStream.reset();  
        }
        outputStream.close();
        outputDataStream.close();
      }
      catch ( Exception error)
      {
       alert = new Alert("Error Writing", 
              error.toString(), null, AlertType.WARNING); 
       alert.setTimeout(Alert.FOREVER); 
       display.setCurrent(alert);
      }
      try
      {
       String inputString;
       byte[] byteInputData = new byte[300]; 
       ByteArrayInputStream inputStream = 
                   new ByteArrayInputStream(byteInputData);
       DataInputStream inputDataStream = 
                   new DataInputStream(inputStream);
       if (recordstore.getNumRecords() > 0)
       { 
         filter = new FilterMix("Mary");
          recordEnumeration = recordstore.enumerateRecords( 
                       filter, null, false);
          while (recordEnumeration.hasNextElement())
          {
            recordstore.getRecord(recordEnumeration.nextRecordId(), 
                            byteInputData, 0);       
            inputString = inputDataStream.readUTF() + 
                           " " + inputDataStream.readInt();
            alert = new Alert("Reading", inputString, 
                      null, AlertType.WARNING); 
            alert.setTimeout(Alert.FOREVER); 
           // display.setCurrent(alert);
            form.append(inputString);
          }
       }
       inputStream.close();
      }
      catch (Exception error)
      {
        alert = new Alert("Error Reading", 
                error.toString(), null, AlertType.WARNING); 
        alert.setTimeout(Alert.FOREVER); 
        display.setCurrent(alert);
      }
      try
      {
        recordstore.closeRecordStore();
      }
      catch (Exception error)
      {
        alert = new Alert("Error Closing", 
                 error.toString(), null, AlertType.WARNING); 
        alert.setTimeout(Alert.FOREVER); 
        display.setCurrent(alert);
      }
      if (RecordStore.listRecordStores() != null)
      {
        try
        {
          RecordStore.deleteRecordStore("myRecordStore");
          filter.filterClose();
          recordEnumeration.destroy();
        }
        catch (Exception error)
        {
         alert = new Alert("Error Removing", 
                 error.toString(), null, AlertType.WARNING); 
         alert.setTimeout(Alert.FOREVER); 
         display.setCurrent(alert);
        }
      }
    }
  }     
}
