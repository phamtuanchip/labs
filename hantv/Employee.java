public class Employee {
  
  public String id;
 public String name;
  public String dateOfbirth;
  public long salary;

	 public Employee(){
    
    System.out.println("Tao Employee") ;
    
  }
 
	public Employee (String id, String name, String dateOfbirth, long salary){
	this.id = id;
	this.name = name;
	this.dateOfbirth = dateOfbirth;
	this.salary = salary; 
	}

	public void printInfo() {
    System.out.println("ID: " + id) ;
    System.out.println("Name: " + name) ;
	System.out.println("DOB: " + dateOfbirth) ;
	System.out.println("Salary: " + salary) ;
  }

 public static void main(String[] args){
	Employee employee = new Employee();
	employee.printInfo();
  }
  
}
