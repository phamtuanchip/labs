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

import java.util.Comparator;

class Dog implements Comparator<Dog>, Comparable<Dog>{
  private String name;
  private int age;
  Dog(){
  }

  Dog(String n, int a){
    name = n;
    age = a;
  }

  public String getDogName(){
    return name;
  }

  public int getDogAge(){
    return age;
  }

  // Overriding the compareTo method
  public int compareTo(Dog d){
    return (this.name).compareTo(d.name);
  }

  // Overriding the compare method to sort the age 
  public int compare(Dog d, Dog d1){
    return d.age - d1.age;
  }
}
