package edu.KorobovMS.OpenCVExtensions;

/**
 * Класс, характеризующий позицию в матрице
 */
public class Point {
	private int row;
	private int col;
	
	public Point(int row, int col) {
		if (row >= 0) {
			this.row = row;
		} else {
			throw new RuntimeException();
		}
		
		if (col >= 0) {
			this.col = col;
		} else {
			throw new RuntimeException();
		}
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean equals(Point p) {
		if (p == null) {
			return false;
		}
		
		if (row == p.row && col == p.col) {
			return true;
		}
		
		return false;
	}
	
	public String toString() {
		return "(" + row + ", " + col + ")";
	}
}
