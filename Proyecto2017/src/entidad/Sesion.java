package entidad;

import java.util.*;

public class Sesion {
private Date fecha_sesion;
private int nro_sesion;
private Practica Practica;
private Turno Turno;
public Date getFecha_sesion() {
	return fecha_sesion;
}
public void setFecha_sesion(Date fecha_sesion) {
	this.fecha_sesion = fecha_sesion;
}
public int getNro_sesion() {
	return nro_sesion;
}
public void setNro_sesion(int nro_sesion) {
	this.nro_sesion = nro_sesion;
}
public Practica getPractica() {
	return Practica;
}
public void setPractica(Practica practica) {
	Practica = practica;
}
public Turno getTurno() {
	return Turno;
}
public void setTurno(Turno turno) {
	Turno = turno;
}
}
