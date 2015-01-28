# Dijikstra-s-Algorithm-using-FIbonacci-Heaps
COMPILING INSTRUCTIONS:

IDE used: Eclipse Java EE IDE for Web Developers. Version: Indigo Service Release 2. Java version 7 update 51. 

To run in random mode:
Go to Package Explorer-> Choose the project “dijikstra” -> Run as ->Run Configuration -> Arguments and customize it -> Apply -> Run
In the Arguments section, type -r n d x (e.g. -r 1000 10 0) to run the program in the Random mode. The first argument will choose the mode, the second argument would be the number of nodes/ vertices, the third argument would be the density of the edges and the fourth argument will be the source node. In this mode, the execution of both the Simple scheme and Fibonacci scheme will take place and you will get the execution time for both the algorithms.

To run in user input mode:
 Go to Package Explorer-> Choose the project “dijikstra” -> Run as ->Run Configuration -> Arguments and customize it -> Apply -> Run
For simple Dijikstra:
In the Arguments section, type –s filename (e.g. –s graph.txt) to run the program for simple scheme in the user input mode. This mode will give the execution time of the program for the simple scheme only.

For Fibonacci Dijikstra:
In the Arguments section, type –f filename (e.g. –f graph.txt) to run the program for Fibonacci scheme in the user input mode. This mode will give you the execution time of the program for the Fibonacci scheme only.
