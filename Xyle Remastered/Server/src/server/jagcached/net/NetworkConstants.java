package server.jagcached.net;

/**
 * A class which holds network-related constants.
 * @author Graham
 */
public final class NetworkConstants {
	
	/**
	 * The HTTP port.
	 */
	public static final int HTTP_PORT = 80;
	
	/**
	 * The JAGGRAB port.
	 */
	public static final int JAGGRAB_PORT = 5556;
	
	/**
	 * The service port (which is also used for the 'on-demand' protocol).
	 */
	public static final int SERVICE_PORT = 43596;

	/**
	 * The number of seconds a channel can be idle before being closed
	 * automatically.
	 */
	public static final int IDLE_TIME = 15;
	
	/**
	 * Default private constructor to prevent instantiaton.
	 */
	private NetworkConstants() {
		
	}

}
