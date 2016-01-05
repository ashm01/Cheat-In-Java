package question2;

import java.util.Scanner;
import question2.Card.Rank;
import question2.Card.Suit;

/**
 * Human Strategy class that implements the advanced strategy interface
 * @author Ash
 */
public class HumanStrategy implements AdvancedStrategy {
    //Class constants that are used to check the inputs of the user
    private final String RANK_INPUT = "(two|three|four|five|six|seven|eight|nine"
            + "|ten|jack|queen|king|ace)";
    private final String SUIT_INPUT = "(clubs|diamonds|hearts|spades)";
    private final String NUMCARDS_INPUT = "(1|2|3|4)";
    private final String ANSWER_INPUT = "(yes|y|Y|no|N|n)";
    /**
     * Boolean cheat method that determines if the player will cheat on their 
     * turn or not
     * @return true or false
     */
    @Override
    public boolean cheat(Bid b, Hand h) {
        //Initialiising str to a blank string
        String str = "";
        boolean answer = false;
        //Checking whether the hand doesnt contains any of the bids rank or
        //the rank above
        if (h.countRank(b.getRank().getRankValue()) == 0
                && h.countRank(b.getRank().getRankValue() + 1) == 0) {
            //Player has to cheat
            System.out.println("You have to cheat!");
            return true;

        }
        //while the str input doesnt match the correct input
        while (!str.matches(ANSWER_INPUT)) {
            System.out.println("\nWould you like to Cheat?");
            //Initialising scanner object
            Scanner in = new Scanner(System.in);
            //setting str the answer the user entered into the console
            str = in.nextLine();
            //switch to check the input of the user
            switch (str) {
                case "yes":
                case "y":
                case "Y":
                    answer = true;
                    break;
                case "no":
                case "n":
                case "N":
                    answer = false;
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }
        return answer;
    }
    /**
     * Method to determine what cards the player will play in his bid
     * 
     * @param b the current bid 
     * @param h the players hand
     * @param cheat whether he needs to cheat or not
     * @return Bid the bid the player wants to play
     */
    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        //sorts the hand into descending order
        h.sortAscending();
        //prints players hand
        System.out.println("\nCurrent hand:\n"+h.toString());
        //creating hand, bid and rank objects
        Hand tempHand = new Hand();
        Bid newBid = new Bid();
        Rank rank = Rank.TWO;
        //initialising the counter which will be used in the while loop below
        int count = 0;
        // If the player has choosen to cheat
        if (cheat == true) {
            //continue looping until count is not zero
            while (count == 0) {
                //creating scanner object
                Scanner in = new Scanner(System.in);
                System.out.println("How many cards would you like to play?");
                //using scanner to get input from user 
                String numCardsStr = in.nextLine();
                //If the input isnt a valid input
                if (!numCardsStr.matches(NUMCARDS_INPUT)) {

                    System.out.println("Invalid Number of Cards");
                  //if the input was valid
                } else {
                    //creates an integer value from the string input
                    int numCards = Integer.parseInt(numCardsStr);
                    //prints command to the concole
                    System.out.println("What rank is your bid?"
                            + "(e.g. four not 4)");
                    //reinitialise scanner object
                    in = new Scanner(System.in);
                    //takes the rank input
                    String rankIn = in.nextLine();
                    //while the nput doesnt match the rank criteria repeat
                    while (!rankIn.matches(RANK_INPUT)) {
                        System.out.println("\n Invalid Rank Given!");
                        System.out.println("What rank is your bid?"
                                + "(e.g. four not 4)");
                        in = new Scanner(System.in);
                        rankIn = in.nextLine();

                    }
                    //Creating a rank object using the rank input 
                    Rank r = Rank.valueOf(rankIn.toUpperCase());
                    //if rank is invalid e.g. current bid rank is 2 and 
                    //new rankk is 4
                    if (r.getRankValue() > b.getRank().getRankValue() + 1) {
                        System.out.println("Inavlid Rank Given!");
                    } else {
                        //loop to repeat process for the number of cards the
                        //player chose to play earlier
                        for (int i = 0; i < numCards; i++) {
                            System.out.println("What card do you want to"
                                    + " play for card " + (i + 1) + "?");
                            //re initialise scanner object
                            in = new Scanner(System.in);
                            //taking input of the card they want to play
                            String cardIn = in.nextLine();
                            //split the string into rank and suit
                            String[] parts = cardIn.split(" of ");
                            //checking the input against the right criteria
                            if (parts[0].matches(RANK_INPUT) && 
                                    parts[1].matches(SUIT_INPUT)) {
                                //creatiing a card using the given details
                                Card tempCard = new Card(
                                        Rank.valueOf(parts[0].toUpperCase()),
                                        Suit.valueOf(parts[1].toUpperCase()));
                                //remove that card from the hand
                                h.remove(tempCard);
                                // add it to the tempory hand
                                tempHand.add(tempCard);
                                //invalid card input
                            } else {
                                System.out.println("Invalid Card inputed");
                                i--;
                            }
                        }
                        //creating the new bid
                        newBid = new Bid(tempHand, r);
                        //increment count so while loop is terminated
                        count++;
                    }
                }
            }
        }
        //if the player choose not to cheat
        if (cheat == false) {
            //while loop to repeat until count is incremented 
            while (count == 0) {
                //scanner object
                Scanner in = new Scanner(System.in);
                System.out.println("How many cards would you like to play?");
                //taken input from the console
                String numCardsStr = in.nextLine();
                //if the input was invalid
                if (!numCardsStr.matches(NUMCARDS_INPUT)) {

                    System.out.println("Invalid Number of Cards");

                } else {
                    //creating a integer from the input
                    int numCards = Integer.parseInt(numCardsStr);
                    //initialising the rankIn string
                    String rankIn = "";
                    //while rankIn doesnt match the input criteria for a rank
                    while (!rankIn.matches(RANK_INPUT)) {
                        System.out.println("What rank is your bid?"
                                + "(e.g. four not 4)");
                        in = new Scanner(System.in);
                        //Takes rank input
                        rankIn = in.nextLine();
                        //checking the input 
                        if (!rankIn.matches(RANK_INPUT)) {
                            System.out.println("Invalid Rank Given");
                        } else {
                            //creating rank object using the details inputted
                            Rank r = Rank.valueOf(rankIn.toUpperCase());
                            //checking whether the player has the number of 
                            //cards the user wants to play in his hand
                            if (h.countRank(r.getRankValue()) == numCards) {
                                //iterate through every card
                                for (Card c : h) {
                                    //checking whether the card equals the rank
                                    if (c.getRank() == r) {
                                        //add card to temp and set the rank of the
                                        //play
                                        tempHand.add(c);
                                        rank = c.getRank();
                                    }
                                }
                                //removing temp hand from current hand
                                h.remove(tempHand);
                                //bid to be played
                                newBid = new Bid(tempHand, rank);
                                //increment count so inital while is terminated
                                count++;
                            } else {
                                //prints invalid play
                                System.out.println("Invalid Play!");
                            }
                        }
                    }
                }
            }
        }
        //returns the newBid
        return newBid;
    }
    /**
     * Method to determine what cards the player will call cheat
     * 
     * @param b the current bid 
     * @param h the players hand
     * @return true or false
     */
    @Override
    public boolean callCheat(Hand h, Bid b) {
        //initialising scanner object
        Scanner in = new Scanner(System.in);
        //setting boolean answer
        boolean answer = false;
        //printing command to user
        System.out.println("\nWould you like to Call Cheat?");
        //taking user input
        String str = in.nextLine();
        //Determining what to do with the users input
        if (str.equals("yes") || str.equals("y") || str.equals("Y")) {
            answer = true;
        }
        if (str.equals("no") || str.equals("n") || str.equals("N")) {
            answer = false;
        }
        //return answer
        return answer;


    }
    //Not implemented in this strategy
    @Override
    public void resetDiscardCards() {
        
    }
}
