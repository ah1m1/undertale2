package engine.entities;

import engine.Engine;
import engine.KeyHandler;
import util.MatchManager;

import javax.swing.*;
import java.awt.*;

public class ScreenPanel extends JPanel implements Runnable {
    final int tileResolution = 128;
    final int upscale = 3;

    final int tileSize = tileResolution * upscale;

    final int pixelWidth = 2;
    final int pixelHeight = 2;
    final int screenWidth = tileSize * pixelWidth;
    final int screenHeight = tileSize * pixelHeight;

    int fps = 60;

    KeyHandler keyHandler = new KeyHandler();
    volatile Thread gameThread;

    PokemonSprite self;
    PokemonSprite enemy;
    BackgroundSprite bg;
    HealthBarSprite firstHealth;
    HealthBarSprite secondHealth;
    TextBoxSprite textBoxSprite;
    int selfHealthAmount, enemyHealthAmount;

    MatchManager matchManager;

    public ScreenPanel(MatchManager matchManager) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        this.matchManager = matchManager;

        String selfSprite, enemySprite;

        if(matchManager.isOwner()) {
            selfSprite = matchManager.getMatch().getFirst().getSpriteBack();
            enemySprite = matchManager.getMatch().getSecond().getSpriteFront();
            selfHealthAmount = matchManager.getMatch().getFirst().getHealth();
            enemyHealthAmount = matchManager.getMatch().getSecond().getHealth();
        }else {
            selfSprite = matchManager.getMatch().getSecond().getSpriteBack();
            enemySprite = matchManager.getMatch().getFirst().getSpriteFront();
            selfHealthAmount = matchManager.getMatch().getSecond().getHealth();
            enemyHealthAmount = matchManager.getMatch().getFirst().getHealth();
        }

        self = new PokemonSprite(this, 0, 400, selfSprite, "first");
        enemy = new PokemonSprite(this, 400, 0, enemySprite, "second");

        bg = new BackgroundSprite(this);

        firstHealth = new HealthBarSprite(this, 400, 600, selfHealthAmount);
        secondHealth = new HealthBarSprite(this, 100, 200, enemyHealthAmount);
        textBoxSprite = new TextBoxSprite(this, 0, 700, matchManager.getMatch().getContextMessage());
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = (nextDrawTime - System.nanoTime()) / 1_000_000;

                if(remainingTime < 0) {
                    System.out.println(Engine.RENDERING + "Rendering took longer than expected. Took " + Math.sqrt(Math.pow(remainingTime, 2)) + "ms.");
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        if(matchManager.isOwner()) {
            firstHealth.setHealth(matchManager.getMatch().getFirst().getHealth());
            secondHealth.setHealth(matchManager.getMatch().getSecond().getHealth());
        }else {
            secondHealth.setHealth(matchManager.getMatch().getFirst().getHealth());
            firstHealth.setHealth(matchManager.getMatch().getSecond().getHealth());
        }
        textBoxSprite.setMessage(matchManager.getMatch().getContextMessage());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        bg.draw(g2);
        self.draw(g2);
        enemy.draw(g2);

        firstHealth.draw(g2);
        secondHealth.draw(g2);

        textBoxSprite.draw(g2);

        g2.dispose();
    }
}
