package lab6;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class CanvasDrawExample extends MIDlet implements CommandListener{
	private Display display;
	private List list;
	private Command ok, exit;
	private CanvasDraw canvasDraw;
	int listItemIndex;

	public CanvasDrawExample(){
		canvasDraw = new CanvasDraw();
		display = Display.getDisplay(this);
		list = new List("CanvasDrawList", List.IMPLICIT);
		ok = new Command("Draw", Command.OK, 2);
		exit = new Command("Exit", Command.EXIT, 2);
		list.append("Draw Lines", null);
		list.append("Draw Rectangle", null);
		list.append("Draw Rounded Rectangle", null);
		list.append("Draw Arc", null);
		list.append("Draw Triangle", null);
		list.addCommand(ok);
		list.addCommand(exit);
		list.setCommandListener(this);
	}
	
	public void startApp(){
		display.setCurrent(list);
	}

	public void pauseApp(){}
	
	public void destroyApp(boolean unconditional){
		notifyDestroyed ();
	}

	public void commandAction(Command c, Displayable d){
		listItemIndex = list.getSelectedIndex();
		if(c == ok){
			display.setCurrent(canvasDraw);
		}else if(c == exit){
			destroyApp(true);
		}
	}

	class CanvasDraw extends Canvas implements CommandListener{
		Command back;

		public CanvasDraw(){
			back = new Command("Back", Command.BACK, 1);
			addCommand(back);
			setCommandListener(this);
		}

		public void paint(Graphics g){
			g.setColor(0, 0, 255);
			g.fillRect(0,0, getWidth (), getHeight ());
			g.setColor(255, 0, 0);

			if(listItemIndex == 0){
				g.drawString("Draw line", getWidth () / 2, 5, Graphics.HCENTER | Graphics.TOP);
				g.drawLine(getWidth()/4, 100, 3 * getWidth()/4, 100);
			}

			if(listItemIndex == 1){
				g.drawString("Draw Rectangle", getWidth()/2, 5, Graphics.HCENTER | Graphics.TOP);
				g.fillRect(getWidth () / 4, 100, getWidth() / 2, 40);
			}

			if(listItemIndex==2){
				g.drawString("Draw Rounded Rectangle", getWidth () / 2, 5, Graphics.HCENTER | Graphics.TOP);
				g.fillRoundRect(getWidth () / 4, 100, getWidth () / 2, 40, 30, 40);
			}

			if(listItemIndex == 3){
				g.drawString("Draw Arc", getWidth () / 2, 5, Graphics.HCENTER | Graphics.TOP);				
				g.fillArc(getWidth () / 4, 100, 90, 90, 180, 270);
			}

			if(listItemIndex == 4){
				g.drawString("Draw Traingle", getWidth () / 2, 5, Graphics.HCENTER | Graphics.TOP);
				g.fillTriangle(getWidth() / 4, 100, 90, 90, 100, 180);
			}
		}

		public void commandAction (Command c, Displayable d){
			if(c == back){
				display.setCurrent(list);
			}
		}
	}
}