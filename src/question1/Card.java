
package question1;

import java.util.Comparator;


public class Card implements Comparable<Card>  {

    
    private Suit suit;
    private Rank rank;
    
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }
    
    public enum Rank{ACE(1,11), TWO(2,2), THREE(3,3), FOUR(4,4), FIVE(5,5)
    ,SIX(6,6), SEVEN(7,7), EIGHT(8,8), NINE(9,9), TEN(10,10),
    JACK(11,10), QUEEN(12,10), KING(13,10);
    
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
        return String.format("Card is %s of %s", this.rank, this.suit);
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
   
   public class CompareDescending implements Comparator<Card>{

        @Override
        public int compare(Card c1, Card c2) {
            return 0;
            
        }
    }
   
   public class CompareSuit implements Comparator<Card>{

        @Override
        public int compare(Card c1, Card c2) {
            return 0;
            
        }
    }
    
    
    
    
    
}
