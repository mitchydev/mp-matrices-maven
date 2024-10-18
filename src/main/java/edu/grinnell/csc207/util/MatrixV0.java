package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Mitch Paiva
 * @author Samuel A. Rebelsky
 *
 * @param <T> The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /** The matrix that stores elements. */
  T[][] matrix;

  /** The width of the matrix. */
  int width;

  /** The height of the matrix. */
  int height;

  /** The default value for an uninitialized matrix. */
  T defaultVal;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the given value as the default.
   *
   * @param width The width of the matrix.
   * @param height The height of the matrix.
   * @param def The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    this.width = width;
    this.height = height;
    this.defaultVal = def;
    this.matrix = (T[][]) new Object[height][width];
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with null as the default value.
   *
   * @param width The width of the matrix.
   * @param height The height of the matrix.
   *
   * @throws NegativeArraySizeException If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row The row of the element.
   * @param col The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    if ((row < 0 || row >= height) || (col >= width || col < 0)) {
      throw new IndexOutOfBoundsException();
    } // if statement
    if (matrix[row][col] == null) {
      return defaultVal;
    } // if statement
    return matrix[row][col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row The row of the element.
   * @param col The column of the element.
   * @param val The value to set.
   *
   * @throws IndexOutOfBoundsException If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if ((row < 0 || row >= height) || (col >= width || col < 0)) {
      throw new IndexOutOfBoundsException();
    } // if statement
    this.matrix[row][col] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if (row < 0 || row > this.height) {
      throw new IndexOutOfBoundsException("The row is out of bounds");
    } else {
      T[][] nextMatrix = (T[][]) new Object[this.height + 1][this.width];

      for (int i = 0; i < row; i++) {
        nextMatrix[i] = matrix[i];
      } // for loop
      for (int i = 0; i < width; i++) {
        nextMatrix[row][i] = this.defaultVal;
      } // for loop
      for (int i = row + 1; i <= height; i++) {
        nextMatrix[i] = matrix[i - 1];
      } // for loop
      this.matrix = nextMatrix;
      this.height++;
    } // else
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row The number of the row to insert.
   * @param vals The values to insert.
   *
   * @throws IndexOutOfBoundsException If the row is negative or greater than the height.
   * @throws ArraySizeException If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (row < 0 || row > this.height) {
      throw new IndexOutOfBoundsException("The row is out of bounds");
    } // if statement

    if (vals.length != this.width) {
      throw new ArraySizeException();
    } // if statement

    T[][] nextMatrix = (T[][]) new Object[this.height + 1][this.width];

    for (int i = 0; i < row; i++) {
      nextMatrix[i] = matrix[i];
    } // for loop
    for (int i = 0; i < width; i++) {
      nextMatrix[row][i] = vals[i];
    } // for loop
    for (int i = row + 1; i <= height; i++) {
      nextMatrix[i] = matrix[i - 1];
    } // for loop
    this.matrix = nextMatrix;
    this.height++;
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if (col < 0 || col > this.width) {
      throw new IndexOutOfBoundsException("The column is out of bounds");
    } // if statement

    T[][] nextMatrix = (T[][]) new Object[this.height][this.width + 1];

    for (int x = 0; x < this.height; x++) {
      for (int i = 0; i < col; i++) {
        nextMatrix[x][i] = this.matrix[x][i];
      } // for loop
      nextMatrix[x][col] = this.defaultVal;
      for (int i = col; i < this.width; i++) {
        nextMatrix[x][i + 1] = this.matrix[x][i];
      } // for loop
    } // for loop
    this.matrix = nextMatrix;
    this.width++;
  } // insertCol(int, T[])

  /**
   * Insert a column filled with the specified values.
   *
   * @param col The number of the column to insert.
   * @param vals The values to insert.
   *
   * @throws IndexOutOfBoundsException If the column is negative or greater than the width.
   * @throws ArraySizeException If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (col < 0 || col > this.width) {
      throw new IndexOutOfBoundsException();
    } // if statement

    if (vals.length != this.height) {
      throw new ArraySizeException();
    } // if statement

    T[][] nextMatrix = (T[][]) new Object[this.height][this.width + 1];

    for (int x = 0; x < this.height; x++) {
      for (int i = 0; i < col; i++) {
        nextMatrix[x][i] = this.matrix[x][i];
      } // for loop
      nextMatrix[x][col] = vals[x];
      for (int i = col; i < this.width; i++) {
        nextMatrix[x][i + 1] = this.matrix[x][i];
      } // for loop
    } // for loop
    this.matrix = nextMatrix;
    this.width++;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException If the row is negative or greater than or equal to the
   *         height.
   */
  public void deleteRow(int row) {
    if (row < 0 || row >= this.height) {
      throw new IndexOutOfBoundsException("The row is out of bounds");
    } // if statement

    T[][] nextMatrix = (T[][]) new Object[this.height - 1][this.width];

    for (int i = 0; i < row; i++) {
      nextMatrix[i] = this.matrix[i];
    } // for loop
    for (int i = row; i < this.height - 1; i++) {
      nextMatrix[i] = matrix[i + 1];
    } // for loop
    this.matrix = nextMatrix;
    this.height--;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException If the column is negative or greater than or equal to the
   *         width.
   */
  public void deleteCol(int col) {
    if (col < 0 || col >= this.width) {
      throw new IndexOutOfBoundsException("The column is out of bounds");
    } // if statement

    T[][] nextMatrix = (T[][]) new Object[this.height][this.width - 1];
    for (int x = 0; x < this.height; x++) {
      for (int i = 0; i < col; i++) {
        nextMatrix[x][i] = this.matrix[x][i];
      } // for loop

      for (int i = col; i < this.width - 1; i++) {
        nextMatrix[x][i] = matrix[x][i + 1];
      } // for loop
    } // for loop
    this.matrix = nextMatrix;
    this.width--;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow The top edge / row to start with (inclusive).
   * @param startCol The left edge / column to start with (inclusive).
   * @param endRow The bottom edge / row to stop with (exclusive).
   * @param endCol The right edge / column to stop with (exclusive).
   * @param val The value to store.
   *
   * @throw IndexOutOfBoundsException If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol, T val) {
    if (startRow < 0 || endRow > this.height || endCol > this.width || startRow >= endRow
        || startCol >= endCol) {
      throw new IndexOutOfBoundsException();
    } // if statement
    for (int i = startRow; i < endRow; i++) {
      for (int x = startCol; x < endCol; x++) {
        this.matrix[i][x] = val;
      } // for loop
    } // for loop
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow The row to start with (inclusive).
   * @param startCol The column to start with (inclusive).
   * @param deltaRow How much to change the row in each step.
   * @param deltaCol How much to change the column in each step.
   * @param endRow The row to stop with (exclusive).
   * @param endCol The column to stop with (exclusive).
   * @param val The value to store.
   *
   * @throw IndexOutOfBoundsException If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol, int endRow,
      int endCol, T val) {
    if (startRow < 0 || endRow > this.height || endCol > this.width || startRow >= endRow
        || startCol >= endCol || deltaCol > this.width || deltaRow > this.height) {
      throw new IndexOutOfBoundsException();
    } // if statement

    int row = startRow;
    int col = startCol;
    while (row < endRow && col < endCol) {
      this.matrix[row][col] = val;
      row += deltaRow;
      col += deltaCol;
    } // while loop
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual elements are mutable,
   * mutating them in one matrix may affect the other matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    MatrixV0<T> matrixCopy = new MatrixV0<>(this.width, this.height, this.defaultVal);
    for (int i = 0; i < this.height; i++) {
      System.arraycopy(this.matrix[i], 0, matrixCopy.matrix[i], 0, this.width);
    } // for loop
    return matrixCopy;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other The object to compare.
   *
   * @return true if the other object is a matrix with the same width, height, and equal elements;
   *         false otherwise.
   */
  public boolean equals(Object other) {
    if (other instanceof Matrix) {
      Matrix<T> otherMatrix = (Matrix<T>) other;
      if (this.height() != otherMatrix.height() || this.width() != otherMatrix.width()) {
        return false;
      } // if statement
      for (int row = 0; row < this.height(); row++) {
        for (int col = 0; col < this.width(); col++) {
          T thisValue = this.get(row, col);
          Object otherValue = otherMatrix.get(row, col);

          if (thisValue == null) {
            if (otherValue != null) {
              return false;
            } // if statement
          } else {
            if (!thisValue.equals(otherValue)) {
              return false;
            } // if statement
          } // else statement
        } // for loop
      } // for loop
      return true;
    } else {
      return false;
    } // else statement
  } //

  /**
   * Compute a hash code for this matrix. Included because any object that implements `equals` is
   * expected to implement `hashCode` and ensure that the hash codes for two equal objects are the
   * same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
