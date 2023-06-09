package IntegradorMaven.ConexionMysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.swing.JOptionPane;
import java.awt.Dialog;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;


public class BasedeDatos {
	public static void main(String[] args) throws ClassNotFoundException {
		Connection conectar = null;
    	String sURL = "jdbc:mysql://localhost:3306/projectofinal";
    		
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	
    		try {
    			conectar = DriverManager.getConnection(sURL, "root", "");
    		JOptionPane.showMessageDialog(null,"Conexion Exitosa");
    		} catch (SQLException ex) {
    			JOptionPane.showMessageDialog(null,"Conexion fallida");
    		}
    		Statement s=null;
    		try {
    				s = conectar.createStatement();
    		    }
    		catch (SQLException ex) {
    		}
    	
    		 ResultSet rs=null;
    		 
    		  try { 
    			  
    			  rs = s.executeQuery("SELECT r.codResultado,r.codEquipo1,e1.descEquipo,r.golesEquipo1,r.golesEquipo2,e2.descEquipo,r.codEquipo2 "
    			  		+ "			   FROM projectofinal.resultado as r inner join  projectofinal.equipo as e1 on r.codEquipo1=e1.idEquipo "
    			  		+ "										   inner join projectofinal.equipo as e2  on (  r.codEquipo2=e2.idEquipo);");
    			  
    			  
    			  
    		     }
    		  catch (SQLException ex) {
    			  JOptionPane.showMessageDialog(null,"consulta de tabla --Resultado-- fallida");
    		
    		
    		  	}
    		  
    		  
    		  ArrayList<Partido> listaDePartido = new ArrayList<Partido>();
				
				Partido partidoX;
				
				Equipo equipo1;
				Equipo equipo2;
    		  
				
				
    		try {
    			
    			while(rs.next() ){
    									
    					partidoX = new Partido();
    	 				equipo1 = new Equipo();
    	 				equipo2 = new Equipo();
    	 				
    	 				equipo1.setNombre(rs.getString(3));
    	 				equipo2.setNombre(rs.getString(6));
    	 				partidoX.setEquipo1(equipo1);
    	 				partidoX.setEquipo2(equipo2);
    	 				partidoX.setGolesEquipo1(rs.getInt(4));
    	 				partidoX.setGolesEquipo2(rs.getInt(5));
    	    					
    	    			listaDePartido.add(partidoX);
					
    			 }
    			

//    			JOptionPane.showMessageDialog(null, listaDePronos.get(0).getNombre());
    			
    				
    		} catch (SQLException ex)  {
    			JOptionPane.showMessageDialog(null,"no se cargaron correctamente los datos correctamente de "
    					+ "la tabla --Resultado--");
    		}
    		
    		
    		rs=null;
    		
    		try {
    			rs = s.executeQuery("SELECT * FROM projectofinal.pronostico;");
			} catch (SQLException ex) {
  			  JOptionPane.showMessageDialog(null,"consulta de tabla --Pronostico-- fallida");
			}
    		
    		ArrayList<Pronostico> listaDePronos = new ArrayList<Pronostico>();
			
			Pronostico pronosticoX;
			
			Equipo equipoX;
			ResultadoEnum resultado = null;
		
			
			try {
				
				while (rs.next()) {
					pronosticoX = new Pronostico();	
    				partidoX = new Partido();
    				equipo1 = new Equipo();
    				equipo1.setNombre(rs.getString(3));
    				equipo2 = new Equipo();
    				equipo2.setNombre(rs.getString(7));
    				partidoX.setEquipo1(equipo1);
    				partidoX.setEquipo2(equipo2);	
    				equipoX = new Equipo();	
    				 				
    				
    				if (rs.getString(4) != null && "x".equalsIgnoreCase(rs.getString(4)) ) {
    				    equipoX.setNombre(rs.getString(3));
    				    resultado = ResultadoEnum.ganador;
    				}
    				if(rs.getString(5) != null && "x".equalsIgnoreCase(rs.getString(5)) ) {
    				    resultado = ResultadoEnum.empate;
    				}
    				if(rs.getString(6) != null && "x".equalsIgnoreCase(rs.getString(6)) ) {
    				    equipoX.setNombre(rs.getString(3));
    				    resultado = ResultadoEnum.perdedor;
    				}
    				
    				

    					
    				pronosticoX.setNombre(rs.getString(2));
    				pronosticoX.setPartido(partidoX);
    				pronosticoX.setEquipo(equipoX);
    				pronosticoX.setResultado(resultado);
    				
    				listaDePronos.add(pronosticoX);	
					
				}
				
//				JOptionPane.showMessageDialog(null, listaDePronos.get(0).getNombre());
				
			} catch (SQLException ex)  {
    			JOptionPane.showMessageDialog(null,"no se cargaron correctamente los datos correctamente "
    					+ "de la tabla --Pronostico--");
    		}

			//listaDePronos
			//listaDePartido
			
			
			int puntos = 0;
			ResultadoEnum resultadoDePartido = null;
			String nombreParticipante = null;
			
			
			
			for (int i = 0; i < listaDePartido.size(); i++) {
				
				// verificacion entre ambos arrays 
				nombreParticipante = listaDePronos.get(i).getNombre();
				
				if (listaDePartido.get(i).getEquipo1().getNombre().equals(listaDePronos.get(i).getPartido().getEquipo1().getNombre()) && 
					listaDePartido.get(i).getEquipo2().getNombre().equals(listaDePronos.get(i).getPartido().getEquipo2().getNombre())) {
					
					// obtencion de resultado del partido
					
					resultadoDePartido = listaDePartido.get(i).resultado(listaDePartido.get(i).getEquipo1());

					// obtencion y sumatoria de puntos
					
					puntos += listaDePronos.get(i).puntos(resultadoDePartido);
					
				}
					
			}
			
			JOptionPane.showMessageDialog(null,nombreParticipante + " a obtenido "+ puntos +" puntos.");
			
			
	}
}

