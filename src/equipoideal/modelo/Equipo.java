package equipoideal.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Equipo {

	List<Empleado> empleados = new ArrayList<>();
	List<List<Empleado>> incompatibles = new ArrayList<>();
	
	public void cargarEmpleado(Empleado e) {
		// si el empleado ya existe, se debe lanzar una excepcion
		empleados.add(e);
	}
	
	public void cargarIncompatibles(Empleado e1, Empleado e2) {
		// si son los mismos. o si alguno no es empleado, se debe lanzar una excepcion
		List<Empleado> par = new ArrayList<>();
		par.add(e1);
		par.add(e2);
		this.incompatibles.add(par);
	}
		
	public static List<Empleado> encontrarEquipoSinConflictos(List<Empleado> empleados, List<List<Empleado>> incompatibles, List<String> rolesRequeridos) {
        int n = empleados.size();
        List<Empleado> mejorEquipo = new ArrayList<>();
        int mejorContador = -1;
        
        // Ordenar los empleados por calificación (de mayor a menor) VERIFICAR SI FUNCIONA!!!!
        empleados.sort((empleado1, empleado2) -> Double.compare(empleado2.getCalificacion(), empleado1.getCalificacion()));
        
        // Generar todas las posibles combinaciones de empleados
        for (int i = 0; i < (1 << n); i++) {
            List<Empleado> equipoActual = new ArrayList<>();
            int contadorConflictos = 0;
            boolean esEquipoValido = true;
            
            // Verificar cada empleado en la combinación
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    Empleado empleado = empleados.get(j);
                    
                    // Verificar si el empleado tiene el rol requerido
                    if (!rolesRequeridos.contains(empleado.getRol())) {
                        esEquipoValido = false;
                        break;
                    }
                    
                    equipoActual.add(empleado);
                }
            }
            
            
            // Salta a la siguiente iteración del for en caso de que el equipo actual no cumpla 
            //con los requisitos necesarios.
            if (!esEquipoValido) {
                continue;
            }
            
            // Verificar conflictos entre empleados
            for (List<Empleado> incompatibilidad : incompatibles) {
                Empleado empleado1 = incompatibilidad.get(0);
                Empleado empleado2 = incompatibilidad.get(1);
                
                if (equipoActual.contains(empleado1) && equipoActual.contains(empleado2)) {
                    contadorConflictos++;
                    esEquipoValido = false;
                    break;
                }
            }
            
            // Actualizar el mejor equipo encontrado hasta ahora
            if (esEquipoValido && contadorConflictos > mejorContador) {
                mejorEquipo = equipoActual;
                mejorContador = contadorConflictos;
            }
            System.out.println(mejorEquipo.toString());
        }
        
        return mejorEquipo;
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
