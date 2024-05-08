import java.util.Scanner;
/**
 * Clase Utilidades
 */
public class Utilidades {
    /**
     * Método estático leerCadena para leer un cadena de carecteres por pantalla.
     * Leer por pantalla y comprobar que es una cadena de caracteres válida.
     * @param teclado escáner
     * @param s mensaje a mostrar por pantalla
     * @return cadena
     */
    public static String leerCadena(Scanner teclado, String s) {
        String cadena;
        do{
            System.out.print(s); //Así se utiliza 's'?
            cadena=teclado.nextLine();
            if(!cadena.matches("^[A-Za-zÁ-ÿ0-9]+([._ ]?[a-zA-ZÁ-ÿ0-9]+)*$")) System.out.println("No válido");
        }while(!cadena.matches("^[A-Za-zÁ-ÿ0-9]+([._ ]?[a-zA-ZÁ-ÿ0-9]+)*$")); //A esto se refiere con cadena válida?
        return cadena;
    }

    /**
     * Método estático leerNumero para leer un número pasado por pantalla.
     * Leer por pantalla y comprobar que es un número válido. Solicita un número repetidamente hasta que se
     *  introduzca uno correcto (dentro de los límites)
     * @param teclado escáner
     * @param mensaje a mostrar por pantalla
     * @param minimo valor mínimo de num
     * @param maximo valor máximo de num
     * @return número introducido dentro del rango solicitado
     */
    // Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        String num;
        do{
            System.out.print(mensaje);
            num = teclado.nextLine();
            if(num.matches("[0-9]+")){
                if(Integer.parseInt(num)<minimo||Integer.parseInt(num)>maximo) System.out.println("Valor fuera del rango");
            }else System.out.println("Debe intoducir un número");
        }while (!num.matches("[0-9]+")||(Integer.parseInt(num)<minimo||Integer.parseInt(num)>maximo));

        return Integer.parseInt(num);
    }
}