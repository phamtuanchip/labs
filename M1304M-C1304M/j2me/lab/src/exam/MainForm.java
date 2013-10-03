package exam;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class MainForm extends Canvas //implements KeyListener 
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
