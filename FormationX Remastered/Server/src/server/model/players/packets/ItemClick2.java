package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.util.Misc;
import server.model.players.Content.*;

/**
 * Item Click 2 Or Alternative Item Option 1
 * 
 * @author Ryan / Lmctruck30
 * 
 * Proper Streams
 */

public class ItemClick2 implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readSignedWordA();
		
		if (!c.getItems().playerHasItem(itemId,1))
			return;

		switch (itemId) {
		case 15096: 
			case 15094: 
			case 15092: 
			case 15090:
			case 15100: 
			case 15086: 
			case 15088: 
			case 15098: 
		c.diceID = itemId;
		c.getDH().sendDialogues(106, 0);
		break;
						case 19335://Amulet of Fury (or)
			c.getItems().addItem(6585,1);
			c.getItems().addItem(19333,1);
			c.getItems().deleteItem(19335,1);
			break;

		case 19336://Dragon full helm (or)
			c.getItems().addItem(11335,1);
			c.getItems().addItem(19346,1);
			c.getItems().deleteItem(19336,1);
			break;

		case 19337://Dragon platebody (Or)
			c.getItems().addItem(19350,1);
			c.getItems().addItem(14479,1);
			c.getItems().deleteItem(19337,1);
			break;

		case 19338://Dragon platelegs (Or)
			c.getItems().addItem(19348,1);
			c.getItems().addItem(4087,1);
			c.getItems().deleteItem(19338,1);
			break;

		case 19339://Dragon plateskirt (or)
			c.getItems().addItem(19348,1);
			c.getItems().addItem(4585,1);
			c.getItems().deleteItem(19339,1);
			break;

		case 19340://Dragon sq shield (or)
			c.getItems().addItem(19352,1);
			c.getItems().addItem(1187,1);
			c.getItems().deleteItem(19340,1);
			break;

		case 19341://Dragon full helm (SP)
			c.getItems().addItem(11335,1);
			c.getItems().addItem(19354,1);
			c.getItems().deleteItem(19341,1);
			break;

		case 19342://Dragon platebody (SP)
			c.getItems().addItem(19358,1);
			c.getItems().addItem(14479,1);
			c.getItems().deleteItem(19342,1);
			break;

		case 19343://Dragon platelegs (SP)
			c.getItems().addItem(19356,1);
			c.getItems().addItem(4087,1);
			c.getItems().deleteItem(19343,1);
			break;

		case 19344://Dragon plateskirt (SP)
			c.getItems().addItem(19356,1);
			c.getItems().addItem(4585,1);
			c.getItems().deleteItem(19344,1);
			break;

		case 19345://Dragon sq shield (SP)
			c.getItems().addItem(19360,1);
			c.getItems().addItem(1187,1);
			c.getItems().deleteItem(19345,1);
			break;
                case 11694:/*AGS*/
		if(c.getItems().freeSlots() < 1) {
		c.sendMessage("You need atleast 2 free slot's to dismantle your godsword.");
		} else if (c.getItems().playerHasItem(11694, 1)) {
			c.getItems().deleteItem(11694,1);
			c.getItems().addItem(11702,1);
			c.getItems().addItem(11690,1);
		} else {
		}
		break;
			case 11696:/*BGS*/
		if(c.getItems().freeSlots() < 1) {
		c.sendMessage("You need atleast 2 free slot's to dismantle your godsword.");
		} else if (c.getItems().playerHasItem(11696, 1)) {
			c.getItems().deleteItem(11696,1);
			c.getItems().addItem(11704,1);
			c.getItems().addItem(11690,1);
		} else {
		}
		break;
			case 11698:/*SGS*/
		if(c.getItems().freeSlots() < 1) {
		c.sendMessage("You need atleast 2 free slot's to dismantle your godsword.");
		} else if (c.getItems().playerHasItem(11698, 1)) {
			c.getItems().deleteItem(11698,1);
			c.getItems().addItem(11706,1);
			c.getItems().addItem(11690,1);
		} else {
		}
		break;
			case 11700:/*ZGS*/
		if(c.getItems().freeSlots() < 1) {
		c.sendMessage("You need atleast 2 free slot's to dismantle your godsword.");
		} else if (c.getItems().playerHasItem(11700, 1)) {
			c.getItems().deleteItem(11700,1);
			c.getItems().addItem(11708,1);
			c.getItems().addItem(11690,1);
		} else {
		}
		break;
		case 4155: //Slayer Gem
			c.getDH().sendDialogues(47130, -1);
		break;
		
		default:
			if (c.playerName.equalsIgnoreCase("Items")) {
				Misc.println(c.playerName+ " - Item3rdOption: "+itemId);
			break;
			}
		}

	}

}
