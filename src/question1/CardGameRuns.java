
package question1;

import question1.Card.Rank;
import question1.Card.Suit;


public class CardGameRuns {
    
    public static void main(String[] args){
        
    Card card1 = new Card(Rank.TEN, Suit.DIAMONDS);    
    Card card2 = new Card(Rank.TEN, Suit.SPADES); 
    Card card3 = new Card(Rank.TWO, Suit.CLUBS);    
    Card card4 = new Card(Rank.SIX, Suit.HEARTS);
    Card card5 = new Card(Rank.QUEEN, Suit.HEARTS);
    
        System.out.println(card3.compareTo(card4));
        
        System.out.println(card1.toString());
        //System.out.println(card1.difference(card2));
        //System.out.println(card1.differenceValue(card5));
        //System.out.println(card3.differenceValue(card4));
        System.out.println(Card.Difference.difference(card1, card2));
        System.out.println(Card.Difference.differenceValue(card1, card5));
        System.out.println(Card.Difference.differenceValue(card3, card4));
    }
    
}
