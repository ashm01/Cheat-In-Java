
package question2;

/**
 * Interface that extends Strategy which contains the advanced strategy methods
 * @author Ash
 */
public interface AdvancedStrategy extends Strategy {
 
    /**
     * Method to reset the players discarded cards 
     */
    void resetDiscardCards();
    
}
