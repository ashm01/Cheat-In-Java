
package question1;

import java.io.Serializable;
import java.util.Comparator;


public class Card implements Serializable, Comparable<Card>  {

    
    private Suit suit;
    private Rank rank;
    private final double serialisationID = 100;
    
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    
    
    public enum Rank{ TWO(2,2), THREE(3,3), FOUR(4,4), FIVE(5,5)
    ,SIX(6,6), SEVEN(7,7), EIGHT(8,8), NINE(9,9), TEN(10,10),
    JACK(11,10), QUEEN(12,10), KING(13,10), ACE(14,11);
    
        final int cardValue;
        final int cardRankValue;
        
        Rank(int rank, int val){
            
            this.cardValue = val;
            this.cardRankValue = rank;
        }
    
        public Rank getNext(){
            //Gets the next enum...... King.getNext returns Ace 
            return values()[(ordinal()+1)%values().length];
            }
        
        
        public int getCardValue() {
            return cardValue;
        }

        public int getRankValue() {
            return cardRankValue;
        }
    }
   
    public enum Suit{CLUBS, DIAMONDS, HEARTS, SPADES}
    
     @Override
    public int compareTo(Card c) {
         
     int compare = this.getRank().compareTo(c.getRank());
     if (compare ==0){
         compare = this.getSuit().compareTo(c.getSuit());
     }
     return compare ;  
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
     
    
    
    @Override
    public String toString() {
        return String.format("%s of %s", this.rank, this.suit);
    }
    
   
   public static class Difference{
       
         
     public static int difference(Card c, Card d){
        int answer = 
              Math.abs(d.getRank().getRankValue()- c.getRank().getRankValue());
        return answer;
    }
    
    public static int differenceValue(Card c, Card d){
        int answer = 
              Math.abs(d.getRank().getCardValue()- c.getRank().getCardValue()); 
        return answer;
    } 
       
       
      
   } 
   
   public static class CompareDescending implements Comparator<Card>{

        @Override
        public int compare(Card c1, Card c2) {
            
            if (c1.getRank().getRankValue()<c2.getRank().getRankValue()){
                return 1;
            }else if(c1.getRank().getRankValue()==c2.getRank().getRankValue()){
                return 0;
            }else 
                return -1;
            
        }
    }
   
   public static class CompareSuit implements Comparator<Card>{

        @Override
        public int compare(Card c1, Card c2) {
            
            return c1.getSuit().compareTo(c2.getSuit());
            

        }
    }
    
    
    
    
    
}
