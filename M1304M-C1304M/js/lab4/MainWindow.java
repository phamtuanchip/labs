package lab4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
public class MainWindow extends Frame{

	Panel p1 = new Panel();
	Panel p2 = new Panel();
	Panel p3 = new Panel();
	Panel p4 = new Panel();
	Panel p5 = new Panel();
	Panel p6 = new Panel();
	Label l1 = new Label("Ma SV");
	TextField ma = new TextField(25);
	TextField ten = new TextField(25);

	Label l2 = new Label("Ten");
	Label l3 = new Label("Tuoi");
	Choice tuoi = new Choice();
	Label l4 = new Label("Gioi Tinh");
	CheckboxGroup gt = new CheckboxGroup();
	Checkbox nam = new Checkbox("Nam", gt, true) ;
	Checkbox nu = new Checkbox("Nu", gt, false);
	TextArea diachi = new TextArea(1,25);
	Label l5 = new Label("Dia Chi");

	Button save = new Button("Luu");
	Button cancel = new Button("Bo qua");
	Button reset = new Button("Lam lai");

	public MainWindow(String title) {
		super(title);
		setLayout(new BorderLayout());
		p1.setLayout(new GridLayout(5,1));
		p1.add(l1);
		p1.add(l2);
		p1.add(l3);
		p1.add(l4);
		p1.add(l5);

		add(p1, BorderLayout.WEST);
		p2.setLayout(new GridLayout(5,1));
		p2.add(ma);
		p2.add(ten);
		for(int i = 1920; i <= 2013; i++){
			tuoi.add(String.valueOf(i));
		}
		p2.add(tuoi);
		p3.setLayout(new FlowLayout());
		p3.add(nam);
		p3.add(nu);
		p2.add(p3);
		p2.add(diachi);
		add(p2);

		p6.setLayout(new FlowLayout());
		p6.add(save);
		p6.add(reset);
		p6.add(cancel);
		add(p6, BorderLayout.SOUTH);


		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				creteDialog();
			}
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ma.setText(null);
				ten.setText(null);
				nam.setState(true);
				nu.setState(false);
			
				diachi.setText(null);
				
			}
		});


	}

	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	public  void creteDialog(){
		AboutDialog ab = new AboutDialog(this);
		ab.setVisible(true);
	}

	public static void main(String[] args){
		MainWindow m = new MainWindow("Them thong tin sinh vien");
		m.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				System.exit(0);
			}
		});

		m.setSize(500, 200);
		centreWindow(m);

		m.setVisible(true);
	}




}
