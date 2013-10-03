package exam;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
public class TimerMIDlet extends MIDlet implements CommandListener {
    // number of elements in gauge
    final private static int GAUGE_MAX = 10;
    private boolean timersRunning; // tracks state of timers
    private Display myDisplay;     // handle to the display
    private Gauge gaugeOne;        // "going up" gauge
    private Gauge gaugeTwo;        // "going down" gauge
    private Form myScreen;         // form on which to 
                                   // place gauges
    private Command cmdOK;         // OK command
    private Command cmdExit;       // EXIT command
    private Timer timer;
    private MyTimerTask timerTaskOne;
    private MyTimerTask timerTaskTwo;
    /**
     * Internal class that provides a TimerTask.
     */
    private class MyTimerTask extends TimerTask {
        private Gauge myGauge; // reference to gauge
        private boolean goUp;  // if true, go up
        private int num;       // number of times called
        /**
         * Public constructor: stores "direction" and a reference to
         * a gauge.
         */
        public MyTimerTask(Gauge g, boolean up) {
            myGauge = g;
            goUp = up;
        }
        /**
         * As the timer fires, this method is invoked. Set gauge
         * based on goUp
         */
        public void run() {
            num++;
            myGauge.setValue(goUp ?
                             GAUGE_MAX -(num % GAUGE_MAX) :
                             num % GAUGE_MAX);
        }
    }
    /**
     * Public constructor: gets handle to display,
     * creates form, gauges, and commands.
     */
    public TimerMIDlet() {
        myDisplay = Display.getDisplay(this);
        myScreen = new Form("TimerMIDlet");
        gaugeOne = new Gauge("Up Gauge",
                             false,
                             GAUGE_MAX,
                             0);
        myScreen.append(gaugeOne);
        gaugeTwo = new Gauge("Down Gauge",
                             false,
                             GAUGE_MAX,
                             GAUGE_MAX);
        myScreen.append(gaugeTwo);
        cmdOK = new Command("OK", Command.OK, 1);
        cmdExit = new Command("Exit", Command.EXIT, 1);
        myScreen.addCommand(cmdOK);
        myScreen.addCommand(cmdExit);
        myScreen.setCommandListener(this);
    }
    /**
     * Changes the state of timers to/from active to/from
     * not-active.
     */
    private void flipFlop() {
        if (timersRunning) {
            timerTaskOne.cancel();
            timerTaskTwo.cancel();
            timer.cancel();
            timersRunning = false;
        } else {
            timer = new Timer();
            timerTaskOne = new MyTimerTask(gaugeOne, false);
            timerTaskTwo = new MyTimerTask(gaugeTwo, true);
            timer.schedule(timerTaskOne, 0, 1000);
            timer.schedule(timerTaskTwo, 0, 1500);
            timersRunning = true;
        }
    }
    /**
     * Called by the system to start our MIDlet.
    * @exception MIDletStateChangeException
     */
    protected void startApp() throws MIDletStateChangeException {
        myDisplay.setCurrent(myScreen);
        flipFlop();
    }

    /**
     * Called by the system to pause our MIDlet.
     * No actions required by our MIDLet.
     */
    protected void pauseApp() {}
    /**
     * Called by the system to end our MIDlet.
     * No actions required by our MIDLet.
     */
    protected void destroyApp(boolean unconditional) {}
    /***
     * Respond to command selections. Process two commands:
     * 
     * OK: flip flop the timers to/from active
     * EXIT: exit this MIDlet
     * 
     */
    public void commandAction(Command c, Displayable d) {
        if (c == cmdOK) {
            flipFlop();
        } else if (c == cmdExit) {
            destroyApp(false);
            notifyDestroyed();
        }
    }
}