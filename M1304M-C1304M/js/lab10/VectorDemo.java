/*
 * Copyright (C) 2003-2013 eXo Platform SAS.
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
package lab10;

import java.util.Vector;
public class VectorDemo
{
  public static void main (String args[])
  {
    Vector v = new Vector();
    v.addElement("Jade");
    v.addElement("Topaz");
    v.addElement("Turquoise");
    v.addElement("Emerald");
    v.insertElementAt("Precious Stones",0);
    v.insertElementAt("Opal",4);
    System.out.println("Contents of Vector :");
    int count = 0;
    while(count < v.size())
    {
      System.out.print(v.elementAt(count));
      count++;
      if(count < v.size())
        System.out.print(", ");
      
    }
    System.out.println("\nSize : "+ v.size());
    
    v.removeElement("Topaz");
  
    System.out.println("\nContents of Vector after removing Topaz :");
    count = 0;
    while(count < v.size())
    {
      System.out.print(v.elementAt(count));
      count++;
      if(count < v.size())
        System.out.print(", ");
  
    }
    System.out.println("\nSize : " + v.size());
    System.out.println("\nFirst Element = " + v.firstElement());
    System.out.println("Default Capacity = " + v.capacity());
    System.out.println("Last Element = " + v.lastElement());
  }
}
