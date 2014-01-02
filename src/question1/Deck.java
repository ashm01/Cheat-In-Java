
package question1;

import java.util.ArrayList;
import java.util.Collections;
import question1.Card.Rank;
import question1.Card.Suit;


public class Deck {
    
    private final ArrayList<Card> deck;
    
   
    
    
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
        
        
//        Collections.shuffle(deck);
//        for(Card c :deck){
//            System.out.println(c); 
//        }
    }
    
    
    
    
}
