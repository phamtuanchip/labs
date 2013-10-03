package lab8;

import java.util.Calendar;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

public class VoiceRecordForm extends Form implements CommandListener {
	private StringItem message;
	private StringItem errormessage;
	private final Command record, play;
	private byte[] recordedAudioArray = null;
	private TextField textfieldH = new TextField("Hour:", "", 30, TextField.ANY);
	private TextField textfieldM = new TextField("Minute:", "", 30, TextField.ANY);
	private TextField textfieldS = new TextField("Second:", "", 30, TextField.ANY);



	private int minDiff;
	private int secondDiff;

	private int userMinute;
	private int userSecond;



	public VoiceRecordForm() {
		super("Recording Audio");
		message = new StringItem("", "Select Record to start recording.");
		this.append(message);
		errormessage = new StringItem("", "");
		this.append(errormessage);
		this.append(textfieldH);
		this.append(textfieldM);
		this.append(textfieldS);

		record = new Command("Record", Command.OK, 0);
		this.addCommand(record);
		play = new Command("Play", Command.BACK, 0);
		this.addCommand(play);
		this.setCommandListener(this);



	}
	public void commandAction(Command comm, Displayable disp) {
		Thread_1 instanceThread1 = new Thread_1();
		Thread_2 instanceThread2 = new Thread_2();

		Thread t1 = new Thread(instanceThread1);
		Thread t2 = new Thread(instanceThread2);



		userMinute = Integer.parseInt(textfieldM.getString());
		userSecond = Integer.parseInt(textfieldS.getString());
		while (true){
			Calendar c = Calendar.getInstance();
			minDiff = (c.get(Calendar.MINUTE) - userMinute);
			secondDiff =  (c.get(Calendar.SECOND) - userSecond);
			//textfieldS.setString(Integer.toString(c.get(Calendar.SECOND)));

			if(minDiff == 0 & secondDiff == 0) {
				break;
			}
		}
		if (comm == record) {
			while (true){
				t1.start();
				t2.start();
				Calendar c = Calendar.getInstance();
				minDiff = (c.get(Calendar.MINUTE) - userMinute + 1);
				//secondDiff =  (c.get(Calendar.SECOND) - userSecond);
				//textfieldS.setString(Integer.toString(c.get(Calendar.SECOND)));

				if(minDiff == 0 ) {
					break;
				}
			}




		}

	}

}
