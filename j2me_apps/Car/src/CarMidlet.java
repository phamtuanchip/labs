/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author ChuotFx
 */
public class CarMidlet extends MIDlet {

    private Display ds;
    private CarCanvas cv;
    public CarMidlet(){
        ds=Display.getDisplay(this);
        cv=new CarCanvas(false,this);
    }

    public void startApp() {
        ds.setCurrent(cv);
        cv.start();
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
    public void exitMidlet(){
        destroyApp(true);
        notifyDestroyed();
    }
}
