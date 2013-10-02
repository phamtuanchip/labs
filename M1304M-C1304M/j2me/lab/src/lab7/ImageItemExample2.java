package lab7;
import java.io.IOException;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class ImageItemExample2 extends MIDlet{
	private Display display;

	public void startApp(){
		display = Display.getDisplay(this);
		display.setCurrent(new ImageCanvas()); 
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}
}

class ImageCanvas extends Canvas{
	private Image image1, image2, image3;

	public ImageCanvas(){
		try{
			image1 = Image.createImage("/cartoon.png");
			image2 = Image.createImage("/girl-8-icon.png");
			image3 = Image.createImage("/girl-icon.png");
		}catch (IOException e){
			throw new RuntimeException("Unable to load Image: "+e);
		}
	}

	public void paint(Graphics g){
		g.setGrayScale(255);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(image1, 0, 0, Graphics.TOP | Graphics.LEFT);
		g.drawImage(image2, getWidth() / 2, getHeight() / 2, Graphics.HCENTER | Graphics.VCENTER);
		g.drawImage(image3, getWidth(), getHeight(), Graphics.BOTTOM | Graphics.RIGHT);
	}
}