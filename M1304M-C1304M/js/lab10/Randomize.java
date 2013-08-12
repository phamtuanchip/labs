package lab10;

import java.util.*;

class  Randomize
{
	public static void main(String[] args) 
{
		while (true)
	{ 
				Random objRandom = new java.util.Random(); 
			System.out.println(objRandom.nextInt(3));
				try
			{
 				Thread.sleep(500);
			}	
			catch(InterruptedException e){  }
		}
	}
}
