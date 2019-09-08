package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	public static Cell startCell;
	public static Random cell=new Random();
	
	public static int randomUnvisited;
	public static Cell randomCell;

	public static ArrayList<Cell>unvisited;
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		
		startCell=new Cell(cell.nextInt(width), cell.nextInt(height));
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(startCell);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. check for unvisited neighbors using the cell
		
		unvisited=getUnvisitedNeighbors(currentCell);
		System.out.println(unvisited.size());
		//C. if has unvisited neighbors,
		if (unvisited.size()>0) {
			//C1. select one at random.
			randomUnvisited=cell.nextInt(unvisited.size());
			//C2. push it to the stack
			randomCell=unvisited.get(randomUnvisited);
			uncheckedCells.push(randomCell);
			
			//C3. remove the wall between the two cells
			removeWalls(currentCell, randomCell);
			//C4. make the new cell the current cell and mark it as visited
			currentCell=randomCell;
			currentCell.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(randomCell);
			}
		//D. if all neighbors are visited
		if(unvisited.size()==0) {
			
		
			//D1. if the stack is not empty
			if (uncheckedCells.isEmpty()==false) {
				
			
				// D1a. pop a cell from the stack
				
				//uncheckedCells.pop();
				
				// D1b. make that the current cell
				currentCell=uncheckedCells.pop();
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		//System.out.println(c1.getX());
		
		
		if (c1.getX()==c2.getX()-1) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		}
		else if(c1.getX()==c2.getX()+1) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		}
		else if(c1.getY()==c2.getY()-1) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}
		else if(c1.getY()==c2.getY()+1) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		//create new ArrayList
		ArrayList<Cell>notVisited=new ArrayList<Cell>();
		int cellX=c.getX();
		int cellY=c.getY();
		//System.out.println(cellX);
		//System.out.println(cellY);
		
		if (cellX>0) {
			if (Maze.cells[cellX-1][cellY].hasBeenVisited()==false) {
				
				notVisited.add(Maze.cells[cellX-1][cellY]);
			}
		}
		if (cellY>0) {
			if (Maze.cells[cellX][cellY-1].hasBeenVisited()==false) {
				
				notVisited.add(Maze.cells[cellX][cellY-1]);
			}
		}
		if (cellX<width-1) {
			if (Maze.cells[cellX+1][cellY].hasBeenVisited()==false) {
				
				notVisited.add(Maze.cells[cellX+1][cellY]);
			}
		}
		if (cellY<height-1) {
			if (Maze.cells[cellX][cellY+1].hasBeenVisited()==false) {
				
				notVisited.add(Maze.cells[cellX][cellY+1]);
			}
		}
		return notVisited;
		
	}
}
