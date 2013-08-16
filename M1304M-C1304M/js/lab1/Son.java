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
package lab1;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 16, 2013  
 */
public class Son extends Uncle implements GranPa {

  @Override
 public void sayhello() {
    // TODO Auto-generated method stub
    
    
  }
  
  
  void checkException() throws CheckedException {
    System.out.println("un catch checked exception already then  need to throws");
    throw new CheckedException() ;
  }
  
  
  void checkException(String a)  {
    try {
      throw new CheckedException() ;
    } catch (Exception e) {
      System.out.println("catch checked exception already then no need to throw");
    }
   
  }

   void methoda(int a){
     System.out.println("runtime exeption no need to throw either catch");
     throw new Unchecked1Exception();
    }
   
   
   void methoda(float a) throws Unchecked2Exception {
     System.out.println("runtime exeption throws is no mater");
     throw new Unchecked2Exception();
    }
   
   void methoda(String a) throws IllegalArgumentException{
     throw new IllegalArgumentException();
    }
   
   public static void main(String[] args){
     
     Son s = new Son() ;
     s.methoda(1);
     s.methoda("1");
     s.methoda(0.f);
     
     try {
    // TODO has to block with try catch
       s.checkException() ;
    } catch (CheckedException e) {
      
      e.printStackTrace();
    }
     s.checkException("with try catch in side") ;
     
   }
}
