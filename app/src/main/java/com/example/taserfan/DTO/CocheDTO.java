package com.example.taserfan.DTO;


import java.sql.Date;

public class CocheDTO extends VehiculoDTO {

    private int plazas;
    private int puertas;

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    public CocheDTO(String matricula, String marca, String color, int preciohora, String descripcion, int bateria, Date fecha, int carnettipo, String estado, int plazas, int puertas) {
        super(matricula, marca, color, preciohora, descripcion, bateria, fecha, carnettipo, estado);
        this.plazas = plazas;
        this.puertas = puertas;
    }
}
