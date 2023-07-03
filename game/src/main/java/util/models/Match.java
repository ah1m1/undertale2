package util.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import util.GLOBALS;
import util.WebTools;

import java.io.IOException;

public class Match {
    private String matchId;
    private String current;

    private String contextMessage;

    // Player 1
    private String firstUuid;
    private Character first;

    // Player 2
    private String secondUuid;
    private Character second;
    private String secondVersion;

    public Match(String matchId, String current, String contextMessage, String firstUuid, Character first, String secondUuid, Character second, String secondVersion) {
        this.matchId = matchId;
        this.current = current;
        this.contextMessage = contextMessage;
        this.firstUuid = firstUuid;
        this.first = first;
        this.secondUuid = secondUuid;
        this.second = second;
        this.secondVersion = secondVersion;
    }

    public void updateFromServer() throws IOException, InterruptedException {
        String res = WebTools.getMatchInfo(matchId);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(res);

        JsonNode data = node.get("data");

        setCurrent(data.get("current").asText());
        setContextMessage(data.get("contextMessage").asText());

        JsonNode firstCharacter = data.get("first").get("character");
        ArrayNode firstMoves = mapper.valueToTree(firstCharacter.get("moves"));

        setFirst(new Character(
                firstCharacter.get("name").asText(),
                firstCharacter.get("spriteFront").asText(),
                firstCharacter.get("spriteBack").asText(),
                firstCharacter.get("type").asText(),
                new String[]{firstMoves.get(0).asText(), firstMoves.get(1).asText(), firstMoves.get(2).asText(), firstMoves.get(3).asText()},
                firstCharacter.get("health").asInt(),
                firstCharacter.get("attack").asInt(),
                firstCharacter.get("defense").asInt(),
                firstCharacter.get("speed").asInt(),
                firstCharacter.get("accuracy").asInt()
        ));

        setSecondUuid(data.get("second").get("uuid").asText());
        setSecondVersion(data.get("second").get("version").asText());

        JsonNode secondCharacter = data.get("second").get("character");
        ArrayNode secondMoves = mapper.valueToTree(secondCharacter.get("moves"));

        setSecond(new Character(
                secondCharacter.get("name").asText(),
                secondCharacter.get("spriteFront").asText(),
                secondCharacter.get("spriteBack").asText(),
                secondCharacter.get("type").asText(),
                new String[]{secondMoves.get(0).asText(), secondMoves.get(1).asText(), secondMoves.get(2).asText(), secondMoves.get(3).asText()},
                secondCharacter.get("health").asInt(),
                secondCharacter.get("attack").asInt(),
                secondCharacter.get("defense").asInt(),
                secondCharacter.get("speed").asInt(),
                secondCharacter.get("accuracy").asInt()
        ));
    }

    public void sendToServer() throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        node.put("current", current);
        node.put("contextMessage", contextMessage);

        ObjectNode firstPlayer = mapper.createObjectNode();
        firstPlayer.put("uuid", firstUuid);
        firstPlayer.put("version", GLOBALS.VERSION);

        ObjectNode firstChar = mapper.createObjectNode();
        firstChar.put("name", first.getName());
        firstChar.put("spriteFront", first.getSpriteFront());
        firstChar.put("spriteBack", first.getSpriteBack());
        firstChar.put("type", first.getType());

        ArrayNode moves = mapper.valueToTree(first.getMoves());
        firstChar.put("moves", moves);

        firstChar.put("health", first.getHealth());
        firstChar.put("attack", first.getAttack());
        firstChar.put("defense", first.getDefense());
        firstChar.put("speed", first.getSpeed());
        firstChar.put("accuracy", first.getAccuracy());

        firstPlayer.put("character", firstChar);
        node.put("first", firstPlayer);

        ObjectNode secondPlayer = mapper.createObjectNode();
        secondPlayer.put("uuid", secondUuid);
        secondPlayer.put("version", secondVersion);

        ObjectNode secondChar = mapper.createObjectNode();
        secondChar.put("name", second.getName());
        secondChar.put("spriteFront", second.getSpriteFront());
        secondChar.put("spriteBack", second.getSpriteBack());
        secondChar.put("type", second.getType());

        ArrayNode secondMoves = mapper.valueToTree(second.getMoves());
        secondChar.put("moves", secondMoves);

        secondChar.put("health", second.getHealth());
        secondChar.put("attack", second.getAttack());
        secondChar.put("defense", second.getDefense());
        secondChar.put("speed", second.getSpeed());
        secondChar.put("accuracy", second.getAccuracy());

        secondPlayer.put("character", secondChar);
        node.put("second", secondPlayer);

        WebTools.postMatchInfo(matchId, node);
    }

    public String getMatchId() {
        return matchId;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getContextMessage() {
        return contextMessage;
    }

    public void setContextMessage(String contextMessage) {
        this.contextMessage = contextMessage;
    }

    public String getFirstUuid() {
        return firstUuid;
    }

    public Character getFirst() {
        return first;
    }

    public void setFirst(Character first) {
        this.first = first;
    }

    public Character getSecond() {
        return second;
    }

    public void setSecond(Character second) {
        this.second = second;
    }

    public String getSecondUuid() {
        return secondUuid;
    }

    public void setSecondUuid(String getSecondUuid) {
        this.secondUuid = secondUuid;
    }

    public String getSecondVersion() {
        return secondVersion;
    }

    public void setSecondVersion(String secondVersion) {
        this.secondVersion = secondVersion;
    }
}
