package lab8;
import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.media.*;
import javax.microedition.lcdui.*;

public class RingTones extends MIDlet implements ItemStateListener, CommandListener{
	private Display display;    
	private Form form;      
	private Command exit;     
	private ChoiceGroup choice;
	private Player player1, player2;

	public void startApp(){
		try {
			player1 = Manager.createPlayer(getClass().getResourceAsStream("/bark.wav"), "audio/x-wav");
			player2 = Manager.createPlayer(getClass().getResourceAsStream("/bong.wav"), "audio/x-wav");
		} catch(MediaException e) {
			e.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		display = Display.getDisplay(this);
		choice = new ChoiceGroup("List of RingTones", Choice.EXCLUSIVE);
		choice.append("Bark", null);    
		choice.append("Bong", null);    
		exit = new Command("Exit", Command.EXIT, 1);
		form = new Form("Playing song");
		form.append(choice);
		form.addCommand(exit);
		form.setCommandListener(this);   
		form.setItemStateListener(this);
		display.setCurrent(form);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable s){
		String label = c.getLabel();
		if(label.equals("Exit")){
			destroyApp(false);
		} 
	}

	public void itemStateChanged(Item item){
		switch(choice.getSelectedIndex()){
			case 0:
				try{
					player1.start();
					if(player2 != null) player2.stop();
				}catch(MediaException e) {
					e.printStackTrace();
				}
			break;

			case 1:
				try{
					player2.start();
					if(player1 != null) player1.stop();
				}catch(MediaException e) {
					e.printStackTrace();
				}
			break;
		}
	}
}