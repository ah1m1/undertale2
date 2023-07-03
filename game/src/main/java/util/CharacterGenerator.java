package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.AttackSwitch;
import util.models.Character;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class CharacterGenerator {

    private int id = -1;

    public CharacterGenerator(int id) {
        this.id = id;
    }

    public CharacterGenerator() {

    }

    private String[] collectMoves(String type) {
        AttackSwitch.doAttackSwitch(type);
        return AttackSwitch.getAttacks();
    }

    public Character create() throws IOException, InterruptedException {
        if (id <= 0 || id > 896) {
            id = new Random().nextInt(896) + 1;
        }

        HttpClient http = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + id))
                .build();

        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.body());

        JsonNode types = node.get("types");
        String type = types.get(0).get("type").get("name").asText();

        Random r = new Random();

        return new Character(
                node.get("name").asText(),
                node.get("sprites").get("front_default").asText(),
                node.get("sprites").get("back_default").asText(),
                type,
                collectMoves(type),
                r.nextInt(256) * 5,
                r.nextInt(256),
                r.nextInt(256),
                r.nextInt(256),
                r.nextInt(256)
        );
    }

}
