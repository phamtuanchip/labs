public class Developer extends Employee{

public String TeamName;
 public String JobTitle;

	public Developer(){
    
    System.out.println("Tao Developer") ;
    
  }

	public Developer (String id, String name, String dateOfbirth, long salary, String TeamName, String JobTitle){
	super(id, name, dateOfbirth,salary);
	this.TeamName = TeamName;
	this.JobTitle = JobTitle; 
	}

 public void printInfo() { 
	 
 	System.out.println("TeamName: " + TeamName) ;
	System.out.println("JobTitle: " + JobTitle) ;	
	  super.printInfo(); 
 }
public void getOwner() { 
	System.out.print("This is Developer");
}
 

public static void main(String[] args){
	Developer vanA = new Developer("1","a","25011981",1000,"sn","admin");
	vanA.printInfo();
	vanA.getOwner();
  }
}
