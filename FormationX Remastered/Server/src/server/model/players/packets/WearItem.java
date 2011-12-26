package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.Config;
import java.io.*;
import server.util.*;
import server.model.npcs.*;
import server.*;
import server.model.items.*;


/**
 * Wear Item
 **/
public class WearItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		c.wearId = c.getInStream().readUnsignedWord();
		c.wearSlot = c.getInStream().readUnsignedWordA();
		boolean torvaChanged = false;
		c.interfaceId = c.getInStream().readUnsignedWordA();

		if (c.autoRet == 1) {
			c.autoRet = 0;
			c.getCombat().resetPlayerAttack();
			c.simpleFix = 1;
		}
			
	if(!c.getItems().playerHasItem(c.wearId, 1, c.wearSlot)) {
			return;
		}
		int oldCombatTimer = c.attackTimer;
		if (c.playerIndex > 0 || c.npcIndex > 0)
			c.getCombat().resetPlayerAttack();
		if (c.wearId == 5509) {
			c.getPA().removeSmallPouch();
			return;
		}
		if (c.wearId == 5510) {
			c.getPA().removeMediumPouch();
			return;
		}
		if (c.wearId == 5511) {
			c.getPA().removeMediumPouch();
			return;
		}
		if (c.wearId == 5512) {
			c.getPA().removeLargePouch();
			return;
		}
		if (c.wearId == 5513) {
			c.getPA().removeLargePouch();
			return;
		}
		if (c.wearId == 5514) {
			c.getPA().removeGiantPouch();
			return;
		}
		if (c.wearId == 5515) {
			c.getPA().removeGiantPouch();
			return;
		}
			//c.attackTimer = oldCombatTimer;
		//c.getItems().wearItem(c.wearId, c.wearSlot, c.getItems().getItemName(c.wearId).toLowerCase());
		if (c.wearSlot == 0 || c.wearSlot == 4 || c.wearSlot == 7) {
			if (c.playerEquipment[c.wearSlot] == 13362 || c.playerEquipment[c.wearSlot] == 13358 || c.playerEquipment[c.wearSlot] == 13360) //Torva
			if (c.playerEquipment[c.wearSlot] == 13355 || c.playerEquipment[c.wearSlot] == 13354 || c.playerEquipment[c.wearSlot] == 13352) //Pernix
			if (c.playerEquipment[c.wearSlot] == 13350 || c.playerEquipment[c.wearSlot] == 13348 || c.playerEquipment[c.wearSlot] == 13346) //Virtus
				torvaChanged = true;
		}
		c.getItems().wearItem(c.wearId, c.wearSlot);
		if (torvaChanged && c.playerLevel[3] > c.calculateMaxLifePoints()) {
			c.playerLevel[3] = c.calculateMaxLifePoints();
			c.getPA().refreshSkill(3);
		}


if(c.playerRights == 3)
{
//c.sendMessage(" ID: "+c.wearId+" SLOT: "+c.wearSlot+" "+c.getItems().getItemName(c.wearId).toLowerCase()+"");
}
	}

}
