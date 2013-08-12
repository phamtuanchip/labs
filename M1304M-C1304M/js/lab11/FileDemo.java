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

import java.io.FileInputStream;
import java.io.InputStream;

import org.omg.CORBA.Environment;

class FileDemo
{
  public static void main(String args[]) throws Exception
  {
    int size;
    InputStream f = new FileInputStream(args[0]);
    System.out.println("Bytes available to read : " + (size = 
        f.available()));
    char str[] = new char[200];
    for(int count = 0;count < size;count++)
    {
      str[count] = ((char)f.read());
      System.out.print(str[count]);
    }
    System.out.println("");
    f.close();
  }
}

