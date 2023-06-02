package equipoideal.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class App {

	private JFrame frmTrabajoPracticoIii;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmTrabajoPracticoIii.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrabajoPracticoIii = new JFrame();
		frmTrabajoPracticoIii.setTitle("Programación III");
		frmTrabajoPracticoIii.setBounds(100, 100, 800, 600);
		frmTrabajoPracticoIii.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrabajoPracticoIii.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Software Factory Team");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(268, 11, 267, 36);
		lblNewLabel.setFont(new Font("Mistral", Font.PLAIN, 35));
		frmTrabajoPracticoIii.getContentPane().add(lblNewLabel);
	}
}
