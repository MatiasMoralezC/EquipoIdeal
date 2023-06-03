package equipoideal.controlador;

import java.util.List;

import equipoideal.modelo.Empleado;
import equipoideal.modelo.Equipo;

public class Controlador {
	Equipo e = new Equipo();

	public void agregarEmpleado(Empleado nuevoEmpleado) {
		e.cargarEmpleado(nuevoEmpleado);
	}

	public List<Empleado> verEmpleados() {
		// TODO Auto-generated method stub
		return e.verEmpleados();
	}

	public void quitarEmpleado(String nombre) {
		e.quitarEmpleado(nombre);
		
		System.out.println(nombre + " ha sido eliminado de la lista de Empleados.");
	}

}
