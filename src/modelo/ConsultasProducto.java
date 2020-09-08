package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ConsultasProducto extends Conexion{
	PreparedStatement ps; 
    ResultSet rs; 
    
    public boolean insertar(Producto producto){
        //Desde aqui mandamos a llamar el metodo para hacer establecer la conexion con nuestra DB
        Connection conexion = getConnection();
    
    
    try{
        ps = conexion.prepareStatement("insert into producto (platillo, codigo, precio, cantidad, ingredientes) values (?,?,?,?,?)");
        ps.setString(1, producto.getPlatillo());
        ps.setString(2, producto.getCodigo());
        ps.setString(3, producto.getPrecio());
        ps.setNString(4, producto.getCantidad());
        ps.setString(5, producto.getIngredientes());
        
        
        int resultado = ps.executeUpdate(); 
        
       
        if(resultado > 0){
            return true;
        }else{
            return false;
        }
        
    }catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Error: "+ex);
        return false;
    }finally{
        try{
            conexion.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al cerrar Conexion: "+ex);
        }
    }
}
    
    
    public boolean buscar(Producto producto){
        //Desde aqui mandamos a llamar el metodo para hacer establecer la conexion con nuestra DB
        Connection conexion = getConnection(); 
        
        //Hacemos un bloque try-catch por si algo malo sucede al tratar de hacer la conexion con la DB
        try{
            ps = conexion.prepareStatement("select * from producto where codigo=?");
            ps.setString(1, producto.getCodigo());
            rs = ps.executeQuery(); //Se usa esta sentencia por que vamos a tener un resultado de nuestra DB
            
            //Significa que encontramos un contenido siguiendo esta consulta
            if(rs.next() == true){
            	producto.setIdProducto(rs.getInt("idProducto"));
            	producto.setPlatillo(rs.getString("platillo"));
            	producto.setCodigo(rs.getString("codigo"));
            	producto.setPrecio(rs.getString("precio"));
            	producto.setCantidad(rs.getString("cantidad"));
            	producto.setIngredientes(rs.getString("ingredientes"));
                  
                //Si encontro a la persona por medio de su clave, retorna verdadero
                return true;
                
            }else{
                return false;
            }
            
        }catch(Exception ex){
            //Por si ocurre algun error con la Db aqui nos mostrara el error que este pasando
            JOptionPane.showMessageDialog(null, "Error: "+ex);
            //Si cai aqui la exception que tambien retorne un false
            return false;
        }finally{
            try{
                conexion.close();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error al cerrar Conexion: "+ex);
            }
        }
    }
    
    
    
    public boolean modificar(Producto producto){
       
        Connection conexion = getConnection(); 
       
        try{
            ps = conexion.prepareStatement("update producto set platillo=?, codigo=?, precio=?, cantidad=?, ingredientes=? where idProducto=?");
            ps.setString(1, producto.getPlatillo());
            ps.setString(2, producto.getCodigo());
            ps.setString(3, producto.getPrecio());
            ps.setString(4, producto.getCantidad());
            ps.setString(5, producto.getIngredientes());
            ps.setInt(6, producto.getIdProducto());
                    
            int resultado = ps.executeUpdate(); //La insercion que se hace se almacena en resultado
            
            //Si la insercion ha sido correcta retornamos true
            if(resultado > 0){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception ex){
            //Por si ocurre algun error con la Db aqui nos mostrara el error que este pasando
            JOptionPane.showMessageDialog(null, "Error: "+ex);
            //Si cai aqui la exception que tambien retorne un false
            return false;
        }finally{
            try{
                conexion.close();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error al cerrar Conexion: "+ex);
            }
        }
    }
    
    
    
    
    public boolean eliminar(Producto producto){
       
        Connection conexion = getConnection(); 
        
        
        try{
            
            ps = conexion.prepareStatement("delete from producto where idProducto=?");
            ps.setInt(1, producto.getIdProducto());
                    
            int resultado = ps.executeUpdate(); 
            
            
            if(resultado > 0){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception ex){
            
            JOptionPane.showMessageDialog(null, "Error: "+ex);
            
            return false;
        }finally{
            try{
                conexion.close();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error al cerrar Conexion: "+ex);
            }
        }
    }
    
}