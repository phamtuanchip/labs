

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 18, 2013  
 */
public class UI{



  public static void main(String[] args) {
    Frame f = new Frame("hello window !");
    f.setLayout(new FlowLayout());
    f.setSize(500, 600);
    f.add(new Label("hello"));
    f.add(new Checkbox("check me"));
    f.add(new Button("click me"));
    f.setVisible(true);
    f.addWindowListener(new WindowListener() {

      @Override
      public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);

      }

      @Override
      public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

      }
    });
  }

}
