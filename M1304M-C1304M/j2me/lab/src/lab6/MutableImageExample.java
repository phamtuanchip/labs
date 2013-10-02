package lab6;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class MutableImageExample extends MIDlet{
	private Display  display;
	private ImageCanvas1 canvas;

	public MutableImageExample(){
		display = Display.getDisplay(this);
		canvas  = new ImageCanvas1(this);
	}

	protected void startApp(){
		display.setCurrent(canvas);
	}

	protected void pauseApp(){}

	protected void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void exitMIDlet(){
		destroyApp(true);
	}
}

class ImageCanvas1 extends Canvas implements CommandListener{
	private Command exit;
	private MutableImageExample midlet;
	private Image image;
	private String message = "Core J2ME Example";

	public ImageCanvas1(MutableImageExample midlet){
		this.midlet = midlet;
		exit = new Command("Exit", Command.EXIT, 1);
		addCommand(exit);
		setCommandListener(this);
		try{
			image = Image.createImage(100, 20);
			Graphics graphics = image.getGraphics();
			Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
			graphics.setFont(font);
			graphics.setColor(255, 0, 0);
			graphics.fillRoundRect(0,0, image.getWidth()-1, image.getHeight()-1, 20, 20); 
			graphics.setColor(0, 0, 255);           
			graphics.drawString(message,(image.getWidth()/2) - (font.stringWidth(message)/2), 0, Graphics.TOP | Graphics.LEFT);
		}catch (Exception e){
			System.err.println(e);
		}    
	}

	protected void paint(Graphics g){
		if(image != null){
			g.drawImage(image, getWidth()/2, getHeight()/2, Graphics.VCENTER | Graphics.HCENTER);
		}
	}

	public void commandAction(Command c, Displayable d){
		String label = c.getLabel();
		if(label.equals("Exit")){
			midlet.exitMIDlet();
		} 
	}
}