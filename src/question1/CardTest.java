package question1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * CardTest class which is used to test the card, deck and hand classes
 * @author Ash
 */
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
            //If statement to have 4 cards on each line
            if(count % 4 ==0)
            {
                System.out.printf("%2d %s %n",count,it.next());
            }else
            {
                System.out.printf("%2d %-25s ",count,it.next());
            }
            count++;
        }
        continueOn();
        //Changing the iteration to the oddeven iterator
        it = deck.oddEvenIterator();
        //counter for the while loop
        int count1 = 1;
        //Printing out all the cards after the odd even iteration
        //with some formating applied
        System.out.println("\n\nOdd Even Iteration:");
        while (it.hasNext()) 
        {
            if(count1 % 4 ==0)
            {
                System.out.printf("%2d %s %n",count1,it.next());
            }else
            {
                System.out.printf("%2d %-25s ",count1,it.next());
            }
            count1++;
        }
        continueOn();
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
        
        
        System.out.println(divider);
        
        System.out.println("\nPlayer Two's Hand: ");
        for (Card c : hand2) {
            System.out.println(c);
        }
        
        System.out.println(divider);
        
        System.out.println("\nPlayer Three's Hand: ");
        for (Card c : hand3) {
            System.out.println(c);
        }
        
        System.out.println(divider);
        
        System.out.println("\nPlayer Four's Hand: ");
        for (Card c : hand4) {
            System.out.println(c);
        }
        
        System.out.println(divider);
        
        continueOn();
        //Saving the hands
        
        //Storing hands in a temp list
        ArrayList<Hand> handList = new ArrayList();
        handList.add(hand1);
        handList.add(hand2);
        handList.add(hand3);
        handList.add(hand4);
        //try catch to catch any exceptions that occur when saving the hands
        try{
            //for loop to save every hand from the temp array
            for (int i = 0; i < handList.size(); i++) 
            {
                String filename = "hand"+i+".ser";
                FileOutputStream fos = new  FileOutputStream (filename); 
                ObjectOutputStream out = new  ObjectOutputStream (fos);
                out.writeObject(handList.get(i));
                out.close();
                
            }
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        System.out.println("\nHands Saved to hands.ser \n");
        
        continueOn();
        
        System.out.println("\nHand Values:\n");
        //printing out hands value 
        System.out.println("\nHand 1 Value = "+hand1.handValue());
        System.out.println("Hand 2 Value = "+hand2.handValue());
        System.out.println("Hand 3 Value = "+hand3.handValue());
        System.out.println("Hand 4 Value = "+hand4.handValue());
        
        continueOn();
        //Displaying whether the hands are flushes or straights
        System.out.println("\nHand 1:");
        System.out.println("\nFlush:"+hand1.isFlush());
        System.out.println("Straight:"+hand1.isStraight());
        System.out.println("\nHand 2:");
        System.out.println("\nFlush:"+hand2.isFlush());
        System.out.println("Straight:"+hand2.isStraight());
        System.out.println("\nHand 3:");
        System.out.println("\nFlush:"+hand3.isFlush());
        System.out.println("Straight:"+hand3.isStraight());
        System.out.println("\nHand 4:");
        System.out.println("\nFlush:"+hand4.isFlush());
        System.out.println("Straight:"+hand4.isStraight());
        
        continueOn();
        
        //printing rank and suit counts
        System.out.println("\n Rank and Suit counts for each hand: \n");
        int c = 1;
        for (Hand hand : handList) {
            System.out.println("\nHand"+c+" Rank occurrences:");
            System.out.println("Number of 2's: "+hand.countRank(2));
            System.out.println("Number of 3's: "+hand.countRank(3));
            System.out.println("Number of 4's: "+hand.countRank(4));
            System.out.println("Number of 5's: "+hand.countRank(5));
            System.out.println("Number of 6's: "+hand.countRank(6));
            System.out.println("Number of 7's: "+hand.countRank(7));
            System.out.println("Number of 8's: "+hand.countRank(8));
            System.out.println("Number of 9's: "+hand.countRank(9));
            System.out.println("Number of 10's: "+hand.countRank(10));
            System.out.println("Number of Jacks: "+hand.countRank(11));
            System.out.println("Number of Queens: "+hand.countRank(12));
            System.out.println("Number of Kings: "+hand.countRank(13));
            System.out.println("Number of Aces: "+hand.countRank(14));
            
            System.out.println("\nHand"+c+" Suit occurrences:");
            System.out.println("Number of Clubs: "+hand.countSuit(0));
            System.out.println("Number of Diamondss: "+hand.countSuit(1));
            System.out.println("Number of Hearts: "+hand.countSuit(2));
            System.out.println("Number of Spadess: "+hand.countSuit(3));

            
            c++;
            
        }
        continueOn();
        //Sorting the hands
        hand1.sortAscending();
        hand2.sortAscending();
        hand3.sortDescending();
        hand4.sortSuit();
        //Printing the hands
        System.out.println("\nHands After sort:");
        System.out.println("Hand 1(Ascending): \n"+hand1.toString());
        System.out.println("Hand 2(Ascending): \n"+hand2.toString());
        System.out.println("Hand 3(Descending): \n"+hand3.toString());
        System.out.println("Hand 4(Suit): \n"+hand4.toString());
        System.out.println(divider);
        
        continueOn();
        int size = handList.size();
        handList.clear();
        //Loading hands which where saved earlier
        //try catch to catch any exceptions that occur when loading the hands
        try{
            //for loop to load every hand into the arraylist
            for (int i = 0; i < size; i++) 
            {
                String filename = "hand"+i+".ser";
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fis);
                handList.add((Hand)in.readObject());
                in.close();
                
            }
            
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        //re initialising the hands from the arraylist
        hand1 = handList.get(0);
        hand2 = handList.get(1);
        hand3 = handList.get(2);
        hand4 = handList.get(3);
        
         //Printing the hands that hav just been loaded
        System.out.println("\nHands after being loaded from hands.ser:");
        System.out.println("Hand 1: \n"+hand1.toString());
        System.out.println("Hand 2: \n"+hand2.toString());
        System.out.println("Hand 3: \n"+hand3.toString());
        System.out.println("Hand 4: \n"+hand4.toString());
        System.out.println(divider);
        
        continueOn();
        
        Iterator<Card> it2 = hand1.iterator();
        while(it2.hasNext())
        {
            Card temp2 = it2.next();
            it2.remove();
            hand2.add(temp2);
            
        }
        //Print outs showing the changes to the hands
        System.out.println("\nHands After Add/Remove Iteration:");
        System.out.println("Hand 1: \n"+hand1.toString());
        System.out.println("Hand 2: \n"+hand2.toString());
        System.out.println("Hand 3: \n"+hand3.toString());
        System.out.println("Hand 4: \n"+hand4.toString());
        System.out.println(divider);
        
        continueOn();
        
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
        System.out.println("Hand 1: \n"+hand1.toString());
        System.out.println("Hand 2: \n"+hand2.toString());
        System.out.println("Hand 3: \n"+hand3.toString());
        System.out.println("Hand 4: \n"+hand4.toString());
        System.out.println(divider);
        
        
    }
    
    private static void continueOn(){
        //Asking the user whether they would like to continue onto the next step
        Scanner input = new Scanner(System.in);
        System.out.println("\nPress any key to continue or enter Q to quit>");
        String str=input.nextLine();
        if(str.equals("Q")||str.equals("q")||str.equals("quit"))
              System.exit(0);
    }
}
