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
            System.out.println("Формат ввода: N M X Y A_1_1 ... A_N_M B_1_1 ... B_X_Y\n" +
            "где N и M - размерность первой матрицы A (количество строк и столбцов), \n" +
                    "A_1_1 ... A_N_M - элементы матрицы A, X и Y - размерность второй матрицы B, \n" +
                    "B_1_1 ... B_X_Y - элементы матрицы B. \n" +
                    "Например, для умножения единичной матрицы размером 2 на 2 на вектор (-1, -1) \n" +
                    "необходимо на вход приложению подать следующие аргументы:\n" +
                    "\t2 2 2 1 1 0 0 1 -1 -1\n" +
                    "В консоль должен распечататься вектор:\n" +
                    "\t-1\n\t-1\n");
        }
        for (int i = 0; i != 4; ++i) {
            if (Integer.parseInt(args[i]) < 1) {
                System.out.println("Some dimensions are non-positive");
                System.exit(1);
            }
        }
        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);
        x = Integer.parseInt(args[2]);
        if (x != m) {
            System.out.println("Dimensions are not consistent");
            System.exit(1);
        }
        y = Integer.parseInt(args[3]);

        if (args.length < 4 + n * m + x * y) {
            System.out.println("Wrong arguments length");
            System.exit(1);
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
        parse(args);
        Matrix firstMatrix = new Matrix(firstArray);
        Matrix secondMatrix = new Matrix(secondArray);
        Matrix resultMatrix = firstMatrix.multiplyTo(secondMatrix);
        if (resultMatrix != null) {
            resultMatrix.printMatrix();
        } else {
            System.out.println("Error while multiplication");
            System.exit(1);
        }
    }
}
