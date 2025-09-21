package gui;

import main.BoggleBoard;
import main.BoggleSolver;
import utils.In;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BoggleGUI {
    private static int totalScore = 0;
    private BoggleSolver solver;
    private JLabel resultLabel;
    private JPanel boardPanel;
    private JLabel statsLabel;
    private BoggleBoard currBoard;

    public BoggleGUI() {
        // Initializez dictionarul de cuvinte
        In in = new In("/boards/dict.txt");
        String[] dict = in.readAllStrings();
        solver = new BoggleSolver(dict);

        // Initializez frame ul
        JFrame frame = new JFrame("Boggle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());

        // Creez tabla
        currBoard = new BoggleBoard();
        boardPanel = createBoardPanel(new BoggleBoard());

        // Butoane pentru check si reset
        JPanel inputPanel = new JPanel();
        JTextField wordField = new JTextField(12);
        JButton checkButton = new JButton("Check Word");
        checkButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        JButton resetButton = new JButton("Reset Board");
        resetButton.setFont(new Font("Times New Roman", Font.BOLD, 15));

        inputPanel.add(wordField);
        inputPanel.add(checkButton);
        inputPanel.add(resetButton);

        resultLabel = new JLabel("Scrie un cuvant si apasa Check:");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        statsLabel = new JLabel();
        statsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateStats();

//        frame.add(boardPanel, BorderLayout.CENTER);
        // changed how i add the board panel to the frame
        // used a gridbaglayout for centering
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(boardPanel);
        frame.add(centerWrapper, BorderLayout.CENTER);

        frame.add(inputPanel, BorderLayout.SOUTH);

        JPanel northPanel = new JPanel(new GridLayout(2, 1));
        northPanel.add(resultLabel);
        northPanel.add(statsLabel);
        frame.add(northPanel, BorderLayout.NORTH);

        checkButton.addActionListener(e -> {
            String word = wordField.getText().toUpperCase();
            if(word.isEmpty()) {
                resultLabel.setText("Introdu un cuvant");
                return;
            }

            int score = solver.score(word);
            if(score > 0) {
                totalScore += score;
                resultLabel.setText(word + " Valid! + " + score + " puncte | Total: " + totalScore);
            } else if(score == 0){
                resultLabel.setText(word + " A fost deja introdus! + " + score + " puncte | Total: " + totalScore);
            }else if(score == -1){
                resultLabel.setText(word + " Nu este valid!");
            }
            wordField.setText("");
        });

        resetButton.addActionListener(e -> {
            totalScore = 0;
            frame.remove(boardPanel);
            currBoard = new BoggleBoard();
            boardPanel = createBoardPanel(currBoard);
            frame.add(boardPanel, BorderLayout.CENTER);
            updateStats();
            frame.revalidate();
            frame.repaint();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private JPanel createBoardPanel(BoggleBoard board) {
        JPanel panel = new JPanel(new GridLayout(board.getRows(), board.getColumns()));
        for(int i = 0; i < board.getRows(); i++) {
            for(int j = 0; j < board.getColumns(); j++) {
                JButton button = new JButton(String.valueOf(board.getLetter(i, j)));
                button.setFont(new Font("Times New Roman", Font.BOLD, 18));
                button.setEnabled(false);
                panel.add(button);
            }
        }
        panel.setPreferredSize(new Dimension(280, 280));
        return panel;
    }

    private void updateStats() {
        Iterable<String> validWords = solver.getAllValidWords(currBoard);
        int maxScore = 0;
        Map<Integer, Integer> lengthCounts = new HashMap<>();

        for(String word : validWords) {
            int score = solver.score(word);
            maxScore += score;

            int len = word.length();
            lengthCounts.put(len, lengthCounts.getOrDefault(len, 0) + 1);
        }

        StringBuilder sb = new StringBuilder("<html>");
        sb.append("Scor maxim posibil: ").append(maxScore).append("<br>");
        for(int len : lengthCounts.keySet()) {
            sb.append("Cuvinte cu ").append(len).append(" litere: ").append(lengthCounts.get(len)).append("<br>");
        }
        sb.append("<html>");

        statsLabel.setText(sb.toString());
    }
    public static void main(String[] args) {
        new BoggleGUI();
    }
}
