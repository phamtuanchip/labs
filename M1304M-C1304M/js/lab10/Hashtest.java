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

import java.util.*;
class Hashtest 
{
  public static void main(String args[])
  {
      Hashtable htblStudents = new Hashtable();
      Enumeration enuNames;
      String strName;
      htblStudents.put("Tony",new String("2001"));
      htblStudents.put("Cathy",new String("2002"));
      htblStudents.put("Michael",new String("2002"));
      htblStudents.put("Priscilla",new String("2001"));
      htblStudents.put("Mark",new String("2001"));
      enuNames = htblStudents.keys();
      while(enuNames.hasMoreElements())
      {
      strName = (String)enuNames.nextElement();
      System.out.println(strName + " completed graduation in " + htblStudents.get(strName) + "\n");
      }
  }
}
