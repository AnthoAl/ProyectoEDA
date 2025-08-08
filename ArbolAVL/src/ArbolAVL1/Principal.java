/* 
Escuela Politécnica Nacional
Integrantes: Anahí Achote, Anthony Alangasí, Gary Defas, Lindsay Guzmán, Jarvin Ríos
Grupo 1
Curso: GR1CC
Fecha: 21/07/2025
*/
package ArbolAVL1;

import java.util.Scanner;

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
			opcion = sc.nextInt();

			switch (opcion) {
			
			case 1: {
				System.out.print("\nIngrese el valor que quiere insertar: ");
				int valorNuevo = sc.nextInt();
				arbolAVL.insertar(valorNuevo);
				break;
			}

			case 2: {
				arbolAVL.ejecutarInorden();
				break;
			}

			case 3: {
				System.out.print("\nIngrese el valor a eliminar: ");
				int valorEliminar = sc.nextInt();
				arbolAVL.eliminar(valorEliminar);
				break;
			}

			case 4: {
				System.out.print("\nIngrese el valor que quiera buscar: ");
				int buscado = sc.nextInt();
				arbolAVL.buscar(buscado);
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
}
