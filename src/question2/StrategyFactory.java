
package question2;

/**
 *
 * @author Ash
 */
public class StrategyFactory {
    
   

    public Strategy myStrategy(String s){
        Strategy strat; 
        s = s.toUpperCase();
        switch(s){
            case "THINKER": strat = new ThinkerStrategy();
                            break;
            case "HUMAN":   strat = new HumanStrategy();
                            break;
            case "MY":      strat = new MyStrategy();
                            break; 
            default:        strat = new BasicStrategy();
                            break;
        }
        return strat;
        
    }
    
    
    
    
}
