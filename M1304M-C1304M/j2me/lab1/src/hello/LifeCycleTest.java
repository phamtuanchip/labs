package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

// Testing the Life Cycle of a MIDlet
public class LifeCycleTest extends MIDlet implements CommandListener {

   private Form mainForm;           // for holding UI components
   private Display currentDisplay;  // the current display screen
   private Command cmdExit;         // exit command
   private StringItem msg;          //String UI Component

   // Constructor
   public LifeCycleTest() {
      System.out.println("Constructor is called");
      mainForm = new Form("Midlet Lifecycle Test");
      msg = new StringItem(null, "Constructing Midlet...");
      mainForm.append(msg);
      cmdExit = new Command("Exit", Command.EXIT, 0);
      mainForm.addCommand(cmdExit);
      mainForm.setCommandListener(this);
      currentDisplay = Display.getDisplay(this);
      currentDisplay.setCurrent(mainForm);
   }

   public void startApp() {
      System.out.println("startApp() is called");
      msg.setText("Midlet started...");
   }

   public void pauseApp() {
      System.out.println("pauseApp() is called");
      msg.setText("Midlet paused...");
   }

   public void destroyApp(boolean unconditional) {
      System.out.println("destroyApp() is called");
      msg.setText("Midlet destroyed...");
   }

   public void commandAction(Command cmd, Displayable displayable) {
      if (cmd == cmdExit) {
         //destroyApp(true);  // Uncomment and try this
         notifyDestroyed();
      }
   }
}