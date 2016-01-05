
package question2;


/**
 * Interface which extends player interface adding methods regarding advanced
 * players
 * @author Ash
 */
public interface AdvancedPlayerInterface extends Player {
    
    /**
     * Method to get the players current hand
     * @return Current Hand
     */
    Hand getHand();
    
    /**
    * Method to reset the players discarded cards 
    */
    void resetDiscards();
    
    
}
