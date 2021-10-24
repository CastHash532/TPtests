package ogl.tp;

import org.junit.Test;
import ogl.tp.exception.NoSquareException;
import ogl.tp.model.Matrix;
import ogl.tp.service.MatrixMathematics;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class MatrixMathematicsTest {
    /**
     * Rigorous Test.
     * @throws NoSquareException
     */
    @Test
    public void DeterminantTest1() throws NoSquareException {
        double[][] matrix = {
            {1,2,3},
            {2,1,3},
            {3,1,2},
        };

        Matrix m = new Matrix(matrix);

        assertEquals(6, MatrixMathematics.determinant(m), 0.001);
    }


    @Test
    public void DeterminantTest2() throws NoSquareException {
        double[][] matrix = {
            {1},
        };

        Matrix m = new Matrix(matrix);
        assertEquals(1,MatrixMathematics.determinant(m), 0.001);
    }

    @Test
    public void DeterminantTest3() throws NoSquareException {
        double[][] matrix = {
            {1,3},
            {2,1},
        };

        Matrix m = new Matrix(matrix);
        assertEquals(-5,MatrixMathematics.determinant(m), 0.001);

    }

    @Test
    public void DeterminantTest4() throws NoSquareException {
        double[][] matrix = {
            {1,3,7,1},
            {2,1,3,3},
        };

        Matrix m = new Matrix(matrix);
        boolean thrown = false;

  try {
    MatrixMathematics.determinant(m);
  } catch (NoSquareException e) {
    thrown = true;
    assertTrue(thrown);

    String expectedMessage = "matrix need to be square.";
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

    }
}