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
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

class SerializationDemo {
  public static void main(String [] args)
  {
    try
    {
      TestClass t1 = new TestClass("hello ", new Date(), 500.75, 7);
      System.out.println("the values are : " + t1);
      FileOutputStream fos = new FileOutputStream("text1");
      ObjectOutputStream out1 = new ObjectOutputStream(fos);
      out1.writeObject(t1);
      out1.flush();
      out1.close();
    }
    catch(Exception e)
    {
      System.exit(0);
    }
    try
    {
      TestClass test = new TestClass();
      //System.out.println("the values are : " + t1);
      FileInputStream fis = new FileInputStream("text1");
      ObjectInputStream in1 = new ObjectInputStream(fis);
      test = (TestClass)in1.readObject();
      in1.close();
      System.out.println(test.toString());
    }
    catch(Exception e)
    {
      System.exit(0);
    }
  }
}