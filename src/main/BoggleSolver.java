package main;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BoggleSolver {
    private final Set<String> dictionary = new HashSet<>();
    private final Set<String> prefixes = new HashSet<>();

    // for the getAllValidWords method
    private Set<String> result = new HashSet<>();

    // Initializes the data structure using the dictionary
    public BoggleSolver(String[] dictionary){
        for(String word: dictionary){
            this.dictionary.add(word);
            for(int i = 1; i <= word.length(); i++) {
                this.prefixes.add(word.substring(0, i));
            }
        }
    }

    // Returns the set of all valid words
    public Iterable<String> getAllValidWords(BoggleBoard board){
        int rows = board.getRows();
        int cols = board.getColumns();
        boolean[][] visited = new boolean[rows][cols];

        result.clear();

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                dfs(i, j, board, "", visited);
            }
        }
        return result;
    }

    public void dfs(int r, int c, BoggleBoard board, String prefix, boolean[][] visited) {
        if(r < 0 || r >= board.getRows() || c < 0 || c >= board.getColumns() || visited[r][c]){
            return;
        }

        String word = prefix + board.getLetter(r, c);
        //        System.out.println(word);


        if(!(dictionary.stream().anyMatch(w -> w.startsWith(word)))){
            return;
        }

        if(word.length() >= 3 && dictionary.contains(word)){
            result.add(word);
        }

        visited[r][c] = true;

        // 8 directii
        for(int dr = -1; dr <= 1; dr++) {
            for(int dc = -1; dc <= 1; dc++) {
                if(dr != 0 || dc != 0) {
                    dfs(r + dr, c + dc, board, word, visited);
                }
            }
        }

        visited[r][c] = false;
    }

    // Returns the score of the word, if it exists, 0 otherwise
    public int score(String word) {
        if(!dictionary.contains(word) || word.length() < 3){
            return 0;
        }

        int n = word.length();
        if(n <= 4) return 1;
        if(n == 5) return 2;
        if(n == 6) return 3;
        if(n == 7) return 5;

        return 11;
    }
}
