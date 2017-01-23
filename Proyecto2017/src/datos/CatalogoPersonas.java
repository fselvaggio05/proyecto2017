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
		Persona p= new Persona();
			try {
			stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
					"select dni, nombre, apellido, email, sexo, clave from persona where dni = ?"
					);
			stmt.setInt(1, dni);
			rs = stmt.executeQuery();
			if(rs !=null && rs.next()){
		    	p.setDni(rs.getInt("dni"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setEmail(rs.getString("email"));
				p.setSexo(rs.getString("sexo"));
				p.setClave(rs.getString("clave"));
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
		return p;
	}
	
	public Kinesiologo buscarKinesiologo(int dni) {
		//sirve para buscar un kinesiologo mediante un dni
				ResultSet rs=null;
		PreparedStatement stmt=null;
		Kinesiologo k = new Kinesiologo();
		k=(Kinesiologo) this.buscarPersona(dni);
		
			try {
			stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
					"select matricula from persona where dni = ?"
					);
			rs = stmt.executeQuery();
			if(rs !=null && rs.next()){
		    	k.setMatricula(rs.getInt("matricula"));
				k.setDni(rs.getInt("dni"));
				
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
		return k;
	}
	
	public Paciente buscarPaciente(int dni) {
		//sirve para buscar un paciente mediante un dni
				ResultSet rs=null;
		PreparedStatement stmt=null;
		Paciente p = new Paciente();
		p= (Paciente) this.buscarPersona(dni);
			try {
			stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
					"select nro_afiliado_os from persona where dni = ?"
					);
			rs = stmt.executeQuery();
			if(rs !=null && rs.next()){
		    	p.setNro_afiliado_os(rs.getInt("nro_afiliado_os"));
				p.setDni(rs.getInt("dni"));
				
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
		return p;
	}
	
	public boolean validaUsuario (int dni, String clave){
//sirve para validar si los datos ingresados pertenecen a un usuario registrado
		//en el ctrl para validar que tipo de persona es, llamar a los 3 metodos e ir chequeando
		//si el valor obtenido en matricula es null o nro_afiliado es null, si ambos son null es persona
			boolean rta = false;
			
			ResultSet rs=null;
			PreparedStatement stmt=null;
			Persona  p = null;
			
			try {
				stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
						"select dni from usuario where dni = ? and clave = ?"
						);
				stmt.setInt(1, dni);
				stmt.setString(2, clave);
				rs = stmt.executeQuery();
				if(rs !=null && rs.next()){
					rta=true;
									
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
		this.actualizarPersona(pacient);
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement
					("Update persona set  nro_afiliado_os=? where dni=?");
			
	
			stmt.setInt(1, pacient.getNro_afiliado_os());
			stmt.setInt(2, pacient.getDni());
			
			stmt.execute();

			//rs=stmt.getGeneratedKeys();
			
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
	
	public void actualizarPersona(Persona p){
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"Update persona set nombre=?, apellido=?, email=?, fecha_nacimiento=?, sexo=?, clave=? where dni=?");
			
			stmt.setString(1, p.getNombre());
			stmt.setString(2, p.getApellido());
			stmt.setString(3, p.getEmail());
			stmt.setDate(4, (Date) p.getFecha_nacimiento());
			stmt.setString(5, p.getSexo());
			stmt.setString(6, p.getClave());
			stmt.setInt(7, p.getDni());
			
			stmt.execute();

			//rs=stmt.getGeneratedKeys();
			
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
	
	public void actualizarKinesiologo(Kinesiologo k){
		
		this.actualizarPersona(k);
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"Update persona set  matricula=? where dni=?");
			
		
			stmt.setInt(1, k.getMatricula());
			stmt.setInt(2, k.getDni());
			
			stmt.execute();

			//rs=stmt.getGeneratedKeys();
			
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
	

