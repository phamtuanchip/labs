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
public class Run {

  public static void main(String[] args) {
    Employee emp = new Employee() ;
    try {
      emp.inputFields() ;
    } catch (Exception e) {
      System.out.println("Error when input " + e.getMessage());
    }
    try {
      emp.displayInfo() ;
    } catch (TooYoungException e) {
      System.out.println("Error when display, You are too yourng to work! " + e.getMessage());
    } catch (Exception e) {
      System.out.println("Error when display " + e.getMessage());
    }

  }
}
