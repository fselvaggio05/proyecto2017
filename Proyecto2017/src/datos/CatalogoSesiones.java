package datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
