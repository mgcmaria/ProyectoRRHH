package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tablas.Candidato;
import vista.Ventana;

public class Eventos implements ActionListener, MouseListener {
	
	private Ventana ventana;
	Connection conexion;
	
	public Eventos(Ventana ventana) {
		this.ventana = ventana;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub			
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//Abrimos la conexion con la BBDD
		conexion = AccesoDB.conexion();
		if (conexion == null)
		return;	
		
		if(e.getSource() == ventana.getBotonInicioCandidato()) {
			
			//Mostramos la botonera de Candidatos
			ventana.getBotoneraPanelCandidato().setVisible(true);
			ventana.getImagenCandidato().setVisible(true);
			
			//Ocultamos el resto
			ventana.getImagenInicio().setVisible(false);
			ventana.getPanelListarCandidato().setVisible(false);
			ventana.getPanelInsertarCandidato().setVisible(false);
			ventana.getPanelConsultarCandidato().setVisible(false);
			ventana.getSubpanelConsulCan().setVisible(false);
			ventana.getPanelUpdateCan().setVisible(false);
		}
		
		else if(e.getSource() == ventana.getBotonListarCandidato()) {
			
			//Mostramos la lista de Candidatos
			ventana.getPanelListarCandidato().setVisible(true);
			
			//Ocultamos el resto
			ventana.getImagenInicio().setVisible(false);
			ventana.getImagenCandidato().setVisible(false);
			ventana.getPanelInsertarCandidato().setVisible(false);
			ventana.getPanelConsultarCandidato().setVisible(false);
			ventana.getSubpanelConsulCan().setVisible(false);
			ventana.getPanelUpdateCan().setVisible(false);
			
		}
		
		else if(e.getSource() == ventana.getBExportCandidato()) {
			
			Boolean ok_fichero = AccesoDB.exportarFicheroEmpleados();			

			if (ok_fichero == true) {
				ventana.getEResulExportCan().setText("Fichero generado con éxito");
			} else {
				ventana.getEResulExportCan().setText("Error al generar fichero");
			}
		}
		
		else if(e.getSource() == ventana.getBotonInsertarCandidato()) {
			
			//Mostramos Panel de insertar Candidatos
			ventana.getPanelInsertarCandidato().setVisible(true);
			
			//Ocultamos el resto
			ventana.getImagenCandidato().setVisible(false);
			ventana.getPanelListarCandidato().setVisible(false);
			ventana.getImagenInicio().setVisible(false);
			ventana.getImagenCandidato().setVisible(false);
			ventana.getPanelConsultarCandidato().setVisible(false);
			ventana.getSubpanelConsulCan().setVisible(false);
			ventana.getPanelUpdateCan().setVisible(false);
		}
		
		else if(e.getSource() == ventana.getBinsertFinalCandidato()) {
			
			if(ventana.getJTFinsertNomCan().getText().isEmpty() || ventana.getJTFinsertApellCan().getText().isEmpty() ||
					ventana.getJTFinsertEmailCan().getText().isEmpty() || ventana.getJTFinsertTeleCan().getText().isEmpty() ||
					ventana.getJTFinsertPerfilCan().getText().isEmpty() || ventana.getJTFinsertObscan().getText().isEmpty() ||
					ventana.getJTFinsertFuenteCan().getText().isEmpty())	
			{
				ventana.geteResulInsertCan().setText("Rellene todos los campos");
			} else {
				
				//Limpiamos la etiqueta de resultado final
				ventana.geteResulInsertCan().setText("");
				
				//Creamos un ArrayList que almacenará los datos del nuevo Candidato
				ArrayList<Candidato> nuevoCandidato = new ArrayList<Candidato>();
				
				//Recogemos los datos del nuevo candidato de la ventana de Insert
				
				String nombre = ventana.getJTFinsertNomCan().getText();
				String apellidos = ventana.getJTFinsertApellCan().getText();
				String email = ventana.getJTFinsertEmailCan().getText();
				int telefono = Integer.parseInt(ventana.getJTFinsertTeleCan().getText());				
				String fuente = ventana.getJTFinsertFuenteCan().getText();
				String perfil = ventana.getJTFinsertPerfilCan().getText();
				String observaciones = ventana.getJTFinsertObscan().getText();
				
				//Creamos un nuevo Candidato
				
				Candidato can = new Candidato(email, nombre, apellidos, telefono, fuente, perfil, observaciones);
				
				//Lo añadimos al ArrayList
				nuevoCandidato.add(can);	
				
				int afectados = AccesoDB.insertarCandidato(nuevoCandidato, conexion);
				
				if(afectados == 0) {
					ventana.geteResulInsertCan().setText("Error al insertar el nuevo Candidato");
				} else {
					ventana.geteResulInsertCan().setText("Candidato insertado");	
					
					actualizarTablaCandidatos();

				}
				
			}
		}
		
		else if(e.getSource() == ventana.getBotonConsultarCandidato()) {
			
			//Limpiamos las etiquetas que tuviéramos rellenas
			ventana.geteResulConsulCan().setText("");
			ventana.getJTFResulComboConsulCan().setText("");
			
			//Mostramos Panel de consultar Candidatos
			ventana.getPanelConsultarCandidato().setVisible(true);
			
			//Ocultamos el resto
			ventana.getPanelInsertarCandidato().setVisible(false);
			ventana.getImagenCandidato().setVisible(false);
			ventana.getPanelListarCandidato().setVisible(false);
			ventana.getImagenInicio().setVisible(false);
			ventana.getImagenCandidato().setVisible(false);
			ventana.getSubpanelConsulCan().setVisible(false);
			ventana.getPanelUpdateCan().setVisible(false);
			
		}
		
		else if(e.getSource() == ventana.getBconsulFinalCandidato()) {
			
			//Limpiamos la etiqueta de resultado por si estuviera rellena con un resultado anterior
			ventana.geteResulConsulCan().setText("");
			
			//Si el campo de consulta esta vacio lo indicamos
			if(ventana.getJTFResulComboConsulCan().getText().isEmpty()) 
			{				
				ventana.geteResulConsulCan().setText("Introduzca un valor para realizar la consulta del candidato");	
			} else {
				
				//Recogemos los valores de la consulta
				String columna_consul_can = ventana.getComboConsultaCandidato().getSelectedItem().toString();
				String valor_consul_can = ventana.getJTFResulComboConsulCan().getText();
				
				//Hacemos la consulta almacenando el resultado en un nuevo objeto Candidato
				//Pasaremos como argumentos los valores de la consulta
				
				ArrayList<Candidato> listaCandidatos = AccesoDB.datosCandidatos(conexion);
				
				for (Candidato candidato : listaCandidatos) {
					
					if((columna_consul_can == "IdCandidato" && candidato.getIdCandidato() == Integer.parseInt(valor_consul_can)) ||
					   (columna_consul_can == "nombre" && candidato.getNombre().equalsIgnoreCase(valor_consul_can)) ||
					   (columna_consul_can == "apellidos" && candidato.getApellidos().equalsIgnoreCase(valor_consul_can))) {
						
						//Mostramos el subpanel con el resultado de la consulta
						ventana.getSubpanelConsulCan().setVisible(true);
						
						//Mostramos en cada etiqueta el resultado del candidato consultado
						ventana.getEresulConsultaIdCan().setText(Integer.toString(candidato.getIdCandidato()));
						ventana.getEresulConsultaNombreCan().setText(candidato.getNombre());
						ventana.getEresulConsultaApelCan().setText(candidato.getApellidos());
						ventana.getEresulConsultaEmailCan().setText(candidato.getEmail());
						ventana.getEresulConsultaTelCan().setText(Integer.toString(candidato.getTelefono()));
						ventana.getEresulConsultaFuenteCan().setText(candidato.getFuente());
						ventana.getEresulConsultaPerfilCan().setText(candidato.getPerfil());
						ventana.getEresulConsultaObsCan().setText(candidato.getObservaciones());
						
						ventana.geteResulConsulCan().setText("");
						
						return;
						
					} else {
						ventana.geteResulConsulCan().setText("Error al consultar candidato / No existe");
						ventana.getSubpanelConsulCan().setVisible(false);
					}
				}				
			}	
		}
		
		else if(e.getSource() == ventana.getBotonUpdateCandidato()) {
			
			//Limpiamos etiquetas y ocultamos boton actualizar
			ventana.getJTFidUpdateCan().setText("");
			ventana.getJTFUpdateNewdataCan().setText("");
			ventana.getEpreguntaUpdateCan().setVisible(false);
			ventana.getBUpdateFinalCandidato().setVisible(false);
			
			//Mostramos Panel de Actualizar Candidatos
			ventana.getPanelUpdateCan().setVisible(true);
			
			//Ocultamos el resto
			ventana.getImagenCandidato().setVisible(false);
			ventana.getPanelListarCandidato().setVisible(false);
			ventana.getImagenInicio().setVisible(false);
			ventana.getImagenCandidato().setVisible(false);
			ventana.getPanelConsultarCandidato().setVisible(false);
			ventana.getSubpanelConsulCan().setVisible(false);
			ventana.getPanelInsertarCandidato().setVisible(false);
			
		}
		
		else if(e.getSource() == ventana.getBVerificarUpdateCan()) {
			
			if(ventana.getJTFidUpdateCan().getText().isEmpty() || ventana.getJTFUpdateNewdataCan().getText().isEmpty()) {
				
				ventana.getEresulVerUpdateCan().setText("Introduzca todos los camopos");
				
			} else {
				
				//Recogemos los valores de IdCandidato y el nuevo valor
				String idCandidato = ventana.getJTFidUpdateCan().getText();
				String campo = ventana.getComboUpdateCandidato().getSelectedItem().toString();
				String nuevoValor = ventana.getJTFUpdateNewdataCan().getText();
				
				//Hacemos la consulta almacenando el resultado en un nuevo objeto Candidato
				//Pasaremos como argumentos los valores de la consulta
				
				ArrayList<Candidato> listaCandidatos = AccesoDB.datosCandidatos(conexion);
				
				for (Candidato candidato : listaCandidatos) {
					
					if((candidato.getIdCandidato() == Integer.parseInt(idCandidato))) {
						
						ventana.getEresulVerUpdateCan().setText("El candidato: "+ candidato.getNombre()
						+", " + candidato.getApellidos()+", modificará el campo "+ campo + " por: "+ nuevoValor);
						ventana.getEpreguntaUpdateCan().setVisible(true);
						ventana.getBUpdateFinalCandidato().setVisible(true);
						
						return;
						
					} else {
						ventana.getEresulVerUpdateCan().setText("Error al seleccionar candidato / No existe");
					}				
				}
			}
		}
		
		else if(e.getSource() == ventana.getBUpdateFinalCandidato()) {
			
			//Recogemos los valores de IdCandidato y el nuevo valor
			String idCandidato = ventana.getJTFidUpdateCan().getText();
			String campo = ventana.getComboUpdateCandidato().getSelectedItem().toString();
			String nuevoValor = ventana.getJTFUpdateNewdataCan().getText();
			
			int afectados = AccesoDB.actualizarCandidato( idCandidato, campo, nuevoValor, conexion);			

			if(afectados == 0) {

				ventana.getEresulUpdateFinalCan().setText("Error al actualizar Candidato");

			} else {

				ventana.getEresulUpdateFinalCan().setText("Candidato actualizado con éxito");
				actualizarTablaCandidatos();

			}
			
		}
	}

	private void actualizarTablaCandidatos() {
		//ACTUALIZAR LA Tabla con la información de los candidatos
		
		String titulosCandidatos[] = { "IdCandidato", "Nombre", "Apellidos", "e-mail", "Telefono", "Perfil", "Fuente",
		"Observaciones" };
		String infoCandidatos[][] = AccesoDB.obtenerMatrizCandidatos();
		
		TableModel modelo = new DefaultTableModel(infoCandidatos, titulosCandidatos);
		
		ventana.getTablaCandidatos().setModel(modelo);
		AccesoDB.obtenerMatrizCandidatos();
		((AbstractTableModel) modelo).fireTableDataChanged();
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}
