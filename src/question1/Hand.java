package question1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import question1.Card.CompareDescending;
import question1.Card.CompareSuit;

/**
 * Hand class that implements iterable and serializable. This class contains
 * all the methods regarding the hand object
 * @author Ash
 */
public class Hand implements Iterable<Card>, Serializable {
    //Declaring the class variables
    private final ArrayList<Card> hand;
    private static final long serialVersionUID = 102;
    //Arrays used to store the count for each rank and suit in the hand
    private int[] rankCount = new int[13];
    private int[] suitCount = new int[4];
    
    /**
     * Hand default constructor which creates an empty hand
     */
    public Hand() {
        //Initialise empty hand
        this.hand = new ArrayList();
    }
    
    /**
     * Hand constructor that takes one parameter h an arraylist of cards
     * @param h arraylist to be added
     */
    public Hand(ArrayList<Card> h) {
        //Initialise empty hand
        hand = new ArrayList();
        //adds each card within the araylist to the new hand
        for (Card c : h){
            hand.add(c);     
        }  
    }
    
    /**
     * Hand constructor that takes a parameter of h an Hand object
     * @param h hand to be added
     */
    public Hand(Hand h){
        //Initialise empty hand
        hand = new ArrayList();
        //adds each card within the hand to the new hand
        for (Card c : h){
            hand.add(c);        
        }
    }
    /**
     * Method to add a given card to the hand
     * @param c Card to be added
     */
    public void add(Card c) {
        //Taking the rank and suit of the given card as integer values which 
        //will be used to specify the place in the counter array to be added to
        int rank = c.getRank().cardRankValue - 2;
        int suit = c.getSuit().ordinal();
        hand.add(c);
        //adds to the suit and rank counter
        rankCount[rank]++;
        suitCount[suit]++;
    }
    
    /**
     * Method to add a given list of cards to the hand
     * @param cards list of cards to be added
     */
    public void add(Collection<Card> cards) {
        //for each loop to iterate through very card in the parsed arraylist
        for (Card c : cards) {
            //Taking the rank and suit of the given card as integer values which 
            //will be used to specify the place in the counter array to be added to
            int pos = c.getRank().cardRankValue - 2;
            int suit = c.getSuit().ordinal();
            hand.add(c);
            //adds to the suit and rank counter
            rankCount[pos]++;
            suitCount[suit]++;
        }
        
    }
    
    /**
     * Method to add a given hand to the current hand
     * @param h hand to be added
     */
    public void add(Hand h) {
        //for each loop to iterate through very card in the parsed hand
        for (Card c : h) {
            //Taking the rank and suit of the given card as integer values which 
            //will be used to specify the place in the counter array to be added to
            int pos = c.getRank().cardRankValue - 2;
            int suit = c.getSuit().ordinal();
            hand.add(c);
            //adds to the suit and rank counter
            rankCount[pos]++; 
            suitCount[suit]++;
        }
    }
    
    /**
     * Method to remove a card from the hand
     * @param c card to be removed
     * @return true or false is card is present
     */
    public boolean remove(Card c) {
        //loop through evry card in the hand
        for (Card temp : hand) {
            //checking whether the card is the hand
            if (temp == c) {
                //Taking the rank and suit of the given card as integer values which 
                //will be used to specify the place in the counter array to be 
                //taken away from
                int pos = c.getRank().cardRankValue - 2;//-2 as rank 2 is 0 in the array
                int suit = c.getSuit().ordinal();
                //takes one from the suit and rank counter
                rankCount[pos]--; 
                suitCount[suit]--;
                //remove the acrd from the hand
                hand.remove(c);
                //return true
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method to remove a hand from the current hand
     * @param h hand to be removed
     * @return true or false if hand is present
     */
    public boolean remove(Hand h) {
        //Initialisng counter
        int count = 0;
        //Creating temporary card object array
        Card[] temp = new Card[h.size()];
         //Iterate through every card in the hand
        for (Card c : h) {
            //if current hand contains each card that was parsed in the hand
            if (hand.contains(c)) {
                //Taking the rank and suit of the given card as integer values which 
                //will be used to specify the place in the counter array to be 
                //taken away from
                int pos = c.getRank().cardRankValue - 2;
                int suit = c.getSuit().ordinal();
                //takes one from the suit and rank counter
                rankCount[pos]--; 
                suitCount[suit]--;
                //add card to the temp array
                temp[count] = c; 
                //add one to count
                count++;
                
            }else return false;//as soon as there isnt a match false is returned
            
        }
        //iterate through all the cards in the temp array removing each one
        for (int i = 0; i < temp.length; i++) {
            hand.remove(temp[i]);
        }
        //return true
        return true;
    }
    
    /**
     * Method to remove a card from the hand in a given position
     * @param pos position of card to remove
     * @return Card 
     */
    public Card remove(int pos) {
        //Creates temp card duplicate of the one in the given position
        Card temp = hand.get(pos);
        //Taking the rank and suit of the given card as integer values which 
        //will be used to specify the place in the counter array to be 
        //taken away from
        int rank = temp.getRank().cardRankValue - 2;
        int suit = temp.getSuit().ordinal();
        //takes one from the suit and rank counter
        rankCount[rank]--; 
        suitCount[suit]--;
        //removes the card from the given position
        hand.remove(pos);
        //returns the temp card
        return temp;
        
    }
    /*
     * Method to return the default hand iterator 
     */
    @Override
    public Iterator<Card> iterator() {
        return hand.iterator();
    }
    
    /**
     * Method to determine the size of the hand
     * @return size
     */
    public int size() {
        //Initialise size as 0
        int size = 0;
        //adds to the counter through every iteration
        for (Card c: this) {
            size++;
        }
        //returns size
        return size;
    }
    
    /**
     * Method which sorts the hand into ascending order of rank
     */
    public void sortAscending(){
        //uses the defualt card sort method to sort list into ascending order
        Collections.sort(hand);
    }
    
    /**
     * Method to sort the hand into descending order of rank
     */
    public void sortDescending(){
        //sorts the hand using the compare descending method from the card class
        Collections.sort(hand, new CompareDescending());
    }
    
    /**
     * Method to sort the hand into order of suits e.g. clubs, diamonds hearts etc
     */
    public void sortSuit(){
        //sorts the hand using the compare suit method from the card class
        Collections.sort(hand, new CompareSuit());
    }
    
    /**
     * Method to the number of occurrences of a given rank in the hand
     * @param rank to count
     * @return the number of occurrences
     */
    public int countRank(int rank){
        
        return rankCount[rank-2];
    }
    
    /**
     * Method to the number of occurrences of a given suit in the hand
     * @param suit to count
     * @return the number of occurrences
     */
    public int countSuit(int suit){
        
        return suitCount[suit];
    }
    /**
     * Method to find the value of this hand
     * @return value of hand
     */
    public int handValue(){
        //Initialising handValue to 0
        int handValue = 0;
        //Iterattion through every card
        for (Card c : hand) {
            //adding the rank to the current hand value
            handValue = handValue + c.getRank().getCardValue();   
        }
        return handValue;
    }

    /**
     * Method to return the string representation of the hand
     * @return String Hand
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card c : this.hand) {
            
            sb.append(c.toString()+"\n");
        }
        return sb.toString();
    }
    
    /**
     * Boolean method to check whether the hand is a flush(all cards same suit)
     * @return True or false
     */
    public boolean isFlush(){
        //for loop to iterate through hand
        for (int i = 0; i < this.size()-1; i++) {
            //using the ordinal of the suit to check whether the current and next
            //card are of the same suit
            if (hand.get(i).getSuit().ordinal()==
                    hand.get(i+1).getSuit().ordinal()) {
                
            }else return false;//As soon as there isnt a match false is returned
        }
        return true;//if the whole hand is iterated through true is returned
    }
    
    /**
     * Boolean method to check whether the hand is a straight
     * @return true or false
     */
    public boolean isStraight(){
        //First sorts the hand into ascending order
        this.sortAscending();
        //for loop to iterate through hand
        for (int i = 0; i < this.size()-1; i++) {
            //using the getrankValue to check whether the current and next
            //card are of consecutive order
            if (hand.get(i).getRank().getRankValue()+1==
                    hand.get(i+1).getRank().getRankValue()) {
                
            }else return false;//As soon as there isnt a match false is returned
        }
        return true;//if the whole hand is iterated through true is returned
    }
}
