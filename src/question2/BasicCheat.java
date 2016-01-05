package question2;

import java.util.*;
import question2.Card.Rank;
import question2.Card.Suit;

public class BasicCheat implements CardGame {

    private AdvancedPlayer[] players;
    private int nosPlayers;
    public static final int MINPLAYERS = 5;
    private int currentPlayer;
    private Hand discards;
    private Bid currentBid;

    public BasicCheat() {
        this(MINPLAYERS);
    }

    public BasicCheat(int n) {
        StrategyFactory strategy = new StrategyFactory();
        nosPlayers = n;
        players = new AdvancedPlayer[nosPlayers];
        for (int i = 0; i < nosPlayers; i++) {
            players[i] = (new AdvancedPlayer(strategy.randomStrategy(), this));
        }
        currentBid = new Bid();
        currentBid.setRank(Card.Rank.TWO);
        currentPlayer = 0;
    }

    @Override
    public boolean playTurn() {
//        lastBid=currentBid;
        //Ask player for a play,
        System.out.println("current bid = " + currentBid);

        currentBid = players[currentPlayer].playHand(currentBid);

        currentBid.setRank(currentBid.getRank());
        System.out.println("Player bid = " + currentBid);
        //Add hand played to discard pile
        discards.add(currentBid.getHand());
        //Offer all other players the chance to call cheat
        boolean cheat = false;
        for (int i = 0; i < players.length && !cheat; i++) {
            if (i != currentPlayer) {
                //System.out.println("Player "+(i+1));
                cheat = players[i].callCheat(currentBid);
                if (cheat) {
                    System.out.println("Player called cheat by Player "
                            + "" + (i + 1));
                    if (isCheat(currentBid)) {
//CHEAT CALLED CORRECTLY
//Give the discard pile of cards to currentPlayer who then has to play again                      
                        players[currentPlayer].addHand(discards);
                        System.out.println("Player cheats!");
                        System.out.println("Adding cards to player "
                                + (currentPlayer + 1) + players[currentPlayer]);
                        //resets each players discarded cards once cheat has 
                        //been succesfully called
                        for (int j = 0; j < players.length; j++) {
                            players[j].resetDiscards();
                        }

                    } else {
//CHEAT CALLED INCORRECTLY
//Give cards to caller i who is new currentPlayer
                        System.out.println("Player Honest");
                        currentPlayer = i;
                        players[currentPlayer].addHand(discards);
                        System.out.println("Adding cards to player "
                                + (currentPlayer + 1) + players[currentPlayer]);
                        //resets each players discarded cards once cheat has 
                        //been succesfully called
                        // if(players[currentPlayer].)
                        for (int j = 0; j < players.length; j++) {
                            players[j].resetDiscards();
                        }
                    }
//If cheat is called, current bid reset to an empty bid with rank two whatever 
//the outcome
                    currentBid = new Bid();
//Discards now reset to empty	
                    discards = new Hand();
                }
            }
        }
        if (!cheat) {
//Go to the next player       
            System.out.println("No Cheat Called");

            currentPlayer = (currentPlayer + 1) % nosPlayers;
        }
        return true;
    }

    @Override
    public void initialise() {
        //Create Deck of cards
        Deck d = new Deck();
        d.shuffleDeck();
        //Deal cards to players
        Iterator<Card> it = d.iterator();
        int count = 0;
        while (it.hasNext()) {
            players[count % nosPlayers].addCard(it.next());
            it.remove();
            count++;
        }
        //Initialise Discards
        discards = new Hand();
         //Chose first player
        Card twoClubs = new Card(Rank.TWO,Suit.CLUBS);
        //counter to determine the index of the current player
        int counter = 0;
        //iterate through every player
        for (AdvancedPlayer p : players) {
            //temp hand object which stors the hand for each player
            Hand temp = p.getHand();
            //iteration through each card of the hand
            for (Card c : temp) {
                //if the hand cotains two of clubs
                if(c.toString().equals(twoClubs.toString())){
                 //set current player to the player that has two of cluvs   
                currentPlayer = counter;    
                }
            }
           counter++; 
        }
        
        currentBid = new Bid();
        currentBid.setRank(Card.Rank.TWO);
    }

    public void playGame() {
        initialise();
       
        int c = 0;
        Scanner in = new Scanner(System.in);
        boolean finished = false;
        while (!finished) {
            //Play a hand 
            System.out.println("Cheat turn for PLAYER " + (currentPlayer + 1));
            playTurn();
            System.out.println("\nCurrent discards =\n" + discards);
            c++;
            for (int j = 0; j < players.length; j++) {
                            System.out.println("Player "+(j+1)+" cards left: "
                                    +players[j].cardsLeft());
                        }
            System.out.println("\nTurn " + c + " Complete. Press any key to "
                    + "continue or enter Q to quit>\n");
            String str = in.nextLine();
            if (str.equals("Q") || str.equals("q") || str.equals("quit")) {
                finished = true;
            }
            int w = winner();
//            if (w>0) {
//                System.out.println("The Winner is Player " + (w+1));
//                finished = true;
//            }
            if (winsGame()) {
                System.out.println("The Winner is Player " + (currentPlayer));
                finished = true;
            }
            System.out.println("--------------------------------------------"
                    + "-------------------------");
        }
    }

    @Override
    public int winner() {
        for (int i = 0; i < nosPlayers; i++) {
            if (players[i].cardsLeft() == 0) {
                return i;
            }
        }
        return -1;

    }

    public static boolean isCheat(Bid b) {
        for (Card c : b.getHand()) {
            if (c.getRank() != b.r) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BasicCheat cheat = new BasicCheat();
        cheat.playGame();
    }

    public boolean winsGame() {
        //if currentplayer is 0 reset pos to number of players so that the
        //last object in the players array can be accessed
        if (currentPlayer == 0) {
            int cPlayer = nosPlayers;
            //checking whether the player has any cards left returns true if so
            if (players[cPlayer - 1].cardsLeft() == 0) {
                return true;
            }
            //returns false is currentplayer has cards left
            return false;
            //Checking cards left for currentplayer when it doesnt = 0
        }else if(players[currentPlayer - 1].cardsLeft() == 0) {
            return true;
        }
        return false;

    }
}
