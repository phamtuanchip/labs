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

import java.util.Enumeration;
import java.util.Properties;
class ProductVendors
{
  public static void main(String args[])
  {
      Properties proProducts = new Properties();
      String strTemp;
    proProducts.put("Turbo C","Borland");
    proProducts.put("Flash MX","Macromedia");
    proProducts.put("Java","Sun");
    proProducts.put("3D Studio Max","Discreet");
    proProducts.put("PhotoShop","Adobe");
    proProducts.put("OS/2","IBM");
    // Display properties without using an iterator
    // or enumerator 
    proProducts.list(System.out);
    // Search for non-existing element
    strTemp = proProducts.getProperty("Oracle 9i","Not Present");
    if (strTemp.trim().equals("Not Present"))

      System.out.println("\nOracle 9i does not exist in the Products list"); 
    
      // Copy the contents of proProducts to 
      // new Properties object
      Properties proClone = new Properties();
      Enumeration enuProductNames = proProducts.propertyNames();
    
      String strKey = "";
    
      while (enuProductNames.hasMoreElements())
      {
            strKey = (String)enuProductNames.nextElement();
        proClone.setProperty(strKey,proProducts.getProperty(strKey));
          }
      // Copying done
      System.out.println("\nDisplaying cloned Properties object :\n");
      
      proClone.list(System.out);
    }
  }
