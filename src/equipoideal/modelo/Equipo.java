package equipoideal.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Equipo {

	List<Empleado> empleados = new ArrayList<>();
	List<List<Empleado>> incompatibles = new ArrayList<>();
	
	public void cargarEmpleado(Empleado e) {
		// si el empleado ya existe, se debe lanzar una excepcion
		empleados.add(e);
	}
	
	public void cargarIncompatibles(String nombre1, String nombre2) {
		if(nombre1.equals(nombre2))
			throw new RuntimeException("los nombres ingresados son iguales");
		
		if(!verificarEmpleado(nombre1) || !verificarEmpleado(nombre2))
			throw new RuntimeException("alguno de los nombres ingresados no es valido");
		
		// chequear
		List<Empleado> par = empleados.stream()
		.filter(p -> p.getNombre().equals(nombre1) || p.getNombre().equals(nombre2))
		.toList();
		
		this.incompatibles.add(par);
	}
	
	private boolean verificarEmpleado(String nombre) {
		// chequear
		return empleados.stream()
				.anyMatch(p -> p.getNombre().equals(nombre));
	}
	
	public void quitarIncompatibilidad(int index) {
		if(index > incompatibles.size() || index < 0)
			throw new RuntimeException("el indice de la incompatibilidad a quitar no es valido");
		incompatibles.remove(index);
	}
		
	public static List<Empleado> encontrarEquipoSinConflictos(List<Empleado> empleados, List<List<Empleado>> incompatibles, HashMap<Rol, Integer> rolesRequeridos) {
	    List<Empleado> mejorEquipo = new ArrayList<>();
	    int mejorContador = Integer.MAX_VALUE;

	    for (int i = 1; i <= empleados.size(); i++) {
	        List<List<Empleado>> combinaciones = generarCombinaciones(empleados, i);
	        
	        for (List<Empleado> combinacion : combinaciones) {
	            boolean cumpleRequerimientos = verificarRequerimientos(rolesRequeridos, combinacion);
	            boolean tieneConflictos = tieneConflictos(combinacion, incompatibles);
	            
	            if (cumpleRequerimientos && !tieneConflictos) {
	                if (combinacion.size() < mejorContador) {
	                    mejorEquipo = combinacion;
	                    mejorContador = combinacion.size();
	                }
	            }
	        }

	        System.out.println("Mejor equipo hasta el momento: " + mejorEquipo.toString());
	    }

	    System.out.println("Mejor equipo encontrado: " + mejorEquipo.toString());
	    return mejorEquipo;
	}

	public static List<List<Empleado>> generarCombinaciones(List<Empleado> empleados, int r) {
	    List<List<Empleado>> result = new ArrayList<>();
	    generarCombinacionesRecursivo(empleados, r, 0, new ArrayList<>(), result);
	    return result;
	}

	public static void generarCombinacionesRecursivo(List<Empleado> empleados, int r, int index, List<Empleado> combinacionActual, List<List<Empleado>> result) {
	    if (combinacionActual.size() == r) {
	        result.add(new ArrayList<>(combinacionActual));
	        return;
	    }

	    for (int i = index; i < empleados.size(); i++) {
	        combinacionActual.add(empleados.get(i));
	        generarCombinacionesRecursivo(empleados, r, i + 1, combinacionActual, result);
	        combinacionActual.remove(combinacionActual.size() - 1);
	    }
	}

	public static boolean verificarRequerimientos(HashMap<Rol, Integer> rolesRequeridos, List<Empleado> equipo) {
	    HashMap<Rol, Integer> contadorRoles = new HashMap<>();

	    for (Empleado empleado : equipo) {
	        Rol rol = empleado.getRol();
	        contadorRoles.put(rol, contadorRoles.getOrDefault(rol, 0) + 1);
	    }

	    for (Map.Entry<Rol, Integer> entry : rolesRequeridos.entrySet()) {
	        Rol rol = entry.getKey();
	        int cantidadRequerida = entry.getValue();
	        int cantidadActual = contadorRoles.getOrDefault(rol, 0);
	        
	        if (cantidadActual < cantidadRequerida) {
	            return false;
	        }
	    }

	    return true;
	}

	public static boolean tieneConflictos(List<Empleado> equipo, List<List<Empleado>> incompatibles) {
	    for (List<Empleado> incompatibilidad : incompatibles) {
	        Empleado empleado1 = incompatibilidad.get(0);
	        Empleado empleado2 = incompatibilidad.get(1);

	        if (equipo.contains(empleado1) && equipo.contains(empleado2)) {
	            return true;
	        }
	    }

	    return false;
	}
	
	//////////////////////////////////////////////////////
	
	private static boolean verificarRequerimiento(HashMap<Rol, Integer> requerimientos, Empleado e) {
//		if(requerimientos == null || requerimientos.isEmpty())
//			throw new RuntimeException("los requerimientos no son validos");
		return requerimientos.containsKey( e.getRol() );
	}
	
	// Uso streams para buscar a un empleado en base a su nombre
	public Empleado buscarPorNombre(String nombre) {
		Empleado e = empleados.stream()
				.filter(empleado -> empleado.getNombre().equals(nombre.toUpperCase()))
				.limit(1)
				.findFirst().orElse(null);
		
		return e;
	}
	
	public List<Empleado> verEmpleados() {
		return this.empleados;
	}

	// Elimina al primer empleado que encuentra con ese nombre.
	//Si no encuentra arroja excepcion.
	public void quitarEmpleado(String nombre) {
		Empleado e = buscarPorNombre(nombre);
		
		if (e.equals(null)) throw new RuntimeException("Empleado no encontrado");
		
		empleados.remove(e);
	}
	
	public List<List<Empleado>> getIncompatibilidades() {
		return incompatibles;
	}
}
