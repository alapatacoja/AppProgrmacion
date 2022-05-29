package com.example.taserfan.DTO;

import java.sql.Date;

public class VehiculoDTO {

    private String matricula;
    private String marca;
    private String color;
    private int preciohora;
    private String descripcion;
    private int bateria;
    private Date fecha;
    private int carnettipo;
    private String estado;

    public VehiculoDTO(String matricula, String marca, String color, int preciohora, String descripcion, int bateria, Date fecha, int carnettipo, String estado) {
        this.matricula = matricula;
        this.marca = marca;
        this.color = color;
        this.preciohora = preciohora;
        this.descripcion = descripcion;
        this.bateria = bateria;
        this.fecha = fecha;
        this.carnettipo = carnettipo;
        this.estado = estado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPreciohora() {
        return preciohora;
    }

    public void setPreciohora(int preciohora) {
        this.preciohora = preciohora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCarnettipo() {
        return carnettipo;
    }

    public void setCarnettipo(int carnettipo) {
        this.carnettipo = carnettipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "VehiculoDTO{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", color='" + color + '\'' +
                ", preciohora=" + preciohora +
                ", descripcion='" + descripcion + '\'' +
                ", bateria=" + bateria +
                ", fecha=" + fecha +
                ", carnettipo=" + carnettipo +
                ", estado='" + estado + '\'' +
                '}';
    }
}
