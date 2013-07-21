/*
  * Copyright (C) 2003-2013 eXo Platform SAS.
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
package lab4;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.EventListener;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 18, 2013  
 */
public class UI extends Frame implements EventListener{


  void createWindow() {
     
      
  }
  
  public void creteDialog(){
    AboutDialog ab = new AboutDialog(this);
    ab.setVisible(true);
  }
  
  public void createMenu(){
    
    
      MenuBar mbar = new MenuBar();
      Menu m = new Menu("File");
      m.add(new MenuItem("New"));
      m.add(new MenuItem("Open"));
      m.add(new MenuItem("Save"));
      m.add(new MenuItem("Save As"));
      m.add(new MenuItem("Print"));
      m.addSeparator();
      MenuItem miq = new MenuItem("Quit");
      miq.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent arg0) {
          System.exit(0);
          
        }
      });
      m.add(miq);    
      mbar.add(m);
        
      m = new Menu("Help");
      m.add(new MenuItem("Help!!!!"));
      m.addSeparator();
      MenuItem miab = new MenuItem("About...");
      miab.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
          creteDialog();
          
        }
      });
      m.add(miab);
      mbar.add(m);

      // Recent change
      setMenuBar(mbar);
   
  }

  public static void main(String[] args) {
   UI f = new UI();
   f.createMenu();
   f.setLayout(new FlowLayout());
   f.setSize(500, 600);
    
   f.addWindowListener(new WindowListener() {
     
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
     
   });

   f.setVisible(true);
  }

}
