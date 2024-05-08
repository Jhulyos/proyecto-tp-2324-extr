import java.io.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal de Aventuras desde donde lanzar la ejecución de la práctica
 */
public class Aventuras {

    /**
     * Main desde donde ejecutar el programa
     * TODO instanciación e inicialización de objetos para la ejecución,
     *  ejecución del motor, muestra de puntuaciones y lectura de instrucciones
     *  por teclado para jugar. Finalmente guardar la puntuación
     * @param args parámetros de la configuración: filas, columnas, maxItemsPorSala, maxMonstruosPorSala,
     *             maxTrampasPorSala, fichero_salas, fichero_items, fichero_monstruos, fichero_trampas,
     *             fichero_puntuaciones
     */
    public static void main(String[] args) {
        Motor motor=new Motor(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]),
                Integer.parseInt(args[3]),Integer.parseInt(args[4]));
        motor.iniciar(args[5],args[6],args[7],args[8]);
        Scanner teclado=new Scanner(System.in);
        Personaje personaje=Personaje.crearPersonaje(teclado);
        motor.jugar(teclado,personaje,new Random());

    }

    /**
     * Metodo guardarPuntuación en fichero
     * Abrir y guardar en el fichero pasado como parametro el personaje
     *  siguiendo el formato descrito en la memoria de la práctica
     * @param ficheroPuntuaciones fichero en el que se guardarán las puntuaciones
     * @param jugador objeto de la clase personaje que se guardará en el fichero
     */
    private static void guardarPuntuacion(String ficheroPuntuaciones, Personaje jugador) {
        PrintWriter pw = null;
        LocalDate fecha = LocalDate.now();
        try {
            FileWriter fw = new FileWriter(ficheroPuntuaciones,true);
            pw = new PrintWriter(fw);
            pw.println(fecha+" "+jugador.toString()+", "+jugador.getValorMochila());
        } catch (IOException e) {
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    /**
     * Metodo mostrarPuntuaciones del fichero puntuaciones
     * Mostrar por pantalla todas las puntuaciones almacenadas en el fichero
     *  pasado como parámetro. P.e:
     *              "Puntuaciones:
     *                  2024-04-04	{ Raul (V: -4, A: 50, D: 40, X: 20) }, 420.0 monedas"
     * @param ficheroPuntuaciones que contiene las puntuaciones guardadas de partidas anteriores
     */
    private static void mostrarPuntuaciones(String ficheroPuntuaciones) {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(ficheroPuntuaciones));
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (Exception e) {
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
