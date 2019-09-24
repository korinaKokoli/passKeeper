package passKeeper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	//test tool in other branch kjKLAJSLKJS korina axa
	//test tool in other branch kjKLAJSLKJS korina kok
	private static byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's',
		't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };
	public static void main(String[] args) throws Exception {
		try {

			String content = "This is the content to write into file";

			content = AES_encryption.encrypt(keyValue, content);
			File file = new File("resources/pass.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
