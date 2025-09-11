package main;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BoggleSolver {
    private Set<String> validWords = new HashSet<>();
    private final Set<String> dictionarySet;

    // Initializes the data structure using the dictionary
    public BoggleSolver(String[] dictionary){
        dictionarySet = new HashSet<>();
        for(String word : dictionary){
            dictionarySet.add(word);
        }
    }

    // Returns the set of all valid words
    public Iterable<String> getAllValidWords(BoggleBoard board){
        int rows = board.getRows();
        int cols = board.getColumns();
        boolean[][] visited = new boolean[rows][cols];

        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                dfs(r, c, board, "", visited);
            }
        }

        return validWords;
    }

    public void dfs(int r, int c, BoggleBoard board, String prefix, boolean[][] visited) {
        if(r < 0 || r >= board.getRows() || c < 0 || c >= board.getColumns() || visited[r][c]) {
            return;
        }

        char letter = board.getLetter(r, c);
        String word = prefix + letter;

        visited[r][c] = true;

        if(word.length() >= 3 && dictionarySet.contains(word)) {
            validWords.add(word);
        }

        for(int dr = -1; dr <= 1; dr++) {
            for(int dc = -1; dc <= 1; dc++) {
                if(dr != 0 || dc != 0) {
                    dfs(r+dr, c+dc, board, word, visited);
                }
            }
        }
        visited[r][c] = false;
    }

    // Returns the score of the word, if it exists, 0 otherwise
    public int score(String word) {
        if(!dictionarySet.contains(word)) {
            return 0;
        }

        int size = word.length();
        if(size < 3) return 0;
        if(size <= 4) return 1;
        if(size == 5) return 2;
        if(size == 6) return 3;
        if(size == 7) return 5;

        return 11;
    }
}
