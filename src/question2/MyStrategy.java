package question2;

import java.util.Random;

/**
 * My Strategy class that implements advanced strategy, Similar to the thinker
 * Strategy however the player will cheat 70% of time even if he doesnt need 
 * to. The player will also call cheat a higher percent of time. He calls cheat
 * %20 of the time even when they don't suspect the player is cheating. Also 
 * plays a random number of cards for each turn.
 *
 * @author Ash
 */
public class MyStrategy implements AdvancedStrategy {
    //Class variables
    Hand discardedCards = new Hand();
    private Random generator = new Random();

    /**
     * Boolean cheat method that determines if the player will cheat on their
     * turn or not
     *
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
            int r = generator.nextInt(10);
            //Player cheats %70 of the time even though he doesnt have to
            if (r > 2) {
                return true;
            }
            //30% of the time player doesnt cheat
            return false;
        }
        //player will have to cheat
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
        h.sortAscending();
        //Printing out players current hand
        //System.out.println("\nCurrent hand:\n" + h.toString());
        // This implys from boolean cheat method that the hand 
        // Does contain a card with the rank value equal to or one above the
        // the bid rank value
        if (cheat == false) {
            //Setting variables
            Card.Rank rank = b.getRank();
            //if there was a a card with the same rank value as the bid...
            if (h.countRank(rank.getRankValue()) > 0) {
                //iterate through each card in the hand
                for (Card c : h) {
                    //if there is a match add the card to the temp hand
                    if (c.getRank() == b.getRank()) {
                        tempHand.add(c);
                    }
                }
                //creating a random int between 0-9
                int rr = generator.nextInt(10);
                //if player had more then one of the rank in their hand
                if (tempHand.size() > 1) {
                    //10% of the time user will play not all the cards they have
                    //of the given rank
                    if (rr > 8) {
                        int r = generator.nextInt(tempHand.size());
                        tempHand.remove(r);
                    }
                }
                //Creating bid, removing cards and adding to discards
                rank = b.getRank();
                h.remove(tempHand);
                this.discardedCards.add(tempHand);
            } else {
                //iteration through eah card in the hand
                for (Card c : h) {
                    //if there is a match add the card to the temp hand
                    if (c.getRank() == b.getRank().getNext()) {
                        tempHand.add(c);
                    }
                }
                //creating a random int between 0-9
                int rr = generator.nextInt(10);
                //creating a random number within the size of the temp
                if (tempHand.size() > 1) {
                    //using the random to determine whether to play all cards of
                    //the rank
                    if (rr > 8) {
                        //creating a random number within the size of the temp
                        int r = generator.nextInt(tempHand.size());
                        //removes a card from the temphand
                        tempHand.remove(r);
                    }
                }
                //Creating bid, removing cards and adding to discards
                rank = b.getRank().getNext();
                h.remove(tempHand);
                this.discardedCards.add(tempHand);
            }
            //Creating the new bid
            newBid = new Bid(tempHand, rank);
        }
        // When the player must cheat
        if (cheat == true) {
            //Initialising a random int which will be used to select
            //the random card from the hand
            Card.Rank rank;
            //random amount of cards to be played in the cheat play
            int numCards = 0;
            //checking the number of cards the player has in his hand
            if (h.size() > 4) {
                numCards = generator.nextInt(4) + 1;
            } else if (h.size() == 1) {
                numCards = 1;
            } else {
                numCards = generator.nextInt(h.size() + 1);
            }
            //looping through the number of cards to be played
            for (int i = 0; i < numCards; i++) {
                //playing the players highest cards in the cheat play
                int index = h.size() - 1;
                tempHand.add(h.remove(index));
            }
            //Adds the random card to the tempory hand
            //tempHand.add(tc);
            int r = generator.nextInt(2);
            //Determining the rank of the new bid and creating the newBid
            if (r == 0) {
                rank = b.getRank();
            } else {
                rank = b.getRank().getNext();
            }

            newBid = new Bid(tempHand, rank);
            this.discardedCards.add(tempHand);
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
        //creating variable of the current bid rank
        int bidRank = b.getRank().getRankValue();
        //if the bid is greater then four minus the number of cards of that rank
        //in the players hand or discarded cards then player will call cheat most
        //of the time
        if (b.getCount() > 4 - h.countRank(bidRank)
                + discardedCards.countRank(bidRank)) {
            int r = generator.nextInt(10);
            //Player doesnt call cheat %10 of the time even tho they know the
            //player is cheating bit trivial but in real sense would happen 
            //sometime
            if (r > 8) {
                return false;
            }
            return true;
            // If the player has bid 2 cards and a player also holds 2 of that rank
            // or if the bid is 3 and the player hold one of that rank    
        } else if (b.getCount() + h.countRank(bidRank)
                + discardedCards.countRank(bidRank) == 4) {
            //random number between 0-9
            int r = generator.nextInt(10);
            //60% of the time cheat will be called in this case
            if (r > 3) {
                return true;
            }
            return false;
        } else {
            //for everything else the player will call cheat on any play 
            //%20 of the time
            int r = generator.nextInt(10);
            if (r > 7) {
                return true;
            } else {
                return false;
            }
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
