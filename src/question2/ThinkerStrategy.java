package question2;

import java.util.Random;

/**
 * Thinker Strategy class that implements advanced strategy
 * @author Ash
 */
public class ThinkerStrategy implements AdvancedStrategy {
    //Stores information regarding the cards the player has discarded
    Hand discardedCards = new Hand();
    //random number 
    Random rand = new Random();
    /**
     * Boolean cheat method that determines if the player will cheat on their 
     * turn or not
     * @param b current bid
     * @param h players hand
     * @return true or false
     */
    @Override
    public boolean cheat(Bid b, Hand h) {
        //Checking whether the player has a card of the current or next rank
        if (h.countRank(b.getRank().getRankValue()) > 0
                || h.countRank(b.getRank().getNext().getRankValue()) > 0) {
            //creating a random number between 0-9
            int r = rand.nextInt(10);
            //if the random number is above 7(20%) the player still cheats
            //even tho they dont need to
            if (r > 7) {
                return true;
            }
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
        //initialising temporyHand
        Hand tempHand = new Hand();
        //Initialising the bid
        Bid newBid = new Bid();
        //sorts the hand into descending order
        h.sortDescending();
        //printing out current hand
        //System.out.println("\nCurrent hand:\n"+h.toString());
        // This implys from boolean cheat method that the hand 
        // Does contain a card with the rank value equal to or one above the
        // the bid rank value
        if (cheat == false) {          
            //Creates rank which is the same as bid
            Card.Rank rank = b.getRank();
            //for when there was a a card with the same rank value as the bid...
            if (h.countRank(rank.getRankValue()) > 0) {
                //iterate through each card in the hand
                for (Card c : h) {
                    //if there is a match add the card to the temp hand
                    if (c.getRank() == b.getRank()) {
                        tempHand.add(c);
                    }
                }
                //creating a random int between 0-9
                int rr = rand.nextInt(10);
                //if player had more then one of the rank in their hand
                if (tempHand.size() > 1) {
                    //10% of the time user will play not all the cards they have
                    //of the given rank
                    if (rr > 8) {
                        //creating a random number within the size of the temp
                        int r = rand.nextInt(tempHand.size());
                        //removes a card from the temphand
                        tempHand.remove(r);
                    }
                }
                //getting from the current bid
                rank = b.getRank();
                //removing cards from current hand
                h.remove(tempHand);
                //adding the discarded cards to the discarded card list
                this.discardedCards.add(tempHand);
                
            //For when the player only has cards of the rank above the current
            //rank bid
            } else {
                //iteration through eah card in the hand
                for (Card c : h) {
                    //if there is a match add the card to the temp hand
                    if (c.getRank() == b.getRank().getNext()) {
                        tempHand.add(c);
                    }
                }
                //creating a random int between 0-9
                int rr = rand.nextInt(10);
                //If the hand conatined more then one of the given rank
                if (tempHand.size() > 1) {
                    //using the random to determine whether to play all cards of
                    //the rank
                    if (rr > 8) {
                        //creating a random number within the size of the temp
                        int r = rand.nextInt(tempHand.size());
                        //removes a card from the temphand
                        tempHand.remove(r);
                    }
                }
                //getting from the current bid
                rank = b.getRank().getNext();
                //removing cards from current hand
                h.remove(tempHand);
                //adding the discarded cards to the discarded card list
                this.discardedCards.add(tempHand);
            }
            //Creating the new bid
            newBid = new Bid(tempHand, rank);
        }
        // When the player must cheat
        if (cheat == true) { 
            //initialising rank object
            Card.Rank rank;
            //Initialising a random int which will be used to select
            //the random card from the hand
            int randomIndex = rand.nextInt(h.size());
            //random number between 0-1 to determine the rank of cheat bid
            int r = rand.nextInt(10);
            //Plays highest card %70 of the time if not a random card is played
            if(r>2)
                randomIndex = h.size()-1;
            //removing the card
            //Adds the random card to the tempory hand
            tempHand.add(h.remove(randomIndex));
            //Determining the rank of the new bid and creating the newBid
            if (r == 0) {
                rank = b.getRank();
            } else {
                rank = b.getRank().getNext();
            }
            //creating new bid
            newBid = new Bid(tempHand, rank);
            //adding removed cards to the discarded list
            this.discardedCards.add(tempHand);
        }
        //return newBdid
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
        //creating variable of the current bid rank
        int bidRank = b.getRank().getRankValue();
        //if the bid is greater then four minus the number of cards of that rank
        //in the players hand or discarded cards then player will call cheat
        if (b.getCount() > 4 - h.countRank(bidRank)
                + discardedCards.countRank(bidRank)) {
            return true;
            // If the player has bid 2 cards and a player also holds 2 of that rank
            // or if the bid is 3 and the player hold one of that rank    
        } else if (b.getCount() + h.countRank(bidRank)
                + discardedCards.countRank(bidRank) == 4) {
            //random number between 0-9
            int r = rand.nextInt(10);
            //20% of the time cheat will be called in this case even though its
            //certain player will cheating
            if (r > 7) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }
    /*
     * Method to reset the discardedCards of the player
     */
    @Override
    public void resetDiscardCards() {
        //re initialise the discardedCards
        this.discardedCards = new Hand();
    }
}
