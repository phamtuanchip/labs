package lab8;

class One
{       
  synchronized void display(int num)      
  {       
    System.out.print(""+num);
    try
    {       
      Thread.sleep(1000);
    }
    catch(InterruptedException e)
    {
      System.out.println("Interrupted");
    }
    System.out.println(" done");
  }
}
class Two implements Runnable
{  
  int number;
  One objOne;
  Thread objTh;

  public Two(One one_num, int num)
  {
    objOne = one_num;
    number = num;
    objTh = new Thread(this);
    objTh.start();
  }
  public void run()
  { 
    objOne.display(number);
  }
}
class SynchMethod
{   
  public static void main(String args[])
  {
    One objOne = new One();
    int digit = 10;
    Two objSynch1 = new Two(objOne,digit++);
    Two objSynch2 = new Two(objOne,digit++);
    Two objSynch3 = new Two(objOne,digit++);

    //wait for threads to end

    try
    {
      objSynch1.objTh.join(); 
      objSynch2.objTh.join();
      objSynch3.objTh.join();
    }
    catch(InterruptedException e)
    { 
      System.out.println("Interrupted");
    }
  }
} 