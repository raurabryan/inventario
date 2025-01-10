package com.krakedev.inventario.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Ventas {
    private int codigo;
    private Date fecha;
    private BigDecimal totalSinIva;
    private BigDecimal iva;
    private BigDecimal total;
    private ArrayList<DetalleVentas> detalles;

    public Ventas() {}

    public Ventas(int codigo, Date fecha, BigDecimal totalSinIva, BigDecimal iva, BigDecimal total, ArrayList<DetalleVentas> detalles) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.totalSinIva = totalSinIva;
        this.iva = iva;
        this.total = total;
        this.detalles = detalles;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotalSinIva() {
        return totalSinIva;
    }

    public void setTotalSinIva(BigDecimal totalSinIva) {
        this.totalSinIva = totalSinIva;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ArrayList<DetalleVentas> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleVentas> detalles) {
        this.detalles = detalles;
    }
}
