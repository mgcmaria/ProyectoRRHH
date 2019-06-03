package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import tablas.Candidato;

public class AccesoDB {
	
public static Connection conexion() {
	
		Connection con = null;

		// Paso 1: Cargar el driver		

		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Paso 2: Establecer conexion con la base de datos
		
		String cadenaConexion = "jdbc:oracle:thin:@10.2.0.11:1521:GESTION";
		String user = "RAPIDSITE";
		String pass = "sixtina";

		try {
			con = DriverManager.getConnection(cadenaConexion, user, pass);
		} catch (SQLException e) {
			e.getMessage();
			//return null;
		}		
		return con;
	}

	public static void cerrarConexion(Connection conexion) {
		
		try {	
			conexion.close();	
		} catch (SQLException e) {	
			e.getMessage();	
			return;	
		}	
	}

	public static String[][] obtenerMatrizCandidatos() {
		
		Connection conexion = conexion();	

		ArrayList<Candidato> listaCandidatos = datosCandidatos(conexion);		

		String matrizInfo[][] = new String[listaCandidatos.size()][8];		

		for (int i = 0; i < listaCandidatos.size(); i++) {

			matrizInfo[i][0] = listaCandidatos.get(i).getIdCandidato()+"";
			matrizInfo[i][1] = listaCandidatos.get(i).getNombre()+"";
			matrizInfo[i][2] = listaCandidatos.get(i).getApellidos()+"";
			matrizInfo[i][3] = listaCandidatos.get(i).getEmail()+"";
			matrizInfo[i][4] = listaCandidatos.get(i).getTelefono()+"";
			matrizInfo[i][5] = listaCandidatos.get(i).getPerfil()+"";
			matrizInfo[i][6] = listaCandidatos.get(i).getFuente()+"";
			matrizInfo[i][7] = listaCandidatos.get(i).getObservaciones()+"";
		}		

		return matrizInfo;		
	}

	private static ArrayList<Candidato> datosCandidatos(Connection conexion) {
		
		ArrayList<Candidato> lista_candidatos = new ArrayList<Candidato>();		

		Candidato c;		

		try {		

			Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement

			// Consulta SQL con resulset
			ResultSet rs = sentencia.executeQuery("SELECT * FROM RS_CANDIDATOS");
			
			// Mientras haya registros anadimos al ArrayList
			while (rs.next()) { 			

				int idCan = rs.getInt("IDCANDIDATO");				
				String nombre = rs.getString("NOMBRE");
				String apellidos = rs.getString("APELLIDOS");
				String email = rs.getString("EMAIL");
				String fuente = rs.getString("FUENTE");
				String obs = rs.getString("OBSERVACIONES");
				String perfil = rs.getString("PERFIL");
				int telefono = rs.getInt("TELEFONO");				

				c = new Candidato(idCan,telefono,apellidos,email,nombre,fuente,perfil,obs);			

				lista_candidatos.add(c);
				System.out.println(rs.getInt("IDCANDIDATO"));
				System.out.println(lista_candidatos.size());

			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return lista_candidatos;
		
	}

}
