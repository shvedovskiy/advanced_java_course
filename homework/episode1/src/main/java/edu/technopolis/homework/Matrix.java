package edu.technopolis.homework;

class Matrix implements Cloneable {
    private long[][] buffer;
    private boolean checkRange(int a, int b) {
        return (a >= 0 && a < height()) && (b >= 0 && b < width());
    }

    public Matrix(int[][] matrix) {
        long[][] tmp = new long[matrix.length][matrix[0].length];
        for (int i = 0; i != matrix.length; ++i) {
            for (int j = 0; j != matrix[0].length; ++j) {
                tmp[i][j] = (long) (matrix[i][j]);
            }
        }
        this.buffer = tmp.clone();
    }
    public Matrix(long[][] matrix) {
        this.buffer = matrix.clone();
    }
    public int height() {
        return this.buffer.length;
    }
    public int width() {
        return this.buffer[0].length;
    }
    public long elementAt(int i, int j) {
        if (checkRange(i, j)) {
            return this.buffer[i][j];
        } else {
            throw new IndexOutOfBoundsException("Impossible to get");
        }
    }
    public void insertElement(int i, int j, long value) {
        if (checkRange(i, j)) {
            this.buffer[i][j] = value;
        } else {
            throw new IndexOutOfBoundsException("Impossible to insert");
        }
    }
    public Matrix multiplyTo(Matrix that) {
        long[][] resultArray = new long[this.height()][that.width()];
        for (int i = 0; i != this.height(); ++i) {
            for (int j = 0; j != that.width(); ++j) {
                for (int k = 0; k != this.width(); ++k) {
                    resultArray[i][j] += this.buffer[i][k] * that.buffer[k][j];
                }
            }
        }
        return new Matrix(resultArray);
    }
    public void printMatrix() {
        for (int i = 0; i != this.height(); ++i) {
            for (int j = 0; j != this.width(); ++j) {
                System.out.print(this.elementAt(i, j) + " ");
            }
            System.out.println();
        }
    }
}
