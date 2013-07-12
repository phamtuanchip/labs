import java.util.Date;
import java.util.Scanner;


public class Customer {

	String name;
	String dob;
	String address;
	String email;
	String phone;
	
	public void AccepInformation(){
		System.out.print("Customer name: ");
		name = new Scanner(System.in).nextLine();
		System.out.print("\n Date of birth: ");
		dob = new Scanner(System.in).nextLine();
		System.out.print("\n Adress: ");
		address = new Scanner(System.in).nextLine();
		System.out.print("\n Email: ");
		email = new Scanner(System.in).nextLine();
		System.out.print("\n phone: ");
		phone = new Scanner(System.in).nextLine();
	}
	
	public void DisplayInformation(){
		System.out.println("Customer name: "+ name);
		System.out.println("Customer dob: "+ dob);
		System.out.println("Customer address: "+ address);
		System.out.println("Customer email: "+ email);
		System.out.println("Customer phone: "+ phone);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Customer c = new Customer();
		c.AccepInformation();
		c.DisplayInformation();
		
		

	}

}
