package exam;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class CustomItemMIDletExample extends MIDlet implements CommandListener{
	private Form form;
	private Command exit;
	private Display display;
	private CustomItemExample customItem;

	public CustomItemMIDletExample(){
		form = new Form("CustomItemMIDletExample");
		exit = new Command("Exit", Command.EXIT, 0);
		form.append(new CustomItemExample("CustomItemExample"));
		form.addCommand(exit);
		form.setCommandListener(this);
	}

	public void startApp(){
		display = Display.getDisplay(this);
		display.setCurrent(form);
	}

	public void pauseApp(){}

	public void destroyApp(boolean unconditional){}

	public void commandAction(Command c, Displayable s){
		if(c.getCommandType() == Command.EXIT)
			notifyDestroyed();
	}
}

class CustomItemExample extends CustomItem{
	public CustomItemExample(String title){
		super(title);
	}

	public int getMinContentWidth(){
		return 100;
	}

	public int getMinContentHeight(){
		return 60;
	}

	public int getPrefContentWidth(int width){
		return getMinContentWidth();
	}

	public int getPrefContentHeight(int height){
		return getMinContentHeight();
	}

	public void paint(Graphics g, int w, int h){
		g.drawRect(0, 0, w - 1, h - 1);
		g.setColor(0, 0, 255);
		int offset = 0;
		for (int y = 4; y < h; y += 12){
			offset = (offset + 12) % 24;
			for (int x = 7; x < w; x += 24){
				g.fillTriangle(x + offset, y, x + offset - 3, y + 6, x + offset + 3, y + 6);
			}
		}
	}
}