package server.model.players;

import server.util.Misc;
import server.Config;
import server.Server;
import server.model.npcs.NPC;

public class Curses {
	private Client c;
	public Curses(Client client) {
		this.c = client;
	}
	
	public final int PROTECT_ITEM = 0, SAP_WARRIOR = 1, SAP_RANGER = 2, SAP_MAGE = 3, SAP_SPIRIT = 4, BERSERKER = 5, DEFLECT_SUMMONING = 6, DEFLECT_MAGIC = 7,
					DEFLECT_MISSILES = 8, DEFLECT_MELEE = 9, LEECH_ATTACK = 10, LEECH_RANGED = 11, LEECH_MAGIC = 12, LEECH_DEFENCE = 13, LEECH_STRENGTH = 14,
					LEECH_ENERGY = 15, LEECH_SPEC = 16, WRATH = 17, SOUL_SPLIT = 18, TURMOIL = 19;
					
	public double getTurmoilMultiplier(String stat) {
		NPC n = null;
		Client c2 = null;
		double otherLevel = 0;
		double turmoilMultiplier = stat.equalsIgnoreCase("Strength") ? 1.23 : 1.15;
		if (c.oldPlayerIndex > 0)
			c2 = (Client)Server.playerHandler.players[c.oldPlayerIndex];
		else if (c.oldNpcIndex > 0)
			n = Server.npcHandler.npcs[c.oldNpcIndex];
		if (stat.equalsIgnoreCase("Defence")) {
			if (c2 != null)
				otherLevel = c2.getLevelForXP(c2.playerXP[1]) * 0.15;
			else if (n != null)
				otherLevel = n.getCombatLevel() * 0.15;
			else
				otherLevel = 0;
		} else if (stat.equalsIgnoreCase("Strength")) {
			if (c2 != null)
				otherLevel = c2.getLevelForXP(c2.playerXP[2]) * 0.10;
			else if (n != null)
				otherLevel = n.getCombatLevel() * 0.10;
			else
				otherLevel = 0;
		} else if (stat.equalsIgnoreCase("Attack")) {
			if (c2 != null)
				otherLevel = c2.getLevelForXP(c2.playerXP[0]) * 0.15;
			else if (n != null)
				otherLevel = n.getCombatLevel() * 0.15;
			else
				otherLevel = 0;
		}
		if (otherLevel > 14)
			otherLevel = 14;
		turmoilMultiplier += otherLevel * .01;
		return turmoilMultiplier;
	}
	
	public void soulSplit(int id, int damage) {
		if(c.constitution < 1)
			return;
		if (c.curseActive(SOUL_SPLIT) && c.ssDelay <= 0) {
			if (c.oldPlayerIndex > 0) {
				c.ssTarget = (Client)Server.playerHandler.players[id];
				c.getPA().createPlayersProjectile(c.getX(), c.getY(), (c.getX() - c.ssTarget.getX()) * -1, (c.getY() - c.ssTarget.getY()) * -1, 50, 75, 2263, 25, 25, - id - 1, 0);
				c.ssTarget.playerLevel[5] -= (int)damage/50;
				c.ssTarget.getPA().refreshSkill(5);
			} else if (c.oldNpcIndex > 0) {
				c.ssTargetNPC = Server.npcHandler.npcs[id];
				c.getPA().createPlayersProjectile(c.getX(), c.getY(), (c.getX() - c.ssTargetNPC.getX()) * -1, (c.getY() - c.ssTargetNPC.getY()) * -1, 50, 75, 2263, 25, 25, id + 1, 0);
			}
			c.ssHeal = (int)damage/5;
			c.ssDelay = 5;
		}
	}
	
	public void appendRandomLeech(int id, int leechType) {
	}
	
	private void leechEffect(int leechType) {
	}
	
	private void leechEffectNPC(int leechType) {
	}
	
	public void deflect(Client c2, int damage, int damageType) {
		int[] gfx = {2230, 2229, 2228, 2228};
		if (Misc.random(3) == 0) {
			int deflect = (damage < 10) ? 1 : (int)(damage / 10);
			c.getCombat().appendHit(c2, deflect, 0, 3, false, 0);
			c.gfx0(gfx[damageType]);
			c.startAnimation(12573);
		}
	}
	
	public void deflectNPC(NPC n, int damage, int damageType) {
		int[] gfx = {2230, 2229, 2228, 2228};
		if (Misc.random(3) == 0) {
			int deflect = (damage < 10) ? 1 : (int)(damage/10);
			c.getCombat().appendHit(n, deflect, 0, 3, 2);
			c.gfx0(gfx[damageType]);
			c.startAnimation(12573);
		}
	}
	
	public void handleProcess() {
		if (c.ssDelay > 0) 
			c.ssDelay--;
		if (c.ssDelay == 3) {
			if (c.ssTarget != null) {
				c.getPA().createPlayersProjectile(c.ssTarget.getX(), c.ssTarget.getY(), (c.ssTarget.getY() - c.getY()) * -1, (c.ssTarget.getX() - c.getX()) * -1, 50, 75, 2263, 25, 25, - c.getId() - 1, 40);
				c.ssTarget.gfx0(2264);
			} else if (c.ssTargetNPC != null) {
				c.getPA().createPlayersProjectile(c.ssTargetNPC.getX(), c.ssTargetNPC.getY(), (c.ssTargetNPC.getY() - c.getY())* -1, (c.ssTargetNPC.getX() - c.getX())* -1, 50, 75, 2263, 25, 25, - c.getId() - 1, 40);
				c.ssTargetNPC.gfx0(2264);
			}
			if(c.constitution < c.maxConstitution)
				c.addToHp(c.ssHeal);
		}
		if (c.leechDelay > 0)
			c.leechDelay--;
		if (c.leechDelay == 5) {
			if (c.oldPlayerIndex > 0)
				appendRandomLeech(c.oldPlayerIndex, Misc.random(6));
			else if (c.oldNpcIndex > 0) 
				appendRandomLeech(c.oldNpcIndex, Misc.random(6));
		} else if (c.leechDelay == 3) {
			if (c.leechTarget != null) {
				c.leechTarget.gfx0(c.leechEndGFX);
				leechEffect(c.leechType);
			} else if (c.leechTargetNPC != null) {
				c.leechTargetNPC.gfx0(c.leechEndGFX);
				leechEffectNPC(c.leechType);
			}
		}
	}
}
