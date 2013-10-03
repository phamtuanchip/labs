package lab7;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class CanvasForm extends MIDlet implements CommandListener{
	private Display display;
	private Form form;
	private Displayable current;
	private TextField one, two;
	private StringItem item;
	private long result;
	private Command quit, add ;
	private CanvasClass canvas;
	int first=0, second=0;

	public CanvasForm(){
		form = new Form("Calculator");
		one = new TextField(null, "10000002", 8, TextField.NUMERIC );
		two = new TextField(null, "10000003", 8, TextField.NUMERIC );
		item = new StringItem("Result", "");
		quit = new Command("Quit", Command.EXIT, 0);
		add = new Command("Add", Command.SCREEN, 0);
		form.append(one);
		form.append(two);
		form.append(item);
		form.addCommand(add);
		form.addCommand(quit);
		form.setCommandListener(this);
	}

	public void startApp(){
		display=Display.getDisplay(this);
		if (current!=null)
			display.setCurrent(current);
		else{
			display.setCurrent(form);
			current=form;
		}
	}

	public void pauseApp(){}

	public void destroyApp(boolean b){}
	  
	private void calculate(){
		try {
			first = Integer.parseInt( one.getString() );
			second = Integer.parseInt( two.getString() );
			result = first + second ;
			item.setText( result + "" );
		} catch (NumberFormatException e){
			item.setText("unknown");
			e.printStackTrace();
		}
	}

	public void commandAction(Command c, Displayable s){
		if (c == quit){
			notifyDestroyed();
			return;
		}
		calculate();
		if (canvas==null) canvas= new CanvasClass() ;
		current=canvas;
		display.setCurrent(canvas);
	}

	class CanvasClass extends Canvas implements CommandListener{
		CanvasClass(){
			this.addCommand( new Command("Back", Command.BACK, 0 ) );
			this.setCommandListener(this);
		}

		protected void paint(Graphics g){
			int w = getWidth();
			int h = getHeight();
			g.setColor(244,244,244);
			g.fillRect( 0,0,w,h );
			g.setGrayScale(12*14);
			h = Math.min( w, h );

			long mf = 100000000;
			int angle = (int)(( (first*(36000*mf/result)) +50*mf)/(100*mf)) ;
			int origin=180;
			g.fillArc(0,0,h,h, origin, (int)angle);
			g.setGrayScale(13*16);
			g.fillArc(0,0,h,h, (int)(origin+angle), (int)(360-angle) );
			
			g.setColor(123);
			g.drawString("A = "+first+"  ", h/2, h/2-10, Graphics.BASELINE|Graphics.RIGHT);
			g.drawString("  B = "+second, h/2, h/2-10, Graphics.BASELINE|Graphics.LEFT);
			g.drawString("Total = "+(first+second), h/2, h/2, Graphics.TOP|Graphics.HCENTER);

		}

		public void commandAction( Command c, Displayable d){
			current=form;
			display.setCurrent(form);
		}
	}
}