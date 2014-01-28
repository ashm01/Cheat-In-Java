package question2;

import java.util.Random;

/**
 *
 * @author Ash
 */
public class BasicStrategy implements Strategy {

    @Override
    public boolean cheat(Bid b, Hand h) {
       
        
            if (h.countRank(b.getRank().getRankValue()) > 0
                    || h.countRank(b.getRank().getNext().getRankValue()) > 0) {
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
                rank = b.getRank();
                h.remove(tempHand);
                
            }else {
                
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
            if (r == 0)
                rank = b.getRank();
            else
                rank = b.getRank().getNext();
            
            newBid = new Bid(tempHand, rank);
            

        }
        return newBid;
    }

    @Override
    public boolean callCheat(Hand h, Bid b) {

        if (b.getCount() > 4 - h.countRank(b.getRank().getRankValue())) {

            return true;

        } else {
            return false;
        }
    }
}
