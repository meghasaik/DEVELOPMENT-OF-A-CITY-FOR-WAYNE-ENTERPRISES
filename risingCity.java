import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;
//rising_City implementation

public class risingCity {
   static long gcc = 0;
   static HashMap<Integer, RBTree.RBNode> data = new HashMap<Integer, RBTree.RBNode>();
   static HashMap<Integer, Storage[]> data2 = new HashMap<Integer, Storage[]>();
   static int initial = 1;
   static min_Heap minHeap = new min_Heap(2000);
   static RBTree rbTree = new RBTree();
   static Storage currentBuilding = new Storage();
   static boolean access = true;
  
      
    public static void main(String[] args) {
        
        int day = 0;
        String input = "";
        boolean nxtline = true;
        String line = "";
       
  
        
        BufferedReader reader = null;
        PrintStream out = null;
    	try 
    	{
    		out = new PrintStream(new FileOutputStream("output_file.txt"));
    	} 
    	catch (FileNotFoundException e1) {
    		
    		e1.printStackTrace();
    	}
       	System.setOut(out);
       
        try {
        	reader = new BufferedReader(new FileReader("input.txt"));
        	
        	while(initial == 1 || minHeap.getSize() > 0 || line != null) {
        			
        	
        		if(nxtline == true)
        		{
					line = reader.readLine();
        			
					if((line != null)) 
					{
						String[] line_arg = delimiter_remove(line);
						day = Integer.parseInt(line_arg[0]);
						
						input = line_arg[1];
					}
					nxtline = false;
				}
        		if(gcc == day) 
        		{
					operation(input);
					nxtline = true;
			
				}
        		if( minHeap.getSize() > 0 ) 
        		{
	        		if((currentBuilding.getExecuteTime() == 0) || currentBuilding.getExecuteTime() % 5 != 0 ) 
	        		{
	        			if(day != 0 )
	        			{
	        				if( access == true)
	        				{
	        					currentBuilding.setExecutionTime(currentBuilding.getExecuteTime() + 1);
				    	
	        				}
	        			}
	        			access = true;
	        		}
	        		if((currentBuilding.getExecuteTime() % 5 == 0) && (currentBuilding.getExecuteTime() != 0))
	        		{
	        			if((currentBuilding.getExecuteTime() != currentBuilding.getTotal_time()))
	        			{
	        				currentBuilding = minHeap.minHeap();
	        				access = true;
	        				if(currentBuilding.getExecuteTime() > 0)
	        				{
	        					currentBuilding.setExecutionTime(currentBuilding.getExecuteTime() + 1);
	        					access = false;
	        				}
	        			}
	        		}
	        		if(currentBuilding.getExecuteTime() == currentBuilding.getTotal_time()) 
	        		{
				    	System.out.println("(" + currentBuilding.getBuildingNums() + "," + gcc + ")");
				    	minHeap.remove(currentBuilding.getBuildingNums());
				    	rbTree.deleteNode(currentBuilding.getBuildingNums());
				    	if(minHeap.getSize() > 0 ) 
				    	{
				    	   currentBuilding = minHeap.minHeap();
				    	   currentBuilding.setExecutionTime(currentBuilding.getExecuteTime() + 1);
					    	access = false;
				    	}
				    	if(minHeap.getSize() == 0 && line != null) 
				    	{
				    		initial = 1;
				    	}
				    }
        		}
        		gcc++;
        	}
        	
        	
        }catch(IOException e) {
			e.printStackTrace();
		}
     } 
//method for operation performing
    private static void operation(String input) {
		if(input.contains("Insert")) 
		{
			insert_Operation(input);
		}
		else if(input.contains("PrintBuilding")) 
		{
			print_Operation(input);
		} 
    				
  }
    //for insertion operation
    private static void insert_Operation(String input)
    {
    	String[] parameters = split_cmd(input);
		Storage data = new Storage(Integer.parseInt(parameters[1]),0,Integer.parseInt(parameters[2]));
		rbTree.insertion(data);
		Storage minheapnode = minHeap.insertion(data);
		if(initial == 1) 
		{
			currentBuilding = minheapnode;
			initial = 0;
		}
    }
    //for print operation
    private static void print_Operation(String input)
    {
    	String[] parameters = split_cmd(input); 
		int l= parameters.length;
		switch(l)
		{
		case 2: if(access == true) 
				{
			  	currentBuilding.setExecutionTime(currentBuilding.getExecuteTime() + 1);
			  	access = false;
				}
				rbTree.printBuilding(Integer.parseInt(parameters[1]));
				break;
		case 3: if(access == true) 
				{
			  		currentBuilding.setExecutionTime(currentBuilding.getExecuteTime() + 1);
			  		access = false;
				}
				rbTree.printBuildRange(Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]) );
				access = false;
				System.out.println();
				break;
		}
    }
    //for removal of extra junk from input line
    public static String[] split_cmd(String cmdlines) {
		cmdlines = cmdlines.trim();
        cmdlines = cmdlines.replace(" ", "");
        cmdlines = cmdlines.replace("(", ",");
        cmdlines = cmdlines.replace(")", "");
        cmdlines = cmdlines.toLowerCase();
        return cmdlines.split(",");
	}
      //for delimiter removing
     public static String[] delimiter_remove(String cmdlines) {
  		String[] split = cmdlines.split(":");
  		return split;
  	}
     //getting the minimum heap
     public min_Heap getHeapObj() {
    	 return minHeap;
     }
    
}
    
    







