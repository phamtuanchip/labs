import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;

public class LoginExample extends MIDlet implements CommandListener{
	private Display display;
	private TextField userName,password;
	public Form form;
	private Command login,cancel;
	private Image img, imge, img2;
			
	public LoginExample() {
		form = new Form("Sign in");
		userName = new TextField("LoginID:", "", 30, TextField.ANY);
		password = new TextField("Password:", "", 30, TextField.PASSWORD);
		cancel = new Command("Cancel", Command.CANCEL, 2);
		login = new Command("Login", Command.OK, 2);
		try{
			img = Image.createImage("/logo.png");
			imge = Image.createImage("/front_left1_bad.png");
			img2 = Image.createImage("/Congratulations-1.png");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}		
	}

 	public void startApp() {
		display = Display.getDisplay(this);
		try{form.append(img);}catch(Exception e){}
		form.append(userName);
		form.append(password);
		form.addCommand(cancel);
		form.addCommand(login);
		form.setCommandListener(this);
		display.setCurrent(form);
	}

	public void pauseApp() {}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	public void validateUser(String name, String password) {
		if (name.equals("sandeep") && password.equals("sandeep")) {
			showMsg();
		} else {
			tryAgain();
		}
	}  

	public void showMsg() {
		Alert success = new Alert("Login Successfully", "Your Login Process is completed!", img2, AlertType.INFO);
		success.setImage(img2);
		userName.setString("");
		password.setString("");
		display.setCurrent(success, form);		
	}

	public void tryAgain() {
		Alert error = new Alert("Login Incorrect", "Please try again", imge, AlertType.ERROR);
		error.setTimeout(900);
		error.setImage(imge);
		userName.setString("");
		password.setString("");
		display.setCurrent(error, form);
	}
	
	public void commandAction(Command c, Displayable d) {
		String label = c.getLabel();
		if(label.equals("Cancel")) {
			destroyApp(true);
		} else if(label.equals("Login")) {
			validateUser(userName.getString(), password.getString());
		} 
	}
}