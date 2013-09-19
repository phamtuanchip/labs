package lab1;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class ImageItemMIDlet extends MIDlet implements CommandListener{
	private Command exit;
	private ImageItem imageItem;
	private Image image;
	private Display display;
	private Form form;

	public ImageItemMIDlet(){
		try{
			image = Image.createImage("/sunrise.png");
		} catch (Exception e){ }
		imageItem = new ImageItem("This is the IMAGE_ITEM Application", image, ImageItem.LAYOUT_DEFAULT, "image");
	}

	public void startApp(){
		form = new Form("ImageItem Example");
		display = Display.getDisplay(this);
		exit = new Command("Exit", Command.EXIT, 1);
		form.append(imageItem);
		form.addCommand(exit);
		form.setCommandListener(this);
		display.setCurrent(form);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable d){
		String label = c.getLabel();
		if(label.equals("Exit")){
			destroyApp(true);
		}
	}
}