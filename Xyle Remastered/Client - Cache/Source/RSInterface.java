// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public final class RSInterface {

	public void swapInventoryItems(int i, int j) {
		int k = inv[i];
		inv[i] = inv[j];
		inv[j] = k;
		k = invStackSizes[i];
		invStackSizes[i] = invStackSizes[j];
		invStackSizes[j] = k;
	}

	public static void unpack(StreamLoader streamLoader, TextDrawingArea textDrawingAreas[], StreamLoader streamLoader_1) {
		aMRUNodes_238 = new MRUNodes(50000);
		Stream stream = new Stream(streamLoader.getDataForName("data"));
		int i = -1;
		int j = stream.readUnsignedWord();
		interfaceCache = new RSInterface[45000];
		while(stream.currentOffset < stream.buffer.length) {
			int k = stream.readUnsignedWord();
			if(k == 65535) {
				i = stream.readUnsignedWord();
				k = stream.readUnsignedWord();
			}
			RSInterface rsInterface = interfaceCache[k] = new RSInterface();
			rsInterface.id = k;
			rsInterface.parentID = i;
			rsInterface.type = stream.readUnsignedByte();
			rsInterface.atActionType = stream.readUnsignedByte();
			rsInterface.contentType = stream.readUnsignedWord();
			rsInterface.width = stream.readUnsignedWord();
			rsInterface.height = stream.readUnsignedWord();
			rsInterface.opacity = (byte) stream.readUnsignedByte();
			rsInterface.hoverType = stream.readUnsignedByte();
			if(rsInterface.hoverType != 0)
				rsInterface.hoverType = (rsInterface.hoverType - 1 << 8) + stream.readUnsignedByte();
			else
				rsInterface.hoverType = -1;
			int i1 = stream.readUnsignedByte();
			if(i1 > 0) {
				rsInterface.valueCompareType = new int[i1];//				rsInterface.valueCompareType = new int[i1];
				rsInterface.requiredValues = new int[i1];//			rsInterface.requiredValues = new int[i1];
				for(int j1 = 0; j1 < i1; j1++) {
					rsInterface.valueCompareType[j1] = stream.readUnsignedByte();
					rsInterface.requiredValues[j1] = stream.readUnsignedWord();
				}

			}
			int k1 = stream.readUnsignedByte();
			if(k1 > 0) {
				rsInterface.valueIndexArray = new int[k1][];
				for(int l1 = 0; l1 < k1; l1++) {
					int i3 = stream.readUnsignedWord();
					rsInterface.valueIndexArray[l1] = new int[i3];
					for(int l4 = 0; l4 < i3; l4++)
						rsInterface.valueIndexArray[l1][l4] = stream.readUnsignedWord();

				}

			}
			if(rsInterface.type == 0) {
				rsInterface.drawsTransparent = false;
				rsInterface.scrollMax = stream.readUnsignedWord();
				rsInterface.interfaceShown = stream.readUnsignedByte() == 1;
				int i2 = stream.readUnsignedWord();
				rsInterface.children = new int[i2];
				rsInterface.childX = new int[i2];
				rsInterface.childY = new int[i2];
				for(int j3 = 0; j3 < i2; j3++) {
					rsInterface.children[j3] = stream.readUnsignedWord();
					rsInterface.childX[j3] = stream.readSignedWord();
					rsInterface.childY[j3] = stream.readSignedWord();
				}
			}
			if(rsInterface.type == 1) {
				stream.readUnsignedWord();
				stream.readUnsignedByte();
			}
			if(rsInterface.type == 2) {
				rsInterface.inv = new int[rsInterface.width * rsInterface.height];
				rsInterface.invStackSizes = new int[rsInterface.width * rsInterface.height];
				rsInterface.aBoolean259 = stream.readUnsignedByte() == 1;
				rsInterface.isInventoryInterface = stream.readUnsignedByte() == 1;
				rsInterface.usableItemInterface = stream.readUnsignedByte() == 1;
				rsInterface.aBoolean235 = stream.readUnsignedByte() == 1;
				rsInterface.invSpritePadX = stream.readUnsignedByte();
				rsInterface.invSpritePadY = stream.readUnsignedByte();
				rsInterface.spritesX = new int[20];
				rsInterface.spritesY = new int[20];
				rsInterface.sprites = new Sprite[20];
				for(int j2 = 0; j2 < 20; j2++) {
					int k3 = stream.readUnsignedByte();
					if(k3 == 1) {
						rsInterface.spritesX[j2] = stream.readSignedWord();
						rsInterface.spritesY[j2] = stream.readSignedWord();
						String s1 = stream.readString();
						if(streamLoader_1 != null && s1.length() > 0) {
							int i5 = s1.lastIndexOf(",");
							rsInterface.sprites[j2] = method207(Integer.parseInt(s1.substring(i5 + 1)), streamLoader_1, s1.substring(0, i5));
						}
					}
				}
				rsInterface.actions = new String[5];
				for(int l3 = 0; l3 < 5; l3++) {
					rsInterface.actions[l3] = stream.readString();
					if(rsInterface.actions[l3].length() == 0)
						rsInterface.actions[l3] = null;
					if(rsInterface.parentID == 1644)
						rsInterface.actions[2] = "Operate";
					if(rsInterface.parentID == 3824)
                        rsInterface.actions[4] = "Buy 50";
				}
			}
			if(rsInterface.type == 3)
				rsInterface.aBoolean227 = stream.readUnsignedByte() == 1;
			if(rsInterface.type == 4 || rsInterface.type == 1) {
				rsInterface.centerText = stream.readUnsignedByte() == 1;
				int k2 = stream.readUnsignedByte();
				if(textDrawingAreas != null)
					rsInterface.textDrawingAreas = textDrawingAreas[k2];
				rsInterface.textShadow = stream.readUnsignedByte() == 1;
			}
			if(rsInterface.type == 4) {
				rsInterface.message = stream.readString();
				rsInterface.aString228 = stream.readString();
			}
			if(rsInterface.type == 1 || rsInterface.type == 3 || rsInterface.type == 4)
				rsInterface.textColor = stream.readDWord();
			if(rsInterface.type == 3 || rsInterface.type == 4) {
				rsInterface.anInt219 = stream.readDWord();
				rsInterface.anInt216 = stream.readDWord();
				rsInterface.anInt239 = stream.readDWord();
			}
			if(rsInterface.type == 5) {
				rsInterface.drawsTransparent = false;
				String s = stream.readString();
				if(streamLoader_1 != null && s.length() > 0) {
					int i4 = s.lastIndexOf(",");
					rsInterface.sprite1 = method207(Integer.parseInt(s.substring(i4 + 1)), streamLoader_1, s.substring(0, i4));
				}
				s = stream.readString();
				if(streamLoader_1 != null && s.length() > 0) {
					int j4 = s.lastIndexOf(",");
					rsInterface.sprite2 = method207(Integer.parseInt(s.substring(j4 + 1)), streamLoader_1, s.substring(0, j4));
				}
			}
			if(rsInterface.type == 6) {
				int l = stream.readUnsignedByte();
				if(l != 0) {
					rsInterface.anInt233 = 1;
					rsInterface.mediaID = (l - 1 << 8) + stream.readUnsignedByte();
				}
				l = stream.readUnsignedByte();
				if(l != 0) {
					rsInterface.anInt255 = 1;
					rsInterface.anInt256 = (l - 1 << 8) + stream.readUnsignedByte();
				}
				l = stream.readUnsignedByte();
				if(l != 0)
					rsInterface.anInt257 = (l - 1 << 8) + stream.readUnsignedByte();
				else
					rsInterface.anInt257 = -1;
				l = stream.readUnsignedByte();
				if(l != 0)
					rsInterface.anInt258 = (l - 1 << 8) + stream.readUnsignedByte();
				else
					rsInterface.anInt258 = -1;
				rsInterface.modelZoom = stream.readUnsignedWord();
				rsInterface.modelRotation1 = stream.readUnsignedWord();
				rsInterface.modelRotation2 = stream.readUnsignedWord();
			}
			if(rsInterface.type == 7) {
				rsInterface.inv = new int[rsInterface.width * rsInterface.height];
				rsInterface.invStackSizes = new int[rsInterface.width * rsInterface.height];
				rsInterface.centerText = stream.readUnsignedByte() == 1;
				int l2 = stream.readUnsignedByte();
				if(textDrawingAreas != null)
					rsInterface.textDrawingAreas = textDrawingAreas[l2];
				rsInterface.textShadow = stream.readUnsignedByte() == 1;
				rsInterface.textColor = stream.readDWord();
				rsInterface.invSpritePadX = stream.readSignedWord();
				rsInterface.invSpritePadY = stream.readSignedWord();
				rsInterface.isInventoryInterface = stream.readUnsignedByte() == 1;
				rsInterface.actions = new String[5];
				for(int k4 = 0; k4 < 5; k4++) {
					rsInterface.actions[k4] = stream.readString();
					if(rsInterface.actions[k4].length() == 0)
						rsInterface.actions[k4] = null;
				}

			}
			if(rsInterface.atActionType == 2 || rsInterface.type == 2) {
				rsInterface.selectedActionName = stream.readString();
				rsInterface.spellName = stream.readString();
				rsInterface.spellUsableOn = stream.readUnsignedWord();
			}

			if(rsInterface.type == 8)
				rsInterface.message = stream.readString();

			if(rsInterface.atActionType == 1 || rsInterface.atActionType == 4 || rsInterface.atActionType == 5 || rsInterface.atActionType == 6) {
				rsInterface.tooltip = stream.readString();
				if(rsInterface.tooltip.length() == 0) {
					if(rsInterface.atActionType == 1)
						rsInterface.tooltip = "Ok";
					if(rsInterface.atActionType == 4)
						rsInterface.tooltip = "Select";
					if(rsInterface.atActionType == 5)
						rsInterface.tooltip = "Select";
					if(rsInterface.atActionType == 6)
						rsInterface.tooltip = "Continue";
				}
			}
		}
		aClass44 = streamLoader_1;
		Sidebar0(textDrawingAreas);
		Bank();
			optionTwo(textDrawingAreas);
			// EquipmentTab(textDrawingAreas);
//audio(textDrawingAreas);
//graphic(textDrawingAreas);
		curses(textDrawingAreas);
		normals(textDrawingAreas);
		clanChatTab(textDrawingAreas);
		beastOfBurden(textDrawingAreas);
		beastOfBurden2(textDrawingAreas);
		netWork(textDrawingAreas);
		equipmentScreen(textDrawingAreas);
        configureLunar(textDrawingAreas);
		aMRUNodes_238 = null;
	}
	
	public static void netWork(TextDrawingArea[] wid) {    RSInterface Interface = addTabInterface(14500);
    setChildren(7, Interface);
    addSprite2(14501, 1, "NETWORK/BACKGROUND");
    addHover(14502, 3, 0, 16003, 1, "NETWORK/CLOSE", 17, 17, "Exit");
    addHovered(14503, 2, "NETWORK/CLOSE", 17, 17, 14004);
    //addText2(14506, "Like us, Follow us, Watch us.", 0xFFFF00, false, true, 52, TDA, 1);
	addText(14506, "Like us, Follow us, Watch us.", wid, 2, 0xFFFF00, false, true);
	//addText(19266, "Achievements", 0xFF981F, false, true, -1, TDA, 2);
    addButton2(14512, 1, "NETWORK/ITEM", 64, 64, "@or1@ Facebook", 1);
    addButton2(14513, 2, "NETWORK/ITEM", 64, 64, "@or1@ Twitter", 1);
    addButton2(14514, 3, "NETWORK/ITEM", 64, 64, "@or1@ Youtube", 1);
    setBounds(14501, 55, 95, 0, Interface);
    setBounds(14502, 424, 104, 1, Interface);
    setBounds(14503, 424, 104, 2, Interface);
    setBounds(14506, 185, 104, 3, Interface);
    setBounds(14512, 110, 165, 4, Interface);
    setBounds(14513, 230, 165, 5, Interface);
    setBounds(14514, 350, 165, 6, Interface);
    }
/*public static void EquipmentTab(TextDrawingArea[] wid){
        RSInterface tab = interfaceCache[1644];
        addHoverButton2(19201, "Equipment/Tab/CUSTOM", 1, 40, 40, " ", 0, 19202, 1);
        addHoveredButton2(19202, "Equipment/Tab/CUSTOM", 5, 40, 40, 19203);
        addHoverButton2(19204, "Equipment/Tab/CUSTOM", 2, 40, 40, "Show Items Kept on Death", 0, 19205, 1);
        addHoveredButton2(19205, "Equipment/Tab/CUSTOM", 4, 40, 40, 19206);
        addHoverButton2(29207, "Equipment/Tab/CUSTOM", 3, 40, 40, "Show Price-checker", 0, 19208, 1);
        addHoveredButton2(29208, "Equipment/Tab/CUSTOM", 6, 40, 40, 19209);
        addSprite2(29022, 1, "Equipment/Tab/AURA");
        tab.child(23, 19201, 21, 210);
        tab.child(1, 19226, 95, 250);
        tab.child(24, 19202, 21, 210);
        tab.child(25, 19204, 129, 210);
        tab.child(26, 19205, 129, 210);
        tab.child(0, 29207, 75, 210);
        tab.child(2, 29208, 75, 210);
        tab.child(3, 29022, 10, 4);
       }*/
 
	public static void equipmentScreen(TextDrawingArea[] wid) {
		RSInterface tab = addTabInterface(15106);
		addSprite2(15107, 1, "Equipment/bg");
		//addButton2(14514, 3, "NETWORK/ITEM", 64, 64, "@or1@ Youtube", 1);
		addHoverButton2(15210, "SPRITE", 1, 21, 21, "Close", 250, 15211, 3);
		addHoveredButton2(15211, "SPRITE", 3, 21, 21, 15212);
		addText(15111, "", wid, 2, 0xe4a146, false, true);
		int rofl = 3;
		addText(15112, "Attack bonuses", wid, 2, 0xFF8900, false, true);
		addText(15113, "Defence bonuses", wid, 2, 0xFF8900, false, true);
		addText(15114, "Other bonuses", wid, 2, 0xFF8900, false, true);
		addText(19148, "Summoning: +0", wid, 1, 0xFF8900, false, true);
		addText(19149, "Absorb Melee: +0%", wid, 1, 0xFF9200, false, true);
		addText(19150, "Absorb Magic: +0%", wid, 1, 0xFF9200, false, true);
		addText(19151, "Absorb Ranged: +0%", wid, 1, 0xFF9200, false, true);
		addText(19152, "Ranged Strength: 0", wid, 1, 0xFF9200, false, true);
		addText(19153, "Magic Damage: +0%", wid, 1, 0xFF9200, false, true);
		for(int i = 1675; i <= 1684; i++) { 
			textSize(i, wid, 1); 
		}
		textSize(1686, wid, 1); 
		textSize(1687, wid, 1);
		addChar(15125);
		tab.totalChildren(50);
		tab.child(0, 15107, 15, 5);
		tab.child(1, 15210, 476, 8);
		tab.child(2, 15211, 476, 8);
		tab.child(3, 15111, 14, 30);
		int Child = 4; int Y = 45;
		tab.child(16, 15112, 24, 30 - rofl);
		for(int i = 1675; i <= 1679; i++) {
			tab.child(Child, i, 29, Y - rofl);
			Child++; 
			Y += 14; 
		}
		int edit = 7 + rofl;
		tab.child(18, 15113, 24, 122 - edit); // 147
		tab.child(9, 1680, 29, 137 - edit - 2); // 161
		tab.child(10, 1681, 29, 153 - edit - 3);
		tab.child(11, 1682, 29, 168 - edit - 3);
		tab.child(12, 1683, 29, 183 - edit - 3);
		tab.child(13, 1684, 29, 197 - edit - 3);
		tab.child(44, 19148, 29, 211 - edit - 3);
		tab.child(45, 19149, 29, 225 - edit - 3);
		tab.child(46, 19150, 29, 239 - edit - 3);
		tab.child(47, 19151, 29, 253 - edit - 3);
		/* bottom */
		int edit2 = 33 - rofl, edit3 = 2;
		tab.child(19, 15114, 24, 223 + edit2);
		tab.child(14, 1686, 29, 262-24 + edit2 - edit3);
		tab.child(17, 19152, 29, 276-24 + edit2 - edit3);
		tab.child(48, 1687, 29, 290-24 + edit2 - edit3);
		tab.child(49, 19153, 29, 304-24 + edit2 - edit3);
		
		tab.child(15, 15125, 170, 200);
		tab.child(20, 1645, 104+295, 149-52);
		tab.child(21, 1646, 399, 163);
		tab.child(22, 1647, 399, 163);
		tab.child(23, 1648, 399, 58+146);
		tab.child(24, 1649, 26+22+297-2, 110-44+118-13+5);
		tab.child(25, 1650, 321+22, 58+154);
		tab.child(26, 1651, 321+134, 58+118);
		tab.child(27, 1652, 321+134, 58+154);
		tab.child(28, 1653, 321+48, 58+81);
		tab.child(29, 1654, 321+107, 58+81);
		tab.child(30, 1655, 321+58, 58+42);
		tab.child(31, 1656, 321+112, 58+41);
		tab.child(32, 1657, 321+78, 58+4);
		tab.child(33, 1658, 321+37, 58+43);
		tab.child(34, 1659, 321+78, 58+43);
		tab.child(35, 1660, 321+119, 58+43);
		tab.child(36, 1661, 321+22, 58+82);
		tab.child(37, 1662, 321+78, 58+82);
		tab.child(38, 1663, 321+134, 58+82);
		tab.child(39, 1664, 321+78, 58+122);
		tab.child(40, 1665, 321+78, 58+162);
		tab.child(41, 1666, 321+22, 58+162);
		tab.child(42, 1667, 321+134, 58+162);
		tab.child(43, 1688, 50+297-2, 110-13+5);
		for(int i = 1675; i <= 1684; i++){
			RSInterface rsi = interfaceCache[i];
            rsi.textColor = 0xFF9200;
			rsi.centerText = false; 
		}
		for(int i = 1686; i <= 1687; i++) {
			RSInterface rsi = interfaceCache[i];
			rsi.textColor = 0xFF9200;
			rsi.centerText = false; 
		}
	}
	public static void addChar(int ID) { 
			RSInterface t = interfaceCache[ID] = new RSInterface(); 
			t.id = ID; 
			t.parentID = ID; 
			t.type = 6;
			t.atActionType = 0; 
			t.contentType = 328; 
			t.width = 180; 
			t.height = 190; 
			t.opacity = 0;
			t.hoverType = 0;
			t.modelZoom = 560;
			t.modelRotation1 = 30;
			t.modelRotation2 = 0; 
			t.anInt257 = -1; 
			t.anInt258 = -1; 
		}

	public static void addBankHover(int interfaceID, int actionType, int hoverid, int spriteId, int spriteId2, String NAME, int Width, int Height, int configFrame, int configId, String Tooltip,int hoverId2, int hoverSpriteId,int hoverSpriteId2, String hoverSpriteName, int hoverId3, String hoverDisabledText, String hoverEnabledText, int X, int Y){
		RSInterface hover = addTabInterface(interfaceID);
		hover.id = interfaceID;
		hover.parentID = interfaceID;
		hover.type = 5;
		hover.atActionType = actionType;
		hover.contentType = 0;
//		hover.aByte254 = 0;
//		hover.mOverInterToTrigger = hoverid;
		hover.sprite1 = imageLoader(spriteId, NAME);
		hover.sprite2 = imageLoader(spriteId2, NAME);
		hover.width = Width;
		hover.tooltip = Tooltip;
		hover.height = Height;
//		hover.anIntArray245 = new int[1];
//		hover.anIntArray212 = new int[1];
//		hover.anIntArray245[0] = 1;
//		hover.anIntArray212[0] = configId;
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
//		hover.isMouseoverTriggered = true;
//		hover.mOverInterToTrigger = -1;
		addSprite2(hoverId2, hoverSpriteId, hoverSpriteId2, hoverSpriteName, configId, configFrame);
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
//		hover.aByte254 = 0;
//		hover.mOverInterToTrigger = hoverid;
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
//		hover.isMouseoverTriggered = true;
//		hover.mOverInterToTrigger = -1;
		addSprite2(hoverId2, hoverSpriteId, hoverSpriteId, hoverSpriteName,0,0);
		addHoverBox(hoverId3, interfaceID,hoverDisabledText, hoverDisabledText, 0, 0);
		setChildren(2, hover);
		setBounds(hoverId2, 15, 60, 0, hover);
		setBounds(hoverId3, X, Y, 1, hover);
	}
	/*public static void audio(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(400);
		addSprite2(451, 1, "AUDIO");
		addHoverButton(460, "CLOSE", 3, 50, 50, "Close", -1, 461, 1);
		addHoveredButton(461, "CLOSE", 4, 20, 20, 17269);
		//------------------------------------------------------------------------------------//
		addButton2(25810, 5, -1, 3, 4, "/OptionTab/OPTION", 32, 32, "Adjust Music Level", 168, 4);
		addButton2(25811, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 168, 4);
		addButton2(25812, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 168, 3);
		addButton2(25813, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 168, 2);
		addButton2(25814, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 168, 1);
		addButton2(25815, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 168, 0);
		//------------------------------------------------------------------------------------//
		addButton2(25816, 5, -1, 5, 6, "/OptionTab/OPTION", 32, 32, "Adjust Sounds", 169, 4);
		addButton2(25817, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 169, 4);
		addButton2(25818, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 169, 3);
		addButton2(25819, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 169, 2);
		addButton2(25820, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 169, 1);
		addButton2(25821, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 169, 0);
		//------------------------------------------------------------------------------------//
		addButton2(25822, 5, -1, 7, 8, "/OptionTab/OPTION", 32, 32, "Adjust Sound Effects", 400, 0);
		addButton2(25823, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 400, 0);
		addButton2(25824, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 400, 1);
		addButton2(25825, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 400, 2);
		addButton2(25826, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 400, 3);
		addButton2(25827, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 400, 4);
		//------------------------------------------------------------------------------------//
		tab.totalChildren(21);
		tab.child(0, 451, 178, 43);
		tab.child(1, 25811, 204, 113);
		tab.child(2, 25812, 228, 113);
		tab.child(3, 25813, 255, 113);
		tab.child(4, 25814, 281, 113);
		tab.child(5, 25815, 306, 113);
		tab.child(6, 25810, 248, 79);
		tab.child(7, 460, 328, 47);
		tab.child(8, 461, 328, 47);
		tab.child(9, 25817, 204, 169);
		tab.child(10, 25818, 228, 169);
		tab.child(11, 25819, 255, 169);
		tab.child(12, 25820, 281, 169);
		tab.child(13, 25821, 306, 169);
		tab.child(14, 25816, 248, 135);
		tab.child(15, 25823, 204, 228);
		tab.child(16, 25824, 228, 228);
		tab.child(17, 25825, 255, 228);
		tab.child(18, 25826, 281, 228);
		tab.child(19, 25827, 306, 228);	
		tab.child(20, 25822, 250, 193);
		tab = addTabInterface(1400);
		tab.width = 474;
		tab.height = 213;
		tab.scrollMax = 305;
		for(int i = 1401; i <= 1430; i++){
		addText(i, "", tda, 1, 0xffffff, false, true);
		}
		tab.totalChildren(30);
		int Child = 0;
		int Y = 5;
		for(int i = 1401; i <= 1430; i++){
		tab.child(Child, i, 248, Y);
		Child++;
		Y += 13;
		}
	}*/
	
		public static void optionTwo(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(904);
		addSprite2(351, 0, "OPTION");
		addButton2(360, 4, 361, 1, 1, "/OptionTab/OPT", 40, 40, "Graphic Settings", 160, 1);
		drawTooltip(361, "Toggle Mouse Buttons");
		addButton2(364, 4, 365, 2, 2, "/OptionTab/OPT", 40, 40, "Audio Settings", 161, 1);
		drawTooltip(366, "Toggle Mouse Buttons");
		
		addButton2(25828, 4, 25829, 9, 10, "/OptionTab/OPTION", 90, 90, "Toggle Number of Mouse Buttons", 170, 1);
		drawTooltip(25829, "Mouse buttons\n(currently 2)");		
		addButton2(25831, 4, 25832, 9, 10, "/OptionTab/OPTION", 40, 40, "Toggle Chat Effects", 171, 1);
		drawTooltip(25832, "Toggle Chat Effects");
		addButton2(25834, 4, 25835, 9, 10, "/OptionTab/OPTION", 40, 40, "Split Private Chat", 287, 1);
		drawTooltip(25835, "Split Private Chat");
		addButton2(25837, 4, 25838, 9, 10, "/OptionTab/OPTION", 40, 40, "Toggle Accept Aid", 427, 0);
		drawTooltip(25838, "Toggle Accept Aid");
		addButton2(152, 4, 25841, 9, 10, "/OptionTab/OPTION", 40, 40, "Toggle Run", 173, 1);
		drawTooltip(25841, "Toggle Run");
		addButton2(25843, 9, 25844, "/OptionTab/OPTION", 40, 40, "Open Other Settings", 1);
		drawTooltip(25844, "Open Other Settings");
		addSprite2(380, 11, "/OptionTab/OPTION");
		addSprite2(381, 12, "/OptionTab/OPTION"); 
		addSprite2(382, 13, "/OptionTab/OPTION"); 
		addSprite2(383, 14, "/OptionTab/OPTION");
		addSprite2(384, 15, "/OptionTab/OPTION");
		addSprite2(385, 17, "/OptionTab/OPTION");
		addText(390, "100%", 0xFF9800, true, true, 52, 1);
		tab.totalChildren(22);
		tab.child(0, 351, 0, 0);
		tab.child(1, 25828, 19, 129);
		tab.child(2, 25829, 19, 102);
		tab.child(3, 25831, 75, 129);
		tab.child(4, 25832, 78, 102);
		tab.child(5, 25834, 131, 129);
		tab.child(6, 25835, 71, 102);
		tab.child(7, 25837, 19, 202);
		tab.child(8, 25838, 19, 183);
		tab.child(9, 152, 75, 202);
		tab.child(10, 25841, 78, 183);
		tab.child(11, 25843, 130, 202);
		tab.child(12, 25844, 133, 183);
		tab.child(13, 380, 23, 133);
		tab.child(14, 381, 79, 133);
		tab.child(15, 382, 135, 133);
		tab.child(16, 383, 23, 207);
		tab.child(17, 384, 84, 205);
		tab.child(18, 385, 137, 208);
		tab.child(19, 390, 95, 225);
		
		tab.child(20, 360, 40, 35);
		tab.child(21, 364, 110, 35);
		tab = addTabInterface(1400);
		tab.width = 474;
		tab.height = 213;
		tab.scrollMax = 305;
		for(int i = 1401; i <= 1430; i++){
		addText(i, "", tda, 1, 0xffffff, false, true);
		}
		tab.totalChildren(30);
		int Child = 0;
		int Y = 5;
		for(int i = 1401; i <= 1430; i++){
		tab.child(Child, i, 248, Y);
		Child++;
		Y += 13;
    }
	}

	/*public static void graphic(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(100);
		addSprite2(151, 0, "/Graphic/GRAPHIC");
		addHoverButton2(560, "CLOSE", 3, 50, 50, "Close", -1, 561, 1);
		addHoveredButton2(561, "CLOSE", 4, 20, 20, 17269);
		addButton2(25805, 5, -1, 2, 2, "/OptionTab/OPTION", 32, 32, "Adjust Brightness", 166, 1);
		addButton2(25806, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 166, 1);
		addButton2(25807, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 166, 2);
		addButton2(25808, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 166, 3);
		addButton2(25809, 5, -1, -1, 18, "/OptionTab/OPTION", 16, 16, "Select", 166, 4);
		tab.totalChildren(8);
		tab.child(0, 151, 7, 50);
		tab.child(1, 25806, 205, 105);
		tab.child(2, 25807, 236, 105);
		tab.child(3, 25808, 267, 105);
		tab.child(4, 25809, 301, 105);
		tab.child(5, 25805, 165, 97);
		tab.child(6, 560, 480, 52);
		tab.child(7, 561, 480, 52);
		tab = addTabInterface(1400);
		tab.width = 474;
		tab.height = 213;
		tab.scrollMax = 305;
		for(int i = 1401; i <= 1430; i++){
		addText(i, "", tda, 1, 0xffffff, false, true);
		}
		tab.totalChildren(30);
		int Child = 0;
		int Y = 5;
		for(int i = 1401; i <= 1430; i++){
		tab.child(Child, i, 248, Y);
		Child++;
		Y += 13;
		}
	}*/
	
	public RSInterface totalChildrenReturn(int total) {
		this.children = new int[total];
		this.childX = new int[total];
		this.childY = new int[total];
		return this;
	}
	
	public RSInterface childReturn(int index, int id, int x, int y) {
		this.children[index] = id;
		this.childX[index] = x;
		this.childY[index] = y;
		return this;
	}
		
	
	public static void beastOfBurden(TextDrawingArea[] wid) {
		RSInterface familiarInventory = addTabInterface(24000).totalChildrenReturn(4).childReturn(0, 24001, 104-25, 16);
		familiarInventory.addSprite(24001, 0, "familiarinvent");
		familiarInventory.childReturn(1, 24002, 95, 59).addInventoryItemGroup(24002, 5, 6);
		familiarInventory.childReturn(2, 24003, 216-25, 25).addText(24003, "Familiar's Inventory", wid, 2, 0xFF8C00, false, true);
		familiarInventory.childReturn(3, 24004, 445-25, 27).addButton(24004, 1, "familiarinvent", "Close");
	}
		
	public static void beastOfBurden2(TextDrawingArea[] wid) {
		RSInterface familiarInventory = addTabInterface(24005).totalChildrenReturn(1).childReturn(0, 24006, 0, 0);
		familiarInventory.addInventoryItemGroup2(24006, 7, 4);
	}
	
	public static void addInventoryItemGroup(int id, int h, int w) {
		RSInterface Tab = interfaceCache[id] = new RSInterface();
		Tab.inv = new int[w * h];
		Tab.invStackSizes = new int[w * h];
		for(int i1 = 0; i1 < w * h; i1++) {
			Tab.invStackSizes[i1] = 0; //inv item stack size
			Tab.inv[i1] = 0; //inv item ids
		}
		Tab.spritesY = new int[30];
		Tab.spritesX = new int[30];
		int[] rowX = {0, 22, 44, 66, 88, 110,
				0, 22, 44, 66, 88, 110,
				0, 22, 44, 66, 88, 110,
				0, 22, 44, 66, 88, 110,
				0, 22, 44, 66, 88, 110};
		int[] rowY = {0, 0, 0, 0, 0, 0,
				22, 22, 22, 22, 22, 22,
				44, 44, 44, 44, 44, 44,
				66, 66, 66, 66, 66, 66,
				88, 88, 88, 88, 88, 88};
		for(int i2 = 0; i2 < 30; i2++) {
			Tab.spritesY[i2] = rowY[i2];
			Tab.spritesX[i2] = rowX[i2];
		}
		Tab.actions = new String[] {"Withdraw 1", "Withdraw 5", "Withdraw 10", "Withdraw All", null};
		Tab.width = w;
		Tab.hoverType = -1;
		Tab.parentID = id;
		Tab.id = id;
		Tab.scrollMax = 0;
		Tab.type = 2;
		Tab.height = h;
	}
	
	public static void addInventoryItemGroup2(int id, int h, int w) {
		RSInterface Tab = interfaceCache[id] = new RSInterface();
		Tab.inv = new int[w * h];
		Tab.invStackSizes = new int[w * h];
		for(int i1 = 0; i1 < w * h; i1++) {
			Tab.invStackSizes[i1] = 0; //inv item stack size
			Tab.inv[i1] = 0; //inv item ids
		}
		Tab.spritesY = new int[28];
		Tab.spritesX = new int[28];
		for(int i2 = 0; i2 < 28; i2++) {
			Tab.spritesY[i2] = 8;
			Tab.spritesX[i2] = 16;
		}
		Tab.invSpritePadX = 10;
		Tab.invSpritePadY = 4;
		Tab.actions = new String[] {"Store 1", "Store 5", "Store 10", "Store All", null};
		Tab.width = w;
		Tab.hoverType = -1;
		Tab.parentID = id;
		Tab.id = id;
		Tab.scrollMax = 0;
		Tab.type = 2;
		Tab.height = h;
	}
	/***********************/
	/** addButton Methods **/
	/**********************/
	
	public static void addButton(int id, int sid, String spriteName, String tooltip) { //Loaded by Cachepacking
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.sprite1 = method207(sid, aClass44, spriteName);
		tab.sprite2 = method207(sid, aClass44, spriteName);
		tab.width = tab.sprite1.myWidth;
		tab.height = tab.sprite2.myHeight;
		tab.tooltip = tooltip;
	}
		public static void addButton2(int i, int j, int hoverId, String name, int W, int H, String S, int AT) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.type = 5;
		RSInterface.atActionType = AT;
		RSInterface.opacity = 0;
		RSInterface.hoverType = hoverId;
		RSInterface.sprite1 = imageLoader(j,name);
		RSInterface.sprite2 = imageLoader(j,name);
		RSInterface.width = W;
		RSInterface.height = H;
		RSInterface.tooltip = S;
	}
	
	public static void addButton2(int id, int sid, String spriteName, String tooltip) { //Loaded by signlink.findcachedir - ./Sprites/
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.sprite1 = imageLoader(sid, spriteName);
		tab.sprite2 = imageLoader(sid, spriteName);
		tab.width = tab.sprite1.myWidth;
		tab.height = tab.sprite2.myHeight;
		tab.tooltip = tooltip;
	}
	
	public static void addButton2(int id, int sid, String spriteName, String tooltip, int w, int h) { //Loaded by signlink.findcachedir - ./Sprites/
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.sprite1 = imageLoader(sid, spriteName);
		tab.sprite2 = imageLoader(sid, spriteName);
		tab.width = w;
		tab.height = h;
		tab.tooltip = tooltip;
	}
	
	
	public static void addButton2(int i, int j, String name, int W, int H, String S, int AT) {
			RSInterface RSInterface = addInterface(i);
			RSInterface.id = i;
			RSInterface.parentID = i;
			RSInterface.type = 5;
			RSInterface.atActionType = AT;
			RSInterface.contentType = 0;
			RSInterface.opacity = 0;
			RSInterface.hoverType = 52;
			RSInterface.sprite1 = imageLoader(j,name);
			RSInterface.sprite2 = imageLoader(j,name);
			RSInterface.width = W;
			RSInterface.height = H;
			RSInterface.tooltip = S;
		}
			public static void drawTooltip(int id, String text) {
		RSInterface rsinterface = addTabInterface(id);
		rsinterface.parentID = id;
		rsinterface.type = 0;
		rsinterface.interfaceShown = true;
		rsinterface.hoverType = -1;
		addTooltipBox(id + 1, text);
		rsinterface.totalChildren(1);
		rsinterface.child(0, id + 1, 0, 0);
	}
	
			private static void addButton2(int ID, int type, int hoverID, int dS, int eS, String NAME, int W, int H, String text, int configFrame, int configId) {
		RSInterface rsinterface = addInterface(ID);
		rsinterface.id = ID;
		rsinterface.parentID = ID;
		rsinterface.type = 5;
		rsinterface.atActionType = type;
		rsinterface.opacity = 0;
		rsinterface.hoverType = hoverID;
		rsinterface.sprite1 = imageLoader(dS, NAME);
		rsinterface.sprite2 = imageLoader(eS, NAME);
		rsinterface.width = W;
		rsinterface.height = H;
		rsinterface.valueCompareType = new int[1];
		rsinterface.requiredValues = new int[1];
		rsinterface.valueCompareType[0] = 1;
		rsinterface.requiredValues[0] = configId;
		rsinterface.valueIndexArray = new int[1][3];
		rsinterface.valueIndexArray[0][0] = 5;
		rsinterface.valueIndexArray[0][1] = configFrame;
		rsinterface.valueIndexArray[0][2] = 0;
		rsinterface.tooltip = text;
	}
	public static void Bank(){
		RSInterface Interface = addTabInterface(5292);
		setChildren(19, Interface);
		addSprite(5293, 6, "quickpray");
		setBounds(5293, 13, 13, 0, Interface);
		addHover(5384, 3, 0, 5380, 1, "Bank/BANK", 17, 17, "Close Window");
		addHovered(5380, 2, "Bank/BANK", 17, 17, 5379);
		setBounds(5384, 476, 16, 3, Interface);
		setBounds(5380, 476, 16, 4, Interface);	
		addHover(5294, 4, 0, 5295, 3, "Bank/BANK", 114, 25, "Set A Bank PIN");
		addHovered(5295, 4, "Bank/BANK", 114, 25, 5296);
		setBounds(5294, 110, 285, 5, Interface);
		setBounds(5295, 110, 285, 6, Interface);
		addBankHover(21000, 4, 21001, 5, 8, "Bank/BANK", 35, 25, 304, 1, "Swap Withdraw Mode", 21002, 7, 6, "Bank/BANK", 21003, "Switch to insert items \nmode", "Switch to swap items \nmode.", 12, 20);
		setBounds(21000, 25, 285, 7, Interface);
		setBounds(21001, 10, 225, 8, Interface);
		addBankHover(21004, 4, 21005, 13, 15, "Bank/BANK", 35, 25, 0, 1, "Search", 21006, 14, 16, "Bank/BANK", 21007, "Click here to search your \nbank", "Click here to search your \nbank", 12, 20);
		setBounds(21004, 65, 285, 9, Interface);
		setBounds(21005, 50, 225, 10, Interface);
		addBankHover(21008, 4, 21009, 9, 11, "Bank/BANK", 35, 25, 115, 1, "Note", 21010, 10, 12, "Bank/BANK", 21011, "Switch to note withdrawal \nmode", "Switch to item withdrawal \nmode", 12, 20);
		setBounds(21008, 240, 285, 11, Interface);
		setBounds(21009, 225, 225, 12, Interface);
		addBankHover1(21012, 5, 21013, 17, "Bank/BANK", 35, 25, "Deposit carried tems", 21014, 18, "Bank/BANK", 21015, "Empty your backpack into\nyour bank", 0, 20);
		setBounds(21012, 375, 285, 13, Interface);
		setBounds(21013, 360, 225, 14, Interface);
		addBankHover1(21016, 5, 21017, 19, "Bank/BANK", 35, 25, "Deposit worn items", 21018, 20, "Bank/BANK", 21019, "Empty the items your are\nwearing into your bank", 0, 20);
		setBounds(21016, 415, 285, 15, Interface);
		setBounds(21017, 400, 225, 16, Interface);
		addBankHover1(21020, 5, 21021, 21, "Bank/BANK", 35, 25, "Deposit beast of burden inventory.", 21022, 22, "Bank/BANK", 21023, "Empty your BoB's inventory\ninto your bank", 0, 20);
		setBounds(21020, 455, 285, 17, Interface);
		setBounds(21021, 440, 225, 18, Interface);
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
	public static void clanChatTab(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(18128);
        addHoverButton(18129, "clanchat", 6, 72, 32, "Join Chat", 550, 18130, 1);
        addHoveredButton(18130, "clanchat", 7, 72, 32, 18131);
        addHoverButton(18132, "clanchat", 6, 72, 32, "Leave Chat", -1, 18133, 5);
        addHoveredButton(18133, "clanchat", 7, 72, 32, 18134);
		addButton(18250, 8, "clanchat", "Toggle lootshare");
        addText(18135, "Join Chat", tda, 0, 0xff9b00, true, true);
        addText(18136, "Leave Chat", tda, 0, 0xff9b00, true, true);
        addSprite(18137, 0, "clanchat");
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
		
	public static void addPrayer(int i, int configId, int configFrame, int requiredValues, int prayerSpriteID, String PrayerName, int Hover) {
	}
	
	public static void addTooltipBox(int id, String text) {
		RSInterface rsi = addInterface(id);
		rsi.id = id;
		rsi.parentID = id;
		rsi.type = 8;
		rsi.popupString = text;
	}

	public static void addTooltip(int id, String text, int H, int W) {
		RSInterface rsi = addTabInterface(id);
		rsi.id = id;
		rsi.type = 0;
		rsi.interfaceShown = true;
		rsi.hoverType = -1;
		addTooltipBox(id + 1, text);
		rsi.totalChildren(1);
		rsi.child(0, id + 1, 0, 0);
		rsi.height = H;
		rsi.width = W;
	}
	
	public static void setBounds(int ID, int X, int Y, int frame, RSInterface RSinterface){
		RSinterface.children[frame] = ID;
		RSinterface.childX[frame] = X;
		RSinterface.childY[frame] = Y;
	}

	public void totalChildren(int t) {
		children = new int[t];
		childX = new int[t];
		childY = new int[t];
	}

	public static void setChildren(int total,RSInterface i){
		i.children = new int[total];
		i.childX = new int[total];
		i.childY = new int[total];
	}
	
	public static void addText(int i, String s,int k, boolean l, boolean m, int a,TextDrawingArea[] TDA, int j) {
        RSInterface rsinterface = addInterface(i);
        rsinterface.parentID = i;
        rsinterface.id = i;
        rsinterface.type = 4;
        rsinterface.atActionType = 0;
        rsinterface.width = 0;
        rsinterface.height = 0;
        rsinterface.contentType = 0;
        rsinterface.opacity = 0;
        rsinterface.hoverType = a;
        rsinterface.centerText = l;
        rsinterface.textShadow = m;
        rsinterface.textDrawingAreas = TDA[j];
        rsinterface.message = s;
        rsinterface.aString228 = "";
        rsinterface.textColor = k;
    }
	/**************************/
	/** addHoverBox Methods **/
	/************************/
	public String hoverText;
	
	public static void addHoverBox(int id, String text) {
        RSInterface rsi = interfaceCache[id];//addTabInterface(id);
        rsi.id = id;
        rsi.parentID = id;
		rsi.interfaceShown = true;
        rsi.type = 8;
        rsi.hoverText = text;
    }
	public static void addHoverBox(int id, int ParentID,String text, String text2, int configId, int configFrame) {
		RSInterface rsi = addTabInterface(id);
		rsi.id = id;
		rsi.parentID = ParentID;
		rsi.type = 8;
		rsi.aString228 = text;
		rsi.message = text2;
//		rsi.anIntArray245 = new int[1];
//		rsi.anIntArray212 = new int[1];
//		rsi.anIntArray245[0] = 1;
//		rsi.anIntArray212[0] = configId;
		rsi.valueIndexArray = new int[1][3];
		rsi.valueIndexArray[0][0] = 5;
		rsi.valueIndexArray[0][1] = configFrame;
		rsi.valueIndexArray[0][2] = 0;
	}
	/******************************/
	/** End addHoverBox Methods **/
	/****************************/
	/***********************/
	/** addSprite Methods **/
	/**********************/
	public static void addSprite(int id, int spriteId, String spriteName) { //Loaded by Cache Packing
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.sprite1 = method207(spriteId, aClass44, spriteName);//imageLoader(spriteId, spriteName);
		tab.sprite2 = method207(spriteId, aClass44, spriteName);//imageLoader(spriteId, spriteName); 
		tab.width = 512;
		tab.height = 334;
	}
	public static void addSprite2(int id, int spriteId, String spriteName) { //Loaded By Cache itself ./Sprites/
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.sprite1 = imageLoader(spriteId, spriteName);
		tab.sprite2 = imageLoader(spriteId, spriteName); 
		tab.width = 512;
		tab.height = 334;
	}
	public static void addSprite2(int ID, int i, int i2, String name, int configId, int configFrame) {
		RSInterface Tab = addTabInterface(ID);
		Tab.id = ID;
		Tab.parentID = ID;
		Tab.type = 5;
		Tab.atActionType = 0;
		Tab.contentType = 0;
		Tab.width = 512;
		Tab.height = 334;
//		Tab.aByte254 = (byte) 0;
//		Tab.mOverInterToTrigger = -1;
//		Tab.anIntArray245 = new int[1];
//		Tab.anIntArray212 = new int[1];
//		Tab.anIntArray245[0] = 1;
//		Tab.anIntArray212[0] = configId;
		Tab.valueIndexArray = new int[1][3];
		Tab.valueIndexArray[0][0] = 5;
		Tab.valueIndexArray[0][1] = configFrame;
		Tab.valueIndexArray[0][2] = 0;
		Tab.sprite1 = imageLoader(i, name);
		Tab.sprite2 = imageLoader(i2, name);
	}
	/****************************/
	/** End addSprite Methods **/
	/**************************/
	

	public static void addHoverButton(int i, String imageName, int j, int width, int height, String text, int contentType, int hoverOver, int aT) {//hoverable button
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = aT;
		tab.contentType = contentType;
		tab.opacity = 0;
		tab.hoverType = hoverOver;
		tab.sprite1 = method207(j, aClass44, imageName);
		tab.sprite2 = method207(j, aClass44, imageName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = text;
	}
		public static void addHoverButton2(int i, String imageName, int j, int width, int height, String text, int contentType, int hoverOver, int aT) {//hoverable button
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = aT;
		tab.contentType = contentType;
		tab.opacity = 0;
		tab.hoverType = hoverOver;
		tab.sprite1 = imageLoader(j, imageName);
		tab.sprite2 = imageLoader(j, imageName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = text;
	}
		public static void addHoveredButton2(int i, String imageName, int j, int w, int h, int IMAGEID) {//hoverable button
		RSInterface tab = addTabInterface(i);
		tab.parentID = i;
		tab.id = i;
		tab.type = 0;
		tab.atActionType = 0;
		tab.width = w;
		tab.height = h;
		tab.interfaceShown = true;
		tab.opacity = 0;
		tab.hoverType = -1;
		tab.scrollMax = 0;
		addHoverImage(IMAGEID, j, j, imageName);
		tab.totalChildren(1);
		tab.child(0, IMAGEID, 0, 0);
	}

	public static void addHoveredButton(int i, String imageName, int j, int w, int h, int IMAGEID) {//hoverable button
		RSInterface tab = addTabInterface(i);
		tab.parentID = i;
		tab.id = i;
		tab.type = 0;
		tab.atActionType = 0;
		tab.width = w;
		tab.height = h;
		tab.interfaceShown = true;
		tab.opacity = 0;
		tab.hoverType = -1;
		tab.scrollMax = 0;
		addHoverImage2(IMAGEID, j, j, imageName);
		tab.totalChildren(1);
		tab.child(0, IMAGEID, 0, 0);
	}
	public static void addHovered(int i, int j, String imageName, int w, int h, int IMAGEID) {
		RSInterface hover = addTabInterface(i);
		hover.parentID = i;
		hover.id = i;
		hover.type = 0;
		hover.atActionType = 0;
		hover.width = w;
		hover.height = h;
//		hover.isMouseoverTriggered = true;
//		hover.mOverInterToTrigger = -1;
		addSprite(IMAGEID, j, imageName);
		setChildren(1, hover);
		setBounds(IMAGEID, 0, 0, 0, hover);
	}
	public static void addHover(int i, int aT, int cT, int hoverid,int sId, String NAME, int W, int H, String tip){
		RSInterface hover = addTabInterface(i);
		hover.id = i;
		hover.parentID = i;
		hover.type = 5;
		hover.atActionType = aT;
		hover.contentType = cT;
//		hover.mOverInterToTrigger = hoverid;
		hover.sprite1 = imageLoader(sId, NAME);
		hover.sprite2 = imageLoader(sId, NAME);
		hover.width = W;
		hover.height = H;
		hover.tooltip = tip;
	}

	public static void addHoverImage(int i, int j, int k, String name) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 334;
		tab.opacity = 0;
		tab.hoverType = 52;
		tab.sprite1 = method207(j, aClass44, name);
		tab.sprite2 = method207(k, aClass44, name);
	}
		public static void addHoverImage2(int i, int j, int k, String name) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 334;
		tab.opacity = 0;
		tab.hoverType = 52;
		tab.sprite1 = imageLoader(j, name);
		tab.sprite2 = imageLoader(k, name);
	}

	public static void addTransparentSprite(int id, int spriteId, String spriteName, int opacity) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 10;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.opacity = (byte) opacity;
		tab.hoverType = 52;
		tab.sprite1 = method207(spriteId, aClass44, spriteName);
		tab.sprite2 = method207(spriteId, aClass44, spriteName);
		tab.width = 512;
		tab.height = 334;
		tab.drawsTransparent = true;
	}
	
	public static void addConfigButton(int ID, int pID, int bID, int bID2, String bName, int width, int height, String tT, int configID, int aT, int configFrame) {
        RSInterface Tab = addTabInterface(ID);
        Tab.parentID = pID;
        Tab.id = ID;
        Tab.type = 5;
        Tab.atActionType = aT;
        Tab.contentType = 0;
        Tab.width = width;
        Tab.height = height;
        Tab.opacity = 0;
        Tab.hoverType = -1;
        Tab.valueCompareType = new int[1];
        Tab.requiredValues = new int[1];
        Tab.valueCompareType[0] = 1;
        Tab.requiredValues[0] = configID;
        Tab.valueIndexArray = new int[1][3];
        Tab.valueIndexArray[0][0] = 5;
        Tab.valueIndexArray[0][1] = configFrame;
        Tab.valueIndexArray[0][2] = 0;
		Tab.sprite1 = method207(bID, aClass44, bName);
		Tab.sprite2 = method207(bID2, aClass44, bName);
        Tab.tooltip = tT;
    }
	public static void addConfigButton2(int ID, int pID, int bID, int bID2, String bName, int width, int height, String tT, int configID, int aT, int configFrame) {
        RSInterface Tab = addTabInterface(ID);
        Tab.parentID = pID;
        Tab.id = ID;
        Tab.type = 5;
        Tab.atActionType = aT;
        Tab.contentType = 0;
        Tab.width = width;
        Tab.height = height;
        Tab.opacity = 0;
        Tab.hoverType = -1;
        Tab.valueCompareType = new int[1];
        Tab.requiredValues = new int[1];
        Tab.valueCompareType[0] = 1;
        Tab.requiredValues[0] = configID;
        Tab.valueIndexArray = new int[1][3];
        Tab.valueIndexArray[0][0] = 5;
        Tab.valueIndexArray[0][1] = configFrame;
        Tab.valueIndexArray[0][2] = 0;
        Tab.sprite1 = imageLoader(bID, bName);
        Tab.sprite2 = imageLoader(bID2, bName);
        Tab.tooltip = tT;
    }

	
	public static void curses(TextDrawingArea[] TDA) {
		RSInterface tab = addTabInterface(23500);
		
		addSprite(17201, 3, "quickpray");
		addText(17230, "Select your quick prayers:", TDA, 0, 0xFF981F, false, true);
		addTransparentSprite(17229, 0, "quickpray", 50);
		for(int i = 17202, j = 630; i <= 17228 || j <= 656; i++, j++) {
			addConfigButton(i, 17200, 2, 1, "quickpray", 14, 15, "Select", 0, 1, j);
		}
		addHoverButton(17231, "quickpray", 4, 190, 24, "Confirm Selection", -1, 17232, 1);
		addHoveredButton(17232, "quickpray", 5, 190, 24, 17233);
		
		int frame = 0;
		setChildren(46, tab);
		setBounds(21358, 5, 8+20, frame++, tab);
		setBounds(21360, 44, 8+20, frame++, tab);
		setBounds(21362, 79, 11+20, frame++, tab);
		setBounds(21364, 116, 10+20, frame++, tab);
		setBounds(21366, 153, 9+20, frame++, tab);
		setBounds(21368, 5, 48+20, frame++, tab);
		setBounds(21370, 44, 47+20, frame++, tab);
		setBounds(21372, 79, 49+20, frame++, tab);	
		setBounds(21374, 116, 50+20, frame++, tab);
		setBounds(21376, 154, 50+20, frame++, tab);
		setBounds(21378, 4, 84+20, frame++, tab);
		setBounds(21380, 44, 87+20, frame++, tab);
		setBounds(21382, 81, 85+20, frame++, tab);
		setBounds(21384, 117, 85+20, frame++, tab);
		setBounds(21386, 156, 87+20, frame++, tab);
		setBounds(21388, 5, 125+20, frame++, tab);
		setBounds(21390, 43, 124+20, frame++, tab);
		setBounds(21392, 83, 124+20, frame++, tab);
		setBounds(21394, 115, 121+20, frame++, tab);
		setBounds(21396, 154, 124+20, frame++, tab);
		
		setBounds(17229, 0, 25, frame++, tab);//Faded backing
		setBounds(17201, 0, 22, frame++, tab);//Split
		setBounds(17201, 0, 237, frame++, tab);//Split
		
		setBounds(17202, 5-3, 8+17, frame++, tab);
		setBounds(17203, 44-3, 8+17, frame++, tab);
		setBounds(17204, 79-3, 8+17, frame++, tab);
		setBounds(17205, 116-3, 8+17, frame++, tab);
		setBounds(17206, 153-3, 8+17, frame++, tab);
		setBounds(17207, 5-3, 48+17, frame++, tab);
		setBounds(17208, 44-3, 48+17, frame++, tab);
		setBounds(17209, 79-3, 48+17, frame++, tab);
		setBounds(17210, 116-3, 48+17, frame++, tab);
		setBounds(17211, 153-3, 48+17, frame++, tab);
		setBounds(17212, 5-3, 85+17, frame++, tab);
		setBounds(17213, 44-3, 85+17, frame++, tab);
		setBounds(17214, 79-3, 85+17, frame++, tab);
		setBounds(17215, 116-3, 85+17, frame++, tab);
		setBounds(17216, 153-3, 85+17, frame++, tab);
		setBounds(17217, 5-3, 124+17, frame++, tab);
		setBounds(17218, 44-3, 124+17, frame++, tab);
		setBounds(17219, 79-3, 124+17, frame++, tab);
		setBounds(17220, 116-3, 124+17, frame++, tab);
		setBounds(17221, 153-3, 124+17, frame++, tab);
		
		setBounds(17230, 5, 5, frame++, tab);//text	
		setBounds(17231, 0, 237, frame++, tab);//confirm
		setBounds(17232, 0, 237, frame++, tab);//Confirm hover
	}
	
	public static void normals(TextDrawingArea[] TDA) {
		int frame = 0;
		RSInterface tab = addTabInterface(17200);

		setChildren(58, tab);//
		setBounds(5632, 5, 8+20, frame++, tab);
		setBounds(5633, 44, 8+20, frame++, tab);
		setBounds(5634, 79, 11+20, frame++, tab);
		setBounds(19813, 116, 10+20, frame++, tab);
		setBounds(19815, 153, 9+20, frame++, tab);
		setBounds(5635, 5, 48+20, frame++, tab);
		setBounds(5636, 44, 47+20, frame++, tab);
		setBounds(5637, 79, 49+20, frame++, tab);	
		setBounds(5638, 116, 50+20, frame++, tab);
		setBounds(5639, 154, 50+20, frame++, tab);
		setBounds(5640, 4, 84+20, frame++, tab);
		setBounds(19817, 44, 87+20, frame++, tab);
		setBounds(19820, 81, 85+20, frame++, tab);
		setBounds(5641, 117, 85+20, frame++, tab);
		setBounds(5642, 156, 87+20, frame++, tab);
		setBounds(5643, 5, 125+20, frame++, tab);
		setBounds(5644, 43, 124+20, frame++, tab);
		setBounds(13984, 83, 124+20, frame++, tab);
		setBounds(5645, 115, 121+20, frame++, tab);
		setBounds(19822, 154, 124+20, frame++, tab);
		setBounds(19824, 5, 160+20, frame++, tab);
		setBounds(5649, 41, 158+20, frame++, tab);
		setBounds(5647, 79, 163+20, frame++, tab);
		setBounds(5648, 116, 158+20, frame++, tab);
		setBounds(19826, 161, 160+20, frame++, tab);
		setBounds(19828, 4, 207+12, frame++, tab);
		
		setBounds(17229, 0, 25, frame++, tab);//Faded backing
		setBounds(17201, 0, 22, frame++, tab);//Split
		setBounds(17201, 0, 237, frame++, tab);//Split
		
		setBounds(17202, 5-3, 8+17, frame++, tab);
		setBounds(17203, 44-3, 8+17, frame++, tab);
		setBounds(17204, 79-3, 8+17, frame++, tab);
		setBounds(17205, 116-3, 8+17, frame++, tab);
		setBounds(17206, 153-3, 8+17, frame++, tab);
		setBounds(17207, 5-3, 48+17, frame++, tab);
		setBounds(17208, 44-3, 48+17, frame++, tab);
		setBounds(17209, 79-3, 48+17, frame++, tab);
		setBounds(17210, 116-3, 48+17, frame++, tab);
		setBounds(17211, 153-3, 48+17, frame++, tab);
		setBounds(17212, 5-3, 85+17, frame++, tab);
		setBounds(17213, 44-3, 85+17, frame++, tab);
		setBounds(17214, 79-3, 85+17, frame++, tab);
		setBounds(17215, 116-3, 85+17, frame++, tab);
		setBounds(17216, 153-3, 85+17, frame++, tab);
		setBounds(17217, 5-3, 124+17, frame++, tab);
		setBounds(17218, 44-3, 124+17, frame++, tab);
		setBounds(17219, 79-3, 124+17, frame++, tab);
		setBounds(17220, 116-3, 124+17, frame++, tab);
		setBounds(17221, 153-3, 124+17, frame++, tab);
		setBounds(17222, 5-3, 160+17, frame++, tab);
		setBounds(17223, 44-3, 160+17, frame++, tab);
		setBounds(17224, 79-3, 160+17, frame++, tab);
		setBounds(17225, 116-3, 160+17, frame++, tab);
		setBounds(17226, 153-3, 160+17, frame++, tab);
		setBounds(17227, 4-3, 207+4, frame++, tab);
		
		setBounds(17230, 5, 5, frame++, tab);//text	
		setBounds(17231, 0, 237, frame++, tab);//confirm
		setBounds(17232, 0, 237, frame++, tab);//Confirm hover
	}

	private Model method206(int i, int j)
	{
		Model model = (Model) aMRUNodes_264.insertFromCache((i << 16) + j);
		if(model != null)
			return model;
		if(i == 1)
			model = Model.method462(j);
		if(i == 2)
			model = EntityDef.forID(j).method160();
		if(i == 3)
			model = client.myPlayer.method453();
		if(i == 4)
			model = ItemDef.forID(j).method202(50);
		if(i == 5)
			model = null;
		if(model != null)
			aMRUNodes_264.removeFromCache(model, (i << 16) + j);
		return model;
	}

	private static Sprite method207(int i, StreamLoader streamLoader, String s)
	{
		long l = (TextClass.method585(s) << 8) + (long)i;
		Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
		if(sprite != null)
			return sprite;
		try
		{
			sprite = new Sprite(streamLoader, s, i);
			aMRUNodes_238.removeFromCache(sprite, l);
		}
		catch(Exception _ex)
		{
			return null;
		}
		return sprite;
	}
	
	private static Sprite imageLoader(int i, String s) {
		long l = (TextClass.method585(s) << 8) + (long)i;
		Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
		if(sprite != null)
			return sprite;
		try {
			sprite = new Sprite(s+" "+i);
			aMRUNodes_238.removeFromCache(sprite, l);
		} catch(Exception exception) {
			return null;
		}
		return sprite;
	}

	public static void method208(boolean flag, Model model)
	{
		int i = 0;//was parameter
		int j = 5;//was parameter
		if(flag)
			return;
		aMRUNodes_264.unlinkAll();
		if(model != null && j != 4)
			aMRUNodes_264.removeFromCache(model, (j << 16) + i);
	}

	public Model method209(int j, int k, boolean flag)
	{
		Model model;
		if(flag)
			model = method206(anInt255, anInt256);
		else
			model = method206(anInt233, mediaID);
		if(model == null)
			return null;
		if(k == -1 && j == -1 && model.anIntArray1640 == null)
			return model;
		Model model_1 = new Model(true, Class36.method532(k) & Class36.method532(j), false, model);
		if(k != -1 || j != -1)
			model_1.method469();
		if(k != -1)
			model_1.method470(k);
		if(j != -1)
			model_1.method470(j);
		model_1.method479(64, 768, -50, -10, -50, true);
			return model_1;
	}

	public RSInterface()
	{
	}

	public static StreamLoader aClass44;
	public boolean drawsTransparent;
	public Sprite sprite1;
	public int anInt208;
	public Sprite sprites[];
	public static RSInterface interfaceCache[];
	public int requiredValues[];
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
	public int hoverType;
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
	public int valueCompareType[];
	public int anInt246;
	public int spritesY[];
	public String message;
	public boolean isInventoryInterface;
	public int id;
	public int invStackSizes[];
	public int inv[];
	public byte opacity;
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
	public boolean interfaceShown;
	public int height;
	public boolean textShadow;
	public int modelZoom;
	public int modelRotation1;
	public int modelRotation2;
	public int childY[];


public static TextDrawingArea[] fonts;
	
	public static Sprite[] combatIcons = new Sprite[12];

    public void totalChildren(int id, int x, int y)
    { children = new int[id]; childX = new int[x]; childY = new int[y]; }

    public static void removeSomething(int id)
    { RSInterface rsi = interfaceCache[id] = new RSInterface(); }
	
	public void specialBar(int id) {
        /*addActionButton(ID, SpriteOFF, SpriteON, Width, Height, "SpriteText");*/
            addActionButton(id-12, 11, 150, 26, "Use @gre@Special Attack");
        /*removeSomething(ID);*/
        for (int i = id-11; i < id; i++)
            removeSomething(i);

        RSInterface rsi = interfaceCache[id-12];
        rsi.width = 150;
        rsi.height = 26;

        rsi = interfaceCache[id];
        rsi.width = 150;
        rsi.height = 26;

        rsi.child(0, id-12, 0, 0);

        rsi.child(12, id+1, 3, 7);

        rsi.child(23, id+12, 16, 8);

        for (int i = 13; i < 23; i++) {
            rsi.childY[i] -= 1;
        }

        rsi = interfaceCache[id+1];
        rsi.type = 5;
        rsi.sprite1 = combatIcons[0];

        for (int i = id+2; i < id+12; i++) {
			rsi = interfaceCache[i];
            rsi.type = 5;
        }

        sprite1(id+2, 1);sprite1(id+3, 2);
        sprite1(id+4, 3);sprite1(id+5, 4);
        sprite1(id+6, 5);sprite1(id+7, 6);
        sprite1(id+8, 7);sprite1(id+9, 8);
        sprite1(id+10, 9);sprite1(id+11, 10);
    }

    public static void Sidebar0(TextDrawingArea[] tda)
    {
        /*Sidebar0a(id, id2, id3, "text1", "text2", "text3", "text4", str1x, str1y, str2x, str2y, str3x, str3y, str4x, str4y, img1x, img1y, img2x, img2y, img3x, img3y, img4x, img4y, tda);*/
            Sidebar0a(1698, 1701, 7499, "Chop", "Hack", "Smash", "Block", 42, 75, 127, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(2276, 2279, 7574, "Stab", "Lunge", "Slash", "Block", 43, 75, 124, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(2423, 2426, 7599, "Chop", "Slash", "Lunge", "Block", 42, 75, 125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(3796, 3799, 7624, "Pound", "Pummel", "Spike", "Block", 39, 75, 121, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(4679, 4682, 7674, "Lunge", "Swipe", "Pound", "Block", 40, 75, 124, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(4705, 4708, 7699, "Chop", "Slash", "Smash", "Block", 42, 75, 125, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(5570, 5573, 7724, "Spike", "Impale", "Smash", "Block", 41, 75, 123, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0a(7762, 7765, 7800, "Chop", "Slash", "Lunge", "Block", 42, 75, 125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103, tda);
        /*Sidebar0b(id, id2, "text1", "text2", "text3", "text4", str1x, str1y, str2x, str2y, str3x, str3y, str4x, str4y, img1x, img1y, img2x, img2y, img3x, img3y, img4x, img4y, tda);*/
            Sidebar0b(776, 779, "Reap", "Chop", "Jab", "Block", 42, 75, 126, 75, 46, 128, 125, 128, 122, 103, 122, 50, 40, 103, 40, 50, tda);
        /*Sidebar0c(id, id2, id3, "text1", "text2", "text3", str1x, str1y, str2x, str2y, str3x, str3y, img1x, img1y, img2x, img2y, img3x, img3y, tda);*/
            Sidebar0c(425, 428, 7474, "Pound", "Pummel", "Block", 39, 75, 121, 75, 42, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(1749, 1752, 7524, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(1764, 1767, 7549, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(4446, 4449, 7649, "Accurate", "Rapid", "Longrange", 33, 75, 125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(5855, 5857, 7749, "Punch", "Kick", "Block", 40, 75, 129, 75, 42, 128, 40, 50, 122, 50, 40, 103, tda);
            Sidebar0c(6103, 6132, 6117, "Bash", "Pound", "Block", 43, 75, 124, 75, 42, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(8460, 8463, 8493, "Jab", "Swipe", "Fend", 46, 75, 124, 75, 43, 128, 40, 103, 40, 50, 122, 50, tda);
            Sidebar0c(12290, 12293, 12323, "Flick", "Lash", "Deflect", 44, 75, 127, 75, 36, 128, 40, 50, 40, 103, 122, 50, tda);
		Sidebar0d(328, 331, "Bash", "Pound", "Focus", 42, 66, 39, 101, 41, 136,
				40, 120, 40, 50, 40, 85, tda);

        RSInterface rsi = addInterface(19300);
        /*addToggleButton(id, sprite, config, width, height, wid);*/
            addToggleButton(150, 172, 150, 44, "Auto Retaliate");
        rsi.totalChildren(2, 2, 2);
            rsi.child(0, 19000, 92, 26); //combat level
            rsi.child(1, 150, 21, 153); //auto retaliate

		addText(19000, "Combat level:", tda, 0, 0xff981f, true, false);
    }

    public static void Sidebar0a(int id, int id2, int id3, String text1, String text2, String text3, String text4,
                                               int str1x, int str1y, int str2x, int str2y, int str3x, int str3y, int str4x, int str4y,
                                               int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, int img4x, int img4y, TextDrawingArea[] tda) //4button spec
    {
        RSInterface rsi = addInterface(id); //2423
        /*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+11, text1, tda, 0, 0xff981f, false);
            addText(id2+12, text2, tda, 0, 0xff981f, false);
            addText(id2+13, text3, tda, 0, 0xff981f, false);
            addText(id2+14, text4, tda, 0, 0xff981f, false);
        /*specialBar(ID);*/
            rsi.specialBar(id3); //7599

            rsi.width = 190;
            rsi.height = 261;

        int last = 15; int frame = 0;
        rsi.totalChildren(last, last, last);

            rsi.child(frame, id2+3, 21, 46); frame++; //2429
            rsi.child(frame, id2+4, 104, 99); frame++; //2430
            rsi.child(frame, id2+5, 21, 99); frame++; //2431
            rsi.child(frame, id2+6, 105, 46); frame++; //2432

            rsi.child(frame, id2+7, img1x, img1y); frame++; //bottomright 2433
            rsi.child(frame, id2+8, img2x, img2y); frame++; //topleft 2434
            rsi.child(frame, id2+9, img3x, img3y); frame++; //bottomleft 2435
            rsi.child(frame, id2+10, img4x, img4y); frame++; //topright 2436

            rsi.child(frame, id2+11, str1x, str1y); frame++; //chop 2437
            rsi.child(frame, id2+12, str2x, str2y); frame++; //slash 2438
            rsi.child(frame, id2+13, str3x, str3y); frame++; //lunge 2439
            rsi.child(frame, id2+14, str4x, str4y); frame++; //block 2440

            rsi.child(frame, 19300, 0, 0); frame++; //stuffs
            rsi.child(frame, id2, 94, 4); frame++; //weapon 2426
            rsi.child(frame, id3, 21, 205); frame++; //special attack 7599

        for (int i = id2+3; i < id2+7; i++) { //2429 - 2433
        rsi = interfaceCache[i];
            rsi.sprite1 = combatBoxes[4];
			rsi.sprite2 = combatBoxes[5];
            rsi.width = 68; rsi.height = 44;
        }
    }
	
	public static Sprite[] combatBoxes = new Sprite[8];

    public static void Sidebar0b(int id, int id2, String text1, String text2, String text3, String text4,
                                               int str1x, int str1y, int str2x, int str2y, int str3x, int str3y, int str4x, int str4y,
                                               int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, int img4x, int img4y, TextDrawingArea[] tda) //4button nospec
    {
        RSInterface rsi = addInterface(id); //2423
        /*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+11, text1, tda, 0, 0xff981f, false);
            addText(id2+12, text2, tda, 0, 0xff981f, false);
            addText(id2+13, text3, tda, 0, 0xff981f, false);
            addText(id2+14, text4, tda, 0, 0xff981f, false);

            rsi.width = 190;
            rsi.height = 261;

        int last = 14; int frame = 0;
        rsi.totalChildren(last, last, last);

            rsi.child(frame, id2+3, 21, 46); frame++; //2429
            rsi.child(frame, id2+4, 104, 99); frame++; //2430
            rsi.child(frame, id2+5, 21, 99); frame++; //2431
            rsi.child(frame, id2+6, 105, 46); frame++; //2432

            rsi.child(frame, id2+7, img1x, img1y); frame++; //bottomright 2433
            rsi.child(frame, id2+8, img2x, img2y); frame++; //topleft 2434
            rsi.child(frame, id2+9, img3x, img3y); frame++; //bottomleft 2435
            rsi.child(frame, id2+10, img4x, img4y); frame++; //topright 2436

            rsi.child(frame, id2+11, str1x, str1y); frame++; //chop 2437
            rsi.child(frame, id2+12, str2x, str2y); frame++; //slash 2438
            rsi.child(frame, id2+13, str3x, str3y); frame++; //lunge 2439
            rsi.child(frame, id2+14, str4x, str4y); frame++; //block 2440

            rsi.child(frame, 19300, 0, 0); frame++; //stuffs
            rsi.child(frame, id2, 94, 4); frame++; //weapon 2426

        for (int i = id2+3; i < id2+7; i++) { //2429 - 2433
        rsi = interfaceCache[i];
            rsi.sprite1 = combatBoxes[4];
			rsi.sprite2 = combatBoxes[5];
            rsi.width = 68; rsi.height = 44;
        }
    }

    public static void Sidebar0c(int id, int id2, int id3, String text1, String text2, String text3,
                                               int str1x, int str1y, int str2x, int str2y, int str3x, int str3y,
                                               int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, TextDrawingArea[] tda) //3button spec
    {
        RSInterface rsi = addInterface(id); //2423
        /*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+9, text1, tda, 0, 0xff981f, false);
            addText(id2+10, text2, tda, 0, 0xff981f, false);
            addText(id2+11, text3, tda, 0, 0xff981f, false);
        /*specialBar(ID);*/
            rsi.specialBar(id3); //7599

            rsi.width = 190;
            rsi.height = 261;

        int last = 12; int frame = 0;
        rsi.totalChildren(last, last, last);

            rsi.child(frame, id2+3, 21, 99); frame++;
            rsi.child(frame, id2+4, 105, 46); frame++;
            rsi.child(frame, id2+5, 21, 46); frame++;

            rsi.child(frame, id2+6, img1x, img1y); frame++; //topleft
            rsi.child(frame, id2+7, img2x, img2y); frame++; //bottomleft
            rsi.child(frame, id2+8, img3x, img3y); frame++; //topright

            rsi.child(frame, id2+9, str1x, str1y); frame++; //chop
            rsi.child(frame, id2+10, str2x, str2y); frame++; //slash
            rsi.child(frame, id2+11, str3x, str3y); frame++; //lunge

            rsi.child(frame, 19300, 0, 0); frame++; //stuffs
            rsi.child(frame, id2, 94, 4); frame++; //weapon
            rsi.child(frame, id3, 21, 205); frame++; //special attack 7599

        for (int i = id2+3; i < id2+6; i++) {
        rsi = interfaceCache[i];
            rsi.sprite1 = combatBoxes[4];
			rsi.sprite2 = combatBoxes[5];
            rsi.width = 68; rsi.height = 44;
        }
    }

	public static void Sidebar0d(int id, int id2, String text1, String text2,
			String text3, int str1x, int str1y, int str2x, int str2y,
			int str3x, int str3y, int img1x, int img1y, int img2x, int img2y,
			int img3x, int img3y, TextDrawingArea[] tda) // 3button nospec (magic intf)
	{
		RSInterface rsi = addInterface(id); // 2423
		/* addText(ID, "Text", tda, Size, Colour, Centered); */
		addText(id2, "-2", tda, 3, 0xff981f, true); // 2426
		addText(id2 + 9, text1, tda, 0, 0xff981f, false);
		addText(id2 + 10, text2, tda, 0, 0xff981f, false);
		addText(id2 + 11, text3, tda, 0, 0xff981f, false);

		// addText(353, "Spell", tda, 0, 0xff981f, false);
		removeSomething(353);
		addText(354, "Spell", tda, 0, 0xff981f, false);

		addCacheSprite(337, 19, 0, "combaticons");
		addCacheSprite(338, 13, 0, "combaticons2");
		addCacheSprite(339, 14, 0, "combaticons2");

		/* addToggleButton(id, sprite, config, width, height, tooltip); */
		// addToggleButton(349, 349, 108, 68, 44, "Select");
		removeSomething(349);
		addToggleButton(350, 350, 108, 68, 44, "Select");

		rsi.width = 190;
		rsi.height = 261;

		int last = 11;
		int frame = 0;
		rsi.totalChildren(last, last, last);

		rsi.child(frame, id2 + 3, 21, 99);
		frame++;
		rsi.child(frame, id2 + 4, 105, 46);
		frame++;
		rsi.child(frame, id2 + 5, 21, 46);
		frame++;

		rsi.child(frame, id2 + 6, 38, 50);
		frame++; // topleft
		rsi.child(frame, id2 + 7, 38, 103);
		frame++; // bottomleft
		rsi.child(frame, id2 + 8, 120, 50);
		frame++; // topright

		rsi.child(frame, id2 + 9, 42, 76);
		frame++; // bash
		rsi.child(frame, id2 + 10, 124, 76);
		frame++; // pound
		rsi.child(frame, id2 + 11, 39, 126);
		frame++; // focus

		rsi.child(frame, 19300, 0, 0);
		frame++; // stuffs
		rsi.child(frame, id2, 94, 4);
		frame++; // weapon

        for (int i = id2 + 3; i < id2 + 6; i++) { //2429 - 2433
			rsi = interfaceCache[i];
            rsi.sprite1 = combatBoxes[4];
			rsi.sprite2 = combatBoxes[5];
            rsi.width = 68; 
			rsi.height = 44;
        }
	}

	public void child(int id, int interID, int x, int y) {
		children[id] = interID;
		childX[id] = x;
		childY[id] = y;
	}

	private static Sprite CustomSpriteLoader(int id, String s) {
        return method207(id, aClass44, s);
    }

    public static void addToggleButton(int id, int button, int setconfig, int width, int height, String s) {
        RSInterface rsi = addInterface(id);
        rsi.sprite1 = combatBoxes[4];
        rsi.sprite2 = combatBoxes[5];
        rsi.requiredValues = new int[1];
        rsi.requiredValues[0] = button;
        rsi.valueCompareType = new int[1];
        rsi.valueCompareType[0] = button;
        rsi.valueIndexArray = new int[1][3];
        rsi.valueIndexArray[0][0] = 5;
        rsi.valueIndexArray[0][1] = setconfig;
        rsi.valueIndexArray[0][2] = 0;
        rsi.atActionType = 4;
        rsi.width = width;
        rsi.hoverType = -1;
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
        rsi.height = height;
        rsi.tooltip = s;
    }

    public static void addToggleButton(int id, int setconfig, int width, int height, String s)
    {
        RSInterface rsi = addInterface(id);
        rsi.sprite1 = combatBoxes[6];
        rsi.sprite2 = combatBoxes[7];
        rsi.requiredValues = new int[1];
        rsi.requiredValues[0] = 1;
        rsi.valueCompareType = new int[1];
        rsi.valueCompareType[0] = 1;
        rsi.valueIndexArray = new int[1][3];
        rsi.valueIndexArray[0][0] = 5;
        rsi.valueIndexArray[0][1] = setconfig;
        rsi.valueIndexArray[0][2] = 0;
        rsi.atActionType = 4;
        rsi.width = width;
        rsi.hoverType = -1;
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
        rsi.height = height;
        rsi.tooltip = s;
    }

    public static RSInterface addInterface(int id)
    {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.id = id;
        rsi.parentID = id;
        rsi.width = 512;
        rsi.height = 334;
        return rsi;
    }

	public static void addText(int id, String text, TextDrawingArea tda[], int idx, int color, boolean center, boolean shadow) {
		RSInterface tab = addTabInterface(id);
		tab.parentID = id;
		tab.id = id;
		tab.type = 4;
		tab.atActionType = 0;
		tab.width = 0;
		tab.height = 11;
		tab.contentType = 0;
		tab.opacity = 0;
		tab.hoverType = -1;
		tab.centerText = center;
		tab.textShadow = shadow;
		tab.textDrawingAreas = tda[idx];
		tab.message = text;
		tab.aString228 = "";
		tab.textColor = color;
		tab.anInt219 = 0;
		tab.anInt216 = 0;
		tab.anInt239 = 0;	
	}

    public static void addText(int id, String text, TextDrawingArea tda[], int idx, int color, boolean centered) { 
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        if(centered)
			rsi.centerText = true;
        rsi.textShadow = true;
        rsi.textDrawingAreas = tda[idx];
        rsi.message = text;
        rsi.textColor = color;
        rsi.id = id;
        rsi.type = 4;
    }
	
	

    public static void textColor(int id, int color)
    { RSInterface rsi = interfaceCache[id]; rsi.textColor = color; }

    public static void textSize(int id, TextDrawingArea tda[], int idx)
    { RSInterface rsi = interfaceCache[id]; rsi.textDrawingAreas = tda[idx]; }

    public static void addCacheSprite(int id, int sprite1, int sprite2, String sprites) {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.sprite1 = method207(sprite1, aClass44, sprites);
        rsi.sprite2 = method207(sprite2, aClass44, sprites);
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
    }
	
	public static void addCacheSprite(int id) {
		RSInterface rsi = interfaceCache[id] = new RSInterface();
		rsi.sprite1 = combatBoxes[4];
		rsi.sprite2 = combatBoxes[5];
	}
    
    public static void sprite1(int id, int sprite) {
		RSInterface class9 = interfaceCache[id];
        class9.sprite1 = combatIcons[sprite];
    }

	


	public static RSInterface addTabInterface(int id) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 0;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 700;
		tab.opacity = (byte)0;
		tab.hoverType = -1;
		return tab;
	}

	public String popupString;

    public static void addActionButton(int id, int sprite, int width, int height, String s)
    {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.sprite1 = combatIcons[11];
        rsi.tooltip = s;
        rsi.contentType = 0;
        rsi.atActionType = 1;
        rsi.width = width;
        rsi.hoverType = 52;
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
        rsi.height = height;
    }

    public static void addActionBox(int id, int sprite, int width, int height, String s)
    {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.sprite1 = combatBoxes[sprite];
        rsi.sprite2 = combatBoxes[sprite + 1];
        rsi.tooltip = s;
        rsi.contentType = 0;
        rsi.atActionType = 1;
        rsi.width = width;
        rsi.hoverType = 52;
        rsi.parentID = id;
        rsi.id = id;
        rsi.type = 5;
        rsi.height = height;
    }

public static void addText(int i, String s, int k, boolean l, boolean m, int a, int j) {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.parentID = i;
        rsinterface.id = i;
        rsinterface.type = 4;
        rsinterface.atActionType = 0;
        rsinterface.width = 0;
        rsinterface.height = 0;
        rsinterface.contentType = 0;
        rsinterface.opacity = 0;
        rsinterface.hoverType = a;
        rsinterface.centerText = l;
        rsinterface.textShadow = m;
        rsinterface.textDrawingAreas = RSInterface.fonts[j];
        rsinterface.message = s;
        rsinterface.textColor = k;
    }
	public static void addText2(int i, String s,int k, boolean l, boolean m, int a,TextDrawingArea[] TDA, int j) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.parentID = i;
		RSInterface.id = i;
		RSInterface.type = 4;
		RSInterface.atActionType = 0;
		RSInterface.width = 0;
		RSInterface.height = 0;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = a;
		RSInterface.centerText = l;
		RSInterface.textShadow = m;
		RSInterface.textDrawingAreas = TDA[j];
		RSInterface.message = s;
		RSInterface.aString228 = "";
		RSInterface.textColor = k;
	}
	public static void addText2(int i, String s,int k, boolean l, boolean m, int a,TextDrawingArea[] TDA, int j, int dsc) {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.parentID = i;
        rsinterface.id = i;
        rsinterface.type = 4;
        rsinterface.atActionType = 1;
        rsinterface.width = 174;
        rsinterface.height = 11;
        rsinterface.contentType = 0;
//        rsinterface.aByte254 = 0;
//        rsinterface.mOverInterToTrigger = a;
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
	private static Sprite loadSprite(int i, String s) {
		return CustomSpriteLoader(i, s);
	}

	public Sprite loadSprite(String s, int i) {
		return CustomSpriteLoader(i, s);
	}

	public static void addLunarSprite(int i, int j, String name) {
		RSInterface RSInterface = addTabInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.type = 5;
		RSInterface.atActionType = 0;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = 52;
		RSInterface.sprite1 = loadSprite(j, name);
		RSInterface.width = 500;
		RSInterface.height = 500;
		RSInterface.tooltip = "";
	}

	public static void drawRune(int i, int id, String runeName) {
		RSInterface RSInterface = addTabInterface(i);
		RSInterface.type = 5;
		RSInterface.atActionType = 0;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = 52;
		RSInterface.sprite1 = loadSprite(id - 1, "lunarrune");
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
		rsInterface.opacity = 0;
		rsInterface.hoverType = -1;
		rsInterface.valueCompareType = new int[1];
		rsInterface.requiredValues = new int[1];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = runeAmount;
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
		RSInterface.opacity = 0;
		RSInterface.hoverType = 30001;
		RSInterface.sprite1 = loadSprite(37, "magicon2");
		RSInterface.width = 20;
		RSInterface.height = 20;
		RSInterface hover = addTabInterface(30001);
		hover.hoverType = -1;
		hover.interfaceShown = true;
		setChildren(1, hover);
		addLunarSprite(30002, 5, "lunarbox");
		setBounds(30002, 0, 0, 0, hover);
	}

	public static void addLunar2RunesSmallBox(int ID, int r1, int r2, int ra1, int ra2, int rune1, int lvl, String name, String descr, TextDrawingArea[] TDA, int sid, int suo, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.hoverType = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast On";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[3];
		rsInterface.requiredValues = new int[3];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = lvl;
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
		rsInterface.sprite2 = loadSprite(sid, "lunaron");
		rsInterface.sprite1 = loadSprite(sid, "lunaroff");
		RSInterface hover = addTabInterface(ID + 1);
		hover.hoverType = -1;
		hover.interfaceShown = true;
		setChildren(7, hover);
		addLunarSprite(ID + 2, 0, "lunarbox");
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
		rsInterface.hoverType = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[4];
		rsInterface.requiredValues = new int[4];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = ra3;
		rsInterface.valueCompareType[3] = 3;
		rsInterface.requiredValues[3] = lvl;
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
		rsInterface.sprite2 = loadSprite(sid, "lunaron");
		rsInterface.sprite1 = loadSprite(sid, "lunaroff");
		RSInterface hover = addTabInterface(ID + 1);
		hover.hoverType = -1;
		hover.interfaceShown = true;
		setChildren(9, hover);
		addLunarSprite(ID + 2, 0, "lunarbox");
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
		rsInterface.hoverType = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[4];
		rsInterface.requiredValues = new int[4];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = ra3;
		rsInterface.valueCompareType[3] = 3;
		rsInterface.requiredValues[3] = lvl;
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
		rsInterface.sprite2 = loadSprite(sid, "lunaron");
		rsInterface.sprite1 = loadSprite(sid, "lunaroff");
		RSInterface hover = addTabInterface(ID + 1);
		hover.hoverType = -1;
		hover.interfaceShown = true;
		setChildren(9, hover);
		addLunarSprite(ID + 2, 1, "lunarbox");
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
		rsInterface.hoverType = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[4];
		rsInterface.requiredValues = new int[4];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = ra3;
		rsInterface.valueCompareType[3] = 3;
		rsInterface.requiredValues[3] = lvl;
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
		rsInterface.sprite2 = loadSprite(sid, "lunaron");
		rsInterface.sprite1 = loadSprite(sid, "lunaroff");
		RSInterface hover = addTabInterface(ID + 1);
		hover.interfaceShown = true;
		hover.hoverType = -1;
		setChildren(9, hover);
		addLunarSprite(ID + 2, 2, "lunarbox");
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
		constructLunar();
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
		RSInterface Interface = addTabInterface(29999);
		int[] LunarIDs = { 30000, 30017, 30025, 30032, 30040, 30048, 30056, 30064, 30075, 30083, 30091, 30099, 30106, 30114, 30122, 30130, 30138, 30146, 30154, 30162, 30170, 30178, 30186, 30194, 30202, 30210, 30218, 30226, 30234, 30242, 30250, 30258, 30266, 30274, 30282, 30290, 30298, 30306, 30314, 30322, 30001, 30018, 30026, 30033, 30041, 30049, 30057, 30065, 30076, 30084, 30092, 30100, 30107, 30115, 30123, 30131, 30139, 30147, 30155, 30163, 30171, 30179, 30187, 30195, 30203, 30211, 30219, 30227, 30235, 30243, 30251, 30259, 30267, 30275, 30283, 30291, 30299, 30307, 30323, 30315};
		int[] LunarX = { 11, 40, 71, 103, 133, 162, 8, 41, 71, 103, 134, 165, 12, 42, 71, 103, 135, 165, 14, 42, 71, 101, 135, 168, 10, 42, 74, 103, 135, 164, 10, 42, 71, 103, 138, 162, 13, 42, 69, 104, 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
		int[] LunarY = { 10, 9, 12, 10, 12, 10, 38, 39, 39, 39, 39, 37, 68, 68, 66, 68, 68, 68, 97, 97, 97, 97, 98, 98, 126, 124, 125, 125, 125, 126, 155, 155, 155, 155, 155, 155, 185, 185, 183, 184, 184, 176, 176, 163, 176, 176, 176, 176, 163, 176, 176, 176, 176, 163, 176, 163, 163, 163, 176, 176, 176, 163, 176, 149, 176, 163, 163, 176, 149, 176, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
		Interface.totalChildren(LunarIDs.length);
		for(int index = 0; index < LunarIDs.length; index++) {
			Interface.child(index, LunarIDs[index], LunarX[index], LunarY[index]);
		}
	}

}