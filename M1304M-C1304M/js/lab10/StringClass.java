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

import java.util.HashMap;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 9, 2013  
 */
public class StringClass {
  
  public static void main(String[] args){
    String s1 = "chuoi" ;
    String s2 = "chuoi" ;
    
    if("chuoi" == s1 && s2 == "chuoi" && s1==s2) {
      System.out.println("La 1");
    }
      
    String s3 = new String("chuoi") ;
    
    if("chuoi" == s3 && s2 == s3 && s1==s3) {
      System.out.println("La 1 nua !");
    } else {
      System.out.println("La moi !");
    }
    
    s3 = s3.intern();
    
    if("chuoi" == s3 && s2 == s3 && s1==s3) {
      System.out.println("La 1 nua sau khi goi inturn() !");
    } else {
      System.out.println("La moi !");
    }
    
  }

}
