
import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SampleApplet extends Applet {

	String s ="Hello SampleApplet";
	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		this.setSize(400,400);
		arg0.drawString(s, 100, 200);
		add(new Label("Name") );
		add(new TextField(25));
		Button b = new Button("click me");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 s = "repaint me " ;
			 repaint();
				
			}
		});
		add(b);
		super.paint(arg0);
	}
	
	

}
