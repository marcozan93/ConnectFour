/**
 * This class is responsible for the game logic of the Connect Four game.
 * This involves switching players, placing tokens on the board,
 * checking if the game is over, and checking if the game is a draw.
 */
public class GameLogic {

    // Fields
    /** The board of the game. */
    private Board board;
    /** The first player. */
    private final Player player1;
    /** The second player. */
    private final Player player2;
    /** The current player. */
    private Player currentPlayer;

    // Constructor
    /**
     * Constructor for the GameLogic object with the given board, player1, and player2.
     * @param board   the board of the game.
     * @param player1 the first player.
     * @param player2 the second player.
     */
    public GameLogic(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }


    // Methods
    /**
     * This method returns the current player who needs to play.
     * @return the current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Switches the current player to the other player.
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    /**
     * This method places a token on the board in the given column.
     * @param col the column where the token should be placed.
     */
    public void placeTokenOnBoard(int col) {
        // it gets the token of the current player
        Token token = currentPlayer.getToken();
        if (board.isValidMove(col)) {
            int minRow = board.getFirstAvailableRow(col);
            board.setToken(minRow, col, token);
        }
        else {
            System.out.println("Invalid move!");
        }
    }

    /**
     * This method checks if the game is over.
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return board.checkHorizontally() || board.checkVertically() || board.checkDiagonally();
    }

    /**
     * This method checks if the game is a draw.
     * @return true if the game is a draw, false otherwise.
     */
    public boolean isDraw() {
        return (board.areAllColumnsFull() && !isGameOver());
    }





}
