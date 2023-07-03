package engine;
import util.MatchManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Engine {
    public static String VERSION = "0.1.0";
    public static String RENDERING = "[Render Engine v" + VERSION + "] ";
    public static String GENERAL = "[General Engine v" + VERSION + "] ";

    public Engine(MatchManager matchManager) {
        System.out.println(GENERAL + "Initialized.");
        System.out.println(RENDERING + "Initialized.");

        clearLocalCache();
        System.out.println(GENERAL + "Local cache cleared.");

        Screen screen = new Screen(matchManager);
    }

    private void clearLocalCache() {
        Path self = Path.of(System.getProperty("user.dir") + "/assets/first.png");
        Path enemy = Path.of(System.getProperty("user.dir") + "/assets/second.png");
        try {
            Files.deleteIfExists(self);
            Files.deleteIfExists(enemy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
