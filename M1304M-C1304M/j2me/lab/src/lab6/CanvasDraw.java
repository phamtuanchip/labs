package lab6;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.List;

public class CanvasDraw extends Canvas implements CommandListener{
	Command back;
	int listItemIndex;
	private Display display;
	private List list;
	public CanvasDraw(int index, Display d, List l){
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
