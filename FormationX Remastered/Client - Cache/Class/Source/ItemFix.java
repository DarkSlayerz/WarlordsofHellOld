import java.io.*;

public class ItemFix extends ItemDef {
  public static void loadFixes(int i){
  ItemDef itemDef = ItemDef.forID(i);
if(i == 15116)
	{
itemDef.actions = new String[5];
itemDef.actions[1] = "Wear";
itemDef.modelID = 51845;
itemDef.modelZoom = 2256;
itemDef.modelRotation1 = 456;
itemDef.modelRotation2 = 513;
itemDef.modelOffset1 = 0;
itemDef.modelOffset2 = 0;
itemDef.maleEquip1 = 51795;
itemDef.femaleEquip1 = 51795;
itemDef.aByte154 = -10;
itemDef.name = "Staff of light";
itemDef.description = "It's a staff of light.";//examine.
}
if(i == 15115)
	{
itemDef.actions = new String[5];
itemDef.actions[1] = "Wear";
itemDef.modelID = 51844;
itemDef.modelZoom = 789;
itemDef.modelRotation1 = 69;
itemDef.modelRotation2 = 1743;
itemDef.modelOffset1 = -4;
itemDef.modelOffset2 = 0;
itemDef.maleEquip1 = 51797;
itemDef.femaleEquip1 = 51819;
itemDef.name = "Full slayer helmet";
itemDef.description = "It's a full slayer helmet.";//examine.
}
if(i == 15001)
	{
itemDef.actions = new String[5];
itemDef.actions[1] = "Wield";
itemDef.modelID = 40915;
itemDef.modelZoom = 1616;
itemDef.modelRotation1 = 396;
itemDef.modelRotation2 = 1050;
itemDef.modelOffset1 = -3;
itemDef.modelOffset2 = 4;
itemDef.maleEquip1 = 40942;
itemDef.femaleEquip1 = 40942;
itemDef.aByte154 = -10;
itemDef.name = "Elysian spirit shield";
itemDef.description = "It's an Elysian spirit shield.";
}
if(i == 15002)
	{
itemDef.actions = new String[5];//menu
itemDef.actions[1] = "Wield";
itemDef.modelID = 40922;//inventory/drop model
itemDef.modelZoom = 1616;//Model Zoom
itemDef.modelRotation1 = 396;//rotation 1
itemDef.modelRotation2 = 1050;//rotation 2
itemDef.modelOffset1 = -3;//model offset 1
itemDef.modelOffset2 = 4;//model offset 2
itemDef.maleEquip1 = 40944;//male wield ModelId
itemDef.femaleEquip1 = 40944;//female wield ModelId
itemDef.aByte154 = -10;
itemDef.name = "Arcane spirit shield";//name
itemDef.description = "It's a Arcane spirit shield.";//name
}
if(i == 15003)
	{
itemDef.actions = new String[5];//menu
itemDef.actions[1] = "Wield";
itemDef.modelID = 40921;//inventory/drop model
itemDef.modelZoom = 1616;//Model Zoom
itemDef.modelRotation1 = 396;//rotation 1
itemDef.modelRotation2 = 1050;//rotation 2
itemDef.modelOffset1 = -3;//model offset 1
itemDef.modelOffset2 = 4;//model offset 2
itemDef.maleEquip1 = 40939;//male wield ModelId
itemDef.femaleEquip1 = 40939;//female wield ModelId
itemDef.aByte154 = -10;
itemDef.name = "Divine spirit shield";//name
itemDef.description = "It's a Divine spirit shield.";//name
}
if(i == 15004)
	{
itemDef.actions = new String[5];
itemDef.actions[1] = "Wield";
itemDef.modelID = 40920;
itemDef.modelZoom = 1616;
itemDef.modelRotation1 = 396;
itemDef.modelRotation2 = 1050;
itemDef.modelOffset1 = -3;
itemDef.modelOffset2 = 4;
itemDef.maleEquip1 = 40940;
itemDef.femaleEquip1 = 40940;
itemDef.aByte154 = -10;
itemDef.name = "Spectral spirit shield";
itemDef.description = "It's a Spectral spirit shield.";
}
if(i == 15005)
	{
itemDef.actions = new String[5];
itemDef.actions[1] = "Wield";
itemDef.modelID = 40913;
itemDef.modelZoom = 1616;
itemDef.modelRotation1 = 396;
itemDef.modelRotation2 = 1050;
itemDef.modelOffset1 = -3;
itemDef.modelOffset2 = 4;
itemDef.maleEquip1 = 40941;
itemDef.femaleEquip1 = 40941;
itemDef.aByte154 = -10;
itemDef.name = "Blessed spirit shield";
itemDef.description = "It's a Blessed spirit shield.";
}
if(i == 15006)
	{
itemDef.actions = new String[5];//menu
itemDef.actions[1] = "Wield";
itemDef.modelID = 40919;//inventory/drop model
itemDef.modelZoom = 1616;//Model Zoom
itemDef.modelRotation1 = 396;//rotation 1
itemDef.modelRotation2 = 1050;//rotation 2
itemDef.modelOffset1 = -3;//model offset 1
itemDef.modelOffset2 = 4;//model offset 2
itemDef.maleEquip1 = 40943;//male wield ModelId
itemDef.femaleEquip1 = 40943;//female wield ModelId
itemDef.aByte154 = -10;
itemDef.name = "Spirit shield";//name
itemDef.description = "It's a Spirit shield.";//name
}
if(i >= 1174 && i <= 1435) {
itemDef.aByte154 = -10;
}
if(i >= 656 && i <= 665) {
itemDef.aByte154 = -10;
}
		switch(itemDef.id) {
			case 11694:
			case 11696:
			case 2651:
			case 1949:
			case 11698:
			case 11700:
			case 2902:
			case 2912:
			case 2922:
			case 2932:
			case 2942:
			case 4151:
			case 11730:
			case 20072:
			case 1017:
			case 656:
			case 1042:
			case 1038:
			case 1040:
			case 1044:
			case 1046:
			case 1048:
			case 1050:
			case 74:
			case 1137:
			case 1037:
			case 1139:
			case 1141:
			case 1143:
			case 1145:
			case 1147:
			case 1149:
			case 1151:
			case 1153:
			case 1155:
			case 1157:
			case 1159:
			case 5608:
			case 1161:
			case 1163:
			case 1165:
			case 4718:
			case 4710:
			case 4726:
			case 4734:
			case 4747:
			case 4755:
			case 9185:
			case 11235:
			case 1167:
			case 1169:
			case 1171:
			case 6629:
			case 2657:
			case 4675:
			case 2665:
			case 2673:
			case 6631:
			case 11283:
			case 4587:
			case 775:
			case 11284:
			case 11285:
			case 837:
			case 839:
			case 841:
			case 843:
			case 845:
			case 847:
			case 849:
			case 851:
			case 853:
			case 855:
			case 857:
			case 859:
			case 1419:
			case 861:
			case 11286:
			case 2758:
			case 6524:
			case 2522:
			case 13902:
			case 13879:
			case 13883:
			case 13867:
			case 6523:
			case 2900:
			case 4225:
			case 4212:
			case 2910:
			case 4235:
			case 2920:
			case 2930:
			case 4224:
			case 3488:
			case 4156:
			case 2659:
			case 2667:
			case 3122:
			case 2940:
			case 2675:
			case 2890:
			case 1173:
			case 580:
			itemDef.aByte154 = -10;
			break;
		}
	}
}