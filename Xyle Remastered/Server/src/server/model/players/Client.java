package server.model.players;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Future;

import org.apache.mina.common.IoSession;

import server.Config;
import server.Server;
import server.model.items.ItemAssistant;
import server.model.shops.ShopAssistant;
import server.model.minigames.ZombieWaves;
import server.net.HostList;
import server.net.Packet;
import server.net.StaticPacketBuilder;
import server.util.Misc;
import server.util.Stream;
import server.model.players.skills.*;
import server.event.EventManager;
import server.event.Event;
import server.event.EventContainer;
import server.model.players.PlayerSave;
import server.model.players.Summoning;
import server.model.npcs.NPC;
import server.clip.Path;
import server.clip.LineOfSightPathFinder;

public class Client extends Player {

	public boolean lineOfSight(Client opponent) {
                LineOfSightPathFinder pf = new LineOfSightPathFinder();
                int[] tile = closestTileToEntity(opponent);
                Path p = pf.findPath(getX(), getY(), opponent.getX(), opponent.getY(), heightLevel);
                if (p == null)
                        return true;
                if (p.getPoints().size() > 0 && (p.getPoints().getLast().getX() != tile[0] || p.getPoints().getLast().getY() != tile[1])) {
                        return false;
                }
                return true;
        }
		
		public static int zombieWaveId, zombieAmount, waitTimer;
	private final static int[][] zombieWave = {{73, 73, 73, 73}, {73, 73, 73, 73, 74}, {73, 73, 73, 73, 73, 74 ,74, 74}, {73, 73, 73, 74, 74, 73 ,74, 74, 74, 74}, {73, 74, 74, 74, 75, 75, 75, 3622, 76, 75, 75}, {74, 74, 74, 75, 74, 75, 76, 76, 76, 75, 75, 74, 74}, {74, 75, 76, 74, 76, 76, 75, 75, 77, 75, 76, 75, 76, 75, 76, 77, 75}, {75, 76, 75, 77, 76, 76, 77, 75, 76, 76, 77, 75, 76, 75, 77, 76, 76, 75, 75, 77, 77}, {76, 76, 77, 75, 76, 77, 77, 77, 76, 77, 77, 76, 76, 419, 77, 419, 77, 76, 76, 76, 77, 77, 76, 77, 76, 77}, {76, 77, 76, 77, 419, 419, 77, 419, 419, 77, 77, 76, 77, 419, 77, 77, 77, 420, 419, 77, 77, 77, 419, 76, 77, 77, 77}, {77, 77, 419, 419, 77, 420, 420, 77, 419, 420, 419, 419, 77, 419, 420, 419, 421, 421, 419, 77, 77, 77, 419, 420, 421, 420, 77, 419, 77, 419, 420}, {419, 419, 419, 420, 419, 420, 421, 422, 419, 422, 419, 421, 421, 420, 421, 419, 420, 422, 421, 421, 419, 422, 421, 419, 421, 422, 421, 419, 421, 419, 422, 419, 419, 419, 420, 421, 422}, {419, 419, 419, 420, 419, 420, 421, 422, 419, 422, 419, 421, 421, 420, 421, 419, 420, 422, 421, 421, 419, 422, 421, 419, 421, 422, 421, 419, 421, 419, 422, 419, 419, 419, 423, 423, 422, 422, 422, 420, 421, 422, 423}, {420, 421, 422, 420, 421, 421, 421, 422, 423, 422, 422, 421, 421, 420, 421, 421, 420, 420, 421, 422, 423, 420, 421, 423, 422, 422, 422, 421, 421, 421, 422, 423, 422, 421, 421, 423, 422, 422, 422, 423, 422, 421, 421}, {422, 421, 423, 423, 422, 422, 421, 423, 423, 422, 423, 423, 423, 422, 423, 423, 422, 421, 422, 423, 422, 421, 423, 422, 422, 422, 421, 422, 421, 423, 423, 422, 421, 421, 421, 421, 421, 421, 421, 422, 423, 423, 422, 422, 423, 423, 2869}, {73, 73, 73 ,73, 422, 422, 423, 423, 424, 423, 424, 423, 424, 73, 73, 73}, {424, 423, 424, 423,422, 423, 424, 423, 423, 2863, 424, 423, 422, 73, 73, 73, 73, 73}, {423, 422, 423, 424, 422, 423, 423, 422, 424, 2863, 424, 2863, 422, 2863}, {423, 422, 422, 423, 423, 424, 424, 2863, 424, 423, 422, 423, 424, 2863, 424, 424, 422, 423, 2863, 2863}, {424, 423, 422, 423, 424, 2863, 423, 2863, 424, 422, 423, 422, 424, 422, 2863, 422, 2863, 2863, 2863, 2863, 422, 422, 2863, 422, 424}, {422, 423, 424, 423, 424, 424, 2863, 2863, 424, 2863, 2863, 424, 2869, 2863, 2863, 424, 423, 424, 422, 2863, 424, 2863, 2863, 424, 2863, 424, 422, 424, 422, 424, 423, 424, 423, 422}, {424, 422, 2866, 2863, 424, 2863, 424, 422, 423, 2863, 2863, 2866, 424, 2863, 422, 423, 422, 423, 424, 2863, 424, 424, 422, 2866, 424, 2866, 2863, 424, 2866, 2863, 424, 422, 422, 424, 423, 424, 2866, 424, 2863}, {424, 2863, 422, 423, 424, 424, 3066, 2866, 2863, 424, 422, 424, 423, 424, 422, 423, 422, 423, 2863, 2866, 424, 422, 423, 422, 2866, 424, 422, 423, 422, 423, 423, 423, 424, 2863, 2714, 2866, 424, 424, 2866, 2863, 424, 2714, 422, 2863, 422, 2714}, {2863, 424, 423, 424, 424, 424, 424, 3066, 424, 2866, 2863, 424, 423, 424, 424, 2866, 424, 423, 424, 423, 423, 424, 2863, 424, 423, 424, 424, 2866, 2866, 2863, 424, 2866, 424, 423, 423, 424, 424, 423, 424, 423, 424, 2866, 2866, 423, 423, 424, 2866, 2863, 424, 3066, 2863, 424, 424, 423, 3066, 2714, 424, 2866, 424, 423, 424, 424, 424, 423, 3066, 424, 422, 423, 424, 424, 423, 3066, 2714, 424, 423, 424, 2866, 2714}, {73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 2060}}; //Step 1: Each bracket {} is a wave, add new waves like ,{419, 424, ect..}
	private static int[][] coords = {{2790, 10060}, {2791, 10057}, {2788, 10058}, {2783, 10057}, {2781, 10059}, {2781, 10062}, {2779, 10065}, {2776, 10065}, {2782, 10068}, {2801, 10061}, {2804, 10061}, {2802, 10064}, {2797, 10069}, {2800, 10064}, {2794, 10079}, {2782, 10092}, {2766, 10087}, {2786, 10090}, {2782, 10058}, {2781, 10055}, {2777, 10055}, {2778, 10094}, {2782, 10089}, {2786, 10085}, {2790, 10083}, {2789, 10079}, {2791, 10075}, {2771, 10096}, {2774, 10093}, {2772, 10097}, {2775, 10099}, {2781, 10097}, {2784, 10101}, {2781, 10104}, {2768, 10099}, {2775, 10101}, {2779, 10088}, {2776, 10081}, {2778, 10078}, {2780, 10074}, {2786, 10098}, {2780, 10106}, {2782, 10099}, {2780, 10095}, {2783, 10092}, {2790, 10084}, {2786, 10088}, {2795, 10057}, {2798, 10057},  {2778, 10094}, {2782, 10089}, {2786, 10085}, {2790, 10083}, {2789, 10079}, {2791, 10075}, {2771, 10096}, {2774, 10093}, {2772, 10097}, {2775, 10099}, {2781, 10097}, {2784, 10101}, {2781, 10104}, {2768, 10099}, {2775, 10101}, {2779, 10088}, {2776, 10081}, {2778, 10078}, {2780, 10074}, {2786, 10098}, {2780, 10106}, {2782, 10099}, {2780, 10095}, {2783, 10092}, {2790, 10084}, {2786, 10088}, {2795, 10057}, {2798, 10057}, {2787, 10069}, {2790, 10071}, {2771, 10075}, {2777, 10076},  {2783, 10074}, {2785, 10071}, {2788, 10072}, {2791, 10073}, {2777, 10076}, {2789, 10076}, {2772, 10079}, {2772, 10075}, {2775, 10079}, {2777, 10082}, {2773, 10083}, {2769, 10076}, {2769, 10089}, {2765, 10082}, {2762, 10081}, {2759, 10083}, {2761, 10086}, {2760, 10090}, {2765, 10096}, {2768, 10094}, {2772, 10091}, {2774, 10094}, {2773, 10056}, {2769, 10057}, {2766, 10057}, {2768, 10059}, {2773, 10058}, {2773, 10061}, {2775, 10061}};
       
 
	public Prayers.Prayer forId(int id, boolean curses22) {
		if(curses22) {
			switch(id) {
				case 19:
					return Prayers.Prayer.TURMOIL;
				case 18:
					return Prayers.Prayer.SOUL_SPLIT;
				case 17:
					return Prayers.Prayer.WRATH;
				case 16:
					return Prayers.Prayer.LEECH_SPECIAL;
				case 15:
					return Prayers.Prayer.LEECH_ENERGY;
				case 14:
					return Prayers.Prayer.LEECH_STRENGTH;
				case 13:
					return Prayers.Prayer.LEECH_DEFENCE;
				case 12:
					return Prayers.Prayer.LEECH_MAGE;
				case 11:
					return Prayers.Prayer.LEECH_RANGE;
				case 10:
					return Prayers.Prayer.LEECH_ATTACK;
				case 9:
					return Prayers.Prayer.DEFLECT_MELEE;
				case 8:
					return Prayers.Prayer.DEFLECT_RANGE;
				case 7:
					return Prayers.Prayer.DEFLECT_MAGIC;
				case 6:
					return Prayers.Prayer.DEFLECT_SUMMON;
				case 5:
					return Prayers.Prayer.BERSERKER;
				case 4:
					return Prayers.Prayer.SAP_SPIRIT;
				case 3:
					return Prayers.Prayer.SAP_MAGE;
				case 2:
					return Prayers.Prayer.SAP_RANGER;
				case 1:
					return Prayers.Prayer.SAP_WARRIOR;
				case 0:
					return Prayers.Prayer.PROTECT_ITEM_;
			}
		} else {
			switch(id) {
				case 25:
					return Prayers.Prayer.PIETY;
				case 24:
					return Prayers.Prayer.CHIVALRY;
				case 23:
					return Prayers.Prayer.SMITE;
				case 22:
					return Prayers.Prayer.REDEMPTION;
				case 21:
					return Prayers.Prayer.RETRIBUTION;
				case 20:
					return Prayers.Prayer.MYSTIC_MIGHT;
				case 19:
					return Prayers.Prayer.EAGLE_EYE;
				case 18:
					return Prayers.Prayer.PROTECT_FROM_MELEE;
				case 17:
					return Prayers.Prayer.PROTECT_FROM_MISSILES;
				case 16:
					return Prayers.Prayer.PROTECT_FROM_MAGIC;
				case 15:
					return Prayers.Prayer.INCREDIBLE_REFLEXES;
				case 14:
					return Prayers.Prayer.ULTIMATE_STRENGTH;
				case 13:
					return Prayers.Prayer.STEEL_SKIN;
				case 12:
					return Prayers.Prayer.MYSTIC_LORE;
				case 11:
					return Prayers.Prayer.HAWK_EYE;
				case 10:
					return Prayers.Prayer.PROTECT_ITEM;
				case 9:
					return Prayers.Prayer.RAPID_HEAL;
				case 8:
					return Prayers.Prayer.RAPID_RESTORE;
				case 7:
					return Prayers.Prayer.IMPROVED_REFLEXES;
				case 6:
					return Prayers.Prayer.SUPERHUMAN_STRENGTH;
				case 5:
					return Prayers.Prayer.ROCK_SKIN;
				case 4:
					return Prayers.Prayer.MYSTIC_WILL;
				case 3:
					return Prayers.Prayer.SHARP_EYE;
				case 2:
					return Prayers.Prayer.CLARITY_OF_THOUGHT;
				case 1:
					return Prayers.Prayer.BURST_OF_STRENGTH;
				case 0:
					return Prayers.Prayer.THICK_SKIN;
			}
		}
		return null;
	}
	  /*  public static void startNewWait() {
                    waitTimer = 30;
                    EventManager.getSingleton().addEvent(new Event() {
                            @Override
                            public void execute(EventContainer e) {
                                    if (waitTimer > 0)
                                            waitTimer--;
                                    if (waitTimer == 0) {
                                            if (c.getZW.inZombieWaitCount() < 1) {
                                                    waitTimer = 30;
                                            } else {
                                                    startGame();
                                                    e.stop();
                                            }
                                    }
                            }
                    }, 1000);
            }*/
			
     

	public boolean prayerActive(int prayerOrdinal) {
		return get(forId(prayerOrdinal, false), false);
	}
	
	public boolean curseActive(int prayerOrdinal) {
		return get(forId(prayerOrdinal, true), false);
	}
	
	public boolean get(int prayerOrdinal, boolean curse) {
		return get(forId(prayerOrdinal, curse), false);
	}

	public ArrayList<Prayers.Prayer> activePrayers = new ArrayList<Prayers.Prayer>();
	
	public ArrayList<Prayers.Prayer> getActivePrayers() {
		return activePrayers;
	}

	private ArrayList<Prayers.Prayer> quickPrayers = new ArrayList<Prayers.Prayer>();
	
	public ArrayList<Prayers.Prayer> getQuickPrayers() {
		return quickPrayers;
	}
	
	public boolean get(Prayers.Prayer prayer, boolean quickPrayer) {
		ArrayList<Prayers.Prayer> list = quickPrayer ? getQuickPrayers() : getActivePrayers();
		return list.contains(prayer);
	}
	
	public void rem(int i, boolean curse) {
		rem(forId(i, curse), false);
	}
	
	public void rem(Prayers.Prayer prayer) {
		rem(prayer, false);
	}
	
	public void rem(Prayers.Prayer prayer, boolean quickPrayer) {
		ArrayList<Prayers.Prayer> list = quickPrayer ? getQuickPrayers() : getActivePrayers();
		list.remove(prayer);
	}
	
	public boolean add(Prayers.Prayer prayer, boolean quickPrayer) {
		ArrayList<Prayers.Prayer> list = quickPrayer ? getQuickPrayers() : getActivePrayers();
		return list.add(prayer);
	}
	
	public boolean isOnCurses() {
		return true;
	}

	public boolean ramboModeInitiated = false;

	public byte buffer[] = null;
	public Stream inStream = null, outStream = null;
	private IoSession session;
	private ItemAssistant itemAssistant = new ItemAssistant(this);
	private ShopAssistant shopAssistant = new ShopAssistant(this);
	private TradeAndDuel tradeAndDuel = new TradeAndDuel(this);
	private PlayerAssistant playerAssistant = new PlayerAssistant(this);
	private ZombieWaves zombieWaves = new ZombieWaves(this);
	private CombatAssistant combatAssistant = new CombatAssistant(this);
	private Curses curses = new Curses(this);
	private ActionHandler actionHandler = new ActionHandler(this);
	private PlayerKilling playerKilling = new PlayerKilling(this);
	private DialogueHandler dialogueHandler = new DialogueHandler(this);
	private Queue<Packet> queuedPackets = new LinkedList<Packet>();
	private Potions potions = new Potions(this);
	private PotionMixing potionMixing = new PotionMixing(this);
	private Food food = new Food(this);
	/**
	 * Skill instances
	 */
	private Slayer slayer = new Slayer(this);
	private Runecrafting runecrafting = new Runecrafting(this);
	private Woodcutting woodcutting = new Woodcutting(this);
	private Mining mine = new Mining(this);
	private Agility agility = new Agility(this);
	private Cooking cooking = new Cooking(this);
	private Fishing fish = new Fishing(this);
	private Crafting crafting = new Crafting(this);
	private Smithing smith = new Smithing(this);
	private Prayer prayer = new Prayer(this);
	private Fletching fletching = new Fletching(this);
	private SmithingInterface smithInt = new SmithingInterface(this);
	private Farming farming = new Farming(this);
	private Thieving thieving = new Thieving(this);
	private Firemaking firemaking = new Firemaking(this);
	private Herblore herblore = new Herblore(this);

	private int somejunk;
	public int lowMemoryVersion = 0;
	public int timeOutCounter = 0;		
	public int returnCode = 2; 
	private Future<?> currentTask;
	
	public int animToDo;
	public boolean animLoop;
	
	public Client(IoSession s, int _playerId) {
		super(_playerId);
		this.session = s;
		synchronized(this) {
			outStream = new Stream(new byte[Config.BUFFER_SIZE]);
			outStream.currentOffset = 0;
		}
		inStream = new Stream(new byte[Config.BUFFER_SIZE]);
		inStream.currentOffset = 0;
		buffer = new byte[Config.BUFFER_SIZE];
	}
       public int[] closestTileToEntity(Client opponent) {
                int otherX = opponent.absX;
                int otherY = opponent.absY;
                if(otherY > getY() && otherX == getX()) {
                        return new int[] {otherX, otherY - 1};
                } else if(otherY < getY() && otherX == getX()) {
                        return new int[] {otherX, otherY + 1};
                } else if(otherX > getX() && otherY == getY()) {
                        return new int[] {otherX - 1, otherY};
                } else if(otherX < getX() && otherY == getY()) {
                        return new int[] {otherX + 1, otherY};
                } else if(otherX < getX() && otherY < getY()) {
                        return new int[] {otherX + 1, otherY + 1};
                } else if(otherX > getX() && otherY > getY()) {
                        return new int[] {otherX - 1, otherY - 1};
                } else if(otherX < getX() && otherY > getY()) {
                        return new int[] {otherX + 1, otherY - 1};
                } else if(otherX > getX() && otherY < getY()) {
                        return new int[] {otherX - 1, otherY + 1};
                } else {
                        return new int[] {otherX, otherY};
                }
        }
	
	public void flushOutStream() {	
		if(disconnected || outStream.currentOffset == 0) return;
		synchronized(this) {	
			StaticPacketBuilder out = new StaticPacketBuilder().setBare(true);
			byte[] temp = new byte[outStream.currentOffset]; 
			System.arraycopy(outStream.buffer, 0, temp, 0, temp.length);
			out.addBytes(temp);
			session.write(out.toPacket());
			outStream.currentOffset = 0;
		}
    }
	
	public void jadSpawn() {
		getDH().sendDialogues(33, 2618);
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer c) {
				Server.fightCaves.spawnNextWave((Client)Server.playerHandler.players[playerId]);
				c.stop();
			}
		}, 10000);
	}
	
	public void sendClan(String name, String message, String clan, int rights) {
		outStream.createFrameVarSizeWord(217);
		outStream.writeString(name);
		outStream.writeString(message);
		outStream.writeString(clan);
		outStream.writeWord(rights);
		outStream.endFrameVarSize();
	}
	
	public static final int PACKET_SIZES[] = {
		0, 0, 0, 1, -1, 0, 0, 0, 0, 0, //0
		0, 0, 0, 0, 8, 0, 6, 2, 2, 0,  //10
		0, 2, 0, 6, 0, 12, 0, 0, 0, 0, //20
		0, 0, 0, 0, 0, 8, 4, 0, 0, 2,  //30
		2, 6, 0, 6, 0, -1, 0, 0, 0, 0, //40
		0, 0, 0, 12, 0, 0, 0, 8, 8, 12, //50
		8, 8, 0, 0, 0, 0, 0, 0, 0, 0,  //60
		6, 0, 2, 2, 8, 6, 0, -1, 0, 6, //70
		0, 0, 0, 0, 0, 1, 4, 6, 0, 0,  //80
		0, 0, 0, 0, 0, 3, 0, 0, -1, 0, //90
		0, 13, 0, -1, 0, 0, 0, 0, 0, 0,//100
		0, 0, 0, 0, 0, 0, 0, 6, 0, 0,  //110
		1, 0, 6, 0, 0, 0, -1, 0, 2, 6, //120
		0, 4, 6, 8, 0, 6, 0, 0, 0, 2,  //130
		0, 0, 0, 0, 0, 6, 0, 0, 0, 0,  //140
		0, 0, 1, 2, 0, 2, 6, 0, 0, 0,  //150
		0, 0, 0, 0, -1, -1, 0, 0, 0, 0,//160
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  //170
		0, 8, 0, 3, 0, 2, 0, 0, 8, 1,  //180
		0, 0, 12, 0, 0, 0, 0, 0, 0, 0, //190
		2, 0, 0, 0, 0, 0, 0, 0, 4, 0,  //200
		4, 0, 0, 0, 7, 8, 0, 0, 10, 0, //210
		0, 0, 0, 0, 0, 0, -1, 0, 6, 0, //220
		1, 0, 0, 0, 6, 0, 6, 8, 1, 0,  //230
		0, 4, 0, 0, 0, 0, -1, 0, -1, 4,//240
		0, 0, 6, 6, 0, 0, 0            //250
	};

	public void destruct() {
		if(session == null) 
			return;
		//PlayerSaving.getSingleton().requestSave(playerId);
		getPA().removeFromCW();
		if (inPits)
			Server.fightPits.removePlayerFromPits(playerId);
		if (clanId >= 0)
			Server.clanChat.leaveClan(playerId, clanId);
		Misc.println("[DEREGISTERED]: "+playerName+"");
		HostList.getHostList().remove(session);
		disconnected = true;
		session.close();
		session = null;
		inStream = null;
		outStream = null;
		isActive = false;
		buffer = null;
		super.destruct();
	}
	
	public void addToHp(int toAdd) {
		if (constitution + toAdd >= maxConstitution)
			toAdd = maxConstitution - maxConstitution;
		constitution += toAdd;
	}
	
	public void removeFromPrayer(int toRemove) {
		if (toRemove > playerLevel[5]) 
			toRemove = playerLevel[5];
		playerLevel[5] -= toRemove;
		getPA().refreshSkill(5);
	}
	
	
	public void sendMessage(String s) {
		synchronized (this) {
			if(getOutStream() != null) {
				outStream.createFrameVarSize(253);
				outStream.writeString(s);
				outStream.endFrameVarSize();
			}
		}
	}

	public void setSidebarInterface(int menuId, int form) {
		synchronized (this) {
			if(getOutStream() != null) {
				outStream.createFrame(71);
				outStream.writeWord(form);
				outStream.writeByteA(menuId);
			}
		}
	}	
	
	public void initialize() {
		synchronized (this) {
			outStream.createFrame(249);
			outStream.writeByteA(1);		// 1 for members, zero for free
			outStream.writeWordBigEndianA(playerId);
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (j == playerId)
					continue;
				if (Server.playerHandler.players[j] != null) {
					if (Server.playerHandler.players[j].playerName.equalsIgnoreCase(playerName))
						disconnected = true;
				}
			}
			for (int i = 0; i < 25; i++) {
				getPA().setSkillLevel(i, playerLevel[i], playerXP[i]);
				getPA().refreshSkill(i);
			}
			Prayers.refreshPrayers(this, false);
			Prayers.refreshPrayers(this, true);
			getPA().sendCrashFrame();
			getPA().handleWeaponStyle();
			getPA().handleLoginText();
			accountFlagged = getPA().checkForFlags();
			getPA().sendFrame36(108, 0);//resets autocast button
			getPA().sendFrame36(172, 1);
			getPA().sendFrame107(); // reset screen
			getPA().setChatOptions(0, 0, 0); // reset private messaging options
			setSidebarInterface(0, 2423);
			setSidebarInterface(1, 3917);
			setSidebarInterface(2, 638);
			setSidebarInterface(3, 3213);
			setSidebarInterface(4, 1644);
			setSidebarInterface(5, 22500); // Curses - 22500  5608
			setSidebarInterface(6, getPA().spellBook[playerMagicBook]);
			setSidebarInterface(7, 18128);		
			setSidebarInterface(8, 5065);
			setSidebarInterface(9, 5715);
			setSidebarInterface(10, 2449); //2449
			setSidebarInterface(11, 904); // wrench tab
			setSidebarInterface(12, 147); // run tab
			setSidebarInterface(13, 962); //music tab 6299 for lowdetail. 962 for highdetail
			setSidebarInterface(14, 17000); //acheivement
			setSidebarInterface(15, 17000); //blank
			setSidebarInterface(16, 17000); //blank
			correctCoordinates();
			getPA().resetAutocast();
			getCombat().resetPrayers();
			if (specAmount > 10)
				specAmount = 10;
			sendMessage("Welcome to "+Config.SERVER_NAME);
			//sendMessage("Please visit our new website! www.exitium-rsps.com/webclient.");
			//sendMessage(Config.WELCOME_MESSAGE);
			getPA().showOption(4, 0,"Follow", 4);
			getPA().showOption(5, 0,"Trade With", 3);
			getItems().resetItems(3214);
			getItems().sendWeapon(playerEquipment[playerWeapon], getItems().getItemName(playerEquipment[playerWeapon]));
			getItems().resetBonus();
			getItems().getBonus();
			getItems().writeBonus();
			getItems().setEquipment(playerEquipment[playerHat],1,playerHat);
			getItems().setEquipment(playerEquipment[playerCape],1,playerCape);
			getItems().setEquipment(playerEquipment[playerAmulet],1,playerAmulet);
			getItems().setEquipment(playerEquipment[playerArrows],playerEquipmentN[playerArrows],playerArrows);
			getItems().setEquipment(playerEquipment[playerChest],1,playerChest);
			getItems().setEquipment(playerEquipment[playerShield],1,playerShield);
			getItems().setEquipment(playerEquipment[playerLegs],1,playerLegs);
			getItems().setEquipment(playerEquipment[playerHands],1,playerHands);
			getItems().setEquipment(playerEquipment[playerFeet],1,playerFeet);
			getItems().setEquipment(playerEquipment[playerRing],1,playerRing);
			getItems().setEquipment(playerEquipment[playerWeapon],playerEquipmentN[playerWeapon],playerWeapon);
			getCombat().getPlayerAnimIndex(getItems().getItemName(playerEquipment[playerWeapon]).toLowerCase());
			getPA().logIntoPM();
			getItems().addSpecialBar(playerEquipment[playerWeapon]);
			saveTimer = 100;
			saveCharacter = true;
			Misc.println("[REGISTERED]: "+playerName+"");
			if (addStarter) {
				//joinTime = System.currentTimeMillis();
				getPA().addStarter();
				sendMessage("@blu@You recieve 10 PK points as a starter.");
				pkp += 10;
				constitution = 100;
			}
			handler.updatePlayer(this, outStream);
			handler.updateNPC(this, outStream);
			flushOutStream();
			getPA().clearClanChat();
			getPA().resetFollow();
			getPA().sendFrame36(172, autoRet);
			getPA().sendFrame36(173, isRunning2 ? 1 : 0);
			if(savedClan.length() > 0 && savedClan != null)
				Server.clanChat.handleClanChat(this, savedClan);
		}
	}

	public void update() {
		synchronized (this) {
			handler.updatePlayer(this, outStream);
			handler.updateNPC(this, outStream);
			flushOutStream();
		}
	}
	
	public void logout() {
		synchronized (this) {
			if(System.currentTimeMillis() - logoutDelay > 10000) {
				getSummoning().dismissFamiliar(true);
				outStream.createFrame(109);
				properLogout = true;
			} else {
				sendMessage("You must wait a few seconds from being out of combat to logout.");
			}
		}
	}

	public int diceTimer = 0;
	
	public int packetSize = 0, packetType = -1;
	
	public void process() {
		getPA().sendFrame126("Combat Level: "+combatLevel, 19000);
		getPA().sendFrame126("" + constitution, 19001);
		curses().handleProcess();
		getSummoning().familiarTick();
		if (wcTimer > 0 && woodcut[0] > 0) {
			wcTimer--;
		} else if (wcTimer == 0 && woodcut[0] > 0) {
			getWoodcutting().cutWood();
		} else if (miningTimer > 0 && mining[0] > 0) {
			miningTimer--;
		} else if (miningTimer == 0 && mining[0] > 0) {
			getMining().mineOre();
		} else  if (smeltTimer > 0 && smeltType > 0) {
			smeltTimer--;
		} else if (smeltTimer == 0 && smeltType > 0) {
			getSmithing().smelt(smeltType);
		} else if (fishing && fishTimer > 0) {
			fishTimer--;
		} else if (fishing && fishTimer == 0) {
			getFishing().catchFish();
		}
		diceTimer--;
  maxConstitution = getLevelForXP(playerXP[3]) * 10;
  if(torvaEquipped(playerHat))
   maxConstitution += 60;
  if(torvaEquipped(playerChest))
   maxConstitution += 200;
  if(torvaEquipped(playerLegs))
   maxConstitution += 10;
		if(playerLevel[3] > (maxHp * 1.2))
			playerLevel[3] = (int) (maxHp * 1.2);
		getPA().sendFrame126(""+maxHp, 4017);
		if (clawDelay > 0) 
			clawDelay--;
		if (clawDelay == 0) {
			if (clawTarg != 0) {
				int soak = ((Client)Server.playerHandler.players[clawTarg]).getCombat().damageSoaked(hit3, "Melee");
				hit3 -= soak;
				int soak2 = ((Client)Server.playerHandler.players[clawTarg]).getCombat().damageSoaked(hit4, "Melee");
				hit4 -= soak;
				getCombat().appendHit((Client)Server.playerHandler.players[clawTarg], hit3, 0, 0, true, soak);
				getCombat().appendHit((Client)Server.playerHandler.players[clawTarg], hit4, 0, 0, true, soak2);
			} else if (clawTargNPC != 0) {
				getCombat().appendHit(Server.npcHandler.npcs[clawTargNPC], hit3, 0, 0, 1);
				getCombat().appendHit(Server.npcHandler.npcs[clawTargNPC], hit4, 0, 0, 2);
			}
			clawDelay = -1;
		}
		if (System.currentTimeMillis() - lastPoison > 20000 && poisonDamage > 0) {
			int damage = poisonDamage/20;
			if (damage > 0) {
				lastPoison = System.currentTimeMillis();
				getCombat().appendHit(this, damage, 2, -1, false, 0);
				poisonDamage--;
			} else {
				poisonDamage = -1;
				sendMessage("The poison has worn off.");
			}	
		}
		
		if(System.currentTimeMillis() - duelDelay > 800 && duelCount > 0) {
			if(duelCount != 1) {
				forcedChat(""+(--duelCount));
				duelDelay = System.currentTimeMillis();
			} else {
				damageTaken = new int[Config.MAX_PLAYERS];
				forcedChat("FIGHT!");
				duelCount = 0;
			}
		}
	
		if(System.currentTimeMillis() - specDelay > Config.INCREASE_SPECIAL_AMOUNT) {
			specDelay = System.currentTimeMillis();
			if(specAmount < 10) {
				specAmount += .5;
				if (specAmount > 10)
					specAmount = 10;
				getItems().addSpecialBar(playerEquipment[playerWeapon]);
			}
		}
		
		if(clickObjectType > 0 && goodDistance(objectX + objectXOffset, objectY + objectYOffset, getX(), getY(), objectDistance)) {
			if(clickObjectType == 1) {
				getActions().firstClickObject(objectId, objectX, objectY);
			}
			if(clickObjectType == 2) {
				getActions().secondClickObject(objectId, objectX, objectY);
			}
			if(clickObjectType == 3) {
				getActions().thirdClickObject(objectId, objectX, objectY);
			}
		}
		
		if((clickNpcType > 0) && Server.npcHandler.npcs[npcClickIndex] != null) {			
			if(goodDistance(getX(), getY(), Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY(), 1)) {
				if(clickNpcType == 1) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().firstClickNpc(npcType);
				}
				if(clickNpcType == 2) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().secondClickNpc(npcType);
				}
				if(clickNpcType == 3) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().thirdClickNpc(npcType);
				}
			}
		}
		if(getAwaitingUpdate())
			getItems().resetItems(3214);
		if(walkingToItem) {
			if(getX() == pItemX && getY() == pItemY || goodDistance(getX(), getY(), pItemX, pItemY,1)) {
				walkingToItem = false;
				Server.itemHandler.removeGroundItem(this, pItemId, pItemX, pItemY, true);
			}
		}
		
		if(followId > 0) {
			getPA().followPlayer();
		} else if (followId2 > 0) {
			getPA().followNpc();
		}
		
		getCombat().handlePrayerDrain();
		
		if(System.currentTimeMillis() - singleCombatDelay >  3300) {
			underAttackBy = 0;
		}
		if (System.currentTimeMillis() - singleCombatDelay2 > 3300) {
			underAttackBy2 = 0;
		}
		if (System.currentTimeMillis() - restoreStatsDelay2 > 6000) {
			if (!inWild()) {
			restoreStatsDelay2 = System.currentTimeMillis();
			if (constitution < maxConstitution) 
				constitution += 1;
			}
		}
		if(System.currentTimeMillis() - restoreStatsDelay > 60000) {
			restoreStatsDelay = System.currentTimeMillis();
			for (int level = 0; level < playerLevel.length; level++)  {
				if (playerLevel[level] < (level == 3 ? maxHp : getLevelForXP(playerXP[level]))) {
					if(level != 5) { // prayer doesn't restore
						playerLevel[level] += 1;
						getPA().setSkillLevel(level, playerLevel[level], playerXP[level]);
						getPA().refreshSkill(level);
					}
				} else if (playerLevel[level] > (level == 3 ? maxHp : getLevelForXP(playerXP[level]))) {
					playerLevel[level] -= 1;
					getPA().setSkillLevel(level, playerLevel[level], playerXP[level]);
					getPA().refreshSkill(level);
				}
			}
		}

		if(System.currentTimeMillis() - teleGrabDelay >  1550 && usingMagic) {
			usingMagic = false;
			if(Server.itemHandler.itemExists(teleGrabItem, teleGrabX, teleGrabY)) {
				Server.itemHandler.removeGroundItem(this, teleGrabItem, teleGrabX, teleGrabY, true);
			}
		}
		
		if (barbLeader > 0 && inBarbDef) {
			NPC n = Server.npcHandler.npcs[barbLeader];
			if (n != null) {
				n.facePlayer(playerId);
				if (Misc.random(50) == 0) {
					n.requestAnimation(6728, 0);
					n.forceChat(n.barbRandom(this, Misc.random(5)));
				}
			}
		}
			
		
		if(inWild() || playerName.toLowerCase().equals("brandyn") || playerName.toLowerCase().equals("gacoa")) {
			int modY = absY > 6400 ?  absY - 6400 : absY;
			wildLevel = (((modY - 3525) / 8) + 1);
			getPA().walkableInterface(197);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
					getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
				} else {
					getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
				}
			} else {
				getPA().multiWay(-1);
				getPA().sendFrame126("@yel@Level: "+wildLevel, 199);
			}
			getPA().showOption(3, 0, "Attack", 1);
		} else if (inDuelArena()) {
			getPA().walkableInterface(201);
			if(duelStatus == 5) {
				getPA().showOption(3, 0, "Attack", 1);
			} else {
				getPA().showOption(3, 0, "Challenge", 1);
			}
		} else if(inBarrows()){
			getPA().sendFrame99(2);
			getPA().sendFrame126("Kill Count: "+barrowsKillCount, 4536);
			getPA().walkableInterface(4535);
		} else if (inCwGame || inPits) {
			getPA().showOption(3, 0, "Attack", 1);
		} else if (inBarbDef) {
			getPA().walkableInterface(2459);
			getPA().sendFrame126("Total damage dealt: @gre@"+barbDamage, 2461);
			getPA().sendFrame126("Barbarian Defense", 2460);
			getPA().sendFrame126("Wave @gre@"+(barbWave+1), 2462);
		} else if (inZombieWait()) {
         getPA().walkableInterface(2459);
         getPA().sendFrame126("", 2460);
         getPA().sendFrame126(ZombieWaves.waitTimer < 0 ? "Game in progress" : "Next Departure: " + ZombieWaves.waitTimer, 2461);
         getPA().sendFrame126("Players Ready: " + ZombieWaves.inZombieWaitCount(), 2462);
		} else if (inZombieGame()) {
         getPA().walkableInterface(2459);
         getPA().sendFrame126("", 2460);
         getPA().sendFrame126("Wave Number: " + ZombieWaves.zombieWaveId, 2461);
         getPA().sendFrame126("Zombies Left: " + ZombieWaves.zombieAmount, 2462);			
		} else if (getPA().inPitsWait()) {
			getPA().showOption(3, 0, "Null", 1);
		}else if (!inCwWait) {
			getPA().sendFrame99(0);
			getPA().walkableInterface(-1);
			getPA().showOption(3, 0, "Null", 1);

		}
		
		if(!hasMultiSign && inMulti()) {
			hasMultiSign = true;
			getPA().multiWay(1);
		}
		
		if(hasMultiSign && !inMulti()) {
			hasMultiSign = false;
			getPA().multiWay(-1);
		}

		if(skullTimer > 0) {
			skullTimer--;
			if(skullTimer == 1) {
				isSkulled = false;
				attackedPlayers.clear();
				headIconPk = -1;
				skullTimer = -1;
				getPA().requestUpdates();
			}	
		}
		
		if(isDead && respawnTimer == -6) {
			getPA().applyDead();
		}
		
		if(respawnTimer == 7) {
			respawnTimer = -6;
			getPA().giveLife();
		} else if(respawnTimer == 12) {
			respawnTimer--;
			startAnimation(0x900);
			poisonDamage = -1;
		}	
		
		if(respawnTimer > -6) {
			respawnTimer--;
		}
		if(freezeTimer > -6) {
			freezeTimer--;
			if (frozenBy > 0) {
				if (Server.playerHandler.players[frozenBy] == null) {
					freezeTimer = -1;
					frozenBy = -1;
				} else if (!goodDistance(absX, absY, Server.playerHandler.players[frozenBy].absX, Server.playerHandler.players[frozenBy].absY, 12)) {
					freezeTimer = -1;
					frozenBy = -1;
				}
			}
		}
		
		if(hitDelay > 0) {
			hitDelay--;
		}
		
		if(teleTimer > 0) {
			teleTimer--;
			if (!isDead) {
				if(teleTimer == 1 && newLocation > 0) {
					teleTimer = 0;
					getPA().changeLocation();
				}
				if(teleTimer == 5) {
					teleTimer--;
					getPA().processTeleport();
				}
				if(teleTimer == 9 && teleGfx > 0) {
					teleTimer--;
					if (teleGfx != 342)
						gfx100(teleGfx);
					else 
						gfx0(teleGfx);
				}
			} else {
				teleTimer = 0;
			}
		}	
		
		if (teleBlockLength > 0 && !inWild())
			teleBlockLength = 0;

		if(hitDelay == 1) {
			if(oldNpcIndex > 0) {
				getCombat().delayedHit(oldNpcIndex);
			}
			if(oldPlayerIndex > 0) {
				getCombat().playerDelayedHit(oldPlayerIndex);				
			}		
		}
		
		if(attackTimer > 0) {
			attackTimer--;
		}
		
		if(attackTimer == 1){
			if(npcIndex > 0 && clickNpcType == 0) {
				getCombat().attackNpc(npcIndex);
			}
			if(playerIndex > 0) {
				getCombat().attackPlayer(playerIndex);
			}
		} else if (attackTimer <= 0 && (npcIndex > 0 || playerIndex > 0)) {
			if (npcIndex > 0) {
				attackTimer = 0;
				getCombat().attackNpc(npcIndex);
			} else if (playerIndex > 0) {
				attackTimer = 0;
				getCombat().attackPlayer(playerIndex);
			}
		}
		
		if(timeOutCounter > Config.TIMEOUT) {
			disconnected = true;
		}
		
		timeOutCounter++;
		
		if(inTrade && tradeResetNeeded){
			Client o = (Client) Server.playerHandler.players[tradeWith];
			if(o != null){
				if(o.tradeResetNeeded){
					getTradeAndDuel().resetTrade();
					o.getTradeAndDuel().resetTrade();
				}
			}
		}
	}
	
	public boolean torvaEquipped(int slot) {
		if(slot == playerHat) {
			if(playerEquipment[slot] == 20135 || playerEquipment[slot] == 20147 || playerEquipment[slot] == 20159)
				return true;
		} else if(slot == playerChest) {
			if(playerEquipment[slot] == 20163 || playerEquipment[slot] == 20151 || playerEquipment[slot] == 20139)
				return true;
		} else if(slot == playerLegs) {
			if(playerEquipment[slot] == 20167 || playerEquipment[slot] == 20155 || playerEquipment[slot] == 20143)
				return true;
		}
		return false;
	}

	public void setCurrentTask(Future<?> task) {
		currentTask = task;
	}

	public Future<?> getCurrentTask() {
		return currentTask;
	}
	
	public synchronized Stream getInStream() {
		return inStream;
	}
	
	public synchronized int getPacketType() {
		return packetType;
	}
	
	public synchronized int getPacketSize() {
		return packetSize;
	}
	
	public synchronized Stream getOutStream() {
		return outStream;
	}
	
	public ItemAssistant getItems() {
		return itemAssistant;
	}
		
	public PlayerAssistant getPA() {
		return playerAssistant;
	}
	public ZombieWaves getZW() {
		return zombieWaves;
	}
	
	public DialogueHandler getDH() {
		return dialogueHandler;
	}
	
	public ShopAssistant getShops() {
		return shopAssistant;
	}
	
	public TradeAndDuel getTradeAndDuel() {
		return tradeAndDuel;
	}
	
	public CombatAssistant getCombat() {
		return combatAssistant;
	}
	
	public Curses curses() {
		return curses;
	}
	
	public Summoning getSummoning() {
		return summoning;
	}
	
	public ActionHandler getActions() {
		return actionHandler;
	}
  
	public PlayerKilling getKill() {
		return playerKilling;
	}
	
	public IoSession getSession() {
		return session;
	}
	
	public Potions getPotions() {
		return potions;
	}
	
	public PotionMixing getPotMixing() {
		return potionMixing;
	}
	
	public Food getFood() {
		return food;
	}
	
	/**
	 * Skill Constructors
	 */
	public Slayer getSlayer() {
		return slayer;
	}
	
	public Runecrafting getRunecrafting() {
		return runecrafting;
	}
	
	public Woodcutting getWoodcutting() {
		return woodcutting;
	}
	
	public Mining getMining() {
		return mine;
	}
	
	public Cooking getCooking() {
		return cooking;
	}
	
	public Agility getAgility() {
		return agility;
	}
	
	public Fishing getFishing() {
		return fish;
	}
	
	public Crafting getCrafting() {
		return crafting;
	}
	
	public Smithing getSmithing() {
		return smith;
	}
	
	public Farming getFarming() {
		return farming;
	}
	
	public Thieving getThieving() {
		return thieving;
	}
	
	public Herblore getHerblore() {
		return herblore;
	}
	
	public Firemaking getFiremaking() {
		return firemaking;
	}
	
	public SmithingInterface getSmithingInt() {
		return smithInt;
	}
	
	public Prayer getPrayer() { 
		return prayer;
	}
	
	public Fletching getFletching() { 
		return fletching;
	}
	
	/**
	 * End of Skill Constructors
	 */
	
	public void queueMessage(Packet arg1) {
		synchronized(queuedPackets) {
				queuedPackets.add(arg1);
		}
	}
	
	public synchronized boolean processQueuedPackets() {
		Packet p = null;
		synchronized(queuedPackets) {
			p = queuedPackets.poll();
		}
		if(p == null) {
			return false;
		}
		inStream.currentOffset = 0;
		packetType = p.getId();
		packetSize = p.getLength();
		inStream.buffer = p.getData();
		if(packetType > 0) {
			PacketHandler.processPacket(this, packetType, packetSize);
		}
		timeOutCounter = 0;
		return true;
	}
	
	public synchronized boolean processPacket(Packet p) {
		synchronized (this) {
			if(p == null) {
				return false;
			}
			inStream.currentOffset = 0;
			packetType = p.getId();
			packetSize = p.getLength();
			inStream.buffer = p.getData();
			if(packetType > 0) {
				PacketHandler.processPacket(this, packetType, packetSize);
			}
			timeOutCounter = 0;
			return true;
		}
	}
	
	
	public void correctCoordinates() {
		if (inPcGame()) {
			getPA().movePlayer(2657, 2639, 0);
		}
		if (inFightCaves()) {
			getPA().movePlayer(absX, absY, playerId * 4);
			sendMessage("Your wave will start in 10 seconds.");
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer c) {
					Server.fightCaves.spawnNextWave((Client)Server.playerHandler.players[playerId]);
					c.stop();
				}
				}, 10000);
		
		}
	
	}
	
}
