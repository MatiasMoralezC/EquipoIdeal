package equipoideal.controlador;

import java.util.List;
import java.util.Scanner;

import equipoideal.modelo.Empleado;
import equipoideal.modelo.Equipo;

public class Controlador {
	Equipo e = new Equipo();

	public void agregarEmpleado() {
		Scanner lector = new Scanner(System.in);

		System.out.println("Ingrese el nombre: ");
		String nombre = lector.next();

		System.out.println("Ingrese su rol: ");
		String rol = lector.next();

		System.out.println("Ingrese su calificacion: ");
		int calificacion = lector.nextInt();

		// por alguna razon no me deja cerrar el lector
//		lector.close();

		Empleado nuevoEmpleado  = new Empleado(nombre.toUpperCase(), rol, calificacion);
		e.cargarEmpleado(nuevoEmpleado);
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
