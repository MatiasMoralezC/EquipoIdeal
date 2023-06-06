package equipoideal.controlador;

import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

import equipoideal.modelo.Empleado;
import equipoideal.modelo.Equipo;
import equipoideal.modelo.Rol;

public class Controlador {
	Equipo e = new Equipo();
	HashMap<Rol, Integer> requerimientos = new HashMap<Rol, Integer>();
	List<Empleado> equipoIdeal = new ArrayList<>();

	public void agregarEmpleado() {
		Scanner lector = new Scanner(System.in);
		System.out.println(lector);

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
		return e.verEmpleados();
	}

	public HashMap<Rol, Integer> obtenerMapDeRequerimientos() {
		return this.requerimientos;
	}

	public void quitarEmpleado() {
		Scanner lector = new Scanner(System.in);
		System.out.println("Ingrese el nombre: ");
		String nombre = lector.next();

		e.quitarEmpleado(nombre);

		System.out.println(nombre + " ha sido eliminado de la lista de Empleados.");

	}

	public List<List<Empleado>> obtenerIncompatibilidades() {
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
		System.out.println("Ingrese el indice de la incompatibilidad a quitar (iniciando en 1): ");
		int indexIncompat = lector.nextInt();

		e.quitarIncompatibilidad(indexIncompat - 1);
	}

	public void agregarRequerimiento() {
		Scanner lector = new Scanner(System.in);
		Rol rol = conseguirRol(lector);
		if (requerimientos.containsKey(rol)) {
			throw new RuntimeException("El requerimiento que quieres ingresar ya existe");
		}

		System.out.println("Ingrese su cantidad para el rol " + rol.toString() + ": ");
		int cant = lector.nextInt();


		requerimientos.put(rol, cant);
	}

	public List<String> obtenerRequerimientos() {
		List<String> aux = new ArrayList<>();
		requerimientos.forEach((k, v) -> aux.add("se requieren " + v + " para " + k.toString()));
		return aux;
	}

	public void quitarRequerimiento() {
		Scanner lector = new Scanner(System.in);
		Rol rol = conseguirRol(lector);

		System.out.println("Ingrese su cantidad que desea quitar del  rol " + rol.toString() + ": ");
		int cant = lector.nextInt();
		int cantidadDelRol = cantidadRol(rol);

		if (cantidadDelRol < cant) {
			throw new RuntimeException("No existe esa cantidad de " + rol.toString());
		} else if (cantidadDelRol > cant) {
			requerimientos.remove(rol);
			requerimientos.put(rol, cantidadDelRol - cant);
			System.out.println("Se ha/n quitado " + cant + " " + rol.toString() + "/s");
		} else {
			requerimientos.remove(rol);
			System.out.println("Se han quitado todos los " + rol.toString());
		}

	}

	public int cantidadRol(Rol rol) {
		if (!requerimientos.containsKey(rol)) {
			throw new RuntimeException("El requerimiento que intenta quitar no existe");
		}

		return requerimientos.get(rol);
	}

	public void imprimirEquipo() {
		if (equipoIdeal.isEmpty())
			throw new RuntimeException("Aun no se creo un equipo");

		System.out.println(equipoIdeal.toString());
	}

	public void formarEquipo() {
		List<Empleado> empleados = verEmpleados();
		List<List<Empleado>> incompatibles = obtenerIncompatibilidades();
		HashMap<Rol, Integer> requerimientos = obtenerMapDeRequerimientos();

		Thread equipoIdealThread = new Thread(() -> {
			equipoIdeal = e.encontrarEquipoSinConflictos(empleados, incompatibles, requerimientos);
		});

		equipoIdealThread.start();
	}

}
