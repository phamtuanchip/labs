package lab7;
import java.io.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class ThreadProcessing extends MIDlet implements CommandListener{
	private Display display;
	private Ticker ticker;
	private Form form; 
	private Command exit, start;

	public ThreadProcessing(){
		form = new Form("Thread Processing");
		ticker = new Ticker ("10/17/2008 3:59:59 PM     ACC LTD 489.05 (-6.42%)     BHARTI ARTL 676.80 (-7.47%)       BHEL 1,194.80 (-9.00%)  ACC LTD 489.05 (-6.42%)    BHARTI ARTL 676.80 (-7.47%)       BHEL 1,194.80 (-9.00%)       DLF LTD* 291.30 (-10.34%)       GRASIM INDUSTRIES LTD. 1,293.40 (-5.71%)       HDFC BANK LT 1,024.05 (-5.82%)");
		exit = new Command("Exit", Command.EXIT, 1);
		start = new Command("Start", Command.SCREEN, 2);    
	}

	public void startApp(){
		display = Display.getDisplay(this);
		form.addCommand(exit);
		form.addCommand(start );    
		form.setCommandListener(this);
		form.setTicker(ticker);
		display.setCurrent(form);
	}

	public void pauseApp(){ }

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable displayable){
		String label = c.getLabel();
		if (label.equals("Exit")){
			destroyApp(false);
		}else if (label.equals("Start")){
			Process process = new Process(this);
			process.start(); 
		} 
	}
}

class Process implements Runnable{
	private ThreadProcessing MIDlet;

	public Process(ThreadProcessing MIDlet){ 
		this.MIDlet = MIDlet;
		System.out.println("Thread Process...");
	}

	public void run(){
		try{
			transmit();
			System.out.println("Thread Run...");
		}catch(Exception error){ 
			System.err.println(error.toString());
		}      
	}

	public void start(){
		Thread thread = new Thread(this);
		try{
			thread.start();
			System.out.println("Thread Start...");
		}catch(Exception error){}
	}

	private void transmit() throws IOException{} 

}