package server.model.players.combat.melee;

import server.model.players.Client;

public class MeleeData {
	
	public static int getRequiredDistance(Client c) {
		if (c.followId > 0 && c.freezeTimer <= 0 && !c.isMoving)
			return 2;
		else if(c.followId > 0 && c.freezeTimer <= 0 && c.isMoving) {
			return 3;
		} else {
			return 1;
		}
	}
	
	public static boolean usingHally(Client c) {
		switch(c.playerEquipment[c.playerWeapon]) {
			case 3190:
			case 3192:
			case 3194:
			case 3196:
			case 3198:
			case 3200:
			case 3202:
			case 3204:
			return true;
			
			default:
			return false;
		}
	}
		/**
	* How long it takes to hit your enemy
	**/
	public static int getHitDelay(Client c, String weaponName) {
		if(c.usingMagic) {
			switch(c.MAGIC_SPELLS[c.spellId][0]) {			
				case 12891:
				return 4;
				case 12871:
				return 6;
				default:
				return 4;
			}
		} else {

			if(weaponName.contains("knife") || weaponName.contains("dart") || weaponName.contains("javelin") || weaponName.contains("thrownaxe")){
				return 3;
			}
			if(weaponName.contains("cross") || weaponName.contains("c'bow")) {
				return 4;
			}
			if(weaponName.contains("bow") && !c.dbowSpec) {
				return 4;
			} else if (c.dbowSpec) {
				return 4;
			}

			switch(c.playerEquipment[c.playerWeapon]) {	
				case 6522: // Toktz-xil-ul
				return 3;
				
				
				default:
				return 2;
			}
		}
	}
	
		public static int getAttackDelay(Client c, String s) {
		if(c.usingMagic) {
			switch(c.MAGIC_SPELLS[c.spellId][0]) {
				case 12871: // ice blitz
				case 13023: // shadow barrage
				case 12891: // ice barrage
				return 5;
				
				default:
				return 5;
			}
		}
		if(c.playerEquipment[c.playerWeapon] == -1)
			return 4;//unarmed
			
		switch (c.playerEquipment[c.playerWeapon]) {
		case 15241:
			return 9;
	     		case 15038:
			return 5;
			case 15701:
			case 15702:
			case 15703:
			case 15704:
			case 11235:
			return 9;
			case 15015:
			return 8;
			case 15037:
			case 11730:
			return 4;
                        case 14484:
			return 5;
			case 15016:
			return 6;
			case 10887:
			case 6528:
			case 18353:
			case 16425:
			return 7;
                        case 13905:
                        return 5;
		}
		
		if(s.endsWith("greataxe"))
			return 7;
		else if(s.equals("torags hammers"))
			return 5;
		else if(s.equals("guthans warspear"))
			return 5;
		else if(s.equals("veracs flail"))
			return 5;
		else if(s.equals("ahrims staff"))
			return 6;
		else if(s.contains("staff")){
			if(s.contains("zamarok") || s.contains("guthix") || s.contains("saradomian") || s.contains("slayer") || s.contains("ancient"))
				return 4;
			else
				return 5;
		} else if(s.contains("aril")){
			if(s.contains("composite") || s.equals("seercull"))
				return 5;
			else if(s.contains("hi"))
				return 4;
			else if(s.contains("Ogre"))
				return 8;
			else if(s.contains("short") || s.contains("hunt") || s.contains("sword"))
				return 4;
			else if(s.contains("long") || s.contains("crystal"))
				return 6;
			else if(s.contains("'bow"))
				return 7;
			
			return 5;
		}
		else if(s.contains("dagger"))
			return 4;
		else if(s.contains("godsword") || s.contains("2h"))
			return 6;
		else if(s.contains("longsword"))
			return 5;
		else if(s.contains("sword"))
			return 4;
		else if(s.contains("scimitar"))
			return 4;
		else if(s.contains("mace"))
			return 5;
		else if(s.contains("battleaxe"))
			return 6;
		else if(s.contains("pickaxe"))
			return 5;
		else if(s.contains("thrownaxe"))
			return 5;
		else if(s.contains("axe"))
			return 5;
		else if(s.contains("warhammer"))
			return 6;
		else if(s.contains("2h"))
			return 7;
		else if(s.contains("spear"))
			return 5;
		else if(s.contains("claw"))
			return 4;
		else if(s.contains("halberd"))
			return 7;
		
		//sara sword, 2400ms
		else if(s.equals("granite maul"))
			return 7;
		else if(s.equals("toktz-xil-ak"))//sword
			return 4;
		else if(s.equals("tzhaar-ket-em"))//mace
			return 5;
		else if(s.equals("tzhaar-ket-om"))//maul
			return 7;
                else if(s.equals("chaotic maul"))//maul
			return 7;
		else if(s.equals("toktz-xil-ek"))//knife
			return 4;
		else if(s.equals("toktz-xil-ul"))//rings
			return 4;
		else if(s.equals("toktz-mej-tal"))//staff
			return 6;
		else if(s.contains("whip"))
			return 4;
		else if(s.contains("dart"))
			return 3;
		else if(s.contains("knife"))
			return 3;
		else if(s.contains("javelin"))
			return 6;
		return 5;
	}
}