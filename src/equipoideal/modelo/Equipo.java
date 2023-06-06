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
		boolean aux = empleados.stream()
				.filter(empleado -> empleado.getNombre().equals(e.getNombre()))
				.findAny() != null;
		
		if(!aux) {
			throw new RuntimeException("no se puede cargar el empleado");
		} else {
			empleados.add(e);
		}
	}
	
	public void cargarIncompatibles(String nombre1, String nombre2) {
		if(nombre1.equals(nombre2))
			throw new RuntimeException("los nombres ingresados son iguales");
		
		if(!verificarEmpleado(nombre1) || !verificarEmpleado(nombre2))
			throw new RuntimeException("alguno de los nombres ingresados no es valido");
		
		List<Empleado> par = empleados.stream()
		.filter(p -> p.getNombre().equals(nombre1) || p.getNombre().equals(nombre2))
		.toList();
		
		this.incompatibles.add(par);
	}
	
	private boolean verificarEmpleado(String nombre) {
		return empleados.stream()
				.anyMatch(p -> p.getNombre().equals(nombre));
	}
	
	public void quitarIncompatibilidad(int index) {
		if(index > incompatibles.size() || index < 0)
			throw new RuntimeException("el indice de la incompatibilidad a quitar no es valido");
		incompatibles.remove(index);
	}
		
	public List<Empleado> encontrarEquipoSinConflictos(List<Empleado> empleados, List<List<Empleado>> incompatibilidades, Map<Rol, Integer> requerimientos) {
	    verificarEntrada(empleados, requerimientos);
		
		List<Empleado> mejorEquipo = new ArrayList<>();
	    List<List<Empleado>> combinaciones = generarCombinaciones(empleados, 0);

	    for (List<Empleado> equipo : combinaciones) {
	    	System.out.println("Mejor equipo hasta ahora: "+mejorEquipo.toString());
	        if (cumpleRequerimientos(equipo, requerimientos) && esCompatible(equipo, incompatibilidades)) {
	            int calificacionEquipo = calcularCalificacionTotal(equipo);
	            int calificacionMejorEquipo = calcularCalificacionTotal(mejorEquipo);

	            if (calificacionEquipo > calificacionMejorEquipo) {
	                mejorEquipo = equipo;
	            }
	            
	        }
	    }

	    return mejorEquipo;
	}

	private void verificarEntrada(List<Empleado> empleados, Map<Rol, Integer> requerimientos) {
		if (empleados.isEmpty() || requerimientos.isEmpty()) throw new  RuntimeException("Falta entrada de datos");
	}
	
	//DUDA CONSIGNA - SI HAY DOS PERSONAS EN CONFLICTO SE DEBE EXCLUIR SOLO 1 DE ELLAS DEL EQUIPO A FORMAR O AMBAS?
	//ACTUALMENTE EL PROGRAMA VETA A AMBAS.

	public List<List<Empleado>> generarCombinaciones(List<Empleado> empleados, int index) {
	    List<List<Empleado>> combinaciones = new ArrayList<>();

	    if (index == empleados.size()) {
	        combinaciones.add(new ArrayList<>());
	    } else {
	        List<List<Empleado>> subCombinaciones = generarCombinaciones(empleados, index + 1);

	        Empleado empleado = empleados.get(index);

	        for (List<Empleado> subCombinacion : subCombinaciones) {
	            combinaciones.add(subCombinacion); // Combinación sin el empleado actual

	            List<Empleado> nuevaCombinacion = new ArrayList<>(subCombinacion);
	            nuevaCombinacion.add(empleado);
	            combinaciones.add(nuevaCombinacion);
	        }
	    }

	    return combinaciones;
	}

    public boolean cumpleRequerimientos(List<Empleado> equipo, Map<Rol, Integer> requerimientos) {
        Map<Rol, Integer> contadorRoles = new HashMap<>();

        for (Empleado empleado : equipo) {
            Rol rol = empleado.getRol();
            int count = contadorRoles.getOrDefault(rol, 0) + 1;
            contadorRoles.put(rol, count);
        }

        for (Map.Entry<Rol, Integer> requerimiento : requerimientos.entrySet()) {
            Rol rol = requerimiento.getKey();
            int requeridos = requerimiento.getValue();
            int disponibles = contadorRoles.getOrDefault(rol, 0);

            if (disponibles != requeridos) {
                return false;
            }
        }

        return true;
    }

    
    public boolean esCompatible(List<Empleado> equipo, List<List<Empleado>> incompatibilidades) {
        for (List<Empleado> incompatibilidad : incompatibilidades) {
            for (Empleado empleado : incompatibilidad) {
                if (equipo.contains(empleado)) {
                    return false;
                }
            }
        }

        return true;
    }

    public int calcularCalificacionTotal(List<Empleado> equipo) {
        int calificacionTotal = 0;
        for (Empleado empleado : equipo) {
            calificacionTotal += empleado.getCalificacion();
        }
        return calificacionTotal;
    }
	
	public Empleado buscarPorNombre(String nombre) {
		return empleados.stream()
				.filter(empleado -> empleado.getNombre().equals(nombre))
				.limit(1)
				.findFirst().orElse(null);
	}
	
	public List<Empleado> verEmpleados() {
		return this.empleados;
	}

	public void quitarEmpleado(String nombre) {
		Empleado e = buscarPorNombre(nombre.toUpperCase());
		
		if (e.equals(null)) throw new RuntimeException("Empleado no encontrado");
		
		empleados.remove(e);
	}
	
	public List<List<Empleado>> getIncompatibilidades() {
		return incompatibles;
	}
}
