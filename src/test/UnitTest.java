package test;

import main.BoggleBoard;
import utils.StdOut;

public class UnitTest {
    public static void main(String[] args) {
        StdOut.println("Board:");

        BoggleBoard board1 = new BoggleBoard();
        StdOut.println(board1);

        StdOut.println();

        StdOut.println("Random 4-by-4 board:");

        BoggleBoard board2 = new BoggleBoard(4, 4);
        StdOut.println(board2);

        StdOut.println();

        StdOut.println("4-by-4 board from 2d array:");
        char[][] array = {
                { 'D', 'O', 'T', 'Y' },
                { 'T', 'R', 'S', 'F' },
                { 'M', 'X', 'M', 'O' },
                { 'Z', 'A', 'B', 'W' }
        };

        BoggleBoard board3 = new BoggleBoard(array);
        StdOut.println(board3);
        StdOut.println();

        String fileName = "src/boards/board5x5";

        StdOut.println("4-by-4 board from a file:");
        BoggleBoard board4 = new BoggleBoard(fileName);
        StdOut.println(board4);
    }
}