package lab7;
import java.io.*;
import java.lang.*;
import javax.microedition.io.*;
import javax.microedition.rms.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;  

public class FontSize extends MIDlet {
	public static final boolean COLOR = false;
	public static final boolean DEBUG = false;
	private Display display = null;
	private FontCanvas fontCanvas = null;
	private boolean painting = false;

	public FontSize() {        
		display = Display.getDisplay(this);
        fontCanvas = new FontCanvas(this);
	}

	public void startApp() throws MIDletStateChangeException { 
		display.setCurrent(fontCanvas);
	}

	public void pauseApp() {}

	protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {}

	class FontCanvas extends Canvas {
		private FontSize parent = null;
		private int width = getWidth();
        private int height = getHeight();

		public FontCanvas(FontSize parent) {
			this.parent = parent;
		}

		public void paint(Graphics g) {
			g.setColor(255, 128, 0);  
            g.fillRect(0, 0, width, height);
			Font font1 = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);  
            Font font2 = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);  
            Font font3 = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);  
            int position = 0;
            if(COLOR){
                g.setColor(255, 255, 255);       
            }else{ 
                g.setColor(192, 192, 192);            
				g.fillRect(0, position, width, font1.getHeight());
			}
            if(COLOR){
                g.setColor(255, 255, 255);       
            }else{
                g.setColor(0, 0, 0);
			}
			g.setFont(font1);
            g.drawString("LARGE SIZE FONT", 0, position, Graphics.LEFT | Graphics.TOP);
			position = position + font1.getHeight() + 10;
            g.setFont(font2);
		    g.drawString("MEDIUM SIZE FONT", 0, position, Graphics.LEFT | Graphics.TOP);
            g.setColor(0, 0, 0);       
            position = position + font1.getHeight() + 10;            
            g.setFont(font3);
            g.drawString("SMALL SIZE FONT", 0, position, Graphics.LEFT | Graphics.TOP);
			position = position + font1.getHeight() + 10;
			g.drawLine(0, font3.getHeight() + position - 1, width, font3.getHeight() + position - 1);
			painting = false;
		}
	}
}