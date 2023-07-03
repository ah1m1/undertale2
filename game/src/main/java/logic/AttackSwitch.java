package logic;

public class AttackSwitch {
    static String[] attacks;
    public static void doAttackSwitch(String input) {
        String attack1 = "Kick";
        String attack2 = "Punch";
        String attack3 = "Tackle";
        String attack4 = "Slam";
        switch(input) {
            case "bug":
                attack1 = "Attack Order"; //High critical hit ratio.
                attack2 = "Fell Stinger"; //Drastically raises user's Attack if target is KO'd.
                attack3 = "Leech Life"; //User recovers half the HP inflicted on opponent. DONE
                attack4 = "Megahorn"; //DONE
                break;
            case "dark":
                attack1 = "Ceaseless Edge"; //DONE
                attack2 = "HyperSpace Fury"; //Lowers user's Defense. Can strike through Protect/Detect. KEINER HAT PROTECT
                attack3 = "Night Slash"; //High critical hit ratio.
                attack4 = "Wicked Blow"; //Always results in a critical hit and ignores stat changes.
                break;
            case "dragon":
                attack1 = "Breaking Swipe"; //Hits multiple opponents and lowers their attack. DONE
                attack2 = "Dragon Claw"; //DONE
                attack3 = "Dragon Rush"; //May cause flinching.
                attack4 = "Outrage"; //User attacks for 2-3 turns but then becomes confused.
                break;
            case "electric":
                attack1 = "Bolt Strike"; //	May paralyze opponent.
                attack2 = "Fusion Bolt"; //Power increases if Fusion Flare is used in the same turn.
                attack3 = "Thunder Punch"; //May paralyze opponent.
                attack4 = "Wild Charge"; //User receives recoil damage.
                break;
            case "fairy":
                attack1 = "Fairy Wind"; //DONE
                attack2 = "Misty Explosion"; //Power increases on Misty Terrain.
                attack3 = "Play Rough"; //May lower opponent's Attack.
                attack4 = "Spirit Break"; //Lowers opponent's Special Attack.
                break;
            case "fighting":
                attack1 = "Arm Thrust"; //Hits 2-5 times in one turn. DONE
                attack2 = "Body Press"; //The higher the user's Defense, the stronger the attack.
                attack3 = "Cross Chop"; //High critical hit ratio.
                attack4 = "Power-Up Punch"; //Raises Attack.
                break;
            case "fire":
                attack1 = "Blaze Kick"; //High critical hit ratio. May burn opponent.
                attack2 = "Fire Fang"; //May cause flinching and/or burn opponent.
                attack3 = "Fire Punch"; //May burn opponent.
                attack4 = "Pyor Ball"; //May burn opponent.
                break;
            case "flying":
                attack1 = "Dragon Ascent"; //Lowers user's Defense and Special Defense.
                attack2 = "Floaty Fall"; //May cause flinching.
                attack3 = "Peck"; //DONE
                attack4 = "Wing Attack"; //DONE
                break;
            case "ghost":
                attack1 = "Astonish"; //May cause flinching.
                attack2 = "Lick"; //May paralyze opponent.
                attack3 = "Shadow Bone"; //May lower opponent's Defense.
                attack4 = "Shadow Claw"; //High critical hit ratio.
                break;
            case "grass":
                attack1 = "Absorb"; //User recovers half the HP inflicted on opponent.
                attack2 = "Cotton Guard"; //Drastically raises user's Defense.
                attack3 = "Grav Apple"; //Lowers the opponent's Defense stat.
                attack4 = "Leaf Blade"; //High critical hit ratio.
                break;
            case "ground":
                attack1 = "Bonemerang"; //Hits twice in one turn. DONE
                attack2 = "Fissure"; //One-Hit-KO, if it hits.
                attack3 = "Magnitude"; //Hits with random power.
                attack4 = "Mud Bomb"; //May lower opponent's Accuracy.
                break;
            case "ice":
                attack1 = "Aurora Beam"; //May lower opponent's Attack.
                attack2 = "Avalanche"; //Power doubles if user took damage first.
                attack3 = "Ice Ball"; //Doubles in power each turn for 5 turns. (alle 5 Runden Schaden *2 machen)
                attack4 = "Mountain Gale"; //DONE
                break;
            case "normal":
                attack1 = "Cut"; //DONE
                attack2 = "Egg Bomb"; //DONE
                attack3 = "Explosion"; //Selbstmord DONE
                attack4 = "Flail"; //The lower the user's HP, the higher the power.
                break;
            case "poison":
                attack1 = "Barb Barrage"; //DONE
                attack2 = "Cross Poison"; //High critical hit ratio. May poison opponent.
                attack3 = "Gunk Shot"; //May poison opponent.
                attack4 = "Poison Sting"; //May poison the opponent.
                break;
            case "psychic":
                attack1 = "Heart Stamp"; //May cause flinching.
                attack2 = "Kinesis"; //Lowers opponent's Accuracy.
                attack3 = "Meditate"; //Raises user's Attack. DONE
                attack4 = "Psyshield Bash"; //DONE
                break;
            case "rock":
                attack1 = "Ancient Power"; //May raise all user's stats at once.
                attack2 = "Rock Throw"; //DONE
                attack3 = "Rollout!"; //Doubles in power each turn for 5 turns.
                attack4 = "Stone Axe"; //DONE
                break;
            case "steel":
                attack1 = "Gyro Ball"; //The slower the user, the stronger the attack.
                attack2 = "Heavy Slam"; //The heavier the user, the stronger the attack.
                attack3 = "Meteor Mash"; //May raise user's Attack.
                attack4 = "Steel Wing"; //May raise user's Defense.
                break;
            case "water":
                attack1 = "Aqua Ring"; //Restores a little HP each turn.
                attack2 = "Crabhammer"; //High critical hit ratio.
                attack3 = "Liquidation"; //May lower opponent's Defense.
                attack4 = "Razor Shell"; //May lower opponent's Defense.
                break;
            default:
                attack1 = attack1;
                attack2 = attack2;
                attack3 = attack3;
                attack4 = attack4;
                break;
        }
        attacks = new String[]{attack1, attack2, attack3, attack4};
    }
    public static String[] getAttacks() {
        return attacks;
    }
}