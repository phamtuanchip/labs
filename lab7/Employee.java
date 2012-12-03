import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

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

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Dec 3, 2012  
 */
public class Employee {

  private String name ;
  private Date dob;
  private boolean sex;


  public Employee() {


  }

  public Employee(String name, Date date, boolean isMale) throws NameTooLongException{
    setName(name);
    setDob(date);
    setSex(isMale);

  }

  public String getName() {
    return name;
  }

  public void setName(String name) throws NameTooLongException {
    if(name.length() > 10) throw new NameTooLongException();
    this.name = name;
  }

  public void inputFields() throws NameTooLongException {

    System.out.println("Input name: " );
    boolean inValidate = true ;
    while(inValidate) {
      Scanner s = new Scanner(System.in) ;
      String name = s.nextLine() ;
      try {
        setName(name) ;
        inValidate = false ;
      } catch (NameTooLongException e) {
       // e.printStackTrace() ;
        inValidate = true ;
        System.out.println("Name too long pleae input name again: " );
      }
    }
    
    System.out.println("Input Date of birth: dd/MM/yyyy " );
    inValidate = true ;
    while(inValidate) {
      Scanner s = new Scanner(System.in) ;
      String date = s.nextLine() ;
      try {
        setDob(date) ;
        inValidate = false;
      } catch (ParseException e) {
        inValidate = true ;
        System.out.println("Date time format wrong: dd/MM/yyyy  " );
      }
    }

    System.out.println("You are male or female: y/n ?" );
    Scanner s = new Scanner(System.in) ;
    String answer = s.next() ;
    setSex("y".equalsIgnoreCase(answer)) ;

  }

  public void displayInfo() throws TooYoungException {
    System.out.println("Name : " + name);
    System.out.println("DOB : " + new SimpleDateFormat("dd/MM/yyyy").format(dob));
    System.out.println("Working years : " + calculateWorkingYear());
    System.out.println("Sex  : " + sex);
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public void setDob(String input) throws ParseException {
    SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy") ;
    this.dob = sf.parse(input) ;

  }

  public boolean isSex() {
    return sex;
  }

  public void setSex(boolean sex) {
    this.sex = sex;
  }

  public int calculateWorkingYear() throws TooYoungException {
    int workingYears = new Date().getYear() - dob.getYear() ;
    if(workingYears <= 16) throw new TooYoungException() ;
    return workingYears;
  }

}
