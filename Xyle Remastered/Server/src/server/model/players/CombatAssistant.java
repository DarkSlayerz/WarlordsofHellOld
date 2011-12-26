package server.model.players;

import server.Config;
import server.model.players.Prayers.Prayer;
import server.Server;
import server.model.npcs.NPC;
import server.util.Misc;
import java.util.Random;
import server.clip.region.Region;
import server.clip.region.ObjectDef;



public class CombatAssistant{

	private final Random random = new Random();
	private final double DEFENCE_MODIFIER = 0.70;
	
	private Client c;
	public CombatAssistant(Client Client) {
		this.c = Client;
	}

	public int[][] slayerReqs = {{1648,5},{1612,15},{1643,45},{1618,50},{1624,65},{1610,75},{1613,80},{1615,85},{2783,90}};
	
	
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
	
	/**
	* Attack Npcs
	*/
	public void attackNpc(int i) {		
		if (Server.npcHandler.npcs[i] != null) {
			if (Server.npcHandler.npcs[i].isDead || Server.npcHandler.npcs[i].MaxHP <= 0) {
				c.usingMagic = false;
				c.faceUpdate(0);
				c.npcIndex = 0;
				return;
			}			
			if(c.respawnTimer > 0) {
				c.npcIndex = 0;
				return;
			}
			if (Server.npcHandler.npcs[i].underAttackBy > 0 && Server.npcHandler.npcs[i].underAttackBy != c.playerId && !Server.npcHandler.npcs[i].inMulti()) {
				c.npcIndex = 0;
				c.sendMessage("This monster is already in combat.");
				return;
			}
			if ((c.underAttackBy > 0 || c.underAttackBy2 > 0) && c.underAttackBy2 != i && !c.inMulti()) {
				resetPlayerAttack();
				c.sendMessage("I am already under attack.");
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
			c.followId2 = i;
			c.followId = 0;
			if(c.attackTimer <= 0) {
				boolean usingBow = false;
				boolean usingArrows = false;
				boolean usingOtherRangeWeapons = false;
				boolean usingCross = c.playerEquipment[c.playerWeapon] == 9185 || c.playerEquipment[c.playerWeapon] == 18357;
				c.bonusAttack = 0;
				c.rangeItemUsed = 0;
				c.projectileStage = 0;
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
					return;
				}
				if((!c.goodDistance(c.getX(), c.getY(), Server.npcHandler.npcs[i].getX(), Server.npcHandler.npcs[i].getY(), 2) && (usingHally() && !usingOtherRangeWeapons && !usingBow && !c.usingMagic)) ||(!c.goodDistance(c.getX(), c.getY(), Server.npcHandler.npcs[i].getX(), Server.npcHandler.npcs[i].getY(), 4) && (usingOtherRangeWeapons && !usingBow && !c.usingMagic)) || (!c.goodDistance(c.getX(), c.getY(), Server.npcHandler.npcs[i].getX(), Server.npcHandler.npcs[i].getY(), 1) && (!usingOtherRangeWeapons && !usingHally() && !usingBow && !c.usingMagic)) || ((!c.goodDistance(c.getX(), c.getY(), Server.npcHandler.npcs[i].getX(), Server.npcHandler.npcs[i].getY(), 8) && (usingBow || c.usingMagic)))) {
					c.attackTimer = 2;
					return;
				}
				
				if(!usingCross && !usingArrows && usingBow && (c.playerEquipment[c.playerWeapon] < 4212 || c.playerEquipment[c.playerWeapon] > 4223)) {
					c.sendMessage("You have run out of arrows!");
					c.stopMovement();
					c.npcIndex = 0;
					return;
				} 
				if(correctBowAndArrows() < c.playerEquipment[c.playerArrows] && Config.CORRECT_ARROWS && usingBow && !usingCrystalBow() && c.playerEquipment[c.playerWeapon] != 9185 && c.playerEquipment[c.playerWeapon] != 18357) {
					c.sendMessage("You can't use "+c.getItems().getItemName(c.playerEquipment[c.playerArrows]).toLowerCase()+"s with a "+c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()+".");
					c.stopMovement();
					c.npcIndex = 0;
					return;
				}
				
				
				if ((c.playerEquipment[c.playerWeapon] == 9185 || c.playerEquipment[c.playerWeapon] == 18357) && !properBolts()) {
					c.sendMessage("You must use bolts with a crossbow.");
					c.stopMovement();
					resetPlayerAttack();
					return;				
				}
				
				if(usingBow || c.usingMagic || usingOtherRangeWeapons || (c.goodDistance(c.getX(), c.getY(), Server.npcHandler.npcs[i].getX(), Server.npcHandler.npcs[i].getY(), 2) && usingHally())) {
					c.stopMovement();
				}

				if(!checkMagicReqs(c.spellId)) {
					c.stopMovement();
					c.npcIndex = 0;
					return;
				}
				
				c.faceUpdate(i);
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
				c.specMaxHitIncrease = 0;
				if(!c.usingMagic) {
					c.startAnimation(getWepAnim(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()));
				} else {
					c.startAnimation(c.MAGIC_SPELLS[c.spellId][2]);
				}
				c.lastWeaponUsed = c.playerEquipment[c.playerWeapon];
				c.lastArrowUsed = c.playerEquipment[c.playerArrows];
				if(!usingBow && !c.usingMagic && !usingOtherRangeWeapons) { // melee hit delay
					c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
					c.projectileStage = 0;
					c.oldNpcIndex = i;
				}
				
				if(usingBow && !usingOtherRangeWeapons && !c.usingMagic || usingCross) { // range hit delay					
					if (usingCross)
						c.usingBow = true;
					if (c.fightMode == c.RAPID)
						c.attackTimer--;
					c.lastArrowUsed = c.playerEquipment[c.playerArrows];
					c.lastWeaponUsed = c.playerEquipment[c.playerWeapon];
					c.gfx100(getRangeStartGFX());	
					c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
					c.projectileStage = 1;
					c.oldNpcIndex = i;
					if(c.playerEquipment[c.playerWeapon] >= 4212 && c.playerEquipment[c.playerWeapon] <= 4223) {
						c.rangeItemUsed = c.playerEquipment[c.playerWeapon];
						c.crystalBowArrowCount++;
						c.lastArrowUsed = 0;
					} else {
						c.rangeItemUsed = c.playerEquipment[c.playerArrows];
						c.getItems().deleteArrow();	
					}
					fireProjectileNpc();
				}
							
				
				if(usingOtherRangeWeapons && !c.usingMagic && !usingBow) {	// knives, darts, etc hit delay		
					c.rangeItemUsed = c.playerEquipment[c.playerWeapon];
					c.getItems().deleteEquipment();
					c.gfx100(getRangeStartGFX());
					c.lastArrowUsed = 0;
					c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
					c.projectileStage = 1;
					c.oldNpcIndex = i;
					if (c.fightMode == c.RAPID)
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
				if (c.leechDelay == 0)
					c.leechDelay = 7;
			}
		}
	}
	

	public void delayedHit(int i) { // npc hit delay
		if (Server.npcHandler.npcs[i] != null) {
			if (Server.npcHandler.npcs[i].isDead) {
				c.npcIndex = 0;
				c.dBowHits = 0;
				c.dbowSpec = false;
				return;
			}
			Server.npcHandler.npcs[i].facePlayer(c.playerId);
			
			if (Server.npcHandler.npcs[i].underAttackBy > 0 && Server.npcHandler.getsPulled(i)) {
				Server.npcHandler.npcs[i].killerId = c.playerId;			
			} else if (Server.npcHandler.npcs[i].underAttackBy < 0 && !Server.npcHandler.getsPulled(i)) {
				Server.npcHandler.npcs[i].killerId = c.playerId;
			}
			c.lastNpcAttacked = i;
			if(c.projectileStage == 0) { // melee hit damage
				applyNpcMeleeDamage(i, 1);
				if(c.doubleHit) {
					applyNpcMeleeDamage(i, 2);
				}				
			}

			if(!c.castingMagic && c.projectileStage > 0) { // range hit damage
				int damage = Misc.random(rangeMaxHit());
				int damage2 = -1;
				if (c.bowSpecShot == 1)
					damage2 = Misc.random(rangeMaxHit());
				boolean ignoreDef = false;
				if (Misc.random(5) == 1 && c.lastArrowUsed == 9243) {
					ignoreDef = true;
					Server.npcHandler.npcs[i].gfx0(758);
				}

				
				if(Misc.random(Server.npcHandler.npcs[i].defence) > Misc.random(10+calculateRangeAttack()) && !ignoreDef) {
					damage = 0;
				} else if (Server.npcHandler.npcs[i].npcType == 2881 || Server.npcHandler.npcs[i].npcType == 2883 && !ignoreDef) {
					damage = 0;
				}
				
				if (c.bowSpecShot == 1) {
					if (Misc.random(Server.npcHandler.npcs[i].defence) > Misc.random(10+calculateRangeAttack()))
						damage2 = 0;
				}
				if (c.dbowSpec) {
					Server.npcHandler.npcs[i].gfx100(1100);
					c.dBowHits++;
					if (damage < 8)
						damage = 8;
					if (c.dBowHits == 2) {
						c.dbowSpec = false;
						c.dBowHits = 0;
					}
				}
				if (damage > 0 && Misc.random(5) == 1 && c.lastArrowUsed == 9244) {
					damage *= 1.45;
					Server.npcHandler.npcs[i].gfx0(756);
				}
				
				if (Server.npcHandler.npcs[i].HP - damage < 0) { 
					damage = Server.npcHandler.npcs[i].HP;
				}
				if (Server.npcHandler.npcs[i].HP - damage <= 0 && damage2 > 0) {
					damage2 = 0;
				}
				if (damage > 0) {
					if (Server.npcHandler.npcs[i].npcType >= 3777 && Server.npcHandler.npcs[i].npcType <= 3780) {
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
				appendHit(Server.npcHandler.npcs[i], damage, 0, 1, 1);
				addCombatXP(1, damage);
				if (damage2 > -1) {
					appendHit(Server.npcHandler.npcs[i], damage2, 0, 1, 2);
					c.totalDamageDealt += damage2;	
					addCombatXP(1, damage2);
				}
				if (c.inBarbDef) {
					c.barbDamage += damage;
					if (damage2 > 0) 
						c.barbDamage += damage2;
				}
				if (c.killingNpcIndex != c.oldNpcIndex) {
					c.totalDamageDealt = 0;				
				}
				c.killingNpcIndex = c.oldNpcIndex;
				c.totalDamageDealt += damage;
				c.curses().soulSplit(i, damage);
			} else if (c.projectileStage > 0) { // magic hit damage
				int damage = Misc.random(finalMagicDamage(c));
				if(godSpells()) {
					if(System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
						damage += Misc.random(10);
					}
				}
				boolean magicFailed = false;
				int bonusAttack = getBonusAttack(i);
				if (Misc.random(Server.npcHandler.npcs[i].defence) > 10+ Misc.random(mageAtk()) + bonusAttack) {
					damage = 0;
					magicFailed = true;
				} else if (Server.npcHandler.npcs[i].npcType == 2881 || Server.npcHandler.npcs[i].npcType == 2882) {
					damage = 0;
					magicFailed = true;
				}
				
				
				if (Server.npcHandler.npcs[i].inMulti() && multis()) {
					c.barrageCount = 0;
					for (int j = 0; j < Server.npcHandler.npcs.length; j++) {
						if (Server.npcHandler.npcs[j] != null) {
							if (c.barrageCount >= 9)
								break;
							int nX = Server.npcHandler.npcs[j].getX(),
							nY = Server.npcHandler.npcs[j].getY(),
							pX = Server.npcHandler.npcs[i].getX(),
							pY = Server.npcHandler.npcs[i].getY();
							if ((nX - pX == -1 || nX - pX == 0 || nX - pX == 1) && (nY - pY == -1 || nY - pY == 0 || nY - pY == 1) && i != j) 
								appendMultiBarrageNPC(j, c.magicFailed);
						}	
					}
				}
				
				if (Server.npcHandler.npcs[i].HP - damage < 0) { 
					damage = Server.npcHandler.npcs[i].HP;
				}
				c.getPA().refreshSkill(3);
				c.getPA().refreshSkill(6);
				if (damage > 0) {
					if (Server.npcHandler.npcs[i].npcType >= 3777 && Server.npcHandler.npcs[i].npcType <= 3780) {
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
						if(c.constitution + heal >= c.maxConstitution) {
							c.constitution = c.maxConstitution;
						} else {
							c.constitution += heal;
						}
						c.getPA().refreshSkill(3);
						break;
					}

				}
				Server.npcHandler.npcs[i].underAttack = true;
				if(c.MAGIC_SPELLS[c.oldSpellId][6] != 0) {
					appendHit(Server.npcHandler.npcs[i], damage, 0, 2, 1);
					addCombatXP(2, damage);
					c.totalDamageDealt += damage;
					if (c.inBarbDef) 
						c.barbDamage += damage;
				} else {
					addCombatXP(1, 0);
				}
				c.curses().soulSplit(i, damage);
				c.killingNpcIndex = c.oldNpcIndex;			
				Server.npcHandler.npcs[i].updateRequired = true;
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
		}
		if(c.bowSpecShot == 1) {
			fireProjectileNpc();
			c.hitDelay = 2;
			c.bowSpecShot = 0;
		}
	}
	
	
	public void applyNpcMeleeDamage(int i, int damageMask) {
		int damage = Misc.random(calculateMeleeMaxHit());
		boolean fullVeracsEffect = c.getPA().fullVeracs() && Misc.random(3) == 1;
		if (Server.npcHandler.npcs[i].HP - damage < 0) { 
			damage = Server.npcHandler.npcs[i].HP;
		}
		
		if (!fullVeracsEffect) {
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
		if (damage > 0) {
			if (Server.npcHandler.npcs[i].npcType >= 3777 && Server.npcHandler.npcs[i].npcType <= 3780) {
				c.pcDamage += damage;					
			}				
		}
		if (damage > 0 && guthansEffect) {
			c.constitution += damage;
			if (c.constitution > c.maxConstitution)
				c.constitution = c.maxConstitution;
			c.getPA().refreshSkill(3);
			Server.npcHandler.npcs[i].gfx0(398);		
		}
		Server.npcHandler.npcs[i].underAttack = true;
		c.killingNpcIndex = c.npcIndex;
		c.lastNpcAttacked = i;
		switch (c.specEffect) {
			case 4:
				if (damage > 0) {
					if (c.constitution + damage > c.maxConstitution)
						if (c.constitution > c.maxConstitution);
						else 
						c.constitution = c.maxConstitution;
					else 
						c.constitution += damage;
				}
			break;
			case 9:
				damage = korasiDamage(null);
				calculateMeleeAttack();
				Server.npcHandler.npcs[i].gfx0(1248);
			break;
			case 10:
				c.hit1 = damage;
				if (c.hit1 > 0)
					c.hit2 = c.hit1/2;
				else
					c.hit2 = Misc.random(Server.npcHandler.npcs[i].defence) > 10 + Misc.random(calculateMeleeAttack()) ? Misc.random(calculateMeleeMaxHit()) : 0;
				if (c.hit2 > 0)
					c.hit3 = c.hit2/2;
				else
					c.hit3 = Misc.random(Server.npcHandler.npcs[i].defence) > 10 + Misc.random(calculateMeleeAttack()) ? Misc.random(calculateMeleeMaxHit()) : 0;
				if (c.hit3 > 0)
					c.hit4 = c.hit3 + 1;
				else
					c.hit4 = Misc.random(Server.npcHandler.npcs[i].defence) > 10 + Misc.random(calculateMeleeAttack()) ? Misc.random(calculateMeleeMaxHit()) : 1;
				appendHit(Server.npcHandler.npcs[i], c.hit2, 0, 0, damageMask == 1 ? 2 : 1);
				c.clawDelay = 1;
				c.clawTarg = 0;
				c.clawTargNPC = i;
			break;
		
		}
		appendHit(Server.npcHandler.npcs[i], damage, 0, c.korasiSpec ? 2 : 0, damageMask);
		c.korasiSpec = false;
		addCombatXP(0, damage);
		c.specEffect = 0;
		c.totalDamageDealt += damage;
		if (c.inBarbDef) 
			c.barbDamage += damage;
		c.curses().soulSplit(i, damage);
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
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, getProjectileSpeed() - 20, getRangeProjectileGFX(), 43, 31,  c.oldNpcIndex + 1, getStartDelay(), 29);
			}
		}
	}
	

	
	/**
	* Attack Players, same as npc tbh xD
	**/
	
		public void attackPlayer(int i) {

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
				boolean usingCross = c.playerEquipment[c.playerWeapon] == 9185 || c.playerEquipment[c.playerWeapon] == 18357;
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
					c.sendMessage("You have run out of arrows!");
					c.stopMovement();
					resetPlayerAttack();
					return;
				}
				if(correctBowAndArrows() < c.playerEquipment[c.playerArrows] && Config.CORRECT_ARROWS && usingBow && !usingCrystalBow() && c.playerEquipment[c.playerWeapon] != 9185 && c.playerEquipment[c.playerWeapon] != 18357 && !c.usingMagic) {
					c.sendMessage("You can't use "+c.getItems().getItemName(c.playerEquipment[c.playerArrows]).toLowerCase()+"s with a "+c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()+".");
					c.stopMovement();
					resetPlayerAttack();
					return;
				}
				if ((c.playerEquipment[c.playerWeapon] == 9185 || c.playerEquipment[c.playerWeapon] == 18357) && !properBolts() && !c.usingMagic) {
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
				if(!usingBow && !c.usingMagic && !usingOtherRangeWeapons) { // melee hit delay
					c.followId = Server.playerHandler.players[c.playerIndex].playerId;
					c.getPA().followPlayer();
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
					if (c.fightMode == c.RAPID)
						c.attackTimer--;
					if (usingCross)
						c.usingBow = true;
					c.usingBow = true;
					c.followId = Server.playerHandler.players[c.playerIndex].playerId;
					c.getPA().followPlayer();
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
					c.getPA().followPlayer();
					c.gfx100(getRangeStartGFX());
					if (c.fightMode == c.RAPID)
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
						c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50, 85, 368, 25, 25, -i - 1, getStartDelay());
					}
					c.magicFailed = canHitMage(o);
					int freezeDelay = getFreezeTime();//freeze time
					if(freezeDelay > 0 && Server.playerHandler.players[i].freezeTimer <= -3 && !c.magicFailed) { 
						Server.playerHandler.players[i].freezeTimer = freezeDelay;
						o.resetWalkingQueue();
						o.getCombat().resetPlayerAttack();
						o.sendMessage("You have been frozen!");
						o.orb = false;
						o.frozenBy = c.playerId;
					}
					if (!c.autocasting && c.spellId <= 0)
						c.playerIndex = 0;
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
				if (c.leechDelay == 0)
					c.leechDelay = 7;
			}
		}
	}
	
	public boolean usingCrystalBow() {
		return c.playerEquipment[c.playerWeapon] >= 4212 && c.playerEquipment[c.playerWeapon] <= 4223;	
	}
	
	public void appendHit(Client c2, int damage, int mask, int icon, boolean playerHitting, int soak) {
		boolean maxHit = false;
		if (playerHitting) {
			switch (icon) {
				case 0:
					maxHit = damage >= calculateMeleeMaxHit() - 20;
					break;
				case 1:
					maxHit = damage >= rangeMaxHit() - 20;
					break;
				case 2:
					maxHit = damage == finalMagicDamage(c);
					break;
			}
		}
		if(damage > c2.constitution)
			damage = c2.constitution;
		if (c.korasiSpec)
			maxHit = true;
		c2.handleHitMask(damage, mask, icon, soak, maxHit);
		c2.dealDamage(damage);
	}
	
	public void appendHit(NPC n, int damage, int mask, int icon, int damageMask) {
		n.HP -= damage;
		boolean maxHit = false;
		switch (icon) {
			case 0:
				maxHit = damage >= calculateMeleeMaxHit() - 20;
			break;
			case 1:
				maxHit = damage >= rangeMaxHit() - 20;
			break;
			case 2:
				maxHit = damage == finalMagicDamage(c);
			break;
		}
		if (maxHit)
			mask = 1;
		switch(damageMask) {
			case 1:
				n.hitDiff = damage;
				n.hitUpdateRequired = true;
				n.updateRequired = true;
				n.hitIcon = icon;
				n.hitMask = mask;
			break;
			case 2:
				n.hitDiff2 = damage;
				n.hitUpdateRequired2 = true;	
				n.updateRequired = true;
				c.doubleHit = false;
				n.hitIcon2 = icon;
				n.hitMask2 = mask;
			break;
		}
	}
	
	public void appendVengeance(int otherPlayer, int damage) {
		if (damage <= 0)
			return;
		Player o = Server.playerHandler.players[otherPlayer];
		o.forcedText = "Taste vengeance!";
		o.forcedChatUpdateRequired = true;
		o.updateRequired = true;
		o.vengOn = false;
		if ((o.constitution - damage) > 0) {
			damage = (int)(damage * 0.75);
			if (damage > c.constitution)
				damage = c.constitution;
			appendHit(c, damage, 0, -1, false, 0);
		}	
		c.updateRequired = true;
	}
	
	
	
	public void playerDelayedHit(int i) {
		if (Server.playerHandler.players[i] != null) {
			if (Server.playerHandler.players[i].isDead || c.isDead || c.constitution <= 0) {
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
			if(c.projectileStage == 0) { // melee hit damage								
				applyPlayerMeleeDamage(i, 1);
				if(c.doubleHit) {
					applyPlayerMeleeDamage(i, 2);
				}	
			}
			
			if(!c.castingMagic && c.projectileStage > 0) { // range hit damage
				int damage = Misc.random(rangeMaxHit());
				int damage2 = -1;
				if (c.lastWeaponUsed == 11235 || c.bowSpecShot == 1)
					damage2 = Misc.random(rangeMaxHit());
				boolean ignoreDef = false;
				if (Misc.random(4) == 1 && c.lastArrowUsed == 9243) {
					ignoreDef = true;
					o.gfx0(758);
				}					
				if(Misc.random(10+o.getCombat().calculateRangeDefence()) > Misc.random(10+calculateRangeAttack()) && !ignoreDef) {
					damage = 0;
				}
				
				if (c.lastWeaponUsed == 11235 || c.bowSpecShot == 1) {
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
				if (damage > 0 && Misc.random(5) == 1 && c.lastArrowUsed == 9244) {
					damage *= 1.45;
					o.gfx0(756);
				}
				if (o.curseActive(c.curses().DEFLECT_MISSILES) && System.currentTimeMillis() - o.protRangeDelay > 1500) {
					damage = (int)damage * 40 / 100;
					o.curses().deflect(c, damage, 1);
					if (c.lastWeaponUsed == 11235 || c.bowSpecShot == 1) {
						damage2 = (int)damage2 * 40 / 100;
						o.curses().deflect(c, damage2, 1);
					}
				}
				if(o.prayerActive(17) && System.currentTimeMillis() - o.protRangeDelay > 1500) { // if prayer active reduce damage by half 
					damage = (int)damage * 60 / 100;
					if (c.lastWeaponUsed == 11235 || c.bowSpecShot == 1)
						damage2 = (int)damage2 * 60 / 100;
				}
				if (Server.playerHandler.players[i].constitution - damage < 0) { 
					damage = Server.playerHandler.players[i].constitution;
				}
				if (Server.playerHandler.players[i].constitution - damage - damage2 < 0) { 
					damage2 = Server.playerHandler.players[i].constitution - damage;
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
				if (damage2 > 0)
					applyRecoil(damage2, i);
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
				Server.playerHandler.players[i].damageTaken[c.playerId] += damage;
				c.killedBy = Server.playerHandler.players[i].playerId;
				int soak = o.getCombat().damageSoaked(damage, "Range");
				damage -= soak;
				appendHit(o, damage, 0, 1, true, soak);
				addCombatXP(1, damage);
				if (damage2 != -1) {
					int soak2 = o.getCombat().damageSoaked(damage2, "Melee");
					damage2 -= soak2;
					Server.playerHandler.players[i].damageTaken[c.playerId] += damage2;
					appendHit(o, damage2, 0, 1, true, soak2);
					addCombatXP(1, damage2);
				}
				Server.playerHandler.players[i].updateRequired = true;
				applySmite(i, damage);
				if (damage2 != -1) {
					applySmite(i, damage2);
					c.curses().soulSplit(i, damage2);
				}
				c.curses().soulSplit(i, damage);
			
			} else if (c.projectileStage > 0) { // magic hit damage
				int damage = Misc.random(finalMagicDamage(c));
				if(godSpells()) {
					if(System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
						damage += 10;
					}
				}
				if (c.magicFailed)
					damage = 0;
				if (o.curseActive(c.curses().DEFLECT_MAGIC) && System.currentTimeMillis() - o.protMageDelay > 1500) {
					damage = (int)damage * 40 / 100;
					o.curses().deflect(c, 0, 0);
				}
				if(o.prayerActive(16) && System.currentTimeMillis() - o.protMageDelay > 1500) { // if prayer active reduce damage by half 
					damage = (int)damage * 60 / 100;
				}
				if (Server.playerHandler.players[i].constitution - damage < 0) {
					damage = Server.playerHandler.players[i].constitution;
				}
				if (o.vengOn)
					appendVengeance(i, damage);
				if (damage > 0)
					applyRecoil(damage, i);
				c.getPA().refreshSkill(3);
				c.getPA().refreshSkill(6);
				
				
				int endGFX = c.MAGIC_SPELLS[c.oldSpellId][5];
				if (endGFX == 369 && o.orb) {
					endGFX = 1677;
				}
				o.orb = true;
				if(getEndGfxHeight() == 100 && !c.magicFailed){ // end GFX
					Server.playerHandler.players[i].gfx100(c.MAGIC_SPELLS[c.oldSpellId][5]);
				} else if (!c.magicFailed){
					if (endGFX == 1677)
						Server.playerHandler.players[i].gfx(1677, 50);
					else
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
							if (o.prayerActive(16) && System.currentTimeMillis() - o.protMageDelay > 1500 || 
							o.curseActive(c.curses().DEFLECT_MAGIC) && System.currentTimeMillis() - o.protMageDelay > 1500)
								o.teleBlockLength = 150000;
							else
								o.teleBlockLength = 300000;
						}		
						break;
						
						case 12901:
						case 12919: // blood spells
						case 12911:
						case 12929:
						int heal = (int)(damage / 4);
						if(c.constitution + heal > c.maxConstitution) {
							c.constitution = c.maxConstitution;
						} else {
							c.constitution += heal;
						}
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
					Server.playerHandler.players[i].damageTaken[c.playerId] += damage;
					c.totalPlayerDamageDealt += damage;
					if (!c.magicFailed) {
						addCombatXP(2, damage);
						int soak = o.getCombat().damageSoaked(damage, "Magic");
						damage -= soak;
						appendHit(o, damage, 0, 2, true, soak);
					} else 
						addCombatXP(2, 0);
				}
				addCombatXP(2, 0);
				applySmite(i, damage);
				c.curses().soulSplit(i, damage);
				c.killedBy = Server.playerHandler.players[i].playerId;
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
					int spellGFX = c.MAGIC_SPELLS[c.spellId][5];
					if (spellGFX == 369 && c2.orb) { //ORB
						spellGFX = 1677;
					}
					c2.orb = true;
					if(getEndGfxHeight() == 100){ // end GFX
						c2.gfx100(c.MAGIC_SPELLS[c.oldSpellId][5]);
					} else {
						if (spellGFX == 1677)
							c2.gfx0(spellGFX);
						else
							c2.gfx0(c.MAGIC_SPELLS[c.oldSpellId][5]);
					}
					int damage = Misc.random(finalMagicDamage(c));
					if (c2.prayerActive(12)) {
						damage *= (int)(.60);
					}
					int soak = c2.getCombat().damageSoaked(damage, "Melee");
					damage -= soak;
					appendHit(c2, damage, 0, 2, true, soak);
					addCombatXP(2, damage);
					Server.playerHandler.players[playerId].damageTaken[c.playerId] += damage;
					c.totalPlayerDamageDealt += damage;
					multiSpellEffect(playerId, damage);
				} else {
					c2.gfx100(85);
				}			
			}		
		}	
	}
	
	public void appendMultiBarrageNPC(int npcId, boolean splashed) {
		if (Server.npcHandler.npcs[npcId] != null) {
			NPC n = Server.npcHandler.npcs[npcId];
			if (n.isDead || n.HP <= 0)
				return;
			if (checkMultiBarrageReqsNPC(npcId)) {
				c.barrageCount++;
				if (Misc.random(Server.npcHandler.npcs[npcId].defence) < (10 + Misc.random(mageAtk())) && !c.magicFailed) {
					if(getEndGfxHeight() == 100)
						n.gfx100(c.MAGIC_SPELLS[c.oldSpellId][5]);
					else
						n.gfx0(c.MAGIC_SPELLS[c.oldSpellId][5]);
					int damage = Misc.random(finalMagicDamage(c));
					if (n.HP - damage < 0) {
						damage = n.HP;					
					}
					appendHit(n, damage, 0, 2, 1);
					addCombatXP(2, damage);
					n.underAttackBy = c.playerId;
					n.underAttack = true;
					if (c.inBarbDef) 
						c.barbDamage += damage;
					c.totalDamageDealt += damage;
					multiSpellEffectNPC(npcId, damage);
				} else
					n.gfx100(85);
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
				if(c.constitution + heal >= c.maxConstitution) {
					c.constitution = c.maxConstitution;
				} else {
					c.constitution += heal;
				}
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
	
	public void multiSpellEffectNPC(int npcId, int damage) {					
		switch(c.MAGIC_SPELLS[c.oldSpellId][0]) {
			case 12919: // blood spells
			case 12929:
				int heal = (int)(damage / 4);
				if(c.constitution + heal >= c.maxConstitution)
					c.constitution = c.maxConstitution;
				else 
					c.constitution += heal;
			break;
			case 12891:
			case 12881:
				if (Server.npcHandler.npcs[npcId].freezeTimer == 0) {
					Server.npcHandler.npcs[npcId].freezeTimer = getFreezeTime();
				}
			break;
		}	
	}
	
	public void applyPlayerMeleeDamage(int i, int damageMask){
		Client o = (Client) Server.playerHandler.players[i];
		if(o == null) {
			return;
		}
		int damage = 0;
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
		if (!meleeHitSuccess(calculateMeleeAttack(), o.getCombat().calculateMeleeDefence()) && !veracsEffect) {
			damage = 0;
			c.bonusAttack = 0;
		} else if (c.playerEquipment[c.playerWeapon] == 5698 && o.poisonDamage <= 0 && Misc.random(3) == 1) {
			//o.getPA().appendPoison(13);//This is disabled due to poison hitmarks working 192837
			c.bonusAttack += damage/3;
		} else {
			c.bonusAttack += damage/3;
		}
		if (o.curseActive(c.curses().DEFLECT_MELEE) && System.currentTimeMillis() - o.protMeleeDelay > 1500 && !veracsEffect) {
			damage = (int)damage * 40 / 100;
			o.curses().deflect(c, damage, 0);
		}
		if(o.prayerActive(18) && System.currentTimeMillis() - o.protMeleeDelay > 1500 && !veracsEffect) { // if prayer active reduce damage by 40%
			damage = (int)damage * 60 / 100;
		}
		if (c.maxNextHit) {
			damage = calculateMeleeMaxHit();
		}
		if (damage > 0 && guthansEffect) {
			c.constitution += damage;
			if (c.constitution > c.maxConstitution)
				c.constitution = c.maxConstitution;
			o.gfx0(398);		
		}
		if (c.ssSpec && damageMask == 2) {
			damage = 5 + Misc.random(11);
			c.ssSpec = false;
		}
		if (o.vengOn && damage > 0)
			appendVengeance(i, damage);
		if (damage > 0)
			applyRecoil(damage, i);
		switch (c.specEffect) {
			case 1: // dragon scimmy special
			if(damage > 0) {
				o.sendMessage("You have been injured!");
				o.stopPrayerDelay = System.currentTimeMillis();
				o.rem(o.isOnCurses() ? Prayer.DEFLECT_MELEE : Prayer.PROTECT_FROM_MELEE);
				o.rem(o.isOnCurses() ? Prayer.DEFLECT_RANGE : Prayer.PROTECT_FROM_MISSILES);
				o.rem(o.isOnCurses() ? Prayer.DEFLECT_MAGIC : Prayer.PROTECT_FROM_MAGIC);
				Prayers.refreshPrayers(o, false);
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
					if (c.constitution + damage > c.maxConstitution)
						if (c.constitution > c.maxConstitution);
						else 
						c.constitution = c.maxConstitution;
					else 
						c.constitution += damage;
					c.getPA().refreshSkill(3);
				}
			break;
			case 9:
				damage = korasiDamage(o);
				o.gfx0(1248);
			break;
			case 10:
				c.hit1 = damage;
				if (c.hit1 > 0)
					c.hit2 = c.hit1/2;
				else
					c.hit2 = meleeHitSuccess(calculateMeleeAttack(), o.getCombat().calculateMeleeDefence()) ? Misc.random(calculateMeleeMaxHit()) : 0;
				if (c.hit2 > 0)
					c.hit3 = c.hit2/2;
				else
					c.hit3 = meleeHitSuccess(calculateMeleeAttack(), o.getCombat().calculateMeleeDefence()) ? Misc.random(calculateMeleeMaxHit()) : 0;
				if (c.hit3 > 0)
					c.hit4 = c.hit3 + 1;
				else
					c.hit4 = meleeHitSuccess(calculateMeleeAttack(), o.getCombat().calculateMeleeDefence()) ? Misc.random(calculateMeleeMaxHit()) : 1;
				int soak = o.getCombat().damageSoaked(c.hit2, "Melee");
				c.hit2 -= soak;
				appendHit(o, c.hit2, 0, 0, true, soak);
				c.clawDelay = 1;
				c.clawTarg = i;
				c.clawTargNPC = 0;
			break;
		}
		c.specEffect = 0;
		Server.playerHandler.players[i].logoutDelay = System.currentTimeMillis();
		Server.playerHandler.players[i].underAttackBy = c.playerId;
		Server.playerHandler.players[i].killerId = c.playerId;	
		Server.playerHandler.players[i].singleCombatDelay = System.currentTimeMillis();
		if (c.killedBy != Server.playerHandler.players[i].playerId)
			c.totalPlayerDamageDealt = 0;
		c.killedBy = Server.playerHandler.players[i].playerId;
		applySmite(i, damage);
		c.curses().soulSplit(i, damage);
		int soak = o.getCombat().damageSoaked(damage, c.korasiSpec ? "Magic" : "Melee");
		damage -= soak;
		appendHit(o, damage, 0, c.korasiSpec ? 2 : 0, true, soak);
		addCombatXP(1, damage);
		addCombatXP(0, damage);
		switch(damageMask) {
			case 1:
			Server.playerHandler.players[i].damageTaken[c.playerId] += damage;
			c.totalPlayerDamageDealt += damage;
			break;
		
			case 2:
			Server.playerHandler.players[i].damageTaken[c.playerId] += damage;
			c.totalPlayerDamageDealt += damage;
			c.doubleHit = false;
			break;			
		}
		c.korasiSpec = false;
	}
	
	public void applySmite(int index, int damage) {
		if (!c.prayerActive(23))
			return;
		if (damage <= 0)
			return;
		if (Server.playerHandler.players[index] != null) { 
			Client c2 = (Client)Server.playerHandler.players[index];
			c2.playerLevel[5] -= (int)(damage/40);//4
			if (c2.playerLevel[5] <= 0) {
				c2.playerLevel[5] = 0;
				c2.getCombat().resetPrayers();
			}
			c2.getPA().refreshSkill(5);
		}
	
	}
	
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
				if (!c.msbSpec)
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, getProjectileSpeed(), getRangeProjectileGFX(), 43, 31, - c.oldPlayerIndex - 1, getStartDelay(), 16);
				else if (c.msbSpec) {
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, getProjectileSpeed(), getRangeProjectileGFX(), 43, 31, - c.oldPlayerIndex - 1, getStartDelay(), 10);
					c.msbSpec = false;
				}
				if (usingDbow())
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, getProjectileSpeed(), getRangeProjectileGFX(), 60, 31, - c.oldPlayerIndex - 1, getStartDelay(), 30);
			}
		}
	}
	
	public boolean usingDbow() {
		return c.playerEquipment[c.playerWeapon] == 11235;
	}
		
	/**
	*Specials
	**/
	
	public void activateSpecial(int weapon, int i){
	if(c.playerEquipment[c.playerRing] == 19669) {
		c.specAmount += c.specAmount*0.1;
	}
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
			
			case 11730:
			c.gfx100(1224);
			c.startAnimation(811);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			c.doubleHit = true;
			c.ssSpec = true;
			c.specAccuracy = 1.30;
			break;
			
			case 4151:
			case 15445:
			case 15444:
			case 15443:
			case 15442:
			case 15441:
			if(Server.npcHandler.npcs[i] != null) {
				Server.npcHandler.npcs[i].gfx0(1208);
			}
			c.specAccuracy = 1.10;
			c.startAnimation(11956);
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			break;
			
			case 14484:
				c.startAnimation(10961);
				c.gfx0(1950);
				c.specDamage = -0.20;
				c.specAccuracy = 1.40;//these are overpowered as fuck omfgomfg bryce hit a 518-518 on me in shit str gear like welfare l0l
				c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
				c.specEffect = 10;
			break;
							case 13905: // Vesta spear
								c.startAnimation(10499);
								c.gfx0(1835);
                                c.specAccuracy = 4.25;
                                //c.specEffect = 6;
                                c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
				break;

				case 13899: // Vesta LongSword
					c.startAnimation(10502);
					c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()+1);
					c.specDamage = 1.05;
					c.specAccuracy = 0.5;
				break;

				case 13902: // Statius
				if (c.fullStatius()) {
					c.startAnimation(10505);
					c.gfx0(1840);
					c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()+1);
					c.specDamage = 1.12;
					c.specAccuracy = 1.15; 
				} else { 
					c.startAnimation(10505);
					c.gfx0(1840);
					c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()+1);
					c.specDamage = 1.00;
					c.specAccuracy = 1.25;
					}
				break;
			
			case 19780:
				c.startAnimation(4000);
				c.gfx(1247, 0);
				c.korasiSpec = true;
				c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()+1);
				c.specEffect = 9;
				c.specAccuracy = 1.4;
			break;
			
			case 11694: // Armadyl godsword
				c.startAnimation(7074);
				c.specDamage = 1.25;
				c.specAccuracy = 2.50;
				c.gfx0(1222);
				c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			break;
			
			case 11700: // Zamorak godsword
				c.startAnimation(7070);		
				c.gfx0(1221);
				c.specAccuracy = 1.25;
				c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
				c.specEffect = 2;
			break;
			
			case 11696: // Bandos godsword
				c.startAnimation(7073);
				c.gfx0(1223);
				c.specDamage = 1.10;
				c.specAccuracy = 1.5;
				c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
				c.specEffect = 3;
			break;
			
			case 11698: // Saradomin godsword
				c.startAnimation(7071);
				c.gfx0(1220);
				c.specAccuracy = 1.25;
				c.specEffect = 4;
				c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			break;
			
			case 1249:
				c.startAnimation(405);
				c.gfx100(253);
				if (c.playerIndex > 0) {
					Client o = (Client)Server.playerHandler.players[i];
					o.getPA().getSpeared(c.absX, c.absY);
				}	
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
			if (c.fightMode == c.RAPID)
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
			if (c.fightMode == c.RAPID)
				c.attackTimer--;
			if (c.playerIndex > 0)
				fireProjectilePlayer();
			else if (c.npcIndex > 0)
				fireProjectileNpc();	
			break;
			
			case 11235: // dark bow	
			c.usingBow = true;
			c.dbowSpec = true;
			c.rangeItemUsed = c.playerEquipment[c.playerArrows];
			c.getItems().deleteArrow();
			c.getItems().deleteArrow();
			c.lastWeaponUsed = weapon;
			c.startAnimation(426);
			c.projectileStage = 1;
			c.gfx100(getRangeStartGFX());
			c.hitDelay = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
			c.hitDelay2 = getHitDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase()) + 1;
			if (c.fightMode == c.RAPID)
				c.attackTimer--;
			if (c.playerIndex > 0)
				fireProjectilePlayer();
			else if (c.npcIndex > 0)
				fireProjectileNpc();
			c.specAccuracy = 1.75;
			c.specDamage = 1.65;
			break;
		}
		c.delayedDamage = (int) (c.ramboModeInitiated ? ((calculateMeleeMaxHit() * 0.85) + Misc.random(100)) : Misc.random(calculateMeleeMaxHit()));
		c.delayedDamage2 = (int) (c.ramboModeInitiated ? ((calculateMeleeMaxHit() * 0.85) + Misc.random(20)) : Misc.random(calculateMeleeMaxHit()));
		c.usingSpecial = false;
		c.getItems().updateSpecialBar();
	}
	
	
	public boolean checkSpecAmount(int weapon) {
		double modifier = c.playerEquipment[c.playerRing] == 19669 ? 0.9 : 1;
		switch(weapon) {
			case 19780: 
			if(c.specAmount >= (6 * modifier)) {
				c.specAmount -= (6 * modifier);
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;
			
			case 1249:
			case 1215:
			case 1231:
			case 5680:
			case 5698:
			case 13899:
			case 1305:
			case 1434:
			if(c.specAmount >= (2.5 * modifier)) {
				c.specAmount -= (2.5 * modifier);
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;
			
			case 4151:
			case 15445:
			case 15444:
			case 15443:
			case 15442:
			case 15441:
            case 11694:
			case 11698:
			case 4153:
			case 14484:
			case 13902:
			if(c.specAmount >= (5 * modifier)) {
				c.specAmount -= (5 * modifier);
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;
			
			case 3204:
			if(c.specAmount >= (3 * modifier)) {
				c.specAmount -= (3 * modifier);
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;
			
			case 1377:
			case 11696:
			case 11730:
			if(c.specAmount >= (10 * modifier)) {
				c.specAmount -= (10 * modifier);
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;
			
			case 4587:
			case 859:
			case 861:
			case 11235:
			case 11700:
			if(c.specAmount >= (5.5 * modifier)) {
				c.specAmount -= (5.5 * modifier);
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
	
	public void handlePrayerDrain() {
		double amountDrain = 0;
		for(int i = 0; i < c.getActivePrayers().size(); i++) {
			Prayer prayer = c.getActivePrayers().get(i);
			double drain = prayer.getDrain();
			double bonus = 0.035 * c.playerBonus[11];
			drain = drain * (1 + bonus);
			drain = 0.6 / drain;
			amountDrain += drain;
		}
		c.prayerPoint -= amountDrain;
		if (c.prayerPoint <= 0) {
			c.prayerPoint = 1.0 + c.prayerPoint;
			reducePrayerLevel();
		}
	
	}
	
	public void reducePrayerLevel() {
		if(c.playerLevel[5] - 1 > 0) {
			c.playerLevel[5] -= 1;
		} else {
			c.sendMessage("You have run out of prayer points!");
			c.playerLevel[5] = 0;
			resetPrayers();
			c.prayerId = -1;	
		}
		c.getPA().refreshSkill(5);
	}
	
	public void resetPrayers() {
		c.activePrayers.clear();
		Prayers.refreshPrayers(c, false);
		c.headIcon = -1;
		c.getPA().requestUpdates();
	}
	
	/**
	* Adding combat XP
	**/
	public void addCombatXP(int attackType, int damage) {
		// Attack type is based on the kind of damage that is being done (ranged, mage, melee)
		c.getPA().addSkillXP((int)(Config.MELEE_EXP_RATE/3 * damage), 3);
		switch (attackType) {
			case 0: // Melee
				switch (c.fightMode) {
					case 0: // Accurate
						c.getPA().addSkillXP(4 * damage, 0);
					break;
					case 1: // Aggressive
						c.getPA().addSkillXP(4 * damage, 2);
					break;
					case 2: // Block
						c.getPA().addSkillXP(4 * damage, 1);
					break;
					case 3: // Controlled
						for (int i = 0; i < 3; i++)
							c.getPA().addSkillXP((int)(1.3 * damage), i);//1.3
					break;
				}
			break;
			case 1: // Ranged
				switch (c.fightMode) {
					case 0: // Accurate
					case 1: // Rapid
						c.getPA().addSkillXP(4 * damage, 4);
					break;
					case 3: // Block
						c.getPA().addSkillXP(2 * damage, 1);
						c.getPA().addSkillXP(2 * damage, 4);
					break;
				}
			break;
			case 2: // Magic
				int magicXP = (4 * damage) + c.MAGIC_SPELLS[c.oldSpellId][7];
				c.getPA().addSkillXP(magicXP, 6);
			break;
		}
	}
	/**
	* Wildy and duel info
	**/
	
	public boolean checkReqs() {
		if(Server.playerHandler.players[c.playerIndex] == null) {
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
		if(!Server.playerHandler.players[c.playerIndex].inWild() && !c.playerName.toLowerCase().equals("runited") && !c.playerName.toLowerCase().equals("ey cuzzo")) {
			c.sendMessage("That player is not in the wilderness.");
			c.stopMovement();
			c.getCombat().resetPlayerAttack();
			return false;
		}
		if(!c.inWild() && !c.playerName.toLowerCase().equals("runited") && !c.playerName.toLowerCase().equals("ey cuzzo")) {
			c.sendMessage("You are not in the wilderness.");
			c.stopMovement();
			c.getCombat().resetPlayerAttack();
			return false;
		}
		if(Config.COMBAT_LEVEL_DIFFERENCE && !c.playerName.toLowerCase().equals("runited") && !c.playerName.toLowerCase().equals("ey cuzzo")) {
			int combatDif1 = c.getCombat().getCombatDifference(c.combatLevel, Server.playerHandler.players[c.playerIndex].combatLevel);
			if(combatDif1 > c.wildLevel || combatDif1 > Server.playerHandler.players[c.playerIndex].wildLevel) {
				c.sendMessage("Your combat level difference is too great to attack that player here.");
				c.stopMovement();
				c.getCombat().resetPlayerAttack();
				return false;
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
		if(Config.COMBAT_LEVEL_DIFFERENCE) {
			int combatDif1 = c.getCombat().getCombatDifference(c.combatLevel, Server.playerHandler.players[i].combatLevel);
			if(combatDif1 > c.wildLevel || combatDif1 > Server.playerHandler.players[i].wildLevel) {
				c.sendMessage("Your combat level difference is too great to attack that player here.");
				return false;
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
	
	public boolean checkMultiBarrageReqsNPC(int i) {
		if(Server.npcHandler.npcs[i] == null)
			return false;
		return true;
	}
	
	/**
	*Weapon stand, walk, run, etc emotes
	**/
	
	public void getPlayerAnimIndex(String weaponName){
		c.playerStandIndex = 0x328;
		c.playerTurnIndex = 0x337;
		c.playerWalkIndex = 0x333;
		c.playerTurn180Index = 0x334;
		c.playerTurn90CWIndex = 0x335;
		c.playerTurn90CCWIndex = 0x336;
		c.playerRunIndex = 0x338;
		
		if(weaponName.contains("ahrim")) {
			c.playerStandIndex = 809;
			c.playerWalkIndex = 1146;
			c.playerRunIndex = 1210;
			return;
		}
		
		if(weaponName.contains("staff") || weaponName.contains("halberd") || weaponName.contains("guthan") || weaponName.contains("rapier") || weaponName.contains("wand")) {
			weaponInfo(12010, 1146, 1210);
			c.playerTurnIndex = 1205;
			c.playerTurn180Index = 1206;
			c.playerTurn90CWIndex = 1207;
			c.playerTurn90CCWIndex = 1208;
			return;
		}
		
		if(weaponName.contains("dharok")) {
			c.playerStandIndex = 0x811;
			c.playerWalkIndex = 0x67F;
			c.playerRunIndex = 12001;
			return;
		}	
		
		if(weaponName.contains("verac")) {
			weaponInfo(1832, 1830, 1831);
			return;
		}
		
		if(weaponName.contains("karil")) {
			c.playerStandIndex = 2074;
			c.playerWalkIndex = 2076;
			c.playerRunIndex = 2077;
			return;
		}
		
		if(weaponName.contains("2h sword") || weaponName.contains("godsword") || weaponName.contains("saradomin sw")) {
			weaponInfo(7047, 7046, 7039);
			c.playerTurnIndex = 7040;
			c.playerTurn180Index = 7045;
			c.playerTurn90CWIndex = 7043;
			c.playerTurn90CCWIndex = 7044;
			return;
		}	
		
		if(weaponName.contains("sword") || weaponName.contains("scimitar")) {
			weaponInfo(15069, 15073, 15070);
			c.playerTurnIndex = 15075;
			c.playerTurn180Index = 15075;
			c.playerTurn90CWIndex = 15076;
			c.playerTurn90CCWIndex = 15077;
			return;
		}
		
		if(weaponName.contains("bow")) {
			c.playerStandIndex = 808;
			c.playerWalkIndex = 819;
			c.playerRunIndex = 824;
			return;
		}

		switch(c.playerEquipment[c.playerWeapon]) {	
			case 4151:
			case 15445:
			case 15444:
			case 15443:
			case 15442:
			case 15441:
				weaponInfo(11973, 11975, 11976);
			break;
			case 15241:
			c.playerStandIndex = 12155;
			c.playerWalkIndex = 12154;
			c.playerRunIndex = 12154;
			break;
			case 18355:
				c.playerStandIndex = 808;
			break;
			case 6528:
				c.playerStandIndex = 0x811;
				c.playerWalkIndex = 2064;
				c.playerRunIndex = 1664;
			break;
			case 18353:
			case 4153:
			c.playerStandIndex = 1662;
			c.playerWalkIndex = 1663;
			c.playerRunIndex = 1664;
			break;
			case 1305:
			c.playerStandIndex = 809;
			break;
			case 19784:
			c.playerStandIndex = 809;
			break;
			case 11716:
			c.playerRunIndex = 12016;
			c.playerWalkIndex = 12012;
			c.playerStandIndex = 12010;
			break;
		}
	}
	
	public void weaponInfo(int s, int w, int r) {
		c.playerStandIndex = s;
		c.playerWalkIndex = w;
		c.playerRunIndex = r;
	}
	
	/**
	* Weapon emotes
	**/
	
	public int getWepAnim(String weaponName) {
		if(c.playerEquipment[c.playerWeapon] <= 0) {
			if (c.combatType(c.ACCURATE) || c.combatType(c.BLOCK))
				return 422;			
			if (c.combatType(c.AGGRESSIVE))
				return 423;	
		}
		if(weaponName.contains("knife") || weaponName.contains("dart") || weaponName.contains("javelin") || weaponName.contains("thrownaxe")){
			return 806;
		}
		if(weaponName.contains("halberd")) {
			return 440;
		}
		if(weaponName.contains("ancient staff")) {
			return 419;
		}
		if(weaponName.startsWith("dragon dagger") || weaponName.startsWith("dragon dag")) {
			return 402;
		}	
		if(weaponName.endsWith("dagger")) {
			return 412;
		}		
		if(weaponName.contains("2h sword") || weaponName.contains("godsword") || weaponName.contains("aradomin sword")) {
			if (c.combatType(c.AGGRESSIVE) || c.combatType(c.ACCURATE))
				return 7041;			
			if (c.combatType(c.CONTROLLED))
				return 7048;	
			if (c.combatType(c.DEFENSIVE))
				return 7049;
		}		
		if(weaponName.contains("sword") && !weaponName.contains("korasi"))
			return 390;
		if(weaponName.contains("karil"))
			return 2075;
		if(weaponName.contains("bow") && !weaponName.contains("'bow"))
			return 426;
		if (weaponName.contains("'bow"))
			return 4230;
		if (weaponName.contains("battleaxe"))
			return 395;
		if(weaponName.contains("scimitar") || weaponName.contains("longsword") || weaponName.contains("korasi")) {
			switch(c.fightMode) {
				case 0:
				return 15071;	
				case 1:
				return 15071;		
				case 2:
				return 15071;	
				case 3:
				return 15072;		
			}
		}
		if(weaponName.contains("pickaxe")) {
				return 13035;		
		}
		if(weaponName.contains("rapier")) {
			switch(c.fightMode) {
				case 0:
				return 12028;	
				case 1:
				return 12028;		
				case 2:
				return 12028;	
				case 3:
				return 12028;
			}
		}
			
		switch(c.playerEquipment[c.playerWeapon]) { // if you don't want to use strings
			case 6522:
				return 2614;
			case 4153: // Granite maul
				return 1665;
			case 4726: // Guthans spear 
				return 2080;
			case 4747: // Torags hammers
				return 0x814;
			case 13905:
				return 13041;
			case 4718: // Dharok's greataxe
				if (c.combatType(c.AGGRESSIVE))
					return 2066;
				return 2067;
			case 4710: // Ahrim's staff
				return 406;
			case 14484:
				return 393;//claws
			case 11716:
				return 12006;//zamorakian spear
			case 4755: // Verac's flail
				return 2062;
			case 4734: // Karil's crossbow
				return 2075;
			case 13902: // Karil's crossbow
				return 13035;
			case 4151:
			case 15445:
			case 15444:
			case 15443:
			case 15442:
			case 15441:
				return 1658;
			case 15241:
				return 12152;
			case 6528: // Obby maul
			case 18353:
				return 2661;
			default:
				return 451;
		}
	}
	
	/**
	* Block emotes
	*/
	public int getBlockEmote() {
		String shield = c.getItems().getItemName(c.playerEquipment[c.playerShield]).toLowerCase();
		String weapon = c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase();
		if (shield.contains("defender"))
			return 4177;
		if (shield.contains("book") && (weapon.contains("wand")))
			return 420;
		if (shield.contains("shield"))
			return 1156;
		if (weapon.contains("rapier") || weapon.contains("scimitar") || weapon.contains("korasi"))
			return 15074;
		switch(c.playerEquipment[c.playerWeapon]) {
			case 4755:
			return 2063;
			case 15241:
			return 12156;
			case 4718:
			return 12004;
			case 13899:
			return 13042;
			case 18355:
			return 13046;
			case 14484:
			return 397;
			case 11716:
			return 12008;
			case 4153:
			return 1666;
			case 15486:
			return 12806;
			case 18349:
			return 12030;
			case 18353:
			return 13054;
			case 18351:
			return 13042;
			
			case 4151:
			case 15445:
			case 15444:
			case 15443:
			case 15442:
			case 15441:
			return 11974;
			
			case 11694:
			case 11698:
			case 11700:
			case 11696:
			case 11730:
			return 13051;
			default:
			return 404;
		}
	}
			
	/**
	* Weapon and magic attack speed!
	**/
	
	public int getAttackDelay(String s) {
		if(c.usingMagic) {
			switch(c.MAGIC_SPELLS[c.spellId][0]) {
				case 12871: // ice blitz
				case 13023: // shadow barrage
				case 12891: // ice barrage
				return 5;
				
				default:
				return 5;
			}
		}
		if(c.playerEquipment[c.playerWeapon] == -1)
			return 4;//unarmed
			
		switch (c.playerEquipment[c.playerWeapon]) {
			case 15241:
				return 10;
			case 11235:
			return 9;
			case 11730:
			return 4;
			case 6528:
			case 18353:
			return 7;
		}
		
		if(s.endsWith("greataxe"))
			return 7;
		else if(s.equals("torags hammers"))
			return 5;
		else if(s.equals("guthans warspear"))
			return 5;
		else if(s.equals("veracs flail"))
			return 5;
		else if(s.equals("ahrims staff"))
			return 6;
		else if(s.contains("staff")){
			if(s.contains("zamarok") || s.contains("guthix") || s.contains("saradomian") || s.contains("slayer") || s.contains("ancient"))
				return 4;
			else
				return 5;
		} else if(s.contains("bow")){
			if(s.contains("composite") || s.equals("seercull"))
				return 5;
			else if (s.contains("aril"))
				return 4;
			else if(s.contains("Ogre"))
				return 8;
			else if(s.contains("short") || s.contains("hunt") || s.contains("sword"))
				return 4;
			else if(s.contains("long") || s.contains("crystal"))
				return 6;
			else if(s.contains("'bow"))
				return 7;
			
			return 5;
		}
		else if(s.contains("dagger"))
			return 4;
		else if(s.contains("godsword") || s.contains("2h"))
			return 6;
		else if(s.contains("longsword"))
			return 5;
		else if(s.contains("sword"))
			return 4;
		else if(s.contains("scimitar"))
			return 4;
		else if(s.contains("rapier"))
			return 4;
		else if(s.contains("mace"))
			return 5;
		else if(s.contains("battleaxe"))
			return 6;
		else if(s.contains("pickaxe"))
			return 5;
		else if(s.contains("thrownaxe"))
			return 5;
		else if(s.contains("axe"))
			return 5;
		else if(s.contains("warhammer"))
			return 6;
		else if(s.contains("2h"))
			return 7;
		else if(s.contains("spear"))
			return 5;
		else if(s.contains("claw"))
			return 4;
		else if(s.contains("halberd"))
			return 7;
		
		//sara sword, 2400ms
		else if(s.equals("granite maul"))
			return 7;
		else if(s.equals("toktz-xil-ak"))//sword
			return 4;
		else if(s.equals("tzhaar-ket-em"))//mace
			return 5;
		else if(s.equals("tzhaar-ket-om"))//maul
			return 7;
		else if(s.equals("toktz-xil-ek"))//knife
			return 4;
		else if(s.equals("toktz-xil-ul"))//rings
			return 4;
		else if(s.equals("toktz-mej-tal"))//staff
			return 6;
		else if(s.contains("whip"))
			return 4;
		else if(s.contains("dart"))
			return 3;
		else if(s.contains("knife"))
			return 3;
		else if(s.contains("javelin"))
			return 6;
		return 5;
	}
	/**
	* How long it takes to hit your enemy
	**/
	public int getHitDelay(String weaponName) {
		if(c.usingMagic) {
			switch(c.MAGIC_SPELLS[c.spellId][0]) {			
				case 12891:
				return 4;
				case 12871:
				return 6;
				default:
				return 4;
			}
		} else {

			if(weaponName.contains("knife") || weaponName.contains("dart") || weaponName.contains("javelin") || weaponName.contains("thrownaxe")){
				return 3;
			}
			if(weaponName.contains("cross") || weaponName.contains("c'bow")) {
				return 4;
			}
			if(weaponName.contains("bow") && !c.dbowSpec) {
				return 4;
			} else if (c.dbowSpec) {
				return 4;
			}

			switch(c.playerEquipment[c.playerWeapon]) {	
				case 6522: // Toktz-xil-ul
				return 3;
				
				
				default:
				return 2;
			}
		}
	}
	
	public int getRequiredDistance() {
		if (c.followId > 0 && c.freezeTimer <= 0 && !c.isMoving)
			return 2;
		else if(c.followId > 0 && c.freezeTimer <= 0 && c.isMoving) {
			return 3;
		} else {
			return 1;
		}
	}
	
	public boolean usingHally() {
		switch(c.playerEquipment[c.playerWeapon]) {
			case 3190:
			case 3192:
			case 3194:
			case 3196:
			case 3198:
			case 3200:
			case 3202:
			case 3204:
			return true;
			
			default:
			return false;
		}
	}
	
	/**
	* Melee
	**/
	
	boolean meleeHitSuccess(int a, int d) {
		a = Misc.random(a);
		d = Misc.random(d);
		//c.sendMessage("Attack: " + a + " Defence: " + d);
		return a > d;
	}
	
	public int calculateMeleeAttack() {
		int attackLevel = c.playerLevel[0];
        if (c.prayerActive(2))
            attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.05;
        else if (c.prayerActive(7))
            attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.1;
        else if (c.prayerActive(15))
            attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.15;
        else if (c.prayerActive(24))
            attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.15;
        else if (c.prayerActive(25))
            attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.2;
		else if (c.curseActive(c.curses().TURMOIL))
			attackLevel = (int)(attackLevel * c.curses().getTurmoilMultiplier("Attack"));
        if (c.fullVoidMelee())
            attackLevel += c.getLevelForXP(c.playerXP[c.playerAttack]) * 0.1;
		attackLevel *= c.specAccuracy;
        int i = c.playerBonus[bestMeleeAtk()];
		i += c.bonusAttack;
		if (c.playerEquipment[c.playerAmulet] == 11128 && c.playerEquipment[c.playerWeapon] == 6528) {
			i *= 1.30;
		}
		return (int)(attackLevel + (attackLevel * 0.15) + (i + i * 0.05));
	}
	
	public int bestMeleeAtk() {
        if(c.playerBonus[0] > c.playerBonus[1] && c.playerBonus[0] > c.playerBonus[2])
            return 0;
        if(c.playerBonus[1] > c.playerBonus[0] && c.playerBonus[1] > c.playerBonus[2])
            return 1;
        return c.playerBonus[2] <= c.playerBonus[1] || c.playerBonus[2] <= c.playerBonus[0] ? 0 : 2;
    }
	
	public int calculateMeleeMaxHit() {
		double maxHit = 0;
		int strBonus = c.playerBonus[10];
		int strength = c.playerLevel[2];
		int lvlForXP = c.getLevelForXP(c.playerXP[2]);
		if(c.prayerActive(1))
			strength += (int)(lvlForXP * .05);
		else if(c.prayerActive(6))
			strength += (int)(lvlForXP * .10);
		else if(c.prayerActive(14))
			strength += (int)(lvlForXP * .15);
		else if(c.prayerActive(24))
			strength += (int)(lvlForXP * .18);
		else if(c.prayerActive(25))
			strength += (int)(lvlForXP * .23);
		else if (c.curseActive(c.curses().TURMOIL))
			strength = (int)(strength * c.curses().getTurmoilMultiplier("Strength"));
		if(c.playerEquipment[c.playerHat] == 2526 && c.playerEquipment[c.playerChest] == 2520 && c.playerEquipment[c.playerLegs] == 2522) {	
			maxHit += (maxHit * 10 / 100);
		}
		maxHit += 1.05D + (double)(strBonus * strength) * 0.00175D;
		maxHit += (double)strength * 0.11D;
		if(c.playerEquipment[c.playerWeapon] == 4718 && c.playerEquipment[c.playerHat] == 4716 && c.playerEquipment[c.playerChest] == 4720 && c.playerEquipment[c.playerLegs] == 4722) {	
				maxHit += (c.maxConstitution - c.constitution) / 20 ;//multiplied by 10 due to constitution			
		}
		if (c.specDamage > 1)
			maxHit = (int)(maxHit * c.specDamage);
		if (maxHit < 0)
			maxHit = 1;
		if (c.fullVoidMelee())
			maxHit = (int)(maxHit * 1.10);
		if (c.playerEquipment[c.playerAmulet] == 11128 && c.playerEquipment[c.playerWeapon] == 6528) {
			maxHit *= 1.20;
		}
		return (int)(Math.floor(maxHit) * 10);
	}
	

	public int calculateMeleeDefence() {
        int defenceLevel = c.playerLevel[1];
		int i = c.playerBonus[bestMeleeDef()];
        if (c.prayerActive(0))
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.05;
        else if (c.prayerActive(5))
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.1;
        else if (c.prayerActive(13))
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.15;
        else if (c.prayerActive(24))
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.2;
        else if (c.prayerActive(25))
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.25;
        else if (c.curseActive(c.curses().TURMOIL))
			defenceLevel = (int)(defenceLevel * c.curses().getTurmoilMultiplier("Defence"));
        return (int)(defenceLevel + (defenceLevel * 0.15) + (i + i * 0.05));
    }
	
	public int bestMeleeDef() {
        if(c.playerBonus[5] > c.playerBonus[6] && c.playerBonus[5] > c.playerBonus[7])
            return 5;
        if(c.playerBonus[6] > c.playerBonus[5] && c.playerBonus[6] > c.playerBonus[7])
            return 6;
        return c.playerBonus[7] <= c.playerBonus[5] || c.playerBonus[7] <= c.playerBonus[6] ? 5 : 7;
    }
	
	public double getMeleeAttack() {
		double level = c.playerLevel[0];
		double levelMultiplier = 1.00;
		if (c.prayerActive(2))
            levelMultiplier *= 1.05;
        else if (c.prayerActive(7))
            levelMultiplier *= 1.1;
        else if (c.prayerActive(15))
            levelMultiplier *= 1.15;
        else if (c.prayerActive(24))
            levelMultiplier *= 1.15;
        else if (c.prayerActive(25)) 
            levelMultiplier *= 1.20;
        if (c.fullVoidMelee())
            levelMultiplier *= 1.1;
		double effectiveAttack = (int) (level * levelMultiplier);
		double maxAttack = effectiveAttack * getMeleeAccuracyBonus();
		maxAttack *= c.specAccuracy;
		if (maxAttack < 1) {
			maxAttack = 1;
		}
		return maxAttack;
	}

	public int getMeleeAccuracyBonus() {
        if (c.combatType(c.ACCURATE))
            return c.playerBonus[0];
        else if (c.combatType(c.AGGRESSIVE))
            return c.playerBonus[1];
        else if (c.combatType(c.CONTROLLED))
            return c.playerBonus[2];
        return 0;
	}

	public double getMeleeDefence(Client o) {
        double level = c.playerLevel[1];
        double levelMultiplier = 1.00;
		if (c.prayerActive(2))
            levelMultiplier *= 1.05;
        else if (c.prayerActive(7))
            levelMultiplier *= 1.1;
        else if (c.prayerActive(15))
            levelMultiplier *= 1.15;
        else if (c.prayerActive(24))
            levelMultiplier *= 1.20;
        else if (c.prayerActive(25)) 
            levelMultiplier *= 1.25;
        double effectiveDefence = (int) (level * levelMultiplier);
        double maxDefence = effectiveDefence * getMeleeDefenceBonus(o);
        if (maxDefence < 1) {
            maxDefence = 1;
        }
        return maxDefence * 1.20;
    }
	
	public int getMeleeDefenceBonus(Client o) {
        if (c.combatType(c.ACCURATE))
            return o.playerBonus[5];
        else if (c.combatType(c.AGGRESSIVE))
            return o.playerBonus[6];
        else if (c.combatType(c.CONTROLLED))
            return o.playerBonus[7];
        return 0;
    }

	/**
	* Range
	**/
	
	public int calculateRangeAttack() {
		int attackLevel = c.playerLevel[4];
		attackLevel *= c.specAccuracy;
        if (c.fullVoidRange())
            attackLevel += c.getLevelForXP(c.playerXP[c.playerRanged]) * 0.1;
		if (c.prayerActive(3))
			attackLevel *= 1.05;
		else if (c.prayerActive(11))
			attackLevel *= 1.10;
		else if (c.prayerActive(19))
			attackLevel *= 1.15;
		//dbow spec
		if (c.fullVoidRange() && c.specAccuracy > 1.15) {
			attackLevel *= 1.75;		
		}
        return (int) (attackLevel + (c.playerBonus[4] * 1.95));
	}
	
	public int calculateRangeDefence() {
		int defenceLevel = c.playerLevel[1];
        if (c.prayerActive(0)) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.05;
        } else if (c.prayerActive(5)) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.1;
        } else if (c.prayerActive(13)) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.15;
        } else if (c.prayerActive(24)) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.2;
        } else if (c.prayerActive(25)) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.25;
        }
        return (int) (defenceLevel + c.playerBonus[9] + (c.playerBonus[9] / 2));
	}
	
	public boolean usingBolts() {
		return c.playerEquipment[c.playerArrows] >= 9130 && c.playerEquipment[c.playerArrows] <= 9145 || c.playerEquipment[c.playerArrows] >= 9230 && c.playerEquipment[c.playerArrows] <= 9245;
	}
	
	public int rangeMaxHit() {
		int rangeLevel = c.playerLevel[4];
		double modifier = 1.0;
		double wtf = c.specDamage;
		int itemUsed = c.usingBow ? c.lastArrowUsed : c.lastWeaponUsed;
		if (c.prayerActive(3))
			modifier += 0.05;
		else if (c.prayerActive(11))
			modifier += 0.10;
		else if (c.prayerActive(19))
			modifier += 0.15;
		if (c.fullVoidRange())
			modifier += 0.20;
		double c = modifier * rangeLevel;
		int rangeStr = getRangeStr(itemUsed);
		double max =(c + 8) * (rangeStr + 64) / 640;
		if (wtf != 1)
			max *= wtf;
		if (max < 1)
			max = 1;
		return (int)(max * 10);
	}
	
	public int getRangeStr(int i) {
		if (i == 4214)
			return 70;
		switch (i) {
			case 15241:
			case 15243:
				return 150;
			//bronze to rune bolts
			case 877:
			return 10;
			case 9140:
			return 46;
			case 9141:
			return 64;
			case 9142:
			case 9241:
			case 9240:
			return 82;
			case 9143:
			case 9243:
			case 9242:
			return 100;
			case 9144:
			case 9244:
			case 9245:
			return 115;
			//bronze to dragon arrows
			case 882:
			return 7;
			case 884:
			return 10;
			case 886:
			return 16;
			case 888:
			return 22;
			case 890:
			return 31;
			case 892:
			case 4740:
			return 49;
			case 11212:
			return 60;
			//knifes
			case 864:
			return 3;
			case 863:
			return 4;
			case 865:
			return 7;
			case 866:
			return 10;
			case 867:
			return 14;
			case 868:
			return 24;
		}
		return 0;
	}
	
	
	
	public boolean properBolts() {
		return c.playerEquipment[c.playerArrows] >= 9140 && c.playerEquipment[c.playerArrows] <= 9144
				|| c.playerEquipment[c.playerArrows] >= 9240 && c.playerEquipment[c.playerArrows] <= 9244;
	}
	
	public int correctBowAndArrows() {
		if (usingBolts())
			return -1;
		switch(c.playerEquipment[c.playerWeapon]) {
			
			case 839:
			case 841:
			return 882;
			
			case 843:
			case 845:
			return 884;
			
			case 847:
			case 849:
			return 886;
			
			case 851:
			case 853:
			return 890;        
			
			case 855:
			case 857:
			return 890;
			
			case 859:
			case 861:
			return 892;
			
			case 4734:
			case 4935:
			case 4936:
			case 4937:
			return 4740;
			
			case 15241:
				return 15243;
			
			case 11235:
			return 11212;
		}
		return -1;
	}
	
	public int getRangeStartGFX() {
		switch(c.rangeItemUsed) {
			            
			case 863:
			return 220;
			case 864:
			return 219;
			case 865:
			return 221;
			case 866: // knives
			return 223;
			case 867:
			return 224;
			case 868:
			return 225;
			case 869:
			return 222;
			
			case 806:
			return 232;
			case 807:
			return 233;
			case 808:
			return 234;
			case 809: // darts
			return 235;
			case 810:
			return 236;
			case 811:
			return 237;
			
			case 825:
			return 206;
			case 826:
			return 207;
			case 827: // javelin
			return 208;
			case 828:
			return 209;
			case 829:
			return 210;
			case 830:
			return 211;

			case 800:
			return 42;
			case 801:
			return 43;
			case 802:
			return 44; // axes
			case 803:
			return 45;
			case 804:
			return 46;
			case 805:
			return 48;
								
			case 882:
			return 19;
			
			case 884:
			return 18;
			
			case 886:
			return 20;

			case 888:
			return 21;
			
			case 890:
			return 22;
			
			case 892:
			return 24;
			
			case 11212:
			return 26;
			
			case 4212:
			case 4214:
			case 4215:
			case 4216:
			case 4217:
			case 4218:
			case 4219:
			case 4220:
			case 4221:
			case 4222:
			case 4223:
			return 250;
			
		}
		return -1;
	}
		
	public int getRangeProjectileGFX() {
		if (c.dbowSpec) {
			return 1099;
		}
		if(c.bowSpecShot > 0) {
			switch(c.rangeItemUsed) {
				default:
				return 249;
			}
		}
		if (c.playerEquipment[c.playerWeapon] == 9185 || c.playerEquipment[c.playerWeapon] == 18357)
			return 27;
		switch(c.rangeItemUsed) {
			case 15243:
				return 2143;
			case 863:
			return 213;
			case 864:
			return 212;
			case 865:
			return 214;
			case 866: // knives
			return 216;
			case 867:
			return 217;
			case 868:
			return 218;	
			case 869:
			return 215;  

			case 806:
			return 226;
			case 807:
			return 227;
			case 808:
			return 228;
			case 809: // darts
			return 229;
			case 810:
			return 230;
			case 811:
			return 231;	

			case 825:
			return 200;
			case 826:
			return 201;
			case 827: // javelin
			return 202;
			case 828:
			return 203;
			case 829:
			return 204;
			case 830:
			return 205;	
			
			case 6522: // Toktz-xil-ul
			return 442;

			case 800:
			return 36;
			case 801:
			return 35;
			case 802:
			return 37; // axes
			case 803:
			return 38;
			case 804:
			return 39;
			case 805:
			return 40;

			case 882:
			return 10;
			
			case 884:
			return 9;
			
			case 886:
			return 11;

			case 888:
			return 12;
			
			case 890:
			return 13;
			
			case 892:
			return 15;
			
			case 11212:
			return 17;
			
			case 4740: // bolt rack
			return 27;
			
			case 4212:
			case 4214:
			case 4215:
			case 4216:
			case 4217:
			case 4218:
			case 4219:
			case 4220:
			case 4221:
			case 4222:
			case 4223:
			return 249;
			
			
		}
		return -1;
	}
	
	public int getProjectileSpeed() {
		if (c.dbowSpec)
			return 100;
		if (c.playerEquipment[c.playerWeapon] == 15241)
			return 50;
		return 70;
	}
	
	public int getProjectileShowDelay() {
		switch(c.playerEquipment[c.playerWeapon]) {
			case 863:
			case 864:
			case 865:
			case 866: // knives
			case 867:
			case 868:
			case 869:
			
			case 806:
			case 807:
			case 808:
			case 809: // darts
			case 810:
			case 811:
			
			case 825:
			case 826:
			case 827: // javelin
			case 828:
			case 829:
			case 830:
			
			case 800:
			case 801:
			case 802:
			case 803: // axes
			case 804:
			case 805:
			
			case 4734:
            case 9185:
			case 18357:
			case 4935:
			case 4936:
			case 4937:
			return 15; 
			
		
			default:
			return 0;
		}
	}
	
	/**
	*MAGIC
	**/
	
	public boolean canHitMage(Client o) {
		double hitSucceed = DEFENCE_MODIFIER * (mageAtkTest() / o.getCombat().mageDefTest());
		if(hitSucceed > 1.0) 
			hitSucceed = 1;
		return hitSucceed < random.nextDouble();
	}
	
	public double mageDefTest() {
        double defenceBonus = c.playerBonus[8] == 0 ? 1 : c.playerBonus[8];	
		if(defenceBonus < 1)
			defenceBonus = 1;
		double defenceCalc = defenceBonus * c.playerLevel[1];
        if (c.prayerActive(0)) 
            defenceCalc *= 1.05;
        else if (c.prayerActive(3)) 
            defenceCalc *= 1.10;
        else if (c.prayerActive(9))
            defenceCalc *= 1.15;
        else if (c.prayerActive(18))
            defenceCalc *= 1.20;
        else if (c.prayerActive(19))
            defenceCalc *= 1.25;
        return defenceCalc;
    }
	
	public double mageAtkTest() {
        double attackBonus = c.playerBonus[3] == 0 ? 1 : c.playerBonus[3];
		if(attackBonus < 1) 
			attackBonus = 1;
		double attackCalc =  attackBonus * c.playerLevel[6]; // +1 as its exclusive
		if (c.fullVoidMage())
            attackCalc += 1.10;
        if (c.prayerActive(4))
			attackCalc *= 1.05;
		else if (c.prayerActive(12))
			attackCalc *= 1.10;
		else if (c.prayerActive(20))
			attackCalc *= 1.15;
        return attackCalc;
    }
	
	public int mageAtk() {
        int attackLevel = c.playerLevel[6];
		if (c.fullVoidMage())
            attackLevel += c.getLevelForXP(c.playerXP[6]) * 0.2;
        if (c.prayerActive(4))
			attackLevel *= 1.05;
		else if (c.prayerActive(12))
			attackLevel *= 1.10;
		else if (c.prayerActive(20))
			attackLevel *= 1.15;
        return (int) (attackLevel + (c.playerBonus[3] * 2));
    }
	
	public int mageDef() {
        int defenceLevel = c.playerLevel[1]/2 + c.playerLevel[6]/2;
        if (c.prayerActive(0)) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.05;
        } else if (c.prayerActive(3)) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.1;
        } else if (c.prayerActive(9)) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.15;
        } else if (c.prayerActive(18)) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.2;
        } else if (c.prayerActive(19)) {
            defenceLevel += c.getLevelForXP(c.playerXP[c.playerDefence]) * 0.25;
        }
        return (int) (defenceLevel + c.playerBonus[8] + (c.playerBonus[8] / 3));
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
		switch(c.MAGIC_SPELLS[c.oldSpellId][0]) {
			case 1572:
			case 12861: // ice rush
			return 10;
						
			case 1582:
			case 12881: // ice burst
			return 17;
			
			case 1592:
			case 12871: // ice blitz
			return 25;
			
			case 12891: // ice barrage
			return 33;
			
			default:
			return 0;
		}
	}
	
	public void freezePlayer(int i) {
	}

	public int getStartHeight() {
		switch(c.MAGIC_SPELLS[c.spellId][0]) {
			case 1562: // stun
			return 25;
			
			case 12939:// smoke rush
			return 35;
			
			case 12987: // shadow rush
			return 38;
			
			case 12861: // ice rush
			return 15;
			
			case 12951:  // smoke blitz
			return 38;
			
			case 12999: // shadow blitz
			return 25;
			
			case 12911: // blood blitz
			return 25;
			
			default:
			return 43;
		}
	}
	

	
	public int getEndHeight() {
		switch(c.MAGIC_SPELLS[c.spellId][0]) {
			case 1562: // stun
			return 10;
			
			case 12939: // smoke rush
			return 20;
			
			case 12987: // shadow rush
			return 28;
			
			case 12861: // ice rush
			return 10;
			
			case 12951:  // smoke blitz
			return 28;
			
			case 12999: // shadow blitz
			return 15;
			
			case 12911: return 10;// blood blitz
				
			default:
			return 31;
		}
	}
	
	public int getStartDelay() {
		switch (c.playerEquipment[c.playerWeapon]) {
			case 15241:
				return 0;
		}
		switch(c.MAGIC_SPELLS[c.spellId][0]) {
			case 1539:
			return 60;
			default:
			return 53;
		}
	}
	
	public int getStaffNeeded() {
		switch(c.MAGIC_SPELLS[c.spellId][0]) {
			case 1539:
			return 1409;
			
			case 12037:
			return 4170;
			
			case 1190:
			return 2415;
			
			case 1191:
			return 2416;
			
			case 1192:
			return 2417;
			
			default:
			return 0;
		}
	}
	
	public boolean godSpells() {
		switch(c.MAGIC_SPELLS[c.spellId][0]) {	
			case 1190:
			return true;
			
			case 1191:
			return true;
			
			case 1192:
			return true;
			
			default:
			return false;
		}
	}
		
	public int getEndGfxHeight() {
		switch(c.MAGIC_SPELLS[c.oldSpellId][0]) {
			case 12987:	
			case 12901:		
			case 12861:
			case 12445:
			case 1192:
			case 13011:
			case 12919:
			case 12881:
			case 12999:
			case 12911:
			case 12871:
			case 13023:
			case 12929:
			case 12891:
			return 0;
			
			default:
			return 100;
		}
	}
	
	public int getStartGfxHeight() {
		switch(c.MAGIC_SPELLS[c.spellId][0]) {
			case 12871:
			case 12891:
			return 0;
			
			default:
			return 100;
		}
	}
	
	public void handleDfs() {
	}
	
	public void handleDfsNPC() {
	}
	
	public void applyRecoil(int damage, int i) {
		if (damage > 0 && Server.playerHandler.players[i].playerEquipment[c.playerRing] == 2550) {
			int recDamage = damage/10 + 1;
			appendHit(c, recDamage, 0, 3, false, 0);
		}	
	}
	
	public int getBonusAttack(int i) {
		switch (Server.npcHandler.npcs[i].npcType) {
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
						int damage = 0;
						if (meleeHitSuccess(calculateMeleeAttack(), o.getCombat().calculateMeleeDefence()))
							damage = Misc.random(calculateMeleeMaxHit());
						if (o.prayerActive(18) && System.currentTimeMillis() - o.protMeleeDelay > 1500)
							damage *= .6;
						if (o.curseActive(o.curses().DEFLECT_MELEE) && System.currentTimeMillis() - o.protMeleeDelay > 1500)
							damage *= .4;
						c.startAnimation(1667);
						c.gfx100(337);
						int soak = o.getCombat().damageSoaked(damage, "Melee");
						damage -= soak;
						appendHit(o, damage, 0, 0, (calculateMeleeMaxHit() - 20) <= damage, soak);
					}	
				}	
			}			
		}	
	}
	
	public boolean armaNpc(int i) {
		switch (Server.npcHandler.npcs[i].npcType) {
			case 2558:
			case 2559:
			case 2560:
			case 2561:
			return true;	
		}
		return false;	
	}
	
	public int korasiDamage(Client o) {
		double hitMultiplier = random.nextDouble() + 0.5;
		int damage = (int)(calculateMeleeMaxHit() * hitMultiplier);
		if(damage > 750) {
			damage = 20;
		}
		if (o != null && o.curseActive(c.curses().DEFLECT_MAGIC) && System.currentTimeMillis() - o.protMageDelay > 1500) 
			damage = (int)damage * 40 / 100;
		if(o != null && o.prayerActive(16) && System.currentTimeMillis() - o.protMageDelay > 1500)
			damage = (int)damage * 60 / 100;
		return damage;
	}
	
	public double soakPercentage(String type) {
		double total = 0;
		if (type.equals("Melee")) {
			total = c.soakingBonus[0];
		} else if (type.equals("Ranged")) {
			total = c.soakingBonus[2];
		} else if (type.equals("Magic")) {
			total = c.soakingBonus[1];
		}
		return total;
	}
	
	public int damageSoaked(int damage, String type) {
		if (damage <= 200)
			return 0;
		return (int)((damage - 200) * soakPercentage(type));
	}
	
	public static int finalMagicDamage(Client c) {
		double damage = c.MAGIC_SPELLS[c.oldSpellId][6] * 10;
		double damageMultiplier = 1;
		int level = c.playerLevel[c.playerMagic];
		if (level > c.getLevelForXP(c.playerXP[6]) && c.getLevelForXP(c.playerXP[6]) >= 95)
			damageMultiplier += .03 * ((level > 104 ? 104 : level) - 99);
		else
			damageMultiplier = 1;
		switch (c.playerEquipment[c.playerWeapon]) {
		case 18371: // Gravite Staff
			damageMultiplier += .05;
			break;
		case 4675: // Ancient Staff
		case 4710: // Ahrim's Staff
		case 4862: // Ahrim's Staff
		case 4864: // Ahrim's Staff
		case 4865: // Ahrim's Staff
		case 6914: // Master Wand
		case 8841: // Void Knight Mace
		case 13867: // Zuriel's Staff
		case 13869: // Zuriel's Staff (Deg)
			damageMultiplier += .10;
			break;
		case 15486: // Staff of Light
			damageMultiplier += .15;
			break;
		case 18355: // Chaotic Staff
			damageMultiplier += .20;
			break;
		}
		switch (c.playerEquipment[c.playerAmulet]) {
		case 18333: // Arcane Pulse
			damageMultiplier += .05;
			break;
		case 18334:// Arcane Blast
			damageMultiplier += .10;
			break;
		case 18335:// Arcane Stream
			damageMultiplier += .15;
			break;
		}
		damage *= damageMultiplier;
		return (int)damage;
	}
	
}
