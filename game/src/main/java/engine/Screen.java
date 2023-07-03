package engine;

import engine.entities.ScreenPanel;
import logic.AttackFunctions;
import logic.AttackSwitch;
import logic.LogicFunctions;
import util.GLOBALS;
import util.MatchManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Screen {

    private final JFrame window;
    static JButton attackOne = new JButton(),
            attackTwo = new JButton(),
            attackThree = new JButton(),
            attackFour = new JButton();

    public Screen(MatchManager matchManager) {
        window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(GLOBALS.NAME + " - In Match");

        window.addKeyListener(new KeyHandler());
        window.setVisible(true);

        ScreenPanel screenPanel = new ScreenPanel(matchManager);
        window.add(screenPanel);
        window.pack();

        window.setSize(new Dimension(screenPanel.getWidth(), screenPanel.getHeight() + 200));

        int buttonHeight = 40;

        if(matchManager.isOwner()) {
            attackOne.setText(matchManager.getMatch().getFirst().getMoves()[0]);
            attackTwo.setText(matchManager.getMatch().getFirst().getMoves()[1]);
            attackThree.setText(matchManager.getMatch().getFirst().getMoves()[2]);
            attackFour.setText(matchManager.getMatch().getFirst().getMoves()[3]);
        }else {
            attackOne.setText(matchManager.getMatch().getSecond().getMoves()[0]);
            attackTwo.setText(matchManager.getMatch().getSecond().getMoves()[1]);
            attackThree.setText(matchManager.getMatch().getSecond().getMoves()[2]);
            attackFour.setText(matchManager.getMatch().getSecond().getMoves()[3]);
        }
        attackOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    AttackFunctions.doAttack(matchManager, 1);
                    LogicFunctions.checkDeath(matchManager);
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        attackTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    AttackFunctions.doAttack(matchManager, 2);
                    LogicFunctions.checkDeath(matchManager);
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        attackThree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    AttackFunctions.doAttack(matchManager, 3);
                    LogicFunctions.checkDeath(matchManager);
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        attackFour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    AttackFunctions.doAttack(matchManager, 4);
                    LogicFunctions.checkDeath(matchManager);
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(attackOne);
        panel.add(attackTwo);
        panel.add(attackThree);
        panel.add(attackFour);
        panel.setBounds(0, screenPanel.getHeight(), screenPanel.getWidth(), buttonHeight);
        window.getContentPane().add(panel);

        // This button only exists to make the other buttons work fine
        JButton placeholderButton = new JButton();
        placeholderButton.setVisible(false);
        window.add(placeholderButton);

        screenPanel.startGameThread();
        LogicFunctions.fetchCycle(matchManager);
    }

    public static void disableButtons() {
        attackOne.disable();
        attackTwo.disable();
        attackThree.disable();
        attackFour.disable();
    }

    public static void enableButtons() {
        attackOne.enable();
        attackTwo.enable();
        attackThree.enable();
        attackFour.enable();
    }

    public JFrame getWindow() {
        return window;
    }
}
