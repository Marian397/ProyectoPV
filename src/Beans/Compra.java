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
public class Compra {
    private int idCompra;
    private double totalCompra;
    private String fechaHoraCompra;
    private String folioCompra;

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public String getFechaHoraCompra() {
        return fechaHoraCompra;
    }

    public void setFechaHoraCompra(String fechaHoraCompra) {
        this.fechaHoraCompra = fechaHoraCompra;
    }

    public String getFolioCompra() {
        return folioCompra;
    }

    public void setFolioCompra(String folioCompra) {
        this.folioCompra = folioCompra;
    }
    
    
}
