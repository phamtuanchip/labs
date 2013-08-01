package lab8;

class Philosopher extends Thread
{
  ChopStick left,right;
  int philo_num;

public Philosopher(int num, ChopStick chop1,ChopStick chop2)
  {
    philo_num = num;
    left = chop1;
    right = chop2;
  }
  public void eat()
  {
    left.takeup();
    right.takeup();
    System.out.println("Philosopher "+(philo_num+1)+" is eating");
  }

  public void think()
  {
    left.putdown();
    right.putdown();
    System.out.println("Philosopher "+(philo_num+1)+" is thinking");
  }  	

  public void run()
  {
    while(true)
    {
      eat();
      try
      {
        sleep(1000);
      }
      catch(InterruptedException e)
      {}
      think();
      try
      {
        sleep(1000);
      } 
      catch(InterruptedException e)
      {}
    }
  }
}
//end of class 