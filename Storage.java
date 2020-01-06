//This class is used for initializing and returning the building numbers, executed time and total time
public class Storage {
    
    int Building_Numbers,Executed_time, Total_time;
    //for initializing the values of building numbers, executed time and total time
     public Storage( int bn, int exetime, int tt ){
        
       
        Building_Numbers = bn;
        Executed_time = exetime;
        Total_time = tt;
    }
     
    public Storage() {
	
	}
    //returning the executed time
	public int getExecuteTime ()
    {
        return Executed_time;
    }
	//returning the building numbers
      public int getBuildingNums ()
    {
        return Building_Numbers;
    }
    //returning the total time
      public int getTotal_time ()
    {
        return Total_time;
    }
    //setting the execution time
    public void setExecutionTime(int execute_time) {
    	
    	Executed_time = execute_time;
    }
   
    
}


