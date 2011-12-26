import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.Enumeration;

import sign.signlink;

public class CacheDownloader {

        private client client;

        private final int BUFFER = 1024;

        public CacheDownloader(client client) {
                this.client = client;
        }
		
        public void downloadFile(String adress, String localFileName) {
			OutputStream out = null;
			URLConnection conn;
			InputStream in = null;
			try {
				URL url = new URL(adress);
				out = new BufferedOutputStream(new FileOutputStream(System.getProperty("java.io.tmpdir") + "/" +localFileName)); 
				conn = url.openConnection();
				in = conn.getInputStream(); 
                byte[] data = new byte[BUFFER]; 
                int numRead;
                long numWritten = 0;
                int length = conn.getContentLength();
                while((numRead = in.read(data)) != -1) {
					out.write(data, 0, numRead);
					numWritten += numRead;
					int percentage = (int)(((double)numWritten / (double)length) * 100D);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                try {
                    if (in != null) {
						in.close();
                    }
                    if (out != null) {
						out.close();
                    }
                } catch (IOException ioe) {
                }
            }
        }
}