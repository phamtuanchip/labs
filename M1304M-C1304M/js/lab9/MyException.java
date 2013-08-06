package lab9;

public class MyException extends Exception {
	String massage;
	public MyException(String msg){
		massage = msg;
	}
	@Override
	public String getMessage() {
		 return massage;
	}

}
