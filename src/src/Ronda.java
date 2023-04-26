package src;

import java.util.ArrayList;

public class Ronda {

	private String nro;
	private ArrayList<Partido> partidos;
	
	//constructor
	public Ronda(String numero,ArrayList<Partido> partidos) {
	
	this.nro = numero;
	this.partidos = partidos;
		
	}
	
	public int puntos() {
		return 0;
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public ArrayList<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(ArrayList<Partido> partidos) {
		this.partidos = partidos;
	}

	
	
}
