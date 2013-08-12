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

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class SearchPattern
{
  public static void main(String args[])
  {
    String strText = "Oak and Java";
    System.out.println("Original String : " + strText);

    System.out.println("Search pattern : " + strText);

    Pattern ptnSearch = Pattern.compile(strText);   
    Matcher mtrText = ptnSearch.matcher(strText);

    if(mtrText.matches()) 
      System.out.println("Exact match found for " + strText);
    else
      System.out.println("Exact match not found");
    mtrText = ptnSearch.matcher("Java");

    System.out.println("Search pattern : " +"Java");    
    if(mtrText.matches()) 
      System.out.println("Exact match found for " + "Java");
    else
      System.out.println("Exact match not found");
  } 
}
