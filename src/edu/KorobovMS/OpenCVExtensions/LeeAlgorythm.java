package edu.KorobovMS.OpenCVExtensions;

import org.opencv.core.Mat;
import java.util.*;

public class LeeAlgorythm {
	private double[] borderColor = null;
	private double[] sourceColor = null;
	private double[] destinationColor = null;
	private double[] backgroundColor = null;
	private Mat matrix = null;
	
	public LeeAlgorythm(Mat matrix, 
			double[] sourceColor, 
			double[] destinationColor, 
			double[] backgroundColor,
			double[] borderColor) {
		this.matrix = matrix;
		this.sourceColor = sourceColor;
		this.destinationColor = destinationColor;
		this.backgroundColor = backgroundColor;
		this.borderColor = borderColor;
	}
	
	public List<Point> findPath() {
		List<Point> result = new ArrayList<Point>();
		Point source = null;
		Point dest = null;
		int[][] temp = new int[matrix.rows()][matrix.cols()];
		
		// находим точки соответствующего цвета
		for (int i = 0; i < matrix.rows(); i++) {
			for (int j = 0; j < matrix.cols(); j++) {
				double[] v = matrix.get(i, j);
				if (Arrays.equals(v, sourceColor)) {
					source = new Point(i, j);
				}
				
				if (Arrays.equals(v, destinationColor)) {
					dest = new Point(i, j);
				}
				
				if (Arrays.equals(v, borderColor)) {
					temp[i][j] = -2;
				} else {
					temp[i][j] = -1;
				}
			}
		}
		
		int nstep = 0;		
		List<Point> wave = new ArrayList<Point>();
		List<Point> oldWave = new ArrayList<Point>();
		final int[] dx = {0, 1, 0, -1};
		final int[] dy = {-1, 0, 1, 0};
		oldWave.add(source);
		temp[source.getRow()][source.getCol()] = nstep;
		
		loop:
		while (oldWave.size() > 0) {
			nstep++;
			wave.clear();
			Iterator<Point> i = oldWave.iterator();
			while (i.hasNext()) {
				Point current = i.next();
				for (int d = 0; d < dx.length; d++) {
					int nx = current.getRow() + dx[d];
					int ny = current.getCol() + dy[d];
					if (temp[nx][ny] == -1) {
						wave.add(new Point(nx, ny));
						temp[nx][ny] = nstep;
						if (nx == dest.getRow() && ny == dest.getCol()) {
							break loop;
						}
					}
				}
			}
			oldWave.clear();
			oldWave.addAll(wave);
		}
		
		return restorePath(temp, source, dest);
	}
	
	private List<Point> restorePath(int[][]mat, Point source, Point dest) {
		LinkedList<Point> res = new LinkedList<Point>();
		int x = dest.getRow();
		int y = dest.getCol();
		int xEnd = source.getRow();
		int yEnd = source.getCol();
		
		do {
			res.addFirst(new Point(x, y));
			if (mat[x-1][y] == mat[x][y] - 1) {
				x -= 1;
			} else if (mat[x+1][y] == mat[x][y] - 1) {
				x += 1;
			} else if (mat[x][y-1] == mat[x][y] - 1) {
				y -= 1;
			} else if (mat[x][y+1] == mat[x][y] - 1) {
				y += 1;
			} else {
				System.out.println("can't commit iteration");
				break;
			}
			
		} while (x != xEnd || y != yEnd);	
		
		return res;
	}
}