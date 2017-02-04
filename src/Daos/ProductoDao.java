/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Beans.Marca;
import Beans.Producto;
import Beans.Categoria;
import Beans.Precio;
import Conexion.ConexionBD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nestor
 */
public class ProductoDao {

//MÉTODO QUE PERMITE ACTUALIZAR LA INFORMACIÓN Y PRECIOS DE UN PRODUCTO
    public boolean updateProducto(Object[] producto_precio) {

        Producto producto = (Producto) producto_precio[0];
        Precio precio = (Precio) producto_precio[1];

        String updateProducto = "UPDATE producto set nombreProducto= '" + producto.getNombreProducto() + "' , cantidad= " + producto.getCantidad() + " WHERE idProducto = " + producto.getIdProducto() + ";";
        String updatePrecios = "UPDATE precioproducto set precioCompra= " + precio.getPrecioCompra() + ", precioCuarto= " + precio.getPrecioCuarto() + ", precioMedio= " + precio.getPrecioMedio() + ", precioUnidad= " + precio.getPrecioUnidad() + ", medioMayoreo= " + precio.getMedioMayoreo() + ", mayoreo= " + precio.getMayoreo() + ", cantidadMedioMay= " + precio.getCantidadMedioMayoreo() + ", cantidadMayoreo= " + precio.getCantidadMayoreo() + " WHERE idPrecio= " + precio.getIdPrecio();
        boolean success = false;
        try {
            Connection db = ConexionBD.getConnection();

            CallableStatement cs1 = db.prepareCall(updateProducto);
            CallableStatement cs2 = db.prepareCall(updatePrecios);

            boolean update1 = cs1.executeUpdate() == 1;
            boolean update2 = cs2.executeUpdate() == 1;
            cs1.close();
            cs2.close();

            if (update1 && update2) {
                success = true;
            } else {
                success = false;
            }

            db.close();

        } catch (Exception e) {
            System.out.println("INCIDENCIAS AL INTENTAR ACTUALIZAR LOS DATOS DEL PRODUCTO" + e);
        }
        return success;
    }

    public boolean deleteProducto(int idProducto) {
        boolean delete = false;
        try {
            Connection db = ConexionBD.getConnection();
            CallableStatement cs = db.prepareCall("UPDATE producto SET estatus= 0 WHERE idProducto = " + idProducto);

            delete = cs.executeUpdate() == 1;

            cs.close();
            db.close();

        } catch (Exception e) {
            System.out.println("INCIDENCIAS AL INTENTAR ELIMINAR UN PRODUCTO deleteProducto " + e.getMessage());
        }
        return delete;
    }
//
//    public List<Producto> readProducto(int id) {
//     
//        List<Producto> listProducto = new ArrayList();
//        try {
//
//            Connection db = ConexionBD.getConnection();
//
//            CallableStatement cs = db.prepareCall("select nombreProducto from producto;");
//            ResultSet rs = cs.executeQuery();
//
//          
//
//            while (rs.next()) {
//                AseguradoraBean unAseguradoraBean = new AseguradoraBean();
//                unAseguradoraBean.setId(rs.getInt(1));
//                unAseguradoraBean.setNombre(rs.getString(2));
//                unAseguradoraBean.setTelefonoEmergencia(rs.getString(3));
//                unAseguradoraBean.setFechaAlta(rs.getString(4));
//                unAseguradoraBean.setEstatus(rs.getInt(5));
//
//                listAsociaciones.add(unAseguradoraBean);
//            }
//
//            cs.close();
//            rs.close();
//            db.close();
//
//        } catch (SQLException sqle) {
//            System.err.println("Error de AseguradoraDao.java en el método readAseguradoras: " + sqle.getMessage());
//        }
//        return listProducto;
//    }

    ////////////////////ReadMarca//////////////////////////////
    public List readMarca() {

        List listMarca = new ArrayList();
        try {

            Connection db = ConexionBD.getConnection();

            CallableStatement cs = db.prepareCall(" select nombreMarca from marca;");
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {

                listMarca.add(rs.getString(1));
            }

            cs.close();
            rs.close();
            db.close();

        } catch (SQLException sqle) {
            System.err.println("Error de ProductoDao.java en el método readMarca: " + sqle.getMessage());
        }
        return listMarca;
    }

    // MÉTODO QUE CONSULTA EL ÚLTIMO PRODUCTO REGISTRADO
    public Producto readLastProduct() {
        Producto producto = new Producto();
        try {
            Connection db = ConexionBD.getConnection();
            CallableStatement cs = db.prepareCall(" SELECT producto.* FROM `producto` ORDER BY `idProducto` DESC LIMIT 1;");
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombreProducto(rs.getString("nombreProducto"));
                producto.setCantidad(rs.getFloat("cantidad"));
                producto.setMedida(rs.getInt("medida"));
                producto.setEstatus(rs.getInt("estatus"));
            } else {
                producto = null;
            }

            cs.close();
            rs.close();
            db.close();

        } catch (SQLException sqle) {

            System.err.println("Error de ProductoDao.java en el método readLastProduct: " + sqle.getMessage());
        }
        return producto;
    }

    // MÉTODO QUE PERMITE INSERTAR LOS PRECIOS DE UN PRODUCTO (PESO/PIEZA)
    public boolean insertPreciosProducto(Precio precio, int medida) {
        boolean insertOk = false;
        String parameters = "", values = "";
        try {
            //TIPO DE MEDIDA PIEZA = 1, KILO = 2.
            if (medida == 2) {
                parameters = ",precioCuarto,precioMedio";
                values = ",?,?";

            }
            String query = "INSERT INTO precioproducto(idProducto,precioCompra,precioUnidad,medioMayoreo,mayoreo,cantidadMedioMay,cantidadMayoreo" + parameters + ") VALUES(?,?,?,?,?,?,?" + values + ");";
            Connection db = ConexionBD.getConnection();
            CallableStatement cs = db.prepareCall(query);
            cs.setInt(1, precio.getIdProducto());
            cs.setFloat(2, precio.getPrecioCompra());
            cs.setFloat(3, precio.getPrecioUnidad());
            cs.setFloat(4, precio.getMedioMayoreo());
            cs.setFloat(5, precio.getMayoreo());
            cs.setDouble(6, precio.getCantidadMedioMayoreo());
            cs.setDouble(7, precio.getCantidadMayoreo());
            if (medida == 2) {
                cs.setFloat(8, precio.getPrecioCuarto());
                cs.setFloat(9, precio.getPrecioMedio());
            }
            cs.execute();
            insertOk = true;
            cs.close();
            db.close();
        } catch (Exception e) {
            insertOk = false;
            System.out.println("ERROR EN EL MÉTODO insertPreciosProducto: " + e.getMessage());
        }
        return insertOk;
    }

    // MÉTODO QUE PERMITE INSERTAR LOS DATOS DE UN PRODUCTO
    public Producto insertProducto(Producto producto) {
        Producto nuevoProducto = null;
        try {
            Connection db = ConexionBD.getConnection();
            CallableStatement cs = db.prepareCall("INSERT INTO producto(nombreProducto,cantidad,medida) VALUES(?,?,?);");
            cs.setString(1, producto.getNombreProducto());
            cs.setFloat(2, producto.getCantidad());
            cs.setInt(3, producto.getMedida());
            cs.execute();
            //CONSULTA EL ÚLTIMO PRODUCTO INSERTADO
            nuevoProducto = readLastProduct();
            cs.close();
            db.close();
        } catch (Exception e) {
            nuevoProducto = null;
            System.out.println("ERROR EN EL MÉTODO insertProducto: " + e.getMessage());
        }
        return nuevoProducto;
    }

    // MÉTODO QUE CONSULTA TODOS LOS PRODUCTOS
    public ArrayList<Object[]> readAllProducts() {

        ArrayList<Object[]> productos = new ArrayList<>();

        try {
            Connection db = ConexionBD.getConnection();
            CallableStatement cs = db.prepareCall(" SELECT producto.*, precioproducto.* FROM `producto` join precioproducto on producto.idProducto = precioproducto.idProducto WHERE producto.estatus = 1 ORDER BY producto.nombreProducto;");
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Object[] producto_precios = new Object[2];

                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombreProducto(rs.getString("nombreProducto"));
                producto.setCantidad(rs.getFloat("cantidad"));
                producto.setMedida(rs.getInt("medida"));
                producto.setEstatus(rs.getInt("estatus"));

                Precio precio = new Precio();
                precio.setIdPrecio(rs.getInt("idPrecio"));
                precio.setIdProducto(rs.getInt("idProducto"));
                precio.setPrecioCompra(rs.getFloat("precioCompra"));
                precio.setPrecioCuarto(rs.getFloat("precioCuarto"));
                precio.setPrecioMedio(rs.getFloat("precioMedio"));
                precio.setPrecioUnidad(rs.getFloat("precioUnidad"));
                precio.setMayoreo(rs.getFloat("mayoreo"));
                precio.setMedioMayoreo(rs.getFloat("medioMayoreo"));
                precio.setCantidadMedioMayoreo(rs.getDouble("cantidadMedioMay"));
                precio.setCantidadMayoreo(rs.getDouble("cantidadMayoreo"));

                producto_precios[0] = producto;
                producto_precios[1] = precio;

                productos.add(producto_precios);
            }

            cs.close();
            rs.close();
            db.close();

        } catch (SQLException sqle) {

            System.err.println("Error de ProductoDao.java en el método readLastProduct: " + sqle.getMessage());
        }


        return productos;
    }
}
