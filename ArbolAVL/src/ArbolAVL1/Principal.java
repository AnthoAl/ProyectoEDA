package ArbolAVL1;

import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
		ArbolAVL arbolAVL = new ArbolAVL();
		Scanner sc = new Scanner(System.in);
		int opcion = 0;

		System.out.println("ÁRBOL AVL\n");
		//int[] valores = { 13, 7, 20, 15, 14 }; // Caso izquierda, izquierda
		//int[] valores = { 13, 7, 20, 24, 25 }; // Caso derecha, derecha
		//int[] valores = { 10, 5, 13, 17, 16 }; // Caso derecha , izquierda
		int[] valores = { 10, 5, 13, 1, 2 }; // Caso izquierda, derecha
		
		for (int valor : valores) {
			arbolAVL.insertar(valor);
		}

		do {
			System.out.println("\n________________________");
			System.out.println("\tMENU");
			System.out.println("________________________");
			System.out.println("1. Insertar Nodo");
			System.out.println("2. Recorrido Inorden");
			System.out.println("3. Eliminar");
			System.out.println("4. Buscar");
			System.out.println("5. Salir");
			System.out.println("________________________");
			System.out.print("Seleccione una opción: ");
			opcion = sc.nextInt();

			switch (opcion) {
			
			case 1: {
				System.out.println("Por favor, ingrese el valor que quiere insertar:");
				int valorNuevo = sc.nextInt();
				arbolAVL.insertar(valorNuevo);
				break;
			}

			case 2: {
				System.out.println("Recorrido Inorden:");
				arbolAVL.ejecutarInorden();
				break;
			}

			case 3: {
				System.out.println("Por favor ingrese el valor a eliminar:");
				int valorEliminar = sc.nextInt();
				arbolAVL.eliminar(valorEliminar);
				System.out.println("Se ha eliminado el nodo con exito");
				break;
			}

			case 4: {
				System.out.println("Por favor ingrese el valor que quiera buscar");
				int buscado = sc.nextInt();
				arbolAVL.buscar(buscado);
				break;
			}
			
			case 5: {
				System.out.println("Saliendo...");
				break;
			}
			default:
				System.out.println("Opción inválida. Seleccione otra");
			}
			
		} while (opcion != 5);
		
		sc.close();
	}
}
