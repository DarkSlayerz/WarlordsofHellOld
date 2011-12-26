public final class RSInterface
{
public int transAmount;
    public String popupString;
    public String hoverText;
    public static StreamLoader aClass44;
    public boolean drawsTransparent;
    public Sprite sprite1;
    public int anInt208;
    public Sprite sprites[];
    public static RSInterface interfaceCache[];
    public int anIntArray212[];
    public int contentType;
    public int spritesX[];
    public int anInt216;
    public int atActionType;
    public String spellName;
    public int anInt219;
    public int width;
    public String tooltip;
    public String selectedActionName;
    public boolean centerText;
    public int scrollPosition;
    public String actions[];
    public int valueIndexArray[][];
    public boolean aBoolean227;
    public String aString228;
    public int mOverInterToTrigger;
    public int invSpritePadX;
    public int textColor;
    public int anInt233;
    public int mediaID;
    public boolean aBoolean235;
    public int parentID;
    public int spellUsableOn;
    private static MRUNodes aMRUNodes_238;
    public int anInt239;
    public int children[];
    public int childX[];
    public boolean usableItemInterface;
    public TextDrawingArea textDrawingAreas;
    public int invSpritePadY;
    public int anIntArray245[];
    public int anInt246;
    public int spritesY[];
    public String message;
    public boolean isInventoryInterface;
    public int id;
    public int invStackSizes[];
    public int inv[];
    public byte aByte254;
    private int anInt255;
    private int anInt256;
    public int anInt257;
    public int anInt258;
    public boolean aBoolean259;
    public Sprite sprite2;
    public int scrollMax;
    public int type;
    public int anInt263;
    private static final MRUNodes aMRUNodes_264 = new MRUNodes(30);
    public int anInt265;
    public boolean isMouseoverTriggered;
    public int height;
    public boolean textShadow;
    public int modelZoom;
    public int modelRotation1;
    public int modelRotation2;
    public int childY[];
	
		public static void addHoverText(int id, String text, String tooltip, TextDrawingArea tda[], int idx, int color, boolean center, boolean textShadow, int width) {
		RSInterface rsinterface = addInterface(id);
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.type = 4;
		rsinterface.atActionType = 1;
		rsinterface.width = width;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.aByte254 = 0;
		rsinterface.mOverInterToTrigger = -1;
		rsinterface.centerText = center;
		rsinterface.textShadow = textShadow;
		rsinterface.textDrawingAreas = tda[idx];
		rsinterface.message = text;
		rsinterface.aString228 = "";
		rsinterface.textColor = color;
		rsinterface.anInt219 = 0;
		rsinterface.anInt216 = 0xffffff;
		rsinterface.anInt239 = 0;
		rsinterface.tooltip = tooltip;
	}

	
		public static void questTab(TextDrawingArea[] TDA){
		RSInterface Interface = addInterface(638);
		setChildren(4, Interface);
		addText(29155, "Quests", 0xFF981F, false, true, 52, TDA, 2);
		addButton(29156, 2, "QuestTab/QUEST", 18, 18, "Swap to Information", 1);
		addSprite(29157, 0, "Interfaces/QuestTab/QUEST");
		setBounds(29155, 10, 5, 0, Interface);
		setBounds(29156, 165, 5, 1, Interface);
		setBounds(29157, 3, 24, 2, Interface);
		setBounds(29160, 5, 29, 3, Interface);
		Interface = addInterface(29160);
		Interface.height = 214;
		Interface.width = 165;
		Interface.scrollMax = 1700;
		setChildren(105, Interface);
		addText(29161, "Information:", 0xFF981F, false, true, 52, TDA, 2);
		addHoverText(29162, "Do ::vote for 5M GP!", "Vote by doing ::vote", TDA, 0, 0xff0000, false, true, 150);
		addText(29163, "Register on :", 0xFF981F, false, true, 52, TDA, 2);
		addHoverText(29164, "www.caution-x.net", "Register", TDA, 0, 0xff0000, false, true, 150);
		addText(29165, "Register on :", 0xFF981F, false, true, 52, TDA, 2);
		addHoverText(29166, "www.caution-x.net", "Register", TDA, 0, 0xff0000, false, true, 150);
		setBounds(29161, 4, 4, 0, Interface);
		setBounds(29162, 8, 22, 1, Interface);
		setBounds(29163, 4, 35, 2, Interface);
		setBounds(29164, 8, 53, 3, Interface);
		setBounds(663, 4, 67, 4, Interface);
		int Ypos = 83;
		int frameID = 5;
		for(int iD = 29165; iD <= 29264;iD++){
			addHoverText(iD, "", "Continue"/*"View Quest Journal, "+iD*/, TDA, 0, 0xff0000, false, true, 150);
			setBounds(iD, 8, Ypos, frameID, Interface);
			frameID++;
			Ypos += 15;
			Ypos++;
		}
		Interface = addInterface(29265);
		try {
			setChildren(4, Interface);
			addText(29266, "Coming soon!", 0xFF981F, false, true, -1, TDA, 2);
			addButton(29267, 1, "Interfaces/QuestTab/QUEST", 18, 18, "Swap to Player Info", 1);
			addSprite(29269, 0, "Interfaces/QuestTab/QUEST");
			setBounds(29266, 10, 5, 0, Interface);
			setBounds(29267, 165, 5, 1, Interface);
			setBounds(29269, 3, 24, 2, Interface);
			setBounds(29268, 5, 29, 3, Interface);
			Interface = addInterface(29268);
			Interface.height = 214;
			Interface.width = 165;
			Interface.scrollMax = 1700;
			setChildren(20, Interface);
			setBounds(29295, 8, 4, 0, Interface);
			setBounds(29296, 8, 16, 1, Interface);
			setBounds(29297, 8, 29, 2, Interface);
			setBounds(29298, 8, 42, 3, Interface);
			setBounds(29299, 8, 54, 4, Interface);
			setBounds(29300, 8, 66, 5, Interface);
			setBounds(29301, 8, 78, 6, Interface);
			setBounds(29302, 8, 90, 7, Interface);
			setBounds(29303, 8, 102, 8, Interface);
			setBounds(29304, 8, 114, 9, Interface);
			setBounds(29305, 8, 126, 10, Interface);
			setBounds(29306, 8, 138, 11, Interface);
			setBounds(29307, 8, 150, 12, Interface);
			setBounds(29308, 8, 162, 13, Interface);
			setBounds(29309, 8, 174, 14, Interface);
			setBounds(29310, 8, 186, 15, Interface);
			setBounds(29311, 8, 198, 16, Interface);
			setBounds(29312, 8, 210, 17, Interface);
			setBounds(29313, 8, 222, 18, Interface);
			setBounds(29314, 8, 234, 19, Interface);
			addHoverText(29295, "Please register at", "Please Register", TDA, 1, 0xFF981F, false, true, 150);
			addHoverText(29296, "www.caution-x.net", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29297, "And advertise/vote daily!", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29298, "::vote for more players!", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29299, "More players=More updates!", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29300, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29301, "", "", TDA, 1, 0xFF981F, false, true, 150);
			addHoverText(29302, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29303, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29304, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29305, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29306, "", "", TDA,1, 0xFF981F, false, true, 150);
			addHoverText(29307, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29308, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29309, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29310, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29311, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29312, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29313, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29314, "", "", TDA, 0, 0xff0000, false, true, 150);
			} catch(Exception e){
				e.printStackTrace();
		}	
	}

public static void DonatorTab(TextDrawingArea[] TDA) {
		RSInterface tab = addTabInterface(19050);
		addSprite(19051, 1, "Interfaces/DonorTab/DONATOR");
		addHover(19052, 1, 0, 19151, 3, "Interfaces/DonorTab/BOX", 40, 39, "Full GWD KillCount");
		addHover(19053, 1, 0, 19151, 5, "Interfaces/DonorTab/BOX", 40, 39, "Donor Zone");
		addHover(19054, 1, 0, 19151, 6, "Interfaces/DonorTab/COINS", 32, 29, "Vote For Caution-X");
		addHover(19055, 3, 0, 19151, 7, "Interfaces/DonorTab/CLOSE", 95, 29, "Close");
		addHover(19056, 1, 0, 19151, 9, "Interfaces/DonorTab/BOX", 40, 39, "Recover Special");
		addHover(19057, 1, 0, 19151, 4, "Interfaces/DonorTab/BOX", 40, 39, "Change SpellBooks");
		addHover(19058, 1, 0, 19151, 8, "Interfaces/DonorTab/BOX", 95, 29, "Donate For Items And More!");
		addHover(19059, 1, 0, 19151, 10, "Interfaces/DonorTab/BOX", 40, 39, "Teleport to Frost Dragons!");
		addHover(19060, 1, 0, 19151, 11, "Interfaces/DonorTab/BOX", 40, 39, "Choose A Reward Of 3M Xp In Any Skill!");
		tab.totalChildren(10);
		tab.child(0, 19051, 10, 15);
		tab.child(1, 19052, 86, 165);
		tab.child(2, 19053, 135, 165);
		tab.child(3, 19054, 284, 165);
		tab.child(4, 19055, 310, 230);
		tab.child(5, 19056, 234, 165);
		tab.child(6, 19057, 184, 165);
		tab.child(7, 19058, 110, 230);
		tab.child(8, 19059, 334, 165);
		tab.child(9, 19060, 384, 165);

	}
	public static void Classes(TextDrawingArea[] TDA) {
		RSInterface tab = addTabInterface(17050);
		addSprite(17051, 1, "Interfaces/Classes/CHOOSE");
		addHover(17052, 1, 0, 19151, 1, "Interfaces/Classes/MAGIC", 134, 180, "Choose Melee As A Class");
		addHover(17053, 1, 0, 19151, 3, "Interfaces/Classes/MELEE", 134, 180, "Choose Melee As A Class");
		addHover(17054, 1, 0, 19151, 2, "Interfaces/Classes/RANGE", 134, 180, "Choose Range As A Class");
		tab.totalChildren(4);
		tab.child(0, 17051, 10, 15);
		tab.child(1, 17052, 30, 78);
		tab.child(2, 17053, 182, 78);
		tab.child(3, 17054, 334, 78);
		}
		public static void Dungeoneering(TextDrawingArea[] TDA) {
		RSInterface tab = addTabInterface(18070);
		addSprite(18051, 1, "Interfaces/Dungeoneering/DONATOR");
		addHover(18052, 1, 0, 19151, 1, "Interfaces/Dungeoneering/CROSSBOW", 46, 50, "Buy 1 Chaotic Crossbow");
		addHover(18053, 1, 0, 19151, 2, "Interfaces/Dungeoneering/SHIELD", 46, 50, "Buy 1 Chaotic Shield");
		addHover(18054, 1, 0, 19151, 3, "Interfaces/Dungeoneering/STAFF", 46, 50, "Buy 1 Chaotic Staff");
		addHover(18055, 1, 0, 19151, 4, "Interfaces/Dungeoneering/MAUL", 46, 50, "Buy 1 Chaotic Maul");
		addHover(18056, 1, 0, 19151, 5, "Interfaces/Dungeoneering/LONGSWORD", 46, 50, "Buy 1 Chaotic Longsword");
		addHover(18057, 1, 0, 19151, 6, "Interfaces/Dungeoneering/RAPIER", 46, 50, "Buy 1 Chaotic Rapier");
		addHover(18058, 1, 0, 19151, 7, "Interfaces/Dungeoneering/EXPERIENCE", 46, 50, "Buy Dungeoneering Experience");
		addHover(18059, 1, 0, 19151, 8, "Interfaces/Dungeoneering/STREAM", 46, 50, "Buy 1 Arcane Steam Necklace");
		addHover(18060, 1, 0, 19151, 9, "Interfaces/Dungeoneering/RIGOUR", 46, 50, "Buy 1 Ring Of Vigour");
		addHover(18061, 1, 0, 19151, 10, "Interfaces/Dungeoneering/GRAPIER", 46, 50, "Buy 1 Gravite Rapier");
		addHover(18062, 1, 0, 19151, 11, "Interfaces/Dungeoneering/GHSWORD", 46, 50, "Buy 1 Gravite 2H Sword");
		addHover(18063, 1, 0, 19151, 12, "Interfaces/Dungeoneering/GSTAFF", 46, 50, "Buy 1 Gravite Staff");
		addHover(18064, 1, 0, 19151, 13, "Interfaces/Dungeoneering/GSHORT", 46, 50, "Buy 1 Gravite Shortbow");
		addHover(18065, 1, 0, 19151, 14, "Interfaces/Dungeoneering/GSHORT", 46, 50, "Buy 1 Gravite Longsword");
		addHover(18066, 1, 0, 19151, 15, "Interfaces/Dungeoneering/BUY", 132, 25, "Buy");
		addHover(18067, 1, 0, 19151, 16, "Interfaces/Dungeoneering/EAGLE", 46, 50, "Buy 1 Eagle-Eye Shield");
		addHover(18068, 1, 0, 19151, 17, "Interfaces/Dungeoneering/FARSEER", 46, 50, "Buy 1 Farseer Shield");
		addHover(18069, 3, 0, 19151, 18, "Interfaces/Dungeoneering/CLOSE", 16, 16, "Close");
		addText(18071, "", 0xFF981F, false, true, 52, TDA, 2);
			
		tab.totalChildren(20);
		tab.child(0, 18051, 4, 4);
		tab.child(1, 18052, 17, 234);
		tab.child(2, 18053, 78, 234);
		tab.child(3, 18054, 257, 167);
		tab.child(4, 18055, 196, 167);
		tab.child(5, 18056, 136, 167);
		tab.child(6, 18057, 79, 167);
		tab.child(7, 18058, 258, 234);
		tab.child(8, 18059, 18, 167);
		tab.child(9, 18060, 256, 100);
		tab.child(10, 18061, 19, 32);
		tab.child(11, 18062, 139, 34);
		tab.child(12, 18063, 199, 33);
		tab.child(13, 18064, 258, 34);
		tab.child(14, 18065, 79, 34);
		tab.child(15, 18066, 347, 280);
		tab.child(16, 18067, 136, 235);
		tab.child(17, 18068, 195, 234);
		tab.child(18, 18069, 482, 6);
		tab.child(19, 18071, 134, 300);

	}
public static void LevelUp(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(19499);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19501, 1, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19501, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19502);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19503, 2, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19503, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19504);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19505, 3, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19505, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19506);
        		addSprite(29500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(29507, 4, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(29500, 200, 85, 0, Interface);//Level Up
			setBounds(29507, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19508);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19509, 5, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19509, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19510);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19511, 6, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19511, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19512);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19513, 7, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19513, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19514);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19515, 8, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19515, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19516);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19517, 9, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19517, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19518);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19519, 10, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19519, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19520);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19521, 11, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19521, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19522);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19523, 12, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19523, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19524);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19525, 13, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19525, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19526);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19527, 14, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19527, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19528);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19529, 15, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19529, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19530);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19531, 16, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19531, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19532);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19533, 17, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19533, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19534);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19535, 18, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19535, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19536);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19537, 19, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19537, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19538);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19539, 20, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19539, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19540);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19541, 21, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19541, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19542);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19543, 0, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19543, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19544);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19545, 22, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19545, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19546);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19547, 23, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19547, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19548);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19549, 24, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19549, 177, 85, 1, Interface);//Skill icon

		Interface = addInterface(19550);
        		addSprite(19500, 0, "Levelup/LEVELUP");//Level Up
        		addSprite(19551, 25, "Levelup/SKILL");//Skill icon
			setChildren(2, Interface);
			setBounds(19500, 200, 85, 0, Interface);//Level Up
			setBounds(19551, 177, 85, 1, Interface);//Skill icon

	}

public static void Skillcapes(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(29560);
			setChildren(56, Interface);
			addHoverButton(29561, "Interfaces/Skillcapes/CLOSE", 1, 16, 16, "Close", 0, 29562, 1);//CLOSE
        		addHoveredButton(29562, "Interfaces/Skillcapes/CLOSE", 2, 16, 16, 29563);//CLOSE HOVER
        		addButton(29564, 1, "Interfaces/Skillcapes/SKILLCAPES", 271, 44, "Skillcape Shop", 1);//TITLE
        		addSprite(29565, 0, "Interfaces/Skillcapes/SKILLCAPES");//BACKGROUND

			addHoverButton(29566, "Interfaces/Skillcapes/HOVER", 1, 25, 40, "Fishing Cape", 0, 29567, 1);
        		addHoveredButton(29567, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29568);
			addHoverButton(29569, "Interfaces/Skillcapes/HOVER", 2, 25, 40, "Fletching Cape", 0, 29570, 1);
        		addHoveredButton(29570, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29571);
			addHoverButton(29572, "Interfaces/Skillcapes/HOVER", 3, 25, 40, "Firemaking Cape", 0, 29573, 1);
        		addHoveredButton(29573, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29574);
			addHoverButton(29575, "Interfaces/Skillcapes/HOVER", 4, 25, 40, "Herblore Cape", 0, 29576, 1);
        		addHoveredButton(29576, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29577);
			addHoverButton(29578, "Interfaces/Skillcapes/HOVER", 5, 25, 40, "Hitpoints Cape", 0, 29579, 1);
        		addHoveredButton(29579, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29580);
			addHoverButton(29581, "Interfaces/Skillcapes/HOVER", 6, 25, 40, "Hunter Cape", 0, 29582, 1);
        		addHoveredButton(29582, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29583);

			addHoverButton(29584, "Interfaces/Skillcapes/HOVER", 7, 25, 40, "Magic Cape", 0, 29585, 1);
        		addHoveredButton(29585, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29586);
			addHoverButton(29587, "Interfaces/Skillcapes/HOVER", 8, 25, 40, "Mining Cape", 0, 29588, 1);
        		addHoveredButton(29588, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29589);
			addHoverButton(29590, "Interfaces/Skillcapes/HOVER", 9, 25, 40, "Prayer Cape", 0, 29591, 1);
        		addHoveredButton(29591, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29592);
			addHoverButton(29593, "Interfaces/Skillcapes/HOVER", 10, 25, 40, "Quest Cape", 0, 29594, 1);
        		addHoveredButton(29594, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29595);
			addHoverButton(29596, "Interfaces/Skillcapes/HOVER", 11, 25, 40, "Ranged Cape", 0, 29597, 1);
        		addHoveredButton(29597, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29598);
			addHoverButton(29599, "Interfaces/Skillcapes/HOVER", 12, 25, 40, "Runecrafting Cape", 0, 29600, 1);
        		addHoveredButton(29600, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29601);

			addHoverButton(29602, "Interfaces/Skillcapes/HOVER", 13, 25, 40, "Slayer Cape", 0, 29603, 1);
        		addHoveredButton(29603, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29604);
			addHoverButton(29605, "Interfaces/Skillcapes/HOVER", 14, 25, 40, "Smithing Cape", 0, 29606, 1);
        		addHoveredButton(29606, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29607);
			addHoverButton(29608, "Interfaces/Skillcapes/HOVER", 15, 25, 40, "Strength Cape", 0, 29609, 1);
        		addHoveredButton(29609, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29610);
			addHoverButton(29611, "Interfaces/Skillcapes/HOVER", 16, 25, 40, "Thieving Cape", 0, 29612, 1);
        		addHoveredButton(29612, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29613);
			addHoverButton(29614, "Interfaces/Skillcapes/HOVER", 17, 25, 40, "Woodcutting Cape", 0, 29615, 1);
        		addHoveredButton(29615, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29616);
			addHoverButton(29617, "Interfaces/Skillcapes/HOVER", 18, 25, 40, "Agility Cape", 0, 29618, 1);
        		addHoveredButton(29618, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29619);

			addHoverButton(29620, "Interfaces/Skillcapes/HOVER", 19, 25, 40, "Attack Cape", 0, 29621, 1);
        		addHoveredButton(29621, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29622);
			addHoverButton(29623, "Interfaces/Skillcapes/HOVER", 20, 25, 40, "Dungeoneering Cape", 0, 29624, 1);
        		addHoveredButton(29624, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29625);
			addHoverButton(29626, "Interfaces/Skillcapes/HOVER", 21, 25, 40, "Cooking Cape", 0, 29627, 1);
        		addHoveredButton(29627, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29628);
			addHoverButton(29629, "Interfaces/Skillcapes/HOVER", 22, 25, 40, "Crafting Cape", 0, 29630, 1);
        		addHoveredButton(29630, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29631);
			addHoverButton(29632, "Interfaces/Skillcapes/HOVER", 23, 25, 40, "Defence Cape", 0, 29633, 1);
        		addHoveredButton(29633, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29634);
			addHoverButton(29635, "Interfaces/Skillcapes/HOVER", 24, 25, 40, "Herblore Cape", 0, 29636, 1);
        		addHoveredButton(29636, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29637);

			addHoverButton(29638, "Interfaces/Skillcapes/HOVER", 25, 25, 40, "Summoning Cape", 0, 29639, 1);
        		addHoveredButton(29639, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29640);
			addHoverButton(29641, "Interfaces/Skillcapes/HOVER", 26, 25, 40, "Construction Cape", 0, 29642, 1);
        		addHoveredButton(29642, "Interfaces/Skillcapes/HOVERED", 0, 25, 40, 29643);


			setBounds(29561, 474, 11, 1, Interface);//CLOSE
			setBounds(29562, 474, 11, 2, Interface);//CLOSE HOVER
			setBounds(29564, 177, 18, 3, Interface);//TITLE
			setBounds(29565, 10, 5, 0, Interface);//BACKOGRUND X Y

			setBounds(29566, 35, 72, 4, Interface);//CAPE
			setBounds(29567, 35, 72, 5, Interface);//CAPE HOVER
			setBounds(29569, 116, 72, 6, Interface);//CAPE
			setBounds(29570, 116, 72, 7, Interface);//CAPE HOVER
			setBounds(29572, 199, 72, 8, Interface);//CAPE
			setBounds(29573, 199, 72, 9, Interface);//CAPE HOVER
			setBounds(29575, 281, 72, 10, Interface);//CAPE
			setBounds(29576, 281, 72, 11, Interface);//CAPE HOVER
			setBounds(29578, 363, 72, 12, Interface);//CAPE
			setBounds(29579, 363, 72, 13, Interface);//CAPE HOVER
			setBounds(29581, 445, 72, 14, Interface);//CAPE
			setBounds(29582, 445, 72, 15, Interface);//CAPE HOVER

			setBounds(29584, 35, 132, 16, Interface);//CAPE
			setBounds(29585, 35, 132, 17, Interface);//CAPE HOVER
			setBounds(29587, 116, 132, 18, Interface);//CAPE
			setBounds(29588, 116, 132, 19, Interface);//CAPE HOVER
			setBounds(29590, 199, 132, 20, Interface);//CAPE
			setBounds(29591, 199, 132, 21, Interface);//CAPE HOVER
			setBounds(29593, 281, 132, 22, Interface);//CAPE
			setBounds(29594, 281, 132, 23, Interface);//CAPE HOVER
			setBounds(29596, 363, 132, 24, Interface);//CAPE
			setBounds(29597, 363, 132, 25, Interface);//CAPE HOVER
			setBounds(29599, 445, 132, 26, Interface);//CAPE
			setBounds(29600, 445, 132, 27, Interface);//CAPE HOVER

			setBounds(29602, 35, 192, 28, Interface);//CAPE
			setBounds(29603, 35, 192, 29, Interface);//CAPE HOVER
			setBounds(29605, 116, 192, 30, Interface);//CAPE
			setBounds(29606, 116, 192, 31, Interface);//CAPE HOVER
			setBounds(29608, 199, 192, 32, Interface);//CAPE
			setBounds(29609, 199, 192, 33, Interface);//CAPE HOVER
			setBounds(29611, 281, 192, 34, Interface);//CAPE
			setBounds(29612, 281, 192, 35, Interface);//CAPE HOVER
			setBounds(29614, 363, 192, 36, Interface);//CAPE
			setBounds(29615, 363, 192, 37, Interface);//CAPE HOVER
			setBounds(29617, 445, 192, 38, Interface);//CAPE
			setBounds(29618, 445, 192, 39, Interface);//CAPE HOVER

			setBounds(29620, 35, 252, 40, Interface);//CAPE
			setBounds(29621, 35, 252, 41, Interface);//CAPE HOVER
			setBounds(29623, 116, 252, 42, Interface);//CAPE
			setBounds(29624, 116, 252, 43, Interface);//CAPE HOVER
			setBounds(29626, 199, 252, 44, Interface);//CAPE
			setBounds(29627, 199, 252, 45, Interface);//CAPE HOVER
			setBounds(29629, 281, 252, 46, Interface);//CAPE
			setBounds(29630, 281, 252, 47, Interface);//CAPE HOVER
			setBounds(29632, 363, 252, 48, Interface);//CAPE
			setBounds(29633, 363, 252, 49, Interface);//CAPE HOVER
			setBounds(29635, 445, 252, 50, Interface);//CAPE
			setBounds(29636, 445, 252, 51, Interface);//CAPE HOVER

			setBounds(29638, 35, 18, 52, Interface);//CAPE
			setBounds(29639, 35, 18, 53, Interface);//CAPE HOVER
			setBounds(29641, 116, 18, 54, Interface);//CAPE
			setBounds(29642, 116, 18, 55, Interface);//CAPE HOVER
	}

public static void FightCave(TextDrawingArea[] tda) {
		RSInterface RSinterface = addInterface(21400);
		addSprite(21445, 0, "Interfaces/Other/FIGHTCAVE1");
		addText(21450, "", tda, 1, 0xff9040, true, true);
		int last = 2;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21445, 440, 5, 0,RSinterface);
		setBounds(21450, 470, 9, 1,RSinterface);
	}
public static void EasyTask(TextDrawingArea[] TDA) {
		RSInterface tab = addTabInterface(15050);
		addSprite(15051, 1, "Interfaces/Achievement/EASYTASK");
		addHover(15052, 3, 0, 19151, 3, "Interfaces/Welcome/BOX", 115, 33, "Accept");
		addHover(15053, 1, 0, 19151, 8, "Interfaces/Achievement/BOX", 123, 29, "Claim Your Reward");
		tab.totalChildren(3);
		tab.child(0, 15051, 10, 15);
		tab.child(1, 15052, 110, 230);
		tab.child(2, 15053, 215, 230);

	}
public static void MediumTask(TextDrawingArea[] TDA) {
		RSInterface tab = addTabInterface(14050);
		addSprite(14051, 1, "Interfaces/Achievement/MEDIUMTASK");
		addHover(14052, 3, 0, 19151, 3, "Interfaces/Welcome/ACCEPT", 115, 33, "Accept");
		addHover(14053, 1, 0, 19151, 8, "Interfaces/Achievement/BOX", 123, 29, "Claim Your Reward");
		tab.totalChildren(3);
		tab.child(0, 14051, 10, 15);
		tab.child(1, 14052, 110, 230);
		tab.child(2, 15053, 170, 230);

	}
public static void HardTask(TextDrawingArea[] TDA) {
		RSInterface tab = addTabInterface(16050);
		addSprite(16051, 1, "Interfaces/Achievement/HARDTASK");
		addHover(16052, 3, 0, 19151, 3, "Interfaces/Welcome/ACCEPT", 115, 33, "Accept");
		addHover(16053, 1, 0, 19151, 8, "Interfaces/Achievement/BOX", 123, 29, "Claim Your Reward");
		tab.totalChildren(3);
		tab.child(0, 16051, 10, 15);
		tab.child(1, 16052, 110, 230);
		tab.child(2, 16053, 170, 230);

	}


public static void achievement(TextDrawingArea[] TDA) {
        RSInterface tab = addTabInterface(17000);
		RSInterface scroll = addTabInterface(17001);
        addText(17002, "Caution-X Achievements", TDA, 2, 0xFF9900, false, true);
		addSprite(17003, 0, "Interfaces/Achieve/ACH");
		addSprite(17004, 3, "Interfaces/Achieve/ACH");
		addSprite(17005, 0, "Interfaces/Achieve/ACH");
		addButton(17090, 1, "QuestTab/QUEST", 18, 18, "Swap To Quest Tab", 1);
        tab.totalChildren(6);
        tab.child(0, 17002, 5, 5);
		tab.child(1, 17003, 0, 25);
		tab.child(2, 17004, 0, 28);
		tab.child(3, 17005, 0, 249);
        	tab.child(4, 17001, 0, 25);
		tab.child(5, 17090, 165, 3);
		scroll.width = 174; scroll.height = 224; scroll.scrollMax = 1250;
		
		addHoverText(17049, "Information", "Read", TDA, 1, 0xFF9900, false, true, 150);
		addText(17006, "Quests", TDA, 2, 0xFF9900, false, true);
		addHoverText(17007, "Ring Of Reincarnation", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17008, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17009, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17010, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17011, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17012, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17013, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17014, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17015, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addText(17016, "Player Killing", TDA, 2, 0xFF9900, false, true);
		addHoverText(17017, "Kill 10 Players", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17018, "Kill 25 Players", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17019, "Kill 50 Players", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17020, "Kill 100 Players", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17021, "Kill 200 Players", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17022, "Kill 350 Players", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17023, "Kill 500 Players", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17024, "Kill 750 Players", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17025, "Kill 1000 Players", "View", TDA, 0, 0xff0000, false, true, 150);
		//addText(17026, "Creations", "View", TDA, 2, 0xff0000, false, true, 150);
		addText(17026, "NPC's", TDA, 2, 0xFF9900, false, true);
		addHoverText(17027, "Defeat the King Black Dragon", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17028, "Defeat the Chaos Elemental", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17029, "Defeat the Corporal Beast", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17030, "Defeat a Tormented Demon", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17031, "Complete 5 Slayer tasks", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17032, "Complete 10 Slayer tasks", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17033, "Complete 25 Slayer tasks", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17034, "Complete 50 Slayer tasks", "View", TDA, 0, 0xff0000, false, true, 150);
		addText(17035, "Minigames", TDA, 2, 0xFF9900, false, true);
		addHoverText(17036, "Win one game of Pest Control", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17037, "Win five games of Pest Control", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17038, "Complete Barrow once", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17039, "Complete Barrows five times", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17040, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17041, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17042, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17043, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17044, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17045, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17046, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17047, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(17048, "", "View", TDA, 0, 0xff0000, false, true, 150);
		scroll.totalChildren(44);
		scroll.child(0, 17007, 5, 47);
		scroll.child(1, 17008, 5, 61);
		scroll.child(2, 17009, 5, 75);
		scroll.child(3, 17010, 5, 89);
		scroll.child(4, 17011, 5, 103);
		scroll.child(5, 17006, 5, 20);
		scroll.child(6, 17012, 5, 117);
		scroll.child(7, 17013, 5, 131);
		scroll.child(8, 17014, 5, 145);
		scroll.child(9, 17015, 5, 159);
		scroll.child(10, 17016, 5, 187);
		scroll.child(11, 17017, 5, 215);
		scroll.child(12, 17018, 5, 229);
		scroll.child(13, 17019, 5, 243);
		scroll.child(14, 17020, 5, 257);
		scroll.child(15, 17021, 5, 271);
		scroll.child(16, 17022, 5, 285);
		scroll.child(17, 17023, 5, 299);
		scroll.child(18, 17024, 5, 313);
		scroll.child(19, 17025, 5, 327);
		scroll.child(20, 17026, 5, 350);
		scroll.child(21, 17027, 5, 380);
		scroll.child(22, 17028, 5, 393);
		scroll.child(23, 17029, 5, 411);
		scroll.child(24, 17030, 5, 424);
		scroll.child(25, 17031, 5, 439);
		scroll.child(26, 17032, 5, 453);
		scroll.child(27, 17033, 5, 467);
		scroll.child(28, 17034, 5, 481);
		scroll.child(29, 17035, 5, 509);
		scroll.child(30, 17036, 5, 537);
		scroll.child(31, 17037, 5, 551);
		scroll.child(32, 17038, 5, 565);
		scroll.child(33, 17039, 5, 579);
		scroll.child(34, 17040, 5, 593);
		scroll.child(35, 17041, 5, 607);
		scroll.child(36, 17042, 5, 621);
		scroll.child(37, 17043, 5, 635);
		scroll.child(38, 17044, 5, 649);
		scroll.child(39, 17045, 5, 663);
		scroll.child(40, 17046, 5, 677);
		scroll.child(41, 17047, 5, 691);
		scroll.child(42, 17048, 5, 705);
		scroll.child(43, 17049, 5, 6);
       }		
public static void teleport(TextDrawingArea[] TDA) {
      RSInterface localRSInterface = addInterface(17650);
    addSprite(17651, 10, "CLICK");
    addHoverButton(17652, "CLICK", 2, 200, 30, "Which Zone?", -1, 17653, 1);
    addHoveredButton(17653, "CLICK", 2, 200, 30, 17654);
    addHoverButton(17655, "CLICK", 3, 200, 30, "Which Zone?", -1, 17656, 1);
    addHoveredButton(17656, "CLICK", 3, 200, 30, 17657);
    addHoverButton(17658, "CLICK", 3, 200, 30, "Which Zone?", -1, 17659, 1);
    addHoveredButton(17659, "CLICK", 3, 200, 30, 17660);
    addHoverButton(17661, "CLICK", 3, 200, 30, "Which Zone?", -1, 17662, 1);
    addHoveredButton(17662, "CLICK", 3, 200, 30, 17663);
    addHoverButton(17664, "CLICK", 3, 200, 30, "Which Zone?", -1, 17665, 1);
    addHoveredButton(17665, "CLICK", 3, 200, 30, 17666);
    addHoverButton(17667, "CLICK", 3, 200, 30, "Which Zone?", -1, 17668, 1);
    addHoveredButton(17668, "CLICK", 3, 200, 30, 17669);
    addHoverButton(17670, "CLICK", 3, 200, 30, "Which Zone?", -1, 17671, 1);
    addHoveredButton(17671, "CLICK", 3, 200, 30, 17672);
    addHoverButton(17673, "CLICK", 1, 200, 30, "Stop Viewing", -1, 17674, 1);
    addHoveredButton(17674, "CLICK", 1, 200, 30, 17675);
    addText(22804, "Rock Crabs", TDA, 0, 16750623, false, true);
    addText(22808, "Taverly Dungeon", TDA, 0, 16750623, false, true);
    addText(22812, "Slayer Tower", TDA, 0, 16750623, false, true);
    addText(22816, "Brimhaven Dungeon", TDA, 0, 16750623, false, true);
    addText(22820, "Hill Giants", TDA, 0, 16750623, false, true);
    addText(22824, "Experiments", TDA, 0, 16750623, false, true);
    addText(22828, "Bandits", TDA, 0, 16750623, false, true);
    localRSInterface.totalChildren(24);
    localRSInterface.child(0, 17651, 0, 0);
    localRSInterface.child(1, 17652, 12, 40);
    localRSInterface.child(2, 17653, 11, 40);
    localRSInterface.child(3, 17655, 12, 65);
    localRSInterface.child(4, 17656, 11, 65);
    localRSInterface.child(5, 17658, 12, 90);
    localRSInterface.child(6, 17659, 11, 90);
    localRSInterface.child(7, 17661, 12, 115);
    localRSInterface.child(8, 17662, 11, 115);
    localRSInterface.child(9, 17664, 12, 143);
    localRSInterface.child(10, 17665, 11, 143);
    localRSInterface.child(11, 17667, 12, 168);
    localRSInterface.child(12, 17668, 11, 168);
    localRSInterface.child(13, 17670, 12, 193);
    localRSInterface.child(14, 17671, 11, 193);
    localRSInterface.child(15, 17673, 38, 236);
    localRSInterface.child(16, 17674, 38, 236);
    localRSInterface.child(17, 22804, 38, 45);
    localRSInterface.child(18, 22808, 38, 70);
    localRSInterface.child(19, 22812, 38, 95);
    localRSInterface.child(20, 22816, 38, 120);
    localRSInterface.child(21, 22820, 38, 147);
    localRSInterface.child(22, 22824, 38, 174);
    localRSInterface.child(23, 22828, 38, 201);
    localRSInterface = addTabInterface(14000);
    localRSInterface.width = 474;
    localRSInterface.height = 213;
    localRSInterface.scrollMax = 305;
    for (int i = 14001; i <= 14030; ++i) {
      addText(i, "", TDA, 1, 16777215, false, true);
    }
    localRSInterface.totalChildren(30);
    int i = 0;
    int j = 5;
    for (int k = 14001; k <= 14030; ++k) {
      localRSInterface.child(i, k, 248, j);
      ++i;
      j += 13;
    }
  }

    public void swapInventoryItems(int i, int j)
    {
        int k = inv[i];
        inv[i] = inv[j];
        inv[j] = k;
        k = invStackSizes[i];
        invStackSizes[i] = invStackSizes[j];
        invStackSizes[j] = k;
    }

	public static void ColorChanger(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(31000);
		addSprite(31001, "PrivateChat/Private");
		addSprite(31057, "sample 0");
		addHoverButton(31002, "PrivateChat/Color 0", 16, 16, "Use", -1, 31003, 1);
		addHoveredButton(31003, "PrivateChat/GoldBox", 19, 20, 31004);
		addHoverButton(31005, "PrivateChat/Color 1", 16, 16, "Use", -1, 31006, 1);
		addHoveredButton(31006, "PrivateChat/GoldBox", 19, 20, 31007);
		addHoverButton(31008, "PrivateChat/Color 2", 16, 16, "Use", -1, 31009, 1);
		addHoveredButton(31009, "PrivateChat/GoldBox", 19, 20, 31010);
		addHoverButton(31011, "PrivateChat/Color 3", 16, 16, "Use", -1, 31012, 1);
		addHoveredButton(31012, "PrivateChat/GoldBox", 19, 20, 31013);
		addHoverButton(31014, "PrivateChat/Color 4", 16, 16, "Use", -1, 31015, 1);
		addHoveredButton(31015, "PrivateChat/GoldBox", 19, 20, 31016);
		addHoverButton(31017, "PrivateChat/Color 5", 16, 16, "Use", -1, 31018, 1);
		addHoveredButton(31018, "PrivateChat/GoldBox", 19, 20, 31019);
		addHoverButton(31020, "PrivateChat/Color 6", 16, 16, "Use", -1, 31021, 1);
		addHoveredButton(31021, "PrivateChat/GoldBox", 19, 20, 31022);
		addHoverButton(31023, "PrivateChat/Color 7", 16, 16, "Use", -1, 31024, 1);
		addHoveredButton(31024, "PrivateChat/GoldBox", 19, 20, 31025);
		addHoverButton(31026, "PrivateChat/Color 8", 16, 16, "Use", -1, 31027, 1);
		addHoveredButton(31027, "PrivateChat/GoldBox", 19, 20, 31028);
		addHoverButton(31029, "PrivateChat/Color 9", 16, 16, "Use", -1, 31030, 1);
		addHoveredButton(31030, "PrivateChat/GoldBox", 19, 20, 31031);
		addHoverButton(31032, "PrivateChat/Color 10", 16, 16, "Use", -1, 31033, 1);
		addHoveredButton(31033, "PrivateChat/GoldBox", 19, 20, 31034);
		addHoverButton(31035, "PrivateChat/Color 11", 16, 16, "Use", -1, 31036, 1);
		addHoveredButton(31036, "PrivateChat/GoldBox", 19, 20, 31037);
		addHoverButton(31038, "PrivateChat/Color 12", 16, 16, "Use", -1, 31039, 1);
		addHoveredButton(31039, "PrivateChat/GoldBox", 19, 20, 31040);
		addHoverButton(31041, "PrivateChat/Color 13", 16, 16, "Use", -1, 31042, 1);
		addHoveredButton(31042, "PrivateChat/GoldBox", 19, 20, 31043);
		addHoverButton(31044, "PrivateChat/Color 14", 16, 16, "Use", -1, 31045, 1);
		addHoveredButton(31045, "PrivateChat/GoldBox", 19, 20, 31046);
		addHoverButton(31047, "PrivateChat/X", 16, 15, "Close", -1, 31048, 1);
		addHoveredButton(31048, "PrivateChat/HX", 16, 15, 31049);
		addToggleButton(31050, 0, 2, "PrivateChat/CHECK", "Toggle", 1, 4, 500);
		addHoverButton(31054, "close", 0, 200, 30, "Back", -1, 31055, 1);
		addHoveredButton(31055, "close", 1, 200, 30, 31056);
		addText(31059, "Sample Text", client.getChatColor(), false, true, -1, tda, 0);
		tab.totalChildren(38);
		tab.child(0, 31001, 0, 2);
		tab.child(1, 31002, 26, 119);
		tab.child(2, 31003, 25, 117);
		tab.child(3, 31005, 56, 119);
		tab.child(4, 31006, 55, 117);
		tab.child(5, 31008, 86, 119);
		tab.child(6, 31009, 85, 117);
		tab.child(7, 31011, 116, 119);
		tab.child(8, 31012, 115, 117);
		tab.child(9, 31014, 146, 119);
		tab.child(10, 31015, 145, 117);
		tab.child(11, 31017, 26, 149);
		tab.child(12, 31018, 25, 147);
		tab.child(13, 31020, 56, 149);
		tab.child(14, 31021, 55, 147);
		tab.child(15, 31023, 86, 149);
		tab.child(16, 31024, 85, 147);
		tab.child(17, 31026, 116, 149);
		tab.child(18, 31027, 115, 147);
		tab.child(19, 31029, 146, 149);
		tab.child(20, 31030, 145, 147);
		tab.child(21, 31032, 26, 179);
		tab.child(22, 31033, 25, 177);
		tab.child(23, 31035, 56, 179);
		tab.child(24, 31036, 55, 177);
		tab.child(25, 31038, 86, 179);
		tab.child(26, 31039, 85, 177);
		tab.child(27, 31041, 116, 179);
		tab.child(28, 31042, 115, 177);
		tab.child(29, 31044, 146, 179);
		tab.child(30, 31045, 145, 177);
		tab.child(31, 31047, 167, 10);
		tab.child(32, 31048, 167, 10);
		tab.child(33, 31050, 167, 50);
		tab.child(34, 31054, 170, 5);
		tab.child(35, 31055, 170, 5);
		tab.child(36, 31057, 20, 220);
		tab.child(37, 31059, 64, 225);
	}

private static Sprite imageLoader(String s) {
		long l = (TextClass.method585(s) << 8);
		Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
		if (sprite != null)
			return sprite;
		try {
			sprite = new Sprite(s);
			aMRUNodes_238.removeFromCache(sprite, l);
		} catch (Exception exception) {
			return null;
		}
		return sprite;
	}
	public static void addHoveredButton(int i, String imageName, int w, int h, int IMAGEID) {
		RSInterface tab = addTabInterface(i);
		tab.parentID = i;
		tab.id = i;
		tab.type = 0;
		tab.atActionType = 0;
		tab.width = w;
		tab.height = h;
		tab.isMouseoverTriggered = true;
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = -1;
		tab.scrollMax = 0;
		addHoverImage(IMAGEID, imageName);
		tab.totalChildren(1);
		tab.child(0, IMAGEID, 0, 0);
	}
public static void addHoverImage(int i, String name) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 334;
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(name);
		tab.sprite2 = imageLoader(name);
	}
	public static void resetRunesAndLevels(int id){
                RSInterface rsInterface = interfaceCache[id];
                rsInterface.anIntArray245 = new int[4];
                rsInterface.anIntArray212 = new int[4];
                rsInterface.anIntArray245[0] = 3;
                rsInterface.anIntArray212[0] = 565;
                rsInterface.anIntArray245[1] = 3;
                rsInterface.anIntArray212[1] = 560;
                rsInterface.anIntArray245[2] = 3;
                rsInterface.anIntArray212[2] = 555;
                rsInterface.anIntArray245[3] = 3;
                rsInterface.anIntArray212[3] = 1;
               
                rsInterface.valueIndexArray = new int[4][];
                rsInterface.valueIndexArray[0] = new int[4];
                rsInterface.valueIndexArray[0][0] = 4;
                rsInterface.valueIndexArray[0][1] = 3214;
                rsInterface.valueIndexArray[0][2] = 0;
                rsInterface.valueIndexArray[0][3] = 0;
                rsInterface.valueIndexArray[1] = new int[4];
                rsInterface.valueIndexArray[1][0] = 4;
                rsInterface.valueIndexArray[1][1] = 3214;
                rsInterface.valueIndexArray[1][2] = 0;
                rsInterface.valueIndexArray[1][3] = 0;
                rsInterface.valueIndexArray[2] = new int[4];
                rsInterface.valueIndexArray[2][0] = 4;
                rsInterface.valueIndexArray[2][1] = 3214;
                rsInterface.valueIndexArray[2][2] = 0;
                rsInterface.valueIndexArray[2][3] = 0;
                rsInterface.valueIndexArray[3] = new int[3];
                rsInterface.valueIndexArray[3][0] = 1;
                rsInterface.valueIndexArray[3][1] = 6;
                rsInterface.valueIndexArray[3][2] = 0;
               
        }
	public static void addToggleButton(int id, int bID, int bID2, String bName, String tT, int configID, int aT, int configFrame) {
		RSInterface tab = addTabInterface(id);
		tab.parentID = id;
		tab.id = id;
		tab.type = 5;
		tab.atActionType = aT;
		tab.contentType = 0;// anInt214
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = 52;
		tab.anIntArray212 = new int[1];
		tab.anIntArray245 = new int[1];
		tab.anIntArray212[0] = 1;
		tab.anIntArray245[0] = configID;
		tab.valueIndexArray = new int[1][3];
		tab.valueIndexArray[0][0] = 5;
		tab.valueIndexArray[0][1] = configFrame;
		tab.valueIndexArray[0][2] = 0;
		tab.sprite1 = imageLoader(bID, bName);
		tab.sprite2 = imageLoader(bID2, bName);
		tab.width = tab.sprite1.myWidth;
		tab.height = tab.sprite1.myHeight;
		tab.tooltip = tT;
	}
	public static void addSprite(int id, String spriteName) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.aByte254 = (byte) 0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(spriteName);
		tab.sprite2 = imageLoader(spriteName);
		tab.width = 512;
		tab.height = 334;
	}
	public static void addHoverButton(int i, String imageName, int width, int height, String text, int contentType, int hoverOver, int aT) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = aT;
		tab.contentType = contentType;
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = hoverOver;
		tab.sprite1 = imageLoader(imageName);
		tab.sprite2 = imageLoader(imageName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = text;
	}
	public static void SettingsTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(6299);
        	addSprite(37001, 1, "Switcher/Background");
		addButton(37046, 1, "Switcher/Top", 63, 24, "Settings Menu", 1);
		addText(37050, "Gameframe", tda, 0, 0xFFFFFF, false, true);
		addText(37051, "x10 Hitting", tda, 0, 0xFFFFFF, false, true);
		addText(37052, "New Hitsplats", tda, 0, 0xFFFFFF, false, true);
		addText(37053, "New Hitpoints Bar", tda, 0, 0xFFFFFF, false, true);
		addText(37054, "New Context Menu", tda, 0, 0xFFFFFF, false, true);
		addText(37055, "Names Above Heads", tda, 0, 0xFFFFFF, false, true);
		addText(37056, "HP Above Heads", tda, 0, 0xFFFFFF, false, true);
		addText(37057, "Fog", tda, 0, 0xFFFFFF, false, true);
		addText(37058, "525", tda, 0, 0xFFFFFF, false, true);
		addText(37059, "", tda, 0, 0x67E300, false, true);
		addText(37060, "", tda, 0, 0x67E300, false, true);
		addText(37061, "", tda, 0, 0xF30021, false, true);
		addText(37062, "", tda, 0, 0x67E300, false, true);
		addText(37063, "", tda, 0, 0xF30021, false, true);
		addText(37064, "", tda, 0, 0xF30021, false, true);
		addText(37065, "", tda, 0, 0x67E300, false, true);
		addButton(37002, 1, "Switcher/SmallerTextBox", 63, 24, "Change gameframe", 1);
		addButton(37003, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37004, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37005, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37006, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37007, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37008, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37009, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addHoverButton(37020, "Switcher/SmallButton", 0, 200, 30, "Change Gameframe", -1, 37021, 1);
		addHoveredButton(37021, "Switcher/SmallButton", 1, 200, 30, 37022);
		addHoverButton(37023, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37024, 1);
		addHoveredButton(37024, "Switcher/SmallButton", 1, 200, 30, 37025);
		addHoverButton(37026, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37027, 1);
		addHoveredButton(37027, "Switcher/SmallButton", 1, 200, 30, 37028);
		addHoverButton(37029, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37030, 1);
		addHoveredButton(37030, "Switcher/SmallButton", 1, 200, 30, 37031);
		addHoverButton(37033, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37034, 1);
		addHoveredButton(37034, "Switcher/SmallButton", 1, 200, 30, 37035);
		addHoverButton(37036, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37037, 1);
		addHoveredButton(37037, "Switcher/SmallButton", 1, 200, 30, 37038);
		addHoverButton(37039, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37040, 1);
		addHoveredButton(37040, "Switcher/SmallButton", 1, 200, 30, 37042);
		addHoverButton(37043, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37044, 1);
		addHoveredButton(37044, "Switcher/SmallButton", 1, 200, 30, 37045);
       	        addHoverButton(37047, "Interfaces/Minigame/Back", 0, 16, 16, "Back", -1, 37048, 1);
       	        addHoveredButton(37048, "Interfaces/Minigame/Back", 1, 16, 16, 37049);
		tab.totalChildren(44);
		/*BUTTONS*/
       		tab.child(0, 37001, 0, 0);
       		tab.child(1, 37002, 5, 20);
       		tab.child(2, 37003, 5, 50);
       		tab.child(3, 37004, 5, 80);
       		tab.child(4, 37005, 5, 110);
       		tab.child(5, 37006, 5, 140);
       		tab.child(6, 37007, 5, 170);
       		tab.child(7, 37008, 5, 200);
       		tab.child(8, 37009, 5, 230);
		/*HOVER/HOVERED BUTTONS*/
		tab.child(9, 37020, 140, 20);
		tab.child(10, 37021, 140, 20);
		tab.child(11, 37023, 150, 50);
		tab.child(12, 37024, 150, 50);
		tab.child(13, 37026, 150, 80);
		tab.child(14, 37027, 150, 80);
		tab.child(15, 37029, 150, 110);
		tab.child(16, 37030, 150, 110);
		 tab.child(17, 37033, 150, 140);
		tab.child(18, 37034, 150, 140);
		tab.child(19, 37036, 150, 170);
		tab.child(20, 37037, 150, 170);
		tab.child(21, 37039, 150, 200);
		tab.child(22, 37040, 150, 200);
		tab.child(23, 37043, 150, 230);
		tab.child(24, 37044, 150, 230);
		/*TOP SPRITE*/
		tab.child(25, 37046, 35, 0);
		tab.child(26, 37047, 0, 0);
		tab.child(27, 37048, 0, 0);
		/*TEXT*/
		tab.child(43, 37050, 8, 23);
		tab.child(28, 37051, 8, 53);
		tab.child(29, 37052, 8, 83);
		tab.child(30, 37053, 8, 113);
		tab.child(31, 37054, 8, 143);
		tab.child(32, 37055, 8, 173);
		tab.child(33, 37056, 8, 203);
		tab.child(34, 37057, 8, 233);
		//dddd
		tab.child(35, 37058, 146, 23);
		tab.child(36, 37059, 159, 53);
		tab.child(37, 37060, 159, 83);
		tab.child(38, 37061, 159, 113);
		tab.child(39, 37062, 159, 143);
		tab.child(40, 37063, 159, 173);
		tab.child(41, 37064, 159, 203);
		tab.child(42, 37065, 159, 233);


	}
	/*public static void Trade(TextDrawingArea[] TDA){
		RSInterface Interface = addTabInterface(3323);
		setChildren(18, Interface);
		addSprite(3324, 6, "Bank2/TRADE");
		addHover(3442, 3, 0, 3325, 1, "Bank2/BANK", 17, 17, "Close Window");
		addHovered(3325, 2, "Bank2/BANK", 17, 17, 3326);
		addText(3417, "Trading With:", 0xFF9933, true, true, 52,TDA, 2);
		addText(3418, "Trader's Offer", 0xFF9933, false, true, 52,TDA, 1);
		addText(3419, "Your Offer", 0xFF9933, false, true, 52,TDA, 1);
		addText(3421, "Accept", 0x00C000, true, true, 52,TDA, 1);
		addText(3423, "Decline", 0xC00000, true, true, 52,TDA, 1);
		addText(3431, "Waiting For Other Player", 0xFFFFFF, true, true, 52,TDA, 1);
		addHover(3420, 1, 0, 3327, 5, "Bank2/TRADE", 65, 32, "Accept");
		addHovered(3327, 2, "Bank2/TRADE", 65, 32, 3328);
		addHover(3422, 3, 0, 3329, 5, "Bank2/TRADE", 65, 32, "Close Window");
		addHovered(3329, 2, "Bank2/TRADE", 65, 32, 3330);
		addText(19000, "", TDA, 0, 0xFF9933, true, true);
		addText(19001, "", TDA, 0, 0xFF9933, true, true);
		addText(19002, "inventory slots.", TDA, 0, 0xFF9933, true, true);
		setBounds(3324, 0, 16, 0, Interface);
		setBounds(3442, 485, 24, 1, Interface);
		setBounds(3325, 485, 24, 2, Interface);
		setBounds(3417, 258, 25, 3, Interface);
		setBounds(3418, 355, 51, 4, Interface);
		setBounds(3419, 68, 51, 5, Interface);
		setBounds(3420, 223, 120, 6, Interface);
		setBounds(3327, 223, 120, 7, Interface);
		setBounds(3422, 223, 160, 8, Interface);
		setBounds(3329, 223, 160, 9, Interface);
		setBounds(3421, 256, 127, 10, Interface);
		setBounds(3423, 256, 167, 11, Interface);
		setBounds(3431, 256, 272, 12, Interface);
		setBounds(3415, 12, 64, 13, Interface);
		setBounds(3416, 321, 64, 14, Interface);
		setBounds(19000, 257, 68, 15, Interface);
		setBounds(19001, 257, 79, 16, Interface);
		setBounds(19002, 257, 90, 17, Interface);
		Interface = addTabInterface(3443);
		setChildren(15, Interface);
		addSprite(3444, 3, "Bank2/TRADE");
		/*addButton(3546, 2, "Bank2/SHOP", 63, 24, "Accept", 1);
		addButton(3548, 2, "Bank2/SHOP", 63, 24, "Decline", 3);*/
		/*addText(3547, "Accept", 0x00C000, true, true, 52,TDA, 1);
		addText(3549, "Decline", 0xC00000, true, true, 52,TDA, 1);
		addText(3450, "Trading With:", 0x00FFFF, true, true, 52,TDA, 2);
		addText(3451, "Yourself", 0x00FFFF, true, true, 52,TDA, 2);
		setBounds(3444, 12, 20, 0, Interface);
		setBounds(3442, 470, 32, 1, Interface);
		setBounds(3325, 470, 32, 2, Interface);
		setBounds(3535, 130, 28, 3, Interface);
		setBounds(3536, 105, 47, 4, Interface);
		setBounds(3546, 189, 295, 5, Interface);
		setBounds(3548, 258, 295, 6, Interface);
		setBounds(3547, 220, 299, 7, Interface);
		setBounds(3549, 288, 299, 8, Interface);
		setBounds(3557, 71, 87, 9, Interface);
		setBounds(3558, 315, 87, 10, Interface);
		setBounds(3533, 64, 70, 11, Interface);
		setBounds(3534, 297, 70, 12, Interface);
		setBounds(3450, 95, 289, 13, Interface);
		setBounds(3451, 95, 304, 14, Interface);
	}*/
		public static void newTrade(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(3323);
		setChildren(19, Interface);

		addSprite(3324, 6, "Interfaces/Trade/TRADE");
		addHover(3442, 3, 0, 3325, 1, "Interfaces/Bank/BANK", 17, 17, "Close Window");
		addHovered(3325, 2, "Interfaces/Bank/BANK", 17, 17, 3326);
		addText(3417, "Trading With:", 0xFF981F, true, true, 52,TDA, 2);
		addText(3418, "Trader's Offer", 0xFF981F, false, true, 52,TDA, 1);
		addText(3419, "Your Offer", 0xFF981F, false, true, 52,TDA, 1);
		addText(3421, "Accept", 0x00C000, true, true, 52,TDA, 1);
		addText(3423, "Decline", 0xC00000, true, true, 52,TDA, 1);

		addText(3431, "Waiting For Other Player", 0xFFFFFF, true, true, 52,TDA, 1);
		addText(23504, "Wealth transfer: 2147,000,000 coins' worth to Zezimablud12", 0xB9B855, true, true, -1,TDA, 0);
		addText(23505, "1 has\\n 28 free\\n inventory slots.", 0xFF981F, true, true, -1,TDA, 0);

		addText(23506, "Wealth transfer: 2147,000,000 coins' worth to Zezimablud12", 0xB9B855, false, true, -1,TDA, 0);
		addText(23507, "Wealth transfer: 2147,000,000 coins' worth to me", 0xB9B855, false, true, -1,TDA, 0);

		addHover(3420, 1, 0, 3327, 5, "Interfaces/Trade/TRADE", 65, 32, "Accept");
		addHovered(3327, 2, "Interfaces/Trade/TRADE", 65, 32, 3328);
		addHover(3422, 3, 0, 3329, 5, "Interfaces/Trade/TRADE", 65, 32, "Decline");
		addHovered(3329, 2, "Interfaces/Trade/TRADE", 65, 32, 3330);



		setBounds(3324, 0, 16, 0, Interface);
		setBounds(3442, 485, 24, 1, Interface);
		setBounds(3325, 485, 24, 2, Interface);
		setBounds(3417, 258, 25, 3, Interface);
		setBounds(3418, 355, 51, 4, Interface);
		setBounds(3419, 68, 51, 5, Interface);
		setBounds(3420, 223, 120, 6, Interface);
		setBounds(3327, 223, 120, 7, Interface);
		setBounds(3422, 223, 160, 8, Interface);
		setBounds(3329, 223, 160, 9, Interface);
		setBounds(3421, 256, 127, 10, Interface);
		setBounds(3423, 256, 167, 11, Interface);
		setBounds(3431, 256, 272, 12, Interface);
		setBounds(3415, 12, 64, 13, Interface);
		setBounds(3416, 321, 67, 14, Interface);

		setBounds(23505, 256, 67, 16, Interface);

		setBounds(23504, 255, 310, 15, Interface);
		setBounds(23506, 20, 310, 17, Interface);
		setBounds(23507, 380, 310, 18, Interface);

		Interface = addInterface(3443);
		setChildren(15, Interface);
		addSprite(3444, 3, "Interfaces/Trade/TRADE");
		addButton(3546, 2, "Interfaces/ShopTab/SHOP", 63, 24, "Accept", 1);
		addButton(3548, 2, "Interfaces/ShopTab/SHOP", 63, 24, "Decline", 3);
		addText(3547, "Accept", 0x00C000, true, true, 52,TDA, 1);
		addText(3549, "Decline", 0xC00000, true, true, 52,TDA, 1);
		addText(3450, "Trading With:", 0x00FFFF, true, true, 52,TDA, 2);
		addText(3451, "Yourself", 0x00FFFF, true, true, 52,TDA, 2);
		setBounds(3444, 12, 20, 0, Interface);
		setBounds(3442, 470, 32, 1, Interface);
		setBounds(3325, 470, 32, 2, Interface);
		setBounds(3535, 130, 28, 3, Interface);
		setBounds(3536, 105, 47, 4, Interface);
		setBounds(3546, 189, 295, 5, Interface);
		setBounds(3548, 258, 295, 6, Interface);
		setBounds(3547, 220, 299, 7, Interface);
		setBounds(3549, 288, 299, 8, Interface);
		setBounds(3557, 71, 87, 9, Interface);
		setBounds(3558, 315, 87, 10, Interface);
		setBounds(3533, 64, 70, 11, Interface);
		setBounds(3534, 297, 70, 12, Interface);
		setBounds(3450, 95, 289, 13, Interface);
		setBounds(3451, 95, 304, 14, Interface);
	}

	public static void addPrayer(int i, int configId, int configFrame, int requiredValues, int prayerSpriteID, String PrayerName, int Hover) {
		RSInterface Interface = addTabInterface(i);
		Interface.id = i;
		Interface.parentID = 22500;
		Interface.type = 5;
		Interface.atActionType = 4;
		Interface.contentType = 0;
		Interface.aByte254 = 0;
		Interface.mOverInterToTrigger = -1;
		Interface.sprite1 = imageLoader(0, "CurseTab/GLOW");
		Interface.sprite2 = imageLoader(1, "CurseTab/GLOW");
		Interface.width = 34;
		Interface.height = 34;
		Interface.anIntArray245 = new int[1];
		Interface.anIntArray212 = new int[1];
		Interface.anIntArray245[0] = 1;
		Interface.anIntArray212[0] = configId;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 5;
		Interface.valueIndexArray[0][1] = configFrame;
		Interface.valueIndexArray[0][2] = 0;
		Interface.tooltip = "Activate@or1@ " + PrayerName;
		Interface = addTabInterface(i + 1);
		Interface.id = i + 1;
		Interface.parentID = 22500;
		Interface.type = 5;
		Interface.atActionType = 0;
		Interface.contentType  = 0;
		Interface.aByte254 = 0;
		Interface.sprite1 = imageLoader(prayerSpriteID, "CurseTab/PRAYON");
		Interface.sprite2 = imageLoader(prayerSpriteID, "CurseTab/PRAYOFF");
		Interface.width = 34;
		Interface.height = 34;
		Interface.anIntArray245 = new int[1];
		Interface.anIntArray212 = new int[1];
		Interface.anIntArray245[0] = 2;
		Interface.anIntArray212[0] = requiredValues + 1;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 2;
		Interface.valueIndexArray[0][1] = 5;
		Interface.valueIndexArray[0][2] = 0;
	}
public static void Curses(TextDrawingArea[] TDA) {
		RSInterface Interface = addTabInterface(22500);
		int index = 0;
		addText(687, "99/99", 0xFF981F, false, false, -1, TDA, 1);
		addSprite(22502, 0, "CurseTab/ICON");
		addPrayer(22503, 0, 610, 49, 7, "Protect Item", 22582);//1
		addPrayer(22505, 0, 611, 49, 4, "Sap Warrior", 22544);//2
		addPrayer(22507, 0, 612, 51, 5, "Sap Ranger", 22546);//3
		addPrayer(22509, 0, 613, 53, 3, "Sap Mage", 22548);//4
		addPrayer(22511, 0, 614, 55, 2, "Sap Spirit", 22550);//5
		addPrayer(22513, 0, 615, 58, 18, "Berserker", 22552);//6
		addPrayer(22515, 0, 616, 61, 15, "Deflect Summoning", 22554);///7
		addPrayer(22517, 0, 617, 64, 17, "Deflect Magic", 22556);///8
		addPrayer(22519, 0, 618, 67, 16, "Deflect Missiles", 22558);///9
		addPrayer(22521, 0, 619, 70, 6, "Deflect Melee", 22560);///10
		addPrayer(22523, 0, 620, 73, 9, "Leech Attack", 22562);//11
		addPrayer(22525, 0, 621, 75, 10, "Leech Ranged", 22564);//12
		addPrayer(22527, 0, 622, 77, 11, "Leech Magic", 22566);//13
		addPrayer(22529, 0, 623, 79, 12, "Leech Defence", 22568);//14
		addPrayer(22531, 0, 624, 81, 13, "Leech Strength", 22570);//15
		addPrayer(22533, 0, 625, 83, 14, "Leech Energy", 22572);//16
		addPrayer(22535, 0, 626, 85, 19, "Leech Special Attack", 22574);//17
		addPrayer(22537, 0, 627, 88, 1, "Wrath", 22576);///18
		addPrayer(22539, 0, 628, 91, 8, "Soul Split", 22578);///19
		addPrayer(22541, 0, 629, 94, 20, "Turmoil", 22580);//20
		drawTooltip(22582, "Level 50\nProtect Item\nKeep 1 extra item if you die");
		drawTooltip(22544, "Level 50\nSap Warrior\nDrains 10% of enemy Attack,\nStrength and Defence,\nincreasing to 20% over time");
		drawTooltip(22546, "Level 52\nSap Ranger\nDrains 10% of enemy Ranged\nand Defence, increasing to 20%\nover time");
		drawTooltip(22548, "Level 54\nSap Mage\nDrains 10% of enemy Magic\nand Defence, increasing to 20%\nover time");
		drawTooltip(22550, "Level 56\nSap Spirit\nDrains enenmy special attack\nenergy");
		drawTooltip(22552, "Level 59\nBerserker\nBoosted stats last 15% longer");
		drawTooltip(22554, "Level 62\nDeflect Summoning\nReduces damage dealt from\nSummoning scrolls, prevents the\nuse of a familiar's special\nattack, and can deflect some of\ndamage back to the attacker");
		drawTooltip(22556, "Level 65\nDeflect Magic\nProtects against magical attacks\nand can deflect some of the\ndamage back to the attacker");
		drawTooltip(22558, "Level 68\nDeflect Missiles\nProtects against ranged attacks\nand can deflect some of the\ndamage back to the attacker");
		drawTooltip(22560, "Level 71\nDeflect Melee\nProtects against melee attacks\nand can deflect some of the\ndamage back to the attacker");
		drawTooltip(22562, "Level 74\nLeech Attack\nBoosts Attack by 5%, increasing\nto 10% over time, while draining\nenemy Attack by 10%, increasing\nto 25% over time");
		drawTooltip(22564, "Level 76\nLeech Ranged\nBoosts Ranged by 5%, increasing\nto 10% over time, while draining\nenemy Ranged by 10%,\nincreasing to 25% over\ntime");
		drawTooltip(22566, "Level 78\nLeech Magic\nBoosts Magic by 5%, increasing\nto 10% over time, while draining\nenemy Magic by 10%, increasing\nto 25% over time");
		drawTooltip(22568, "Level 80\nLeech Defence\nBoosts Defence by 5%, increasing\nto 10% over time, while draining\n enemy Defence by10%,\nincreasing to 25% over\ntime");
		drawTooltip(22570, "Level 82\nLeech Strength\nBoosts Strength by 5%, increasing\nto 10% over time, while draining\nenemy Strength by 10%, increasing\n to 25% over time");
		drawTooltip(22572, "Level 84\nLeech Energy\nDrains enemy run energy, while\nincreasing your own");
		drawTooltip(22574, "Level 86\nLeech Special Attack\nDrains enemy special attack\nenergy, while increasing your\nown");
		drawTooltip(22576, "Level 89\nWrath\nInflicts damage to nearby\ntargets if you die");
		drawTooltip(22578, "Level 92\nSoul Split\n1/4 of damage dealt is also removed\nfrom opponent's Prayer and\nadded to your Hitpoints");
		drawTooltip(22580, "Level 95\nTurmoil\nIncreases Attack and Defence\nby 15%, plus 15% of enemy's\nlevel, and Strength by 23% plus\n10% of enemy's level");
		setChildren(62, Interface);

		setBounds(687, 85, 241, index, Interface);index++;
		setBounds(22502, 65, 241, index, Interface);index++;
		setBounds(22503, 2, 5, index, Interface);index++;
		setBounds(22504, 8, 8, index, Interface);index++;
		setBounds(22505, 40, 5, index, Interface);index++;
		setBounds(22506, 47, 12, index, Interface);index++;
		setBounds(22507, 76, 5, index, Interface);index++;
		setBounds(22508, 82, 11, index, Interface);index++;
		setBounds(22509, 113, 5, index, Interface);index++;
		setBounds(22510, 116, 8, index, Interface);index++;
		setBounds(22511, 150, 5, index, Interface);index++;
		setBounds(22512, 155, 10, index, Interface);index++;
		setBounds(22513, 2, 45, index, Interface);index++;
		setBounds(22514, 9, 48, index, Interface);index++;
		setBounds(22515, 39, 45, index, Interface);index++;
		setBounds(22516, 42, 47, index, Interface);index++;
		setBounds(22517, 76, 45, index, Interface);index++;
		setBounds(22518, 79, 48, index, Interface);index++;
		setBounds(22519, 113, 45, index, Interface);index++;
		setBounds(22520, 116, 48, index, Interface);index++;
		setBounds(22521, 151, 45, index, Interface);index++;
		setBounds(22522, 154, 48, index, Interface);index++;
		setBounds(22523, 2, 82, index, Interface);index++;
		setBounds(22524, 6, 86, index, Interface);index++;
		setBounds(22525, 40, 82, index, Interface);index++;
		setBounds(22526, 42, 86, index, Interface);index++;
		setBounds(22527, 77, 82, index, Interface);index++;
		setBounds(22528, 79, 86, index, Interface);index++;
		setBounds(22529, 114, 83, index, Interface);index++;
		setBounds(22530, 119, 87, index, Interface);index++;
		setBounds(22531, 153, 83, index, Interface);index++;
		setBounds(22532, 156, 86, index, Interface);index++;
		setBounds(22533, 2, 120, index, Interface);index++;
		setBounds(22534, 7, 125, index, Interface);index++;
		setBounds(22535, 40, 120, index, Interface);index++;
		setBounds(22536, 45, 124, index, Interface);index++;
		setBounds(22537, 78, 120, index, Interface);index++;
		setBounds(22538, 86, 124, index, Interface);index++;
		setBounds(22539, 114, 120, index, Interface);index++;
		setBounds(22540, 120, 125, index, Interface);index++;
		setBounds(22541, 151, 120, index, Interface);index++;
		setBounds(22542, 153, 127, index, Interface);index++;
		setBounds(22582, 10, 40, index, Interface);index++;
		setBounds(22544, 20, 40, index, Interface);index++;
		setBounds(22546, 20, 40, index, Interface);index++;
		setBounds(22548, 20, 40, index, Interface);index++;
		setBounds(22550, 20, 40, index, Interface);index++;
		setBounds(22552, 10, 80, index, Interface);index++;
		setBounds(22554, 10, 80, index, Interface);index++;
		setBounds(22556, 10, 80, index, Interface);index++;
		setBounds(22558, 10, 80, index, Interface);index++;
		setBounds(22560, 10, 80, index, Interface);index++;
		setBounds(22562, 10, 120, index, Interface);index++;
		setBounds(22564, 10, 120, index, Interface);index++;
		setBounds(22566, 10, 120, index, Interface);index++;
		setBounds(22568, 5, 120, index, Interface);index++;
		setBounds(22570, 5, 120, index, Interface);index++;
		setBounds(22572, 10, 160, index, Interface);index++;
		setBounds(22574, 10, 160, index, Interface);index++;
		setBounds(22576, 10, 160, index, Interface);index++;
		setBounds(22578, 10, 160, index, Interface);index++;
		setBounds(22580, 10, 160, index, Interface);index++;
		
	}
	public static void Shop(TextDrawingArea[] TDA){
		RSInterface Interface = addTabInterface(3824);
		setChildren(8, Interface);
		addSprite(3825, 0, "Bank2/SHOP");
		addHover(3902, 3, 0, 3826, 1, "Bank/BANK", 17, 17, "Close Window");
		addHovered(3826, 2, "Bank2/BANK", 17, 17, 3827);
		addText(19679, "Main Stock", 0xFF981F, false, true, 52,TDA, 1);//3628////19679
		addText(19680, "Store Info", 0xBF751D, false, true, 52,TDA, 1);//3629//19680
		addButton(19681, 2, "Bank2/SHOP", 95, 19, "Store Information", 1);//3630//19681
		setBounds(3825, 12, 12, 0, Interface);
		setBounds(3902, 471, 22, 1, Interface);
		setBounds(3826, 471, 22, 2, Interface);
		setBounds(3900, 60, 85, 3, Interface);
		setBounds(3901, 240, 21, 4, Interface);
		setBounds(19679, 42, 54, 5, Interface);
		setBounds(19680, 150, 54, 6, Interface);
		setBounds(19681, 129, 50, 7, Interface);
		Interface = interfaceCache[3900];
		Interface.invSpritePadX = 8;
		Interface.width = 10;
		Interface.height = 4;
		Interface.invSpritePadY = 19;
		Interface = addTabInterface(19682);
		addSprite(19683, 1, "Bank2/SHOP");
		addText(19684, "Main Stock", 0xBF751D, false, true, 52,TDA, 1);
		addText(19685, "Store Info", 0xFF981F, false, true, 52,TDA, 1);
		addButton(19686, 2, "Bank/SHOP", 95, 19, "Main Stock", 1);
		setChildren(7, Interface);
		setBounds(19683, 12, 12, 0, Interface);
		setBounds(3901, 240, 21, 1, Interface);
		setBounds(19684, 42, 54, 2, Interface);
		setBounds(19685, 150, 54, 3, Interface);
		setBounds(19686, 23, 50, 4, Interface);
		setBounds(3902, 471, 22, 5, Interface);
		setBounds(3826, 60, 85, 6, Interface);
	}
public static void addText(int i, String s,int k, boolean l, boolean m, int a,TextDrawingArea[] TDA, int j, int dsc) {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.parentID = i;
        rsinterface.id = i;
        rsinterface.type = 4;
        rsinterface.atActionType = 1;
        rsinterface.width = 174;
        rsinterface.height = 11;
        rsinterface.contentType = 0;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = a;
        rsinterface.centerText = l;
        rsinterface.textShadow = m;
        rsinterface.textDrawingAreas = TDA[j];
        rsinterface.message = s;
        rsinterface.aString228 = "";
		rsinterface.anInt219 = 0;
        rsinterface.textColor = k;
		rsinterface.anInt216 = dsc;
		rsinterface.tooltip = s;
    }

public static void Bank(){
		RSInterface Interface = addTabInterface(5292);
		setChildren(19, Interface);
		addSprite(5293, 0, "Bank/BANK");
		setBounds(5293, 13, 13, 0, Interface);
		addHover(5384, 3, 0, 5380, 1, "Bank/BANK", 17, 17, "Close Window");
		addHovered(5380, 2, "Bank/BANK", 17, 17, 5379);
		setBounds(5384, 476, 16, 3, Interface);
		setBounds(5380, 476, 16, 4, Interface);	
		addHover(5294, 4, 0, 5295, 3, "Bank/BANK", 114, 25, "Set A Bank PIN");
		addHovered(5295, 4, "Bank/BANK", 114, 25, 5296);
		setBounds(5294, 110, 285, 5, Interface);
		setBounds(5295, 110, 285, 6, Interface);
		addBankHover(22000, 4, 22001, 5, 8, "Bank/BANK", 35, 25, 304, 1, "Swap Withdraw Mode", 22002, 7, 6, "Bank/BANK", 22003, "Switch to insert items \nmode", "Switch to swap items \nmode.", 12, 20);
		setBounds(22000, 25, 285, 7, Interface);
		setBounds(22001, 10, 225, 8, Interface);
		addBankHover(22004, 4, 22005, 13, 15, "Bank/BANK", 35, 25, 0, 1, "Search", 22006, 14, 16, "Bank/BANK", 22007, "Click here to search your \nbank", "Click here to search your \nbank", 12, 20);
		setBounds(22004, 65, 285, 9, Interface);
		setBounds(22005, 50, 225, 10, Interface);
		addBankHover(22008, 4, 22009, 9, 11, "Bank/BANK", 35, 25, 115, 1, "Withdraw", 22010, 10, 12, "Bank/BANK", 22011, "Switch to note withdrawal \nmode", "Switch to item withdrawal \nmode", 12, 20);
		setBounds(22008, 240, 285, 11, Interface);
		setBounds(22009, 225, 225, 12, Interface);
		addBankHover1(22012, 5, 22013, 17, "Bank/BANK", 35, 25, "Deposit carried tems", 22014, 18, "Bank/BANK", 22015, "Empty your backpack into\nyour bank", 0, 20);
		setBounds(22012, 375, 285, 13, Interface);
		setBounds(22013, 360, 225, 14, Interface);
		addBankHover1(22016, 5, 22017, 19, "Bank/BANK", 35, 25, "Deposit worn items", 22018, 20, "Bank/BANK", 22019, "Empty the items your are\nwearing into your bank", 0, 20);
		setBounds(22016, 415, 285, 15, Interface);
		setBounds(22017, 400, 225, 16, Interface);
		addBankHover1(22020, 5, 22021, 21, "Bank/BANK", 35, 25, "Deposit beast of burden inventory.", 22022, 22, "Bank/BANK", 22023, "Empty your BoB's inventory\ninto your bank", 0, 20);
		setBounds(22020, 455, 285, 17, Interface);
		setBounds(22021, 440, 225, 18, Interface);
		setBounds(5383, 170, 15, 1, Interface);
		setBounds(5385, -4, 74, 2, Interface);
		Interface = interfaceCache[5385];
		Interface.height = 206;
		Interface.width = 480;
		Interface = interfaceCache[5382];
		Interface.width = 10;
		Interface.invSpritePadX = 12;
		Interface.height = 35;
	}

	public static void addBankHover(int interfaceID, int actionType, int hoverid, int spriteId, int spriteId2, String NAME, int Width, int Height, int configFrame, int configId, String Tooltip,int hoverId2, int hoverSpriteId,int hoverSpriteId2, String hoverSpriteName, int hoverId3, String hoverDisabledText, String hoverEnabledText, int X, int Y){
		RSInterface hover = addTabInterface(interfaceID);
		hover.id = interfaceID;
		hover.parentID = interfaceID;
		hover.type = 5;
		hover.atActionType = actionType;
		hover.contentType = 0;
		hover.aByte254 = 0;
		hover.mOverInterToTrigger = hoverid;
		hover.sprite1 = imageLoader(spriteId, NAME);
		hover.sprite2 = imageLoader(spriteId2, NAME);
		hover.width = Width;
		hover.tooltip = Tooltip;
		hover.height = Height;
		hover.anIntArray245 = new int[1];
		hover.anIntArray212 = new int[1];
		hover.anIntArray245[0] = 1;
		hover.anIntArray212[0] = configId;
		hover.valueIndexArray = new int[1][3];
		hover.valueIndexArray[0][0] = 5;
		hover.valueIndexArray[0][1] = configFrame;
		hover.valueIndexArray[0][2] = 0;
		hover = addTabInterface(hoverid);
		hover.parentID = hoverid;
		hover.id = hoverid;
		hover.type = 0;
		hover.atActionType = 0;
		hover.width =550;
		hover.height = 334;
		hover.isMouseoverTriggered = true;
		hover.mOverInterToTrigger = -1;
		addSprite(hoverId2, hoverSpriteId, hoverSpriteId2, hoverSpriteName, configId, configFrame);
		addHoverBox(hoverId3, interfaceID,hoverDisabledText, hoverEnabledText, configId, configFrame);
		setChildren(2, hover);
		setBounds(hoverId2, 15, 60, 0, hover);
		setBounds(hoverId3, X, Y, 1, hover);
	}
	public static void addBankHover1(int interfaceID, int actionType, int hoverid, int spriteId, String NAME,  int Width, int Height, String Tooltip, int hoverId2, int hoverSpriteId, String hoverSpriteName, int hoverId3, String hoverDisabledText, int X, int Y){
		RSInterface hover = addTabInterface(interfaceID);
		hover.id = interfaceID;
		hover.parentID = interfaceID;
		hover.type = 5;
		hover.atActionType = actionType;
		hover.contentType = 0;
		hover.aByte254 = 0;
		hover.mOverInterToTrigger = hoverid;
		hover.sprite1 = imageLoader(spriteId, NAME);
		hover.width = Width;
		hover.tooltip = Tooltip;
		hover.height = Height;
		hover = addTabInterface(hoverid);
		hover.parentID = hoverid;
		hover.id = hoverid;
		hover.type = 0;
		hover.atActionType = 0;
		hover.width =550;
		hover.height = 334;
		hover.isMouseoverTriggered = true;
		hover.mOverInterToTrigger = -1;
		addSprite(hoverId2, hoverSpriteId, hoverSpriteId, hoverSpriteName,0,0);
		addHoverBox(hoverId3, interfaceID,hoverDisabledText, hoverDisabledText, 0, 0);
		setChildren(2, hover);
		setBounds(hoverId2, 15, 60, 0, hover);
		setBounds(hoverId3, X, Y, 1, hover);
	}
	public static void addHoverBox(int id, int ParentID,String text, String text2, int configId, int configFrame) {
		RSInterface rsi = addTabInterface(id);
		rsi.id = id;
		rsi.parentID = ParentID;
		rsi.type = 8;
		rsi.aString228 = text;
		rsi.message = text2;
		rsi.anIntArray245 = new int[1];
		rsi.anIntArray212 = new int[1];
		rsi.anIntArray245[0] = 1;
		rsi.anIntArray212[0] = configId;
		rsi.valueIndexArray = new int[1][3];
		rsi.valueIndexArray[0][0] = 5;
		rsi.valueIndexArray[0][1] = configFrame;
		rsi.valueIndexArray[0][2] = 0;
	}
	public static void addHover(int i, int aT, int cT, int hoverid,int sId, String NAME, int W, int H, String tip){
		RSInterface hover = addTabInterface(i);
		hover.id = i;
		hover.parentID = i;
		hover.type = 5;
		hover.atActionType = aT;
		hover.contentType = cT;
		hover.mOverInterToTrigger = hoverid;
		hover.sprite1 = imageLoader(sId, NAME);
		hover.sprite2 = imageLoader(sId, NAME);
		hover.width = W;
		hover.height = H;
		hover.tooltip = tip;
	}
	public static void addHovered(int i, int j, String imageName, int w, int h, int IMAGEID) {
		RSInterface hover = addTabInterface(i);
		hover.parentID = i;
		hover.id = i;
		hover.type = 0;
		hover.atActionType = 0;
		hover.width = w;
		hover.height = h;
		hover.isMouseoverTriggered = true;
		hover.mOverInterToTrigger = -1;
		addSprite(IMAGEID, j, imageName);
		setChildren(1, hover);
		setBounds(IMAGEID, 0, 0, 0, hover);
	}
	public static void addSprite(int ID, int i, int i2, String name, int configId, int configFrame) {
		RSInterface Tab = addTabInterface(ID);
		Tab.id = ID;
		Tab.parentID = ID;
		Tab.type = 5;
		Tab.atActionType = 0;
		Tab.contentType = 0;
		Tab.width = 512;
		Tab.height = 334;
		Tab.aByte254 = (byte) 0;
		Tab.mOverInterToTrigger = -1;
		Tab.anIntArray245 = new int[1];
		Tab.anIntArray212 = new int[1];
		Tab.anIntArray245[0] = 1;
		Tab.anIntArray212[0] = configId;
		Tab.valueIndexArray = new int[1][3];
		Tab.valueIndexArray[0][0] = 5;
		Tab.valueIndexArray[0][1] = configFrame;
		Tab.valueIndexArray[0][2] = 0;
		Tab.sprite1 = imageLoader(i, name);
		Tab.sprite2 = imageLoader(i2, name);
	}
    public static void unpack(StreamLoader streamloader, TextDrawingArea atextdrawingarea[], StreamLoader streamloader1)
    {
		aMRUNodes_238 = new MRUNodes(50000);
		Stream stream = new Stream(streamloader.getDataForName("data"));
		int i = -1;
		int j = stream.readUnsignedWord();
		interfaceCache = new RSInterface[j + 50000];
        do
        {
            if(stream.currentOffset >= stream.buffer.length)
            {
                break;
            }
            int k = stream.readUnsignedWord();
            if(k == 65535)
            {
                i = stream.readUnsignedWord();
                k = stream.readUnsignedWord();
            }
            RSInterface rsinterface = interfaceCache[k] = new RSInterface();
            rsinterface.id = k;
            rsinterface.parentID = i;
            rsinterface.type = stream.readUnsignedByte();
            rsinterface.atActionType = stream.readUnsignedByte();
            rsinterface.contentType = stream.readUnsignedWord();
            rsinterface.width = stream.readUnsignedWord();
            rsinterface.height = stream.readUnsignedWord();
            rsinterface.aByte254 = (byte)stream.readUnsignedByte();
            rsinterface.mOverInterToTrigger = stream.readUnsignedByte();
            if(rsinterface.mOverInterToTrigger != 0)
            {
                rsinterface.mOverInterToTrigger = (rsinterface.mOverInterToTrigger - 1 << 8) + stream.readUnsignedByte();
            } else
            {
                rsinterface.mOverInterToTrigger = -1;
            }
            int l = stream.readUnsignedByte();
            if(l > 0)
            {
                rsinterface.anIntArray245 = new int[l];
                rsinterface.anIntArray212 = new int[l];
                for(int i1 = 0; i1 < l; i1++)
                {
                    rsinterface.anIntArray245[i1] = stream.readUnsignedByte();
                    rsinterface.anIntArray212[i1] = stream.readUnsignedWord();
                }

            }
            int j1 = stream.readUnsignedByte();
            if(j1 > 0)
            {
                rsinterface.valueIndexArray = new int[j1][];
                for(int k1 = 0; k1 < j1; k1++)
                {
                    int j3 = stream.readUnsignedWord();
                    rsinterface.valueIndexArray[k1] = new int[j3];
                    for(int l4 = 0; l4 < j3; l4++)
                    {
                        rsinterface.valueIndexArray[k1][l4] = stream.readUnsignedWord();
                    }

                }

            }
            if(rsinterface.type == 0)
            {
                rsinterface.drawsTransparent = false;
                rsinterface.scrollMax = stream.readUnsignedWord();
                rsinterface.isMouseoverTriggered = stream.readUnsignedByte() == 1;
                int l1 = stream.readUnsignedWord();
                rsinterface.children = new int[l1];
                rsinterface.childX = new int[l1];
                rsinterface.childY = new int[l1];
                for(int k3 = 0; k3 < l1; k3++)
                {
                    rsinterface.children[k3] = stream.readUnsignedWord();
                    rsinterface.childX[k3] = stream.readSignedWord();
                    rsinterface.childY[k3] = stream.readSignedWord();
                }

            }
            if(rsinterface.type == 1)
            {
                stream.readUnsignedWord();
                stream.readUnsignedByte();
            }
            if(rsinterface.type == 2)
            {
                rsinterface.inv = new int[rsinterface.width * rsinterface.height];
                rsinterface.invStackSizes = new int[rsinterface.width * rsinterface.height];
                rsinterface.aBoolean259 = stream.readUnsignedByte() == 1;
                rsinterface.isInventoryInterface = stream.readUnsignedByte() == 1;
                rsinterface.usableItemInterface = stream.readUnsignedByte() == 1;
                rsinterface.aBoolean235 = stream.readUnsignedByte() == 1;
                rsinterface.invSpritePadX = stream.readUnsignedByte();
                rsinterface.invSpritePadY = stream.readUnsignedByte();
                rsinterface.spritesX = new int[20];
                rsinterface.spritesY = new int[20];
                rsinterface.sprites = new Sprite[20];
                for(int i2 = 0; i2 < 20; i2++)
                {
                    int l3 = stream.readUnsignedByte();
                    if(l3 != 1)
                    {
                        continue;
                    }
                    rsinterface.spritesX[i2] = stream.readSignedWord();
                    rsinterface.spritesY[i2] = stream.readSignedWord();
                    String s1 = stream.readString();
                    if(streamloader1 != null && s1.length() > 0)
                    {
                        int i5 = s1.lastIndexOf(",");
                        rsinterface.sprites[i2] = method207(Integer.parseInt(s1.substring(i5 + 1)), streamloader1, s1.substring(0, i5));
                    }
                }

                rsinterface.actions = new String[5];
                for(int j2 = 0; j2 < 5; j2++)
                {
                    rsinterface.actions[j2] = stream.readString();
                    if(rsinterface.actions[j2].length() == 0)
                    {
                        rsinterface.actions[j2] = null;
                    }
                    if(rsinterface.parentID == 1644)
                    {
                        rsinterface.actions[2] = "Operate";
                    }
                }

            }
            if(rsinterface.type == 3)
            {
                rsinterface.aBoolean227 = stream.readUnsignedByte() == 1;
            }
            if(rsinterface.type == 4 || rsinterface.type == 1)
            {
                rsinterface.centerText = stream.readUnsignedByte() == 1;
                int k2 = stream.readUnsignedByte();
                if(atextdrawingarea != null)
                {
                    rsinterface.textDrawingAreas = atextdrawingarea[k2];
                }
                rsinterface.textShadow = stream.readUnsignedByte() == 1;
            }
            if(rsinterface.type == 4)
            {
                rsinterface.message = stream.readString().replaceAll("RuneScape", "Caution-X");
                rsinterface.aString228 = stream.readString();
            }
            if(rsinterface.type == 1 || rsinterface.type == 3 || rsinterface.type == 4)
            {
                rsinterface.textColor = stream.readDWord();
            }
            if(rsinterface.type == 3 || rsinterface.type == 4)
            {
                rsinterface.anInt219 = stream.readDWord();
                rsinterface.anInt216 = stream.readDWord();
                rsinterface.anInt239 = stream.readDWord();
            }
            if(rsinterface.type == 5)
            {
                rsinterface.drawsTransparent = false;
                String s = stream.readString();
                if(streamloader1 != null && s.length() > 0)
                {
                    int i4 = s.lastIndexOf(",");
                    rsinterface.sprite1 = method207(Integer.parseInt(s.substring(i4 + 1)), streamloader1, s.substring(0, i4));
                }
                s = stream.readString();
                if(streamloader1 != null && s.length() > 0)
                {
                    int j4 = s.lastIndexOf(",");
                    rsinterface.sprite2 = method207(Integer.parseInt(s.substring(j4 + 1)), streamloader1, s.substring(0, j4));
                }
            }
            if(rsinterface.type == 6)
            {
                int l2 = stream.readUnsignedByte();
                if(l2 != 0)
                {
                    rsinterface.anInt233 = 1;
                    rsinterface.mediaID = (l2 - 1 << 8) + stream.readUnsignedByte();
                }
                l2 = stream.readUnsignedByte();
                if(l2 != 0)
                {
                    rsinterface.anInt255 = 1;
                    rsinterface.anInt256 = (l2 - 1 << 8) + stream.readUnsignedByte();
                }
                l2 = stream.readUnsignedByte();
                if(l2 != 0)
                {
                    rsinterface.anInt257 = (l2 - 1 << 8) + stream.readUnsignedByte();
                } else
                {
                    rsinterface.anInt257 = -1;
                }
                l2 = stream.readUnsignedByte();
                if(l2 != 0)
                {
                    rsinterface.anInt258 = (l2 - 1 << 8) + stream.readUnsignedByte();
                } else
                {
                    rsinterface.anInt258 = -1;
                }
                rsinterface.modelZoom = stream.readUnsignedWord();
                rsinterface.modelRotation1 = stream.readUnsignedWord();
                rsinterface.modelRotation2 = stream.readUnsignedWord();
            }
            if(rsinterface.type == 7)
            {
                rsinterface.inv = new int[rsinterface.width * rsinterface.height];
                rsinterface.invStackSizes = new int[rsinterface.width * rsinterface.height];
                rsinterface.centerText = stream.readUnsignedByte() == 1;
                int i3 = stream.readUnsignedByte();
                if(atextdrawingarea != null)
                {
                    rsinterface.textDrawingAreas = atextdrawingarea[i3];
                }
                rsinterface.textShadow = stream.readUnsignedByte() == 1;
                rsinterface.textColor = stream.readDWord();
                rsinterface.invSpritePadX = stream.readSignedWord();
                rsinterface.invSpritePadY = stream.readSignedWord();
                rsinterface.isInventoryInterface = stream.readUnsignedByte() == 1;
                rsinterface.actions = new String[5];
                for(int k4 = 0; k4 < 5; k4++)
                {
                    rsinterface.actions[k4] = stream.readString();
                    if(rsinterface.actions[k4].length() == 0)
                    {
                        rsinterface.actions[k4] = null;
                    }
                }

            }
            if(rsinterface.atActionType == 2 || rsinterface.type == 2)
            {
                rsinterface.selectedActionName = stream.readString();
                rsinterface.spellName = stream.readString();
                rsinterface.spellUsableOn = stream.readUnsignedWord();
            }
            if(rsinterface.type == 8)
            {
                rsinterface.message = stream.readString();
            }
            if(rsinterface.atActionType == 1 || rsinterface.atActionType == 4 || rsinterface.atActionType == 5 || rsinterface.atActionType == 6)
            {
                rsinterface.tooltip = stream.readString();
                if(rsinterface.tooltip.length() == 0)
                {
                    if(rsinterface.atActionType == 1)
                    {
                        rsinterface.tooltip = "Ok";
                    }
                    if(rsinterface.atActionType == 4)
                    {
                        rsinterface.tooltip = "Select";
                    }
                    if(rsinterface.atActionType == 5)
                    {
                        rsinterface.tooltip = "Select";
                    }
                    if(rsinterface.atActionType == 6)
                    {
                        rsinterface.tooltip = "Continue";
                    }
                }
            }
        } while(true);
        aClass44 = streamloader;
        prayerTab(atextdrawingarea);
        emoteTab();
        Bank();
		skillInterface(atextdrawingarea);
        newTrade(atextdrawingarea);
        Shop(atextdrawingarea);
			itemsOnDeathDATA(atextdrawingarea);
			itemsOnDeath(atextdrawingarea);
        optionTab(atextdrawingarea);
        clanChatTab(atextdrawingarea);
        Sidebar0(atextdrawingarea);
        friendsTab(atextdrawingarea);
        ignoreTab(atextdrawingarea);
        EquipmentTab(atextdrawingarea);
	teleport(atextdrawingarea);
	DonatorTab(atextdrawingarea);
	Classes(atextdrawingarea);
	Dungeoneering(atextdrawingarea);
	LevelUp(atextdrawingarea);
	Skillcapes(atextdrawingarea);
	FightCave(atextdrawingarea);
	EasyTask(atextdrawingarea);
	MediumTask(atextdrawingarea);
	HardTask(atextdrawingarea);
	achievement(atextdrawingarea);
        equipmentScreen(atextdrawingarea);
        Pestpanel(atextdrawingarea);
        Pestpanel2(atextdrawingarea);
        magicTab(atextdrawingarea);
        ancientMagicTab(atextdrawingarea);
        configureLunar(atextdrawingarea);
	ColorChanger(atextdrawingarea);
	Curses(atextdrawingarea);
SettingsTab(atextdrawingarea);
        constructLunar();
			questTab(atextdrawingarea);
			GodWars(atextdrawingarea);
        aMRUNodes_238 = null;
    }
	
	public static void skillInterface(TextDrawingArea[] wid) {
	RSInterface Interface = addTab(3917);
	int index = 0;
		skillInterface(19746, 255);
		//skillInterface(19747, 51);
		//skillInterface(19748, 50); 
		skillInterface(19749, 52);
		addText(29801, "1", wid, 0, 0xFFEE33); //Dungeoneering
		addText(29800, "1", wid, 0, 0xFFEE33); //Hunter
		
		addButton(19747, 51, 27700, "Skill/Skill", 62, 32, "Hunter Skill", 1);
		addButton(19748, 50, 27701, "Skill/Skill", 62, 32, "Dissmiss Beast of Burden", 1);
		/*
		drawTooltip(27700, "Toggle the current\ngameframe");
		drawTooltip(27701, "Toggle the current\ngameframe");
		
		setChildren(3, Interface);
		setBounds(27700, 43, 140, index, Interface);index++;
		setBounds(27701, 43, 140, index, Interface);index++;
		*/
		
		addText(13984, "Total", wid, 0, 0xFFEE33);
		//addText(13984, "Total", wid, 0, 0xFFEE33, false, true);
		addText(3985, "", wid, 0, 0xFFEE33);
		addText(13983, "Level: 2277", wid, 0, 0xFFEE33, true, true);
		for(int k = 0; k < boxIds.length; k++) {
			skillInterface(boxIds[k], 256);
		}
		RSInterface rsinterface = addTab(3917);
		rsinterface.children = new int[63];		rsinterface.childX = new int[63];	rsinterface.childY = new int[63];
		rsinterface.children[0] = 3918;			rsinterface.childX[0] = 0;			rsinterface.childY[0] = 0;
		rsinterface.children[1] = 3925;			rsinterface.childX[1] = 0;			rsinterface.childY[1] = 31;
		rsinterface.children[2] = 3932;			rsinterface.childX[2] = 0;			rsinterface.childY[2] = 62;
		rsinterface.children[3] = 3939;			rsinterface.childX[3] = 0;			rsinterface.childY[3] = 93;
		rsinterface.children[4] = 3946;			rsinterface.childX[4] = 0;			rsinterface.childY[4] = 124;
		rsinterface.children[5] = 3953;			rsinterface.childX[5] = 0;			rsinterface.childY[5] = 155;
		rsinterface.children[6] = 4148;			rsinterface.childX[6] = 0;			rsinterface.childY[6] = 186;
		rsinterface.children[7] = 19746;		rsinterface.childX[7] = 70;			rsinterface.childY[7] = 69;
		rsinterface.children[8] = 19748;		rsinterface.childX[8] = 1;			rsinterface.childY[8] = 219;
		rsinterface.children[9] = 19747;		rsinterface.childX[9] = 64;			rsinterface.childY[9] = 219;
		rsinterface.children[10] = 14000;		rsinterface.childX[10] = 10;		rsinterface.childY[10] = 219;
		rsinterface.children[11] = 19749;		rsinterface.childX[11] = 128;		rsinterface.childY[11] = 220;
		rsinterface.children[12] = 13983; 		rsinterface.childX[12] = 158;  		rsinterface.childY[12] = 238;
		rsinterface.children[13] = 3984;		rsinterface.childX[13] = 300;		rsinterface.childY[13] = 225;
		rsinterface.children[14] = 3985;		rsinterface.childX[14] = 130;		rsinterface.childY[14] = 238;
		rsinterface.children[15] = 29800;		rsinterface.childX[15] = 98;  		rsinterface.childY[15] = 220;
		rsinterface.children[16] = 29800;		rsinterface.childX[16] = 107;  		rsinterface.childY[16] = 235;
		rsinterface.children[17] = 29801;		rsinterface.childX[17] = 36;		rsinterface.childY[17] = 220;
		rsinterface.children[18] = 29801;		rsinterface.childX[18] = 45;		rsinterface.childY[18] = 235;
		rsinterface.children[19] = 4040;		rsinterface.childX[19] = 5;			rsinterface.childY[19] = 20;
		rsinterface.children[20] = 8654;		rsinterface.childX[20] = 0;			rsinterface.childY[20] = 2;
		rsinterface.children[21] = 8655;		rsinterface.childX[21] = 64;		rsinterface.childY[21] = 2;
		rsinterface.children[22] = 4076;		rsinterface.childX[22] = 20;		rsinterface.childY[22] = 20;
		rsinterface.children[23] = 8656;		rsinterface.childX[23] = 128;		rsinterface.childY[23] = 2;
		rsinterface.children[24] = 4112;		rsinterface.childX[24] = 20;		rsinterface.childY[24] = 20;
		rsinterface.children[25] = 8657;		rsinterface.childX[25] = 0;			rsinterface.childY[25] = 33;
		rsinterface.children[26] = 4046;		rsinterface.childX[26] = 20;		rsinterface.childY[26] = 50;
		rsinterface.children[27] = 8658;		rsinterface.childX[27] = 64;		rsinterface.childY[27] = 33;
		rsinterface.children[28] = 4082;		rsinterface.childX[28] = 20;		rsinterface.childY[28] = 50;
		rsinterface.children[29] = 8659;		rsinterface.childX[29] = 128;		rsinterface.childY[29] = 33;
		rsinterface.children[30] = 4118;		rsinterface.childX[30] = 20;		rsinterface.childY[30] = 50;
		rsinterface.children[31] = 8660;		rsinterface.childX[31] = 0;			rsinterface.childY[31] = 60+10;
		rsinterface.children[32] = 4052;		rsinterface.childX[32] = 20;		rsinterface.childY[32] = 83;
		rsinterface.children[33] = 8661;		rsinterface.childX[33] = 65;		rsinterface.childY[33] = 60+10;
		rsinterface.children[34] = 4088;		rsinterface.childX[34] = 20;		rsinterface.childY[34] = 83;
		rsinterface.children[35] = 8662;		rsinterface.childX[35] = 130;		rsinterface.childY[35] = 60+10;
		rsinterface.children[36] = 4124;		rsinterface.childX[36] = 20;		rsinterface.childY[36] = 83;
		rsinterface.children[37] = 8663;		rsinterface.childX[37] = 0;			rsinterface.childY[37] = 90+10;
		rsinterface.children[38] = 4058;		rsinterface.childX[38] = 20;		rsinterface.childY[38] = 120;
		rsinterface.children[39] = 8664;		rsinterface.childX[39] = 65;		rsinterface.childY[39] = 90+10;
		rsinterface.children[40] = 4094;		rsinterface.childX[40] = 20;		rsinterface.childY[40] = 120;
		rsinterface.children[41] = 8665;		rsinterface.childX[41] = 130;		rsinterface.childY[41] = 90+10;
		rsinterface.children[42] = 4130;		rsinterface.childX[42] = 20;		rsinterface.childY[42] = 120;
		rsinterface.children[43] = 8666;		rsinterface.childX[43] = 0;			rsinterface.childY[43] = 130;
		rsinterface.children[44] = 4064;		rsinterface.childX[44] = 20;		rsinterface.childY[44] = 150;
		rsinterface.children[45] = 8667;		rsinterface.childX[45] = 65;		rsinterface.childY[45] = 130;
		rsinterface.children[46] = 4100;		rsinterface.childX[46] = 20;		rsinterface.childY[46] = 150;
		rsinterface.children[47] = 8668;		rsinterface.childX[47] = 130;		rsinterface.childY[47] = 130;
		rsinterface.children[48] = 4136;		rsinterface.childX[48] = 20;		rsinterface.childY[48] = 150;
		rsinterface.children[49] = 8669;		rsinterface.childX[49] = 0;			rsinterface.childY[49] = 160;
		rsinterface.children[50] = 4070;		rsinterface.childX[50] = 20;		rsinterface.childY[50] = 180;
		rsinterface.children[51] = 8670;		rsinterface.childX[51] = 65;		rsinterface.childY[51] = 160;
		rsinterface.children[52] = 4106;		rsinterface.childX[52] = 20;		rsinterface.childY[52] = 180;
		rsinterface.children[53] = 8671;		rsinterface.childX[53] = 130;		rsinterface.childY[53] = 160;
		rsinterface.children[54] = 4142;		rsinterface.childX[54] = 20;		rsinterface.childY[54] = 180;
		rsinterface.children[55] = 8672;		rsinterface.childX[55] = 0;			rsinterface.childY[55] = 190;
		rsinterface.children[56] = 4160;		rsinterface.childX[56] = 20;		rsinterface.childY[56] = 150;
		rsinterface.children[57] = 4160;		rsinterface.childX[57] = 20;		rsinterface.childY[57] = 150;
		rsinterface.children[58] = 12162;		rsinterface.childX[58] = 65;		rsinterface.childY[58] = 190;
		rsinterface.children[59] = 2832;		rsinterface.childX[59] = 20;		rsinterface.childY[59] = 150;
		rsinterface.children[60] = 13928;		rsinterface.childX[60] = 130;		rsinterface.childY[60] = 190;
		rsinterface.children[61] = 13917;		rsinterface.childX[61] = 20;		rsinterface.childY[61] = 150;
		rsinterface.children[62] = 13984;		rsinterface.childX[62] = 145;		rsinterface.childY[62] = 225;
	}
	
	public static void skillInterface(int i, int j){
		RSInterface rsinterface = interfaceCache[i] = new RSInterface();
		rsinterface.id = i;
		rsinterface.parentID = i;
		rsinterface.type = 5;
		rsinterface.atActionType = 0;
		rsinterface.contentType = 0;
		rsinterface.width = 26;
		rsinterface.height = 34;
		rsinterface.aByte254 = 0;
		rsinterface.mOverInterToTrigger = 0;
		rsinterface.sprite1 = imageLoader(j, "Skill/Skill");
		rsinterface.sprite2 = imageLoader(j, "Skill/Skill");
	}

	public static void itemsOnDeath(TextDrawingArea[] wid) {
		RSInterface rsinterface = addInterface(17100);
		addSprite(17101, 2, 2);
		addHover(17102, 3, 0, 10601, 1, "Interfaces/Equipment/SPRITE", 17, 17, "Close Window");
		addHovered(10601, 3, "Interfaces/Equipment/SPRITE", 17, 17, 10602);
		addText(17103, "Items Kept On Death", wid, 2, 0xff981f);
		addText(17104, "Items you will keep on death (if not skulled):", wid, 1, 0xff981f);
		addText(17105, "Items you will lose on death (if not skulled):", wid, 1, 0xff981f);
		addText(17106, "Information", wid, 1, 0xff981f);
		addText(17107, "Max items kept on death:", wid, 1, 0xffcc33);
		addText(17108, "~ 3 ~", wid, 1, 0xffcc33);
		rsinterface.scrollMax = 0;
		rsinterface.isMouseoverTriggered = false;
		rsinterface.children = new int[12];		rsinterface.childX = new int[12];	rsinterface.childY = new int[12];
		
		rsinterface.children[0] = 17101;		rsinterface.childX[0] = 7;			rsinterface.childY[0] = 8;
		rsinterface.children[1] = 17102;		rsinterface.childX[1] = 480;		rsinterface.childY[1] = 17;        
		rsinterface.children[2] = 17103;		rsinterface.childX[2] = 185;		rsinterface.childY[2] = 18;
		rsinterface.children[3] = 17104;		rsinterface.childX[3] = 22;			rsinterface.childY[3] = 50;
		rsinterface.children[4] = 17105;		rsinterface.childX[4] = 22;			rsinterface.childY[4] = 110;
		rsinterface.children[5] = 17106;		rsinterface.childX[5] = 347;		rsinterface.childY[5] = 47;
		rsinterface.children[6] = 17107;		rsinterface.childX[6] = 349;		rsinterface.childY[6] = 270;
		rsinterface.children[7] = 17108;		rsinterface.childX[7] = 398;		rsinterface.childY[7] = 298;
		rsinterface.children[8] = 17115;		rsinterface.childX[8] = 348;		rsinterface.childY[8] = 64;
		rsinterface.children[9] = 10494;		rsinterface.childX[9] = 26;			rsinterface.childY[9] = 74;
		rsinterface.children[10] = 10600;		rsinterface.childX[10] = 26;		rsinterface.childY[10] = 133;
		rsinterface.children[11] = 10601;		rsinterface.childX[11] = 480;		rsinterface.childY[11] = 17; 
	}

	public static void itemsOnDeathDATA(TextDrawingArea[] wid) {
		RSInterface rsinterface = addInterface(17115);
		addText(17109, "", wid, 0, 0xff981f);		addText(17110, "The normal amount of", wid, 0, 0xff981f);		addText(17111, "items kept is three.", wid, 0, 0xff981f);
		addText(17112, "", wid, 0, 0xff981f);		addText(17113, "If you are skulled,", wid, 0, 0xff981f);		addText(17114, "you will lose all your", wid, 0, 0xff981f);
		addText(17117, "items, unless an item", wid, 0, 0xff981f);		addText(17118, "protecting prayer is", wid, 0, 0xff981f);		addText(17119, "used.", wid, 0, 0xff981f);
		addText(17120, "", wid, 0, 0xff981f);		addText(17121, "Item protecting prayers", wid, 0, 0xff981f);		addText(17122, "will allow you to keep", wid, 0, 0xff981f);
		addText(17123, "one extra item.", wid, 0, 0xff981f);		addText(17124, "", wid, 0, 0xff981f);		addText(17125, "The items kept are", wid, 0, 0xff981f);
		addText(17126, "selected by the server", wid, 0, 0xff981f);		addText(17127, "and include the most", wid, 0, 0xff981f);		addText(17128, "expensive items you're", wid, 0, 0xff981f);
		addText(17129, "carrying.", wid, 0, 0xff981f);		addText(17130, "", wid, 0, 0xff981f);
		rsinterface.parentID = 17115;
		rsinterface.id = 17115;
		rsinterface.type = 0;
		rsinterface.atActionType = 0;
		rsinterface.contentType = 0;
		rsinterface.width = 130;
		rsinterface.height = 197;
		rsinterface.aByte254 = 0;
		rsinterface.mOverInterToTrigger = -1;
		rsinterface.scrollMax = 280;
		rsinterface.children = new int[20];		rsinterface.childX = new int[20];	rsinterface.childY = new int[20];
		rsinterface.children[0] = 17109;		rsinterface.childX[0] = 0;			rsinterface.childY[0] = 0;
		rsinterface.children[1] = 17110;		rsinterface.childX[1] = 0;			rsinterface.childY[1] = 12;
		rsinterface.children[2] = 17111;		rsinterface.childX[2] = 0;			rsinterface.childY[2] = 24;
		rsinterface.children[3] = 17112;		rsinterface.childX[3] = 0;			rsinterface.childY[3] = 36;
		rsinterface.children[4] = 17113;		rsinterface.childX[4] = 0;			rsinterface.childY[4] = 48;
		rsinterface.children[5] = 17114;		rsinterface.childX[5] = 0;			rsinterface.childY[5] = 60;
		rsinterface.children[6] = 17117;		rsinterface.childX[6] = 0;			rsinterface.childY[6] = 72;
		rsinterface.children[7] = 17118;		rsinterface.childX[7] = 0;			rsinterface.childY[7] = 84;
		rsinterface.children[8] = 17119;		rsinterface.childX[8] = 0;			rsinterface.childY[8] = 96;
		rsinterface.children[9] = 17120;		rsinterface.childX[9] = 0;			rsinterface.childY[9] = 108;
		rsinterface.children[10] = 17121;		rsinterface.childX[10] = 0;			rsinterface.childY[10] = 120;
		rsinterface.children[11] = 17122;		rsinterface.childX[11] = 0;			rsinterface.childY[11] = 132;
		rsinterface.children[12] = 17123;		rsinterface.childX[12] = 0;			rsinterface.childY[12] = 144;
		rsinterface.children[13] = 17124;		rsinterface.childX[13] = 0;			rsinterface.childY[13] = 156;
		rsinterface.children[14] = 17125;		rsinterface.childX[14] = 0;			rsinterface.childY[14] = 168;
		rsinterface.children[15] = 17126;		rsinterface.childX[15] = 0;			rsinterface.childY[15] = 180;
		rsinterface.children[16] = 17127;		rsinterface.childX[16] = 0;			rsinterface.childY[16] = 192;
		rsinterface.children[17] = 17128;		rsinterface.childX[17] = 0;			rsinterface.childY[17] = 204;
		rsinterface.children[18] = 17129;		rsinterface.childX[18] = 0;			rsinterface.childY[18] = 216;
		rsinterface.children[19] = 17130;		rsinterface.childX[19] = 0;			rsinterface.childY[19] = 228;
	}

	public static void addSprite(int i, int j, int k) {
		RSInterface rsinterface = interfaceCache[i] = new RSInterface();
		rsinterface.id = i;
		rsinterface.parentID = i;
		rsinterface.type = 5;
		rsinterface.atActionType = 1;
		rsinterface.contentType = 0;
		rsinterface.width = 20;
		rsinterface.height = 20;
		rsinterface.aByte254 = 0;
		rsinterface.mOverInterToTrigger = 52;
		rsinterface.sprite1 = imageLoader(j, "Interfaces/Equipment/SPRITE");
		rsinterface.sprite2 = imageLoader(k, "Interfaces/Equipment/SPRITE");
	}
public static void GodWars(TextDrawingArea[] TDA){
		RSInterface rsinterface = addTab(16210);
		addText(16211, "NPC killcount", 0xff9040, false, true, 52, TDA, 0);
		addText(16212, "Armadyl kills", 0xff9040, false, true, 52, TDA, 0);
		addText(16213, "Bandos kills", 0xff9040, false, true, 52, TDA, 0);
		addText(16214, "Saradomin kills", 0xff9040, false, true, 52, TDA, 0);
		addText(16215, "Zamorak kills", 0xff9040, false, true, 52, TDA, 0);
		addText(16216, "0", 0x66FFFF, false, true, 52, TDA, 0);//armadyl
		addText(16217, "0", 0x66FFFF, false, true, 52, TDA, 0);//bandos
		addText(16218, "0", 0x66FFFF, false, true, 52, TDA, 0);//saradomin
		addText(16219, "0", 0x66FFFF, false, true, 52, TDA, 0);//zamorak
		rsinterface.scrollMax = 0;
		rsinterface.children = new int[9];
		rsinterface.childX = new int[9];
		rsinterface.childY = new int[9];
		rsinterface.children[0] = 16211;
			rsinterface.childX[0] = -52+375+30;		rsinterface.childY[0] = 7;
		rsinterface.children[1] = 16212;
			rsinterface.childX[1] = -52+375+30;		rsinterface.childY[1] = 30;
		rsinterface.children[2] = 16213;
			rsinterface.childX[2] = -52+375+30;		rsinterface.childY[2] = 44;
		rsinterface.children[3] = 16214;
		rsinterface.childX[3] = -52+375+30;		rsinterface.childY[3] = 58;
			rsinterface.children[4] = 16215;
			rsinterface.childX[4] = -52+375+30;		rsinterface.childY[4] = 73;
		
		rsinterface.children[5] = 16216;
			rsinterface.childX[5] = -52+460+60;		rsinterface.childY[5] = 31;
		rsinterface.children[6] = 16217;
			rsinterface.childX[6] = -52+460+60;		rsinterface.childY[6] = 45;
		rsinterface.children[7] = 16218;
			rsinterface.childX[7] = -52+460+60;		rsinterface.childY[7] = 59;
		rsinterface.children[8] = 16219;
			rsinterface.childX[8] = -52+460+60;		rsinterface.childY[8] = 74;
	}

	public static void magicTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(1151);
		RSInterface homeHover = addTabInterface(1196);
		RSInterface spellButtons = interfaceCache[12424];
		spellButtons.scrollMax = 0; spellButtons.height = 260; spellButtons.width = 190;
		int[] spellButton = {
			1196, 1199, 1206, 1215, 1224, 1231, 1240, 1249, 1258, 1267, 1274, 1283, 1573,
			1290, 1299, 1308, 1315, 1324, 1333, 1340, 1349, 1358, 1367, 1374, 1381, 1388,
			1397, 1404, 1583, 12038, 1414, 1421, 1430, 1437, 1446, 1453, 1460, 1469, 15878,
			1602, 1613, 1624, 7456, 1478, 1485, 1494, 1503, 1512, 1521, 1530, 1544, 1553,
			1563, 1593, 1635, 12426, 12436, 12446, 12456, 6004, 18471
		};
		tab.totalChildren(63);
		tab.child(0, 12424, 13, 24);
		for(int i1 = 0; i1 < spellButton.length; i1++) {
			int yPos = i1 > 34 ? 8 : 183;
			tab.child(1, 1195, 13, 24);
			tab.child(i1 + 2, spellButton[i1], 5, yPos);
			addButton(1195, 1, "Magic/Home", "Cast @gre@Home Teleport");
			RSInterface homeButton = interfaceCache[1195];
			homeButton.mOverInterToTrigger = 1196;
		}
		for(int i2 = 0; i2 < spellButton.length; i2++) {
			if(i2 < 60)
				spellButtons.childX[i2] = spellButtons.childX[i2] + 24;
			if(i2 == 6 || i2 == 12 || i2 == 19 || i2 == 35 || i2 == 41 || i2 == 44 || i2 == 49 || i2 == 51)
				spellButtons.childX[i2] = 0;
			spellButtons.childY[6] = 24; spellButtons.childY[12] = 48;
			spellButtons.childY[19] = 72; spellButtons.childY[49] = 96;
			spellButtons.childY[44] = 120; spellButtons.childY[51] = 144;
			spellButtons.childY[35] = 170; spellButtons.childY[41] = 192;
		}
		homeHover.isMouseoverTriggered = true;
		addText(1197, "Level 0: Home Teleport", tda, 1, 0xFE981F, true, true);
		RSInterface homeLevel = interfaceCache[1197]; homeLevel.width = 174; homeLevel.height = 68;
		addText(1198, "A teleport which requires no", tda, 0, 0xAF6A1A, true, true);
		addText(18998, "runes and no required level that", tda, 0, 0xAF6A1A, true, true);
		addText(18999, "teleports you to the main land.", tda, 0, 0xAF6A1A, true, true);
		homeHover.totalChildren(4);
		homeHover.child(0, 1197, 3, 4);
		homeHover.child(1, 1198, 91, 23);
		homeHover.child(2, 18998, 91, 34);
		homeHover.child(3, 18999, 91, 45);
		resetRunesAndLevels(1541);
	}

    public static void ancientMagicTab(TextDrawingArea atextdrawingarea[])
    {
        RSInterface rsinterface = addInterface(12855);
        addButton(12856, 1, "Magic/Home", "Cast @gre@Home Teleport");
        RSInterface rsinterface1 = interfaceCache[12856];
        rsinterface1.mOverInterToTrigger = 1196;
        int ai[] = {
            12856, 12939, 12987, 13035, 12901, 12861, 13045, 12963, 13011, 13053, 
            12919, 12881, 13061, 12951, 12999, 13069, 12911, 12871, 13079, 12975, 
            13023, 13087, 12929, 12891, 13095, 1196, 12940, 12988, 13036, 12902, 
            12862, 13046, 12964, 13012, 13054, 12920, 12882, 13062, 12952, 13000, 
            13070, 12912, 12872, 13080, 12976, 13024, 13088, 12930, 12892, 13096
		};
        rsinterface.totalChildren(ai.length);
        int i = 0;
        int j = 18;
        int k = 8;
        while(i < ai.length) 
        {
            if(j > 175)
            {
                j = 18;
                k += 28;
            }
            if(i < 25)
            {
                rsinterface.child(i, ai[i], j, k);
            }
            if(i > 24)
            {
                k = i >= 41 ? 1 : 181;
                rsinterface.child(i, ai[i], 4, k);
            }
            i++;
            j += 45;
        }
    }

    public static void drawBlackBox(int i, int j)
    {
        DrawingArea.drawPixels(71, j - 1, i - 2, 0x726451, 1);
        DrawingArea.drawPixels(69, j, i + 174, 0x726451, 1);
        DrawingArea.drawPixels(1, j - 2, i - 2, 0x726451, 178);
        DrawingArea.drawPixels(1, j + 68, i, 0x726451, 174);
        DrawingArea.drawPixels(71, j - 1, i - 1, 0x2e2b23, 1);
        DrawingArea.drawPixels(71, j - 1, i + 175, 0x2e2b23, 1);
        DrawingArea.drawPixels(1, j - 1, i, 0x2e2b23, 175);
        DrawingArea.drawPixels(1, j + 69, i, 0x2e2b23, 175);
        DrawingArea.method335(0, j, 174, 68, 220, i);
    }

    public static void addButton(int i, int j, String s, String s1)
    {
        RSInterface rsinterface = interfaceCache[i] = new RSInterface();
        rsinterface.id = i;
        rsinterface.parentID = i;
        rsinterface.type = 5;
        rsinterface.atActionType = 1;
        rsinterface.contentType = 0;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = 52;
        rsinterface.sprite1 = imageLoader(j, s);
        rsinterface.sprite2 = imageLoader(j, s);
        rsinterface.width = rsinterface.sprite1.myWidth;
        rsinterface.height = rsinterface.sprite2.myHeight;
        rsinterface.tooltip = s1;
    }

    public static void prayerTab(TextDrawingArea atextdrawingarea[])
    {
        RSInterface rsinterface = addTabInterface(5608);
        RSInterface rsinterface1 = interfaceCache[687];
        rsinterface1.textColor = 0xff981f;
        rsinterface1.textShadow = true;
        rsinterface1.message = "%1/%2";
        addSprite(5651, 0, "PRAYER");
        addPrayer(18000, 0, 601, 7, 0, "Sharp Eye");
        addPrayer(18002, 0, 602, 8, 1, "Mystic Will");
        addPrayer(18004, 0, 603, 25, 2, "Hawk Eye");
        addPrayer(18006, 0, 604, 26, 3, "Mystic Lore");
        addPrayer(18008, 0, 605, 43, 4, "Eagle Eye");
        addPrayer(18010, 0, 606, 44, 5, "Mystic Might");
        addPrayer(18012, 0, 607, 59, 6, "Chivalry");
        addPrayer(18014, 0, 608, 69, 7, "Piety");
        rsinterface.totalChildren(54);
        rsinterface.child(0, 5609, 6, 4);
        rsinterface.child(1, 5610, 42, 4);
        rsinterface.child(2, 5611, 78, 4);
        rsinterface.child(3, 5612, 6, 40);
        rsinterface.child(4, 5613, 42, 40);
        rsinterface.child(5, 5614, 78, 40);
        rsinterface.child(6, 5615, 114, 40);
        rsinterface.child(7, 5616, 150, 40);
        rsinterface.child(8, 5617, 6, 76);
        rsinterface.child(9, 5618, 114, 76);
        rsinterface.child(10, 5619, 150, 76);
        rsinterface.child(11, 5620, 6, 112);
        rsinterface.child(12, 5621, 42, 112);
        rsinterface.child(13, 5622, 78, 112);
        rsinterface.child(14, 5623, 114, 112);
        rsinterface.child(15, 683, 42, 148);
        rsinterface.child(16, 684, 78, 148);
        rsinterface.child(17, 685, 114, 148);
        rsinterface.child(18, 5632, 8, 6);
        rsinterface.child(19, 5633, 44, 6);
        rsinterface.child(20, 5634, 80, 6);
        rsinterface.child(21, 5635, 8, 42);
        rsinterface.child(22, 5636, 44, 42);
        rsinterface.child(23, 5637, 80, 42);
        rsinterface.child(24, 5638, 116, 42);
        rsinterface.child(25, 5639, 152, 42);
        rsinterface.child(26, 5640, 8, 79);
        rsinterface.child(27, 5641, 116, 78);
        rsinterface.child(28, 5642, 152, 78);
        rsinterface.child(29, 5643, 8, 114);
        rsinterface.child(30, 5644, 44, 114);
        rsinterface.child(31, 686, 80, 114);
        rsinterface.child(32, 5645, 116, 114);
        rsinterface.child(33, 5649, 44, 150);
        rsinterface.child(34, 5647, 80, 150);
        rsinterface.child(35, 5648, 116, 150);
        rsinterface.child(36, 18000, 114, 4);
        rsinterface.child(37, 18001, 117, 8);
        rsinterface.child(38, 18002, 150, 4);
        rsinterface.child(39, 18003, 153, 7);
        rsinterface.child(40, 18004, 42, 76);
        rsinterface.child(41, 18005, 45, 80);
        rsinterface.child(42, 18006, 78, 76);
        rsinterface.child(43, 18007, 81, 79);
        rsinterface.child(44, 18008, 150, 112);
        rsinterface.child(45, 18009, 153, 116);
        rsinterface.child(46, 18010, 6, 148);
        rsinterface.child(47, 18011, 9, 151);
        rsinterface.child(48, 18012, 150, 148);
        rsinterface.child(49, 18013, 157, 151);
        rsinterface.child(50, 18014, 6, 184);
        rsinterface.child(51, 18015, 8, 194);
        rsinterface.child(52, 5651, 65, 242);
        rsinterface.child(53, 687, 85, 241);
        String as[] = {
            "Thick Skin", "Burst of Strength", "Clarity of Thought", "Rock Skin", "Superhuman Strength", "Improved Reflexes", "Rapid Restore", "Rapid Heal", "Protect Item", "Steel Skin", 
            "Ultimate Strength", "Incredible Reflexes", "Protect from Magic", "Protect from Range", "Protect from Melee", "Retribution", "Redemption", "Smite"
        };
        int i = 0;
        for(int j = 5609; j <= 5623; j++)
        {
            RSInterface rsinterface2 = interfaceCache[j];
            rsinterface2.tooltip = (new StringBuilder()).append("Activate @or2@").append(as[i++]).toString();
        }

        for(int k = 683; k <= 685; k++)
        {
            RSInterface rsinterface3 = interfaceCache[k];
            rsinterface3.tooltip = (new StringBuilder()).append("Activate @or2@").append(as[i++]).toString();
        }

    }

    public static void addTooltipBox(int i, String s)
    {
        RSInterface rsinterface = addInterface(i);
        rsinterface.id = i;
        rsinterface.parentID = i;
        rsinterface.type = 8;
        rsinterface.popupString = s;
    }

	public static void drawTooltip(int id, String text) {
		RSInterface rsinterface = addTabInterface(id);
		rsinterface.parentID = id;
		rsinterface.type = 0;
        rsinterface.isMouseoverTriggered = true;
        rsinterface.mOverInterToTrigger = -1;
		rsinterface.tooltip = text;
		rsinterface.totalChildren(1);
		rsinterface.child(0, id + 1, 0, 0);
	}

    public static void addTooltip(int i, String s)
    {
        RSInterface rsinterface = addInterface(i);
        rsinterface.id = i;
        rsinterface.type = 0;
        rsinterface.isMouseoverTriggered = true;
        rsinterface.mOverInterToTrigger = -1;
        addTooltipBox(i + 1, s);
        rsinterface.totalChildren(1);
        rsinterface.child(0, i + 1, 0, 0);
    }

    public static void addPrayer(int i, int j, int k, int l, int i1, String s)
    {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.id = i;
        rsinterface.parentID = 5608;
        rsinterface.type = 5;
        rsinterface.atActionType = 4;
        rsinterface.contentType = 0;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = -1;
        rsinterface.sprite1 = imageLoader(0, "PRAYERGLOW");
        rsinterface.sprite2 = imageLoader(1, "PRAYERGLOW");
        rsinterface.width = 34;
        rsinterface.height = 34;
        rsinterface.anIntArray245 = new int[1];
        rsinterface.anIntArray212 = new int[1];
        rsinterface.anIntArray245[0] = 1;
        rsinterface.anIntArray212[0] = j;
        rsinterface.valueIndexArray = new int[1][3];
        rsinterface.valueIndexArray[0][0] = 5;
        rsinterface.valueIndexArray[0][1] = k;
        rsinterface.valueIndexArray[0][2] = 0;
        rsinterface.tooltip = (new StringBuilder()).append("Activate@or2@ ").append(s).toString();
        RSInterface rsinterface1 = addTabInterface(i + 1);
        rsinterface1.id = i + 1;
        rsinterface1.parentID = 5608;
        rsinterface1.type = 5;
        rsinterface1.atActionType = 0;
        rsinterface1.contentType = 0;
        rsinterface1.aByte254 = 0;
        rsinterface1.mOverInterToTrigger = -1;
        rsinterface1.sprite1 = imageLoader(i1, "/PRAYER/PRAYON");
        rsinterface1.sprite2 = imageLoader(i1, "/PRAYER/PRAYOFF");
        rsinterface1.width = 34;
        rsinterface1.height = 34;
        rsinterface1.anIntArray245 = new int[1];
        rsinterface1.anIntArray212 = new int[1];
        rsinterface1.anIntArray245[0] = 2;
        rsinterface1.anIntArray212[0] = 1 + 1;
        rsinterface1.valueIndexArray = new int[1][3];
        rsinterface1.valueIndexArray[0][0] = 2;
        rsinterface1.valueIndexArray[0][1] = 5;
        rsinterface1.valueIndexArray[0][2] = 0;
    }

    public static void friendsTab(TextDrawingArea atextdrawingarea[])
    {
        RSInterface rsinterface = addTabInterface(5065);
        RSInterface rsinterface1 = interfaceCache[5066];
        addText(5067, "Friends List", atextdrawingarea, 1, 0xff9933, true, true);
        addText(5070, "Add Friend", atextdrawingarea, 0, 0xff9933, false, true);
        addText(5071, "Delete Friend", atextdrawingarea, 0, 0xff9933, false, true);
        addSprite(16126, 4, "/Friends/SPRITE");
        addSprite(16127, 8, "/Friends/SPRITE");
        addHoverButton(5068, "/Friends/SPRITE", 6, 72, 32, "Add Friend", 201, 5072, 1);
        addHoveredButton(5072, "/Friends/SPRITE", 7, 72, 32, 5073);
        addHoverButton(5069, "/Friends/SPRITE", 6, 72, 32, "Delete Friend", 202, 5074, 1);
        addHoveredButton(5074, "/Friends/SPRITE", 7, 72, 32, 5075);
        rsinterface.totalChildren(11);
        rsinterface.child(0, 5067, 95, 4);
        rsinterface.child(1, 16127, 0, 25);
        rsinterface.child(2, 16126, 0, 221);
        rsinterface.child(3, 5066, 0, 24);
        rsinterface.child(4, 16126, 0, 22);
        rsinterface.child(5, 5068, 15, 226);
        rsinterface.child(6, 5072, 15, 226);
        rsinterface.child(7, 5069, 103, 226);
        rsinterface.child(8, 5074, 103, 226);
        rsinterface.child(9, 5070, 25, 237);
        rsinterface.child(10, 5071, 106, 237);
        rsinterface1.height = 196;
        rsinterface1.width = 174;
        int i = 5092;
        for(int j = 0; i <= 5191 && j <= 99; j++)
        {
            rsinterface1.children[j] = i;
            rsinterface1.childX[j] = 3;
            rsinterface1.childY[j] = rsinterface1.childY[j] - 7;
            i++;
        }

        i = 5192;
        for(int k = 100; i <= 5291 && k <= 199; k++)
        {
            rsinterface1.children[k] = i;
            rsinterface1.childX[k] = 131;
            rsinterface1.childY[k] = rsinterface1.childY[k] - 7;
            i++;
        }

    }

    public static void ignoreTab(TextDrawingArea atextdrawingarea[])
    {
        RSInterface rsinterface = addTabInterface(5715);
        RSInterface rsinterface1 = interfaceCache[5716];
        addText(5717, "Ignore List", atextdrawingarea, 1, 0xff9933, true, true);
        addText(5720, "Add Name", atextdrawingarea, 0, 0xff9933, false, true);
        addText(5721, "Delete Name", atextdrawingarea, 0, 0xff9933, false, true);
        addHoverButton(5718, "/Friends/SPRITE", 6, 72, 32, "Add Name", 501, 5722, 1);
        addHoveredButton(5722, "/Friends/SPRITE", 7, 72, 32, 5723);
        addHoverButton(5719, "/Friends/SPRITE", 6, 72, 32, "Delete Name", 502, 5724, 1);
        addHoveredButton(5724, "/Friends/SPRITE", 7, 72, 32, 5725);
        rsinterface.totalChildren(11);
        rsinterface.child(0, 5717, 95, 4);
        rsinterface.child(1, 16127, 0, 25);
        rsinterface.child(2, 16126, 0, 221);
        rsinterface.child(3, 5716, 0, 24);
        rsinterface.child(4, 16126, 0, 22);
        rsinterface.child(5, 5718, 15, 226);
        rsinterface.child(6, 5722, 15, 226);
        rsinterface.child(7, 5719, 103, 226);
        rsinterface.child(8, 5724, 103, 226);
        rsinterface.child(9, 5720, 27, 237);
        rsinterface.child(10, 5721, 108, 237);
        rsinterface1.height = 196;
        rsinterface1.width = 174;
        int i = 5742;
        for(int j = 0; i <= 5841 && j <= 99; j++)
        {
            rsinterface1.children[j] = i;
            rsinterface1.childX[j] = 3;
            rsinterface1.childY[j] = rsinterface1.childY[j] - 7;
            i++;
        }

    }

    private static Sprite CustomSpriteLoader(int i, String s)
    {
        long l = (TextClass.method585(s) << 8) + (long)i;
        Sprite sprite = (Sprite)aMRUNodes_238.insertFromCache(l);
        if(sprite != null)
        {
            return sprite;
        }
        try
        {
            sprite = new Sprite((new StringBuilder()).append("/Attack/").append(i).append(s).toString());
            aMRUNodes_238.removeFromCache(sprite, l);
        }
        catch(Exception exception)
        {
            return null;
        }
        return sprite;
    }

    public static RSInterface addInterface(int i)
    {
        RSInterface rsinterface = interfaceCache[i] = new RSInterface();
        rsinterface.id = i;
        rsinterface.parentID = i;
        rsinterface.width = 512;
        rsinterface.height = 334;
        return rsinterface;
    }

    public static void addText(int i, String s, TextDrawingArea textDrawingAreas[], int j, int k, boolean flag)
    {
        RSInterface rsinterface = interfaceCache[i] = new RSInterface();
        if(flag)
        {
            rsinterface.centerText = true;
        }
        rsinterface.textShadow = true;
        rsinterface.textDrawingAreas = textDrawingAreas[j];
        rsinterface.message = s;
        rsinterface.textColor = k;
        rsinterface.id = i;
        rsinterface.type = 4;
    }

    public static void textColor(int i, int j)
    {
        RSInterface rsinterface = interfaceCache[i];
        rsinterface.textColor = j;
    }

    public static void textSize(int i, TextDrawingArea textDrawingAreas[], int j)
    {
        RSInterface rsinterface = interfaceCache[i];
        rsinterface.textDrawingAreas = textDrawingAreas[j];
    }

    public static void addCacheSprite(int i, int j, int k, String s)
    {
        RSInterface rsinterface = interfaceCache[i] = new RSInterface();
        rsinterface.sprite1 = method207(j, aClass44, s);
        rsinterface.sprite2 = method207(k, aClass44, s);
        rsinterface.parentID = i;
        rsinterface.id = i;
        rsinterface.type = 5;
    }

    public static void sprite1(int i, int j)
    {
        RSInterface rsinterface = interfaceCache[i];
        rsinterface.sprite1 = CustomSpriteLoader(j, "");
    }

    public static void addActionButton(int i, int j, int k, int l, int i1, String s)
    {
        RSInterface rsinterface = interfaceCache[i] = new RSInterface();
        rsinterface.sprite1 = CustomSpriteLoader(j, "");
        if(k == j)
        {
            rsinterface.sprite2 = CustomSpriteLoader(j, "a");
        } else
        {
            rsinterface.sprite2 = CustomSpriteLoader(k, "");
        }
        rsinterface.tooltip = s;
        rsinterface.contentType = 0;
        rsinterface.atActionType = 1;
        rsinterface.width = l;
        rsinterface.mOverInterToTrigger = 52;
        rsinterface.parentID = i;
        rsinterface.id = i;
        rsinterface.type = 5;
        rsinterface.height = i1;
    }

    public static void addToggleButton(int i, int j, int k, int l, int i1, String s)
    {
        RSInterface rsinterface = addInterface(i);
        rsinterface.sprite1 = CustomSpriteLoader(j, "");
        rsinterface.sprite2 = CustomSpriteLoader(j, "a");
        rsinterface.anIntArray212 = new int[1];
        rsinterface.anIntArray212[0] = 1;
        rsinterface.anIntArray245 = new int[1];
        rsinterface.anIntArray245[0] = 1;
        rsinterface.valueIndexArray = new int[1][3];
        rsinterface.valueIndexArray[0][0] = 5;
        rsinterface.valueIndexArray[0][1] = k;
        rsinterface.valueIndexArray[0][2] = 0;
        rsinterface.atActionType = 4;
        rsinterface.width = l;
        rsinterface.mOverInterToTrigger = -1;
        rsinterface.parentID = i;
        rsinterface.id = i;
        rsinterface.type = 5;
        rsinterface.height = i1;
        rsinterface.tooltip = s;
    }

    public void totalChildren(int i, int j, int k)
    {
        children = new int[i];
        childX = new int[j];
        childY = new int[k];
    }

    public static void removeSomething(int i)
    {
        RSInterface rsinterface = interfaceCache[i] = new RSInterface();
    }

    public void specialBar(int i)
    {
        addActionButton(i - 12, 7587, -1, 150, 26, "Use @gre@Special Attack");
        for(int j = i - 11; j < i; j++)
        {
            removeSomething(j);
        }

        RSInterface rsinterface = interfaceCache[i - 12];
        rsinterface.width = 150;
        rsinterface.height = 26;
        rsinterface = interfaceCache[i];
        rsinterface.width = 150;
        rsinterface.height = 26;
        rsinterface.child(0, i - 12, 0, 0);
        rsinterface.child(12, i + 1, 3, 7);
        rsinterface.child(23, i + 12, 16, 8);
        for(int k = 13; k < 23; k++)
        {
            rsinterface.childY[k]--;
        }

        rsinterface = interfaceCache[i + 1];
        rsinterface.type = 5;
        rsinterface.sprite1 = CustomSpriteLoader(7600, "");
        for(int l = i + 2; l < i + 12; l++)
        {
            RSInterface rsinterface1 = interfaceCache[l];
            rsinterface1.type = 5;
        }

        sprite1(i + 2, 7601);
        sprite1(i + 3, 7602);
        sprite1(i + 4, 7603);
        sprite1(i + 5, 7604);
        sprite1(i + 6, 7605);
        sprite1(i + 7, 7606);
        sprite1(i + 8, 7607);
        sprite1(i + 9, 7608);
        sprite1(i + 10, 7609);
        sprite1(i + 11, 7610);
    }

    public static void Sidebar0(TextDrawingArea atextdrawingarea[])
    {
        Sidebar0a(1698, 1701, 7499, "Chop", "Hack", "Smash", "Block", 42, 75, 127, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, atextdrawingarea);
        Sidebar0a(2276, 2279, 7574, "Stab", "Lunge", "Slash", "Block", 43, 75, 124, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, atextdrawingarea);
        Sidebar0a(2423, 2426, 7599, "Chop", "Slash", "Lunge", "Block", 42, 75, 125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, atextdrawingarea);
        Sidebar0a(3796, 3799, 7624, "Pound", "Pummel", "Spike", "Block", 39, 75, 121, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, atextdrawingarea);
        Sidebar0a(4679, 4682, 7674, "Lunge", "Swipe", "Pound", "Block", 40, 75, 124, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, atextdrawingarea);
        Sidebar0a(4705, 4708, 7699, "Chop", "Slash", "Smash", "Block", 42, 75, 125, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, atextdrawingarea);
        Sidebar0a(5570, 5573, 7724, "Spike", "Impale", "Smash", "Block", 41, 75, 123, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, atextdrawingarea);
        Sidebar0a(7762, 7765, 7800, "Chop", "Slash", "Lunge", "Block", 42, 75, 125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, atextdrawingarea);
        Sidebar0b(776, 779, "Reap", "Chop", "Jab", "Block", 42, 75, 126, 75, 46, 128, 125, 128, 122, 103, 122, 50, 40, 103, 40, 50, atextdrawingarea);
        Sidebar0c(425, 428, 7474, "Pound", "Pummel", "Block", 39, 75, 121, 75, 42, 128, 40, 103, 40, 50, 122, 50, atextdrawingarea);
        Sidebar0c(1749, 1752, 7524, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, atextdrawingarea);
        Sidebar0c(1764, 1767, 7549, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, atextdrawingarea);
        Sidebar0c(4446, 4449, 7649, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, atextdrawingarea);
        Sidebar0c(5855, 5857, 7749, "Punch", "Kick", "Block", 40, 75, 129, 75, 42, 128, 40, 50, 122, 50, 40, 103, atextdrawingarea);
        Sidebar0c(6103, 6132, 6117, "Bash", "Pound", "Block", 43, 75, 124, 75, 42, 128, 40, 103, 40, 50, 122, 50, atextdrawingarea);
        Sidebar0c(8460, 8463, 8493, "Jab", "Swipe", "Fend", 46, 75, 124, 75, 43, 128, 40, 103, 40, 50, 122, 50, atextdrawingarea);
        Sidebar0c(12290, 12293, 12323, "Flick", "Lash", "Deflect", 44, 75, 127, 75, 36, 128, 40, 50, 40, 103, 122, 50, atextdrawingarea);
        Sidebar0d(328, 331, "Bash", "Pound", "Focus", 42, 66, 39, 101, 41, 136, 40, 120, 40, 50, 40, 85, atextdrawingarea);
        RSInterface rsinterface = addInterface(19300);
        textSize(3983, atextdrawingarea, 0);
        addToggleButton(150, 150, 172, 150, 44, "Auto Retaliate");
        rsinterface.totalChildren(2, 2, 2);
        rsinterface.child(0, 3983, 52, 25);
        rsinterface.child(1, 150, 21, 153);
        rsinterface = interfaceCache[3983];
        rsinterface.centerText = true;
        rsinterface.textColor = 0xff981f;
    }

    public static void Sidebar0a(int i, int j, int k, String s, String s1, String s2, String s3, int l, 
            int i1, int j1, int k1, int l1, int i2, int j2, int k2, 
            int l2, int i3, int j3, int k3, int l3, int i4, int j4, 
            int k4, TextDrawingArea atextdrawingarea[])
    {
        RSInterface rsinterface = addInterface(i);
        addText(j, "-2", atextdrawingarea, 3, 0xff981f, true);
        addText(j + 11, s, atextdrawingarea, 0, 0xff981f, false);
        addText(j + 12, s1, atextdrawingarea, 0, 0xff981f, false);
        addText(j + 13, s2, atextdrawingarea, 0, 0xff981f, false);
        addText(j + 14, s3, atextdrawingarea, 0, 0xff981f, false);
        rsinterface.specialBar(k);
        rsinterface.width = 190;
        rsinterface.height = 261;
        byte byte0 = 15;
        int l4 = 0;
        rsinterface.totalChildren(byte0, byte0, byte0);
        rsinterface.child(l4, j + 3, 21, 46);
        l4++;
        rsinterface.child(l4, j + 4, 104, 99);
        l4++;
        rsinterface.child(l4, j + 5, 21, 99);
        l4++;
        rsinterface.child(l4, j + 6, 105, 46);
        l4++;
        rsinterface.child(l4, j + 7, l2, i3);
        l4++;
        rsinterface.child(l4, j + 8, j3, k3);
        l4++;
        rsinterface.child(l4, j + 9, l3, i4);
        l4++;
        rsinterface.child(l4, j + 10, j4, k4);
        l4++;
        rsinterface.child(l4, j + 11, l, i1);
        l4++;
        rsinterface.child(l4, j + 12, j1, k1);
        l4++;
        rsinterface.child(l4, j + 13, l1, i2);
        l4++;
        rsinterface.child(l4, j + 14, j2, k2);
        l4++;
        rsinterface.child(l4, 19300, 0, 0);
        l4++;
        rsinterface.child(l4, j, 94, 4);
        l4++;
        rsinterface.child(l4, k, 21, 205);
        l4++;
        for(int i5 = j + 3; i5 < j + 7; i5++)
        {
            RSInterface rsinterface1 = interfaceCache[i5];
            rsinterface1.sprite1 = CustomSpriteLoader(19301, "");
            rsinterface1.sprite2 = CustomSpriteLoader(19301, "a");
            rsinterface1.width = 68;
            rsinterface1.height = 44;
        }

    }

    public static void Sidebar0b(int i, int j, String s, String s1, String s2, String s3, int k, int l, 
            int i1, int j1, int k1, int l1, int i2, int j2, int k2, 
            int l2, int i3, int j3, int k3, int l3, int i4, int j4, 
            TextDrawingArea atextdrawingarea[])
    {
        RSInterface rsinterface = addInterface(i);
        addText(j, "-2", atextdrawingarea, 3, 0xff981f, true);
        addText(j + 11, s, atextdrawingarea, 0, 0xff981f, false);
        addText(j + 12, s1, atextdrawingarea, 0, 0xff981f, false);
        addText(j + 13, s2, atextdrawingarea, 0, 0xff981f, false);
        addText(j + 14, s3, atextdrawingarea, 0, 0xff981f, false);
        rsinterface.width = 190;
        rsinterface.height = 261;
        byte byte0 = 14;
        int k4 = 0;
        rsinterface.totalChildren(byte0, byte0, byte0);
        rsinterface.child(k4, j + 3, 21, 46);
        k4++;
        rsinterface.child(k4, j + 4, 104, 99);
        k4++;
        rsinterface.child(k4, j + 5, 21, 99);
        k4++;
        rsinterface.child(k4, j + 6, 105, 46);
        k4++;
        rsinterface.child(k4, j + 7, k2, l2);
        k4++;
        rsinterface.child(k4, j + 8, i3, j3);
        k4++;
        rsinterface.child(k4, j + 9, k3, l3);
        k4++;
        rsinterface.child(k4, j + 10, i4, j4);
        k4++;
        rsinterface.child(k4, j + 11, k, l);
        k4++;
        rsinterface.child(k4, j + 12, i1, j1);
        k4++;
        rsinterface.child(k4, j + 13, k1, l1);
        k4++;
        rsinterface.child(k4, j + 14, i2, j2);
        k4++;
        rsinterface.child(k4, 19300, 0, 0);
        k4++;
        rsinterface.child(k4, j, 94, 4);
        k4++;
        for(int l4 = j + 3; l4 < j + 7; l4++)
        {
            RSInterface rsinterface1 = interfaceCache[l4];
            rsinterface1.sprite1 = CustomSpriteLoader(19301, "");
            rsinterface1.sprite2 = CustomSpriteLoader(19301, "a");
            rsinterface1.width = 68;
            rsinterface1.height = 44;
        }

    }

    public static void Sidebar0c(int i, int j, int k, String s, String s1, String s2, int l, int i1, 
            int j1, int k1, int l1, int i2, int j2, int k2, int l2, 
            int i3, int j3, int k3, TextDrawingArea atextdrawingarea[])
    {
        RSInterface rsinterface = addInterface(i);
        addText(j, "-2", atextdrawingarea, 3, 0xff981f, true);
        addText(j + 9, s, atextdrawingarea, 0, 0xff981f, false);
        addText(j + 10, s1, atextdrawingarea, 0, 0xff981f, false);
        addText(j + 11, s2, atextdrawingarea, 0, 0xff981f, false);
        rsinterface.specialBar(k);
        rsinterface.width = 190;
        rsinterface.height = 261;
        byte byte0 = 12;
        int l3 = 0;
        rsinterface.totalChildren(byte0, byte0, byte0);
        rsinterface.child(l3, j + 3, 21, 99);
        l3++;
        rsinterface.child(l3, j + 4, 105, 46);
        l3++;
        rsinterface.child(l3, j + 5, 21, 46);
        l3++;
        rsinterface.child(l3, j + 6, j2, k2);
        l3++;
        rsinterface.child(l3, j + 7, l2, i3);
        l3++;
        rsinterface.child(l3, j + 8, j3, k3);
        l3++;
        rsinterface.child(l3, j + 9, l, i1);
        l3++;
        rsinterface.child(l3, j + 10, j1, k1);
        l3++;
        rsinterface.child(l3, j + 11, l1, i2);
        l3++;
        rsinterface.child(l3, 19300, 0, 0);
        l3++;
        rsinterface.child(l3, j, 94, 4);
        l3++;
        rsinterface.child(l3, k, 21, 205);
        l3++;
        for(int i4 = j + 3; i4 < j + 6; i4++)
        {
            RSInterface rsinterface1 = interfaceCache[i4];
            rsinterface1.sprite1 = CustomSpriteLoader(19301, "");
            rsinterface1.sprite2 = CustomSpriteLoader(19301, "a");
            rsinterface1.width = 68;
            rsinterface1.height = 44;
        }

    }

    public static void Sidebar0d(int i, int j, String s, String s1, String s2, int k, int l, int i1, 
            int j1, int k1, int l1, int i2, int j2, int k2, int l2, 
            int i3, int j3, TextDrawingArea atextdrawingarea[])
    {
        RSInterface rsinterface = addInterface(i);
        addText(j, "-2", atextdrawingarea, 3, 0xff981f, true);
        addText(j + 9, s, atextdrawingarea, 0, 0xff981f, false);
        addText(j + 10, s1, atextdrawingarea, 0, 0xff981f, false);
        addText(j + 11, s2, atextdrawingarea, 0, 0xff981f, false);
        removeSomething(353);
        addText(354, "Spell", atextdrawingarea, 0, 0xff981f, false);
        addCacheSprite(337, 19, 0, "combaticons");
        addCacheSprite(338, 13, 0, "combaticons2");
        addCacheSprite(339, 14, 0, "combaticons2");
        removeSomething(349);
        addToggleButton(350, 350, 108, 68, 44, "Select");
        rsinterface.width = 190;
        rsinterface.height = 261;
        byte byte0 = 15;
        int k3 = 0;
        rsinterface.totalChildren(byte0, byte0, byte0);
        rsinterface.child(k3, j + 3, 20, 115);
        k3++;
        rsinterface.child(k3, j + 4, 20, 80);
        k3++;
        rsinterface.child(k3, j + 5, 20, 45);
        k3++;
        rsinterface.child(k3, j + 6, i2, j2);
        k3++;
        rsinterface.child(k3, j + 7, k2, l2);
        k3++;
        rsinterface.child(k3, j + 8, i3, j3);
        k3++;
        rsinterface.child(k3, j + 9, k, l);
        k3++;
        rsinterface.child(k3, j + 10, i1, j1);
        k3++;
        rsinterface.child(k3, j + 11, k1, l1);
        k3++;
        rsinterface.child(k3, 349, 105, 46);
        k3++;
        rsinterface.child(k3, 350, 104, 106);
        k3++;
        rsinterface.child(k3, 353, 125, 74);
        k3++;
        rsinterface.child(k3, 354, 125, 134);
        k3++;
        rsinterface.child(k3, 19300, 0, 0);
        k3++;
        rsinterface.child(k3, j, 94, 4);
        k3++;
    }

    public static void emoteTab()
    {
		RSInterface tab = addTabInterface(147);
		RSInterface scroll = addTabInterface(148);
		tab.totalChildren(1);
		tab.child(0, 148, 0, 1);
		int[] ButtonIDs = { 168, 169, 164, 165, 162, 163,13370, 171, 167, 170, 13366, 13368, 166, 13363, 13364, 13365, 161, 11100, 13362, 13367, 172, 13369, 13383, 13384, 667, 6503, 6506, 666, 18464, 18465, 15166, 18686, 18689, 18688, 18691, 18692, 18687, 154, 22586, 22587, 22588, 22589, 22590, 22591, 22592, 22593, 22594};
		int[] EmoteX = { 10, 54, 98, 137, 9, 48, 95, 137, 7, 51, 95, 139, 6, 50, 90, 135, 8, 51, 99, 137, 10, 53, 88, 138, 3, 52, 96, 141, 5, 53, 88, 142, 10, 51, 139, 88, 12, 49, 97, 140, 10, 50, 90, 140, 10, 60, 90};
		int[] EmoteY = { 6, 6, 7, 6, 55, 55, 55, 55, 104, 104, 103, 104, 153, 153, 153, 153, 203, 202, 203, 202, 250, 250, 255, 249, 300, 299, 299, 299, 349, 350, 352, 350, 401, 402, 406, 402, 452, 450, 452, 452, 505, 505, 505, 505, 560, 560, 560};
		int[] EmoteIDs = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 33, 34, 35, 36, 28, 29, 30, 37, 31, 32, 38, 39, 40, 41, 42, 43, 44, 45, 46};
		String[] EmoteTooltip = { "Yes", "No", "Bow", "Angry", "Think", "Wave", "Shrug", "Cheer", "Beckon", "Laugh", "Jump for Joy", "Yawn", "Dance", "Jig", "Spin", "Headbang", "Cry", "Blow Kiss", "Panic", "Raspberry", "Clap", "Salute", "Goblin Bow", "Goblin Salute", "Glass Box", "Climb Rope", "Lean on Air", "Glass Wall", "Idea", "Stomp", "Flap", "Slap Head", "Zombie Walk", "Zombie Dance", "Scared", "Zombie Hand", "Bunny Hop", "Skill Cape", "Snowman Dance", "Air Guitar", "Safety First", "Explore", "Trick", "Freeze & Melt", "Give Thanks", "Around The World In Eggty Days", "Dramatic Point"};
		scroll.totalChildren(ButtonIDs.length);
		for(int index = 0; index < ButtonIDs.length; index++) {
			addButton(ButtonIDs[index], EmoteIDs[index], "Emotes/EMOTE", EmoteTooltip[index] );
		}
		for(int index = 0; index < ButtonIDs.length; index++) {
			scroll.child(index, ButtonIDs[index], EmoteX[index], EmoteY[index]);
		}
		scroll.width = 173;
		scroll.height = 260;
		scroll.scrollMax = 610;
	}


    public static void optionTab(TextDrawingArea tda[])
    {
        RSInterface rsinterface = addTabInterface(904);
        RSInterface rsinterface1 = interfaceCache[149];
        rsinterface1.textColor = 0xff9933;
        addSprite(905, 9, "/Options/SPRITE");
        addSprite(907, 18, "/Options/SPRITE");
        addSprite(909, 29, "/Options/SPRITE");
        addSprite(951, 32, "/Options/SPRITE");
        addSprite(953, 33, "/Options/SPRITE");
        addSprite(955, 34, "/Options/SPRITE");
        addSprite(947, 36, "/Options/SPRITE");
        addSprite(949, 35, "/Options/SPRITE");
        addConfigButton(152, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-run", 1, 5, 173);
        addConfigButton(906, 904, 10, 14, "/Options/SPRITE", 32, 16, "Dark", 1, 5, 166);
        addConfigButton(908, 904, 11, 15, "/Options/SPRITE", 32, 16, "Normal", 2, 5, 166);
        addConfigButton(910, 904, 12, 16, "/Options/SPRITE", 32, 16, "Bright", 3, 5, 166);
        addConfigButton(912, 904, 13, 17, "/Options/SPRITE", 32, 16, "Very Bright", 4, 5, 166);
        addConfigButton(930, 904, 19, 24, "/Options/SPRITE", 26, 16, "Music Off", 4, 5, 168);
        addConfigButton(931, 904, 20, 25, "/Options/SPRITE", 26, 16, "Music Level-1", 3, 5, 168);
        addConfigButton(932, 904, 21, 26, "/Options/SPRITE", 26, 16, "Music Level-2", 2, 5, 168);
        addConfigButton(933, 904, 22, 27, "/Options/SPRITE", 26, 16, "Music Level-3", 1, 5, 168);
        addConfigButton(934, 904, 23, 28, "/Options/SPRITE", 24, 16, "Music Level-4", 0, 5, 168);
        addConfigButton(941, 904, 19, 24, "/Options/SPRITE", 26, 16, "Regular Zoom", 4, 5, 169);
        addConfigButton(942, 904, 20, 25, "/Options/SPRITE", 26, 16, "Zoom +1", 3, 5, 169);
        addConfigButton(943, 904, 21, 26, "/Options/SPRITE", 26, 16, "Zoom +2", 2, 5, 169);
        addConfigButton(944, 904, 22, 27, "/Options/SPRITE", 26, 16, "Zoom +3", 1, 5, 169);
        addConfigButton(945, 904, 23, 28, "/Options/SPRITE", 24, 16, "Zoom +4", 0, 5, 169);
        addConfigButton(913, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-Mouse Buttons", 0, 5, 170);
        addConfigButton(915, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-Chat Effects", 0, 5, 171);
        addConfigButton(957, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-Split Private Chat", 1, 5, 287);
        addConfigButton(12464, 904, 30, 31, "/Options/SPRITE", 40, 40, "Toggle-Accept Aid", 0, 5, 427);
	addButton(18988, 0, "screent", "Toggle Screen Size");
        rsinterface.totalChildren(29);
        int i = 0;
        byte byte0 = 2;
        rsinterface.child(0, 905, 13 + i, 10 + byte0);
        rsinterface.child(1, 906, 48 + i, 18 + byte0);
        rsinterface.child(2, 908, 80 + i, 18 + byte0);
        rsinterface.child(3, 910, 112 + i, 18 + byte0);
        rsinterface.child(4, 912, 144 + i, 18 + byte0);
        rsinterface.child(5, 907, 14 + i, 55 + byte0);
        rsinterface.child(6, 930, 49 + i, 61 + byte0);
        rsinterface.child(7, 931, 75 + i, 61 + byte0);
        rsinterface.child(8, 932, 101 + i, 61 + byte0);
        rsinterface.child(9, 933, 127 + i, 61 + byte0);
        rsinterface.child(10, 934, 151 + i, 61 + byte0);
        rsinterface.child(11, 909, 13 + i, 99 + byte0);
        rsinterface.child(12, 941, 49 + i, 104 + byte0);
        rsinterface.child(13, 942, 75 + i, 104 + byte0);
        rsinterface.child(14, 943, 101 + i, 104 + byte0);
        rsinterface.child(15, 944, 127 + i, 104 + byte0);
        rsinterface.child(16, 945, 151 + i, 104 + byte0);
        rsinterface.child(17, 913, 15, 153);
        rsinterface.child(18, 955, 19, 159);
        rsinterface.child(19, 915, 75, 153);
        rsinterface.child(20, 953, 79, 160);
        rsinterface.child(21, 957, 135, 153);
        rsinterface.child(22, 951, 139, 159);
        rsinterface.child(23, 12464, 15, 208);
        rsinterface.child(24, 949, 20, 213);
        rsinterface.child(25, 152, 75, 208);
        rsinterface.child(26, 947, 87, 212);
        rsinterface.child(27, 149, 80, 231);
        rsinterface.child(28, 18988, 130, 208);
    }

	public static void clanChatTab(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(18128);
        addHoverButton(18129, "/Clan Chat/SPRITE", 6, 72, 32, "Join Chat", 550, 18130, 1);
        addHoveredButton(18130, "/Clan Chat/SPRITE", 7, 72, 32, 18131);
        addHoverButton(18132, "/Clan Chat/SPRITE", 6, 72, 32, "Leave Chat", -1, 18133, 5);
        addHoveredButton(18133, "/Clan Chat/SPRITE", 7, 72, 32, 18134);
		addButton(18250, 0, "/Clan Chat/Lootshare", "Toggle lootshare");
        addText(18135, "Join Chat", tda, 0, 0xff9b00, true, true);
        addText(18136, "Leave Chat", tda, 0, 0xff9b00, true, true);
        addSprite(18137, 37, "/Clan Chat/SPRITE");
        addText(18138, "Clan Chat", tda, 1, 0xff9b00, true, true);
        addText(18139, "Talking in: Not in chat", tda, 0, 0xff9b00, false, true);
        addText(18140, "Owner: None", tda, 0, 0xff9b00, false, true);
        tab.totalChildren(14);
        tab.child(0, 16126, 0, 221);
        tab.child(1, 16126, 0, 59);
        tab.child(2, 18137, 0, 62);
        tab.child(3, 18143, 0, 62);
        tab.child(4, 18129, 15, 226);
        tab.child(5, 18130, 15, 226);
        tab.child(6, 18132, 103, 226);
        tab.child(7, 18133, 103, 226);
        tab.child(8, 18135, 51, 237);
        tab.child(9, 18136, 139, 237);
        tab.child(10, 18138, 95, 1);
        tab.child(11, 18139, 10, 23);
        tab.child(12, 18140, 25, 38);
		tab.child(13, 18250, 145,15);
        /* Text area */
        RSInterface list = addTabInterface(18143);
        list.totalChildren(100);
        for(int i = 18144; i <= 18244; i++) {
            addText(i, "", tda, 0, 0xffffff, false, true);
        }
        for(int id = 18144, i = 0; id <= 18243 && i <= 99; id++, i++) {
            list.children[i] = id; list.childX[i] = 5;
            for(int id2 = 18144, i2 = 1; id2 <= 18243 && i2<= 99; id2++, i2++) {
                list.childY[0] = 2;
                list.childY[i2] = list.childY[i2 - 1] + 14;
            }
        }
        list.height = 158; list.width = 174;
        list.scrollMax = 1405;
    }
	
	public static void addText(int id, String text, TextDrawingArea wid[], int idx, int color) {
		RSInterface rsinterface = addTab(id);
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.type = 4;
		rsinterface.atActionType = 0;
		rsinterface.width = 174;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.aByte254 = 0;
		rsinterface.mOverInterToTrigger = -1;
		rsinterface.centerText = false;
		rsinterface.textShadow = true;
		rsinterface.textDrawingAreas = wid[idx];
		rsinterface.message = text;
		rsinterface.aString228 = "";
		rsinterface.textColor = color;
		rsinterface.anInt219 = 0;
		rsinterface.anInt216 = 0;
		rsinterface.anInt239 = 0;	
	}
	
	public static int boxIds[] = {
		4041, 4077, 4113, 4047, 4083, 4119, 4053, 4089, 4125, 4059, 4095,
		4131, 4065, 4101, 4137, 4071, 4107, 4143, 4154, 12168, 13918
	};
	
	public static RSInterface addTab(int i) {
		RSInterface rsinterface = interfaceCache[i] = new RSInterface();
		rsinterface.id = i;
		rsinterface.parentID = i;
		rsinterface.type = 0;
		rsinterface.atActionType = 0;
		rsinterface.contentType = 0;
		rsinterface.width = 512;
		rsinterface.height = 334;
		rsinterface.aByte254 = 0;
		rsinterface.mOverInterToTrigger = 0;
		return rsinterface;
	}
	
	public static void addButton(int i, int j, int hoverId, String name, int W, int H, String S, int AT) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.type = 5;
		RSInterface.atActionType = AT;
		RSInterface.aByte254 = 0;
		RSInterface.mOverInterToTrigger = hoverId;
		RSInterface.sprite1 = imageLoader(j,name);
		RSInterface.sprite2 = imageLoader(j,name);
		RSInterface.width = W;
		RSInterface.height = H;
		RSInterface.tooltip = S;
	}

    public static void addText(int i, String s, int j, boolean flag, boolean flag1, int k, TextDrawingArea atextdrawingarea[], int l)
    {
        RSInterface rsinterface = addInterface(i);
        rsinterface.parentID = i;
        rsinterface.id = i;
        rsinterface.type = 4;
        rsinterface.atActionType = 0;
        rsinterface.width = 0;
        rsinterface.height = 0;
        rsinterface.contentType = 0;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = k;
        rsinterface.centerText = flag;
        rsinterface.textShadow = flag1;
        rsinterface.textDrawingAreas = atextdrawingarea[l];
        rsinterface.message = s;
        rsinterface.aString228 = "";
        rsinterface.textColor = j;
    }

    public static void Pestpanel(TextDrawingArea atextdrawingarea[])
    {
        RSInterface rsinterface = addInterface(21005);
        addText(21006, "Next Departure:", 0xCCCBCB, false, true, 52, atextdrawingarea, 1);
        addText(21007, "Players Ready:", 0x5BD230, false, true, 52, atextdrawingarea, 1);
        addText(21008, "(Need 5 to 25 players)", 0xDED36A, false, true, 52, atextdrawingarea, 1);
        addText(21009, "Pest Points:", 0x99FFFF, false, true, 52, atextdrawingarea, 1);
        byte byte0 = 4;
        rsinterface.children = new int[byte0];
        rsinterface.childX = new int[byte0];
        rsinterface.childY = new int[byte0];
        setBounds(21006, 15, 12, 0, rsinterface);
        setBounds(21007, 15, 30, 1, rsinterface);
        setBounds(21008, 15, 48, 2, rsinterface);
        setBounds(21009, 15, 66, 3, rsinterface);
    }

    public static void Pestpanel2(TextDrawingArea atextdrawingarea[])
    {
        RSInterface rsinterface = addInterface(21100);
        addSprite(21101, 0, "Pest Control/PEST1");
        addSprite(21102, 1, "Pest Control/PEST1");
        addSprite(21103, 2, "Pest Control/PEST1");
        addSprite(21104, 3, "Pest Control/PEST1");
        addSprite(21105, 4, "Pest Control/PEST1");
        addSprite(21106, 5, "Pest Control/PEST1");
        addText(21107, "W", 0xcc00cc, false, true, 52, atextdrawingarea, 1);
        addText(21108, "E", 0x0000FF, false, true, 52, atextdrawingarea, 1);
        addText(21109, "SE", 0xffff44, false, true, 52, atextdrawingarea, 1);
        addText(21110, "SW", 0xcc0000, false, true, 52, atextdrawingarea, 1);
        addText(21111, "250", 0x99ff33, false, true, 52, atextdrawingarea, 1);
        addText(21112, "250", 0x99ff33, false, true, 52, atextdrawingarea, 1);
        addText(21113, "250", 0x99ff33, false, true, 52, atextdrawingarea, 1);
        addText(21114, "250", 0x99ff33, false, true, 52, atextdrawingarea, 1);
        addText(21115, "***", 0x99ff33, false, true, 52, atextdrawingarea, 1);
        addText(21116, "***", 0x99ff33, false, true, 52, atextdrawingarea, 1);
        addText(21117, "Time Remaining:", 0xffffff, false, true, 52, atextdrawingarea, 0);
        addText(21118, "10 Minutes", 0xffffff, false, true, 52, atextdrawingarea, 0);
        byte byte0 = 18;
        rsinterface.children = new int[byte0];
        rsinterface.childX = new int[byte0];
        rsinterface.childY = new int[byte0];
        setBounds(21101, 361, 26, 0, rsinterface);
        setBounds(21102, 396, 26, 1, rsinterface);
        setBounds(21103, 436, 26, 2, rsinterface);
        setBounds(21104, 474, 26, 3, rsinterface);
        setBounds(21105, 3, 21, 4, rsinterface);
        setBounds(21106, 3, 50, 5, rsinterface);
        setBounds(21107, 371, 60, 6, rsinterface);
        setBounds(21108, 409, 60, 7, rsinterface);
        setBounds(21109, 443, 60, 8, rsinterface);
        setBounds(21110, 479, 60, 9, rsinterface);
        setBounds(21111, 362, 10, 10, rsinterface);
        setBounds(21112, 398, 10, 11, rsinterface);
        setBounds(21113, 436, 10, 12, rsinterface);
        setBounds(21114, 475, 10, 13, rsinterface);
        setBounds(21115, 32, 32, 14, rsinterface);
        setBounds(21116, 32, 62, 15, rsinterface);
        setBounds(21117, 8, 88, 16, rsinterface);
        setBounds(21118, 87, 88, 17, rsinterface);
    }

    public static void addHoverBox(int i, String s)
    {
        RSInterface rsinterface = interfaceCache[i];
        rsinterface.id = i;
        rsinterface.parentID = i;
        rsinterface.isMouseoverTriggered = true;
        rsinterface.type = 8;
        rsinterface.hoverText = s;
    }

    public static void addText(int i, String s, TextDrawingArea atextdrawingarea[], int j, int k, boolean flag, boolean flag1)
    {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.parentID = i;
        rsinterface.id = i;
        rsinterface.type = 4;
        rsinterface.atActionType = 0;
        rsinterface.width = 0;
        rsinterface.height = 11;
        rsinterface.contentType = 0;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = -1;
        rsinterface.centerText = flag;
        rsinterface.textShadow = flag1;
        rsinterface.textDrawingAreas = atextdrawingarea[j];
        rsinterface.message = s;
        rsinterface.aString228 = "";
        rsinterface.textColor = k;
        rsinterface.anInt219 = 0;
        rsinterface.anInt216 = 0;
        rsinterface.anInt239 = 0;
    }

    public static void addButton(int i, int j, String s, String s1, int k, int l)
    {
        RSInterface rsinterface = interfaceCache[i] = new RSInterface();
        rsinterface.id = i;
        rsinterface.parentID = i;
        rsinterface.type = 5;
        rsinterface.atActionType = 1;
        rsinterface.contentType = 0;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = 52;
        rsinterface.sprite1 = imageLoader(j, s);
        rsinterface.sprite2 = imageLoader(j, s);
        rsinterface.width = k;
        rsinterface.height = l;
        rsinterface.tooltip = s1;
    }

    public static void addConfigButton(int i, int j, int k, int l, String s, int i1, int j1, String s1, 
            int k1, int l1, int i2)
    {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.parentID = j;
        rsinterface.id = i;
        rsinterface.type = 5;
        rsinterface.atActionType = l1;
        rsinterface.contentType = 0;
        rsinterface.width = i1;
        rsinterface.height = j1;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = -1;
        rsinterface.anIntArray245 = new int[1];
        rsinterface.anIntArray212 = new int[1];
        rsinterface.anIntArray245[0] = 1;
        rsinterface.anIntArray212[0] = k1;
        rsinterface.valueIndexArray = new int[1][3];
        rsinterface.valueIndexArray[0][0] = 5;
        rsinterface.valueIndexArray[0][1] = i2;
        rsinterface.valueIndexArray[0][2] = 0;
        rsinterface.sprite1 = imageLoader(k, s);
        rsinterface.sprite2 = imageLoader(l, s);
        rsinterface.tooltip = s1;
    }

    public static void addSprite(int i, int j, String s)
    {
        RSInterface rsinterface = interfaceCache[i] = new RSInterface();
        rsinterface.id = i;
        rsinterface.parentID = i;
        rsinterface.type = 5;
        rsinterface.atActionType = 0;
        rsinterface.contentType = 0;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = 52;
        rsinterface.sprite1 = imageLoader(j, s);
        rsinterface.sprite2 = imageLoader(j, s);
        rsinterface.width = 512;
        rsinterface.height = 334;
    }

    public static void addHoverButton(int i, String s, int j, int k, int l, String s1, int i1, int j1, 
            int k1)
    {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.id = i;
        rsinterface.parentID = i;
        rsinterface.type = 5;
        rsinterface.atActionType = k1;
        rsinterface.contentType = i1;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = j1;
        rsinterface.sprite1 = imageLoader(j, s);
        rsinterface.sprite2 = imageLoader(j, s);
        rsinterface.width = k;
        rsinterface.height = l;
        rsinterface.tooltip = s1;
    }

    public static void addHoveredButton(int i, String s, int j, int k, int l, int i1)
    {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.parentID = i;
        rsinterface.id = i;
        rsinterface.type = 0;
        rsinterface.atActionType = 0;
        rsinterface.width = k;
        rsinterface.height = l;
        rsinterface.isMouseoverTriggered = true;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = -1;
        rsinterface.scrollMax = 0;
        addHoverImage(i1, j, j, s);
        rsinterface.totalChildren(1);
        rsinterface.child(0, i1, 0, 0);
    }

    public static void addHoverImage(int i, int j, int k, String s)
    {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.id = i;
        rsinterface.parentID = i;
        rsinterface.type = 5;
        rsinterface.atActionType = 0;
        rsinterface.contentType = 0;
        rsinterface.width = 512;
        rsinterface.height = 334;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = 52;
        rsinterface.sprite1 = imageLoader(j, s);
        rsinterface.sprite2 = imageLoader(k, s);
    }

    public static void addTransparentSprite(int i, int j, String s)
    {
        RSInterface rsinterface = interfaceCache[i] = new RSInterface();
        rsinterface.id = i;
        rsinterface.parentID = i;
        rsinterface.type = 5;
        rsinterface.atActionType = 0;
        rsinterface.contentType = 0;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = 52;
        rsinterface.sprite1 = imageLoader(j, s);
        rsinterface.sprite2 = imageLoader(j, s);
        rsinterface.width = 512;
        rsinterface.height = 334;
        rsinterface.drawsTransparent = true;
    }

    public static RSInterface addScreenInterface(int i)
    {
        RSInterface rsinterface = interfaceCache[i] = new RSInterface();
        rsinterface.id = i;
        rsinterface.parentID = i;
        rsinterface.type = 0;
        rsinterface.atActionType = 0;
        rsinterface.contentType = 0;
        rsinterface.width = 512;
        rsinterface.height = 334;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = 0;
        return rsinterface;
    }

    public static RSInterface addTabInterface(int i)
    {
        RSInterface rsinterface = interfaceCache[i] = new RSInterface();
        rsinterface.id = i;
        rsinterface.parentID = i;
        rsinterface.type = 0;
        rsinterface.atActionType = 0;
        rsinterface.contentType = 0;
        rsinterface.width = 512;
        rsinterface.height = 700;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = -1;
        return rsinterface;
    }

    private static Sprite imageLoader(int i, String s)
    {
        long l = (TextClass.method585(s) << 8) + (long)i;
        Sprite sprite = (Sprite)aMRUNodes_238.insertFromCache(l);
        if(sprite != null)
        {
            return sprite;
        }
        try
        {
            sprite = new Sprite((new StringBuilder()).append(s).append(" ").append(i).toString());
            aMRUNodes_238.removeFromCache(sprite, l);
        }
        catch(Exception exception)
        {
            return null;
        }
        return sprite;
    }

    public void child(int i, int j, int k, int l)
    {
        children[i] = j;
        childX[i] = k;
        childY[i] = l;
    }

    public void totalChildren(int i)
    {
        children = new int[i];
        childX = new int[i];
        childY = new int[i];
    }

    private Model method206(int i, int j)
    {
        Model model = (Model)aMRUNodes_264.insertFromCache((i << 16) + j);
        if(model != null)
        {
            return model;
        }
        if(i == 1)
        {
            model = Model.method462(j);
        }
        if(i == 2)
        {
            model = EntityDef.forID(j).method160();
        }
        if(i == 3)
        {
            model = client.myPlayer.method453();
        }
        if(i == 4)
        {
            model = ItemDef.forID(j).method202(50);
        }
        if(i == 5)
        {
            model = null;
        }
        if(model != null)
        {
            aMRUNodes_264.removeFromCache(model, (i << 16) + j);
        }
        return model;
    }

    private static Sprite method207(int i, StreamLoader streamloader, String s)
    {
        long l = (TextClass.method585(s) << 8) + (long)i;
        Sprite sprite = (Sprite)aMRUNodes_238.insertFromCache(l);
        if(sprite != null)
        {
            return sprite;
        }
        try
        {
            sprite = new Sprite(streamloader, s, i);
            aMRUNodes_238.removeFromCache(sprite, l);
        }
        catch(Exception exception)
        {
            return null;
        }
        return sprite;
    }

    public static void method208(boolean flag, Model model)
    {
        int i = 0;
        byte byte0 = 5;
        if(flag)
        {
            return;
        }
        aMRUNodes_264.unlinkAll();
        if(model != null && byte0 != 4)
        {
            aMRUNodes_264.removeFromCache(model, (byte0 << 16) + i);
        }
    }

    public Model method209(int i, int j, boolean flag)
    {
        Model model;
        if(flag)
        {
            model = method206(anInt255, anInt256);
        } else
        {
            model = method206(anInt233, mediaID);
        }
        if(model == null)
        {
            return null;
        }
        if(j == -1 && i == -1 && model.anIntArray1640 == null)
        {
            return model;
        }
        Model model1 = new Model(true, Class36.method532(j) & Class36.method532(i), false, model);
        if(j != -1 || i != -1)
        {
            model1.method469();
        }
        if(j != -1)
        {
            model1.method470(j);
        }
        if(i != -1)
        {
            model1.method470(i);
        }
        model1.method479(64, 768, -50, -10, -50, true);
        return model1;
    }

    public RSInterface()
    {
    }

private static void addHButton(int i1, String Name, int Iid, int w, int h, String Discreption, int i2, int BType, int Iid2) {
addHoverButton(i1, Name, Iid, w, h, Discreption, 0, i2, BType);
addHoveredButton(i2, Name, Iid2, w, h, i2 + 1);
}
	public static void addButton(int id, int sId, String spriteName, String tooltip, int aT) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = aT;
		tab.contentType = 0;
		tab.aByte254 = (byte)0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(sId, spriteName);
		tab.sprite2 = imageLoader(sId, spriteName);
		tab.width = tab.sprite1.myWidth;
		tab.height = tab.sprite1.myHeight;
		tab.tooltip = tooltip;
	}

public static void addChar(int ID) { 
	RSInterface t = interfaceCache[ID] = new RSInterface(); 
	t.id = ID; 
	t.parentID = ID; 
	t.type = 6;
	t.atActionType = 0; 
	t.contentType = 328; 
	t.width = 136; 
	t.height = 168; 
	t.aByte254 = 0;
	t.mOverInterToTrigger = 0;
	t.modelZoom = 560;
	t.modelRotation1 = 150;
	t.modelRotation2 = 0; 
	t.anInt257 = -1; 
	t.anInt258 = -1; 
}



	
	public static void equipmentScreen(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(19148);
		addSprite(19149, 0, "Equipment/CHAR");
		addHover(19150, 3, 0, 19151, 3, "Equipment/CHAR", 21, 21, "Close");
		addHovered(19151, 2, "Equipment/CHAR", 21, 21, 19152);
		addText(19154, "Equip Your Character...", 0xFF981F, false, true, 52, TDA, 2);
		addText(1673, "Attack bonus", 0xFF981F, false, true, 52, TDA, 2);
		addText(1674, "Defense bonus", 0xFF981F, false, true, 52, TDA, 2);
		addText(1685, "Other bonuses", 0xFF981F, false, true, 52, TDA, 2);	
		addText(1675, "Stab:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1676, "Slash:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1677, "Crush:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1678, "Magic:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1679, "Ranged:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1680, "Stab:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1681, "Slash:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1682, "Crush:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1683, "Magic:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1684, "Ranged:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1686, "Strength:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1687, "Prayer:", 0xFF981F, false, true, 52, TDA, 1);
		addText(19155, "0%", 0xFF981F, false, true, 52, TDA, 1);
		addChar(19153);
		int last = 45;
		int frame = 0;
		setChildren(last, Interface);
		setBounds(19149, 12, 20, frame, Interface);frame++;
		setBounds(19150, 472, 27, frame, Interface);frame++;
		setBounds(19151, 472, 27, frame, Interface);frame++;
		setBounds(19153, 193, 190, frame, Interface);frame++;
		setBounds(19154, 23, 29, frame, Interface);frame++;
		setBounds(1673, 365, 71, frame, Interface); frame++;
		setBounds(1674, 365, 163, frame, Interface); frame++;
		setBounds(1675, 372, 85, frame, Interface); frame++;
		setBounds(1676, 372, 99, frame, Interface); frame++;
		setBounds(1677, 372, 113, frame, Interface); frame++;
		setBounds(1678, 372, 127, frame, Interface); frame++;
		setBounds(1679, 372, 141, frame, Interface); frame++;
		setBounds(1680, 372, 177, frame, Interface); frame++;
		setBounds(1681, 372, 191, frame, Interface); frame++;
		setBounds(1682, 372, 205, frame, Interface); frame++;
		setBounds(1683, 372, 219, frame, Interface); frame++;
		setBounds(1684, 372, 233, frame, Interface); frame++;
		setBounds(1685, 365, 253, frame, Interface); frame++;
		setBounds(1686, 372, 267, frame, Interface); frame++;
		setBounds(1687, 372, 281, frame, Interface); frame++;
		setBounds(19155, 94, 286, frame, Interface); frame++;
		setBounds(1645, 83, 106, frame, Interface); frame++;
		setBounds(1646, 83, 135, frame, Interface); frame++;
		setBounds(1647, 83, 172, frame, Interface); frame++;
		setBounds(1648, 83, 213, frame, Interface); frame++;
		setBounds(1649, 27, 185, frame, Interface); frame++;
		setBounds(1650, 27, 221, frame, Interface); frame++;
		setBounds(1651, 139, 185, frame, Interface); frame++;
		setBounds(1652, 139, 221, frame, Interface); frame++;
		setBounds(1653, 53, 148, frame, Interface); frame++;
		setBounds(1654, 112, 148, frame, Interface); frame++;
		setBounds(1655, 63, 109, frame, Interface); frame++;
		setBounds(1656, 117, 108, frame, Interface); frame++;
		setBounds(1657, 83, 71, frame, Interface); frame++;
		setBounds(1658, 42, 110, frame, Interface); frame++;
		setBounds(1659, 83, 110, frame, Interface); frame++;
		setBounds(1660, 124, 110, frame, Interface); frame++;
		setBounds(1661, 27, 149, frame, Interface); frame++;
		setBounds(1662, 83, 149, frame, Interface); frame++;
		setBounds(1663, 139, 149, frame, Interface); frame++;
		setBounds(1664, 83, 189, frame, Interface); frame++;
		setBounds(1665, 83, 229, frame, Interface); frame++;
		setBounds(1666, 27, 229, frame, Interface); frame++;
		setBounds(1667, 139, 229, frame, Interface); frame++;
		setBounds(1688, 29, 111, frame, Interface); frame++;
	}

	public static void EquipmentTab(TextDrawingArea[] wid) {
		RSInterface Interface = interfaceCache[1644];
		addSprite(15101, 0, "Equipment/bl");//cheap hax
		addSprite(15102, 1, "Equipment/bl");//cheap hax
		addSprite(15109, 2, "Equipment/bl");//cheap hax
		addSprite(15110, 3, "Equipment/bl");//cheap hax
		removeSomething(15103);	removeSomething(15104);
		Interface.children[23] = 15101;	Interface.childX[23] = 40;			Interface.childY[23] = 205;
		Interface.children[24] = 15102;	Interface.childX[24] = 110;			Interface.childY[24] = 205;
		Interface.children[25] = 15109;	Interface.childX[25] = 39;			Interface.childY[25] = 240;
		Interface.children[26] = 27650; Interface.childX[26] = 0;			Interface.childY[26] = 0;
		Interface = addInterface(27650);
			addButton(27651, 6, "Equipment/BOX", "Xp-Lock", 27659, 1, 26, 33);
			addTooltip(27659, "Locks/unlocks your EXP");
			addButton(27653, 1, "Equipment/BOX", "Show Equipment Stats", 27655, 1, 40, 39);
			addButton(27654, 2, "Equipment/BOX", "Items Kept On Death", 27657, 1, 40, 39);
			addButton(27655, 8, "Interfaces/Equipment/BOX", "More options", 27659, 1, 40, 39);
			setChildren(4, Interface);
			setBounds(27651, 84, 215, 0, Interface);
			setBounds(27653, 29, 205, 1, Interface);
			setBounds(27654, 124, 205, 2, Interface);
			setBounds(27655, 127, 4, 3, Interface);
			//setBounds(27655, 590, 224, 3, Interface); 
			//setBounds(27659, 39, 240, 3, Interface);
			//setBounds(27655, 39, 240, 4, Interface);
			//setBounds(27657, 39, 240, 5, Interface);
	}
	
	
	
	public static void addButton(int id, int sid, String spriteName, String tooltip, int mOver, int atAction, int width, int height) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = atAction;
		tab.contentType = 0;
		tab.aByte254 = 0;
		tab.mOverInterToTrigger = mOver;
		tab.sprite1 = imageLoader(sid, spriteName);
		tab.sprite2 = imageLoader(sid, spriteName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = tooltip;
		tab.inventoryhover = true;
	}
	public boolean inventoryhover;

	public static void addActionButton(int id, int sprite, int sprite2, int width, int height, String s, String folder) {
		RSInterface rsi = interfaceCache[id] = new RSInterface();
		if (folder.equalsIgnoreCase("equipment")) {
			rsi.sprite1 = imageLoader("equipment", sprite, "CUSTOM");
		} else if (folder.equalsIgnoreCase("attack")) {
			rsi.sprite1 = imageLoader("attack", sprite, "");
		} else {
			rsi.sprite1 = imageLoader("misc", sprite, "");
		}
		if (sprite2 == sprite) {
			rsi.sprite2 = imageLoader("attack", sprite, "a");
		} else {
			rsi.sprite2 = imageLoader("attack", sprite2, "");
		}
		rsi.tooltip = s;
		rsi.contentType = 0;
		rsi.atActionType = 1;
		rsi.width = width;
		rsi.mOverInterToTrigger = 52;
		rsi.parentID = id;
		rsi.id = id;
		rsi.type = 5;
		rsi.height = height;
	}

	private static Sprite imageLoader(String folder, int i, String s) {
		long l = (TextClass.method585(s) << 8) + (long) i;
		Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
		if (sprite != null)
			return sprite;
		try {
			if (folder.equals("attack")) {
				sprite = new Sprite("" + folder + "/" + i + "" + s + "");
			} else {
				sprite = new Sprite("" + folder + "/" + s + " " + i + "");
			}
			aMRUNodes_238.removeFromCache(sprite, l);
		} catch (Exception exception) {
			return null;
		}
		return sprite;
	}

	public static void addClick(int id, int spriteOnId, int spriteOffId, String castText, int h, int w) {
		RSInterface rsi = interfaceCache[id] = new RSInterface();
		rsi.id = id;
		rsi.parentID = id;
		rsi.type = 5;
		rsi.atActionType = 1;
		rsi.contentType = 0;
		rsi.width = h;
		rsi.height = w;
		rsi.aByte254 = (byte)0;
		rsi.mOverInterToTrigger = 52;
		rsi.selectedActionName = "Cast On";
		rsi.spellName = castText;
		rsi.sprite1 = imageLoader("equipment", spriteOnId, "interface");
		rsi.sprite2 = imageLoader("equipment", spriteOffId, "interface");
		rsi.tooltip = castText;
	}


public static TextDrawingArea[] fonts;

public static void addText(int i, String s, int k, boolean l, boolean m, int a, int j) {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.parentID = i;
        rsinterface.id = i;
        rsinterface.type = 4;
        rsinterface.atActionType = 0;
        rsinterface.width = 0;
        rsinterface.height = 0;
        rsinterface.contentType = 0;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = a;
        rsinterface.centerText = l;
        rsinterface.textShadow = m;
        rsinterface.textDrawingAreas = RSInterface.fonts[j];
        rsinterface.message = s;
        rsinterface.textColor = k;
    }
	private static Sprite loadSprite(int i, String s) {

		Sprite sprite;
		try {
			sprite = new Sprite(s + " " + i);
			if (sprite != null) {
				return sprite;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
		return sprite;
	}

	public Sprite loadSprite(String s, int i) {
		Sprite sprite;
		try {
			sprite = new Sprite(s + " " + i);
			if (sprite != null) {
				return sprite;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
		return null;
	}

	public static void addLunarSprite(int i, int j, String name) {
		RSInterface RSInterface = addTabInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.type = 5;
		RSInterface.atActionType = 0;
		RSInterface.contentType = 0;
		RSInterface.aByte254 = 0;
		RSInterface.mOverInterToTrigger = 52;
		RSInterface.sprite1 = imageLoader(j, name);
		RSInterface.width = 500;
		RSInterface.height = 500;
		RSInterface.tooltip = "";
	}

	public static void drawRune(int i, int id, String runeName) {
		RSInterface RSInterface = addTabInterface(i);
		RSInterface.type = 5;
		RSInterface.atActionType = 0;
		RSInterface.contentType = 0;
		RSInterface.aByte254 = 0;
		RSInterface.mOverInterToTrigger = 52;
		RSInterface.sprite1 = loadSprite(id, "Lunar/RUNE");
		RSInterface.width = 500;
		RSInterface.height = 500;
	}

	public static void addRuneText(int ID, int runeAmount, int RuneID, TextDrawingArea[] font) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 4;
		rsInterface.atActionType = 0;
		rsInterface.contentType = 0;
		rsInterface.width = 0;
		rsInterface.height = 14;
		rsInterface.aByte254 = 0;
		rsInterface.mOverInterToTrigger = -1;
		rsInterface.anIntArray245 = new int[1];
		rsInterface.anIntArray212 = new int[1];
		rsInterface.anIntArray245[0] = 3;
		rsInterface.anIntArray212[0] = runeAmount;
		rsInterface.valueIndexArray = new int[1][4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = RuneID;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.centerText = true;
		rsInterface.textDrawingAreas = font[0];
		rsInterface.textShadow = true;
		rsInterface.message = "%1/" + runeAmount + "";
		rsInterface.aString228 = "";
		rsInterface.textColor = 12582912;
		rsInterface.anInt219 = 49152;
	}

	public static void homeTeleport() {
		RSInterface RSInterface = addTabInterface(30000);
		RSInterface.tooltip = "Cast @gre@Lunar Home Teleport";
		RSInterface.id = 30000;
		RSInterface.parentID = 30000;
		RSInterface.type = 5;
		RSInterface.atActionType = 5;
		RSInterface.contentType = 0;
		RSInterface.aByte254 = 0;
		RSInterface.mOverInterToTrigger = 30001;
		RSInterface.sprite1 = loadSprite(1, "Lunar/SPRITE");
		RSInterface.width = 20;
		RSInterface.height = 20;
		RSInterface hover = addTabInterface(30001);
		hover.mOverInterToTrigger = -1;
		hover.isMouseoverTriggered = true;
		setChildren(1, hover);
		addLunarSprite(30002, 0, "Lunar/SPRITE");
		setBounds(30002, 0, 0, 0, hover);
	}

	public static void addLunar2RunesSmallBox(int ID, int r1, int r2, int ra1, int ra2, int rune1, int lvl, String name, String descr, TextDrawingArea[] TDA, int sid, int suo, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.mOverInterToTrigger = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast On";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.anIntArray245 = new int[3];
		rsInterface.anIntArray212 = new int[3];
		rsInterface.anIntArray245[0] = 3;
		rsInterface.anIntArray212[0] = ra1;
		rsInterface.anIntArray245[1] = 3;
		rsInterface.anIntArray212[1] = ra2;
		rsInterface.anIntArray245[2] = 3;
		rsInterface.anIntArray212[2] = lvl;
		rsInterface.valueIndexArray = new int[3][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[3];
		rsInterface.valueIndexArray[2][0] = 1;
		rsInterface.valueIndexArray[2][1] = 6;
		rsInterface.valueIndexArray[2][2] = 0;
		rsInterface.sprite2 = loadSprite(sid, "Lunar/LUNARON");
		rsInterface.sprite1 = loadSprite(sid, "Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.mOverInterToTrigger = -1;
		hover.isMouseoverTriggered = true;
		setChildren(7, hover);
		addLunarSprite(ID + 2, 0, "Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true, true, 52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xAF6A1A, true, true, 52, 0);
		setBounds(ID + 4, 90, 19, 2, hover);
		setBounds(30016, 37, 35, 3, hover);// Rune
		setBounds(rune1, 112, 35, 4, hover);// Rune
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 50, 66, 5, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 123, 66, 6, hover);

	}

	public static void addLunar3RunesSmallBox(int ID, int r1, int r2, int r3,
			int ra1, int ra2, int ra3, int rune1, int rune2, int lvl,
			String name, String descr, TextDrawingArea[] TDA, int sid, int suo, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.mOverInterToTrigger = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.anIntArray245 = new int[4];
		rsInterface.anIntArray212 = new int[4];
		rsInterface.anIntArray245[0] = 3;
		rsInterface.anIntArray212[0] = ra1;
		rsInterface.anIntArray245[1] = 3;
		rsInterface.anIntArray212[1] = ra2;
		rsInterface.anIntArray245[2] = 3;
		rsInterface.anIntArray212[2] = ra3;
		rsInterface.anIntArray245[3] = 3;
		rsInterface.anIntArray212[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.sprite2 = loadSprite(sid, "Lunar/LUNARON");
		rsInterface.sprite1 = loadSprite(sid, "Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.mOverInterToTrigger = -1;
		hover.isMouseoverTriggered = true;
		setChildren(9, hover);
		addLunarSprite(ID + 2, 0, "Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true,
				true, 52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xAF6A1A, true, true, 52, 0);
		setBounds(ID + 4, 90, 19, 2, hover);
		setBounds(30016, 14, 35, 3, hover);
		setBounds(rune1, 74, 35, 4, hover);
		setBounds(rune2, 130, 35, 5, hover);
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 26, 66, 6, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 87, 66, 7, hover);
		addRuneText(ID + 7, ra3 + 1, r3, TDA);
		setBounds(ID + 7, 142, 66, 8, hover);
	}

	public static void addLunar3RunesBigBox(int ID, int r1, int r2, int r3,
			int ra1, int ra2, int ra3, int rune1, int rune2, int lvl,
			String name, String descr, TextDrawingArea[] TDA, int sid, int suo, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.mOverInterToTrigger = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.anIntArray245 = new int[4];
		rsInterface.anIntArray212 = new int[4];
		rsInterface.anIntArray245[0] = 3;
		rsInterface.anIntArray212[0] = ra1;
		rsInterface.anIntArray245[1] = 3;
		rsInterface.anIntArray212[1] = ra2;
		rsInterface.anIntArray245[2] = 3;
		rsInterface.anIntArray212[2] = ra3;
		rsInterface.anIntArray245[3] = 3;
		rsInterface.anIntArray212[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.sprite2 = loadSprite(sid, "Lunar/LUNARON");
		rsInterface.sprite1 = loadSprite(sid, "Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.mOverInterToTrigger = -1;
		hover.isMouseoverTriggered = true;
		setChildren(9, hover);
		addLunarSprite(ID + 2, 1, "Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true,
				true, 52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xAF6A1A, true, true, 52, 0);
		setBounds(ID + 4, 90, 21, 2, hover);
		setBounds(30016, 14, 48, 3, hover);
		setBounds(rune1, 74, 48, 4, hover);
		setBounds(rune2, 130, 48, 5, hover);
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 26, 79, 6, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 87, 79, 7, hover);
		addRuneText(ID + 7, ra3 + 1, r3, TDA);
		setBounds(ID + 7, 142, 79, 8, hover);
	}

	public static void addLunar3RunesLargeBox(int ID, int r1, int r2, int r3,
			int ra1, int ra2, int ra3, int rune1, int rune2, int lvl,
			String name, String descr, TextDrawingArea[] TDA, int sid, int suo, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.mOverInterToTrigger = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.anIntArray245 = new int[4];
		rsInterface.anIntArray212 = new int[4];
		rsInterface.anIntArray245[0] = 3;
		rsInterface.anIntArray212[0] = ra1;
		rsInterface.anIntArray245[1] = 3;
		rsInterface.anIntArray212[1] = ra2;
		rsInterface.anIntArray245[2] = 3;
		rsInterface.anIntArray212[2] = ra3;
		rsInterface.anIntArray245[3] = 3;
		rsInterface.anIntArray212[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.sprite2 = loadSprite(sid, "Lunar/LUNARON");
		rsInterface.sprite1 = loadSprite(sid, "Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.isMouseoverTriggered = true;
		hover.mOverInterToTrigger = -1;
		setChildren(9, hover);
		addLunarSprite(ID + 2, 2, "Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true,
				true, 52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xAF6A1A, true, true, 52, 0);
		setBounds(ID + 4, 90, 34, 2, hover);
		setBounds(30016, 14, 61, 3, hover);
		setBounds(rune1, 74, 61, 4, hover);
		setBounds(rune2, 130, 61, 5, hover);
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 26, 92, 6, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 87, 92, 7, hover);
		addRuneText(ID + 7, ra3 + 1, r3, TDA);
		setBounds(ID + 7, 142, 92, 8, hover);
	}

	public static void configureLunar(TextDrawingArea[] TDA) {
		homeTeleport();
		drawRune(30003, 1, "Fire");
		drawRune(30004, 2, "Water");
		drawRune(30005, 3, "Air");
		drawRune(30006, 4, "Earth");
		drawRune(30007, 5, "Mind");
		drawRune(30008, 6, "Body");
		drawRune(30009, 7, "Death");
		drawRune(30010, 8, "Nature");
		drawRune(30011, 9, "Chaos");
		drawRune(30012, 10, "Law");
		drawRune(30013, 11, "Cosmic");
		drawRune(30014, 12, "Blood");
		drawRune(30015, 13, "Soul");
		drawRune(30016, 14, "Astral");
		addLunar3RunesSmallBox(30017, 9075, 554, 555, 0, 4, 3, 30003, 30004, 64, "Bake Pie", "Bake pies without a stove", TDA, 0, 16, 2);
		addLunar2RunesSmallBox(30025, 9075, 557, 0, 7, 30006, 65, "Cure Plant", "Cure disease on farming patch", TDA, 1, 4, 2);
		addLunar3RunesBigBox(30032, 9075, 564, 558, 0, 0, 0, 30013, 30007, 65, "Monster Examine", "Detect the combat statistics of a\\nmonster", TDA, 2, 2, 2);
		addLunar3RunesSmallBox(30040, 9075, 564, 556, 0, 0, 1, 30013, 30005, 66, "NPC Contact", "Speak with varied NPCs", TDA, 3, 0, 5);
		addLunar3RunesSmallBox(30048, 9075, 563, 557, 0, 0, 9, 30012, 30006, 67, "Cure Other", "Cure poisoned players", TDA, 4, 8, 2);
		addLunar3RunesSmallBox(30056, 9075, 555, 554, 0, 2, 0, 30004, 30003, 67, "Humidify", "Fills certain vessels with water", TDA, 5, 0, 5);
		addLunar3RunesSmallBox(30064, 9075, 563, 557, 1, 0, 1, 30012, 30006, 68, "Monster Teleport", "Teleports you to Training Spots", TDA, 6, 0, 5);
		addLunar3RunesBigBox(30075, 9075, 563, 557, 1, 0, 3, 30012, 30006, 69, "Minigame Teleports", "Teleports you to MiniGames", TDA, 7, 0, 5);
		addLunar3RunesSmallBox(30083, 9075, 563, 557, 1, 0, 5, 30012, 30006, 70, "Boss Teleports", "Teleports you to Bosses", TDA, 8, 0, 5);
		addLunar3RunesSmallBox(30091, 9075, 564, 563, 1, 1, 0, 30013, 30012, 70, "Cure Me", "Cures Poison", TDA, 9, 0, 5);
		addLunar2RunesSmallBox(30099, 9075, 557, 1, 1, 30006, 70, "Hunter Kit", "Get a kit of hunting gear", TDA, 10, 0, 5);
		addLunar3RunesSmallBox(30106, 9075, 563, 555, 1, 0, 0, 30012, 30004, 71, "PK Teleports", "Teleports you to Pking spots", TDA, 11, 0, 5);
		addLunar3RunesBigBox(30114, 9075, 563, 555, 1, 0, 4, 30012, 30004, 72, "Skilling Teleport", "Teleports you to Skilling Spots", TDA, 12, 0, 5);
		addLunar3RunesSmallBox(30122, 9075, 564, 563, 1, 1, 1, 30013, 30012, 73, "Cure Group", "Cures Poison on players", TDA, 13, 0, 5);
		addLunar3RunesBigBox(30130, 9075, 564, 559, 1, 1, 4, 30013, 30008, 74, "Stat Spy", "Cast on another player to see\\ntheir skill levels", TDA, 14, 8, 2);
		addLunar3RunesBigBox(30138, 9075, 563, 554, 1, 1, 2, 30012, 30003, 74, "Strykeworms Tele", "Teleports you to the Strykeworms", TDA, 15, 0, 5);
		addLunar3RunesBigBox(30146, 9075, 563, 554, 1, 1, 5, 30012, 30003, 75, "Tele Group Barbarian", "Teleports players to the\\nBarbarian Outpost", TDA, 16, 0, 5);
		addLunar3RunesSmallBox(30154, 9075, 554, 556, 1, 5, 9, 30003, 30005, 76, "Superglass Make", "Make glass without a furnace", TDA, 17, 16, 2);
		addLunar3RunesSmallBox(30162, 9075, 563, 555, 1, 1, 3, 30012, 30004, 77, "Khazard Teleport", "Teleports you to Port khazard", TDA, 18, 0, 5);
		addLunar3RunesSmallBox(30170, 9075, 563, 555, 1, 1, 7, 30012, 30004, 78, "Tele Group Khazard", "Teleports players to Port khazard", TDA, 19, 0, 5);
		addLunar3RunesBigBox(30178, 9075, 564, 559, 1, 0, 4, 30013, 30008, 78, "Dream", "Take a rest and restore hitpoints 3\\n times faster", TDA, 20, 0, 5);
		addLunar3RunesSmallBox(30186, 9075, 557, 555, 1, 9, 4, 30006, 30004, 79, "String Jewellery", "String amulets without wool", TDA, 21, 0, 5);
		addLunar3RunesLargeBox(30194, 9075, 557, 555, 1, 9, 9, 30006, 30004, 80, "Boost Other Stats", "Temporarily increases Atk, Str\\nand Def of other players", TDA, 22, 0, 5);
		addLunar3RunesSmallBox(30202, 9075, 554, 555, 1, 6, 6, 30003, 30004, 81, "Magic Imbue", "Combine runes without a talisman", TDA, 23, 0, 5);
		addLunar3RunesBigBox(30210, 9075, 561, 557, 2, 1, 14, 30010, 30006, 82, "Fertile Soil", "Fertilise a farming patch with\\nsuper compost", TDA, 24, 4, 2);
		addLunar3RunesBigBox(30218, 9075, 557, 555, 2, 11, 9, 30006, 30004, 83, "Boost Stats", "Temporarily increases Attack,\\nStrength and Defence", TDA, 25, 0, 5);
		addLunar3RunesSmallBox(30226, 9075, 563, 555, 2, 2, 9, 30012, 30004, 84, "Fishing Guild Teleport", "Teleports you to the fishing guild", TDA, 26, 0, 5);
		addLunar3RunesLargeBox(30234, 9075, 563, 555, 1, 2, 13, 30012, 30004, 85, "Tele Group Fishing\\nGuild", "Teleports players to the Fishing\\nGuild", TDA, 27, 0, 5);
		addLunar3RunesSmallBox(30242, 9075, 557, 561, 2, 14, 0, 30006, 30010, 85, "Plank Make", "Turn Logs into planks", TDA, 28, 16, 5);
		addLunar3RunesSmallBox(30250, 9075, 563, 555, 2, 2, 9, 30012, 30004, 86, "Catherby Teleport", "Teleports you to Catherby", TDA, 29, 0, 5);
		addLunar3RunesSmallBox(30258, 9075, 563, 555, 2, 2, 14, 30012, 30004, 87, "Tele Group Catherby", "Teleports players to Catherby", TDA, 30, 0, 5);
		addLunar3RunesSmallBox(30266, 9075, 563, 555, 2, 2, 7, 30012, 30004, 88, "Ice Plateau Teleport", "Teleports you to Ice Plateau", TDA, 31, 0, 5);
		addLunar3RunesBigBox(30274, 9075, 563, 555, 2, 2, 15, 30012, 30004, 89, "Tele Group Ice\\n Plateau", "\\nTeleports players to Ice Plateau", TDA, 32, 0, 5);
		addLunar3RunesBigBox(30282, 9075, 563, 561, 2, 1, 0, 30012, 30010, 90, "Energy Transfer", "Spend HP and REnergy to give\\naway to another player", TDA, 33, 8, 2);
		addLunar3RunesBigBox(30290, 9075, 563, 565, 2, 2, 0, 30012, 30014, 91, "Heal Other", "Heal targeted player by\\nup to 40 HP", TDA, 34, 8, 2);
		addLunar3RunesBigBox(30298, 9075, 560, 557, 2, 1, 9, 30009, 30006, 92, "Vengeance Other", "Allows another player to rebound\\ndamage to an opponent", TDA, 35, 8, 2);
		addLunar3RunesSmallBox(30306, 9075, 560, 557, 3, 1, 9, 30009, 30006, 93, "Vengeance", "Rebound damage to an opponent", TDA, 36, 0, 5);
		addLunar3RunesBigBox(30314, 9075, 565, 563, 3, 2, 5, 30014, 30012, 94, "Heal Group", "Heal all players around you\\nby up to 40 HP", TDA, 37, 0, 5);
		addLunar3RunesBigBox(30322, 9075, 564, 563, 2, 1, 0, 30013, 30012, 95, "Spellbook Swap", "Change to another spellbook for 1\\nspell cast", TDA, 38, 0, 5);
	}
	public static void constructLunar() {
		RSInterface Interface = addTabInterface(16640);
		int[] LunarIDs = { 30000, 30017, 30025, 30032, 30040, 30048, 30056, 30064, 30075, 30083, 30091, 30099, 30106, 30114, 30122, 30130, 30138, 30146, 30154, 30162, 30170, 30178, 30186, 30194, 30202, 30210, 30218, 30226, 30234, 30242, 30250, 30258, 30266, 30274, 30282, 30290, 30298, 30306, 30314, 30322, 30001, 30018, 30026, 30033, 30041, 30049, 30057, 30065, 30076, 30084, 30092, 30100, 30107, 30115, 30123, 30131, 30139, 30147, 30155, 30163, 30171, 30179, 30187, 30195, 30203, 30211, 30219, 30227, 30235, 30243, 30251, 30259, 30267, 30275, 30283, 30291, 30299, 30307, 30323, 30315};
		int[] LunarX = { 11, 40, 71, 103, 133, 162, 8, 41, 71, 103, 134, 165, 12, 42, 71, 103, 135, 165, 14, 42, 71, 101, 135, 168, 10, 42, 74, 103, 135, 164, 10, 42, 71, 103, 138, 162, 13, 42, 69, 104, 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
		int[] LunarY = { 10, 9, 12, 10, 12, 10, 38, 39, 39, 39, 39, 37, 68, 68, 66, 68, 68, 68, 97, 97, 97, 97, 98, 98, 126, 124, 125, 125, 125, 126, 155, 155, 155, 155, 155, 155, 185, 185, 183, 184, 184, 176, 176, 163, 176, 176, 176, 176, 163, 176, 176, 176, 176, 163, 176, 163, 163, 163, 176, 176, 176, 163, 176, 149, 176, 163, 163, 176, 149, 176, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
		Interface.totalChildren(LunarIDs.length);
		for(int index = 0; index < LunarIDs.length; index++) {
			Interface.child(index, LunarIDs[index], LunarX[index], LunarY[index]);
		}
	}
	
	public static void setBounds(int ID, int X, int Y, int frame, RSInterface RSinterface){
		RSinterface.children[frame] = ID;
        RSinterface.childX[frame] = X;
        RSinterface.childY[frame] = Y;
	}
	
	public static void setChildren(int total,RSInterface i){
	    i.children = new int[total];
        i.childX = new int[total];
        i.childY = new int[total];
	}

    public static void addButton(int i, int j, String s, int k, int l, String s1, int i1)
    {
        RSInterface rsinterface = addInterface(i);
        rsinterface.id = i;
        rsinterface.parentID = i;
        rsinterface.type = 5;
        rsinterface.atActionType = i1;
        rsinterface.contentType = 0;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = 52;
        rsinterface.sprite1 = imageLoader(j, s);
        rsinterface.sprite2 = imageLoader(j, s);
        rsinterface.width = k;
        rsinterface.height = l;
        rsinterface.tooltip = s1;
    }

}