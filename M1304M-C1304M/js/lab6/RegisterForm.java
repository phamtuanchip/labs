package layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;

import javax.swing.JOptionPane;

public class RegisterForm extends Frame {

	CardLayout card = new CardLayout();
	String card1Name = "card1";
	String card2Name = "card2";
	String card3Name = "card3";
	Panel mainPanel = new Panel();
	Panel card1 = new Panel();
	Label email = new Label("E Mail:");
	TextField emailInput = new TextField(30);
	Panel card2 = new Panel();

	Label name = new Label("User Name:");
	TextField nameInput = new TextField(30);
	Label pass = new Label("Password:");
	TextField passInput = new TextField(30);
	Label repass = new Label("Confirm password:");
	TextField repassInput = new TextField(30);

	Panel card3 = new Panel();

	Label fname = new Label("Name:");
	TextField fnameInput = new TextField(30);
	Label dob = new Label("DOB:");
	Choice dobChoice = new Choice();
	Label sex = new Label("Sex:");
	CheckboxGroup g = new CheckboxGroup();
	Checkbox male = new Checkbox("Male",g,true);
	Checkbox female = new Checkbox("Female",g,false);
	Label address = new Label("Address:");
	TextArea addressInput = new TextArea(4, 30);

	public Panel buttons = new Panel();
	Button next = new Button("Next");
	Button back = new Button("Back");

	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	int maxLength = 8;


	// Contructor thuong dung de khoi tao toan bo cac component can thiet, tao layout va add component
	public RegisterForm(){

		super("Register Form");
		setSize(500,500);
		// Main panel se dung de holder 3 panel con theo kieu cardlayout
		mainPanel.setLayout(card);
		//Set name cho panel de ti nua ta co the lay duoc panel nao dang duoc show trong card
		card1.setName(card1Name);
		card1.setLayout(new FlowLayout());
		card1.add(email);
		card1.add(emailInput);
		mainPanel.add(card1, card1Name);

		card2.setName(card2Name);
		card2.setLayout(gb);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		//Set ky tu khi gho vao se chuyen thanh dau * 
		passInput.setEchoChar('*');
		repassInput.setEchoChar('*');

		//Goi ham tu viet de set lai cac vi tri compoent tuong ung 
		addComponent(card2, name,0,1,1,1);
		addComponent(card2, nameInput,0,2,2,1);
		addComponent(card2, pass,2,1,1,1);
		addComponent(card2, passInput,2,2,2,1);
		addComponent(card2, repass,4,1,1,1);
		addComponent(card2, repassInput,4,2,2,1);
		mainPanel.add(card2, card2Name);
		//Khoi tao choice cho so nam tu 1900 den 2013
		for(int i = 1990 ; i <= 2013; i++) {
			//Convert tu int ra String bang ham String.valueOf();
			dobChoice.add(String.valueOf(i));
		}
		card3.setLayout(gb);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		addComponent(card3, fname,0,1,1,1);
		addComponent(card3, fnameInput,0,2,2,1);
		addComponent(card3, dob,2,1,1,1);
		addComponent(card3, dobChoice,2,2,2,1);
		addComponent(card3, sex,4,1,1,1);

		Panel bound = new Panel();
		bound.setLayout(new FlowLayout());
		bound.add(male);
		bound.add(female);
		addComponent(card3, bound,4,2,2,1);
		addComponent(card3, address,6,1,1,1);
		addComponent(card3, addressInput,6,2,2,1);
		mainPanel.add(card3, card3Name);


		add(mainPanel, BorderLayout.CENTER);
		buttons.add(back);
		buttons.add(next);
		add(buttons, BorderLayout.SOUTH);

		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				next();

			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				back();

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
		gbc.gridx = col;
		gbc.gridy = row;

		gbc.gridwidth = ncol;
		gbc.gridheight = nrow;

		gb.setConstraints(comp,gbc);
		card.add(comp);
	}

	//ham valid de kiem tra cac truong can thiet 
	boolean valid(){
		for (Component comp : mainPanel.getComponents()) {
			if (comp.isVisible() == true) {
				if(comp.getName().equals(card1Name)) {
					//Nguoi dung dang o card1 panel 1 dang visiable 
					try {
						return isValid(emailInput, email) && isEmailValid(emailInput, email);
					} catch (FieldEmptyException e) {
						JOptionPane.showMessageDialog(this, e.getMessage()) ;
						return false ;
					} catch (EmailInvalidException e) {
						JOptionPane.showMessageDialog(this, e.getMessage()) ;
						return false;
					}

				} else if(comp.getName().equals(card2Name)) {
					try {
					return (isValid(nameInput, name) && isValid(passInput, pass) && isEnough(passInput, pass) 
							&& isValid(repassInput, repass) 
							&& isEnough( repassInput, repass)
							&& isSame(passInput, repassInput, pass, repass));
					}catch (FieldEmptyException e) {
						JOptionPane.showMessageDialog(this, e.getMessage()) ;
						 return false ;
					} catch (PasswordTooShortException e) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(this, e.getMessage()) ;
						 return false ;
					} catch (PasswordNotSameException e) {
						JOptionPane.showMessageDialog(this, e.getMessage()) ;
						// TODO: handle exception
						 return false ;
					}
				} else if(comp.getName().equals(card3Name)) {

				}
				break;
			}
		}
		return false;
	}


	boolean isValid(TextField feild, Label label ) throws FieldEmptyException {
		if(feild.getText() == null || feild.getText().trim().isEmpty()) {
			throw new FieldEmptyException();
		} else {
			return true;
		}
	}


	boolean isEmailValid(TextField feild, Label label ) throws EmailInvalidException {
		if(feild.getText() != null && !feild.getText().trim().isEmpty()) {
			if(! new EmailValidator().validate(feild.getText())) 
				throw new EmailInvalidException();
		} 
		return true;

	}



	boolean isEnough(TextField feild1, Label label1) throws PasswordTooShortException {
		if( feild1.getText().length() > maxLength) {
			return true;
		} else {
			throw new PasswordTooShortException();
		}


	}

	boolean isSame(TextField feild1, TextField feild2, Label label1, Label label2 ) throws PasswordNotSameException {
		if( feild1.getText().length() > maxLength && feild2.getText().length() > maxLength) {
			return true;
		} else {
			throw new PasswordNotSameException();
		}
	}

	boolean isMatch(TextField feild1, TextField feild2, Label label1, Label label2 ) {
		if(feild1.getText().equals(feild2.getText()) ) {
			return true;
		} else {
			JOptionPane.showMessageDialog(this, label1.getText() + " phai giong voi " + label2.getText());
		}
		return false;

	}



	void next(){
		if(valid()) 
			//card lay out co san ham next va previous ta dung luon
			card.next(mainPanel);
	}

	void back(){
		card.previous(mainPanel);
	}

	public static void main(String argsp[]) {

		RegisterForm form = new RegisterForm();
		form.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		form.setVisible(true);
	}

}
