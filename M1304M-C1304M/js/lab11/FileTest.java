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

import java.io.File;

class FileTest
{
  static void show(String s)
  {
    System.out.println(s);
  }

  public static void main(String args[])
  {
    File f1 = new File(args[0]);
    show(f1.getName()+(f1.exists()?" exists" : " does not exist"));
    show ("File size   :"+f1.length()+" bytes");
    show ("Is"+(f1.isDirectory()?" a directory":"not a directory"));
    show (f1.getName()+(f1.canWrite()? " is writable" : " is not writable"));
    show(f1.getName()+(f1.canRead()? " is readable" : " is not readable"));
    show("File was last modified :" + f1.lastModified());
  }
}
