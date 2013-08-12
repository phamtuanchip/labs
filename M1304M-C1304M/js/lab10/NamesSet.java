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

import java.util.HashSet;
import java.util.Set;
public class NamesSet
{
    public static void main(String args[])
  {
      Set objSet = new HashSet();
      objSet.add("Patrick");
      objSet.add("Bill");
      objSet.add("Gene");
      objSet.add("Daniel");
      objSet.add("Claire");
    System.out.println("Contents of the set :");
    System.out.println(objSet);
    
      System.out.println("Size of the set : " + objSet.size());
    
    System.out.println("\nContents of the set after adding 2 elements :");
      objSet.add("Rubio");
      objSet.add("Yang Sun");
      System.out.println(objSet); 
      System.out.println("Size of the set : " + objSet.size());
  }
}
