import utils.In;
import utils.StdRandom;

public class BoggleBoard {
    private static final String[] BOGGLE_ROMANIA_16 = {
            "AĂEÎÂL", "EAIONU", "RSTLNE", "SNTĂEI",
            "RMNLST", "OCĂEIU", "ȘȚNRLA", "DPBVCĂ",
            "FGMȚÂI", "HZȘȚLN", "ĂÂÎOEU", "SCNRTA",
            "LEÎROA", "MNȚȘIA", "PRLCĂU", "VDFGȚĂ"
    };

    private static final String[] BOGGLE_ROMANIA_25 = {
            "AĂEÎÂO", "EAIONU", "RSTLNE", "SNTĂEI", "RMNLST",
            "OCĂEIU", "ȘȚNRLA", "DPBVCĂ", "FGMȚÂI", "HZȘȚLN",
            "ĂÂÎOEU", "SCNRTA", "LEÎROA", "MNȚȘIA", "PRLCĂU",
            "VDFGȚĂ", "EAIORU", "SLNRTC", "PDMNĂȘ", "TGÂȘEI",
            "AUEÎÂO", "CRSTLN", "BVPFDM", "HȚȘZLN", "JȚȘĂEI"
    };

    private static final String ALFABET = "AĂÂBCDEFGHIÎJKLMNOPQRSȘTȚUVWXYZ";

    private static final double[] FREQUENCIES = {
            0.1359, 0.0163, 0.0072, 0.0045, 0.0408, 0.0353,
            0.1531, 0.0036, 0.0036, 0.0027, 0.0897, 0.0145,
            0.0018, 0.0005, 0.0371, 0.0263, 0.0825, 0.0516,
            0.0281, 0.0001, 0.0789, 0.0480, 0.0063, 0.0652,
            0.0054, 0.0553, 0.0036, 0.0001, 0.0001, 0.0001,
            0.0018
    };

    private int m; // no of rows
    private int n; // no of cols
    private char[][] board; // m * n array of characters

    // Initializes random 4-by-4 board
    public BoggleBoard() {
        m = 4;
        n = 4;
        StdRandom.shuffle(BOGGLE_ROMANIA_16);
        board = new  char[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j ++) {
                String letters = BOGGLE_ROMANIA_16[n * i + j];
                int r = StdRandom.uniform(letters.length());  // returns a random integer uniformly in [0, letters.length()]
                board[i][j] = letters.charAt(r);
            }
        }
    }

    // Initializes a random m-by-n board, by frequencies
    public BoggleBoard(int m, int n) {
        this.m = m;
        this.n = n;

        if(m <= 0) throw new IllegalArgumentException("error, m needs to be greater than 0");
        if(n <= 0) throw new IllegalArgumentException("error, n needs to be greater than 0");

        board = new char[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int r = StdRandom.discrete(FREQUENCIES);
                board[i][j] = ALFABET.charAt(r);
            }
        }
    }

    // Initializes a board from a file
    public BoggleBoard(String fileName) {
        In in = new In(fileName);
        int m = in.readInt();
        int n = in.readInt();

        if(m <= 0) throw new IllegalArgumentException("error, m needs to be greater than 0");
        if(n <= 0) throw new IllegalArgumentException("error, n needs to be greater than 0");

        board = new char[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                String letter = in.readString().toUpperCase();
                if(letter.length() != 1) {
                    throw new IllegalArgumentException("error, invalid character" + letter);
                } else if(!ALFABET.contains(letter)) {
                    throw new IllegalArgumentException("error, invalid character" + letter);
                } else {
                    board[i][j] = letter.charAt(0);
                }
            }
        }
    }

    // Initializes a board by a 2d array
    public BoggleBoard(char[][] array) {
        this.m = array.length;
        if(m <= 0) throw new IllegalArgumentException("error, m needs to be greater than 0");
        this.n = array[0].length;
        if(n <= 0) throw new IllegalArgumentException("error, n needs to be greater than 0");

        board = new char[m][n];

        for(int i = 0; i < m; i++) {
            if(array[i].length != n) {
                throw new IllegalArgumentException("error, array ragged" );
            }
            for(int j = 0; j < n; j++) {
                if(ALFABET.indexOf(array[i][j]) == -1) {
                    throw new IllegalArgumentException("error, invalid character" + array[i][j]);
                }
                board[i][j] = array[i][j];
            }
        }

    }

    // Returns the number of rows
    public int getRows() {
        return m;
    }

    // Returns the number of columns
    public int getColumns() {
        return n;
    }

    // Returns the letter on the row 'row' and column 'col'
    public char getLetter(int row, int col) {
        return board[row][col];
    }

    // Returns a string representation of the board, replacing "C" with "CE"
    public String toString() {
        StringBuilder sb = new StringBuilder(m + " " + n + "\n");
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                sb.append(board[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }

}
