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

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
public class WordsCount 
{
  public static void main(String args[]) 
  {
    if(args.length < 1)
    {
      System.out.println("Usage : java WordsCount <sample string>");
      System.exit(0);
    }
    
      Map WordList = new HashMap();
     
    for(int count = 0;count < args.length; count++) 
    {
        // Get the next word
      String key = args[count];
      
      // Get the frequency of the word 
      // referred by "key"
       Integer frequency = (Integer)WordList.get(key);
       /* If the word does not exists in the map
       then initialize frequency to 1
       else increment it by 1 
     */
    if(frequency == null) 
    {
      frequency = new Integer(1);
    } 
    else 
    {
            int value = frequency.intValue();
            frequency = new Integer(value + 1);
      }
          
    // Update the frequency for the word 
    // referred by "key"
    WordList.put(key, frequency);
    }

  // Display the words and its
  // corresponding frequency
    Map sortedWordList = new TreeMap(WordList);
  System.out.println(sortedWordList);
  }
}
