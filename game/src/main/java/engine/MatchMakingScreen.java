package engine;

import logic.GameLogic;
import util.CharacterGenerator;
import util.GLOBALS;
import util.MatchManager;
import util.WebTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MatchMakingScreen {
    boolean matchCreated = false;
    private final JFrame window;
    private MatchManager matchManager;

    public MatchMakingScreen() throws IOException, InterruptedException {
        window = new JFrame();

        matchManager = new MatchManager();

        JLabel networkMessage = new JLabel();
        JTextField matchIdField = new JTextField();

        JButton createMatchButton = new JButton();
        createMatchButton.setText("Create Match");
        createMatchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    createMatch(matchIdField);
                    networkMessage.setText("Waiting for second Player...");

                    matchCreated = true;
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton connectToMatchButton = new JButton();
        connectToMatchButton.setText("Connect to Match");
        connectToMatchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    connectToMatch(matchIdField);
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(createMatchButton);
        panel.add(connectToMatchButton);
        window.getContentPane().add(panel);

        matchIdField.setText("      Enter a new Match ID to connect or create a new Match       ");
        panel.add(matchIdField);

        networkMessage.setText("");
        panel.add(networkMessage);

        // Render window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(GLOBALS.NAME + " - Join or Create a Match");
        window.setSize(new Dimension(400, 200));
        window.setLocationRelativeTo(null);

        window.setVisible(true);

        searchForMatch();
    }

    private void createMatch(JTextField matchIdField) throws IOException, InterruptedException {
        matchManager.createMatch(new CharacterGenerator().create());
        matchManager.sendMatch();

        matchIdField.setText(matchManager.getMatch().getMatchId());
        window.repaint();
    }

    private void connectToMatch(JTextField matchIdField) throws IOException, InterruptedException {
        matchManager.connectToMatch(matchIdField.getText(), new CharacterGenerator().create());
        matchManager.sendMatch();

        Engine engine = new Engine(matchManager);
    }

    private void searchForMatch() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable searchService = new Runnable() {
            public void run() {
                try {
                    if(matchCreated) {
                        System.out.println("[Match Making] Fetching Match data...");
                        matchManager.fetchMatch();
                        if(!matchManager.getMatch().getSecond().getName().equals("")) {
                            Engine engine = new Engine(matchManager);
                            executor.shutdown();
                        }
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        executor.scheduleAtFixedRate(searchService, 0, 1, TimeUnit.SECONDS);
    }

    public MatchManager getMatchManager() {
        return matchManager;
    }
}
