package question2;

import java.util.Random;

/**
 * Basic Strategy class that implements the advanced strategy interface
 * @author Ash
 */
public class BasicStrategy implements AdvancedStrategy {
    
    /**
     * Boolean cheat method that determines if the player will cheat on their 
     * turn or not
     * @return true or false
     */
    @Override
    public boolean cheat(Bid b, Hand h) {
            //Checking whether the player has cards that is equal to the current
            //bid rank or one above
            //If so false is returned if not the player will have to cheat
            if (h.countRank(b.getRank().getRankValue()) > 0
                    || h.countRank(b.getRank().getNext().getRankValue()) > 0) {
                return false;
            }
        return true;
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
        //Creating a tempory hand
        Hand tempHand = new Hand();
        //Initialising the bid to be played
        Bid newBid = new Bid();
        //sorts the hand into descending order
        h.sortAscending();
        //printing out current hand
        //System.out.println("\nCurrent hand:\n"+h.toString());
        // This implys from boolean cheat method that the hand 
        // Does contain a card with the rank value equal to or one above the
        // the bid rank value
        if (cheat == false) {
            //Setting variables
            Card.Rank rank = b.getRank();
            //if there was a a card with the same rank value as the bid
            if (h.countRank(rank.getRankValue()) > 0) 
            {
                //iterate through each card in the hand
                for (Card c : h) {
                    //if there is a match add the card to the temp hand
                    if (c.getRank() == b.getRank()) {
                        tempHand.add(c);    
                    }
                }
                //removing the cards from the hand
                h.remove(tempHand);
                
            }else {
                //iteration through each card of the hand
                for (Card c : h) {
                    //if there is a match add the card to the temp hand
                    if (c.getRank() == b.getRank().getNext()) {
                        tempHand.add(c);
                    }
                }
                rank = b.getRank().getNext();
                h.remove(tempHand);
            }
            //Creating the new bid
            newBid = new Bid(tempHand, rank);
        }
        // When the player must cheat
        if (cheat == true) {
            //Initialising a random int which will be used to select
            //the random card from the hand
            Card.Rank rank;
            Random generator = new Random();
            int randomIndex = generator.nextInt(h.size());
            int r = generator.nextInt(2);
            Card tc =h.remove(randomIndex); 
            //Adds the random card to the tempory hand
            tempHand.add(tc);
            //Determining the rank of the new bid and creating the newBid
            //either same as the current or the next rank
            if (r == 0)
                rank = b.getRank();
            else
                rank = b.getRank().getNext();
            //Creating the new bid
            newBid = new Bid(tempHand, rank);
        }
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
        //checking whether the players bid is possible as the most the player
        //can have of one rank is 4, so if the opponent plays 3 twos
        //and im holding 2 i know that play is a cheat
        if (b.getCount() > 4 - h.countRank(b.getRank().getRankValue())) {
            return true;
        } else {
            return false;
        }
    }
    //Not implemented in this strategy
    @Override
    public void resetDiscardCards() {
        
    }
}
