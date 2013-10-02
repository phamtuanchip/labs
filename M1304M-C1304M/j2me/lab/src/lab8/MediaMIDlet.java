package lab8;
import java.io.*;
import java.util.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.microedition.media.*;

public class MediaMIDlet extends MIDlet implements CommandListener, PlayerListener {	
	private Display display;
	private List itemList;
	private Form form;
	private Command stopCommand, pauseCommand, startCommand;
	private Hashtable items, itemsInfo;
	private Player player;

	public MediaMIDlet() {
		display = Display.getDisplay(this);
		itemList = new List("Select an item to play", List.IMPLICIT);
		stopCommand = new Command("Stop", Command.STOP, 1);
		pauseCommand = new Command("Pause", Command.ITEM, 1);
		startCommand = new Command("Start", Command.ITEM, 1);
		form = new Form("Playing media");
		form.addCommand(stopCommand);
		form.addCommand(pauseCommand);
		form.setCommandListener(this);
		items = new Hashtable();
		itemsInfo = new Hashtable();

		items.put("Kyo Aage-Piche Dolte Ho...", "file://aagepiche.wav");
		itemsInfo.put("Kyo Aage-Piche Dolte Ho...", "audio/x-wav");

		items.put("kabhi alvida-na-kehna...", "file://kabhi-alvida-na-kehna.wav");
		itemsInfo.put("kabhi alvida-na-kehna...", "audio/x-wav");

		items.put("Lucky Boy...", "file://Lucky-Boy.wav");
		itemsInfo.put("Lucky Boy...", "audio/x-wav");
		
		items.put("Move Your Body...", "file://Move-Your-Body.wav");
		itemsInfo.put("Move Your Body...", "audio/x-wav");

		items.put("Jee Karda...", "file://Jee-Karda.wav");
		itemsInfo.put("Jee Karda...", "audio/x-wav");

		items.put("masoomchehra...", "file://masoomchehra.wav");
		itemsInfo.put("masoomchehra...", "audio/x-wav");

		items.put("tu-saala...", "file://tu-saala.wav");
		itemsInfo.put("tu-saala...", "audio/x-wav");
	}

	public void startApp() {
		for(Enumeration en = items.keys(); en.hasMoreElements();) {
			itemList.append((String)en.nextElement(), null);
		}
		itemList.setCommandListener(this);
		display.setCurrent(itemList);
	}

	public void pauseApp() {
		try {
			if(player != null) player.stop();
		} catch(Exception e) {}
	}

	public void destroyApp(boolean unconditional) {
		if(player != null) player.close();
	}

	public void commandAction(Command command, Displayable disp){
		if(disp instanceof List) {
			List list = ((List)disp);			
			String key = list.getString(list.getSelectedIndex());
			try {
				playMedia((String)items.get(key), key);
			} catch (Exception e) {
				System.err.println("Unable to play: " + e);
				e.printStackTrace();
			}
		} else if(disp instanceof Form){
			try {
				if(command == stopCommand){
					player.close();
					display.setCurrent(itemList);
					form.removeCommand(startCommand);
					form.addCommand(pauseCommand);
				} else if(command == pauseCommand){
					player.stop();
					form.removeCommand(pauseCommand);
					form.addCommand(startCommand);
				} else if(command == startCommand){
					player.start();
					form.removeCommand(startCommand);
					form.addCommand(pauseCommand);
				}
			} catch(Exception e) {
				System.err.println(e);
			}
		}
	}

	private void playMedia(String locator, String key) throws Exception {
		String file = locator.substring(locator.indexOf("file://") + 6, locator.length());
		player = Manager.createPlayer(getClass().getResourceAsStream(file), (String)itemsInfo.get(key));
		player.addPlayerListener(this);
		player.setLoopCount(-1);
		player.prefetch();
		player.realize();
		player.start();
	}

	public void playerUpdate(Player player, String event, Object eventData) {
		if(event.equals(PlayerListener.STARTED) && new Long(0L).equals((Long)eventData)) {
			display.setCurrent(form);
		} else if(event.equals(PlayerListener.CLOSED)) {
			form.deleteAll();
		}
	}
}