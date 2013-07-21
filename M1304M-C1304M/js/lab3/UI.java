

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.*;
/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 18, 2013  
 */
public class UI{



  public static void main(String[] args) {
    Frame f = new Frame("hello window !");
    Panel p = new Panel();
    p.setSize(100, 100);
    //p.setb
    //f.setLayout(new FlowLayout());4
    f.add(p);
    f.setSize(500, 600);
    //f.add(new Label("hello"));
    //f.add(new Checkbox("check me"));
    //f.add(new Button("click me"));
    f.setVisible(true);

    f.addWindowListener( new DieuKhienCuaSo()  );
  }

}
