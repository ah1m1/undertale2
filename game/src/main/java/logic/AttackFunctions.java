package logic;

import util.GLOBALS;
import util.MatchManager;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class AttackFunctions {
    static boolean flinch = false;
    public static void doAttack(MatchManager matchManager, int uinput) throws IOException, InterruptedException {
        flinch = false;

        if(matchManager.isOwner()) {
            matchManager.getMatch().setContextMessage(matchManager.getMatch().getFirst().getName() + " used " + AttackSwitch.getAttacks()[uinput-1]);
        }
        else {
            matchManager.getMatch().setContextMessage(matchManager.getMatch().getSecond().getName() + " used " + AttackSwitch.getAttacks()[uinput-1]);
        }
        matchManager.sendMatch();
        Thread.sleep(1000);



        switch(AttackSwitch.getAttacks()[uinput-1]) {
            case "Arm Thrust": //Hits 2-5 times in one turn.
                int armthrustrndmhit = (int)(2+Math.random()*4); //Generiert Zahl von 2-5
                if(matchManager.isOwner()) {
                    matchManager.getMatch().getSecond().setHealth(matchManager.getMatch().getSecond().getHealth()-(matchManager.getMatch().getFirst().getAttack()*armthrustrndmhit));
                    GLOBALS.damagedone += matchManager.getMatch().getFirst().getAttack()*armthrustrndmhit;
                    matchManager.getMatch().setContextMessage(matchManager.getMatch().getFirst().getName() + " dealt " + matchManager.getMatch().getFirst().getAttack()*armthrustrndmhit + " damage to " + matchManager.getMatch().getSecond().getName() + "<ENTER>" + matchManager.getMatch().getFirst().getName() + " dealt " + armthrustrndmhit + " times the usual damage!");
                }
                else {
                    matchManager.getMatch().getFirst().setHealth(matchManager.getMatch().getFirst().getHealth()-(matchManager.getMatch().getSecond().getAttack()*armthrustrndmhit));
                    GLOBALS.damagedone += matchManager.getMatch().getSecond().getAttack()*armthrustrndmhit;
                    matchManager.getMatch().setContextMessage(matchManager.getMatch().getSecond().getName() + " dealt " + matchManager.getMatch().getSecond().getAttack()*armthrustrndmhit + " damage to " + matchManager.getMatch().getFirst().getName() + "<ENTER>" + matchManager.getMatch().getSecond().getName() + " dealt " + armthrustrndmhit + " times the usual damage!");
                }
                break;



            case "Bonemerang": //Hits twice in one turn.
                if(matchManager.isOwner()) {
                    matchManager.getMatch().getSecond().setHealth(matchManager.getMatch().getSecond().getHealth()-(matchManager.getMatch().getFirst().getAttack()*2));
                    GLOBALS.damagedone += matchManager.getMatch().getFirst().getAttack()*2;
                    matchManager.getMatch().setContextMessage(matchManager.getMatch().getFirst().getName() + " dealt " + matchManager.getMatch().getFirst().getAttack()*2 + " damage to " + matchManager.getMatch().getSecond().getName() + "<ENTER>" + matchManager.getMatch().getFirst().getName() + " dealt " + 2 + " times the usual damage!");
                }
                else {
                    matchManager.getMatch().getFirst().setHealth(matchManager.getMatch().getFirst().getHealth()-(matchManager.getMatch().getSecond().getAttack()*2));
                    GLOBALS.damagedone += matchManager.getMatch().getSecond().getAttack()*2;
                    matchManager.getMatch().setContextMessage(matchManager.getMatch().getSecond().getName() + " dealt " + matchManager.getMatch().getSecond().getAttack()*2 + " damage to " + matchManager.getMatch().getFirst().getName() + "<ENTER>" + matchManager.getMatch().getSecond().getName() + " dealt " + 2 + " times the usual damage!");
                }
                break;



            case "Breaking Swipe": ////Hits multiple opponents and lowers their attack.
                if(matchManager.isOwner()) {
                    matchManager.getMatch().getSecond().setHealth(matchManager.getMatch().getSecond().getHealth()-matchManager.getMatch().getFirst().getAttack());
                    GLOBALS.damagedone += matchManager.getMatch().getFirst().getAttack();
                    matchManager.getMatch().getSecond().setAttack(matchManager.getMatch().getSecond().getAttack()-10);
                    matchManager.getMatch().setContextMessage(matchManager.getMatch().getFirst().getName() + " dealt " + matchManager.getMatch().getFirst().getAttack() + " damage to " + matchManager.getMatch().getSecond().getName() + "<ENTER>" + matchManager.getMatch().getSecond().getName() + "s damage went down by 10");
                }
                else {
                    matchManager.getMatch().getFirst().setHealth(matchManager.getMatch().getFirst().getHealth()-matchManager.getMatch().getSecond().getAttack());
                    GLOBALS.damagedone += matchManager.getMatch().getFirst().getAttack();
                    matchManager.getMatch().getFirst().setAttack(matchManager.getMatch().getFirst().getAttack()-10);
                    matchManager.getMatch().setContextMessage(matchManager.getMatch().getSecond().getName() + " dealt " + matchManager.getMatch().getSecond().getAttack() + " damage to " + matchManager.getMatch().getFirst().getName() + "<ENTER>" + matchManager.getMatch().getFirst().getName() + "s damage went down by 10");
                }
                break;



            case "Dragon Rush": ////May cause flinching.
                if(matchManager.isOwner()) {
                    flinch = true;
                    GLOBALS.damagedone += matchManager.getMatch().getFirst().getAttack();
                    matchManager.getMatch().getSecond().setHealth(matchManager.getMatch().getSecond().getHealth()-matchManager.getMatch().getFirst().getAttack());
                    matchManager.getMatch().setContextMessage(matchManager.getMatch().getFirst().getName() + " dealt " + matchManager.getMatch().getFirst().getAttack() + " damage to " + matchManager.getMatch().getSecond().getName() + "<ENTER>" + matchManager.getMatch().getSecond().getName() + " flinches!");
                }
                else {
                    GLOBALS.damagedone += matchManager.getMatch().getSecond().getAttack();
                    matchManager.getMatch().getFirst().setHealth(matchManager.getMatch().getFirst().getHealth()-matchManager.getMatch().getSecond().getAttack());
                    matchManager.getMatch().setContextMessage(matchManager.getMatch().getSecond().getName() + " dealt " + matchManager.getMatch().getSecond().getAttack() + " damage to " + matchManager.getMatch().getFirst().getName());
                }
                break;



            case "Fissure": //One-Hit-KO, if it hits.
                int onehitko = (int)(1+Math.random()*10); //Generiert Zahl von 1-10
                if(matchManager.isOwner()) {
                    if(onehitko==1) {
                        System.out.println(onehitko);
                        matchManager.getMatch().getSecond().setHealth(0);
                        matchManager.getMatch().setContextMessage(matchManager.getMatch().getFirst().getName() + "s One-Hit-KO hit " + matchManager.getMatch().getSecond().getName() + "!");
                    }
                    else {
                        System.out.println(onehitko);
                        matchManager.getMatch().setContextMessage(matchManager.getMatch().getFirst().getName() + "s One-Hit-KO missed!");
                    }
                }
                else {
                    if(onehitko==1) {
                        System.out.println(onehitko);
                        matchManager.getMatch().getFirst().setHealth(0);
                        matchManager.getMatch().setContextMessage(matchManager.getMatch().getSecond().getName() + "s One-Hit-KO hit " + matchManager.getMatch().getFirst().getName() + "!");
                    }
                    else {
                        System.out.println(onehitko);
                        matchManager.getMatch().setContextMessage(matchManager.getMatch().getSecond().getName() + "s One-Hit-KO missed!");
                    }
                }
                break;



            case "Leech Life": //User recovers half the HP inflicted on opponent.
                if(matchManager.isOwner()) {
                    matchManager.getMatch().getFirst().setHealth(matchManager.getMatch().getFirst().getHealth() + (GLOBALS.damagedone/2));
                    matchManager.getMatch().setContextMessage(matchManager.getMatch().getFirst().getName() + " leeched " + (GLOBALS.damagedone/2) + " HP");
                }
                else {
                    matchManager.getMatch().getSecond().setHealth(matchManager.getMatch().getSecond().getHealth() + (GLOBALS.damagedone/2));
                    matchManager.getMatch().setContextMessage(matchManager.getMatch().getSecond().getName() + " leeched " + (GLOBALS.damagedone/2) + " HP");
                }
                break;



            case "Meditate": //Raises user's Attack.
                if(matchManager.isOwner()) {
                    matchManager.getMatch().getFirst().setAttack(matchManager.getMatch().getFirst().getAttack()+25);
                    matchManager.getMatch().setContextMessage(matchManager.getMatch().getFirst().getName() + " raised attack by 25");
                }
                else {
                    matchManager.getMatch().getSecond().setAttack(matchManager.getMatch().getSecond().getAttack()+25);
                    matchManager.getMatch().setContextMessage(matchManager.getMatch().getSecond().getName() + " raised attack by 25");
                }
                break;



            case "Explosion": //Selbstmord
                if(matchManager.isOwner()) {
                    matchManager.getMatch().getFirst().setHealth(0);
                    matchManager.getMatch().getSecond().setHealth(matchManager.getMatch().getSecond().getHealth() - (int)(matchManager.getMatch().getFirst().getAttack()*1.5));
                }
                else {
                    matchManager.getMatch().getSecond().setHealth(0);
                    matchManager.getMatch().getFirst().setHealth(matchManager.getMatch().getFirst().getHealth() - (int)(matchManager.getMatch().getSecond().getAttack()*1.5));
                }
                break;



            default:
                if(matchManager.isOwner()) {
                    matchManager.getMatch().getSecond().setHealth(matchManager.getMatch().getSecond().getHealth()-matchManager.getMatch().getFirst().getAttack());
                    GLOBALS.damagedone += matchManager.getMatch().getFirst().getAttack();
                    matchManager.getMatch().setContextMessage(matchManager.getMatch().getFirst().getName() + " dealt " + matchManager.getMatch().getFirst().getAttack() + " damage to " + matchManager.getMatch().getSecond().getName());
                }
                else {
                    matchManager.getMatch().getFirst().setHealth(matchManager.getMatch().getFirst().getHealth()-matchManager.getMatch().getSecond().getAttack());
                    GLOBALS.damagedone += matchManager.getMatch().getSecond().getAttack();
                    matchManager.getMatch().setContextMessage(matchManager.getMatch().getSecond().getName() + " dealt " + matchManager.getMatch().getSecond().getAttack() + " damage to " + matchManager.getMatch().getFirst().getName());
                }
                break;
        }
        int newKey = new Random().nextInt(1000000);
        matchManager.getMatch().setCurrent("" + newKey);
        LogicFunctions.lastSave = String.valueOf(newKey);
        matchManager.sendMatch(); //Muss bleiben sonst Fehler?
    }
    public static boolean getFlinch() {
        return flinch;
    }
}

            //if(matchManager.isOwner()) {
            //
            //}
            //else {
            //
            //}
            //break;
            //FÜR COPYPASTE