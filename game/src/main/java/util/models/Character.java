package util.models;

public class Character {
    private final String name;

    private final String spriteFront;
    private final String spriteBack;

    private final String type;
    private final String[] moves;

    private int health;
    private int attack;
    private int defense;
    private int speed;
    private int accuracy;

    public Character(String name, String spriteFront, String spriteBack, String type, String[] moves, int health, int attack, int defense, int speed, int accuracy) {
        this.name = name;
        this.spriteFront = spriteFront;
        this.spriteBack = spriteBack;
        this.type = type;
        this.moves = moves;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.accuracy = accuracy;
    }

    public static Character EMPTY_CHARACTER() {
        return new Character(
                "",
                "",
                "",
                "",
                new String[]{"", "", "", ""},
                0,
                0,
                0,
                0,
                0
        );
    }

    public String getName() {
        return name;
    }

    public String getSpriteFront() {
        return spriteFront;
    }

    public String getSpriteBack() {
        return spriteBack;
    }

    public String getType() {
        return type;
    }

    public String[] getMoves() {
        return moves;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }
}
