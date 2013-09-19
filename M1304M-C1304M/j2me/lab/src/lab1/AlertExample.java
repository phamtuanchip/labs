package lab1;

import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;

public class AlertExample extends MIDlet implements CommandListener{
	private Display display;
	public Form form;
	private Command ok,cancel,done;
	private Image img, imge, img2;
			
	public AlertExample() {
		form = new Form("Roseindia Alert");
		cancel = new Command("Cancel", Command.CANCEL, 2);
		ok = new Command("OK", Command.OK, 2);
		try{
			img = Image.createImage("/logo.png");
			imge = Image.createImage("/front_left1_bad.png");
			img2 = Image.createImage("/Congratulations-1.png");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}		
	}

 	public void startApp() {
		display = Display.getDisplay(this);
		try{form.append(img);}catch(Exception e){}
		form.addCommand(cancel);
		form.addCommand(ok);
		form.setCommandListener(this);
		display.setCurrent(form);
	}

	public void pauseApp() {}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	public void showMsg(){
		Alert success = new Alert("OK", "Alert Message Here!", img2, AlertType.INFO);
		success.setTimeout(5000);
		success.setImage(img2);
		display.setCurrent(success, form);		
	}

	public void tryAgain() {
		Alert error = new Alert("Cancel", "Alert Message Here!", imge, AlertType.INFO);
		error.setTimeout(5000);
		error.setImage(imge);
		display.setCurrent(error, form);
	}
	
	public void commandAction(Command c, Displayable d) {
		String label = c.getLabel();
		if(label.equals("Cancel")) {
			tryAgain();
		} else if(label.equals("OK")) {
			showMsg();
		} 
	}
}



