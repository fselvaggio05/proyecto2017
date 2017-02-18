package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.*;

public class CatalogoHorarios {

	public void altaHorario (Horario h){
		// permite el registro de un nuevo horario
				ResultSet rs=null;
				PreparedStatement stmt=null;
				try {
					stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
							"insert into horario (fecha_alta_h, hora_desde, hora_hasta, dia_semana, idkinesiologo) values (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
					stmt.setDate(1, (java.sql.Date) h.getFecha_alta_h());
					stmt.setTime(2, h.getHora_desde());
					stmt.setTime(3, h.getHora_hasta());
					stmt.setString(4, h.getDia_semana());
					stmt.setInt(5, h.getKinesiologo().getMatricula());			
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

public void bajaHorario ( Horario h){
	// permite dar de baja un horario
			
			ResultSet rs=null;
			PreparedStatement stmt=null;
			
			
			try {
				stmt = FactoryConexion.getInstancia().getConn().prepareStatement
						("Update horario set  fecha_baja_h=?, where fecha_alta_h=? and hora_desde=? and hora_hasta=? and fecha_baja_h=?");
				
		
				stmt.setDate(1, (Date) h.getFecha_baja_h());
				stmt.setDate(2, (Date) h.getFecha_alta_h());
				stmt.setTime(3, h.getHora_desde());
				stmt.setTime(4, h.getHora_hasta());
				stmt.setDate(5, null);
				
				
				stmt.execute();

				//rs=stmt.getGeneratedKeys();
				
				if(rs!=null && rs.next()){
//por si se necesita el id
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


public ArrayList <Horario> buscarHorariosVigentes ( int matricula)
{
	//sirve para buscar horarios actuales de un kinesiologo
	
ArrayList <Horario> horarios = new ArrayList <Horario>();
String sql="select fecha_alta_h, hora_desde, hora_hasta, dia_semana from horario where idkinesiologo= ? and fecha_baja_h=?";
PreparedStatement sentencia=null;
ResultSet rs=null;
Connection con = FactoryConexion.getInstancia().getConn();
try 
{			
	sentencia= con.prepareStatement(sql);
	sentencia.setInt(1, matricula );
	sentencia.setDate(2, null);
	rs= sentencia.executeQuery();
	while (rs !=null && rs.next()){
		Horario h = new Horario();
		
		h.setFecha_alta_h(rs.getDate("fecha_alta_h"));
		h.setHora_desde(rs.getTime("hora_desde"));
		h.setHora_hasta(rs.getTime("hora_hasta"));
		h.setDia_semana(rs.getString("dia_semana"));
		
		
		horarios.add(h);
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
return horarios;
}}


