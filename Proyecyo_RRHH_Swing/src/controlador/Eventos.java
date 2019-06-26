package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

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
			
			ocultarPanelesProcesos(); //Ocultamos paneles de procesos
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
			
			ocultarPanelesProcesos(); //Ocultamos paneles de procesos
			
		}
		
		else if(e.getSource() == ventana.getBExportCandidato()) {
			
			Boolean ok_fichero = AccesoDB.exportarFicheroCandidatos();			

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
			
			ocultarPanelesProcesos(); //Ocultamos paneles de procesos
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
			
			ocultarPanelesProcesos(); //Ocultamos paneles de procesos
			
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
			ventana.getJTFNomCanUP().setText("");
			ventana.getJTFApellCanUP().setText("");
			ventana.getJTFTeleCanUP().setText("");
			ventana.getJTFEmailCanUP().setText("");
			ventana.getJTFPerfilCanUP().setText("");
			ventana.getJTFFuenteCanUP().setText("");
			ventana.getJTFObscanUP().setText("");
			
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
			
			ocultarPanelesProcesos(); //Ocultamos paneles de procesos
			
		}
		
		else if(e.getSource() == ventana.getBVerificarUpdateCan()) {
			
			// Recogemos los valores de IdCandidato y el nuevo valor
			String idCandidato = ventana.getJTFidUpdateCan().getText();

			if (ventana.getJTFidUpdateCan().getText().isEmpty()) {

				// Mostramos Dialog con mensaje
				JOptionPane.showMessageDialog(new JFrame(), 
						"Introduzca un Id de Candidato", 
						"Actualizar Candidato",
						JOptionPane.ERROR_MESSAGE);
			}

			// Obtenemos la lista de candidatos y la almacenamos en un ArrayList
			ArrayList<Candidato> listaCandidatos = AccesoDB.datosCandidatos(conexion);

			//Variable que indica si coincide el candidato
			int encontrado = 0;
			
			// Recorremos el ArrayList para ver si coincide el idCandidato introducido por el usuario
			for (Candidato candidato : listaCandidatos) {

				if ((candidato.getIdCandidato() == Integer.parseInt(idCandidato))) {
					ventana.getJTFNomCanUP().setText(candidato.getNombre());
					ventana.getJTFApellCanUP().setText(candidato.getApellidos());
					ventana.getJTFTeleCanUP().setText(Integer.toString(candidato.getTelefono()));
					ventana.getJTFEmailCanUP().setText(candidato.getEmail());
					ventana.getJTFPerfilCanUP().setText(candidato.getPerfil());
					ventana.getJTFFuenteCanUP().setText(candidato.getFuente());
					ventana.getJTFObscanUP().setText(candidato.getObservaciones());
					encontrado = 1; //Cambiamos el valor a 1
					return;
				}
			}
			
			//Si no encuentra el candidato
			if (encontrado == 0) {					
				// Mostramos Dialog con mensaje
				JOptionPane.showMessageDialog(new JFrame(), 
						"Error al recuperar Candidato o no existe.", 
						"Actualizar Candidato",
						JOptionPane.ERROR_MESSAGE);
			}
		}		

		else if(e.getSource() == ventana.getBUpdateFinalCandidato()) {
			
			//Creamos un arrayList de Candidato
			ArrayList<Candidato> lisCanUP = new ArrayList<Candidato>();

			//Recogemos los valores del candidato
			int idCandidato = Integer.parseInt(ventana.getJTFidUpdateCan().getText());		
			String nombreCan = ventana.getJTFNomCanUP().getText();
			String apellCan = ventana.getJTFApellCanUP().getText();
			int telCan = Integer.parseInt(ventana.getJTFTeleCanUP().getText());
			String emailCan = ventana.getJTFEmailCanUP().getText();
			String perfilCan = ventana.getJTFPerfilCanUP().getText();
			String fuenteCan = ventana.getJTFFuenteCanUP().getText();
			String obsCan = ventana.getJTFObscanUP().getText();
			
			//Lo almacenamos en un nuevo Objeto de Candidato
			Candidato c = new Candidato(idCandidato, nombreCan, apellCan, emailCan, telCan, fuenteCan, perfilCan, obsCan);
			
			//Lo añadimos al Array
			lisCanUP.add(c);
			
			//Llamamos al método actualizar Candidato
			int afectados = AccesoDB.actualizarCandidato(lisCanUP, conexion);
			
			/*
			 * if (afectados == 0) {
			 * 
			 * // Mostramos Dialog JOptionPane.showMessageDialog(new JFrame(),
			 * "Error al actualizar candidato", "Actualizar candidato",
			 * JOptionPane.ERROR_MESSAGE);
			 * 
			 * } else { //
			 * ventana.getEresulUpdateFinalCan().setText("Candidato actualizado con éxito");
			 * // Mostramos Dialog JOptionPane.showMessageDialog(new JFrame(),
			 * "Candidato actualizado con éxito", "Actualizar candidato",
			 * JOptionPane.INFORMATION_MESSAGE); actualizarTablaCandidatos(); }
			 */
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
			
			ocultarPanelesProcesos(); //Ocultamos paneles de procesos
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
		
		// TODO Auto-generated method stub
		if(e.getSource() == ventana.getBotonInicioProceso()) {
			
			//Mostramos la botonera fija y la imagen de proceso
			ventana.getBotoneraPanelProceso().setVisible(true);
			ventana.getImagenProceso().setVisible(true);
			
			ocultarPanelesCandidatos(); //Ocultamos paneles de candidatos
			
		}
		
		if(e.getSource() == ventana.getBotonListarProceso()) {
			
			//Mostramos la botonera de Candidatos
			ventana.getPanelListarProceso().setVisible(true);
			
			ocultarPanelesCandidatos(); //Ocultamos paneles de candidatos
			
			//Ocultamos el resto --> PROCESOS
			ventana.getImagenProceso().setVisible(false);
			
		}
		
		else if(e.getSource() == ventana.getBExportProceso()) {
			
			Boolean ok_fichero = AccesoDB.exportarFicheroProceso();			

			if (ok_fichero == true) {
				// Mostramos Dialog
				JOptionPane.showMessageDialog(new JFrame(), 
						"Fichero generado con éxito", 
						"Exportar Proceso",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				// Mostramos Dialog
				JOptionPane.showMessageDialog(new JFrame(), 
						"Error al generar el fichero", 
						"Exportar Proceso",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(e.getSource() == ventana.getBotonInsertarProceso()) {
			
			//Mostramos el panel de Insertar Proceso
			ventana.getPanelInsertarProceso().setVisible(true);
			
			ocultarPanelesCandidatos(); //Ocultamos paneles de candidatos
			
			//Ocultamos el resto --> PROCESOS
			ventana.getImagenProceso().setVisible(false);
			ventana.getPanelListarProceso().setVisible(false);
			
			ventana.getCalendarEntradaPro().addPropertyChangeListener(new java.beans.PropertyChangeListener() {
				public void propertyChange(java.beans.PropertyChangeEvent evt) {
					// If the 'date' property was changed...
					if ("date".equals(evt.getPropertyName())) {
						try {
							String año = Integer
									.toString(ventana.getCalendarEntradaPro().getCalendar().get(Calendar.YEAR));

							String mes = Integer
									.toString(ventana.getCalendarEntradaPro().getCalendar().get(Calendar.MONTH)+1);
							String dia = Integer
									.toString(ventana.getCalendarEntradaPro().getCalendar().get(Calendar.DAY_OF_MONTH));
							
							String fecha = año+"/"+mes+"/"+dia;
							System.out.println(fecha);							

						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			});
			
			/*
			 * Calendar cal = new GregorianCalendar();
			 * 
			 * //desde aqui empieza lo del jdatechooser String dia =
			 * Integer.toString(ventana.getCalendarEntradaPro().getCalendar().get(Calendar.
			 * DAY_OF_MONTH)); String mes =
			 * Integer.toString(ventana.getCalendarEntradaPro().getCalendar().get(Calendar.
			 * MONTH)+1); String año =
			 * Integer.toString(ventana.getCalendarEntradaPro().getCalendar().get(Calendar.
			 * YEAR)); String fecha1 = año + "-" + mes +"-"+dia;
			 * 
			 * if(ventana.getCalendarEntradaPro().getDate() != null){
			 * 
			 * 
			 * System.out.println(ventana.getCalendarEntradaPro().getDate()); }
			 * 
			 * System.out.println(fecha1);
			 */
			
			
			
			
			
		}
	}

	private void ocultarPanelesCandidatos() {
		//CANDIDATOS 
		ventana.getImagenInicio().setVisible(false);
		ventana.getPanelListarCandidato().setVisible(false);
		ventana.getPanelInsertarCandidato().setVisible(false);
		ventana.getPanelConsultarCandidato().setVisible(false);
		ventana.getSubpanelConsulCan().setVisible(false);
		ventana.getPanelUpdateCan().setVisible(false);
		ventana.getPanelEliminarCan().setVisible(false);
		ventana.getBotoneraPanelCandidato().setVisible(false);
		ventana.getImagenCandidato().setVisible(false);
	}
	
	private void ocultarPanelesProcesos() {
		//PROCESOS
		ventana.getBotoneraPanelProceso().setVisible(false);
		ventana.getImagenProceso().setVisible(false);
		ventana.getPanelListarProceso().setVisible(false);
		ventana.getPanelInsertarProceso().setVisible(false);
		
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
