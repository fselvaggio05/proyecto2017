package datos;

import entidad.Paciente;
import entidad.Persona;

public class CatalogoPersonas {

	public boolean validaPersona ( int dni) {
	//este m�todo sirve para validar la existencia de la persona
		boolean rta = false;
		
	Persona person = this.buscarPersona(dni);
		if (person!=null) {
							rta=true;
							}
		return rta;
		
	}

	public Persona buscarPersona(int dni) {
		//sirve para buscar una persona mediante un dni
		
		return null;
	}
	
	public boolean validaIdentidadUsuario (int dni, String clave){
//sirve para validar si los datos ingresados pertenecen a un usuario registrado
			boolean rta = false;
	
			
			return rta;
	}

	public void altaPaciente(Paciente pacient) {
		// permite el registro de un nuevo paciente
		
	}
	
	public void actualizarPaciente(Paciente pacient){
		
	}

	public void eliminarPersona(int dni) {
		// permite eliminar cualquier persona por dni
		
	}
	

	}
	

