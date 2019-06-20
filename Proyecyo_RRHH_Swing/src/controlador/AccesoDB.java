package controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tablas.Candidato;
import tablas.Proceso;

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
			return null;
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

	public static ArrayList<Candidato> datosCandidatos(Connection conexion) {	
		
		ArrayList<Candidato> lista_candidatos = new ArrayList<Candidato>();
		
		Candidato c;	

		try {
			
			Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement
			// Consulta SQL con resulset
			ResultSet rs = sentencia.executeQuery("SELECT * FROM RS_CANDIDATOS order by idcandidato");		

			// Mientras haya registros anadimos al ArrayList

			while (rs.next()) {
				
				int idCan = rs.getInt("IDCANDIDATO");
				String nombre = rs.getString("NOMBRE");
				String apellidos = rs.getString("APELLIDOS");
				String email = rs.getString("EMAIL");
				int telefono = rs.getInt("TELEFONO");
				String fuente = rs.getString("FUENTE");
				String perfil = rs.getString("PERFIL");
				String obs = rs.getString("OBSERVACIONES");				

				c = new Candidato(idCan,nombre,apellidos,email,telefono,fuente,perfil,obs);	

				lista_candidatos.add(c);
			}
		} catch (NullPointerException e) {
			e.getMessage();
			//Mostramos Dialog
			JOptionPane.showMessageDialog(new JFrame(), 
					"No está conectado al sistema de Acens",
					"Conexión",
					JOptionPane.ERROR_MESSAGE);	
			System.exit(0);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
	
		}
		return lista_candidatos; 		
		
	}

	public static int insertarCandidato(ArrayList<Candidato> nuevoCandidato, Connection conexion) {
		// TODO Auto-generated method stub
		
		//Variable que vamos a retornar para comprobar que todo ha salido OK
		int afectados = 0;
		
		try {
			//Almacenamos en un String la Sentencia SQL
			String sql = "INSERT INTO RS_CANDIDATOS (NOMBRE, APELLIDOS, EMAIL, TELEFONO, "
					+ "FUENTE, PERFIL, OBSERVACIONES) VALUES (?, ?, ?, ?, ?, ?, ?)";			
			
			String nombre = null;
			String apellidos = null;
			String email = null;
			int telefono = 0;
			String fuente = null;
			String perfil = null;	
			String obs = null ;

			for (Candidato can : nuevoCandidato) {
				
				nombre = can.getNombre();
				apellidos = can.getApellidos();
				email = can.getEmail();
				telefono = can.getTelefono();				
				fuente = can.getFuente();
				perfil = can.getPerfil();
				obs = can.getObservaciones();
			}				

			//Con PreparedStatement recogemos los valores del Array que hemos recogido de la ventana

			PreparedStatement sentencia;
			sentencia = conexion.prepareStatement(sql);

			sentencia.setString(1, nombre);
			sentencia.setString(2, apellidos);
			sentencia.setString(3, email);
			sentencia.setInt(4, telefono);
			sentencia.setString(5, fuente);
			sentencia.setString(6, perfil);		
			sentencia.setString(7, obs);

			afectados = sentencia.executeUpdate(); //Ejecutamos la insercion

		} catch (SQLException e) {
			e.getMessage();
		}
		return afectados;	
	}

	public static Candidato consultarCandidato(String columna_consul_can, String valor_consul_can,
			Connection conexion) {
		
		Candidato c = null;		
		Statement sentencia;
		
		try {
			sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery("SELECT * FROM RS_CANDIDATOS WHERE"
					+ columna_consul_can+ "LIKE '%" + valor_consul_can +"%'"); //Consulta SQL
			
			while (rs.next()) {
		
				System.out.println("Campos actuales del candidato"+ valor_consul_can);	
				int idCandidato = rs.getInt("IDCANDIDATO");
				String nombre = rs.getString("NOMBRE");
				String apellidos= rs.getString("APELLIDOS");
				String email = rs.getString("EMAIL");
				int telefono = rs.getInt("TELEFONO");
				String fuente = rs.getString("FUENTE");				
				String perfil = rs.getString("PERFIL");
				String observaciones = rs.getString("OBSERVACIONES");
				
				c = new Candidato(idCandidato, email, nombre, apellidos, telefono, fuente, perfil, observaciones);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return c;
	}

	public static Boolean exportarFicheroCandidatos() {
		
		File f = new File("candidatos.csv");		

		Connection conexion = AccesoDB.conexion();		

		ArrayList<Candidato> lista_candidatos = datosCandidatos(conexion);		

		try {

			FileWriter ficheroEmp = new FileWriter(f);			

			//Escribimos los títulos de los campos de las columnas
			ficheroEmp.write("IDCandidato,Nombre,Apellidos,Email,Telefono,Perfil,Fuente,Observaciones");
			ficheroEmp.write("\n"); // Salto línea			

			for (Candidato c : lista_candidatos) {		

				ficheroEmp.write(Integer.toString(c.getIdCandidato()));
				ficheroEmp.write(",");
				ficheroEmp.write(c.getNombre());
				ficheroEmp.write(",");
				ficheroEmp.write(c.getApellidos());
				ficheroEmp.write(",");
				ficheroEmp.write(c.getEmail());
				ficheroEmp.write(",");
				ficheroEmp.write(Integer.toString(c.getTelefono()));
				ficheroEmp.write(",");
				ficheroEmp.write(c.getPerfil());
				ficheroEmp.write(",");
				ficheroEmp.write(c.getFuente());
				ficheroEmp.write(",");
				ficheroEmp.write(c.getObservaciones());
				ficheroEmp.write("\n");
			}
			ficheroEmp.close();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static int actualizarCandidato(String idCandidato, String campo, String nuevoValor, Connection conexion) {

		int afectados = 0;		

		// Almacenamos en un String la Sentencia SQL

		String sql = "UPDATE RS_CANDIDATOS SET " + campo +"= '"+ nuevoValor +"' WHERE IDCANDIDATO LIKE '%" + Integer.parseInt(idCandidato) + "%'";		
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			afectados = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return afectados;
	}

	public static int eliminarCandidato(String idCanEli, Connection conexion) {
		// TODO Auto-generated method stub
		
		int idCandidato = Integer.parseInt(idCanEli);
		int afectados = 0;
		
		try {
			
			//Sentencia SQL
			String sql = "DELETE FROM rs_candidatos WHERE idcandidato=?";
			
			//Con PreparedStatement recogemos los valores introducidos			
			PreparedStatement sentencia;
			sentencia = conexion.prepareStatement(sql);
			
			sentencia.setInt(1, idCandidato);
						
			afectados = sentencia.executeUpdate(); //Ejecutamos el borrado
			
		} catch (SQLException e) {
			e.getMessage();
		}
		return afectados;
		
	}

	public static String[][] obtenerMatrizProcesos() {

		Connection conexion = conexion();	
		
		ArrayList<Proceso> listaProcesos = datosProcesos(conexion);

		String matrizInfo[][] = new String[listaProcesos.size()][9];		

		for (int i = 0; i < listaProcesos.size(); i++) {

			matrizInfo[i][0] = listaProcesos.get(i).getIdProceso()+"";
			matrizInfo[i][1] = listaProcesos.get(i).getNombrePro()+"";
			matrizInfo[i][2] = listaProcesos.get(i).getClientePro()+"";
			matrizInfo[i][3] = listaProcesos.get(i).getResponsablePro()+"";
			matrizInfo[i][4] = listaProcesos.get(i).getDireccionPro()+"";
			matrizInfo[i][5] = listaProcesos.get(i).getDepartPro()+"";
			matrizInfo[i][6] = listaProcesos.get(i).getFechaEntradaPro()+"";
			matrizInfo[i][7] = listaProcesos.get(i).getEstadoPro()+"";
			matrizInfo[i][8] = listaProcesos.get(i).getDesTrabajoPro()+"";
		}		

		return matrizInfo;		
	}

	private static ArrayList<Proceso> datosProcesos(Connection conexion) {
		
		ArrayList<Proceso> lista_procesos = new ArrayList<Proceso>();
		
		Proceso p;	

		try {
			
			Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement
			// Consulta SQL con resulset
			ResultSet rs = sentencia.executeQuery("SELECT * FROM RS_PROCESOS_SELECCION order by idproceso");		

			// Mientras haya registros anadimos al ArrayList

			while (rs.next()) {
				
				int idProceso = rs.getInt("IDPROCESO");
				String nombrePro = rs.getString("NOMBRE");
				String clientePro = rs.getString("CLIENTE");
				String responsablePro = rs.getString("RESPONSABLE");
				String direccionPro = rs.getString("DIRECCION");
				String departPro = rs.getString("DEPARTAMENTO");
				Date fechaEntradaPro = rs.getDate("FECHAENTRADA");
				String estadoPro = rs.getString("ESTADOPROCESO");	
				String desTrabajoPro = rs.getString("DESCRIPCIONTRABAJO");

				p = new Proceso(idProceso, nombrePro, clientePro, responsablePro, direccionPro, departPro, 
						fechaEntradaPro, estadoPro, desTrabajoPro);	

				lista_procesos.add(p);
			}
		} catch (NullPointerException e) {
			e.getMessage();
			//Mostramos Dialog
			JOptionPane.showMessageDialog(new JFrame(), 
					"No está conectado al sistema de Acens",
					"Conexión",
					JOptionPane.ERROR_MESSAGE);	
			System.exit(0);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
	
		}
		return lista_procesos; 	
	}

	public static Boolean exportarFicheroProceso() {
		// TODO Auto-generated method stub
		
		File f = new File("procesos.csv");		

		Connection conexion = AccesoDB.conexion();		

		ArrayList<Proceso> lista_procesos = datosProcesos(conexion);		

		try {

			FileWriter ficheroPro = new FileWriter(f);			

			//Escribimos los títulos de los campos de las columnas
			ficheroPro.write("IDProceso,Nombre_Proceso,Cliente,Responsable,Direccion,Departamento,Fecha_Entrada,"
					+ "Estado_Proceso,Descripcion_Trabajo");
			ficheroPro.write("\n"); // Salto línea			

			for (Proceso p : lista_procesos) {		

				ficheroPro.write(Integer.toString(p.getIdProceso()));
				ficheroPro.write(",");
				ficheroPro.write(p.getNombrePro());
				ficheroPro.write(",");
				ficheroPro.write(p.getClientePro());
				ficheroPro.write(",");
				ficheroPro.write(p.getResponsablePro());
				ficheroPro.write(",");
				ficheroPro.write(p.getDireccionPro());
				ficheroPro.write(",");
				ficheroPro.write(p.getDepartPro());
				ficheroPro.write(",");
				ficheroPro.write(String.valueOf(p.getFechaEntradaPro()));
				ficheroPro.write(",");
				ficheroPro.write(p.getEstadoPro());
				ficheroPro.write(",");
				ficheroPro.write(p.getDesTrabajoPro());
				ficheroPro.write("\n");
			}
			ficheroPro.close();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
