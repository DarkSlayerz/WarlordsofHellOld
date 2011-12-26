import sign.signlink;
import java.net.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PrintStream;
import java.net.MalformedURLException;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;


public class Jframe extends client implements ActionListener {

    private static JMenuItem menuItem;
	public static JFrame frame;
	public static JPanel gamePanel;

	public Jframe(String args[]) {
		super();
		try {
			sign.signlink.startpriv(InetAddress.getByName(server));
			initUI();
		} catch (Exception ex) {
		}
	}
	public static void setCursor(int id) {
Toolkit toolkit = Toolkit.getDefaultToolkit();
String location = signlink.findcachedir() + "/Sprites/";
Cursor cursor = toolkit.createCustomCursor(toolkit.getImage(location+"CURSOR "+id+".PNG"), new Point(0,0), location+"CURSOR "+id+".PNG");
frame.setCursor(cursor);
}
	public static TrayIcon trayIcon;
	
	public void setTray() {
		if (SystemTray.isSupported()) {
			Image icon = Toolkit.getDefaultToolkit().getImage("./Sprites/icon.png");
			trayIcon = new TrayIcon(icon, "Client is running.");
			trayIcon.setImageAutoSize(true);
			try {
				SystemTray tray = SystemTray.getSystemTray();
				tray.add(trayIcon);
				trayIcon.displayMessage("Client", "Initiated client.", TrayIcon.MessageType.INFO);
				PopupMenu menu = new PopupMenu();
				final MenuItem minimizeItem = new MenuItem("Minimize to Tray");
				MenuItem exitItem = new MenuItem("Exit");
				menu.add(minimizeItem);
				menu.add(exitItem);
				trayIcon.setPopupMenu(menu);
				ActionListener minimizeListener = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(frame.isVisible()) {
							frame.setVisible(false);
							minimizeItem.setLabel("Restore.");						
						} else {
							frame.setVisible(true);				
							minimizeItem.setLabel("Minimize to tray.");
						}
					}
				};
				minimizeItem.addActionListener(minimizeListener);
				ActionListener exitListener = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				};
				exitItem.addActionListener(exitListener);
			} catch (AWTException e) {
				System.err.println(e);
			}
		}		
	}

	public void initUI() {
		try {
		setTray();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JPopupMenu.setDefaultLightWeightPopupEnabled(false);
			frame = new JFrame("Warlords of Hell 317");
			frame.setLayout(new BorderLayout());
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel gamePanel = new JPanel();

			gamePanel.setLayout(new BorderLayout());
			gamePanel.add(this);
			gamePanel.setPreferredSize(new Dimension(765, 503));
			
			frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
			frame.pack();

			frame.setVisible(true); // can see the client
			frame.setResizable(false); // resizeable frame

			init();
		} catch (Exception e) {
				e.printStackTrace();
		}
	}

	public URL getCodeBase() {
		try {
			return new URL("http://" + server + "/cache");
		} catch (Exception e) {
			return super.getCodeBase();
		}
	}

	public URL getDocumentBase() {
		return getCodeBase();
	}

	public void loadError(String s) {
		System.out.println("loadError: " + s);
	}

	public String getParameter(String key) {
			return "";
	}

	private static void openUpWebSite(String url) {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(url)); 	
		} catch (Exception e) {
		}
	}

	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		try {
			if (cmd != null) {
				if (cmd.equalsIgnoreCase("exit")) {
					System.exit(0);
				}
				if (cmd.equalsIgnoreCase("Project-Insanity.net")) {
					openUpWebSite("http://www.project-insanity.net/");
				}	
			}
		} catch (Exception e) {
		}
	}
}