package lab8;

class ChopStick
{
	boolean available;
	ChopStick()
	{
		available = true;
	}
	public synchronized void takeup()
	{
		while(!available)
		{
			try
			{
				System.out.println("Philosopher is waiting for the other chopstick");
				wait();
			}
			catch(InterruptedException e)
			{ }
		}
		available = false;
	}
	public synchronized void putdown()
	{
		available = true;
    	 	notify();
	}
}