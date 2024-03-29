package server.clip;
import java.util.Deque;import java.util.LinkedList;
/** * Represents a path found by a <code>PathFinder</code> between two points. * @author Graham Edgecombe
 */public class Path {
	/**
	 * The queue of points.	 */	private Deque<Point> tiles = new LinkedList<Point>();
	/**	 * Creates an empty path.	 */	public Path() {	}
	/**
	 * Adds a point onto the queue.
	 * @param p The point to add.
	 */	public void addPoint(Point p) {		tiles.add(p);	}
	/**	 * Removes the last point in the queue.	 */	public void removeLast() {		tiles.removeLast();	}
	/**
	 * Gets the deque backing this path.	 * @return The deque backing this path.	 */	public Deque<Point> getPoints() {		return tiles;	}	
}
