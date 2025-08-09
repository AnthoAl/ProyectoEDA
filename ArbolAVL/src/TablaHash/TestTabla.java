package TablaHash;

import java.util.Scanner;

public class TestTablaHash  {

    public static void ejecutar(Scanner sc) {
        TablaHash tablaHash = null;
        int opcion;

        do {
            System.out.println("\n------------------------");
            System.out.println("\t SUBMENÚ TABLA HASH");
            System.out.println("------------------------");
            System.out.println("1. Crear tabla hash");
            System.out.println("2. Insertar clave");
            System.out.println("3. Eliminar clave");
            System.out.println("4. Buscar clave");
            System.out.println("5. Mostrar tabla");
            System.out.println("6. Salir");
            System.out.println("------------------------");

            opcion = leerEntero(sc, "Seleccione una opción: "); 

            switch (opcion) {
                case 1: {
                    int tamanio;
                    do {
                        tamanio = leerEntero(sc, "Ingrese el tamaño de la tabla (número primo): "); // Validación de entero
                        if (!esPrimo(tamanio)) { // Validación: tamaño debe ser primo
                            System.out.println(" El tamaño debe ser un número primo.");
                        }
                    } while (!esPrimo(tamanio));

                    tablaHash = new TablaHash(tamanio);
                    System.out.println("Tabla Hash creada con tamaño " + tamanio);
                    break;
                }
                case 2: {
                    if (tablaHash == null) { // Validación: tabla no creada
                        System.out.println("Primero debe crear la tabla.");
                    } else {
                        int clave = leerEntero(sc, "Ingrese la clave a insertar: "); // Validación de entero
                        if (tablaHash.buscar(clave) != -2) { // Validación: evitar insertar duplicados
                            System.out.println("La clave " + clave + " ya existe.");
                        } else {
                            tablaHash.insertar(clave);
                        }
                    }
                    break;
                }
                case 3: {
                    if (tablaHash == null) { // Validación: tabla no creada
                        System.out.println("Primero debe crear la tabla.");
                    } else {
                        int clave = leerEntero(sc, "Ingrese la clave a eliminar: "); // Validación de entero
                        tablaHash.borrar(clave);
                    }
                    break;
                }
                case 4: {
                    if (tablaHash == null) { // Validación: tabla no creada
                        System.out.println("Primero debe crear la tabla.");
                    } else {
                        int clave = leerEntero(sc, "Ingrese la clave a buscar: "); // Validación de entero
                        int pos = tablaHash.buscar(clave);
                        if (pos != -2) { // Validación: clave encontrada
                            System.out.println("La clave " + clave + " está en la posición " + pos);
                        } else {
                            System.out.println("La clave " + clave + " no se encuentra.");
                        }
                    }
                    break;
                }
                case 5: {
                    if (tablaHash == null) { // Validación: tabla no creada
                        System.out.println("Primero debe crear la tabla.");
                    } else {
                        tablaHash.mostrarTablaHash();
                    }
                    break;
                }
                case 6: {
                    System.out.println("\nSaliendo...");
                    break;
                }
                default:
                    System.out.println("Opción inválida."); 
            }

        } while (opcion != 6);
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

    // Validación: comprobar si un número es primo
    private static boolean esPrimo(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
