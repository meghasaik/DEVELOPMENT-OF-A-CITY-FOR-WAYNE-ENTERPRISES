import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;


 // Implementation of Minimum Min heap 
public class min_Heap { 
    
    
    private Storage[] MHeap; 
    private int size; 
    private int max; 
    private static int originalsize = 0;
    private static final int FRONT = 1; 
    static HashMap<Integer,Storage> execution_time = new HashMap<Integer, Storage>();
    static RBTree rbTree = new RBTree();
    
  
     class MinHeapNode {

       Storage executed_time = new Storage(0,0,0);
        
             MinHeapNode(Storage element) {
            this.executed_time = element;
            
        }
    }
    public min_Heap(int max) 
    { 
        this.max = max; 
        this.size = 0; 
        MHeap = new Storage[this.max + 1]; 
        MHeap[0] = new Storage(0,Integer.MIN_VALUE,0); 
    }
    

  
    // Function for returning the position of the parentNode for the node currently at position  
    
   // public min_Heap() {}


//getting the size of the min heap
	public int getSize() {
    	return originalsize;
    }
    // parent node retrieval
    private int parentNode(int pos) 
    { 
        return pos / 2; 
    } 
  
    // returning the position of the left child for the node currently at position
    private int leftChildNode(int pos) 
    { 
        return (2 * pos); 
    } 
  
    // returning the position of the right child for the node currently at position 
    private int rightChildNode(int pos) 
    { 
        return (2 * pos) + 1; 
    } 
  
    /* checking if the node is a leaf node or not */
    private String[] leaf_node(int pos) 
    { 
    	String[] present = new String[2];
    	if(leftChildNode(pos) > size)  
    	{
    		present[0] = "true";
    		present[1] = "0";
    	}
    	else if(leftChildNode(pos) <= size && rightChildNode(pos) <= size)
    	{
    		present[0] = "false";
    		present[1] = "2";
    	}
    	else  
    	{
    		present[0] = "false";
    		present[1] = "1";
    	}
    	return present;
    } 
  
   
     
    /* heapify the node at position*/ 
    private void heapify(int pos) 
    { 
    	 Storage temp; 
    	String[] leaf_nodeHolder = leaf_node(pos);
    	if(leaf_nodeHolder[0].equals("false") && leaf_nodeHolder[1].equals("2"))
        { 
    		
    		 if(MHeap[pos].Executed_time < MHeap[leftChildNode(pos)].Executed_time)
             {
    			  if(MHeap[rightChildNode(pos)].Executed_time == MHeap[pos].Executed_time)
               	  {
               		  if(MHeap[rightChildNode(pos)].Building_Numbers < MHeap[pos].Building_Numbers)
               		  {
               			              			 
               			  temp = MHeap[pos]; 
               			  MHeap[pos] = MHeap[rightChildNode(pos)]; 
               			  MHeap[rightChildNode(pos)] = temp; 
               			  heapify(rightChildNode(pos)); 
               		  }
               	  }
    		      else if(MHeap[rightChildNode(pos)].Executed_time < MHeap[pos].Executed_time)
           	      {
    		    	  	  		    	  
    		    	  temp = MHeap[pos]; 
    		    	  MHeap[pos] = MHeap[rightChildNode(pos)]; 
           			  MHeap[rightChildNode(pos)] = temp; 
    		    	  heapify(rightChildNode(pos));  
           	      }
            }
    		 else  if ( MHeap[pos].Executed_time > MHeap[leftChildNode(pos)].Executed_time  || MHeap[pos].Executed_time > MHeap[rightChildNode(pos)].Executed_time )
            {
    			   if (MHeap[leftChildNode(pos)].Executed_time == MHeap[rightChildNode(pos)].Executed_time)
                   {
                 	  if(MHeap[leftChildNode(pos)].Building_Numbers < MHeap[rightChildNode(pos)].Building_Numbers)
                 	  {
                 		               		
              			  temp = MHeap[pos]; 
              			  MHeap[pos] = MHeap[leftChildNode(pos)]; 
              			  MHeap[leftChildNode(pos)] = temp; 
                 		  heapify(leftChildNode(pos));
                 	  }
                 	  else
                 	  {
                 		                		
              			  temp = MHeap[pos]; 
              			  MHeap[pos] = MHeap[rightChildNode(pos)]; 
              			  MHeap[rightChildNode(pos)] = temp; 
                 		  heapify(rightChildNode(pos));
                 	  }
                   }
    			   else if(MHeap[leftChildNode(pos)].Executed_time > MHeap[rightChildNode(pos)].Executed_time) 
    			   {
    				  
    				 
            			  temp = MHeap[pos]; 
            			  MHeap[pos] = MHeap[rightChildNode(pos)]; 
            			  MHeap[rightChildNode(pos)] = temp; 
    				   heapify(rightChildNode(pos));
    			   }
    			   else
    			   {
    				   
            			  temp = MHeap[pos]; 
            			  MHeap[pos] = MHeap[leftChildNode(pos)]; 
            			  MHeap[leftChildNode(pos)] = temp; 
    				   heapify(leftChildNode(pos)); 
    			   }
            }
           
            else if(MHeap[pos].Executed_time == MHeap[leftChildNode(pos)].Executed_time)
            {
            	if(MHeap[pos].Executed_time != MHeap[rightChildNode(pos)].Executed_time)
            	{
            		if(MHeap[leftChildNode(pos)].Building_Numbers < MHeap[pos].Building_Numbers)
             		 {
            			 
           			  	temp = MHeap[pos]; 
           			  	MHeap[pos] = MHeap[ leftChildNode(pos)]; 
           			  	MHeap[ leftChildNode(pos)] = temp; 
            			heapify(leftChildNode(pos));  
             		 }
            	}
            	else
            	{
            		if(MHeap[rightChildNode(pos)].Building_Numbers > MHeap[leftChildNode(pos)].Building_Numbers)
            		{
            			if(MHeap[pos].Building_Numbers > MHeap[leftChildNode(pos)].Building_Numbers)
            			{
            					temp = MHeap[pos]; 
                			  	MHeap[pos] = MHeap[ leftChildNode(pos)]; 
                			  	MHeap[ leftChildNode(pos)] = temp; 
            				heapify(leftChildNode(pos)); 
             			 }
            		}
            		else
            		{
            			 if(MHeap[pos].Building_Numbers > MHeap[rightChildNode(pos)].Building_Numbers )
              			 {
              				
            				
             			  	temp = MHeap[pos]; 
             			  	MHeap[pos] = MHeap[ rightChildNode(pos)]; 
             			  	MHeap[ rightChildNode(pos)] = temp; 
            				 heapify(rightChildNode(pos)); 
              			 }
            		}
            	}
            	
            	
            }
       }
        else if((leaf_nodeHolder[0].equals("false") && leaf_nodeHolder[1].equals("1")))
        {
      	  if((float)pos == Math.ceil((float) getSize()/2) )
      	  {
      		  if(MHeap[pos].Executed_time == MHeap[leftChildNode(pos)].Executed_time)
	    	  {
	    	
	    		  if(MHeap[leftChildNode(pos)].Building_Numbers < MHeap[pos].Building_Numbers )
	    		  {
	    		
	    			
       			  	temp = MHeap[pos]; 
       			  	MHeap[pos] = MHeap[ leftChildNode(pos)]; 
       			  	MHeap[ leftChildNode(pos)] = temp; 
	    			  heapify(leftChildNode(pos)); 
	    		  }
	    	  }
      		  
      		  else if(MHeap[pos].Executed_time > MHeap[leftChildNode(pos)].Executed_time)
  	    	  {
  	    		 
      			
   			  	temp = MHeap[pos]; 
   			  	MHeap[pos] = MHeap[ leftChildNode(pos)]; 
   			  	MHeap[ leftChildNode(pos)] = temp; 
      			 heapify(leftChildNode(pos)); 		  
  	    	  }
  	    	  
      	  }
        }
  
    }
  
    /* insertion of a node into the minimum heap*/ 
    public Storage insertion(Storage element) 
    { 
    	if(size < max)
    	{
	       MHeap[++size] = element;
	        int current = size; 
	        Storage temp; 
	        while (MHeap[current].Executed_time < MHeap[parentNode(current)].Executed_time)
	        { 
	            
	        	
   			  	temp = MHeap[current]; 
   			  	MHeap[current] = MHeap[ parentNode(current)]; 
   			  	MHeap[ parentNode(current)] = temp; 
	        	current = parentNode(current); 
	        } 
	        	originalsize++;
	        for(int i=1; i <= getSize(); i++)
	  		{
	  			rbTree.setptr(MHeap[i].getBuildingNums(), i);
	  			execution_time.put(MHeap[i].getBuildingNums(), MHeap[i]);
	  		}
	        
        }
    	else
    	{ 
            System.out.println("Size is bigger than max size");
        }
    	
        return MHeap[size];
     
    } 
   
  
    /* building the minimum heap using heapify.*/ 
    
    public Storage minHeap() 
    { 
        for(int pos = (size / 2); pos >= 1; pos--) 
        { 
            heapify(pos); 
         } 
       
        for(int i=1; i <= getSize(); i++)
  		{
  			rbTree.setptr(MHeap[i].getBuildingNums(), i);
  		}
        
        return MHeap[1];
    } 
  
    /* removing and returning the minimum element from the heap */
    public void remove(int buildingNum) 
    { 
    	
        int position = rbTree.getValueFromptr(buildingNum);
        position_removal(position);
    }
    
    public void position_removal(int index)
    {
    	if(index == 1)
        {
	    	Storage pop = MHeap[FRONT]; 
	    	if(getSize() == 1) 
	    	{
	    		MHeap[FRONT] = MHeap[size--]; 
	    		originalsize--;
	    	}
	    	if(getSize()>1)
	    	{
		        MHeap[FRONT] = MHeap[size--]; 
		        originalsize--;
		        heapify(FRONT); 
		        rbTree.deleteFromptr(pop.getBuildingNums());
		  		for(int i=1; i<= getSize(); i++)
		  		{
		  			rbTree.setptr(MHeap[i].getBuildingNums(), i);
		  		}
	    	}
	    }
        else
        {
        	if(size==2)
        	{
        		Storage pop = MHeap[size]; 
            	size--;
            	originalsize--;
        	}
        	else if(size>2)
        	{
        		Storage pop = MHeap[index];
            	MHeap[index] = MHeap[size--];
            	originalsize--;
            	minHeap();
        	}
        	
        }
    }
    
    //for getting the min heap
    public Storage fetchNode(int position) {
    	return MHeap[position];
    }
}
  
