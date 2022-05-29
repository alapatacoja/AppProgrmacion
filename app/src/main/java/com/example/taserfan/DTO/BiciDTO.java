package com.example.taserfan.DTO;

import java.sql.Date;

public class BiciDTO extends VehiculoDTO {

    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BiciDTO(String matricula, String marca, String color, int preciohora, String descripcion, int bateria, Date fecha, int carnettipo, String estado, String tipo) {
        super(matricula, marca, color, preciohora, descripcion, bateria, fecha, carnettipo, estado);
        this.tipo = tipo;
    }
}
