package lab7;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class SlideImage extends MIDlet{
	private Display display;
	private SimpleSlidingCanvas canvas;
	
	public void startApp(){
		canvas = new SimpleSlidingCanvas();
		display = Display.getDisplay(this);
		display.setCurrent(canvas);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}
}

class SimpleSlidingCanvas extends Canvas implements Runnable{
	Image image = null;

	public SimpleSlidingCanvas(){
		try{
			this.image = Image.createImage("/mobile.png");
			new Thread(this).start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run(){
		while(true){
			repaint();
			try{
				synchronized(this){
					wait(50L);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	protected void paint(Graphics g){
		g.setColor(0xffffff);
		g.fillRect(0, 0, getWidth(), getHeight());

		if(image != null){
			g.drawImage(image, getWidth() / 2, getHeight() / 2, Graphics.HCENTER | Graphics.VCENTER);
		} else {
			g.setColor(0x000000);
			g.drawString("No image available", getWidth() / 2, getHeight() / 2, Graphics.HCENTER | Graphics.BASELINE);
		}
	}
}