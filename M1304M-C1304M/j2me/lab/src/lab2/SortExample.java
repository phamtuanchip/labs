package lab2;

import javax.microedition.rms.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.io.*;

public class SortExample extends MIDlet implements CommandListener
{
  private Display display;
  private Alert alert;
  private Form form; 
  private Command exit; 
  private Command start;
  private RecordStore recordstore = null;
  private RecordEnumeration recordEnumeration = null;
  private StringComparator comparator = null;
  public SortExample ()
  {
    display = Display.getDisplay(this);
    exit = new Command("Exit", Command.SCREEN, 1);
    start = new Command("Start", Command.SCREEN, 1);
    form = new Form("Mixed RecordEnumeration", null);
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
        String outputData[] = {"Mary", "Bob", "Adam"};
        for (int x = 0; x < 3; x++)
        {
          byte[] byteOutputData = outputData[x].getBytes();
          recordstore.addRecord(byteOutputData, 0, 
                   byteOutputData.length);
        }
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
        StringBuffer buffer = new StringBuffer();
        StringComparator comparator = new StringComparator();
        recordEnumeration = recordstore.enumerateRecords(
                          null, comparator, false);
        while (recordEnumeration.hasNextElement())
        {
          buffer.append(new String(recordEnumeration.nextRecord()));
          buffer.append("\n");
        }
        alert = new Alert("Reading", buffer.toString() , 
                 null, AlertType.WARNING); 
        alert.setTimeout(Alert.FOREVER); 
       // display.setCurrent(alert);
        form.append(buffer.toString());
        
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
