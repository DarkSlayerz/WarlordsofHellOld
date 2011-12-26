package server.model.minigames.TreasureTrails;

import server.Config;
import server.util.*;
import server.model.players.*;
import server.model.players.Content.*;
import java.util.*;
 
public class Clues {
 /**
 * Alot of these methods were released in older 
 * servers or by others, I don't claim anything in this file.
 **/
 public Clues(Client c) {
  this.c = c; 
 }
 
 private final Client c;
 public int xStage1 = 3113, yStage1 = 3493, xStage2 = 1, yStage2 = 1; 
 public boolean hasClue() {
  return c.getItems().playerHasItem(2690, 1);
 }
 
 public void scrollFrame(int level, String text1, String text2, String text3, String text4, String text5, String text6, String text7) {
  c.getPA().sendFrame126("Close Window", 6967);
  c.getPA().sendFrame126("Level "+level+"", 6968);
  c.getPA().sendFrame126(text1, 6969);
  c.getPA().sendFrame126(text2, 6970);
  c.getPA().sendFrame126(text3, 6971);
  c.getPA().sendFrame126(text4, 6972);
  c.getPA().sendFrame126(text5, 6973);
  c.getPA().sendFrame126(text6, 6974);
  c.getPA().sendFrame126(text7, 6975);
  c.getPA().showInterface(6965);
 }
 
 public void HandleClueMessages(int itemId) {
  c.levelOne().level1ClueMsg(itemId);
 }
 
 public void SpeakToNPC(int npcType) {
  if(c.clueStage == 0) {
   c.getDH().sendDialogues(57, npcType);
   c.clueStage++;
  }
 }
 
 public void speakToAnagram(int npcType) {
  if(c.levelOne().anagram == 0) {
   if (c.clueStage == 0) {
    SpeakToNPC(npcType);
   }
  } else
  if(c.levelOne().anagram != 0) {
   c.getDH().sendDialogues(58, npcType);
  }
 }
 
 public void Anagrams(int npcType) {
  switch(npcType) {
   case 1294://NPC
    speakToAnagram(1294);
   break;
  }
 }
 
 public void digForClue() {
  if (hasClue() && c.absX == xStage1 && c.absY == yStage1 && c.clueStage == 1) {
   c.getDH().sendDialogues(56, 4289);
   c.clueStage++;
  } else if (hasClue() && c.absX == xStage2 && c.absY == yStage2 && c.clueStage == 2) {
   c.getDH().sendDialogues(55, 4289);
   c.getItems().deleteItem(2690, c.getItems().getItemSlot(2690), 1);
   c.getItems().addItem(3585, 1);
   c.clueStage = 3;
  }
 }
 
 public void clickCasket(int itemId) {
  switch(itemId) {
   case 3585: //Casket ID
    if(c.clueStage == 3) {
     c.levelOne().Casket(3585);
    }
   break;
  }
 }
 
}