package server.model.players.content;

import server.model.players.Client;

public class OrnamentsKit {
	
	public static void ItemonItem(Client c, int itemUsed, int useWith) {
		if (itemUsed == 19333 && useWith == 6585 || itemUsed == 6585 && useWith == 19333) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(19335,1);
		}
		if (itemUsed == 19346 && useWith == 11335 || itemUsed == 11335 && useWith == 19346) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(19336,1);
		}	
		if (itemUsed == 19350 && useWith == 14479 || itemUsed == 14479 && useWith == 19350) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(19337,1);
		}
		if (itemUsed == 19348 && useWith == 4087 || itemUsed == 4087 && useWith == 19348) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(19338,1);
		}
		if (itemUsed == 19348 && useWith == 4585 || itemUsed == 4585 && useWith == 19348) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(19339,1);
		}
		if (itemUsed == 19352 && useWith == 1187 || itemUsed == 1187 && useWith == 19352) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(19340,1);
		}
		if (itemUsed == 19354 && useWith == 11335 || itemUsed == 11335 && useWith == 19354) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(19341,1);
		}
		if (itemUsed == 19358 && useWith == 14479 || itemUsed == 14479 && useWith == 19358) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(19342,1);
		}
		if (itemUsed == 19356 && useWith == 4087 || itemUsed == 4087 && useWith == 19356) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(19343,1);
		}
		if (itemUsed == 19356 && useWith == 4585 || itemUsed == 4585 && useWith == 19356) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(19344,1);
		}
		if (itemUsed == 19360 && useWith == 1187 || itemUsed == 1187 && useWith == 19360) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(19345,1);
		}
	
	}

}
