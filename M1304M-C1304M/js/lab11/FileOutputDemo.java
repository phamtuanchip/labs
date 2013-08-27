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
class FileOutputDemo
{
  public static void main(String args[])
    {
    byte b[] = new byte[80];
    try
    {
      System.out.println("Enter a line to be saved into a file");
          int bytes = System.in.read(b);
      FileOutputStream fos = new FileOutputStream("xyz.txt", true);
       
    fos.write(b,0,bytes);
      System.out.println("Written!");
    }
    catch(IOException e)
    {
      System.out.println("Error creating file!");
    }
    }
}