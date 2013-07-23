import java.util.Date;

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
 * Jul 16, 2013  
 */
public class Manager extends Employee {
  
  short responsibilitySalary ;

  @Override
  public String getId() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getAddress() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public short getAge() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Date getDOB() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setId(String id) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setName(String name) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setAddress(String address) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setDOB(Date d) {
    // TODO Auto-generated method stub
    
  }

  @Override
  double getBonusBylevel() {
    // TODO Auto-generated method stub
    return bonus*responsibilitySalary;
  }

  @Override
  double getSalary() {
    // TODO Auto-generated method stub
    return super.getSalary();
  }
  
  

}
