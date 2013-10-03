package lab8;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.RecordControl;

public class RecordForm extends Form implements Runnable, CommandListener{
	private StringItem messageItem;
    private StringItem errorItem;
    private final Command recordCommand, playCommand;
    private Player p;
    private byte[] recordedSoundArray = null;
    
    public RecordForm(){
        super("Record Audio");        
        messageItem = new StringItem("Record", "Click record to start recording.");
        this.append(messageItem);
        errorItem = new StringItem("", "");
        this.append(errorItem);        
        recordCommand = new Command("Record", Command.SCREEN, 1);
        this.addCommand(recordCommand);
        playCommand = new Command("Play", Command.SCREEN, 2);
        this.addCommand(playCommand);        
        StringBuffer inhalt = new StringBuffer();        
        this.setCommandListener(this);
    }
    
    public void commandAction(Command comm, Displayable disp){
        if(comm==recordCommand){
            try{                
                p = Manager.createPlayer("capture://audio?encoding=pcm");
                p.realize();                
                RecordControl rc = (RecordControl)p.getControl("RecordControl");                
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                rc.setRecordStream(output);                
                rc.startRecord();
                p.start();
                messageItem.setText("recording...");
                Thread.currentThread().sleep(5000);
                messageItem.setText("done!");
                rc.commit();               
                recordedSoundArray = output.toByteArray();                
                p.close();
            } catch (IOException ioe) {
                errorItem.setLabel("Error");
                errorItem.setText(ioe.toString());
            } catch (MediaException me) {
                errorItem.setLabel("Error");
                errorItem.setText(me.toString());
            } catch (InterruptedException ie) {
                errorItem.setLabel("Error");
                errorItem.setText(ie.toString());
            }
        } else if(comm == playCommand) {
            try {
                ByteArrayInputStream recordedInputStream = new ByteArrayInputStream(recordedSoundArray);
                Player p2 = Manager.createPlayer(recordedInputStream,"audio/basic");
                p2.prefetch();
                p2.start();
            }  catch (IOException ioe) {
                errorItem.setLabel("Error");
                errorItem.setText(ioe.toString());
            } catch (MediaException me) {
                errorItem.setLabel("Error");
                errorItem.setText(me.toString());
            }
        }
    }

	public void run() {
		// TODO Auto-generated method stub
		
	}
}
