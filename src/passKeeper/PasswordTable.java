package passKeeper;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PasswordTable {

	private JFrame f = new JFrame("Password table");
	private JTable table;

	/*
	 * private static byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e',
	 * 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };
	 */

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public PasswordTable(final byte[] keyValue) throws Exception {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300, 300);
		// f.getContentPane().setLayout(null);
		String text = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"resources/pass.txt"));
			String line;

			while ((line = in.readLine()) != null) {
				text = text + line;
				text = AES_encryption.decrypt(keyValue, text);
				// editorPane.setText(text);
				// System.out.println(line);
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		f.getContentPane().add(panel);
		f.setVisible(true);

		

		String[] data = text.split(" ");
		Vector<Vector> rowData = new Vector<Vector>();
		for (int i = 0; i < data.length; i = i + 3) {
			Vector<String> row = new Vector<String>();
			for (int j = i; j < i+3; j++) {
				row.add(data[j]);
			}
			rowData.addElement(row);
		}

		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("Name");
		columnNames.addElement("Username");
		columnNames.addElement("Password");

		table = new JTable(rowData, columnNames);
		// table = new JTable(new MyTableModel());
		// // Create columns names
		// String columnNames[] = { "Column 1", "Column 2", "Column 3" };
		//
		// // Create some data
		// String dataValues[][] = { { "12", "234", "67" },
		// { "-123", "43", "853" }, { "93", "89.2", "109" },
		// { "279", "9033", "3092" } };
		// table = new JTable(dataValues, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnAddRow = new JButton("Add row");
		btnAddRow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Vector<String> rowOne = new Vector<String>();
				rowOne.addElement("");
				rowOne.addElement("");
				rowOne.addElement("");
				model.addRow(rowOne);
			}
		});
		panel_1.add(btnAddRow);

		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("kaleeee???");
				int rowNums = table.getRowCount();
				String passwords = "";
				for (int i = 0; i < rowNums; i++) {
					for (int j = 0; j < 3; j++) {
						passwords = passwords + table.getValueAt(i, j) + " ";
					}
				}
				try {
					passwords = AES_encryption.encrypt(keyValue, passwords);//"korina kokoli kokoli a a a a a a a a a in in in");

					File file = new File("resources/pass.txt");

					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(passwords);
					bw.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel_1.add(btnSave);

	}

}
