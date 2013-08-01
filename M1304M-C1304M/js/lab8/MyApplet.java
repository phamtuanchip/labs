
package lab8;
import java.awt.*;
import java.applet.*;
public class MyApplet extends Applet implements Runnable
{
	int count; 
	Thread objTh;
	public void init()
	{
		objTh = new Thread(this);
     	objTh.start();
  	}
	
	public void run()
	{
	  for(count = 1; count <= 20; count++) 
	      {
	         try
	     {
	        repaint();
	        Thread.sleep(500);
	      }
	      catch (InterruptedException e)
	      {}
	  }
	   }
	   public void paint(Graphics g)
	   {
	    g.drawString("count = "+count, 30, 30); 
	   }
	}