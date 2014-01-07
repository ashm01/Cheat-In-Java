package question1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import question1.Card.CompareDescending;

public class Hand implements Iterable<Card>, Serializable {
    
    private final ArrayList<Card> hand;
    private final double serialisationID = 102;
    private int[] rankCount = new int[13];
    private int[] suitCount = new int[4];
    
    public Hand() {
        
        this.hand = null;
    }
    
    public Hand(ArrayList<Card> h) {
        
        this.hand = h;
        suitAndRankCounter();
    }
    
    public Hand(Hand h){
        
        hand = new ArrayList();
        for (Card c : h){
            
            hand.add(c);
            suitAndRankCounter();
            
        }
    }
    
    
    
    private void suitAndRankCounter(){
        for (Card c : hand) {
        int rank = c.getRank().cardRankValue - 2;
        int suit = c.getSuit().ordinal();    
        rankCount[rank]++;
        suitCount[suit]++;  
        
        }
        
    }
    
    public void add(Card c) {
        int rank = c.getRank().cardRankValue - 2;
        int suit = c.getSuit().ordinal();
        hand.add(c);
        rankCount[rank]++;
        suitCount[suit]++;
    }
    
    public void add(Collection<Card> cards) {
        for (Card c : cards) {
            int pos = c.getRank().cardRankValue - 2;
            int suit = c.getSuit().ordinal();
            hand.add(c);
            rankCount[pos]++;
            suitCount[suit]++;
        }
        
    }
    
    public void add(Hand h) {
        for (Card c : h) {
            int pos = c.getRank().cardRankValue - 2;
            int suit = c.getSuit().ordinal();
            hand.add(c);
            rankCount[pos]++; 
            suitCount[suit]++;
        }
    }
    
    public boolean remove(Card c) {
        for (Card temp : hand) {
            if (temp == c) {
                int pos = c.getRank().cardRankValue - 2;
                int suit = c.getSuit().ordinal();
                rankCount[pos]--; 
                suitCount[suit]--;
                hand.remove(c);
                return true;
            }
        }
        return false;
    }
    
    public boolean remove(Hand h) {
        int count = 0;
        
        Card[] temp = new Card[size(h)];
        for (Card c : h) {
            if (hand.contains(c)) {
                int pos = c.getRank().cardRankValue - 2;
                int suit = c.getSuit().ordinal();
                rankCount[pos]--; 
                suitCount[suit]--;
                temp[count] = c;                
                count++;
            }else return false;
            
        }
        for (int i = 0; i < temp.length; i++) {
            hand.remove(temp[i]);
        }
        return true;
    }
    
    public Card remove(int pos) {
        Card temp = hand.get(pos);
        int pos2 = temp.getRank().cardRankValue - 2;
        int suit = temp.getSuit().ordinal();
        rankCount[pos]--; 
        suitCount[suit]--;
        hand.remove(pos);
        return temp;
        
    }
    
    @Override
    public Iterator<Card> iterator() {
        return hand.iterator();
    }
    
    public int size(Hand h) {
        
        int size = 0;
        for (Card c: h) {
            size++;
        }
        
        return size;
    }
    
    public void sortAscending(){
        
        Collections.sort(hand);
    }
    
    public void sortDescending(){
        Collections.sort(hand, new CompareDescending());
    }
    
    public int countRank(int rank){
        
        return rankCount[rank-2];
    }
    
    public int countSuit(int suit){
        
        return suitCount[suit];
    }
    public int handValue(){
        int handValue = 0;
        for (Card c : hand) {
            handValue = handValue + c.getRank().getCardValue();   
        }
        return handValue;
    }

    @Override
    public String toString() {
        return "Hand{" + "hand=" + hand + '}';
    }
    
    public boolean isFlush(){
        
        for (int i = 0; i < size(this)-1; i++) {
            if (hand.get(i).getSuit().ordinal()==
                    hand.get(i+1).getSuit().ordinal()) {
                
            }else return false;
        }
        return true;
    }
    
    public boolean isStraight(){
        this.sortAscending();
        for (int i = 0; i < size(this)-1; i++) {
            if (hand.get(i).getRank().getRankValue()+1==
                    hand.get(i+1).getRank().getRankValue()) {
                
            }else return false;
        }
        return true;
    }
}
