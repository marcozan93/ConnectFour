/**
 * This abstract class represents the players in the game.
 * It is abstract as it is not appropriate to create an instance of a player,
 * but it should be specified whether the player is human or a robot. The aim
 * of this class is to generalise the behaviour of a player.
 * An abstract method is included as the behaviour of the player will depend
 * on the type of player (i.e., human or robot).
 */

public abstract class Player {

    // Fields
    /** The token of the player. */
    private final Token token;

    // Constructor
    /**
     * Constructor for the player.
     * @param token The token of the player.
     */
    public Player(Token token) {
        this.token = token;
    }

    // Methods
    /**
     * Getter for the token of the player.
     * @return The token of the player.
     */
    public Token getToken() {
        return token;
    }

    // Abstract method
    /**
     * Abstract method to represent how to choose a column.
     * @return The column chosen by the player.
     */
    public abstract int chooseColumn();

}
