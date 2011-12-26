package server.model.minigames;

import server.Server;
import server.event.Event;
import server.event.EventContainer;
import server.event.EventManager;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;

public class ZombieWaves {

	private Client c;
	public ZombieWaves(Client Client) {
		this.c = Client;
	}

        public static int zombieWaveId, zombieAmount, waitTimer;
        private final static int[][] zombieWave = {{73, 73, 73, 73}, {73, 73, 73, 73, 74}, {73, 73, 73, 73, 73, 74 ,74, 74}, {73, 73, 73, 74, 74, 73 ,74, 74, 74, 74}, {73, 74, 74, 74, 75, 75, 75, 3622, 76, 75, 75}, {74, 74, 74, 75, 74, 75, 76, 76, 76, 75, 75, 74, 74}, {74, 75, 76, 74, 76, 76, 75, 75, 77, 75, 76, 75, 76, 75, 76, 77, 75}, {75, 76, 75, 77, 76, 76, 77, 75, 76, 76, 77, 75, 76, 75, 77, 76, 76, 75, 75, 77, 77}, {76, 76, 77, 75, 76, 77, 77, 77, 76, 77, 77, 76, 76, 419, 77, 419, 77, 76, 76, 76, 77, 77, 76, 77, 76, 77}, {76, 77, 76, 77, 419, 419, 77, 419, 419, 77, 77, 76, 77, 419, 77, 77, 77, 420, 419, 77, 77, 77, 419, 76, 77, 77, 77}, {77, 77, 419, 419, 77, 420, 420, 77, 419, 420, 419, 419, 77, 419, 420, 419, 421, 421, 419, 77, 77, 77, 419, 420, 421, 420, 77, 419, 77, 419, 420}, {419, 419, 419, 420, 419, 420, 421, 422, 419, 422, 419, 421, 421, 420, 421, 419, 420, 422, 421, 421, 419, 422, 421, 419, 421, 422, 421, 419, 421, 419, 422, 419, 419, 419, 420, 421, 422}, {419, 419, 419, 420, 419, 420, 421, 422, 419, 422, 419, 421, 421, 420, 421, 419, 420, 422, 421, 421, 419, 422, 421, 419, 421, 422, 421, 419, 421, 419, 422, 419, 419, 419, 423, 423, 422, 422, 422, 420, 421, 422, 423}, {420, 421, 422, 420, 421, 421, 421, 422, 423, 422, 422, 421, 421, 420, 421, 421, 420, 420, 421, 422, 423, 420, 421, 423, 422, 422, 422, 421, 421, 421, 422, 423, 422, 421, 421, 423, 422, 422, 422, 423, 422, 421, 421}, {422, 421, 423, 423, 422, 422, 421, 423, 423, 422, 423, 423, 423, 422, 423, 423, 422, 421, 422, 423, 422, 421, 423, 422, 422, 422, 421, 422, 421, 423, 423, 422, 421, 421, 421, 421, 421, 421, 421, 422, 423, 423, 422, 422, 423, 423, 2869}, {73, 73, 73 ,73, 422, 422, 423, 423, 424, 423, 424, 423, 424, 73, 73, 73}, {424, 423, 424, 423,422, 423, 424, 423, 423, 2863, 424, 423, 422, 73, 73, 73, 73, 73}, {423, 422, 423, 424, 422, 423, 423, 422, 424, 2863, 424, 2863, 422, 2863}, {423, 422, 422, 423, 423, 424, 424, 2863, 424, 423, 422, 423, 424, 2863, 424, 424, 422, 423, 2863, 2863}, {424, 423, 422, 423, 424, 2863, 423, 2863, 424, 422, 423, 422, 424, 422, 2863, 422, 2863, 2863, 2863, 2863, 422, 422, 2863, 422, 424}, {422, 423, 424, 423, 424, 424, 2863, 2863, 424, 2863, 2863, 424, 2869, 2863, 2863, 424, 423, 424, 422, 2863, 424, 2863, 2863, 424, 2863, 424, 422, 424, 422, 424, 423, 424, 423, 422}, {424, 422, 2866, 2863, 424, 2863, 424, 422, 423, 2863, 2863, 2866, 424, 2863, 422, 423, 422, 423, 424, 2863, 424, 424, 422, 2866, 424, 2866, 2863, 424, 2866, 2863, 424, 422, 422, 424, 423, 424, 2866, 424, 2863}, {424, 2863, 422, 423, 424, 424, 3066, 2866, 2863, 424, 422, 424, 423, 424, 422, 423, 422, 423, 2863, 2866, 424, 422, 423, 422, 2866, 424, 422, 423, 422, 423, 423, 423, 424, 2863, 2714, 2866, 424, 424, 2866, 2863, 424, 2714, 422, 2863, 422, 2714}, {2863, 424, 423, 424, 424, 424, 424, 3066, 424, 2866, 2863, 424, 423, 424, 424, 2866, 424, 423, 424, 423, 423, 424, 2863, 424, 423, 424, 424, 2866, 2866, 2863, 424, 2866, 424, 423, 423, 424, 424, 423, 424, 423, 424, 2866, 2866, 423, 423, 424, 2866, 2863, 424, 3066, 2863, 424, 424, 423, 3066, 2714, 424, 2866, 424, 423, 424, 424, 424, 423, 3066, 424, 422, 423, 424, 424, 423, 3066, 2714, 424, 423, 424, 2866, 2714}, {73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 2060}}; //Step 1: Each bracket {} is a wave, add new waves like ,{419, 424, ect..}
        private static int[][] coords = {{2790, 10060}, {2791, 10057}, {2788, 10058}, {2783, 10057}, {2781, 10059}, {2781, 10062}, {2779, 10065}, {2776, 10065}, {2782, 10068}, {2801, 10061}, {2804, 10061}, {2802, 10064}, {2797, 10069}, {2800, 10064}, {2794, 10079}, {2782, 10092}, {2766, 10087}, {2786, 10090}, {2782, 10058}, {2781, 10055}, {2777, 10055}, {2778, 10094}, {2782, 10089}, {2786, 10085}, {2790, 10083}, {2789, 10079}, {2791, 10075}, {2771, 10096}, {2774, 10093}, {2772, 10097}, {2775, 10099}, {2781, 10097}, {2784, 10101}, {2781, 10104}, {2768, 10099}, {2775, 10101}, {2779, 10088}, {2776, 10081}, {2778, 10078}, {2780, 10074}, {2786, 10098}, {2780, 10106}, {2782, 10099}, {2780, 10095}, {2783, 10092}, {2790, 10084}, {2786, 10088}, {2795, 10057}, {2798, 10057},  {2778, 10094}, {2782, 10089}, {2786, 10085}, {2790, 10083}, {2789, 10079}, {2791, 10075}, {2771, 10096}, {2774, 10093}, {2772, 10097}, {2775, 10099}, {2781, 10097}, {2784, 10101}, {2781, 10104}, {2768, 10099}, {2775, 10101}, {2779, 10088}, {2776, 10081}, {2778, 10078}, {2780, 10074}, {2786, 10098}, {2780, 10106}, {2782, 10099}, {2780, 10095}, {2783, 10092}, {2790, 10084}, {2786, 10088}, {2795, 10057}, {2798, 10057}, {2787, 10069}, {2790, 10071}, {2771, 10075}, {2777, 10076},  {2783, 10074}, {2785, 10071}, {2788, 10072}, {2791, 10073}, {2777, 10076}, {2789, 10076}, {2772, 10079}, {2772, 10075}, {2775, 10079}, {2777, 10082}, {2773, 10083}, {2769, 10076}, {2769, 10089}, {2765, 10082}, {2762, 10081}, {2759, 10083}, {2761, 10086}, {2760, 10090}, {2765, 10096}, {2768, 10094}, {2772, 10091}, {2774, 10094}, {2773, 10056}, {2769, 10057}, {2766, 10057}, {2768, 10059}, {2773, 10058}, {2773, 10061}, {2775, 10061}};

       
        /**
         * Wait for game start
         */
        public static void startNewWait() {
                waitTimer = 30;
                EventManager.getSingleton().addEvent(new Event() {
                        @Override
                        public void execute(EventContainer e) {
                                if (waitTimer > 0)
                                        waitTimer--;
                                if (waitTimer == 0) {
                                        if (inZombieWaitCount() < 1) {
                                                waitTimer = 30;
                                        } else {
                                                startGame();
                                                e.stop();
                                        }
                                }
                        }
                }, 1000);
        }

       
        public static void reset() {
                for (Player p : PlayerHandler.players) {
                        if (p == null || !p.isActive)
                                continue;
                        final Client c = (Client) p;
        endGame();
        startNewWait();
        zombieWaveId = 0;
        zombieAmount = 0;
        }
}
        public static void startGame() {
                zombieWaveId = 0;
                zombieAmount = 0;
                spawnNextWave();
                for (Player p : PlayerHandler.players) {
                        if (p == null || !p.isActive)
                                continue;
                        final Client c = (Client) p;
                        if (c.inZombieWait()) {
                                c.getPA().movePlayer(2784, 10057, 0);
                        }
                }
        }

        public static void spawnNextWave() {
                if (zombieWaveId > zombieWave[zombieWaveId].length) {
                        zombieWaveId = 0;
                        endGame();
                        return;
                } else if (zombieWaveId >= 26) {
                        endGame();
                }
                zombieAmount = zombieWave[zombieWaveId].length;
                for (int j = 0; j < zombieWave[zombieWaveId].length; j++) {
                        int npc = zombieWave[zombieWaveId][j];
                        int X = coords[j][0];
                        int Y = coords[j][1];
                        int hp = getHp(npc);
                        int max = getMax(npc);
                        int atk = getAtk(npc);
                        int def = getDef(npc);
                        Server.npcHandler.spawnNpc2(npc, X, Y, 0, 1, hp, max, atk, def);
                }
                zombieWaveId++;
        }

        public static void endGame() {
		                for (Player p : PlayerHandler.players) {
                        if (p == null || !p.isActive)
                                continue;
                        final Client c = (Client) p;
                startNewWait();

                        if (c.inZombieGame()) {
                                for (int i = 0; i < c.playerLevel.length; i++) {
                                        c.playerLevel[i] = c.getLevelForXP(c.playerXP[i]);
                                        c.getPA().refreshSkill(i);
                                }
                                c.respawnTimer = -6;
                                c.isDead = false;
                                c.getCombat().resetPrayers();
                                c.specAmount = 10.0;
                                c.getItems().updateSpecialBar();
                                c.getItems().addItem(995, zombieWaveId * 100000);
                                c.getPA().movePlayer(2464, 4781, 0);
                        }
                }
                for (int j = 0; j < NPCHandler.npcs.length; j++) {
                        if (NPCHandler.npcs[j] != null && !NPCHandler.npcs[j].isDead) {
                                if (isZombie(j)) {
                                        NPCHandler.npcs[j].HP = 0;
                                        NPCHandler.npcs[j].updateRequired = true;
                                        NPCHandler.npcs[j].isDead = true;
                                }
                        }
                }
        }

        public static int getHp(int npc) { //Step 2: Define new zombie id's hp here
                switch (npc) {
                        case 73:
                                return 210;
                        case 74 :
                                return 35;
                        case 75 :
                                return 48;
                        case 76 :
                                return 65;
                        case 77 :
                                return 78;
                        case 419 :
                                return 91;
                        case 420 :
                                return 112;
                        case 421 :
                                return 128;
                        case 422 :
                                return 148;
                        case 423 :
                                return 165;
                        case 424 :
                                return 185;
                        case 2060 :
                                return 2500;
                        case 2863 :
                                return 80;
                        case 2866 :
                                return 105;
                        case 2869 :
                                return 600;
                        case 3066 :
                                return 350;
                        case 3622 :
                                return 150;
                        case 2714 :
                                return 200;
                }
                return 100;
        }

        public static int getMax(int npc) { //Step 3: Define new zombie id's max hit here
                switch (npc) {
                        case 73:
                                return 2;
                        case 74 :
                                return 5;
                        case 75 :
                                return 8;
                        case 76 :
                                return 9;
                        case 77 :
                                return 11;
                        case 419 :
                                return 12;
                        case 420 :
                                return 14;
                        case 421 :
                                return 16;
                        case 422 :
                                return 19;
                        case 423 :
                                return 21;
                        case 424 :
                                return 23;
                        case 2060 :
                                return 61;
                        case 2863 :
                                return 12;
                        case 2866 :
                                return 18;
                        case 2869 :
                                return 45;
                        case 3066 :
                                return 38;
                        case 3622 :
                                return 14;
                        case 2714 :
                                return 28;
                }
                return 5;
        }

        public static int getAtk(int npc) { //Step 4: Define new zombie id's attack lvl here
                switch (npc) {
                        case 73:
                                return 60;
                        case 74 :
                                return 75;
                        case 75 :
                                return 90;
                        case 76 :
                                return 110;
                        case 77 :
                                return 125;
                        case 419 :
                                return 140;
                        case 420 :
                                return 160;
                        case 421 :
                                return 180;
                        case 422 :
                                return 195;
                        case 423 :
                                return 210;
                        case 424 :
                                return 230;
                        case 2060 :
                                return 520;
                        case 2863 :
                                return 130;
                        case 2866 :
                                return 150;
                        case 2869 :
                                return 400;
                        case 3066 :
                                return 270;
                        case 3622 :
                                return 130;
                        case 2714 :    
                                return 170;
                }
                return 100;
        }

        public static int getDef(int npc) { //Step 5: Define new zombie id's def level here
                switch (npc) {
                        case 73:
                                return 55;
                        case 74 :
                                return 75;
                        case 75 :
                                return 95;
                        case 76 :
                                return 110;
                        case 77 :
                                return 125;
                        case 419 :
                                return 140;
                        case 420 :
                                return 160;
                        case 421 :
                                return 180;
                        case 422 :
                                return 195;
                        case 423 :
                                return 210;
                        case 424 :
                                return 230;
                        case 2060 :
                                return 500;
                        case 2863 :
                                return 130;
                        case 2866 :
                                return 150;
                        case 2869 :
                                return 320;
                        case 3066 :
                                return 265;
                        case 3622 :
                                return 110;
                        case 2714 :
                                return 165;
                }
                return 100;
        }

        public static boolean isZombie(int j) { //Step 6: Add new zombie id's here
                switch (NPCHandler.npcs[j].npcType) {
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 419:
                        case 420:
                        case 421:
                        case 422:
                        case 423:
                        case 424:
                        case 2060:
                        case 2863:
                        case 2866:
                        case 2869:
                        case 3066:
                        case 3622:
                        case 2714:
                                return true;
                        default:
                                return false;
                }
        }

        public static void handleZombieDeath(Client c, int i) {
                ZombieWaves.zombieAmount--;
                NPCHandler.npcs[i].HP = 0;
                NPCHandler.npcs[i].updateRequired = true;
                NPCHandler.npcs[i].isDead = true;
                if (ZombieWaves.zombieAmount < 1) {
                     ZombieWaves.spawnNextWave();
                }
        }


        public static int inZombieWaitCount() {
                int count = 0;
                for (int i = 0; i < PlayerHandler.players.length; i++)
                        if (PlayerHandler.players[i] != null && !PlayerHandler.players[i].disconnected)
                                if (PlayerHandler.players[i].inZombieWait())
                                        count++;
                return count;
        }

        public static int inZombieGameCount() {
                int count = 0;
                for (int i = 0; i < PlayerHandler.players.length; i++)
                        if (PlayerHandler.players[i] != null && !PlayerHandler.players[i].disconnected)
                                if (PlayerHandler.players[i].inZombieGame())
                                        count++;
                return count;
        }
}