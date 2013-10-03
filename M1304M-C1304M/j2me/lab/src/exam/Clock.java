
package exam;

/**
 * Digi clock sample with stop watch feature. Works in any MIDP 1.0 devide (in theory). Tested with N95 and N78 at least.
 * @ersion 0.2
 * @date 2008-08-20
 * @author winquist.net
 * License: free: use and modify as you like as long as you don't hold the author responsible for anything
 */

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;


	

	// The necessary bloat
	public class Clock extends MIDlet 
	{
		public void startApp() 
		{
			Display.getDisplay(this).setCurrent(new MainForm( this ));
		}
		public void pauseApp() {}
		public void destroyApp(boolean b) 
		{
			notifyDestroyed();
		}
	}