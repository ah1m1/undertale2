package logic;

import util.MatchManager;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Fight {
    private MatchManager matchManager;

    public static boolean current(MatchManager matchManager, Scanner sc, int[] endCondition) throws IOException, InterruptedException {
        Random rand = new Random();
        Attacks.doAttack(matchManager, sc, endCondition);
        matchManager.getMatch().setCurrent(Long.toString(rand.nextLong(1000000000)));
//        if (AttackFunctions.getFlinch()) {
//            System.out.println("TRUE CURRENT");
//            return true;
//        }
//        else {
//            System.out.println("FALSE CURRENT");
            return false;
//        }
    }

    public static boolean pleaseWait(String endCondition, MatchManager matchManager, int[] health) throws IOException, InterruptedException {

        while (Objects.equals(endCondition, matchManager.getMatch().getCurrent())){
            matchManager.fetchMatch();
            System.out.print("Waiting for the enemy attack. \r");
            Thread.sleep(333);
            System.out.print("Waiting for the enemy attack.. \r");
            Thread.sleep(333);
            System.out.print("Waiting for the enemy attack... \r");
            Thread.sleep(333);
        }

        if (!matchManager.isOwner())
            System.out.println("\nThe enemy dealt " + matchManager.getMatch().getFirst().getAttack() + " damage and instead of " + health[1] + " HP, you only have " + matchManager.getMatch().getSecond().getHealth() + " HP left.");
        else
            System.out.println("\nThe enemy dealt " + matchManager.getMatch().getSecond().getAttack() + " damage and instead of " + health[0] + " HP, you only have " + matchManager.getMatch().getFirst().getHealth() + " HP left.");

//        if (AttackFunctions.getFlinch()) {
//            System.out.println("TRUE WAIT");
//            return false;
//        }
//        else {
//            System.out.println("FALSE WAIT");
            return true;
//        }
    }
}
