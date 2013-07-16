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
public class Worker extends Employee {
  
  short level ;

  @Override
  public String getId() {
    // TODO Auto-generated method stub
    return id;
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return name;
  }

  @Override
  public String getAddress() {
    // TODO Auto-generated method stub
    return address;
  }

  @Override
  public short getAge() {
    // TODO Auto-generated method stub
    return age;
  }

  @Override
  public Date getDOB() {
    // TODO Auto-generated method stub
    return dob;
  }

  @Override
  public void setId(String id) {
    // TODO Auto-generated method stub
    this.id = id ;
  }

  @Override
  public void setName(String name) {
    // TODO Auto-generated method stub
    this.name = name ;
    
  }

  @Override
  public void setAddress(String address) {
    // TODO Auto-generated method stub
    this.address = address;
    
  }

  @Override
  public void setDOB(Date d) {
    // TODO Auto-generated method stub
    this.dob = d;
    
  }

  @Override
  double getBonusBylevel() {
    // TODO Auto-generated method stub
    return bonus*level;
  }

}
