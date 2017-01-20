package datos;

import java.sql.*;


import entidad.*;

public class CatalogoPersonas {

	public boolean validaPersona ( int dni) {
	//este mètodo sirve para validar la existencia de la persona
		boolean rta = false;
		
	Persona person = this.buscarPersona(dni);
		if (person!=null) {
							rta=true;
							}
		return rta;
		
	}

	public Persona buscarPersona(int dni) {
		//sirve para buscar una persona mediante un dni
		

		ResultSet rs=null;
		PreparedStatement stmt=null;
		//Persona p= new Persona();
		
		try {
			stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
					"select dni, nombre, apellido, email, sexo, matricula, nro_afiliado_os from persona where dni = ?"
					);
			stmt.setInt(1, dni);
			rs = stmt.executeQuery();
			if(rs !=null && rs.next()){
				if(rs.getInt("matricula") == Integer.parseInt(null)){
				
					Paciente p = new Paciente();
				p.setDni(rs.getInt("dni"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setEmail(rs.getString("email"));
				p.setSexo(rs.getString("sexo"));
				p.setNro_afiliado_os(rs.getInt("nro_afiliado_os"));
				
				return p;
				}
				if(rs.getInt("nro_afiliado_os") == Integer.parseInt(null)){
					
				Kinesiologo k = new Kinesiologo();
				k.setDni(rs.getInt("dni"));
				k.setNombre(rs.getString("nombre"));
				k.setApellido(rs.getString("apellido"));
				k.setEmail(rs.getString("email"));
				k.setSexo(rs.getString("sexo"));
				k.setMatricula(rs.getInt("matricula"));
				
				return k;
				}
				else {
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FactoryConexion.getInstancia().releaseConn();
		}
		return null;
	}
	
	public boolean validaIdentidadUsuario (int dni, String clave){
//sirve para validar si los datos ingresados pertenecen a un usuario registrado
			boolean rta = false;
	
			
			return rta;
	}

	public void altaPaciente(Paciente pacient) {
		
		// permite el registro de un nuevo paciente
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into persona (dni, nombre, apellido, email, fecha_nacimiento, sexo, nro_afiliado_os) values (?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, pacient.getDni());
			stmt.setString(2, pacient.getNombre());
			stmt.setString(3, pacient.getApellido());
			stmt.setString(4, pacient.getEmail());
			stmt.setDate(5, (Date) pacient.getFecha_nacimiento());
			stmt.setString(6, pacient.getSexo());
			stmt.setInt(7, pacient.getNro_afiliado_os());
			
			
			stmt.execute();

			rs=stmt.getGeneratedKeys();
			
			if(rs!=null && rs.next()){
			//	pacient.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			try {
				if(rs!=null ) rs.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			FactoryConexion.getInstancia().releaseConn();
		}
	
	}
	
	public void actualizarPaciente(Paciente pacient){
		
	}

	public void eliminarPersona(int dni) {
		// permite eliminar cualquier persona por dni
		
		PreparedStatement stmt=null;
		try {
			stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
					"DELETE from persona where dni = ?"
					);
			stmt.setInt(1, dni);
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				if(stmt!=null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FactoryConexion.getInstancia().releaseConn();
		
	
	
	}
		
	}
	

	}
	

