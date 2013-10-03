package lab8;

import java.util.Hashtable;
import java.util.Enumeration;

import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Alert;
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

public class MediaMIDletV2 extends MIDlet
  implements CommandListener {

	private Display display;
	private List itemList;
	private Form form;

	private Hashtable items;

	public MediaMIDletV2() {

	  display = Display.getDisplay(this);

	  // creates an item list to let you select multimedia files to play
	  itemList = new List("Select an item to play", List.IMPLICIT);


	  // a form to display when items are being played
	  form = new Form("Playing media");

	  // create a hashtable of items
	  items = new Hashtable();

	  // and populate both of them
	  items.put("Siren from web", "http://www.craftbits.com/j2me/siren.wav");
	  items.put(
			"Promo Video from web",
	    "http://www.craftbits.com/j2me/promo.mpg");
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
	}

	public void destroyApp(boolean unconditional) {
	}


	public void commandAction(Command command, Displayable disp) {

		// generic command handler

		// if list is displayed, the user wants to play the item
		if(disp instanceof List) {
			List list = ((List)disp);

			String key = list.getString(list.getSelectedIndex());

			// try and play the selected file
			try {
				playMedia((String)items.get(key));
			} catch (Exception e) {
				System.err.println("Unable to play: " + e);
				e.printStackTrace();
			}
		}

	}


	/* Creates Player and plays media for the first time */
	private void playMedia(String locator) throws Exception {

		PlayerManager manager =
		  new PlayerManager(form, itemList, locator, display);
	  form.setCommandListener(manager);
	  Thread runner = new Thread(manager);
	  runner.start();
	}

}

class PlayerManager implements Runnable, CommandListener, PlayerListener {

	Form form;
	List list;
	Player player;
	String locator;
	Display display;

	private Command stopCommand;
	private Command pauseCommand;
	private Command startCommand;

	public PlayerManager(Form form, List list, String locator, Display display) {
		this.form = form;
		this.list = list;
		this.locator = locator;
		this.display = display;

	  // stop, pause and restart commands
	  stopCommand = new Command("Stop", Command.STOP, 1);
	  pauseCommand = new Command("Pause", Command.ITEM, 1);
	  startCommand = new Command("Start", Command.ITEM, 1);

	  // the form acts as the interface to stop and pause the media
	  form.addCommand(stopCommand);
	  form.addCommand(pauseCommand);

	}

	public void run() {

		try {

			// since we are loading data over the network, a delay can be
			// expected
			Alert alert = new Alert("Loading. Please wait ....");
			alert.setTimeout(Alert.FOREVER);
			display.setCurrent(alert);

			player = Manager.createPlayer(locator);

			// a listener to handle player events like starting, closing etc
			player.addPlayerListener(this);

			player.setLoopCount(-1); // play indefinitely
			player.prefetch(); // prefetch
			player.realize(); // realize

			player.start(); // and start
		} catch(Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}

	}


	public void commandAction(Command command, Displayable disp) {

		if(disp instanceof Form) {

			// if showing form, means the media is being played
			// and the user is trying to stop or pause the player
			try {

				if(command == stopCommand) { // if stopping the media play

					player.close(); // close the player
					display.setCurrent(list); // redisplay the list of media
					form.removeCommand(startCommand); // remove the start command
					form.removeCommand(pauseCommand); // remove the pause command
					form.removeCommand(stopCommand);  // and the stop command

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