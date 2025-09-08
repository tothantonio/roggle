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
            0.1500, 0.0180, 0.0080, 0.0050, 0.0450, 0.0390,
            0.1690, 0.0040, 0.0040, 0.0030, 0.0990, 0.0160,
            0.0020, 0.0005, 0.0410, 0.0290, 0.0910, 0.0570,
            0.0310, 0.0001, 0.0870, 0.0530, 0.0070, 0.0720,
            0.0060, 0.0610, 0.0040, 0.0001, 0.0001, 0.0001,
            0.0020
    };

    private final int m; // no of rows
    private final int n; // no of cols
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

}
