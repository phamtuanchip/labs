package lab8;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
public class AudioRecordMIDlet extends MIDlet {
	private Display display;

	public void startApp() {
		display = Display.getDisplay(this);
		display.setCurrent(new VoiceRecordForm());
	}

	public void pauseApp() {
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}
}















