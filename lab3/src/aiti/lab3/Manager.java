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

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Nov 19, 2012  
 */
public class Manager extends Programmer {

  public double bonus ;

  public Manager(){
    System.out.println("==== Create Manager ====") ;
  }

  public void display() {

    System.out.println("==== Display Manager ====") ;
    super.display();
  }

  public void inputFields() {
    super.inputFields();
    int workingYears = new Date().getYear() - startDate.getYear() ;
    if( workingYears > 3 && workingYears < 5) {
      bonus  = 10000 ;
    } else if (workingYears >= 5) bonus = 15000;
    salary = salary + bonus;
  }
}
