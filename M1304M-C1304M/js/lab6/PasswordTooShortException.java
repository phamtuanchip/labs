package layout;

public class PasswordTooShortException extends Exception {
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Mat khau phai dai hon hoac bang 8 ky tu!";
	}

}
