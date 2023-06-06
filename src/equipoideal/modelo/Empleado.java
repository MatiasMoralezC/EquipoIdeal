package equipoideal.modelo;

public class Empleado {

	private String nombre;
	private Rol rol;
	private int calificacion;
	
	public Empleado(String nombre, Rol rol, int calificacion) {
		this.nombre = nombre;
		this.rol = rol;
		this.calificacion = calificacion;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public Rol getRol() {
		return this.rol;
	}
	
	public int getCalificacion() {
		return this.calificacion;
	}

	@Override
	public String toString() {
		return  "\n" + rol.toString() + " -- Nombre: " + nombre + ", Calificacion: " + calificacion;
	}
}
