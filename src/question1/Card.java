
package question1;


public class Card implements Comparable<Card> {
 
    
    public enum Rank{ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, 
    NINE, TEN, JACK, QUEEN, KING;
    
        public Rank getNext(){
            //Gets the next enum...... King.getNext returns Ace 
            return values()[(ordinal()+1)%values().length];
            }
        }
   
    public enum Suit{CLUBS, DIAMONDS, HEARTS, SPACES}
    
    private Suit suit;
    private Rank rank;
    
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }
    
    
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
     
    public static int difference(Card c1, Card c2){
        
        
    } 
}
