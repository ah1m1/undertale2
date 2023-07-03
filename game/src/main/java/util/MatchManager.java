package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import util.models.Character;
import util.models.Match;

import java.io.IOException;

public class MatchManager {

    private Match match;
    private boolean owner;

    public MatchManager() {
        GLOBALS.USER_ID = Identification.getUUID();
    }

    public void createMatch(Character pokemon) {
        match = new Match(
                Identification.getUUID(),
                GLOBALS.USER_ID,
                "Match has started.",
                GLOBALS.USER_ID,
                pokemon,
                "",
                Character.EMPTY_CHARACTER(),
                ""
        );
        owner = true;
    }

    public void connectToMatch(String matchId, Character pokemon) throws IOException, InterruptedException {
        String response = WebTools.getMatchInfo(matchId);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response);

        JsonNode data = node.get("data");

        JsonNode character = data.get("first").get("character");
        ArrayNode moves = mapper.valueToTree(character.get("moves"));

        Character enemy = new Character(
                character.get("name").asText(),
                character.get("spriteFront").asText(),
                character.get("spriteBack").asText(),
                character.get("type").asText(),
                new String[]{moves.get(0).asText(), moves.get(1).asText(), moves.get(2).asText(), moves.get(3).asText()},
                character.get("health").asInt(),
                character.get("attack").asInt(),
                character.get("defense").asInt(),
                character.get("speed").asInt(),
                character.get("accuracy").asInt()
        );

        match = new Match(
                matchId,
                data.get("current").asText(),
                data.get("contextMessage").asText(),
                data.get("first").get("uuid").asText(),
                enemy,
                GLOBALS.USER_ID,
                pokemon,
                GLOBALS.VERSION
                );

        owner = false;
    }

    public void fetchMatch() throws IOException, InterruptedException {
        match.updateFromServer();
    }

    public void sendMatch() throws IOException, InterruptedException {
        match.sendToServer();
    }

    public Match getMatch() {
        return match;
    }

    public boolean isOwner() {
        return owner;
    }
}
