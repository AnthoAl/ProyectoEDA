package ps;

import java.util.Scanner;
import ArbolAVL1.PrincipalAVL; // Clase donde está tu submenú AVL
import ArbolB.SubmenuArbolB;  // Clase con submenú Árbol B
import TablaHash.SubmenuTablaHash; // Clase con submenú Hash

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=====================================");
            System.out.println("  MENÚ PRINCIPAL - ESTRUCTURAS DE DATOS");
            System.out.println("=====================================");
            System.out.println("1. Árbol AVL");
            System.out.println("2. Árbol B");
            System.out.println("3. Tabla Hash (Doble Hashing)");
            System.out.println("4. Salir");
            System.out.println("=====================================");

            opcion = leerEntero(sc, "Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    PrincipalAVL.ejecutar(sc); // Submenú AVL
                    break;
                case 2:
                    SubmenuArbolB.ejecutar(sc, new ArbolB.ArbolB(3)); // Submenú Árbol B
                    break;
                case 3:
                    SubmenuTablaHash.ejecutar(sc); // Submenú Hash
                    break;
                case 4:
                    System.out.println("✅ Saliendo del programa...");
                    break;
                default:
                    System.out.println("❌ Opción inválida.");
            }

        } while (opcion != 4);

        sc.close();
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        int numero;
        while (true) {
            try {
                System.out.print(mensaje);
                numero = sc.nextInt();
                return numero;
            } catch (Exception e) {
                System.out.println("❌ Error: debe ingresar un número entero.");
                sc.nextLine(); // limpiar buffer
            }
        }
    }
}
