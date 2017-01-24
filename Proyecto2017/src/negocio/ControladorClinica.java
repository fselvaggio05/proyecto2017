
package negocio;
import java.sql.Time;



import java.util.*;

import datos.*;
import entidad.*;


public class ControladorClinica {
CatalogoPersonas cpersonas = new CatalogoPersonas();
CatalogoTurnos cturnos= new CatalogoTurnos();

public ArrayList<Turno> buscarTurno (){
	ArrayList<Turno> turnosDisponibles = new ArrayList <Turno> ();
turnosDisponibles= cturnos.buscarTurnos("Libre");
	return turnosDisponibles;
}

public Turno seleccionarTurno( Date dia_turno, Time hora_turno){
Turno t = cturnos.seleccionarTurno(dia_turno, hora_turno);
return t;

}

public void reservarTurno(int dni, String motivo_turno, Turno t){
//permite reservar un turno
	String mensaje= "Dni no encontrado";
	// se castea la variable para que sean del mismo tipo
	Paciente pacient = (Paciente) cpersonas.buscarPersona(dni);
	
	if (pacient == null)
	{
		 //si no encuentra un paciente, se le da el alta
		this.altaPaciente();
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
	Paciente pacient = new Paciente();
	
	
	cpersonas.altaPaciente(pacient);
	
}

private void actualizarPaciente(Paciente p){
	cpersonas.actualizarPaciente(p);
}

private void eliminarPersona(int dni){
	cpersonas.eliminarPersona(dni);
}

public void generarAgenda (Horario h, Date fecha_generacion, int dias_horizonte, Time duracion_turno) {
	//dado un horario para un kinesiologo permite generar todos los turnos Libres 
	// desde una fecha de generacion dada hasta el ultimo dia del horizonte de planificacion
	ArrayList<Turno> turnosNuevos = new ArrayList <Turno> ();
	
	Calendar dia_alta = Calendar.getInstance();
	dia_alta.setTime(fecha_generacion); // Configuramos la fecha que se recibe
	for (int i=0; i<dias_horizonte; i=i+1){
		
		dia_alta.add(Calendar.DAY_OF_YEAR, i); //se le agrega i dias a la fecha
		Date d_a=dia_alta.getTime(); //devuelve el dia en tipo Date
		
	int dia_generado=getDiaDeLaSemana(fecha_generacion);
	int dia_semana=getNroDia(h.getDia_semana());
		if(dia_generado == dia_semana){
			for (Time k=h.getHora_desde(); k< h.getHora_hasta(); k=k+ duracion_turno)
			{		
			//tiene que haber un for por cada horario
			
			Turno t = new Turno();
			t.setFecha_alta_t(d_a);
			t.setHora_alta_t(h.getHora_desde());
			t.setEstado("Libre");
			t.setKinesiologo(h.getKinesiologo());
			t.setFecha_generacion(fecha_generacion);
			
		}dia_generado=dia_generado+1;
		
		}
	}
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
}


