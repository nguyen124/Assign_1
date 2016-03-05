
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
	}

	/*
	 * This fuction return the path between 2 primes 
	 * 
	 * Input: Integer, Integer
	 * Output: The path
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
	 * Input: Integer, Integer 
	 * Output: String
	 */
	public String breadthFirstSearch(Integer prime1, Integer prime2) {

		// This variable is used to traced back to the first prime when
		// we hit the second prime
		TreeMap<Integer, Integer> predecessor = new TreeMap<Integer, Integer>();
		// This variable is used to see how far we've gone away from the source
		TreeMap<Integer, Integer> distance = new TreeMap<Integer, Integer>();
		// This variable is used to mark the node of graph. Each node is 1 prime
		// number. The node that has all neighbours checked will be label as
		// black node so we don't have to visit it again. The neighbour will be label as a
		// gray node, it means that we're going to check its neighbours. The left
		// nodes will be labeled as white node, it means we haven't done anything with
		// them yet.
		TreeMap<Integer, Color> color = new TreeMap<Integer, Color>();

		// if 2 primes are identical then return Separation 0
		if (prime1 == prime2) {
			return "Separation: 0";
		}
		// we initiate values
		for (int key : graph.keySet()) {
			predecessor.put(key, -1);
			distance.put(key, 0);
			color.put(key, Color.WHITE);
		}
		// the source is put into gray because it's going to be checked
		// for its neighbours
		color.put(prime1, Color.GRAY);
		distance.put(prime1, 0);
		// This queue will hold the gray nodes
		ArrayList<Integer> queue = new ArrayList<Integer>();
		queue.add(prime1);
		// While all gray nodes has not been checked yet then

		while (queue.size() != 0) {
			Integer prime = queue.get(0);
			ArrayList<Integer> neighbours = graph.get(prime);
			for (int i = 0; i < neighbours.size(); i++) {
				Integer neighbor = neighbours.get(i);
				// Scan the neighbout of gray node. If the destination node
				// is hit. Then tracked back to the start node and count the
				// distance from start to destination.
				if (neighbor.equals(prime2)) {
					distance.put(neighbor, distance.get(prime) + 1);
					predecessor.put(neighbor, prime);
					color.put(neighbor, Color.GRAY);

					Integer dis = distance.get(neighbor);
					// traced back the predessor when we hit the destination
					while (predecessor.get(neighbor) != -1) {
						// path += neighbor + " ";
						neighbor = predecessor.get(neighbor);
					}
					// path += neighbor;
					return "Separation: " + dis;

				} // else we keep bring all white neighbour nodes into gray
				else if (color.get(neighbor) == Color.WHITE) {
					int tempDistance = distance.get(prime) + 1;
					distance.put(neighbor, tempDistance);
					predecessor.put(neighbor, prime);
					color.put(neighbor, Color.GRAY);
					queue.add(neighbor);
				}
			}
			// After 1 node has fully checked its neigbours, we turn
			// it into blacked so we don't have to check it again
			queue.remove(0);
			color.put(prime, Color.BLACK);
		}
		// if those 2 nodes are not connected then we return Impossible
		return "Impossible";
	}

	/*
	 * For each node of the graph, we begin to travel the whole graph from that
	 * node to see how far we can go. The possible maxium serperation is the
	 * furthest distance we can go 
	 * 
	 * Input: void 
	 * Output: int
	 */
	public int getMaxDistance() {
		// This variable is used to see how far we've gone away from the source
		TreeMap<Integer, Integer> distance = new TreeMap<Integer, Integer>();
		// This variable is used to mark the node of graph. Each node is 1 prime
		// number The node that has all neighbours checked will be label as
		// black node so we don't have to visit it again. The neighbour will be
		// label as a gray node, it means that we're going to check its
		// neighbours. The left nodes will be labeled as white node, it means we
		// haven't done anything with them yet.
		TreeMap<Integer, Color> color = new TreeMap<Integer, Color>();
		// we initiate values

		// the source is put into gray because it's going to be checked
		// for its neighbours

		// Let 2 is the start node
		int max = 0;
		for (int key : graph.keySet()) {
			for (int k : graph.keySet()) {
				distance.put(k, 0);
				color.put(k, Color.WHITE);
			}
			Integer start = key;
			// Integer start = 79589;
			color.put(start, Color.GRAY);
			distance.put(start, 0);
			// This queue will hold the gray nodes
			ArrayList<Integer> queue = new ArrayList<Integer>();
			queue.add(start);
			// Max Separation variable initialized by -1
			int maxSeparation = 0;
			// While all gray nodes has not been checked yet then
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
				// After 1 node has fully checked its neigbours, we turn
				// it into blacked so we don't have to check it again
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
	 * Input: void
	 * Output: boolean
	 */
	public boolean areAllNodesConnected() {
		// This variable is used to mark the node of graph. Each node is 1 prime
		// number. The node that has all neighbours checked will be label as black node
		// so we don't have to visit it again. The neighbour will be label as a gray
		// node, it means that we're going to check its neighbours. The left nodes will
		// be labeled as white node, it means we haven't done anything with
		// them yet.
		TreeMap<Integer, Color> color = new TreeMap<Integer, Color>();
		// we initiate values
		for (int key : graph.keySet()) {
			color.put(key, Color.WHITE);
		}
		// the source is put into gray because it's going to be checked
		// for its neighbours

		// Let 2 is the start node
		Integer start = 2;
		color.put(start, Color.GRAY);
		// This queue will hold the gray nodes
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
