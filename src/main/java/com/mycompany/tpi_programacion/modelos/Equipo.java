package com.mycompany.tpi_programacion.modelos;

public class Equipo {
    private int id;
    private String nombre;
    private int cantidadJugadores;
    private String conferencia;
    private int partidosGanados;
    private int partidosPerdidos;

    public Equipo(int id, String nombre, int cantidadJugadores, String conferencia, int partidosGanados, int partidosPerdidos) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadJugadores = cantidadJugadores;
        this.conferencia = conferencia;
        this.partidosGanados = partidosGanados;
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public String getConferencia() {
        return conferencia;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidadJugadores(int cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    @Override
    public String toString() {
        return "Equipo{" + "id=" + id + ", nombre=" + nombre + ", cantidadJugadores=" + cantidadJugadores + ", conferencia=" + conferencia + ", partidosGanados=" + partidosGanados + ", partidosPerdidos=" + partidosPerdidos + '}';
    }

    
    
}
