package logic;

import util.WebTools;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Runtime {
    public Runtime() {

    }

    public void run() {
        Runnable helloRunnable = new Runnable() {
            public void run() {
                try {
                    System.out.println(WebTools.getMatchInfo("neuesMatch"));
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);
    }
}
