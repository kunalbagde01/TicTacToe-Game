import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] board;
    private JLabel statusLabel;
    private JButton replayButton;
    private boolean xTurn;

    public TicTacToe() {
        super("Tic Tac Toe");

        // Set the font for the status label
        Font statusFont = new Font("Arial", Font.BOLD, 20);

        // Create the board
        board = new JButton[3][3];
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = new JButton("");
                board[row][col].setFont(new Font("Arial", Font.BOLD, 60)); // Set the font for the board buttons
                board[row][col].setForeground(Color.BLUE); // Set the foreground color of the board buttons
                board[row][col].setBackground(Color.WHITE); // Set the background color of the board
                board[row][col].addActionListener(this);
                boardPanel.add(board[row][col]);
            }
        }

        // Create the status label
        statusLabel = new JLabel("X's turn");
        statusLabel.setFont(statusFont); // Set the font for the status label
        statusLabel.setForeground(Color.RED);

        // Create the replay button
        replayButton = new JButton("Replay");
        replayButton.setEnabled(false);
        replayButton.setFont(statusFont); // Set the font for the replay button
        replayButton.addActionListener(this);

        // Add the components to the frame
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);
        mainPanel.add(replayButton, BorderLayout.NORTH);
        setContentPane(mainPanel);

        // Set the size and visibility of the frame
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

public void actionPerformed(ActionEvent e) {
    JButton button = (JButton)e.getSource();
    if (button.equals(replayButton)) {
        resetGame();
    } else if (button.getText().equals("")) {
        if (xTurn) {
            button.setText("X");
            statusLabel.setText("O's turn");
        } else {
            button.setText("O");
            statusLabel.setText("X's turn");
        }
        xTurn = !xTurn;
        checkForWinner();
    }
}


    public void checkForWinner() {
        String winner = "";
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (!board[row][0].getText().equals("") &&
                board[row][0].getText().equals(board[row][1].getText()) &&
                board[row][1].getText().equals(board[row][2].getText())) {
                statusLabel.setText(board[row][0].getText() + " wins!");
                replayButton.setEnabled(true); // Enable the replay button
            disableBoard();
            return;
            }
        }
        // Check columns
        for (int col = 0; col < 3; col++) {
            if (!board[0][col].getText().equals("") &&
                board[0][col].getText().equals(board[1][col].getText()) &&
                board[1][col].getText().equals(board[2][col].getText())) {
                statusLabel.setText(board[0][col].getText() + " wins!");
            replayButton.setEnabled(true); // Enable the replay button
            disableBoard();
            return;
            }
        }
        // Check diagonals
        if (!board[0][0].getText().equals("") &&
            board[0][0].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][2].getText())) {
            statusLabel.setText(board[0][0].getText() + " wins!");
            replayButton.setEnabled(true); // Enable the replay button
            disableBoard();
            return;
        }
        if (!board[0][2].getText().equals("") &&
            board[0][2].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][0].getText())) {
            statusLabel.setText(board[0][2].getText() + " wins!");
            replayButton.setEnabled(true); // Enable the replay button
            disableBoard();
            return;
        }

        if (!winner.equals("")) {
            statusLabel.setText(winner + " wins!");
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    board[row][col].setEnabled(false);
                }
            }
        } else {
            boolean draw = true;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col].getText().equals("")) {
                        draw = false;
                    }
                }
            }
            if (isBoardFull()) {
                statusLabel.setText("Draw!");
                replayButton.setEnabled(true);
                return;
            }
        }
    }

    public boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void disableBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setEnabled(false);
            }
        }
    }

    public void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setText("");
                board[row][col].setEnabled(true);
            }
        }
        xTurn = true;
        statusLabel.setText("X's turn");
        replayButton.setEnabled(false);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
