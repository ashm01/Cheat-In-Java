package question2;

import java.util.Random;

/**
 *
 * @author Ash
 */
public class StrategyFactory {
    /**
     * Method to take in a string and return a strategy associated with that
     * string e.g. "human" will return the human strategy
     *
     * @param s string representation of a strategy
     * @return Strategy
     */
    public AdvancedStrategy myStrategy(String s) {
        //creating empty strategy object
        AdvancedStrategy strat;
        //making the string parsed into uppercase
        s = s.toUpperCase();
        //switch to determine what strategy is returned
        switch (s) {
            case "THINKER":
                strat = new ThinkerStrategy();
                break;
            case "HUMAN":
                strat = new HumanStrategy();
                break;
            case "MY":
                strat = new MyStrategy();
                break;
            case "RANDOM":
                strat = randomStrategy();
            default:
                strat = new BasicStrategy();
                break;
        }
        return strat;
    }

    /**
     * Method to choose a strategy randomly
     *
     * @return Strategy
     */
    public AdvancedStrategy randomStrategy() {
        //Initialing random number generator
        Random generator = new Random();
        //random int between 0-2
        int r = generator.nextInt(4);
        AdvancedStrategy s;
        //switch to determine which strategy has been choosen
        switch (r) {
            case 0:
                s = new ThinkerStrategy();
                break;
            case 1:
                s = new MyStrategy();
                break;
            case 2:
                s = new HumanStrategy();
                break;
            default:
                s = new BasicStrategy();
                break;
        }
        //return strategy
        return s;
    }
}
