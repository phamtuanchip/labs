public class Manager extends Developer{

public long Bonus;
	
	public Manager(){
    
    System.out.println("Tao Manager") ;
    
  }
	
	public Manager (String id, String name, String dateOfbirth, long salary, String TeamName, String JobTitle, long Bonus){
	super (id, name, dateOfbirth, salary, TeamName, JobTitle);	
	this.Bonus = Bonus;
	
	}
 public void printInfo() { 
	System.out.print("Salary + Bonus = " + (salary + Bonus));
	
	  super.printInfo();
 }
public void getOwner() { 
	System.out.print("This is Manager");
}
 

public static void main(String[] args){
	Manager vanB = new Manager("1","a","25011981",1000,"sn","admin",1000);
	vanB.printInfo();
	vanB.getOwner();
  }
}
