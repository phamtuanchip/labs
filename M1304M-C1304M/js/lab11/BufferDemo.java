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
package lab11;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
class BufferDemo
{
  public static void main(String []args) throws IOException
  {
    String str = "Jack & Jill, went up the hill";
    System.out.println("Original String: "+str);
    System.out.println("After replacing '&' with 'and': ");
    byte buf[] = str.getBytes();
    ByteArrayInputStream in = new ByteArrayInputStream(buf);
    BufferedInputStream bis = new BufferedInputStream(in);
    int c; 
    boolean flag = false;
    while((c = bis.read()) != -1)
    {
      switch(c)
      {
      case '&':
        if(!flag)
        {
          bis.mark(5);
          flag = true;
        }
        else
        {
          flag = false;
        }
        break;
      case ' ':
        if (flag)
        {
          flag = false;
          bis.reset();
          System.out.print("and");
        }
        else
        {
          System.out.print ((char) c);
        }
        break;
      default:
        if(!flag)
          System.out.print((char)c);
        break;
      }
    }
  }
}
