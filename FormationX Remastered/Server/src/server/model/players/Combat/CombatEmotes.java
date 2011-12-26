package server.model.players.combat;

import server.model.players.CombatAssistant;
import server.model.players.Client;

public class CombatEmotes {
	
	public Client c;
	public CombatEmotes(Client Client) {
		this.c = Client;
	}
	


	/**
	*Weapon stand, walk, run, etc emotes
	**/
	
	public static void getPlayerAnimIndex(Client c, String weaponName){
		c.playerStandIndex = 0x328;
		c.playerTurnIndex = 0x337;
		c.playerWalkIndex = 0x333;
		c.playerTurn180Index = 0x334;
		c.playerTurn90CWIndex = 0x335;
		c.playerTurn90CCWIndex = 0x336;
		c.playerRunIndex = 0x338;
	
		if(weaponName.contains("halberd") || weaponName.contains("guthan")) {
			c.playerStandIndex = 809;
			c.playerWalkIndex = 1146;
			c.playerRunIndex = 1210;
			return;
		}	
		if(weaponName.contains("dharok")) {
			c.playerStandIndex = 0x811;
			c.playerWalkIndex = 0x67F;
			c.playerRunIndex = 0x680;
			return;
		}	
		if(weaponName.contains("ahrim")) {
			c.playerStandIndex = 809;
			c.playerWalkIndex = 1146;
			c.playerRunIndex = 1210;
			return;
		}
		if(weaponName.contains("verac")) {
			c.playerStandIndex = 0x328;
			c.playerWalkIndex = 0x333;
			c.playerRunIndex = 824;
			return;
		}
		if (weaponName.contains("longsword") || weaponName.contains("scimitar") || weaponName.contains("silverlight") || weaponName.contains("korasi's"))  {
			c.playerStandIndex = 12021;
			c.playerRunIndex = 12023;
			c.playerWalkIndex = 12024;
			return;
		}
		if (weaponName.contains("wand") || weaponName.contains("staff") || weaponName.contains("staff") || weaponName.contains("spear")) {
			c.playerStandIndex = 8980;
			c.playerRunIndex = 1210;
			c.playerWalkIndex = 1146;
			return;
		}
		if(weaponName.contains("karil")) {
			c.playerStandIndex = 2074;
			c.playerWalkIndex = 2076;
			c.playerRunIndex = 2077;
			return;
		}
	if(weaponName.contains("2h sword") || weaponName.contains("godsword") || weaponName.contains("saradomin sw")) {
			c.playerStandIndex = 7047;
			c.playerWalkIndex = 7046;
			c.playerRunIndex = 7039;
			return;
		}							
		if(weaponName.contains("bow")) {
			c.playerStandIndex = 808;
			c.playerWalkIndex = 819;
			c.playerRunIndex = 824;
			return;
		}

		switch(c.playerEquipment[c.playerWeapon]) {	
			case 18349: // rapier chaotic
			c.playerStandIndex = 12021;
			c.playerWalkIndex = 12024;
			c.playerRunIndex = 12023;
			break;
			case 18353: // maul chaotic
			c.playerStandIndex = 13217;
			c.playerWalkIndex = 13218;
			c.playerRunIndex = 13220;
			break;
 			case 4151:
			case 15441: // whip
			case 15442: // whip
			case 15443: // whip
			case 15444: // whip
			c.playerStandIndex = 11973;
			c.playerWalkIndex = 11975;
			c.playerRunIndex = 1661;
			break;
 			case 15039:
			c.playerStandIndex = 12000;
			c.playerWalkIndex = 1663;
			c.playerRunIndex = 1664;
			break;
			case 10887:
			c.playerStandIndex = 5869;
			c.playerWalkIndex = 5867;
			c.playerRunIndex = 5868;
			break;
			case 6528:
				c.playerStandIndex = 0x811;
				c.playerWalkIndex = 2064;
				c.playerRunIndex = 1664;
			break;
			case 4153:
			c.playerStandIndex = 1662;
			c.playerWalkIndex = 1663;
			c.playerRunIndex = 1664;
			break;
			case 15241:
			c.playerStandIndex = 12155;
			c.playerWalkIndex = 12154;
			c.playerRunIndex = 12154;
			break;
			case 11694:
			case 11696:
			case 11730:
			case 11698:
			case 11700:
			c.playerStandIndex = 4300;
			c.playerWalkIndex = 4306;
			c.playerRunIndex = 4305;
			break;
			case 1305:
			c.playerStandIndex = 809;
			break;
		}
	}
	
	/**
	* Weapon emotes
	**/
	
	public static int getWepAnim(Client c, String weaponName) {
		if(c.playerEquipment[c.playerWeapon] <= 0) {
			switch(c.fightMode) {
				case 0:
				return 422;			
				case 2:
				return 423;			
				case 1:
				return 451;
			}
		}
		if(weaponName.contains("knife") || weaponName.contains("dart") || weaponName.contains("javelin") || weaponName.contains("thrownaxe")){
			return 806;
		}
		if(weaponName.contains("halberd")) {
			return 440;
		}
		if(weaponName.startsWith("dragon dagger")) {
			return 402;
		}	
		if(weaponName.endsWith("dagger")) {
			return 412;
		}		
 				if(weaponName.contains("2h sword") || weaponName.contains("godsword") || weaponName.contains("saradomin sword")) {
			switch(c.fightMode) {
				case 0:
				return 7041;		
				case 2:
				return 7042;			
				case 1:
				return 7048;
			}	
			}	
		if(weaponName.contains("scimitar") || weaponName.contains("longsword") || weaponName.contains("korasi's")) {
			switch(c.fightMode) {
				case 0:
				return 12029;	
				case 1: // New Scimmi models
				return 12029;		
				case 2:
				return 12029;	
				case 3:
				return 12028;		
			}
		}
		if(weaponName.contains("rapier")) {
			switch(c.fightMode) {
				case 0:
				return 386;	
				case 1:
				return 386;		
				case 2:
				return 386;	
				case 3:
				return 386;
			}
		}
              if(weaponName.contains("dharok")) {
                   switch(c.fightMode) {
                          case 0: 
                          return 2067;
                          case 1: 
                          return 2067;
                          case 2: 
                          return 2067;
                          case 3:
                          return 2066;
			}
		}
		if(weaponName.contains("sword")) {
			return 451;
		}
		if(weaponName.contains("karil")) {
			return 2075;
		}
		if(weaponName.contains("chaotic crossbow")) {
			return 4230;
		}
		if(weaponName.contains("bow") && !weaponName.contains("'bow")) {
			return 426;
		}
		if (weaponName.contains("'bow"))
			return 4230;
			
		switch(c.playerEquipment[c.playerWeapon]) { // if you don't want to use strings
			case 6522:
			return 2614;
                        case 13905:
			return 2080;
			case 4153: // granite maul
			return 1665;
			case 4726: // guthan 
			return 2080;
			case 13879:
			case 13883:
			return 806;
			case 14484: //  Dclaw
			return 393;
			case 15039: //  Chaotic maul
			return 2661;
			case 15241:
			return 12153;
			case 4747: // torag
			return 0x814;
			case 4710: // ahrim
			return 406;
			case 18353:
			return 13055;
			case 18349:
			return 386;
			case 4755: // verac
			return 2062;
			case 4734: // karil
			return 2075;
			case 10887:
			return 5865;
			case 4151:
			case 15441: // whip
			case 15442: // whip
			case 15443: // whip
			case 15444: // whip
			return 1658;
			case 6528:
			return 2661;
			default:
			return 451;
		}
	}
	
	/**
	* Block emotes
	*/
		public static int getBlockEmote(Client c) {
	
		//if(s.endsWith("defender")) {
			//return 7;
			//}
		switch(c.playerEquipment[c.playerShield]) {
		
		//DEFENDERS
		case 8844:
		case 8845:
		case 8846:
		case 8847:
		case 8848:
		case 8849:
		case 8850:
		case 20072:
		return 4177;
		
		//Shields
		case 11283:
		case 11284:
		case 11285:
		case 13734:
		case 13736:
		case 13738:
		case 13740:
		case 13742:
		case 13744:
		case 1187:
		case 13506:
		case 13964:
		case 1201:
		case 6524:
		case 1540:
		return 1156;
		

		}
		switch(c.playerEquipment[c.playerWeapon]) {
		
			case 19780:
			return 12030;
			
			case 4755:
			return 2063;
			
			case 10887:
			return 5866;

			case 4153:
			return 1666;
			
			case 15241:
			return 12156;
			
			//Staffs
			case 15486:
			case 15502:
			case 4675:
			case 13406:
			return 12004;

			case 18353:
			return 13054;

			case 18349:
			return 13038;

			case 4151:
			case 15441: // whip
			case 15442: // whip
			case 15443: // whip
			case 15444: // whip
			return 11974;
			
			//2H Block
			case 11694:
			case 11698:
			case 11700: // scimmy anim 12030
			case 11696:
			case 11730:
			return 7050;
			
			//Scimitars
			case 4587:
			case 13477:
			case 13979:
			return 12030;
			
		    case 861:
			return -1;
			default:
			return 404;
		}
	}
}