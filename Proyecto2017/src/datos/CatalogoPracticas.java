package datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidad.*;

public class CatalogoPracticas {

	public void altaPractica (Practica p){
		// permite el registro de una nueva practica
				ResultSet rs=null;
				PreparedStatement stmt=null;
				
				
				try {
					stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
							"insert into practica (codigo_practica, descripcion_practica) values (?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
					stmt.setInt(1, p.getCodigo_practica());
					stmt.setString(2, p.getDescripcion_practica());
				
					
					
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

    public void bajaPractica (int codigo_practica){
	// permite eliminar cualquier practica por codigo_practica
	
			PreparedStatement stmt=null;
			try {
				stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
						"DELETE from practica where codigo_practica = ?"
						);
				stmt.setInt(1, codigo_practica);
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
			
	
	
	
}}

	public Practica buscarPractica(int cod_practica) {

		//sirve para buscar una practica
		ResultSet rs=null;
PreparedStatement stmt=null;
Practica p= new Practica();
	try {
	stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
			"select descripcion_practica from practica where codigo_practica = ?"
			);
	stmt.setInt(1, cod_practica);
	rs = stmt.executeQuery();
	if(rs !=null && rs.next()){
    	p.setDescripcion_practica((rs.getString("descripcion_pactica")));
		
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
}
