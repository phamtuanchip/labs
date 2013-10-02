package lab7;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class PacerExample extends MIDlet{
	public void startApp(){ 
		Displayable d = new PacerCanvas();
		d.addCommand(new Command("Exit", Command.EXIT, 0));
		d.setCommandListener(new CommandListener() { 
			public void commandAction(Command c, Displayable s) { 
				notifyDestroyed();
			} 
		});
		Display.getDisplay(this).setCurrent(d);
	} 

	public void pauseApp(){} 

	public void destroyApp(boolean unconditional){} 
}

class PacerCanvas extends Canvas{ 
	public void paint(Graphics g){ 
		int w = getWidth();
		int h = getHeight();
		g.setColor(255, 162, 117);
		g.fillRect(0, 0, w, h);
		g.setColor(0, 0, 255);

		for(int x = 0; x < w; x += 20){
			g.drawLine(0, w - x, x, 0);
		}
		int z = 100;
		g.drawRect(z, z, 30, 30);
		z += 20;
		g.fillRoundRect(z, z, 30, 30, 10, 10);
		z += 20;
		g.drawArc(z, z, 30, 30, 0, 360);
	} 
} 