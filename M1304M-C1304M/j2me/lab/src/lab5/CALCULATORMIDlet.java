package lab5;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class CALCULATORMIDlet extends MIDlet implements CommandListener, ActionListener{
  private Display display;
  Button n1,n2,n3,n4,n5,n6,n7,n8,n9,n0,plus,minus,mul,div,dot,equal;
  private TextField enter numbers, result;
  private Command enter,back;
  

 public CALCULATORMIDlet() {
  form = new Form("Sign in");
  userName = new TextField("enter numbers:", "", 30, TextField.ANY);
  password = new TextField("Result:", "", 30, TextField.result);
  cancel = new Command("enter", Command.CANCEL, 2);
  login = new Command("back", Command.OK, 2);
   n7 = new Button("7");
   n7.addActionListener(this);
   n8 = new Button("8");
   n8.addActionListener(this);
   div=new Button("/");
   div.addActionListener(this);

     n4=new Button("4");
     n4.addActionListener(this);
     n5=new Button("5");
     n5.addActionListener(this);
     n6=new Button("6");
     n6.addActionListener(this);
     mul=new Button("*");
     mul.addActionListener(this);

     n1=new Button("1");
     n1.addActionListener(this);
     n2=new Button("2");
     n2.addActionListener(this);
     n3=new Button("3");
     n3.addActionListener(this);
     minus=new Button("-");
     minus.addActionListener(this);

     dot=new Button(".");
     dot.addActionListener(this);
     n0=new Button("0");
     n0.addActionListener(this);
     equal=new Button("=");
     equal.addActionListener(this);
     plus=new Button("+");
     plus.addActionListener(this);
     add(panel,BorderLayout.CENTER);
    }
     public void actionPerformed(ActionEvent ae)
     {
    if(ae.getSource()==n1) assign("1");
    else if(ae.getSource()==n2) assign("2");
    else if(ae.getSource()==n3) assign("3");
    else if(ae.getSource()==n4) assign("4");
    else if(ae.getSource()==n5) assign("5");
    else if(ae.getSource()==n6) assign("6");
    else if(ae.getSource()==n7) assign("7");
    else if(ae.getSource()==n8) assign("8");
    else if(ae.getSource()==n9) assign("9");
    else if(ae.getSource()==n0) assign("0");
    else if(ae.getSource()==dot)
      {
       if(((result.getText()).indexOf("."))==-1)
        result.setText(result.getText()+".");
       }
    else if(ae.getSource()==minus)
       {
       preRes=Double.parseDouble(result.getText());
       lastCommand="-";
       result.setText("0");
       }
    else if(ae.getSource()==div)
       {
       preRes=Double.parseDouble(result.getText());
       lastCommand="/";
       result.setText("0");
       }
    else if(ae.getSource()==equal)
       {
       secVal=Double.parseDouble(result.getText());
       if(lastCommand.equals("/"))
          res=preRes/secVal;
       else if(lastCommand.equals("*"))
          res=preRes*secVal;
       else if(lastCommand.equals("-"))
          res=preRes-secVal;
       else if(lastCommand.equals("+"))
          res=preRes+secVal;
       result.setText(" "+res);
       lastCommand="=";
       }
    else if(ae.getSource()==mul)
       {
        preRes=Double.parseDouble(result.getText());
        lastCommand="*";
        result.setText("0");
        }
    else if(ae.getSource()==plus)
        {
        preRes=Double.parseDouble(result.getText());
        lastCommand="+";
        result.setText("0");
        }

     }
 
    private static void assign(String no)
    {
     if((result.getText()).equals("0"))
      result.setText(no);
      else if(lastCommand=="=")
       {
      result.setText(no);
      lastCommand=null;
       }
      else
      result.setText(result.getText()+no);
     }
	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		
	}
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		
	}
	protected void pauseApp() {
		// TODO Auto-generated method stub
		
	}
	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		
	}
 }
