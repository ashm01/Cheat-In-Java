package question2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import question2.Card.Suit;
import question2.Card.Rank;


/**
 * Deck class that implements serializable and iterable. Contains all the 
 * methods regarding the Deck object
 * @author Ash
 */
public class Deck implements Serializable, Iterable<Card> {
    //Declaring the class variables
    //Deck is an arraylist of Card objects
    private final ArrayList<Card> deck;
    //Constant Variable for the serialisationID
    private static final long serialVersionUID  = 101;
    
    /**
     * Deck constructor which creates a new deck of 52 cards objects 2 to ace of 
     * hearts, spades, diamonds and clubs
     */
    public Deck() {
        //Initialise empty list of cards
        deck = new ArrayList<>();
        //2 loops to iterate through all four suits creating a card object of
        //every rank value for every suit 
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                //Creating the card of the given rank and suit
                Card nextCard = new Card(r, s);
                deck.add(nextCard);
            }
        }


    }
    
    /**
     * Method to randomly shuffle the order of the deck of cards
     */
    public void shuffleDeck() {
        //Using the pre built shuffle function for collections
        Collections.shuffle(deck);

    }

    /**
     * Method to display every card of the deck
     */
    public void display() {
        //For each loop to iterate through evry card of the card 
        for (Card c : deck) {
            //Printing each card
            System.out.println(c);
        }
    }

    /**
     * Method to deal the top card of the deck. Removes that card from the deck
     * @return Top card of the deck
     */
    public Card deal() {
        return deck.remove(0);
    }

    /**
     * Method to create a new deck of cards
     * @return Deck
     */
    public Deck newDeck() {
        //Clears the current deck of cards
        deck.clear();
        //Initialise a new deck
        Deck d = new Deck();
        //returns the new deck
        return d;

    }

    /**
     * Method to get the size of the deck
     * @return size
     */
    public int size() {
        //Initialise the size counter
        int size = 0;
        //For each to iterate through every card
        for (Card c: this) {
            size++;
        }
        //returns the size
        return size;
    }
    /**
     * Iterator method overwritten to return a new deal iterator
     * @return dealIterator
     */
    @Override
    public Iterator<Card> iterator() {
        return new Deck.dealIterator();
    }
    
    /**
     *  Method to return the oddEven iterator
     * @return oddEvenIterator
     */
    public Iterator<Card> oddEvenIterator() {
        return new Deck.OddEvenIterator();
    }
    
    /**
     * OddEvenIterator class which implements iterator. This creates a iterator 
     * that iterates through all the odd placed elements with the deck then 
     * through all the even objects
     */
    private class OddEvenIterator implements Iterator<Card> {
        //Initialising the starting position to 1 so it starts with the odd
        //objects first
        int pos = 1;
        //Initialsing a tempory card object
        Card temp = deck.get(pos);
        /**
        * hasNext method for the iterator.
        * @return true or false
        */
        @Override
        public boolean hasNext() {
            //Checking whether card is less than the deck size. as soon as pos
            //reaches 53 (pos increases by 2 each time in the next method)
            //it will go into the bottom statement re setting pos to 0 so the
            //iteration will be through all the even elements
            if (pos < deck.size()) {
                return true;
            }
            if (pos > deck.size()) {
                pos = 0;
                return true;
            }
            return false;
        }
        /**
        * next method for the iterator
        * @return next card
        */
        @Override
        public Card next() {
            //temp is set to the next card
            temp = deck.get(pos);
            //increasing pos by 2 to continue with either odd or even iteration
            pos = pos + 2;
            //return next card
            return temp;

        }
        /**
        * remove method for iterator
        */
        @Override
        public void remove() {
            //remove current card
            deck.remove(pos);
        }
    }
    /**
     * DealIterator class which implements iterator, this will be used as the
     * default iterator for the deck object
     */
    private class dealIterator implements Iterator<Card> {
        //Initialising the pos to the sixe of the deck
        int pos = deck.size();
        /**
        * hasNext method for dealIterator
        * @return true or false
        */
        @Override
        public boolean hasNext() {
            //As soon as the pos reaches 0 false is returned
            if (pos >= 1) {
                return true;

            }
            return false;
        }
        /**
        * next method for deal iterator
        * @return next Card
        */
        @Override
        public Card next() {
            pos--;
            return deck.get(pos);
          
        }
        /**
        * remove method for deal iterator
        * @return
        */
        @Override
        public void remove() {
            // removes current card
            deck.remove(pos);
        }
    }
}
