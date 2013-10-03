package lab8;

import java.util.Hashtable;
import java.util.Enumeration;

import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.CommandListener;

import javax.microedition.media.Player;
import javax.microedition.media.Control;
import javax.microedition.media.Manager;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.VideoControl;

public class MediaMIDlet2 extends MIDlet
  implements CommandListener, PlayerListener {

	private Display display;
	private List itemList;
	private Form form;

	private Command stopCommand;
	private Command pauseCommand;
	private Command startCommand;

	private Hashtable items;
	private Hashtable itemsInfo;

	private Player player;

	public MediaMIDlet2() {

	  display = Display.getDisplay(this);

	  // creates an item list to let you select multimedia files to play
	  itemList = new List("Select an item to play", List.IMPLICIT);

	  // stop, pause and restart commands
	  stopCommand = new Command("Stop", Command.STOP, 1);
	  pauseCommand = new Command("Pause", Command.ITEM, 1);
	  startCommand = new Command("Start", Command.ITEM, 1);

	  // a form to display when items are being played
	  form = new Form("Playing media");

	  // the form acts as the interface to stop and pause the media
	  form.addCommand(stopCommand);
	  form.addCommand(pauseCommand);
	  form.setCommandListener(this);

	  // create a hashtable of items
	  items = new Hashtable();

	  // and a hashtable to hold information about them
	  itemsInfo = new Hashtable();

	  // and populate both of them
	  items.put("Siren from jar", "file://siren.wav");
	  itemsInfo.put("Siren from jar", "audio/x-wav");
	  items.put("Promo Video from jar", "file://promo.mpg");
	  itemsInfo.put("Promo Video from jar", "video/mpeg");
	}

	public void startApp() {

		// when MIDlet is started, use the item list to display elements
		for(Enumeration en = items.keys(); en.hasMoreElements();) {
	    itemList.append((String)en.nextElement(), null);
		}

	  itemList.setCommandListener(this);

		// show the list when MIDlet is started
		display.setCurrent(itemList);
	}

	public void pauseApp() {
		 // pause the player
		try {
		  if(player != null) player.stop();
		} catch(Exception e) {}
	}

	public void destroyApp(boolean unconditional) {
		if(player != null) player.close(); // close the player
	}

	public void commandAction(Command command, Displayable disp) {

		// generic command handler

		// if list is displayed, the user wants to play the item
		if(disp instanceof List) {
			List list = ((List)disp);

			String key = list.getString(list.getSelectedIndex());

			// try and play the selected file
			try {
				playMedia((String)items.get(key), key);
			} catch (Exception e) {
				System.err.println("Unable to play: " + e);
				e.printStackTrace();
			}
		} else if(disp instanceof Form) {

			// if showing form, means the media is being played
			// and the user is trying to stop or pause the player
			try {

				if(command == stopCommand) { // if stopping the media play

					player.close(); // close the player
					display.setCurrent(itemList); // redisplay the list of media
					form.removeCommand(startCommand); // remove the start command
					form.addCommand(pauseCommand); // add the pause command

				} else if(command == pauseCommand) { // if pausing

					player.stop(); // pauses the media, note that it is called stop
					form.removeCommand(pauseCommand); // remove the pause command
					form.addCommand(startCommand); // add the start (restart) command
				} else if(command == startCommand) { // if restarting

					player.start(); // starts from where the last pause was called
					form.removeCommand(startCommand);
					form.addCommand(pauseCommand);
				}
			} catch(Exception e) {
				System.err.println(e);
			}
		}

	}

	/* Creates Player and plays media for the first time */
	private void playMedia(String locator, String key) throws Exception {

		// locate the actual file, we are only dealing
		// with file based media here
		String file = locator.substring(
			locator.indexOf("file://") + 6,
			locator.length());

		// create the player
		// loading it as a resource and using information about it
		// from the itemsInfo hashtable
		player =
			Manager.createPlayer(
				getClass().getResourceAsStream(file), (String)itemsInfo.get(key));

		// a listener to handle player events like starting, closing etc
		player.addPlayerListener(this);

		player.setLoopCount(-1); // play indefinitely
		player.prefetch(); // prefetch
		player.realize(); // realize

		player.start(); // and start

	}

	/* Handle player events */
	public void playerUpdate(Player player, String event, Object eventData) {

		// if the event is that the player has started, show the form
		// but only if the event data indicates that the event relates to newly
		// stated player, as the STARTED event is fired even if a player is
		// restarted. Note that eventData indicates the time at which the start
		// event is fired.
		if(event.equals(PlayerListener.STARTED) &&
		  new Long(0L).equals((Long)eventData)) {

			// see if we can show a video control, depending on whether the media
			// is a video or not
			VideoControl vc = null;
			if((vc = (VideoControl)player.getControl("VideoControl")) != null) {
				Item videoDisp =
				  (Item)vc.initDisplayMode(vc.USE_GUI_PRIMITIVE, null);
				form.append(videoDisp);
			}

			display.setCurrent(form);
		} else if(event.equals(PlayerListener.CLOSED)) {

			form.deleteAll(); // clears the form of any previous controls
		}
	}

}