package assignment9;

import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Game {

	private Snake snake;
	private Food food;

	public Game() {
		StdDraw.enableDoubleBuffering();

		// Construct new Snake and Food objects
		snake = new Snake();
		food = new Food();
	}

	public void play() {
		while (snake.isInBounds()) { // End game if snake goes out of bounds
			int dir = getKeypress();

			// 1. Pass direction to your snake
			if (dir != -1) {
				snake.changeDirection(dir);
			}

			// 2. Tell the snake to move
			snake.move();

			// 3. If the food has been eaten, make a new one
			if (snake.eat(food)) {
				food = new Food();
			}

			// 4. Update the drawing
			updateDrawing();
		}

		// Optional: Game over screen
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.5, 0.5, "Game Over!");
		StdDraw.show();
	}

	private int getKeypress() {
		if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}

	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		StdDraw.clear();        // 1. Clear screen
		snake.draw();           // 2. Draw snake
		food.draw();            // 2. Draw food
		StdDraw.pause(50);      // 3. Pause (50 ms)
		StdDraw.show();         // 4. Show
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
