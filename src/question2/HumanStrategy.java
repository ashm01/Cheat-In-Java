
package question2;

import java.util.Scanner;


public class HumanStrategy implements Strategy {
    

    @Override
    public boolean cheat(Bid b, Hand h) {
        Scanner in = new Scanner(System.in);
        boolean answer=false;
        if (h.countRank(b.getRank().getRankValue()) == 0
                    && h.countRank(b.getRank().getRankValue() + 1) == 0){
            System.out.println("You have to cheat!");
            return answer;
        
        }
        System.out.println("Would you like to Cheat?");
        String str=in.nextLine();
        if(str.equals("yes")||str.equals("y")||str.equals("Y"))
            answer = true;
        if(str.equals("no")||str.equals("n")||str.equals("N"))
            answer = false;
        return answer;
    }

    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        
        Hand tempHand = new Hand();
        Bid newBid = new Bid();
        
        if(cheat==true){
            
        }
        if(cheat==false){
            Scanner in = new Scanner(System.in);
            
            
            
            
        }
        
        return newBid;
        
    }

    @Override
    public boolean callCheat(Hand h, Bid b) {
        Scanner in = new Scanner(System.in);
        boolean answer = false;
        System.out.println("Would you like to Call Cheat?");
        String str=in.nextLine();
        if(str.equals("yes")||str.equals("y")||str.equals("Y"))
            answer = true;
        if(str.equals("no")||str.equals("n")||str.equals("N"))
            answer = false;
        return answer;
        
        
    }
    
}
