package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
			ventana.getPanelEliminarCan().setVisible(false);
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
			ventana.getPanelEliminarCan().setVisible(false);
			
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
			
			//Limpiamos las etiquetas
			ventana.getJTFinsertNomCan().setText(""); 
			ventana.getJTFinsertApellCan().setText("");
			ventana.getJTFinsertEmailCan().setText(""); 
			ventana.getJTFinsertTeleCan().setText("");
			ventana.getJTFinsertPerfilCan().setText("");
			ventana.getJTFinsertObscan().setText(""); 
			ventana.getJTFinsertFuenteCan().setText("");
			ventana.geteResulInsertCan().setText("");
			ventana.getJTFinsertNomCan().requestFocus(); // Ponemos el foco en el nombre
			
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
			ventana.getPanelEliminarCan().setVisible(false);
		}
		
		else if(e.getSource() == ventana.getBinsertFinalCandidato()) {
			
			if(ventana.getJTFinsertNomCan().getText().isEmpty() || ventana.getJTFinsertApellCan().getText().isEmpty() ||
					ventana.getJTFinsertEmailCan().getText().isEmpty() || ventana.getJTFinsertTeleCan().getText().isEmpty() ||
					ventana.getJTFinsertPerfilCan().getText().isEmpty() || ventana.getJTFinsertObscan().getText().isEmpty() ||
					ventana.getJTFinsertFuenteCan().getText().isEmpty())	
			{
				//Mostramos Dialog con mensaje
				JOptionPane.showMessageDialog(new JFrame(), 
						"Rellene todos los campos",
						"Insertar Candidato",
						JOptionPane.ERROR_MESSAGE);		
				//Comprobamos que el telefono no tenga más de 9 carateres
			} else if(ventana.getJTFinsertTeleCan().getText().length() > 9) {
					//Mostramos Dialog
					JOptionPane.showMessageDialog(new JFrame(), 
							"El telefono no puede superar los 9 caracteres",
							"Insertar candidato",
							JOptionPane.ERROR_MESSAGE);	
					
			//Comprobamos ademas que el telefono contega solo numeros
			} else if (!ventana.getJTFinsertTeleCan().getText().matches("[0-9]*")){

				//Mostramos Dialog
				JOptionPane.showMessageDialog(new JFrame(), 
						"El teléfono sólo puede ser numérico",
						"Insertar candidato",
						JOptionPane.ERROR_MESSAGE);
				
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
				
				Candidato can = new Candidato(nombre, apellidos, email, telefono, fuente, perfil, observaciones);
				
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
			ventana.getPanelEliminarCan().setVisible(false);
			
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
			ventana.getEresulVerUpdateCan().setText("");
			ventana.getEresulUpdateFinalCan().setText("");
			
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
			ventana.getPanelEliminarCan().setVisible(false);
			
		}
		
		else if(e.getSource() == ventana.getBVerificarUpdateCan()) {
			
			//Recogemos los valores de IdCandidato y el nuevo valor
			String idCandidato = ventana.getJTFidUpdateCan().getText();
			String campo = ventana.getComboUpdateCandidato().getSelectedItem().toString();
			String nuevoValor = ventana.getJTFUpdateNewdataCan().getText();	
			
			if(ventana.getJTFidUpdateCan().getText().isEmpty() || ventana.getJTFUpdateNewdataCan().getText().isEmpty()) {

				//Mostramos Dialog con mensaje
				JOptionPane.showMessageDialog(new JFrame(), 
						"Rellene todos los campos",
						"Actualizar Candidato",
						JOptionPane.ERROR_MESSAGE);		
				
			//Comprobamos que el telefono no tenga más de 9 carateres
			} else if(campo.contains("TELEFONO") && nuevoValor.length() > 9) {
					//Mostramos Dialog
					JOptionPane.showMessageDialog(new JFrame(), 
							"El telefono no puede superar los 9 caracteres",
							"Actualizar candidato",
							JOptionPane.ERROR_MESSAGE);	
					
			//Comprobamos ademas que el telefono contega solo numeros
			} else if (campo.contains("TELEFONO") && !nuevoValor.matches("[0-9]*")){

				//Mostramos Dialog
				JOptionPane.showMessageDialog(new JFrame(), 
						"El teléfono sólo puede ser numérico",
						"Actualizar candidato",
						JOptionPane.ERROR_MESSAGE);
				
			} else { //Si pasa las validaciones
				
				//Obtenemos la lista de candidatos y la almacenamos en un ArrayList
				ArrayList<Candidato> listaCandidatos = AccesoDB.datosCandidatos(conexion);				

				//Recorremos el ArrayList para ver si coincide el idCandidato introducido por el usuario
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
				
			// Los pasamos como parametros en el método actualizar
			int afectados = AccesoDB.actualizarCandidato(idCandidato, campo, nuevoValor, conexion);

			if (afectados == 0) {

				// Mostramos Dialog
				JOptionPane.showMessageDialog(new JFrame(), 
						"Error al actualizar candidato", 
						"Actualizar candidato",
						JOptionPane.ERROR_MESSAGE);

			} else {
				// ventana.getEresulUpdateFinalCan().setText("Candidato actualizado con éxito");
				// Mostramos Dialog
				JOptionPane.showMessageDialog(new JFrame(), 
						"Candidato actualizado con éxito", 
						"Actualizar candidato",
						JOptionPane.INFORMATION_MESSAGE);
				actualizarTablaCandidatos();
			}
		} 	
		
		else if(e.getSource() == ventana.getBotonDeleteCandidato()) {
			
			//Limpiamos las etiquetas de IdCandidato y ocultamos Boton definitivo
			ventana.getJTFidEliminarCan().setText("");
			ventana.getEtiResulEliCan().setText("");
			ventana.getBDeleteFinalCan().setVisible(false);
			
			//Mostramos el panel de Eliminar Candidato
			ventana.getPanelEliminarCan().setVisible(true);
			
			//Ocultamos el resto
			ventana.getPanelUpdateCan().setVisible(false);
			ventana.getImagenCandidato().setVisible(false);
			ventana.getPanelListarCandidato().setVisible(false);
			ventana.getImagenInicio().setVisible(false);
			ventana.getImagenCandidato().setVisible(false);
			ventana.getPanelConsultarCandidato().setVisible(false);
			ventana.getSubpanelConsulCan().setVisible(false);
			ventana.getPanelInsertarCandidato().setVisible(false);
		}
		
		else if(e.getSource() == ventana.getBVerificarDeleteCan()) {
			
			//Obtenemos la lista de candidatos y la almacenamos en un ArrayList
			ArrayList<Candidato> listaCandidatos = AccesoDB.datosCandidatos(conexion);	
						
			//Recogemos los valores de IdCandidato
			String idCanEli = ventana.getJTFidEliminarCan().getText();
			
			if(ventana.getJTFidEliminarCan().getText().isEmpty()) {
				
				// Mostramos Dialog
				JOptionPane.showMessageDialog(new JFrame(), 
						"Introduzca un IdCandidato", 
						"Eliminar candidato",
						JOptionPane.ERROR_MESSAGE);
			} else {
				
				for (Candidato candidato : listaCandidatos) {
					
					if(candidato.getIdCandidato() == Integer.parseInt(idCanEli)) {
						
						ventana.getEtiResulEliCan().setText("El candidado: " + candidato.getNombre() +
								" " + candidato.getApellidos() + ", será eliminado");
						
						//Mostramos el botón de eliminar el candidato
						ventana.getBDeleteFinalCan().setVisible(true);
						//Detenemos la busqueda
						return;
						
					} else {
						
						ventana.getEtiResulEliCan().setText("Error al consultar candidato / No existe");
					}
				}
			}				
		}
		
		else if(e.getSource() == ventana.getBDeleteFinalCan()) {
			
			//Obtenemos la lista de candidatos y la almacenamos en un ArrayList
			ArrayList<Candidato> listaCandidatos = AccesoDB.datosCandidatos(conexion);	
						
			//Recogemos los valores de IdCandidato
			String idCanEli = ventana.getJTFidEliminarCan().getText();
			
			for (Candidato candidato : listaCandidatos) {
				
				if(candidato.getIdCandidato() == Integer.parseInt(idCanEli)) {
					
					int confirmado = JOptionPane.showConfirmDialog(
							  ventana.getPanelEliminarCan(), "El candidato: "+candidato.getNombre() +
							  ", "+ candidato.getApellidos() +
							  ", será eliminado. ¿Estás seguro que deseas eliminar al candidato?");
							  
							  if (JOptionPane.OK_OPTION == confirmado) {
								  
								 int afectados =  AccesoDB.eliminarCandidato(idCanEli, conexion);
								 
								 if (afectados == 0) {

										// Mostramos Dialog
										JOptionPane.showMessageDialog(new JFrame(), 
												"Error al eliminar candidato", 
												"Eliminar candidato",
												JOptionPane.ERROR_MESSAGE);

									} else {
										
										// Mostramos Dialog
										JOptionPane.showMessageDialog(new JFrame(), 
												"Candidato eliminado con éxito", 
												"Eliminar candidato",
												JOptionPane.INFORMATION_MESSAGE);
										actualizarTablaCandidatos();
									}
								  
							  }	else {
								  ventana.getJTFidEliminarCan().setText("");
							  }									
				} 
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
