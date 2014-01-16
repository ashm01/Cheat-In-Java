package question2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import question2.Card.CompareDescending;
import question2.Card.CompareSuit;

public class Hand implements Iterable<Card>, Serializable {
    
    private final ArrayList<Card> hand;
    private static final long serialVersionUID = 102;
    private int[] rankCount = new int[13];
    private int[] suitCount = new int[4];
    
    public Hand() {
        
        this.hand = new ArrayList();
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
        
        Card[] temp = new Card[h.size()];
        for (Card c : h) {
            if (hand.contains(c)) {
                int pos = c.getRank().cardRankValue - 2;
                int suit = c.getSuit().ordinal();
                rankCount[pos]--; 
                suitCount[suit]--;
                temp[count] = c;                
                count++;
                System.out.println(pos +" number of ="+rankCount[pos]);
            }else return false;
            
        }
        for (int i = 0; i < temp.length; i++) {
            hand.remove(temp[i]);
        }
        
        return true;
    }
    
    public Card remove(int pos) {
        Card temp = hand.get(pos);
        int rank = temp.getRank().cardRankValue - 2;
        int suit = temp.getSuit().ordinal();
        rankCount[rank]--; 
        suitCount[suit]--;
        hand.remove(pos);
        return temp;
        
    }
    
    @Override
    public Iterator<Card> iterator() {
        return hand.iterator();
    }
    
    public int size() {
        
        int size = 0;
        for (Card c: this) {
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
    
    public void sortSuit(){
        Collections.sort(hand, new CompareSuit());
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
        StringBuilder sb = new StringBuilder();
        for (Card c : this.hand) {
            
            sb.append(c.toString()+"\n");
        }
        return sb.toString();
    }
    
    public boolean isFlush(){
        
        for (int i = 0; i < this.size()-1; i++) {
            if (hand.get(i).getSuit().ordinal()==
                    hand.get(i+1).getSuit().ordinal()) {
                
            }else return false;
        }
        return true;
    }
    
    public boolean isStraight(){
        this.sortAscending();
        for (int i = 0; i < this.size()-1; i++) {
            if (hand.get(i).getRank().getRankValue()+1==
                    hand.get(i+1).getRank().getRankValue()) {
                
            }else return false;
        }
        return true;
    }
}
