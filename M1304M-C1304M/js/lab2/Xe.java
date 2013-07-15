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

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 15, 2013  
 */
public class Xe {
  
  String name;
  int numbOfWheel;
  int width;
  int height;
  
  public String getName(){
    return name;
  }
  
  public int getNumberOfWheel(){
    return numbOfWheel;
  }
  
  public int getWidth(){
    return width;
  }
  
  public int getHeight(){
    return height;
  }
  
  
  public void inputInfor(String name,int numbOfWheel, int width, int height){
    this.name = name;
    this.numbOfWheel = numbOfWheel;
    this.width = width;
    this.height = height;
  }
  
  public void displayInformation(){
    System.out.println("I am a " + name);  
    System.out.println("I have " + numbOfWheel + " wheels");
    System.out.println("I am tall about " + height);
    System.out.println("I am fat about  " + width);  
  }
  
  public Xe() {
    System.out.println("This use to created object !");  
  }
  
  public Xe(String name){
    this.name = name;
  }
  
  public Xe(String name, int numberOfWheel, int width, int height) {
    System.out.println("This use to created object with default value in paramesters !");  
    this.name = name;
    this.numbOfWheel = numberOfWheel;
    this.width = width;
    this.height = height;
  }
  
  

}
