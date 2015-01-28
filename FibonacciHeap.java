/* This class performs all the basic operations of a Fibonacci Heap
 * The operation performed include insertion, delete, decrease Key, meld
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FibonacciHeap 
{

	FibonacciHeapNode minimumNode;
	int numberOfNodes;

	public FibonacciHeap()
	{
		
	} 
	public boolean isEmpty()
	{
		return (minimumNode==null);
	} 
	public void reset()
	{
		minimumNode=null; 
		numberOfNodes=0; 
	}
	public FibonacciHeapNode minimumElement()
	{
		return minimumNode;
	}
	public int size()
	{
		return numberOfNodes;
	}

	// function to insert a new node into the heap
	public void insertNode(FibonacciHeapNode fNode, double key)
	{
		fNode.key = key;
        if (minimumNode != null)
        {
            fNode.leftSibling = minimumNode;
            fNode.rightSibling = minimumNode.rightSibling;
            minimumNode.rightSibling = fNode;
            fNode.rightSibling.leftSibling = fNode;

            if (key < minimumNode.key)
            {
                minimumNode = fNode;
            }
        } 
         	else 
         	{
         		minimumNode = fNode;
         	} 
         	numberOfNodes++;
	}	

	public void decreaseKey(FibonacciHeapNode fNode, double key)
	    {
	        if (key > fNode.key)
	        {
       	
	        }
	        fNode.key = key;
	        FibonacciHeapNode temporaryParentNode = fNode.parent;
	        if ((temporaryParentNode != null) && (fNode.key < temporaryParentNode.key))
	        {
	            cut(fNode,temporaryParentNode);
	            cascadingCut(temporaryParentNode);
	        }

	        if (fNode.key < minimumNode.key) 
	        {
	            minimumNode = fNode;
	        }
	    }


	// this function would perform the Cascading cut operation
	private void cascadingCut(FibonacciHeapNode temporaryParentNode)
	{ 
		FibonacciHeapNode temp= temporaryParentNode.parent;
		if(temp!=null)
		{
			if(!temporaryParentNode.flag)
			{
				temporaryParentNode.flag=true;
			}
			else 
			{
				cut(temporaryParentNode,temp);
				cascadingCut(temp);
			}
		}
	}

	
	// Function to perform the cut 
	private void cut(FibonacciHeapNode fNode, FibonacciHeapNode temporaryParentNode)
	{
			fNode.leftSibling.rightSibling = fNode.rightSibling;
	        fNode.rightSibling.leftSibling = fNode.leftSibling;
	        temporaryParentNode.degree--;

	        if (temporaryParentNode.child == fNode)
	        {
	            temporaryParentNode.child = fNode.rightSibling;
	        }

	        if (temporaryParentNode.degree == 0) 
	        {
	            temporaryParentNode.child = null;
	        }

	        fNode.leftSibling= minimumNode;
	        fNode.rightSibling=minimumNode.rightSibling;
	        minimumNode.rightSibling=fNode;
	        fNode.rightSibling.leftSibling= fNode;
	        fNode.parent=null;
	        fNode.flag=false;
   }

	public FibonacciHeap meld(FibonacciHeap fHeap1, FibonacciHeap fHeap2)
	{
		FibonacciHeap temp= new FibonacciHeap();
		if((fHeap1!=null) && (fHeap2!=null))
		{
			temp.minimumNode=fHeap1.minimumNode;
		if(temp.minimumNode!=null)
		{
			if(fHeap2.minimumNode!=null)
			{
				 temp.minimumNode.rightSibling.leftSibling = fHeap2.minimumNode.leftSibling;
                 fHeap2.minimumNode.leftSibling.rightSibling = temp.minimumNode.rightSibling;
                 temp.minimumNode.rightSibling = fHeap2.minimumNode;
                 fHeap2.minimumNode.leftSibling = temp.minimumNode;
                 if (fHeap2.minimumNode.key < fHeap1.minimumNode.key)
                 {
                     temp.minimumNode = fHeap2.minimumNode;
                 }
			}
		}
		else 
		{
            temp.minimumNode = fHeap2.minimumNode;
        }

        temp.numberOfNodes = fHeap1.numberOfNodes + fHeap2.numberOfNodes;
	}
		return temp;
	}

	// Function to perform the remove Min operation
	public FibonacciHeapNode removeMin()
	{
		FibonacciHeapNode fNode1= minimumNode;
		if (fNode1 != null) 
		{
            int numChilds = fNode1.degree;
            FibonacciHeapNode fNode2 = fNode1.child;
            FibonacciHeapNode tempChild;
            
            while(numChilds>0)
            {
            	tempChild=fNode2.rightSibling;
            	fNode2.leftSibling.rightSibling = fNode2.rightSibling;
                fNode2.rightSibling.leftSibling = fNode2.leftSibling;
                fNode2.leftSibling = minimumNode;
                fNode2.rightSibling = minimumNode.rightSibling;
                minimumNode.rightSibling = fNode2;
                fNode2.rightSibling.leftSibling = fNode2;
                fNode2.parent = null;
                fNode2 = tempChild;
                numChilds--;
            }
            fNode1.leftSibling.rightSibling= fNode1.rightSibling;
            fNode1.rightSibling.leftSibling=fNode1.leftSibling;
            
            if (fNode1 == fNode1.rightSibling) 
            { 
                minimumNode = null;
            } 
            else 
            {
                minimumNode = fNode1.rightSibling;
                pairwiseCombine();
            }
            numberOfNodes--;
        }

		return fNode1;
	}

	// function to perform the pairwise combine
	private void pairwiseCombine() 
	{
		double value= 1.0 / Math.log((1.0 + Math.sqrt(5.0)) / 2.0);
		int size =((int) Math.floor(Math.log(numberOfNodes) * value)) + 1;
		List<FibonacciHeapNode> list =new ArrayList<FibonacciHeapNode>(size);
		for (int i = 0; i < size; i++)
		{
            list.add(null);
        }
		int roots=0;
		FibonacciHeapNode temporaryNode= minimumNode;
		if (temporaryNode != null) 
		{
            roots++;
            temporaryNode = temporaryNode.rightSibling;
            while (temporaryNode != minimumNode) 
            {
                roots++;
                temporaryNode =temporaryNode.rightSibling;
            }
         }
		
		while (roots > 0) 
		{
            int d = temporaryNode.degree;
            FibonacciHeapNode nextNode = temporaryNode.rightSibling;
            
            while(true)
            {
            	FibonacciHeapNode fNode = list.get(d);
                if (fNode == null)
                {
                    break;
                }
                if (temporaryNode.key > fNode.key)
                {
                    FibonacciHeapNode temp1 = fNode;
                    fNode = temporaryNode;
                    temporaryNode = temp1;
                }
                merge(fNode,temporaryNode);
                list.set(d,null);
                d++;
            }
            list.set(d,temporaryNode);
            temporaryNode=nextNode;
            roots--;

	}
		minimumNode=null;

		for (int i = 0; i < size; i++) 
		{
            FibonacciHeapNode fNodeTemp = list.get(i);
            if (fNodeTemp == null) 
            {
                continue;
            }
            if (minimumNode != null)
            {
                fNodeTemp.leftSibling.rightSibling = fNodeTemp.rightSibling;
                fNodeTemp.rightSibling.leftSibling = fNodeTemp.leftSibling;
                fNodeTemp.leftSibling = minimumNode;
                fNodeTemp.rightSibling = minimumNode.rightSibling;
                minimumNode.rightSibling = fNodeTemp;
                fNodeTemp.rightSibling.leftSibling = fNodeTemp;
                if (fNodeTemp.key < minimumNode.key) 
                {
                    minimumNode = fNodeTemp;
                }
            } 
            else 
            {
                minimumNode = fNodeTemp;
            }
        }
    }

	// function to perform the merge
	private void merge(FibonacciHeapNode fNode1, FibonacciHeapNode fNode2)
	    {
	        fNode1.leftSibling.rightSibling = fNode1.rightSibling;
	        fNode1.rightSibling.leftSibling = fNode1.leftSibling;
	        fNode1.parent = fNode2;

	        if (fNode2.child == null) 
	        {
	            fNode2.child = fNode1;
	            fNode1.rightSibling = fNode1;
	            fNode1.leftSibling = fNode1;
	        } 
	        else 
	        {
	            fNode1.leftSibling = fNode2.child;
	            fNode1.rightSibling = fNode2.child.rightSibling;
	            fNode2.child.rightSibling = fNode1;
	            fNode1.rightSibling.leftSibling = fNode1;
	        }
	        fNode2.degree++;
	        fNode1.flag = false;
	    }

	// function to perform deletion
	public void deleteNode(FibonacciHeapNode fNode)
	    {
	        decreaseKey(fNode, Double.NEGATIVE_INFINITY);
	        removeMin();
	    }

	public FibonacciHeapNode search(int x)
	   {
		   Stack<FibonacciHeapNode> stack = new Stack<FibonacciHeapNode>();
	        stack.push(minimumNode);	   
	        while (!stack.empty()) 
	        {
	            FibonacciHeapNode curr = stack.pop();
	        	if(curr.data==x) {return curr;}

	            else
	            {
	            	if (curr.child != null) 
	            	{
	            		stack.push(curr.child);
	            	}
	            	FibonacciHeapNode start = curr;
	            	curr = curr.rightSibling;

	            	while (curr != start) 
	            	{

	            		if (curr.child != null) 
	            		{
	            			stack.push(curr.child);
	            		}
	            		if(curr.data==x) 
	            		{
	            			return curr;
	            		}
	            		curr = curr.rightSibling;
	            	}

	            }
	        }  
	        return null;
	   }
}
