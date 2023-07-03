package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WebTools {

    public static String getMatchInfo(String id) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        node.put("gameId", id);

        HttpClient http = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GLOBALS.BASEURL + "/game/" + id))
                .build();

        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static void postMatchInfo(String id, ObjectNode data) throws IOException, InterruptedException {
        HttpClient http = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GLOBALS.BASEURL + "/game/" + id))
                .POST(HttpRequest.BodyPublishers.ofString(data.toPrettyString()))
                .setHeader("Content-Type", "application/json")
                .build();

        http.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void downloadSpriteFromUrl(String url, String name) {
        try(InputStream in = new URL(url).openStream()){
            Files.copy(in, Paths.get(System.getProperty("user.dir") + "/assets/" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
