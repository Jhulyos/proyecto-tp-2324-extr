import java.util.Scanner;

/**
 * Clase Personaje
 */
public class Personaje {
    private final String nombre;
    private int vida;
    private final int ataque, defensa, destreza;
    private final Item[] items;
    private int fila;
    private int columna;
    private final double maxPesoPorPersonaje;

    /**
     * Constructor de la clase para inicializar todos los atributos
     * @param nombre Nombre del personaje
     * @param vida puntos de vida que tiene el personaje
     * @param ataque puntos de ataque que tiene el personaje
     * @param defensa puntos de defensa que tiene el personaje
     * @param destreza puntos de destreza que tiene el presonaje
     * @param maxItemsPorPersonaje máximo número de items que puede llevar el personaje
     * @param maxPesoPorPersonaje peso máximo que puede llevar el personaje
     */
    public Personaje(String nombre, int vida, int ataque, int defensa, int destreza, int maxItemsPorPersonaje, double maxPesoPorPersonaje) {
        this.nombre=nombre;
        this.vida=vida;
        this.ataque=ataque;
        this.defensa=defensa;
        this.destreza=destreza;
        this.maxPesoPorPersonaje=maxPesoPorPersonaje;
        this.items=new Item[maxItemsPorPersonaje];
        fila=0;
        columna=0;
    }


    /**
     * Metodo crearPersonaje que administra toda la generación de personajes.
     * El metodo tiene que ser capaz de recoger todas las características del personaje mediante preguntas y
     *  respuestas por pantalla y se debe controlar que los valores introducidos sean válidos. Una vez recabados
     *  todos los datos del personaje generar un objeto con dichas características.
     * @param teclado escáner
     * @return personaje creado
     */
    public static Personaje crearPersonaje(Scanner teclado) {//verificar si los valores son correctos
        int puntos=250;
        String nombre= Utilidades.leerCadena(teclado,"¿Cómo te llamas? ");
        System.out.println("\n¡Hola, "+nombre+"! Tienes 250 puntos para repartir entre vida, ataque, defensa y destreza.");
        int vida=Utilidades.leerNumero(teclado,"¿Cuánta vida quieres tener? (50-"+(puntos-3)+"): ",50,puntos-3);
        puntos-=vida;
        int ataque=Utilidades.leerNumero(teclado,"¿Cuánto ataque quieres tener? (1-"+(puntos-2)+"): ",1,puntos-2);
        puntos-=ataque;
        int defensa=Utilidades.leerNumero(teclado,"¿Cuánta defensa quieres tener? (1-"+(puntos-1)+"): ",1,puntos-1);
        puntos-=defensa;
        int destreza=Utilidades.leerNumero(teclado,"¿Cuánta destreza quieres tener? (1-"+(puntos)+"): ",1,puntos);

        int maxItems= destreza/4; if(maxItems<1) {maxItems=1;}
        int maxPeso= ataque/2; if(maxPeso<1){maxPeso=1;}

        return new Personaje(nombre,vida,ataque,defensa,destreza,maxItems,maxPeso);
    }

    /**
     * Método getNombre
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método getVida
     * @return int vida
     */
    public int getVida() {
        return vida;
    }

    /**
     * Método getAtaque
     * @return int ataque
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * Método getDefensa
     * @return int defensa
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * Método getDestreza
     * @return int destreza
     */
    public int getDestreza() {
        return destreza;
    }

    /**
     * Método getItems
     * @return Item[] items
     */

    public Item[] getItems() {
        return items;
    }

    /**
     * Método getItem para devolver un Item según un índice dado.
     * Devolver null si el índice no es válido, y el item si el índice es correcto
     * @param indice indica la posición del array de items que se solicita
     * @return el item solicitado
     */
    public Item getItem(int indice) {
        Item item=null;
        if(indice<items.length&&indice>=0){
            item=items[indice];
        }
            return item;
    }

    /**
     * Método getFila
     * @return int fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * Método getColumna
     * @return int columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Método setFila
     * @param fila que se asigna
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * Método setColumna
     * @param columna que se asigna
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    /**
     * Método recibirDanyo para actualizar la vida de un personaje.
     * Si el daño no es positivo, no hacer nada. En caso contrario reducir la vida según el daño pasado
     * @param danyo entero que indica los puntos de ataque que recibe el personaje
     */
    public void recibirDanyo(int danyo) {
        if (danyo>=0){vida-=ataque;}
    }

    /**
     * Método anyadirItem para incluir un item en la mochila del personaje.
     * Comprobar si el item es válido y si el peso max del personaje no se supera para poder incluir el item,
     *  en caso negativo devolver false, en caso de que se pueda incluir, añadir el item a la lista de items del
     *  personaje y devolver true
     * @param item pasado por parámetro
     * @return boolean del éxito de la operación
     */
    public boolean anyadirItem(Item item) {
        boolean resul=false;
        if(item.valido()&&maxPesoPorPersonaje>(getPesoMochila()+item.getPeso())){
            int i=0;
            do{
                if(items[i]==null){items[i]=item;resul=true;}
                i++;
            }while(i<items.length&&!resul);
            if(!resul)System.out.println("La mochila esta llena");
        }
        return resul;
    }

    /**
     * Método sobreescrito para devolver la información de un personaje.
     * Método para devolver un String con la información del personaje en el formato
     *  descrito en la memoria de la práctica P.e: "{ Edgar (V: 20, A: 5, D: 2, X: 5) }"
     * @return toString descripción del personaje
     */
    @Override
    public String toString() {
        return "{ "+nombre+" (V: "+vida+", A: "+ataque+", D: "+defensa+", X: "+destreza+") }";
    }
    /**
     * Método getPesoMochila para obtener el peso total que carga en la mochila el personaje.
     * Recorrer la lista de items para obtener el peso total de todos y devolverlo
     * @return double suma del peso de todos los items
     */
    public double getPesoMochila() {
        double peso=0;
        for (Item item : items) {
            if(item!=null)peso += item.getPeso();
        }
        return peso;
    }

    /**
     * Método getValorMochila para obtener el valor total que lleva entre todos los items el personaje.
     * Recorrer la lista de items para obtener el valor total de todos y devolverlo
     * @return double suma del valor de todos los items
     */
    public double getValorMochila() {
        double valor=0;
        for (Item item : items) {
            valor += item.getValor();
        }
        return valor;
    }

    /**
     * Método infoMochila para obtener en formato String la información de la mochila.
     * Recorrer toda la lista de items del personaje para ir añadiendo la información de los items según el
     *  formato mostrado en la memoria. "Mochila de Edgar:
     *                                   Espada Mágica Peso: 1.5, Valor: 100
     *                                   Armadura de Gromril Peso: 4, Valor: 300
     *                                   Peso total: 5.5 Kg
     *                                   Tu mochila vale 400 monedas"
     * @return mochila más el peso y valor totales
     */
    public String infoMochila() { //revisar si funciona correctamente
        String mochila="Mochila de "+this.nombre+":\n";
        double peso=0,valor=0;
        for (Item item : items){
            if(item!=null){
                mochila+=(item.toString()+"\n");
                peso+=item.getPeso();
                valor+=item.getValor();
            }
        }

        return mochila+"Peso total: "+peso+" Kg\nTu mochila vale "+valor+" monedas";
    }
}
