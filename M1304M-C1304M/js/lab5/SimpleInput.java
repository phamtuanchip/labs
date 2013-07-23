

import java.applet.Applet;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.SwingUtilities;


/*
 * Copyright (C) 2003-2012 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Nov 26, 2012  
 */
@SuppressWarnings("serial")
public class SimpleInput extends Applet implements ActionListener, WindowListener{

  private String textField_ = null ;
  TextField input ;
  List select = new List() ;
  public void init(){

    setLayout(null) ;
    setSize(500, 500);
    Panel p1 = new Panel() ;
    p1.setLayout(new GridLayout(2,1)) ;
    input = new TextField() ;
    p1.setBounds(100, 20, 300, 50);
    p1.add(input);
    Button b = new Button("Draw") ;
    // b.setBounds(200, 20, 50, 20) ;
    p1.add(b);
    b.addActionListener(this) ;

    add(p1) ;

    Label l = new Label("Fonts");
    Panel p2 = new Panel() ;
    p2.setLayout(new GridLayout(2,1)) ;
   
    GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment() ;

    String[] fonts = g.getAvailableFontFamilyNames() ;
    for (String name : fonts) {
      select.add(name) ;

    }
    p2.add(l);
    p2.add(select);
    p2.setBounds(100, 100, 300, 75);


    add(p2);


  }
  
   

  @Override
public void paint(Graphics g) {
	// TODO Auto-generated method stub
	  if(textField_ != null){
		  g.setFont(new Font(select.getSelectedItem(), 50, 30));
		  g.drawString(textField_, 100, 200);
	  
	  }
	super.paint(g);
}



@Override
  public void actionPerformed(ActionEvent e) {
	   
    if("Draw".equals(e.getActionCommand())) {
      //button clicked  ;
      textField_ =  input.getText() ;
      if(textField_ == null || textField_.trim().length() == 0 ) {
        Frame f =
            (Frame)SwingUtilities.getAncestorOfClass(Frame.class,
                                                     this);
        Dialog d = new Dialog(f,false);
        d.add(new Label("you have to input value !")) ;
        d.addWindowListener(this) ;
        d.setSize(200, 200) ;
        d.show() ;
        return ;
      }
      repaint() ;

    }

  }



  @Override
  public void windowActivated(WindowEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowClosed(WindowEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowClosing(WindowEvent e) {
    // TODO Auto-generated method stub
    try {
      Dialog d = (Dialog) e.getSource() ;
      d.setVisible(false) ;
    } catch (Exception ee) {
      ee.printStackTrace() ;
    }

  }

  @Override
  public void windowDeactivated(WindowEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowDeiconified(WindowEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowIconified(WindowEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowOpened(WindowEvent e) {
    // TODO Auto-generated method stub

  }



}
