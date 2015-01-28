/* The InputFileGraph is a class which is used to create a graph when the user chooses an input file mode. 
 *  This class has two constructors one for creating a graph from the number of nodes as input 
 *  and the other for creating a graph when the file name is input 
 */
import java.util.*;
import java.io.*;


public class InputFileGraph 
{
	int sourceNode;
	int size; // Number of vertices
	public ArrayList<LinkedList<Edge>> neighborAdjacencyList; // Adjacency lists
	
	// Constructs a graph with s vertices and no edges
	public InputFileGraph(int numOfNodes) 
	{
		size = numOfNodes;;
		//System.out.println("Size="+size);
		neighborAdjacencyList = new ArrayList<LinkedList<Edge>>(size);
		for (int i=0; i<size; i++)
		{
			neighborAdjacencyList.add(new LinkedList<Edge>());
			//System.out.println(neighborAdjacencyList.get(i));
		}
	}

	// Constructs a graph from a given file name
	public InputFileGraph(String filename) 
	{
		
		try 
		{		
			Scanner scanner = new Scanner(new File(filename));
			sourceNode = scanner.nextInt();
			//System.out.println("Source Node" + sourceNode);
			int numOfNodes = scanner.nextInt();
			//System.out.println("Number of nodes Node" + numOfNodes);
			
			// Make room for the vertices
			size = numOfNodes;
			//System.out.println("Size" +size);
			neighborAdjacencyList = new ArrayList<LinkedList<Edge>>(size);
			for (int i=0; i<size; i++)
			{
				neighborAdjacencyList.add(new LinkedList<Edge>());
			}
			
			// Read edges
			int numOfEdges = scanner.nextInt();
			//System.out.println("Number of Edges" +numOfEdges);
			
			while (scanner.hasNextInt()) 
			{
				int v1 = scanner.nextInt();
				//System.out.println("Value of v1 is " +v1);
				int v2 = scanner.nextInt();
				//System.out.println("Value of v2 is " +v2);
				int weight = scanner.nextInt();
				//System.out.println("Value of weight is " +weight);
				addEdge(v1, v2, weight);
				//System.out.println(v1 + " " + v2 + " " + weight);
			}	
			scanner.close();			
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("Error: could not find file "+filename);
			System.exit(0);
		} 
		catch (InputMismatchException e) 
		{
			System.out.println("Error: incorrect format in "+filename);
			System.exit(0);
		}
	}

	// Adds an edge to the graph
	public void addEdge(int v1, int v2, int weight) 
	{
		Edge edge1 = new Edge(v1, v2, weight);
		listOfNeighbors(v1).add(edge1);
		Edge edge2 = new Edge(v2, v1, weight);
		listOfNeighbors(v2).add(edge2);
	}
	
	// Gets the neighbor list of a particular vertex
	public LinkedList<Edge> listOfNeighbors(int v)
	{
		LinkedList<Edge> list =neighborAdjacencyList.get(v);
		int neighborArray [] = new int [list.size()];
		//System.out.println("The size of the temp is" + temp.size());
		for (int j = 0; j < list.size(); j++)
		{
			Edge e=list.get(j);
			neighborArray[j] = e.v2;	
		}
		return neighborAdjacencyList.get(v);
	}
	
	// this function would return the nodes adjacent to the node v
	public int [] arrayOfNeighbors(int v)
	{		
		LinkedList<Edge> temp =neighborAdjacencyList.get(v);
		int neighborArray [] = new int [temp.size()];
		//System.out.println("The size of the temp is" + temp.size());
		for (int j = 0; j < temp.size(); j++)
		{
			Edge e=temp.get(j);
			neighborArray[j] = e.v2;	
		}
		return neighborArray;
	}
	
	// this function would return the weight of a particular edge
	public int getWeight(int v1, int v2)
	{
		int weight=0;
		LinkedList<Edge> temp =neighborAdjacencyList.get(v1); 
		for (int j = 0; j < temp.size(); j++)
		{
			Edge e=temp.get(j);
			if( v2 == e.v2)
			{
				weight = e.weight;				
			}
		}		
		return weight;
	}

	// Gets the size of the graph
	public int size()
	{
		return size;
	}	
}
