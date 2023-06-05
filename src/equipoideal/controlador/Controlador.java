package equipoideal.controlador;

import java.util.List;
import java.util.Scanner;

import equipoideal.modelo.Empleado;
import equipoideal.modelo.Equipo;
import equipoideal.modelo.Rol;

public class Controlador {
	Equipo e = new Equipo();

	public void agregarEmpleado() {
		Scanner lector = new Scanner(System.in);

		System.out.println("Ingrese el nombre: ");
		String nombre = lector.next();

		Rol rol = conseguirRol(lector);

		System.out.println("Ingrese su calificacion: ");
		int calificacion = lector.nextInt();

		Empleado nuevoEmpleado = new Empleado(nombre.toUpperCase(), rol, calificacion);
		e.cargarEmpleado(nuevoEmpleado);
	}

	private Rol conseguirRol(Scanner lector) {
		Rol rol = null;
		System.out.println("Ingrese su rol: ");
		String aux = lector.next();

		while (rol == null) {
			if (aux.equals("lider") || aux.equals("l")) {
				rol = Rol.LIDER_DE_PROYECTO;
			} else if (aux.equals("arquitecto") || aux.equals("a")) {
				rol = Rol.ARQUITECTO;
			} else if (aux.equals("programador") || aux.equals("p")) {
				rol = Rol.PROGRAMADOR;
			} else if (aux.equals("tester") || aux.equals("t")) {
				rol = Rol.TESTER;
			} else {
				System.out.println("Ingrese su rol: ");
				aux = lector.next();
			}
		}

		return rol;
	}

	public List<Empleado> verEmpleados() {
		// TODO Auto-generated method stub
		return e.verEmpleados();
	}

	public void quitarEmpleado() { // deberia ser boolean¿?
		try (Scanner lector = new Scanner(System.in)) {
			System.out.println("Ingrese el nombre: ");
			String nombre = lector.next();
//		lector.close();

			e.quitarEmpleado(nombre);

			System.out.println(nombre + " ha sido eliminado de la lista de Empleados.");
		}
	}

	public List<List<Empleado>> obtenerIncompatibilidades() {
		// esta bien que devuelva la lista¿¿??
		return e.getIncompatibilidades();
	}

	public void agregarIncompatibilidades() {
		Scanner lector = new Scanner(System.in);
		System.out.println("Ingrese el primer nombre: ");
		String nombre1 = lector.next();
		System.out.println("Ingrese el segundo nombre: ");
		String nombre2 = lector.next();
		e.cargarIncompatibles(nombre1.toUpperCase(), nombre2.toUpperCase());

	}

	public void eliminarIncompatibilidad() {
		Scanner lector = new Scanner(System.in);
		System.out.println("Dime el indice de la incompatibilidad a quitar: ");
		int indexIncompat = lector.nextInt();
		e.quitarIncompatibilidad(indexIncompat - 1); // ojo con esto
	}

}
