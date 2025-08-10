package ArbolB;

import java.util.Scanner;

public class TestArbolB {

    public static void ejecutar(Scanner sc) {
        ArbolB arbolB;
        int numero = leerEntero(sc, "\nIngrese el grado del Arbol B: ");
        double grado = numero / 2.0;
        int gradoMin = (int) Math.round(grado);
        // System.out.println("El grado minimo del arbolB es: " + gradoMin);
        System.out.print("\nCreando Arbol...\n");

        arbolB = new ArbolB(gradoMin);

        int opcion;
        do {
            System.out.println("\n------------------------");
            System.out.println("MENÚ ÁRBOL B");
            System.out.println("------------------------");
            System.out.println("1. Insertar clave");
            System.out.println("2. Eliminar clave");
            System.out.println("3. Buscar clave");
            System.out.println("4. Mostrar recorrido INORDEN");
            System.out.println("5. Mostrar recorrido PREORDEN");
            System.out.println("6. Mostrar árbol completo");
            System.out.println("7. Salir");
            opcion = leerEntero(sc, "\nSeleccione una opción: ");

            switch (opcion) {
                case 1:
                    int claveInsertar = leerEntero(sc, "\nIngrese clave a insertar: ");
                    arbolB.insertar(claveInsertar);
                    break;
                case 2:
                    int claveEliminar = leerEntero(sc, "\nIngrese clave a eliminar: ");
                    arbolB.eliminar(claveEliminar);
                    break;
                case 3:
                    int claveBuscar = leerEntero(sc, "\nIngrese clave a buscar: ");
                    boolean encontrado = arbolB.buscar(claveBuscar);
                    if (encontrado) {
                        System.out.println("La clave " + claveBuscar + " existe en el árbol.");
                    } else {
                        System.out.println("La clave " + claveBuscar + " NO se encontró.");
                    }
                    break;
                case 4:
                    System.out.println("\nRecorrido INORDEN:");
                    arbolB.recorridoInorden();
                    break;
                case 5:
                    System.out.println("\nRecorrido PREORDEN:");
                    arbolB.recorridoPreorden();
                    break;
                case 6:
                    System.out.println("\nÁrbol completo:");
                    arbolB.mostrarArbolB();
                    break;
                case 7:
                    System.out.println("\nSaliendo...");
                    break;
                default:
                    System.out.println("\nOpción inválida, intente de nuevo.");
            }
        } while (opcion != 7);
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        int numero;
        while (true) {
            try {
                System.out.print(mensaje);
                numero = sc.nextInt();
                return numero;
            } catch (Exception e) { // Manejo de error si no se ingresa un número entero
                System.out.println("\nError: debe ingresar un número entero.");
                sc.nextLine();
            }
        }
    }
}
