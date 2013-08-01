package lab8;

class GCDemo
{	
	public static void main(String args[])
	{       
		int count;
		long num;
		Runtime objRun = Runtime.getRuntime();
		Long values[] = new Long[200];
    		 System.out.println("Amount of  free memory is  " + objRun.freeMemory());
		objRun.gc();
		System.out.println("Amount of  free memory after garbage collection is "+ objRun.freeMemory());
		for(num = 10000,count = 0; count < 200; num++,count++)
		{
     			values[count] = new Long(num);
		}
		System.out.println("Amount of  free memory after creating array is  "+ objRun.freeMemory());
          
		for (count = 0;count < 200 ; count++)
		{
     			values[count] = null;
		}
		objRun.gc();
		System.out.println("Amount of  free memory after  garbage collection is   "+ objRun.freeMemory( ));
	}
}