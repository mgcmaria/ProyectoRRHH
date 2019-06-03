package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

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
		}
		
		else if(e.getSource() == ventana.getBotonListarCandidato()) {
			
			//Mostramos la lista de Candidatos
			ventana.getPanelListarCandidato().setVisible(true);
			
			//Ocultamos el resto
			ventana.getImagenCandidato().setVisible(false);
			ventana.getPanelInsertarCandidato().setVisible(false);
			
		}
		
		else if(e.getSource() == ventana.getBotonInsertarCandidato()) {
			
			//Mostramos Panel de insertar Candidatos
			ventana.getPanelInsertarCandidato().setVisible(true);
			
			//Ocultamos el resto
			ventana.getImagenCandidato().setVisible(false);
			ventana.getPanelListarCandidato().setVisible(false);
			ventana.getImagenInicio().setVisible(false);
			ventana.getImagenCandidato().setVisible(false);
		}
		
		else if(e.getSource() == ventana.getBinsertFinalCandidato()) {
			
			if(ventana.getJTFinsertNomCan().getText().isEmpty() || ventana.getJTFinsertApellCan().getText().isEmpty() ||
					ventana.getJTFinsertEmailCan().getText().isEmpty() || ventana.getJTFinsertTeleCan().getText().isEmpty() ||
					ventana.getJTFinsertPerfilCan().getText().isEmpty() || ventana.getJTFinsertObscan().getText().isEmpty() ||
					ventana.getJTFinsertFuenteCan().getText().isEmpty())	
			{
				ventana.geteResulInsertCan().setText("Inserte todos los campos");
			}
		}
		
	}
	
	//PARA PROBAR A ACTUALIZAR LA TABLA
	/*
	 * private void Clear_Table1() { // Limpiar rows jtable for (int i = 0; i <
	 * jTable1.getRowCount(); i++) { modelo4.removeRow(i); i -= 1; } }
	 */

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
