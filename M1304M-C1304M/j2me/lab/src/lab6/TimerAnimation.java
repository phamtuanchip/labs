package lab6;
import java.util.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class TimerAnimation extends MIDlet{
	private Display  display;    
	private AnimationCanvas canvas;
	private Timer timer;              
	private AnimateTimerTask timertask;  

	public TimerAnimation(){
		display = Display.getDisplay(this);
		timer = new Timer();
		canvas  = new AnimationCanvas(this);
		timertask = new AnimateTimerTask(canvas);
		timer.schedule(timertask, 0, 100);    
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

class AnimateTimerTask extends TimerTask{
	private AnimationCanvas canvas;

	public AnimateTimerTask(AnimationCanvas canvas){
		this.canvas = canvas;
	}

	public final void run(){
		if ((canvas.x_loc + canvas.radius +  canvas.x_dir > canvas.getWidth()) ||
			(canvas.x_loc - canvas.radius +  canvas.x_dir < 0)){                
			canvas.x_dir = -canvas.x_dir;
			canvas.changeColor();
			canvas.change_way++;
		}
		if ((canvas.y_loc + canvas.radius +  canvas.y_dir > canvas.getHeight()) ||
			(canvas.y_loc - canvas.radius + canvas.y_dir < 0)){
			canvas.y_dir = -canvas.y_dir;
			canvas.changeColor();
			canvas.change_way++;      
		}
		canvas.x_loc += canvas.x_dir;
		canvas.y_loc += canvas.y_dir;
		canvas.repaint();            
	}
}

class AnimationCanvas extends Canvas implements CommandListener{
	private TimerAnimation a_midlet;    
	private Command exit;     
	private int fire, right, left;  
	private boolean clean = false; 
	private Random random;          
	int x_loc, y_loc, radius, red, green, blue, x_dir, y_dir, start_x, start_y, change_way = 0;
	private static final int MAX_CHANGES = 50;

	public AnimationCanvas(TimerAnimation a_midlet){
		this.a_midlet = a_midlet;
		random = new java.util.Random();
		init();
		radius = 7;
		exit = new Command("Exit", Command.EXIT, 1);
		fire =  getKeyCode(FIRE);
		right = getKeyCode(RIGHT);
		left = getKeyCode(LEFT);
		addCommand(exit);
		setCommandListener(this);
	} 

	protected void paint(Graphics g){
		if (change_way > MAX_CHANGES){
			init();
		}
		if (clean){
			g.setColor(255, 255, 255);
			g.fillRect(0, 0, getWidth(), getHeight());
			clean = !clean;
		}
		g.setColor(red, green, blue);                    
		g.fillArc( x_loc, y_loc, radius, radius, 0, 360);
	}

	private void init(){
		x_loc = getWidth() / 2;
		y_loc = getHeight() / 2; 
		x_dir = (random.nextInt() % 10);
		if (x_dir == 0)  x_dir = 1;
		y_dir = (random.nextInt() % 10);
		if (y_dir == 0)  y_dir = 1;
		change_way = 0;    
		clean = true;      
		changeColor();      
	}

	protected void changeColor(){
		red = (random.nextInt() >>> 1) % 256;
		green = (random.nextInt() >>> 1) % 256;
		blue = (random.nextInt() >>> 1) % 256;
	}

	public void commandAction(Command c, Displayable d){
		String label = c.getLabel();
		if (label.equals("Exit"))
			a_midlet.exitMIDlet();
	}

	protected void keyPressed(int keyCode){
		if (keyCode == fire)
			init();
		else if (keyCode == left)
			radius = Math.max(1, --radius);
		else if (keyCode == right)
			radius = Math.min(getWidth() / 4, ++radius);
	}
}