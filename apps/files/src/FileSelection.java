import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
* @author test
*/
public class FileSelection extends MIDlet {
      private Display display;
      FileSelector fileSelector;
      public FileSelection() {
            display=Display.getDisplay(this);
      }
      public void startApp() {
            fileSelector=new FileSelector(this);
            display.setCurrent(fileSelector);
      }

      public void pauseApp() {
      }

      public void destroyApp(boolean unconditional) {
            notifyDestroyed();
      }
}