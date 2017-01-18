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
}

}
