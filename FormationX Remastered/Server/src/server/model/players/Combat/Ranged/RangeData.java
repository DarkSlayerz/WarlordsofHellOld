package server.model.players.combat.ranged;

import server.model.players.Client;

public class RangeData {
	
	public static int getProjectileShowDelay(Client c) {
		int[] data = {
			806, 806, 808, 809, 810, 811,
			10033, 10034, 11230,
		};
		int str = 53;
		for(int i = 0; i < data.length; i++) {
			if(c.playerEquipment[c.playerWeapon] == data[i]) {
				str = 32;
			}
		}
		return str;
	}
	
	public static int getProjectileSpeed(Client c) {
		if (c.dbowSpec)
			return 100;
		switch(c.playerEquipment[3]) {
			case 10033:
			case 10034:
				return 60;
		}
		return 70;
	}
		public static boolean properBolts(Client c) {
		return c.playerEquipment[c.playerArrows] >= 9140 && c.playerEquipment[c.playerArrows] <= 9144
				|| c.playerEquipment[c.playerArrows] >= 9240 && c.playerEquipment[c.playerArrows] <= 9245 && c.playerEquipment[c.playerArrows] <= 9342;
	}
	
		public static boolean usingBolts(Client c) {
		return c.playerEquipment[c.playerArrows] >= 9130 && c.playerEquipment[c.playerArrows] <= 9145 
				|| c.playerEquipment[c.playerArrows] >= 9230 && c.playerEquipment[c.playerArrows] <= 9245 && c.playerEquipment[c.playerArrows] <= 9342;
	}
	
		public static int correctBowAndArrows(Client c) {
		if (usingBolts(c))
			return -1;
		switch(c.playerEquipment[c.playerWeapon]) {
		
			case 15241://hand cannon with Shots
			return 15243;
			
			case 839:
			case 841:
			return 882;
			
			case 843:
			case 845:
			return 884;
			
			case 18537:
			case 9185:
			return 9244;
			
			case 847:
			case 849:
			return 886;
			
			case 851:
			case 853:
			return 888;        
			
			case 855:
			case 857:
			return 890;
			
			case 859:
			case 861:
			return 892;
			
			case 4734:
			case 4935:
			case 4936:
			case 4937:
			return 4740;
			
			case 11235:
			case 15701:
			case 15702:
			case 15703:
			case 15704:
			return 11212;
		}
		return -1;
	}
	

	public static int getRangeStartGFX(Client c) {
		int str = -1;
		int[][] data = {
			//	KNIFES
			{863, 220}, {864, 219}, {865, 221}, {866, 223},
			{867, 224}, {868, 225}, {869, 222},

			//	DARTS
			{806, 1234}, {807, 1235}, {808, 1236}, 
			{809, 1238}, {810, 1239}, {811, 1240},
			{11230, 1242},

			//	JAVELIN
			{825, 206}, {826, 207}, {827, 208}, {828, 209},
			{829, 210}, {830, 211},

			//	AXES
			{800, 42}, {801, 43}, {802, 44}, {803, 45},
			{804, 46}, {805, 48},

			//	ARROWS
			{882, 19}, {884, 18}, {886, 20}, {888, 21},
			{890, 22}, {892, 24},

			//	CRYSTAL_BOW
			{4212, 250}, {4214, 250}, {4215, 250}, {4216, 250},
			{4217, 250}, {4218, 250}, {4219, 250}, {4220, 250},
			{4221, 250}, {4222, 250}, {4223, 250},
		};
		for(int l = 0; l < data.length; l++) {
			if(c.rangeItemUsed == data[l][0]) {
				str = data[l][1];
			}
		}
		if(c.playerEquipment[3] == 11235) {
			int[][] moreD = {
				{882, 1104}, {884, 1105}, {886, 1106}, {888, 1107},
				{890, 1108}, {892, 1109}, {11212, 1111},
			};
			for(int l = 0; l < moreD.length; l++) {
				if(c.playerEquipment[c.playerArrows] == moreD[l][0]) {
					str = moreD[l][1];
				}
			}
		}
		return str;
	}

	public static int getRangeProjectileGFX(Client c) {
		if (c.dbowSpec) {
			return 1099;
		}
		if(c.bowSpecShot > 0) {
			switch(c.rangeItemUsed) {
				default:
				return 249;
			}
		}
		if (c.playerEquipment[c.playerWeapon] == 9185 || c.playerEquipment[c.playerWeapon] == 18357)
			return 27;
			if (c.playerEquipment[c.playerWeapon] == 15241)
			return 2143;
		switch(c.rangeItemUsed) {
			case 13883:
                        		return 1839;
			case 13882:
			case 13881:
			case 13880:
			case 13879:
                        		return 1837;
			case 863:
			return 213;
			case 864:
			return 212;
			case 865:
			return 214;
			case 866: // knives
			return 216;
			case 867:
			return 217;
			case 868:
			return 218;	
			case 869:
			return 215;  

			case 806:
			return 226;
			case 807:
			return 227;
			case 808:
			return 228;
			case 809: // darts
			return 229;
			case 810:
			return 230;
			case 811:
			return 231;	

			case 825:
			return 200;
			case 826:
			return 201;
			case 827: // javelin
			return 202;
			case 828:
			return 203;
			case 829:
			return 204;
			case 830:
			return 205;	
			
			case 6522: // Toktz-xil-ul
			return 442;

			case 800:
			return 36;
			case 801:
			return 35;
			case 802:
			return 37; // axes
			case 803:
			return 38;
			case 804:
			return 39;
			case 805:
			return 40;

			case 882:
			return 10;
			
			case 884:
			return 9;
			
			case 886:
			return 11;

			case 888:
			return 12;
			
			case 890:
			return 13;
			
			case 892:
			return 15;
			
			case 11212:
			return 17;
			
			case 4740: // bolt rack
			return 27;


			
			case 4212:
			case 4214:
			case 4215:
			case 4216:
			case 4217:
			case 4218:
			case 4219:
			case 4220:
			case 4221:
			case 4222:
			case 4223:
			case 20171:
			case 18357:
			case 15241:
			return 249;
			
			
		}
		return -1;
	}

	public static int getRangeEndGFX(Client c) {
		int str = -1;
		int gfx = 0;
		int[][] data = {
			{10033, 157, 100}, {10034, 157, 100},
		};
		for(int l = 0; l < data.length; l++) {
			if(c.playerEquipment[c.playerWeapon] == data[l][0]) {
				str = data[l][1];
				gfx = data[l][2];
			}
		}
		if(gfx == 100) {
			c.rangeEndGFXHeight = true;
		}
		return str;
	}
}