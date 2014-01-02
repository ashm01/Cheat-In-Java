
package question1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import question1.Card.Rank;
import question1.Card.Suit;


public class Deck implements Serializable {
    
    private final ArrayList<Card> deck;
    private final double serialisationID = 101;
    public Deck (){

        deck = new ArrayList<Card>();
        for (Suit s : Suit.values())
        {
            for (Rank r : Rank.values())
            {
                Card nextCard = new Card(r, s);
	        deck.add(nextCard);
            }
	}
        
        
    }
    public void shuffleDeck(){
        
       Collections.shuffle(deck); 
           
    }
    
    
    public void display() {
	for (Card c : deck) {
		System.out.println(c);
	}
    }
    
    public Card deal() {
		return deck.remove(0);
	}
    
    public Deck newDeck (){
        deck.clear();
        
        Deck d = new Deck();
        
        return d;
        
    }
    
    public int size(){
        
        int size = this.size();
        
        return size;
    }
    
    
    
    
    public class OddEvenIterator implements Iterable{

        @Override
        public Iterator iterator() {
            return null;
            
        }
        
        
        
    }
    
}
