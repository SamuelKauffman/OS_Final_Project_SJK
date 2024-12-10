import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The Game class provides utility methods for rolling dice and calculating
 * scores.
 * It simulates rolling three six-sided dice and determines the score based on
 * matching dice.
 */
public class Game {

    static Random ran = new Random();

    /**
     * Simulates rolling three six-sided dice.
     * Generates a list of three random integers between 1 and 6.
     *
     * @return a list containing three dice rolls
     */
    public synchronized static List<Integer> roll() {
        List<Integer> rolls = new ArrayList<>();
        int i = 0;
        int roll;

        while (i < 3) {
            roll = ran.nextInt(6) + 1;
            rolls.add(roll);
            i++;
        }
        return rolls;
    }

    /**
     * Calculates the score for a given list of dice rolls.
     * If two dice match, the score is the value of the third die. If all three dice
     * are different, the score is 0.
     *
     * @param list a list of three integers representing dice rolls
     * @return the score based on the matching dice:
     *         - Value of the non-matching die if two dice match
     *         - 0 if all three dice are different
     */
    public synchronized static int rollScore(List<Integer> list) {
        Collections.sort(list);
        if (list.get(0) == list.get(1)) {
            return list.get(2);
        } else if (list.get(1) == list.get(2)) {
            return list.get(0);
        } else {
            return 0;
        }
    }
}
