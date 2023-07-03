package engine.entities;

import java.awt.*;

public class TextBoxSprite extends Sprite {
    ScreenPanel panel;
    String message;

    public TextBoxSprite(ScreenPanel panel, int x, int y, String message) {
        this.panel = panel;
        this.x = x;
        this.y = y;
        this.message = message;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(x, y -5, panel.getWidth(), 75);

        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(x, y, panel.getWidth(), 75);

        String[] lines = message.split("<ENTER>");

        graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        graphics2D.setColor(Color.BLACK);

        graphics2D.drawString(lines[0], x + 5, y + 25);
        if(lines.length > 1) {
            graphics2D.drawString(lines[1], x + 5, y + 55);
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
