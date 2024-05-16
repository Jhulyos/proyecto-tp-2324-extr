import java.util.Scanner;

/**
 * Clase Sala
 */
public class Sala {
    private final String descripcion;
    private final Item[] items;
    private final Monstruo[] monstruos;
    private final Trampa[] trampas;

    private final int fila;
    private final int columna;

    /**
     * Constructor de clase para inicializar los atributos de clase
     * @param descripcion texto que describe la sala
     * @param max_items máximo número posible de items en la sala
     * @param max_monstruos máximo número posible de monstruos en la sala
     * @param maxTrampasPorSala máximo número posible de trampas en la sala
     * @param fila número de fila del laberinto en la que se encuentra la sala
     * @param columna número de la columna del laberinto en la que se encuentra la sala
     */
    public Sala(String descripcion, int max_items, int max_monstruos, int maxTrampasPorSala, int fila, int columna) {
        this.descripcion=descripcion;
        this.items=new Item[max_items];
        this.monstruos=new Monstruo[max_monstruos];
        this.trampas=new Trampa[maxTrampasPorSala];
        this.fila=fila;
        this.columna=columna;
    }

    /**
     * Método agregarItem para incluir items en la sala.
     *  Comprobar si existe el objeto en la sala o si la lista de items no está ya llena en caso afirmativo
     *  devolver false. En caso de no existir incluirlo en la lista y devolver true
     * @param item objeto que se busca introducir a la lista de items
     * @return resul - éxito de la operación
     */
    public boolean agregarItem(Item item) {
        int pos=0;
        boolean llena=true, unico=true, resul=false;
        for(int i=0;i<items.length;i++){
            if(items[i]==null){llena=false;pos=i;}
            else if(items[i].equals(item))unico=false;
        }
        if(!llena&&unico){items[pos]=item;resul=true;}

            return resul;
    }

    /**
     * Método agregarMonstruo para incluir un monstruo en la sala.
     * Comprobar si existe el monstruo en la sala o si la lista de monstruos no está ya llena en caso afirmativo
     *  devolver false. En caso de no existir incluirlo en la lista y devolver true
     * @param monstruo objeto tipo Monstruo que se pretende agregar a la lista monstruos
     * @return resul - éxito de la operación
     */
    public boolean agregarMonstruo(Monstruo monstruo) {
        int pos=-1;
        boolean llena=true, unico=true, resul=false;
        for(int i=0;i<monstruos.length;i++){
            if(monstruos[i]==null&&llena){llena=false;pos=i;
            }
            else if(monstruos[i]!=null && monstruos[i].equals(monstruo))unico=false;
        }
        if(!llena&&unico){monstruos[pos]=monstruo;resul=true;}

        return resul;
    }

    /**
     * Método agregarTrampa para incluir una trampa en la sala.
     * Comprobar si existe la trampa en la sala o si la lista de trampas no está ya llena en caso afirmativo
     *  devolver false. En caso de no existir incluirlo en la lista y devolver true
     * @param trampa objeto tipo Trampa que se pretende agregar a la lista trampas
     * @return resul - éxito de la operación
     */
    public boolean agregarTrampa(Trampa trampa) {
        int pos=0;
        boolean llena=true, unico=true, resul=false;
        for(int i=0;i<trampas.length;i++){
            if(trampas[i]==null){llena=false;pos=i;}
            else if(trampas[i].equals(trampa))unico=false;
        }
        if(!llena&&unico){trampas[pos]=trampa;resul=true;}

        return resul;
    }

    /**
     * Método getDescripcion
     * @return String descripcion
     */
    public String getDescripcion() {return descripcion;}

    /**
     * Método hayMonstruos para comprobar si hay algún monstruo en la sala.
     * Comprobar si hay algún monstruo en la lista
     * @return resul - boolean que indica si hay o no monstruos
     */
    public boolean hayMonstruos() {
        boolean resul=false;
        int i=0;
        do{
            if (monstruos[i]!=null)resul=true;
            i++;
        }while(i<monstruos.length && monstruos[i]==null);
        return resul;
    }

    /**
     * Método seleccionarMonstruo para introducir desde pantalla el nombre de un monstruo.
     * Mostrar por pantalla todos los monstruos y luego solicitar que se introduzca el nombre del monstruo que se
     *  quiere seleccionar.
     * @param teclado escáner que se pasará al método de lectura de Strings
     * @return el monstruo seleccionado
     */
    public Monstruo seleccionarMonstruo(Scanner teclado) {
        listarMonstruos();
        return buscarMonstruo(Utilidades.leerCadena(teclado,"Introduce el nombre del monstruo "));
    }

    /**
     * Método buscarMonstruo para buscar un monstruo dado el nombre del mismo.
     * Devolver el monstruo según el nombre pasado como parámetro o devolver null si no se encuentra
     * @param nombreMonstruo nombre del monstruo que se busca
     * @return monstruo -
     */
    public Monstruo buscarMonstruo(String nombreMonstruo) {
        Monstruo monstruo=null;
        int i=0;
        do{
            if(monstruos[i]!=null&&monstruos[i].getNombre().equals(nombreMonstruo))monstruo=monstruos[i];
            i++;
        }while(i< monstruos.length && monstruo==null);
        return monstruo;
    }

    /**
     * Método listarMonstruos para mostrar por pantalla la información de los monstruos.
     * Mostrar por pantalla la info de los monstruos utilizando los métodos implementados en la clase "monstruo"
     */
    private void listarMonstruos() {
        System.out.println("Monstruos en la sala:");
        for(Monstruo monstruo : monstruos){
            if(monstruo!=null)System.out.println(monstruo.toString());
        }
    }

    /**
     * Método eliminarMonstruo para eliminar un monstruo de la lista segun un nombre dado.
     * Buscar en la lista el monstruo segun el nombre pasado como parámetro y eliminarlo.
     * @param nombreMonstruo String que contiene el nombre del monstruo a eliminar
     */
    public void eliminarMonstruo(String nombreMonstruo) { //Mover elementos del array al eliminar elementos
        Monstruo montruo=buscarMonstruo(nombreMonstruo);
        int i=-1;
        do{
            i++;
            if(i<monstruos.length&&montruo.equals(monstruos[i])){
                for(int j=i;j<monstruos.length;j++){
                    if(j<monstruos.length-1)monstruos[j]=monstruos[j+1];
                    else monstruos[j]=null;
                }
            }
        }while(i<monstruos.length&&!montruo.equals(monstruos[i]));
    }

    /**
     * Método hayTrampas para saber si la sala dispone de alguna trampa
     * Mostrar si existe alguna trampa en la sala, false en caso contrario
     * @return boolean resul
     */
    public boolean hayTrampas() {
        boolean resul=false;
        int i=0;
        do{
            if (trampas[i]!=null)resul=true;
            i++;
        }while(i<trampas.length && trampas[i]==null);
        return resul;
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
     * Método hayItems para mostrar si existe algún item en la sala.
     * Buscar si hay algún item en la lista de items, false en caso contrario
     * @return boolean resul
     */
    public boolean hayItems() {
        boolean resul=false;
        int i=0;
        do{
            if (items[i]!=null)resul=true;
            i++;
        }while(i<items.length && !resul);
        return resul;
    }

    /**
     * Método buscarItem para obtener un item según una descripcion dada.
     * Buscar en la lista de items un item con la descripción pasada como parámetro, devolver null si no lo
     *  encuentra
     * @param descripcion String que contiene la descripción del item
     * @return item solicitado
     */
    public Item buscarItem(String descripcion) {
        Item item=null;
        if(!descripcion.equals("NINGUNO")) {
            int i = 0;
            do {
                if (items[i]!=null&&items[i].getDescripcion().equals(descripcion)) item = items[i];
                i++;
            } while (i < items.length && item == null);
            if (item==null) System.out.println("No se ha encontrado un item con esa descripción");
        }else item=new Item("NINGUNO",1,1);
        return item;
    }

    /**
     * Método buscarTrampa para obtener una trampa según una descripcion dada.
     * Buscar en la lista de trampas una trampa con la descripción pasada como parámetro, devolver null si no lo
     *  encuentra
     * @param descripcion String que contiene la descripción de la trampa
     * @return trampa solicitada
     */
    public Trampa buscarTrampa(String descripcion) {
        Trampa trampa=null;
        int i=0;
        do{
            if(trampas[i].getDescripcion().equals(descripcion))trampa=trampas[i];
            i++;
        }while(i< trampas.length && trampa==null);
        return trampa;
    }

    /**
     * Método getTrampas
     * @return Trampa[] trampas
     */
    public Trampa[] getTrampas() {
        return trampas;
    }

    /**
     * Método seleccionarItem para obtener un item concreto con parámetro pasado por pantalla.
     * Mostrar por pantalla todos los items de la sala para después pedir que se introduzca una descripcion del
     *  item que se quiere seleccionar
     * @param teclado escáner
     * @return el item solicitado
     */
    public Item seleccionarItem(Scanner teclado) {
        listarItems();
        return buscarItem(Utilidades.leerCadena(teclado,"Introduce el nombre del item "));
    }

    /**
     * Método listarItems para mostrar por pantalla todos los items.
     * Utilizar las funciones de la clase Item para poder mostrar por pantalla toda la información de todos los
     *  items que hay en la sala
     */
    private void listarItems() {
        for(Item item : items){
            if(item!=null)System.out.println(item.toString());
        }
    }

    /**
     * Método eliminarItem para eliminar un item con la descripción pasada como parámetro.
     * Buscar el item que coincida con la descripción pasada por parámetro y eliminarlo de la lista de items
     * @param descripcion String que contiene la descripción del item a eliminar
     */
    public void eliminarItem(String descripcion) { //Mover elementos del array al eliminar elementos
        Item item=buscarItem(descripcion);
        int i=0;
        do{
            if(item.equals(items[i])){
                for(int j=i;j<items.length;j++){
                    if(j<items.length-1)items[j]=items[j+1];
                    else items[j]=null;
                }
            }
            i++;
        }while(!item.equals(items[i]));
    }
}
