/* 
Escuela Politécnica Nacional
Integrantes: Anahí Achote, Anthony Alangasí, Gary Defas, Lindsay Guzmán, Jarvin Ríos
Grupo 1
Curso: GR1CC
Fecha: 21/07/2025
*/
package ArbolAVL1;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Principal {
	public static void main(String[] args) {
		ArbolAVL arbolAVL = new ArbolAVL();
		Scanner sc = new Scanner(System.in);
		int opcion = 0;

		System.out.println("Proyecto: Creación de un árbol AVL\n");
		//int[] valores = { 13, 7, 20, 15, 14 }; // Caso izquierda, izquierda
		//int[] valores = { 13, 7, 20, 24, 25 }; // Caso derecha, derecha
		int[] valores = { 10, 5, 13, 17, 16 }; // Caso derecha , izquierda
		//int[] valores = { 10, 5, 13, 1, 2 }; // Caso izquierda, derecha

		for (int valor : valores) {
			arbolAVL.insertar(valor);
		}

		System.out.println("\nÁrbol AVL creado correctamente\n");

		do {
			System.out.println("\n------------------------");
			System.out.println("\t MENU");
			System.out.println("------------------------");
			System.out.println("1. Insertar Nodo");
			System.out.println("2. Recorrido Inorden");
			System.out.println("3. Eliminar Nodo");
			System.out.println("4. Buscar Nodo");
			System.out.println("5. Salir");
			System.out.println("------------------------");
			System.out.print("Seleccione una opción: ");
			opcion = leerEntero(sc, "Seleccione una opción: "); 

			switch (opcion) {
			
			case 1: {
                    int valorNuevo = leerEntero(sc, "\nIngrese el valor que quiere insertar: "); // Validación de entero
                    if (arbolAVL.buscar(valorNuevo)) { // Validación: evitar insertar duplicados
                        System.out.println("\nEl valor " + valorNuevo + " ya existe en el árbol.");
                    } else {
                        arbolAVL.insertar(valorNuevo);
                    }
                    break;
                }

			case 2: {
				arbolAVL.ejecutarInorden();
				break;
			}

			case 3: {
                    if (arbolAVL.inicial == null) { // Validación: árbol vacío
                        System.out.println("El árbol está vacío, no hay nodos para eliminar.");
                    } else {
                        int valorEliminar = leerEntero(sc, "\nIngrese el valor a eliminar: "); // Validación de entero
                        if (arbolAVL.buscar(valorEliminar)) { // Validación: nodo existente antes de eliminar
                            arbolAVL.eliminar(valorEliminar);
                        } else {
                            System.out.println(" El valor " + valorEliminar + " no existe en el árbol.");
                        }
                    }
                    break;
                }

			case 4: {
                    if (arbolAVL.inicial == null) { // Validación: árbol vacío
                        System.out.println("\nEl árbol está vacío, no hay nodos para buscar.");
                    } else {
                        int buscado = leerEntero(sc, "\nIngrese el valor que quiera buscar: "); // Validación de entero
                        arbolAVL.buscar(buscado);
                    }
                    break;
                }

			case 5: {
				System.out.println("\nSaliendo...");
				break;
			}

			default:
				System.out.println("\nOpción inválida. Seleccione otra opción"); 
			}
			
		} while (opcion != 5);
		
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
            } catch (InputMismatchException e) { // Manejo de error si el usuario ingresa algo que no sea entero
                System.out.println("Error: Debe ingresar un número entero.");
                sc.nextLine(); 
            }
        }
    }
}

