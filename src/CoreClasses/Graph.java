package CoreClasses;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph<K> {
	private class Node {
		private K destination;
		private String direction; // can be equal to UP - DOWN - LEFT - RIGHT
		private K key;
		/**
		 * if set to true means unidirectional : meaning you can go only from one node
		 * to another. if set to false meaning bidirectional : you can go from a node to
		 * another and vice versa
		 */
		private Boolean unidirectional;

		/**
		 * The constructor of my Node.
		 * 
		 * @param theKey           : the room am in at the moment
		 * @param theDestination   : destination room
		 * @param theDirection     : in which direction my destination is can be right -
		 *                         left - down - up
		 * @param isUnidirectional : wanted to use this to set traps, meaning if this is
		 *                         set to true the relationship between the two node is
		 *                         Unidirectional, this is used only at the start so I
		 *                         don't let the user go back to the start node
		 */
		public Node(K theKey, K theDestination, String theDirection, Boolean isUnidirectional) {
			key = theKey;
			destination = theDestination;
			direction = theDirection;
			unidirectional = isUnidirectional;
		}

		public boolean equals(Node firstNode) {
			if (key.equals(firstNode)) {
				return true;
			}
			return false;
		}

		public K getDestination() {
			return destination;
		}

		public String getDirection() {
			return direction;
		}

		public String toString() {
			return "{ the key : " + key + " ; the destination : " + destination + " ; Direction : " + direction + " }";
		}
	}

	private Map<K, LinkedList<Node>> graph = null;

	public Graph() {
		graph = new HashMap<>();
	}

	/**
	 * 
	 * addEdge with four arguments will add a relation from one direction (Trap in
	 * the game) - the objective of this method is when a user open a door it can
	 * either be a Boss room(Trap) or a regular room. Update: couldn't implement the
	 * trap I only used it at the start Node - at first I create the node if it is
	 * not yet created - after the two if statements, I create the relationship
	 * between the two nodes
	 * 
	 * @param firstNode
	 * @param secondNode
	 * @param direction      : can be right left top down
	 * @param unidirectional : create one sided direction if this is set to true
	 */
	public void addEdge(K firstNode, K secondNode, String direction, Boolean unidirectional) {
		if (!graph.containsKey(firstNode)) {
			graph.put(firstNode, new LinkedList<>());
		}
		if (!graph.containsKey(secondNode)) {
			graph.put(secondNode, new LinkedList<>());
		}
		graph.get(firstNode).add(new Node(firstNode, secondNode, direction, unidirectional));

	}

	/**
	 * addEdge with tree arguments will add relation from both Nodes(Vertex)
	 * 
	 * @param firstNode
	 * @param secondNode
	 * @param direction
	 */
	public void addEdge(K firstNode, K secondNode, String direction) {
		addEdge(firstNode, secondNode, direction, false);
		switch (direction) {
		case "UP":
			addEdge(secondNode, firstNode, "DOWN", false);
			break;
		case "DOWN":
			addEdge(secondNode, firstNode, "UP", false);
			break;
		case "LEFT":
			addEdge(secondNode, firstNode, "RIGHT", false);
			break;
		case "RIGHT":
			addEdge(secondNode, firstNode, "LEFT", false);
			break;
		}

	}

	/**
	 * 
	 * we check if between the node firstNode - secondNode there is an edge :
	 * connection between them.
	 * 
	 * @param firstNode
	 * @param secondNode
	 * @return
	 */
	public boolean hasEdge(K firstNode, K secondNode) {
		if (graph.containsKey(firstNode)) {
			LinkedList<Node> temp = graph.get(firstNode);
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).getDestination() == secondNode) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * In this function I will find the direction in a specific key (node) for
	 * example : in the node (2,0) we can go Left, and Up meaning north and west
	 * 
	 * @param node      : we are interested in the Direction of this Node
	 * @param direction (LEFT or RIGHT or UP or DOWN)
	 * @return
	 */
	public K goDirection(K node, String direction) {
		if (graph.containsKey(node)) {
			LinkedList<Node> temp = graph.get(node);
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).getDirection() == direction) {// there is a direction called SOUTH for example
					return temp.get(i).getDestination();
				}
			}
		}
		return null;
	}

	/**
	 * Creating the map of the game
	 * 
	 * @return
	 */
	public Graph buildMap() {
		Graph<String> g = new Graph<>();
		g.addEdge("Start", "(0,1)", "RIGHT", true);// unidirectional

		g.addEdge("(0,1)", "(1,1)", "RIGHT");
		g.addEdge("(1,1)", "(2,1)", "RIGHT");
		g.addEdge("(2,2)", "Finish", "UP");

		g.addEdge("(0,0)", "(1,0)", "RIGHT");
		g.addEdge("(1,0)", "(2,0)", "RIGHT");

		g.addEdge("(0,2)", "(1,2)", "RIGHT");
		g.addEdge("(1,2)", "(2,2)", "RIGHT");

		g.addEdge("(0,1)", "(0,2)", "UP");
		g.addEdge("(0,1)", "(0,0)", "DOWN");

		g.addEdge("(1,1)", "(1,2)", "UP");
		g.addEdge("(1,1)", "(1,0)", "DOWN");

		g.addEdge("(2,1)", "(2,2)", "UP");
		g.addEdge("(2,1)", "(2,0)", "DOWN");

		return g;
	}

	public String toString() {
		String s = "";
		for (K key : graph.keySet()) {
			s += key + " ==> [ " + graph.get(key) + " ]\n";
		}
		return s;

	}

}