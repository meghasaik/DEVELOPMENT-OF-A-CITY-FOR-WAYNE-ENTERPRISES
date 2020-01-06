import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//Implementation of Red-Black Tree

public class RBTree {

	 min_Heap min ;
    private final int RED = 0;
    private final int BLACK = 1;
    private static HashMap<Integer, Integer> ptr = new HashMap<Integer, Integer>();
	ArrayList<Integer> printBuildingRange = new ArrayList<Integer>();
	static HashMap<Integer,RBNode> RBNode = new HashMap<Integer,RBNode>();
	 risingCity risingCity= new risingCity();
    


    class RBNode {

        Storage element = new Storage(-1,-1,-1);
        int color = BLACK;
        RBNode left = nil, right = nil, parent = nil;

        RBNode(Storage element) {
            this.element = element;
        }
        
      
    }

    //default nil node for the RB Tree
    private final RBNode nil = new RBNode(new Storage(-1,-1,-1));

    //initialize root as nil initially and continue
    private RBNode root = nil;
    private RBNode right_node = nil;

    //Checks if the node exists or not
    
    
    private RBNode node_exist(RBNode noins, RBNode node) {
    	if (noins.element.Building_Numbers > node.element.Building_Numbers && (node.right != nil)) 
        {
              return node_exist(noins, node.right);
        }
    	else if (noins.element.Building_Numbers < node.element.Building_Numbers && (node.left != nil))
        {
              return node_exist(noins, node.left);
        } 
    	else
    	{
    		if (noins.element.Building_Numbers == node.element.Building_Numbers) 
            {
                return node;
            }
    		else if (root == nil) 
            {
                return null;
            }
    	}
    		return null;
    }

    
      public RBNode insertion(Storage item)
      {
        RBNode node = new RBNode(item);
        RBNode temp = root;
        insert(node, temp);
        RB_insfixup(node);
        
         RBNode.put(node.element.Building_Numbers, node);
          return node;
    }
   //insertion of the node into the tree
    public void insert(RBNode node, RBNode temp)
    {
    	//check if the root is empty or not 
    	if(root != nil)
        {
            node.color = RED;
            while (true)
            {
            	  if (node.element.Building_Numbers >= temp.element.Building_Numbers) 
                 {
            		  if(temp.right != nil)
                      {
                          temp = temp.right;
                      }
            		  else 
            		  {
                         temp.right = node;
                         node.parent = temp;
                         break;
                      } 
                }
            	  else if (node.element.Building_Numbers < temp.element.Building_Numbers) 
            	  {
                    if(temp.left != nil)
                    {
                        temp = temp.left;
                    }
                    else
                    {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } 
            	  }
               
            }        
          }

        //Insert at root if tree is empty
        else 
        {
            root = node;
            right_node = root;
            node.color = BLACK;
            node.parent = nil;
        } 
    }
    
    
    // Fixing the tree in order to maintain its properties
     private void RB_insfixup(RBNode node) 
    {
        // Here the insertion is done and the color given=red. If the parent node also red then property violated.
    	 
        while (node.parent.color == RED)
        {
            RBNode lfnode = nil;

            /* Two cases. When :
              1. Parent is left child of it parent - Rotate Right
              2. Parent is right child of its parent - Rotate Left
             */
            if (node.parent != node.parent.parent.left)
            {
            	lfnode = node.parent.parent.left;
            	if (lfnode != nil && lfnode.color == RED) 
                {
                    node.parent.color = BLACK;
                    lfnode.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                else if (node == node.parent.left) {
                    node = node.parent;
                    RR(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                LR(node.parent.parent);
            }
            else 
            {
            	lfnode = node.parent.parent.right;

                if (lfnode != nil && lfnode.color == RED) 
                {
                    node.parent.color = BLACK;
                    lfnode.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                else if (node == node.parent.right) 
                {
                    node = node.parent;
                    LR(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                RR(node.parent.parent);
            } 
          
        }
        root.color = BLACK;
     }
    
    //left rotation of the tree for balancing

    void LR(RBNode node)
    {
    	 if (node.parent == nil) 
         {
             // The root node is rotated to the left
             RBNode right = root.right;
             root.right = right.left;
             right.left.parent = root;
             root.parent = right;
             right.left = root;
             right.parent = nil;
             root = right;
         }
    	 else 
        {
            if (node == node.parent.left) 
            {
                node.parent.left = node.right;
            } 
            else
            {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != nil) 
            {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        }
       
    }
//right rotation of the tree for balancing
    void RR(RBNode node)
    {
    	 if (node.parent == nil)
         {
             //the root node is rotated to the right
             RBNode left = root.left;
             root.left = root.left.right;
             left.right.parent = root;
             root.parent = left;
             left.right = root;
             left.parent = nil;
             root = left;
         }
    	else
        {
            if (node == node.parent.left)
            {
                node.parent.left = node.left;
            } 
            else
            {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != nil) 
            {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } 
       
    }

    // for replacing one subtree as a child of its parent with another subtree.

    void rbTransfer(RBNode u, RBNode v){
        if(u.parent == nil){
            root = v;
        }else if(u == u.parent.left){
            u.parent.left = v;
        }else
            u.parent.right = v;
        v.parent = u.parent;
    }

 
 
   public RBNode findBuildingNum(int Building_num)
    {
        return findBuildingNum(root, Building_num);
    }
    private RBNode findBuildingNum(RBNode root, int Building_num)
    {
    	
        while (root != nil)
        {
            int rootvalue = root.element.Building_Numbers;
            if (Building_num < rootvalue) {
            	root = root.left;
            
            }
                
            else if (Building_num > rootvalue)
                root = root.right;
            else
            {
                return root;
            }
            return root;
        }
        return null;
    }
    
 
    boolean deleteNode(int building_num)
    {
    	 RBNode node = RBNode.get(building_num);
        deleteNode(node);
        RBNode.remove(building_num);
        return true;
    }
    
    
    /* deleting a node in the tree*/
    boolean deleteNode(RBNode fin)
    {
        if((fin = node_exist(fin, root))==null)
        {
            return false;
        }
        RBNode upfix;
        RBNode rbdel = fin;
        int initial_color = rbdel.color;

        if(fin.left == nil)
        {
            upfix = fin.right;
            rbTransfer(fin, fin.right);
        }
        else if(fin.right == nil)
        {
            upfix = fin.left;
            rbTransfer(fin, fin.left);
        }
        else 
        {
            rbdel = minNode(fin.right);
            initial_color = rbdel.color;
            upfix = rbdel.right;
            if(rbdel.parent != fin)
            {
                rbTransfer(rbdel, rbdel.right);
                rbdel.right = fin.right;
                rbdel.right.parent = rbdel;
            }
            else
            {
                upfix.parent = rbdel;
            }
            rbTransfer(fin, rbdel);
            rbdel.left = fin.left;
            rbdel.left.parent = rbdel;
            rbdel.color = fin.color;
        }
        if(initial_color==BLACK)
        {
            rb_DeletionFix(upfix);
        }
        return true;
    }

    /* method to fix colors and RB Tree properties after deletion*/

    void rb_DeletionFix(RBNode upfix){
        while(upfix!=root && upfix.color == BLACK){
        	
        	if(upfix != upfix.parent.left)
            {
                RBNode fixing = upfix.parent.left;
                if(fixing.color == RED)
                {
                    fixing.color = BLACK;
                    upfix.parent.color = RED;
                    RR(upfix.parent);
                    fixing = upfix.parent.left;
                }
                else if(fixing.left.color == RED)
                {
                    fixing.color = upfix.parent.color;
                    upfix.parent.color = BLACK;
                    fixing.left.color = BLACK;
                    RR(upfix.parent);
                    upfix = root;
                }
                else if(fixing.right.color == BLACK)
                {
                	if(fixing.left.color == BLACK)
                	{
                		fixing.color = RED;
                		upfix = upfix.parent;
                		continue;
                	}
                }
                else if(fixing.left.color == BLACK)
                {
                    fixing.right.color = BLACK;
                    fixing.color = RED;
                    LR(fixing);
                    fixing = upfix.parent.left;
                }
                
            }
        	else
            {
                RBNode fixing = upfix.parent.right;
                if(fixing.color == RED){
                    fixing.color = BLACK;
                    upfix.parent.color = RED;
                    LR(upfix.parent);
                    fixing = upfix.parent.right;
                }
                else if(fixing.left.color == BLACK )
                {
                	if(fixing.right.color == BLACK)
                	{
                		fixing.color = RED;
                		upfix = upfix.parent;
                		continue;
                	}
                }
                else if(fixing.right.color == BLACK)
                {
                    fixing.left.color = BLACK;
                    fixing.color = RED;
                    RR(fixing);
                    fixing = upfix.parent.right;
                }
                else if(fixing.right.color == RED)
                {
                    fixing.color = upfix.parent.color;
                    upfix.parent.color = BLACK;
                    fixing.right.color = BLACK;
                    LR(upfix.parent);
                    upfix = root;
                }
            }
            
        }
        upfix.color = BLACK;
    }
    
    
    /*Finding the minimum element at the subtree root*/
    RBNode minNode(RBNode subTreeRoot){
        while(subTreeRoot.left!=nil){
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }

    /* Method to search for an element in the Red Black Tree and for checking if there is a node with the given id */
   
     private void Search(RBNode r)
     {
         if (r != nil)
         {
             char color = 'B';
             if (r.color == 0)
                 color = 'R';
             System.out.print(r.element.Building_Numbers +""+color+" ");
             Search(r.left);             
             Search(r.right);
         }
     }
     /*Printing the building number given, its total time and executed time*/
     public void printBuilding (int BuildingNum)
     {
    	
        if(ptr.containsKey(BuildingNum)) {
        	 int position = ptr.get(BuildingNum);
             min = risingCity.getHeapObj();
             Storage node = min.fetchNode(position);
     		 System.out.println("(" + node.getBuildingNums() + "," + node.getExecuteTime() + "," + node.getTotal_time() + ")");
        }else {
        	System.out.println("("+0+","+0+","+0+")");
        }
      }
     /*Printing the building numbers between the range */
     public void printBuildRange(int building_num1, int building_num2)
     {
    	 printBuildRange(building_num1, building_num2, root);
    	 int j = 1;
    	 Collections.sort(printBuildingRange);
    	 
    	 if(printBuildingRange.size() != 0) {
	    	 for(int i = 0; i < printBuildingRange.size() ; i++) {int heapIndex = 0;
	    		 try {
	    		  heapIndex = ptr.get(printBuildingRange.get(i));
	    		  }catch(Exception e) 
	    		 {
	    			 // print();
	    			  Search(root);
	    			  System.out.println("error: " + printBuildingRange.get(i));
	    		}
				 min = risingCity.getHeapObj();
				 Storage node = min.fetchNode(heapIndex);
				 if(j ==1) {
				    System.out.print("(" + node.getBuildingNums() + "," + node.getExecuteTime() + "," + node.getTotal_time() + ")");
				    j = 0;
				 }else {
					 System.out.print("," + "(" + node.getBuildingNums() + "," + node.getExecuteTime() + "," + node.getTotal_time() + ")");
				 }
			 }
	    	 printBuildingRange.clear();
    	 }else {
    		 System.out.print("("+0+","+0+","+0+")");
    	 }
     }
     /*Printing the building numbers between the range */
     private void printBuildRange(int building_num1, int building_num2, RBNode r)
     {
    	 if(r.element.Building_Numbers >= building_num1 && r.element.Building_Numbers <= building_num2)
    	 {
    		 
    		 printBuildingRange.add(r.element.Building_Numbers);
    	 }
    	 if(r.left != null && r.left.element.Building_Numbers >= building_num1)
    	 {
    		 printBuildRange(building_num1, building_num2, r.left);
    	 }
    	 if(r.right != null &&  r.right.element.Building_Numbers <= building_num2 )
    	 {
    		 printBuildRange(building_num1, building_num2, r.right);
    	 }
     }
   
  
     /*for setting pointer*/
     public void setptr(int buildingnum, int position)
	  {
			  ptr.put(buildingnum, position);	
	  }
     /*deleting pointer*/	  
	  public void deleteFromptr(int buildingnum)
	  {
		  if(!ptr.isEmpty())
		  {
			  ptr.remove(buildingnum);
		  }
		 
	  }
	  /*getting value from pointer*/
	  public int getValueFromptr(int buildingNum) {
		  return ptr.get(buildingNum);
	  }
   
    }
