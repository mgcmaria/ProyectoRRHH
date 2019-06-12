package tablas;

public class Candidato {
	
	private int idCandidato, telefono;
	private String nombre, apellidos, email, fuente, perfil, observaciones;	
	
	public Candidato(int idCandidato, String nombre, String apellidos, String email, int telefono,String fuente,
			String perfil, String observaciones) {
		super();
		this.idCandidato = idCandidato;
		this.telefono = telefono;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.fuente = fuente;
		this.perfil = perfil;
		this.observaciones = observaciones;
	}
	
	
	//Sobrecarga del constructor 
	public Candidato(String nombre, String apellidos, String email, int telefono, String fuente, 
			String perfil, String observaciones) {
		super(); 
		this.telefono = telefono;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email; 
		this.fuente = fuente;
		this.perfil = perfil; 
		this.observaciones = observaciones;
	}

	public int getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(int idCandidato) {
		this.idCandidato = idCandidato;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
