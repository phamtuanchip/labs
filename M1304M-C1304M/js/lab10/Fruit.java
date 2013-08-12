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

public class Fruit implements Comparable<Fruit>{

  private String fruitName;
  private String fruitDesc;
  private int quantity;

  public Fruit(String fruitName, String fruitDesc, int quantity) {
    super();
    this.fruitName = fruitName;
    this.fruitDesc = fruitDesc;
    this.quantity = quantity;
  }

  public String getFruitName() {
    return fruitName;
  }
  public void setFruitName(String fruitName) {
    this.fruitName = fruitName;
  }
  public String getFruitDesc() {
    return fruitDesc;
  }
  public void setFruitDesc(String fruitDesc) {
    this.fruitDesc = fruitDesc;
  }
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int compareTo(Fruit compareFruit) {

    int compareQuantity = ((Fruit) compareFruit).getQuantity(); 

    //ascending order
    return this.quantity - compareQuantity;

    //descending order
    //return compareQuantity - this.quantity;

  } 
}
