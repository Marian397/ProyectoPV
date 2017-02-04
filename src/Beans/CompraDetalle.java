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
public class CompraDetalle {
    private int idCompraDetalle;
    private int cantidad;
    private double totalCompraD;
    private int producto;
    private int compra;

    public int getIdCompraDetalle() {
        return idCompraDetalle;
    }

    public void setIdCompraDetalle(int idCompraDetalle) {
        this.idCompraDetalle = idCompraDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotalCompraD() {
        return totalCompraD;
    }

    public void setTotalCompraD(double totalCompraD) {
        this.totalCompraD = totalCompraD;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getCompra() {
        return compra;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }
    
    
    
}
