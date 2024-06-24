/**
 * This class represents the abstraction of the token used in the game
 * by both the human and robot player.
 */


public class Token {

    // Fields
    /** The symbol of the token as a String (generally either "X" or "O"). */
    private final String symbol;

    // Constructor
    /**
     * Constructs a token with the given symbol.
     * @param symbol The symbol of the token as a String.
     */
    public Token(String symbol) {
        this.symbol = symbol;
    }

    // Methods
    /**
     * Returns the symbol of the token.
     * @return The symbol of the token as a String.
     */
    public String getSymbol() {
        return symbol;
    }

    // Overridden methods
    /**
     * This method overrides the equals for Strings to compare if
     * two Tokens are equal, meaning that they contain the same symbol.
     *
     * @param obj The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        // if current instance is the same as object passed as argument
        if (this == obj) {
            return true;
        }
        // if the object passed as argument is null or if it is not an instance of Token class
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // cast the object to Token class to compare their symbols
        Token token = (Token) obj;
        // compare symbols of current instance with argument symbol
        return symbol.equals(token.symbol);
    }


}
