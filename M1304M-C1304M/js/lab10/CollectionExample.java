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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 9, 2013  
 */
public class CollectionExample {

  public static void main(String[] args){

    System.out.println("Array !");
    ArrayList arr = new ArrayList();

    arr.add("string type");
    arr.add(10);

    System.out.println("capacity " + arr.size());

    System.out.println("1st  " + arr.get(0));
    System.out.println("2st  " + arr.get(1));

    System.out.println("Linked list  !");

    LinkedList larr = new LinkedList();

    larr.add("string type 2");
    larr.add(new Integer(10));

    System.out.println("1st  " + larr.get(0));
    System.out.println("2st  " + larr.get(1));

    larr.addFirst(new Double(1.0));
    System.out.println("after add first  !");
    System.out.println("1st  " + larr.get(0));
    System.out.println("2st  " + larr.get(1));


    System.out.println("Set !");

    int count[] = {34, 22,10,60,30,22};
    Set<Integer> set = new HashSet<Integer>();
    try{
      for(int i = 0; i<5; i++){
        set.add(count[i]);
      }
      System.out.println(set);

      TreeSet sortedSet = new TreeSet<Integer>(set);
      System.out.println("The sorted list is:");
      System.out.println(sortedSet);

      System.out.println("The First element of the set is: "+
          (Integer)sortedSet.first());
      System.out.println("The last element of the set is: "+
          (Integer)sortedSet.last());
    }
    catch(Exception e){

    }

    System.out.println("Generic type !");

    ArrayList arr2 = new ArrayList();

    arr2.add(3);
    arr2.add(10);
    arr2.add(7);

    int tong = 0;
    for(int i =0; i < arr2.size(); i++){
      tong += (Integer)arr2.get(i);

    }
    System.out.println("Tong: " + tong);

    arr2.add("I am a string");

    tong = 0 ;
    try {
      for(int i =0; i < arr2.size(); i++){
        tong += (Integer)arr2.get(i);

      }
    }
    catch (ClassCastException e) {
      System.out.println("Loi xay ra khi ep kieu !");
    }
    System.out.println("Tong: " + tong);
    
    
    System.out.println("Tranh loi ep kieu va tuong minh code !");
    
    ArrayList<Integer> arr3 = new ArrayList<Integer>();
    arr3.add(3);
    arr3.add(10);
    arr3.add(7);
    arr3.add("bon");
    
    //Loi xay ra khi build , compile time
    /*
     * Exception in thread "main" java.lang.Error: Unresolved compilation problem: 
  The method add(Integer) in the type ArrayList<Integer> is not applicable for the arguments (String)

  at lab9.CollectionExample.main(CollectionExample.java:121)

     */
    

  }





}
