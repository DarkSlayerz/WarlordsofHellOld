package server.model.players;

import java.util.HashMap;
import java.util.Map;
import server.util.Misc;

public class Prayers {
		
	/**
	 * Represents Prayer Types / Masks
	 * @author Nikki / Brandyn
	 */
	public static final int OVERHEAD_PRAYER = 1;
	public static final int ATTACK_PRAYER = 2;
	public static final int STRENGTH_PRAYER = 4;
	public static final int RANGE_PRAYER = 8;
	public static final int MAGIC_PRAYER = 16;
	public static final int DEFENCE_PRAYER = 32;
	public static final int SAP_PRAYER = 64;
	public static final int LEECH_PRAYER = 128;
	
	/**
	 * Represents prayers (Curses/Normals)
	 * @author Brandyn / Scu11
	 */
	public static enum Prayer {
		THICK_SKIN(new int[] {21233, 67050, 1, 83, 631}, 12, DEFENCE_PRAYER),
		BURST_OF_STRENGTH(new int[] {21234, 67051, 4, 84, 632}, 12, STRENGTH_PRAYER),
		CLARITY_OF_THOUGHT(new int[] {21235, 67052, 7, 85, 633}, 12, ATTACK_PRAYER),
		SHARP_EYE(new int[] {77100, 67053, 8, 700, 634}, 12, RANGE_PRAYER | ATTACK_PRAYER | STRENGTH_PRAYER),
		MYSTIC_WILL(new int[] {77102, 67054, 9, 701, 635}, 12, MAGIC_PRAYER | ATTACK_PRAYER | STRENGTH_PRAYER),
		ROCK_SKIN(new int[] {21236, 67055, 10, 86, 636}, 6, DEFENCE_PRAYER),
		SUPERHUMAN_STRENGTH(new int[] {21237, 67056, 13, 87, 637}, 6, STRENGTH_PRAYER),
		IMPROVED_REFLEXES(new int[] {21238, 67057, 16, 88, 638}, 6, ATTACK_PRAYER),
		RAPID_RESTORE(new int[] {21239, 67058, 19, 89, 639}, 26, -1),
		RAPID_HEAL(new int[] {21240, 67059, 22, 90, 640}, 18, -1),
		PROTECT_ITEM(new int[] {21241, 67060, 25, 91, 641}, 18, -1),
		HAWK_EYE(new int[] {77104, 67061, 26, 702, 642}, 6, RANGE_PRAYER | ATTACK_PRAYER | STRENGTH_PRAYER),
		MYSTIC_LORE(new int[] {77106, 67062, 27, 703, 643}, 6, MAGIC_PRAYER | ATTACK_PRAYER | STRENGTH_PRAYER),
		STEEL_SKIN(new int[] {21242, 67063, 28, 92, 644}, 3, DEFENCE_PRAYER),
		ULTIMATE_STRENGTH(new int[] {21243, 67064, 31, 93, 645}, 3, STRENGTH_PRAYER),
		INCREDIBLE_REFLEXES(new int[] {21244, 67065, 34, 94, 646}, 3, ATTACK_PRAYER),
		PROTECT_FROM_MAGIC(new int[] {21245, 67066, 37, 95, 647, 2}, 3, OVERHEAD_PRAYER),
		PROTECT_FROM_MISSILES(new int[] {21246, 67067, 40, 96, 648, 1}, 3, OVERHEAD_PRAYER),
		PROTECT_FROM_MELEE(new int[] {21247, 67068, 43, 97, 649, 0}, 3, OVERHEAD_PRAYER),
		EAGLE_EYE(new int[] {77109, 67069, 44, 704, 650}, 3, RANGE_PRAYER | ATTACK_PRAYER | STRENGTH_PRAYER),
		MYSTIC_MIGHT(new int[] {77111, 67070, 45, 705, 651}, 3, MAGIC_PRAYER | ATTACK_PRAYER | STRENGTH_PRAYER),
		RETRIBUTION(new int[] {2171, 67071, 46, 98, 652, 3}, 1, OVERHEAD_PRAYER),
		REDEMPTION(new int[] {2172, 67072, 49, 99, 653, 5}, 2, OVERHEAD_PRAYER),
		SMITE(new int[] {2173, 67073, 52, 100, 654, 4}, 2, OVERHEAD_PRAYER),
		CHIVALRY(new int[] {77113, 67074, 60, 706, 655}, 2, ATTACK_PRAYER | STRENGTH_PRAYER | DEFENCE_PRAYER),
		PIETY(new int[] {77115, 67075, 70, 707, 656}, 1, ATTACK_PRAYER | STRENGTH_PRAYER | DEFENCE_PRAYER),
		PROTECT_ITEM_(new int[] {87231, 67050, 50, 724, 631, 12567, 2213}, 18, -1),
		SAP_WARRIOR(new int[] {87233, 67051, 50, 725, 632}, 3, SAP_PRAYER),
		SAP_RANGER(new int[] {87235, 67052, 52, 726, 633}, 3, SAP_PRAYER),
		SAP_MAGE(new int[] {87237, 67053, 54, 727, 634}, 3, SAP_PRAYER),
		SAP_SPIRIT(new int[] {87239, 67054, 56, 728, 635}, 3, SAP_PRAYER),
		BERSERKER(new int[] {87241, 67055, 59, 729, 636, 12589, 2266}, 18, -1),
		DEFLECT_SUMMON(new int[] {87243, 67056, 62, 730, 637}, 100, OVERHEAD_PRAYER),
		DEFLECT_MAGIC(new int[] {87245, 67057, 65, 731, 638, 10}, 3, OVERHEAD_PRAYER),
		DEFLECT_RANGE(new int[] {87247, 67058, 68, 732, 639, 11}, 3, OVERHEAD_PRAYER),
		DEFLECT_MELEE(new int[] {87249, 67059, 71, 733, 640, 9}, 3, OVERHEAD_PRAYER),
		LEECH_ATTACK(new int[] {87251, 67060, 74, 734, 641}, 3, LEECH_PRAYER),
		LEECH_RANGE(new int[] {87253, 67061, 76, 735, 642}, 3, LEECH_PRAYER),
		LEECH_MAGE(new int[] {87255, 67062, 78, 736, 643}, 3, LEECH_PRAYER),
		LEECH_DEFENCE(new int[] {88001, 67063, 80, 737, 644}, 3, LEECH_PRAYER),
		LEECH_STRENGTH(new int[] {88003, 67064, 82, 738, 645}, 3, LEECH_PRAYER),
		LEECH_ENERGY(new int[] {88005, 67065, 84, 739, 646}, 3, LEECH_PRAYER),
		LEECH_SPECIAL(new int[] {88007, 67066, 86, 740, 647}, 3, LEECH_PRAYER),
		WRATH(new int[] {88009, 67067, 89, 741, 648, 16}, 2, OVERHEAD_PRAYER),
		SOUL_SPLIT(new int[] {88011, 67068, 92, 742, 649, 17}, 2, OVERHEAD_PRAYER),
		TURMOIL(new int[] {88013, 67069, 95, 743, 650, 12565, 2226}, 2, SAP_PRAYER | LEECH_PRAYER);

		public static Prayer forId(int prayer) {
			return prayers.get(prayer);
		}

		public static Prayer quickId(int prayer) {
			return quickPrayers.get(prayer);
		}

		public static Prayer forName(String name) {
			return Prayer.valueOf(name.replace(" ", "_").toUpperCase());
		}

		private static Map<Integer, Prayer> prayers = new HashMap<Integer, Prayer>();

		private static Map<Integer, Prayer> quickPrayers = new HashMap<Integer, Prayer>();
		
		static {
			for(Prayer prayer : Prayer.values()) {
				prayers.put(prayer.id, prayer);
				quickPrayers.put(prayer.quickId, prayer);
			}
		}

		private Prayer(int[] data, double drain, int prayerMask) {
			this.id = data[0];
			this.quickId = data[1];
			this.level = data[2];
			this.config = data[3];
			this.quickConfig = data[4] - 1;
			this.icon = data.length == 6 ? data[5] : -1;
			this.anim = data.length == 7 ? data[5] : -1;
			this.graphic = data.length == 7 ? data[6] : -1;
			this.drain = drain * 2;
			this.name = Misc.formatPlayerName(this.toString());
			this.prayMask = prayerMask;
			this.curse = curse;
		}

		private int id;

		private int quickId;

		private String name;
		
		private double drain;
		
		private int anim;
		
		private int graphic;
		
		private int level;

		private int config;

		private int quickConfig;

		private int icon;
		
		private int prayMask;
		
		private boolean curse;

		public int getPrayerId() {
			return id;
		}

		public int getQuickPrayerId() {
			return quickId;
		}

		public String getName() {
			return name;
		}
		
		public double getDrain() {
			return drain;
		}
		
		public int getAnim() {
			return anim;
		}
		
		public int getGraphic() {
			return graphic;
		}
		
		public int getLevelRequired() {
			return level;
		}

		public int getClientConfiguration() {
			return config;
		}

		public int getQuickConfiguration() {
			return quickConfig;
		}
		
		public int getHeadIcon() {
			return icon;
		}
		
		public int getMask() {
			return prayMask;
		}
		
		public boolean isCurse() {
			return id >= 21357;
		}
	}
	
	/**
	 * Toggle a prayer, setting the headicon and checking level if turning on
	 * @param prayer The prayer to toggle
	 */
	public static void togglePrayer(Client client, Prayer prayer, boolean quickPrayer) {
		if(quickPrayer)
			client.sendMessage(prayer.getName());
		if (client.get(prayer, quickPrayer)) {
			client.rem(prayer, quickPrayer);
			client.getPA().sendFrame36(quickPrayer ? prayer.getQuickConfiguration() : prayer.getClientConfiguration(), 0);
			if(client.headIcon == prayer.getHeadIcon() && !quickPrayer) {
				client.headIcon = -1;
			}
			client.getPA().requestUpdates();
		} else {
			boolean failed = false;
			if(!quickPrayer) {
				if(client.playerLevel[5] < 1) {
					client.sendMessage("You have no prayer left!");
				} else if(client.duelRule[7]) {
					client.sendMessage("Prayer has been disabled in this duel!");
					failed = true;
				} else if (client.playerLevel[1] < 30) {
					client.sendMessage("You need 30 Defence to use this prayer.");  
					failed = true;
				} else if (client.inBarbDef) {
					client.sendMessage("Think your funny dickhead?!");
					failed = true;
				}
				if(prayer == Prayer.PROTECT_ITEM || prayer == Prayer.PROTECT_ITEM_) {
					client.lastProtItem = System.currentTimeMillis();
				} else if(prayer == Prayer.DEFLECT_MAGIC || prayer == Prayer.DEFLECT_RANGE || prayer == Prayer.DEFLECT_MELEE || prayer == Prayer.PROTECT_FROM_MAGIC || prayer == Prayer.PROTECT_FROM_MISSILES || prayer == Prayer.PROTECT_FROM_MELEE) {
					if(System.currentTimeMillis() - client.stopPrayerDelay < 5000) {
						client.sendMessage("You have been injured and can't use this prayer!");
						failed = true;
					}
					if(prayer == Prayer.DEFLECT_MAGIC || prayer == Prayer.PROTECT_FROM_MAGIC) {
						client.protMageDelay = System.currentTimeMillis();
					} else if(prayer == Prayer.DEFLECT_RANGE || prayer == Prayer.PROTECT_FROM_MISSILES) {
						client.protRangeDelay = System.currentTimeMillis();
					} else if(prayer == Prayer.DEFLECT_MELEE || prayer == Prayer.PROTECT_FROM_MELEE) {
						client.protMeleeDelay = System.currentTimeMillis();
					}
				}
			}
			if(client.getLevelForXP(client.playerXP[5]) < prayer.getLevelRequired()) {
				client.getPA().sendFrame126("You need a @blu@Prayer level of " + prayer.getLevelRequired() + " to use " + prayer.getName() + ".", 357);
				client.getPA().sendFrame164(356);
				failed = true;
			}
			if(failed || client.isOnCurses() && !prayer.isCurse()) {
				client.getPA().sendFrame36(quickPrayer ? prayer.getQuickConfiguration() : prayer.getClientConfiguration(), 0);
				return;
			}
			if(prayer.getAnim() != -1 && !quickPrayer) {
				client.startAnimation(prayer.getAnim());
				client.gfx0(prayer.getGraphic());
			}
			client.add(prayer, quickPrayer);
			client.getPA().sendFrame36(quickPrayer ? prayer.getQuickConfiguration() : prayer.getClientConfiguration(), 1);
			checkExtraPrayers(client, prayer, quickPrayer);
			if(client.headIcon == -1 && !quickPrayer) {
				client.headIcon = prayer.getHeadIcon();
			}
			client.getPA().requestUpdates();
		}
	}

	/**
	 * Check for the extra prayers on, such as turning on Piety turns off all
	 * other strength boosting
	 * 
	 * @param prayer
	 *            The prayer toggled
	 */
	public static void checkExtraPrayers(Client client, Prayer prayer, boolean quickPrayer) {
		if (prayer.getMask() == -1) {
			return;
		}
		boolean overheadPrayer = (prayer.getMask() & OVERHEAD_PRAYER) != 0;
		boolean attackPrayer = (prayer.getMask() & ATTACK_PRAYER) != 0;
		boolean strengthPrayer = (prayer.getMask() & STRENGTH_PRAYER) != 0;
		boolean defencePrayer = (prayer.getMask() & DEFENCE_PRAYER) != 0;
		boolean rangePrayer = (prayer.getMask() & RANGE_PRAYER) != 0;
		boolean magicPrayer = (prayer.getMask() & MAGIC_PRAYER) != 0;
		boolean sapPrayer = (prayer.getMask() & SAP_PRAYER) != 0;
		boolean leechPrayer = (prayer.getMask() & LEECH_PRAYER) != 0;
		for (Prayer p : Prayer.values()) {
			if (!client.get(p, quickPrayer) || p == prayer) {
				continue;
			}
			if (p.getMask() == -1)
				continue;
			if((p.getMask() & OVERHEAD_PRAYER) != 0 && overheadPrayer)
				togglePrayer(client, p, quickPrayer);
			if(client.isOnCurses() && p.isCurse()) {
				if ((p.getMask() & SAP_PRAYER) != 0 && leechPrayer
						|| (p.getMask() & LEECH_PRAYER) != 0 && sapPrayer) {
					togglePrayer(client, p, quickPrayer);
				}
			} else if(!client.isOnCurses() && !p.isCurse()) {
				if ((p.getMask() & ATTACK_PRAYER) != 0 && attackPrayer
						|| (p.getMask() & STRENGTH_PRAYER) != 0 && strengthPrayer
						|| (p.getMask() & DEFENCE_PRAYER) != 0 && defencePrayer
						|| (p.getMask() & RANGE_PRAYER) != 0 && rangePrayer
						|| (p.getMask() & MAGIC_PRAYER) != 0 && magicPrayer) {
					togglePrayer(client, p, quickPrayer);
				}
			}
		}
	}
	
	/**
	 * Clears/Resets the prayer configurations.
	 */
	public static void refreshPrayers(Client client, boolean quickPrayers) {
		for (Prayer p : Prayer.values()) {
			client.getPA().sendFrame36(quickPrayers ? p.getQuickConfiguration() : p.getClientConfiguration(), client.get(p, quickPrayers) ? 1 : 0);
		}
	}
	
	/**
	 * Get the prayer for the specified mask
	 * @param mask The mask
	 * @return The prayer or null
	 */
	public static Prayer[] get(Client client, int mask) {
		Prayer[] tempPrayerArray = new Prayer[7];
		int index = 0;
		for (Prayer prayer : Prayer.values()) {
			if (client.get(prayer, false)) {
				if ((prayer.getMask() & mask) != 0) {
					tempPrayerArray[index++] = prayer;
				}
			}
		}
		return tempPrayerArray;
	}
}