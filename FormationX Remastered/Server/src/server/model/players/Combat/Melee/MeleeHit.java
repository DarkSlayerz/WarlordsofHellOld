package server.model.players.combat.melee;

import server.model.players.Client;
import server.util.Misc;

public class MeleeHit extends MeleeData {

	public static int calculateMeleeAttack(Client c) {
		int attackLevel = c.playerLevel[0];
		//2, 5, 11, 18, 19
		if (c.slayerHelmetEffect && c.slayerTask != 0)
			attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.15;
        if (c.prayerActive[2]) {
            attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.05;
        } else if (c.prayerActive[7]) {
            attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.1;
        } else if (c.prayerActive[15]) {
            attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.15;
        } else if (c.prayerActive[24]) {
            attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.20;
        } else if (c.prayerActive[25]) {
            attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.32;
	} else if (c.curseActive[19]) {
            attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 1.0;
        }
        if (c.fullVoidMelee())
            attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.1;
		attackLevel *= c.specAccuracy;
		//c.sendMessage("Attack: " + (attackLevel + (c.playerBonus[bestMeleeAtk()] * 2)));
        int i = c.playerBonus[bestMeleeAtk(c)];
		i += c.bonusAttack;
		if (c.playerEquipment[c.playerAmulet] == 11128 && c.playerEquipment[c.playerWeapon] == 6528) {
			i *= 1.30;
		}
		return (int)(attackLevel + (attackLevel * 0.15) + (i + i * 0.05));
	}
	public static int bestMeleeAtk(Client c)
    {
        if(c.playerBonus[0] > c.playerBonus[1] && c.playerBonus[0] > c.playerBonus[2])
            return 0;
        if(c.playerBonus[1] > c.playerBonus[0] && c.playerBonus[1] > c.playerBonus[2])
            return 1;
        return c.playerBonus[2] <= c.playerBonus[1] || c.playerBonus[2] <= c.playerBonus[0] ? 0 : 2;
    }
	
	public static int calculateMeleeMaxHit(Client c) {
		double maxHit = 0;
		int strBonus = c.playerBonus[10];
		int strength = c.playerLevel[2];
		int lvlForXP = c.getLevelForXP(c.playerXP[2]);
		if (c.slayerHelmetEffect && c.slayerTask != 0)
			maxHit = (int)(maxHit * 1.15);
		if(c.prayerActive[1]) {
			strength += (int)(lvlForXP * .05);
		} else
		if(c.prayerActive[6]) {
			strength += (int)(lvlForXP * .10);
		} else
		if(c.prayerActive[14]) {
			strength += (int)(lvlForXP * .15);
		} else
		if(c.prayerActive[24]) {
			strength += (int)(lvlForXP * .18);
		} else
		if(c.prayerActive[25]) {
			strength += (int)(lvlForXP * .23);
		} else
		if(c.curseActive[19]) {
			strength += (int)(lvlForXP * .25);
		}
				if(c.curseActive[10]) { // Leech Attack
			strength += (int)(lvlForXP * .10 + c.getstr);
		}	
		if(c.curseActive[13]) { // Leech Defense
			strength += (int)(lvlForXP * .10 + c.getstr);
		}	
		if(c.curseActive[14]) { // Leech Strength
			strength += (int)(lvlForXP * .10 + c.getstr);
		}	
		if(c.curseActive[11]) { // Leech Ranged
			strength += (int)(lvlForXP * .10 + c.getstr);
		}	
		if(c.curseActive[12]) { // Leech Magic
			strength += (int)(lvlForXP * .10 + c.getstr);
		}	
		if(c.curseActive[16]) { // Leech Special
			strength += (int)(lvlForXP * .10 + c.getstr);
		}
		if(c.playerEquipment[c.playerHat] == 2526 && c.playerEquipment[c.playerChest] == 2520 && c.playerEquipment[c.playerLegs] == 2522) {	
			maxHit += (maxHit * 10 / 100);
		}
		maxHit += 1.05D + (double)(strBonus * strength) * 0.00175D;
		maxHit += (double)strength * 0.11D;
		if(c.playerEquipment[c.playerWeapon] == 4718 && c.playerEquipment[c.playerHat] == 4716 && c.playerEquipment[c.playerChest] == 4720 && c.playerEquipment[c.playerLegs] == 4722) {	
				maxHit += (c.getPA().getLevelForXP(c.playerXP[3]) - c.playerLevel[3]) / 2;			
		}
		if (c.specDamage > 1)
			maxHit = (int)(maxHit * c.specDamage);
		if (maxHit < 0)
			maxHit = 1;
		if (c.fullVoidMelee())
			maxHit = (int)(maxHit * 1.10);
		if (c.playerEquipment[c.playerAmulet] == 11128 && c.playerEquipment[c.playerWeapon] == 6528) {
			maxHit *= 1.20;
		}
		int MeleeDamage = (int) Math.floor(maxHit);
		CalculateCriticalChance(c);
		if (CalculateCriticalChance(c)) {
			return CalculateCriticalDamage(c, MeleeDamage, "Melee");
		} else
			return (int) Math.floor(maxHit);
	}
	/*
	 * CalculateCriticalChance
	 *  Method Created by Mr. Chris of rune-server
	 */
public static boolean CalculateCriticalChance(Client c) {
		if (c.usingSpecial)
			return false;
		int CriticalStrikeChance = Misc.random(100);
		if (CriticalStrikeChance > 99)
			return true;
		else
			return false;
	}


	/*
	 * CalculateCriticalDamage Method
	 *  Created by Mr. Chris of rune-server
	 */
	public static int CalculateCriticalDamage(Client c, int Damage, String type) {
		return Damage += Damage;
	}

	/*
	 * sendCriticalStrikeGloating Method
	 *  Created by Mr. Chris of Rune-Server
	 */

	/*public static void sendCriticalStrikeGloating(Client c, String CombatStyle) {
		if (CombatStyle == "Ranged") {
			c.forcedText = "BOOM HEADSHOT!!";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
		}

		if (CombatStyle == "Melee") {
			c.forcedText = "FALCO PAWNCH!!";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
		}
	}
	*/

	public static int calculateMeleeDefence(Client c)
    {
        int defenceLevel = c.playerLevel[1];
		int i = c.playerBonus[bestMeleeDef(c)];
        if (c.prayerActive[0]) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.05;
        } else if (c.prayerActive[5]) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.1;
        } else if (c.prayerActive[13]) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.15;
        } else if (c.prayerActive[24]) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.2;
        } else if (c.prayerActive[25]) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.25;
        }
        return (int)(defenceLevel + (defenceLevel * 0.15) + (i + i * 0.05));
    }
	
	public static int bestMeleeDef(Client c)
    {
        if(c.playerBonus[5] > c.playerBonus[6] && c.playerBonus[5] > c.playerBonus[7])
            return 5;
        if(c.playerBonus[6] > c.playerBonus[5] && c.playerBonus[6] > c.playerBonus[7])
            return 6;
        return c.playerBonus[7] <= c.playerBonus[5] || c.playerBonus[7] <= c.playerBonus[6] ? 5 : 7;
    }

}