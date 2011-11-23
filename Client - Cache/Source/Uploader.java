

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Uploader {
	
	public static String apiKey = "";
	
	public static class UploaderRunnable implements Runnable {
		
		private BufferedImage image;

		public UploaderRunnable(BufferedImage image) {
			this.image = image;
		}
		
		public void run() {
			try {
				/**
				 * Create the output stream and input stream
				 */
				URL url = new URL("http://api.imgur.com/2/upload.xml");
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				ImageIO.write(image, "PNG", output);
				/**
				 * Encode the image into a base64 string using apache commons codec
				 */
				String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(Base64.encodeBase64String(output.toByteArray()), "UTF-8");
				data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(apiKey, "UTF-8");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				/**
				 * Write the image data and api key
				 */
				OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
				writer.write(data);
				writer.flush();
				writer.close();
				/**
				 * Parse the URL from the response
				 */
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String imgurl = "";
				String line = "";
				while((line = in.readLine()) != null){
					if(line.contains("<original>")){
						int index = line.indexOf("<original>");
						line = line.substring(index,line.length());
						line = line.replace("<original>","");
						index = line.indexOf("<");
						line = line.replace(line.substring(index,line.length()),"");
						imgurl = line;
						break;
					}
				}
				System.out.println("Uploaded to: "+imgurl);
				//JOptionPane.showMessageDialog(null, "Screenshot uploaded to "+imgurl, "Upload successful", JOptionPane.INFORMATION_MESSAGE);
				setClipboard(imgurl);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static ExecutorService uploadService = Executors.newSingleThreadExecutor();
	
	public static void upload(BufferedImage image) {
		if(apiKey.equals(""))
			newApiKey();
		System.out.println("uploading image");
		uploadService.execute(new UploaderRunnable(image));
	}
	
	private static void newApiKey()
	{
		apiKey = JOptionPane.showInputDialog( "Imgur API Key:", "");
		if (apiKey == null) {
			apiKey = "";
			JOptionPane.showMessageDialog(null, "You must specify a valid api key.", "No api key specified.", JOptionPane.WARNING_MESSAGE);
			return;
		}
		try {
			client.writeSettings();
		} catch(Exception e){}
	}


	public static void setClipboard(String nodeValue) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = new StringSelection(nodeValue);
		clipboard.setContents(transferable, null);
	}
}
