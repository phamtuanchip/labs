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
public class XeDap extends Xe {
  String color ;
  
  
  public XeDap(){
    super("Bycle");
  }
  //overloardding
  public XeDap(String name, String color){
    super(name);
    this.color = color;    
  }

  
  
  //overloardding
  public void inputInfor(String name, String color, int numbOfWheel, int width, int height) {
    // TODO Auto-generated method stub
    super.inputInfor(name, numbOfWheel, width, height);
    this.color = color;
  }
  
  @Override
  public void displayInformation() {
    super.displayInformation();
    System.out.println("I have color " + color);
  }
  
   
}
