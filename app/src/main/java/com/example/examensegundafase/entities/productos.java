package com.example.examensegundafase.entities;

public class productos {
    private String imagenUrl;
    private String nombreProducto;
    private String precioProducto;
    private boolean estaAgregado;

    public productos() {
    }

    public productos(String imagenUrl, String nombreProducto, String precioProducto, boolean estaAgregado) {
        this.imagenUrl = imagenUrl;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.estaAgregado = estaAgregado;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(String precioProducto) {
        this.precioProducto = precioProducto;
    }

    public boolean isEstaAgregado() {
        return estaAgregado;
    }

    public void setEstaAgregado(boolean estaAgregado) {
        this.estaAgregado = estaAgregado;
    }
}
