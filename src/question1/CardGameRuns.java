
package question1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import question1.Card.CompareDescending;
import question1.Card.CompareSuit;
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
        
        List<Card> listOfCards = new ArrayList<Card>();
        listOfCards.add(card5);
        listOfCards.add(card4);
        listOfCards.add(card3);
        listOfCards.add(card2);
        listOfCards.add(card1);
        
        Collections.sort(listOfCards, new CompareDescending());
        for (Card c : listOfCards) {
		System.out.println(c);
	}
        System.out.println("");
        Collections.sort(listOfCards, new CompareSuit());
        for (Card c : listOfCards) {
		System.out.println(c);
	}
        
//        Deck deck1 = new Deck();
//        deck1.display();
//        deck1.shuffleDeck();
//        System.out.println("");
//        deck1.display();
//        
//        
//        String filename = "test.ser";
//        try{
//        FileOutputStream fos = new  FileOutputStream (filename); 
//        ObjectOutputStream out = new  ObjectOutputStream (fos);
//        out.writeObject(deck1);
//        out.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        
       
        
            
        
       
    }
    
}
