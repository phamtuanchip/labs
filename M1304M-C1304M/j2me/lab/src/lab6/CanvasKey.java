package lab6;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class CanvasKey extends Canvas{

	private Font font;
	private String message = "[PRESS KEY]";

	public CanvasKey(){
		font = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
	}

	public void paint(Graphics g){
		int width = getWidth();
		int height = getHeight();

		g.setColor(255, 0, 0);
		g.fillRect(0, 0, width - 1, height - 1);
		g.setColor(0, 0, 255);
		g.drawRect(0, 0, width - 1, height - 1);
		g.setFont(font);

		int x = width / 2;
		int y = height / 2;

		g.drawString(message, x, y, Graphics.BASELINE | Graphics.HCENTER);
	}

	protected void keyPressed(int keyCode){
		int gameAction = getGameAction(keyCode);

		switch(gameAction){
			case UP:
				message = "[UP]";
			break;
			case DOWN:
				message = "[DOWN]";
			break;
			case LEFT:
				message = "[LEFT]";
			break;
			case RIGHT:
				message = "[RIGHT]";
			break;
			case FIRE:
				message = "[FIRE]";
			break;
			case GAME_A:
				message = "[LEFT_UP]";
			break;
			case GAME_B:
				message = "[RIGHT_UP]";
			break;
			case GAME_C:
				message = "[LEFT_DOWN]";
			break;
			case GAME_D:
				message = "[RIGHT_DOWN]";
			break;
			default:
				message = "";
			break;
		}
		repaint();
	}
}