/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package question2;

import java.util.ArrayList;

/**
 *
 * @author Ash
 */
public class AdvancedPlayer implements AdvancedPlayerInterface {
    
    private Strategy strategy;
    private CardGame cardGame;
    private Hand hand;
    private ArrayList<Card> discardedCards;
    
    AdvancedPlayer(Strategy strategy, BasicCheat game) {
        this.strategy = strategy;
        this.cardGame = game;
        this.hand = new Hand();
        this.discardedCards = new ArrayList();
    }

    @Override
    public void addDiscardedCard(Card c) {
        this.discardedCards.add(c);
    }

    @Override
    public void addDiscardedHand(Hand h) {
         for (Card c : h) {
             this.discardedCards.add(c);
        }
    }

    @Override
    public void resetDiscards() {
        this.discardedCards = new ArrayList();
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
