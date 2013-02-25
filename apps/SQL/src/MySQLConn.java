import java.io.*;
import java.util.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;

public class MySQLConn extends MIDlet implements CommandListener {
      private String username;
      private String url = "http://localhost:8080/servlets-examples/servlet/getConnection";
      private Display display;
      private Command exit = new Command("EXIT", Command.EXIT, 1);;
      private Command connect = new Command("Connect", Command.SCREEN, 1);
      private TextField tb;
      private Form menu;
      private TextField tb1;
      private TextField tb2;
      DBConn db;

      public MySQLConn() throws Exception {
            display = Display.getDisplay(this);

      }

      public void startApp() {
            displayMenu();
      }

      public void displayMenu() {
            menu = new Form("Connect"); 
            tb = new TextField("Please input database: ","",30,TextField.ANY );

            tb1 = new TextField("Please input username: ","",30,TextField.ANY);

            tb2 = new TextField("Please input password: ","",30,TextField.PASSWORD);

            menu.append(tb);
            menu.append(tb1);
            menu.append(tb2);
            menu.addCommand(exit);
            menu.addCommand(connect);
            menu.setCommandListener(this);
            display.setCurrent(menu);
      }

      public void pauseApp() {}

      public void destroyApp(boolean unconditional) {}

      public void commandAction(Command command, Displayable screen) {
            if (command == exit) {
                  destroyApp(false);
                  notifyDestroyed();
            } else if (command == connect) {
                  db = new DBConn(this);
                  db.start();
                  db.connectDb(tb.getString(),tb1.getString(),tb2.getString());
            }
      }

      public class DBConn implements Runnable {
            MySQLConn midlet;
            private Display display;
            String db;
            String user;
            String pwd;
            public DBConn(MySQLConn midlet) {
                  this.midlet = midlet;
                  display = Display.getDisplay(midlet);
            }

            public void start() {
                  Thread t = new Thread(this);
                  t.start();
            }

            public void run() {
                  StringBuffer sb = new StringBuffer();
                  try {
                        HttpConnection c = (HttpConnection) Connector.open(url);
                        c.setRequestProperty("User-Agent","Profile/MIDP-1.0, Configuration/CLDC-1.0");
                        c.setRequestProperty("Content-Language","en-US");
                        c.setRequestMethod(HttpConnection.POST); 
                        DataOutputStream os = (DataOutputStream)c.openDataOutputStream();

                        os.writeUTF(db.trim());
                        os.writeUTF(user.trim());
                        os.writeUTF(pwd.trim());
                        os.flush();
                        os.close();

                        // Get the response from the servlet page.
                        DataInputStream is =(DataInputStream)c.openDataInputStream();
                        //is = c.openInputStream();
                        int ch;
                        sb = new StringBuffer();
                        while ((ch = is.read()) != -1) {
                              sb.append((char)ch);
                        }
                        showAlert(sb.toString());
                        is.close();
                        c.close();
                  } catch (Exception e) {
                        showAlert(e.getMessage());
                  }
            }
            /* This method takes input from user like db,user and pwd and pass to servlet */
            public void connectDb(String db,String user,String pwd) {
                  this.db = db;
                  this.user = user;
                  this.pwd = pwd;
            }

            /* Display Error On screen*/
            private void showAlert(String err) {
                  Alert a = new Alert("");
                  a.setString(err);
                  a.setTimeout(Alert.FOREVER);
                  display.setCurrent(a);
            } 
      };
}