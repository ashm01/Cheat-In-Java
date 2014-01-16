package question2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import question2.Card.Suit;
import question2.Card.Rank;


public class Deck implements Serializable, Iterable<Card> {

    private final ArrayList<Card> deck;
    private static final long serialVersionUID  = 101;

    public Deck() {

        deck = new ArrayList<>();
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                Card nextCard = new Card(r, s);
                deck.add(nextCard);
            }
        }


    }

    public void shuffleDeck() {

        Collections.shuffle(deck);

    }

    public void display() {
        for (Card c : deck) {
            System.out.println(c);
        }
    }

    public Card deal() {
        return deck.remove(0);
    }

    public Deck newDeck() {
        deck.clear();

        Deck d = new Deck();

        return d;

    }

    public int size() {

        int size = 0;
        for (Card c: this) {
            size++;
        }
        
        return size;
    }

    @Override
    public Iterator<Card> iterator() {
        return new Deck.dealIterator();
    }
    
    public Iterator<Card> oddEvenIterator() {
        return new Deck.OddEvenIterator();
    }
    

    private class OddEvenIterator implements Iterator<Card> {

        int pos = 1;
        Card temp = deck.get(pos);

        @Override
        public boolean hasNext() {

            if (pos < deck.size()) {
                return true;
            }
            if (pos > deck.size()) {
                pos = 0;
                return true;
            }
            return false;
        }

        @Override
        public Card next() {
            temp = deck.get(pos);
            pos = pos + 2;
            return temp;

        }

        @Override
        public void remove() {

            deck.remove(pos);
        }
    }

    private class dealIterator implements Iterator<Card> {

        int pos = deck.size();

        @Override
        public boolean hasNext() {

            if (pos >= 1) {
                return true;

            }
            return false;
        }

        @Override
        public Card next() {
            pos--;
            return deck.get(pos);
          
        }

        @Override
        public void remove() {

            deck.remove(pos);
        }
    }
}
