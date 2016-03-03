import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.TreeMap;
// This class has function to generate the Graph of Prime Numbers
public class Generator {
	// This function generate the graph of prime numbers, using adjacent list
	// for example: 
	// 2, 3, 5, 7, 
	// 3, 2, 5, 7, 13, 23, 43, 53, 73, 83, 103, 503, 2003, 4003, 5003, 70003, 
	// 5, 2, 3, 7, 
	// 7, 2, 3, 5, 17, 37, 47, 67, 97, 107, 307, 607, 907, 4007, 6007, 9007, 10007, 90007, 
	// 11, 13, 17, 19, 31, 41, 61, 71, 211, 311, 811, 911, 2011, 3011, 5011, 6011, 8011, 9011, 20011, 30011, 90011, 
	// 13, 3, 11, 17, 19, 23, 43, 53, 73, 83, 113, 313, 613, 1013, 4013, 7013, 9013, 30013, 40013, 60013, 
	// 17, 7, 11, 13, 19, 37, 47, 67, 97, 317, 617, 2017, 8017, 60017, 90017, 
	// 19, 11, 13, 17, 29, 59, 79, 89, 419, 619, 719, 919, 1019, 3019, 4019, 7019, 70019, 90019,
	// ...
	// all digits after the first digit of every single line is 1 digit
	// away different with the first one
	public static TreeMap<Integer, ArrayList<Integer>> generateGraph() {
		
		// We using TreeMap data structure of java to represent graph
		// the key is prime and the value is the list of primes that are 1
		// digit away diffrent with the key
		TreeMap<Integer, ArrayList<Integer>> graph = new TreeMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> primes = new ArrayList<Integer>();
		
		// First we have to find a list of prime numbers from 1 to 100000
		for (int i = 0; i < PrimesGraph.LIMIT; i++) {
			if (PrimeService.isPrime(i)) {
				primes.add(i);
			}
		}
		
		// For every single pair of prime number
		// we check is they are 1 digit away by function isOneDigitAwayDiffer
		// If they are then we add them to the graph.
		for (int i = 0; i < primes.size(); i++) {
			int prime = primes.get(i);
			ArrayList<Integer> neighbours = new ArrayList<Integer>();
			for (int j = 0; j < primes.size(); j++) {
				int neighbour = primes.get(j);
				if (PrimeService.isOneDigitAwayDiffer(prime, neighbour)) {
					neighbours.add(neighbour);
				}
			}
			graph.put(prime, neighbours);
		}
		return graph;
	}
	
	// This is just a test function, write out the graph into the file 
	// to see if we construct the graph correctly or not
	public static void writeGraphOfPrimesToFile(String outputPath) {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for (int i = 0; i < PrimesGraph.LIMIT; i++) {
			if (PrimeService.isPrime(i)) {
				primes.add(i);
			}
		}
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("output.txt")));
			for (int i = 0; i < primes.size(); i++) {
				int prime = primes.get(i);
				writer.write(prime + ", ");
				for (int j = 0; j < primes.size(); j++) {

					int neighbour = primes.get(j);
					if (PrimeService.isOneDigitAwayDiffer(prime, neighbour)) {
						writer.write(neighbour + ", ");
					}
				}
				writer.write("\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
