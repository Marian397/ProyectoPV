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
public class Venta {
    private int idVenta;
    private double totalVenta;
    private String fechaHoraVenta;
    private String folioVenta;
    private int usuario;

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getFechaHoraVenta() {
        return fechaHoraVenta;
    }

    public void setFechaHoraVenta(String fechaHoraVenta) {
        this.fechaHoraVenta = fechaHoraVenta;
    }

    public String getFolioVenta() {
        return folioVenta;
    }

    public void setFolioVenta(String folioVenta) {
        this.folioVenta = folioVenta;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    
    
}
