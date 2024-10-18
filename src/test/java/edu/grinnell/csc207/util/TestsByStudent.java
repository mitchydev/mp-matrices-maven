package edu.grinnell.csc207.util;

import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;


/**e
 * A variety of student made tests for the Matrix class.
 *
 * @author Mitch Paiva
 */
public class TestsByStudent {
  @Test
  public void testSet() {
    Integer i0 = Integer.valueOf(0);
    Integer i1 = Integer.valueOf(1);
    Integer i2 = Integer.valueOf(2);
    Integer i3 = Integer.valueOf(3);
    Integer i4 = Integer.valueOf(4);
    Integer i5 = Integer.valueOf(5);

    MatrixV0<Integer> matrix = new MatrixV0<> (2, 2, 0);
    
    assertMatrixEquals(new Integer[][] {{i0, i0}, {i0, i0}} , matrix, "Unset Matrix");
    
    matrix.set(0, 0, i2);

    assertMatrixEquals(new Integer[][] {{i2, i0}, {i0, i0}} , matrix, "Matrix after setting at (0,0)");
  }

  @Test
  public void testGet() {
    Integer i0 = Integer.valueOf(0);
    Integer i1 = Integer.valueOf(1);
    Integer i2 = Integer.valueOf(2);
    Integer i3 = Integer.valueOf(3);
    Integer i4 = Integer.valueOf(4);
    Integer i5 = Integer.valueOf(5);

    MatrixV0<Integer> matrix = new MatrixV0<> (2, 2, 0);
    
    assertMatrixEquals(new Integer[][] {{i0, i0}, {i0, i0}} , matrix, "Unset Matrix");
    
    matrix.set(0, 0, i2);

    assertEquals(i0, matrix.get(1,1));
  }
  
}
