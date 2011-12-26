package server.model.players.content;

import server.model.players.Client;

public class ExchangeSets {

	public Client c;
	public ExchangeSets(Client Client) {
		this.c = Client;
	}

	 public void ExchangeSets(Client c) {
	 
	 //Rune
	 //Rune Armour Set (Skirt)
	 if (c.getItems().playerHasItem(1, 1)) {
		 //c.getItems().deleteItem(RuneArmourSetSK,1);
        // c.getItems().addItem(ExchangedRuneArmourSetSK,1);
        // c.sendMessage("You Exchange your Rune Armour Set (SK) For the real items");
	 }
	 //Rune Armour Set (Legs)
	 if (c.getItems().playerHasItem(1, 1)) {
		 c.getItems().deleteItem(RuneArmourSetLG);
         c.getItems().deleteItem(13736,1);
         c.getItems().addItem(13738,1);
         c.sendMessage("You Exchange your Rune Armour Set (LG) For the real items");
	 }
}
 	
   public boolean RuneArmourSetSK(Client c) {
       return c.getItems().playerHasItem(11840,1);
    }
	public boolean ExchangedRuneArmourSetSK(Client c) { //Rune Full Helm, Rune Platebody, Rune Plateskirt, Rune Kiteshield
        return c.getItems().addItem(1163,1) && c.getItems().addItem(1127,1) && c.getItems().playerHasItem(1093,1) && c.getItems().playerHasItem(1201,1);
    }
	public static int RuneArmourSetLG(Client c) {
	    c.getItems().playerHasItem(11838,1);
	}
	public static boolean ExchangedRuneArmourSetLG(Client c) {
		return c.getItems().playerHasItem(11838,1);
	}
	
	}

