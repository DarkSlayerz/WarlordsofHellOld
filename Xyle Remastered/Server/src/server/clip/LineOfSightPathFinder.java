package server.clip;
public class LineOfSightPathFinder {
	public Path findPath(int curX, int curY, int desX, int desY, int z) {
		if(curX == desX && curY == desY) {
			return null;
		}
		Path p = new Path();
		while(curX != desX || curY != desY) {
			int beforeX = curX;
			int beforeY = curY;
			if(curX > desX) {