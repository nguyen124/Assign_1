
/*
 * This file contain the functions related to the graph of primes
 * Author: Hai Nguyen, Mohammed Shaikh, Gurupur Prashant Prabhu, Harika Kopparapu
 */
import java.util.ArrayList;
import java.util.TreeMap;

public class PrimesGraph {
	public static final int LIMIT = 100000;

	public enum Color {
		WHITE, BLACK, GRAY
	}

	TreeMap<Integer, ArrayList<Integer>> graph;

	public PrimesGraph() {
		graph = Generator.generateGraph();
		//Generator.writeGraphOfPrimesToFile();
	}

	/*
	 * This fuction return the path between 2 primes
	 * 
	 * Input: Integer, Integer Output: The path
	 */
	public String findPath(int prime1, int prime2) {
		// visited = new TreeSet<Integer>();
		return breadthFirstSearch(prime1, prime2);

	}

	/*
	 * apply BFS in finding shortest path between 2 primes. Because 2 adjacent
	 * prime numbers are 1 digit away different, we consider that 1 digit is 1
	 * unit of distance between 2 prime numbers . For that reason, to find the
	 * shortest path between 2 prime numbers we just need to scan every
	 * connected neighbors of 1 prime recursively until we hit the second prime
	 * number. The function return "Impossible" if 2 primes are not connected.
	 * 
	 * Input: Integer, Integer Output: String
	 */
	public String breadthFirstSearch(Integer prime1, Integer prime2) {
		TreeMap<Integer, Integer> predecessor = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> distance = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Color> color = new TreeMap<Integer, Color>();
		if (prime1 == prime2) {
			return "Separation: 0";
		}
		for (int key : graph.keySet()) {
			predecessor.put(key, -1);
			distance.put(key, 0);
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
				// Scan the neighbout of gray node. If the destination node is
				// hit
				if (neighbor.equals(prime2)) {
					distance.put(neighbor, distance.get(prime) + 1);
					predecessor.put(neighbor, prime);
					color.put(neighbor, Color.GRAY);
					Integer dis = distance.get(neighbor);
					//String tracePath = "";
					while (predecessor.get(neighbor) != -1) {
						//tracePath += neighbor + " ";
						neighbor = predecessor.get(neighbor);						
					}
					//tracePath+=neighbor;
					return "Separation: " + dis;//+"\n"+tracePath;

				} // else we keep bring all white neighbour nodes into gray
				else if (color.get(neighbor) == Color.WHITE) {
					int tempDistance = distance.get(prime) + 1;
					distance.put(neighbor, tempDistance);
					predecessor.put(neighbor, prime);
					color.put(neighbor, Color.GRAY);
					queue.add(neighbor);
				}
			}
			queue.remove(0);
			color.put(prime, Color.BLACK);
		}
		return "Impossible";
	}

	/*
	 * For each node of the graph, we begin to travel the whole graph from that
	 * node to see how far we can go. The possible maxium serperation is the
	 * furthest distance we can go
	 * 
	 * Input: void Output: int
	 */
	public int getMaxDistance() {
		TreeMap<Integer, Integer> distance = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Color> color = new TreeMap<Integer, Color>();
		// Let 2 is the start node
		int max = 0;
		for (int key : graph.keySet()) {
			for (int k : graph.keySet()) {
				distance.put(k, 0);
				color.put(k, Color.WHITE);
			}
			Integer start = key;
			color.put(start, Color.GRAY);
			distance.put(start, 0);
			ArrayList<Integer> queue = new ArrayList<Integer>();
			queue.add(start);
			int maxSeparation = 0;
			while (queue.size() != 0) {
				Integer prime = queue.get(0);
				ArrayList<Integer> neighbours = graph.get(prime);
				for (int i = 0; i < neighbours.size(); i++) {
					Integer neighbor = neighbours.get(i);
					if (color.get(neighbor) == Color.WHITE) {
						int tempDistance = distance.get(prime) + 1;
						if (tempDistance > maxSeparation) {
							maxSeparation = tempDistance;
						}
						distance.put(neighbor, tempDistance);
						color.put(neighbor, Color.GRAY);
						queue.add(neighbor);
					}
				}
				queue.remove(0);
				color.put(prime, Color.BLACK);
				if (max < maxSeparation) {
					max = maxSeparation;
				}
			}
		}
		return max;
	}

	/*
	 * Check to see if all nodes are connected or not by counting all black
	 * nodes.if the total of all black nodes is not equal to the number of nodes
	 * then there is some pair of nodes which are not connected
	 * 
	 * Input: void Output: boolean
	 */
	public boolean areAllNodesConnected() {
		TreeMap<Integer, Color> color = new TreeMap<Integer, Color>();
		for (int key : graph.keySet()) {
			color.put(key, Color.WHITE);
		}
		// Let 2 is the start node
		Integer start = 2;
		color.put(start, Color.GRAY);
		ArrayList<Integer> queue = new ArrayList<Integer>();
		queue.add(start);
		// While all gray nodes has not been checked yet then
		while (queue.size() != 0) {
			Integer prime = queue.get(0);
			ArrayList<Integer> neighbours = graph.get(prime);
			for (int i = 0; i < neighbours.size(); i++) {
				Integer neighbor = neighbours.get(i);
				if (color.get(neighbor) == Color.WHITE) {
					color.put(neighbor, Color.GRAY);
					queue.add(neighbor);
				}
			}
			queue.remove(0);
			color.put(prime, Color.BLACK);
		}
		if (color.size() == this.getNumberOfNodes()) {
			return true;
		}
		return false;
	}

	public int getNumberOfNodes() {
		if (graph != null) {
			return graph.size();
		}
		return 0;
	}
}
