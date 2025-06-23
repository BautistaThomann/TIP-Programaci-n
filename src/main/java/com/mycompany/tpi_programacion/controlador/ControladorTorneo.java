package com.mycompany.tpi_programacion.controlador;

import com.mycompany.tpi_programacion.modelos.Administrador;
import com.mycompany.tpi_programacion.modelos.Equipo;
import com.mycompany.tpi_programacion.modelos.Partido;
import com.mycompany.tpi_programacion.vista.VistaTorneo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControladorTorneo {

    VistaTorneo vista = new VistaTorneo();
    List<Equipo> equipos = new ArrayList<>();
    List<Administrador> admin = new ArrayList<>();
    List<Partido> partido1 = new ArrayList<>();
    List<Partido> partido2 = new ArrayList<>();
    List<Partido> partido3 = new ArrayList<>();
    List<Partido> partido4 = new ArrayList<>();
    List<Partido> semifinalEste = new ArrayList<>();
    List<Partido> semifinalOeste = new ArrayList<>();
    List<Partido> finalTorneo = new ArrayList<>();

    public void ejecutarMenu() {

        int opcion = 0;

        do {
            opcion = vista.menu();

            switch (opcion) {
                case 1 ->
                    registrarEquipo();
                case 2 ->
                    registrarAdministrador();
                case 3 ->
                    listarEquipos();
                case 4 ->
                    iniciarPlayoff();
                case 5 ->
                    registrarResultados();
                case 6 ->
                    generarSemifinales();
                case 7 ->
                    registrarResultadosSemifinales();
                case 8 ->
                    generarFinalTorneo();
                case 9 ->
                    registrarResultadosFinales();
            }

        } while (opcion != 0);

    }

    public void registrarEquipo() {
        int id = Integer.parseInt(vista.pedirDato("Ingrese el id del equipo: "));
        String nombreEquipo = vista.pedirDato("Ingrese el nombre del equipo").toUpperCase();
        int cantidadJugadores = Integer.parseInt(vista.pedirDato("Ingrese la cantidad de jugadores: "));
        String conferencia = vista.pedirDato("Ingrese la conferencia a la que pertenece el equipo: ");
        Equipo equipo = new Equipo(id, nombreEquipo, cantidadJugadores, conferencia, 0, 0);
        equipos.add(equipo);
        System.out.println("Equipo registrado!");

    }

    public void registrarAdministrador() {
        int id = Integer.parseInt(vista.pedirDato("Ingrese el id del administrador: "));
        String nombreAdmin = vista.pedirDato("Ingrese el nombre del administrador: ");
        System.out.println("Administrador registrado!");
    }

    public void listarEquipos() {
        for (Equipo e : equipos) {
            vista.mensaje(e.toString());
        }
    }

    public void iniciarPlayoff() {
        List<Equipo> este = new ArrayList<>();
        List<Equipo> oeste = new ArrayList<>();

        for (Equipo e : equipos) {
            if (e.getConferencia().equalsIgnoreCase("Este")) {
                este.add(e);
            } else if (e.getConferencia().equalsIgnoreCase("Oeste")) {
                oeste.add(e);
            }
        }

        if (este.size() == 4 && oeste.size() == 4) {
            // sigue con el torneo, por que si o si tienen que ser 4 equipos por conferencia
        } else {
            vista.mensaje("Debe ingresar al menos 4 equipos por conferencia");
            return;
        }

        // hacemos el sorteo de los equipos
        Collections.shuffle(este);
        Collections.shuffle(oeste);

        Partido p1 = new Partido(este.get(0), este.get(1), "Partido Este 1");
        Partido p2 = new Partido(este.get(2), este.get(3), "Partido Este 2");

        partido1.add(p1);
        partido2.add(p2);

        Partido p3 = new Partido(oeste.get(0), oeste.get(1), "Partido Oeste 1");
        Partido p4 = new Partido(oeste.get(2), oeste.get(3), "Partido Oeste 2");

        partido3.add(p3);
        partido4.add(p4);

        vista.mensaje("Los Playoff se han iniciado correctamente");
        vista.mensaje("Cruces: ");
        vista.mensaje("==========================================");
        vista.mensaje("Partido Este 1 " + este.get(0).getNombre() + " VS " + este.get(1).getNombre());
        vista.mensaje("Partido Este 2 " + este.get(2).getNombre() + " VS " + este.get(3).getNombre());
        vista.mensaje("Partido Oeste 3 " + oeste.get(0).getNombre() + " VS " + oeste.get(1).getNombre());
        vista.mensaje("Partido Oeste 4 " + oeste.get(2).getNombre() + " VS " + oeste.get(3).getNombre());

    }

    public void registrarResultados() {
        // si o si tiene q haber partidos para continuar con el torneo
        if (partido1.isEmpty() && partido2.isEmpty() && partido3.isEmpty() && partido4.isEmpty()) {
            vista.mensaje("No hay partidos generados todavia");
            return;
        }
        
        for (Partido p : partido1) {
            registrarResultadoSerie(p);
        }
        for (Partido p : partido2) {
            registrarResultadoSerie(p);
        }
        for (Partido p : partido3) {
            registrarResultadoSerie(p);
        }
        for (Partido p : partido4) {
            registrarResultadoSerie(p);
        }

    }

    public void registrarResultadoSerie(Partido partido) {
        
        int victoriasEquipo1 = 0;
        int victoriasEquipo2 = 0;
        int nroPartido = 1;
        
        // si ninguno de los 2 llego a cuatro partidos ganados se ejecuta (condicion del while)
        while (victoriasEquipo1 < 4 && victoriasEquipo2 < 4) {
            vista.mensaje("Partido " + nroPartido + " de la serie: " + partido.getNombrePartido());
            
            // pide los puntos de los equipos
            int puntos1 = Integer.parseInt(vista.pedirDato("Puntaje de " + partido.getEquipo1().getNombre() + ":"));
            int puntos2 = Integer.parseInt(vista.pedirDato("Puntaje de " + partido.getEquipo2().getNombre() + ":"));
            
            // compara los ganadores y se suman a los partidos ganados
            if (puntos1 > puntos2) {
                victoriasEquipo1++;
            } else {
                victoriasEquipo2++;
            }
            
            // se suma el numero de partido (ya que es al mejor de 7)
            nroPartido++;
        }
        
        // hace la condicion del torneo que es al mejor de 7
        if (victoriasEquipo1 == 4) {
            partido.setEquipoGanador(partido.getEquipo1().getNombre());
        } else {
            partido.setEquipoGanador(partido.getEquipo2().getNombre());
        }

        vista.mensaje("Ganador de la serie: " + partido.getEquipoGanador());
    }

    public void generarSemifinales() {
        if (partido1.isEmpty() || partido2.isEmpty() || partido3.isEmpty() || partido4.isEmpty()) {
            vista.mensaje("Tenes que registrar resultados de los cuartos antes de generar semifinales");
            return;
        }
        
        // mete los ganadores de los cuartos a la semifinal
        Equipo ganadorEste1 = obtenerGanador(partido1);
        Equipo ganadorEste2 = obtenerGanador(partido2);
        semifinalEste.add(new Partido(ganadorEste1, ganadorEste2, "Semifinal Este"));

        Equipo ganadorOeste1 = obtenerGanador(partido3);
        Equipo ganadorOeste2 = obtenerGanador(partido4);
        semifinalOeste.add(new Partido(ganadorOeste1, ganadorOeste2, "Semifinal Oeste"));

        // muestra como quedaron las semifinales
        vista.mensaje("Semifinales generadas correctamente!!");
        vista.mensaje("Semifinal Este: " + ganadorEste1.getNombre() + " VS " + ganadorEste2.getNombre());
        vista.mensaje("Semifinal Oeste: " + ganadorOeste1.getNombre() + " VS " + ganadorOeste2.getNombre());

    }
    
    // busca el ganador
    public Equipo obtenerGanador(List<Partido> lista) {
        // chequea que la lista no este vacia
        if (!lista.isEmpty()) {
            // obtiene el nombre del equipo ganador
            String nombreGanador = lista.get(0).getEquipoGanador();
            
            if (nombreGanador.equals(lista.get(0).getEquipo1().getNombre())) {
                // devuelve como ganador al equipo que este primero en la lista
                return lista.get(0).getEquipo1();
            } else {
                // devuelve como ganador al equipo que este segundo en la lista
                return lista.get(0).getEquipo2();
            }
        }
        // y si la lista esta vacia devuelve null (no hay ganador)
        return null;

    }

    public void registrarResultadosSemifinales() {
        if (semifinalEste.isEmpty() || semifinalOeste.isEmpty()) {
            vista.mensaje("No se han generado las semifinales todavia");
            return;
        }

        vista.mensaje("Registrando resultados de la Semifinal del Este: ");
        for (Partido p : semifinalEste) {
            registrarResultadoSerie(p);
        }

        vista.mensaje("Registrando resultados de la Semifinal del Oeste: ");
        for (Partido p : semifinalOeste) {
            registrarResultadoSerie(p);
        }
    }

    public void generarFinalTorneo() {
        if (semifinalEste.isEmpty() || semifinalOeste.isEmpty()) {
            vista.mensaje("Primero tenes que jugar las semifinales para seguir con el torneo");
            return;
        }

        Equipo finalistaEste = obtenerGanador(semifinalEste);
        Equipo finalistaOeste = obtenerGanador(semifinalOeste);

        Partido finalPartido = new Partido(finalistaEste, finalistaOeste, "Final del Torneo");
        finalTorneo.add(finalPartido);

        vista.mensaje("Final del torneo generada!!");
        vista.mensaje("Se enfrentan: " + finalistaEste.getNombre() + " VS " + finalistaOeste.getNombre());
    }

    public void registrarResultadosFinales() {
        if (finalTorneo.isEmpty()) {
            vista.mensaje("La final no se genero");
            return;
        }
        
        // muestra el ganador del torneo
        vista.mensaje("Registrando resultado de la gran final:");

        Partido finalPartido = finalTorneo.get(0);
        registrarResultadoSerie(finalPartido);
        vista.mensaje("===========================================");
        vista.mensaje("EL CAMPEON DE LOS PLAYOFF ES: " + finalPartido.getEquipoGanador() + "!!");
        vista.mensaje("===========================================");
        vista.mensaje("===============¡FELICIDADES!================");
        
    }

}
