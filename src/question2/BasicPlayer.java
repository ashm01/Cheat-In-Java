
package question2;



/**
 * Basic Player class that implements the player interface
 * @author Ash
 */
public class BasicPlayer implements Player {
    //Class variables
    private Strategy strategy;
    private CardGame cardGame;
    private Hand hand;
    
    /**
     * Basic player constructor that takes two parameters. A strategy and a type
     * of game
     * @param strategy Strategy the player will use
     * @param game the game the player plays
     */
    public BasicPlayer(Strategy strategy, BasicCheat game) {
        this.strategy = strategy;
        this.cardGame = game;
        this.hand = new Hand();
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
        
        this.strategy=s;
        
    }
    /**
     * Method to play a hand during the game
     * @param b current bid
     */
    @Override
    public Bid playHand(Bid b) {
       boolean t = this.strategy.cheat(b,hand);
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
    
    
   
    
}
