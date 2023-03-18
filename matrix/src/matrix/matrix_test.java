package matrix;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class matrix_test {

	@Test
	void test() {
	matrix matrix1 = new matrix(new double[] {1.2, 2, 3, 4.6, 5, 6, 7, 8, 9.3, 10, 11.4, 12}, 3, 4);
	matrix matrix2 = new matrix(new double[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 3, 4);

    double[] anotherMatrix1 = matrix1.getCollumMajor();
    assertArrayEquals(new double[] {1.2, 5, 9.3, 2, 6, 10, 3, 7, 11.4, 4.6, 8, 12}, anotherMatrix1);
    double[][] matrixFull = new double[][] {{1.2, 2, 3, 4.6},{5, 6, 7, 8},{9.3, 10, 11.4, 12}};
	
	for(int i = 0; i < 3; i++) {
		for (int j = 0; j < 4; j++) {
		System.out.print(matrix1.getMatrix()[i][j] + " ");
		}
		System.out.println();
	}
	matrix scaledMatrix = matrix1.scaled(6);
	for(int i = 0; i < 3; i++) {
		for (int j = 0; j < 4; j++) {
		System.out.print(scaledMatrix.getElementAtIndex(i, j) + " ");
		}
		System.out.println();
	}
	
	matrix addedMatrix = matrix1.plus(matrix2);
	for(int i = 0; i < 3; i++) {
		for (int j = 0; j < 4; j++) {
		System.out.print(addedMatrix.getElementAtIndex(i, j) + " ");
		}
		System.out.println();
	}
	}

}
