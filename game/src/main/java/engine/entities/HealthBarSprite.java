package engine.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HealthBarSprite extends Sprite {
    ScreenPanel panel;
    float healthPercentage;
    float health;
    float fixedHealth;

    public HealthBarSprite(ScreenPanel panel, int x, int y, float health) {
        this.panel = panel;
        this.x = x;
        this.y = y;
        this.healthPercentage = 100;
        this.health = health;
        this.fixedHealth = health;
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = new BufferedImage(200, 30, BufferedImage.TYPE_INT_RGB);
        Graphics2D imgG2 = image.createGraphics();

        float drawPercent = image.getWidth() - (healthPercentage * image.getWidth() / 100);

        imgG2.setColor(Color.GREEN);
        imgG2.fillRect(0, 0, (int) (image.getWidth() - drawPercent), image.getHeight());

        graphics2D.drawImage(image, x, y, image.getWidth(), image.getHeight(), new Color(0f, 0f, 0f, 0f), null);
    }

    public float getHealthPercentage() {
        return healthPercentage;
    }

    public void setHealthPercentage(float healthPercentage) {
        this.healthPercentage = healthPercentage;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
        this.setHealthPercentage((200 * (this.health / fixedHealth)) / 2);
    }
}
