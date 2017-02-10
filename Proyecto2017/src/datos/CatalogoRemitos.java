package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.*;

public class CatalogoRemitos {

	public void altaRemito (Remito r){
		// permite el registro de un nuevo remito
		ResultSet rs=null;
		PreparedStatement stmt=null;
				
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into remito (nro_remito, fecha_confeccion, fecha_desde, fecha_hasta) values (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, r.getNro_remito());
			stmt.setDate(2, (Date) r.getFecha_confeccion());
			stmt.setDate(3, (Date) r.getFecha_desde());
			stmt.setDate(4,(Date) r.getFecha_hasta());
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

	public ArrayList<DetalleRemito> obtenerDetalleRemito (Date fecha_desde, Date fecha_hasta){
		//registra la cantidad de practicas por obra social
		ArrayList<DetalleRemito> detalle= new ArrayList <DetalleRemito>();
		
		
			String sql="select COUNT(sesion.idsesion)AS cant_sesiones, obra_social.codigo_os, obrasocial.nombre_os from obra_social INNER JOIN persona on persona.idobra_social=obra_social.idobra_social"
					+ " INNER JOIN turno on turno.nro_afiliado_os=persona.nro_afiliado_os"
					+ "INNER JOIN sesion on sesion.idturno=turno.idturno"
					+ "GROUP BY obra_social obra_social.codigo_os "
					+ "WHERE sesion.fecha_sesion > ? AND sesion.fecha_sesion < ?";
			//MIENTRAS SEA LA SESION DEL MES CORRIENTE
			
			PreparedStatement sentencia=null;
			ResultSet rs=null;
			Connection con = FactoryConexion.getInstancia().getConn();
			try 
			{			
				sentencia= con.prepareStatement(sql);
				sentencia.setDate(1, fecha_desde);
				sentencia.setDate(2, fecha_hasta);
				rs= sentencia.executeQuery();
				
			
				while (rs !=null && rs.next()){
					DetalleRemito dr = new DetalleRemito();
					dr.setCodigo_os(rs.getInt(2));
					dr.setNombre_os(rs.getString(3));
					dr.setCant_sesionesmensuales(rs.getInt(1));
					detalle.add(dr);
					
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
		
		return detalle;
	}
}
