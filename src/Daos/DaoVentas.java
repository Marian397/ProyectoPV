/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Beans.Compra;
import Beans.CompraDetalle;
import Beans.Producto;
import Beans.Venta;
import Beans.ventaDetalle;
import Conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Musica
 */
public class DaoVentas {
    
  String InsertarVenta="insert into venta(totalVenta,fechaHoraVenta,folioVenta,usuario)values(?,now(),?,?)";
  String consultaProducto="select codigo Producto from producto";
  String queryExistencia="UPDATE producto SET existencias=existencias-? WHERE codigo= ?";
  String consultaPrecio="select idProducto,precio,precioNeto,nombreProducto from producto where codigo=?";
  String ConsultaidCompra= "SELECT Max(idVenta) FROM venta";
  String InsertarVentaDetalle="insert into ventadetalle(cantidad,totalVentaD,producto,venta)values(?,?,?,null)";
  String updateIdVenta="update ventadetalle set venta=? where venta is null;";
  String consultarCantidad="select existencias from producto where codigo=?";
  String eliminarExistencia="UPDATE producto SET existencias=existencias+? WHERE codigo= ?";

//  
//       public boolean altaCompra(){
//           boolean insertOK = false;  
//       }
       
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
       
       
       
       
        public boolean ModificarExistencias(ventaDetalle detalle,String codigo) {
        boolean Modificar = false;
        try {
            Connection connect = ConexionBD.getConnection();
            PreparedStatement ps = connect.prepareStatement(queryExistencia);
             ps.setInt(1, detalle.getCantidad());
             ps.setString(2,codigo);
             Modificar=ps.executeUpdate()==1;
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
             p.setNombreProducto(rs.getString(4));
            
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
       
       
       public int ConsultaIdventa (){
        boolean existe=false;
        int res=0;
        try{
            Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(ConsultaidCompra);
            ResultSet rs = ps.executeQuery();
            rs.next();
            res=rs.getInt(1);
            System.out.println(""+res);
            ps.close();
            con.close();
        }catch(SQLException e){
            System.out.println("ERROR DE ID" + e);
        }
        return res;
    }
    
    public boolean altaVenta(Venta venta){
        boolean registroCorrecto = false;
       
        
        // conexion a la base de datos
        try {
             Connection conexion = ConexionBD.getConnection();
            // Preparar la consulta o sentencia SQL
              PreparedStatement ps ;
            ps = conexion.prepareStatement(InsertarVenta);
            //Pasar los atributos del bean al PrepareStatement que contiene la sentencia SQL.
            ps.setDouble(1,venta.getTotalVenta());
            ps.setString(2,venta.getFolioVenta());
             ps.setInt(3,venta.getUsuario());
           
           
          
           
           
            registroCorrecto = ps.executeUpdate() == 1;
            
            ps.close();
            conexion.close();
        } catch (SQLException e) {
            System.err.println("ERROR INSERTAR PERSONA");
            e.printStackTrace();
        }
    return registroCorrecto;
    }
   
     public boolean InsertarVentaDetalle(ventaDetalle detalle){
        boolean registroCorrecto = false;
       // int idcompra=ConsultaIdCompra();
        int id=0;
        // conexion a la base de datos
        try {
             Connection conexion = ConexionBD.getConnection();
            // Preparar la consulta o sentencia SQL
              PreparedStatement ps ;
            ps = conexion.prepareStatement(InsertarVentaDetalle);
            //Pasar los atributos del bean al PrepareStatement que contiene la sentencia SQL.
            ps.setInt(1,detalle.getCantidad());
            ps.setDouble(2,detalle.getTotalVentaD());
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
        int id=ConsultaIdventa();
        
          System.out.println("idcompra"+id);
        boolean Modificar = false;
        try {
            Connection connect = ConexionBD.getConnection();
            PreparedStatement ps = connect.prepareStatement(updateIdVenta);
             
             ps.setInt(1, id);
            
             Modificar = ps.executeUpdate() == 1;
             ps.close();
            connect.close();

        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return Modificar;    
        } 
  
public int ProductoCantidad(String condigo){
    int producto=0;
   // int cantidad=0;
    try {
            Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(consultarCantidad);
             ps.setString(1,condigo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            producto=rs.getInt(1);
            System.out.println(""+producto);
            ps.close();
            con.close();
        
    } catch (Exception e) {
    }
return producto;
} 
public boolean EliminarExistencias(ventaDetalle detalle,String codigo) {
        boolean Modificar = false;
        try {
            Connection connect = ConexionBD.getConnection();
            PreparedStatement ps = connect.prepareStatement(eliminarExistencia);
             ps.setInt(1, detalle.getCantidad());
             ps.setString(2, codigo);
             Modificar = ps.executeUpdate() == 1;
             ps.close();
            connect.close();
            }catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return Modificar;
     }
} 