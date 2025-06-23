package com.mycompany.tpi_programacion;

import com.mycompany.tpi_programacion.controlador.ControladorTorneo;

public class TPI_Programacion {

    public static void main(String[] args) {
        ControladorTorneo controlador = new ControladorTorneo();
        System.out.println("=========================================");
        System.out.println("   Bienvenido a los Playoff de Basquet");
        System.out.println("=========================================");
        System.out.println("Este sistema permite gestionar un torneo de eliminacion directa");
        System.out.println("dividido en dos conferencias: Este y Oeste.");
        System.out.println("Podras registrar equipos (8 equipos en total, 4 por conferencia), asignarlos a conferencias,");
        System.out.println("generar los enfrentamientos iniciales, ingresar resultados");
        System.out.println("y ver el avance del torneo hasta la final");
        controlador.ejecutarMenu();
    }
}
