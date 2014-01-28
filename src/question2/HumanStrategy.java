package question2;

import java.util.Scanner;
import question2.Card.Rank;
import question2.Card.Suit;

public class HumanStrategy implements Strategy {

    private final String RANK_INPUT = "(two|three|four|five|six|seven|eight|nine"
            + "|ten|jack|queen|king|ace)";
    private final String SUIT_INPUT = "(clubs|diamonds|hearts|spades)";
    private final String NUMCARDS_INPUT = "(1|2|3|4)";
    private final String ANSWER_INPUT = "(yes|y|Y|no|N|n)";

    @Override
    public boolean cheat(Bid b, Hand h) {
        String str = "";
        boolean answer = false;
        if (h.countRank(b.getRank().getRankValue()) == 0
                && h.countRank(b.getRank().getRankValue() + 1) == 0) {
            System.out.println("You have to cheat!");
            answer = true;
            return answer;

        }
        while (!str.matches(ANSWER_INPUT)) {
            System.out.println("Would you like to Cheat?");

            Scanner in = new Scanner(System.in);
            str = in.nextLine();
            //return str.toLowerCase().equals("yes") || str.toLowerCase().equals("y");
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

    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        h.sortDescending();
        System.out.println(h.toString());
        Hand tempHand = new Hand();
        Bid newBid = new Bid();
        Rank rank = Rank.TWO;
        int count = 0;

        // If the player has choosen to cheat
        if (cheat == true) {
            while (count == 0) {
                Scanner in = new Scanner(System.in);
                System.out.println("How many cards would you like to play?");
                String numCardsStr = in.nextLine();

                if (!numCardsStr.matches(NUMCARDS_INPUT)) {

                    System.out.println("Invalid Number of Cards");

                } else {
                    int numCards = Integer.parseInt(numCardsStr);

                    System.out.println("What rank is your bid?(e.g. four not 4)");
                    in = new Scanner(System.in);
                    String rankIn = in.nextLine();
                    while (!rankIn.matches(RANK_INPUT)) {
                        System.out.println("\n Invalid Rank Given!");
                        System.out.println("What rank is your bid?(e.g. four not 4)");
                        in = new Scanner(System.in);
                        rankIn = in.nextLine();

                    }
                    Rank r = Rank.valueOf(rankIn.toUpperCase());
                    if (r.getRankValue() > b.getRank().getRankValue() + 1) {
                        System.out.println("Inavlid Rank Given!");
                    } else {

                        for (int i = 0; i < numCards; i++) {
                            System.out.println("What card do you want to play for card " + (i + 1) + "?");
                            in = new Scanner(System.in);
                            String cardIn = in.nextLine();
                            String[] parts = cardIn.split(" of ");
                            if (parts[0].matches(RANK_INPUT) && parts[1].matches(SUIT_INPUT)) {
                                Card tempCard = new Card(
                                        Rank.valueOf(parts[0].toUpperCase()),
                                        Suit.valueOf(parts[1].toUpperCase()));
                                h.remove(tempCard);
                                tempHand.add(tempCard);

                            } else {
                                System.out.println("Invalid Card inputed");
                                i--;
                            }
                        }

                        newBid = new Bid(tempHand, r);
                        count++;
                    }
                }
            }
        }
        if (cheat == false) {
            while (count == 0) {
                Scanner in = new Scanner(System.in);
                System.out.println("How many cards would you like to play?");
                String numCardsStr = in.nextLine();
                if (!numCardsStr.matches(NUMCARDS_INPUT)) {

                    System.out.println("Invalid Number of Cards");

                } else {
                    int numCards = Integer.parseInt(numCardsStr);
                    String rankIn = "";
                    while (!rankIn.matches(RANK_INPUT)) {
                        System.out.println("What rank is your bid?(e.g. four not 4)");
                        in = new Scanner(System.in);
                        rankIn = in.nextLine();
                        if (!rankIn.matches(RANK_INPUT)) {
                            System.out.println("Invalid Rank Given");
                        } else {
                            Rank r = Rank.valueOf(rankIn.toUpperCase());
                            if (h.countRank(r.getRankValue()) == numCards) {
                                for (Card c : h) {
                                    if (c.getRank() == r) {
                                        tempHand.add(c);
                                        rank = c.getRank();
                                    }
                                }
                                h.remove(tempHand);
                                newBid = new Bid(tempHand, rank);
                                count++;
                            } else {

                                System.out.println("Invalid Play!");

                            }
                        }
                    }
                }

            }

        }
        return newBid;

    }

    @Override
    public boolean callCheat(Hand h, Bid b) {
        Scanner in = new Scanner(System.in);
        boolean answer = false;
        System.out.println("Would you like to Call Cheat?");
        String str = in.nextLine();
        if (str.equals("yes") || str.equals("y") || str.equals("Y")) {
            answer = true;
        }
        if (str.equals("no") || str.equals("n") || str.equals("N")) {
            answer = false;
        }
        return answer;


    }
}
