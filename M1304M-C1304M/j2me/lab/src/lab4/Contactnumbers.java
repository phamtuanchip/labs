package lab4;

import java.util.*;
import javax.microedition.lcdui.*;
import javax.microedition.pim.*;


public class Contactnumbers extends Form implements CommandListener {
      private final Command exit, select, back;
      boolean available;
      private Vector allTelNumbers = new Vector();
      private Display display;
      private ChoiceGroup contactnum;
      SendSms parent;
      ContactListForm contactlistform;
      public Contactnumbers(SendSms parent) {
            super("");
            this.parent = parent;
            this.display = Display.getDisplay(parent);
            contactnum = new ChoiceGroup("Contacts", ChoiceGroup.EXCLUSIVE);
            contactnum.deleteAll();
            append(contactnum);
            exit = new Command("Exit", Command.SCREEN, 2);
            select = new Command("Submit", Command.BACK, 0);
            back = new Command("Back", Command.SCREEN, 1);
            addCommand(select);
            addCommand(back);
            addCommand(exit);
            setCommandListener(this);
      }

      public void commandAction(Command cmd, Displayable disp) {
            if (cmd == select) {
                  int selected = contactnum.getSelectedIndex();
                  if (selected >= 0) {
                        parent.contactSelected((String) allTelNumbers.elementAt(selected));
                  }
            }
            if (cmd == back) {
                  parent.showContactsList();
            }
            if (cmd == exit) {
                  parent.notifyDestroyed();
            }
      }
      public void loadNames(String name) throws PIMException, SecurityException {
            ContactList contactList = null;
            try {
                  contactList = (ContactList) PIM.getInstance().openPIMList(PIM.CONTACT_LIST, PIM.READ_ONLY, name);
                  if (contactList.isSupportedField(Contact.FORMATTED_NAME) && contactList.isSupportedField(Contact.TEL)) {
                        Enumeration items = contactList.items();
                        Vector telNumbers = new Vector();
                        while (items.hasMoreElements()) {
                              Contact contact = (Contact) items.nextElement();
                              int telCount = contact.countValues(Contact.TEL);
                              int nameCount = contact.countValues(Contact.FORMATTED_NAME);
                              if (telCount > 0 && nameCount > 0) {
                                    String contactName = contact.getString(Contact.FORMATTED_NAME, 0);
                                    for (int i = 0; i < telCount; i++) {
                                          int telAttributes = contact.getAttributes(Contact.TEL, i);
                                          String telNumber = contact.getString(Contact.TEL, i);
                                          telNumbers.addElement(telNumber);
                                          allTelNumbers.addElement(telNumber);
                                    }
                                    for (int i = 0; i < telNumbers.size(); i++) {
                                          contactnum.append(contactName, null);
                                          contactnum.setSelectedIndex(0, true);
                                    }
                                    telNumbers.removeAllElements();
                              }
                        }
                        available = true;
                  } else {
                        contactnum.append("Contact list required items not supported", null);
                        available = false;
                  }
            } finally {
                  if (contactList != null) {
                        contactList.close();
                  }
            }
      }
}