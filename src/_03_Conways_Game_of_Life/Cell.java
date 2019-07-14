package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Graphics;

public class Cell implements Drawable {
	public boolean isAlive = false;

	private int x;
	private int y;

	private int cellSize;

	public Cell(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.cellSize = size;
	}

	// 11. Complete the liveOrDie method
	// It sets isAlive to true or false based on the neighbors and
	// the rules of the game
	/*
	 * 1. Any live cell with fewer than two live neighbors dies, as if caused by
	 * under population. 2. Any live cell with two or three live neighbors lives on
	 * to the next generation. 3. Any live cell with more than three live neighbors
	 * dies, as if by over population. 4. Any dead cell with exactly three live
	 * neighbors becomes a live cell, as if by reproduction. (source: Wikipedia)
	 */
	public void liveOrDie(int numNeighbors) {
		if (isAlive == true) {
			if (numNeighbors < 2) {
				isAlive = false;
			} else if (numNeighbors == 2 || numNeighbors == 3) {
				isAlive = true;
			} else if (numNeighbors > 3) {
				isAlive = false;
			}
		} else {
			if (isAlive == false && numNeighbors == 3) {
				isAlive = true;
			}
		}
		
			System.out.println(numNeighbors);


	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// 12. Complete the draw method.
	// It draws a colored square if cell is alive
	// draws empty square if cell is dead
	@Override
	public void draw(Graphics g) {
		if (isAlive == true) {
			g.setColor(Color.blue);
			g.fillRect(x, y, cellSize, cellSize);
		} else {
			g.setColor(Color.gray);
			g.fillRect(x, y, cellSize, cellSize);
		}

		g.setColor(Color.black);
		g.drawRect(x, y, cellSize, cellSize);

	}
}
