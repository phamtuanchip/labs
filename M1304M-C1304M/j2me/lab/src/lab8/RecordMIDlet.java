package lab8;
import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.media.*;
import javax.microedition.media.control.*;

public class RecordMIDlet extends MIDlet{
    public void startApp(){
        Display.getDisplay(this).setCurrent(new RecordForm());
    }
    
    public void pauseApp(){}
    
    public void destroyApp(boolean unconditional){}
}

