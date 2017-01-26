package entidad;

import java.sql.Time;
import java.util.*;

public class Turno {
private Date fecha_alta_t;
private Time hora_alta_t;
private String observacion;
private int cant_sesiones;
private Date fecha_baja_t;
private String Estado;
private Date fecha_generacion;
private Paciente Paciente;
private Kinesiologo Kinesiologo;
public java.sql.Date getFecha_generacion;

private int idTurno;



public int getIdTurno() {
	return idTurno;
}
public void setIdTurno(int idTurno) {
	this.idTurno = idTurno;
}




public Date getFecha_alta_t() {
	return fecha_alta_t;
}
public void setFecha_alta_t(Date fecha_alta_t) {
	this.fecha_alta_t = fecha_alta_t;
}
public Time getHora_alta_t() {
	return hora_alta_t;
}
public void setHora_alta_t(Time hoa_alta_t) {
	this.hora_alta_t = hoa_alta_t;
}
public String getObservacion() {
	return observacion;
}
public void setObservacion(String observacion) {
	this.observacion = observacion;
}
public int getCant_sesiones() {
	return cant_sesiones;
}
public void setCant_sesiones(int cant_sesiones) {
	this.cant_sesiones = cant_sesiones;
}
public Date getFecha_baja_t() {
	return fecha_baja_t;
}
public void setFecha_baja_t(Date fecha_baja_t) {
	this.fecha_baja_t = fecha_baja_t;
}
public String getEstado() {
	return Estado;
}
public void setEstado(String estado) {
	Estado = estado;
}
public Date getFecha_generacion() {
	return fecha_generacion;
}
public void setFecha_generacion(Date fecha_generacion) {
	this.fecha_generacion = fecha_generacion;
}
public void setPaciente(Paciente pacient) {
	//la clase turno contiene un paciente
	this.Paciente=pacient;
	
}
public void setKinesiologo(Kinesiologo Kinesiologo)
{this.Kinesiologo=Kinesiologo;
	}
public Kinesiologo getKinesiologo(){
	return Kinesiologo;
}
public Paciente getPaciente() {
	
	return Paciente;
}

}
