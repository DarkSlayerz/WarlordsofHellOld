package server.model.players;

import server.Config;
import server.Server;
import server.util.Misc;
import server.model.players.Client;
import server.model.players.Hit.CombatType;
import server.model.players.PlayerSave;
import server.event.EventManager;
import server.model.players.Weapon;
import server.model.npcs.NPC;
import server.event.*;
import server.model.npcs.NPCHandler;
import server.event.EventContainer;
import server.event.Event;
import server.model.players.combat.*;
import server.model.players.combat.melee.*;
import server.model.players.combat.magic.*;
import server.model.players.combat.ranged.*;


public class CombatAssistant{

	private Client c;
	public CombatAssistant(Client Client) {
		this.c = Client;
	}
	

	public int[][] slayerReqs = {{1648,5},{1612,15},{1643,45},{1618,50},{1624,65},{1610,75},{1613,80},{1615,85},{2783,90},{3590,95},{3592,85},{3591,85},{5666,96},{1472,90}};
	
	public boolean goodSlayer(int i) {
		for (int j = 0; j < slayerReqs.length; j++) {
			if (slayerReqs[j][0] == Server.npcHandler.npcs[i].npcType) {
				if (slayerReqs[j][1] > c.playerLevel[c.playerSlayer]) {
					c.sendMessage("You need a slayer level of " + slayerReqs[j][1] + " to harm this NPC.");
					return false;
				}
			}
		}
		return true;
	}
	/*
	 * CalculateCriticalChance
	 *  Method Created by Mr. Chris of rune-server
	 */
public boolean CalculateCriticalChance(Client c) {
		if(c.usingSpecial && c.inWild())
			return false;
		int CriticalStrikeChance = Misc.random(100);
		if (CriticalStrikeChance > 99)
			return true;
		else
			return false;
	}


	/*
	 * CalculateCriticalDamage Method
	 *  Created by Mr. Chris of rune-server
	 */
	public int CalculateCriticalDamage(Client c, int Damage, String type) {
		return Damage += Damage;
	}

	/*
	 * sendCriticalStrikeGloating Method
	 *  Created by Mr. Chris of Rune-Server
	 */

	public void sendCriticalStrikeGloating(Client c, String CombatStyle) {
		if (CombatStyle == "Ranged") {
			//c.forcedText = "Critical Ranged Hit Activated!!!";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
		}

		if (CombatStyle == "Melee") {
			//c.forcedText = "Critical Melee Hit Activated!!!";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
		}
	}
	
	/**
	* Attack Npcs
	*/
	public void attackNpc(int i) {	
	if (c.playerEquipment[c.playerWeapon] == 15241) {
          c.gfx0(2138);
          }
	if(c.playerEquipment[c.playerWeapon] == 15241 && 15243 != c.playerEquipment[c.playerArrows]){
                c.sendMessage("You can't use Hand Cannon without the shots!");
                return;
	}
		if (Server.npcHandler.npcs[i] != null) {
			if (Server.npcHandler.npcs[i].isDead || Server.npcHandler.npcs[i].MaxHP <= 0) {
				c.usingMagic = false;
				c.faceUpdate(0);
				c.npcIndex = 0;
				return;
			}	

			if (Server.npcHandler.npcs[i].summon == true) {
			if(Server.npcHandler.npcs[i].index != c.playerId || c.wildLevel == 0) {
				resetPlayerAttack();
				c.sendMessage("You cannot attack this monster.");

//				c.sendMessage("This is "+Server.npcHandler.npcz[i]+" NPC.");
				return;
			}
}

	
		if(c.specEffect == 4) {
		c.specEffect = 0;
		}
		if (c.inWG()) {
			resetPlayerAttack();
			c.stopMovement();
			return;
              	  }
			if (Server.npcHandler.npcs[i].npcType == 1000 || Server.npcHandler.npcs[i].npcType == 1001 || Server.npcHandler.npcs[i].npcType == 3100 || Server.npcHandler.npcs[i].npcType == 1002 || Server.npcHandler.npcs[i].npcType == 1003) {
			resetPlayerAttack();
			c.stopMovement();
			return; // Bandos
              	  }

			if (Server.npcHandler.npcs[i].npcType == 3104) {
			c.sendMessage("You must click the gate before attacking the Corporeal Beast!");
			resetPlayerAttack();
			c.stopMovement();
			return; // Corporeal beast *fake*
              	  }

			if (Server.npcHandler.npcs[i].npcType == 1004 || Server.npcHandler.npcs[i].npcType == 1005 || Server.npcHandler.npcs[i].npcType == 1006 || Server.npcHandler.npcs[i].npcType == 1007) {
			resetPlayerAttack();
			c.stopMovement();
			return; // Arma
              	  }
			if (Server.npcHandler.npcs[i].npcType == 1008 || Server.npcHandler.npcs[i].npcType == 1009 || Server.npcHandler.npcs[i].npcType == 1010 || Server.npcHandler.npcs[i].npcType == 1011) {
			resetPlayerAttack();
			c.stopMovement();
			return; // Zammy
              	  }
			if (Server.npcHandler.npcs[i].npcType == 1012 || Server.npcHandler.npcs[i].npcType == 1013 || Server.npcHandler.npcs[i].npcType == 1014 || Server.npcHandler.npcs[i].npcType == 1015) {
			resetPlayerAttack();
			c.stopMovement();
			return; // Sara
              	  }
			if(c.respawnTimer > 0) {
				c.npcIndex = 0;
				return;
			}
			if (!goodSlayer(i)) {
				resetPlayerAttack();
				return;
			}
			if (Server.npcHandler.npcs[i].spawnedBy != c.playerId && Server.npcHandler.npcs[i].spawnedBy > 0 && Server.npcHandler.npcs[i].summon != true) {
				resetPlayerAttack();
				c.sendMessage("This monster was not spawned for you.");
				return;
			}
			if(Server.npcHandler.npcs[i].summoner == true) {
				c.sendMessage("You cannot attack your own NPC.");
				resetPlayerAttack();
				return;
			}
			if ((c.underAttackBy > 0 || c.underAttackBy2 > 0) && c.underAttackBy2 != i && !c.inMulti()) {
				resetPlayerAttack();
				c.sendMessage("I am already under attack.");
				return;
			}
			if (Server.npcHandler.npcs[i].underAttackBy > 0 && Server.npcHandler.npcs[i].underAttackBy != c.playerId && !Server.npcHandler.npcs[i].inMulti()) {
				c.npcIndex = 0;
				c.sendMessage("This monster is already in combat.");
				return;
			}



			if (!goodSlayer(i)) {
				resetPlayerAttack();
				return;
			}
			if (Server.npcHandler.npcs[i].spawnedBy != c.playerId && Server.npcHandler.npcs[i].spawnedBy > 0) {
				resetPlayerAttack();
				c.sendMessage("This monster was not spawned for you.");
				return;
			}
			if(c.attackTimer <= 0) {
				boolean usingBow = false;
				boolean usingArrows = false;
				boolean usingOtherRangeWeapons = false;
				boolean usingCross = c.playerEquipment[c.playerWeapon] == 9185 || c.playerEquipment[c.playerWeapon] == 18357;
				c.bonusAttack = 0;
				c.rangeItemUsed = 0;
				c.projectileStage = 0;
				//c.SaveGame();
				if (c.autocasting) {
					c.spellId = c.autocastId;
					c.usingMagic = true;
				}
				if(c.spellId > 0) {
				c.usingMagic = true;
				}
				c.attackTimer = getAttackDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
				c.specAccuracy = 1.0;
				c.specDamage = 1.0;
				if(!c.usingMagic) {
					for (int bowId : c.BOWS) {
						if(c.playerEquipment[c.playerWeapon] == bowId) {
							usingBow = true;
							for (int arrowId : c.ARROWS) {
								if(c.playerEquipment[c.playerArrows] == arrowId) {
									usingArrows = true;
					c.gfx100(getRangeStartGFX());
								}
							}
						}
					}
					
					for (int otherRangeId : c.OTHER_RANGE_WEAPONS) {
						if(c.playerEquipment[c.playerWeapon] == otherRangeId) {
							usingOtherRangeWeapons = true;
						}
					}
				}
				if (armaNpc(i) && !usingCross && !usingBow && !c.usingMagic && !usingCrystalBow() && !usingOtherRangeWeapons) {				
					resetPlayerAttack();
					c.sendMessage("You can only use ranged or magical attacks against this creature!");
					return;
				}
				if((!c.goodDistance(c.getX(), c.getY(), Server.npcHandler.npcs[i].getX(), Server.npcHandler.npcs[i].getY(), 2) && (usingHally() && !usingOtherRangeWeapons && !usingBow && !usingCross && !c.usingMagic)) ||(!c.goodDistance(c.getX(), c.getY(), Server.npcHandler.npcs[i].getX(), Server.npcHandler.npcs[i].getY(), 4) && (usingOtherRangeWeapons && !usingCross && !usingBow && !c.usingMagic)) || (!c.goodDistance(c.getX(), c.getY(), Server.npcHandler.npcs[i].getX(), Server.npcHandler.npcs[i].getY(), 1) && (!usingOtherRangeWeapons && !usingHally() && !usingCross && !usingBow && !c.usingMagic)) || ((!c.goodDistance(c.getX(), c.getY(), Server.npcHandler.npcs[i].getX(), Server.npcHandler.npcs[i].getY(), 8) && (usingBow || usingCross	|| c.usingMagic)))) {
					c.attackTimer = 2;
					return;
				}
				
				if(!usingCross && !usingArrows && usingBow && (c.playerEquipment[c.playerWeapon] < 4212 || c.playerEquipment[c.playerWeapon] > 4223)) {
				if(c.playerEquipment[c.playerWeapon] == 15241)
					c.sendMessage("You have run out of shots!");
				else
					c.sendMessage("You have run out of arrows!");
					c.stopMovement();
					c.npcIndex = 0;
					return;
				} 
				
				if(usingBow || usingCross || c.usingMagic || usingOtherRangeWeapons || (c.goodDistance(c.getX(), c.getY(), Server.npcHandler.npcs[i].getX(), Server.npcHandler.npcs[i].getY(), 2) && usingHally())) {
					c.stopMovement();
				}

				if(!checkMagicReqs(c.spellId)) {
					c.stopMovement();
					c.npcIndex = 0;
					return;
				}
				
				c.faceUpdate(i);
				c.slayerHelmetEffect = c.playerEquipment[c.playerHat] == 15492 && c.slayerTask == i;
				//c.specAccuracy = 1.0;
				//c.specDamage = 1.0;
				Server.npcHandler.npcs[i].underAttackBy = c.playerId;
				Server.npcHandler.npcs[i].lastDamageTaken = System.currentTimeMillis();
				if(c.usingSpecial && !c.usingMagic) {
					if(checkSpecAmount(c.playerEquipment[c.playerWeapon])){
						c.lastWeaponUsed = c.playerEquipment[c.playerWeapon];
						c.lastArrowUsed = c.playerEquipment[c.playerArrows];
						activateSpecial(c.playerEquipment[c.playerWeapon], i);
						return;
					} else {
						c.sendMessage("You don't have the required special energy to use this attack.");
						c.usingSpecial = false;
						c.getItems().updateSpecialBar();
						c.npcIndex = 0;
						return;
					}
				}
								if(usingBow || c.usingMagic || usingOtherRangeWeapons) {
					c.mageFollow = true;
				} else {
					c.mageFollow = false;
				}
				c.specMaxHitIncrease = 0;
				if(!c.usingMagic) {
					c.startAnimation(getWepAnim(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()));
					//c.sendSound(getAttackSound());
				} else {
					c.startAnimation(c.MAGIC_SPELLS[c.spellId][2]);
				}
				c.lastWeaponUsed = c.playerEquipment[c.playerWeapon];
				c.lastArrowUsed = c.playerEquipment[c.playerArrows];
				if(!usingBow && !usingCross && !c.usingMagic && !usingOtherRangeWeapons) { // melee hit delay
					c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
					c.projectileStage = 0;
					c.oldNpcIndex = i;
				}
				
				if(usingBow && !usingOtherRangeWeapons && !c.usingMagic || usingCross) { // range hit delay					
					if (usingCross)
						c.usingBow = true;
					if (c.fightMode == 2)
						c.attackTimer--;
					c.lastArrowUsed = c.playerEquipment[c.playerArrows];
					c.lastWeaponUsed = c.playerEquipment[c.playerWeapon];
					c.gfx100(getRangeStartGFX());	
					c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
					c.projectileStage = 1;
					c.oldNpcIndex = i;
					if(c.playerEquipment[c.playerWeapon] >= 4212 && c.playerEquipment[c.playerWeapon] <= 4223) {
						c.rangeItemUsed = c.playerEquipment[c.playerWeapon];
						//c.crystalBowArrowCount++;
						c.lastArrowUsed = 0;
					} else {
						c.rangeItemUsed = c.playerEquipment[c.playerArrows];
						c.getItems().deleteArrow();	
					}
					fireProjectileNpc();
				}

			if(usingBow && usingCross && c.usingMagic && usingOtherRangeWeapons) {
			c.getPA().followNpc();
			c.stopMovement();
			} else {
			c.followId = 0;
			c.followId2 = i;
			}
							
				
				if(usingOtherRangeWeapons && !c.usingMagic && !usingCross && !usingBow) {	// knives, darts, etc hit delay		
					c.rangeItemUsed = c.playerEquipment[c.playerWeapon];
					c.getItems().deleteEquipment();
					c.gfx100(getRangeStartGFX());
					c.lastArrowUsed = 0;
					c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
					c.projectileStage = 1;
					c.oldNpcIndex = i;
					if (c.fightMode == 2)
						c.attackTimer--;
					fireProjectileNpc();	
				}

				if(c.usingMagic) {	// magic hit delay
					int pX = c.getX();
					int pY = c.getY();
					int nX = Server.npcHandler.npcs[i].getX();
					int nY = Server.npcHandler.npcs[i].getY();
					int offX = (pY - nY)* -1;
					int offY = (pX - nX)* -1;
					c.castingMagic = true;
					c.projectileStage = 2;
					if(c.MAGIC_SPELLS[c.spellId][3] > 0) {
						if(getStartGfxHeight() == 100) {
							c.gfx100(c.MAGIC_SPELLS[c.spellId][3]);
						} else {
							c.gfx0(c.MAGIC_SPELLS[c.spellId][3]);
						}
					}
					if(c.MAGIC_SPELLS[c.spellId][4] > 0) {
						c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 78, c.MAGIC_SPELLS[c.spellId][4], getStartHeight(), getEndHeight(), i + 1, 50);
					}
					c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
					c.oldNpcIndex = i;
					c.oldSpellId = c.spellId;
					c.spellId = 0;
					if (!c.autocasting)
						c.npcIndex = 0;
				}
		/*if (c.curseActive[18]) {
			if(c.oldNpcIndex > 0) {
			if(Server.npcHandler.npcs[c.oldNpcIndex] != null) {
			//final Client c2 = (Client)Server.playerHandler.players[i];
			final int pX = c.getX();
			final int pY = c.getY();
			final int oX = c2.getX();
			final int oY = c2.getY();
			int offX = (pY - oY)* -1;
			int offY = (pX - oX)* -1;
			
			/*if (damage > 0) {
				c2.playerLevel[5] -= 1;
				if (c2.playerLevel[5] <= 0) {
					c2.playerLevel[5] = 0;
					c2.getCombat().resetPrayers();
				}
				if (c.playerLevel[3] >= c.getLevelForXP(c.playerXP[3])) {
					c.playerLevel[3] += 0;
				} else {
					if (c.playerLevel[3] + ((damage * 4)/10) < c.getLevelForXP(c.playerXP[3])) {
						c.playerLevel[3] += (damage * 4) / 10;
					} else {
						c.playerLevel[3] += (c.getLevelForXP(c.playerXP[3]) - c.playerLevel[3]);
					}	
				}*//*
				
			c.getPA().refreshSkill(3);
			c2.getPA().refreshSkill(5);
			}
			c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2263, 31, 31, - c.oldNpcIndex  - 1, 0);
			c.soulSplitDelay = 4;
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer s) {
					if (c.soulSplitDelay > 0) {
						c.soulSplitDelay--;
					}
					if (c.soulSplitDelay == 3) {
						c2.gfx0(2264);
					}
					if (c.soulSplitDelay == 2) {
						int offX2 = (oY - pY)* -1;
						int offY2 = (oX - pX)* -1;
						c.getPA().createPlayersProjectile(oX, oY, offX2, offY2, 50, 45, 2263, 31, 31, - c.playerId - 1, 0);
					}
					if (c.soulSplitDelay == 0) {	
						s.stop();
					}
				}
			}, 500);
		}
	}
}*/
			/*if(c.curseActive[18]) { // SoulSplit NPC
					final int pX = c.getX();
					final int pY = c.getY();
					final int nX = Server.npcHandler.npcs[i].getX();
					final int nY = Server.npcHandler.npcs[i].getY();
					final int offX = (pY - nY)* -1;
					final int offY = (pX - nX)* -1;
					final Client giveHP = c;
					//c.SSPLIT = true;
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, 50, 2263, 9, 9, - c.playerIndex - 1, 24, 0);
				EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer b) {
 					Server.npcHandler.npcs[c.npcIndex].gfx0(2264); // 1738
					//c.SSPLIT = false;
				        b.stop();
					}
				}, 500);
				EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer b) { 
					c.getPA().createPlayersProjectile2(nX, nY, offX, offY, 50, 50, 2263, 9, 9, - c.playerId - 1, 24, 0);
				        b.stop();
					}
				}, 800);

				EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer b) {
						try {
							int before = giveHP.ssHeal; // Debug
							giveHP.playerLevel[giveHP.playerHitpoints] += before;
							if(giveHP.playerLevel[giveHP.playerHitpoints] > giveHP.getLevelForXP(giveHP.playerXP[giveHP.playerHitpoints])) {
								giveHP.playerLevel[giveHP.playerHitpoints] = giveHP.getLevelForXP(giveHP.playerXP[giveHP.playerHitpoints]);
							}
							giveHP.ssHeal -= before;
						} catch(Exception e) { }
						b.stop();
					}
				}, 1300); // Timer should be better.
			}*/
					if(c.crystalBowArrowCount >= 250){
						switch(c.playerEquipment[c.playerWeapon]) {
							
							case 4223: // 1/10 bow
							c.getItems().wearItem(-1, 1, 3);
							c.sendMessage("Your crystal bow has fully degraded.");
							if(!c.getItems().addItem(4207, 1)) {
								Server.itemHandler.createGroundItem(c, 4207, c.getX(), c.getY(), 1, c.getId());
							}
							c.crystalBowArrowCount = 0;
							break;
							
							default:
							c.getItems().wearItem(++c.playerEquipment[c.playerWeapon], 1, 3);
							c.sendMessage("Your crystal bow degrades.");
							c.crystalBowArrowCount = 0;
							break;
							
						
						}
					}	
				}
			}
		}
	

	public void delayedHit(int i) { // npc hit delay

		if (Server.npcHandler.npcs[i] != null) {
			if (Server.npcHandler.npcs[i].isDead) {
				c.npcIndex = 0;
				return;
			}
			Server.npcHandler.npcs[i].facePlayer(c.playerId);
			if (Server.npcHandler.npcs[i].underAttackBy > 0 && Server.npcHandler.getsPulled(i)) {
				Server.npcHandler.npcs[i].killerId = c.playerId;			
			} else if (Server.npcHandler.npcs[i].underAttackBy < 0 && !Server.npcHandler.getsPulled(i)) {
				Server.npcHandler.npcs[i].killerId = c.playerId;
			}
			c.lastNpcAttacked = i;
			if(c.projectileStage == 0 && !c.usingMagic && !c.castingMagic) { // melee hit damage
				if (!c.usingClaws)
					applyNpcMeleeDamage(i, 1, Misc.random(calculateMeleeMaxHit()));
				if(c.doubleHit && !c.usingClaws) {
					applyNpcMeleeDamage(i, 2, Misc.random(calculateMeleeMaxHit()));
				}
				if(c.doubleHit && c.usingClaws) {
					c.delayedDamage = c.clawDamage;
					c.delayedDamage2 = c.clawDamage/2;
					applyNpcMeleeDamage(i, 1, c.clawDamage);
					applyNpcMeleeDamage(i, 2, c.clawDamage/2);
				}				
			}

			if(!c.castingMagic && c.projectileStage > 0) { // range hit damage
				int damage = Misc.random(rangeMaxHit());
				int damage2 = -1;
				if (c.lastWeaponUsed == 14481 || c.lastWeaponUsed == 15704 || c.lastWeaponUsed == 11235 || c.lastWeaponUsed == 15702 || c.lastWeaponUsed == 15701 || c.lastWeaponUsed == 15703 || c.lastWeaponUsed == 14482 || c.bowSpecShot == 1)
					damage2 = Misc.random(rangeMaxHit());
				boolean ignoreDef = false;					

				
				if(Misc.random(Server.npcHandler.npcs[i].defence) > Misc.random(10+calculateRangeAttack()) && !ignoreDef) {
					damage = 0;
				} else if (Server.npcHandler.npcs[i].npcType == 2881 || Server.npcHandler.npcs[i].npcType == 2883 || Server.npcHandler.npcs[i].npcType == 3340 && !ignoreDef) {
					damage = 0;
				}
				
				if(c.playerEquipment[3] == 9185) {
					if(Misc.random(5) == 1) {
						if(damage > 0) {
							c.boltDamage = damage;
							CrossbowEffects.crossbowSpecial(c,i);
							damage *= c.crossbowDamage;
						}
					}
				}
				
			
				
				
				if (c.lastWeaponUsed == 15701 || c.lastWeaponUsed == 15702 || c.lastWeaponUsed == 11235 || c.lastWeaponUsed == 15703 || c.lastWeaponUsed == 15704 || c.bowSpecShot == 1) {
					if (Misc.random(Server.npcHandler.npcs[i].defence) > Misc.random(10+calculateRangeAttack()))
						damage2 = 0;
				}

				if (c.dbowSpec) {
					Server.npcHandler.npcs[i].gfx100(1100);
                                        if(c.dbowDelay == 4)
					if (damage < 8)
						damage = 8;
                                        else if(c.dbowDelay == 1)
					if (damage2 < 8)
						damage2 = 8;
					c.dbowSpec = false;
				}
				
				if (Server.npcHandler.npcs[i].HP - damage < 0) { 
					damage = Server.npcHandler.npcs[i].HP;
				}
				if (Server.npcHandler.npcs[i].HP - damage <= 0 && damage2 > 0) {
					damage2 = 0;
				}
				if(c.fightMode == 3) {
					c.getPA().addSkillXP((damage*Config.RANGE_EXP_RATE/3), 4); 
					c.getPA().addSkillXP((damage*Config.RANGE_EXP_RATE/3), 1);				
					c.getPA().addSkillXP((damage*Config.RANGE_EXP_RATE/3), 3);
					c.getPA().refreshSkill(1);
					c.getPA().refreshSkill(3);
					c.getPA().refreshSkill(4);
				} else {
					c.getPA().addSkillXP((damage*Config.RANGE_EXP_RATE), 4); 
					c.getPA().addSkillXP((damage*Config.RANGE_EXP_RATE/3), 3);
					c.getPA().refreshSkill(3);
					c.getPA().refreshSkill(4);
				}
				if (damage > 0) {
					if (Server.npcHandler.npcs[i].npcType >= 6142 && Server.npcHandler.npcs[i].npcType <= 6145) {
						c.pcDamage += damage;					
					}				
				}
				boolean dropArrows = true;
						
				for(int noArrowId : c.NO_ARROW_DROP) {
					if(c.lastWeaponUsed == noArrowId) {
						dropArrows = false;
						break;
					}
				}
				if(dropArrows) {
					c.getItems().dropArrowNpc();	
				}
				Server.npcHandler.npcs[i].underAttack = true;
				Server.npcHandler.npcs[i].hitDiff = new Hit(damage, CombatType.RANGE);
				Server.npcHandler.npcs[i].HP -= damage;
				if (damage2 > -1) {
					Server.npcHandler.npcs[i].hitDiff2 = new Hit(damage2, CombatType.RANGE);
					Server.npcHandler.npcs[i].HP -= damage2;
					c.totalDamageDealt += damage2;	
				}
				if (c.killingNpcIndex != c.oldNpcIndex) {
					c.totalDamageDealt = 0;				
				}
				c.killingNpcIndex = c.oldNpcIndex;
				c.totalDamageDealt += damage;
				Server.npcHandler.npcs[i].hitUpdateRequired = true;
				applynpcSS(i, damage); //Soulsplit on Npcs
				if (damage2 > -1)
					Server.npcHandler.npcs[i].hitUpdateRequired2 = true;
				Server.npcHandler.npcs[i].updateRequired = true;

			} else if (c.projectileStage > 0) { // magic hit damage
				int damage = Misc.random(c.MAGIC_SPELLS[c.oldSpellId][6]);
				if(godSpells()) {
					if(System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
						damage += Misc.random(10);
					}
				}
				boolean magicFailed = false;
				//c.npcIndex = 0;
				int bonusAttack = getBonusAttack(i);
				if (Misc.random(Server.npcHandler.npcs[i].defence) > 10+ Misc.random(mageAtk()) + bonusAttack) {
					damage = 0;
					magicFailed = true;
				} else if (Server.npcHandler.npcs[i].npcType == 2881 || Server.npcHandler.npcs[i].npcType == 2882) {
					damage = 0;
					magicFailed = true;
				}
				
				if (Server.npcHandler.npcs[i].HP - damage < 0) { 
					damage = Server.npcHandler.npcs[i].HP;
				}
				
				c.getPA().addSkillXP((c.MAGIC_SPELLS[c.oldSpellId][7] + damage*Config.MAGIC_EXP_RATE), 6); 
				c.getPA().addSkillXP((c.MAGIC_SPELLS[c.oldSpellId][7] + damage*Config.MAGIC_EXP_RATE/3), 3);
				c.getPA().refreshSkill(3);
				c.getPA().refreshSkill(6);
				if (damage > 0) {
					if (Server.npcHandler.npcs[i].npcType >= 6142 && Server.npcHandler.npcs[i].npcType <= 6145) {
						c.pcDamage += damage;					
					}				
				}
				if(getEndGfxHeight() == 100 && !magicFailed){ // end GFX
					Server.npcHandler.npcs[i].gfx100(c.MAGIC_SPELLS[c.oldSpellId][5]);
				} else if (!magicFailed){
					Server.npcHandler.npcs[i].gfx0(c.MAGIC_SPELLS[c.oldSpellId][5]);
				}
				
				if(magicFailed) {	
					Server.npcHandler.npcs[i].gfx100(85);
				}			
				if(!magicFailed) {
					int freezeDelay = getFreezeTime();//freeze 
					if(freezeDelay > 0 && Server.npcHandler.npcs[i].freezeTimer == 0) {
						Server.npcHandler.npcs[i].freezeTimer = freezeDelay;
					}
					switch(c.MAGIC_SPELLS[c.oldSpellId][0]) { 
						case 12901:
						case 12919: // blood spells
						case 12911:
						case 12929:
						int heal = Misc.random(damage / 2);
						if(c.playerLevel[3] + heal >= c.getPA().getLevelForXP(c.playerXP[3])) {
							c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
						} else {
							c.playerLevel[3] += heal;
						}
						c.getPA().refreshSkill(3);
						break;
					}

				}
				Server.npcHandler.npcs[i].underAttack = true;
				if(c.MAGIC_SPELLS[c.oldSpellId][6] != 0) {
					Server.npcHandler.npcs[i].hitDiff = new Hit(damage, CombatType.MAGE);
					Server.npcHandler.npcs[i].HP -= damage;
					Server.npcHandler.npcs[i].hitUpdateRequired = true;
					applynpcSS(i, damage); //Soulsplit on Npcs
					c.totalDamageDealt += damage;
				}
				c.killingNpcIndex = c.oldNpcIndex;			
				Server.npcHandler.npcs[i].updateRequired = true;
				//applynpcSS(i, damage); //Soulsplit on Npcs
				c.usingMagic = false;
				c.castingMagic = false;
				c.oldSpellId = 0;
			}
		}
	
		if(c.bowSpecShot <= 0) {
			c.oldNpcIndex = 0;
			c.projectileStage = 0;
			c.doubleHit = false;
			c.lastWeaponUsed = 0;
			c.bowSpecShot = 0;
		}
		if(c.bowSpecShot >= 2) {
			c.bowSpecShot = 0;
			//c.attackTimer = getAttackDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
		}
		if(c.bowSpecShot == 1) {
			fireProjectileNpc();
			c.hitDelay = 2;
			c.bowSpecShot = 0;
		}
	}
	
	
	public void applyNpcMeleeDamage(int i, int damageMask, int damage) {
		c.previousDamage = damage;
		boolean fullVeracsEffect = c.getPA().fullVeracs() && Misc.random(3) == 1;
		if (Server.npcHandler.npcs[i].HP - damage < 0) { 
			damage = Server.npcHandler.npcs[i].HP;
		}
		
		if (!fullVeracsEffect && !c.usingClaws) {
			if (Misc.random(Server.npcHandler.npcs[i].defence) > 10 + Misc.random(calculateMeleeAttack())) {
				damage = 0;
			} else if (Server.npcHandler.npcs[i].npcType == 2882 || Server.npcHandler.npcs[i].npcType == 2883) {
				damage = 0;
			}
		}	
		boolean guthansEffect = false;
		if (c.getPA().fullGuthans()) {
			if (Misc.random(3) == 1) {
				guthansEffect = true;			
			}		
		}
		if(c.fightMode == 3) {
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 0); 
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 1);
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 2); 				
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 3);
			c.getPA().refreshSkill(0);
			c.getPA().refreshSkill(1);
			c.getPA().refreshSkill(2);
			c.getPA().refreshSkill(3);
		} else {
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE), c.fightMode); 
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 3);
			c.getPA().refreshSkill(c.fightMode);
			c.getPA().refreshSkill(3);
		}
		applynpcSS(i, damage); //Soulsplit on Npcs
		if (damage > 0) {
			if (Server.npcHandler.npcs[i].npcType >= 6142 && Server.npcHandler.npcs[i].npcType <= 6145) {
				c.pcDamage += damage;					
			}				
		}
		if (damage > 0 && guthansEffect) {
			c.playerLevel[3] += damage;
			if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
				c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
			c.getPA().refreshSkill(3);
			Server.npcHandler.npcs[i].gfx0(398);		
		}
		Server.npcHandler.npcs[i].underAttack = true;
		//Server.npcHandler.npcs[i].killerId = c.playerId;
		c.killingNpcIndex = c.npcIndex;
		c.lastNpcAttacked = i;
		switch (c.specEffect) {
			case 4:
				if (damage > 0) {
					if (c.playerLevel[3] + damage > c.getLevelForXP(c.playerXP[3]))
						if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]));
						else 
						c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
					else 
						c.playerLevel[3] += damage;
					c.getPA().refreshSkill(3);
				}
			break;
                        case 5:
                        c.clawDelay = 2;
                        c.clawDamage = Misc.random(calculateMeleeMaxHit());
                        break;

                        case 7:
                        c.dbowDelay = 6;
                        break;
		
		}
		switch(damageMask) {
			case 1:
			Server.npcHandler.npcs[i].hitDiff = new Hit(damage, CombatType.MELEE);
			Server.npcHandler.npcs[i].HP -= damage;
			c.totalDamageDealt += damage;
			Server.npcHandler.npcs[i].hitUpdateRequired = true;	
			Server.npcHandler.npcs[i].updateRequired = true;
			break;
		
			case 2:
			Server.npcHandler.npcs[i].hitDiff2 = new Hit(damage, CombatType.MELEE);
			Server.npcHandler.npcs[i].HP -= damage;
			c.totalDamageDealt += damage;
			Server.npcHandler.npcs[i].hitUpdateRequired2 = true;	
			Server.npcHandler.npcs[i].updateRequired = true;
			c.doubleHit = false;
			break;
			
		}
	}
	
	public void fireProjectileNpc() {
		if(c.oldNpcIndex > 0) {
			if(Server.npcHandler.npcs[c.oldNpcIndex] != null) {
				c.projectileStage = 2;
				int pX = c.getX();
				int pY = c.getY();
				int nX = Server.npcHandler.npcs[c.oldNpcIndex].getX();
				int nY = Server.npcHandler.npcs[c.oldNpcIndex].getY();
				int offX = (pY - nY)* -1;
				int offY = (pX - nX)* -1;
				c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, getProjectileSpeed(), getRangeProjectileGFX(), 43, 31, c.oldNpcIndex + 1, getStartDelay());
				if (usingDbow())
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, getProjectileSpeed(), getRangeProjectileGFX(), 60, 31,  c.oldNpcIndex + 1, getStartDelay(), 35);
			}
		}
	}
	

	
	/**
	* Attack Players, same as npc tbh xD
	**/
	
		public void attackPlayer(int i) {
if (c.playerEquipment[c.playerWeapon] == 15241) {
         	c.gfx0(2138);
          	}
		/*if(c.playerEquipment[c.playerWeapon] == 15241 && 15243 != c.playerEquipment[c.playerArrows]){
                c.sendMessage("You can't use Hand Cannon without the shots! (You dumb?)");
                return;
				}*/
                if(c.vestaDelay > 0) {
                   resetPlayerAttack();
                   return;
                }
				/*if(c.playerEquipment[c.playerWeapon] == 18357 && 9342 != c.playerEquipment[c.playerArrows]){
                c.sendMessage("You can't use Chaotic Crossbow Without Onyx Bolts! (You dumb?)");
                return;	
				}*/
				if(c.playerEquipment[c.playerWeapon] == 13022 && 15243 != c.playerEquipment[c.playerArrows]){
                c.sendMessage("You can't use Hand Cannon without the shots!");
                return;
				}
/*if(c.playerEquipment[c.playerWeapon] == 11235 && 11212 != c.playerEquipment[c.playerArrows]){
c.sendMessage("you need dragon arrows to attack with a dark bow");
return;
}*/

					/*if(c.curseActive[18]) { // SoulSplit GFX's - CAUSES CRASH
					if(c.oldNpcIndex > 0) {
					if(Server.npcHandler.npcs[c.oldNpcIndex] != null) {
					try {
					if(c.curseActive[18] && !c.prayerActive[23] && c.playerLevel[3] <= 99) {
						int heal = 2;
						if(c.playerLevel[3] + heal >= c.getPA().getLevelForXP(c.playerXP[3])) {
							c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
						} else {
							c.playerLevel[3] += heal;
						}
						c.getPA().refreshSkill(3);
					}
					final int pX = c.getX();
 					final int pY = c.getY();
					final int nX = Server.npcHandler.npcs[c.oldNpcIndex].getX();
					final int nY = Server.npcHandler.npcs[c.oldNpcIndex].getY();
					final int offX = (pY - nY)* -1;
					final int offY = (pX - nX)* -1;
					c.SSPLIT = true;
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, 50, 2263, 9, 9, c.oldNpcIndex + 1, 24, 0);
					EventManager.getSingleton().addEvent(new Event() {
					public void execute(EventContainer b) {
					c.SSPLIT = false;
				        b.stop();
					}
					}, 500);
					/*EventManager.getSingleton().addEvent(new Event() { // CAUSES CRASH
					public void execute(EventContainer b) {
					//c.getPA().createPlayersProjectile2(nX, nY, offX, offY, 50, 50, 2263, 9, 9, - c.playerId - 1, 24, 0);
				        b.stop();
					}
					}, 800);*/ /*
					} catch (Exception e) {
					e.printStackTrace();


				}


			}


		}


	}*/
				
				if(c.curseActive[19]) { // Turmoil
				c.getstr = PlayerHandler.players[i].playerLevel[2] * 10 / 100;
				c.getdef = PlayerHandler.players[i].playerLevel[1] * 15 / 100;
				c.getatt = PlayerHandler.players[i].playerLevel[0] * 15 / 100;
				c.getranged = PlayerHandler.players[i].playerLevel[4] * 15 / 100;
				c.getmagic = PlayerHandler.players[i].playerLevel[6] * 15 / 100;
				}
							if(c.curseActive[10]) { // Leech Attack
				c.getatt = PlayerHandler.players[i].playerLevel[0] * 10 / 100;
			}
			if(c.curseActive[13]) { // Leech Defense
				c.getdef = PlayerHandler.players[i].playerLevel[1] * 10 / 100;
			}
			if(c.curseActive[14]) { // Leech Strength
				c.getstr = PlayerHandler.players[i].playerLevel[2] * 10 / 100;
			}
			if(c.curseActive[11]) { // Leech Ranged
				c.getdef = PlayerHandler.players[i].playerLevel[4] * 10 / 100;
			}
			if(c.curseActive[12]) { // Leech Magic
				c.getdef = PlayerHandler.players[i].playerLevel[6] * 10 / 100;
			}
					
		

for (int u : c.Bolts)  {
                for (int y : c.BOWS)  {
                    if(y == c.playerEquipment[c.playerWeapon] && c.playerEquipment[c.playerWeapon] != 9185 && u == c.playerEquipment[c.playerArrows] && c.playerEquipment[c.playerWeapon] != 15241 && u == c.playerEquipment[c.playerArrows]){
                        c.sendMessage("You can only use arrows with this bow.");
                        return;
                    }
                }
            }
			
		if(c.specEffect == 4) {
		c.specEffect = 0;

		}
                    /* DEGRADING */
		if (Server.playerHandler.players[i] != null) {
			
			if (Server.playerHandler.players[i].isDead) {
				resetPlayerAttack();
				return;
			}
			
			if(c.respawnTimer > 0 || Server.playerHandler.players[i].respawnTimer > 0) {
				resetPlayerAttack();
				return;
			}
			if(!c.getCombat().checkReqs()) {
				return;
			}
			if(Server.playerHandler.players[i].trade11 > 0) {
				c.sendMessage("You cannot attack a player that has just started the game!");
				c.sendMessage("this is to stop STG (Stop transfering gold)");
				resetPlayerAttack();
				return;
			}

			if(c.underAttackBy != 0 || c.underAttackBy2 > 0 || c.curseActive[9]) {
            }

			if (c.getPA().getWearingAmount() < 4 && c.duelStatus < 1) {
				c.sendMessage("You must be wearing at least 4 items to attack someone.");
				resetPlayerAttack();
				return;
			}
			
			boolean sameSpot = c.absX == Server.playerHandler.players[i].getX() && c.absY == Server.playerHandler.players[i].getY();
			if(!c.goodDistance(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), c.getX(), c.getY(), 25) && !sameSpot) {
				resetPlayerAttack();
				return;
			}

			if(Server.playerHandler.players[i].respawnTimer > 0) {
				Server.playerHandler.players[i].playerIndex = 0;
				resetPlayerAttack();
				return;
			}
			
			if (Server.playerHandler.players[i].heightLevel != c.heightLevel) {
				resetPlayerAttack();
				return;
			}
			/*if (c.attackTimer == 1) {
				applyLeeches(i);
			}*/

			//c.sendMessage("Made it here0.");
			c.followId = i;
			c.followId2 = 0;
			if(c.attackTimer <= 0) {
				c.usingBow = false;
				c.specEffect = 0;
				c.usingRangeWeapon = false;
				c.rangeItemUsed = 0;
				boolean usingBow = false;
				boolean usingArrows = false;
				boolean usingOtherRangeWeapons = false;

				boolean usingCross = c.playerEquipment[c.playerWeapon] == 9185;
				c.projectileStage = 0;
				
				if (c.absX == Server.playerHandler.players[i].absX && c.absY == Server.playerHandler.players[i].absY) {
					if (c.freezeTimer > 0) {
						resetPlayerAttack();
						return;
					}	
					c.followId = i;
					c.attackTimer = 0;
					return;
				}
				
				/*if ((c.inPirateHouse() && !Server.playerHandler.players[i].inPirateHouse()) || (Server.playerHandler.players[i].inPirateHouse() && !c.inPirateHouse())) {
					resetPlayerAttack();
					return;
				}*/
				//c.sendMessage("Made it here1.");
				if(!c.usingMagic) {
					for (int bowId : c.BOWS) {
						if(c.playerEquipment[c.playerWeapon] == bowId) {
							usingBow = true;
							for (int arrowId : c.ARROWS) {
								if(c.playerEquipment[c.playerArrows] == arrowId) {
									usingArrows = true;
								}
							}
						}
					}				
				
					for (int otherRangeId : c.OTHER_RANGE_WEAPONS) {
						if(c.playerEquipment[c.playerWeapon] == otherRangeId) {
							usingOtherRangeWeapons = true;
						}
					}
				}
				if (c.autocasting) {
					c.spellId = c.autocastId;
					c.usingMagic = true;
				}
				//c.sendMessage("Made it here2.");
				if(c.spellId > 0) {
                    c.usingMagic = true;
                }
				c.attackTimer = getAttackDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());

				if(c.duelRule[9]){
				boolean canUseWeapon = false;
					for(int funWeapon: Config.FUN_WEAPONS) {
						if(c.playerEquipment[c.playerWeapon] == funWeapon) {
							canUseWeapon = true;
						}
					}
					if(!canUseWeapon) {
						c.sendMessage("You can only use fun weapons in this duel!");
						resetPlayerAttack();
						return;
					}
				}
				//c.sendMessage("Made it here3.");
				if(c.duelRule[2] && (usingBow || usingOtherRangeWeapons)) {
					c.sendMessage("Range has been disabled in this duel!");
					return;
				}
				if(c.duelRule[3] && (!usingBow && !usingOtherRangeWeapons && !c.usingMagic)) {
					c.sendMessage("Melee has been disabled in this duel!");
					return;
				}
				
				if(c.duelRule[4] && c.usingMagic) {
					c.sendMessage("Magic has been disabled in this duel!");
					resetPlayerAttack();
					return;
				}
				
				if((!c.goodDistance(c.getX(), c.getY(), Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), 4) && (usingOtherRangeWeapons && !usingBow && !c.usingMagic)) 
				|| (!c.goodDistance(c.getX(), c.getY(), Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), 2) && (!usingOtherRangeWeapons && usingHally() && !usingBow && !c.usingMagic))
				|| (!c.goodDistance(c.getX(), c.getY(), Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), getRequiredDistance()) && (!usingOtherRangeWeapons && !usingHally() && !usingBow && !c.usingMagic)) 
				|| (!c.goodDistance(c.getX(), c.getY(), Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), 10) && (usingBow || c.usingMagic))) {
					//c.sendMessage("Setting attack timer to 1");
					c.attackTimer = 1;
					if (!usingBow && !c.usingMagic && !usingOtherRangeWeapons && c.freezeTimer > 0)
						resetPlayerAttack();
					return;
				}
				
				if(!usingCross && !usingArrows && usingBow && (c.playerEquipment[c.playerWeapon] < 4212 || c.playerEquipment[c.playerWeapon] > 4223) && !c.usingMagic) {
				if(c.playerEquipment[c.playerWeapon] == 15241)
					c.sendMessage("You have run out of Shots!");
				else
					c.sendMessage("You have run out of arrows!");
					c.stopMovement();
					resetPlayerAttack();
					return;
				}
				if(correctBowAndArrows() < c.playerEquipment[c.playerArrows] && Config.CORRECT_ARROWS && usingBow && !usingCrystalBow() && c.playerEquipment[c.playerWeapon] != 9185 && !c.usingMagic) {
					c.sendMessage("You can't use "+c.getItems().getItemName(c.playerEquipment[c.playerArrows]).toLowerCase()+"s with a "+c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()+".");
					c.stopMovement();
					resetPlayerAttack();
					return;
				}
				if (c.playerEquipment[c.playerWeapon] == 9185 && c.playerEquipment[c.playerWeapon] == 18357 && !properBolts() && !c.usingMagic) {
					c.sendMessage("You must use bolts with a crossbow.");
					c.stopMovement();
					resetPlayerAttack();
					return;				
				}
				
				
				if(usingBow || c.usingMagic || usingOtherRangeWeapons || usingHally()) {
					c.stopMovement();
				}
				
				if(!checkMagicReqs(c.spellId)) {
					c.stopMovement();
					resetPlayerAttack();
					return;
				}
				
				c.faceUpdate(i+32768);
				
				if(c.duelStatus != 5) {
					if(!c.attackedPlayers.contains(c.playerIndex) && !Server.playerHandler.players[c.playerIndex].attackedPlayers.contains(c.playerId)) {
						c.attackedPlayers.add(c.playerIndex);
						c.isSkulled = true;
						c.skullTimer = Config.SKULL_TIMER;
						c.headIconPk = 0;
						c.getPA().requestUpdates();
					} 
				}
				c.specAccuracy = 1.0;
				c.specDamage = 1.0;
				c.delayedDamage = c.delayedDamage2 = 0;
				if(c.usingSpecial && !c.usingMagic) {
					if(c.duelRule[10] && c.duelStatus == 5) {
						c.sendMessage("Special attacks have been disabled during this duel!");
						c.usingSpecial = false;
						c.getItems().updateSpecialBar();
						resetPlayerAttack();
						return;
					}
					if(checkSpecAmount(c.playerEquipment[c.playerWeapon])){
						c.lastArrowUsed = c.playerEquipment[c.playerArrows];
						activateSpecial(c.playerEquipment[c.playerWeapon], i);
						c.followId = c.playerIndex;
						return;
					} else {
						c.sendMessage("You don't have the required special energy to use this attack.");
						c.usingSpecial = false;
						c.getItems().updateSpecialBar();
						c.playerIndex = 0;
						return;
					}	
				}
				
				if(!c.usingMagic) {
					c.startAnimation(getWepAnim(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()));
					//c.sendSound(getAttackSound());
					c.mageFollow = false;
				} else {
					c.startAnimation(c.MAGIC_SPELLS[c.spellId][2]);
					c.mageFollow = true;
					c.followId = c.playerIndex;
				}
				Server.playerHandler.players[i].underAttackBy = c.playerId;
				Server.playerHandler.players[i].logoutDelay = System.currentTimeMillis();
				Server.playerHandler.players[i].singleCombatDelay = System.currentTimeMillis();
				Server.playerHandler.players[i].killerId = c.playerId;
				c.lastArrowUsed = 0;
				c.rangeItemUsed = 0;
								c.slayerHelmetEffect = false;
				if(!usingBow && !c.usingMagic && !c.usingSpecial && !usingOtherRangeWeapons) { // melee hit delay
					c.followId = Server.playerHandler.players[c.playerIndex].playerId;
					c.getPA().followPlayer(c.playerIndex);
					c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
					c.delayedDamage = Misc.random(calculateMeleeMaxHit());
					c.projectileStage = 0;
					c.oldPlayerIndex = i;
				}
								
				if(usingBow && !usingOtherRangeWeapons && !c.usingMagic || usingCross) { // range hit delay
					if(c.playerEquipment[c.playerWeapon] >= 4212 && c.playerEquipment[c.playerWeapon] <= 4223) {
						c.rangeItemUsed = c.playerEquipment[c.playerWeapon];
						c.crystalBowArrowCount++;
					} else {
						c.rangeItemUsed = c.playerEquipment[c.playerArrows];
						c.getItems().deleteArrow();
					}
					if (c.fightMode == 2)
						c.attackTimer--;
					if (usingCross)
						c.usingBow = true;
					c.usingBow = true;
					c.followId = Server.playerHandler.players[c.playerIndex].playerId;
					c.getPA().followPlayer(c.playerIndex);
					c.lastWeaponUsed = c.playerEquipment[c.playerWeapon];
					c.lastArrowUsed = c.playerEquipment[c.playerArrows];
					c.gfx100(getRangeStartGFX());	
					c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
					c.projectileStage = 1;
					c.oldPlayerIndex = i;
					fireProjectilePlayer();
				}
											
				if(usingOtherRangeWeapons) {	// knives, darts, etc hit delay
					c.rangeItemUsed = c.playerEquipment[c.playerWeapon];
					c.getItems().deleteEquipment();
					c.usingRangeWeapon = true;
					c.followId = Server.playerHandler.players[c.playerIndex].playerId;
					c.getPA().followPlayer(c.playerIndex);
					c.gfx100(getRangeStartGFX());
					if (c.fightMode == 2)
						c.attackTimer--;
					c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
					c.projectileStage = 1;
					c.oldPlayerIndex = i;
					fireProjectilePlayer();
				}

				if(c.usingMagic) {	// magic hit delay
					int pX = c.getX();
					int pY = c.getY();
					int nX = Server.playerHandler.players[i].getX();
					int nY = Server.playerHandler.players[i].getY();
					int offX = (pY - nY)* -1;
					int offY = (pX - nX)* -1;
					c.castingMagic = true;
					c.projectileStage = 2;
					if(c.MAGIC_SPELLS[c.spellId][3] > 0) {
						if(getStartGfxHeight() == 100) {
							c.gfx100(c.MAGIC_SPELLS[c.spellId][3]);
						} else {
							c.gfx0(c.MAGIC_SPELLS[c.spellId][3]);
						}
					}
					if(c.MAGIC_SPELLS[c.spellId][4] > 0) {
						c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 78, c.MAGIC_SPELLS[c.spellId][4], getStartHeight(), getEndHeight(), -i - 1, getStartDelay());
					}
					if (c.autocastId > 0) {
						c.followId = c.playerIndex;
						c.followDistance = 5;
					}	
					c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
					c.oldPlayerIndex = i;
					c.oldSpellId = c.spellId;
                    c.spellId = 0;
					Client o = (Client)Server.playerHandler.players[i];
					if(c.MAGIC_SPELLS[c.oldSpellId][0] == 12891 && o.isMoving) {
						//c.sendMessage("Barrage projectile..");
						c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 85, 368, 25, 25, -i - 1, getStartDelay());
					}
					if(Misc.random(mageAtk()) > Misc.random(o.getCombat().mageDef())) {
						c.magicFailed = false;
					} else if(Misc.random(mageAtk()) < Misc.random(o.getCombat().mageDef())) {
						c.magicFailed = true;
					}
					int freezeDelay = getFreezeTime();//freeze time
					if(freezeDelay > 0 && Server.playerHandler.players[i].freezeTimer <= -3 && !c.magicFailed) { 
						Server.playerHandler.players[i].freezeTimer = freezeDelay;
						o.resetWalkingQueue();
						o.sendMessage("You have been frozen.");
						o.barrageorb = 1;
						o.frozenBy = c.playerId;
					}
					if (!c.autocasting && c.spellId <= 0)
						c.playerIndex = 0;
				}
				if(c.curseActive[13]) { //Leech Defense
						int failed = Misc.random(5);
						Client c2 = (Client)PlayerHandler.players[i];
				if(failed < 5) {
						c.Ldefense = false;
					return;
				} else if(failed == 5) {
							c2.playerLevel[1] -= Misc.random(8);
				if (c2.playerLevel[1] < 1)
						c2.playerLevel[1] = 1;
						c2.getPA().refreshSkill(1);
				}
				if(c.oldPlayerIndex > 0) {
				if(PlayerHandler.players[c.oldPlayerIndex] != null) {
						final int pX = c.getX();
						final int pY = c.getY();
						final int nX = PlayerHandler.players[i].getX();
						final int nY = PlayerHandler.players[i].getY();
						final int offX = (pY - nY)* -1;
						final int offY = (pX - nX)* -1;
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, 50, 2242, 9, 9, - c.oldPlayerIndex - 1, 24, 0);
						c2.sendMessage("Your defense has been leeched by " +Misc.optimizeText(c.playerName)+"!");
						c2.gfx0(2246);
						c.startAnimation(12575);
						c.Ldefense = false;				
				}
			}
		}
				
				
				if(c.curseActive[14]) { //Leech Strength
						int failed = Misc.random(5);
						Client c2 = (Client)PlayerHandler.players[i];
				if(failed < 5) {
						c.Lstrength = false;
					return;
				} else if(failed == 5) {
							c2.playerLevel[2] -= Misc.random(8);
				if (c2.playerLevel[2] < 1)
						c2.playerLevel[2] = 1;
						c2.getPA().refreshSkill(2);
				}
				if(c.oldPlayerIndex > 0) {
				if(PlayerHandler.players[c.oldPlayerIndex] != null) {
						final int pX = c.getX();
						final int pY = c.getY();
						final int nX = PlayerHandler.players[i].getX();
						final int nY = PlayerHandler.players[i].getY();
						final int offX = (pY - nY)* -1;
						final int offY = (pX - nX)* -1;
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, 50, 2248, 9, 9, - c.oldPlayerIndex - 1, 24, 0);
						c2.sendMessage("Your strength has been leeched by " +Misc.optimizeText(c.playerName)+"!");
						c2.gfx0(2250);
						c.startAnimation(12575);
						c.Lstrength = false;				
				}
			}
		}
		
				if(c.curseActive[11]) { //Leech Ranged
						int failed = Misc.random(5);
						Client c2 = (Client)PlayerHandler.players[i];
				if(failed < 5) {
						c.Lranged = false;
					return;
				} else if(failed == 5) {
							c2.playerLevel[4] -= Misc.random(8);
				if (c2.playerLevel[4] < 1)
						c2.playerLevel[4] = 1;
						c2.getPA().refreshSkill(4);
				}
				if(c.oldPlayerIndex > 0) {
				if(PlayerHandler.players[c.oldPlayerIndex] != null) {
						final int pX = c.getX();
						final int pY = c.getY();
						final int nX = PlayerHandler.players[i].getX();
						final int nY = PlayerHandler.players[i].getY();
						final int offX = (pY - nY)* -1;
						final int offY = (pX - nX)* -1;
						c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, 50, 2236, 9, 9, - c.oldPlayerIndex - 1, 24, 0);
						c2.sendMessage("Your ranged has been leeched by " +Misc.optimizeText(c.playerName)+"!");
						c2.gfx0(2238);
						c.startAnimation(12575);
						c.Lranged = false;				
				}
			}
		}
				
				if(c.curseActive[12]) { //Leech Magic
						int failed = Misc.random(5);
						Client c2 = (Client)PlayerHandler.players[i];
				if(failed < 5) {
						c.Lmagic = false;
					return;
				} else if(failed == 5) {
							c2.playerLevel[6] -= Misc.random(8);
				if (c2.playerLevel[6] < 1)
						c2.playerLevel[6] = 1;
						c2.getPA().refreshSkill(6);
				}
				if(c.oldPlayerIndex > 0) {
				if(PlayerHandler.players[c.oldPlayerIndex] != null) {
						final int pX = c.getX();
						final int pY = c.getY();
						final int nX = PlayerHandler.players[i].getX();
						final int nY = PlayerHandler.players[i].getY();
						final int offX = (pY - nY)* -1;
						final int offY = (pX - nX)* -1;
						c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, 50, 2240, 9, 9, - c.oldPlayerIndex - 1, 24, 0);
						c2.sendMessage("Your ranged has been leeched by " +Misc.optimizeText(c.playerName)+"!");
						c2.gfx0(2242);
						c.startAnimation(12575);
						c.Lmagic = false;				
				}
			}
		}

				if(c.curseActive[16]) { //Leech Special
						int failed = Misc.random(10);
						Client c2 = (Client)PlayerHandler.players[i];
				if(failed < 10) {
						c.Lspecial = false;
					return;
				} else if(failed == 10) {
							c2.specAmount -= 0.5;
							//c2.specAmount -= Misc.random(8);
				if (c2.specAmount < 1)
						c2.specAmount = 1;
				}
				if(c.oldPlayerIndex > 0) {
				if(PlayerHandler.players[c.oldPlayerIndex] != null) {
						final int pX = c.getX();
						final int pY = c.getY();
						final int nX = PlayerHandler.players[i].getX();
						final int nY = PlayerHandler.players[i].getY();
						final int offX = (pY - nY)* -1;
						final int offY = (pX - nX)* -1;
						c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, 50, 2256, 9, 9, - c.oldPlayerIndex - 1, 24, 0);
						c2.sendMessage("Your special attack has been leeched by " +Misc.optimizeText(c.playerName)+"!");
						c2.gfx0(2258);
						c.startAnimation(12575);
						c.getItems().updateSpecialBar();
						c.Lspecial = false;									
				}
			}
		}

				if(usingBow && Config.CRYSTAL_BOW_DEGRADES) { // crystal bow degrading
					if(c.playerEquipment[c.playerWeapon] == 4212) { // new crystal bow becomes full bow on the first shot
						c.getItems().wearItem(4214, 1, 3);
					}
					
					if(c.crystalBowArrowCount >= 250){
						switch(c.playerEquipment[c.playerWeapon]) {
							
							case 4223: // 1/10 bow
							c.getItems().wearItem(-1, 1, 3);
							c.sendMessage("Your crystal bow has fully degraded.");
							if(!c.getItems().addItem(4207, 1)) {
								Server.itemHandler.createGroundItem(c, 4207, c.getX(), c.getY(), 1, c.getId());
							}
							c.crystalBowArrowCount = 0;
							break;
							
							default:
							c.getItems().wearItem(++c.playerEquipment[c.playerWeapon], 1, 3);
							c.sendMessage("Your crystal bow degrades.");
							c.crystalBowArrowCount = 0;
							break;
						}
					}	
				}
			}
		}
	}
	
	public boolean usingCrystalBow() {
		return c.playerEquipment[c.playerWeapon] >= 4212 && c.playerEquipment[c.playerWeapon] <= 4223;	
	}
	
	public void appendVengeance(int otherPlayer, int damage) {
		if (damage <= 0)
			return;
		Player o = Server.playerHandler.players[otherPlayer];
		o.forcedText = "Taste Vengeance!";
		o.forcedChatUpdateRequired = true;
		o.updateRequired = true;
		o.vengOn = false;
		if ((o.playerLevel[3] - damage) > 0) {
			damage = (int)(damage * 0.75);
			if (damage > c.playerLevel[3]) {
				damage = c.playerLevel[3];
			}
			c.hitDiff2 = new Hit(damage, CombatType.MAGE);
			c.setHitUpdateRequired2(true);
			c.playerLevel[3] -= damage;
			c.getPA().refreshSkill(3);
		}	
		c.updateRequired = true;
	}

	public void appendVengeanceNPC(int otherPlayer, int damage) {
		if (damage <= 0)
			return;
		if (c.npcIndex > 0 && Server.npcHandler.npcs[c.npcIndex] != null) {
			c.forcedText = "Taste Vengeance!";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			c.vengOn = false;
			if ((Server.npcHandler.npcs[c.npcIndex].HP - damage) > 0) {
				damage = (int)(damage * 0.75);
				if (damage > Server.npcHandler.npcs[c.npcIndex].HP) {
					damage = Server.npcHandler.npcs[c.npcIndex].HP;
				}
				Server.npcHandler.npcs[c.npcIndex].HP -= damage;
				Server.npcHandler.npcs[c.npcIndex].hitDiff2 = new Hit(damage, CombatType.MAGE);
				Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
				Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
			}
		}	
		c.updateRequired = true;
	}
	
	public void playerDelayedHit(int i) {
		if (Server.playerHandler.players[i] != null) {
			if (Server.playerHandler.players[i].isDead || c.isDead || Server.playerHandler.players[i].playerLevel[3] <= 0 || c.playerLevel[3] <= 0) {
				c.playerIndex = 0;
				return;
			}
			if (Server.playerHandler.players[i].respawnTimer > 0) {
				c.faceUpdate(0);
				c.playerIndex = 0;
				return;
			}
			Client o = (Client) Server.playerHandler.players[i];
			o.getPA().removeAllWindows();
			if (o.playerIndex <= 0 && o.npcIndex <= 0) {
				if (o.autoRet == 1) {
					o.playerIndex = c.playerId;
				}	
			}
			if(o.attackTimer <= 3 || o.attackTimer == 0 && o.playerIndex == 0 && !c.castingMagic) { // block animation
				o.startAnimation(o.getCombat().getBlockEmote());
			}
			if(o.inTrade) {
				o.getTradeAndDuel().declineTrade();
			}
			if(c.projectileStage == 0 && !c.usingMagic && !c.castingMagic) { // melee hit damage								
					applyPlayerMeleeDamage(i, 1, Misc.random(calculateMeleeMaxHit()));
					if(c.doubleHit && !c.usingClaws) {
						applyPlayerMeleeDamage(i, 2, Misc.random(calculateMeleeMaxHit()));
					}	
					if(c.doubleHit && c.usingClaws) {
						applyPlayerMeleeDamage(i, 2, c.previousDamage / 2);
					}
			}
			
			if(!c.castingMagic && c.projectileStage > 0) { // range hit damage
				int damage = Misc.random(rangeMaxHit());
				int damage2 = -1;
				int PrayerDrain = damage / 4;
				if (c.lastWeaponUsed == 15701 || c.lastWeaponUsed == 15702 || c.lastWeaponUsed == 11235 || c.lastWeaponUsed == 15703 || c.lastWeaponUsed == 15704 || c.bowSpecShot == 1)
					damage2 = Misc.random(rangeMaxHit());
				boolean ignoreDef = false;
				if(c.playerEquipment[3] == 9185) {
					if(Misc.random(5) == 1) {
						if(damage > 0) {
							c.boltDamage = damage;
							CrossbowEffects.crossbowSpecial(c,i);
							damage *= c.crossbowDamage;
						}
					}
				}				
				if(Misc.random(10+o.getCombat().calculateRangeDefence()) > Misc.random(10+calculateRangeAttack()) && !ignoreDef) {
					damage = 0;
				}
				
				if (c.lastWeaponUsed == 11235 || c.lastWeaponUsed == 15701 || c.lastWeaponUsed == 15702 || c.lastWeaponUsed == 15703 || c.lastWeaponUsed == 15704 || c.bowSpecShot == 1) {
					if (Misc.random(10+o.getCombat().calculateRangeDefence()) > Misc.random(10+calculateRangeAttack()))
						damage2 = 0;
				}
								
				if (c.dbowSpec) {
					o.gfx100(1100);
					if (damage < 8)
						damage = 8;
					if (damage2 < 8)
						damage2 = 8;
					c.dbowSpec = false;
				}
				if(o.curseActive[8] && !c.castingMagic && c.projectileStage > 0 && damage > 0) {//Range Deflect
					appendDeflect(i, damage);
				}
				if(o.prayerActive[17] || o.curseActive[8] && System.currentTimeMillis() - o.protRangeDelay > 1500) { // if prayer active reduce damage by half 
					damage = (int)damage * 60 / 100;
					if (o.playerEquipment[o.playerShield] == 15023 && o.playerLevel[5] >= 1 && damage >= 1) {
					//if (Misc.random(2) == 1) {
					damage = (int)damage * 42 / 100;
					damage2 = (int)damage2 * 42 / 100;
  					o.playerLevel[5] -= PrayerDrain;
					o.getPA().refreshSkill(5);

					if (o.playerLevel[5] <= 0) {
					o.playerLevel[5] = 0;
					o.getCombat().resetPrayers();
					o.getPA().refreshSkill(5);
					//}
					}
					}

					if (o.playerEquipment[o.playerShield] == 15026 && !o.prayerActive[17] || !o.curseActive[8] && damage >= 1) {
					if(Misc.random(4) == 3) {
					damage = (int)damage * 65 / 100;
					damage2 = (int)damage2 * 65 / 100;
					}
					}

					if (c.lastWeaponUsed == 11235 || c.lastWeaponUsed == 15701 || c.lastWeaponUsed == 15702 || c.lastWeaponUsed == 15703 || c.lastWeaponUsed == 15704 || c.bowSpecShot == 1)
						damage2 = (int)damage2 * 60 / 100;
				}
					if (o.playerEquipment[o.playerWeapon] == 15486 && damage >= 1 && o.SolProtect >= 1) {
					damage = (int)damage / 2;
					damage2 = (int)damage2 / 2;
					}

					if (o.playerEquipment[o.playerShield] == 15023 && !o.prayerActive[17] || !o.curseActive[8] && o.playerLevel[5] >= 1 && damage >= 1) {
					//if(Misc.random(2) == 1) {
					damage = (int)damage * 70 / 100;
					damage2 = (int)damage2 * 70 / 100;
					o.playerLevel[5] -= PrayerDrain;
					o.getPA().refreshSkill(5);

					if (o.playerLevel[5] <= 0) {
					o.playerLevel[5] = 0;
					o.getCombat().resetPrayers();
					o.getPA().refreshSkill(5);
					//}
					}
					}
				if (Server.playerHandler.players[i].playerLevel[3] - damage < 0) { 
					damage = Server.playerHandler.players[i].playerLevel[3];
				}
				if (Server.playerHandler.players[i].playerLevel[3] - damage - damage2 < 0) { 
					damage2 = Server.playerHandler.players[i].playerLevel[3] - damage;
				}
				if (damage < 0)
					damage = 0;
				if (damage2 < 0 && damage2 != -1)
					damage2 = 0;
				if (o.vengOn) {
					appendVengeance(i, damage);
					appendVengeance(i, damage2);
				}
				if (damage > 0)
					applyRecoil(damage, i);
					//Deflect(damage, i);
				if (damage2 > 0)
					applyRecoil(damage2, i);
					//Deflect(damage2, i);
				if(c.fightMode == 3) {
					c.getPA().addSkillXP((damage*Config.RANGE_EXP_RATE/3), 4); 
					c.getPA().addSkillXP((damage*Config.RANGE_EXP_RATE/3), 1);				
					c.getPA().addSkillXP((damage*Config.RANGE_EXP_RATE/3), 3);
					c.getPA().refreshSkill(1);
					c.getPA().refreshSkill(3);
					c.getPA().refreshSkill(4);
				} else {
					c.getPA().addSkillXP((damage*Config.RANGE_EXP_RATE), 4); 
					c.getPA().addSkillXP((damage*Config.RANGE_EXP_RATE/3), 3);
					c.getPA().refreshSkill(3);
					c.getPA().refreshSkill(4);
				}
				boolean dropArrows = true;
						
				for(int noArrowId : c.NO_ARROW_DROP) {
					if(c.lastWeaponUsed == noArrowId) {
						dropArrows = false;
						break;
					}
				}
				if(dropArrows) {
					c.getItems().dropArrowPlayer();	
				}
				Server.playerHandler.players[i].underAttackBy = c.playerId;
				Server.playerHandler.players[i].logoutDelay = System.currentTimeMillis();
				Server.playerHandler.players[i].singleCombatDelay = System.currentTimeMillis();
				Server.playerHandler.players[i].killerId = c.playerId;
				//Server.playerHandler.players[i].hitDiff = new Hit(damage);
				//Server.playerHandler.players[i].playerLevel[3] -= damage;
				Server.playerHandler.players[i].dealDamage(damage);
				Server.playerHandler.players[i].damageTaken[c.playerId] += damage;
				c.killedBy = Server.playerHandler.players[i].playerId;
				Server.playerHandler.players[i].handleHitMask(damage, CombatType.RANGE);
				int [] rangeDouble = {11235};
for (int j = 0; j < rangeDouble.length; j++) {
if (damage2 != -1 && rangeDouble[j] == c.playerEquipment[c.playerWeapon] || c.bowSpecShot == 1) {
//Server.playerHandler.players[i].playerLevel[3] -= damage2;
Server.playerHandler.players[i].dealDamage(damage2);
Server.playerHandler.players[i].damageTaken[c.playerId] += damage2;

Server.playerHandler.players[i].handleHitMask(damage2, CombatType.RANGE);

}
} 
				/*if (damage2 != -1) {
					//Server.playerHandler.players[i].playerLevel[3] -= damage2;
					Server.playerHandler.players[i].dealDamage(damage2);
					Server.playerHandler.players[i].damageTaken[c.playerId] += damage2;
					Server.playerHandler.players[i].handleHitMask(damage2, CombatType.RANGE);
				
				}*/
				o.getPA().refreshSkill(3);
					
				//Server.playerHandler.players[i].setHitUpdateRequired(true);	
				Server.playerHandler.players[i].updateRequired = true;
				applySmite(i, damage);
				if (c.soulSplitDelay <= 0) {
					applySoulSplit(i, damage);
				}
				if (damage2 != -1)
					if (c.soulSplitDelay <= 0) {
						applySoulSplit(i, damage);
					}
					applySmite(i, damage2);
			
			} else if (c.projectileStage > 0) { // magic hit damage
				int damage = Misc.random(c.MAGIC_SPELLS[c.oldSpellId][6]);
				if(godSpells()) {
					if(System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
						damage += 10;
					}
				}
				if(c.playerEquipment[c.playerWeapon] == 15486) {
						damage += 10;
					}
				int PrayerDrain = damage / 4;
				//c.playerIndex = 0;
				if (c.magicFailed)
					damage = 0;
					
				if(o.prayerActive[16] || o.curseActive[7] && System.currentTimeMillis() - o.protMageDelay > 1500) { // if prayer active reduce damage by half 
					damage = (int)damage * 60 / 100;
 					if (o.playerEquipment[o.playerShield] == 15023 && o.playerLevel[5] >= 1 && damage >= 1) {
					//if (Misc.random(2) == 1) {
					damage = (int)damage * 42 / 100;
  					o.playerLevel[5] -= PrayerDrain;
					o.getPA().refreshSkill(5);

					if (o.playerLevel[5] <= 0) {
					o.playerLevel[5] = 0;
					o.getCombat().resetPrayers();
					o.getPA().refreshSkill(5);
					}
					}
					}
					//}


					if (o.playerEquipment[o.playerShield] == 15026 && !o.prayerActive[16] || !o.curseActive[7] && damage >= 1) {
					if(Misc.random(4) == 3) {
					damage = (int)damage * 65 / 100;
					}
					}

					if (o.playerEquipment[o.playerWeapon] == 15486 && damage >= 1 && o.SolProtect >= 1) {
					damage = (int)damage / 2;
					}

 					if (o.playerEquipment[o.playerShield] == 15023 && !o.prayerActive[16] || !o.curseActive[7] && o.playerLevel[5] >= 1 && damage >= 1) {
					//if(Misc.random(2) == 1) {
					damage = (int)damage * 70 / 100;
					o.getPA().refreshSkill(5);
					o.playerLevel[5] -= PrayerDrain;

					if (o.playerLevel[5] <= 0) {
					o.playerLevel[5] = 0;
					o.getCombat().resetPrayers();
					o.getPA().refreshSkill(5);
					//}
					}
					}
				if (Server.playerHandler.players[i].playerLevel[3] - damage < 0) {
					damage = Server.playerHandler.players[i].playerLevel[3];
				}
				if(o.curseActive[7] && damage > 0) {//Mage Deflect
					appendDeflect(i, damage);
				}
				if (o.vengOn)
					appendVengeance(i, damage);
				if (damage > 0)
					//applyRecoil(damage, i);
					//Deflect(damage, i);
				c.getPA().addSkillXP((c.MAGIC_SPELLS[c.oldSpellId][7] + damage*Config.MAGIC_EXP_RATE), 6); 
				c.getPA().addSkillXP((c.MAGIC_SPELLS[c.oldSpellId][7] + damage*Config.MAGIC_EXP_RATE/3), 3);
				c.getPA().refreshSkill(3);
				c.getPA().refreshSkill(6);
				
				if(getEndGfxHeight() == 100 && !c.magicFailed){ // end GFX
					Server.playerHandler.players[i].gfx100(c.MAGIC_SPELLS[c.oldSpellId][5]);
				} else if (!c.magicFailed){
					Server.playerHandler.players[i].gfx0(c.MAGIC_SPELLS[c.oldSpellId][5]);
				} else if(c.magicFailed) {	
					Server.playerHandler.players[i].gfx100(85);
				}
				
				if(!c.magicFailed) {
					if(System.currentTimeMillis() - Server.playerHandler.players[i].reduceStat > 35000) {
						Server.playerHandler.players[i].reduceStat = System.currentTimeMillis();
						switch(c.MAGIC_SPELLS[c.oldSpellId][0]) { 
							case 12987:
							case 13011:
							case 12999:
							case 13023:
							Server.playerHandler.players[i].playerLevel[0] -= ((o.getPA().getLevelForXP(Server.playerHandler.players[i].playerXP[0]) * 10) / 100);
							break;
						}
					}
					
					switch(c.MAGIC_SPELLS[c.oldSpellId][0]) { 	
						case 12445: //teleblock
						if (System.currentTimeMillis() - o.teleBlockDelay > o.teleBlockLength) {
							o.teleBlockDelay = System.currentTimeMillis();
							o.sendMessage("You have been teleblocked.");
							if (o.prayerActive[16] || o.curseActive[7] && System.currentTimeMillis() - o.protMageDelay > 1500)
								o.teleBlockLength = 150000;
							else
								o.teleBlockLength = 300000;
						}			
						break;

					case 12871:
						if (o.barrageorb == 1) {
						o.barrageorb = 0;
						}
						break;

					case 12891:			
						if (o.barrageorb != 1) {
						o.gfx50(1677);
						}
						if (o.barrageorb == 1) {
						o.barrageorb = 0;
						}
						break;

						
						case 12901:
						case 12919: // blood spells
						case 12911:
						case 12929:
						int heal = (int)(damage / 4);
						if(c.playerLevel[3] + heal > c.getPA().getLevelForXP(c.playerXP[3])) {
							c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
						} else {
							c.playerLevel[3] += heal;
						}
						c.getPA().refreshSkill(3);
						break;
						
						case 1153:						
						Server.playerHandler.players[i].playerLevel[0] -= ((o.getPA().getLevelForXP(Server.playerHandler.players[i].playerXP[0]) * 5) / 100);
						o.sendMessage("Your attack level has been reduced!");
						Server.playerHandler.players[i].reduceSpellDelay[c.reduceSpellId] = System.currentTimeMillis();
						o.getPA().refreshSkill(0);
						break;
						
						case 1157:
						Server.playerHandler.players[i].playerLevel[2] -= ((o.getPA().getLevelForXP(Server.playerHandler.players[i].playerXP[2]) * 5) / 100);
						o.sendMessage("Your strength level has been reduced!");
						Server.playerHandler.players[i].reduceSpellDelay[c.reduceSpellId] = System.currentTimeMillis();						
						o.getPA().refreshSkill(2);
						break;
						
						case 1161:
						Server.playerHandler.players[i].playerLevel[1] -= ((o.getPA().getLevelForXP(Server.playerHandler.players[i].playerXP[1]) * 5) / 100);
						o.sendMessage("Your defence level has been reduced!");
						Server.playerHandler.players[i].reduceSpellDelay[c.reduceSpellId] = System.currentTimeMillis();					
						o.getPA().refreshSkill(1);
						break;
						
						case 1542:
						Server.playerHandler.players[i].playerLevel[1] -= ((o.getPA().getLevelForXP(Server.playerHandler.players[i].playerXP[1]) * 10) / 100);
						o.sendMessage("Your defence level has been reduced!");
						Server.playerHandler.players[i].reduceSpellDelay[c.reduceSpellId] =  System.currentTimeMillis();
						o.getPA().refreshSkill(1);
						break;
						
						case 1543:
						Server.playerHandler.players[i].playerLevel[2] -= ((o.getPA().getLevelForXP(Server.playerHandler.players[i].playerXP[2]) * 10) / 100);
						o.sendMessage("Your strength level has been reduced!");
						Server.playerHandler.players[i].reduceSpellDelay[c.reduceSpellId] = System.currentTimeMillis();
						o.getPA().refreshSkill(2);
						break;
						
						case 1562:					
						Server.playerHandler.players[i].playerLevel[0] -= ((o.getPA().getLevelForXP(Server.playerHandler.players[i].playerXP[0]) * 10) / 100);
						o.sendMessage("Your attack level has been reduced!");
						Server.playerHandler.players[i].reduceSpellDelay[c.reduceSpellId] = System.currentTimeMillis();					
						o.getPA().refreshSkill(0);
						break;
					}					
				}
				
				Server.playerHandler.players[i].logoutDelay = System.currentTimeMillis();
				Server.playerHandler.players[i].underAttackBy = c.playerId;
				Server.playerHandler.players[i].killerId = c.playerId;
				Server.playerHandler.players[i].singleCombatDelay = System.currentTimeMillis();
				if(c.MAGIC_SPELLS[c.oldSpellId][6] != 0) {
					//Server.playerHandler.players[i].playerLevel[3] -= damage;
					Server.playerHandler.players[i].dealDamage(damage);
					Server.playerHandler.players[i].damageTaken[c.playerId] += damage;
					c.totalPlayerDamageDealt += damage;
					if (!c.magicFailed) {
						//Server.playerHandler.players[i].hitDiff = new Hit(damage);
						//Server.playerHandler.players[i].setHitUpdateRequired(true);
						Server.playerHandler.players[i].handleHitMask(damage, CombatType.MAGE);
					}
				}
				applySmite(i, damage);
				if (c.soulSplitDelay <= 0) {
					applySoulSplit(i, damage);
				}
				c.killedBy = Server.playerHandler.players[i].playerId;	
				o.getPA().refreshSkill(3);
				Server.playerHandler.players[i].updateRequired = true;
				c.usingMagic = false;
				c.castingMagic = false;
				if (o.inMulti() && multis()) {
					c.barrageCount = 0;
					for (int j = 0; j < Server.playerHandler.players.length; j++) {
						if (Server.playerHandler.players[j] != null) {
							if (j == o.playerId)
								continue;
							if (c.barrageCount >= 9)
								break;
							if (o.goodDistance(o.getX(), o.getY(), Server.playerHandler.players[j].getX(), Server.playerHandler.players[j].getY(), 1))
								appendMultiBarrage(j, c.magicFailed);
						}	
					}
				}
				c.getPA().refreshSkill(3);
				c.getPA().refreshSkill(6);
				c.oldSpellId = 0;
			}
		}	
		c.getPA().requestUpdates();
		int oldindex = c.oldPlayerIndex;
		if(c.bowSpecShot <= 0) {
			c.oldPlayerIndex = 0;	
			c.projectileStage = 0;
			c.lastWeaponUsed = 0;
			c.doubleHit = false;
			c.bowSpecShot = 0;
		}
		if(c.bowSpecShot != 0) {
			c.bowSpecShot = 0;
		}
	}
	
	public boolean multis() {
		switch (c.MAGIC_SPELLS[c.oldSpellId][0]) {
			case 12891:
			case 12881:
			case 13011:
			case 13023:
			case 12919: // blood spells
			case 12929:
			case 12963:
			case 12975:
			return true;
		}
		return false;
	
	}
	public void appendMultiBarrage(int playerId, boolean splashed) {
		if (Server.playerHandler.players[playerId] != null) {
			Client c2 = (Client)Server.playerHandler.players[playerId];
			if (c2.isDead || c2.respawnTimer > 0)
				return;
			if (checkMultiBarrageReqs(playerId)) {
				c.barrageCount++;
				if (Misc.random(mageAtk()) > Misc.random(mageDef()) && !c.magicFailed) {
					if(getEndGfxHeight() == 100){ // end GFX
						c2.gfx100(c.MAGIC_SPELLS[c.oldSpellId][5]);
					} else {
						c2.gfx0(c.MAGIC_SPELLS[c.oldSpellId][5]);
					}
					int damage = Misc.random(c.MAGIC_SPELLS[c.oldSpellId][6]);
					if (c2.prayerActive[12]) {
						damage *= (int)(.60);
					}
					if (c2.playerLevel[3] - damage < 0) {
						damage = c2.playerLevel[3];					
					}
					c.getPA().addSkillXP((c.MAGIC_SPELLS[c.oldSpellId][7] + damage*Config.MAGIC_EXP_RATE), 6); 
					c.getPA().addSkillXP((c.MAGIC_SPELLS[c.oldSpellId][7] + damage*Config.MAGIC_EXP_RATE/3), 3);
					//Server.playerHandler.players[playerId].hitDiff = new Hit(damage);
					//Server.playerHandler.players[playerId].setHitUpdateRequired(true);
					Server.playerHandler.players[playerId].handleHitMask(damage, CombatType.MAGE);
					//Server.playerHandler.players[playerId].playerLevel[3] -= damage;
 					Server.playerHandler.players[playerId].dealDamage(damage);
					Server.playerHandler.players[playerId].damageTaken[c.playerId] += damage;
					c2.getPA().refreshSkill(3);
					c.totalPlayerDamageDealt += damage;
					multiSpellEffect(playerId, damage);
				} else {
					c2.gfx100(85);
				}			
			}		
		}	
	}
	
	public void multiSpellEffect(int playerId, int damage) {					
		switch(c.MAGIC_SPELLS[c.oldSpellId][0]) {
			case 13011:
			case 13023:
			if(System.currentTimeMillis() - Server.playerHandler.players[playerId].reduceStat > 35000) {
				Server.playerHandler.players[playerId].reduceStat = System.currentTimeMillis();
				Server.playerHandler.players[playerId].playerLevel[0] -= ((Server.playerHandler.players[playerId].getLevelForXP(Server.playerHandler.players[playerId].playerXP[0]) * 10) / 100);
			}	
			break;
			case 12919: // blood spells
			case 12929:
				int heal = (int)(damage / 4);
				if(c.playerLevel[3] + heal >= c.getPA().getLevelForXP(c.playerXP[3])) {
					c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				} else {
					c.playerLevel[3] += heal;
				}
				c.getPA().refreshSkill(3);
			break;
			case 12891:
			case 12881:
				if (Server.playerHandler.players[playerId].freezeTimer < -4) {
					Server.playerHandler.players[playerId].freezeTimer = getFreezeTime();
					Server.playerHandler.players[playerId].stopMovement();
				}
			break;
		}	
	}
	public void applyPlayerClawDamage(int i, int damageMask, int damage){
		int PrayerDrain = damage / 4;
		Client o = (Client) Server.playerHandler.players[i];
		if(o == null) {
			return;
		}

		c.previousDamage = damage;
		boolean veracsEffect = false;
		boolean guthansEffect = false;
		if (c.getPA().fullVeracs()) {
			if (Misc.random(4) == 1) {
				veracsEffect = true;				
			}		
		}
		if (c.getPA().fullGuthans()) {
			if (Misc.random(4) == 1) {
				guthansEffect = true;
			}		
		}
		if (damageMask == 1) {
			damage = c.delayedDamage;
			c.delayedDamage = 0;
		} else {
			damage = c.delayedDamage2;
			c.delayedDamage2 = 0;
		}
		if(Misc.random(o.getCombat().calculateMeleeDefence()) > Misc.random(calculateMeleeAttack()) && !veracsEffect) {
			damage = 0;
			c.bonusAttack = 0;
		} else if (c.playerEquipment[c.playerWeapon] == 5698 && o.poisonDamage <= 0 && Misc.random(3) == 1) {
			o.getPA().appendPoison(13);
			c.bonusAttack += damage/3;
		} else {
			c.bonusAttack += damage/3;
		}
		if(o.prayerActive[18] || o.curseActive[9] && System.currentTimeMillis() - o.protMeleeDelay > 1500 && !veracsEffect) { // if prayer active reduce damage by 40%
					damage = (int)damage * 60 / 100;
					if (o.playerEquipment[o.playerShield] == 15023 && o.playerLevel[5] >= 1 && damage >= 1) {
					//if (Misc.random(2) == 1) {
					damage = (int)damage * 42 / 100;
  					o.playerLevel[5] -= PrayerDrain;
					o.getPA().refreshSkill(5);

					if (o.playerLevel[5] <= 0) {
					o.playerLevel[5] = 0;
					o.getCombat().resetPrayers();
					o.getPA().refreshSkill(5);
					//}
					}
					}
					}

					if (o.playerEquipment[o.playerWeapon] == 15486 && damage >= 1 && o.SolProtect >= 1) {
					damage = (int)damage / 2;
					}

					if (o.playerEquipment[o.playerShield] == 15026 && !o.prayerActive[18] || !o.curseActive[9] && damage >= 1) {
					if(Misc.random(4) == 3) {
					damage = (int)damage * 65 / 100;
					}
					}

 					if (o.playerEquipment[o.playerShield] == 15023 && !o.prayerActive[18] || !o.curseActive[9] && o.playerLevel[5] >= 1 && damage >= 1) {
					//if(Misc.random(2) == 1) {
					damage = (int)damage * 70 / 100;
					o.getPA().refreshSkill(5);
					o.playerLevel[5] -= PrayerDrain;

					if (o.playerLevel[5] <= 0) {
					o.playerLevel[5] = 0;
					o.getCombat().resetPrayers();
					o.getPA().refreshSkill(5);
					}
					//}
					}
		if(Misc.random(o.getCombat().calculateMeleeDefence()) > Misc.random(calculateMeleeAttack()) && !veracsEffect) {
			damage = 0;
			c.bonusAttack = 0;
		} else if (c.playerEquipment[c.playerWeapon] == 5698 && o.poisonDamage <= 0 && Misc.random(3) == 1) {
			o.getPA().appendPoison(13);
			c.bonusAttack += damage/3;
		} else {
			c.bonusAttack += damage/3;
		}
		if(o.prayerActive[18] && System.currentTimeMillis() - o.protMeleeDelay > 1500 && !veracsEffect) { // if prayer active reduce damage by 40%
			damage = (int)damage * 60 / 100;
		}
		if (c.maxNextHit) {
			damage = calculateMeleeMaxHit();
		}
		if (damage > 0 && guthansEffect) {
			c.playerLevel[3] += damage;
			if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
				c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
			c.getPA().refreshSkill(3);
			o.gfx0(398);		
		}
		if (c.ssSpec && damageMask == 2) {
			damage = 5 + Misc.random(11);
			c.ssSpec = false;
		}
		if (Server.playerHandler.players[i].playerLevel[3] - damage < 0) { 
			damage = Server.playerHandler.players[i].playerLevel[3];
		}
		if (o.vengOn && damage > 0)
			appendVengeance(i, damage);
		if (damage > 0)
		applyRecoil(damage, i);
		if(o.curseActive[9] && damage > 0) {//Melee Deflect
			appendDeflect(i, damage);
		}
		switch(c.specEffect) {
			case 1: // dragon scimmy special
			if(damage > 0) {
				if(o.prayerActive[16] || o.prayerActive[17] || o.prayerActive[18] || o.curseActive[7] || o.curseActive[8] || o.curseActive[9]) {
					o.headIcon = -1;
					o.getPA().sendFrame36(c.PRAYER_GLOW[16], 0);
					o.getPA().sendFrame36(c.PRAYER_GLOW[17], 0);
					o.getPA().sendFrame36(c.PRAYER_GLOW[18], 0);	
					o.getPA().sendFrame36(c.CURSE_GLOW[7], 0);
					o.getPA().sendFrame36(c.CURSE_GLOW[8], 0);
					o.getPA().sendFrame36(c.CURSE_GLOW[9], 0);					
				}
				o.sendMessage("You have been injured!");
				o.stopPrayerDelay = System.currentTimeMillis();
				o.prayerActive[16] = false;
				o.prayerActive[17] = false;
				o.prayerActive[18] = false;
				o.curseActive[7] = false;
				o.curseActive[8] = false;
				o.curseActive[9] = false;
				o.getPA().requestUpdates();		
			}
			break;
			case 2:
				if (damage > 0) {
					if (o.freezeTimer <= 0)
						o.freezeTimer = 30;
					o.gfx0(369);
					o.sendMessage("You have been frozen.");
					o.frozenBy = c.playerId;
					o.stopMovement();
					c.sendMessage("You freeze your enemy.");
				}		
			break;
			case 3:
				if (damage > 0) {
					o.playerLevel[1] -= damage;
					o.sendMessage("You feel weak.");
					if (o.playerLevel[1] < 1)
						o.playerLevel[1] = 1;
					o.getPA().refreshSkill(1);
				}
			break;
			case 4:
				if (damage > 0) {
					if (c.playerLevel[3] + damage > c.getLevelForXP(c.playerXP[3]))
						if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]));
						else 
						c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
					else 
						c.playerLevel[3] += damage;
					c.getPA().refreshSkill(3);
				}
			break;
			case 5:
			c.clawDelay = 2;
			break;
                        case 6:
                        o.vestaDelay = 12;
                        break;

		}
		c.specEffect = 0;
		if(c.fightMode == 3) {
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 0); 
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 1);
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 2); 				
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 3);
			c.getPA().refreshSkill(0);
			c.getPA().refreshSkill(1);
			c.getPA().refreshSkill(2);
			c.getPA().refreshSkill(3);
		} else {
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE), c.fightMode); 
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 3);
			c.getPA().refreshSkill(c.fightMode);
			c.getPA().refreshSkill(3);
		}
		Server.playerHandler.players[i].logoutDelay = System.currentTimeMillis();
		Server.playerHandler.players[i].underAttackBy = c.playerId;
		Server.playerHandler.players[i].killerId = c.playerId;	
		Server.playerHandler.players[i].singleCombatDelay = System.currentTimeMillis();
		if (c.killedBy != Server.playerHandler.players[i].playerId)
			c.totalPlayerDamageDealt = 0;
		c.killedBy = Server.playerHandler.players[i].playerId;
		applySmite(i, damage);
		if (c.soulSplitDelay <= 0) {
			applySoulSplit(i, damage);
		}
		switch(damageMask) {
			case 1:
			/*if (!Server.playerHandler.players[i].getHitUpdateRequired()){
				Server.playerHandler.players[i].hitDiff = new Hit(damage);
				Server.playerHandler.players[i].setHitUpdateRequired(true);
			} else {
				Server.playerHandler.players[i].hitDiff2 = new Hit(damage);
				Server.playerHandler.players[i].setHitUpdateRequired2(true);			
			}*/
			//Server.playerHandler.players[i].playerLevel[3] -= damage;
			Server.playerHandler.players[i].dealDamage(damage);
			Server.playerHandler.players[i].damageTaken[c.playerId] += damage;
			c.totalPlayerDamageDealt += damage;
			Server.playerHandler.players[i].updateRequired = true;
			o.getPA().refreshSkill(3);
			break;
		
			case 2:
			/*if (!Server.playerHandler.players[i].getHitUpdateRequired2()){
				Server.playerHandler.players[i].hitDiff2 = new Hit(damage);
				Server.playerHandler.players[i].setHitUpdateRequired2(true);
			} else {
				Server.playerHandler.players[i].hitDiff = new Hit(damage);
				Server.playerHandler.players[i].setHitUpdateRequired(true);			
			}*/
			//Server.playerHandler.players[i].playerLevel[3] -= damage;
			Server.playerHandler.players[i].dealDamage(damage);
			Server.playerHandler.players[i].damageTaken[c.playerId] += damage;
			c.totalPlayerDamageDealt += damage;
			Server.playerHandler.players[i].updateRequired = true;	
			c.doubleHit = false;
			o.getPA().refreshSkill(3);
			break;			
		}
		Server.playerHandler.players[i].handleHitMask(damage, CombatType.MELEE);
	}
	
	
public void applyPlayerMeleeDamage(int i, int damageMask, int damage){
		c.previousDamage = damage;
		Client o = (Client) Server.playerHandler.players[i];
		if(o == null) {
			return;
		}
		// int damage = 0;
		boolean veracsEffect = false;
		boolean guthansEffect = false;
		if (c.getPA().fullVeracs()) {
			if (Misc.random(4) == 1) {
				veracsEffect = true;				
			}		
		}
		if (c.getPA().fullGuthans()) {
			if (Misc.random(4) == 1) {
				guthansEffect = true;
			}		
		}
		/*if (damageMask == 1) {
			damage = c.delayedDamage;
			c.delayedDamage = 0;
		} else {
			damage = c.delayedDamage2;
			c.delayedDamage2 = 0;
		}*/
		if(Misc.random(o.getCombat().calculateMeleeDefence()) > Misc.random(calculateMeleeAttack()) && !veracsEffect) {
			damage = 0;
			c.bonusAttack = 0;
		} else if (c.playerEquipment[c.playerWeapon] == 5698 && o.poisonDamage <= 0 && Misc.random(3) == 1) {
			o.getPA().appendPoison(13);
			c.bonusAttack += damage/3;
		} else {
			c.bonusAttack += damage/3;
		}
		if (c.maxNextHit) {
			damage = calculateMeleeMaxHit();
		}
		if (damage > 0 && guthansEffect) {
			c.playerLevel[3] += damage;
			if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
				c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
			c.getPA().refreshSkill(3);
			o.gfx0(398);		
		}
		if (c.ssSpec && damageMask == 2) {
			damage = 5 + Misc.random(11);
			c.ssSpec = false;
		}
		if (Server.playerHandler.players[i].playerLevel[3] - damage < 0) { 
			damage = Server.playerHandler.players[i].playerLevel[3];
		}
		if(o.curseActive[9] && damage > 0) {//Melee Deflect
			appendDeflect(i, damage);
		}
		if (o.vengOn && damage > 0)
			appendVengeance(i, damage);
		if (damage > 0)
			applyRecoil(damage, i);
			//Deflect(damage, i);

		switch(c.specEffect) {
			case 1: // dragon scimmy special
			if(damage > 0) {
				if(o.prayerActive[16] || o.prayerActive[17] || o.prayerActive[18]) {
					o.headIcon = -1;
					o.getPA().sendFrame36(c.PRAYER_GLOW[16], 0);
					o.getPA().sendFrame36(c.PRAYER_GLOW[17], 0);
					o.getPA().sendFrame36(c.PRAYER_GLOW[18], 0);					
				}
				o.sendMessage("You have been injured!");
				o.stopPrayerDelay = System.currentTimeMillis();
				o.prayerActive[16] = false;
				o.prayerActive[17] = false;
				o.prayerActive[18] = false;
				o.getPA().requestUpdates();		
			}
			break;
			case 2:
				if (damage > 0) {
					if (o.freezeTimer <= 0)
						o.freezeTimer = 30;
					o.gfx0(369);
					o.sendMessage("You have been frozen.");
					o.frozenBy = c.playerId;
					o.stopMovement();
					c.sendMessage("You freeze your enemy.");
				}		
			break;
			case 3:
				if (damage > 0) {
					o.playerLevel[1] -= damage;
					o.sendMessage("You feel weak.");
					if (o.playerLevel[1] < 1)
						o.playerLevel[1] = 1;
					o.getPA().refreshSkill(1);
				}
			break;
			case 4:
				if (damage > 0) {
					if (c.playerLevel[3] + damage > c.getLevelForXP(c.playerXP[3]))
						if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]));
						else 
						c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
					else 
						c.playerLevel[3] += damage;
					c.getPA().refreshSkill(3);
				}
			break;
			case 5:
				c.clawDelay = 2;
			break;
		}
		c.specEffect = 0;
		if(c.fightMode == 3) {
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 0); 
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 1);
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 2); 				
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 3);
			c.getPA().refreshSkill(0);
			c.getPA().refreshSkill(1);
			c.getPA().refreshSkill(2);
			c.getPA().refreshSkill(3);
		} else {
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE), c.fightMode); 
			c.getPA().addSkillXP((damage*Config.MELEE_EXP_RATE/3), 3);
			c.getPA().refreshSkill(c.fightMode);
			c.getPA().refreshSkill(3);
		}
		Server.playerHandler.players[i].logoutDelay = System.currentTimeMillis();
		Server.playerHandler.players[i].underAttackBy = c.playerId;
		Server.playerHandler.players[i].killerId = c.playerId;	
		Server.playerHandler.players[i].singleCombatDelay = System.currentTimeMillis();
		if (c.killedBy != Server.playerHandler.players[i].playerId)
			c.totalPlayerDamageDealt = 0;
		c.killedBy = Server.playerHandler.players[i].playerId;
		applySmite(i, damage);
		if (c.soulSplitDelay <= 0) {
			applySoulSplit(i, damage);
		}
		switch(damageMask) {
			case 1:
			/*if (!Server.playerHandler.players[i].getHitUpdateRequired()){
				Server.playerHandler.players[i].hitDiff = new Hit(damage);
				Server.playerHandler.players[i].setHitUpdateRequired(true);
			} else {
				Server.playerHandler.players[i].hitDiff2 = new Hit(damage);
				Server.playerHandler.players[i].setHitUpdateRequired2(true);			
			}*/
			//Server.playerHandler.players[i].playerLevel[3] -= damage;
			Server.playerHandler.players[i].dealDamage(damage);
			Server.playerHandler.players[i].damageTaken[c.playerId] += damage;
			c.totalPlayerDamageDealt += damage;
			Server.playerHandler.players[i].updateRequired = true;
			o.getPA().refreshSkill(3);
			break;
		
			case 2:
			/*if (!Server.playerHandler.players[i].getHitUpdateRequired2()){
				Server.playerHandler.players[i].hitDiff2 = new Hit(damage);
				Server.playerHandler.players[i].setHitUpdateRequired2(true);
			} else {
				Server.playerHandler.players[i].hitDiff = new Hit(damage);
				Server.playerHandler.players[i].setHitUpdateRequired(true);			
			}*/
			//Server.playerHandler.players[i].playerLevel[3] -= damage;
			Server.playerHandler.players[i].dealDamage(damage);
			Server.playerHandler.players[i].damageTaken[c.playerId] += damage;
			c.totalPlayerDamageDealt += damage;
			Server.playerHandler.players[i].updateRequired = true;	
			c.doubleHit = false;
			o.getPA().refreshSkill(3);
			break;			
		}
		Server.playerHandler.players[i].handleHitMask(damage, CombatType.MELEE);
	}
	
		public void deflectDamage(int damage) {
		int damage2 = 0;
		if (damage < 10)
			damage2 = 0;
		else 
			damage2 = damage/10;
		c.dealDamage(damage2);
	}
	public void applySmite(int index, int damage) {
		if (!c.prayerActive[23] && !c.curseActive[18])
			return;
		if (damage <= 0)
			return;
		if (Server.playerHandler.players[index] != null) { 
			Client c2 = (Client)Server.playerHandler.players[index];
			if(c.curseActive[18] && !c.prayerActive[23] && c.playerLevel[3] <= 99) {
						int heal = (int)(damage/5);
						if(c.playerLevel[3] + heal >= c.getPA().getLevelForXP(c.playerXP[3])) {
							c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
						} else {
							c.playerLevel[3] += heal;
						}
						c.getPA().refreshSkill(3);
			}
			c2.playerLevel[5] -= (int)(damage/4);
			if (c2.playerLevel[5] <= 0) {
				c2.playerLevel[5] = 0;
				c2.getCombat().resetPrayers();
			}
			c2.getPA().refreshSkill(5);
		}
	
	}
	
	public void applySoulSplit(int index, int damage) {
		if (!c.curseActive[18])
			return;
		if (Server.playerHandler.players[index] != null) { 
			final Client c2 = (Client)Server.playerHandler.players[index];
			final int pX = c.getX();
			final int pY = c.getY();
			final int oX = c2.getX();
			final int oY = c2.getY();
			int offX = (pY - oY)* -1;
			int offY = (pX - oX)* -1;
			if (damage > 0) {
				c2.playerLevel[5] -= 1;
				if (c2.playerLevel[5] <= 0) {
					c2.playerLevel[5] = 0;
					c2.getCombat().resetPrayers();
				}
				if (c.playerLevel[3] >= c.getLevelForXP(c.playerXP[3])) {
					c.playerLevel[3] += 0;
				} else {
					if (c.playerLevel[3] + ((damage * 4)/10) < c.getLevelForXP(c.playerXP[3])) {
						c.playerLevel[3] += (damage * 4) / 10;
					} else {
						c.playerLevel[3] += (c.getLevelForXP(c.playerXP[3]) - c.playerLevel[3]);
					}	
				}
			c.getPA().refreshSkill(3);
			c2.getPA().refreshSkill(5);
			}
			c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2263, 31, 31, - c.oldPlayerIndex - 1, 0);
			c.soulSplitDelay = 4;
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer s) {
					if (c.soulSplitDelay > 0) {
						c.soulSplitDelay--;
					}
					if (c.soulSplitDelay == 3) {
						c2.gfx0(2264);
					}
					if (c.soulSplitDelay == 2) {
						int offX2 = (oY - pY)* -1;
						int offY2 = (oX - pX)* -1;
						c.getPA().createPlayersProjectile(oX, oY, offX2, offY2, 50, 45, 2263, 31, 31, - c.playerId - 1, 0);
					}
					if (c.soulSplitDelay == 0) {	
						s.stop();
					}
				}
			}, 500);
		}
	}
	public void applynpcSS(final int i, int damage) {
		if(c.curseActive[18]) {  // Soulsplit	
					if (damage <= 0)
						return;
					if(c.curseActive[18] && !c.prayerActive[23] && c.playerLevel[3] <=  c.getPA().getLevelForXP(c.playerXP[3])) {
						int ssheal = (int)(damage/4);
					/*if(c.playerLevel[3] + ssheal >= (c.torva() ? (c.getPA().getLevelForXP(c.playerXP[3]) + c.primalBoost) : c.getPA().getLevelForXP(c.playerXP[3]))) {
						if (!c.torva())
							c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
						} else {
							c.playerLevel[3] += ssheal;
						}*/
				c.getPA().refreshSkill(3);
				final int pX = c.getX();
 				final int pY = c.getY();
				final int nX = Server.npcHandler.npcs[c.oldNpcIndex].getX();
				final int nY = Server.npcHandler.npcs[c.oldNpcIndex].getY();
				final int offX = (pY - nY)* -1;
				final int offY = (pX - nX)* -1;
				if (c.attackTimer < 2) {
					Server.npcHandler.npcs[i].gfx0(2264);
				} else {
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, 50, 2263, 9, 9, c.oldNpcIndex + 1, 24, 0);
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer container) {
							Server.npcHandler.npcs[i].gfx0(2264);
							container.stop();
						}
						@Override
						public void stop() {

						}
					}, 1); 
					CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer container) {
							c.getPA().createPlayersProjectile2(nX, nY, offX, offY, 50, 50, 2263, 9, 9, - c.playerId - 1, 24, 0);
							container.stop();
						}
						@Override
						public void stop() {

						}
					}, 2); 
				}
			}
		}
	}
		/*public void applyLeeches(int index) {
		if (Misc.random(10) == 0) {
			leechAttack(index);
		}
		if (Misc.random(10) == 0) {
			leechDefence(index);
		}
		if (Misc.random(10) == 0) {
			leechStrength(index);
		}
		if (Misc.random(10) == 0) {
			leechSpecial(index);
		}
		if (Misc.random(10) == 0) {
			leechRanged(index);
		}
		if (Misc.random(10) == 0) {
			leechMagic(index);
		}
		if (Misc.random(10) == 0) {
			leechEnergy(index);
		}
	}
	
	public void leechAttack(int index) {
		if (!c.curseActive[10])
			return;
		if (Server.playerHandler.players[index] != null) { 
			final Client c2 = (Client)Server.playerHandler.players[index];
			final int pX = c.getX();
			final int pY = c.getY();
			final int oX = c2.getX();
			final int oY = c2.getY();
			int offX = (pY - oY)* -1;
			int offY = (pX - oX)* -1;
			c.sendMessage("You leech your opponent's attack.");
			c.startAnimation(12575);
			c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2231, 43, 31, - c.oldPlayerIndex - 1, 1);
			c.leechAttackDelay = 2;
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer s) {
					if (c.leechAttackDelay > 0) {
						c.leechAttackDelay--;
					}
					if (c.leechAttackDelay == 1) {
						c2.gfx0(2232);
					}
					if (c.leechAttackDelay == 0) {	
						s.stop();
					}
				}
			}, 500);
		}
	}
	
	public void leechRanged(int index) {
		if (!c.curseActive[11])
			return;
		if (Server.playerHandler.players[index] != null) { 
			final Client c2 = (Client)Server.playerHandler.players[index];
			final int pX = c.getX();
			final int pY = c.getY();
			final int oX = c2.getX();
			final int oY = c2.getY();
			int offX = (pY - oY)* -1;
			int offY = (pX - oX)* -1;
			c.sendMessage("You leech your opponent's range.");
			c.startAnimation(12575);
			c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2236, 43, 31, - c.oldPlayerIndex - 1, 0);
			c.leechRangedDelay = 2;
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer s) {
					if (c.leechRangedDelay > 0) {
						c.leechRangedDelay--;
					}
					if (c.leechRangedDelay == 1) {
						c2.gfx0(2238);
					}
					if (c.leechRangedDelay == 0) {	
						s.stop();
					}
				}
			}, 500);
		}
	}
	
	public void leechMagic(int index) {
		if (!c.curseActive[12])
			return;
		if (Server.playerHandler.players[index] != null) { 
			final Client c2 = (Client)Server.playerHandler.players[index];
			final int pX = c.getX();
			final int pY = c.getY();
			final int oX = c2.getX();
			final int oY = c2.getY();
			int offX = (pY - oY)* -1;
			int offY = (pX - oX)* -1;
			c.sendMessage("You leech your opponent's magic.");
			c.startAnimation(12575);
			c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2240, 43, 31, - c.oldPlayerIndex - 1, 2);
			c.leechMagicDelay = 2;
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer s) {
					if (c.leechMagicDelay > 0) {
						c.leechMagicDelay--;
					}
					if (c.leechMagicDelay == 1) {
						c2.gfx0(2242);
					}
					if (c.leechMagicDelay == 0) {	
						s.stop();
					}
				}
			}, 500);
		}
	}
	
	public void leechDefence(int index) {
		if (!c.curseActive[13])
			return;
		if (Server.playerHandler.players[index] != null) { 
			final Client c2 = (Client)Server.playerHandler.players[index];
			final int pX = c.getX();
			final int pY = c.getY();
			final int oX = c2.getX();
			final int oY = c2.getY();
			int offX = (pY - oY)* -1;
			int offY = (pX - oX)* -1;
			c.sendMessage("You leech your opponent's defence.");
			c.startAnimation(12575);
			c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2244, 43, 31, - c.oldPlayerIndex - 1, 3);
			c.leechDefenceDelay = 2;
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer s) {
					if (c.leechDefenceDelay > 0) {
						c.leechDefenceDelay--;
					}
					if (c.leechDefenceDelay == 1) {
						c2.gfx0(2246);
					}
					if (c.leechDefenceDelay == 0) {	
						s.stop();
					}
				}
			}, 500);
		}
	}
	
	public void leechStrength(int index) {
		if (!c.curseActive[14])
			return;
		if (Server.playerHandler.players[index] != null) { 
			final Client c2 = (Client)Server.playerHandler.players[index];
			final int pX = c.getX();
			final int pY = c.getY();
			final int oX = c2.getX();
			final int oY = c2.getY();
			int offX = (pY - oY)* -1;
			int offY = (pX - oX)* -1;
			c.sendMessage("You leech your opponent's strength.");
			c.startAnimation(12575);
			c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2248, 43, 31, - c.oldPlayerIndex - 1, 4);
			c.leechStrengthDelay = 2;
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer s) {
					if (c.leechStrengthDelay > 0) {
						c.leechStrengthDelay--;
					}
					if (c.leechStrengthDelay == 1) {
						c2.gfx0(2250);
					}
					if (c.leechStrengthDelay == 0) {	
						s.stop();
					}
				}
			}, 500);
		}
	}
	
	public void leechEnergy(int index) {
		if (!c.curseActive[15])
			return;
		if (Server.playerHandler.players[index] != null) { 
			final Client c2 = (Client)Server.playerHandler.players[index];
			final int pX = c.getX();
			final int pY = c.getY();
			final int oX = c2.getX();
			final int oY = c2.getY();
			int offX = (pY - oY)* -1;
			int offY = (pX - oX)* -1;
			c.sendMessage("You leech your opponent's run energy.");
			c.startAnimation(12575);
			c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2252, 43, 31, - c.oldPlayerIndex - 1, 5);
			c.leechEnergyDelay = 2;
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer s) {
					if (c.leechEnergyDelay > 0) {
						c.leechEnergyDelay--;
					}
					if (c.leechEnergyDelay == 1) {
						c2.gfx0(2254);
					}
					if (c.leechEnergyDelay == 0) {	
						s.stop();
					}
				}
			}, 500);
		}
	}
	
	public void leechSpecial(int index) {
		if (!c.curseActive[16])
			return;
		if (Server.playerHandler.players[index] != null) { 
			final Client c2 = (Client)Server.playerHandler.players[index];
			final int pX = c.getX();
			final int pY = c.getY();
			final int oX = c2.getX();
			final int oY = c2.getY();
			int offX = (pY - oY)* -1;
			int offY = (pX - oX)* -1;
			c.sendMessage("You leech your opponent's special attack.");
			c.startAnimation(12575);
			c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 45, 2256, 43, 31, - c.oldPlayerIndex - 1, 6);
			c.leechSpecialDelay = 2;
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer s) {
					if (c.leechSpecialDelay > 0) {
						c.leechSpecialDelay--;
					}
					if (c.leechSpecialDelay == 1) {
						c2.gfx0(2258);
						if (c.specAmount >= 10)
							return;
						if (c2.specAmount <= 0)
							return;
						c.specAmount += 1;
						c2.specAmount -= 1;
						c2.sendMessage("Your special attack has been drained.");
					}
					if (c.leechSpecialDelay == 0) {	
						s.stop();
					}
				}
			}, 500);
		}
	}*/
	
public void fireProjectilePlayer() {
		if(c.oldPlayerIndex > 0) {
			if(Server.playerHandler.players[c.oldPlayerIndex] != null) {
				c.projectileStage = 2;
				int pX = c.getX();
				int pY = c.getY();
				int oX = Server.playerHandler.players[c.oldPlayerIndex].getX();
				int oY = Server.playerHandler.players[c.oldPlayerIndex].getY();
				int offX = (pY - oY)* -1;
				int offY = (pX - oX)* -1;	
				if(c.playerEquipment[c.playerWeapon] == 15241) {
					if(!c.specGfx) {
						c.gfx0(2138);
					}
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, 55, getRangeProjectileGFX(), 22, 22, c.oldPlayerIndex - 1, getStartDelay(), -1);
					c.handCannonDestory();	
					c.specGfx = false;	
				} else if(!c.msbSpec)
					c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, getProjectileSpeed(), getRangeProjectileGFX(), 43, 31, - c.oldPlayerIndex - 1, getStartDelay());
				else if (c.msbSpec) {
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, getProjectileSpeed(), getRangeProjectileGFX(), 43, 31, - c.oldPlayerIndex - 1, getStartDelay(), 10);
					c.msbSpec = false;
				}
				if (usingDbow())
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, getProjectileSpeed(), getRangeProjectileGFX(), 60, 31, - c.oldPlayerIndex - 1, getStartDelay(), 35);
			}
		}
	}
	
	public boolean usingDbow() {
		return  (c.playerEquipment[c.playerWeapon] == 11235  || c.playerEquipment[c.playerWeapon] == 15701 || c.playerEquipment[c.playerWeapon] == 15702 || c.playerEquipment[c.playerWeapon] == 15703 || c.playerEquipment[c.playerWeapon] == 15704 ); 
	}
	
	
	

	
	/**Prayer**/
		
	public void activatePrayer(int i) {
		if(c.duelRule[7]){
			for(int p = 0; p < c.PRAYER.length; p++) { // reset prayer glows 
				c.prayerActive[p] = false;
				c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);	
			}
			c.sendMessage("Prayer has been disabled in this duel!");
			return;
		}
		if(c.inRFD()){
			for(int p = 0; p < c.PRAYER.length; p++) { // reset prayer glows 
				c.prayerActive[p] = false;
				c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);	
			}
			c.sendMessage("You cannot use prayer in here!");
			return;
		}
		if (i == 24 && c.getPA().getLevelForXP(c.playerXP[1]) < 70) {
			c.getPA().sendFrame36(c.PRAYER_GLOW[i], 0);
			c.sendMessage("You need 60 Defence to use Chivarly");
			return;
		}
		if (i == 25 && c.getPA().getLevelForXP(c.playerXP[1]) < 70) {
			c.getPA().sendFrame36(c.PRAYER_GLOW[i], 0);
			c.sendMessage("You need 70 defence to use Piety");
			return;
		}

		int[] defPray = {0,5,13,24,25};
		int[] strPray = {1,6,14,24,25};
		int[] atkPray = {2,7,15,24,25};
		int[] rangePray = {3,11,19};
		int[] magePray = {4,12,20};

		if(c.playerLevel[5] > 0 || !Config.PRAYER_POINTS_REQUIRED){
			if(c.getPA().getLevelForXP(c.playerXP[5]) >= c.PRAYER_LEVEL_REQUIRED[i] || !Config.PRAYER_LEVEL_REQUIRED) {
				boolean headIcon = false;
				switch(i) {
					case 0:
					case 5:
					case 13:
					if(c.prayerActive[i] == false) {
						for (int j = 0; j < defPray.length; j++) {
							if (defPray[j] != i) {
								c.prayerActive[defPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[defPray[j]], 0);
							}								
						}
					}
					break;
					
					case 1:
					case 6:
					case 14:
					if(c.prayerActive[i] == false) {
						for (int j = 0; j < strPray.length; j++) {
							if (strPray[j] != i) {
								c.prayerActive[strPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[strPray[j]], 0);
							}								
						}
						for (int j = 0; j < rangePray.length; j++) {
							if (rangePray[j] != i) {
								c.prayerActive[rangePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[rangePray[j]], 0);
							}								
						}
						for (int j = 0; j < magePray.length; j++) {
							if (magePray[j] != i) {
								c.prayerActive[magePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[magePray[j]], 0);
							}								
						}
					}
					break;
					
					case 2:
					case 7:
					case 15:
					if(c.prayerActive[i] == false) {
						for (int j = 0; j < atkPray.length; j++) {
							if (atkPray[j] != i) {
								c.prayerActive[atkPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[atkPray[j]], 0);
							}								
						}
						for (int j = 0; j < rangePray.length; j++) {
							if (rangePray[j] != i) {
								c.prayerActive[rangePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[rangePray[j]], 0);
							}								
						}
						for (int j = 0; j < magePray.length; j++) {
							if (magePray[j] != i) {
								c.prayerActive[magePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[magePray[j]], 0);
							}								
						}
					}
					break;
					
					case 3://range prays
					case 11:
					case 19:
					if(c.prayerActive[i] == false) {
						for (int j = 0; j < atkPray.length; j++) {
							if (atkPray[j] != i) {
								c.prayerActive[atkPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[atkPray[j]], 0);
							}								
						}
						for (int j = 0; j < strPray.length; j++) {
							if (strPray[j] != i) {
								c.prayerActive[strPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[strPray[j]], 0);
							}								
						}
						for (int j = 0; j < rangePray.length; j++) {
							if (rangePray[j] != i) {
								c.prayerActive[rangePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[rangePray[j]], 0);
							}								
						}
						for (int j = 0; j < magePray.length; j++) {
							if (magePray[j] != i) {
								c.prayerActive[magePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[magePray[j]], 0);
							}								
						}
					}
					break;
					case 4:
					case 12:
					case 20:
					if(c.prayerActive[i] == false) {
						for (int j = 0; j < atkPray.length; j++) {
							if (atkPray[j] != i) {
								c.prayerActive[atkPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[atkPray[j]], 0);
							}								
						}
						for (int j = 0; j < strPray.length; j++) {
							if (strPray[j] != i) {
								c.prayerActive[strPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[strPray[j]], 0);
							}								
						}
						for (int j = 0; j < rangePray.length; j++) {
							if (rangePray[j] != i) {
								c.prayerActive[rangePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[rangePray[j]], 0);
							}								
						}
						for (int j = 0; j < magePray.length; j++) {
							if (magePray[j] != i) {
								c.prayerActive[magePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[magePray[j]], 0);
							}								
						}
					}
					break;
					case 10:
						c.lastProtItem = System.currentTimeMillis();
					break;
					

					case 16:					
					case 17:
					case 18:
					if(System.currentTimeMillis() - c.stopPrayerDelay < 5000) {
						c.sendMessage("You have been injured and can't use this prayer!");
						c.getPA().sendFrame36(c.PRAYER_GLOW[16], 0);
						c.getPA().sendFrame36(c.PRAYER_GLOW[17], 0);
						c.getPA().sendFrame36(c.PRAYER_GLOW[18], 0);
						return;
					}
					if (i == 16)
						c.protMageDelay = System.currentTimeMillis();
					else if (i == 17)
						c.protRangeDelay = System.currentTimeMillis();
					else if (i == 18)
						c.protMeleeDelay = System.currentTimeMillis();
						
					case 21:
					case 22:
					case 23:
					headIcon = true;		
					for(int p = 16; p < 24; p++) {
						if(i != p && p != 19 && p != 20) {
							c.prayerActive[p] = false;
							c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);
						}
					}
					break;
					case 24:
					if (c.prayerActive[i] == false) {

						for (int j = 0; j < atkPray.length; j++) {
							if (atkPray[j] != i) {
								c.prayerActive[atkPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[atkPray[j]], 0);
							}								
						}
						for (int j = 0; j < strPray.length; j++) {
							if (strPray[j] != i) {
								c.prayerActive[strPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[strPray[j]], 0);
							}								
						}
						for (int j = 0; j < rangePray.length; j++) {
							if (rangePray[j] != i) {
								c.prayerActive[rangePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[rangePray[j]], 0);
							}								
						}
						for (int j = 0; j < magePray.length; j++) {
							if (magePray[j] != i) {
								c.prayerActive[magePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[magePray[j]], 0);
							}								
						}
						for (int j = 0; j < defPray.length; j++) {
							if (defPray[j] != i) {
								c.prayerActive[defPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[defPray[j]], 0);
							}								
						}
					}
					break;
					case 25:

					if (c.prayerActive[i] == false) {

						for (int j = 0; j < atkPray.length; j++) {
							if (atkPray[j] != i) {
								c.prayerActive[atkPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[atkPray[j]], 0);
							}								
						}
						for (int j = 0; j < strPray.length; j++) {
							if (strPray[j] != i) {
								c.prayerActive[strPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[strPray[j]], 0);
							}								
						}
						for (int j = 0; j < rangePray.length; j++) {
							if (rangePray[j] != i) {
								c.prayerActive[rangePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[rangePray[j]], 0);
							}								
						}
						for (int j = 0; j < magePray.length; j++) {
							if (magePray[j] != i) {
								c.prayerActive[magePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[magePray[j]], 0);
							}								
						}
						for (int j = 0; j < defPray.length; j++) {
							if (defPray[j] != i) {
								c.prayerActive[defPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[defPray[j]], 0);
							}								
						}
					}
					break;
				}
				
				if(!headIcon) {
					if(c.prayerActive[i] == false) {
						c.prayerActive[i] = true;
						c.getPA().sendFrame36(c.PRAYER_GLOW[i], 1);					
					} else {
						c.prayerActive[i] = false;
						c.getPA().sendFrame36(c.PRAYER_GLOW[i], 0);
					}
				} else {
					if(c.prayerActive[i] == false) {
						c.prayerActive[i] = true;
						c.getPA().sendFrame36(c.PRAYER_GLOW[i], 1);
						c.headIcon = c.PRAYER_HEAD_ICONS[i];
						c.getPA().requestUpdates();
					} else {
						c.prayerActive[i] = false;
						c.getPA().sendFrame36(c.PRAYER_GLOW[i], 0);
						c.headIcon = -1;
						c.getPA().requestUpdates();
					}
				}
			} else {
				c.getPA().sendFrame36(c.PRAYER_GLOW[i],0);
				c.getPA().sendFrame126("You need a @blu@Prayer level of "+c.PRAYER_LEVEL_REQUIRED[i]+" to use "+c.PRAYER_NAME[i]+".", 357);
				c.getPA().sendFrame126("Click here to continue", 358);
				c.getPA().sendFrame164(356);
				c.nextChat = 0;
			}
		} else {
			c.getPA().sendFrame36(c.PRAYER_GLOW[i],0);
			c.sendMessage("You have run out of prayer points!");
		}	
				
	}
		
	/**
	*Specials
	**/
	
	public void activateSpecial(int weapon, int i){
		if(Server.npcHandler.npcs[i] == null && c.npcIndex > 0) {
			return;
		}
		if(Server.playerHandler.players[i] == null && c.playerIndex > 0) {
			return;
		}
		c.doubleHit = false;
		c.specEffect = 0;
		c.projectileStage = 0;
		c.specMaxHitIncrease = 2;
		if(c.npcIndex > 0) {
			c.oldNpcIndex = i;
		} else if (c.playerIndex > 0){
			c.oldPlayerIndex = i;
			Server.playerHandler.players[i].underAttackBy = c.playerId;
			Server.playerHandler.players[i].logoutDelay = System.currentTimeMillis();
			Server.playerHandler.players[i].singleCombatDelay = System.currentTimeMillis();
			Server.playerHandler.players[i].killerId = c.playerId;
		}
		switch(weapon) {
			
			case 1305: // dragon long
			c.gfx100(248);
			c.startAnimation(1058);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			c.specAccuracy = 1.10;
			c.specDamage = 1.20;
			break;
			case 15241: // hand cannon spec!!
c.usingBow = true;
c.rangeItemUsed = c.playerEquipment[c.playerArrows];
c.getItems().deleteArrow();	
c.lastWeaponUsed = weapon;
c.startAnimation(12175);
c.specAccuracy = 8.5;
c.specDamage = 2.25;
c.hitDelay = 5;
c.attackTimer-= 7;
c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
if (c.fightMode == 2)
if (c.playerIndex > 0)
fireProjectilePlayer();
else if (c.npcIndex > 0)
fireProjectileNpc();
break;
			case 1215: // dragon daggers
			case 1231:
			case 5680:
			case 5698:
			c.gfx100(252);
			c.startAnimation(1062);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			c.doubleHit = true;
			c.specAccuracy = 1.30;
			c.specDamage = 1.05;
			break;

                        case 10887:
                        c.gfx100(1027);
                        c.specAccuracy = 5.0;
			c.specDamage = 1.15;
                        c.startAnimation(5870);
                        c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
                        break;

			case 13883:
            		c.gfx100(1838);
           		c.startAnimation(10504);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()+1);
			c.specDamage = 1.25;
			c.specAccuracy = 1.75;
            		break;
            case 13879:
            c.gfx100(1836);
			c.startAnimation(10501, 0);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()+1);
			c.specDamage = 1.25;
			c.specAccuracy = 1.75;
			break;
			
			case 14484:
				EventManager.getSingleton().addEvent(new Event() {
					@Override
					public void execute(EventContainer e) {	

						if (c.clawDelay > 0) {
							c.clawDelay--;
						}

						if (c.clawDelay == 1) {
							c.delayedDamage = c.clawDamage / 4;
							c.delayedDamage2 = (c.clawDamage / 4) + 1;
							if (c.clawType == 2) {
								c.getCombat().applyNpcMeleeDamage(c.clawIndex, 1,
										c.clawDamage / 4);
							}
							if (c.clawType == 1) {
								c.getCombat().applyPlayerMeleeDamage(c.clawIndex,
										1, c.clawDamage / 4);
							}
							if (c.clawType == 2) {
								c.getCombat().applyNpcMeleeDamage(c.clawIndex, 2,
										(c.clawDamage / 4) + 1);
							}
							if (c.clawType == 1) {
								c.getCombat().applyPlayerMeleeDamage(c.clawIndex,
										2, (c.clawDamage / 4) + 1);
							}
							c.clawDelay = 0;
							c.specEffect = 0;
							c.previousDamage = 0;
							c.usingClaws = false;
							c.clawType = 0;
							e.stop();
						}

					}
				},500);

				c.gfx0(1950);
				c.startAnimation(10961);
				c.specAccuracy = 19.9;
				// c.specDamage = 1.55;
				c.clawDamage = 0;

				if (c.playerIndex > 0) {
					Client o = (Client) Server.playerHandler.players[c.playerIndex];
					if (Misc.random(calculateMeleeAttack() * 2) > Misc.random(o
							.getCombat().calculateMeleeDefence())) {
						c.clawDamage = Misc.random(calculateMeleeMaxHit())
								+ (calculateMeleeMaxHit() / 3);
					}
					c.clawIndex = c.playerIndex;
					c.clawType = 1;
				} else if (c.npcIndex > 0) {
					NPC n = Server.npcHandler.npcs[c.npcIndex];

					if (Misc.random(calculateMeleeAttack()) > Misc
							.random(n.defence)) {
						c.clawDamage = Misc.random(calculateMeleeMaxHit()
								+ Misc.random(2));
					}
					c.clawIndex = c.npcIndex;
					c.clawType = 2;
				}

				c.doubleHit = true;
				c.usingClaws = true;
				c.specEffect = 5;
				c.hitDelay = getHitDelay(c.getItems()
						.getItemName(c.playerEquipment[c.playerWeapon])
						.toLowerCase());
			break;
			
			case 4151: // whip
			if(Server.npcHandler.npcs[i] != null) {
				Server.npcHandler.npcs[i].gfx100(341);
			}
			c.specAccuracy = 1.10;
			c.startAnimation(1658);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			break;
			
			case 15441: // whip
			if(Server.npcHandler.npcs[i] != null) {
				Server.npcHandler.npcs[i].gfx100(341);
			}
			c.specAccuracy = 1.10;
			c.startAnimation(1658);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			break;
			
			case 15442: // whip
			if(Server.npcHandler.npcs[i] != null) {
				Server.npcHandler.npcs[i].gfx100(341);
			}
			c.specAccuracy = 1.10;
			c.startAnimation(1658);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			break;
			
			case 15443: // whip
			if(Server.npcHandler.npcs[i] != null) {
				Server.npcHandler.npcs[i].gfx100(341);
			}
			c.specAccuracy = 1.10;
			c.startAnimation(1658);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			break;
			
			case 15444: // whip
			if(Server.npcHandler.npcs[i] != null) {
				Server.npcHandler.npcs[i].gfx100(341);
			}
			c.specAccuracy = 1.10;
			c.startAnimation(1658);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			break;
			
				case 11694: // ags
				c.startAnimation(7074);
				c.specDamage = 1.45;
				c.specAccuracy = 5.00;
				c.gfx0(1222);
				c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
				break;

			// korasi
			case 19780:
			c.startAnimation(4000);
			c.specDamage = 2.00;
			c.specAccuracy = 5.0;
			//c.gfx0(1247);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			if (c.playerIndex > 0) {
			Client o = (Client)Server.playerHandler.players[i];
                        o.gfx0(1248);
			c.specDamage = 2.00;
			c.specAccuracy = 5.00;
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			}
			if(Server.npcHandler.npcs[i] != null && c.npcIndex > 0) {
			Server.npcHandler.npcs[i].gfx0(1248);
			c.specDamage = 2.00;

			c.specAccuracy = 5.00;
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			break;
			}



				case 18786:
				c.gfx100(1248);
				c.startAnimation(4000);
				c.specAccuracy = 5.00;
				c.specDamage = 2.00;
				c.ssSpec = true;
				c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
				break;

			case 11730:
			c.gfx100(1224);
			c.startAnimation(7072);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			c.doubleHit = true;
			c.ssSpec = true;
			c.specAccuracy = 1.30;
			break;

				case 13905: // Vesta spear
				c.startAnimation(10499);
				c.gfx0(1835);
                                c.specAccuracy = 1.25;
                                c.specEffect = 6;
                                c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
				break;

				case 13899: // Vesta LongSword
				c.startAnimation(10502);
				c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()+1);
				c.specDamage = 1.15;
				c.specAccuracy = 1.70;
				break;
				case 13902: // Statius
				c.startAnimation(10505);
				c.gfx0(1840);
				c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()+1);
				c.specDamage = 1.35;
				c.specAccuracy = 2.25;
				break;
			
			case 11700:
				c.startAnimation(7070);		
				c.gfx0(1221);
				if (c.playerIndex > 0) {
			        } else {
				Server.npcHandler.npcs[c.npcIndex].gfx0(2104);
				}
				c.specAccuracy = 1.35;
				c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
				c.specEffect = 2;
			break;
			
			case 11696:
				c.startAnimation(7073);
				c.gfx0(1223);
				c.specDamage = 1.30;
				c.specAccuracy = 1.85;
				c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
				c.specEffect = 3;
			break;
			
			case 11698:
				c.startAnimation(7071);
				c.gfx0(1220);
				c.specAccuracy = 1.25;
				c.specEffect = 4;
				c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			break;
			
			case 3204: // d hally
			c.gfx100(282);
			c.startAnimation(1203);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			if(Server.npcHandler.npcs[i] != null && c.npcIndex > 0) {
				if(!c.goodDistance(c.getX(), c.getY(), Server.npcHandler.npcs[i].getX(), Server.npcHandler.npcs[i].getY(), 1)){
					c.doubleHit = true;
				}
			}
			if(Server.playerHandler.players[i] != null && c.playerIndex > 0) {
				if(!c.goodDistance(c.getX(), c.getY(), Server.playerHandler.players[i].getX(),Server.playerHandler.players[i].getY(), 1)){
					c.doubleHit = true;
					c.delayedDamage2 = Misc.random(calculateMeleeMaxHit());
				}
			}
			break;
			
			case 4153: // maul
			c.startAnimation(1667);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			/*if (c.playerIndex > 0)
				gmaulPlayer(i);
			else
				gmaulNpc(i);*/
			c.gfx100(337);
			break;
			
			case 4587: // dscimmy
			c.gfx100(347);
			c.specEffect = 1;
			c.startAnimation(1872);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			break;
			
			case 1434: // mace
			c.startAnimation(1060);
			c.gfx100(251);
			c.specMaxHitIncrease = 3;
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase())+1;
			c.specDamage = 1.35;
			c.specAccuracy = 1.15;
			break;
			
			case 859: // magic long
			c.usingBow = true;
			c.bowSpecShot = 3;
			c.rangeItemUsed = c.playerEquipment[c.playerArrows];
			c.getItems().deleteArrow();	
			c.lastWeaponUsed = weapon;
			c.startAnimation(426);
			c.gfx100(250);	
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			c.projectileStage = 1;
			if (c.fightMode == 2)
				c.attackTimer--;
			break;
			
			case 861: // magic short	
			c.usingBow = true;			
			c.bowSpecShot = 1;
			c.rangeItemUsed = c.playerEquipment[c.playerArrows];
			c.getItems().deleteArrow();	
			c.lastWeaponUsed = weapon;
			c.startAnimation(1074);
			c.hitDelay = 3;
			c.projectileStage = 1;
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			if (c.fightMode == 2)
				c.attackTimer--;
			if (c.playerIndex > 0)
				fireProjectilePlayer();
			else if (c.npcIndex > 0)
				fireProjectileNpc();	
			break;
			
			case 11235: // dark bow
			case 15701:
			case 15702:
			case 15703:
			case 15704:
			c.usingBow = true;
			c.dbowSpec = true;
			c.rangeItemUsed = c.playerEquipment[c.playerArrows];
			c.getItems().deleteArrow();
			c.getItems().deleteArrow();
			c.lastWeaponUsed = weapon;
			c.hitDelay = 3;
			c.startAnimation(426);
			c.projectileStage = 1;
			c.gfx100(getRangeStartGFX());
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			if (c.fightMode == 2)
				c.attackTimer--;
			if (c.playerIndex > 0)
				fireProjectilePlayer();
			else if (c.npcIndex > 0)
				fireProjectileNpc();
			c.specAccuracy = 1.75;
			c.specDamage = 1.50;
			break;
		}
		if (!c.usingSpecial) {
		    	c.delayedDamage = Misc.random(calculateMeleeMaxHit());
			c.delayedDamage2 = Misc.random(calculateMeleeMaxHit());
			c.doubleHit = false;
		}
		c.usingSpecial = false;
		c.getItems().updateSpecialBar();
	}
	
	
	
	public boolean checkSpecAmount(int weapon) {
		switch(weapon) {
			case 1249:
			case 1215:
			case 1231:
			case 5680:
			case 5698:
			case 1305:
			case 1434:
			case 13899:
			if(c.specAmount >= 2.5) {
				c.specAmount -= 2.5;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;
			
			case 4151:
			case 15241:
			case 15441:
			case 15442:
			case 15443:
			case 15444:
            case 11694:
		case 14484:
			case 11698:
			case 4153:
			case 13902:
			case 13905: 
			case 15016: 
			case 15015: 
                        case 10887:
			if(c.specAmount >= 5) {
				c.specAmount -= 5;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;
			
			case 3204:
			if(c.specAmount >= 3) {
				c.specAmount -= 3;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;
			
						case 1377:
			case 11696:
			case 11730:
			if(c.specAmount >= 10) {
				c.specAmount -= 10;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;
			case 18786:
			if(c.specAmount >= 6.5) {
				c.specAmount -= 6.5;
				c.getItems().addSpecialBar(weapon);
				return true;
				}
			case 19780:
			if(c.specAmount >= 6.5) {
				c.specAmount -= 6.5;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

			case 4587:
			case 15486:
			case 859:
			case 861:
			case 11235:
			case 15701:
			case 15702:
			case 15703:
			case 15704:
			case 11700:
			if(c.specAmount >= 5.5) {
				c.specAmount -= 5.5;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

			
			default:
			return true; // incase u want to test a weapon
		}
	}
	
	public void resetPlayerAttack() {
		c.usingMagic = false;
		c.npcIndex = 0;
		c.faceUpdate(0);
		c.playerIndex = 0;
		c.getPA().resetFollow();
		//c.sendMessage("Reset attack.");
	}
	
	public int getCombatDifference(int combat1, int combat2) {
		if(combat1 > combat2) {
			return (combat1 - combat2);
		}
		if(combat2 > combat1) {
			return (combat2 - combat1);
		}	
		return 0;
	}
	
	/**
	*Get killer id 
	**/
	
	public int getKillerId(int playerId) {
		int oldDamage = 0;
		int count = 0;
		int killerId = 0;
		for (int i = 1; i < Config.MAX_PLAYERS; i++) {	
			if (Server.playerHandler.players[i] != null) {
				if(Server.playerHandler.players[i].killedBy == playerId) {
					if (Server.playerHandler.players[i].withinDistance(Server.playerHandler.players[playerId])) {
						if(Server.playerHandler.players[i].totalPlayerDamageDealt > oldDamage) {
							oldDamage = Server.playerHandler.players[i].totalPlayerDamageDealt;
							killerId = i;
						}
					}	
					Server.playerHandler.players[i].totalPlayerDamageDealt = 0;
					Server.playerHandler.players[i].killedBy = 0;
				}	
			}
		}				
		return killerId;
	}
		
	
	
		double[] prayerData = {
                1, // Thick Skin.
                1, // Burst of Strength.
                1, // Clarity of Thought.
                1, // Sharp Eye.
                1, // Mystic Will.
                2, // Rock Skin.
                2, // SuperHuman Strength.
                2, // Improved Reflexes.
                0.4, // Rapid restore.
                0.6, // Rapid Heal.
                0.6, // Protect Items.
                1.5, // Hawk eye.
                2, // Mystic Lore.
                4, // Steel Skin.
                4, // Ultimate Strength.
                4, // Incredible Reflexes.
                4, // Protect from Magic.
                4, // Protect from Missiles.
                4, // Protect from Melee.
                4, // Eagle Eye.
                4, // Mystic Might.
                1, // Retribution.
                2, // Redemption.
                6, // Smite.
                8, // Chivalry.
                8, // Piety.
        };

        double[] curseData = {
		0.6, // Protect Item
		1, // Sap Warrior
		1, // Sap Range
		1, // Sap Mage
		1, // Sap Spirit
		1, // Berserker
		2, // Deflect Summoning
		2, // Deflect Mage 7
		2, // Deflect Range 8 
		2, // Deflect Melee 9
		2, // Leech Attack
		2, // Leech Range
		2, // Leech Mage
		2, // Leech Defence
		2, // Leech Strength
		2, // Leech Energy
		2, // Leech Special
		2, // Wrath
		4, // Soul Split
		5, // Turmoil
	};
	
        public void handlePrayerDrain() {
		c.usingPrayer = false;
		double toRemove = 0.0;
		for (int j = 0; j < prayerData.length; j++) {
			if (c.prayerActive[j]) {
				toRemove += prayerData[j]/20;
				c.usingPrayer = true;
			}
		}
		for (int j = 0; j < curseData.length; j++) {
			if (c.curseActive[j]) {
				toRemove += curseData[j]/20;
				c.usingPrayer = true;
			}
		}
		if (toRemove > 0) {
			toRemove /= (1 + (0.035 * c.playerBonus[11]));		
		}
		c.prayerPoint -= toRemove;
		if (c.prayerPoint <= 0) {
			c.prayerPoint = 1.0 + c.prayerPoint;
			reducePrayerLevel();
		}
	}
	
	public void reducePrayerLevel() {
		if(c.playerLevel[5] - 1 > 0) {
			c.playerLevel[5] -= 1;
		} else {
			c.sendMessage("You have run out of prayer points! L2BuyPots Kthnxbai");
			c.playerLevel[5] = 0;
			c.getCombat().resetPrayers();
			c.prayerId = -1;	
		}
		c.getPA().refreshSkill(5);
	}
	
	public void resetPrayers() {
		for(int i = 0; i < c.prayerActive.length; i++) {
			c.prayerActive[i] = false;
			c.getPA().sendFrame36(c.PRAYER_GLOW[i], 0);
		}
		for(int i = 0; i < c.curseActive.length; i++) {
			c.curseActive[i] = false;
			c.getPA().sendFrame36(c.CURSE_GLOW[i], 0);
		}
		c.headIcon = -1;
		c.getPA().requestUpdates();
	}
	
	/**
	* Wildy and duel info
	**/
	
	public boolean checkReqs() {
		if(Server.playerHandler.players[c.playerIndex] == null) {
			return false;
		}
		if(!Server.playerHandler.players[c.playerIndex].inWild() && !Server.playerHandler.players[c.playerIndex].inDuelArena()) {
			c.getShops().openPlayerShop((Client)Server.playerHandler.players[c.playerIndex]);
			c.getCombat().resetPlayerAttack();
			return false;
		}
		if (c.playerIndex == c.playerId)
			return false;
		if (c.inPits && Server.playerHandler.players[c.playerIndex].inPits)
			return true;
		if(Server.playerHandler.players[c.playerIndex].inDuelArena() && c.duelStatus != 5 && !c.usingMagic) {
			if(c.arenas() || c.duelStatus == 5) {
				c.sendMessage("You can't challenge inside the arena!");
				return false;
			}
			c.getTradeAndDuel().requestDuel(c.playerIndex);
			return false;
		}
		if(c.duelStatus == 5 && Server.playerHandler.players[c.playerIndex].duelStatus == 5) {
			if(Server.playerHandler.players[c.playerIndex].duelingWith == c.getId()) {
				return true;
			} else {
				c.sendMessage("This isn't your opponent!");
				return false;
			}
		}
		if (c.getPA().getWearingAmount() < 4 && c.duelStatus < 1) {
			c.sendMessage("You must be wearing at least 4 items to attack someone.");
			resetPlayerAttack();
		return false;
		}
		/*if (c2.getPA().getWearingAmount() < 4 && c.duelStatus < 1) {
			c.sendMessage("That person is not wearing 4 items you can not attack him.");
			resetPlayerAttack();
		return false;
		}*/
		if(!Server.playerHandler.players[c.playerIndex].inWild()) {
			c.sendMessage("That player is not in the wilderness.");
			c.stopMovement();
			c.getCombat().resetPlayerAttack();
			return false;
		}
		if(Server.playerHandler.players[c.playerIndex].safeZone()) {
			c.sendMessage("This player is currently in a safe zone.");
			c.stopMovement();
			c.getCombat().resetPlayerAttack();
			return false;
		}

		if(!c.inWild()) {
			c.sendMessage("You are not in the wilderness.");
			c.stopMovement();
			c.getCombat().resetPlayerAttack();
			return false;
		}
		if(c.safeZone()) {
			c.sendMessage("You are standing in a safe zone.");
			c.stopMovement();
			c.getCombat().resetPlayerAttack();
			return false;
		}
		if(!c.inFunPk()){
		if(Config.COMBAT_LEVEL_DIFFERENCE) {
			int combatDif1 = c.getCombat().getCombatDifference(c.combatLevel, Server.playerHandler.players[c.playerIndex].combatLevel);
			if(combatDif1 > c.wildLevel || combatDif1 > Server.playerHandler.players[c.playerIndex].wildLevel) {
				c.sendMessage("Your combat level difference is too great to attack that player here.");
				c.stopMovement();
				c.getCombat().resetPlayerAttack();
				return false;
				}
			}
		}
		if(Config.SINGLE_AND_MULTI_ZONES) {
			if(!Server.playerHandler.players[c.playerIndex].inMulti()) {	// single combat zones
				if(Server.playerHandler.players[c.playerIndex].underAttackBy != c.playerId  && Server.playerHandler.players[c.playerIndex].underAttackBy != 0) {
					c.sendMessage("That player is already in combat.");
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					return false;
				}
				if(Server.playerHandler.players[c.playerIndex].playerId != c.underAttackBy && c.underAttackBy != 0 || c.underAttackBy2 > 0) {
					c.sendMessage("You are already in combat.");
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkMultiBarrageReqs(int i) {
		if(Server.playerHandler.players[i] == null) {
			return false;
		}
		if (i == c.playerId)
			return false;
		if (c.inPits && Server.playerHandler.players[i].inPits)
			return true;
		if(!Server.playerHandler.players[i].inWild()) {
			return false;
		}
		if(!c.inFunPk()){
		if(Config.COMBAT_LEVEL_DIFFERENCE) {
			int combatDif1 = c.getCombat().getCombatDifference(c.combatLevel, Server.playerHandler.players[i].combatLevel);
			if(combatDif1 > c.wildLevel || combatDif1 > Server.playerHandler.players[i].wildLevel && !c.inFunPk()) {
				c.sendMessage("Your combat level difference is too great to attack that player here.");
				return false;
				}
			}
		}
		
		if(Config.SINGLE_AND_MULTI_ZONES) {
			if(!Server.playerHandler.players[i].inMulti()) {	// single combat zones
				if(Server.playerHandler.players[i].underAttackBy != c.playerId  && Server.playerHandler.players[i].underAttackBy != 0) {
					return false;
				}
				if(Server.playerHandler.players[i].playerId != c.underAttackBy && c.underAttackBy != 0) {
					c.sendMessage("You are already in combat.");
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	*Weapon stand, walk, run, etc emotes
	**/
	
	public void getPlayerAnimIndex(String weaponName){
		CombatEmotes.getPlayerAnimIndex(c, weaponName);
	}
	
	/**
	* Weapon emotes
	**/
	
	public int getWepAnim(String weaponName) {
		return CombatEmotes.getWepAnim(c, weaponName);
	}
		/**
	 * Gets the appropriate attack sound for the player {@link #c}.
	 * @return A sound id which can be used in calling {@link Client#sendSound}.
	 */
	public int getAttackSound() {
		Weapon wep = Weapon.forId(c.playerEquipment[c.playerWeapon]);
		if (wep != null) return wep.getId();
		return -1;
	}
	/**
	* Block emotes
	*/
	public int getBlockEmote() {
		return CombatEmotes.getBlockEmote(c);
	}
			
	/**
	* Weapon and magic attack speed!
	**/
	
	public int getAttackDelay(String s) {
		return MeleeData.getAttackDelay(c, s);
	}
	/**
	* How long it takes to hit your enemy
	**/
	public int getHitDelay(String weaponName) {
		return MeleeData.getHitDelay(c, weaponName);
	}
	
	public int getRequiredDistance() {
		return MeleeData.getRequiredDistance(c);
	}
	
	public boolean usingHally() {
		return MeleeData.usingHally(c);
	}
	
	/**
	* Melee
	**/
	
	public int calculateMeleeAttack() {
		return MeleeHit.calculateMeleeAttack(c);
	}
	public int bestMeleeAtk() {
		return MeleeHit.bestMeleeAtk(c);
    }
	
	public int calculateMeleeMaxHit() {
		return MeleeHit.calculateMeleeMaxHit(c);
	}
	
	public int calculateMeleeDefence() {
		return MeleeHit.calculateMeleeDefence(c);
    }
	
	public int bestMeleeDef() {
		return MeleeHit.bestMeleeDef(c);
	}

	/**
	* Range
	**/
	
	public int calculateRangeAttack() {
		return RangeHit.calculateRangeAttack(c);
	}
	
	public int calculateRangeDefence() {
		return RangeHit.calculateRangeDefence(c);
	}
	
	public boolean usingBolts() {
		return RangeData.usingBolts(c);
	}
	
public int getRangeStr(int i) {
	return RangeHit.getRangeStr(c, i);
}
	public int rangeMaxHit() {
		return RangeHit.rangeMaxHit(c);
	}
	public boolean properBolts() {
		return RangeData.properBolts(c);
	}
	
	public int correctBowAndArrows() {
		return RangeData.correctBowAndArrows(c);
	}
	
	public int getRangeStartGFX() {
		return RangeData.getRangeStartGFX(c);
	}
		
	public int getRangeProjectileGFX() {
		return RangeData.getRangeProjectileGFX(c);
	}

	public int getProjectileSpeed() {
		return RangeData.getProjectileSpeed(c);
	}

	public int getProjectileShowDelay() {
		return RangeData.getProjectileShowDelay(c);
	}
		public void appendMutliChinchompa(int npcId) {
		RangeExtras.appendMutliChinchompa(c, npcId);
	}
	
	/**
	* Mage
	**/

		public int finalMagicDamage(Client c) {
			return MagicHit.finalMagicDamage(c);
		}
	
	/**
	* Magic
	**/
	
	public int mageAtk() {
        return MagicHit.mageAtk(c);
    }
	public int mageDef() {
		return MagicHit.mageDef(c);
    }
	
	public boolean wearingStaff(int runeId) {
		int wep = c.playerEquipment[c.playerWeapon];
		switch (runeId) {
			case 554:
			if (wep == 1387)
				return true;
			break;
			case 555:
			if (wep == 1383)
				return true;
			break;
			case 556:
			if (wep == 1381)
				return true;
			break;
			case 557:
			if (wep == 1385)
				return true;
			break;
		}
		return false;
	}
	
	public boolean checkMagicReqs(int spell) {
		if(c.usingMagic && Config.RUNES_REQUIRED) { // check for runes
			if((!c.getItems().playerHasItem(c.MAGIC_SPELLS[spell][8], c.MAGIC_SPELLS[spell][9]) && !wearingStaff(c.MAGIC_SPELLS[spell][8])) ||
				(!c.getItems().playerHasItem(c.MAGIC_SPELLS[spell][10], c.MAGIC_SPELLS[spell][11]) && !wearingStaff(c.MAGIC_SPELLS[spell][10])) ||
				(!c.getItems().playerHasItem(c.MAGIC_SPELLS[spell][12], c.MAGIC_SPELLS[spell][13]) && !wearingStaff(c.MAGIC_SPELLS[spell][12])) ||
				(!c.getItems().playerHasItem(c.MAGIC_SPELLS[spell][14], c.MAGIC_SPELLS[spell][15]) && !wearingStaff(c.MAGIC_SPELLS[spell][14]))){
			c.sendMessage("You don't have the required runes to cast this spell.");
			return false;
			} 
		}

		if(c.usingMagic && c.playerIndex > 0) {
			if(Server.playerHandler.players[c.playerIndex] != null) {
				for(int r = 0; r < c.REDUCE_SPELLS.length; r++){	// reducing spells, confuse etc
					if(Server.playerHandler.players[c.playerIndex].REDUCE_SPELLS[r] == c.MAGIC_SPELLS[spell][0]) {
						c.reduceSpellId = r;
						if((System.currentTimeMillis() - Server.playerHandler.players[c.playerIndex].reduceSpellDelay[c.reduceSpellId]) > Server.playerHandler.players[c.playerIndex].REDUCE_SPELL_TIME[c.reduceSpellId]) {
							Server.playerHandler.players[c.playerIndex].canUseReducingSpell[c.reduceSpellId] = true;
						} else {
							Server.playerHandler.players[c.playerIndex].canUseReducingSpell[c.reduceSpellId] = false;
						}
						break;
					}			
				}
				if(!Server.playerHandler.players[c.playerIndex].canUseReducingSpell[c.reduceSpellId]) {
					c.sendMessage("That player is currently immune to this spell.");
					c.usingMagic = false;
					c.stopMovement();
					resetPlayerAttack();
					return false;
				}
			}
		}

		int staffRequired = getStaffNeeded();
		if(c.usingMagic && staffRequired > 0 && Config.RUNES_REQUIRED) { // staff required
			if(c.playerEquipment[c.playerWeapon] != staffRequired) {
				c.sendMessage("You need a "+c.getItems().getItemName(staffRequired).toLowerCase()+" to cast this spell.");
				return false;
			}
		}
		
		if(c.usingMagic && Config.MAGIC_LEVEL_REQUIRED) { // check magic level
			if(c.playerLevel[6] < c.MAGIC_SPELLS[spell][1]) {
				c.sendMessage("You need to have a magic level of " +c.MAGIC_SPELLS[spell][1]+" to cast this spell.");
				return false;
			}
		}
		if(c.usingMagic && Config.RUNES_REQUIRED) {
			if(c.MAGIC_SPELLS[spell][8] > 0) { // deleting runes
				if (!wearingStaff(c.MAGIC_SPELLS[spell][8]))
					c.getItems().deleteItem(c.MAGIC_SPELLS[spell][8], c.getItems().getItemSlot(c.MAGIC_SPELLS[spell][8]), c.MAGIC_SPELLS[spell][9]);
			}
			if(c.MAGIC_SPELLS[spell][10] > 0) {
				if (!wearingStaff(c.MAGIC_SPELLS[spell][10]))
					c.getItems().deleteItem(c.MAGIC_SPELLS[spell][10], c.getItems().getItemSlot(c.MAGIC_SPELLS[spell][10]), c.MAGIC_SPELLS[spell][11]);
			}
			if(c.MAGIC_SPELLS[spell][12] > 0) {
				if (!wearingStaff(c.MAGIC_SPELLS[spell][12]))
					c.getItems().deleteItem(c.MAGIC_SPELLS[spell][12], c.getItems().getItemSlot(c.MAGIC_SPELLS[spell][12]), c.MAGIC_SPELLS[spell][13]);
			}
			if(c.MAGIC_SPELLS[spell][14] > 0) {
				if (!wearingStaff(c.MAGIC_SPELLS[spell][14]))
					c.getItems().deleteItem(c.MAGIC_SPELLS[spell][14], c.getItems().getItemSlot(c.MAGIC_SPELLS[spell][14]), c.MAGIC_SPELLS[spell][15]);
			}
		}
		return true;
	}
	
	
	public int getFreezeTime() {
		return MagicData.getFreezeTime(c);
	}
	
	public void freezePlayer(int i) {
		
	
	}

	public int getStartHeight() {
		return MagicData.getStartHeight(c);
	}
	

	
	public int getEndHeight() {
		return MagicData.getEndHeight(c);
	}
	
	public int getStartDelay() {
		return MagicData.getStartDelay(c);
	}
	
	public int getStaffNeeded() {
		return MagicData.getStaffNeeded(c);
	}
	
	public boolean godSpells() {
		return MagicData.godSpells(c);
	}
		
	public int getEndGfxHeight() {
		return MagicData.getEndGfxHeight(c);
	}
	
	public int getStartGfxHeight() {
		return MagicData.getStartGfxHeight(c);
	}
	
	public void handleDfs() {
try {

	                  if(c.oldPlayerIndex > 0) {
			if(Server.playerHandler.players[c.oldPlayerIndex] != null) {
				c.projectileStage = 2;
				final int pX = c.getX();
				final int pY = c.getY();
				final int oX = Server.playerHandler.players[c.oldPlayerIndex].getX();
				final int oY = Server.playerHandler.players[c.oldPlayerIndex].getY();
				final int offX = (pY - oY)* -1;
				final int offY = (pX - oX)* -1;
			if (System.currentTimeMillis() - c.dfsDelay > 30000) {
			if (c.playerIndex > 0 && Server.playerHandler.players[c.playerIndex] != null) {
				final int damage = Misc.random(15) + 5;
				c.startAnimation(6696);
				c.gfx0(1165);
			c.SSPLIT = true;
			//EventManager.getSingleton().addEvent(new Event() {
				//public void execute(EventContainer b) {
 				Server.playerHandler.players[c.oldPlayerIndex].gfx100(1167);
				c.SSPLIT = false;
				Server.playerHandler.players[c.oldPlayerIndex].playerLevel[3] -= damage;
				Server.playerHandler.players[c.oldPlayerIndex].hitDiff2 = new Hit(damage, CombatType.MAGE);
				Server.playerHandler.players[c.oldPlayerIndex].hitUpdateRequired2 = true;
				Server.playerHandler.players[c.oldPlayerIndex].updateRequired = true;
				//b.stop();
				//}
				//}, 1700);
			//EventManager.getSingleton().addEvent(new Event() {
				//public void execute(EventContainer b) {
  				c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, 50, 1166, 30, 30, - c.oldPlayerIndex - 1, 30, 5);
				//b.stop();
				//}
				//}, 1000);
				c.dfsDelay = System.currentTimeMillis();					
			} else {
				c.sendMessage("I should be in combat before using this.");
			}
		} else {
			c.sendMessage("My shield hasn't finished recharging yet.");
	}		}
		}	
			} catch (Exception e) {
                         }
	}	


	public void handleZerker() {

		if(c.isDonator == 1){
		if (System.currentTimeMillis() - c.dfsDelay > 60000) {
			if (c.playerIndex > 0 && Server.playerHandler.players[c.playerIndex] != null) {
				int damage = Misc.random(10) + 7;
				c.startAnimation(369);
				c.gfx0(369);
				Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
				Server.playerHandler.players[c.playerIndex].hitDiff2 = new Hit(damage, CombatType.NONE);
				c.forcedText = "Feel the power of the Berserker Ring!";
				Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
				Server.playerHandler.players[c.playerIndex].updateRequired = true;
				c.dfsDelay = System.currentTimeMillis();						
			} else {
				c.sendMessage("I should be in combat before using this.");
			}
		} else {
			c.sendMessage("My ring hasn't finished recharging yet (60 Seconds)");
			}if (c.isDonator == 0)
			c.sendMessage("Only Donators can use the ring's Special attack");	
		}
	}
	public void handleWarrior() {
		if(c.isDonator == 1){
		if (System.currentTimeMillis() - c.dfsDelay > 60000) {
			if (c.playerIndex > 0 && Server.playerHandler.players[c.playerIndex] != null) {
				int damage = Misc.random(10) + 7;
				c.startAnimation(369);
				c.gfx0(369);
				Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
				c.forcedText = "Feel the power of the Warrior Ring!";
				Server.playerHandler.players[c.playerIndex].hitDiff2 = new Hit(damage, CombatType.NONE);
				Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
				Server.playerHandler.players[c.playerIndex].updateRequired = true;
				c.dfsDelay = System.currentTimeMillis();						
			} else {
				c.sendMessage("I should be in combat before using this.");
			}
		} else {
			c.sendMessage("My ring hasn't finished recharging yet (60 Seconds)");
			}if (c.isDonator == 0)
			c.sendMessage("Only Donators can use the ring's Special attack");	
		}
	}
	
	public void handleSeers() {
/*

		c.castingMagic = true;
		if(c.isDonator == 1){
		if (System.currentTimeMillis() - c.dfsDelay > 60000) {
			if (c.playerIndex > 0 && Server.playerHandler.players[c.playerIndex] != null) {
				int damage = Misc.random(10) + 7;
								c.startAnimation(1979);
								Server.playerHandler.players[c.playerIndex].gfx0(369);
								c.gfx0(368);
					Server.playerHandler.players[c.playerIndex].freezeTimer = 15;
										Server.playerHandler.players[c.playerIndex].resetWalkingQueue();
										Server.playerHandler.players[c.playerIndex].frozenBy = c.playerId;
				Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
				c.forcedText = ("Feel the power of the Seers Ring!");
				Server.playerHandler.players[c.playerIndex].hitDiff2 = new Hit(damage;

				Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
				Server.playerHandler.players[c.playerIndex].updateRequired = true;
				c.dfsDelay = System.currentTimeMillis();						
			} else {
				c.sendMessage("I should be in combat before using this.");
			}
		} else {
			c.sendMessage("My ring hasn't finished recharging yet (60 Seconds)");
			}if (c.isDonator == 0)
*/
		
	}

	public void Zammybook() {
				c.startAnimation(1670);
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer b) {
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
				c.forcedText = ("Two great warriors, joined by hand,");
				b.stop();
				}
				}, 400);
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer b) {
				c.forcedText = ("to spread destruction across the land.");
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
				b.stop();
				}
				}, 1200);
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer b) {
				c.forcedText = ("In Zamorak's name, now two are one.");
		c.forcedChatUpdateRequired = true;
		c.updateRequired = true;
				b.stop();
				}
				}, 1900);					

		
	}
	
	public void handleArcher() {
		if(c.isDonator == 1){
		if (System.currentTimeMillis() - c.dfsDelay > 60000) {
			if (c.playerIndex > 0 && Server.playerHandler.players[c.playerIndex] != null) {
				int damage = Misc.random(10) + 7;
				c.startAnimation(369);
				c.gfx0(369);
				Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
				Server.playerHandler.players[c.playerIndex].hitDiff2 = new Hit(damage, CombatType.NONE);
				c.forcedText = "Feel the power of the Archer Ring!";
				Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
				Server.playerHandler.players[c.playerIndex].updateRequired = true;
				c.dfsDelay = System.currentTimeMillis();						
			} else {
				c.sendMessage("I should be in combat before using this.");
			}
		} else {
			c.sendMessage("My ring hasn't finished recharging yet (60 Seconds)");
			}if (c.isDonator == 0)
			c.sendMessage("Only Donators can use the ring's Special attack");	
		}
	}
	
		public void handleDfsNPC() {
	                try {
	                 if(c.npcIndex > 0) {
			if(Server.npcHandler.npcs[c.npcIndex] != null) {
				c.projectileStage = 2;
				final int pX = c.getX();
				final int pY = c.getY();
				final int nX = Server.npcHandler.npcs[c.npcIndex].getX();
				final int nY = Server.npcHandler.npcs[c.npcIndex].getY();
				final int offX = (pY - nY)* -1;
				final int offY = (pX - nX)* -1;
			if (System.currentTimeMillis() - c.dfsDelay > 30000) {
			if (c.npcIndex > 0 && Server.npcHandler.npcs[c.npcIndex] != null) {
				final int damage = Misc.random(15) + 5;
				c.startAnimation(6696);
				c.gfx0(1165);
				Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
				Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
				Server.npcHandler.npcs[c.npcIndex].hitDiff2 = new Hit(damage, CombatType.NONE);
				Server.npcHandler.npcs[c.npcIndex].HP -= damage;
				//Server.npcHandler.npcs[c.npcIndex].gfx100(1167);
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer b) {
				c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 50, 1166, 31, 35, - c.npcIndex  - 1, 30);
				b.stop();
				}
				}, 1000);
if(Server.npcHandler.npcs[c.npcIndex].isDead == true) {
c.sendMessage("This NPC is already dead!");
return;
}
				c.dfsDelay = System.currentTimeMillis();						
			} else {
				c.sendMessage("I should be in combat before using this.");
			}
		} else {
			c.sendMessage("My shield hasn't finished recharging yet.");
	}		}	
		}	
		}
		catch (Exception e)
		{
		}
	}	
	
	public void applyRecoil(int damage, int i) {
		if (damage > 0 && Server.playerHandler.players[i].playerEquipment[c.playerRing] == 2550) {
			int recDamage = damage/10 + 1;
			if (!c.getHitUpdateRequired()) {
				c.hitDiff = new Hit(recDamage, CombatType.NONE);
			removeRecoil(c);
			c.recoilHits = recDamage;
				c.setHitUpdateRequired(true);				
			} else if (!c.getHitUpdateRequired2()) {
				c.hitDiff2 = new Hit(recDamage, CombatType.NONE);
				c.setHitUpdateRequired2(true);
			}
			c.dealDamage(recDamage);
			c.updateRequired = true;
		}	
	}

	public void Deflect(int damage, int i) {
		Client o = (Client)Server.playerHandler.players[c.playerIndex];
		if (damage > 0 && Server.playerHandler.players[i].curseActive[7]) {
			int recDamage = damage/6;
			if (!c.getHitUpdateRequired()) {
				c.hitDiff = new Hit(recDamage, CombatType.RANGE);
			c.recoilHits = recDamage;
			
				c.setHitUpdateRequired(true);				
			} else if (!c.getHitUpdateRequired2()) {
				c.hitDiff2 = new Hit(recDamage, CombatType.RANGE);
				c.setHitUpdateRequired2(true);
			}
			c.dealDamage(recDamage);
			c.updateRequired = true;
		} else if (damage > 0  && Server.playerHandler.players[i].curseActive[8]) {
			o.gfx0(2229);
			o.startAnimation(12573);
			int recDamage = damage/6;
			if (!c.getHitUpdateRequired()) {
				c.hitDiff = new Hit(recDamage, CombatType.MAGE);
			c.recoilHits = recDamage;
				c.setHitUpdateRequired(true);				
			} else if (!c.getHitUpdateRequired2()) {
				c.hitDiff2 = new Hit(recDamage, CombatType.MAGE);
				c.setHitUpdateRequired2(true);
			}
			c.dealDamage(recDamage);
			c.updateRequired = true;
		} else if (damage > 0  && Server.playerHandler.players[i].curseActive[9]) { //Deflect Melee
			int recDamage = damage/1;
			o.gfx0(2230);
			o.startAnimation(12573);
			if (!c.getHitUpdateRequired()) {
				c.hitDiff = new Hit(recDamage, CombatType.MELEE);
			c.recoilHits = recDamage;
				c.setHitUpdateRequired(true);				
			} else if (!c.getHitUpdateRequired2()) {
				c.hitDiff2 = new Hit(recDamage, CombatType.MELEE);
				c.setHitUpdateRequired2(true);
			}
			c.dealDamage(recDamage);
			c.updateRequired = true;
			}
		}
		public void appendDeflect(int otherPlayer, int damage) {//Handles Deflect Prayers
		for(int i = 0; i < deflectData.length; i++) {
			if (damage <= 0) {
				return;
		}
		Player o = Server.playerHandler.players[otherPlayer];
		if ((o.playerLevel[3] - damage) >= 3) {
			damage = (int)(damage/2+1);
			if (damage > c.playerLevel[3]) {
				damage = c.playerLevel[3];
			}
			if(o.curseActive[deflectData[i][0]]) {
				c.hitDiff2 = new Hit(damage, CombatType.MAGE);
				c.setHitUpdateRequired2(true);
				c.playerLevel[3] -= damage;
				c.getPA().refreshSkill(3);
				o.gfx0(deflectData[i][1]);
				o.startAnimation(deflectData[i][2]);
			}
		}
		c.updateRequired = true;
		}
	}
	
	int[][] deflectData = {
	//prayerActive[id], gfx, anim
	{7, 2228, 12573},//Deflect Mage
	{8, 2229, 12573},//Deflect Range
	{9, 2230, 12573},//Deflect Melee
	};

	public void applyRecoilNPC(int damage, int i) {
		if (damage > 0 && c.playerEquipment[c.playerRing] == 2550) {
			int recDamage = damage/10 + 1;
			Server.npcHandler.npcs[c.npcIndex].HP -= recDamage;
			Server.npcHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
			Server.npcHandler.npcs[c.npcIndex].updateRequired = true;
			c.updateRequired = true;
			removeRecoil(c);
			c.recoilHits = damage;
		}
	}

	public void removeRecoil(Client c) {
		if(c.recoilHits >= 50) {
			c.getItems().removeItem(2550, c.playerRing);
			c.getItems().deleteItem(2550, c.getItems().getItemSlot(2550), 1);
			c.sendMessage("Your ring of recoil turned to dust!");
			c.recoilHits = 0;
		} else {
			c.recoilHits++;
		}
	}
	
	public int getBonusAttack(int i) {
		switch (Server.npcHandler.npcs[i].npcType) {
case 5247:
			return Misc.random(50) + 30;
			case 2883:
			return Misc.random(50) + 30;
			case 2026:
			case 2027:
			case 2029:
			case 2030:
			return Misc.random(50) + 30;
		}
		return 0;
	}
	
	
	
	public void handleGmaulPlayer() {
		if (c.playerIndex > 0) {
			Client o = (Client)Server.playerHandler.players[c.playerIndex];
			if (c.goodDistance(c.getX(), c.getY(), o.getX(), o.getY(), getRequiredDistance())) {
				if (checkReqs()) {
					if (checkSpecAmount(4153)) {						
						boolean hit = Misc.random(calculateMeleeAttack()) > Misc.random(o.getCombat().calculateMeleeDefence());
						int damage = 0;
						if (hit)
							damage = Misc.random(calculateMeleeMaxHit());
						if (o.prayerActive[18] || o.curseActive[9] && System.currentTimeMillis() - o.protMeleeDelay > 1500)
							damage *= .6;
						o.handleHitMask(damage, CombatType.MELEE);
						c.startAnimation(1667);
						c.gfx100(340);
						o.dealDamage(damage);
					}	
				}	
			}			
		}	
	}
	
	public boolean armaNpc(int i) {
		switch (Server.npcHandler.npcs[i].npcType) {
			case 6222:
			case 6223:
			case 6229:
			case 6225:
			case 6230:
			case 6227:
			case 6232:
			case 6239:
			case 6233:
			case 6231:
			return true;	
		}
		return false;	
	}
	
}