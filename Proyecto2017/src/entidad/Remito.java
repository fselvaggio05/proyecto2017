package entidad;

import java.util.*;

public class Remito {

	private int nro_remito;
	private Date fecha_confeccion;
	private Date fecha_desde;
	private Date fecha_hasta;
	
	public int getNro_remito() {
		return nro_remito;
	}
	public void setNro_remito(int nro_remito) {
		this.nro_remito = nro_remito;
	}
	public Date getFecha_confeccion() {
		return fecha_confeccion;
	}
	public void setFecha_confeccion(Date fecha_confeccion) {
		this.fecha_confeccion = fecha_confeccion;
	}
	public Date getFecha_desde() {
		return fecha_desde;
	}
	public void setFecha_desde(Date fecha_desde) {
		this.fecha_desde = fecha_desde;
	}
	public Date getFecha_hasta() {
		return fecha_hasta;
	}
	public void setFecha_hasta(Date fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}
	
}
