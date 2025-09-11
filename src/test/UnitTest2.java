package test;

import main.BoggleBoard;
import main.BoggleSolver;
import utils.In;
import utils.StdOut;

public class UnitTest2 {
    public static void main(String[] args) {
        In in = new In("src/boards/dict.txt");
        String[] dict = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dict);
        BoggleBoard board = new BoggleBoard("src/boards/board2");
        int score = 0;
        for(String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.score(word);
        }
        StdOut.println(score);
    }
}
