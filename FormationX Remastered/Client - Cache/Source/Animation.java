import sign.signlink;

public final class Animation
{


    public static final int ATTACK_ANIMS[] = {
        451, 806, 427, 402, 2661,2138, 2614, 1665, 2080, 2068, 2067, 
        406, 2062, 2075, 440, 1658, 2779, 1833, 412, 7041, 426, 
        1062, 1058, 1060, 440, 1074, 1872, 435, 7042, 7048, 7049, 
        2066, 10961, 407, 10499, 10502, 10505, 10501, 10504, 403,
	484, 415, 3679, 404, 2063, 1666, 1125, 403, 1659, 424, 4177, 393, 12156,
	2063, 5866, 1659, 7050, 1658, 400, 401, 402, 405, 406, 407,
	408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 12030,
	440, 451, 1662, 1663, 1664, 1665, 1667, 1833, 3157, 4230, 1462,
	1468, 2079, 1661, 10082, 6999, 11974, 13051, 11963, 11973, 1156,
	2836, 12490, 12489, 12488, 12487, 12485, 12484, 12483, 12482, 12481,
	12491, 12492, 12493, 12494, 12495, 12496, 12497, 12498, 12499, 12500,
	11971, 12432, 1666,12004,2063,5866,4177,386,12029,12153,12175,12028,11979, 4000, 
	/*magic spells*/1979, 1978, 711, 729, 716,  708,811,  1819,12575,11969,12002
    };

	public static final int[] UNWALKABLE = {
		12565, 12567, 12589, 12575, 12573, 4410, 4411, 855, 856, 857, 858, 859, 860, 861, 862, 863, 864, 865, 866, 2105, 2106, 2107, 2108, 2109, 2110, 2111, 2112, 2113, 0x558, 11044, 10530,
		8770, 7531, 0x84F, 850, 6111, 3544, 9990, 4278, 4280, 4275, 4272, 2414,  
	};
    public static String loc = (new StringBuilder()).append(signlink.findcachedir()).append("Data/Animation/").toString();
    public static Animation anims[];
    public int anInt352;
    public int anIntArray353[];
    public int anIntArray354[];
    private int anIntArray355[];
    public int anInt356;
    public int anIntArray357[];
    public boolean aBoolean358;
    public int anInt359;
    public int anInt360;
    public int anInt361;
    public int anInt362;
    public int anInt363;
    public int anInt364;
    public int anInt365;
    public static int anInt367;

    public static byte[] getData(String s)
    {
        return FileOperations.ReadFile(s);
    }


    public static void unpackConfig(StreamLoader streamLoader)
    {

        Stream stream = new Stream(streamLoader.getDataForName("seq.dat"));
        int i = stream.readUnsignedWord();

        if(anims == null)
        {
            anims = new Animation[i];
        }
        for(int j = 0; j < i; j++)
        {
            if(anims[j] == null)
            {
                anims[j] = new Animation();
            }
            anims[j].readValues(stream);
	/*Fixes the bug with 602 animations...*/
			if (j == 4000) {
			anims[j].anInt352 = 28;
			anims[j].anIntArray355 = new int[]{3,2,2,2,3,3,3,3,3,2,2,2,2,2,3,3,2,1,1,3,3,3,3,3,3,3,3,3};
			anims[j].anIntArray353 = new int[]{223019263,223019511,223019120,223019119,223019242,223019278,223019027,223019504,223019417,223019428,223019705,223019087,223019664,223019465,223019589,223019707,223019322,223019644,223019174,223019574,223019108,223019335,223019521,223019401,223019300,223019029,223019276,223019115};
			anims[j].anInt363 = 0;
			anims[j].anInt364 = 0;
		}
		if (j == 4001) {
			anims[j].anInt352 = 15;
			anims[j].anIntArray355 = new int[]{9,3,3,3,3,3,2,2,15,4,3,3,3,3,3};
			anims[j].anIntArray353 = new int[]{219742346,219742338,219742330,219742335,219742341,219742348,219742322,219742325,219742318,219742320,219742323,219742349,219742334,219742317,219742347};
		}
		if (j == 4002) {
			anims[j].anInt352 = 40;
			anims[j].anIntArray355 = new int[]{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
			anims[j].anIntArray353 = new int[]{219742278,219742256,219742218,219742282,219742223,219742222,219742253,219742232,219742300,219742239,219742254,219742252,219742245,219742224,219742219,219742294,219742209,219742241,219742299,219742230,219742279,219742238,219742221,219742214,219742283,219742305,219742312,219742280,219742265,219742211,219742210,219742208,219742212,219742234,219742314,219742240,219742292,219742313,219742267,219742263};
		}
	if(j == 7046) {
		anims[j].anIntArray353[10] = 114491627;
	}
	if(j == 7043 || j == 11985) {
		anims[j].anIntArray353[1] = 114491725;
	}
	if(j == 11991) {
		anims[j].anIntArray353[7] = 114491402;
		anims[j].anIntArray353[8] = 114491402;
	}
	if(j == 11989) {
		anims[j].anIntArray353[5] = 114491523;
		anims[j].anIntArray353[25] = 114491692;
	}
	if(j == 11993) {
		anims[j].anIntArray353[8] = 114491890;
	}
for(int c : UNWALKABLE) {
					if(j == c) {
						anims[j].anInt363 = 0;
						anims[j].anInt364 = 0;
	}
}
            int ai[] = ATTACK_ANIMS;
            int k = ai.length;
            for(int l = 0; l < k; l++)
            {
                int i1 = ai[l];
                if(j == i1)
                {
                    anims[j].anInt360 = -1;
                    anims[j].anInt361 = -1;
        }
        }

    }
}


    public int method258(int i)
    {

        int j = anIntArray355[i];
        if(j == 0)
        {

            Class36 class36 = Class36.method531(anIntArray353[i]);
            if(class36 != null)
                j = anIntArray355[i] = class36.anInt636;
        }
        if(j == 0)
            j = 1;
        return j;
    }


    private void readValues(Stream stream)
    {
                        anInt360 = stream.readUnsignedWord();
                            anInt361 = stream.readUnsignedWord();
                        anInt359 = stream.readUnsignedByte();
                        anInt356 = stream.readUnsignedWord();





                        anInt352 = stream.readUnsignedWord();
                        anIntArray353 = new int[anInt352];
                        anIntArray354 = new int[anInt352];
                        anIntArray355 = new int[anInt352];
                            if (anInt360 == 65535)
                                anInt360 = 0;
                        if (anInt360 > 0)
                                    anInt360 += 512;
                        if (anInt361 == 65535)
                                    anInt361 = 0;
                            if (anInt361 > 0)
                                anInt361 += 512;
                        for(int i=0; i < anInt352; i++)
                                anIntArray353[i] = stream.readDWord();
                        for(int i=0; i < anInt352; i++)
                                anIntArray354[i] = -1;
                        for(int i=0; i < anInt352; i++)
                                    anIntArray355[i] = stream.readUnsignedByte();
    }

    private Animation()

















































    {








        anInt356 = -1;
        aBoolean358 = false;
        anInt359 = 5;
        anInt360 = -1;
        anInt361 = -1;
        anInt362 = 99;
        anInt363 = -1;
        anInt364 = -1;
        anInt365 = 2;
    }

















}
