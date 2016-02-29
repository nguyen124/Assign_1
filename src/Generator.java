import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.TreeMap;

public class Generator {
	public static TreeMap<Integer, ArrayList<Integer>> generateGraph() {
		TreeMap<Integer, ArrayList<Integer>> adjList = new TreeMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for (int i = 0; i < 99999; i++) {
			if (PrimeService.isPrime(i)) {
				primes.add(i);
			}
		}
		for (int i = 0; i < primes.size(); i++) {
			int prime1 = primes.get(i);
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int j = 0; j < primes.size(); j++) {
				int prime2 = primes.get(j);
				if (PrimeService.isOneDigitAwayDiffer(prime1, prime2)) {
					temp.add(prime2);
				}
			}
			adjList.put(prime1, temp);
		}
		return adjList;
	}

	public static void writeGraphOfPrimesToFile(String outputPath) {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for (int i = 0; i < 99999; i++) {
			if (PrimeService.isPrime(i)) {
				primes.add(i);
			}
		}
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("output.txt")));
			for (int i = 0; i < primes.size(); i++) {
				int prime1 = primes.get(i);
				writer.write(prime1 + ", ");
				for (int j = 0; j < primes.size(); j++) {

					int prime2 = primes.get(j);
					if (PrimeService.isOneDigitAwayDiffer(prime1, prime2)) {
						writer.write(prime2 + ", ");
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
