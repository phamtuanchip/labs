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

import java.util.Timer;

class TimerDemo
{
  public static void main(String [] args)
  {
    Task t = new Task();
    
    /* Schedule the timer to perform 
     task t every 500 milliseconds.
     Start the timer after 1000 milliseconds
    */
    Timer tmr = new Timer();
    tmr.schedule(t, 1000, 500);
    try
    {
      Thread.sleep(5000);
      }
    catch (InterruptedException e)
      {
    }
    System.out.println("Beeping over");
    
    // Stop the timer
    tmr.cancel();
  }
}
