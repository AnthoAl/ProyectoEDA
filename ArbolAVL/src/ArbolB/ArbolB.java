package ArbolB;

import java.util.Arrays;

public class ArbolB {
    NodoArbolB raiz;// Raíz del árbol B
    int gradoMinimo;// Grado mínimo t del árbol B 

   // Constructor del árbol B, se inicializa la raíz con un nodo vacío
    public ArbolB(int gradoMinimo) {
        this.gradoMinimo = gradoMinimo;
        raiz = new NodoArbolB(gradoMinimo);
    }

    // ==============================
    // BÚSQUEDA
    // ==============================
    // Método que devuelve true si encuentra la clave
    public boolean buscar(int clave) {
        return buscarNodo(raiz, clave) != null;
    }

    // Método privado recursivo para buscar un nodo que contenga la clave dada
    private NodoArbolB buscarNodo(NodoArbolB actual, int clave) {
        int i = 0;
        // Avanza hasta encontrar la posición donde la clave podría estar o el hijo correcto
        while (i < actual.numeroClaves && clave > actual.claves[i]) {
            i++;
        }
        // Si la clave está en el nodo actual, retorna el nodo
        if (i < actual.numeroClaves && clave == actual.claves[i]) {
            return actual;
        }
        // Si es hoja y no encontró, retorna null
        if (actual.esHoja) {
            return null;
        } else {
            // Si no es hoja, busca recursivamente en el hijo correspondiente
            return buscarNodo(actual.hijos[i], clave);
        }
    }

    // ==============================
    // RECORRIDOS (Inorden y Preorden)
    // ==============================
    public void recorridoInorden() {
        inorden(raiz);
        System.out.println();
    }

    // Método recursivo para recorrido inorden
    private void inorden(NodoArbolB nodo) {
        if (nodo != null) {
            int i;
            for (i = 0; i < nodo.numeroClaves; i++) {
                // Recorrer hijo i antes de la clave i
                if (!nodo.esHoja) {
                    inorden(nodo.hijos[i]);
                }
                // Imprimir la clave i
                System.out.print(nodo.claves[i] + " ");
            }
            // Recorrer el último hijo después de la última clave
            if (!nodo.esHoja) {
                inorden(nodo.hijos[i]);
            }
        }
    }

    // Recorrido preorden que imprime claves y nodos
    public void recorridoPreorden() {
        preorden(raiz);
        System.out.println();
    }

    // Método privado recursivo para recorrido preorden
    private void preorden(NodoArbolB nodo) {
        if (nodo != null) {
            nodo.imprimir();// Imprime todas las claves del nodo
            System.out.print(" ");
            if (!nodo.esHoja) {
                // Recorrer todos los hijos en orden
                for (int i = 0; i <= nodo.numeroClaves; i++) {
                    preorden(nodo.hijos[i]);
                }
            }
        }
    }

    // ==============================
    // ELIMINAR CLAVE
    // ==============================
    public void eliminar(int clave) {
        System.out.println("Eliminando clave: " + clave);
        if (raiz == null) {
            System.out.println("El árbol está vacío");
            return;
        }
        eliminarNodo(raiz, clave);
        // Después de eliminar, revisar si la raíz quedó vacía
        if (raiz.numeroClaves == 0) {
            if (raiz.esHoja) {
                raiz = null;
                System.out.println("El árbol quedó vacío después de la eliminación.");
            } else {
                // Si no es hoja, cambia la raíz por su único hijo
                raiz = raiz.hijos[0];
                System.out.println("Se ha cambiado la raíz después de la eliminación.");
            }
        }
        System.out.println("Eliminación completada para clave: " + clave);
    }

    // Método privado que elimina la clave dentro de un nodo dado
    private void eliminarNodo(NodoArbolB nodo, int clave) {
        System.out.println("Eliminar en nodo con " + nodo.numeroClaves + " claves");
        int idx = encontrarClave(nodo, clave);   // Busca índice donde debería estar la clave
        System.out.println("Índice encontrado para la clave: " + idx);

        if (idx < nodo.numeroClaves && nodo.claves[idx] == clave) {
            // Si la clave está en este nodo
            if (nodo.esHoja) {
                System.out.println("Clave encontrada en hoja. Se remueve directamente.");
                removerDeHoja(nodo, idx);//Remueve la clave directamente
            } else {
                removerDeNoHoja(nodo, idx);//Remueve la clave en el nodo no hoja
            }
        } else {
            // La clave no está en este nodo, ir al hijo correcto
            if (nodo.esHoja) {
                System.out.println("La clave " + clave + " no existe en el árbol");
                return;
            }

            boolean ultima = (idx == nodo.numeroClaves);
            System.out.println("Clave no encontrada en nodo actual. Bajando al hijo " + idx);

            // Si el hijo tiene menos claves que el mínimo, se rellena para mantener propiedades
            if (nodo.hijos[idx].numeroClaves < gradoMinimo) {
                System.out.println("El hijo tiene menos claves que el mínimo. Se rellena.");
                llenar(nodo, idx);
            }

            // Se decide el hijo correcto para continuar la eliminación
            if (ultima && idx > nodo.numeroClaves) {
                eliminarNodo(nodo.hijos[idx - 1], clave);
            } else {
                eliminarNodo(nodo.hijos[idx], clave);
            }
        }
    }

    // Método que devuelve la posición donde debería estar una clave en un nodo
    private int encontrarClave(NodoArbolB nodo, int clave) {
        int idx = 0;
        while (idx < nodo.numeroClaves && nodo.claves[idx] < clave) {
            idx++;
        }
        return idx;
    }

    // Remueve clave en un nodo hoja desplazando las claves a la izquierda
    private void removerDeHoja(NodoArbolB nodo, int idx) {
        for (int i = idx + 1; i < nodo.numeroClaves; i++) {
            nodo.claves[i - 1] = nodo.claves[i];
        }
        nodo.numeroClaves--;
    }

    // Remueve clave en un nodo no hoja, casos según disponibilidad en hijos
    private void removerDeNoHoja(NodoArbolB nodo, int idx) {
        int clave = nodo.claves[idx];
        // Si el hijo izquierdo tiene suficientes claves, usa predecesor
        if (nodo.hijos[idx].numeroClaves >= gradoMinimo) {
            int pred = obtenerPredecesor(nodo, idx);
            nodo.claves[idx] = pred;
            eliminarNodo(nodo.hijos[idx], pred);
        } 
        // Si el hijo derecho tiene suficientes claves, usa sucesor
        else if (nodo.hijos[idx + 1].numeroClaves >= gradoMinimo) {
            int succ = obtenerSucesor(nodo, idx);
            nodo.claves[idx] = succ;
            eliminarNodo(nodo.hijos[idx + 1], succ);
        } 
        // Sino, fusiona hijos y sigue eliminando
        else {
            fusionar(nodo, idx);
            eliminarNodo(nodo.hijos[idx], clave);
        }
    }

    // Obtiene la clave predecesora (máxima en subárbol izquierdo)
    private int obtenerPredecesor(NodoArbolB nodo, int idx) {
        NodoArbolB actual = nodo.hijos[idx];
        while (!actual.esHoja) {
            actual = actual.hijos[actual.numeroClaves];
        }
        return actual.claves[actual.numeroClaves - 1];
    }

    // Obtiene la clave sucesora (mínima en subárbol derecho)
    private int obtenerSucesor(NodoArbolB nodo, int idx) {
        NodoArbolB actual = nodo.hijos[idx + 1];
        while (!actual.esHoja) {
            actual = actual.hijos[0];
        }
        return actual.claves[0];
    }

    // Rellena un hijo que tiene menos claves que el mínimo necesario
    private void llenar(NodoArbolB nodo, int idx) {
        if (idx != 0 && nodo.hijos[idx - 1].numeroClaves >= gradoMinimo) {
            tomarDeAnterior(nodo, idx);//Toma una clave del hermano anterior
        } else if (idx != nodo.numeroClaves && nodo.hijos[idx + 1].numeroClaves >= gradoMinimo) {
            tomarDeSiguiente(nodo, idx);//Toma una clave del hermano siguiente
        } else {
            // Fusiona con un hermano si no se puede tomar de ninguno
            if (idx != nodo.numeroClaves) {
                fusionar(nodo, idx);
            } else {
                fusionar(nodo, idx - 1);
            }
        }
    }

    // Toma una clave del hermano anterior para el hijo en idx
    private void tomarDeAnterior(NodoArbolB nodo, int idx) {
        NodoArbolB hijo = nodo.hijos[idx];
        NodoArbolB hermano = nodo.hijos[idx - 1];

        // Mueve claves y punteros en el hijo para abrir espacio
        for (int i = hijo.numeroClaves - 1; i >= 0; i--) {
            hijo.claves[i + 1] = hijo.claves[i];
        }

        if (!hijo.esHoja) {
            for (int i = hijo.numeroClaves; i >= 0; i--) {
                hijo.hijos[i + 1] = hijo.hijos[i];
            }
        }

        // Pasa la clave del padre al hijo
        hijo.claves[0] = nodo.claves[idx - 1];

        if (!hijo.esHoja) {
            hijo.hijos[0] = hermano.hijos[hermano.numeroClaves];
        }

        // Mueve clave del hermano al padre
        nodo.claves[idx - 1] = hermano.claves[hermano.numeroClaves - 1];

        hijo.numeroClaves++;
        hermano.numeroClaves--;
    }

    // Toma una clave del hermano siguiente para el hijo en idx
    private void tomarDeSiguiente(NodoArbolB nodo, int idx) {
        NodoArbolB hijo = nodo.hijos[idx];
        NodoArbolB hermano = nodo.hijos[idx + 1];

        // Pasa la clave del padre al hijo
        hijo.claves[hijo.numeroClaves] = nodo.claves[idx];

        if (!hijo.esHoja) {
            hijo.hijos[hijo.numeroClaves + 1] = hermano.hijos[0];
        }

        // Pasa la primera clave del hermano al padre
        nodo.claves[idx] = hermano.claves[0];

        // Desplaza las claves y punteros del hermano a la izquierda
        for (int i = 1; i < hermano.numeroClaves; i++) {
            hermano.claves[i - 1] = hermano.claves[i];
        }

        if (!hermano.esHoja) {
            for (int i = 1; i <= hermano.numeroClaves; i++) {
                hermano.hijos[i - 1] = hermano.hijos[i];
            }
        }

        hijo.numeroClaves++;
        hermano.numeroClaves--;
    }

    // Fusiona el hijo en idx con el hermano siguiente
    private void fusionar(NodoArbolB nodo, int idx) {
        NodoArbolB hijo = nodo.hijos[idx];
        NodoArbolB hermano = nodo.hijos[idx + 1];

        // Pasa la clave del nodo padre al hijo
        hijo.claves[gradoMinimo - 1] = nodo.claves[idx];

        // Copia las claves del hermano al hijo
        for (int i = 0; i < hermano.numeroClaves; i++) {
            hijo.claves[i + gradoMinimo] = hermano.claves[i];
        }

        // Copia los hijos del hermano al hijo, si no es hoja
        if (!hijo.esHoja) {
            for (int i = 0; i <= hermano.numeroClaves; i++) {
                hijo.hijos[i + gradoMinimo] = hermano.hijos[i];
            }
        }

        // Mueve las claves y punteros del nodo padre para eliminar clave y puntero
        for (int i = idx + 1; i < nodo.numeroClaves; i++) {
            nodo.claves[i - 1] = nodo.claves[i];
        }

        for (int i = idx + 2; i <= nodo.numeroClaves; i++) {
            nodo.hijos[i - 1] = nodo.hijos[i];
        }

        // Actualiza número de claves
        hijo.numeroClaves += hermano.numeroClaves + 1;
        nodo.numeroClaves--;
    }

    // ==============================
    // INSERTAR CLAVE
    // ==============================
    public void insertar(int clave) {
        NodoArbolB r = raiz;
        // Si la raíz está llena, se debe dividir antes de insertar
        if (r.numeroClaves == ((2 * gradoMinimo) - 1)) {
            System.out.println("Raíz llena, dividiendo...");
            NodoArbolB nuevaRaiz = new NodoArbolB(gradoMinimo);
            raiz = nuevaRaiz;
            nuevaRaiz.esHoja = false;
            nuevaRaiz.numeroClaves = 0;
            nuevaRaiz.hijos[0] = r;
            dividirNodo(nuevaRaiz, 0, r);
            insertarEnNodoNoLleno(nuevaRaiz, clave);
        } else {
            insertarEnNodoNoLleno(r, clave);
        }
        System.out.println("Inserción completada para clave: " + clave);
    }

    // Método privado para dividir un nodo hijo lleno
    private void dividirNodo(NodoArbolB padre, int indiceHijo, NodoArbolB hijoLleno) {
        NodoArbolB nuevoHijo = new NodoArbolB(gradoMinimo);
        nuevoHijo.esHoja = hijoLleno.esHoja;
        nuevoHijo.numeroClaves = (gradoMinimo - 1);

        // Copia la mitad superior de claves al nuevo nodo
        for (int j = 0; j < (gradoMinimo - 1); j++) {
            nuevoHijo.claves[j] = hijoLleno.claves[j + gradoMinimo];
        }

        // Copia hijos si no es hoja
        if (!hijoLleno.esHoja) {
            for (int k = 0; k < gradoMinimo; k++) {
                nuevoHijo.hijos[k] = hijoLleno.hijos[k + gradoMinimo];
            }
        }

        // Reduce el número de claves del nodo dividido
        hijoLleno.numeroClaves = (gradoMinimo - 1);

        // Mueve hijos del padre para abrir espacio para nuevoHijo
        for (int j = padre.numeroClaves; j > indiceHijo; j--) {
            padre.hijos[j + 1] = padre.hijos[j];
        }
        padre.hijos[indiceHijo + 1] = nuevoHijo;

        // Mueve claves del padre para abrir espacio
        for (int j = padre.numeroClaves; j > indiceHijo; j--) {
            padre.claves[j] = padre.claves[j - 1];
        }

        // Coloca la clave media del hijo lleno en el padre
        padre.claves[indiceHijo] = hijoLleno.claves[gradoMinimo - 1];
        padre.numeroClaves++;
        System.out.println("Nodo dividido. Padre ahora tiene " + Arrays.toString(Arrays.copyOf(padre.claves, padre.numeroClaves)));
    }

    // Inserta una clave en un nodo que no está lleno (no tiene el máximo de claves)
    private void insertarEnNodoNoLleno(NodoArbolB nodo, int clave) {
        if (nodo.esHoja) {
            // Si es hoja, inserta la clave desplazando las mayores a la derecha
            int i = nodo.numeroClaves;
            while (i >= 1 && clave < nodo.claves[i - 1]) {
                nodo.claves[i] = nodo.claves[i - 1];
                i--;
            }
            nodo.claves[i] = clave;
            nodo.numeroClaves++;
            System.out.println("Clave " + clave + " insertada en hoja. El nodo ahora tiene " + nodo.numeroClaves + " clave(s).");
        } else {
            // Si no es hoja, busca el hijo adecuado para bajar
            int j = 0;
            while (j < nodo.numeroClaves && clave > nodo.claves[j]) {
                j++;
            }

            // Si el hijo está lleno, se divide primero
            if (nodo.hijos[j].numeroClaves == (2 * gradoMinimo - 1)) {
                System.out.println("El hijo está lleno, se va a dividir");
                dividirNodo(nodo, j, nodo.hijos[j]);
                // Después de dividir, decide a qué hijo ir
                if (clave > nodo.claves[j]) {
                    j++;
                }
            }
            insertarEnNodoNoLleno(nodo.hijos[j], clave);
        }
    }

    // ==============================
    // IMPRESIÓN DEL ÁRBOL
    // ==============================
    public void mostrarArbolB() {
        imprimir(raiz);
    }

    // Método recursivo que imprime cada nodo y sus hijos
    private void imprimir(NodoArbolB nodo) {
        nodo.imprimir();    // Imprime claves del nodo
        if (!nodo.esHoja) {
            for (int j = 0; j <= nodo.numeroClaves; j++) {
                if (nodo.hijos[j] != null) {
                    System.out.println();
                    imprimir(nodo.hijos[j]);   // Imprime recursivamente hijos
                }
            }
        }
    }
}
