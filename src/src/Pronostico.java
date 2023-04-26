package src;



public class Pronostico {
	
	private Partido partido;
	
	private Equipo equipo;
	
	private ResultadoEnum resultado;
	
	private String nombre;
	
	public int puntos(ResultadoEnum resultado) {
		if (this.resultado.equals(resultado)) {
			return 1;
		}
		return 0;
	}

	public Partido getPartido() {
		return partido;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public ResultadoEnum getResultado() {
		return resultado;
	}

	public void setResultado(ResultadoEnum resultado) {
		this.resultado = resultado;
	}
	
	

}
