package lab4;

/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
/**
*
* @author Administrator
*/
import java.util.*;
import javax.microedition.lcdui.*;
import javax.microedition.pim.*;

class ContactListForm extends List implements CommandListener {
      private final Command exitCommand, selectCommand, backCommand;
      private final SendSms parent;
      Contactnumbers contactnumbers;
      private boolean available;
      private Vector allTelNumbers = new Vector();
      private Display display;
      public ContactListForm(SendSms parent) {
            super("Select Memory", Choice.IMPLICIT);
            this.display = Display.getDisplay(parent);
            this.parent = parent;
            selectCommand = new Command("Select", Command.OK, 0);
            backCommand = new Command("Back", Command.BACK, 1);
            exitCommand = new Command("Exit", Command.EXIT, 1);
            addCommand(backCommand);
            setSelectCommand(selectCommand);
            addCommand(exitCommand);
            setCommandListener(this);
            setFitPolicy(Choice.TEXT_WRAP_ON);
      }

      public void commandAction(Command cmd, Displayable displayable) {
            if (cmd == selectCommand) {
                  int selected = getSelectedIndex();
                  if (selected >= 0) {
                        try {
                              contactnumbers = new Contactnumbers(parent);
                              contactnumbers.loadNames(getString(selected));
                              display.setCurrent(contactnumbers);
                        } catch (PIMException e) {
                              parent.showMessage(e.getMessage(), ContactListForm.this);
                              available = false;
                        } catch (SecurityException e) {
                              parent.showMessage(e.getMessage(), ContactListForm.this);
                              available = false;
                        }
                  } else {
                        parent.showMain();
                  }
            } else if (cmd == backCommand) {
                  parent.showMain();
            } else if (cmd == exitCommand) {
                  parent.destroyApp(false);
            }
      }

      private void displaycontactnames(String contactname) {
            append(contactname, null);
      }
      public void LoadContacts() {
            try {
                  String[] allContactLists = PIM.getInstance().listPIMLists(PIM.CONTACT_LIST);
                  if (allContactLists.length != 0) {
                        for (int i = 0; i < allContactLists.length; i++) {
                              displaycontactnames(allContactLists[i]);
                        }
                        addCommand(selectCommand);
                  }
                  else {
                        append("No Contact lists available", null);
                        available = false;
                  }
            }
            catch (SecurityException e) {
                  parent.showMessage(e.getMessage(), ContactListForm.this);
                  available = false;
            }
      }
}
