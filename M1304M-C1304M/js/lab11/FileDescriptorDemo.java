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

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

public class FileDescriptorDemo {
  public static void main(String[] args) throws IOException {

    FileDescriptor fd = null;
    FileInputStream fis = null;
    boolean bool = false;

    try{
      // create new file input stream
      fis = new FileInputStream(args[0]);

      // get file descriptor
      fd = fis.getFD();

      // tests if the file is valid
      bool=fd.valid();

      // prints
      System.out.println("Valid file: "+bool);
      if(fd.valid()) {
        FileInputStream inputFile2 = new FileInputStream(fd);
        System.out.println("new file input stream " + inputFile2.available());
      }
    }catch(Exception ex){
      // if an I/O error occurs
      ex.printStackTrace();
    }finally{

      // releases all system resources from the streams
      if(fis!=null)
        fis.close();
    }
  }
}