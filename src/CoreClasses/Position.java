/**
 *  I do all the processing in my Graph class.
 *  the objective of this class is to make things simpler and introduce the position of the player ..
 *  - Move (Up-Down-Left-Right)
 *  - Avoid going out of the map
 */
package CoreClasses;

public class Position {
	private String previousPosition;
	private String actualPosition;
	private Graph<String> graph;

	public Position() {
		this("Start");
	}

	/**
	 * Constructor of the Position class, I want to use actualPosition -
	 * previousPosition to detect if the player changed position, or he is still in
	 * the same room.
	 * 
	 * @param theActualPosition : updated position
	 */
	public Position(String theActualPosition) {
		actualPosition = theActualPosition;
		previousPosition = theActualPosition;
		graph = new Graph<String>();
		graph = graph.buildMap();
	}

	public String getActualPosition() {
		return actualPosition;
	}

	public void setPreviousPosition(String position) {
		previousPosition = position;
	}

	public String getPreviousPosition() {
		return previousPosition;
	}

	public void movePlayer(String direction) {

		String temp = (String) graph.goDirection(actualPosition, direction);
		if (temp != null) {
			previousPosition = actualPosition; // in order to detect if the player has moved from a room to another ..
			actualPosition = temp;
		}
	}

	/**
	 * Will only move if there is a path + no edges avoid jumping out of the map
	 */
	public void goLeft() {
		movePlayer("LEFT");
	}

	public void goRight() {
		movePlayer("RIGHT");
	}

	public void goUp() {
		movePlayer("UP");
	}

	public void goDown() {
		movePlayer("DOWN");
	}

	public String toString() {
		return "Your position: " + actualPosition + "\n" + graph;
	}

}
