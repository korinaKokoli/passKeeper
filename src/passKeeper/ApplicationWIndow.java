package passKeeper;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ApplicationWIndow {

	private JFrame frame;
	private JLabel lblPassword;
	private JTextField textField;
	private JButton btnInitialiseApp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationWIndow window = new ApplicationWIndow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplicationWIndow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(62, 11, 299, 34);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 2, 0, 0));

		lblPassword = new JLabel("Password");
		panel.add(lblPassword);

		textField = new JPasswordField();
		panel.add(textField);
		textField.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
//				AES_encryption.setKeyValue(lblPassword.getText().getBytes());

				try {
					new PasswordTable(textField.getText().getBytes());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnOk.setBounds(284, 86, 89, 23);
		frame.getContentPane().add(btnOk);
		
		btnInitialiseApp = new JButton("Initialise app");
		btnInitialiseApp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				new InitialiseForm();
			}
		});
		btnInitialiseApp.setBounds(25, 86, 118, 23);
		frame.getContentPane().add(btnInitialiseApp);
	}
}
