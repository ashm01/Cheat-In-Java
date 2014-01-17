package question2;

import java.util.Locale;
import java.util.Scanner;
import question2.Card.Rank;

public class HumanStrategy implements Strategy {

    @Override
    public boolean cheat(Bid b, Hand h) {
        Scanner in = new Scanner(System.in);
        boolean answer = false;
        if (h.countRank(b.getRank().getRankValue()) == 0
                && h.countRank(b.getRank().getRankValue() + 1) == 0) {
            System.out.println("You have to cheat!");
            answer = true;
            return answer;

        }
        System.out.println("Would you like to Cheat?");
        String str = in.nextLine();
        if (str.equals("yes") || str.equals("y") || str.equals("Y")) {
            answer = true;
        } else if (str.equals("no") || str.equals("n") || str.equals("N")) {
            answer = false;
        } else {
            System.out.println("Invalid Input!");
            cheat(b, h);
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


        if (cheat == true) {
            while (count == 0) {
                Scanner in = new Scanner(System.in);
                System.out.println("How many cards would you like to play?");
                int numCards = in.nextInt();
                if (numCards > 4 || numCards < 1) {
                    System.out.println("Invalid number of cards!!");

                } else {
                    System.out.println("What rank is your bid?(e.g. four not 4)");
                    in = new Scanner(System.in);
                    String rankIn = in.nextLine();
                    Rank r = Rank.valueOf(rankIn.toUpperCase());
                    if (r.getRankValue() > b.getRank().getRankValue() + 1) {
                        System.out.println("Inavlid Rank Given!");
                    } else {
                        for (int i = 0; i < numCards; i++) {
                            System.out.println("What card do you want to play?");
                            in = new Scanner(System.in);
                            String cardIn = in.nextLine();
                            boolean match;
                            for (Card c : h) {
                                if (cardIn.toLowerCase().matches(c.toString().toLowerCase())) {
                                    tempHand.add(c);
                                    match = true;
                                }
                            }
                        }
                        h.remove(tempHand);
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
                int numCards = in.nextInt();
                if (numCards > 4 || numCards < 1) {
                    System.out.println("Invalid number of cards!!");

                } else {
                    System.out.println("What rank is your bid?(e.g. four not 4)");
                    in = new Scanner(System.in);
                    String rankIn = in.nextLine();
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

                        System.out.println("You have no cards of that rank");

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
