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

import java.util.EmptyStackException;
import java.util.Stack;
public class StackDemo
{
  Stack s;

  public StackDemo()
  {
    s = new Stack();
  } 

  void pushItem(String str)
  {
    s.push(str);
    System.out.println("Pushed : " + str);
    System.out.println("Stack has : " + s);
  }
  void popItem()
  {
    String str = (String)s.pop();
    System.out.println("Popped  : " + str);
    System.out.println("Stack has : " + s);
  }
  public static void main (String args[])
  {
    StackDemo objStackDemo = new StackDemo();

    objStackDemo.pushItem("Stevnson");
    objStackDemo.pushItem("Mark Twain");
    objStackDemo.pushItem("S Maugham");
    objStackDemo.pushItem("Shakespeare");
    objStackDemo.pushItem("E Blyton");

    System.out.println();

    objStackDemo.popItem();
    objStackDemo.popItem();
    objStackDemo.popItem();
    objStackDemo.popItem();
    objStackDemo.popItem();

    try
    {
      objStackDemo.popItem();
    }
    catch(EmptyStackException e)
    {
      System.out.println("Exception : Empty Stack");
    }
  }
}
