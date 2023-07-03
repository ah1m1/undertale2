package util;

import java.util.UUID;

public class Identification {
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
