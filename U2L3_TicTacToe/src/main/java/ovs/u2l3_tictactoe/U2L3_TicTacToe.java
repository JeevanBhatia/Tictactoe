/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ovs.u2l3_tictactoe;
//Author: Jeevan Kumar
//06-03-2024
//Unit 2 Lesson 3 Tic Tac Toe
//In this assignment i have added a score board, a start button, a restart button, and personalized the code in my own way 
//the original code was made with an array instead of that i used jbutton to feature the game 
//I also changed the pictures and size of the code and optimized the original logic

import javax.swing.*;
import java.awt.*;

public class U2L3_TicTacToe extends JFrame {
    private final TicTacToeEvent eventHandler;

    private final JPanel boardPanel = new JPanel();
    private final JButton[][] cells = new JButton[3][3];
    private final JButton startButton = new JButton("Start");
    private final JButton resetButton = new JButton("Reset");
    private final JLabel scoreLabel = new JLabel("Score: X - 0 | O - 0 | Tie - 0");

    private int xWins = 0;
    private int oWins = 0;
    private int ties = 0;

    public static final int BOARD_SIZE = 3;

    public U2L3_TicTacToe() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);

        eventHandler = new TicTacToeEvent(this);

        setLayout(new BorderLayout());

        boardPanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE, 10, 10));
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                cells[row][col] = new JButton();
                boardPanel.add(cells[row][col]);
                cells[row][col].addActionListener(eventHandler);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(startButton);
        controlPanel.add(resetButton);
        add(controlPanel, BorderLayout.SOUTH);
        add(scoreLabel, BorderLayout.NORTH);

        startButton.addActionListener(e -> eventHandler.startGame());
        resetButton.addActionListener(e -> eventHandler.resetGame());

        setVisible(true);
    }

    public TicTacToeEvent getEventHandler() {
        return eventHandler;
    }

    public JButton[][] getCells() {
        return cells;
    }

    public void updateScoreLabel(char winner) {
        switch (winner) {
            case 'X' -> xWins++;
            case 'O' -> oWins++;
            case 'T' -> ties++;
        }
        scoreLabel.setText("Score: X - " + xWins + " | O - " + oWins + " | Tie - " + ties);
    }

    public void resetScoreCounts() {
        xWins = 0;
        oWins = 0;
        ties = 0;
        updateScoreLabel('\0');
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(U2L3_TicTacToe::new);
    }
}