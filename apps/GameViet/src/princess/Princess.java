package princess;
import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
public class Princess extends Sprite{
	public final static int STATUS_STANDDING=1;
	public final static int STATUS_ATTACKING=2;
	public final static int STATUS_RUNNING=3;
	public final static int STATUS_STOP_RUNNING=4;
	public final static int STATUS_WALKING=5;
	public final static int STATUS_INCANTATION=6;
	private Image[]imgArr={null,null,null,null,null,null,null};
	private int[] numFrame={0,4,3,4,1,6,7};
	private boolean isGoBack=false;
	private int speedGo=2;
	private int speedJump=4;
	private int speedRun=4;
	private int speedFall=3;
	private int currentStatus=0;
	public Princess(Image image) {
		super(image);
		try {
			imgArr[STATUS_ATTACKING]=Image.createImage("/PrincessAttacking.png");
			imgArr[STATUS_INCANTATION]=Image.createImage("/PrincessIncantation.png");
			imgArr[STATUS_RUNNING]=Image.createImage("/PrincessRunning.png");
			imgArr[STATUS_STANDDING]=Image.createImage("/PrincessStanding.png");
			imgArr[STATUS_STOP_RUNNING]=Image.createImage("/PrincessStopRunning.png");
			imgArr[STATUS_WALKING]=Image.createImage("/PrincessWalking.png");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("Loi trong qua trinh load anh");
		}
		this.setStatus(STATUS_STANDDING);
		this.setPosition(this.getWidth(), this.getHeight());
		this.setRefPixelPosition(this.getWidth()/2, this.getHeight()/2);
		this.setVisible(true);
	}
	private void setStatus(int status){
		if(currentStatus==status)return;
		this.setImage(imgArr[status], imgArr[status].getWidth()/numFrame[status], imgArr[status].getHeight());
		int[] arrStatus=new int[numFrame[status]];
		for(int i=0;i<numFrame[status];i++)
			arrStatus[i]=i;
		this.setFrameSequence(arrStatus);
		
		currentStatus=(status);
	}
	public void goAhead(){
		if(isGoBack){
			this.setTransform(TRANS_NONE);
			isGoBack=false;
		}
		setStatus(STATUS_WALKING);
		this.move(this.speedGo, 0);
	}
	public void goBack(){
		if(!isGoBack){
			this.setTransform(TRANS_MIRROR);
			isGoBack=true;
		}
		setStatus(STATUS_WALKING);

		this.move(-this.speedGo, 0);
	}
	public void jumpUp(){
		setStatus(STATUS_WALKING);
		this.move(0, -this.speedJump);
	}
	public void fallDown(){
		setStatus(STATUS_WALKING);
		this.move(0, this.speedFall);
	}
	public void runBack(){
		setStatus(STATUS_RUNNING);
		if(!isGoBack){
			this.setTransform(TRANS_MIRROR);
		}
		isGoBack=true;
		this.move(-this.speedRun, 0);
	}
	public void runAhead(){
		setStatus(STATUS_RUNNING);
		this.setTransform(TRANS_NONE);
		this.move(this.speedRun, 0);
		isGoBack=false;
	}
	public void doAttack(){
		this.setStatus(STATUS_ATTACKING);
	}
	public void doStandding(){
		this.setStatus(STATUS_STANDDING);
	}
}
