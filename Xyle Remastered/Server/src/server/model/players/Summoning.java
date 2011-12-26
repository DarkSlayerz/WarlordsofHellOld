package server.model.players;

import java.util.HashMap;

import server.Server;
import server.model.players.packets.ClickingButtons;

/**
 * The Summoning Handler class.
 * @Author: Brandyn
 */
public class Summoning {

	/**
	 * Owner of the summoning instance.
	 */
	public final Client c;

	/**
	 * The owner's summoned familiar.
	 */
	public Familiar summonedFamiliar = null;
	
	/**
	 * Instances the summoning class.
	 */
	public Summoning(Player p) {
		this.c = (Client) p;
	}

	/**
	 * Represents different FamiliarType's.
	 * @Author: Brandyn
	 */
	public static enum FamiliarType {
		NORMAL,
		TELEPORTER,
		COMBAT,
		BOB;
	}

	/**
	 * Familiar special interface.
	 */
	public static interface FamiliarSpecial {
		public abstract void execute(Object... arguements);
	}
	
	/**
	 * Represents different Familiar's.
	 * @Author: Brandyn
	 */
	public static enum Familiar {
		SPIRIT_TERRORBIRD(6794, 12007, 12437, 15, FamiliarType.BOB, 12, 0, false, "Take BoB", "", new FamiliarSpecial() {
				@Override
				public void execute(Object... arguments) {
					/* Special goes here */
				}}), 
		SPIRIT_KYATT(7363, 12812, 12836, 14, FamiliarType.TELEPORTER, 12, 0, false, "Cast @gre@Ambush", "Meow", new FamiliarSpecial() {
				@Override
				public void execute(Object... arguments) {
					/* Special goes here */
				}}), 
		WOLPERTINGER(6869, 12089, 12437, 15, FamiliarType.NORMAL, 0, 40, false, "Cast @gre@Magic Focus", "Derp!", new FamiliarSpecial() {
				@Override
				public void execute(Object... arguments) {
					((Client) arguments[0]).startAnimation(7660);
					((Client) arguments[0]).gfx(1313, 0);
					((Client) arguments[0]).getPotions().enchanceStat(6, false);
					((Client) arguments[0]).sendMessage("Your magic is boosted!");
				}}), 
		WAR_TORTOISE(6815, 12031, 12435, 30, FamiliarType.BOB, 18, 0, true, "Take BoB", "Meow", new FamiliarSpecial() {
				@Override
				public void execute(Object... arguments) {
					/* Special goes here */
				}}),
		PACK_YAK(6873, 12093, 12435, 30, FamiliarType.BOB, 30, 10, true, "Take BoB;Cast @gre@Winter Storage", "Baroo Baroo!", new FamiliarSpecial() {
				@Override
				public void execute(Object... arguments) {
					((Client) arguments[0]).sendMessage(":moi: 24000");
				}});
			
		private Familiar(int npcId, int pouchId, int scrollId, int timeLimit, FamiliarType familiarType, int storeCapacity, int specialEnergyConsumption, boolean large, String clientTooltip, String textChat, FamiliarSpecial familiarSpecial) {
			this.npcId = npcId;
			this.pouchId = pouchId;
			this.scrollId = scrollId;
			this.timeLimit = timeLimit;
			this.familiarType = familiarType;
			this.storeCapacity = storeCapacity;
			this.specialEnergyConsumption = specialEnergyConsumption;
			this.large = large;
			this.clientTooltip = "Dismiss Familiar;Renew Familiar;Call Familiar;" + clientTooltip;
			this.speakText = textChat;
			this.familiarSpecial = familiarSpecial;
		}
			
		public int npcId;
		public int pouchId;
		public int scrollId;
		public int timeLimit;
		public FamiliarType familiarType;
		public int storeCapacity;
		public int specialEnergyConsumption;
		public boolean large;
		public String clientTooltip;
		public String speakText;
		public FamiliarSpecial familiarSpecial;
		
		public static HashMap <Integer, Familiar> familiars = new HashMap<Integer, Familiar>();
		
		public static Familiar forPouchId(int id) {
			return familiars.get(id);
		}
		
		static {
			for (Familiar f : Familiar.values())
				familiars.put(f.pouchId, f);
		}
	}
	
	/**
	 * Summons a familiar based on pouch id
	 * @param itemId - Pouch Id from ActionButton packet.
	 */
	public void summonFamiliar(int itemId, boolean login) {
			Familiar summonedFamiliar = Familiar.forPouchId(itemId);
			if(summonedFamiliar != null && this.summonedFamiliar == null) {
				this.summonedFamiliar = summonedFamiliar;
				c.sendMessage(":summonactive:");
				c.sendMessage(":sumoptions:" + summonedFamiliar.clientTooltip);
				burdenedItems = new int[summonedFamiliar.storeCapacity];
				for(int i = 0; i < burdenedItems.length; i++)
					burdenedItems[i] = 0;
				if(!login) {
					c.specRestoreTimer = (((summonedFamiliar.timeLimit * 60) * 1000) / 600); 
					c.summoned = Server.npcHandler.summonNPC(c, summonedFamiliar.npcId, c.getX(), c.getY() + (summonedFamiliar.large ? 2 : 1), c.heightLevel, 0, 100, 1, 1, 1);
					callFamiliar();
					c.sendMessage("You summon a " + summonedFamiliar.toString() + ".");
					c.getItems().deleteItem(itemId, 1);
				} else {
					loginCycle = 4;
				}
			} else if(this.summonedFamiliar != null && c.summoned != null) {
				c.sendMessage("You already have a familiar!");
			}
		}
	
	/**
	 * Renews a familiar before death.
	 */
	public void renewFamiliar() {
		if(summonedFamiliar != null && this.summonedFamiliar != null) {
			if(c.specRestoreTimer < 300) {
				if(c.getItems().playerHasItem(summonedFamiliar.pouchId, 1)) {
					c.specRestoreTimer = (((summonedFamiliar.timeLimit * 60) * 1000) / 600); 
					c.sendMessage("You sacrifice a pouch to renew your familiar's strength.");
					c.getItems().deleteItem(summonedFamiliar.pouchId, 1);
				} else {
					c.sendMessage("You need a pouch to sacrifice!");
				}
			} else {
				c.sendMessage("You still have excess time left on your current familiar!");
			}
		}
	}
	
	/**
	 * Dismisses/Destroy's the familiar.
	 */
	public void dismissFamiliar(boolean logout) {
		if(summonedFamiliar != null && c.summoned != null) {
			c.summoned.isDead = true;
			c.summoned.applyDead = true;
			c.summoned.actionTimer = 0;
			c.summoned.npcType = -1;
			c.summoned.updateRequired = true;
			c.sendMessage(":summoninactive:");
			if(!logout) {
				for(int i = 0; i < burdenedItems.length; i++) {
					Server.itemHandler.createGroundItem(c, burdenedItems[i] - 1, c.summoned.absX, c.summoned.absY, 1, c.summoned.summonedFor);
				}
				burdenedItems = null;
				summonedFamiliar = null;
			}
			c.summoned = null;
		} else {
			c.sendMessage(":summoninactive:");
		}
	}
	
	/**
	 * Calls the familiar to the owner.
	 */
	public void callFamiliar() {
		if(summonedFamiliar != null && c.summoned != null) {
			c.summoned.npcTeleport(c.absX, c.absY + (summonedFamiliar.large ? 2 : 1), c.heightLevel);
			c.summoned.updateRequired = true;
		} else {
			c.sendMessage(":summoninactive:");
		}
	}
	
	/**
	 * Array of the burdened items.
	 */
	public int[] burdenedItems;
	
	/**
	 * Gets the next slot for the specified itemId.
	 * @param itemId - The specified item id.
	 */
	public int getSlotForId(int itemId) {
		if(summonedFamiliar.familiarType == FamiliarType.BOB) {
			for(int i = 0; i < burdenedItems.length; i++) {
				if((burdenedItems[i] + 1) == itemId)
					return i;
			}
		}
		return -1;
	}
	
	/**
	 * Gets the count of the specified item.
	 * @return the amount of contained items.
	 */
	public int getItemCount(int itemId) {
		int count = 0;
		if(summonedFamiliar.familiarType == FamiliarType.BOB) {
			for(int i = 0; i < burdenedItems.length; i++) {
				if((burdenedItems[i] - 1) == itemId) {
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * Shifts and re-arranges the familiar's inventory.
	 */
	public void shift() {
		int totalItems = 0;
		int highestSlot = 0;
		for (int i = 0; i < summonedFamiliar.storeCapacity; i++) {
			if (burdenedItems[i] != 0) {
				totalItems++;
				if (highestSlot <= i) {	
					highestSlot = i;
				}
			}  
		}
		for (int i = 0; i <= highestSlot; i++) {
			if (burdenedItems[i] == 0) {
				boolean stop = false;
				for (int k = i; k <= highestSlot; k++) {
					if (burdenedItems[k] != 0 && !stop) {
						int spots = k - i;
						for (int j = k; j <= highestSlot; j++) {
							burdenedItems[j - spots] = burdenedItems[j];
							stop = true;
							burdenedItems[j] = 0;
						}
					}
				}					
			}
		}
	}
	
	/**
	 * Withdraws an item from the familiar's inventory, to the owner's inventory.
	 * @param id - Item id
	 * @param slot - Item slot
	 * @param amount - Item amount
	 */
	public void withdrawItem(int id, int amount) {
		if(summonedFamiliar != null && c.summoned != null) {
			if(summonedFamiliar.familiarType == FamiliarType.BOB) {
				if(amount > 0 && id > 0) {
					int slot = getSlotForId(id + 2);
					while(amount > 0 && slot != -1) {
						if(c.getItems().addItem(burdenedItems[slot] - 1, 1)) {
							burdenedItems[slot] = 0;
							slot = getSlotForId(id + 2);
							amount--;
						} else {
							break;
						}
					}
				}
				c.getItems().resetItems(24006);
				c.getItems().resetItems(24002, burdenedItems);
			}
		}
	}
	
	/**
	 * Deposits an item from the owner's inventory, to the familiar's inventory.
	 * @param id - Item id
	 * @param slot - Item slot
	 * @param amount - Item amount
	 */
	public void depositItem(int id, int slot, int amount) {
		if(summonedFamiliar != null && c.summoned != null) {
			if(summonedFamiliar.familiarType == FamiliarType.BOB) {
				if(amount > 0 && c.playerItems[slot] > 0 && slot >= 0 && slot < 28) {
					if(!c.getItems().isStackable(c.playerItems[slot] - 1)) {
						if(itemIsAllowed(c.playerItems[slot] - 1)) {
							while(amount > 0 && slot != -1) {
								if(addItem(c.playerItems[slot])) {
									int tempVar = c.playerItems[slot] - 1;
									c.getItems().deleteItem(c.playerItems[slot] - 1, slot, 1);
									slot = c.getItems().getItemSlot(tempVar);
									amount--;
								} else {
									break;
								}
							}
						} else {
							c.sendMessage("You cannot deposit this item!");
						}
					}
				}
				c.getItems().resetItems(24006);
				c.getItems().resetItems(24002, burdenedItems);
			}
		}
	}
	
	/**
	 * Checks if the specific item is allowed to be deposited.
	 * @return returns if the item is allowed.
	 */
	public boolean itemIsAllowed(int itemId) {
		switch(itemId) {
			case 15272: // Rocktail
			case 7060: // Tuna potato
			case 6685: case 6687: case 6689: case 6691: //Saradomin brew
			case 3024: case 3026: case 3028: case 3030: //Super restore
			case 391: // Manta Ray
			case 385: // Shark
				return true;
		}
		return false;
	}
	
	public void FamiliarTeleport() {
		if(summonedFamiliar.npcId == 7363) {
			c.getPA().startTeleport(3067, 10253, 0, "modern");
		}
	}
	
	/**
	 * Returns true if the item was added to the container successfully.
	 */
	public boolean addItem(int itemId) {
		int nextFreeSlot = getSlotForId(1);
		if(itemId <= 0)
			return false;
		if(nextFreeSlot != -1 && burdenedItems[nextFreeSlot] == 0) {
			burdenedItems[nextFreeSlot] = itemId;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Opens the beast of burden.
	 */
	public void openBoB() {
		if(summonedFamiliar != null && c.summoned != null) {
			if(summonedFamiliar.familiarType == FamiliarType.BOB) {
				shift();
				if(c.getOutStream() != null && c != null) {
					c.getItems().resetItems(24002, burdenedItems);
					c.flushOutStream();
				}
				c.getItems().resetItems(24006);
				c.getOutStream().createFrame(248);
				c.getOutStream().writeWordA(24000);
				c.getOutStream().writeWord(24005);
				c.flushOutStream();
			}
		}
	}
	
	/**
	 * Handles the left click option.
	 */
	public void handleLeftClick() {
		if(summonedFamiliar != null && c.summoned != null) {
			if(summonedFamiliar.familiarType == FamiliarType.BOB) {
				for(int i = 0; i < burdenedItems.length; i++) {
					if(c.getItems().freeSlots() > 0)
						withdrawItem(burdenedItems[i] - 1, 1);
				}
			} else {
				useSpecial(c);
			}
		} else {
			c.sendMessage(":summoninactive:");
		}
	}
	
	/**
	 * Executes the familiar's special using the specified arguments (for compatibility)
	 * @param arguements - The correct arguments.
	 */
	public void useSpecial(Object... arguments) {
		if(summonedFamiliar != null && c.summoned != null) {
			if(summonedFamiliar.familiarSpecial != null && specialTimer < 1) {
				if(familiarSpecialEnergy >= summonedFamiliar.specialEnergyConsumption) {
					if(c.getItems().playerHasItem(summonedFamiliar.scrollId)) {
						c.getItems().deleteItem2(summonedFamiliar.scrollId, 1);
						familiarSpecialEnergy -= summonedFamiliar.specialEnergyConsumption;
						summonedFamiliar.familiarSpecial.execute(arguments);
						specialTimer = 12;
						}
					}
			 else 
				c.sendMessage("Your familiar doesn't have the special energy required...");
				}
		
		
		 else 
				c.sendMessage("You must wait before casting this again.");
				
			}
		}
	
	
	
	private boolean bobWithSpec(int npcType) {
		switch (npcType) {
			case 6873:
				return true;
		}
		return false;
	}

	
	public void handleButtonClick(int buttonId) {
		/*if (!bobWithSpec(summonedFamiliar.npcId)) {
			switch (buttonId) {
				case 58155:
				case 58156:
					handleLeftClick();
					break;
				case 58157:
					callFamiliar();
					break;
				case 58158:
					renewFamiliar();
					break;
				case 58159:
					dismissFamiliar(false);
					break;
				default:
					return;
			}
		} else {*/
			switch (buttonId) {
				case 58156:
					useSpecial(c);
					break;
				case 58157:
					handleLeftClick();
					break;
				case 58158:
					callFamiliar();
					break;
				case 58159:
					renewFamiliar();
					break;
				case 58160:
					dismissFamiliar(false);
					break;
				default:
					return;
			}
		}
	//}
	
	private int specialRestoreCycle = 0;
	private int speakTimer = 70;
	public int familiarSpecialEnergy = 100;
	private int loginCycle = -1;
	public int specialTimer = 5;
	public int renewTimer = -1;
	
	/**
	 * The "process" for familiars.
	 */
	public void familiarTick() {
		if(summonedFamiliar != null && c.summoned != null && loginCycle == 0) {
			if(!c.goodDistance(c.getX(), c.getY(), c.summoned.getX(), c.summoned.getY(), 8)) {
				callFamiliar();
			}
			speakTimer++;
			if(speakTimer == 100) {
				c.summoned.forceChat(summonedFamiliar.speakText);
				speakTimer = 0;
			}
			c.specRestoreTimer--;
			if(c.specRestoreTimer == 100) {
				c.sendMessage("@red@Your familiar will run out in approximately 1 minute.");
				c.sendMessage("@red@Warning! Item's stored in familiar will be dropped upon death!");
			} else if(c.specRestoreTimer == 50) {
				c.sendMessage("@red@Your familiar will run out in approximately 30 seconds.");
				c.sendMessage("@red@Warning! Item's stored in familiar will be dropped upon death!");
			} else if(c.specRestoreTimer == 25) {
				c.sendMessage("@red@Your familiar will run out in approximately 15 seconds.");
				c.sendMessage("@red@Warning! Item's stored in familiar will be dropped upon death!");
			} else if(c.specRestoreTimer == 0) {
				dismissFamiliar(false);
			}
			specialRestoreCycle++;
			if(specialRestoreCycle == 30) {
				if(familiarSpecialEnergy != 100) {
					familiarSpecialEnergy += 10;
				}
				specialRestoreCycle = 0;
			}
			specialTimer--;
		}
		if(renewTimer > 0) {
			renewTimer--;
			if(renewTimer == 0 && c.summoned == null && summonedFamiliar != null) {
				c.summoned = Server.npcHandler.summonNPC(c, summonedFamiliar.npcId, c.getX(), c.getY() + (summonedFamiliar.large ? 2 : 1), c.heightLevel, 0, 100, 1, 1, 1);
				callFamiliar();
			}
		}
		if(loginCycle > 0) {
			loginCycle--;
			if(loginCycle == 0 && c.summoned == null && summonedFamiliar != null) {
				c.summoned = Server.npcHandler.summonNPC(c, summonedFamiliar.npcId, c.getX(), c.getY() + (summonedFamiliar.large ? 2 : 1), c.heightLevel, 0, 100, 1, 1, 1);
				callFamiliar();
			}
		}
	}
}