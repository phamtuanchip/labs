package lab5;

/**
 * Digi clock sample with stop watch feature. Works in any MIDP 1.0 devide (in theory). Tested with N95 and N78 at least.
 * @ersion 0.2
 * @date 2008-08-20
 * @author winquist.net
 * License: free: use and modify as you like as long as you don't hold the author responsible for anything
 */

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;


class Color 
	{
    public static final int WHITE = 0x00ffffff;
    public static final int BLACK = 0x00000000;
	}

class Rect 
	{
    public int x1;
    public int y1;
    public int x2;
    public int y2;

    public Rect(int x1, int y1, int x2, int y2) 
    	{
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
	    }
	}

class DigitalClock 
	{
    static final int[] TABLE = { 63, 6, 91, 79, 102, 109, 124, 7, 127, 103 };

    private Rect rm1;
    private Rect rm2;
    private Rect rs1;
    private Rect rs2;
    private Rect rms1;
    private Rect rms2;
	private long iTime;
    
	public void setTime( long aTime )
		{
		iTime = aTime;
		}
		
    public DigitalClock(Rect r) 
    	{
	    ///@todo CHANGE THESE HARD CODED VALUES!
        this.rm1 = new Rect( 54, 10,  70, 50);
        this.rm2 = new Rect( 74, 10,  90, 50);
        this.rs1 = new Rect(104, 10, 120, 50);
        this.rs2 = new Rect(124, 10, 140, 50);
        this.rms1 = new Rect(154, 40, 170, 50);
        this.rms2 = new Rect(174, 40, 190, 50);
        //this.rms3 = new Rect(194, 40, 210, 50);
        
    	}

    public void draw(Graphics g, int aW, int aH) 
    	{

        g.setColor(Color.BLACK);
        g.fillRect(0,0, aW, aH );

        long n = iTime;//System.currentTimeMillis();
        int ms = (int)(n) % 1000;
        int hs = ms/10; // hundreds of second
        int s = (int)(n/1000) % 60;
        int m = (int)(n/(1000 * 60)) % 60;

        g.setColor(Color.WHITE);

        drawDigit(g, rm1, m/10);
        drawDigit(g, rm2, m%10);
        drawDigit(g, rs1, s/10);
        drawDigit(g, rs2, s%10);
        drawDigit(g, rms1, hs/10);
        drawDigit(g, rms2, hs%10);
        
        
        g.drawString( "Hit 5 to toggle, 0 to exit.", 10, aH - 60, Graphics.TOP | Graphics.LEFT );
        g.drawString( "winquist.net 2008", 10, aH - 30, Graphics.TOP | Graphics.LEFT );
    	}

    private long pow(long v1, long exp) 
    	{
        if (0 == exp) 
        	{
            return 1;
        	}
        long ret = v1;
        for (int i=0; i<exp-1; i++) 
        	{
            ret = ret * v1;
        	}
        return ret;
    	}

    // Draw single digit only
    private void drawDigit(Graphics g, Rect r, int v) 
    	{
        long tv = TABLE[v];
        int x1 = r.x1;
        int y1 = r.y1;
        int x2 = r.x2;
        int y2 = (r.y1 + r.y2)/2;
        int y3 = r.y2;


        for (int i = 0; i < 7; i++) 
        	{
            short c = (short)(tv & pow(2, i));

            switch (c) 
            	{
                case 0:
                    // do nothing
                    break;
                case 1:
                    // top
                    g.drawLine(x1 + 1, y1, x2 - 1, y1);
                    break;
                case 2:
                    // upper right
                    g.drawLine(x2, y1 + 1, x2, y2 - 1);
                    break;
                case 4:
                    // lower right
                    g.drawLine(x2, y2 + 1, x2, y3 - 1);
                    break;
                case 8:
                    // bottom
                    g.drawLine(x1 + 1, y3, x2 - 1, y3);
                    break;
                case 16:
                    // lower left
                    g.drawLine(x1, y2 + 1, x1, y3 - 1);
                    break;
                case 32:
                    // upper left
                    g.drawLine(x1, y1 + 1, x1, y2 - 1);
                    break;
                case 64:
                    // heart
                    g.drawLine(x1 + 1, y2, x2 - 1, y2);
                    break;
            	}
        	}
    	}
	}

class MainForm extends Canvas //implements KeyListener 
	{
    private Timer timer;
    private static final int EIdle = 0;
    private static final int EStarted = 1;
    private static final int EStopped = 2;
    private int iState;
    private Clock iParent; // for exit
    private long iStartTime;
    private Timer iTimer;
    private TimerTask iTask;
    private DigitalClock iDc;
    public MainForm ( Clock aParent ) 
    	{
	    iParent = aParent;
	    iState = EIdle;
        iTimer = new Timer();
        iTask =  new TimerTask() 
            	{
                public void run() 
                	{
                    repaint(); // makes a request for a delayed call to paint()...
                	}
            	};
        iTimer.scheduleAtFixedRate( iTask, 1000 , 50);
        iDc = new DigitalClock(new Rect( 10, 10, getWidth() - 10, getHeight()/3));
    }
    public void paint(Graphics g) 
    	{

	    if ( iState == EStarted )
	    	{
			long now = System.currentTimeMillis();
			iDc.setTime( now - iStartTime  );
	    	}
	    	
	    
        iDc.draw(g, getWidth(), getHeight());
    	}
    public void keyPressed(int keyCode)
    	{
	    p( "keyCode: " + keyCode );
	    if ( keyCode == 48 )
	    	{
		    iTask.cancel();
		    iTimer.cancel();
		    iParent.destroyApp( true );
	    	}
	    else
	    	{
		    switch ( iState )
		    	{
			    case EIdle:
			    	// Start counter
			    	iState = EStarted;
			    	iStartTime = System.currentTimeMillis();
			    	break;
			    case EStarted:
			    	// Show end time
			    	iState = EStopped;
			    	break;
			    case EStopped:
			    	// Reset
			    	iState = EIdle;
			    	iStartTime = 0;
			    	iDc.setTime( 0 );
			    	break;
		    	}
		    }
    	}
    private void p(String aStr)
    	{
	    System.out.println( aStr );
	    
   		}		
	}

// The necessary bloat
public class Clock extends MIDlet 
	{
    public void startApp() 
    	{
        Display.getDisplay(this).setCurrent(new MainForm( this ));
    	}
    public void pauseApp() {}
    public void destroyApp(boolean b) 
    	{
	    notifyDestroyed();
	    }
	}