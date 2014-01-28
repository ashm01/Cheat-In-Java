
package question2;


/**
 *
 * @author Ash
 */
public interface AdvancedPlayerInterface extends Player {
    
    void addDiscardedCard(Card c);
    
    void addDiscardedHand(Hand h);
    
    void resetDiscards();
    
    
}
