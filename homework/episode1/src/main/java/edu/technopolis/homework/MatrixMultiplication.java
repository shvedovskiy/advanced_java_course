package edu.technopolis.homework;

/**
 * Matrix multiplication home task.
 * <br/>
 * Matrix dimension and elements are passed as CLI arguments.
 */

public class MatrixMultiplication {
    private static int[][] firstArray;
    private static int[][] secondArray;

    /**
     * Method checking input and forms matrices.
     * @param args pass from main()
     */
    private static void parse(String... args) {
        int n, m, x, y;
        if (args.length < 4) {
            throw new IllegalArgumentException("Wrong quantity of args has been set");
        }
        for (int i = 0; i != 4; ++i) {
            if (Integer.parseInt(args[i]) < 1) {
                throw new IllegalArgumentException("Some dimensions are non-positive");
            }
        }
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
        x = Integer.parseInt(args[2]);
        if (x != m) {
            throw new IllegalArgumentException("Dimensions are not consistent");
        }
        y = Integer.parseInt(args[3]);

        if (args.length < 4 + n * m + x * y) {
            throw new IllegalArgumentException("Wrong arguments length");
        }

        int cnt = 3;
        firstArray = new int[n][m];
        secondArray = new int[x][y];

        for (int i = 0; i != n; ++i) {
            for (int j = 0; j != m; ++j) {
                firstArray[i][j] = Integer.parseInt(args[++cnt]);
            }
        }
        for (int i = 0; i != x; ++i) {
            for (int j = 0; j != y; ++j) {
                secondArray[i][j] = Integer.parseInt(args[++cnt]);
            }
        }
    }

    public static void main(String... args) {
        try {
            parse(args);
        } catch (RuntimeException e) {
            System.err.println("Exception raised while parsing");
            throw e;
        }
        Matrix firstMatrix = new Matrix(firstArray);
        Matrix secondMatrix = new Matrix(secondArray);
        Matrix resultMatrix = firstMatrix.multiplyTo(secondMatrix);
        if (resultMatrix != null) {
            resultMatrix.printMatrix();
        } else {
            throw new RuntimeException("Exception raised while multiplication");
        }
    }
}
