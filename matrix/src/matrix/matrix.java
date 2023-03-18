package matrix;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @immutable
 *
 *@invar | getAmountOfRows() >= 0
 *@invar | getAmountOfCollums() >= 0
 *@invar | getMatrix().length == getAmountOfRows()
 *@invar | Arrays.stream(getMatrix()).allMatch(row -> row != null && row.length == getAmountOfCollums())
 */

public class matrix {
	/**
	 * @representObject
	 * @invar | matrix != null
	 * @invar | rows != 0
	 * @invar | collums != 0
	 */
	private double[] matrix;
	private int rows;
	private int collums;
	
	public int getAmountOfRows() {
		return rows;
	}
	public int getAmountOfCollums() {
		return collums;
	}
	
	/**
	 * @post | result != null
	 * @post | result.length == getAmountOfRows() * getAmountOfCollums()
	 * @post | IntStream.range(0, getAmountOfRows()).allMatch(i -> 
	 *       |		IntStream.range(0, getAmountOfCollums()).allMatch(j ->
	 *       |			result[getAmountOfCollums() * i  + j] == getMatrix()[i][j]
	 *       |			)
	 *       |		)
	 *@creates | result
	 */
	public double[] getRowMajor() {
		return matrix.clone();
	}
	
	/**
	 * @post | result != null
	 * @post | result.length == getAmountOfRows() * getAmountOfCollums()
	 * @post | IntStream.range(0, getAmountOfRows()).allMatch(i -> 
	 *       |		IntStream.range(0, getAmountOfCollums()).allMatch(j ->
	 *       |			result[getAmountOfRows() * j + i] == getMatrix()[i][j]
	 *       |			)
	 *       |		)
	 *@creates | result
	 */
	public double[] getCollumMajor() {
		int length = rows * collums;
		double[] elements = new double[length];
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < collums; j++) {
				elements[(rows *j) + i] = matrix[(collums * i) + j];
			}
		}
		return elements;
	}
	/**
	 * @creates | result, ...result
	 */
	public double[][] getMatrix(){
		double[][] matrixFull = new double[rows][collums];
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < collums; j++) {
				matrixFull[i][j] = matrix[(collums * i) + j];
			}
	}
		return matrixFull;
	}
	
	/**
	 * @pre | rowIndex < getAmountOfRows() && 0 <= rowIndex
	 * @pre | collumIndex < getAmountOfCollums() && 0 <= collumIndex
	 * @post | result == getMatrix()[rowIndex][collumIndex]
	 */
	
	public double getElementAtIndex(int rowIndex, int collumIndex) {
		return matrix[collums * rowIndex + collumIndex];
	}
	
	/**
	 * @throws IllegalArgumentException | matrix == null || rows == 0 || collums == 0
	 * @throws IllegalArgumentException | matrix.length  != rows * collums
	 * @mutates | 
	 * @inspects | matrix
	 * @post | Arrays.equals(getRowMajor(), matrix)
	 * @post | getAmountOfRows() == rows
	 * @post | getAmountOfCollums() == collums
	 */
	public matrix(double[] matrix, int rows, int collums) {
		if (matrix.length == 0 || rows == 0 || collums == 0) {
			throw new IllegalArgumentException("Argument_Out_Of_Bounds");
		}
		else if(matrix.length  != rows * collums) {
			throw new IllegalArgumentException("Dimensions_Do_Not_Match");
		}
		this.matrix = matrix.clone();
		this.rows = rows;
		this.collums = collums;
	}
	
	
	/**
	 * @inspects | this
	 * @post | result != null
	 * @post | result.getAmountOfRows() == getAmountOfRows()
	 * @post | result.getAmountOfCollums() == getAmountOfCollums()
	 * @post | IntStream.range(0, getAmountOfRows()).allMatch(i -> 
	 *       |		IntStream.range(0, getAmountOfCollums()).allMatch(j ->
	 *       |			result.getElementAtIndex(i,j) == getElementAtIndex(i,j) * scale
	 *       |			)
	 *       |		)
	 *@creates | result

	 */
	public matrix scaled(int scale){
		double[] scaledMatrix = new double[rows * collums];
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < collums; j++) {
				scaledMatrix[i * collums + j] = matrix[(collums * i) + j] * scale;
			}
		}
		return new matrix(scaledMatrix, rows, collums);
	}
	/**
	 * @pre | other != null
	 * @pre | other.getAmountOfRows() == getAmountOfRows()
	 * @pre | other.getAmountOfCollums() == getAmountOfCollums()
	 * @inspects | this, other
	 * @post | result != null
	 * @post | result.getAmountOfRows() == getAmountOfRows()
	 * @post | result.getAmountOfCollums() == getAmountOfCollums()
	 * @post | IntStream.range(0, getAmountOfRows()).allMatch(i -> 
	 *       |		IntStream.range(0, getAmountOfCollums()).allMatch(j ->
	 *       |			result.getElementAtIndex(i,j) == getElementAtIndex(i,j) + other.getElementAtIndex(i,j)
	 *       |			)
	 *       |		)
	 *@creates | result
	 */
	
	public matrix plus(matrix other){
		double[] scaledMatrix = new double[rows * collums];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < collums; j++) {
				scaledMatrix[i* collums + j] = getElementAtIndex(i, j) + other.getElementAtIndex(i, j);
			}
		}
		return new matrix(scaledMatrix, rows, collums);
	}
	


}
