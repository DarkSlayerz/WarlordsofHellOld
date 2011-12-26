package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.util.Misc;

/**
 * Item Click 3 Or Alternative Item Option 1
 * @author Ryan / Lmctruck30
 */

public class ItemClick3 implements PacketType {

	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId11 = c.getInStream().readSignedWordBigEndianA();
		int itemId1 = c.getInStream().readUnsignedWordA();
		int itemId = c.getInStream().readSignedWordA();
		switch (itemId) {
		case 1712:
			c.getPA().handleGlory(itemId);
		break;
		default:
			c.getSummoning().summonFamiliar(itemId, false);
			if (c.playerRights == 3)
				Misc.println(c.playerName+ " - Item3rdOption: "+itemId+" : "+itemId11+" : "+itemId1);
			break;
		}
	}

}
