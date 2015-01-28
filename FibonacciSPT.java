import java.util.ArrayList;
import java.util.LinkedList;

public class FibonacciSPT 
{
	private FibonacciHeap fHeap;
	private int totalWeight =0;
	private int [] distanceList= null;

	//InputFileGraph elements
	private int size=0;
	private ArrayList<LinkedList<Edge>> neighbour =new ArrayList<LinkedList<Edge>>(); // this is the  actual graph which we have stored in an ArrayList of linked list
	public ArrayList<FibonacciHeapNode> nodes = new ArrayList<FibonacciHeapNode>(); // this is an array of the nodes in the heap.
	public double cost()
	{
		return totalWeight;
	}

	public FibonacciSPT(ArrayList<LinkedList<Edge>> object,int size)
	{
		fHeap=new FibonacciHeap();
		neighbour=object;
		this.size=size;

		// this is the array for storing the weights of the edges
		distanceList=new int[size];		
			for(int i=0;i<size;i++)
			{
				distanceList[i]= Integer.MAX_VALUE;
				FibonacciHeapNode temp= new FibonacciHeapNode(i, distanceList[i]);
				nodes.add(temp); // this would add each Fibonacci heap node to the array list 
			}
			for(int i=0;i<size;i++)
			{
				fHeap.insertNode(nodes.get(i), distanceList[i]); // this would insert each node into the heap along with its weight
			}
	}

	//This is the main function which would eventually compute the shortest distance between the different nodes
	public void fDijikstraUserFile(int s)
	{
		// tracker is just a label to keep a track of the visited and the unvisited nodes
		int [] trackList= new int[size];
		for(int i=0;i<size;i++)
		{
			trackList[i]=0; // initially 0 as the nodes are unvisited
		}
		
		final int [] prev = new int[size]; // It's an array to store to preceding nodes on the path
		
		// the decrease key checks if theNode is not a root and new key < parent key, remove subtree rooted at theNode from its doubly linked sibling list.
		fHeap.decreaseKey(nodes.get(s), 0);
		
		// this simply starts the counter for measuring the time
		long startTime1 = System.currentTimeMillis();

		// check if the heap is empty or round
		while (!fHeap.isEmpty())
		{
			FibonacciHeapNode min = fHeap.removeMin();
			int vertex= min.data;				
			// this would flag the vertex as visited
			trackList[vertex]=(int) min.key;
			// get the neighborAdjacencyList of the existing vertex
			LinkedList<Edge> n= neighbour.get(vertex);
			// compute the cost from vertex to the next node with minimum distance
			// this for loop runs till all the neighborAdjacencyList of the vertex are visited
			for(int i=0;i<n.size();i++)
			{
				// this will fetch the first edge among the list of all the adjacent neighborAdjacencyList
				Edge temp=n.get(i);
  				double temp1= (min.key+temp.weight);  				
  				if (temp1< nodes.get(temp.v2).key)
  				{
  					fHeap.decreaseKey(nodes.get(temp.v2), temp1);
  					prev[temp.v2]=vertex;
  				}
			}
		}
		long endTime1   = System.currentTimeMillis();
		long totalTime1 = endTime1 - startTime1;
		//System.out.println("Fibonacci heap run time= "+totalTime1 + "Milliseconds");
		for(int l=0;l<size;l++)
		{
			System.out.println(trackList[l]+"//cost from node "+s+" to "+l);
		}
		System.out.println();
	}
	
	// this function will be called for random files
	public void fDijikstraRandom(int s)
	{
		// tracker is just a label to keep a track of the visited and the unvisited nodes
		int [] trackList= new int[size];
		for(int i=0;i<size;i++)
		{
			trackList[i]=0; // initially 0 as the nodes are unvisited
		}
		
		final int [] prev = new int[size]; // It's an array to store to preceding nodes on the path
		
		// the decrease key checks if theNode is not a root and new key < parent key, remove subtree rooted at theNode from its doubly linked sibling list.
		fHeap.decreaseKey(nodes.get(s), 0);
		
		// this simply starts the counter for measuring the time
		long startTime1 = System.currentTimeMillis();

		// check if the heap is empty or round
		while (!fHeap.isEmpty())
		{
			FibonacciHeapNode min = fHeap.removeMin();
			int vertex= min.data;				
			// this would flag the vertex as visited
			trackList[vertex]=(int) min.key;
			// get the neighborAdjacencyList of the existing vertex
			LinkedList<Edge> n= neighbour.get(vertex);
			// compute the cost from vertex to the next node with minimum distance
			// this for loop runs till all the neighborAdjacencyList of the vertex are visited
			for(int i=0;i<n.size();i++)
			{
				// this will fetch the first edge among the list of all the adjacent neighborAdjacencyList
				Edge temp=n.get(i);
  				double temp1= (min.key+temp.weight);  				
  				if (temp1< nodes.get(temp.v2).key)
  				{
  					fHeap.decreaseKey(nodes.get(temp.v2), temp1);
  					prev[temp.v2]=vertex;
  				}
			}
		}
		long endTime1   = System.currentTimeMillis();
		long totalTime1 = endTime1 - startTime1;
		System.out.println("Fibonacci heap run time= "+totalTime1 + "Milliseconds");
		for(int l=0;l<size;l++)
		{
			System.out.println(trackList[l]+"//cost from node "+s+" to "+l);
		}
		System.out.println();
		//System.out.println("Fibonacci heap run time= "+totalTime1 + "Milliseconds");
	}


}
