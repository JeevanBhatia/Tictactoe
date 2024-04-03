/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ovs.u2l3_tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeEvent implements ActionListener {
    private final U2L3_TicTacToe game;
    private final ImageIcon iconX;
    private final ImageIcon iconO;
    private final ImageIcon iconBack;
    private final int[][] board;
    private int currentPlayer;

    public TicTacToeEvent(U2L3_TicTacToe game) {
        this.game = game;
        iconX = new ImageIcon(getClass().getResource("X.jpg"));
        iconO = new ImageIcon(getClass().getResource("O.jpg"));
        iconBack = new ImageIcon(getClass().getResource("cardback.jpg"));
        board = new int[U2L3_TicTacToe.BOARD_SIZE][U2L3_TicTacToe.BOARD_SIZE];
        initializeBoard();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton clickedButton = (JButton) event.getSource();
        int buttonIndex = getButtonIndex(clickedButton);
        int row = buttonIndex / U2L3_TicTacToe.BOARD_SIZE;
        int col = buttonIndex % U2L3_TicTacToe.BOARD_SIZE;
        handleButtonClick(row, col);
    }

    public void startGame() {
        resetGame();
        JOptionPane.showMessageDialog(null, "Game started!");
    }

    public void resetGame() {
        initializeBoard();
        for (JButton[] row : game.getCells()) {
            for (JButton cell : row) {
                cell.setEnabled(true);
                cell.setIcon(iconBack);
                cell.setBackground(null);
            }
        }
        game.resetScoreCounts();
        char winner = checkWinner();
        if (winner != '\0') {
            JOptionPane.showMessageDialog(null, winner == 'T' ? "The game is a tie!" : "Player " + winner + " wins!");
            updateScoreLabel(winner);
            resetGame();
        }
    }

    private void handleButtonClick(int row, int col) {
        JButton button = game.getCells()[row][col];
        if (board[row][col] == 0) {
            board[row][col] = currentPlayer;
            button.setEnabled(false);
            button.setIcon(currentPlayer == 1 ? iconX : iconO);
            char winner = checkWinner();
            if (winner != '\0') {
                JOptionPane.showMessageDialog(null, winner == 'T' ? "The game is a tie!" : "Player " + winner + " wins!");
                updateScoreLabel(winner);
                resetGame();
            } else {
                togglePlayer();
            }
        }
    }

    private char checkWinner() {
        for (int i = 0; i < U2L3_TicTacToe.BOARD_SIZE; i++) {
            
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return (board[i][0] == 1) ? 'X' : 'O';
            }
            
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return (board[0][i] == 1) ? 'X' : 'O';
            }
        }
        
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return (board[0][0] == 1) ? 'X' : 'O';
        }
        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return (board[0][2] == 1) ? 'X' : 'O';
        }
        
        boolean isTie = true;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    isTie = false;
                    break;
                }
            }
        }
        return isTie ? 'T' : '\0';
    }

    private void initializeBoard() {
        for (int i = 0; i < U2L3_TicTacToe.BOARD_SIZE; i++) {
            for (int j = 0; j < U2L3_TicTacToe.BOARD_SIZE; j++) {
                board[i][j] = 0;
            }
        }
    }

    private int getButtonIndex(JButton button) {
        JButton[][] cells = game.getCells();
        for (int i = 0; i < U2L3_TicTacToe.BOARD_SIZE; i++) {
            for (int j = 0; j < U2L3_TicTacToe.BOARD_SIZE; j++) {
                if (cells[i][j] == button) {
                    return i * U2L3_TicTacToe.BOARD_SIZE + j;
                }
            }
        }
        return -1;
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    private void updateScoreLabel(char winner) {
        game.updateScoreLabel(winner);
    }
}   