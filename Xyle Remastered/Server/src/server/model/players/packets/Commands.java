package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;
import server.model.players.Player;
import server.util.Misc;
import server.world.WorldMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import server.event.EventManager;
import server.event.Event;
import server.event.EventContainer;


/**
 * Commands
 **/
public class Commands implements PacketType {

	
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
	String playerCommand = c.getInStream().readString();
	if(Config.SERVER_DEBUG)
		Misc.println(c.playerName+" playerCommand: "+playerCommand);
		if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
			if (c.clanId >= 0) {
				System.out.println(playerCommand);
				playerCommand = playerCommand.substring(1);
				Server.clanChat.playerMessageToClan(c.playerId, playerCommand, c.clanId);
			} else {
				if (c.clanId != -1)
					c.clanId = -1;
				c.sendMessage("You are not in a clan.");
			}
			return;
		}
		if(c.playerRights >= 0) {
			if (playerCommand.startsWith("cckick")) {
				String name = playerCommand.substring(7);
				Server.clanChat.kickPlayerFromClan(c,name);
			}
			
			if (playerCommand.startsWith("ccpassword")) {
				String pass = playerCommand.substring(11);
				if(c.clanId == -1) {
					c.clanPass = pass;
				} else {
					Server.clanChat.setClanPassword(c,pass,true);
				}
			}
			
			if (playerCommand.startsWith("ccmute")) {
				String name = playerCommand.substring(7);
				if(c.clanId == -1){
					c.sendMessage("You are not in a clan!");
				} else {
					Server.clanChat.mutePlayer(c,name);
				}
			}
			
			if (playerCommand.equalsIgnoreCase("ccclear")) {
				if(c.clanId != -1) {
					Server.clanChat.setClanPassword(c,null,false);
					c.clanPass = null;
					c.sendMessage("Clan chat passwords have been cleared.");
				} else {
					c.clanPass = null;
					c.sendMessage("Clan chat passwords have been cleared.");
				}
			}
			
			if (playerCommand.equalsIgnoreCase("ccowner")) {
				String name = playerCommand.substring(8);
				Server.clanChat.changeOwner(c,name);
			}

			if (playerCommand.startsWith("ccunmute")) {
				String name = playerCommand.substring(9);
				if(c.clanId == -1){
					c.sendMessage("You are not in a clan!");
				} else {
					Server.clanChat.unmutePlayer(c,name);
				}
			}
				
			if (playerCommand.equalsIgnoreCase("players")) 
				c.sendMessage("There are currently "+PlayerHandler.getPlayerCount()+" players online.");
				
			if (playerCommand.startsWith("changepassword") && playerCommand.length() > 15) {
				c.playerPass = playerCommand.substring(15);
				c.sendMessage("Your password is now: " + c.playerPass);			
			}
			
			if (playerCommand.equalsIgnoreCase("explock")) {
				c.expLock = !c.expLock;
				c.sendMessage("Experience lock " + (c.expLock ? "activated." : "deactivated."));
			}
			if (playerCommand.equals("vote")) {
				c.getPA().sendFrame126("www.runelocus.com/toplist/index.php?action=vote&id=24654", 12000);
			}
			if (playerCommand.equals("list")) {
				c.getPA().sendFrame126("www.itemdb.biz", 12000);
			}
			if (playerCommand.equalsIgnoreCase("commands")) {
				c.sendMessage("To get started, use the ::setlevel (level id) (level) to level up. @red@Type ::list for an item list.");
				c.sendMessage("Type ::master for all 99's in combat skills you can also use ::setlevel id level.");
				c.sendMessage("@blue@~Type ::hybrid for a basic hybrid set.");
				c.sendMessage("@blue@~Type ::brunes for barrage runes and ::vengrunes for vengeance runes.");
				c.sendMessage("@blue@~Type ::pots for a variety of noted potions and ::food for rocktails.");
			}
			/*if (playerCommand.startsWith("shop"))
				c.getShops().openShop(Integer.parseInt(playerCommand.substring(5)));
	*/
			if (playerCommand.startsWith("setlevel")) {
				if (c.inWild())
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.sendMessage("Take your items off before using this command.");
						return;
					}
				}
				String[] args = playerCommand.split(" ");
				int skill = Integer.parseInt(args[1]);
				int level = Integer.parseInt(args[2]);
				if (level > 99)
					level = 99;
				else if (level < 0)
					level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
				c.getPA().requestUpdates();
			}
			
			if (playerCommand.equals("master")) {
				if (c.inWild())
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.sendMessage("Take your items off before using this command.");
						return;
					}
				}
				for (int skill = 0; skill < 7; skill++) {
					c.playerXP[skill] = c.getPA().getXPForLevel(99)+5;
					c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
					c.getPA().refreshSkill(skill);
				}
				c.constitution = 990;
				c.getPA().requestUpdates();
			}
			
			
			if (playerCommand.startsWith("bob")) {
				c.getSummoning().openBoB();
			}

			
			
			/*if (playerCommand.startsWith("restorespecial")) {
				if(!c.inWild() && c.specRestoreTimer < 0) {
					c.specAmount = 10;
					c.specRestoreTimer = 83;
				}
			}*/
			if (playerCommand.equalsIgnoreCase("mypos")) {
				c.sendMessage("X: "+c.absX);
				c.sendMessage("Y: "+c.absY);
				c.sendMessage("mapregionX: " + c.mapRegionX);
				c.sendMessage("mapregionY: " + c.mapRegionY);
			}
			/*if (playerCommand.startsWith("item")) {
				if (c.inWild())
					return;
				String[] args = playerCommand.split(" ");
				int newItemID = Integer.parseInt(args[1]);
				int newItemAmount = Integer.parseInt(args[2]);
				if (args.length == 3) {
					if ((newItemID <= 21000) && (newItemID >= 0)) {
						for (int i = 0; i < Config.UNSPAWNABLE.length; i++) {
							if (c.getItems().getItemName(newItemID).toLowerCase().contains(Config.UNSPAWNABLE[i])) {
								c.sendMessage("This item can not be obtained through spawning, earn it a different way.");
								return;
							}
						}
						c.getItems().addItem(newItemID, newItemAmount);
					} else
						c.sendMessage("No such item.");
				}
			}*/
			if (playerCommand.startsWith("hybrid")) {
				if (c.inWild())
					return;
				int[][] set = {{6737, 1}, {7462, 1}, {10828, 1}, {4712, 1}, {4714, 1}, {4151, 1}, {6585, 1}, {6889, 1}, {4736, 1},
								{4749, 1}, {4751, 1}, {2412, 1}, {555, 800}, {560, 600}, {565, 400},
								{20072, 1}, {6920, 1}, {5698, 1}, {6914, 1}};
				for (int i = 0; i < set.length; i++) 
					c.getItems().addItem(set[i][0], set[i][1]);
			}
			if (playerCommand.startsWith("pots")) {
				if (c.inWild())
					return;
				int[][] set = {{6686, 10000}, {3025, 10000}, {2441, 10000}, {2437, 10000}, {2435, 10000}, {2445, 10000}, {2443, 10000}};
				for (int i = 0; i < set.length; i++) 
					c.getItems().addItem(set[i][0], set[i][1]);
			}
			if (playerCommand.startsWith("food")) {
				if (c.inWild())
					return;
				c.getItems().addItem(15273, 10000);
			}
			if (playerCommand.startsWith("vengrunes")) {
				if (c.inWild())
					return;
				c.getItems().addItem(9075, 1000);
				c.getItems().addItem(557, 1000);
				c.getItems().addItem(560, 1000);
			}
			
			if (playerCommand.startsWith("brunes")) {
				if (c.inWild())
					return;
				c.getItems().addItem(555, 1000);
				c.getItems().addItem(565, 1000);
				c.getItems().addItem(560, 1000);
			}
			if (playerCommand.startsWith("yell") && c.playerRights >= 1) {
				if (!Connection.isMuted(c)) {
					if (playerCommand.substring(5).contains("@")) {
						c.sendMessage("You may not use the symbol '@'.");
						return;
					}
					for (int j = 0; j < Server.playerHandler.players.length; j++) {
						if (Server.playerHandler.players[j] != null) {
							Client c2 = (Client)Server.playerHandler.players[j];
							c2.sendMessage(c.getPA().getYellRank() + " " + c.playerName + ": " + Misc.optimize(playerCommand.substring(5)));
						}
					}
				}
			} else if (playerCommand.startsWith("settag") && c.playerRights >= 1) {
				String tag = playerCommand.substring(7);
				if(tag.length() > 15) {
					c.sendMessage("Your tag can be no longer than 15 characters.");
					return;
				}
				if (tag.contains("@")) {
					c.sendMessage("You may not use the symbol '@' in your tag.");
					return;
				}
				c.donorTag = tag;
				c.sendMessage("You have edited your tag to " + c.donorTag + ".");
			}
		}
		
		if (c.playerRights == 1 || c.playerRights == 2 || c.playerRights == 3) {
			if (playerCommand.startsWith("s")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						if(Server.playerHandler.players[j].playerRights >= 1 && Server.playerHandler.players[j].playerRights != 4) {
							c2.sendMessage("@blu@[STAFF CHAT]" + c.playerName + ": " + Misc.optimize(playerCommand.substring(3)));
						}
					}
				}
			} else if (playerCommand.startsWith("mute")) {
				String playerToBan = playerCommand.substring(5);
				Connection.addNameToMuteList(playerToBan);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Client c2 = (Client)Server.playerHandler.players[i];
							c2.sendMessage("You have been muted by: " + c.playerName);
							break;
						} 
					}
				}			
			} else if (playerCommand.startsWith("ipmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP Muted the user: "+Server.playerHandler.players[i].playerName);
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player is offline.");
				}			
			} else if (playerCommand.startsWith("unipmute")) {
				try {	
					String playerToBan = playerCommand.substring(9);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have Un Ip-Muted the user: "+Server.playerHandler.players[i].playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player is offline.");
				}			
			} else if (playerCommand.startsWith("unmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
				} catch(Exception e) {
					c.sendMessage("Player is offline.");
				}			
			} else if (playerCommand.startsWith("checkbank")) {
				try {
					String[] args = playerCommand.split(" ", 2);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						Client o = (Client) Server.playerHandler.players[i];
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
								c.getPA().otherBank(c, o);
								break;
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline."); 
				}
			} else if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
						}
					}
				}			
			} else if (playerCommand.equals("commands")) {
				c.sendMessage("::mute/unmute/ipmute/unipmute/xteleto player_name");	
			}
		}
		
		
		if(c.playerRights == 3) {
			if (playerCommand.startsWith("item")) {
				if (c.inWild())
					return;
				String[] args = playerCommand.split(" ");
				int newItemID = Integer.parseInt(args[1]);
				int newItemAmount = Integer.parseInt(args[2]);
				if (args.length == 3) {
					if ((newItemID <= 21000) && (newItemID >= 0)) {
						/*for (int i = 0; i < Config.UNSPAWNABLE.length; i++) {
							if (c.getItems().getItemName(newItemID).toLowerCase().contains(Config.UNSPAWNABLE[i])) {
								c.sendMessage("This item can not be obtained through spawning, earn it a different way.");
								return;
							}
						}*/
						c.getItems().addItem(newItemID, newItemAmount);
					} else
						c.sendMessage("No such item.");
				}
			}
						if (playerCommand.startsWith("in")) {
				try {
					String[] args = playerCommand.split(" ");
						String itemName1 = args[1];
						String itemName2 = args[2];
						String itemName3 = args[3];
						String itemName4 = args[4];
						if (args.length == 3) {
							String itemName = itemName1;
							int newItemID = c.getItems().getItemId(itemName);
							int newItemAmount = Integer.parseInt(args[2]);
							if ((newItemID <= 20500) && (newItemID >= 0)) {
								c.getItems().addItem(newItemID, newItemAmount);
							}
						} else if (args.length == 4) {
							String itemName = itemName1+" "+itemName2;
							int newItemID = c.getItems().getItemId(itemName);
							int newItemAmount = Integer.parseInt(args[3]);
							if ((newItemID <= 20500) && (newItemID >= 0)) {
								c.getItems().addItem(newItemID, newItemAmount);	
							} 
							} else if (args.length == 5) {
								String itemName = itemName1+" "+itemName2+" "+itemName3;
								int newItemID = c.getItems().getItemId(itemName);
								int newItemAmount = Integer.parseInt(args[4]);
								if ((newItemID <= 20500) && (newItemID >= 0)) {
									c.getItems().addItem(newItemID, newItemAmount);	
								} 
								} else if (args.length == 6) {
									String itemName = itemName1+" "+itemName2+" "+itemName3+" "+itemName4;
									int newItemID = c.getItems().getItemId(itemName);
									int newItemAmount = Integer.parseInt(args[5]);
									if ((newItemID <= 20500) && (newItemID >= 0)) {
										c.getItems().addItem(newItemID, newItemAmount);	
									} 
									}
						c.sendMessage("You spawn a "+Misc.formatPlayerName(itemName1)+" "+Misc.formatPlayerName(itemName2)+" "+Misc.formatPlayerName(itemName3)+" "+Misc.formatPlayerName(itemName4));
						} catch(Exception e) {
					
				} // HERE?
			}// HERE?
			if (playerCommand.equals("spec")) {
				if (!c.inWild())
					c.specAmount = 1000000.0;
			} else if (playerCommand.startsWith("object")) {
				String[] args = playerCommand.split(" ");				
				c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
			}
			if (playerCommand.equals("zombiestart")) {
					c.getZW().startGame();
			}
			
			if (playerCommand.equals("reloaditems")) {
				for(int i = 0; i < Config.ITEM_LIMIT; i++)
					Server.itemHandler.ItemList[i] = null;
				Server.itemHandler.loadItemList("item.cfg");
				Server.itemHandler.loadItemPrices("prices.txt");
				c.sendMessage("Items reloaded.");
			}
			
			if (playerCommand.equals("reloadnpcs")) {
				for(int i = 0; i < Server.npcHandler.maxNPCs; i++) {
					Server.npcHandler.npcs[i] = null;
				}
				for(int i = 0; i < Server.npcHandler.maxListedNPCs; i++) {
					Server.npcHandler.NpcList[i] = null;
				}
				Server.npcHandler.loadNPCList("./Data/CFG/npc.cfg");
				Server.npcHandler.loadAutoSpawn("./Data/CFG/spawn-config.cfg");
				c.sendMessage("NPCs reloaded.");
			}
			
			if (playerCommand.startsWith("reloaddrops")) {
				Server.npcDrops = null;
				Server.npcDrops = new server.model.npcs.NPCDrops();
			}
			
			if (playerCommand.startsWith("reloadshops")) {
				Server.shopHandler = new server.world.ShopHandler();
			}
			
			if (playerCommand.startsWith("interface")) {
				String[] args = playerCommand.split(" ");
				c.getPA().showInterface(Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("gfx")) {
				String[] args = playerCommand.split(" ");
				c.gfx0(Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("update")) {
				String[] args = playerCommand.split(" ");
				int a = Integer.parseInt(args[1]);
				for (int i = 0; i < Server.playerHandler.players.length; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].inTrade)
							((Client)Server.playerHandler.players[i]).getTradeAndDuel().declineTrade();
						((Client)Server.playerHandler.players[i]).getTradeAndDuel().declineDuel();
					}
				}
				PlayerHandler.updateSeconds = a;
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}
			
			if (playerCommand.equals("massvote")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++)
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.getPA().sendFrame126("http://www.runelocus.com/toplist/index.php?action=vote&id=24654", 12000);
					}
			}

			if (playerCommand.equalsIgnoreCase("debug")) {
				Server.playerExecuted = true;
			}
			
			if(playerCommand.startsWith("www")) {
				c.getPA().sendFrame126(playerCommand,0);			
			}
			
			if (playerCommand.startsWith("tele")) {
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
				else if (arg.length == 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),c.heightLevel);
			}
			
			if (playerCommand.startsWith("teletome")) {
				if (c.inWild())
				return;
				try {	
					String playerToBan = playerCommand.substring(9);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.teleportToX = c.absX;
								c2.teleportToY = c.absY;
								c2.heightLevel = c.heightLevel;
								c.sendMessage("You have teleported " + c2.playerName + " to you.");
								c2.sendMessage("You have been teleported to " + c.playerName + "");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("sendmeat")) {
				try {	
					String playerToBan = playerCommand.substring(9);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.getPA().sendFrame126("www.meatspin.com", 12000);
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
				
			if(playerCommand.startsWith("npc")) {
				int newNPC = Integer.parseInt(playerCommand.substring(4));
				if(newNPC > 0) {
					Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 50, 7, 70, 70, false, false);
					c.sendMessage("You spawn a NPC.");
					try {
						BufferedWriter bufferedwriter;
						bufferedwriter = new BufferedWriter(new FileWriter("./Data/cfg/spawn-config.cfg", true));
						bufferedwriter.write("spawn = "+newNPC+"	"+c.getX()+"	"+c.getY()+"	"+c.heightLevel+"	1	0	0	0	Added by "+c.playerName);
						bufferedwriter.newLine();
						bufferedwriter.flush();
					} catch(Exception e) {
						c.sendMessage("Failed to write to list.");
					}
				} else
					c.sendMessage("No such NPC.");	
			}
			
			if(playerCommand.startsWith("pnpc")) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(5));
					c.npcId2 = newNPC;
					c.getPA().requestUpdates();
				} catch(Exception e) {
				}
			}
			
			if(playerCommand.startsWith("dialogue")) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(9));
					c.talkingNpc = newNPC;
					c.getDH().sendDialogues(11, c.talkingNpc);
				} catch(Exception e) {
				}
			}
			
			if(playerCommand.startsWith("h3h3")) {
				c.getItems().itemOnInterface(10549, 1, 14172);
				for(int i = 0; i < 18000; i++) {
					c.getPA().sendFrame126(Integer.toString(i), i);
					System.out.println(Integer.toString(i));
				}
			}
			
			if (playerCommand.startsWith("getip")) {
				String getPlayerIP = playerCommand.substring(6);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(getPlayerIP))
							c.sendMessage(Server.playerHandler.players[i].playerName+"'s IP is "+Server.playerHandler.players[i].connectedFrom); 
					}
				}
			}
			
			if (playerCommand.startsWith("ipban")) { // use as ::ipban name
				String playerToBan = playerCommand.substring(6);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
							Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
							c.sendMessage("You have IP banned the user: "+Server.playerHandler.players[i].playerName+" with the host: "+Server.playerHandler.players[i].connectedFrom);
							Server.playerHandler.players[i].disconnected = true;
						} 
					}
				}
			}
			
			if (playerCommand.startsWith("ban") && playerCommand.charAt(3) == ' ') { // use as ::ban name
				try {	
					String playerToBan = playerCommand.substring(4);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("unban")) {
				String playerToBan = playerCommand.substring(6);
				Connection.removeNameFromBanList(playerToBan);
				c.sendMessage(playerToBan + " has been unbanned.");
			}
		if (playerCommand.startsWith("setplayer")) {
				try {
			String[] args = playerCommand.split(" ");
			int skill = Integer.parseInt(args[1]);
			int level = Integer.parseInt(args[2]);
			String otherplayer = args[3];
			Client target = null;
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
			if(Server.playerHandler.players[i] != null) {
				if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
				target = (Client)Server.playerHandler.players[i];
				break;
				}
			}
		}
		if (target == null) {
		c.sendMessage("Player doesn't exist or is not currently online.");
		return;
		}		
		c.sendMessage("You have just set one of "+ Misc.ucFirst(target.playerName) +"'s skills.");
		target.sendMessage(""+ Misc.ucFirst(c.playerName) +" has just set one of your skills."); 
		target.playerXP[skill] = target.getPA().getXPForLevel(level)+5;
		target.playerLevel[skill] = target.getPA().getLevelForXP(target.playerXP[skill]);
		target.getPA().refreshSkill(skill);
		} catch(Exception e) {
		c.sendMessage("R U NEW?");
			}            
		}
			
			if (playerCommand.startsWith("kick") && playerCommand.charAt(4) == ' ') { // use as ::ban name
				try {	
					String playerToKick = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToKick)) {
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("anim")) {
				String[] args = playerCommand.split(" ");
				c.startAnimation(Integer.parseInt(args[1]));
				//c.sendMessage(String.format("%, d",Integer.parseInt(args[1])));
				c.getPA().requestUpdates();
			}
			
			if (playerCommand.startsWith("emoteloop")) {
				c.animLoop = !c.animLoop;
				String[] args = playerCommand.split(" ");
				c.animToDo = Integer.parseInt(args[1]);
				final Client player = c;
				EventManager.getSingleton().addEvent(new Event() {
					public void execute(EventContainer o) {
						if (player.animLoop) {
							player.sendMessage("Anim: " + player.animToDo);
							player.startAnimation(65535);
							player.getPA().requestUpdates();
							player.startAnimation(player.animToDo++);
						} else 
							o.stop();
					}
				}, 1000);
			}
		}
	}
}
		
		
		
		
		
		
		

