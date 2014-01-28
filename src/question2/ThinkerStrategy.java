package question2;

import java.util.Random;

/**
 *
 * @author Ash
 */
public class ThinkerStrategy implements AdvancedStrategyInterface {

    Hand discardedCards = new Hand();

    @Override
    public boolean cheat(Bid b, Hand h) {


        if (h.countRank(b.getRank().getRankValue()) > 0
                || h.countRank(b.getRank().getNext().getRankValue()) > 0) {
            Random rand = new Random();
            int r = rand.nextInt(10);
            if (r > 8) {
                return true;
            }

            return false;
        }

        return true;

    }

    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {

        Hand tempHand = new Hand();

        Bid newBid = new Bid();

        // This implys from boolean cheat method that the hand 
        // Does contain a card with the rank value equal to or one above the
        // the bid rank value
        if (cheat == false) {
            //Setting variables

            Card.Rank rank = b.getRank();
            //loops through each card counting how many cards equal the bid rank

            //if there was a a card with the same rank value as the bid...
            if (h.countRank(rank.getRankValue()) > 0) {

                //iterate through each card in the hand
                for (Card c : h) {
                    //if there is a match add the card to the temp hand
                    if (c.getRank() == b.getRank()) {
                        tempHand.add(c);

                    }
                }
                Random ran = new Random(10);
                int rr = ran.nextInt();
                if (tempHand.size() > 1) {
                    if (rr > 8) {
                        Random rand = new Random();
                        int r = rand.nextInt(tempHand.size());
                        tempHand.remove(r);
                    }
                }
                rank = b.getRank();
                h.remove(tempHand);
                this.discardedCards.add(tempHand);
                System.out.println("discaaards" + this.discardedCards.toString());

            } else {

                for (Card c : h) {
                    //if there is a match add the card to the temp hand
                    if (c.getRank() == b.getRank().getNext()) {
                        tempHand.add(c);

                    }

                }
                Random ran = new Random(10);
                int rr = ran.nextInt();
                if (tempHand.size() > 1) {
                    if (rr > 8) {
                        Random rand = new Random();
                        int r = rand.nextInt(tempHand.size());
                        tempHand.remove(r);
                    }
                }
                rank = b.getRank().getNext();
                h.remove(tempHand);
                this.discardedCards.add(tempHand);
                System.out.println("discaaards" + this.discardedCards.toString());

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
            Card tc = h.remove(randomIndex);

            //Adds the random card to the tempory hand
            tempHand.add(tc);

            //Determining the rank of the new bid and creating the newBid
            if (r == 0) {
                rank = b.getRank();
            } else {
                rank = b.getRank().getNext();
            }

            newBid = new Bid(tempHand, rank);
            this.discardedCards.add(tempHand);
            System.out.println("discaaards" + this.discardedCards.toString());
        }
        return newBid;


    }

    @Override
    public boolean callCheat(Hand h, Bid b) {
        int bidRank = b.getRank().getRankValue();
        if (b.getCount() > 4 - h.countRank(bidRank)
                + discardedCards.countRank(bidRank)) {

            return true;
            // If the player has bid 2 cards and a player also holds 2 of that rank
            // or if the bid is 3 and the player hold one of that rank    
        } else if (b.getCount() + h.countRank(bidRank)
                + discardedCards.countRank(bidRank) == 4) {
            Random generator = new Random();
            int r = generator.nextInt(10);
            //40% of the time cheat will be called in this case
            if (r > 5) {
                return true;
            }
            return false;


        } else {
            return false;
        }
    }

    @Override
    public void resetDiscardCards() {
        this.discardedCards = new Hand();
    }
}
