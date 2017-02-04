/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Beans.Compra;
import Beans.CompraDetalle;
import Beans.Persona;
import Conexion.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import Beans.Producto;
import Beans.Usuario;
import Beans.ventaDetalle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Musica
 */
public class DaoCompras {
  String InsertarCompra="insert into compra(totalCompra,fechaHoraCompra,folioCompra)values(?,now(),?)";
  String consultaProducto="select codigo Producto from producto WHERE existencias>0";
  String queryExistencia="UPDATE producto SET existencias=existencias+? WHERE codigo= ?";
  String consultaPrecio="select idProducto,precio,precioNeto from producto where codigo=?";
  String ConsultaidCompra= "SELECT Max(idcompra) FROM compra";
  String InsertarCompraDetalle="insert into compradetalle(cantidad,totalCompraD,producto,compra)values(?,?,?,null)";
  String updateIdCompra="update compradetalle set compra=? where compra is null;";
  String eliminarExistencia="UPDATE producto SET existencias=existencias-? WHERE codigo= ?";
       public List consultarProducto(){
           List listaProducto= new ArrayList();
           try {
               Connection con=ConexionBD.getConnection();
               PreparedStatement ps=con.prepareStatement(consultaProducto);
               ResultSet rs=ps.executeQuery();
               listaProducto.clear();
               while (rs.next()) {                   
                   Producto producto=new Producto();
                   producto.setNombreProducto(rs.getString(1));
                   listaProducto.add(producto.getNombreProducto().toString());       
               }
               System.out.println("lista"+listaProducto);
               rs.close();
               ps.close();
               con.close();
           } catch (SQLException e) {
               System.out.println(e);
           }
           return  listaProducto;
          
       }
       
       
       
       
        public boolean ModificarExistencias(CompraDetalle detalle,String codigo) {
        boolean Modificar = false;
        try {
            Connection connect = ConexionBD.getConnection();
            PreparedStatement ps = connect.prepareStatement(queryExistencia);
            System.out.println(""+codigo);
             ps.setInt(1, detalle.getCantidad());
             ps.setString(2, codigo);
            
            Modificar=ps.executeUpdate()==1;
            System.out.println(""+Modificar);
            ps.close();
            connect.close();

        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return Modificar;
        
      
 }
      public Producto consultarPrecio(String Producto){
          Producto p=new Producto();
          try {
            Connection connect = ConexionBD.getConnection();
            PreparedStatement ps = connect.prepareStatement(consultaPrecio); 
            ps.setString(1,Producto);
            ResultSet rs=ps.executeQuery();
            rs.next();
            p.setPrecioNeto(rs.getDouble(3));
            p.setPrecio(rs.getDouble(2));
             p.setIdProducto(rs.getInt(1));
            
            System.out.println("el precio del producto es"+p.getPrecioNeto());            
           
          } catch (SQLException e) {
              
              
          }
           return p;
         
   
        }
       public static void main(String[] args) throws SQLException {
        DaoCompras d=new DaoCompras();
        
//        d.consultarProducto();
//        List lista = new ArrayList();
//        lista.add(d);
//           System.out.println(""+lista);
        
           System.out.println("Resultado" +d.consultarPrecio("1234")); 
           
           
        
        
    }
       
       
          public int ConsultaIdCompra (){
        boolean existe=false;
        int res=0;
        try{
            Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(ConsultaidCompra);
            ResultSet rs = ps.executeQuery();
            rs.next();
            res=rs.getInt(1);
           
            ps.close();
            con.close();
        }catch(SQLException e){
            System.out.println("ERROR DE ID" + e);
        }
        return res;
    }
    
    public boolean altaCompra(Compra compra){
        boolean registroCorrecto = false;
       
        
        // conexion a la base de datos
        try {
             Connection conexion = ConexionBD.getConnection();
            // Preparar la consulta o sentencia SQL
              PreparedStatement ps ;
            ps = conexion.prepareStatement(InsertarCompra);
            //Pasar los atributos del bean al PrepareStatement que contiene la sentencia SQL.
            ps.setDouble(1,compra.getTotalCompra());
            ps.setString(2,compra.getFolioCompra());
           
           
          
           
           
            registroCorrecto = ps.executeUpdate() == 1;
            
            ps.close();
            conexion.close();
        } catch (SQLException e) {
            System.err.println("ERROR INSERTAR PERSONA");
            e.printStackTrace();
        }
    return registroCorrecto;
    }
   
     public boolean InsertarCompraDetalle(CompraDetalle detalle){
        boolean registroCorrecto = false;
       // int idcompra=ConsultaIdCompra();
        int id=0;
        // conexion a la base de datos
        try {
             Connection conexion = ConexionBD.getConnection();
            // Preparar la consulta o sentencia SQL
              PreparedStatement ps ;
            ps = conexion.prepareStatement(InsertarCompraDetalle);
            //Pasar los atributos del bean al PrepareStatement que contiene la sentencia SQL.
            ps.setInt(1,detalle.getCantidad());
            ps.setDouble(2,detalle.getTotalCompraD());
            ps.setInt(3,detalle.getProducto());
           
          //  ps.setInt(4,id);
           registroCorrecto = ps.executeUpdate() == 1;
            
            ps.close();
            conexion.close();
        } catch (SQLException e) {
            System.err.println("ERROR INSERTAR USUARIOS");
            e.printStackTrace();
        }
    return registroCorrecto;
    }
       
     
     
     //////////////////////////////////////////////////////
      public boolean ModificarId() {
        int id=ConsultaIdCompra();
        
          System.out.println("idcompra"+id);
        boolean Modificar = false;
        try {
            Connection connect = ConexionBD.getConnection();
            PreparedStatement ps = connect.prepareStatement(updateIdCompra);
             
             ps.setInt(1, id);
            
             Modificar = ps.executeUpdate() == 1;
             ps.close();
            connect.close();

        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return Modificar;    
        
        
        
 } 
  
     
    public boolean EliminarExistencias(CompraDetalle detalle,String codigo) {
        boolean Modificar = false;
        try {
            Connection connect = ConexionBD.getConnection();
            PreparedStatement ps = connect.prepareStatement(eliminarExistencia);

             ps.setInt(1, detalle.getCantidad());
             ps.setString(2, codigo);
            
              Modificar = ps.executeUpdate() == 1;

            ps.close();
            connect.close();

        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return Modificar;
          }
      
     
}
 


          