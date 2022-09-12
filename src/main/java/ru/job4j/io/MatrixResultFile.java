package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class MatrixResultFile {
    public static int[][] multiple(int size) {
        int[][] array = new int[size][size];
        for (int row = 0; row < array.length; row++) {
            for (int cell = 0; cell < array[row].length; cell++) {
                array[row][cell] = (row + 1) * (cell + 1);
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[][] matrix = multiple(9);
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int[] row : matrix) {
                String line = Arrays.toString(row);
                out.write(line.substring(1, line.length() - 1).getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}