package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
/**
 * Bank 10 Items
 **/
public class Bank10 implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
	int interfaceId = c.getInStream().readUnsignedWordBigEndian();
	int removeId = c.getInStream().readUnsignedWordA();
	int removeSlot = c.getInStream().readUnsignedWordA();
					
		switch(interfaceId){
                    			case 7423:
			if(c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
			}
			c.getItems().bankItem(removeId, removeSlot, 10);
			c.getItems().resetItems(7423);
			break;
			case 1688:
			if(c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
			}
			c.getPA().useOperate(removeId);
			break;
			case 3900:
			if(c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
			}
			c.getShops().buyItem(removeId, removeSlot, 5);
			break;
			
			case 3823:
			if(c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
			}
			c.getShops().sellItem(removeId, removeSlot, 5);
			break;

			case 5064:
			if(c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
			}
			c.getItems().bankItem(removeId, removeSlot, 10);
			break;
			
			case 5382:
			if(c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
			}
			c.getItems().fromBank(removeId, removeSlot, 10);
			break;
			
			case 3322:
			if(c.duelStatus <= 0) { 
                c.getTradeAndDuel().tradeItem(removeId, removeSlot, 10);
           	} else {
				c.getTradeAndDuel().stakeItem(removeId, removeSlot, 10);
			}	
			break;
			
			case 3415:
			if(c.duelStatus <= 0) { 
				c.getTradeAndDuel().fromTrade(removeId, removeSlot, 10);
           	} 
			break;
			
			case 6669:
			c.getTradeAndDuel().fromDuel(removeId, removeSlot, 10);
			break;
			
			case 1119:
			case 1120:
			case 1121:
			case 1122:
			case 1123:
				c.getSmithing().readInput(c.playerLevel[c.playerSmithing], Integer.toString(removeId), c, 5);
			break;
		}	
	}

}
