package question1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class CardTest {

    public static void main(String[] args) {
        //Creating the deck
        Deck deck = new Deck();
        //Initialising the deck iterator
        Iterator<Card> it = deck.iterator();
        //Counter for the while loop
        int count = 1;
        //Printing out all the cards after the normal deal iteration
        //with some formating applied
        System.out.println("Normal Deal Iteration:");
        while (it.hasNext()) 
        {
            //If statement to have 5 cards on each line
            if(count % 5 ==0)
            {
                System.out.printf("%s %n",it.next());
            }else
            {
                System.out.printf("%-25s ",it.next());
            }
            count++;
        }
        //Changing the iteration to the oddeven iterator
        it = deck.oddEvenIterator();
        //counter for the while loop
        int count1 = 1;
        //Printing out all the cards after the odd even iteration
        //with some formating applied
        System.out.println("\n\nOdd Even Iteration:");
        while (it.hasNext()) 
        {
            if(count1 % 5 ==0)
            {
                System.out.printf("%s %n",it.next());
            }else
            {
                System.out.printf("%-25s ",it.next());
            }
            count1++;
        }
        //Shuffling the deck
        deck.shuffleDeck();
       //Initialising the four hands 
       Hand hand1 = new Hand();
       Hand hand2 = new Hand();
       Hand hand3 = new Hand();
       Hand hand4 = new Hand();
       int deckSize = deck.size();
       //Dealing the all the cards to four players
        for (int i = 0; i < deckSize; i=i+4) {
            hand1.add(deck.deal());
            hand2.add(deck.deal());
            hand3.add(deck.deal());
            hand4.add(deck.deal());
            
        }
        //Printing out each players hands
        String divider = "-------------------------------------------";
        System.out.println("");
        System.out.println(divider);
        System.out.println("\nPlayer One's Hand: ");
        for (Card c : hand1) {
            System.out.println(c);
        }
        //Showing whether the hand is a flush or a straight
        System.out.println("\nFlush:"+hand1.isFlush());
        System.out.println("Straight:"+hand1.isStraight());
        System.out.println(divider);
        
        System.out.println("\nPlayer Two's Hand: ");
        for (Card c : hand2) {
            System.out.println(c);
        }
        System.out.println("\nFlush:"+hand2.isFlush());
        System.out.println("Straight:"+hand2.isStraight());
        System.out.println(divider);
        
        System.out.println("\nPlayer Three's Hand: ");
        for (Card c : hand3) {
            System.out.println(c);
        }
        System.out.println("\nFlush:"+hand3.isFlush());
        System.out.println("Straight:"+hand3.isStraight());
        System.out.println(divider);
        
        System.out.println("\nPlayer Four's Hand: ");
        for (Card c : hand4) {
            System.out.println(c);
        }
        System.out.println("\nFlush:"+hand4.isFlush());
        System.out.println("Straight:"+hand4.isStraight());
        System.out.println(divider);
        //Saving the hands
        String filename = "hands.ser";
        try{
        FileOutputStream fos = new  FileOutputStream (filename); 
        ObjectOutputStream out = new  ObjectOutputStream (fos);
        out.writeObject(hand1);
        out.writeObject(hand2);
        out.writeObject(hand3);
        out.writeObject(hand4);
        out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
       //Sorting the hands
        hand1.sortAscending();
        hand2.sortAscending();
        hand3.sortDescending();
        hand4.sortSuit();
        //Printing the hands
        System.out.println("\nHands After sort:");
        System.out.println("Hand 1(Ascending): "+hand1.toString());
        System.out.println("Hand 2(Ascending): "+hand2.toString());
        System.out.println("Hand 3(Descending): "+hand3.toString());
        System.out.println("Hand 4(Suit): "+hand4.toString());
        System.out.println(divider);
        
        //Loading hands which where saved earlier
        try{
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fis);
            hand1 = (Hand)in.readObject();
            hand2 = (Hand)in.readObject();
            hand3 = (Hand)in.readObject();
            hand4 = (Hand)in.readObject(); 
            in.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        
         //Printing the hands that hav just been loaded
        System.out.println("\nHands After sort:");
        System.out.println("Hand 1: "+hand1.toString());
        System.out.println("Hand 2: "+hand2.toString());
        System.out.println("Hand 3: "+hand3.toString());
        System.out.println("Hand 4: "+hand4.toString());
        System.out.println(divider);
        
        Iterator<Card> it2 = hand1.iterator();
        while(it2.hasNext())
        {
            Card temp = it2.next();
            it2.remove();
            hand2.add(temp);
            
        }
        //Print outs showing the changes to the hands
        System.out.println("\nHands After Add/Remove Iteration:");
        System.out.println("Hand 1: "+hand1.toString());
        System.out.println("Hand 2: "+hand2.toString());
        System.out.println("Hand 3: "+hand3.toString());
        System.out.println("Hand 4: "+hand4.toString());
        System.out.println(divider);
        
        //Rearranging the hands so each hand 2 to Ace of the same suit
        hand2.add(hand3);
        hand2.add(hand4);
        //Reinitialising 3 hands 
        hand1 = new Hand();
        hand3 = new Hand();
        hand4 = new Hand();
        //Creating a blank tempory Card object
        Card tempCard = new Card();
        //Iterator used to iterate through the hand that holds all the cards
        Iterator<Card> it3 = hand2.iterator();
        //Iteration through every card within hand2(all 52 cards)
        while(it3.hasNext())
        {   
            //Storing the current card as the the tempCard
            tempCard = it3.next();
            //Checking the ordinal value of every card which represents the suit
            //0 = clubs, 1 Diamonds, 2 Hearts and 3 Spades
            //If a club, remove from hand 2 and add to hand 1
            //Process is repeated for 3 suits leaving spades in the iterated hand
            if(tempCard.getSuit().ordinal()==0){
            it3.remove();
            hand1.add(tempCard);
            }else if(tempCard.getSuit().ordinal()==1){
            it3.remove();
            hand3.add(tempCard);
            }else if(tempCard.getSuit().ordinal()==2){
            it3.remove();
            hand4.add(tempCard);
            }
            
        }
        //Sorts the hands into ascdening order 2 to Ace
        hand1.sortAscending();
        hand2.sortAscending();
        hand3.sortAscending();
        hand4.sortAscending();
        //Print outs showing the changes to the hands
        System.out.println("\nHands After Sorted into Suits 2 to Ace:");
        System.out.println("Hand 1: "+hand1.toString());
        System.out.println("Hand 2: "+hand2.toString());
        System.out.println("Hand 3: "+hand3.toString());
        System.out.println("Hand 4: "+hand4.toString());
        System.out.println(divider);
        
        
    }
}
