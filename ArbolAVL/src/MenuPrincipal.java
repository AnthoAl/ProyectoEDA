package ps;

import java.util.Scanner;
import ArbolAVL1.Principal; // Clase con submenú AVL
import ArbolB.TestArbolB;  // Clase con submenú Árbol B
import TablaHash.TestTablaHash; // Clase con submenú Hash

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=====================================");
            System.out.println(" PROYECTO FINAL ESTRUCTURAS DE DATOS Y ALGORITMOS I ");
            System.out.println("\nÁRBOLES Y FUNCIÓN HASH ");
            System.out.println("=====================================");
            System.out.println("1. Árbol AVL");
            System.out.println("2. Árbol B");
            System.out.println("3. Tabla Hash (Doble Hashing)");
            System.out.println("4. Salir");
            System.out.println("=====================================");

            opcion = leerEntero(sc, "Seleccione el tipo de estructura a crear: ");

            switch (opcion) {
                case 1:
                    Principal.ejecutar(sc); // Submenú AVL
                    break;
                case 2:
                    TestArbolB.ejecutar(sc, new ArbolB.ArbolB(3)); // Submenú Árbol B
                    break;
                case 3:
                    TestTablaHash.ejecutar(sc); // Submenú Hash
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);

        sc.close();
    }
 // Método auxiliar para leer enteros con validación
    private static int leerEntero(Scanner sc, String mensaje) {
        int numero;
        while (true) {
            try {
                System.out.print(mensaje);
                numero = sc.nextInt();
                return numero;
            } catch (Exception e) { // Manejo de error si no se ingresa un número entero
                System.out.println("Error: debe ingresar un número entero.");
                sc.nextLine(); 
            }
        }
    }
}







