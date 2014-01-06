package question1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import question1.Card.Rank;
import question1.Card.Suit;

public class Deck implements Serializable, Iterable<Card> {

    private final ArrayList<Card> deck;
    private final double serialisationID = 101;

    public Deck() {

        deck = new ArrayList<Card>();
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

        int size = this.size();

        return size;
    }

    @Override
    public Iterator<Card> iterator() {
        return new dealIterator();
    }
    
    public Iterator<Card> oddEvenIterator() {
        return new OddEvenIterator();
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

        int pos = deck.size()-1;

        @Override
        public boolean hasNext() {

            if (pos >= 0) {
                return true;

            }
            return false;
        }

        @Override
        public Card next() {
            return deck.get(pos--);
          
        }

        @Override
        public void remove() {

            deck.remove(pos);
        }
    }
}
