package server.model.players;

import server.Server;
public class DialogueHandler {


	private Client c;
	
	public DialogueHandler(Client client) {
		this.c = client;
	}
	
	/**
	 * Handles all talking
	 * @param dialogue The dialogue you want to use
	 * @param npcId The npc id that the chat will focus on during the chat
	 */
	public void sendDialogues(int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch(dialogue) {
		case 165:
	sendNpcChat4("Hi there, do you like to vote for our community?","Its free and you can do this every 24 hours","Plus you will receive membership status","and you will receive 10m of cash and 100 WoH Points", c.talkingNpc, "Captain Donnie");
	c.nextChat = 166;
	break;
	case 166:
	sendNpcChat4("But you will be logged out so we can transfer to gifts to","your account. You can find your received gifts into your","inventory or bank after you have voted and logged","back in on your account", c.talkingNpc, "Captain Donnie");
	c.nextChat = 167;
break;
		case 167:
			sendOption2("Yes i would like to vote",  "No thanks");
			c.dialogueAction = 167;
			c.nextChat = 0;	
			break;
				case 114:
		sendStatement("This lever will teleport you to a location high in the wilderness.");
		c.dialogueAction = 9985;
		c.nextChat = 115;
		break;
		
		case 115:
		sendStatement("Are you sure you want to be teleported?");
		c.dialogueAction = 9986;
		c.nextChat = 116;
		break;
		
		case 116:
		sendOption2("Yes", "No");
		c.teleAction = 874;
		c.nextChat = 0;
		break;
		  case 55:
   sendStartInfo("", "You find a casket!", "", "", "");
   break;
  case 56:
   sendStartInfo("", "You've found another clue scroll!", "", "", "");
   break;
  case 57:
   sendNpcChat2("Well done!", "-", 1294, "Brundt the Chieftain");
   break;
			case 568:
			sendNpcChat4("Hello! I will explain how to use player-owned shops.",
						"To view another person's shop, right click them in a",
						"safe zone, and hit \"View Shop Playername\". this will",
						"open their shop, where you may buy items directly.",
						496,
						"Banker");
			c.nextChat = 569;
		break;
		//Dungeoneering
		case 100:
			sendOption4("Floor 1 - Level 1","Floor 2 - Level 60","Dungeoneering Statistics","Dungeoneering Shop");
			c.dialogueAction = 90;
			c.teleAction = -1;
		break;

		case 125:
			sendOption2("Re-do Dungeon", "Quit Dungeon");
			c.dialogueAction = 1327;
		break;
		//End
		//Dicing
		case 103:
			sendNpcChat2("Sorry it seems that you already have a Dice Bag bought",
						"Please recheck your bank or inventory",
						496,
						"Ali Morrisane");
			c.nextChat = 0;
		break;
		case 104:
			sendNpcChat2("Hi do you want to buy a dice bag?",
						"You can dice with these! You want to buy a bag for 1M?",
						496,
						"Ali Morrisane");
			c.nextChat = 105;
		break;
		case 105:
			sendOption2("Yes i would like to buy one",  "No thanks");
			c.dialogueAction = 105;
			c.nextChat = 0;	
			break;
		case 106:
				sendOption5("One 6-sided die", "Two 6-sided dice", "One 4-sided die", "One 8-sided die", "More...");
				c.dialogueAction = 106;
				c.teleAction = 0;
				c.nextChat = 0;
				break;

			case 107:
				sendOption5("One 10-sided die", "One 12-sided die", "One 20-sided die", "Two 10-sided dice for 1-100", "Back...");
				c.dialogueAction = 107;
				c.teleAction = 0;
				c.nextChat = 0;
				break;
		case 569:
			sendNpcChat4("To view, and stock your own shop, type ::shop",
						"This will open your shop. You can right click and sell",
						"items from your inventory to the shop. You will then",
						"be asked to enter a price, per item.",
						496,
						"Banker");
			c.nextChat = 570;
		break;
		case 570:
			sendNpcChat4("To remove an item from your shop, use ::shop and",
						"Right click an item in the shop and select buy.",
						"This will place the item in your inventory and",
						"remove it from your shop.",
						496,
						"Banker");
			c.nextChat = 571;
		break;
		case 571:
			sendNpcChat4("When a player buys an item in your store, you will",
						"be informed. To collect money, use the ::collect",
						"command. Your shop and collection money is saved",
						"so you can logout and login. Have fun!",
						496,
						"Banker");
			c.nextChat = 0;
		break;
case 576:
			sendOption4("Learn how to use my shop", "Manage my own shop", "No thanks", "");
			c.dialogueAction = 82;
		break;
		//End Player Owned Shops
		case 200:
			sendNpcChat4("Hello there "+c.playerName+"!"," I have the ability to reset your combat stats for free!","But remember, this is irreversable!","What would you like me to do?", c.talkingNpc, "Xp Reseter");
			c.nextChat = 210;
		break;
		case 210:
			sendOption4("Reset Strength", "Reset Prayer", "Reset Attack", "Reset All Combat Stats");
			c.dialogueAction = 42;
		break;
		case 230:
			sendNpcChat2("Congratulations!", "Your Strength has been completely reset!",c.talkingNpc, "Xp Reseter");
			int skilld = 2;
					int leveld = 1;
					c.playerXP[skilld] = c.getPA().getXPForLevel(leveld)+2;
					c.playerLevel[skilld] = c.getPA().getLevelForXP(c.playerXP[skilld]);
					c.getPA().refreshSkill(skilld);
			
			c.nextChat = 0;
		break;
		case 240:
			sendNpcChat2("Congratulations!", "Your attack has been completely reset!",c.talkingNpc, "Xp Reseter");
			int skill = 0;
					int levela = 1;
					c.playerXP[skill] = c.getPA().getXPForLevel(levela)+5;
					c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
					c.getPA().refreshSkill(skill);
			c.nextChat = 0;
		break;
		case 456:
                     	sendNpcChat2("Hey, please type ::vote", "Each 24 hours to help the server!",c.talkingNpc, "Please type ::vote each 24 hours!");
		break;
		case 250:
			sendNpcChat2("Congratulations!", "Your combat stats have been completely reset!",c.talkingNpc, "Xp Reseter");
			int skill1 = 0;
					int level = 1;
					c.playerXP[skill1] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill1] = c.getPA().getLevelForXP(c.playerXP[skill1]);
					c.getPA().refreshSkill(skill1);
					int skill2 = 1;
				//	int level = 1;
					c.playerXP[skill2] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill2] = c.getPA().getLevelForXP(c.playerXP[skill2]);
					c.getPA().refreshSkill(skill2);
					int skill3 = 2;
				//	int level = 1;
					c.playerXP[skill3] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill3] = c.getPA().getLevelForXP(c.playerXP[skill3]);
					c.getPA().refreshSkill(skill3);
					int skill4 = 3;
					level = 10;
					c.playerXP[skill4] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill4] = c.getPA().getLevelForXP(c.playerXP[skill4]);
					c.getPA().refreshSkill(skill4);
					int skill5 = 4;
					level = 1;
					c.playerXP[skill5] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill5] = c.getPA().getLevelForXP(c.playerXP[skill5]);
					c.getPA().refreshSkill(skill5);
					int skill6 = 5;
				//	int level = 1;
					c.playerXP[skill6] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill6] = c.getPA().getLevelForXP(c.playerXP[skill6]);
					c.getPA().refreshSkill(skill6);
					int skill7 = 6;
				//	int level = 1;
					c.playerXP[skill7] = c.getPA().getXPForLevel(level)+5;
					c.playerLevel[skill7] = c.getPA().getLevelForXP(c.playerXP[skill7]);
					c.getPA().refreshSkill(skill7);
			c.nextChat = 0;
		break;
		case 260:
			sendNpcChat2("Congratulations!","Your prayer have been completely reset!",c.talkingNpc, "Xp Reseter");
			int skillp = 5;
					int levelp = 1;
					c.playerXP[skillp] = c.getPA().getXPForLevel(levelp)+5;
					c.playerLevel[skillp] = c.getPA().getLevelForXP(c.playerXP[skillp]);
					c.getPA().refreshSkill(skillp);
			c.nextChat = 0;
		break;
		case 30:
			sendNpcChat4("Congratulations!","You have killed 20 monkeys hope you learned something..", "would you like to escape?","Do not break anymore rules!", c.talkingNpc, "Mosol Rei");
			c.dialogueAction = 26;
			c.nextChat = 31;
			break;
		case 31:
			sendOption2("Yes get me out of this fucking hell hole!",  "Hell no! I love it here, I'm nuts for these monkeys!");
			c.dialogueAction = 27;
			c.nextChat = 0;	
			break;
		case 50:
			sendOption2("Ardougne - 1vs1",  "Fally - Multi");
			c.dialogueAction = 50;
			break;
		case 32:
			sendNpcChat4("You cannot Escape yet!","You've killed "+c.monkeyk0ed+" out of 20 monkeys!","Come back when you have killed 20","Kthxbai", c.talkingNpc, "Mosol Rei");
			c.dialogueAction = 30;
			c.nextChat = 0;
			break;
		/*case 100:
			c.sendMessage("To visit strykeworms please go in your spellbook and find Strykeworms teleport (Ancients)!");
			c.sendMessage("To visit strykeworms please go in your spellbook and find Strykeworms teleport (Ancients)!");
			c.sendMessage("To visit strykeworms please go in your spellbook and find Strykeworms teleport (Ancients)!");
			c.sendMessage("To visit strykeworms please go in your spellbook and find Strykeworms teleport (Ancients)!");
			c.sendMessage("To visit strykeworms please go in your spellbook and find Strykeworms teleport (Ancients)!");
			c.sendMessage("To visit strykeworms please go in your spellbook and find Strykeworms teleport (Ancients)!");
			c.sendMessage("To visit strykeworms please go in your spellbook and find Strykeworms teleport (Ancients)!");
		break;*/
		case 0:
			c.talkingNpc = -1;
			c.getPA().removeAllWindows();
			c.nextChat = 0;
			break;
		case 201:
			sendOption4("Information", "Black Jack","Five", "Maybe later...");
			c.dialogueAction = 100;
			break;

		case 25:
			sendOption4("","Black Jack", "Five","");
			c.dialogueAction = 101;
			break;

		case 21:
			sendNpcChat4("The way we play this game is simple. The way you win is", 
					"You need to get a higher number than me and you win the", 
					"500,000 coins. You need to bet 250,000 coins per round.",
					"If you get over 22 you bust and you lose.", 
					c.talkingNpc, "~ Black Jack ~");
					c.nextChat = 22;
					break;

		case 22:
			sendNpcChat4("", 
					"If i get 22+ I bust and I lose. If you get 21 then you have black", 
					"jack and you win double of what you bet.",
					"", 
					c.talkingNpc, "~ Black Jack ~");
					c.nextChat = 0;
					break;

		case 23:
			sendNpcChat4("This is my own game which I made. It's pretty simple", 
					"and resembles poker but it's a lot different. The aim of this", 
					"game is to get the same number like the random number",
					"You got 2 numbers if both hit the same you win.", 
					c.talkingNpc, "~ Five ~");
					c.nextChat = 24;
					break;
		case 24:
			sendNpcChat4("", 
					"To play this game you need to bet 1,000,000 coins. You", 
					"can win a lot of good items but also lose a lot of cash.",
					"", 
					c.talkingNpc, "~ Five ~");
					c.nextChat = 0;
					break;
		case 1:
			sendStatement("You found a hidden tunnel! Do you want to enter it?");
			c.dialogueAction = 1;
			c.nextChat = 2;
			break;
		case 47130:
			sendStatement("I must slay another " + c.taskAmount + " " + Server.npcHandler.getNpcListName(c.slayerTask) + ".");
			c.nextChat = 0;
			break;
			
		case 45:
			sendNpcChat2("Since you haven't shown me a defender to", "prove your prowess as a warrior.", 4289, "Kamfreena");
			c.nextChat = 46;
			break;
		case 46:
			sendNpcChat3("I'll release some Cyclopes which might drop bronze", "defenders for you to start off with, unless you show me", "another. Have fun in there.", 4289, "Kamfreena");
			c.nextChat = -1;
			break;
		case 47:
			sendNpcChat2("The cyclops will now drop:", "" + c.getWarriorsGuild().getCyclopsDrop126(c) + " defenders.", 4289, "Kamfreena");
			c.nextChat = -1;
			break;
		case 2:
			sendOption2("Yea! I'm fearless!",  "No way! That looks scary!");
			c.dialogueAction = 1;
			c.nextChat = 0;
			break;
		case 3:
			sendNpcChat4("Hello!", "My name is Vannaka and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
			"Would you like a slayer task?", c.talkingNpc, "Vannaka");
			c.nextChat = 4;
		break;
		case 5:
			sendNpcChat4("Hello adventurer...", "My name is Kolodion, the master of this mage bank.", "Would you like to play a minigame in order ", 
						"to earn points towards recieving magic related prizes?", c.talkingNpc, "Kolodion");
			c.nextChat = 6;
		break;
		case 6:
			sendNpcChat4("The way the game works is as follows...", "YOU WILL BE TELEPORTED INTO THE WILDERNESS (LVL 50+!!),", 
			"You must kill mages to recieve points,","redeem points with the chamber guardian.", c.talkingNpc, "Kolodion");
			c.nextChat = 15;
		break;
		case 11:
			sendNpcChat4("Hello!", "My name is Vannaka and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
			"Would you like a slayer task?", c.talkingNpc, "Vannaka");
			c.nextChat = 12;
		break;
		case 12:
			sendOption2("Yes I would like a slayer task.", "No I would not like a slayer task.");
			c.dialogueAction = 5;
		break;
		case 13:
			sendNpcChat4("Hello!", "My name is Vannaka and I am a master of the slayer skill.", "I see I have already assigned you a task to complete.", 
			"Would you like me to give you an easier task?", c.talkingNpc, "Vannaka");
			c.nextChat = 14;
		break;
		case 14:
			sendOption2("Yes I would like an easier task.", "No I would like to keep my task.");
			c.dialogueAction = 6;
		break;
		case 15:
			sendOption2("Yes I would like to play", "No, sounds too dangerous for me.");
			c.dialogueAction = 7;
		break;
		case 16:
			sendOption2("I would like to reset my barrows brothers.", "I would like to fix all my barrows");
			c.dialogueAction = 8;
		break;
		case 17:
			sendOption5("Air", "COMING SOON", "Water", "Earth", "More");
			c.dialogueAction = 10;
			c.dialogueId = 17;
			c.teleAction = -1;
		break;
		case 18:
			sendOption5("COMING SOON", "Body", "Cosmic", "COMING SOON", "More");
			c.dialogueAction = 11;
			c.dialogueId = 18;
			c.teleAction = -1;
		break;
		case 19:
			sendOption5("Nature", "Law", "Death", "COMING SOON", "More");
			c.dialogueAction = 12;
			c.dialogueId = 19;
			c.teleAction = -1;
		break;
		}
	}
	//Shops
	//Player Owned Shops

	
	/*
	 * Information Box
	 */
	
	public void sendStartInfo(String text, String text1, String text2, String text3, String title) {
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(text, 6181);
		c.getPA().sendFrame126(text1, 6182);
		c.getPA().sendFrame126(text2, 6183);
		c.getPA().sendFrame126(text3, 6184);
		c.getPA().sendFrame164(6179);
	}
	
	/*
	 * Options
	 */
	
	private void sendOption(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2470);
	 	c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126("Click here to continue", 2473);
		c.getPA().sendFrame164(13758);
	}	
	
	private void sendOption2(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	private void sendOption3(String s, String s1, String s2) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame126(s2, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	public void sendOption4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame126("Select an Option", 2481);
		c.getPA().sendFrame126(s, 2482);
		c.getPA().sendFrame126(s1, 2483);
		c.getPA().sendFrame126(s2, 2484);
		c.getPA().sendFrame126(s3, 2485);
		c.getPA().sendFrame164(2480);
	}

	public void Kill() {	
		c.nextChat = 999;
		c.getPA().sendFrame126("Dismiss your monster", 2460);
		c.getPA().sendFrame126("Yes", 2461);
		c.getPA().sendFrame126("No", 2462);
		c.getPA().sendFrame164(2459);
	}
	
	public void sendOption5(String s, String s1, String s2, String s3, String s4) {
		c.getPA().sendFrame126("Select an Option", 2493);
		c.getPA().sendFrame126(s, 2494);
		c.getPA().sendFrame126(s1, 2495);
		c.getPA().sendFrame126(s2, 2496);
		c.getPA().sendFrame126(s3, 2497);
		c.getPA().sendFrame126(s4, 2498);
		c.getPA().sendFrame164(2492);
	}

	/*
	 * Statements
	 */
	
	private void sendStatement(String s) { // 1 line click here to continue chat box interface
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}
	
	/*
	 * Npc Chatting
	 */
	
	private void sendNpcChat1(String s) {
		
	}
	
	/*private void sendNpcChat4(String s, String s1, String s2, String s3, int ChatNpc, String name) {
		c.getPA().sendFrame200(4901, 591);
		c.getPA().sendFrame126(name, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4900);
	}
	
	/*
	 * Player Chating Back
	 *
	
	private void sendPlayerChat1(String s) {
		c.getPA().sendFrame200(969, 591);
		c.getPA().sendFrame126(c.playerName, 970);
		c.getPA().sendFrame126(s, 971);
		c.getPA().sendFrame185(969);
		c.getPA().sendFrame164(968);
	}
	
	private void sendPlayerChat2(String s, String s1) {
		c.getPA().sendFrame200(974, 591);
		c.getPA().sendFrame126(c.playerName, 975);
		c.getPA().sendFrame126(s, 976);
		c.getPA().sendFrame126(s1, 977);
		c.getPA().sendFrame185(974);
		c.getPA().sendFrame164(973);
	}

	public void sendNpcChat2(String s, String s1, int ChatNpc, String name) {
		c.getPA().sendFrame200(4888, 591);
		c.getPA().sendFrame126(name, 4889);
		c.getPA().sendFrame126(s, 4890);
		c.getPA().sendFrame126(s1, 4891);
		c.getPA().sendFrame75(ChatNpc, 4888);
		c.getPA().sendFrame164(4887);
	}
	
	public void sendNpcChat3(String s, String s1, String s2, int ChatNpc, String name) {
		c.getPA().sendFrame200(4894, 591);
		c.getPA().sendFrame126(name, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(ChatNpc, 4894);
		c.getPA().sendFrame164(4893);
	}*/
	public void sendNpcChat2(String s, String s1, int ChatNpc, String name) {
		c.getPA().sendFrame200(4888, 9847);
		c.getPA().sendFrame126(name, 4889);
		c.getPA().sendFrame126(s, 4890);
		c.getPA().sendFrame126(s1, 4891);
		c.getPA().sendFrame75(ChatNpc, 4888);
		c.getPA().sendFrame164(4887);
	}
	
	public void sendNpcChat3(String s, String s1, String s2, int ChatNpc, String name) {
		c.getPA().sendFrame200(4894, 9847);	//Was 591
		c.getPA().sendFrame126(name, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(ChatNpc, 4894);
		c.getPA().sendFrame164(4893);
	}
	
	
	private void sendNpcChat4(String s, String s1, String s2, String s3, int ChatNpc, String name) {
		c.getPA().sendFrame200(4901, 9847);
		c.getPA().sendFrame126(name, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4900);
	}
	
	private void sendPlayerChat3(String s, String s1, String s2) {
		c.getPA().sendFrame200(980, 591);
		c.getPA().sendFrame126(c.playerName, 981);
		c.getPA().sendFrame126(s, 982);
		c.getPA().sendFrame126(s1, 983);
		c.getPA().sendFrame126(s2, 984);
		c.getPA().sendFrame185(980);
		c.getPA().sendFrame164(979);
	}

	public void talk(int face, String line1, String line2, String line3, String line4, int npcID) {
		c.getPA().sendFrame200(4901, face);
		c.getPA().sendFrame126(c.getPA().GetNpcName(npcID).replaceAll("_", " "), 4902);
		c.getPA().sendFrame126(""+line1, 4903);
		c.getPA().sendFrame126(""+line2, 4904);
		c.getPA().sendFrame126(""+line3, 4905);
		c.getPA().sendFrame126(""+line4, 4906);
		c.getPA().sendFrame126("Click here to continue", 4907);
		c.getPA().sendFrame75(npcID, 4901);
		c.getPA().sendFrame164(4900);
	}
	
	private void sendPlayerChat4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame200(987, 591);
		c.getPA().sendFrame126(c.playerName, 988);
		c.getPA().sendFrame126(s, 989);
		c.getPA().sendFrame126(s1, 990);
		c.getPA().sendFrame126(s2, 991);
		c.getPA().sendFrame126(s3, 992);
		c.getPA().sendFrame185(987);
		c.getPA().sendFrame164(986);
	}
}
