/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Beans.Marca;
import Beans.Producto;
import Beans.Categoria;
import Conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Musica
 */
public class DaoAlmacen1 {

    //====================================================================================
    public List<Object[]> consultarProductos() {
        List<Object[]> productos = new ArrayList();
        try {
            String consulta = "SELECT p.idProducto,p.codigo,p.nombreProducto,p.precio,p.precioNeto,m.nombreMarca,p.existencias FROM producto p JOIN marca m ON  m.idMarca = p.marca;;";

            Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] beans = new Object[2];
                Producto producto = new Producto();
                Marca marca = new Marca();

                producto.setIdProducto(rs.getInt(1));
                producto.setCodigo(rs.getString(2));
                producto.setNombreProducto(rs.getString(3));
                producto.setPrecio(rs.getDouble(4));
                producto.setPrecioNeto(rs.getDouble(5));
                marca.setNombreMarca(rs.getString(6));
                producto.setExistencias(rs.getInt(7));
                beans[0] = producto;
                beans[1] = marca;
                productos.add(beans);
                System.out.println("poductos"+producto);
            }
            conexion.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }

    //====================================================================================
    public List<Categoria> consultarCategoria() {
        List<Categoria> categorias = new ArrayList();

        try {
            String consulta = "SELECT*FROM RUBRO;";

            Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Categoria categoria = new Categoria();

                categoria.setIdRubro(rs.getInt(1));
                categoria.setNombreRubro(rs.getString(2));
                categorias.add(categoria);
            }
            conexion.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoReportes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categorias;
    }

    //====================================================================================
    public List<Object[]> consultarProductoEspecifico(String categoria) {
        List<Object[]> productos = new ArrayList();
        try {
            String consulta = "SELECT p.idProducto,p.codigo,p.nombreProducto,p.precio,p.precioNeto,m.nombreMarca,p.existencias FROM PRODUCTO p JOIN MARCA m ON  m.idMarca = p.marca join RUBRO r on p.rubro = r.idRubro where r.nombreRubro   = ?;";

            Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setString(1, categoria);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] beans = new Object[2];
                Producto producto = new Producto();
                Marca marca = new Marca();
                
                producto.setIdProducto(rs.getInt(1));
                System.out.println(producto.getIdProducto()+"::::::::::::::::");
                producto.setCodigo(rs.getString(2));
                producto.setNombreProducto(rs.getString(3));
                System.out.println(producto.getNombreProducto()+"::::::::::::::::");
                producto.setPrecio(rs.getDouble(4));
                producto.setPrecioNeto(rs.getDouble(5));
                marca.setNombreMarca(rs.getString(6));
                producto.setExistencias(rs.getInt(7));
                beans[0] = producto;
                beans[1] = marca;
                productos.add(beans);
            }
            conexion.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return productos;
    }

    //====================================================================================
    public List<Object[]> consultarProductoNombre(String nombre) {
        List<Object[]> productos = new ArrayList();
        try {
            String consulta = "SELECT p.idProducto,p.codigo,p.nombreProducto,p.precio,p.precioNeto,m.nombreMarca,p.existencias FROM PRODUCTO p JOIN MARCA m ON  m.idMarca = p.marca  where p.nombreProducto   = ?;";

            Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] beans = new Object[2];
                Producto producto = new Producto();
                Marca marca = new Marca();
                
                producto.setIdProducto(rs.getInt(1));
                System.out.println(producto.getIdProducto()+"::::::::::::::::");
                producto.setCodigo(rs.getString(2));
                producto.setNombreProducto(rs.getString(3));
                System.out.println(producto.getNombreProducto()+"::::::::::::::::");
                producto.setPrecio(rs.getDouble(4));
                producto.setPrecioNeto(rs.getDouble(5));
                marca.setNombreMarca(rs.getString(6));
                producto.setExistencias(rs.getInt(7));
                beans[0] = producto;
                beans[1] = marca;
                productos.add(beans);
            }
            conexion.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(((Producto) productos[0]).getNombreProducto()+"::::::::::::::::");
        return productos;
    }
}
