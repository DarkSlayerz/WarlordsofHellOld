package server.model.players;

/**
 * This enumeration encapsulates variational weapon
 * attributes which are used throughout all components
 * which deal with weapons.
 */
public enum Weapon {
	
	ABBYSAL_WHIP(4151, 1081);//(4151*weaponID*, 1081*soundID*)
	
	private final int id, sound;
	
	public int getId() {
		return id;
	}
	
	public int getSound() {
		return sound;
	}
	
	Weapon(int id, int sound) {
		this.id = id;
		this.sound = sound;
	}
	
	public static Weapon forId(int id) {
		for (Weapon w : values())
			if (w.getId() == id)
				return w;
		return null;
	}

}