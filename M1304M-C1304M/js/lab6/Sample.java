package lab7;

import java.awt.*;
import java.awt.event.*;
public class Sample 
{
  public static void main(String args[])
  {
	  //Tao frame voi border layout
	  Frame f=new Frame("SampleGUI");
		f.setLayout(new BorderLayout());
	  //Tao menu bar cho frame
	  MenuBar mb=new MenuBar();
		
		Menu mf=new Menu("File");
		Menu mr=new Menu("Report");
		Menu mh=new Menu("Help");
		mb.add(mf);
		mb.add(mr);
		mb.add(mh);
		
		f.setMenuBar(mb);
	  
	//Tao panel 1 de luu label va 1 button va add vao NORTH cua frame
	Panel pn=new Panel();
	pn.setBackground(Color.red); 
	Label b1=new Label("File Name Here");
	pn.add(b1);
	pn.add(new Button("Load File"));
	f.add(pn, BorderLayout.NORTH);
	
	//Tao panel 2 de luu text aread va add vao CENTER cua frame
	Panel p2 = new Panel();
	p2.setBackground(Color.blue);
	p2.add(new TextArea());
	f.add(p2,BorderLayout.CENTER);
	
	//Tao panel 2 de luu text aread va add vao SOUTH cua frame
	Panel p3 = new Panel();
	p3.setBackground(Color.green);
	p3.add(new Button("Generate Report"));
	p3.add(new Button("EXIT"));
	f.add(p3, BorderLayout.SOUTH);
	
	
	
	
	f.setSize(312,312);
	f.setVisible(true);
	
	
//xu li bien su kien dong cua so ung dung
	f.addWindowListener(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					
				System.exit(0);
			    }
			});
  }
}
