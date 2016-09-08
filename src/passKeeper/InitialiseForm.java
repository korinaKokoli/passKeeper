package passKeeper;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JButton;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InitialiseForm {
	private JFrame f = new JFrame("Initialise");

	private JPanel panel;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnOk = new JButton("OK");

	/**
	 * Create the frame.
	 */
	public InitialiseForm() {
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f.setSize(400, 300);
		f.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 77, 343, 64);
		f.getContentPane().add(panel);
		panel.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel lblNewPassword = new JLabel("New password:");
		panel.add(lblNewPassword);

		textField = new JPasswordField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textField.getText().equals(textField_1.getText())
						&& textField.getText().length() == 16) {
					btnOk.setEnabled(true);
				} else {
					btnOk.setEnabled(false);
				}
			}
		});
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblRepeatePassword = new JLabel("Repeate password:");
		panel.add(lblRepeatePassword);

		textField_1 = new JPasswordField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textField.getText().equals(textField_1.getText())
						&& textField.getText().length() == 16) {
					btnOk.setEnabled(true);
				} else {
					btnOk.setEnabled(false);
				}
			}
		});
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblThisActionWill = new JLabel(
				"This action will erase all your previous passwords!");
		lblThisActionWill.setForeground(Color.RED);
		lblThisActionWill.setBounds(10, 11, 320, 14);
		f.getContentPane().add(lblThisActionWill);

		JLabel lblPasswordShouldHave = new JLabel(
				"Password should have 16 characters!");
		lblPasswordShouldHave.setForeground(SystemColor.desktop);
		lblPasswordShouldHave.setBounds(10, 36, 320, 14);
		f.getContentPane().add(lblPasswordShouldHave);
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {

					String content = "Welcome!!!! korina kokoli";

					content = AES_encryption.encrypt(textField.getText()
							.getBytes(), content);
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

					f.dispose();
					new PasswordTable(textField.getText().getBytes());

				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnOk.setBounds(264, 190, 89, 23);
		btnOk.setEnabled(false);
		f.getContentPane().add(btnOk);
		f.setVisible(true);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 450, 300);
		// contentPane = new JPanel();
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// contentPane.setLayout(new BorderLayout(0, 0));
		// setContentPane(contentPane);
	}

}
