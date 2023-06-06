package equipoideal.modelo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EquipoTest {
	/*
	 * void cargarEmpleado(Empleado e) --> hace falta?
	 * 
	 * void cargarIncompatibles(String nombre1, String nombre2) -- listo
	 * 
	 * boolean verificarEmpleado(String nombre) -- private -- hace falta¿? el de arriba lo controla
	 * 
	 * quitarIncompatibilidad(int index)
	 * 
	 * List<Empleado> encontrarEquipoSinConflictos(List<Empleado> empleados,
	 *  	List<List<Empleado>> incompatibles, HashMap<Rol, Integer> rolesRequeridos) -- luego
	 *  
	 * List<Empleado> filtrarEmpleadosNoRequeridos(List<Empleado> empleados,
	 * 		HashMap<Rol, Integer> rolesRequeridos)
	 * 
	 * int contarEmpleadosRequeridosCumplidos(HashMap<Rol, Integer> rolesRequeridos, List<Empleado> equipo)
	 * 
	 * double obtenerCalificacionTotal(List<Empleado> equipo)
	 * 
	 * List<List<Empleado>> generarCombinaciones(List<Empleado> empleados, int r)
	 * 
	 * void generarCombinacionesRecursivo(List<Empleado> empleados, int r, int index, List<Empleado> combinacionActual, List<List<Empleado>> result)
	 * 
	 * boolean tieneConflictos(List<Empleado> equipo, List<List<Empleado>> incompatibles)
	 * 
	 * boolean verificarRequerimiento(HashMap<Rol, Integer> requerimientos, Empleado e)
	 * 
	 * Empleado buscarPorNombre(String nombre)
	 */
	

	Equipo equipo;
	@Before
	public void inicializar() {
		equipo = new Equipo();
		
		String[] nombres = {"pedro","pipo","picasso","pablo"};
		Rol[] rolesDeEmpleados = {Rol.LIDER_DE_PROYECTO, Rol.ARQUITECTO, Rol.PROGRAMADOR, Rol.TESTER};
		int[] calificaciones = {5,4,3,3};

		for (int i = 0; i < nombres.length; i++) {
			equipo.cargarEmpleado( new Empleado( nombres[i], rolesDeEmpleados[i], calificaciones[i] ) );
		}
	}
	
	// cargarIncompatibles()
	@Test(expected = Exception.class)
	public void cargarIncompatiblesNombresIguales() {
		String nombre1="pepe";
		String nombre2="pepe";
		
		equipo.cargarIncompatibles(nombre1, nombre2);
	}
	
	@Test(expected = Exception.class)
	public void cargarIncompatiblesNoEmpleado1() {
		String nombre1="pepe";
		String nombre2="pepo";
		
		equipo.cargarIncompatibles(nombre1, nombre2);
	}
	
	@Test
	public void cargarIncompatiblesCorrecto() {
		String nombre1 = "pipo";
		String nombre2 = "picasso";
		
		equipo.cargarIncompatibles(nombre1, nombre2);
		String expect1 = "\nARQUITECTO -- Nombre: pipo, Calificacion: 4";
		String expect2 = "\nPROGRAMADOR -- Nombre: picasso, Calificacion: 3";
		
		assertTrue( expect1.equals( equipo.getIncompatibilidades().get(0).get(0).toString() ));
		assertTrue( expect2.equals( equipo.getIncompatibilidades().get(0).get(1).toString() ));
	}
	

	// quitarIncompatibilidad(int index)
	@Test
	public void quitarIncompatibilidadCorrecto() {
		String nombre1 = "pipo";
		String nombre2 = "picasso";
		
		equipo.cargarIncompatibles(nombre1, nombre2);
		equipo.quitarIncompatibilidad(0);
		
		assertTrue( equipo.getIncompatibilidades().isEmpty() );
	}

	@Test(expected = Exception.class)
	public void quitarIncompatibilidadIndexNegativo() {
		String nombre1 = "pipo";
		String nombre2 = "picasso";
		
		equipo.cargarIncompatibles(nombre1, nombre2);
		equipo.quitarIncompatibilidad(-10);
		
		assertTrue( equipo.getIncompatibilidades().isEmpty() );
	}

	@Test(expected = Exception.class)
	public void quitarIncompatibilidadIndexNoValido() {
		String nombre1 = "pipo";
		String nombre2 = "picasso";
		
		equipo.cargarIncompatibles(nombre1, nombre2);
		equipo.quitarIncompatibilidad(10);
	}
	
}
