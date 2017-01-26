package datos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;



import entidad.*;

public class CatalogoTurnos {

	public ArrayList<Turno> buscarTurnos(String estado) {
		//devuelve una lista con los turnos en el estado indicado en el parametro
		ArrayList<Turno> t = new ArrayList<Turno>();
		t=null;
		
		String sql="select fecha_alta_t, hora_alta_t, estado, fecha_baja_t, observacion, matricula, nro_afiliado_os  from turno  where estado= ?";
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = FactoryConexion.getInstancia().getConn();
		try 
		{			
			sentencia= con.prepareStatement(sql);
			sentencia.setString(1, estado );
			rs= sentencia.executeQuery();
			while (rs !=null && rs.next()){
				Turno turno = new Turno();
				Kinesiologo k =new Kinesiologo ();
				Paciente p = new Paciente();
				turno.setFecha_alta_t(rs.getDate("fecha_alta_t"));
				turno.setHora_alta_t(rs.getTime("hora_alta_t"));
				turno.setEstado(rs.getString("estado"));
				turno.setFecha_baja_t(rs.getDate("fecha_baja_t"));
				turno.setObservacion(rs.getString("observacion"));
				k.setMatricula(rs.getInt("matricula"));
				turno.setKinesiologo(k);
				p.setNro_afiliado_os(rs.getInt("nro_afiliado_os"));
				turno.setPaciente(p);
				
				t.add(turno);
			}
			
				
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}
		return t;
		
		
	}

	public Turno seleccionarTurno(Date dia_turno, Time hora_turno) {
		//busca el turno para ese  dia y horario y devuelve el turno con los datos completos
		
		
		Turno turno = new Turno();
		String sql="select fecha_alta_t, hora_alta_t, estado, fecha_baja_t, observacion, matricula, nro_afiliado_os  from turno  where fecha_alta_t= ? and hora_alta_t=?";
		PreparedStatement sentencia=null;
		ResultSet rs=null;
		Connection con = FactoryConexion.getInstancia().getConn();
		try 
		{			
			sentencia= con.prepareStatement(sql);
			sentencia.setDate(1, (java.sql.Date) dia_turno );
			sentencia.setTime(2, hora_turno);
			rs= sentencia.executeQuery();
			while (rs !=null && rs.next()){
				
				Kinesiologo k =new Kinesiologo ();
				Paciente p = new Paciente();
				turno.setFecha_alta_t(rs.getDate("fecha_alta_t"));
				turno.setHora_alta_t(rs.getTime("hora_alta_t"));
				turno.setEstado(rs.getString("estado"));
				turno.setFecha_baja_t(rs.getDate("fecha_baja_t"));
				turno.setObservacion(rs.getString("observacion"));
				k.setMatricula(rs.getInt("matricula"));
				turno.setKinesiologo(k);
				p.setNro_afiliado_os(rs.getInt("nro_afiliado_os"));
				turno.setPaciente(p);
				
				
			}
			
				
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}
		return turno;
	}

	public void actualizarTurno(Turno t) {
		// permite asignar un turno como Libre, Asignado o Cancelado dependiendo de los datos
		//que contenga el Turno
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement
					("Update turno set  nro_afiliado_os=?, motivo=?, estado=?, fecha_baja_t where fecha_alta_t=? and hora_alta_t=?");
			
	
			stmt.setInt(1, t.getPaciente().getNro_afiliado_os());
			stmt.setString(2, t.getObservacion());
			stmt.setString(3, t.getEstado());
			stmt.setDate(4, (java.sql.Date) t.getFecha_baja_t());
			stmt.setDate(5, (java.sql.Date) t.getFecha_alta_t());
			stmt.setTime(6, t.getHora_alta_t());
			
			stmt.execute();

			//rs=stmt.getGeneratedKeys();
			
			if(rs!=null && rs.next()){

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
	
	public void registrarTurno (Turno t ){
		// permite el registro de un nuevo turno
		ResultSet rs=null;
		PreparedStatement stmt=null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into turno (fecha_alta_t, estado, fecha_generacion, hora_alta_t, observacion, idkinesiologo) values (?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setDate(1, (java.sql.Date) t.getFecha_alta_t());
			stmt.setString(2, t.getEstado());
			stmt.setDate(3, (java.sql.Date) t.getFecha_generacion());
			stmt.setTime(4, t.getHora_alta_t());
			stmt.setString(5, t.getObservacion());
			stmt.setInt(6, t.getKinesiologo().getMatricula());
			
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
		}}
	




	
	
	




}