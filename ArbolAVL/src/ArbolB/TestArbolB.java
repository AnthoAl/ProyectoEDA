package ArbolB;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestArbolB {

    public static void ejecutar(Scanner sc, ArbolB arbolB) {
        int opcion;

        do {
            System.out.println("\n------------------------");
            System.out.println("\t SUBMENÚ ÁRBOL B");
            System.out.println("------------------------");
            System.out.println("1. Insertar clave");
            System.out.println("2. Mostrar árbol (preorden)");
            System.out.println("3. Buscar clave");
            System.out.println("4. Mostrar clave mayor");
            System.out.println("5. Mostrar nodo mínimo");
            System.out.println("6. Salir");
            System.out.println("------------------------");

            opcion = leerEntero(sc, "Seleccione una opción: ");

            switch (opcion) {
                case 1: {
                    int claveNueva = leerEntero(sc, "\nIngrese la clave a insertar: ");
                    if (arbolB.buscar(arbolB.raiz, claveNueva) != null) {
                        System.out.println("La clave " + claveNueva + " ya existe en el árbol.");
                    } else {
                        arbolB.insertar(claveNueva);
                        System.out.println("Clave insertada correctamente.");
                    }
                    break;
                }
                case 2: {
                    if (arbolB.raiz == null || arbolB.raiz.numeroClaves == 0) {
                        System.out.println("El árbol está vacío.");
                    } else {
                        System.out.println("\nÁrbol B (preorden):");
                        arbolB.mostrarArbolB();
                        System.out.println();
                    }
                    break;
                }
                case 3: {
                    if (arbolB.raiz == null || arbolB.raiz.numeroClaves == 0) {
                        System.out.println("El árbol está vacío. No se puede buscar.");
                    } else {
                        int claveBuscar = leerEntero(sc, "\nIngrese la clave a buscar: ");
                        arbolB.buscarNodoPorClave(claveBuscar);
                    }
                    break;
                }
                case 4: {
                    if (arbolB.raiz == null || arbolB.raiz.numeroClaves == 0) {
                        System.out.println("El árbol está vacío.");
                    } else {
                        System.out.println("Clave mayor: " + arbolB.buscarClaveMayor());
                    }
                    break;
                }
                case 5: {
                    if (arbolB.raiz == null || arbolB.raiz.numeroClaves == 0) {
                        System.out.println("El árbol está vacío.");
                    } else {
                        System.out.print("Nodo mínimo: ");
                        arbolB.mostrarClavesNodoMinimo();
                        System.out.println();
                    }
                    break;
                }
                case 6: {
                    System.out.println("\nSaliendo...");
                    break;
                }
                default:
                    System.out.println("\nOpción inválida.");
            }

        } while (opcion != 6);
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        int numero;
        while (true) {
            try {
                System.out.print(mensaje);
                numero = sc.nextInt();
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un número entero.");
                sc.nextLine(); 
            }
        }
    }
}

