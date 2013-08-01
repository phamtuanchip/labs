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
package lab8;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 1, 2013  
 */
public class ThreadDemo implements Runnable


{
  String name;
  Thread objTh;
  ThreadDemo(String str)
  {
    name = str;
    objTh = new Thread(this, name);
    System.out.println("New Threads are starting : " + objTh);
    objTh.start();
  }
  public void run()
  {
    try
    {
      for (int count = 0;count < 2;count++)
      {
        System.out.println(name + " : "+count);
        objTh.sleep(1000);
      }
    }
    catch(InterruptedException e)
    {
      System.out.println(name + "  interrupted");
    }
    System.out.println(name + "  exiting");
  }



  public static void main(String [] args)
  {
    ThreadDemo Objnew1 = new ThreadDemo("one");
    ThreadDemo Objnew2 = new ThreadDemo ("two");
    ThreadDemo Objnew3 = new ThreadDemo ("three");
    System.out.println("First thread is alive :" + Objnew1.objTh.isAlive());
    System.out.println("Second thread is alive :" + Objnew2.objTh.isAlive());
    System.out.println("Third thread is alive :" + Objnew3.objTh.isAlive());
    try
    {
      System.out.println("I am in the main and waiting for the threads to finish");
      Objnew1.objTh.join();
      Objnew2.objTh.join();
      Objnew3.objTh.join();
    }
    catch(InterruptedException e)
    {
      System.out.println("Main thread is interrupted");
    }
    System.out.println("First thread is alive :" + Objnew1.objTh.isAlive());
    System.out.println("Second thread is alive :" + Objnew2.objTh.isAlive());
    System.out.println("Third thread is alive :" + Objnew3.objTh.isAlive());
    System.out.println("Main thread is over and exiting");
  }



}
