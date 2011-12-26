package server.model.minigames.TreasureTrails;

import server.Config;
import server.util.*;
import server.model.players.*;
import server.model.players.Content.*;
import java.util.*;
 
public class LevelOneClue {
 
 public LevelOneClue(Client c) {
  this.c = c; 
 }
 
 private final Client c;
 
 private final String[] randomizeTheAnagram = {
  /**   "ANAGRAM",
  *   //NPC Name
  */
 
  "A BAS",
  //Saba
  "A ZEN SHE",
  //Zenesha
  "ACE MATCH ELM",
  //Cam the Camel
  "AHA JAR",
  //Jaraah
  "AN PAINT TONIC",
  //Captain Ninto
  "ARC O LINE",
  //Caroline
  "ARE COL",
  //Oracle
  "ARR! SO I AM A CRUST, AND?",
  //Ramara du Croissant
  "BAIL TRIMS",
  //Brimstail
  "BY LOOK",
  //Bolkoy
  "C ON GAME HOC",
  //Gnome Coach
  "DT RUN B",
  //Brundt
  "EEK ZERO OP",
  //Zookeeper
  "EL OW",
  //Lowe
  "ERR CURE IT",
  //Recruiter
  "GOBLIN KERN",
  //King Bolren
  "GOT A BOY",
  //Gabooty
  "GULAG RUN",
  //Uglug Nar
  "HALT US",
  //Luthas
  "HE DO POSE. IT IS CULTRRL, MK?",
  //Riki the sculptor's model
  "ICY FE",
  //Fycie
  "I EAT ITS CHART HINTS DO U",
  //Shiratti the Custodian
  "I FAFFY RUN",
  //Fairy Nuff
  "LAND DOOMD",
  //Odd Old Man 
  "LARK IN DOG",
  //King Roald
  "ME AM THE CALC",
  //Cam the Camel
  "ME IF",
  //Femi
  "NOD MED",
  //Edmond
  "O BIRDZ A ZANY EN PC",
  //Cap'n Izzy No Beard
  "OK CO",
  //Cook
  "OR ZINC FUMES WARD",
  //Wizard Frumscone
  "PROFS LOSE WRONG PIE",
  //Professor Onglewip
  "PEATY PERT",
  //Party Pete
  "R AK MI",
  //Karim
  "RED ART TANS",
  //Trader Stan
  "SEQUIN DIRGE",
  //Queen Sigrid
  "SNAH",
  //Hans
  "THEM CALL CAME",
  //Cam the Camel
 };
 
 public final int anagramRandom = Misc.random(37);
 public int anagram;
 /*
 public String Anagram() {
  switch(c.clueStage) {
   case 0:
    if(anagram == 0)
     return randomizeTheAnagram[0];
    else if(anagram == 1)
     return randomizeTheAnagram[1];
    else if(anagram == 2)
     return randomizeTheAnagram[2];
   break;
  }
  return randomizeTheAnagram[0];
 }*/
 
 public String Anagram() {
  switch(c.clueStage) {
   case 0:
    for(int i = 0; i <= 37; i++)
     if(anagram == i)
      return randomizeTheAnagram[i];
     break;
  }
  return randomizeTheAnagram[0];
 }//I'm sure this should work, but I can't test
 
 public void level1ClueMsg(int itemId) {
  String line1 = "This", line2 = "is", line3 = "how many", line4 = "lines there", line5 = "are.", line6 = "Hint: ??", line7 = "unused-";
  String line8 = "This", line9 = "is", line10 = "how many", line11 = "lines there", line12 = "are.", line13 = "Hint: ??", line14 = "unused-";
  switch(itemId) {
   case 2690:
    if(c.clueStage == 0) {
     anagram = anagramRandom;
     c.getClue().scrollFrame(1, "", "This anagram reveals", "who to speak to next:", Anagram(), "", "", "");//part 1
    } else 
    if(c.clueStage == 1) {
     c.getClue().scrollFrame(1, line1, line2, line3, line4, line5, line6, line7);//part 2
    } else 
    if(c.clueStage == 2) {
     c.getClue().scrollFrame(1, line8, line9, line10, line11, line12, line13, line14);//part 3
    }
   break;
  }
 }
 
 public void Casket(int casket) {//3585
  int[] stackedAmount = {
   Misc.random(115)
  };
  int[] stackedItems = {
   554, 555, 8779, 556, 557, 558,
   559, 560, 330, 561, 562, 563, 334, 
   564, 995, 565
  };
  int[] commonRewards = {
   1729, 1725, 1704, 1107, 1694, 1698, 1700,
   1089, 1731, 1125, 1151, 1165, 1179, 1195,
   1696,1367, 1217, 1283, 1297, 1313, 853,
   851, 1077, 1361, 1065, 1099, 1135, 1702, 1727
  };
  int[] moneyAmount = {
   Misc.random(55102)
  };
  int[] moneyStack = {
   995//coins
  };
  int common = (int) (Math.random() * commonRewards.length);
  int common2 = (int) (Math.random() * commonRewards.length);
  int stacked = (int) (Math.random() * stackedItems.length);
  int amount = (int) (Math.random() * stackedAmount.length);
  int stack = (int) (Math.random() * moneyStack.length);
  int am = (int) (Math.random() * moneyAmount.length);
  if(c.getItems().freeSlots() > 2) {
   c.getItems().resetKeepItems();
   c.sendMessage("Well done, you have completed an easy Treasure Trail!");
   c.getItems().deleteItem(casket, c.getItems().getItemSlot(casket), 1);
   c.getPA().showInterface(6960);
    c.getPA().Frame34(6963, commonRewards[common], 0, 1);
    c.getItems().addItem(commonRewards[common], 1);
    c.getPA().Frame34(6963, commonRewards[common2], 1, 1);
    c.getItems().addItem(commonRewards[common2], 1);
    if(stackedItems[stacked] == 995) {
     c.getPA().Frame34(6963, moneyStack[stack], 2, moneyAmount[am]);
     c.getItems().addItem(moneyStack[stack], moneyAmount[am]);
    } else {
     c.getPA().Frame34(6963, stackedItems[stacked], 2, stackedAmount[amount]);
     c.getItems().addItem(stackedItems[stacked], stackedAmount[amount]);
    }
   highValueClue1();
   c.clueStage = 0;
  } else {
   c.sendMessage("You don't have enough inventory space!");
   return;
  }
 }
 
 public void highValueClue1() {
  int[] universalRewards = {
   3827, 3828, 3829, 3830,//sara pages
   3831, 3832, 3833, 3834,//zammy pages
   3835, 3836, 3837, 3838,//guthix pages
   7329, 7330, 7331, 10326, 10327, //firelighters
   10476,//purple sweets
   ///554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565,//Runes
   1727, 1729, 1725, 1731, 1704, //Enchanted ammulets
   1694, 1696, 1698, 1700, 1702, //Unenchanted
   10280, 10282, 10284 //Composite bows
  };
  int[] level1Clue = {
   2583, 2585, 2587, 2589, 3472, //black (t)
   2591, 2593, 2595, 2597, 3473, //black (g)
   7392, 7396, //wizzy (t)
   7390, 7394, //wizzy (g)
   2631, //highwayman Mask
   2633, 2635, 2637, //berets
   7362, 7364, 7366, 7368, //studded (t) (g)
   7332, 10306, //heraldic h1
   7338, 10308, //heraldic h2
   7344, 10314, //heraldic h3
   7350, 10320, //heraldic h4
   7356, 10326, //heraldic h5
   10400, 10402, 10420, 10421, //black ele
   10404, 10406, 10424, 10426, //red ele
   10412, 10414, 10432, 10434, //green ele
   10408, 10410, 10428, 10430, //blue ele
   10416, 10418, 10436, 10438, //purple ele
   10316, 10318, 10320, 10322, 10324, // bob shirts
   10394, 10398, 10392, 10396, //emote enhancers
   10452, 10446, 10458, 10464, 10470, //saradomin robes
   10450, 10456, 10460, 10468, 10474, //zammy robes
   10448, 10454, 10462, 10466, 10472, //guthix robes
  };
  int misc = Misc.random(100);
  int universal = (int) (Math.random() * universalRewards.length);
  int level1 = (int) (Math.random() * level1Clue.length);
  Random random = new Random();
  boolean gotGoodReward = random.nextBoolean();
  ///System.out.println("Randomized clue reward: "+misc);
  if(gotGoodReward) {
   if(misc == 50) {
    c.getPA().Frame34(6963, level1Clue[level1], 3, 1);
    c.getItems().addItem(level1Clue[level1], 1);
   } else if(misc != 1) {
    c.getPA().Frame34(6963, universalRewards[universal], 3, 1);
    c.getItems().addItem(universalRewards[universal], 1);
   }
  } else {
   c.getPA().Frame34(6963, -1, 3, -1);///resets the frame
  }
 }
}