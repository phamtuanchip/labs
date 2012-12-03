/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leopad
 */
public class Threads {
    
    
	public static void main(String[] args){
		Thread th = new Thread();
		System.out.println("Numbers are printing line by line after 5 seconds : ");
		try{
			for(int i = 1;i <= 10;i++)
			{
				System.out.println(i);
				th.sleep(5000);
			}
		}
		catch(InterruptedException e){
			System.out.println("Thread interrupted!");
			e.printStackTrace();
		}
	}

    
}
