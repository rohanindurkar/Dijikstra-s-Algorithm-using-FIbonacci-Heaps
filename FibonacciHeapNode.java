public class FibonacciHeapNode
{
	 int data;               // Vertex number
	 FibonacciHeapNode child;       
	 FibonacciHeapNode leftSibling;       
	 FibonacciHeapNode rightSibling;       
	 FibonacciHeapNode parent;      
	 int degree;            
	 double key;               
	 boolean flag;           



	 public FibonacciHeapNode(int data , double key )
	 {
		 this.data=data;
		 this.key=key;
		 rightSibling=this;
		 leftSibling=this;
	 }

	 public double getKey()
	 {
		 return this.key;
	 }
}
