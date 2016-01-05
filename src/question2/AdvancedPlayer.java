
package question2;

import java.util.ArrayList;

/**
 * AdvancedPlayer class that implements the advanced player interface
 * @author Ash
 */
public class AdvancedPlayer implements AdvancedPlayerInterface {
    //Declaring class variables
    private AdvancedStrategy strategy;
    private CardGame cardGame;
    private Hand hand;
    private ArrayList<Card> discardedCards;
    
    /**
     * Constructor for the AdvancedPlayer object, taking 2 parameters a game 
     * and a strategy
     * @param strategy Strategy the player will use
     * @param game the game the player plays
     */
    public AdvancedPlayer(AdvancedStrategy strategy, BasicCheat game) {
        this.strategy =  strategy;
        this.cardGame = game;
        this.hand = new Hand();
        this.discardedCards = new ArrayList();
    }
    /**
     * Method to reset the players discarded cards 
     */
    @Override
    public void resetDiscards() {
        //envokes the staregty method to reset discarded cards
        this.strategy.resetDiscardCards();
    }
    /**
     * Method to add a card to the hand
     * @param c card to be added
     */
    @Override
    public void addCard(Card c) {
        this.hand.add(c);
    }
    /**
     * Method to add hand to the players hand
     * @param h hand to be added
     */
    @Override
    public void addHand(Hand h) {
        this.hand.add(h);
    }
    /**
     * Method to find the number of cards left in the players hand
     */
    @Override
    public int cardsLeft() {
        return this.hand.size();
    }
    /**
     * Method to set the game the player plays
     * @param g game to be added
     */
    @Override
    public void setGame(CardGame g) {
        this.cardGame = g;
    }
    /**
     * Method to set the strategy the player uses
     * @param s strategy to be used
     */
    @Override
    public void setStrategy(Strategy s) {
        this.strategy=(AdvancedStrategy) s;
    }
    /**
     * Method to play a hand during the game
     * @param b current bid
     */
    @Override
    public Bid playHand(Bid b) {
        //Whether the play needs to cheat
        boolean t = this.strategy.cheat(b,hand);
        //returns the bid for the game
       return strategy.chooseBid(b, hand, t);
    }
    /**
     * Method to determine whether the player needs to call cheat
     * @param b current bid
     */
    @Override
    public boolean callCheat(Bid b) {
        return this.strategy.callCheat(hand, b);
    }
    /**
     *Method to get the players current hand
     */
    @Override
    public Hand getHand(){
        return this.hand;
    }
    
   
    
}
