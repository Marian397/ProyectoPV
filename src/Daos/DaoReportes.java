/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Beans.Compra;
import Beans.Venta;
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
 * @author Nesty
 */
public class DaoReportes {

    public List<Compra> consultarCompras(String mes) {
        List<Compra> compras = new ArrayList();
        try {
            String consulta = "SELECT * FROM Compra WHERE fechaHoraCompra like '%" + mes + "%';";

            Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt(1));
                compra.setTotalCompra(rs.getDouble(2));
                compra.setFechaHoraCompra(rs.getString(3));
                compra.setFolioCompra(rs.getString(4));
                compras.add(compra);
            }
            conexion.close();
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DaoReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return compras;
    }

    //====================================================================================
    public List<Venta> consultarVentas(String mes) {
        List<Venta> ventas = new ArrayList();
        try {
            String consulta = "SELECT * FROM Venta WHERE fechaHoraVenta like '%" + mes + "%';";

            Connection conexion = ConexionBD.getConnection();
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt(1));
                venta.setTotalVenta(rs.getDouble(2));
                venta.setFechaHoraVenta(rs.getString(3));
                venta.setFolioVenta(rs.getString(4));
                venta.setUsuario(rs.getInt(5));
                ventas.add(venta);
            }
            conexion.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventas;
    }
    
       //====================================================================================
    public String especificas(String mes, String anio) {
        String fecha = "";
        if (mes.equals("Enero")) {
            fecha = anio + "01";
        } else if (mes.equals("Febrero")) {
            fecha = anio + "02";
        } else if (mes.equals("Marzo")) {
            fecha = anio + "03";
        } else if (mes.equals("Abril")) {
            fecha = anio + "04";
        } else if (mes.equals("Mayo")) {
            fecha = anio + "05";
        } else if (mes.equals("Junio")) {
            fecha = anio + "06";
        } else if (mes.equals("Julio")) {
            fecha = anio + "07";
        } else if (mes.equals("Agosto")) {
            fecha = anio + "08";
        } else if (mes.equals("Septiembre")) {
            fecha = anio + "09";
        } else if (mes.equals("Octubre")) {
            fecha = anio + "10";
        } else if (mes.equals("Noviembre")) {
            fecha = anio + "11";
        } else if (mes.equals("Diciembre")) {
            fecha = anio + "12";
        }
        return fecha;
    }

    //====================================================================================
    public double consultarTotalVenta(String mes) throws SQLException {
        double total = 0.0;

        String consulta = "SELECT SUM( totalVenta ) FROM VENTA WHERE fechaHoraVenta like '%" + mes + "%';";

        Connection conexion = ConexionBD.getConnection();
        PreparedStatement ps = conexion.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            total = rs.getDouble(1);
        }

        conexion.close();
        ps.close();
        rs.close();

        return total;
    }
    
    //====================================================================================
    public double consultarTotalCompra(String mes) throws SQLException {
        double total = 0.0;

        String consulta = "SELECT SUM( totalCompra ) FROM COMPRA WHERE fechaHoraCompra like '%" + mes + "%';";

        Connection conexion = ConexionBD.getConnection();
        PreparedStatement ps = conexion.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            total = rs.getDouble(1);
        }

        conexion.close();
        ps.close();
        rs.close();

        return total;
    }
  //====================================================================================
}
