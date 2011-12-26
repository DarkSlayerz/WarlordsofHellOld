package server.model.objects;

import server.Server;
import server.model.players.Client;
import server.event.*;
import server.event.EventManager;
import server.event.EventContainer;
import server.event.Event;

public class Ladders {

	private static Client c;

	public static void ladder(int x, int y) {
		c.resetWalkingQueue();
		c.teleportToX = x;
        c.teleportToY = y;
		c.getPA().requestUpdates();
	}	
	
	public static void useLadder(final String climbType) {
		if (System.currentTimeMillis() - c.lastClick > 1500) {
			c.lastClick = System.currentTimeMillis();
			c.startAnimation(828);
		EventManager.getSingleton().addEvent(new Event() {
			@Override
			public void execute(EventContainer s) {
				if (climbType.equalsIgnoreCase("down")) {
					ladder(c.getX(), c.getY());
					c.heightLevel -= 1;
				}
				if (climbType.equalsIgnoreCase("up")) {
					ladder(c.getX(), c.getY());
					c.heightLevel += 1;					
				}
			s.stop();
				}
			},1500);
		}
	}	
	
}