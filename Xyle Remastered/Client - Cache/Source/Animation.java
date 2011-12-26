
// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.signlink;

public final class Animation {

    public static void unpackConfig(StreamLoader streamLoader)
    {
		Stream stream = new Stream(streamLoader.getDataForName("seq.dat"));
        int length = stream.readUnsignedWord();
        if(anims == null)
            anims = new Animation[length + 5000];
        for(int j = 0; j < length; j++) {
            if(anims[j] == null)
                anims[j] = new Animation();
            anims[j].readValues(stream);
        }
			anims[4000] = new Animation();
			anims[4000].anInt352 = 28;
			anims[4000].anIntArray355 = new int[]{3,2,2,2,3,3,3,3,3,2,2,2,2,2,3,3,2,1,1,3,3,3,3,3,3,3,3,3};
			anims[4000].anIntArray357 = new int[]{9,11,13,15,17,19,165,167,169,171,173,175,177,179,9999999};
			anims[4000].anIntArray353 = new int[] {211812607, 211812855, 211812464, 211812463, 211812586, 211812622, 211812371, 211812848, 211812761, 211812772, 211813049, 211812431, 211813008, 211812809, 211812933, 211813051, 211812666, 211812988, 211812518, 211812918, 211812452, 211812679, 211812865, 211812745, 211812644, 211812373, 211812620, 211812459};
			anims[4000].anInt360 = -1;
			anims[4000].anInt361 = -1;
			
			anims[4001].anInt352 = 15;
			anims[4001].anIntArray355 = new int[]{9,3,3,3,3,3,2,2,15,4,3,3,3,3,3};
			anims[4001].anIntArray353 = new int[] {211746954, 211746946, 211746938, 211746943, 211746949, 211746956, 211746930, 211746933, 211746926, 211746928, 211746931, 211746957, 211746942, 211746925, 211746955, };
			
			anims[4002].anInt352 = 40;
			anims[4002].anIntArray355 = new int[]{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
			anims[4002].anIntArray353 = new int[] {211746886, 211746864, 211746826, 211746890, 211746831, 211746830, 211746861, 211746840, 211746908, 211746847, 211746862, 211746860, 211746853, 211746832, 211746827, 211746902, 211746817, 211746849, 211746907, 211746838, 211746887, 211746846, 211746829, 211746822, 211746891, 211746913, 211746920, 211746888, 211746873, 211746819, 211746818, 211746816, 211746820, 211746842, 211746922, 211746848, 211746900, 211746921, 211746875, 211746871, };

			anims[15069] = new Animation();
			anims[15069].anInt352 = 24;
			anims[15069].anIntArray355 = new int[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
			anims[15069].anIntArray353 = new int[] {211681453, 211681284, 211681361, 211681395, 211681441, 211681366, 211681302, 211681424, 211681410, 211681368, 211681412, 211681432, 211681362, 211681301, 211681331, 211681296, 211681430, 211681323, 211681312, 211681373, 211681392, 211681357, 211681324, 211681428};
			anims[15069].anInt363 = 0;
			anims[15069].anInt364 = 0;
			
			anims[15070] = new Animation();
			anims[15070].anInt352 = 24;
			anims[15070].anIntArray355 = new int[] { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
			anims[15070].anIntArray353 = new int[] {211681421, 211681379, 211681298, 211681285, 211681382, 211681465, 211681295, 211681347, 211681349, 211681443, 211681281, 211681406, 211681389, 211681318, 211681386, 211681283, 211681449, 211681461, 211681398, 211681311, 211681329, 211681411, 211681401, 211681409};
			anims[15070].anInt363 = 0;
			anims[15070].anInt364 = 0;
			
			anims[15071] = new Animation();
			anims[15071].anInt352 = 16;
			anims[15071].anIntArray355 = new int[] { 4, 3, 3, 4, 4, 3, 2, 2, 1, 1, 2, 2, 3, 3, 3, 1 };
			anims[15071].anIntArray353 = new int[] {211681446, 211681287, 211681384, 211681307, 211681303, 211681299, 211681467, 211681327, 211681420, 211681431, 211681429, 211681363, 211681354, 211681291, 211681348, 211681446};
			anims[15071].anInt359 = 6;
			anims[15071].anInt362 = 1;
			anims[15071].anInt363 = 2;
			anims[15071].anInt364 = 2;
			
			anims[15072] = new Animation();
			anims[15072].anInt352 = 23;
			anims[15072].anIntArray355 = new int[] { 3, 4, 4, 3, 4, 2, 2, 1, 1, 1, 1, 1, 3, 3, 3, 2, 2, 2, 3, 3, 3, 4, 1 };
			anims[15072].anIntArray353 = new int[] {211681356, 211681452, 211681343, 211681436, 211681371, 211681385, 211681415, 211681471, 211681440, 211681317, 211681458, 211681391, 211681407, 211681381, 211681387, 211681300, 211681282, 211681334, 211681342, 211681375, 211681448, 211681377, 211681356};
			anims[15072].anInt359 = 6;
			anims[15072].anInt362 = 1;
			anims[15072].anInt363 = 2;
			anims[15072].anInt364 = 2;
			
			anims[15073] = new Animation();
			anims[15073].anInt352 = 16;
			anims[15073].anIntArray355 = new int[] { 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2 };
			anims[15073].anIntArray353 = new int[] {211681463, 211681464, 211681367, 211681341, 211681321, 211681403, 211681355, 211681289, 211681456, 211681396, 211681423, 211681353, 211681310, 211681320, 211681351, 211681405};
			anims[15073].anInt363 = 2;
			anims[15073].anInt364 = 2;
			
			anims[15074] = new Animation();
			anims[15074].anInt352 = 17;
			anims[15074].anIntArray355 = new int[] { 1, 2, 2, 2, 3, 3, 3, 4, 2, 1, 3, 1, 2, 2, 1, 1, 1 };
			anims[15074].anIntArray353 = new int[] {211681365, 211681466, 211681419, 211681400, 211681427, 211681416, 211681437, 211681462, 211681447, 211681350, 211681308, 211681290, 211681433, 211681399, 211681369, 211681313, 211681365};
			anims[15074].anInt362 = 1;
			anims[15074].anInt363 = 2;
			anims[15074].anInt364 = 2;
			
			anims[15075] = new Animation();
			anims[15075].anInt352 = 16;
			anims[15075].anIntArray355 = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
			anims[15075].anIntArray353 = new int[] {211681338, 211681370, 211681315, 211681372, 211681376, 211681280, 211681397, 211681319, 211681460, 211681306, 211681330, 211681336, 211681438, 211681288, 211681333, 211681426};
			anims[15075].anInt363 = 0;
			anims[15075].anInt364 = 0;
			
			anims[15076] = new Animation();
			anims[15076].anInt352 = 16;
			anims[15076].anIntArray355 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
			anims[15076].anIntArray353 = new int[] {211681337, 211681442, 211681345, 211681378, 211681360, 211681304, 211681459, 211681450, 211681469, 211681364, 211681326, 211681293, 211681294, 211681457, 211681352, 211681402};
			anims[15076].anInt363 = 0;
			anims[15076].anInt364 = 0;
			
			anims[15077] = new Animation();
			anims[15077].anInt352 = 16;
			anims[15077].anIntArray355 = new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
			anims[15077].anIntArray353 = new int[] {211681292, 211681470, 211681434, 211681414, 211681346, 211681413, 211681388, 211681451, 211681404, 211681297, 211681394, 211681340, 211681422, 211681390, 211681380, 211681339};
			anims[15077].anInt363 = 0;
			anims[15077].anInt364 = 0;
    }

    public int method258(int i) {
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

 	public void readValues(Stream stream)
	{
		do {
			int i = stream.readUnsignedByte();
			if(i == 0)
				break;
			if(i == 1) {
				anInt352 = stream.readUnsignedWord();
				anIntArray353 = new int[anInt352];
				anIntArray354 = new int[anInt352];
				anIntArray355 = new int[anInt352];
				for(int i_ = 0; i_ < anInt352; i_++){
					anIntArray353[i_] = stream.readDWord();
					anIntArray354[i_] = -1;
				}
				for(int i_ = 0; i_ < anInt352; i_++)
					anIntArray355[i_] = stream.readUnsignedByte();
			}
			else if(i == 2)
				anInt356 = stream.readUnsignedWord();
			else if(i == 3) {
				int k = stream.readUnsignedByte();
				anIntArray357 = new int[k + 1];
				for(int l = 0; l < k; l++)
					anIntArray357[l] = stream.readUnsignedByte();
				anIntArray357[k] = 0x98967f;
			}
			else if(i == 4)
				aBoolean358 = true;
			else if(i == 5)
				anInt359 = stream.readUnsignedByte();
			else if(i == 6)
				anInt360 = stream.readUnsignedWord();
			else if(i == 7)
				anInt361 = stream.readUnsignedWord();
			else if(i == 8)
				anInt362 = stream.readUnsignedByte();
			else if(i == 9)
				anInt363 = stream.readUnsignedByte();
			else if(i == 10)
				anInt364 = stream.readUnsignedByte();
			else if(i == 11)
				anInt365 = stream.readUnsignedByte();
			else 
				System.out.println("Unrecognized seq.dat config code: "+i);
		} while(true);
		if(anInt352 == 0)
		{
			anInt352 = 1;
			anIntArray353 = new int[1];
			anIntArray353[0] = -1;
			anIntArray354 = new int[1];
			anIntArray354[0] = -1;
			anIntArray355 = new int[1];
			anIntArray355[0] = -1;
		}
		if(anInt363 == -1)
			if(anIntArray357 != null)
				anInt363 = 2;
			else
				anInt363 = 0;
		if(anInt364 == -1)
		{
			if(anIntArray357 != null)
			{
				anInt364 = 2;
				return;
			}
			anInt364 = 0;
		}
	}

    private Animation() {
        anInt356 = -1;
        aBoolean358 = false;
        anInt359 = 5;
        anInt360 = -1; //Removes shield
        anInt361 = -1; //Removes weapon
        anInt362 = 99;
        anInt363 = -1; //Stops character from moving
        anInt364 = -1;
        anInt365 = 1; 
    }

    public static Animation anims[];
    public int anInt352;
    public int anIntArray353[];
    public int anIntArray354[];
    public int[] anIntArray355;
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
}
