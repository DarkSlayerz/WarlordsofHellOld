package server.model.players.content;

import server.model.players.Client;

public class SpiritShields {
	
	 public static void makeBlessed(Client c) {
		 c.getItems().deleteItem(13754,1);
		 c.getItems().deleteItem(13734,1);
		 c.getItems().addItem(13736,1);
		  c.sendMessage("You bless the shield with the magical powers of the elixir.");
 	 }
	 public static void makeArcane(Client c) {
		 c.getItems().deleteItem(13746, c.getItems().getItemSlot(13746), 1);
		 c.getItems().deleteItem(13736, c.getItems().getItemSlot(13736), 1);
         c.getItems().addItem(13738,1);
         c.sendMessage("You attached the sigil to the blessed spirit shield, and made a Arcane spirit shield.");
	 }
	 public static void makeDivine(Client c) {
		 c.getItems().deleteItem(13748, c.getItems().getItemSlot(13748), 1);
		 c.getItems().deleteItem(13736, c.getItems().getItemSlot(13736), 1);
		 c.getItems().addItem(13740,1);
		 c.sendMessage("You attached the sigil to the blessed spirit shield, and made a Divine spirit shield.");
 	 }
	 public static void makeElysian(Client c) {
		 c.getItems().deleteItem(13750, c.getItems().getItemSlot(13750), 1);
		 c.getItems().deleteItem(13736, c.getItems().getItemSlot(13736), 1);
		 c.getItems().addItem(13742,1);
		 c.sendMessage("You attached the sigil to the blessed spirit shield, and made a Elysian spirit shield.");
 	}
 	public static void makeSpectral(Client c) {
		 c.getItems().deleteItem(13752, c.getItems().getItemSlot(13752), 1);
		 c.getItems().deleteItem(13736, c.getItems().getItemSlot(13736), 1);
		 c.getItems().addItem(13744,1);
         c.sendMessage("You attached the sigil to the blessed spirit shield, and made a Spectral spirit shield.");
 	}
 	
    public static boolean hasArcane(Client c) {
        return c.getItems().playerHasItem(13746,1) && c.getItems().playerHasItem(13736,1);
    }
	public static boolean hasBlessed(Client c) {
		return c.getItems().playerHasItem(13754,1) && c.getItems().playerHasItem(13734,1);
    }
	public static boolean hasDivine(Client c) {
	        return c.getItems().playerHasItem(13748,1) && c.getItems().playerHasItem(13736,1);
	}
	public static boolean hasElysian(Client c) {
	        return c.getItems().playerHasItem(13750,1) && c.getItems().playerHasItem(13736,1);
	}
	public static boolean hasSpectral(Client c) {
	        return c.getItems().playerHasItem(13752,1) && c.getItems().playerHasItem(13736,1);
	}
	
	public static void ItemonItem(Client c, int itemUsed, int useWith) {
        if (itemUsed > 13745 && itemUsed < 13747 && useWith > 13735 && useWith < 13737) {
            if (hasArcane(c)) {
                    makeArcane(c);
            }
        }
		if (itemUsed > 13754 && itemUsed < 13734 && useWith > 13734 && useWith < 13754) {
            if (hasBlessed(c)) {
                    makeBlessed(c);
            }
        }
	    if (itemUsed > 13747 && itemUsed < 13749 && useWith > 13735 && useWith < 13737) {
	            if (hasDivine(c)) {
	                    makeDivine(c);
	            }
	    }
	    if (itemUsed > 13749 && itemUsed < 13751 && useWith > 13735 && useWith < 13737) {
	            if (hasElysian(c)) {
	                    makeElysian(c);
	            }
	    }
	    if (itemUsed > 13751 && itemUsed < 13753 && useWith > 13735 && useWith < 13737) {
	            if (hasSpectral(c)) {
	                    makeSpectral(c);
	            }
		}
	}

}
