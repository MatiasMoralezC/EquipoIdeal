package equipoideal.modelo;

public class Empleado {

	private String nombre;
	private String rol;
	private int calificacion;
	
	public Empleado(String nombre, String rol, int calificacion) {
		this.nombre = nombre;
		this.rol = rol;
		this.calificacion = calificacion;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public String getRol() {
		// TODO Auto-generated method stub
		return this.rol;
	}
	
	public int getCalificacion() {
		return this.calificacion;
	}

	@Override
	public String toString() {
		return  "\n" + rol.toUpperCase() + " -- Nombre: " + nombre + ", Calificacion: " + calificacion;
	}
}
