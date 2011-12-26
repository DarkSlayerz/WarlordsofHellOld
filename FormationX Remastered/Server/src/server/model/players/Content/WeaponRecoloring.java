package server.model.players.content;

import server.model.players.Client;

public class WeaponRecoloring {
	
	public static void ItemonItem(Client c, int itemUsed, int useWith) {
		//WHIPS IN DIFFERENT COLORS
		//GREEN
		if (itemUsed == 4151 && useWith == 1771 || itemUsed == 1771 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15444,1);	
		}
		//WHITE
		if (itemUsed == 4151 && useWith == 20 || itemUsed == 20 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15443,1);	
		}
		//BLUE
		if (itemUsed == 4151 && useWith == 1767 || itemUsed == 1767 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15442,1);	
		}
		//YELLOW
		if (itemUsed == 4151 && useWith == 1765 || itemUsed == 1765 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15441,1);	
		}
		//BACK TO NORMAL
		if (itemUsed == 3188 && useWith == 15441 || itemUsed == 15441 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 15442 || itemUsed == 15442 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 15443 || itemUsed == 15443 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 15444 || itemUsed == 15444 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		//DARK BOWS IN DIFFERENT COLORS
		//GREEN
		if (itemUsed == 11235 && useWith == 1771 || itemUsed == 1771 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15704,1);	
		}
		//WHITE
		if (itemUsed == 11235 && useWith == 20 || itemUsed == 20 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15703,1);	
		}
		//BLUE
		if (itemUsed == 11235 && useWith == 1767 || itemUsed == 1767 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15702,1);	
		}
		//YELLOW
		if (itemUsed == 11235 && useWith == 1765 || itemUsed == 1765 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15701,1);	
		}
		//BACK TO NORMAL
		if (itemUsed == 3188 && useWith == 15701 || itemUsed == 15701 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 3188 && useWith == 15702 || itemUsed == 15702 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 3188 && useWith == 15703 || itemUsed == 15703 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 3188 && useWith == 15704 || itemUsed == 15704 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
	
	}

}
