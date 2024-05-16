import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase Motor
 */
public class Motor {
    Sala[][] mapa;
    private final int maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala;

    /**
     * Constructor Clase Motor
     *
     * @param filas               filas
     * @param columnas            columnas
     * @param maxItemsPorSala     máx número de items por sala
     * @param maxMonstruosPorSala máx número de monstruos por sala
     * @param maxTrampasPorSalas  máx número de trampas por sala
     */
    public Motor(int filas, int columnas, int maxItemsPorSala, int maxMonstruosPorSala, int maxTrampasPorSalas) {
        this.maxItemsPorSala = maxItemsPorSala;
        this.maxMonstruosPorSala = maxMonstruosPorSala;
        this.maxTrampasPorSala = maxTrampasPorSalas;
        this.mapa = new Sala[filas][columnas];
    }

    /**
     * Clase cargarMapa para construir la matriz de mapa a traves del fichero.
     * Leer los datos del fichero de mapa pasado por parametro y generar una matriz Sala[][]
     * con dimension Sala[fila][columna] e inicializar la sala con los valores con la descripción del fichero
     * y los parámetros de maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala.
     *
     * @param ficheroMapa del que se lee para crear la matriz de salas
     * @return sala generada
     */
    Sala[][] cargarMapa(String ficheroMapa) { //Revisar si funciona, y si hay que utilizar mapa en lugar de crear otro Sala [][]
        Scanner sc = null;
        Sala[][] sala = mapa;
        try {
            sc = new Scanner(new FileReader(ficheroMapa));
            while (sc.hasNextLine()) {
                String[] linea = sc.nextLine().split(";");
                sala[Integer.parseInt(linea[0])][Integer.parseInt(linea[1])] = new Sala(linea[2], maxItemsPorSala,
                        maxMonstruosPorSala, maxTrampasPorSala, Integer.parseInt(linea[0]), Integer.parseInt(linea[1]));
            }
        } catch (Exception e) {
            return null;
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return sala;
    }

    /**
     * Metodo cargarItems para agregar los items del fichero en el mapa
     * Método para leer un fichero de items pasado por parámetro y según
     * la fila y columna introducir el item en la sala.
     *
     * @param ficheroItems del que se lee para asignar items a salas
     */
    private void cargarItems(String ficheroItems) {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(ficheroItems));
            while (sc.hasNextLine()) {
                String[] linea = sc.nextLine().split(";");
                mapa[Integer.parseInt(linea[0])][Integer.parseInt(linea[1])].agregarItem(new Item(linea[2],
                        Double.parseDouble(linea[3]), Double.parseDouble(linea[4])));
            }
        } catch (Exception e) {
            System.out.println("Exeption catched");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    /**
     * Método cargarMonstruos para agregar los monstruos del fichero en el mapa
     * Método para leer un fichero de Monstruos pasado por parámetro y según
     * la fila y columna introducir el monstruo en la sala.
     *
     * @param ficheroMonstruos que leemos para introducir monstruos en la sala
     */
    private void cargarMonstruos(String ficheroMonstruos) {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(ficheroMonstruos));
            while (sc.hasNextLine()) {
                String[] linea = sc.nextLine().split(";");
                mapa[Integer.parseInt(linea[0])][Integer.parseInt(linea[1])].agregarMonstruo(new Monstruo(linea[2],
                        Integer.parseInt(linea[3]), Integer.parseInt(linea[4]), Integer.parseInt(linea[5])));
            }
        } catch (Exception e) {
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    /**
     * Método cargarTrampas para agregar las trampas del fichero en el mapa.
     * Método para leer un fichero de trampas pasado por parámetro y según
     * la fila y columna introducir la trampa en la sala.
     *
     * @param ficheroTrampas que se lee para introducir trampas a la sala
     */
    private void cargarTrampas(String ficheroTrampas) {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(ficheroTrampas));
            while (sc.hasNextLine()) {
                String[] linea = sc.nextLine().split(";");
                mapa[Integer.parseInt(linea[0])][Integer.parseInt(linea[1])].agregarTrampa(new Trampa(linea[2],
                        Integer.parseInt(linea[3])));
            }
        } catch (Exception e) {
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    /**
     * Método iniciar, para preparar el mapa
     * Instanciación del parámetro mapa y carga de datos con los ficheros pasados como parámetros
     *
     * @param ficheroMapa      que contiene la información de la matriz de salas
     * @param ficheroItems     que contiene los items a introducir
     * @param ficheroMonstruos que contiene la información de la matriz de salas
     * @param ficheroTrampas   que contiene la información de la matriz de salas
     */
    public void iniciar(String ficheroMapa, String ficheroItems, String ficheroMonstruos, String ficheroTrampas) {
        mapa = cargarMapa(ficheroMapa);
        cargarItems(ficheroItems);
        cargarMonstruos(ficheroMonstruos);
        cargarTrampas(ficheroTrampas);
    }

    /**
     * Método getSala para obtener una sala concreta del mapa.
     * Devolver una Sala concreta del mapa
     *
     * @param fila de la sala solicitada
     * @param columna de la sala solicitada
     * @return sala solicitada
     */
    public Sala getSala(int fila, int columna) {
        return mapa[fila][columna];
    }

    /**
     * Método mostrarMapa para transformar el mapa en String.
     * Construir un String con la información contenida en el mapa
     *  respetando el formato que aparece en la memoria de la práctica
     *
     * @param fila de la sala actual
     * @param columna de la sala actual
     * @return el mapa en formato string
     */
    public String mostrarMapa(int fila, int columna) {
        int numFilas = mapa.length, numColumnas = mapa[0].length;
        String[][] mapaString = new String[numFilas + 2][numColumnas + 2];
        //Bordes y esquinas
        mapaString[0][0] = "╔";
        mapaString[0][numColumnas + 1] = "╗";
        mapaString[numFilas + 1][0] = "╚";
        mapaString[numFilas + 1][numColumnas + 1] = "╝";
        for (int i = 1; i < numColumnas + 1; i++) {
            mapaString[0][i] = "═";
            mapaString[numFilas + 1][i] = "═";
        }
        for (int i = 1; i < numFilas + 1; i++) {
            mapaString[i][0] = "║";
            mapaString[i][numColumnas + 1] = "║";
        }
        //Contenido del mapa
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                if (mapa[i][j] != null) mapaString[i + 1][j + 1] = "░";
                else mapaString[i + 1][j + 1] = " ";
            }
        }
        mapaString[fila + 1][columna + 1] = "@";
        StringBuilder resul = new StringBuilder();
        for (int i = 0; i < numFilas + 2; i++) {
            for (int j = 0; j < numColumnas + 2; j++) resul.append(mapaString[i][j]);
            resul.append("\n");
        }
        return resul.toString();
    }

    /**
     * Método jugar para empezar a jugar con el personaje
     * TODO método complejo en el que hay que seguir la siguiente ejecución:
     *  1. mostrar el mapa por pantalla
     *  2. Obtener la sala actual, mientras el personaje tenga vida y no haya llegado a la casilla final
     *  3. Durante una jugada mostrar la descripcion de la sala actual
     *  4. Comprobar si hay monstruos en la sala y si es así entrar en combate
     *  4.a El combate acaba cuando la vida del monstruo o la vida del personaje llega a 0
     *  4.b cada turno en el combate el personaje ataca al monstruo y restamos su vida
     *  4.c Si la vida no llega a 0 el monstruo hace daño al personaje
     *  5. Las salas pueden tener trampas
     *  5.a Si hay trampa hay que comprobar si un valor aleatorio entre 1 y 50 es inferior a la destreza del personaje, si es asi esquiva la trampa
     *  5.b Si no esquiva la trampa el personaje recibe daño
     *  5.c al igual que en combate hay que tener en cuenta si la vida del personaje lleva a 0
     *  6. Por último puede haber items en la sala, en cuyo caso habrá que preguntar al usuario qué ítems quiere guardarse (o NINGUNO para terminar)
     *  ¡IMPORTANTE! se debe mostrar por pantalla avisos para cada opción dando feedback al usuario de todo lo que ocurra (consultar enunciado)
     *
     * @param teclado escáner
     * @param personaje jugador
     * @param random objeto de la clase Random para el funcionamiento de trampas
     */
    public void jugar(Scanner teclado, Personaje personaje, Random random) {
        //1.Mostrar mapa
        System.out.println(mostrarMapa(personaje.getFila(), personaje.getColumna()));
        //2.Obtener sala actual
        Sala salaActual = getSala(personaje.getFila(), personaje.getColumna());
        while (personaje.getVida() > 0 && !salaActual.getDescripcion().equals("Habitación de salida")) {
            //3.Descripción de la sala actual
            System.out.println(salaActual.getDescripcion());
            //4. Comprobar monstruos, entrar en combate
            while (salaActual.hayMonstruos() && personaje.getVida() > 0) {
                Monstruo monstruoSelec=null;
                do {
                    monstruoSelec = salaActual.seleccionarMonstruo(teclado);
                    if(monstruoSelec==null) System.out.println("¡Ese monstruo no existe!");
                }while(monstruoSelec==null);
                //4.a Comprobar vidas
                while (monstruoSelec.getVida() > 0 && personaje.getVida() > 0) {
                    //4.b Personaje ataca
                    System.out.println(personaje.toString() + " ataca a " + monstruoSelec.toString() + " con " +
                            personaje.getAtaque() + " puntos de daño");
                    monstruoSelec.recibirDanyo(personaje.getAtaque() - monstruoSelec.getDefensa());
                    //4.c Monstuo ataca
                    if (monstruoSelec.getVida() > 0) {
                        System.out.println(monstruoSelec.toString() + "ataca a " + personaje.toString() + " con " +
                                monstruoSelec.getAtaque() + " puntos de daño");
                        personaje.recibirDanyo(monstruoSelec.getAtaque() - personaje.getDefensa());
                    }
                }

                if (monstruoSelec.getVida() > 0&&personaje.getVida()<=0) {
                    System.out.println("El monstruo te ha matado. El valor total de tus ítems es " +
                            personaje.getValorMochila());
                } else {
                    System.out.println("¡Has derrotado al monstruo!");
                    salaActual.eliminarMonstruo(monstruoSelec.getNombre());
                }
            }
            //5. Hay trampas:
            if (personaje.getVida() > 0 && salaActual.hayTrampas()) {
                for (Trampa trampas : salaActual.getTrampas()) {
                    if (personaje.getVida() > 0) {
                        //5.a El personaje esquiva la trampa
                        if (personaje.getDestreza() > (random.nextInt(50) + 1)) {
                            System.out.println("¡Has esquivado la trampa! " + trampas.getDescripcion());
                        }
                        //5.b El personaje NO esquiva la trampa
                        else {
                            personaje.recibirDanyo(trampas.getDanyo());
                            System.out.println("¡Has caído en una trampa!" + trampas.getDescripcion() + "\nTe ha hecho " +
                                    trampas.getDanyo() + " puntos de daño.");
                        }
                    } else System.out.println("La trampa te ha matado. El valor total de tus ítems es " +
                            personaje.getValorMochila());
                }
            }
            //6. Items
            Item itemSelec=null;
            while (salaActual.hayItems() || ((itemSelec != null) && !itemSelec.getDescripcion().equals("NINGUNO"))) { //Falta hacer un bucle
                do{
                    itemSelec = salaActual.seleccionarItem(teclado);
                }while(itemSelec==null);
                if (!itemSelec.getDescripcion().equals("NINGUNO")){
                    if (personaje.anyadirItem(itemSelec)) {
                        System.out.println("¡Te guardas el objeto! " + itemSelec.toString());
                        salaActual.eliminarItem(itemSelec.getDescripcion());
                        System.out.println(personaje.infoMochila());
                    }
                }
            }
            salaActual=seleccionarMovimiento(teclado,salaActual);
            personaje.setFila(salaActual.getFila());
            personaje.setColumna(salaActual.getColumna());
        }
        System.out.println("¡Has completado el laberinto!");
    }


    /**
     * Metodo seleccionarMovimiento para establecer las acciones que tome el jugador con su personaje
     * El desplazamiento del personaje se entiende como norte (N), sur (S), este (E) u oeste (O)
     *  en este método hay que capturar por pantalla la acción que va a tomar el usuario de entre las posibles
     *  para ello hay que tener en cuenta que se debe avisar al usuario si puede realizar o no la acción.
     *  Se devolverá la sala destino a la que se ha movido el personaje.
     *
     * @param teclado esáner
     * @param salaActual sala en la que se encuentra el jugador antes de realizar el movimiento
     * @return sala a la que se mueve el personaje
     */
    public Sala seleccionarMovimiento(Scanner teclado, Sala salaActual) {
        int filaPropuesta = salaActual.getFila(), columnaPropuesta = salaActual.getColumna();
        do {
            System.out.println(mostrarMapa(salaActual.getFila(), salaActual.getColumna()));
            switch (Utilidades.leerCadena(teclado, "Introduce el movimiento (N, E, S, O): ")) {
                case "N":
                    if (!existeEnMapa(salaActual.getFila() - 1, salaActual.getColumna())) {
                        System.out.println("No puedes moverte al norte.");
                    } else {
                        filaPropuesta = salaActual.getFila() - 1;
                        columnaPropuesta = salaActual.getColumna();
                    }
                    break;
                case "E":
                    if (!existeEnMapa(salaActual.getFila(), salaActual.getColumna() + 1)) {
                        System.out.println("No puedes moverte al este.");
                    } else {
                        filaPropuesta = salaActual.getFila();
                        columnaPropuesta = salaActual.getColumna() + 1;
                    }
                    break;
                case "S":
                    if (!existeEnMapa(salaActual.getFila() + 1, salaActual.getColumna())) {
                        System.out.println("No puedes moverte al sur.");
                    } else {
                        filaPropuesta = salaActual.getFila() + 1;
                        columnaPropuesta = salaActual.getColumna();
                    }
                    break;
                case "O":
                    if (!existeEnMapa(salaActual.getFila(), salaActual.getColumna() - 1)) {
                        System.out.println("No puedes moverte al oeste.");
                    } else {
                        filaPropuesta = salaActual.getFila();
                        columnaPropuesta = salaActual.getColumna() - 1;
                    }
                    break;
                default:
                    System.out.println("No existe ese movimiento, introduzca uno válido.");
            }
        } while ((filaPropuesta == salaActual.getFila()) && (columnaPropuesta == salaActual.getColumna()));
        return getSala(filaPropuesta, columnaPropuesta);
    }

    private boolean existeEnMapa(int fila, int columna) {
        return (fila >= 0 && fila < mapa.length && columna >= 0 && columna < mapa[0].length && mapa[fila][columna] != null);
    }
}
