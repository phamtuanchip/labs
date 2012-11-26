/*
 * Copyright (C) 2003-2012 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package aiti.lab3;

import java.util.Date;
import java.util.Scanner;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Nov 19, 2012  
 */
public class Employee {
  
  private String name ;
  private String address;
  private boolean isMale;
  private Date dob;

  
  public Employee(){
    
    System.out.println("==== Create Employee ====") ;
    
  }
  
  public Employee(String name, String address, boolean isMale, Date dob){
    this.name = name;
    this.address = address;
    this.isMale = isMale;
    this.dob = dob;  
  }
  
  public void display() {
    System.out.println("Name: " + name) ;
    System.out.println("is male: " + isMale) ;
    System.out.println("Age: " + (new Date().getYear() - dob.getYear())) ;
    System.out.println("Address: " + address) ;
  }

  public void inputFields() {
    Scanner s = new Scanner(System.in) ;
    System.out.println("Your name: ") ;
    name = s.nextLine();
    
    System.out.println("Your address:");
    address = s.nextLine();
    
    System.out.println("Male or Female?: y/n");
    isMale  ="Y".equalsIgnoreCase(s.next());

    boolean isNotWell = true ;
    while(isNotWell) {
      System.out.println("Your DOB:");
      try {
        dob = new Date(s.next());
        isNotWell = false ;
      } catch (Exception e) {
        isNotWell = true;
      }
    }
  }
    
  public void inputFields(String name, String address, boolean isMale, Date dob) {
    this.name = name;
    this.address = address;
    this.isMale = isMale;
    this.dob = dob;   
  }

  

  public static void main(String[] args){

    /*
    Employee e = new Employee() ;
    e.inputFields() ;
    e.display();
    
    Programmer p = new Programmer();
    p.inputFields();
    p.display();
    */
    Manager m = new Manager() ;
    m.inputFields();
    m.display();
  }


}
