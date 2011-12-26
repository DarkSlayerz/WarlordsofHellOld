package server.model.players;

import server.Server;
import server.util.Misc;

public class EarningPotential {

		private static int[][] xEP = {{892,12},{892,12},{868,50},{892,12},{892,12},{1099,1},{1165,1},{1351,1},{1319,1},{1333,1},{1359,1},{1149,1},{1185,1},{1704,1},{157,3},{145,2},{175,4},{995,14700},{995,22500},{6731,1},{6733,1},{4712,1},{4714,1},{4716,1},{4718,1},{4720,1},{4722,1},{4736,1},{4738,1},{4749,1},{4751,1},{892,100},{4675,1},{4091,1},{4093,1},{4095,1},{4097,1},{4101,1},{4103,1},{4105,1},{892,100},{1768,1},{892,12},{892,12},{4107,1},{4111,1},{4113,1},{4115,1},{4117,1},{4131,1},{1079,1},{1093,1},{1127,1},
		{1163,1},{1201,1},{4587,1},{1340,1},{1725,1},{892,12},{892,12},{1729,1},{1731,1},{1149,1},{892,100},{1351,1},{1319,1},{1333,1},{1359,1},{995,1000000},{892,100},{995,5815},{995,300000},{1085,1},{1089,1},{1351,1},{1079,1},{892,12},{892,12},{9422,140},{6731,1},{6733,1},{4712,1},{4714,1},{4716,1},{4718,1},{4720,1},{4722,1},{4736,1},{4738,1},{4749,1},{4751,1},{892,12},{892,12},{1770,1},{1765,1},{892,12},{892,12},{1127,1},{1093,1},{4087,1},{4585,1},{3140,1},{6737,1},{6731,1},{6733,1},{4712,1},{4714,1},{4716,1},{4718,1},
		{4720,1},{4722,1},{4736,1},{892,12},{892,12},{892,12},{892,12},{892,100},{892,12},{892,12},{892,12},{892,12},{892,100},{892,12},{892,12},{4738,1},{892,12},{892,12},{1704,1},{157,3},{1379,1},{1381,1},{1393,1},{861,1},{145,2},{892,12},{892,12},{175,4},{892,12},{892,12},{4749,1},{4751,1},{892,12},{892,12},{11732,1},
		{892,100},{1340,1},{1725,1},{1729,1},{892,12},{892,12},{892,12},{892,12},{1731,1},{892,12},{892,12},{11235,1},{1079,1},{1127,1},{1099,1},{1165,1},{1149,1},{1351,1},{1319,1},{1333,1},{1359,1},{1379,1},{1381,1},{892,12},{892,12},{892,12},{892,12},{1393,1},{861,1},{1185,1},{892,100},{1093,1},{4087,1},{4585,1},{3140,1},{995,5815},{995,300000},{1085,1},{1089,1},{1340,1},{1725,1},{1729,1},{1731,1},{1351,1},{892,12},{892,12},{4675,1},{1340,1},{892,12},{892,12},{1725,1},{1729,1},{1351,1},{1319,1},{1333,1},{1359,1},{1731,1},{892,100},{1351,1},{1319,1},{1333,1},{1359,1},{4091,1},{4093,1},{4095,1},{4097,1},{4101,1},{4103,1},{1340,1},{1725,1},{1729,1},{1731,1},{4105,1},{6585,1},{4107,1},{4111,1},{4113,1},{4115,1},{4117,1},{4131,1},{1079,1},{1093,1},{1127,1},{1163,1},{1201,1},{4587,1},{1149,1},{6737,1},{6731,1},{11335,1},{11212,40},{4151,1},{6585,1},{1187,1},{4675,1},{892,12},{892,12},{892,12},{892,12},{1351,1},{1319,1},{1333,1},{1359,1},{13884,1},{13887,1},
		{13890,1},{13896,1},{892,100},{1099,1},{1165,1},{892,12},{892,12},{892,12},{892,12},{1149,1},{1185,1},{13736,1},{892,100},{13734,1},{892,100},{13905,1},{995,5815},{1351,1},{1319,1},{1333,1},{1359,1},{995,300000},{1085,1},{1089,1},{1351,1}};

	public static void checkPotential(Client c) {
		if (c.inWild()) {
			if (++c.epDelay == 750) {
			if(c.getItems().getCarriedWealth() > 300000 || c.getPA().getWearingAmount2() > 200000) {
				c.earningPotential += 13 + Misc.random(12);
				if (c.earningPotential > 100) 
					c.earningPotential = 100;
				c.sendMessage("Your EP increases to: " + c.earningPotential + ".");
				if(c.inFunPk());
				return;
				} else {
				c.sendMessage("You must risk more than 300K to increase your EP!");
				}
				c.epDelay = 0;
		}
	}
}
	
	public static void checkTeleport(Client c) {
		if (c.inWild())
			if (c.underAttackBy > 0) {
				c.earningPotential -= 10 + Misc.random(5);
				c.sendMessage("You now have "+c.earningPotential+" EP since you teleported in combat!");
				if (c.earningPotential < 0)
					c.earningPotential = 0;
		}
	}
	
	public static void giveBonusDrops(Client c, Client c2) {
                Client o = (Client) Server.playerHandler.players[c.killerId];
		Client pl = (Client) Server.playerHandler.players[c.killerId];
			if (c.inWild() && c2.inWild()) {
		if (c.earningPotential >= 25) {
				c.earningPotential -= 20 + Misc.random(4);
				int random = (int)(Math.random() * (xEP.length - 1));
				Server.itemHandler.createGroundItem(c, xEP[random][0], c2.absX, c2.absY, 
											xEP[random][1], c.playerId);
				c.sendMessage("Your EP decreased to: "+c.earningPotential+".");
				}
			}
		}
	}	
