import javax.swing.*;
import java.awt.*;

/**
 * This class represents the GUI of the Connect4 game.
 * It represents an extension of the JFrame class and displays the game board
 */
public class BoardGui extends JFrame {

    // Fields
    private Board board;
    private Player player1;
    private Player player2;
    private GameLogic gameLogic;
    private Player currentPlayer;
    public static final String humanPlayerSymbol = "O";
    private static final String robotPlayerSymbol = "X";
    private JButton[] dropButtons;
    private CirclePanel[][] slots;

    // Constructor

    /**
     * Constructor for the BoardGui class.
     * It displays the welcome message, initializes the board, players, and game logic.
     * It also sets up the GUI components for the game.
     */
    public BoardGui() {
        // Display the welcome message
        String startingMessage = """
                Welcome to Connect 4!
                There are 2 players: red and yellow.
                Player 1 is red (human), Player 2 is yellow (robot).
                To play the game, click on the button "Drop" on top of the column you want to drop your token in.
                A player wins by connecting 4 tokens in a row - vertically, horizontally or diagonally.""";
        JOptionPane.showMessageDialog(this, startingMessage, "Welcome", JOptionPane.INFORMATION_MESSAGE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Connect4");
        setSize(700, 600);
        setLayout(new BorderLayout());
        board = new Board();
        player1 = new PlayerHuman(new Token(humanPlayerSymbol));
        player2 = new PlayerRobot(new Token(robotPlayerSymbol), board);
        gameLogic = new GameLogic(board, player1, player2);
        currentPlayer = gameLogic.getCurrentPlayer();
        dropButtons = new JButton[board.getNumberColumns()];
        slots = new CirclePanel[board.getNumberRows()][board.getNumberColumns()];

        setUpBoardGui();
    }

    /**
     * This method sets up the GUI components for the game.
     * It adds the "Drop" buttons at the top of the board and the slots to display the tokens.
     */
    private void setUpBoardGui() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(board.getNumberRows() + 1, board.getNumberColumns()));

        // add a first row of buttons to click and drop tokens
        for (int i = 0; i < board.getNumberColumns(); i++) {
            JButton btn = new JButton("Drop");
            dropButtons[i] = btn;
            int finalI = i;
            btn.addActionListener(e -> handleColumnClick(finalI));
            panel.add(btn);
        }
        // add all the other panels just displaying the tokens
        for (int i = 0; i < board.getNumberRows(); i++) {
            for (int j = 0; j < board.getNumberColumns(); j++) {
                CirclePanel slot = createSlot();
                slots[i][j] = slot;
                panel.add(slot);
            }
        }

        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null); // center the window
        setVisible(true);
    }

    /**
     * This method creates a panel representing a slot on the board.
     * @return a CirclePanel representing a slot on the board.
     */
    private CirclePanel createSlot() {
        return new CirclePanel();
    }

    /**
     * This method handles the click on a column button by the human user.
     * It includes the logic to update the slots, showing messages, and switching turns.
     * @param col the column clicked by the user.
     */
    private void handleColumnClick(int col) {
        System.out.println("You clicked column: " + col);

        if (currentPlayer instanceof PlayerHuman) {
            gameLogic.placeTokenOnBoard(col);
            updateSlots();
            if (gameLogic.isGameOver()) {
                showWinningMessage();
            } else if (gameLogic.isDraw()) {
                showDrawMessage();
            } else {
                System.out.println("Switching turns...");
                switchTurns();
                if (currentPlayer instanceof PlayerRobot) {
                    handleRobotMove();
                }
            }
        }
    }

    /**
     * This method shows a message indicating the winner of the game.
     * After the user clicks on OK of the message, the program terminates.
     */
    private void showWinningMessage() {
        String winner = (currentPlayer.getToken().getSymbol().equals(humanPlayerSymbol)) ? "Human" : "Robot";
        showMessage("Game Over!\n" + winner + " player wins!");
        System.exit(0);
    }

    /**
     * This method shows a message indicating that the game is a draw.
     * After the user clicks on OK, the program terminates.
     */
    private void showDrawMessage() {
        showMessage("It's a draw!");
        System.exit(0);
    }

    /**
     * This method handles the move of the robot player.
     */
    private void handleRobotMove() {
        System.out.println("Robot is thinking...");
        int col = currentPlayer.chooseColumn();

        // Add a loop to ensure the robot picks a valid column
        while (!board.isValidMove(col)) {
            System.out.println("Robot chose an invalid column: " + col + ". Choosing again...");
            col = currentPlayer.chooseColumn();
        }

        System.out.println("Robot is placing a token in column: " + col);
        gameLogic.placeTokenOnBoard(col);
        updateSlots();
        if (gameLogic.isGameOver()) {
            showWinningMessage();
        } else if (gameLogic.isDraw()) {
            showDrawMessage();
        } else {
            System.out.println("Switching turns ...");
            switchTurns();
        }

    }

    /**
     * This method switches the turns between the human and robot players.
     */
    private void switchTurns() {
        gameLogic.switchPlayer();
        currentPlayer = gameLogic.getCurrentPlayer();
    }

    /**
     * This method displays a message to the user.
     * @param message the message to display.
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * This method updates the slots on the board to display the tokens.
     */
    private void updateSlots() {
        for (int i = 0; i < board.getNumberRows(); i++) {
            for (int j = 0; j < board.getNumberColumns(); j++) {
                updateSlot(i, j, board.getBoardItem(i, j));
            }
        }
    }

    /**
     * This method updates a specific slot on the board to display a token.
     * @param row the row of the slot.
     * @param col the column of the slot.
     * @param token the token to display.
     */
    private void updateSlot(int row, int col, Token token) {
        if (token != null) {
            slots[row][col].setColor(getTokenColor(token));
        } else {
            slots[row][col].setColor(Color.WHITE);
        }
    }

    /**
     * This method returns the color of a token based on its symbol.
     * @param token the token to get the color for.
     * @return the color of the token.
     */
    private Color getTokenColor(Token token) {
        if (humanPlayerSymbol.equals(token.getSymbol())) {
            return Color.RED;
        } else if (robotPlayerSymbol.equals(token.getSymbol())) {
            return Color.YELLOW;
        } else {
            return Color.WHITE;
        }
    }



}

