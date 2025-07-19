package ArbolAVL1;

import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
		ArbolAVL arbolAVL=new ArbolAVL();
		Scanner sc=new Scanner (System.in);
		int opcion=0;
		do {
			System.out.println("________________________");
			System.out.println("\tMENU");
			System.out.println("________________________");			
			System.out.println("1. Crear ArbolAVL");
			System.out.println("2. Recorrido Inorden");
			System.out.println("3. Insertar Nodo");
			System.out.println("4. Eliminar");
			System.out.println("5. Buscar");
			System.out.println("6. Salir");
			System.out.println("________________________");	
			opcion=sc.nextInt();
			
			switch (opcion) {
			case 1: {
				System.out.println("¿Cuantos valores desea ingresar inicialmente al arbol?");
				int tamaño=sc.nextInt();
				System.out.println("Por favor ingrese los " + tamaño + " valores");
		        for (int i = 0; i < tamaño; i++) {
		        int valor=sc.nextInt();
		        arbolAVL.insertar(valor);
		        }
				break;
			}
			
			case 2: {
				System.out.println("Recorrido Inorden:");
				arbolAVL.ejecutarInorden();
				break;
			}
			
			case 3: {
				System.out.println("Por favor ingrese el valor que quiere insertar:");
				int valorNuevo=sc.nextInt();
				arbolAVL.insertar(valorNuevo);
			break;
			}
			
			case 4: {
				System.out.println("Por favor ingrese el valor a eliminar:");
				int valorEliminar=sc.nextInt();
				arbolAVL.eliminar(valorEliminar);
				System.out.println("Se ha eliminado el nodo con exito");
				break;
			}
			
			case 5: {
				System.out.println("Por favor ingrese el valor que quiera buscar");
				int buscado=sc.nextInt();
				arbolAVL.buscar(buscado);
				break;
			}
			case 6: {
				System.out.println("Saliendo...");
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion);
			}
		} while (opcion!=6);
		sc.close();
	}
}
