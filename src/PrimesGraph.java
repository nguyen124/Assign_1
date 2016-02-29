import java.util.ArrayList;
import java.util.TreeMap;

public class PrimesGraph {
	public static int LIMIT = 100000;

	public enum Color {
		WHITE, BLACK, GRAY
	}

	TreeMap<Integer, ArrayList<Integer>> graph;

	public PrimesGraph() {
		graph = Generator.generateGraph();
	}

	public String findPath(int prime1, int prime2) {
		// visited = new TreeSet<Integer>();

		return breadthFirstSearch(prime1, prime2);
	}

	private String breadthFirstSearch(Integer prime1, Integer prime2) {

		TreeMap<Integer, Integer> predecessor = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> distance = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Color> color = new TreeMap<Integer, Color>();

		for (int key : graph.keySet()) {
			predecessor.put(key, -1);
			distance.put(key, -1);
			color.put(key, Color.WHITE);
		}
		color.put(prime1, Color.GRAY);
		distance.put(prime1, 0);
		ArrayList<Integer> queue = new ArrayList<Integer>();
		queue.add(prime1);
		while (queue.size() != 0) {
			Integer prime = queue.get(0);
			ArrayList<Integer> neighbours = graph.get(prime);
			for (int i = 0; i < neighbours.size(); i++) {
				Integer neighbor = neighbours.get(i);
				if (neighbor.equals(prime2)) {
					distance.put(neighbor, distance.get(prime) + 1);
					predecessor.put(neighbor, prime);
					color.put(neighbor, Color.GRAY);
					String path = "";
					Integer dis = distance.get(neighbor);
					while (predecessor.get(neighbor) != -1) {
						path += neighbor + " ";
						// dis += distance.get(neighbor);
						neighbor = predecessor.get(neighbor);
					}
					path += neighbor;
					return path + "\nSeperation: " + dis;
				} else if (color.get(neighbor) == Color.WHITE) {
					distance.put(neighbor, distance.get(prime) + 1);
					predecessor.put(neighbor, prime);
					color.put(neighbor, Color.GRAY);
					queue.add(neighbor);
				}
			}
			queue.remove(0);
			color.put(prime, Color.BLACK);
		}
		return "Can't find";
	}

	/*
	 * private ArrayList<Integer> getUnvisitedNeighbourPrimes(int prime) {
	 * ArrayList<Integer> neighbours = graph.get(prime); ArrayList<Integer>
	 * unvisited = new ArrayList<Integer>(); for (int i = 0; i <
	 * neighbours.size(); i++) { if (!isVisited(neighbours.get(i))) {
	 * unvisited.add(neighbours.get(i)); } } if (unvisited.size() != 0) { return
	 * unvisited; } return null; }
	 * 
	 * private void visit(int prime) { visited.add(prime); }
	 * 
	 * private boolean isVisited(int prime) { return visited.contains(prime); }
	 */

	public boolean search(int source, int target) {
		ArrayList<Integer> neighbours = getNeighbourPrimes(source);
		return neighbours.contains(target);
	}

	private ArrayList<Integer> getNeighbourPrimes(int prime) {
		if (PrimeService.isPrime(prime) && prime < LIMIT) {
			return graph.get(prime);
		}
		return null;
	}
}
