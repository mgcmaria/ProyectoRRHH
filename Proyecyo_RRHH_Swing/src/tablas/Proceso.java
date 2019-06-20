package tablas;

import java.sql.Date;

public class Proceso {
	
	private int idProceso;
	private String nombrePro, clientePro, responsablePro, direccionPro, departPro, estadoPro, desTrabajoPro;
	private Date fechaEntradaPro;
	
	public Proceso(int idProceso, String nombrePro, String clientePro, String responsablePro, String direccionPro,
			String departPro, Date fechaEntradaPro, String estadoPro, String desTrabajoPro) {
		super();
		this.idProceso = idProceso;
		this.nombrePro = nombrePro;
		this.clientePro = clientePro;
		this.responsablePro = responsablePro;
		this.direccionPro = direccionPro;
		this.departPro = departPro;
		this.fechaEntradaPro = fechaEntradaPro;
		this.estadoPro = estadoPro;
		this.desTrabajoPro = desTrabajoPro;	
	}
	
	//Sobrecarga del constructor
	public Proceso(String nombrePro, String clientePro, String responsablePro, String direccionPro,
			String departPro, Date fechaEntradaPro, String estadoPro, String desTrabajoPro) {
		super();
		this.nombrePro = nombrePro;
		this.clientePro = clientePro;
		this.responsablePro = responsablePro;
		this.direccionPro = direccionPro;
		this.departPro = departPro;
		this.fechaEntradaPro = fechaEntradaPro;
		this.estadoPro = estadoPro;
		this.desTrabajoPro = desTrabajoPro;	
	}

	public int getIdProceso() {
		return idProceso;
	}
	public String getNombrePro() {
		return nombrePro;
	}
	public String getClientePro() {
		return clientePro;
	}
	public String getResponsablePro() {
		return responsablePro;
	}
	public String getDireccionPro() {
		return direccionPro;
	}
	public String getDepartPro() {
		return departPro;
	}
	public String getEstadoPro() {
		return estadoPro;
	}
	public String getDesTrabajoPro() {
		return desTrabajoPro;
	}
	public Date getFechaEntradaPro() {
		return fechaEntradaPro;
	}
	public void setIdProceso(int idProceso) {
		this.idProceso = idProceso;
	}
	public void setNombrePro(String nombrePro) {
		this.nombrePro = nombrePro;
	}
	public void setClientePro(String clientePro) {
		this.clientePro = clientePro;
	}
	public void setResponsablePro(String responsablePro) {
		this.responsablePro = responsablePro;
	}
	public void setDireccionPro(String direccionPro) {
		this.direccionPro = direccionPro;
	}
	public void setDepartPro(String departPro) {
		this.departPro = departPro;
	}
	public void setEstadoPro(String estadoPro) {
		this.estadoPro = estadoPro;
	}
	public void setDesTrabajoPro(String desTrabajoPro) {
		this.desTrabajoPro = desTrabajoPro;
	}
	public void setFechaEntradaPro(Date fechaEntradaPro) {
		this.fechaEntradaPro = fechaEntradaPro;
	}
}
