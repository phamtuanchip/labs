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
package lab11;

import java.util.Date;
class TestClass
{
  String str;
  Date dt;
  double db;
  int i;
  TestClass()
  {}
  TestClass( String s, Date d, double d1, int i1)
  {
    str = s;
    dt = d;
    db = d1;
    i = i1;
  }
  public String toString()
  {
    return "name = "+ str + "date : " + dt +" income : " + db +" years of service :  " +i;
  }
}