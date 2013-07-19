package lab4;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Event;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// Notes Text editor
// Contains 
// 1. TextArea
// 2. Open/Save Dialogs
// 3. Menus and MenuBar
// All of which are private to this class
public class Notes extends Frame
{
  private	TextArea t;

  private FileDialog openDialog;
  private FileDialog saveDialog;

  private MenuBar mbar;

  // The constructor where we initialize data
  public Notes()
  {
    super("Notes Editor");

    // For non-Win95 systems, try "Helvetica" or "TimesRoman"
    // for the font name
    Font f = new Font("TimesRoman", Font.PLAIN, 12);
    setFont(f);
    setBackground(Color.gray);

    // Move the code out of the contructor into other methods 
    InitializeMenus();
    InitializeTextArea();
    InitializeToolBars();

    openDialog = new FileDialog(this, "Open File...", FileDialog.LOAD);
    saveDialog = new FileDialog(this, "Save File...", FileDialog.SAVE);

    setMenuBar(mbar);
    resize(300,200);
    pack();
    show();
  }

  // Reads from a file, throws an IOException if an error occured
  public void read(String s) throws IOException
  {
    String line;
    int i =0;
    FileInputStream in = null;
    BufferedInputStream bis = null;
    DataInputStream dataIn = null;

    try
    {
      in = new FileInputStream(s);
      bis = new BufferedInputStream(in);
      dataIn = new DataInputStream(bis);
    }
    catch(Throwable e)
    {
      System.out.println("Error in opening file");
      return;
    }

    try
    {
      while ((line = dataIn.readLine()) != null)
      {
        t.insertText(line, i);
        i++;
      }
      in.close();
    }
    catch(IOException e) 
    {
      System.out.println("Error in reading file");
      throw e;
    }
  }

  // Writes to a file
  public void write(String s) throws IOException
  {
    FileOutputStream out = null;
    BufferedOutputStream bos = null;
    DataOutputStream dataOut = null;

    try
    {
      out = new FileOutputStream(s);
      bos = new BufferedOutputStream(out);
      dataOut = new DataOutputStream(bos);

    }
    catch (Throwable e)
    {
      System.out.println("Error in opening file");
      return;
    }

    try
    {
      dataOut.writeChars(t.getText());
      out.close();
    }
    catch(IOException e) 
    {
      System.out.println("Error in writing to file");
      throw e; 
    }
  }

  // Creates the menus and attaches it to the MenuBar
  private void InitializeMenus()
  {
    mbar = new MenuBar();
    Menu m = new Menu("File");
    m.add(new MenuItem("New"));
    m.add(new MenuItem("Open"));
    m.add(new MenuItem("Save"));
    m.add(new MenuItem("Save As"));
    m.addSeparator();
    m.add(new MenuItem("Quit"));
    mbar.add(m);

    m = new Menu("Help");
    m.add(new MenuItem("Help!!!!"));
    m.addSeparator();
    m.add(new MenuItem("About..."));
    mbar.add(m);
  }

  // Creates the TextArea
  private void InitializeTextArea()
  {
    t = new TextArea("This is a TextArea", 24, 80);
    t.setEditable(true);
    add("Center", t);
  }

  // Creates the Toolbars
  private void InitializeToolBars()
  {
  }

  // Handle events that have occurred
  public boolean handleEvent(Event evt)
  {
    switch(evt.id) {

    case Event.WINDOW_DESTROY:
    {
      System.out.println("Exiting...");
      System.exit(0);
      return true;
    }
    // This can be handled
    case Event.ACTION_EVENT:
    {
      String label = evt.arg.toString();

      if(evt.target instanceof MenuItem)
      {
        // or "Open".equals(label)
        // or evt.arg.equals("Open")
        if (label.equals("Open"))
        {
          String filename;
          openDialog.show();
          // getFile() throws an exception
          // So wee need to catch it if there is an error
          try
          {
            filename = openDialog.getFile();
            read(filename);
          }
          catch(IOException e)
          {
            System.out.println("File not found");
            return true;
          }
          return true;
        }
        else if (label.equals("Save As"))
        {
          String filename;
          try
          {
            filename = saveDialog.getFile();
            write(filename);
          }
          catch(IOException e)
          {
            System.out.println("File not found");
            return true;
          }
          saveDialog.show();
          return true;
        }
        else if(label.equals("Quit"))
        {
          System.exit(0);
        }
        else if(label.equals("About..."))
        {
          AboutDialog ab = new AboutDialog(this);
          ab.show();
          return true;
        }
      }
    }
    default:
      return false;
    }
  }

  // Where execution begins in a stand-alone executable
  public static void main(String args[])
  {
    new Notes();
  }
}

// Sub-class(i.e child/descendant) of Dialog, put is NOT public
// because only one public class can reside in a file and that
// class is the name of the file
class AboutDialog extends Dialog
{
  // Class is specific to "Notes", because it takes a parameter to
  // to it. Can be made generic, by asking for a Frame instead
  public AboutDialog(Notes parent)
  {
    super(parent, "About Dialog", true);
    Panel p = new Panel();
    p.add(new Button("OK"));
    add("South", p);
    resize(300,200);
  }
  // Same as the above. Except lets try something different
  /*
	public boolean handleEvent(Event evt)
	{
		switch(evt.id)
		{
			case Event.ACTION_EVENT:
			{
				if("OK".equals(evt.arg))
				{
					dispose();
					return true;
				}
			}
			default:
				return false;
		}
	}
   */
  // No different from the above handler
  public boolean action(Event evt, Object arg)
  {
    if("Ok".equals(arg))
    {
      dispose();
      return true;
    }
    return false;
  }
}
