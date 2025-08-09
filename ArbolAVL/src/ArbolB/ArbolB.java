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

    public void eliminar(int clave){
        eliminar(raiz, clave);
        //la raiz se queda sin claves pero si tiene hijos
        if(raiz.numeroClaves == 0 && !raiz.esHoja){
        //raiz apunta a el nodo con claves válidas
             raiz = raiz.hijos[0];
        }
    }

private void eliminar(NodoArbolB nodo, int clave) {
    int indiceAux = 0;
    while (indiceAux < nodo.numeroClaves && nodo.claves[indiceAux] < clave) {
        indiceAux++;
    }

    if (indiceAux < nodo.numeroClaves && nodo.claves[indiceAux] == clave) {
        if (nodo.esHoja)
            eliminarClaveDeNodo(nodo, indiceAux);
        else
            eliminarClaveNodoInterno(nodo, indiceAux);
    } else {
        if (nodo.esHoja) {
            System.out.println("Clave " + clave + " no encontrada");
            return;
        }
        if (nodo.hijos[indiceAux].numeroClaves < gradoMinimo)
            llenar(nodo, indiceAux);
        if (indiceAux > nodo.numeroClaves)
            eliminar(nodo.hijos[indiceAux - 1], clave);
        else
            eliminar(nodo.hijos[indiceAux], clave);
    }
}

private void eliminarClaveDeNodo(NodoArbolB nodo, int indiceAux) {
    for (int i = indiceAux + 1; i < nodo.numeroClaves; i++)
        nodo.claves[i - 1] = nodo.claves[i];
    nodo.numeroClaves--;
}

private void eliminarClaveNodoInterno(NodoArbolB nodo, int indiceAux) {
    int clave = nodo.claves[indiceAux];
    if (nodo.hijos[indiceAux].numeroClaves >= gradoMinimo) {
        int pred = obtenerPredecesor(nodo, indiceAux);
        nodo.claves[indiceAux] = pred;
        eliminar(nodo.hijos[indiceAux], pred);
    } else if (nodo.hijos[indiceAux + 1].numeroClaves >= gradoMinimo) {
        int succ = obtenerSucesor(nodo, indiceAux);
        nodo.claves[indiceAux] = succ;
        eliminar(nodo.hijos[indiceAux + 1], succ);
    } else {
        unir(nodo, indiceAux);
        eliminar(nodo.hijos[indiceAux], clave);
    }
}

private int obtenerPredecesor(NodoArbolB nodo, int indiceAux) {
    NodoArbolB cur = nodo.hijos[indiceAux];
    while (!cur.esHoja)
        cur = cur.hijos[cur.numeroClaves];
    return cur.claves[cur.numeroClaves - 1];
}

private int obtenerSucesor(NodoArbolB nodo, int indiceAux) {
    NodoArbolB cur = nodo.hijos[indiceAux + 1];
    while (!cur.esHoja)
        cur = cur.hijos[0];
    return cur.claves[0];
}

private void llenar(NodoArbolB nodo, int indiceAux) {
    if (indiceAux != 0 && nodo.hijos[indiceAux - 1].numeroClaves >= gradoMinimo)
        prestarDeHermanoIzquierdo(nodo, indiceAux);
    else if (indiceAux != nodo.numeroClaves && nodo.hijos[indiceAux + 1].numeroClaves >= gradoMinimo)
        prestarDeHermanoDerecho(nodo, indiceAux);
    else if (indiceAux != nodo.numeroClaves)
        unir(nodo, indiceAux);
    else
        unir(nodo, indiceAux - 1);
}

private void prestarDeHermanoIzquierdo(NodoArbolB nodo, int indiceAux) {
    NodoArbolB hijo = nodo.hijos[indiceAux], hermano = nodo.hijos[indiceAux - 1];
    for (int i = hijo.numeroClaves - 1; i >= 0; i--) hijo.claves[i + 1] = hijo.claves[i];
    if (!hijo.esHoja) for (int i = hijo.numeroClaves; i >= 0; i--) hijo.hijos[i + 1] = hijo.hijos[i];
    hijo.claves[0] = nodo.claves[indiceAux - 1];
    if (!hijo.esHoja) hijo.hijos[0] = hermano.hijos[hermano.numeroClaves];
    nodo.claves[indiceAux - 1] = hermano.claves[hermano.numeroClaves - 1];
    hijo.numeroClaves++; hermano.numeroClaves--;
}

private void prestarDeHermanoDerecho(NodoArbolB nodo, int indiceAux) {
    NodoArbolB hijo = nodo.hijos[indiceAux], hermano = nodo.hijos[indiceAux + 1];
    hijo.claves[hijo.numeroClaves] = nodo.claves[indiceAux];
    if (!hijo.esHoja) hijo.hijos[hijo.numeroClaves + 1] = hermano.hijos[0];
    nodo.claves[indiceAux] = hermano.claves[0];
    for (int i = 1; i < hermano.numeroClaves; i++) hermano.claves[i - 1] = hermano.claves[i];
    if (!hermano.esHoja) for (int i = 1; i <= hermano.numeroClaves; i++) hermano.hijos[i - 1] = hermano.hijos[i];
    hijo.numeroClaves++; hermano.numeroClaves--;
}

private void unir(NodoArbolB nodo, int indiceAux) {
    NodoArbolB hijo = nodo.hijos[indiceAux], hermano = nodo.hijos[indiceAux + 1];
    hijo.claves[gradoMinimo - 1] = nodo.claves[indiceAux];
    for (int i = 0; i < hermano.numeroClaves; i++) hijo.claves[i + gradoMinimo] = hermano.claves[i];
    if (!hijo.esHoja) for (int i = 0; i <= hermano.numeroClaves; i++) hijo.hijos[i + gradoMinimo] = hermano.hijos[i];
    hijo.numeroClaves += hermano.numeroClaves + 1;
    for (int i = indiceAux + 1; i < nodo.numeroClaves; i++) nodo.claves[i - 1] = nodo.claves[i];
    for (int i = indiceAux + 2; i <= nodo.numeroClaves; i++) nodo.hijos[i - 1] = nodo.hijos[i];
    nodo.numeroClaves--;
}   

    public void mostrarArbolB() {
        imprimir(raiz);
    }
    
private void imprimirInorden(NodoArbolB nodo) {
    if (nodo == null) return;

    for (int i = 0; i < nodo.numeroClaves; i++) {
        // Primero recorrer el hijo i
        if (!nodo.esHoja) {
            imprimirInorden(nodo.hijos[i]);
        }
        // Luego imprimir la clave i
        System.out.print(nodo.claves[i] + " ");
    }
    // Finalmente, recorrer el último hijo
    if (!nodo.esHoja) {
        imprimirInorden(nodo.hijos[nodo.numeroClaves]);
    }
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
