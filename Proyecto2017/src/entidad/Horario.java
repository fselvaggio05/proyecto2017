package entidad;

import java.sql.Time;
import java.util.*;

public class Horario {
private Date fecha_alta_h;
private Date fecha_baja_h;
private Time hora_desde;
private Time hora_hasta;
private String dia_semana;
private Kinesiologo Kinesiologo;

public Date getFecha_alta_h() {
	return fecha_alta_h;
}
public void setFecha_alta_h(Date fecha_alta_h) {
	this.fecha_alta_h = fecha_alta_h;
}
public Date getFecha_baja_h() {
	return fecha_baja_h;
}
public void setFecha_baja_h(Date fecha_baja_h) {
	this.fecha_baja_h = fecha_baja_h;
}
public Time getHora_desde() {
	return hora_desde;
}
public void setHora_desde(Time hora_desde) {
	this.hora_desde = hora_desde;
}
public Time getHora_hasta() {
	return hora_hasta;
}
public void setHora_hasta(Time hora_hasta) {
	this.hora_hasta = hora_hasta;
}
public String getDia_semana() {
	return dia_semana;
}
public void setDia_semana(String dia_semana) {
	this.dia_semana = dia_semana;
}
public Kinesiologo getKinesiologo(){
return this.Kinesiologo;
}

public void setKinesiologo(Kinesiologo Kinesiologo){
this.Kinesiologo=Kinesiologo;	
}
}
