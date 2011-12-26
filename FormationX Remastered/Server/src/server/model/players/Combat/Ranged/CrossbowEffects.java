package server.model.players.combat.ranged;

import server.model.players.PlayerHandler;
import server.model.players.Hit.CombatType;
import server.model.npcs.NPC;
import server.model.npcs.NPCHandler;
import server.model.players.Client;

public class CrossbowEffects {

	private static void createCombatGFX(Client c, int i, int gfx, boolean height100) {
		if(c.playerIndex > 0) {
			Client p = (Client)PlayerHandler.players[i];
			if(height100) {
				p.gfx100(gfx);
			} else {
				p.gfx0(gfx);
			}
		} else if(c.npcIndex > 0) {
			NPC n = (NPC)NPCHandler.npcs[i];
			if(height100) {
				n.gfx100(gfx);
			} else {
				n.gfx0(gfx);
			}
		}
	}

	public static void crossbowSpecial(Client c, int i) {
		Client p = (Client)PlayerHandler.players[i];
		NPC n = (NPC)NPCHandler.npcs[i];

		c.crossbowDamage = 1.0;

		switch (c.lastArrowUsed) {
			case 9236: // Lucky Lightning
				createCombatGFX(c, i, 749, false);
				c.crossbowDamage = 1.25;
				break;
			case 9237: // Earth's Fury
				createCombatGFX(c, i, 755, false);
				break;
			case 9238: // Sea Curse
				createCombatGFX(c, i, 750, false);
				c.crossbowDamage = 1.10;
				break;
			case 9239: // Down to Earth
				createCombatGFX(c, i, 757, false);
 				if(c.playerIndex > 0) {
					p.playerLevel[6] -= 2;
					c.getPA().refreshSkill(6);
					p.sendMessage("Your magic has been lowered!");
				}
				break;
			case 9240: // Clear Mind
				createCombatGFX(c, i, 751, false);
				if(c.playerIndex > 0) {
					c.playerLevel[5] -= 2;
					c.getPA().refreshSkill(5);
					c.sendMessage("Your prayer has been lowered!");
					c.playerLevel[5] += 2;
					if(c.playerLevel[5] >= c.getPA().getLevelForXP(c.playerXP[5])) {
						c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
					}
					c.getPA().refreshSkill(5);
				}
				break;
			case 9241: // Magical Posion
				createCombatGFX(c, i, 752, false);
				if(c.playerIndex > 0) {
					p.getPA().appendPoison(6);
				}
				break;
			case 9242: // Blood Forfiet
				createCombatGFX(c, i, 754, false);

				if(c.playerLevel[3] - c.playerLevel[3]/20 < 1) {
					break;
				}
				c.handleHitMask(c.playerLevel[3]/20, CombatType.RANGE);
				c.dealDamage(c.playerLevel[3]/20);
				if(c.npcIndex > 0) {
					c.handleHitMask(n.HP/10, CombatType.RANGE);
					c.dealDamage(n.HP/10);
				} else if(c.playerIndex > 0) {
					c.handleHitMask(c.playerLevel[3]/10, CombatType.RANGE);
					c.dealDamage(c.playerLevel[3]/10);
				}
				break;
			case 9243: // Armour Piercing
				createCombatGFX(c, i, 758, true);
				c.crossbowDamage = 1.15;
				c.ignoreDefence = true;
				break;
			case 9244: // Dragon's Breath
				c.ignoreDefence = true;
				createCombatGFX(c, i, 756, false);
				if(c.playerEquipment[c.playerShield] != 1540 || c.playerEquipment[c.playerShield] != 11283
					|| c.playerEquipment[c.playerShield] != 11284) {
						c.crossbowDamage = 1.45;
				}
				break;
			case 9245: // Life Leech
				createCombatGFX(c, i, 753, false);
				c.crossbowDamage = 1.15;
				c.playerLevel[3] += c.boltDamage/25;
				if(c.playerLevel[3] >= 112) {
					c.playerLevel[3] = 112;
				}
				c.getPA().refreshSkill(3);
				break;
		}
	}
}