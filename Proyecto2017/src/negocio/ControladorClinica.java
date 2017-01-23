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
turnosDisponibles= cturnos.buscarTurnosDisponibles();
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
		cturnos.asignarTurno(t);
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
}


