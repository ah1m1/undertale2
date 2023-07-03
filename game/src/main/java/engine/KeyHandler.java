package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        upPressed = code == KeyEvent.VK_UP;
        leftPressed = code == KeyEvent.VK_LEFT;
        rightPressed = code == KeyEvent.VK_RIGHT;
        downPressed = code == KeyEvent.VK_DOWN;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        upPressed = !(code == KeyEvent.VK_UP);
        leftPressed = !(code == KeyEvent.VK_LEFT);
        rightPressed = !(code == KeyEvent.VK_RIGHT);
        downPressed = !(code == KeyEvent.VK_DOWN);
    }
}
