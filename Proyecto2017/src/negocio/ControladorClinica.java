
package negocio;
import java.sql.Time;



import java.text.SimpleDateFormat;
import java.util.*;

import datos.*;
import entidad.*;


public class ControladorClinica {




public ArrayList<Turno> buscarTurno (){
	CatalogoTurnos cturnos= new CatalogoTurnos();
	ArrayList<Turno> turnosDisponibles = new ArrayList <Turno> ();

	turnosDisponibles= cturnos.buscarTurnos("Libre");
	return turnosDisponibles;
}

public Turno seleccionarTurno( Date dia_turno, Time hora_turno){
	CatalogoTurnos cturnos= new CatalogoTurnos();
	Turno t = cturnos.seleccionarTurno(dia_turno, hora_turno);
return t;

}

public void reservarTurno(int dni, String motivo_turno, Turno t){
//permite reservar un turno
	CatalogoPersonas cpersonas = new CatalogoPersonas();
	CatalogoTurnos cturnos= new CatalogoTurnos();
	String mensaje= "Dni no encontrado";
	// se castea la variable para que sean del mismo tipo
	Paciente pacient = (Paciente) cpersonas.buscarPersona(dni);
	
	if (pacient == null)
	{
		 //si no encuentra un paciente, se le da el alta
		this.altaPaciente();
		this.reservarTurno(dni, motivo_turno, t);
	}else 
	{
		//se encontro el paciente y se reserva el turno
		t.setPaciente(pacient);
		t.setObservacion(motivo_turno);
		cturnos.actualizarTurno(t);
		
	}
	
	
	
	
	
}

private void altaPaciente() {
	//necesito obtener los datos personales del paciente
	CatalogoPersonas cpersonas = new CatalogoPersonas();
	Paciente pacient = new Paciente();
	
	
	cpersonas.altaPaciente(pacient);
	
}

private void actualizarPaciente(Paciente p){
	CatalogoPersonas cpersonas = new CatalogoPersonas();
	cpersonas.actualizarPaciente(p);
}

private void eliminarPersona(int dni){
	CatalogoPersonas cpersonas = new CatalogoPersonas();
	cpersonas.eliminarPersona(dni);
}

public void altaPractica (Practica p){
	CatalogoPracticas cpracticas = new CatalogoPracticas();
	cpracticas.altaPractica(p);
}

public void bajaPactica (int codigo_practica){
	CatalogoPracticas cpracticas = new CatalogoPracticas();
	cpracticas.bajaPractica(codigo_practica);
}

public void generarAgenda (Horario h, Date fecha_generacion, int dias_horizonte, Time duracion_turno) {
	//dado un horario para un kinesiologo permite generar todos los turnos Libres 
	// desde una fecha de generacion dada hasta el ultimo dia del horizonte de planificacion
	CatalogoTurnos cturnos= new CatalogoTurnos();
	
	//convierto la fecha de generacion en dia para poder compararla
	Calendar dia_alta = Calendar.getInstance();
	dia_alta.setTime(fecha_generacion); // Configuramos la fecha que se recibe
	
	for (int i=0; i<dias_horizonte; i=i+1){
		//por cada dia del horizonte de planificacion
		dia_alta.add(Calendar.DAY_OF_YEAR, i); //se le agrega i dias a la fecha
		Date d_a=dia_alta.getTime(); //devuelve el dia en tipo Date
	
		//convierto el dia de generacion de agenda y de la semana del horario para compararlos
	int dia_generado=getDiaDeLaSemana(fecha_generacion);
	int dia_semana=getNroDia(h.getDia_semana());
	
	//convierto todo a long para poder obtener la cantidad de turnos del kinesiologo en ese horario por dia
	
	long hora_desde= h.getHora_desde().getTime()/(1000*60);
	long hora_hasta=h.getHora_hasta().getTime()/(1000*60);
	long duracion_t=duracion_turno.getTime()/(1000*60);
	
	//comparo si el dia de generacion es = al dia de la semana del horario
		if(dia_generado == dia_semana){
			for (long k=hora_desde; k< hora_hasta; k=k+ duracion_t)
			{		
			//tiene que haber un for por cada horario
					
			Time hora_from= new Time (k);
			
			Turno t = new Turno();
			t.setFecha_alta_t(d_a);
			t.setHora_alta_t(hora_from);
			t.setEstado("Libre");
			t.setKinesiologo(h.getKinesiologo());
			t.setFecha_generacion(fecha_generacion);
			cturnos.registrarTurno(t);
			
		}dia_generado=dia_generado+1;
		
		}
	}
}

public void  asignarTurnoaPaciente (int dni, Date fecha_alta_t, Time hora_alta_t){
	//asigna el paciente a un turno 
	CatalogoTurnos cturnos = new CatalogoTurnos();
	Turno t = new Turno();
	t=cturnos.seleccionarTurno(fecha_alta_t, hora_alta_t);
	Paciente p = new Paciente();
	CatalogoPersonas cpacientes = new CatalogoPersonas();
	p=cpacientes.buscarPaciente(dni);
	t.setPaciente(p);
	t.setEstado("Asignado");
	cturnos.actualizarTurno(t);
	this.confirmarTurnoPaciente(p, t);

}
	
public void asignarSesionaTurno (Date fecha_alta_t, Time hora_alta_t, ArrayList <Sesion> s ){
	CatalogoTurnos cturnos = new CatalogoTurnos();
	Turno t = new Turno();
	t=cturnos.seleccionarTurno(fecha_alta_t, hora_alta_t);
    t.setSesion(s);

}

public void asignarPracticaaSesion (int cod_practica, Sesion s){
	CatalogoPracticas cpracticas = new CatalogoPracticas();
	Practica p = new Practica();
	p=cpracticas.buscarPractica(cod_practica);
	s.setPractica(p);
	
}

public void registrarSesion (ArrayList <Sesion> s){
	//registra las sesiones
	CatalogoSesiones csesiones = new CatalogoSesiones();
	for(int i=0; i<s.size(); i=i+1){
		Sesion sesion = s.get(i);
	csesiones.registrarSesion(sesion);
	}
}

public void registrarSesionAprobada (ArrayList <Sesion> s){
	//registra las sesiones aprobadas por la obra social
	CatalogoSesiones csesiones = new CatalogoSesiones();
	for(int i=0; i<s.size(); i=i+1){
		Sesion sesion = s.get(i);
	csesiones.ActualizarSesion(sesion);
	}
}

public ArrayList <Sesion> buscarSesionEstado(Remito remito, String estado){
	//busca las sesiones de un remito segun un estado especificado
	ArrayList <Sesion> sesiones = new ArrayList <Sesion>();
	CatalogoSesiones csesiones = new CatalogoSesiones();
	java.sql.Date fecha_desde = (java.sql.Date) this.sumarRestarDiasFecha(remito.getFecha_desde(), -1);
	java.sql.Date fecha_hasta = (java.sql.Date) this.sumarRestarDiasFecha(remito.getFecha_hasta(), 1);
sesiones = csesiones.buscarSesionesEstado(fecha_desde, fecha_hasta, estado);	
	return sesiones;
	
	
}

public void confeccionarDetalleRemito (Remito remito){
	CatalogoRemitos cremitos = new CatalogoRemitos();
	cremitos.altaRemito(remito);	
	ArrayList <DetalleRemito> dr = new ArrayList();
	
	java.sql.Date fecha_desde = (java.sql.Date) this.sumarRestarDiasFecha(remito.getFecha_desde(), -1);
	java.sql.Date fecha_hasta = (java.sql.Date) this.sumarRestarDiasFecha(remito.getFecha_hasta(), 1);
	dr=cremitos.obtenerDetalleRemito(fecha_desde,fecha_hasta);
}


public void confirmarTurnoPaciente(Paciente p, Turno t){
	ServicioEnvioCorreo sec = new ServicioEnvioCorreo();
	String asunto = "Confirmación turno asignado en Clínica Pasos";
	String mensaje = "Buenos dìas" + p.getNombre() +" "+ p.getApellido() +". " +
			         "El siguiente correo tiene por motivo la confirmación del turno asignado para el día  " +
        			 t.getFecha_alta_t() + " a las: " + t.getHora_alta_t() + "." +
			         "El mismo puede ser cancelado hasta 24 horas previas a la fecha. Cualquier duda o consulta "+
        			 "comunicarse vía telefónica a o responda dicho correo. Clínica Pasos";
sec.enviarCorreo(asunto, mensaje, p.getEmail());
}

public static int getDiaDeLaSemana(Date d){
	GregorianCalendar cal = new GregorianCalendar();
	cal.setTime(d);
	return cal.get(Calendar.DAY_OF_WEEK);		
}
public static int getNroDia (String dia){
	

	int nro = 0;
	switch(dia){
	case "Domingo": nro= 1;
		break;
	case "Lunes": nro=2;
	    break;
	case "Martes": nro=3;
		break;
	case "Miercoles": nro=4;
		break;
	case "Jueves": nro=5;
		break;
	case "Viernes": nro=6;
		break;
	case "Sabado": nro=7;
		break;
	default:
		break;
	}
	
	return nro;
}

public Date sumarRestarDiasFecha(Date fecha, int dias){
    Calendar calendar = Calendar.getInstance();	
    calendar.setTime(fecha); // Configuramos la fecha que se recibe
    calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
    java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
    
    
    return javaSqlDate; // Devuelve el objeto Date con los nuevos días añadidos
	
	
}
}


