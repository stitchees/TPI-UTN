package src;

public class Equipo {
	
	private String nombre;
	private String descripcion;
	
	//constructor
	public Equipo(String nombre,String descripcion) {
	
	this.nombre = nombre;
	this.descripcion = descripcion;
	
	}

	public Equipo() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
