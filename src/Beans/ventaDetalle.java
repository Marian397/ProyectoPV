/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author kelly Jimenez
 */
public class ventaDetalle {
    private int idVentaDetalle;
    private int cantidad;
    private double totalVentaD;
    private int producto;
    private int venta;
    private String codigo;
    public int getIdVentaDetalle() {
        return idVentaDetalle;
    }

    public void setIdVentaDetalle(int idVentaDetalle) {
        this.idVentaDetalle = idVentaDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotalVentaD() {
        return totalVentaD;
    }

    public void setTotalVentaD(double totalVentaD) {
        this.totalVentaD = totalVentaD;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    
    
}
