package engine.entities;

import util.WebTools;
import javax.swing.*;
import java.awt.*;

public class PokemonSprite extends Sprite {
    ScreenPanel panel;
    String name;

    public PokemonSprite(ScreenPanel panel, int x, int y, String url, String name) {
        this.panel = panel;
        this.x = x;
        this.y = y;
        this.name = name;
        WebTools.downloadSpriteFromUrl(url, name);
    }

    public void draw(Graphics2D graphics2D) {
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/assets/" + name + ".png");
        graphics2D.drawImage(icon.getImage(), x, y, panel.tileSize, panel.tileSize, new Color(0f,0f,0f,0f ), null);
    }
}
