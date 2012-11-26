import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
 * Nov 22, 2012  
 */
public class Calcutator extends Frame implements KeyListener, WindowListener, MouseListener  {


  private TextField text ;


  public Calcutator() {

    setLayout(new BorderLayout()) ;
    addWindowListener(this) ;
    //addKeyListener(this);
    //addMouseListener(this);

    text = new TextField() ;
    text.addKeyListener(this) ;

    Button click = new Button("0") ;
    add(text, BorderLayout.NORTH);
    add(click, BorderLayout.CENTER);
    click.addMouseListener(this) ;


  }

  public static void main(String[] args) {

    Calcutator c = new Calcutator() ;
    c.setSize(500, 500) ;
    c.show() ;
  }

  @Override
  public void windowActivated(WindowEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowClosed(WindowEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowClosing(WindowEvent arg0) {
    // TODO Auto-generated method stub

    System.exit(0);

  }

  @Override
  public void windowDeactivated(WindowEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowDeiconified(WindowEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowIconified(WindowEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowOpened(WindowEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_DELETE)
      System.out.println("you pressed VK_SHIFT: "+ e.getKeyCode()) ;

  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println("you pressed to "+ e.getKeyCode()) ;


  }




  @Override
  public void mouseClicked(java.awt.event.MouseEvent arg0) {
    if(arg0.getSource() instanceof Button) {
      Button b = (Button) arg0.getSource();
      System.out.println("you cliecked to " +b.getLabel());
    }

  }

  @Override
  public void mouseEntered(java.awt.event.MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(java.awt.event.MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(java.awt.event.MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(java.awt.event.MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

}
