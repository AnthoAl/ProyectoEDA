package ArbolB;

public class ArbolB {
    NodoArbolB raiz;
    int gradoMinimo;

    // Constructor
    public ArbolB(int gradoMinimo) {
        this.gradoMinimo = gradoMinimo;
        raiz = new NodoArbolB(gradoMinimo);
    }

    public int buscarClaveMayor() {
        int claveMaxima = obtenerClaveMayor(this.raiz);
        return claveMaxima;
    }

    private int obtenerClaveMayor(NodoArbolB actual) {
        if (actual == null) {
            return 0; // Si es cero, no existe
        }

        // Mientras no sea una hoja, avanzar al hijo más a la derecha
        while (!actual.esHoja) {
            actual = actual.hijos[actual.numeroClaves];
        }

        return claveMayorEnNodo(actual);
    }

    private int claveMayorEnNodo(NodoArbolB actual) {
        // Devuelve la clave mayor, que está más a la derecha
        return actual.claves[actual.numeroClaves - 1];
    }

    public void mostrarClavesNodoMinimo() {
        NodoArbolB temp = buscarNodoMinimo(raiz);

        if (temp == null) {
            System.out.println("Sin mínimo");
        } else {
            temp.imprimir();
        }
    }

    public NodoArbolB buscarNodoMinimo(NodoArbolB actual) {
        if (raiz == null) {
            return null;
        }

        NodoArbolB aux = raiz;

        // Mientras no sea una hoja, ir al primer hijo
        while (!aux.esHoja) {
            aux = aux.hijos[0];
        }

        // Devuelve el nodo menor, el que está más a la izquierda
        return aux;
    }

    // Busca una clave y muestra el nodo que la contiene
    public void buscarNodoPorClave(int clave) {
        NodoArbolB temp = buscar(raiz, clave);

        if (temp == null) {
            System.out.println("No se ha encontrado un nodo con la clave ingresada");
        } else {
            imprimir(temp);
        }
    }

    private NodoArbolB buscar(NodoArbolB actual, int clave) {
        int i = 0; // Se empieza siempre desde la primera posición

        // Avanzar mientras la clave buscada sea mayor que la clave actual
        while (i < actual.numeroClaves && clave > actual.claves[i]) {
            i++;
        }

        // Si se encuentra la clave, devolver el nodo
        if (i < actual.numeroClaves && clave == actual.claves[i]) {
            return actual;
        }

        // Si es hoja, ya no se puede buscar más
        if (actual.esHoja) {
            return null;
        } else {
            // Buscar recursivamente en el hijo correspondiente
            return buscar(actual.hijos[i], clave);
        }
    }

    public void insertar(int clave) {
        NodoArbolB r = raiz;

        // Si la raíz está llena, dividirla antes de insertar
        if (r.numeroClaves == ((2 * gradoMinimo) - 1)) {
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
    }

    // Divide un nodo lleno
    private void dividirNodo(NodoArbolB padre, int indiceHijo, NodoArbolB hijoLleno) {
        NodoArbolB nuevoHijo = new NodoArbolB(gradoMinimo);
        nuevoHijo.esHoja = hijoLleno.esHoja;
        nuevoHijo.numeroClaves = (gradoMinimo - 1);

        // Copiar las últimas claves al nuevo hijo
        for (int j = 0; j < (gradoMinimo - 1); j++) {
            nuevoHijo.claves[j] = hijoLleno.claves[j + gradoMinimo];
        }

        // Copiar hijos si no es hoja
        if (!hijoLleno.esHoja) {
            for (int k = 0; k < gradoMinimo; k++) {
                nuevoHijo.hijos[k] = hijoLleno.hijos[k + gradoMinimo];
            }
        }

        hijoLleno.numeroClaves = (gradoMinimo - 1);

        // Mover hijos del padre para hacer espacio al nuevo hijo
        for (int j = padre.numeroClaves; j > indiceHijo; j--) {
            padre.hijos[j + 1] = padre.hijos[j];
        }
        padre.hijos[indiceHijo + 1] = nuevoHijo;

        // Mover claves del padre
        for (int j = padre.numeroClaves; j > indiceHijo; j--) {
            padre.claves[j] = padre.claves[j - 1];
        }

        // Insertar clave mediana en el padre
        padre.claves[indiceHijo] = hijoLleno.claves[gradoMinimo - 1];
        padre.numeroClaves++;
    }

    private void insertarEnNodoNoLleno(NodoArbolB nodo, int clave) {
        if (nodo.esHoja) {
            int i = nodo.numeroClaves;
            // Buscar posición para insertar
            while (i >= 1 && clave < nodo.claves[i - 1]) {
                nodo.claves[i] = nodo.claves[i - 1];
                i--;
            }
            nodo.claves[i] = clave;
            nodo.numeroClaves++;
        } else {
            int j = 0;
            while (j < nodo.numeroClaves && clave > nodo.claves[j]) {
                j++;
            }

            if (nodo.hijos[j].numeroClaves == (2 * gradoMinimo - 1)) {
                dividirNodo(nodo, j, nodo.hijos[j]);
                if (clave > nodo.claves[j]) {
                    j++;
                }
            }

            insertarEnNodoNoLleno(nodo.hijos[j], clave);
        }
    }

    public void mostrarArbolB() {
        imprimir(raiz);
    }

    // Imprime en preorden
    private void imprimir(NodoArbolB nodo) {
        nodo.imprimir();

        if (!nodo.esHoja) {
            for (int j = 0; j <= nodo.numeroClaves; j++) {
                if (nodo.hijos[j] != null) {
                    System.out.println();
                    imprimir(nodo.hijos[j]);
                }
            }
        }
    }
}
