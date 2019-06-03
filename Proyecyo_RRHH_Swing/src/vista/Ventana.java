package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.AccesoDB;
import controlador.Eventos;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//DECLARACION DE COMPONENTES - ATRIBUTOS DE LA CLASE (PRIVADAS)	
	// Atributos de pantalla panel INICIO Izquierdo ---FIJO---

	private JLabel imagenInicio;
	private JButton botonInicioCandidato, botonInicioProceso;
	private JPanel inicioFijo,tiraPanelInicio;			

	// Atributos de PANEL CANDIDATOS BOTONERA ---FIJO---
	private JPanel botoneraPanelCandidato;
	private JButton botonListarCandidato,botonInsertarCandidato,botonConsultarCandidato,
	botonUpdateCandidato,botonDeleteCandidato;
	private JLabel imagenCandidato;
	
	// Atributos de PANEL CANDIDATOS ---VA CAMBIANDO SEGÚN EL BOTÓN---
	private JPanel panelListarCandidato;
	private JScrollPane barraCandidatos;
	private JTable tablaCandidatos;
	
	//Colores
	Color colorBlanco = Color.white;
	Color colorGris = Color.gray;
	Color colorNegro = Color.black;
	Color verdeAcens = new Color(50, 205, 50);

	//CONSTRUCTOR
	public Ventana() {

		setSize(1200, 700); // Tamano de la Ventana
		setLocationRelativeTo(null); // Eliminamos la autolocalización
		setTitle("Proyecto RRHH"); // Titulo
		setLayout(null); // Lo colocamos nosotros
		setResizable(false); // Desactivamos bot�n maximizar
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes\\app.jpg")); // Imagen de la App
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Para programa cuendo cerramos
		incializarComponentes(); // Metodo que inicializa los componentes
		setVisible(true); // Visible

	}
	
	//FUNCION QUE INICIALIZA LOS COMPONENTES

	private void incializarComponentes() {		

		getContentPane().setBackground(colorBlanco); //Damos un color de fondo 				

		//Panel INICIO Izquierdo --- Botones Candidato, Procesos
		//Botones LOGOUT, EXIT		

		inicioFijo = new JPanel();
		inicioFijo.setBackground(colorGris);
		inicioFijo.setBounds(0, 0, 240, 700);
		inicioFijo.setLayout(null);
		add(inicioFijo);
		
		Image imgInicio = new ImageIcon("imagenes\\recursos_humanos.jpg").getImage();
		imagenInicio = new JLabel(new ImageIcon(imgInicio.getScaledInstance(950, 680, Image.SCALE_SMOOTH)));
		//las coordenadas del final han de coincidir con las anteriores
		imagenInicio.setBounds(245, 0, 950, 680);
		add(imagenInicio);
		
		tiraPanelInicio = new JPanel();
		tiraPanelInicio.setBounds(240, 0, 10, 700);
		tiraPanelInicio.setBackground(colorNegro);
		add(tiraPanelInicio);
		
		botonInicioCandidato = new JButton("CANDIDATO");
		botonInicioCandidato.setBounds(40,40,160,80);
		botonInicioCandidato.setBackground(colorNegro);
		botonInicioCandidato.setBorder(null);
		botonInicioCandidato.setForeground(verdeAcens);
		botonInicioCandidato.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		inicioFijo.add(botonInicioCandidato);
		
		botonInicioProceso = new JButton("PROCESO");
		botonInicioProceso.setBounds(40,150,160,80);
		botonInicioProceso.setBackground(colorNegro);
		botonInicioProceso.setBorder(null);
		botonInicioProceso.setForeground(verdeAcens);
		botonInicioProceso.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		inicioFijo.add(botonInicioProceso);
		
		//PANEL BOTONERA CANDIDATO
		
		botoneraPanelCandidato = new JPanel();
		botoneraPanelCandidato.setBackground(new Color(220, 220, 220));
		botoneraPanelCandidato.setBounds(245, 0, 950, 100);
		botoneraPanelCandidato.setLayout(null);
		add(botoneraPanelCandidato);
		botoneraPanelCandidato.setVisible(false);
		
		botonListarCandidato = new JButton("LISTAR CANDIDATOS");
		botonListarCandidato.setBounds(20,20,160,60);
		botonListarCandidato.setBackground(colorNegro);
		botonListarCandidato.setBorder(null);
		botonListarCandidato.setForeground(verdeAcens);
		botonListarCandidato.setFont(new Font("Segoe UI",Font.BOLD,12));//Damos formato al contenido
		botoneraPanelCandidato.add(botonListarCandidato);
		
		botonInsertarCandidato = new JButton("INSERTAR CANDIDATO");
		botonInsertarCandidato.setBounds(200,20,160,60);
		botonInsertarCandidato.setBackground(colorNegro);
		botonInsertarCandidato.setBorder(null);
		botonInsertarCandidato.setForeground(verdeAcens);
		botonInsertarCandidato.setFont(new Font("Segoe UI",Font.BOLD,12));//Damos formato al contenido
		botoneraPanelCandidato.add(botonInsertarCandidato);
		
		botonConsultarCandidato = new JButton("CONSULTAR CANDIDATO");
		botonConsultarCandidato.setBounds(380,20,160,60);
		botonConsultarCandidato.setBackground(colorNegro);
		botonConsultarCandidato.setBorder(null);
		botonConsultarCandidato.setForeground(verdeAcens);
		botonConsultarCandidato.setFont(new Font("Segoe UI",Font.BOLD,12));//Damos formato al contenido
		botoneraPanelCandidato.add(botonConsultarCandidato);
		
		botonUpdateCandidato = new JButton("ACTUALIZAR CANDIDATO");
		botonUpdateCandidato.setBounds(560,20,160,60);
		botonUpdateCandidato.setBackground(colorNegro);
		botonUpdateCandidato.setBorder(null);
		botonUpdateCandidato.setForeground(verdeAcens);
		botonUpdateCandidato.setFont(new Font("Segoe UI",Font.BOLD,12));//Damos formato al contenido
		botoneraPanelCandidato.add(botonUpdateCandidato);
		
		botonDeleteCandidato = new JButton("ELIMINAR CANDIDATO");
		botonDeleteCandidato.setBounds(740,20,160,60);
		botonDeleteCandidato.setBackground(colorNegro);
		botonDeleteCandidato.setBorder(null);
		botonDeleteCandidato.setForeground(verdeAcens);
		botonDeleteCandidato.setFont(new Font("Segoe UI",Font.BOLD,12));//Damos formato al contenido
		botoneraPanelCandidato.add(botonDeleteCandidato);
		
		Image imgPanelCandidato = new ImageIcon("imagenes\\candidato.jpg").getImage();
		imagenCandidato = new JLabel(new ImageIcon(imgPanelCandidato.getScaledInstance(700, 400, Image.SCALE_SMOOTH)));
		//las coordenadas del final han de coincidir con las anteriores
		imagenCandidato.setBounds(350, 150, 700, 400);
		add(imagenCandidato);
		imagenCandidato.setVisible(false);
		
		//PANEL LISTAR CANDIDATOS
		
		panelListarCandidato = new JPanel();
		panelListarCandidato.setBackground(Color.magenta);
		panelListarCandidato.setBounds(260, 110, 925, 550);
		panelListarCandidato.setLayout(null);
		add(panelListarCandidato);
		panelListarCandidato.setVisible(false);
		
		//Creación de la tabla Candidatos
		
		barraCandidatos = new JScrollPane();
		barraCandidatos.setBounds(20, 20, 850, 190);
		panelListarCandidato.add(barraCandidatos);
		
		String titulosEmpleados[] = {"IdCandidato","Nombre","Apellidos","e-mail", "Telefono","Perfil",
				"Fuente","Observaciones"};
		String infoCandidatos[][] = AccesoDB.obtenerMatrizCandidatos();
		
		TableModel model = new DefaultTableModel(infoCandidatos, titulosEmpleados);
		tablaCandidatos = new JTable();
		tablaCandidatos.setModel(model);
		((AbstractTableModel) model).fireTableDataChanged();

		tablaCandidatos.getColumnModel().getColumn(7).setPreferredWidth(200);

		barraCandidatos.setViewportView(tablaCandidatos);
	}
	
	//METODO PARA PONER A LA ESCUCHA LOS EVENTOS	

		public void Eventos (Eventos manejador) {

			botonInicioCandidato.addMouseListener(manejador);
			botonListarCandidato.addMouseListener(manejador);
			botonConsultarCandidato.addMouseListener(manejador);

		}

		public JLabel getImagenInicio() {
			return imagenInicio;
		}

		public void setImagenInicio(JLabel imagenInicio) {
			this.imagenInicio = imagenInicio;
		}

		public JButton getBotonInicioCandidato() {
			return botonInicioCandidato;
		}

		public void setBotonInicioCandidato(JButton botonInicioCandidato) {
			this.botonInicioCandidato = botonInicioCandidato;
		}

		public JButton getBotonInicioProceso() {
			return botonInicioProceso;
		}

		public void setBotonInicioProceso(JButton botonInicioProceso) {
			this.botonInicioProceso = botonInicioProceso;
		}

		public JPanel getInicioFijo() {
			return inicioFijo;
		}

		public void setInicioFijo(JPanel inicioFijo) {
			this.inicioFijo = inicioFijo;
		}

		public JPanel getTiraPanelInicio() {
			return tiraPanelInicio;
		}

		public void setTiraPanelInicio(JPanel tiraPanelInicio) {
			this.tiraPanelInicio = tiraPanelInicio;
		}

		public JPanel getBotoneraPanelCandidato() {
			return botoneraPanelCandidato;
		}

		public void setBotoneraPanelCandidato(JPanel botoneraPanelCandidato) {
			this.botoneraPanelCandidato = botoneraPanelCandidato;
		}

		public JButton getBotonListarCandidato() {
			return botonListarCandidato;
		}

		public void setBotonListarCandidato(JButton botonListarCandidato) {
			this.botonListarCandidato = botonListarCandidato;
		}

		public JButton getBotonInsertarCandidato() {
			return botonInsertarCandidato;
		}

		public void setBotonInsertarCandidato(JButton botonInsertarCandidato) {
			this.botonInsertarCandidato = botonInsertarCandidato;
		}

		public JButton getBotonConsultarCandidato() {
			return botonConsultarCandidato;
		}

		public void setBotonConsultarCandidato(JButton botonConsultarCandidato) {
			this.botonConsultarCandidato = botonConsultarCandidato;
		}

		public JButton getBotonUpdateCandidato() {
			return botonUpdateCandidato;
		}

		public void setBotonUpdateCandidato(JButton botonUpdateCandidato) {
			this.botonUpdateCandidato = botonUpdateCandidato;
		}

		public JButton getBotonDeleteCandidato() {
			return botonDeleteCandidato;
		}

		public void setBotonDeleteCandidato(JButton botonDeleteCandidato) {
			this.botonDeleteCandidato = botonDeleteCandidato;
		}

		public JLabel getImagenCandidato() {
			return imagenCandidato;
		}

		public void setImagenCandidato(JLabel imagenCandidato) {
			this.imagenCandidato = imagenCandidato;
		}

		public JPanel getPanelListarCandidato() {
			return panelListarCandidato;
		}

		public void setPanelListarCandidato(JPanel panelConsultaCandidato) {
			panelListarCandidato = panelConsultaCandidato;
		}

		public JTable getTablaCandidatos() {
			return tablaCandidatos;
		}

		public void setTablaCandidatos(JTable tablaCandidatos) {
			this.tablaCandidatos = tablaCandidatos;
		}
}
