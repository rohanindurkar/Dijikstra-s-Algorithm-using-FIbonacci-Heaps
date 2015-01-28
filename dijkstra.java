
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class dijkstra
{	
	public static void main(String []args) throws IOException
	{
		System.out.println("Menu");
		System.out.println("1.Random Mode(-r). Please enter the mode along with number of nodes, density and sorce vertex as arguments");
		System.out.println("2.File Mode. Please enter the (-s) for simple scheme and (-f) for Fibonacci scheme along with the file name as argument");
		int size=0;
		float density=0;
		int sourceNode =0;
		String choice = args[0];
		
		if(choice.equals("-r"))
		{
			size = Integer.parseInt(args[1]);
			density = Float.parseFloat(args[2]);
			sourceNode = Integer.parseInt(args[3]);
			int max =(size*(size-1))/2; // Maximum no. of edges
			density = (density*max)/100;		
			GenerateRandomGraph g= new GenerateRandomGraph(size, density, sourceNode);
			g.generateGraph();	
			//g.print();
			
			// Now  call the SPT function first using Simple scheme and then using Fibonacci heap
			System.out.println("Simple scheme");
			int temp[]=SPT.dijkstraRandomGraph(g, Integer.parseInt(args[1]), Integer.parseInt(args[2]),Integer.parseInt(args[3]));
			
			//Using Fibonacci scheme
			System.out.println("Fibonacci scheme");
			FibonacciSPT f = new FibonacciSPT(g.getNeighbourlist(),g.size());
			f.fDijikstraRandom(g.sourceNode);
		}
		
		// this is for the file mode
		else if (choice.equals("-s"))
			{
				InputFileGraph g= new InputFileGraph(args[1]);
				int tempSize = g.size;
				System.out.println("Simple scheme");
				int temp[]=SPT.dijkstraInputGraph(g, g.sourceNode);
			}
		else if (choice.equals("-f"))
			{
				//Using Fibonacci scheme
				InputFileGraph g= new InputFileGraph(args[1]);
				int tempSize = g.size;
				System.out.println("Fibonacci scheme");
				FibonacciSPT f = new FibonacciSPT(g.neighborAdjacencyList,g.size() );
				f.fDijikstraUserFile(g.sourceNode);				
			}
	}		
}
