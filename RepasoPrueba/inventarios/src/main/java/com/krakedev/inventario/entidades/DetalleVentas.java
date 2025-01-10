package com.krakedev.inventario.entidades;

import java.math.BigDecimal;

public class DetalleVentas {
    private int codigo;
    private Ventas cabecera;
    private Producto producto;
    private int cantidad;
    private BigDecimal precioVenta;
    private BigDecimal subtotal;
    private BigDecimal subtotalConIva;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Ventas getCabecera() {
        return cabecera;
    }

    public void setCabecera(Ventas cabecera) {
        this.cabecera = cabecera;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getSubtotalConIva() {
        return subtotalConIva;
    }

    public void setSubtotalConIva(BigDecimal subtotalConIva) {
        this.subtotalConIva = subtotalConIva;
    }
}