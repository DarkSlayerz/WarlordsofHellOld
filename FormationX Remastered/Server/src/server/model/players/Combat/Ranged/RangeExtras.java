package server.model.players.combat.ranged;

import server.model.npcs.NPC;
import server.model.npcs.NPCHandler;
import server.model.players.Client;

import server.util.Misc;

public class RangeExtras {
	
	public static void appendMutliChinchompa(Client c, int npcId) {
		if (NPCHandler.npcs[npcId] != null) {
			NPC n = (NPC)NPCHandler.npcs[npcId];
			if (n.isDead) {
				return;
			}
			int damage = Misc.random(c.getCombat().rangeMaxHit());
			if (NPCHandler.npcs[npcId].HP - damage < 0) { 
				damage = NPCHandler.npcs[npcId].HP;
			}
			NPCHandler.npcs[npcId].underAttackBy = c.playerId;
			NPCHandler.npcs[npcId].underAttack = true;
			NPCHandler.npcs[npcId].handleHitMask(damage);
			//NPCHandler.npcs[npcId].dealDamage(damage);
			NPCHandler.npcs[c.npcIndex].HP -= damage;
		}
	}

}
