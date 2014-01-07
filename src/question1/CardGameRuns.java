
package question1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
    Card card9 = new Card(Rank.QUEEN, Suit.HEARTS);
    Card card6 = new Card(Rank.TEN, Suit.CLUBS);
    Card card7 = new Card(Rank.ACE, Suit.HEARTS);
    Card card8 = new Card(Rank.KING, Suit.HEARTS);
    Card card5 = new Card(Rank.TEN, Suit.HEARTS);
//        System.out.println(card3.compareTo(card4));
//        
//        System.out.println(card1.toString());
        //System.out.println(card1.difference(card2));
        //System.out.println(card1.differenceValue(card5));
        //System.out.println(card3.differenceValue(card4));
//        System.out.println(Card.Difference.difference(card1, card2));
//        System.out.println(Card.Difference.differenceValue(card1, card5));
//        System.out.println(Card.Difference.differenceValue(card3, card4));
        
        ArrayList<Card> col1 = new ArrayList<Card>();
        col1.add(card1);
        col1.add(card2);
        col1.add(card3);
        col1.add(card4);
        col1.add(card5);
        Hand hand = new Hand(col1);
        
        System.out.println(hand.countSuit(0));
        System.out.println(hand.countRank(10));
        System.out.println(hand.toString());
        System.out.println();
        hand.add(card6);
        System.out.println(hand.countSuit(0));
        System.out.println(hand.countRank(10));
//        hand.remove(card6);
        System.out.println(hand.countSuit(0));
        System.out.println(hand.countRank(10));
        
        ArrayList<Card> col2 = new ArrayList<Card>();
        col2.add(card7);
        col2.add(card8);
        col2.add(card9);
        System.out.println("10's =" +hand.countRank(10));
        System.out.println("Hearts ="+ hand.countSuit(2));
        hand.add(col2);
        System.out.println("10's =" +hand.countRank(10));
        System.out.println("Hearts ="+ hand.countSuit(2));
//        hand.remove(8);
//        System.out.println("10's =" +hand.countRank(10));
//        System.out.println("Hearts ="+ hand.countSuit(2));
        Hand hand2 = new Hand(col2);
        hand.remove(hand2);
        System.out.println("10's =" +hand.countRank(10));
        System.out.println("Hearts ="+ hand.countSuit(2));
        hand.add(hand2);
        System.out.println("10's =" +hand.countRank(10));
        System.out.println("Hearts ="+ hand.countSuit(2));
        hand.sortDescending();
        System.out.println(hand.toString());
        hand.sortAscending();
        System.out.println(hand.toString());
        System.out.println(hand2.isStraight());
        System.out.println(hand2.isFlush());
        
//        Collections.sort(listOfCards, new CompareDescending());
//        for (Card c : listOfCards) {
//		System.out.println(c);
//	}
//        System.out.println("");
//        Collections.sort(listOfCards, new CompareSuit());
//        for (Card c : listOfCards) {
//		System.out.println(c);
//	}
//        Deck deck1 = new Deck();
////        deck1.display();
////        deck1.shuffleDeck();
//        System.out.println("");
////        deck1.display();
//        int count = 0;
//        Iterator<Card> it = deck1.iterator();
//        System.out.println(it.hasNext());
//        while(it.hasNext()){
//            System.out.println(count +" "+it.next());
//            count++;
//        }
        
        
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
