import java.util.Scanner;
/**
 * Clase Utilidades
 */
public class Utilidades {
    /**
     * Método estático leerCadena para leer un cadena de carecteres por pantalla
     * TODO leer por pantalla y comprobar que es una cadena de caracteres válida. 
     * @param teclado escáner
     * @param s mensaje a mostrar por pantalla
     * @return cadena
     */
    public static String leerCadena(Scanner teclado, String s) {
        String cadena;
        do{
            System.out.println(s);
            cadena=teclado.nextLine();
        }while(cadena.matches("^[A-Za-z0-9]+([._]?[a-zA-Z0-9]+)*$"));
        return cadena;
    }

    /**
     * Método estático leerNumero para leer un numero pasado por pantalla
     * TODO leer por pantalla y comprobar que es un número valido. Solicita un número repetidamente hasta que se
     *  introduzca uno correcto (dentro de los límites)
     * @param teclado escáner
     * @param mensaje a mostrar por pantalla
     * @param minimo valor mínimo de num
     * @param maximo valor máximo de num
     * @return número introducido dentro del rango solicitado
     */
    // Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int num;
        do{
            System.out.println(mensaje);
            num= teclado.nextInt();
            if(num<minimo||num>maximo) System.out.println("Valor fuera del rango");
        }while(num<minimo||num>maximo);

        return num;
    }
}