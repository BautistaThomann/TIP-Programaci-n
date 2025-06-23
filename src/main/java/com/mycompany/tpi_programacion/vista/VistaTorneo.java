package com.mycompany.tpi_programacion.vista;

import java.util.Scanner;

public class VistaTorneo {

    Scanner sc = new Scanner(System.in);

    public int menu() {

        int opcion = 0;

        System.out.println("\n=========================================");
        System.out.println("            MENU DE OPCIONES");
        System.out.println("=========================================");
        System.out.println("1. Registrar equipo");
        System.out.println("2. Registrar administrador");
        System.out.println("3. Listar equipos");
        System.out.println("4. Iniciar Playoff");
        System.out.println("5. Registrar resultados");
        System.out.println("6. Generar semifinal");
        System.out.println("7. Registrar resultados semifinal");
        System.out.println("8. Generar Final");
        System.out.println("9. Registrar resultados finales");
        System.out.println("0. Salir");
        System.out.println("Opcion: ");

        opcion = sc.nextInt();
        sc.nextLine();
        return opcion;

    }

    public void mensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String pedirDato(String mensaje) {
        System.out.println(mensaje);
        return sc.nextLine();
    }

}
