package lab4;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UPValidation extends Frame implements ActionListener { 
  DialogTest dt; 
  static TextField tf1; 
  public UPValidation() { 
    Button btn = new Button("Password"); 
    tf1 = new TextField(10); 
    btn.addActionListener(this); 
    add(btn, "North"); 
    add(tf1, "South"); 
    tf1.setBackground(Color.pink);   
    dt = new DialogTest(this);   
    setSize(225,225); setVisible(true); 
  } 
  public void actionPerformed(ActionEvent e) { 
    dt.showPlease(); } 
  public static void confirm(String str1) { 
    if(str1.equals("jyostna")) tf1.setText("Valid Please"); else tf1.setText("Sorry, Invalid Please"); } 
  public static void main(String args[ ]) { new UPValidation(); } 
} 

class DialogTest extends Dialog implements ActionListener { 
  TextField tf2; 
  UPValidation upv; 
  public DialogTest(Frame f1) { 
    super(f1, "Dialog Box by Jyostna", true); setLayout(new FlowLayout()); Button btn1 = new Button("Check Please"); tf2 = new TextField(10); btn1.addActionListener(this); add(new Label("Enter Your Password")); add(tf2); add(btn1);   setSize(225,125); } 
  public void actionPerformed(ActionEvent e) { UPValidation.confirm(tf2.getText()); setVisible(false); } 
  public void showPlease() { setVisible(true); } 
  
  public static void main(String[] args ) {
    DialogTest d = new DialogTest(new UPValidation());
  }
}

