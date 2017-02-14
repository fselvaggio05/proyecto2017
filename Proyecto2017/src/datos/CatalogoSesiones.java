package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.*;

public class CatalogoSesiones {

	public void registrarSesion (Sesion s){
		
		// permite el registro de una nueva sesion
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into sesion (idpractica, idturno, fecha_sesion, nro_sesion ) values (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, s.getPractica().getIdPractica());
			stmt.setInt(2, s.getTurno().getIdTurno());
			stmt.setDate(3, (Date) s.getFecha_sesion());
			stmt.setInt(4, s.getNro_sesion());
		
		
			
			
			stmt.execute();

			rs=stmt.getGeneratedKeys();
			
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

	public void actualizarSesion(Sesion sesion) {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement
					("Update sesion set  estado=? where idsesion=?");
			
	
			stmt.setString(1, sesion.getEstado());
			stmt.setInt(2, sesion.getIdsesion());		
			
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

	public ArrayList<Sesion> buscarSesionesEstado (Date fecha_desde, Date fecha_hasta, String estado){
		
			//devuelve una lista con las sesiones en el estado indicado en el parametro
			ArrayList<Sesion> s = new ArrayList<Sesion>();
			s=null;
			
			String sql="select * from sesion  where estado= ? AND fecha_sesion>fecha_desde AND fecha_sesion<fecha_hasta";
			PreparedStatement sentencia=null;
			ResultSet rs=null;
			Connection con = FactoryConexion.getInstancia().getConn();
			try 
			{			
				sentencia= con.prepareStatement(sql);
				sentencia.setString(1, estado );
				rs= sentencia.executeQuery();
				while (rs !=null && rs.next()){
					Sesion sesion = new Sesion();
					sesion.setIdsesion(rs.getInt("idsesion"));
					sesion.setNro_sesion(rs.getInt("nro_sesion"));
					sesion.setEstado(rs.getString("estado"));
					sesion.setFecha_sesion(rs.getDate("fecha_sesion"));
					s.add(sesion);
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
			return s;
			
	}
}
