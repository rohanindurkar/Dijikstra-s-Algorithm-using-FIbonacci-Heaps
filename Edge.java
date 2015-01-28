/*This class is used to create an Edge which would be used for creating an Edge between any 2 nodes of the graph.
 * It has the inputs as vertex 1, vertex 2 and a weight which is the cost/ distance between the 2 nodes. 
 * @Author: Rohan Indurkar
 *  */ 
public class Edge 
{
		 int v1;
		 int v2;
		 int weight;

		public Edge(int v1, int v2, int cost)
		{
			this.v1 = v1;
			this.v2 = v2;
			weight = cost;
		}
}
