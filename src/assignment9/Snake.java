package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;

	public Snake() {
		segments = new LinkedList<>(); // Set up the segments list
		deltaX = 0;
		deltaY = 0;

		// Add initial head segment at center
		BodySegment head = new BodySegment(0.5, 0.5, SEGMENT_SIZE);
		segments.add(head);
	}

	public void changeDirection(int direction) {
		if (direction == 1) { // up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { // down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { // left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { // right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}

	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		if (deltaX == 0 && deltaY == 0) return; // Don't move if there's no direction

		// Save current head position
		BodySegment head = segments.getFirst();
		double newX = head.getX() + deltaX;
		double newY = head.getY() + deltaY;

		// Add new head at new position
		BodySegment newHead = new BodySegment(newX, newY, SEGMENT_SIZE);
		segments.addFirst(newHead);

		// Remove the last segment to simulate movement
		segments.removeLast();
	}

	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (BodySegment s : segments) {
			s.draw();
		}
	}

	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eat(Food  f) {
		BodySegment head = segments.getFirst();
		double dx = head.getX() - f.getX();
		double dy = head.getY() - f.getY();
		double distance = Math.sqrt(dx * dx + dy * dy);

		if (distance <= SEGMENT_SIZE + Food.FOOD_SIZE) {
			// Grow by adding a segment at the same position as the tail
			BodySegment tail = segments.getLast();
			BodySegment newSegment = new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE);
			segments.addLast(newSegment);
			return true;
		}
		return false;
	}

	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInBounds() {
		BodySegment head = segments.getFirst();
		double x = head.getX();
		double y = head.getY();
		double size = head.getSize();

		return (x - size >= 0) && (x + size <= 1.0) && (y - size >= 0) && (y + size <= 1.0);
	}
}