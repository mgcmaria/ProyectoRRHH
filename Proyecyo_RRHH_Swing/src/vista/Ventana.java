package vista;

import java.awt.*;
import java.sql.Connection;
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
	private JPanel panelListarCandidato, panelInsertarCandidato, panelConsultarCandidato, subpanelConsulCan,
	panelUpdateCan;
	private JScrollPane barraCandidatos;
	private JTable tablaCandidatos;
	private JTextField JTFinsertNomCan,JTFinsertApellCan,JTFinsertEmailCan,JTFinsertTeleCan,
		JTFinsertPerfilCan, JTFinsertFuenteCan, JTFinsertObscan, JTFResulComboConsulCan, JTFidUpdateCan,
		JTFUpdateNewdataCan;
	private JLabel etiInsertarCan, eResulInsertCan, etiConsultarCandidato, etiComboConsulCan, eResulConsulCan,
		EresulConsultaIdCan, EresulConsultaNombreCan, EresulConsultaApelCan, EresulConsultaEmailCan, EresulConsultaTelCan,
		EresulConsultaFuenteCan, EresulConsultaPerfilCan, EresulConsultaObsCan, EtituloConsultaIdCan, EtituloConsultaNombreCan, 
		EtituloConsultaApelCan, EtituloConsultaEmailCan, EtituloConsultaTelCan, EtituloConsultaFuenteCan, EtituloConsultaPerfilCan, 
		EtituloConsultaObsCan, EResulExportCan, etiUpdateCandidato, etiConsulUpCan, etiComboUpdateCan, etiUpNewDataCandidato,
		EresulVerUpdateCan, EpreguntaUpdateCan,EresulUpdateFinalCan;
	private JButton BinsertFinalCandidato, BconsulFinalCandidato, BExportCandidato, BVerificarUpdateCan,BUpdateFinalCandidato;
	private JComboBox <String> comboConsultaCandidato, comboUpdateCandidato;
	
	//Colores
	Color colorBlanco = Color.white;
	Color colorGris = Color.gray;
	Color colorNegro = Color.black;
	Color colorVerdeAcens = new Color(50, 205, 50);
	
	Connection conexion;

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
		//FALTAN Botones LOGOUT, EXIT		

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
		
		panelBotoneraCandidato();		
		panelListarCandidatos();		
		panelInsertarCandidato();		
		panelConsultarCandidato();		
		panelUpdateCandidato();
		
	}

	private void panelUpdateCandidato() {
		
		//PANEL ACTUALIZAR CANDIDATO
		
		panelUpdateCan = new JPanel();
		panelUpdateCan.setBackground(Color.lightGray);
		panelUpdateCan.setBounds(260, 110, 925, 550);
		panelUpdateCan.setLayout(null);
		add(panelUpdateCan);
		panelUpdateCan.setVisible(false);
		
		etiUpdateCandidato = new JLabel("Actualizar Candidato");
		etiUpdateCandidato.setBounds(20, 0, 900, 60);
		etiUpdateCandidato.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		etiUpdateCandidato.setForeground(Color.DARK_GRAY);//Color del texto
		etiUpdateCandidato.setHorizontalAlignment(JLabel.CENTER);
		etiUpdateCandidato.setVerticalAlignment(JLabel.CENTER);
		panelUpdateCan.add(etiUpdateCandidato);//Anadimos
		
		etiConsulUpCan = new JLabel("Introduce el ID del candidato a actualizar");
		etiConsulUpCan.setBounds(20, 70, 370, 30);
		etiConsulUpCan.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
		etiConsulUpCan.setForeground(Color.DARK_GRAY);//Color del texto
		panelUpdateCan.add(etiConsulUpCan);//Anadimos
		
		JTFidUpdateCan = new JTextField();//Creamos el componente
		TextPrompt placeholderidUpCan = new TextPrompt("Id Candidato", JTFidUpdateCan);
		placeholderidUpCan.changeAlpha(0.75f);
		placeholderidUpCan.changeStyle(Font.ITALIC);
		JTFidUpdateCan.setBounds(20,110,370,30);//Posicionamos		
		JTFidUpdateCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); 
		JTFidUpdateCan.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JTFidUpdateCan.setBackground(colorBlanco); //Color de fondo
		JTFidUpdateCan.setForeground(colorGris);//Color del texto
		panelUpdateCan.add(JTFidUpdateCan);//Anadimos	
		
		etiComboUpdateCan = new JLabel("Selecciona el campo a actualizar");
		etiComboUpdateCan.setBounds(400, 70, 370, 30);
		etiComboUpdateCan.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
		etiComboUpdateCan.setForeground(Color.DARK_GRAY);//Color del texto
		panelUpdateCan.add(etiComboUpdateCan);//Anadimos
		
		comboUpdateCandidato = new JComboBox<String>();
		comboUpdateCandidato.addItem("NOMBRE");
		comboUpdateCandidato.addItem("APELLIDOS");
		comboUpdateCandidato.addItem("EMAIL");
		comboUpdateCandidato.addItem("TELEFONO");
		comboUpdateCandidato.addItem("FUENTE");
		comboUpdateCandidato.addItem("PERFIL");
		comboUpdateCandidato.addItem("OBSERVACIONES");
		comboUpdateCandidato.setBounds(400, 110, 370, 30);
		comboUpdateCandidato.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); 
		comboUpdateCandidato.setForeground(colorGris);
		panelUpdateCan.add(comboUpdateCandidato);
		
		etiUpNewDataCandidato = new JLabel("Introduce nuevo dato");
		etiUpNewDataCandidato.setBounds(20, 150, 700, 30);
		etiUpNewDataCandidato.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
		etiUpNewDataCandidato.setForeground(Color.DARK_GRAY);//Color del texto
		panelUpdateCan.add(etiUpNewDataCandidato);//Anadimos
		
		JTFUpdateNewdataCan = new JTextField();//Creamos el componente
		TextPrompt placeholdernewdata = new TextPrompt("Nuevo valor", JTFUpdateNewdataCan);
		placeholdernewdata.changeAlpha(0.75f);
		placeholdernewdata.changeStyle(Font.ITALIC);
		JTFUpdateNewdataCan.setBounds(20,190,370,30);//Posicionamos		
		JTFUpdateNewdataCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); 
		JTFUpdateNewdataCan.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JTFUpdateNewdataCan.setBackground(colorBlanco); //Color de fondo
		JTFUpdateNewdataCan.setForeground(colorGris);//Color del texto
		panelUpdateCan.add(JTFUpdateNewdataCan);//Anadimos	
		
		BVerificarUpdateCan = new JButton("VERIFICAR");
		BVerificarUpdateCan.setBounds(520,170,110,50);
		BVerificarUpdateCan.setBackground(colorVerdeAcens);
		BVerificarUpdateCan.setBorder(null);
		BVerificarUpdateCan.setForeground(colorNegro);
		BVerificarUpdateCan.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		panelUpdateCan.add(BVerificarUpdateCan);
		
		EresulVerUpdateCan = new JLabel();
		EresulVerUpdateCan.setBounds(20, 240, 700, 30);
		EresulVerUpdateCan.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
		EresulVerUpdateCan.setForeground(Color.DARK_GRAY);//Color del texto
		panelUpdateCan.add(EresulVerUpdateCan);//Anadimos
		
		EpreguntaUpdateCan = new JLabel("¿Deseas continuar?");
		EpreguntaUpdateCan.setBounds(20, 280, 700, 30);
		EpreguntaUpdateCan.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
		EpreguntaUpdateCan.setForeground(Color.DARK_GRAY);//Color del texto
		panelUpdateCan.add(EpreguntaUpdateCan);//Anadimos
		EpreguntaUpdateCan.setVisible(false);
		
		BUpdateFinalCandidato = new JButton("ACTUALIZAR");
		BUpdateFinalCandidato.setBounds(20,330,110,50);
		BUpdateFinalCandidato.setBackground(colorVerdeAcens);
		BUpdateFinalCandidato.setBorder(null);
		BUpdateFinalCandidato.setForeground(colorNegro);
		BUpdateFinalCandidato.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		panelUpdateCan.add(BUpdateFinalCandidato);
		BUpdateFinalCandidato.setVisible(false);
		
		EresulUpdateFinalCan = new JLabel();
		EresulUpdateFinalCan.setBounds(20, 400, 700, 30);
		EresulUpdateFinalCan.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
		EresulUpdateFinalCan.setForeground(Color.DARK_GRAY);//Color del texto
		panelUpdateCan.add(EresulUpdateFinalCan);//Anadimos
		
		
	}

	private void panelConsultarCandidato() {
		//PANEL CONSULTAR CANDIDATOS
		
		panelConsultarCandidato = new JPanel();
		panelConsultarCandidato.setBackground(Color.lightGray);
		panelConsultarCandidato.setBounds(260, 110, 925, 230);
		panelConsultarCandidato.setLayout(null);
		add(panelConsultarCandidato);
		panelConsultarCandidato.setVisible(false);
		
		etiConsultarCandidato = new JLabel("Consultar Candidato");
		etiConsultarCandidato.setBounds(20, 0, 900, 60);
		etiConsultarCandidato.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		etiConsultarCandidato.setForeground(Color.DARK_GRAY);//Color del texto
		etiConsultarCandidato.setHorizontalAlignment(JLabel.CENTER);
		etiConsultarCandidato.setVerticalAlignment(JLabel.CENTER);
		panelConsultarCandidato.add(etiConsultarCandidato);//Anadimos
		
		etiComboConsulCan = new JLabel("Introduce el campo por el quieras consultar");
		etiComboConsulCan.setBounds(20, 70, 700, 30);
		etiComboConsulCan.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
		etiComboConsulCan.setForeground(Color.DARK_GRAY);//Color del texto
		panelConsultarCandidato.add(etiComboConsulCan);//Anadimos
		
		comboConsultaCandidato = new JComboBox<String>();
		comboConsultaCandidato.addItem("IdCandidato");
		comboConsultaCandidato.addItem("nombre");
		comboConsultaCandidato.addItem("apellidos");
		comboConsultaCandidato.setBounds(20, 110, 250, 30);
		comboConsultaCandidato.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); 
		comboConsultaCandidato.setForeground(colorGris);
		panelConsultarCandidato.add(comboConsultaCandidato);
		
		JTFResulComboConsulCan = new JTextField();//Creamos el componente
		TextPrompt placeholdercampo = new TextPrompt("Introduce Valor", JTFResulComboConsulCan);
		placeholdercampo.changeAlpha(0.75f);
		placeholdercampo.changeStyle(Font.ITALIC);
		JTFResulComboConsulCan.setBounds(300,110,400,30);//Posicionamos		
		JTFResulComboConsulCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); 
		JTFResulComboConsulCan.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JTFResulComboConsulCan.setBackground(colorBlanco); //Color de fondo
		JTFResulComboConsulCan.setForeground(colorGris);//Color del texto
		panelConsultarCandidato.add(JTFResulComboConsulCan);//Anadimos	
		
		BconsulFinalCandidato = new JButton("CONSULTAR");
		BconsulFinalCandidato.setBounds(20,160,110,50);
		BconsulFinalCandidato.setBackground(colorVerdeAcens);
		BconsulFinalCandidato.setBorder(null);
		BconsulFinalCandidato.setForeground(colorNegro);
		BconsulFinalCandidato.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		panelConsultarCandidato.add(BconsulFinalCandidato);
		
		eResulConsulCan = new JLabel();
		eResulConsulCan.setBounds(150, 160, 700, 50);
		eResulConsulCan.setFont(new Font("Segoe UI", Font.BOLD, 20));// Damos formato al contenido
		eResulConsulCan.setForeground(Color.DARK_GRAY);// Color del texto
		eResulConsulCan.setVerticalAlignment(JLabel.CENTER);
		panelConsultarCandidato.add(eResulConsulCan);// Anadimos
		
		subpanelConsulCan = new JPanel();
		subpanelConsulCan.setBackground(Color.lightGray);
		subpanelConsulCan.setBounds(260, 340, 925, 320);
		subpanelConsulCan.setLayout(null);
		add(subpanelConsulCan);
		subpanelConsulCan.setVisible(false);
		
		EtituloConsultaIdCan = new JLabel("ID Candidato");
		EtituloConsultaIdCan.setBounds(20, 0, 150, 30);
		EtituloConsultaIdCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EtituloConsultaIdCan.setForeground(Color.DARK_GRAY);// Color del texto
		EtituloConsultaIdCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); 
		subpanelConsulCan.add(EtituloConsultaIdCan);// Anadimos
		
		EresulConsultaIdCan = new JLabel();
		EresulConsultaIdCan.setBounds(190, 0, 360, 30);
		EresulConsultaIdCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EresulConsultaIdCan.setForeground(Color.DARK_GRAY);// Color del texto
		EresulConsultaIdCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); 
		subpanelConsulCan.add(EresulConsultaIdCan);// Anadimos
		
		EtituloConsultaNombreCan = new JLabel("Nombre");
		EtituloConsultaNombreCan.setBounds(20, 40, 150, 30);
		EtituloConsultaNombreCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EtituloConsultaNombreCan.setForeground(Color.DARK_GRAY);// Color del texto
		EtituloConsultaNombreCan.setBackground(colorBlanco);
		EtituloConsultaNombreCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); 
		subpanelConsulCan.add(EtituloConsultaNombreCan);// Anadimos
		
		EresulConsultaNombreCan = new JLabel();
		EresulConsultaNombreCan.setBounds(190, 40, 360, 30);
		EresulConsultaNombreCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EresulConsultaNombreCan.setForeground(Color.DARK_GRAY);// Color del texto
		EresulConsultaNombreCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); 
		subpanelConsulCan.add(EresulConsultaNombreCan);// Anadimos
		
		EtituloConsultaApelCan = new JLabel("Apellidos");
		EtituloConsultaApelCan.setBounds(20, 80, 150, 30);
		EtituloConsultaApelCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EtituloConsultaApelCan.setForeground(Color.DARK_GRAY);// Color del texto
		EtituloConsultaApelCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); 
		subpanelConsulCan.add(EtituloConsultaApelCan);// Anadimos
		
		EresulConsultaApelCan = new JLabel();
		EresulConsultaApelCan.setBounds(190, 80, 360, 30);
		EresulConsultaApelCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EresulConsultaApelCan.setForeground(Color.DARK_GRAY);// Color del texto
		EresulConsultaApelCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); 
		subpanelConsulCan.add(EresulConsultaApelCan);// Anadimos
		
		EtituloConsultaEmailCan = new JLabel("Email");
		EtituloConsultaEmailCan.setBounds(20, 120, 150, 30);
		EtituloConsultaEmailCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EtituloConsultaEmailCan.setForeground(Color.DARK_GRAY);// Color del texto
		EtituloConsultaEmailCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2)); 
		subpanelConsulCan.add(EtituloConsultaEmailCan);// Anadimos
		
		EresulConsultaEmailCan = new JLabel();
		EresulConsultaEmailCan.setBounds(190, 120, 360, 30);
		EresulConsultaEmailCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EresulConsultaEmailCan.setForeground(Color.DARK_GRAY);// Color del texto
		EresulConsultaEmailCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2));
		subpanelConsulCan.add(EresulConsultaEmailCan);// Anadimos
		
		EtituloConsultaTelCan = new JLabel("Telefono");
		EtituloConsultaTelCan.setBounds(20, 160, 150, 30);
		EtituloConsultaTelCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EtituloConsultaTelCan.setForeground(Color.DARK_GRAY);// Color del texto
		EtituloConsultaTelCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2));
		subpanelConsulCan.add(EtituloConsultaTelCan);// Anadimos
		
		EresulConsultaTelCan = new JLabel();
		EresulConsultaTelCan.setBounds(190, 160, 360, 30);
		EresulConsultaTelCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EresulConsultaTelCan.setForeground(Color.DARK_GRAY);// Color del texto
		EresulConsultaTelCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2));
		subpanelConsulCan.add(EresulConsultaTelCan);// Anadimos
		
		EtituloConsultaPerfilCan = new JLabel("Perfil");
		EtituloConsultaPerfilCan.setBounds(20, 200, 150, 30);
		EtituloConsultaPerfilCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EtituloConsultaPerfilCan.setForeground(Color.DARK_GRAY);// Color del texto
		EtituloConsultaPerfilCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2));
		subpanelConsulCan.add(EtituloConsultaPerfilCan);// Anadimos
		
		EresulConsultaPerfilCan = new JLabel();
		EresulConsultaPerfilCan.setBounds(190, 200, 360, 30);
		EresulConsultaPerfilCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EresulConsultaPerfilCan.setForeground(Color.DARK_GRAY);// Color del texto
		EresulConsultaPerfilCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2));
		subpanelConsulCan.add(EresulConsultaPerfilCan);// Anadimos
		
		EtituloConsultaFuenteCan = new JLabel("Fuente");
		EtituloConsultaFuenteCan.setBounds(20, 240, 150, 30);
		EtituloConsultaFuenteCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EtituloConsultaFuenteCan.setForeground(Color.DARK_GRAY);// Color del texto
		EtituloConsultaFuenteCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2));
		subpanelConsulCan.add(EtituloConsultaFuenteCan);// Anadimos
		
		EresulConsultaFuenteCan = new JLabel();
		EresulConsultaFuenteCan.setBounds(190, 240, 360, 30);
		EresulConsultaFuenteCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EresulConsultaFuenteCan.setForeground(Color.DARK_GRAY);// Color del texto
		EresulConsultaFuenteCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2));
		subpanelConsulCan.add(EresulConsultaFuenteCan);// Anadimos
		
		EtituloConsultaObsCan = new JLabel("Observaciones");
		EtituloConsultaObsCan.setBounds(20, 280, 150, 30);
		EtituloConsultaObsCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EtituloConsultaObsCan.setForeground(Color.DARK_GRAY);// Color del texto
		EtituloConsultaObsCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2));
		subpanelConsulCan.add(EtituloConsultaObsCan);// Anadimos
		
		EresulConsultaObsCan = new JLabel();
		EresulConsultaObsCan.setBounds(190, 280, 650, 30);
		EresulConsultaObsCan.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		EresulConsultaObsCan.setForeground(Color.DARK_GRAY);// Color del texto
		EresulConsultaObsCan.setBorder(BorderFactory.createLineBorder(colorVerdeAcens, 2));
		subpanelConsulCan.add(EresulConsultaObsCan);// Anadimos
	}

	private void panelInsertarCandidato() {
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

	private void panelListarCandidatos() {
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

		String titulosCandidatos[] = { "IdCandidato", "Nombre", "Apellidos", "e-mail", "Telefono", "Perfil", "Fuente",
				"Observaciones" };
		String infoCandidatos[][] = AccesoDB.obtenerMatrizCandidatos();

		TableModel model = new DefaultTableModel(infoCandidatos, titulosCandidatos);
		tablaCandidatos = new JTable(model);			
		tablaCandidatos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaCandidatos.setFillsViewportHeight(true);
		barraCandidatos.setViewportView(tablaCandidatos);
			
		((AbstractTableModel) model).fireTableDataChanged();
		model.addTableModelListener(tablaCandidatos);
		
		BExportCandidato = new JButton("EXPORTAR");
		BExportCandidato.setBounds(20,350,110,50);
		BExportCandidato.setBackground(colorVerdeAcens);
		BExportCandidato.setBorder(null);
		BExportCandidato.setForeground(colorNegro);
		BExportCandidato.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		panelListarCandidato.add(BExportCandidato);	
		
		EResulExportCan = new JLabel();
		EResulExportCan.setBounds(150, 350, 900, 50);
		EResulExportCan.setFont(new Font("Segoe UI",Font.BOLD,20));//Damos formato al contenido
		EResulExportCan.setForeground(Color.DARK_GRAY);//Color del texto
		EResulExportCan.setVerticalAlignment(JLabel.CENTER);
		panelListarCandidato.add(EResulExportCan);//Anadimos
		
		//tablaCandidatos.getColumnModel().getColumn(7).setPreferredWidth(200);	
	}

	private void panelBotoneraCandidato() {
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
	}

	//METODO PARA PONER A LA ESCUCHA LOS EVENTOS	

	public void Eventos (Eventos manejador) {

		botonInicioCandidato.addMouseListener(manejador);
		botonListarCandidato.addMouseListener(manejador);
		BExportCandidato.addMouseListener(manejador);
		botonConsultarCandidato.addMouseListener(manejador);
		BconsulFinalCandidato.addMouseListener(manejador);
		botonInsertarCandidato.addMouseListener(manejador);
		BinsertFinalCandidato.addMouseListener(manejador);	
		botonUpdateCandidato.addMouseListener(manejador);
		BVerificarUpdateCan.addMouseListener(manejador);
		BUpdateFinalCandidato.addMouseListener(manejador);

	}
	
	//GETTERS Y SETTER

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
	public JPanel getPanelConsultarCandidato() {
		return panelConsultarCandidato;
	}
	public void setPanelConsultarCandidato(JPanel panelConsultarCandidato) {
		this.panelConsultarCandidato = panelConsultarCandidato;
	}
	public JComboBox<String> getComboConsultaCandidato() {
		return comboConsultaCandidato;
	}
	public void setComboConsultaCandidato(JComboBox<String> comboConsultaCandidato) {
		this.comboConsultaCandidato = comboConsultaCandidato;
	}
	public JPanel getSubpanelConsulCan() {
		return subpanelConsulCan;
	}
	public void setSubpanelConsulCan(JPanel subpanelConsulCan) {
		this.subpanelConsulCan = subpanelConsulCan;
	}
	public JTextField getJTFResulComboConsulCan() {
		return JTFResulComboConsulCan;
	}
	public void setJTFResulComboConsulCan(JTextField jTFResulComboConsulCan) {
		JTFResulComboConsulCan = jTFResulComboConsulCan;
	}
	public JButton getBconsulFinalCandidato() {
		return BconsulFinalCandidato;
	}
	public void setBconsulFinalCandidato(JButton bconsulFinalCandidato) {
		BconsulFinalCandidato = bconsulFinalCandidato;
	}
	public JLabel geteResulConsulCan() {
		return eResulConsulCan;
	}
	public void seteResulConsulCan(JLabel eResulConsulCan) {
		this.eResulConsulCan = eResulConsulCan;
	}
	public JLabel getEresulConsultaIdCan() {
		return EresulConsultaIdCan;
	}
	public JLabel getEresulConsultaNombreCan() {
		return EresulConsultaNombreCan;
	}
	public JLabel getEresulConsultaApelCan() {
		return EresulConsultaApelCan;
	}
	public JLabel getEresulConsultaEmailCan() {
		return EresulConsultaEmailCan;
	}
	public JLabel getEresulConsultaTelCan() {
		return EresulConsultaTelCan;
	}
	public JLabel getEresulConsultaFuenteCan() {
		return EresulConsultaFuenteCan;
	}
	public JLabel getEresulConsultaPerfilCan() {
		return EresulConsultaPerfilCan;
	}
	public JLabel getEresulConsultaObsCan() {
		return EresulConsultaObsCan;
	}
	public void setEresulConsultaIdCan(JLabel eresulConsultaIdCan) {
		EresulConsultaIdCan = eresulConsultaIdCan;
	}
	public void setEresulConsultaNombreCan(JLabel eresulConsultaNombreCan) {
		EresulConsultaNombreCan = eresulConsultaNombreCan;
	}
	public void setEresulConsultaApelCan(JLabel eresulConsultaApelCan) {
		EresulConsultaApelCan = eresulConsultaApelCan;
	}
	public void setEresulConsultaEmailCan(JLabel eresulConsultaEmailCan) {
		EresulConsultaEmailCan = eresulConsultaEmailCan;
	}
	public void setEresulConsultaTelCan(JLabel eresulConsultaTelCan) {
		EresulConsultaTelCan = eresulConsultaTelCan;
	}
	public void setEresulConsultaFuenteCan(JLabel eresulConsultaFuenteCan) {
		EresulConsultaFuenteCan = eresulConsultaFuenteCan;
	}
	public void setEresulConsultaPerfilCan(JLabel eresulConsultaPerfilCan) {
		EresulConsultaPerfilCan = eresulConsultaPerfilCan;
	}
	public void setEresulConsultaObsCan(JLabel eresulConsultaObsCan) {
		EresulConsultaObsCan = eresulConsultaObsCan;
	}
	public JButton getBExportCandidato() {
		return BExportCandidato;
	}
	public void setBExportCandidato(JButton bExportCandidato) {
		BExportCandidato = bExportCandidato;
	}
	public JLabel getEResulExportCan() {
		return EResulExportCan;
	}
	public void setEResulExportCan(JLabel eResulExportCan) {
		EResulExportCan = eResulExportCan;
	}
	public JPanel getPanelUpdateCan() {
		return panelUpdateCan;
	}
	public void setPanelUpdateCan(JPanel panelUpdateCan) {
		this.panelUpdateCan = panelUpdateCan;
	}

	public JTextField getJTFidUpdateCan() {
		return JTFidUpdateCan;
	}

	public JTextField getJTFUpdateNewdataCan() {
		return JTFUpdateNewdataCan;
	}

	public JLabel getEresulVerUpdateCan() {
		return EresulVerUpdateCan;
	}

	public JButton getBVerificarUpdateCan() {
		return BVerificarUpdateCan;
	}

	public void setJTFidUpdateCan(JTextField jTFidUpdateCan) {
		JTFidUpdateCan = jTFidUpdateCan;
	}

	public void setJTFUpdateNewdataCan(JTextField jTFUpdateNewdataCan) {
		JTFUpdateNewdataCan = jTFUpdateNewdataCan;
	}

	public void setEresulVerUpdateCan(JLabel eresulVerUpdateCan) {
		EresulVerUpdateCan = eresulVerUpdateCan;
	}

	public void setBVerificarUpdateCan(JButton bVerificarUpdateCan) {
		BVerificarUpdateCan = bVerificarUpdateCan;
	}

	public JComboBox<String> getComboUpdateCandidato() {
		return comboUpdateCandidato;
	}

	public void setComboUpdateCandidato(JComboBox<String> comboUpdateCandidato) {
		this.comboUpdateCandidato = comboUpdateCandidato;
	}

	public JLabel getEpreguntaUpdateCan() {
		return EpreguntaUpdateCan;
	}

	public JButton getBUpdateFinalCandidato() {
		return BUpdateFinalCandidato;
	}

	public void setEpreguntaUpdateCan(JLabel epreguntaUpdateCan) {
		EpreguntaUpdateCan = epreguntaUpdateCan;
	}

	public void setBUpdateFinalCandidato(JButton bUpdateFinalCandidato) {
		BUpdateFinalCandidato = bUpdateFinalCandidato;
	}

	public JLabel getEresulUpdateFinalCan() {
		return EresulUpdateFinalCan;
	}

	public void setEresulUpdateFinalCan(JLabel eresulUpdateFinalCan) {
		EresulUpdateFinalCan = eresulUpdateFinalCan;
	}
}
