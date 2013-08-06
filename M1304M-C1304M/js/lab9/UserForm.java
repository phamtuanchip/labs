package lab9;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class UserForm extends Frame {


	Panel mainPanel = new Panel();
	String formatDate = "dd/mm/yyyy";
	int fiedWidth = 25 ;
	Label name = new Label("Name:");
	TextField nameInput = new TextField(fiedWidth);
	Label age = new Label("Age:");
	TextField ageInput = new TextField(3);

	Label email = new Label("Email:");
	TextField emailInput = new TextField(fiedWidth);

	Label dob = new Label("Birthday:");
	TextField dobInput = new TextField(formatDate,fiedWidth);

	Label address = new Label("Address:");
	TextField addressInput = new TextField(fiedWidth);

	Label note = new Label("Note:");
	TextArea noteInput = new TextArea(4, fiedWidth);

	public Panel buttons = new Panel();

	Button save = new Button("Save");

	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();


	// Contructor thuong dung de khoi tao toan bo cac component can thiet, tao layout va add component, va add cac su kien luon 
	public UserForm(){

		super("User information");
		setSize(800,500);
		// Main panel se dung de holder toan bo phan component ben tren
		mainPanel.setLayout(gb);

		//Set thuoc thinh phu theo chieu dung
		gbc.fill = GridBagConstraints.HORIZONTAL;

		//Hang thu 1
		addComponent(mainPanel, name,0,0,1,1);
		addComponent(mainPanel, nameInput,0,2,3,1);

		addComponent(mainPanel, age,0,7,1,1);
		addComponent(mainPanel, ageInput,0,8,1,1);

		addComponent(mainPanel, email,0,15,1,1);
		addComponent(mainPanel, emailInput,0,17,3,1);

		//Hang thu 2
		addComponent(mainPanel, dob,3,0,1,1);
		addComponent(mainPanel, dobInput,3,2,3,1);

		addComponent(mainPanel, address,3,7,1,1);
		addComponent(mainPanel, addressInput,3,8,3,16);

		//Hang thu 3
		addComponent(mainPanel, note,8,0,1,1);
		addComponent(mainPanel, noteInput,8,2,2,16);

		add(mainPanel, BorderLayout.CENTER);

		buttons.add(save);
		add(buttons, BorderLayout.SOUTH);

		//Cai dat su kien onfocus thi xoa gia tri mac dinh cua truong birthday 
		dobInput.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				dobInput.setText("");
				
			}
		});
		
		//Cai dat su kien cho button save
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Luu y cho nay ta validate tung thu mot lan luot tu tren xuong duoi
				if(valid(nameInput, name)) 
					if(valid(ageInput, age))
						if(validAge(ageInput, age))
							if(valid(emailInput, email))
								if(validMail(emailInput, email))
									if(valid(dobInput, dob))
										if(validDate(dobInput, dob));
				 
			}
		});


	}

	/**
	 * Ham nay tu viet de set lai vi tri component tren panel theo GridBagLayout voi GridBagConstraints 
	 * @param card : panel truyen vao de set vi tri 
	 * @param comp : component can add vao
	 * @param row : rong can add
	 * @param col : cot can add
	 * @param nrow : so luong dong can de ghi component len
	 * @param ncol : so luong cot can de ghi component len
	 */
	public void addComponent(Panel card, Component comp, int row, int col, int   nrow, int ncol)
	{

		gbc.gridy = row;
		gbc.gridx = col;
		gbc.gridheight = nrow;
		gbc.gridwidth = ncol;
		//gbc.insets = new Insets(5, 5, 5, 5) ;
		gb.setConstraints(comp,gbc);
		card.add(comp);
	}

	//ham valid de kiem tra cac truong can thiet 
	boolean valid(TextField name, Label label){
		if(name.getText() == null || name.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, label.getText() + " can phai co!");
			return false;
		} else {
			return true;
		}
	}

	boolean validAge(TextField name, Label label){
		try {
			int tuoi = Integer.parseInt(name.getText());
			if( tuoi <= 0) {
				JOptionPane.showMessageDialog(this, label.getText() + " phai lon hon 0!");
				return false;
			} else return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, label.getText() + " phai la so!");
			return false;
		}
	}

	boolean validMail(TextField name, Label label){
		if(new EmailValidator().validate(name.getText())){
			return true;
		} else {
			JOptionPane.showMessageDialog(this, label.getText() + " khong dung dinh dang (vi du: name@domain.com)!");
			return false;
		}
	}

	boolean validDate(TextField name, Label label){
		try {
			SimpleDateFormat df = new SimpleDateFormat(formatDate);
			df.parse(name.getText());
			return true;
		} catch (ParseException pe) {
			JOptionPane.showMessageDialog(this, label.getText() + " nhap sai dinh dang ngay thang (vi du: 22/07/2012)!");
			return false;
		}
	}

	public static void main(String argsp[]) {

		UserForm form = new UserForm();

		//Luu y cho nay dung WindowAdapter de implement closing event khong can phai dung interface
		form.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);

			}


		}); 

		form.setVisible(true);
	}

}
