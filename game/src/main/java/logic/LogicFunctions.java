package logic;

import engine.Engine;
import engine.Screen;
import util.MatchManager;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LogicFunctions {

    static boolean currentPlayer = false;
    public static String lastSave;
    public static void checkDeath(MatchManager matchManager) {
        if(matchManager.getMatch().getFirst().getHealth() <= 0) {
            matchManager.getMatch().setContextMessage(matchManager.getMatch().getFirst().getName() + " fainted.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }

        if(matchManager.getMatch().getSecond().getHealth() <= 0) {
            matchManager.getMatch().setContextMessage(matchManager.getMatch().getSecond().getName() + " fainted.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }

    public static void fetchCycle(MatchManager matchManager) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable fetchService = new Runnable() {
            public void run() {
                try {
                    matchManager.fetchMatch();

                    if(lastSave != matchManager.getMatch().getCurrent()) {
                        currentPlayer = true;
                    }else {
                        currentPlayer = false;
                    }

                    handleButtons();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        executor.scheduleAtFixedRate(fetchService, 0, 1, TimeUnit.SECONDS);
    }

    public static void handleButtons() {
        if(currentPlayer)
            Screen.enableButtons();
        else
            Screen.disableButtons();
    }

    public static boolean isCurrent(MatchManager matchManager) {
        return currentPlayer;
    }

    public static void roundInit(MatchManager matchManager) {
        currentPlayer = matchManager.isOwner();
    }
}
