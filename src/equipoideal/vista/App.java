package equipoideal.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import equipoideal.controlador.Controlador;
import equipoideal.modelo.Empleado;

import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Scanner;

import javax.swing.JMenu;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class App {

	private JFrame frmTrabajoPracticoIii;

	Controlador controlador = new Controlador();

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

		JLabel lblNewLabel_Empleados = new JLabel("Empleados");
		lblNewLabel_Empleados.setFont(new Font("Elephant", Font.PLAIN, 18));
		lblNewLabel_Empleados.setBounds(10, 75, 111, 21);
		frmTrabajoPracticoIii.getContentPane().add(lblNewLabel_Empleados);

		JButton btnVerEmpleados = new JButton("Ver Empleados");
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirEmpleados();
			}
		});

		btnVerEmpleados.setBounds(10, 107, 133, 23);
		frmTrabajoPracticoIii.getContentPane().add(btnVerEmpleados);

		JButton btnAgregarEmpleado = new JButton("Agregar Nuevo");
		btnAgregarEmpleado.setForeground(new Color(0, 128, 0));
		btnAgregarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarEmpleado();
			}
		});
		btnAgregarEmpleado.setBounds(153, 107, 133, 23);
		frmTrabajoPracticoIii.getContentPane().add(btnAgregarEmpleado);

		JButton btnQuitarEmpleado = new JButton("Quitar");
		btnQuitarEmpleado.setForeground(new Color(255, 0, 0));
		btnQuitarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarEmpleado();
			}
		});
		btnQuitarEmpleado.setBounds(296, 107, 89, 23);
		frmTrabajoPracticoIii.getContentPane().add(btnQuitarEmpleado);

		JLabel lblNewLabel_Incompat = new JLabel("Incompatibilidades");
		lblNewLabel_Incompat.setFont(new Font("Elephant", Font.PLAIN, 18));
		lblNewLabel_Incompat.setBounds(10, 167, 196, 23);
		frmTrabajoPracticoIii.getContentPane().add(lblNewLabel_Incompat);

		JButton btnVerIncompatibles = new JButton("Ver Incompatibles");
		btnVerIncompatibles.setBounds(10, 201, 155, 23);
		btnVerIncompatibles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verIncompatibilidades();
			}
		});
		frmTrabajoPracticoIii.getContentPane().add(btnVerIncompatibles);

		JButton btnAgregarIncompat = new JButton("Agregar Nueva");
		btnAgregarIncompat.setForeground(new Color(0, 128, 0));
		btnAgregarIncompat.setBounds(175, 201, 133, 23);
		btnAgregarIncompat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarIncompatibilidades();
			}
		});
		frmTrabajoPracticoIii.getContentPane().add(btnAgregarIncompat);

		JButton btnQuitarIncompat = new JButton("Quitar");
		btnQuitarIncompat.setForeground(new Color(255, 0, 0));
		btnQuitarIncompat.setBounds(318, 201, 89, 23);
		btnQuitarIncompat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirIncompatibilidades();
				controlador.eliminarIncompatibilidad();
			}
		});
		frmTrabajoPracticoIii.getContentPane().add(btnQuitarIncompat);

		JLabel lblNewLabel_Req = new JLabel("Requerimientos");
		lblNewLabel_Req.setFont(new Font("Elephant", Font.PLAIN, 18));
		lblNewLabel_Req.setBounds(10, 257, 196, 23);
		frmTrabajoPracticoIii.getContentPane().add(lblNewLabel_Req);

		JButton btnVerRequerimientos = new JButton("Ver Requerimientos");
		btnVerRequerimientos.setBounds(10, 291, 155, 23);
		frmTrabajoPracticoIii.getContentPane().add(btnVerRequerimientos);

		JButton btnAgregarReq = new JButton("Agregar Nuevo");
		btnAgregarReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.agregarRequerimiento();
			}
		});
		btnAgregarReq.setForeground(new Color(0, 128, 0));
		btnAgregarReq.setBounds(175, 291, 124, 23);
		frmTrabajoPracticoIii.getContentPane().add(btnAgregarReq);

		JButton btnQuitarReq = new JButton("Quitar");
		btnQuitarReq.setForeground(new Color(255, 0, 0));
		btnQuitarReq.setBounds(318, 291, 89, 23);
		frmTrabajoPracticoIii.getContentPane().add(btnQuitarReq);

		JLabel lblNewLabel_EquipoIdeal = new JLabel("Equipo Ideal");
		lblNewLabel_EquipoIdeal.setFont(new Font("Elephant", Font.PLAIN, 18));
		lblNewLabel_EquipoIdeal.setBounds(10, 344, 196, 23);
		frmTrabajoPracticoIii.getContentPane().add(lblNewLabel_EquipoIdeal);

		JButton btnVerEquipoIdeal = new JButton("Ver Equipo");
		btnVerEquipoIdeal.setBounds(10, 378, 133, 23);
		frmTrabajoPracticoIii.getContentPane().add(btnVerEquipoIdeal);

		JButton btnFormarEquipo = new JButton("Formar Equipo");
		btnFormarEquipo.setForeground(new Color(0, 128, 255));
		btnFormarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * List<Empleado> equipo = c.formarEquipo(empleados, incompatibles,
				 * rolesRequeridos);
				 * 
				 * System.out.println(equipo);
				 */
			}
		});
		btnFormarEquipo.setBounds(153, 378, 124, 23);
		frmTrabajoPracticoIii.getContentPane().add(btnFormarEquipo);
	}

	private void agregarEmpleado() { // --> deberia devolver un boolean¿? por si no se puede, mostrar un aviso
		controlador.agregarEmpleado();
		imprimirEmpleados();
	}

	private void quitarEmpleado() { // --> deberia devolver un boolean¿? por si no se puede, mostrar un aviso
		controlador.quitarEmpleado();
		imprimirEmpleados();
	}

	private void verIncompatibilidades() {
		imprimirEmpleados();
		System.out.println("::::: incompatibilidades :::::");
		imprimirIncompatibilidades();
		// controlador.obtenerIncompatibilidades()
	}
	
	private void agregarIncompatibilidades() {
		imprimirIncompatibilidades();
		controlador.agregarIncompatibilidades();		
	}
	
	private void imprimirEmpleados() {
		System.out.println("::::: empleados :::::\n"+controlador.verEmpleados().toString());
	}

	private void imprimirIncompatibilidades() {
		List incomp = controlador.obtenerIncompatibilidades();
		List aux;
		for (int i = 0; i < incomp.size(); i++) {
			aux = (List) incomp.get(i);
			System.out.println(aux);
		}
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
