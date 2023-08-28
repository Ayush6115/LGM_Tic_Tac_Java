
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;

    public TicTacToeGame() {
        buttons = new JButton[3][3];
        currentPlayer = 'X';

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 200));
                buttons[i][j].addActionListener(new ButtonClickListener());
                add(buttons[i][j]);
            }
        }

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            if (clickedButton.getText().equals("")) {
                clickedButton.setText(Character.toString(currentPlayer));
                clickedButton.setEnabled(false);
                if (checkForWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                    resetGame();
                } else if (checkForDraw()) {
                    JOptionPane.showMessageDialog(null, "It's a draw!");
                    resetGame();
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }
    }

    private boolean checkForWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(Character.toString(currentPlayer)) &&
                    buttons[i][1].getText().equals(Character.toString(currentPlayer)) &&
                    buttons[i][2].getText().equals(Character.toString(currentPlayer))) {
                return true; // Row win
            }
            if (buttons[0][i].getText().equals(Character.toString(currentPlayer)) &&
                    buttons[1][i].getText().equals(Character.toString(currentPlayer)) &&
                    buttons[2][i].getText().equals(Character.toString(currentPlayer))) {
                return true; // Column win
            }
        }
        if (buttons[0][0].getText().equals(Character.toString(currentPlayer)) &&
                buttons[1][1].getText().equals(Character.toString(currentPlayer)) &&
                buttons[2][2].getText().equals(Character.toString(currentPlayer))) {
            return true; // Diagonal win (top-left to bottom-right)
        }
        if (buttons[0][2].getText().equals(Character.toString(currentPlayer)) &&
                buttons[1][1].getText().equals(Character.toString(currentPlayer)) &&
                buttons[2][0].getText().equals(Character.toString(currentPlayer))) {
            return true; // Diagonal win (top-right to bottom-left)
        }
        return false;
    }

    private boolean checkForDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false; // There are still empty cells
                }
            }
        }
        return true; // All cells are filled (draw)
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGame());
    }
}
