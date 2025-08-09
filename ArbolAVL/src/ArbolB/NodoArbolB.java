package ArbolB;

/*
  Clase de un nodo de un Árbol B.
  Un Árbol B es una estructura de datos de búsqueda balanceada,
  utilizada comúnmente en bases de datos y sistemas de archivos
  por su eficiencia al manejar grandes volúmenes de datos.
 */

public class NodoArbolB {

    int n; //numero de claves almacenadas en el nodo
    boolean leaf; //Si el nodo es hoja (nodo hoja=true; nodo interno=false)
    int key[];  //almacena las claves en el nodo
    NodoArbolB child[]; //arreglo con referencias a los hijos

    //Constructores
    public NodoArbolB(int t) {
        n = 0; // Inicialmente no hay claves
        leaf = true; // Por defecto, el nodo es hoja
        key = new int[((2 * t) - 1)]; // Espacio para el número máximo de claves
        child = new NodoArbolB[(2 * t)]; // Espacio para el número máximo de hijos
    }


    /*
      Imprime el contenido del nodo en formato:
      [clave1 | clave2 | ... | claveN]
      Si es el último elemento, no coloca separaciones.
     */
    public void imprimir() {
        System.out.print("[");
        for (int i = 0; i < n; i++) {
            if (i < n - 1) {
                System.out.print(key[i] + " | "); // Clave con separador
            } else {
                System.out.print(key[i]); // Última clave sin separador
            }
        }
        System.out.print("]");
    }

    
//Busca una clave especifica dentro del nodo
    public int find(int k) {
        for (int i = 0; i < n; i++) {
            if (key[i] == k) {
                return i;  // Si encuentra la clave, retorna su posición
            }
        }
        return -1; // No encontrada
    }
}
