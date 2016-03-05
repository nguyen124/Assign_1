import java.util.Scanner;

/*
 *  This is Main file which contains the main function - entry point of the program.
 *  I create the graph as a static value and just initiate it for the first time of
 *  running program. This runs just fine in the Interactive Eclipse SDK with infinite 
 *  loop. But when I run the program by the command line using Echo command and Pipe,
 *  it can not pipe the parameters to infinite loop of program. It runs just 1 time,
 *  print out the result and stop.  
 *  
 *  Authors: Hai Nguyen
 */

public class Main {
	private static PrimesGraph graph = null;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String[] parameters;
		
		if (graph == null) {
			// System.out.println("Init Prime Numbers Graph for the first time
			// ... Please wait");
			graph = new PrimesGraph();
			// System.out.println("Done!");
		}
		
		do {
			// System.out.println("Please input 2 primes");
			parameters = scanner.nextLine().split(" ");
			if (parameters.length == 2) {
				try {
					int prime1 = Integer.parseInt(parameters[0]);
					int prime2 = Integer.parseInt(parameters[1]);
					if (PrimeService.isPrime(prime1) && PrimeService.isPrime(prime2)) {
						if (0 < prime1 && prime1 < PrimesGraph.LIMIT && 0 < prime2 && prime2 < PrimesGraph.LIMIT) {
							System.out.println(graph.findPath(prime1, prime2));
						} else {
							System.out.println("Please input prime number between 1 to " + PrimesGraph.LIMIT + " only");
						}
					} else {
						System.out.println("Please input prime number only");
					}
				} catch (Exception e) {
					System.out.println("Please input number correctly");
				}
			} else {
				break;
			}
		} while (scanner.hasNext());

		scanner.close();
		// System.out.println("Goodbye!");
	}
}
