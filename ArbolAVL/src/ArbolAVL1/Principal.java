package ArbolAVL1;

import java.util.Scanner;

public class Principal {
	public static void ejecutar(Scanner sc) {
		ArbolAVL arbolAVL = new ArbolAVL();
		int opcion = 0;

		// System.out.println("\nÁrbol AVL creado correctamente\n");

		do {
			System.out.println("\n------------------------");
			System.out.println("MENÚ ÁRBOL AVL");
			System.out.println("------------------------");
			System.out.println("1. Insertar Nodo");
			System.out.println("2. Recorrido Inorden");
			System.out.println("3. Eliminar Nodo");
			System.out.println("4. Buscar Nodo");
			System.out.println("5. Salir");
			System.out.println("------------------------");
			opcion = leerEntero(sc, "\nSeleccione una opción: ");

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
						System.out.println("\nEl árbol está vacío, no hay nodos para eliminar.");
					} else {
						int valorEliminar = leerEntero(sc, "\nIngrese el valor a eliminar: "); // Validación de entero
						if (arbolAVL.buscar(valorEliminar)) { // Validación: nodo existente antes de eliminar
							arbolAVL.eliminar(valorEliminar);
						} else {
							System.out.println("\nEl valor " + valorEliminar + " no existe en el árbol.");
						}
					}
					break;
				}

				case 4: {
					if (arbolAVL.inicial == null) { // Validación: árbol vacío
						System.out.println("\nEl árbol está vacío, no hay nodos para buscar.");
					} else {
						int buscado = leerEntero(sc, "\nIngrese el valor que quiere buscar: ");
						if (arbolAVL.buscar(buscado)) {
							System.out.println("\nEl valor " + buscado + " se encuentra en el árbol.");
						} else {
							System.out.println("\nEl valor " + buscado + " NO se encuentra en el árbol.");
						}
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

	}

	// Método auxiliar para leer enteros con validación
	private static int leerEntero(Scanner sc, String mensaje) {
		int numero;
		while (true) {
			try {
				System.out.print(mensaje);
				numero = sc.nextInt();
				sc.nextLine();

                if (numero > 0) { // Verifica que sea positivo
                    return numero;
                } else {
                    System.out.println("\nError: debe ingresar un número entero positivo.");
                }
			} catch (Exception e) { // Manejo de error si el usuario ingresa algo que no sea entero
				System.out.println("\nError: Debe ingresar un número entero.");
				sc.nextLine();
			}
		}
	}
}
