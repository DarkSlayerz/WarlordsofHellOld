package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.util.Misc;

/**
 * Item Click 3 Or Alternative Item Option 1
 * 
 * @author Ryan / Lmctruck30
 * 
 * Proper Streams
 */

public class ItemClick3 implements PacketType {

public void summon(Client c, int npcid)
{
if(c.lastsummon < 1)
{
c.Summoning().SummonNewNPC(npcid);
} else {
c.sendMessage("You already have a NPC summoned");
c.sendMessage("To dismiss it you need to click on the summoning Stat icon");
}
}

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId11 = c.getInStream().readSignedWordBigEndianA();
		int itemId1 = c.getInStream().readSignedWordA();
		int itemId = c.getInStream().readSignedWordA();
		if(!c.getItems().playerHasItem(itemId, 1)) {
			return;
		}
		
c.s = itemId;     
		switch (itemId) {

		case 1712:
			c.getPA().handleGlory(itemId);
			break;
			case 15096: 
			c.getItems().deleteItem(15096, 1);
			c.getItems().addItem(15084, 1);
			case 15094: 
			c.getItems().deleteItem(15094, 1);
			c.getItems().addItem(15084, 1);
			case 15092:
			c.getItems().deleteItem(15092, 1);
			c.getItems().addItem(15084, 1);
			case 15090: 
			c.getItems().deleteItem(15090, 1);
			c.getItems().addItem(15084, 1);
			case 15100:
			c.getItems().deleteItem(15100, 1);
			c.getItems().addItem(15084, 1);
			case 15086: 
			c.getItems().deleteItem(15086, 1);
			c.getItems().addItem(15084, 1);
			case 15088:
			c.getItems().deleteItem(15088, 1);
			c.getItems().addItem(15084, 1);
			case 15098:
			c.getItems().deleteItem(1598, 1);
			c.getItems().addItem(15084, 1);
			break;




case 12007:


summon(c, 6795);
break;
case 12009:

summon(c, 6797);
break;

case 12011:

summon(c, 6799);
break;

case 12013:

summon(c, 6801);
break;

case 12015:

summon(c, 6803);
break;

case 12017:

summon(c, 6805);
break;


case 12019:

summon(c, 6807);
break;


case 12021:

summon(c, 6808);
break;

case 12023:

summon(c, 6810);
break;

case 12025:

summon(c, 6812);
break;

case 12027:

summon(c, 6993);
break;

case 12029:

summon(c, 6814);
break;

case 12031:

summon(c, 6816);
break;


case 12033:

summon(c, 6817);
break;

case 12035:

summon(c, 6819);
break;


case 12037:

summon(c, 6821);
break;

case 12039:

summon(c, 6823);
break;

case 12041:

summon(c, 6824);
break;


case 12043:

summon(c, 6826);
break;

case 12045:

summon(c, 6828);
break;

case 12047:

summon(c, 6830);
break;

case 12049:

summon(c, 6832);
break;
		

case 12051:

summon(c, 6834);
break;	

case 12053:

summon(c, 6836);
break;

case 12055:

summon(c, 6838);
break;

case 12057:

summon(c, 6840);
break;

case 12059:

summon(c, 6842);
break;
	

case 12061:

summon(c, 6844);
break;


case 12063:

summon(c, 6995);
break;
case 12065:

summon(c, 6846);
break;

case 12067:

summon(c, 6848);
break;



case 12069:

summon(c, 6850);
break;


case 12071:

summon(c, 6852);
break;


case 12073:

summon(c, 6854);
break;

case 12075:

summon(c, 6856);
break;
case 12077:

summon(c, 6858);
break;
case 12079:

summon(c, 6860);
break;

case 12081:

summon(c, 6862);
break;
case 12083:

summon(c, 6864);
break;
case 12085:

summon(c, 6866);
break;
case 12087:

summon(c, 6868);
break;
case 12089:

summon(c, 6870);
break;
case 12091:

summon(c, 6872);
break;
case 12093://

summon(c, 6874);
break;
case 12123:

summon(c, 6890);
break;
case 12776:

summon(c, 7330);
break;

case 12778:

summon(c, 7332);
break;

case 12780:

summon(c, 7334);
break;
case 12782:

summon(c, 7336);
break;
case 12784:

summon(c, 7338);
break;
case 12786:

summon(c, 7340);
break;
case 12788:

summon(c, 7342);
break;
case 12790:

summon(c, 7344);
break;
case 12792:

summon(c, 7346);
break;
case 12794:

summon(c, 7346);
break;
case 12796:

summon(c, 7348);
break;
case 12798:

summon(c, 7350);
break;
case 12800:

summon(c, 7352);
break;
case 12802:

summon(c, 7354);
break;
case 12804:

summon(c, 7356);
break;
case 12806:

summon(c, 7358);
break;
case 12808:

summon(c, 7360);
break;
case 12810:

summon(c, 7362);
break;
case 12812:

summon(c, 7364);
break;
case 12814:

summon(c, 7366);
break;
case 12816:

summon(c, 7366);
break;
case 12818:

summon(c, 7368);
break;

case 12820:

summon(c, 7370);
break;

			
		default:
			if (c.playerRights == 3)
				Misc.println(c.playerName+ " - Item3rdOption: "+itemId+" : "+itemId11+" : "+itemId1);
			break;
		}

	}

}
