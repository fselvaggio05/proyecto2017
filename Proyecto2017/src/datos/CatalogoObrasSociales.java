package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidad.*;

public class CatalogoObrasSociales {
	
	
	public void altaPractica (ObraSocial o){
		// permite el registro de una nueva obra social
				ResultSet rs=null;
				PreparedStatement stmt=null;
				
				
				try {
					stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
							"insert into obra_social (codigo_os, nombre) values (?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
					stmt.setInt(1, o.getCodigo_os());
					stmt.setString(2, o.getNombre_os());
							
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

    public void bajaPractica (int codigo_os){
    	
    	// permite eliminar cualquier obra social por codigo_os
    	
    			PreparedStatement stmt=null;
    			try {
    				stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
    						"DELETE from obra_social where codigo_os = ?"
    						);
    				stmt.setInt(1, codigo_os);
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
}
