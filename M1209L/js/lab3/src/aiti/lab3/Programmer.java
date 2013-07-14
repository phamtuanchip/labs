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
public class Programmer extends Employee{

  public Date startDate ;
  public double salary ;
  
 public Programmer(){
    
    System.out.println("==== Create Programmer ====") ;
    
  }
  
  public void display() {
    super.display();
      System.out.println("Working years: " + (new Date().getYear() - startDate.getYear())) ;
      System.out.println("Salary : " + salary) ;
  }
  
  public void inputFields() {
    super.inputFields();
    Scanner sc = new Scanner(System.in);
    boolean isNotWell = true ;
    while(isNotWell) {
      System.out.println("Start working: ") ;
      try {
        startDate = new Date(sc.next());
        isNotWell = false ;
      } catch (Exception e) {
        isNotWell = true;
      }
    }
    System.out.println("Salary : ") ;
    salary = sc.nextDouble();
    
  }
  
}
