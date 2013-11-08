package princess;
import java.io.IOException;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.game.TiledLayer;
public class Game extends Canvas implements Runnable{
	boolean run=false;
	LayerManager lm=new LayerManager();
	TiledLayer tl=null;
	Sprite nguoi1=null;
	Image img1=null;
	Image img2=null;
	Princess princess=null;
	public Game(){
		try {
			img2=Image.createImage("/Nen1.png");
			img2=ZoomPic.zoom(img2,img2.getWidth(),this.getHeight());
			img1=Image.createImage("/PrincessStanding.png");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		tl=new TiledLayer(1,1,img2,img2.getWidth(),img2.getHeight());
		tl.setCell(0, 0, 1);
		princess=new Princess(img2);
		lm.append(princess);
		lm.append(tl);

		
	}
	public void paint(Graphics arg0){
		princess.nextFrame();
		lm.paint(arg0, 0, 0);	
	}
	public void run() {
		while(run){
			this.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	public void stop(){
		run=false;
	}
	public void start(){
		Thread th=new Thread(this);
		th.start();
		run=true;
	}
	protected void keyPressed(int keyCode){
		switch(this.getGameAction(keyCode)){
		case UP:
			princess.jumpUp();
			break;
		case DOWN:
			princess.fallDown();
			break;
		case LEFT:
			princess.goBack();
			break;
		case RIGHT:
			princess.goAhead();
			break;
		case FIRE:
			princess.doAttack();
			
		}
	}
	protected void keyRepeated(int keyCode){
		switch(this.getGameAction(keyCode)){
		case LEFT:
			princess.runBack();
			break;
		case RIGHT:
			princess.runAhead();
			break;
		case FIRE:
			princess.doAttack();
			break;
		}
	}
	protected void keyReleased(int keyCode){
		princess.doStandding();
		
	}
	
}
