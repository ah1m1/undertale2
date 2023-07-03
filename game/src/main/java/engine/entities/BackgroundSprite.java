package engine.entities;

import javax.swing.*;
import java.awt.*;

public class BackgroundSprite extends Sprite {
    ScreenPanel panel;

    public BackgroundSprite(ScreenPanel panel) {
        this.panel = panel;
    }

    public void draw(Graphics2D graphics2D) {
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/assets/static/background.png");
        graphics2D.drawImage(icon.getImage(), 0, 0, panel.tileSize * 2, panel.tileSize * 2, new Color(1f,0f,0f,.5f ), null);
    }
}
