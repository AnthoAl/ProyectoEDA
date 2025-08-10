package ArbolB;

public class NodoArbolB {


/*
  Clase de un nodo de un Árbol B.
  Un Árbol B es una estructura de datos de búsqueda balanceada,
  utilizada comúnmente en bases de datos y sistemas de archivos
  por su eficiencia al manejar grandes volúmenes de datos.
 */


    int numeroClaves; //numero de claves almacenadas en el nodo
    boolean esHoja; //Si el nodo es hoja (nodo hoja=true; nodo interno=false)
    int claves[];  //almacena las claves en el nodo
    NodoArbolB hijos[]; //arreglo con referencias a los hijos

    //Constructores
    public NodoArbolB(int t) {
        numeroClaves = 0; // Inicialmente no hay claves
        esHoja = true; // Por defecto, el nodo es hoja
        claves = new int[((2 * t) - 1)]; // Espacio para el número máximo de claves
        hijos = new NodoArbolB[(2 * t)]; // Espacio para el número máximo de hijos
    }


    /*
      Imprime el contenido del nodo en formato:
      [clave1 | clave2 | ... | claveN]
      Si es el último elemento, no coloca separaciones.
     */
    public void imprimir() {
        System.out.print("[");
        for (int i = 0; i < numeroClaves; i++) {
            if (i < numeroClaves - 1) {
                System.out.print(claves[i] + " | "); // Clave con separador
            } else {
                System.out.print(claves[i]); // Última clave sin separador
            }
        }
        System.out.print("]");
    }

    
//Busca una clave especifica dentro del nodo
    public int buscarClave(int clave) {
        for (int i = 0; i < numeroClaves; i++) {
            if (claves[i] == clave) {
                return i;  // Si encuentra la clave, retorna su posición
            }
        }
        return -1; // No encontrada
    }
}