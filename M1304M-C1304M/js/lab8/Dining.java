package lab8;

class Dining
{
	static ChopStick[] chopsticks = new ChopStick[5];
	static Philosopher[] philos = new Philosopher[5];
	
	public static void main(String args[])
	{
		for (int count = 0;count <= 4;count++)
		{
			chopsticks[count] = new ChopStick();
		}
     
		for (int count = 0;count <= 4;count++)
		{
			philos[count] = new Philosopher(count,chopsticks[count],chopsticks[(count+1)%5]);
		}
    	
		for (int count = 0;count <= 4;count++)
			philos[count].start( );
	}
}