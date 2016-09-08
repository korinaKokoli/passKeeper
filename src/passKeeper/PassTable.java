package passKeeper;

import javax.swing.JFrame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JEditorPane;

public class PassTable {
	private JFrame f = new JFrame("Second");
	private static byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's',
		't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public PassTable() throws Exception {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300, 300);
		f.getContentPane().setLayout(null);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(22, 28, 210, 189);
		f.getContentPane().add(editorPane);
		f.setVisible(true);

		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"resources/pass.txt"));
			String line;
			String text = "";
			while ((line = in.readLine()) != null) {
				text = text + line;
				text = AES_encryption.decrypt(keyValue, text);
				editorPane.setText(text);
				// System.out.println(line);
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
