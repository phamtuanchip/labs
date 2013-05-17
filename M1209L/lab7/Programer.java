import java.util.Scanner;


public class Programer extends Employee {



  private double salary ;

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary){
    this.salary = salary;
  }

  public void setSalary(String salary) throws NumberFormatException {
    this.salary = Double.parseDouble(salary) ;
  }

  public void inputFields() throws NameTooLongException {
    super.inputFields() ;
    boolean isValidate = false ;
    System.out.println("Input salary:");
    while (! isValidate) {
      try {
        Scanner s = new Scanner(System.in) ;
        double salary = s.nextDouble() ;
        setSalary(salary);
        isValidate = true ;
      } catch (NumberFormatException e) {
        System.out.println("Input wrong format of salary: !");
        isValidate = false;
      }
    }
  }
  
  public double calculateSalary() {
    try {
      return salary + 5000*(calculateWorkingYear()) ;
    } catch (TooYoungException e) {
      return 0;
    } finally {
      return salary ;

    }
  }


}
