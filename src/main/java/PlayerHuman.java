/**
 * This class represents the human player in the game, and hence it extends the Player class.
 * It is a smaller class than the robot player as the action of choosing a column
 * is handled by the input inserted in the command line using the Scanner class.
 * Therefore, the human player does not need access to the board, which can be seen in the
 * command line.
 */
public class PlayerHuman extends Player {

    /**
     * Constructor for the human player
     * @param token the token of the player
     */
    public PlayerHuman(Token token) {
        super(token);
    }

    /**
     * Method to choose the column for the human player.
     * This method implements the abstract method from the Player class.
     * This method is not needed for the human player as the GUI will handle this.
     * The input is collected from the command line.
     * @return -1
     */
    @Override
    public int chooseColumn() {
        // Not needed for human player as the GUI handles this
        return -1;
    }



}

