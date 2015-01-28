/*SPT is the Simple Scheme implementation for Dijikstra's Algorithm. 
 * It is given the graph object along with the number of nodes, density and vertex as the input.
 * It would generate the shortest distance from the source node to all the nodes present in the graph. 
 */
import java.util.ArrayList;
import java.util.LinkedList;

 public class SPT 
 {  
     ArrayList<LinkedList<Edge>> neighbor = null;
     int size = 0;
     int sourceNode = 0;
     int density;
	 public SPT(ArrayList<LinkedList<Edge>> object, int numberOfNodes, int density, int vertex)
     {
    	 this.neighbor = object;
    	 this.size = numberOfNodes;
    	 this.density = density;
    	 this.sourceNode = vertex;
     }
	 
	// this method would return the vertex with minimum distance from the current node
     public static int minVertex (int [] distance, boolean [] v) 
     {
        int x = Integer.MAX_VALUE; // this would give the value infinity
        int flag = -1;   // graph not connected, or no unvisited vertices
        for (int i=0; i<distance.length; i++) 
        {
           if (!v[i] && distance[i]<x) 
           {
        	   flag=i;
        	   x=distance[i];
           }
        }
        return flag;
     }
	 
	 /* This would be used when the graph is inputed from a User file.
	  *  Dijkstra's algorithm to find shortest path from s to all other nodes.
	  */
	 public static int [] dijkstraInputGraph (InputFileGraph G, int s)
     {
        final int [] distanceMatrix = new int [G.size()];  // shortest known distance from "s"
        final int [] predecessorMatrix = new int [G.size()];  // preceding node in path
        final boolean [] visited = new boolean [G.size()]; // all false initially and it would keep a track of the nodes visited
  
        // this would initialize all the nodes at a distance of infinity
        for (int i=0; i<distanceMatrix.length; i++) 
        {
           distanceMatrix[i] = Integer.MAX_VALUE;
        }
        distanceMatrix[s] = 0;
  
        // this loop would add the next nearest node to the list of visited node
        for (int i=0; i<distanceMatrix.length; i++)
        {
           final int next = minVertex (distanceMatrix, visited);
           visited[next] = true;
  
           // The shortest path to next is distanceMatrix[next] and via predecessorMatrix[next].  
           final int [] n = G.arrayOfNeighbors(next);
           for (int j=0; j<n.length; j++) 
           {
              final int v = n[j];
              final int d = distanceMatrix[next] + G.getWeight(next,v);
              if (distanceMatrix[v] > d)
              {
                 distanceMatrix[v] = d;
                 predecessorMatrix[v] = next;
              }
           }
        }
        
        // this displays the distance of source node to the other nodes
        for (int i =0; i < distanceMatrix.length; i++)
 		{
 			System.out.println(distanceMatrix[i]+"//Cost from node "+ s +" to " + i);
 		}
        return predecessorMatrix;  
     }
      
     
	 /* This would be used when the graph is generated from the Generate Random Graph function and inputted.
	  *  Dijkstra's algorithm to find shortest path from s to all other nodes.
	  */
	 public static int [] dijkstraRandomGraph (GenerateRandomGraph G, int nNodes, int density, int sourceVertex)
     {
        final int [] distanceMatrix = new int [G.size()];  // shortest known distance from "s"
        final int [] predecessorMatrix = new int [G.size()];  // preceding node in path
        final boolean [] visited = new boolean [G.size()]; // all false initially and it would keep a track of the nodes visited
        long startTime1 = System.currentTimeMillis();
        for (int i=0; i<distanceMatrix.length; i++) 
        {
           distanceMatrix[i] = Integer.MAX_VALUE;
        }
        distanceMatrix[sourceVertex] = 0;
  
        // this loop would add the next nearest node to the list of visited node
        for (int i=0; i<distanceMatrix.length; i++)
        {
           final int next = minVertex (distanceMatrix, visited);
           visited[next] = true;
  
           // The shortest path to next is distanceMatrix[next] and via predecessorMatrix[next].  
           final int [] n = G.adjNeighbors(next);
           for (int j=0; j<n.length; j++) 
           {
              final int v = n[j];
              final int d = distanceMatrix[next] + G.getWeight(next,v);
              if (distanceMatrix[v] > d)
              {
                 distanceMatrix[v] = d;
                 predecessorMatrix[v] = next;
              }
           }
        }
        
        long endTime1   = System.currentTimeMillis();
        long totalTime1 = endTime1 - startTime1;
		System.out.println("Simple heap run time:- "+totalTime1+" Milliseconds");
        
		// this displays the distance of source node to the other nodes
        for (int i =0; i < distanceMatrix.length; i++)
 		{
 			System.out.println(distanceMatrix[i]+"//Cost from node "+ sourceVertex +" to" + i);
 		}
        //System.out.println("Simple heap run time:- "+totalTime1+" Milliseconds");
        return predecessorMatrix; 
        
     }   
  }
