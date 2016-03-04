/*
 *  This is Main file which contains the main function - entry point of the program
 *  Authors: Hai Nguyen
 */

public class Main {
	public static PrimesGraph graph = null;

	public static void main(String[] args) {
		if (args.length == 2) {
			// if graph has not been initiated yet, then we construct one
			if (graph == null) {
				// System.out.println("Init Graph ... Please wait");
				graph = new PrimesGraph();
			}
			try {
				System.out.println(graph.findPath(Integer.parseInt(args[0]),
						Integer.parseInt(args[1])));
				
				// print out the maximum seperation
				//	System.out.println("Max seperation: " + graph.getMaxDistance());
				
				// check to see if all nodes are connected
				System.out.println("Are all nodes connected: "
						+ graph.areAllNodesConnected());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {
			System.exit(-1);
		}

	}
}
