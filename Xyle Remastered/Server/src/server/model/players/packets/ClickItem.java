package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;
import server.model.players.Player;
import server.util.Misc;
import server.world.WorldMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import server.event.EventManager;
import server.event.Event;
import server.event.EventContainer;
import server.model.players.*;
import server.model.players.PacketType;


/**
 * Clicking an item, bury bone, eat food etc
 **/
public class ClickItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int junk = c.getInStream().readSignedWordBigEndianA();
		int itemSlot = c.getInStream().readUnsignedWordA();
		int itemId = c.getInStream().readUnsignedWordBigEndian();
		if (itemId != c.playerItems[itemSlot] - 1) {
			return;
		}
				
		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			int a = itemId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().fillPouch(pouch);
			return;
		}
		if (c.getHerblore().isUnidHerb(itemId))
			c.getHerblore().handleHerbClick(itemId);
		if (c.getFood().isFood(itemId))
			c.getFood().eat(itemId,itemSlot);
		//ScriptManager.callFunc("itemClick_"+itemId, c, itemId, itemSlot);
		if (c.getPotions().isPotion(itemId))
			c.getPotions().handlePotion(itemId,itemSlot);
		if (c.getPrayer().isBone(itemId))
			c.getPrayer().buryBone(itemId, itemSlot);

 		if (itemId == 15098 && c.playerRights >= 1 && c.diceTimer < 1) {
    			int random = Misc.random(100);
			Server.clanChat.messageToClan("@blu@" + c.playerName + " has rolled a @red@" + random + " @blu@using the percentile dice.", c.clanId);
			c.startAnimation(11900);
    			c.gfx0(2075);
			c.diceTimer = 20;
  		}	
		  //START TAX BAGS
        if(itemId == 10832) {
        c.getItems().deleteItem(10832, 1);
        c.getItems().addItem(995,500000000);
        c.sendMessage("<col=1532693>You open the bag and receive 500 Million Coins!</col>");
            
        }
        if(itemId == 10833) {
        c.getItems().deleteItem(10833, 1);
        c.getItems().addItem(995,1000000000);
        c.sendMessage("<col=1532693>You open the bag and receive 1 Billion Coins!</col>");
            
        }
        if(itemId == 10834) {
        c.getItems().deleteItem(10834, 1);
        c.getItems().addItem(995,2000000000);
        c.sendMessage("<col=1532693>You open the bag and receive 2 Billion Coins!</col>");            
        }
		if (itemId == 952) {
			if(c.inArea(3553, 3301, 3561, 3294)) {
				c.teleTimer = 3;
				c.newLocation = 1;
			} else if(c.inArea(3550, 3287, 3557, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 2;
			} else if(c.inArea(3561, 3292, 3568, 3285)) {
				c.teleTimer = 3;
				c.newLocation = 3;
			} else if(c.inArea(3570, 3302, 3579, 3293)) {
				c.teleTimer = 3;
				c.newLocation = 4;
			} else if(c.inArea(3571, 3285, 3582, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 5;
			} else if(c.inArea(3562, 3279, 3569, 3273)) {
				c.teleTimer = 3;
				c.newLocation = 6;
			}
		}
	}

}
