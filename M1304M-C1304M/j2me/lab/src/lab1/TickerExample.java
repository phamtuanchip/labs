package lab1;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class TickerExample extends MIDlet implements CommandListener{
	private Display display; 
	private Ticker ticker; 
	private Command exit;
	private Form form;
		
	public TickerExample(){
		form = new Form("BSE Stock Exchange Ticker");
		display = Display.getDisplay(this);
		ticker = new Ticker ("10/17/2008 3:59:59 PM     ACC LTD 489.05 (-6.42%)     BHARTI ARTL 676.80 (-7.47%)       BHEL 1,194.80 (-9.00%)  ACC LTD 489.05 (-6.42%)    BHARTI ARTL 676.80 (-7.47%)       BHEL 1,194.80 (-9.00%)       DLF LTD* 291.30 (-10.34%)       GRASIM INDUSTRIES LTD. 1,293.40 (-5.71%)       HDFC BANK LT 1,024.05 (-5.82%)");
		exit = new Command("Exit", Command.SCREEN, 1);
		form.addCommand(exit);
		form.setCommandListener(this);
		form.setTicker(ticker);
	}

	public void startApp(){
		display.setCurrent(form);
	}

	public void pauseApp(){ }

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable display){
		String label = c.getLabel();
		if (label.equals("Exit")){
			destroyApp(false);
		}
	}
}