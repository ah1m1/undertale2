package logic;

import util.MatchManager;

import java.io.IOException;

public class GameLogicEngineBased {
    public GameLogicEngineBased (MatchManager matchManager) throws IOException, InterruptedException {

        boolean first;
        if (matchManager.getMatch().getFirst().getSpeed() >= matchManager.getMatch().getSecond().getSpeed()) {
            first = matchManager.isOwner();
            matchManager.getMatch().setContextMessage(matchManager.getMatch().getFirst().getName() + " Your turn!");
        } else {
            first = !matchManager.isOwner();
            matchManager.getMatch().setContextMessage(matchManager.getMatch().getSecond().getName() + " Your turn!");
        }


    }
}
