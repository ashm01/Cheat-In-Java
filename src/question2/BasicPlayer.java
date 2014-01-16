
package question2;

import java.util.ArrayList;

/**
 *
 * @author Ash
 */
public class BasicPlayer implements Player {
    
    private Strategy strategy;
    private CardGame cardGame;
    private Hand hand;

    BasicPlayer(BasicStrategy basicStrategy, BasicCheat game) {
        this.strategy = basicStrategy;
        this.cardGame = game;
        this.hand = new Hand();
    }

    @Override
    public void addCard(Card c) {
        
        this.hand.add(c);
        
    }

    @Override
    public void addHand(Hand h) {
        
        this.hand.add(h);
        
    }

    @Override
    public int cardsLeft() {
        return this.hand.size();
        
    }

    @Override
    public void setGame(CardGame g) {
        
        this.cardGame = g;
        
    }

    @Override
    public void setStrategy(Strategy s) {
        
        this.strategy=s;
        
    }

    @Override
    public Bid playHand(Bid b) {
       boolean t = this.strategy.cheat(b,hand);
       return strategy.chooseBid(b, hand, t);
        
    }

    @Override
    public boolean callCheat(Bid b) {
        return this.strategy.callCheat(hand, b);
    }
    
   
    
}
