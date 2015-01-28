/*  This class is used to generate a graph containing random numbers when the size, density and number of nodes are given
 *  The output of this file would be the graph with the given specifications. 
 */

import java.util.*;

public class GenerateRandomGraph 
{
	private int nodes;
	float density;
	int sourceNode;
	private int edgeConnectivityMatrix [] [] = null; 
	ArrayList<LinkedList<Edge>> neighborAdjacencyList= null; // neighbor lists
	public GenerateRandomGraph(int size, float density, int sourceNode)
	{
		this.nodes= size;
		this.density=density;
		this.sourceNode = sourceNode;
		neighborAdjacencyList = new ArrayList<LinkedList<Edge>>(nodes);
		edgeConnectivityMatrix= new int[nodes] [nodes];
		//System.out.println(density);
		for (int i=0;i<nodes;i++) 
		{
			neighborAdjacencyList.add(new LinkedList<Edge>());
		}
			
	}

	public void generateGraph()
	{
		Random random = new Random();		
		// initialize all the elements of the matrix to 0
		for (int i=0; i < nodes; i++)
		{
			   for (int j=0; j < nodes; j++)
			   {
			      edgeConnectivityMatrix[i][j] = 0;
			   }
		}
		do
		{
			int v1=0;
			int v2=0;
			int weight=0;
			flush();
			int edgeCounter=0;
			while(edgeCounter<density)
			{
				//System.out.println(""+density);
				v1=random.nextInt(nodes);
				v2=random.nextInt(nodes);
				// this would check if the pair of given vertices is connected or not
				if ( (v1 > 0) && (v2 > 0))
				{
					int buffer = v2;
					v2 = buffer;
				}
				if(v1!=v2 && edgeConnectivityMatrix[v1][v2]==0)
				{
					if( !(v1==v2) )
					{
						weight= random.nextInt(1000) + 1;
						Edge e1 = new Edge(v1, v2, weight);
						neighborAdjacencyList.get(v1).add(e1);
						Edge e2 = new Edge(v2, v1, weight);
						neighborAdjacencyList.get(v2).add(e2);
						edgeConnectivityMatrix[v1][v2]=1;
						edgeConnectivityMatrix[v2][v1]=1;
						edgeCounter++;
					}
				}
			}

		}while(!isConnected());
	}

	public void flush()
	{
		for(int i=0;i<nodes;i++)
		{
			neighborAdjacencyList.get(i).clear();
		}
	}


	public boolean isConnected()
	{  
		int[] a= new  int[nodes];
		for(int i=0;i<nodes;i++)  
		{
			a[i]=0;
		}
		
		for(int i=0;i<nodes;i++)
		{
			LinkedList<Edge> test= neighborAdjacencyList.get(i);
			if(test.isEmpty()) 
			{
				return false;
			}
				
			a[i]=1;
			for(int j=0;j<test.size();j++)
			{
				Edge e=test.get(j);
				a[e.v2]=1;
			}
		}

		for(int i=0;i<nodes;i++)
		{
			if(a[i]==0) 
			{
				return false;
			}
		}
		return true;

	}

	public void print()
	{
		int c=0;
		for(int i=0;i<nodes;i++)
		{
			LinkedList<Edge> temp =neighborAdjacencyList.get(i);
			for(int j=0;j<temp.size();j++)
			{
				Edge e=temp.get(j);
				System.out.println(e.v1+" "+e.v2+" "+e.weight);
				c++;
			}
		}
		System.out.println("Count=" + c);
	}


	public ArrayList<LinkedList<Edge>> getNeighbourlist()
	{
		ArrayList<LinkedList<Edge>> temporaryList = new ArrayList<LinkedList<Edge>>();

		for(int i=0;i<nodes;i++)
		{
			LinkedList<Edge> temp1 = new LinkedList<Edge>();
			for(int j=0;j<neighborAdjacencyList.get(i).size();j++)
			{
				Edge temp2 = new Edge(neighborAdjacencyList.get(i).get(j).v1,neighborAdjacencyList.get(i).get(j).v2,neighborAdjacencyList.get(i).get(j).weight);
				temp1.add(temp2);
			}
			temporaryList.add(temp1);
		}
		return temporaryList;
	}
	public int[][] getMatrix()
	{
		return edgeConnectivityMatrix;
	}
	public int size()
	{
		return nodes;
	}
	public int [] adjNeighbors(int v)
	{		
		LinkedList<Edge> temp =neighborAdjacencyList.get(v);
		// I am not sure whether the size of the linked list is correct. 
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
}
