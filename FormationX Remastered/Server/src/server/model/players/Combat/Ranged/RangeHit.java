package server.model.players.combat.ranged;

import server.model.players.Client;

public class RangeHit extends RangeData {

	public static int calculateRangeDefence(Client c) {
		int defenceLevel = c.playerLevel[1];
        if (c.prayerActive[0]) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.05;
        } else if (c.prayerActive[5]) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.1;
        } else if (c.prayerActive[13]) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.15;
        } else if (c.prayerActive[24]) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.2;
        } else if (c.prayerActive[25]) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.25;
        } else if (c.curseActive[19]) { // turmoil
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.15 + c.getdef;
        }
        return (int) (defenceLevel + c.playerBonus[9] + (c.playerBonus[9] / 2));
	}

	public static int calculateRangeAttack(Client c) {
		int attackLevel = c.playerLevel[4];
		attackLevel *= c.specAccuracy;
        if (c.fullVoidRange())
            attackLevel += c.getLevelForXP(c.playerXP[c.playerRanged]) * 0.1;
		if (c.prayerActive[3])
			attackLevel *= 1.05;
		else if (c.prayerActive[11])
			attackLevel *= 1.10;
		else if (c.prayerActive[19])
			attackLevel *= 1.15;
		//dbow spec
		if (c.fullVoidRange() && c.specAccuracy > 1.15) {
			attackLevel *= 1.75;		
		}
        return (int) (attackLevel + (c.playerBonus[4] * 1.95));
	}

	public static int rangeMaxHit(Client c) {
        int rangehit = 0;
		double wtf = c.specDamage;
        rangehit += c.playerLevel[4] / 7.5;
        int weapon = c.lastWeaponUsed;
        int Arrows = c.lastArrowUsed;
        if (weapon == 4223) {//Cbow 1/10
            rangehit = 2;
            rangehit += c.playerLevel[4] / 7;
        } else if (weapon == 4222) {//Cbow 2/10
            rangehit = 3;
            rangehit += c.playerLevel[4] / 7;
        } else if (weapon == 4221) {//Cbow 3/10
            rangehit = 3;
            rangehit += c.playerLevel[4] / 6.5;
        } else if (weapon == 4220) {//Cbow 4/10
            rangehit = 4;
            rangehit += c.playerLevel[4] / 6.5;
        } else if (weapon == 4219) {//Cbow 5/10
            rangehit = 4;
            rangehit += c.playerLevel[4] / 6;
        } else if (weapon == 4218) {//Cbow 6/10
            rangehit = 5;
            rangehit += c.playerLevel[4] / 6;
        } else if (weapon == 4217) {//Cbow 7/10
            rangehit = 5;
            rangehit += c.playerLevel[4] / 5.5;
        } else if (weapon == 4216) {//Cbow 8/10
            rangehit = 6;
            rangehit += c.playerLevel[4] / 5.5;
        } else if (weapon == 4215) {//Cbow 9/10
            rangehit = 6;
            rangehit += c.playerLevel[4] / 5;
        } else if (weapon == 4214) {//Cbow Full
            rangehit = 7;
            rangehit += c.playerLevel[4] / 5;
        } else if (weapon == 6522) {
            rangehit = 5;
            rangehit += c.playerLevel[4] / 6;
        } else if (weapon == 9029) {//dragon darts
            rangehit = 8;
            rangehit += c.playerLevel[4] / 10;
			if(weapon == 15241)
			rangehit *= 1.35;
        } else if (weapon == 18357) {//chaotic c'
            rangehit = 7;
            rangehit += c.playerLevel[4] / 2.5;
		} else if (weapon == 811 || weapon == 868) {//rune darts
            rangehit = 2;
            rangehit += c.playerLevel[4] / 8.5;
        } else if (weapon == 810 || weapon == 867) {//adamant darts
            rangehit = 2;
            rangehit += c.playerLevel[4] / 9;
        } else if (weapon == 809 || weapon == 866) {//mithril darts
            rangehit = 2;
            rangehit += c.playerLevel[4] / 9.5;
        } else if (weapon == 808 || weapon == 865) {//Steel darts
            rangehit = 2;
            rangehit += c.playerLevel[4] / 10;
        } else if (weapon == 807 || weapon == 863) {//Iron darts
            rangehit = 2;
            rangehit += c.playerLevel[4] / 10.5;
        } else if (weapon == 806 || weapon == 864) {//Bronze darts
            rangehit = 1;
            rangehit += c.playerLevel[4] / 11;
        } else if (Arrows == 4740 && weapon == 4734) {//BoltRacks
			rangehit = 3;
            rangehit += c.playerLevel[4] / 6;
			} else if (Arrows == 15243) {//Hand cannon shots
            rangehit = 4;
            rangehit += c.playerLevel[4] / 6;
        } else if (Arrows == 11212) {//dragon arrows
            rangehit = 4;
            rangehit += c.playerLevel[4] / 5.5;
        } else if (Arrows == 892) {//rune arrows
            rangehit = 3;
            rangehit += c.playerLevel[4] / 6;
        } else if (Arrows == 890) {//adamant arrows
            rangehit = 2;
            rangehit += c.playerLevel[4] / 7;
        } else if (Arrows == 888) {//mithril arrows
            rangehit = 2;
            rangehit += c.playerLevel[4] / 7.5;
        } else if (Arrows == 886) {//steel arrows
            rangehit = 2;
            rangehit += c.playerLevel[4] / 8;
        } else if (Arrows == 884) {//Iron arrows
            rangehit = 2;
            rangehit += c.playerLevel[4] / 9;
        } else if (Arrows == 882) {//Bronze arrows
            rangehit = 1;
            rangehit += c.playerLevel[4] / 9.5;
        } else if (Arrows == 9244) {
			rangehit = 9;
			rangehit += c.playerLevel[4] / 3;
		} else if (Arrows == 9139) {
			rangehit = 12;
			rangehit += c.playerLevel[4] / 4;
		} else if (Arrows == 9140) {
			rangehit = 2;
            rangehit += c.playerLevel[4] / 7;
		} else if (Arrows == 9141) {
			rangehit = 3;
            rangehit += c.playerLevel[4] / 6;
		} else if (Arrows == 9142) {
			rangehit = 4;
            rangehit += c.playerLevel[4] / 6;
		} else if (Arrows == 9143) {
			rangehit = 7;
			rangehit += c.playerLevel[4] / 5;
		} else if (Arrows == 9144) {
			rangehit = 7;
			rangehit += c.playerLevel[4] / 4.5;
		}
        int bonus = 0;
        bonus -= rangehit / 10;
        rangehit += bonus;

        if (c.specDamage != 1)
			rangehit *= c.specDamage;
		if (rangehit == 0)
			rangehit++;
		if (c.fullVoidRange()) {
			rangehit *= 1.10;
		}
		if (c.prayerActive[3])
			rangehit *= 1.05;
		else if (c.prayerActive[11])
			rangehit *= 1.10;
		else if (c.prayerActive[19])
			rangehit *= 1.15;
			
		return rangehit;
    }
	
	public static int getRangeStr(Client c, int i) {
		int str = 0;
		int[][] data = {
			{877,  10}, {9140, 46}, {9145, 36}, {9141, 64}, 
			{9142, 82}, {9143,100}, {9144,115}, {9236, 14}, 
			{9237, 30}, {9238, 48}, {9239, 66}, {9240, 83}, 
			{9241, 85}, {9242,103}, {9243,105}, {9244,117}, 
			{9245,120}, {882, 7}, {884, 10}, {886, 16}, 
			{888, 22}, {890, 31}, {892, 49},{15243, 60}, {4740, 55}, 
			{11212, 60}, {806, 1}, {807, 3}, {808, 4}, 
			{809, 7}, {810,10}, {811,14}, {11230,20},
			{864, 3},  {863, 4}, {865, 7}, {866, 10}, 
			{867, 14}, {868, 24}, {825, 6}, {826,10}, 
			{827,12}, {828,18}, {829,28}, {830,42},
			{800, 5}, {801, 7}, {802,11}, {803,16}, 
			{804,23}, {805,36}, {9976, 0}, {9977, 15},
			{4212, 70}, {4214, 70}, {4215, 70}, {4216, 70},
			{4217, 70}, {4218, 70}, {4219, 70}, {4220, 70},
			{4221, 70}, {4222, 70}, {4223, 70}, {6522, 49},
			{10034, 15},
		};
		for(int l = 0; l < data.length; l++) {
			if(i == data[l][0]) {
				str = data[l][1];
			}
		}
		return str;
	}
}