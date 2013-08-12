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
public class GreedyQuantifiers
{
  public static void main(String args[])
   {
    Pattern ptnSearch;
    Matcher mtrText;
    
    /* Create the longest pattern 
       which begins with the alphabet e 
       followed by any characters and ends with d
     */
    ptnSearch = Pattern.compile("e.+d");  
    
    mtrText = ptnSearch.matcher("Kindly extend your end hours of study ");
    while(mtrText.find())
    {
      System.out.println("\"" + mtrText.group() + "\"");
      System.out.println(" found at starting index: " + mtrText.start() + " and ending index: " + mtrText.end());
    }
  }
}
