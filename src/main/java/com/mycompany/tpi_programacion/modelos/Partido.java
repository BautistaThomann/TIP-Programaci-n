package com.mycompany.tpi_programacion.modelos;

public class Partido {
    private int id;
    private String equipo1;
    private String equipo2;
    private int puntajeEquipo1;
    private int puntajeEquipo2;
    private String equipoGanador;

    public Partido(int id, String equipo1, String equipo2, int puntajeEquipo1, int puntajeEquipo2, String equipoGanador) {
        this.id = id;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.puntajeEquipo1 = puntajeEquipo1;
        this.puntajeEquipo2 = puntajeEquipo2;
        this.equipoGanador = equipoGanador;
    }

    public int getId() {
        return id;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public int getPuntajeEquipo1() {
        return puntajeEquipo1;
    }

    public int getPuntajeEquipo2() {
        return puntajeEquipo2;
    }

    public String getEquipoGanador() {
        return equipoGanador;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public void setPuntajeEquipo1(int puntajeEquipo1) {
        this.puntajeEquipo1 = puntajeEquipo1;
    }

    public void setPuntajeEquipo2(int puntajeEquipo2) {
        this.puntajeEquipo2 = puntajeEquipo2;
    }

    public void setEquipoGanador(String equipoGanador) {
        this.equipoGanador = equipoGanador;
    }

    @Override
    public String toString() {
        return "Partido{" + "id=" + id + ", equipo1=" + equipo1 + ", equipo2=" + equipo2 + ", puntajeEquipo1=" + puntajeEquipo1 + ", puntajeEquipo2=" + puntajeEquipo2 + ", equipoGanador=" + equipoGanador + '}';
    }
}
