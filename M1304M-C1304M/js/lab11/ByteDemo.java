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
import java.io.*;

class ByteDemo
{
  public static void main (String []args)
  {
    String str = "Jack and Jill went up the hill";
    byte[] b = str.getBytes();
    ByteArrayInputStream bais = new ByteArrayInputStream(b,0,4);
    int ch;
    while((ch = bais.read()) != -1)
      System.out.print((char) ch);
    System.out.println();
    bais.reset();     //using reset ( ) method and again reading
    ch = 0;
    while((ch = bais.read()) != -1)
      System.out.println((char) ch);
  }
}