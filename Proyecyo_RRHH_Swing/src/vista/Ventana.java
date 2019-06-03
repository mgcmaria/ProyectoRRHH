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
	private JPanel panelListarCandidato, panelInsertarCandidato;
	private JScrollPane barraCandidatos;
	private JTable tablaCandidatos;
	private JTextField JTFinsertNomCan,JTFinsertApellCan,JTFinsertEmailCan,JTFinsertTeleCan,
		JTFinsertPerfilCan, JTFinsertFuenteCan, JTFinsertObscan;
	private JLabel etiInsertarCan, eResulInsertCan;
	private JButton BinsertFinalCandidato;
	
	//Colores
	Color colorBlanco = Color.white;
	Color colorGris = Color.gray;
	Color colorNegro = Color.black;
	Color colorVerdeAcens = new Color(50, 205, 50);

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
		botonInicioCandidato.setForeground(colorVerdeAcens);
		botonInicioCandidato.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		inicioFijo.add(botonInicioCandidato);
		
		botonInicioProceso = new JButton("PROCESO");
		botonInicioProceso.setBounds(40,150,160,80);
		botonInicioProceso.setBackground(colorNegro);
		botonInicioProceso.setBorder(null);
		botonInicioProceso.setForeground(colorVerdeAcens);
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
		botonListarCandidato.setForeground(colorVerdeAcens);
		botonListarCandidato.setFont(new Font("Segoe UI",Font.BOLD,12));//Damos formato al contenido
		botoneraPanelCandidato.add(botonListarCandidato);
		
		botonInsertarCandidato = new JButton("INSERTAR CANDIDATO");
		botonInsertarCandidato.setBounds(200,20,160,60);
		botonInsertarCandidato.setBackground(colorNegro);
		botonInsertarCandidato.setBorder(null);
		botonInsertarCandidato.setForeground(colorVerdeAcens);
		botonInsertarCandidato.setFont(new Font("Segoe UI",Font.BOLD,12));//Damos formato al contenido
		botoneraPanelCandidato.add(botonInsertarCandidato);
		
		botonConsultarCandidato = new JButton("CONSULTAR CANDIDATO");
		botonConsultarCandidato.setBounds(380,20,160,60);
		botonConsultarCandidato.setBackground(colorNegro);
		botonConsultarCandidato.setBorder(null);
		botonConsultarCandidato.setForeground(colorVerdeAcens);
		botonConsultarCandidato.setFont(new Font("Segoe UI",Font.BOLD,12));//Damos formato al contenido
		botoneraPanelCandidato.add(botonConsultarCandidato);
		
		botonUpdateCandidato = new JButton("ACTUALIZAR CANDIDATO");
		botonUpdateCandidato.setBounds(560,20,160,60);
		botonUpdateCandidato.setBackground(colorNegro);
		botonUpdateCandidato.setBorder(null);
		botonUpdateCandidato.setForeground(colorVerdeAcens);
		botonUpdateCandidato.setFont(new Font("Segoe UI",Font.BOLD,12));//Damos formato al contenido
		botoneraPanelCandidato.add(botonUpdateCandidato);
		
		botonDeleteCandidato = new JButton("ELIMINAR CANDIDATO");
		botonDeleteCandidato.setBounds(740,20,160,60);
		botonDeleteCandidato.setBackground(colorNegro);
		botonDeleteCandidato.setBorder(null);
		botonDeleteCandidato.setForeground(colorVerdeAcens);
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
		panelListarCandidato.setBackground(Color.LIGHT_GRAY);
		panelListarCandidato.setBounds(260, 110, 925, 550);
		panelListarCandidato.setLayout(null);
		add(panelListarCandidato);
		panelListarCandidato.setVisible(false);
		
		//Creación de la tabla Candidatos
		
		barraCandidatos = new JScrollPane();
		barraCandidatos.setBounds(20, 20, 850, 300);
		panelListarCandidato.add(barraCandidatos);
		
		String titulosEmpleados[] = {"IdCandidato","Nombre","Apellidos","e-mail", "Telefono","Perfil",
				"Fuente","Observaciones"};
		String infoCandidatos[][] = AccesoDB.obtenerMatrizCandidatos();
		
		
		TableModel model = new DefaultTableModel(infoCandidatos, titulosEmpleados);
		tablaCandidatos = new JTable(model);
		  
		tablaCandidatos.setModel(model); 
		((AbstractTableModel)model).fireTableDataChanged();
		model.addTableModelListener(getTablaCandidatos());
		 
		tablaCandidatos = new JTable(infoCandidatos,titulosEmpleados);

		tablaCandidatos.getColumnModel().getColumn(7).setPreferredWidth(200);

		barraCandidatos.setViewportView(tablaCandidatos);
		
		//PANEL INSERTAR CANDIDATOS
		
		panelInsertarCandidato = new JPanel();
		panelInsertarCandidato.setBackground(Color.lightGray);
		panelInsertarCandidato.setBounds(260, 110, 925, 550);
		panelInsertarCandidato.setLayout(null);
		add(panelInsertarCandidato);
		panelInsertarCandidato.setVisible(false);
		
		etiInsertarCan = new JLabel("Insertar Candidato");
		etiInsertarCan.setBounds(20, 0, 900, 60);
		etiInsertarCan.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		etiInsertarCan.setForeground(Color.DARK_GRAY);//Color del texto
		etiInsertarCan.setHorizontalAlignment(JLabel.CENTER);
		etiInsertarCan.setVerticalAlignment(JLabel.CENTER);
		panelInsertarCandidato.add(etiInsertarCan);//Anadimos
		
		JTFinsertNomCan = new JTextField();//Creamos el componente
		TextPrompt placeholder = new TextPrompt("Nombre candidato", JTFinsertNomCan);
	    placeholder.changeAlpha(0.75f);
	    placeholder.changeStyle(Font.ITALIC);
	    JTFinsertNomCan.setBounds(50,90,250,30);//Posicionamos		
	    JTFinsertNomCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); //Eliminamos el borde
	    JTFinsertNomCan.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    JTFinsertNomCan.setBackground(colorBlanco); //Color de fondo
	    JTFinsertNomCan.setForeground(colorGris);//Color del texto
	    panelInsertarCandidato.add(JTFinsertNomCan);//Anadimos
	    
	    JTFinsertApellCan = new JTextField();//Creamos el componente
		TextPrompt placeholderapel = new TextPrompt("Apellidos candidato", JTFinsertApellCan);
		placeholderapel.changeAlpha(0.75f);
		placeholderapel.changeStyle(Font.ITALIC);
	    JTFinsertApellCan.setBounds(330,90,350,30);//Posicionamos		
	    JTFinsertApellCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); //Eliminamos el borde
	    JTFinsertApellCan.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    JTFinsertApellCan.setBackground(colorBlanco); //Color de fondo
	    JTFinsertApellCan.setForeground(colorGris);//Color del texto
	    panelInsertarCandidato.add(JTFinsertApellCan);//Anadimos
	    
	    JTFinsertTeleCan = new JTextField();//Creamos el componente
		TextPrompt placeholdertel = new TextPrompt("Telefono", JTFinsertTeleCan);
		placeholdertel.changeAlpha(0.75f);
		placeholdertel.changeStyle(Font.ITALIC);
		JTFinsertTeleCan.setBounds(710,90,150,30);//Posicionamos		
		JTFinsertTeleCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); //Eliminamos el borde
		JTFinsertTeleCan.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JTFinsertTeleCan.setBackground(colorBlanco); //Color de fondo
		JTFinsertTeleCan.setForeground(colorGris);//Color del texto
	    panelInsertarCandidato.add(JTFinsertTeleCan);//Anadimos
	    
	    JTFinsertEmailCan = new JTextField();//Creamos el componente
		TextPrompt placeholdertemailCan = new TextPrompt("Email", JTFinsertEmailCan);
		placeholdertemailCan.changeAlpha(0.75f);
		placeholdertemailCan.changeStyle(Font.ITALIC);
		JTFinsertEmailCan.setBounds(50,150,350,30);//Posicionamos		
		JTFinsertEmailCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); //Eliminamos el borde
		JTFinsertEmailCan.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JTFinsertEmailCan.setBackground(colorBlanco); //Color de fondo
		JTFinsertEmailCan.setForeground(colorGris);//Color del texto
	    panelInsertarCandidato.add(JTFinsertEmailCan);//Anadimos
	    
	    JTFinsertFuenteCan = new JTextField();//Creamos el componente
		TextPrompt placeholdertfuente = new TextPrompt("Fuente", JTFinsertFuenteCan);
		placeholdertfuente.changeAlpha(0.75f);
		placeholdertfuente.changeStyle(Font.ITALIC);
		JTFinsertFuenteCan.setBounds(430,150,200,30);//Posicionamos		
		JTFinsertFuenteCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); //Eliminamos el borde
		JTFinsertFuenteCan.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JTFinsertFuenteCan.setBackground(colorBlanco); //Color de fondo
		JTFinsertFuenteCan.setForeground(colorGris);//Color del texto
	    panelInsertarCandidato.add(JTFinsertFuenteCan);//Anadimos
	    
	    JTFinsertPerfilCan = new JTextField();//Creamos el componente
		TextPrompt placeholdertperfil = new TextPrompt("Perfil", JTFinsertPerfilCan);
		placeholdertperfil.changeAlpha(0.75f);
		placeholdertperfil.changeStyle(Font.ITALIC);
		JTFinsertPerfilCan.setBounds(660,150,200,30);//Posicionamos		
		JTFinsertPerfilCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); //Eliminamos el borde
		JTFinsertPerfilCan.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JTFinsertPerfilCan.setBackground(colorBlanco); //Color de fondo
		JTFinsertPerfilCan.setForeground(colorGris);//Color del texto
	    panelInsertarCandidato.add(JTFinsertPerfilCan);//Anadimos
	    
	    JTFinsertObscan = new JTextField();//Creamos el componente
		TextPrompt placeholderobs = new TextPrompt("Observaciones", JTFinsertObscan);
		placeholderobs.changeAlpha(0.75f);
		placeholderobs.changeStyle(Font.ITALIC);
		JTFinsertObscan.setBounds(50,210,810,30);//Posicionamos		
		JTFinsertObscan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); //Eliminamos el borde
		JTFinsertObscan.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JTFinsertObscan.setBackground(colorBlanco); //Color de fondo
		JTFinsertObscan.setForeground(colorGris);//Color del texto
	    panelInsertarCandidato.add(JTFinsertObscan);//Anadimos
	    
	    BinsertFinalCandidato = new JButton("INSERTAR");
	    BinsertFinalCandidato.setBounds(50,270,110,50);
	    BinsertFinalCandidato.setBackground(colorVerdeAcens);
	    BinsertFinalCandidato.setBorder(null);
	    BinsertFinalCandidato.setForeground(colorNegro);
	    BinsertFinalCandidato.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    panelInsertarCandidato.add(BinsertFinalCandidato);
	    
	    eResulInsertCan = new JLabel();
	    eResulInsertCan.setBounds(190, 270, 700, 50);
	    eResulInsertCan.setFont(new Font("Segoe UI",Font.BOLD,20));//Damos formato al contenido
	    eResulInsertCan.setForeground(Color.DARK_GRAY);//Color del texto
	    eResulInsertCan.setVerticalAlignment(JLabel.CENTER);
		panelInsertarCandidato.add(eResulInsertCan);//Anadimos
		
	}
	
	//METODO PARA PONER A LA ESCUCHA LOS EVENTOS	

		public void Eventos (Eventos manejador) {

			botonInicioCandidato.addMouseListener(manejador);
			botonListarCandidato.addMouseListener(manejador);
			botonConsultarCandidato.addMouseListener(manejador);
			botonInsertarCandidato.addMouseListener(manejador);
			BinsertFinalCandidato.addMouseListener(manejador);

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

		public JPanel getPanelInsertarCandidato() {
			return panelInsertarCandidato;
		}

		public void setPanelInsertarCandidato(JPanel panelInsertarCandidato) {
			this.panelInsertarCandidato = panelInsertarCandidato;
		}

		public JTextField getJTFinsertNomCan() {
			return JTFinsertNomCan;
		}

		public void setJTFinsertNomCan(JTextField jTFinsertNomCan) {
			JTFinsertNomCan = jTFinsertNomCan;
		}

		public JTextField getJTFinsertApellCan() {
			return JTFinsertApellCan;
		}

		public void setJTFinsertApellCan(JTextField jTFinsertApellCan) {
			JTFinsertApellCan = jTFinsertApellCan;
		}

		public JTextField getJTFinsertEmailCan() {
			return JTFinsertEmailCan;
		}

		public void setJTFinsertEmailCan(JTextField jTFinsertEmailCan) {
			JTFinsertEmailCan = jTFinsertEmailCan;
		}

		public JTextField getJTFinsertTeleCan() {
			return JTFinsertTeleCan;
		}

		public void setJTFinsertTeleCan(JTextField jTFinsertTeleCan) {
			JTFinsertTeleCan = jTFinsertTeleCan;
		}

		public JTextField getJTFinsertPerfilCan() {
			return JTFinsertPerfilCan;
		}

		public void setJTFinsertPerfilCan(JTextField jTFinsertPerfilCan) {
			JTFinsertPerfilCan = jTFinsertPerfilCan;
		}

		public JTextField getJTFinsertFuenteCan() {
			return JTFinsertFuenteCan;
		}

		public void setJTFinsertFuenteCan(JTextField fTJinsertFuenteCan) {
			JTFinsertFuenteCan = fTJinsertFuenteCan;
		}

		public JTextField getJTFinsertObscan() {
			return JTFinsertObscan;
		}

		public void setJTFinsertObscan(JTextField jTFinsertObscan) {
			JTFinsertObscan = jTFinsertObscan;
		}

		public JButton getBinsertFinalCandidato() {
			return BinsertFinalCandidato;
		}

		public void setBinsertFinalCandidato(JButton binsertFinalCandidato) {
			BinsertFinalCandidato = binsertFinalCandidato;
		}

		public JLabel geteResulInsertCan() {
			return eResulInsertCan;
		}

		public void seteResulInsertCan(JLabel eResulInsertCan) {
			this.eResulInsertCan = eResulInsertCan;
		}
}
