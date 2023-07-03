package logic;

import util.MatchManager;

import java.io.IOException;
import java.util.Scanner;

public class Attacks {
    private static MatchManager matchManager;

    public static void doAttack(MatchManager matchManager, Scanner sc, int[] endCondition) throws IOException, InterruptedException {




        if(matchManager.isOwner()) {
            AttackSwitch.doAttackSwitch(matchManager.getMatch().getFirst().getType()); //Guckt welcher Typ, gibt attacken An
            System.out.println("Player 1, choose your attack:");
            for(int i=0; i<AttackSwitch.getAttacks().length; i++) {
                System.out.println((i+1)+") " + AttackSwitch.getAttacks()[i]);
            }
//            switch (sc.nextInt()) {
//                default:
//                    matchManager.getMatch().getSecond().setHealth(matchManager.getMatch().getSecond().getHealth() - matchManager.getMatch().getFirst().getAttack());
//                    break;
//            }
            AttackFunctions.doAttack(matchManager, sc.nextInt());
            matchManager.sendMatch();

        }


        else {
            AttackSwitch.doAttackSwitch(matchManager.getMatch().getSecond().getType());
            System.out.println("Player 2, choose your attack:");
            for(int i=0; i<AttackSwitch.getAttacks().length; i++) {
                System.out.println((i+1)+") " + AttackSwitch.getAttacks()[i]);
            }
//            switch (sc.nextInt()) {
//                default:
//                    matchManager.getMatch().getFirst().setHealth(matchManager.getMatch().getFirst().getHealth() - matchManager.getMatch().getSecond().getAttack());
//                    break;
//            }
            AttackFunctions.doAttack(matchManager, sc.nextInt());
        }
    }
}