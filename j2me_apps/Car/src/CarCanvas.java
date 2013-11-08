/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ChuotFx
 */
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class CarCanvas extends GameCanvas implements Runnable,CommandListener {
    private CarMidlet midlet;
    private Command exit;
    boolean run;
    private Image im;
    
    private int h=getHeight()/2;
    private int w=getWidth()/2;
  //  private final boolean run;

    
    public CarCanvas(boolean suppressKeyEvents,CarMidlet midlet) {
        super(suppressKeyEvents);
        run = true;
        this.midlet = midlet;


         try{
                 im=Image.createImage("/car.png");
            }catch(java.io.IOException e){}
  
        
        exit=new Command("exit",Command.EXIT,1);
        addCommand(exit);
        
        updateGameScreen(getGraphics());

        setCommandListener(this);
  } 
    public void run() {
       while (run){
      // update game elements, positions, collisions, etc..
      updateGameState();
      // render screen
      updateGameScreen(getGraphics());
      // redraws screen
      this.flushGraphics();
      // controls at which rate the updates are done
      try {
        Thread.sleep(20);
      } catch (InterruptedException e) {  
        e.printStackTrace();
      }
    }
    }
   
   // start Thread
  public void start() {
    run = true;
    Thread t = new Thread(this);
    t.start();
  }
  
  // stop Thread
  public void stop() {
    run = false;
  }

    public void commandAction(Command c, Displayable d) {
        if(c== exit){
            midlet.exitMidlet();
        }
    }

    private void updateGameState() {
        int keyStates = getKeyStates();
        if ((keyStates & LEFT_PRESSED) != 0){
            w-=4;
        }
        else if((keyStates & RIGHT_PRESSED) != 0){
            w+=4;
        }
        else  if ((keyStates & UP_PRESSED) != 0){
            h-=4;
        }
        else if((keyStates & DOWN_PRESSED) != 0){
            h+=4;
        }
    }

    private void updateGameScreen(Graphics g) {
       g.setColor(0x445566);
       g.fillRect(0, 0,getWidth(),getHeight());
      // g.setColor(0xffffff);
      // g.fillArc(w, h,10,10, 0, 360);
       g.drawImage(im,w,h,Graphics.HCENTER | Graphics.VCENTER);
    }

   
}
