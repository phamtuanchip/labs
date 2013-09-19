package lab1;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class VolumeExample extends MIDlet implements ItemStateListener, CommandListener{
	private Form form;
	private Display display;
	private Command exit;
	private Gauge gauge;
	private StringItem sitem;

	public VolumeExample(){
		gauge = new Gauge("Volume", true, 5, 2);
		sitem = new StringItem(null, "[value]");
		itemStateChanged(gauge);
	}

	public void startApp(){
		Form form = new Form("GaugeExample");
		exit = new Command("Exit", Command.EXIT, 0);
		display = Display.getDisplay(this);
		form.append(gauge);
		form.append(sitem);
		form.addCommand(exit);
		form.setCommandListener(this);
		form.setItemStateListener(this);
		display.setCurrent(form);
	}

	public void itemStateChanged(Item item){
		if (item == gauge){
			sitem.setText("Volume Label = " + gauge.getValue());
		}
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable s){
		String label = c.getLabel();
		if (label.equals("EXIT")){
			destroyApp(false);
		}			
	}
}