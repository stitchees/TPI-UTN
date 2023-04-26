package src;

import java.util.*;

import javax.swing.JOptionPane;

import java.awt.Dialog;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Principal {

	public static void main(String[] args) throws IOException {
	
	// lectura de archivo resultado y generacion de array con lo obtenido 
		
		ArrayList<Partido> listaDePartido = new ArrayList<Partido>();
		
		Partido partidoX;
		
		Equipo equipo1;
		Equipo equipo2;
		
		String archivoResultado = "archivosTxt\\resultados.txt";
		
		for (String linea : Files.readAllLines(Paths.get(archivoResultado))) {
		
			String lineas[] = linea.split(";");
			
			partidoX = new Partido();
			equipo1 = new Equipo();
			equipo2 = new Equipo();
		
			equipo1.setNombre(lineas[0]);
			equipo2.setNombre(lineas[3]);
			partidoX.setEquipo1(equipo1);
			partidoX.setEquipo2(equipo2);
			partidoX.setGolesEquipo1(Integer.parseInt(lineas[1]));
			partidoX.setGolesEquipo2(Integer.parseInt(lineas[2]));
			
			listaDePartido.add(partidoX);
		}
		
	// lectura de archivo resultado y generacion de array con lo obtenido 
		
		ArrayList<Pronostico> listaDePronos = new ArrayList<Pronostico>();
		
		String archivoPronostico = "archivosTxt\\pronostico.txt";
		
		Pronostico pronosticoX;
		Equipo equipoX;
		ResultadoEnum resultado = null;		
		
		for (String linea : Files.readAllLines(Paths.get(archivoPronostico))) {
			
			String lineas[] = linea.split(";");
			
			/*
			 * [0] = equipo1 = argentina
			 * [1] = gana1 = 1
			 * [2] = empate = 0
			 * [3] = gana2 = 0
			 * [4] = equipo2 = arabia saudita
			 * [5] = nombre de la persona
			 * */
			
			pronosticoX = new Pronostico();	
			partidoX = new Partido();
			equipo1 = new Equipo();
			equipo1.setNombre(lineas[0]);
			equipo2 = new Equipo();
			equipo2.setNombre(lineas[4]);
			partidoX.setEquipo1(equipo1);
			partidoX.setEquipo2(equipo2);	
			equipoX = new Equipo();
			
			if (lineas[1].equals("1")) {
				equipoX.setNombre(lineas[0]);
				resultado = ResultadoEnum.ganador;
			} else if(lineas[2].equals("1")) {
				resultado = ResultadoEnum.empate;
			} else if(lineas[3].equals("1")) {
				equipoX.setNombre(lineas[0]);
				resultado = ResultadoEnum.perdedor;
			}
				
			pronosticoX.setNombre(lineas[5]);
			pronosticoX.setPartido(partidoX);
			pronosticoX.setEquipo(equipoX);
			pronosticoX.setResultado(resultado);
			
			listaDePronos.add(pronosticoX);			
		}
		
		//listaDePronos
		//listaDePartido
		
		int puntos = 0;
		ResultadoEnum resultadoDePartido = null;
		
		for (int i = 0; i < listaDePartido.size(); i++) {
			
			// verificacion entre ambos arrays 
			
			if (listaDePartido.get(i).getEquipo1().getNombre().equals(listaDePronos.get(i).getPartido().getEquipo1().getNombre()) && 
				listaDePartido.get(i).getEquipo2().getNombre().equals(listaDePronos.get(i).getPartido().getEquipo2().getNombre())) {
				
				// obtencion de resultado del partido
				
				resultadoDePartido = listaDePartido.get(i).resultado(listaDePartido.get(i).getEquipo1());

				// obtencion y sumatoria de puntos
				
				puntos += listaDePronos.get(i).puntos(resultadoDePartido);
				
			}
				
		}
		
		JOptionPane.showMessageDialog(null,"Se han obtenido "+ puntos +" puntos.");
	}

}
