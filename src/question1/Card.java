
package question1;

import java.io.Serializable;
import java.util.Comparator;



/**
 * Card class that implements serializable and comparable. Has all the methods 
 * regarding the card objects
 * Ashley Moore - 23/01/2014
 * @author Ash
 */
public class Card implements Serializable, Comparable<Card>  {

    //Declaring the class variables
    private Card.Suit suit;
    private Card.Rank rank;
    //Constant Variable for the serialisationID
    private static final long serialVersionUID  = 100;
    
    /**
     * Card constructor which takes two parameters, setting the objects fields
     * to the corresponding parameter values
     * @param rank The rank of the card e.g. ACE
     * @param suit The suit of the Card e.g CLUBS
     */
    public Card(Card.Rank rank, Card.Suit suit) {
        //Setting the the object fields to the given parameters
        this.rank = rank;
        this.suit = suit;
    }
    
    /**
     * Default constructor for creating a Card object, creating a empty card
     */
    public Card(){
        
    }

    
    
    /**
     * Constructs the Rank enum object which is used to create the card object.
     * With each enum containing two values, the rank value(jack, queen = 10)
     * and the card value which is regards the face value of the card(jack = 11)
     */
    public enum Rank{ TWO(2,2), THREE(3,3), FOUR(4,4), FIVE(5,5)
    ,SIX(6,6), SEVEN(7,7), EIGHT(8,8), NINE(9,9), TEN(10,10),
    JACK(11,10), QUEEN(12,10), KING(13,10), ACE(14,11);
        //Declaring the enum fields
        final int cardValue;
        final int cardRankValue;
        /**
        *  Default constructor for the rank object
        */
        Rank(int rank, int val){
            //Setting the the object fields to the given parameters
            this.cardValue = val;
            this.cardRankValue = rank;
        }
    
        /**
         * Get method to return the next rank
         * @return nextRank
         */
        public Card.Rank getNext(){
            //Gets the next rank which wraps around- King.getNext returns Ace 
            return values()[(ordinal()+1)%values().length];
            }
        
        
        /**
         * Get method to return the cardValue of the card
         * @return cardValue
         */
        public int getCardValue() {
            return cardValue;
        }

        /**
         * Get method to return the rank value of the card
         * @return cardRankValue
         */
        public int getRankValue() {
            return cardRankValue;
        }
    }
   
    /**
     * Constructs the Suit enum object which is used to create the card object. 
     */
    public enum Suit{CLUBS, DIAMONDS, HEARTS, SPADES}
    /**
     * CompareTo method that compares two cards
     * @return compare 1 or 0 in regards to the compare
     */
     @Override
    public int compareTo(Card c) {
     //setting compare to the result of comparing this card to the card parsed    
     int compare = this.getRank().compareTo(c.getRank());
     if (compare ==0){
         //if there is match we then compare the suits of the cards
         compare = this.getSuit().compareTo(c.getSuit());
     }
     //return the result
     return compare ;  
    }

    /**
     * get method to get the suit of the card
     * @return suit
     */
    public Card.Suit getSuit() {
        return suit;
    }

    /**
     * get method to get the rank of the card
     * @return rank
     */
    public Card.Rank getRank() {
        return rank;
    }
     
    
    /**
     * Method to return the card as a string
     * @return string representation of the card
     */
    @Override
    public String toString() {
        return String.format("%s of %s", this.rank, this.suit);
    }
    
   
    /**
     * Nested class that contains all the methods to determine the difference 
     * between two cards
     */
    public static class Difference{
       
         
        /**
         * Method that finds the difference between rankValues of two cards
         * e.g. difference between king and queen is 0 however between king and 
         * seven is 3
         * @param c Card 
         * @param d Card
         * @return answer the result of the difference calculation
         */
        public static int difference(Card c, Card d){
        //using the abs function to find the difference between the two cards
        //rank value. so 10 - 2 = 8 & 2 - 10 = 8    
        int answer = 
              Math.abs(d.getRank().getRankValue()- c.getRank().getRankValue());
        return answer;
    }
    
        /**
         * Method that finds the difference between cardValues of two cards
         * e.g. difference between king and queen is 1 & between king and 
         * seven is 6
         * @param c Card 
         * @param d Card
         * @return answer the result of the difference calculation
         */
        public static int differenceValue(Card c, Card d){
        //using the abs function to find the difference between the two cards
        //rank value. so 10 - 2 = 8 & 2 - 10 = 8       
        int answer = 
              Math.abs(d.getRank().getCardValue()- c.getRank().getCardValue()); 
        return answer;
    } 
       
       
      
   } 
   
    /**
     * CompareDescending class the implements comparator. Holds the method to 
     * compare two cards based on their rank
     */
    public static class CompareDescending implements Comparator<Card>{
        /**
        * Compare method which compares the two given cards by their rankValue
        * @return result of the compare
        */
        @Override
        public int compare(Card c1, Card c2) {
            //Checking to see if c1 rank is less then c2 rank
            if (c1.getRank().getRankValue()<c2.getRank().getRankValue()){
                return 1;
            //if the ranks the same    
            }else if(c1.getRank().getRankValue()==c2.getRank().getRankValue()){
                return 0;
            //When the rank is greater from c1 to c2    
            }else 
                return -1;
            
        }
    }
   
    /**
     * CompareSuit class the implements comparator. Holds the method to compare 
     * two cards based on their given suits
     */
    public static class CompareSuit implements Comparator<Card>{
        /**
        * Compare method which compares the two given cards by their suit
        * @return result of the compare
        */
        @Override
        public int compare(Card c1, Card c2) {
            //uses the compareTo method 
            return c1.getSuit().compareTo(c2.getSuit());
            

        }
    }
    
    
    
    
    
}
