import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.pim.Contact;
import javax.microedition.pim.ContactList;
import javax.microedition.pim.PIM;
import javax.microedition.pim.PIMException;

public class StubMIDlet extends MIDlet implements CommandListener {
    private Display display;
    private Form mainForm;
    private TextField logField;
    private DataOutputStream logStream;

    private List listContacts;

    /**
     * Holds reference to the currently opened contact list.
     */
    private ContactList contactList;

    private final String LOG_PATH;
    private static final String LOG_FILE = "JavaMEStubLog.txt";

    private static final Command EXECUTE_COMMAND =
            new Command("Execute snippet", Command.ITEM, 0);
    private static final Command EXIT_COMMAND =
            new Command("Exit", Command.EXIT, 0);
    private static final Command BACK_COMMAND =
            new Command("Back", Command.BACK, 0);

    public StubMIDlet() {
        // The Others directory cannot be accessed through properties, so use
        // the picture directory (e.g. C:\Data\Images) as the destination for
        // logs
        LOG_PATH = System.getProperty("fileconn.dir.photos");

        display = Display.getDisplay(this);

        setupMainForm();
        setupLogFile();
        setupContactList();
    }

    /**
     * Sets up the main form.
     */
    private void setupMainForm() {
        mainForm = new Form("StubMIDlet");

        logField = new TextField("Log", null, Short.MAX_VALUE, TextField.PLAIN);
        mainForm.append(logField);

        mainForm.addCommand(EXECUTE_COMMAND);
        mainForm.addCommand(EXIT_COMMAND);
        mainForm.setCommandListener(this);
    }

    /**
     * Sets up the stream for logging.
     */
    private void setupLogFile() {
        String fcVersion =
            System.getProperty("microedition.io.file.FileConnection.version");
        if (fcVersion == null) {
            printString("The device doesn't support file logging.\n" +
                    "Log output is not available.");
            return;
        }
        try {
            FileConnection fconn =
                    (FileConnection)Connector.open(LOG_PATH + LOG_FILE);
            if (!fconn.exists()) {
                fconn.create();
            }
            logStream = fconn.openDataOutputStream();
            fconn.close();
        } catch (IOException ex) {
            printString("The log file could not be set up.\n" +
                    "Log output is not available.");
            return;
        }
    }

    /**
     * Instantiates a listContacts.
     */
    private void setupContactList() {
        listContacts = new List("Contact list", Choice.IMPLICIT);
        listContacts.addCommand(BACK_COMMAND);
        listContacts.setCommandListener(this);
    }

    /**
     * Executes the snippet.
     */
    private void executeSnippet() {
        openContactList();
    }

    /**
     * Fills listContacts fith data read from PIM database and brings
     * the list to the foreground.
     */
    private void openContactList() {
        fillContactList();
        display.setCurrent(listContacts);
        printString("Done");
    }

    /**
     * Reads contacts list from PIM database 'Contacts' and fills listContacts
     * with contact 'name' field value.
     */
    private void fillContactList() {
        try {
            printString("Opening contact list ...");
            contactList = (ContactList)PIM.getInstance().openPIMList(
                    PIM.CONTACT_LIST, PIM.READ_WRITE);

        } catch (PIMException ex) {
            printString(ex.toString());
            return;
        }
        Enumeration contacts = null;
        Contact contact = null;
        try {
            printString("Getting contact list items");
            contacts = contactList.items();
        } catch (PIMException ex) {
            releaseContactList();
            printString(ex.toString());
            return;
        }
        // Checking contact count
        if (!contacts.hasMoreElements()) {
            printString("Contact list IS empty!");
        }
        if (listContacts.size() > 0) {
            listContacts.deleteAll();
        }
        while (contacts.hasMoreElements()) {
            contact = (Contact)contacts.nextElement();
            String contactInfo = contact.getStringArray(Contact.NAME,
                    Contact.ATTR_NONE)[Contact.NAME_GIVEN];
            if (contactInfo != null) {
                listContacts.append(contactInfo, null);
            }
        }
    }

    /**
     * Closes the contact list if one is opened.
     */
    private void releaseContactList() {
        if (contactList != null) {
            try {
                printString("Closing contact list");
                contactList.close();
            } catch (PIMException ex) {
                printString(ex.toString());
            }
        }
        contactList = null;
    }

    /**
     * Displays an information alert.
     */
    private void displayNote(String text) {
        Alert infoNote = new Alert("Info", text, null, AlertType.INFO);
        infoNote.setTimeout(3000);
        display.setCurrent(infoNote, mainForm);
    }

    /**
     * Prints a string on the screen.
     */
    private void printString(String text) {
        logField.insert(text + "\n", logField.size());
    }

    /**
     * Prints a string in the log file.
     */
    private void logPrintString(String text) throws IOException {
        if (logStream == null) {
            return;
        }
        logStream.writeChars(text + "\n");
        logStream.flush();
    }

    /**
     * Closes the opened resources and exits the application.
     */
    private void exit() {
        try {
            if (logStream != null) {
                logStream.close();
            }
        } catch (IOException ex) {
            printString(ex.getMessage());
        }
        // Close contact list if opened
        releaseContactList();
        notifyDestroyed();
    }

    /**
     * From MIDlet.
     * Called when the MIDlet is started.
     */
    public void startApp() {
        // The initial display is the main form
        display.setCurrent(mainForm);
    }

    /**
     * From MIDlet.
     * Called to signal the MIDlet to enter the Paused state.
     */
    public void pauseApp() {
        // No implementation required
    }

    /**
     * From MIDlet.
     * Called to signal the MIDlet to terminate.
     * @param unconditional whether the MIDlet has to be unconditionally
     * terminated
     */
    public void destroyApp(boolean unconditional) {
        // No implementation required
    }

    /**
     * From CommandListener.
     * Called by the system to indicate that a command has been invoked on a
     * particular displayable.
     * @param command the command that was invoked
     * @param displayable the displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {
        if (command == EXIT_COMMAND) {
            // Exit the MIDlet
            exit();
        } else if (command == EXECUTE_COMMAND) {
            // Execute the snippet
            executeSnippet();
        } else if (command == BACK_COMMAND) {
            display.setCurrent(mainForm);
            // Close ContactList object instance
            releaseContactList();
        }
    }
}
