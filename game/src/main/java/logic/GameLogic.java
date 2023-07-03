package logic;

import engine.Engine;
import engine.MatchMakingScreen;
import util.CharacterGenerator;
import util.MatchManager;
import util.models.Character;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;


public class GameLogic {
    public GameLogic(MatchManager matchManager) throws IOException, InterruptedException {
        Scanner test = new Scanner(System.in);


        boolean first;
        if (matchManager.getMatch().getFirst().getSpeed() >= matchManager.getMatch().getSecond().getSpeed()) {
            first = matchManager.isOwner();
            matchManager.getMatch().setContextMessage(matchManager.getMatch().getFirst().getName() + " Your turn!");
        } else {
            first = !matchManager.isOwner();
            matchManager.getMatch().setContextMessage(matchManager.getMatch().getSecond().getName() + " Your turn!");
        }

        matchManager.getMatch().setCurrent("go");

        while (matchManager.getMatch().getFirst().getHealth() > 0 && matchManager.getMatch().getSecond().getHealth() > 0) {
            int[] health = new int[]{matchManager.getMatch().getFirst().getHealth(), matchManager.getMatch().getSecond().getHealth()};
            String endCondition = matchManager.getMatch().getCurrent();

            matchManager.sendMatch();
            matchManager.fetchMatch();

            if (first)
                first = Fight.current(matchManager, test, health);
            else {

                first = Fight.pleaseWait(endCondition, matchManager, health);
            }
        }


        if(matchManager.getMatch().getFirst().getHealth()<=0) {
            matchManager.getMatch().setContextMessage(matchManager.getMatch().getFirst().getName() + " fainted! " + matchManager.getMatch().getSecond().getName() + " wins!");
        }
        if(matchManager.getMatch().getSecond().getHealth()<=0) {
            matchManager.getMatch().setContextMessage(matchManager.getMatch().getSecond().getName() + " fainted! " + matchManager.getMatch().getFirst().getName() + " wins!");
        }
        matchManager.sendMatch();
        matchManager.fetchMatch();
        Thread.sleep(3000);
        System.exit(0);
    }
}
