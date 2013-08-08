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
package lab9;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 2, 2013  
 */
public class Convert implements Cloneable {
  
  
  
  
  public static void main(String[] args){
    
    Integer i = new Integer(10);
    Long l = new Long(5);
    
    System.out.println("Auto boxing i + l : " + (i + l)) ;
    
    
  
    Convert cv1 = new Convert() ;
    Convert cv2 = new Convert() ;
    
    
    System.out.println("cv1.hashCode() == cv1.hashCode()- " + cv1.hashCode() +" : "+ cv2.hashCode());
    
    System.out.println("cv1  == cv2 " + (cv1 == cv2));
    
    System.out.println("cv1.equals(cv2) " + (cv1.equals(cv2)));
    
    cv1 = cv2 ;
     
    System.out.println("cv1.hashCode() == cv1.hashCode()- " + cv1.hashCode() +" : "+ cv2.hashCode());
    
    System.out.println("cv1  == cv2 " + (cv1 == cv2));
    
    System.out.println("cv1.equals(cv2) " + (cv1.equals(cv2)));
    
   
    try {
      cv1 = (Convert)cv2.clone() ;
    } catch (CloneNotSupportedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    System.out.println("cv1.hashCode() == cv1.hashCode()- " + cv1.hashCode() +" : "+ cv2.hashCode());
    
    System.out.println("cv1  == cv2 " + (cv1 == cv2));
    
    System.out.println("cv1.equals(cv2) " + (cv1.equals(cv2)));
    
    String a1 = new String("A1");
    String a2 = "A1";
    
    String a3 = a1 ;
    
    System.out.println("a1 == a2 " + (a1 == a2));
    
    System.out.println("a1.equals(a2)" + a1.equals(a2));
    
    System.out.println("a1.hashCode() == a1.hashCode()- " + a1.hashCode() +" : "+ a2.hashCode());
    
    System.out.println("a1 == a3 " + (a1==a3) + " hashCode " + a1.hashCode() +" : "+ a2.hashCode());
    
    a3 = a2;
    System.out.println("a2 == a3 " + (a2==a3));
    
    a3 = new String("A1");
    
    System.out.println("a2 == a3 " + (a2==a3) + " hashCode " + a1.hashCode() +" : "+ a2.hashCode());
    
  }
  
  

}
