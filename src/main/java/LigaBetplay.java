import java.util.*;

public class LigaBetplay {

    // Lista de equipos participantes
    private static List<Equipo> equipos = new ArrayList<>();

    // Método para registrar un nuevo equipo
    public static void registrarEquipo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();
        Equipo equipo = new Equipo(nombreEquipo);
        equipos.add(equipo);
        System.out.println("Equipo registrado exitosamente!");
    }

    // Método para registrar un partido
    public static void registrarPartido() {
        Scanner scanner = new Scanner(System.in);

        // Mostrar lista de equipos para seleccionar local y visitante
        System.out.println("\nLista de equipos:");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
        }

        System.out.print("\nSeleccione el equipo local (número): ");
        int equipoLocalIndex = scanner.nextInt() - 1;
        Equipo equipoLocal = equipos.get(equipoLocalIndex);

        System.out.print("Seleccione el equipo visitante (número): ");
        int equipoVisitanteIndex = scanner.nextInt() - 1;
        Equipo equipoVisitante = equipos.get(equipoVisitanteIndex);

        System.out.print("Ingrese la fecha del partido (YYYY-MM-DD): ");
        String fechaPartido = scanner.next();

        System.out.print("Ingrese el marcador del equipo local: ");
        int golesLocal = scanner.nextInt();

        System.out.print("Ingrese el marcador del equipo visitante: ");
        int golesVisitante = scanner.nextInt();

        // Calcular el resultado del partido
        int resultadoLocal = 0;
        int resultadoVisitante = 0;

        if (golesLocal > golesVisitante) {
            resultadoLocal = 3;
            resultadoVisitante = 0;
        } else if (golesLocal < golesVisitante) {
            resultadoLocal = 0;
            resultadoVisitante = 3;
        } else {
            resultadoLocal = 1;
            resultadoVisitante = 1;
        }

        // Actualizar las estadísticas de los equipos
        equipoLocal.actualizarEstadisticas(resultadoLocal, golesLocal, golesVisitante);
        equipoVisitante.actualizarEstadisticas(resultadoVisitante, golesVisitante, golesLocal);

        System.out.println("Partido registrado exitosamente!");
    }

    // Método para generar reportes
    public static void generarReportes() {
        Equipo equipoMayorGoles = null;
        Equipo equipoMayorPuntos = null;
        Equipo equipoMayorVictorias = null;
        int totalGoles = 0;

        for (Equipo equipo : equipos) {
            if (equipoMayorGoles == null || equipo.getGolesFavor() > equipoMayorGoles.getGolesFavor()) {
                equipoMayorGoles = equipo;
            }

            if (equipoMayorPuntos == null || equipo.getPuntos() > equipoMayorPuntos.getPuntos()) {
                equipoMayorPuntos = equipo;
            }

            if (equipoMayorVictorias == null || equipo.getPartidosGanados() > equipoMayorVictorias.getPartidosGanados()) {
                equipoMayorVictorias = equipo;
            }
            totalGoles += equipo.getGolesFavor();
        }    

        double promedioGoles = (double) totalGoles / equipos.size();

        // Mostrar reportes
        System.out.println("\nReportes:");
        System.out.println("- Equipo con mayor cantidad de goles anotados: " + equipoMayorGoles.getNombre() + " (" + equipoMayorGoles.getGolesFavor() + " goles)");
        System.out.println("- Equipo con mayor cantidad de puntos: " + equipoMayorPuntos.getNombre() + " (" + equipoMayorPuntos.getPuntos() + " puntos)");
        System.out.println("- Equipo con mayor cantidad de partidos ganados: " + equipoMayorVictorias.getNombre() + " (" + equipoMayorVictorias.getPartidosGanados() + " victorias)");
        System.out.println("- Total de goles anotados en el torneo: " + totalGoles);
        System.out.println("- Promedio de goles anotados en el torneo: " + String.format("%.2f", promedioGoles));
    }

    // Clase Equipo para representar las estadísticas de cada equipo
    private static class Equipo {
        private String nombre;
        private int partidosJugados;
        private int partidosGanados;
        private int partidosPerdidos;
        private int partidosEmpatados;
        private int golesFavor;
        private int golesContra;
        private int puntos;

        public Equipo(String nombre) {
            this.nombre = nombre;
            this.partidosJugados = 0;
            this.partidosGanados = 0;
            this.partidosPerdidos = 0;
            this.partidosEmpatados = 0;
            this.golesFavor = 0;
            this.golesContra = 0;
            this.puntos = 0;
        }

        public String getNombre() {
            return nombre;
        }

        public int getPartidosJugados() {
            return partidosJugados;
        }

        public int getPartidosGanados() {
            return partidosGanados;
        }

        public int getPartidosPerdidos() {
            return partidosPerdidos;
        }

        public int getPartidosEmpatados() {
            return partidosEmpatados;
        }

        public int getGolesFavor() {
            return golesFavor;
        }

        public int getGolesContra() {
            return golesContra;
        }

        public int getPuntos() {
            return puntos;
        }

        public void actualizarEstadisticas(int resultado, int golesFavor, int golesContra) {
            partidosJugados++;

            if (resultado == 3) {
                partidosGanados++;
                puntos += 3;
            } else if (resultado == 1) {
                partidosEmpatados++;
                puntos += 1;
            } else {
                partidosPerdidos++;
            }

            this.golesFavor += golesFavor;
            this.golesContra += golesContra;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSistema de registro y control Liga Betplay");
            System.out.println("1. Registrar equipo");
            System.out.println("2. Registrar partido");
            System.out.println("3. Generar reportes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarEquipo();
                    break;
                case 2:
                    registrarPartido();
                    break;
                case 3:
                    generarReportes();
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }

        }
    }
}

