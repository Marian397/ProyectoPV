/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Nestor
 */
public class Precio {

    private int idPrecio;
    private int idProducto;
    private float precioCompra = 0;
    private float precioCuarto = 0;
    private float precioMedio = 0;
    private float precioUnidad = 0;
    private float medioMayoreo = 0;
    private float mayoreo = 0;
    private double cantidadMedioMayoreo = 0;
    private double cantidadMayoreo = 0;

    public int getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(int idPrecio) {
        this.idPrecio = idPrecio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getPrecioCuarto() {
        return precioCuarto;
    }

    public void setPrecioCuarto(float precioCuarto) {
        this.precioCuarto = precioCuarto;
    }

    public float getPrecioMedio() {
        return precioMedio;
    }

    public void setPrecioMedio(float precioMedio) {
        this.precioMedio = precioMedio;
    }

    public float getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(float precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public float getMedioMayoreo() {
        return medioMayoreo;
    }

    public void setMedioMayoreo(float medioMayoreo) {
        this.medioMayoreo = medioMayoreo;
    }

    public float getMayoreo() {
        return mayoreo;
    }

    public void setMayoreo(float mayoreo) {
        this.mayoreo = mayoreo;
    }

    public double getCantidadMedioMayoreo() {
        return cantidadMedioMayoreo;
    }

    public void setCantidadMedioMayoreo(double cantidadMedioMayoreo) {
        this.cantidadMedioMayoreo = cantidadMedioMayoreo;
    }

    public double getCantidadMayoreo() {
        return cantidadMayoreo;
    }

    public void setCantidadMayoreo(double cantidadMayoreo) {
        this.cantidadMayoreo = cantidadMayoreo;
    }
}
