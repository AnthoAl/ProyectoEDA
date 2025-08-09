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
package ArbolB;

public class ArbolB {
    NodoArbolB root;
    int t;

    //Constructor
    public ArbolB(int t) {
        this.t = t;
        root = new NodoArbolB(t);
    }

    public int buscarClaveMayor() {
        int claveMaxima = getClaveMayor(this.root);

        return claveMaxima;
    }

    private int getClaveMayor(NodoArbolB current) {
        if (current == null) {
            return 0;//Si es cero no existe
        }

        //Mientras no sea una hoja
        while (!current.leaf) {
            //Se accede al hijo mas a la derecha
            current = current.child[current.n];
        }

        return claveMayorPorNodo(current);
    }

    private int claveMayorPorNodo(NodoArbolB current) {
        //Devuelve el valor mayor, el que esta mas a la derecha
        return current.key[current.n - 1];
    }

    public void mostrarClavesNodoMinimo() {
        NodoArbolB temp = buscarNodoMinimo(root);

        if (temp == null) {
            System.out.println("Sin minimo");
        } else {
            temp.imprimir();
        }
    }

    public NodoArbolB buscarNodoMinimo(NodoArbolB nodoActual) {
        if (root == null) {
            return null;
        }

        NodoArbolB aux = root;

        //Mientras no sea una hoja
        while (!aux.leaf) {
            //Se accede al primer hijo
            aux = aux.child[0];
        }

        //Devuelve el nodo menor, el que esta mas a la izquierda
        return aux;
    }

    //Busca el valor ingresado y muestra el contenido del nodo que contiene el valor
    public void buscarNodoPorClave(int num) {
        NodoArbolB temp = search(root, num);

        if (temp == null) {
            System.out.println("No se ha encontrado un nodo con el valor ingresado");
        } else {
            print(temp);
        }
    }

    //Search
    private NodoArbolB search(NodoArbolB actual, int key) {
        int i = 0;//se empieza a buscar siempre en la primera posicion

        //Incrementa el indice mientras el valor de la clave del nodo sea menor
        while (i < actual.n && key > actual.key[i]) {
            i++;
        }

        //Si la clave es igual, entonces retornamos el nodo
        if (i < actual.n && key == actual.key[i]) {
            return actual;
        }

        //Si llegamos hasta aqui, entonces hay que buscar los hijos
        //Se revisa primero si tiene hijos
        if (actual.leaf) {
            return null;
        } else {
            //Si tiene hijos, hace una llamada recursiva
            return search(actual.child[i], key);
        }
    }

    public void insertar(int key) {
        NodoArbolB r = root;

        //Si el nodo esta lleno lo debe separar antes de insertar
        if (r.n == ((2 * t) - 1)) {
            NodoArbolB s = new NodoArbolB(t);
            root = s;
            s.leaf = false;
            s.n = 0;
            s.child[0] = r;
            split(s, 0, r);
            nonFullInsert(s, key);
        } else {
            nonFullInsert(r, key);
        }
    }
    
    // Caso cuando la raiz se divide
    // x =          | | | | | |
    //             /
    //      |10|20|30|40|50|
    // i = 0
    // y = |10|20|30|40|50|
    private void split(NodoArbolB x, int i, NodoArbolB y) {
        //Nodo temporal que sera el hijo i + 1 de x
        NodoArbolB z = new NodoArbolB(t);
        z.leaf = y.leaf;
        z.n = (t - 1);

        //Copia las ultimas (t - 1) claves del nodo y al inicio del nodo z      // z = |40|50| | | |
        for (int j = 0; j < (t - 1); j++) {
            z.key[j] = y.key[(j + t)];
        }

        //Si no es hoja hay que reasignar los nodos hijos
        if (!y.leaf) {
            for (int k = 0; k < t; k++) {
                z.child[k] = y.child[(k + t)];
            }
        }

        //nuevo tamanio de y                                                    // x =            | | | | | |
        y.n = (t - 1);                                                          //               /   \
                                                                                //  |10|20| | | |
        //Mueve los hijos de x para darle espacio a z
        for (int j = x.n; j > i; j--) {
            x.child[(j + 1)] = x.child[j];
        }
        //Reasigna el hijo (i+1) de x                                           // x =            | | | | | |
        x.child[(i + 1)] = z;                                                   //               /   \
                                                                                //  |10|20| | | |     |40|50| | | |
        //Mueve las claves de x
        for (int j = x.n; j > i; j--) {
            x.key[(j + 1)] = x.key[j];
        }

        //Agrega la clave situada en la mediana                                 // x =            |30| | | | |
        x.key[i] = y.key[(t - 1)];                                              //               /    \
        x.n++;                                                                  //  |10|20| | | |      |40|50| | | |
    }

    private void nonFullInsert(NodoArbolB x, int key) {
        //Si es una hoja
        if (x.leaf) {
            int i = x.n; //cantidad de valores del nodo
            //busca la posicion i donde asignar el valor
            while (i >= 1 && key < x.key[i - 1]) {
                x.key[i] = x.key[i - 1];//Desplaza los valores mayores a key
                i--;
            }

            x.key[i] = key;//asigna el valor al nodo
            x.n++; //aumenta la cantidad de elementos del nodo
        } else {
            int j = 0;
            //Busca la posicion del hijo
            while (j < x.n && key > x.key[j]) {
                j++;
            }

            //Si el nodo hijo esta lleno lo separa
            if (x.child[j].n == (2 * t - 1)) {
                split(x, j, x.child[j]);

                if (key > x.key[j]) {
                    j++;
                }
            }

            nonFullInsert(x.child[j], key);
        }
    }

    public void showBTree() {
        print(root);
    }

    //Print en preorder
    private void print(NodoArbolB n) {
        n.imprimir();

        //Si no es hoja
        if (!n.leaf) {
            //recorre los nodos para saber si tiene hijos
            for (int j = 0; j <= n.n; j++) {
                if (n.child[j] != null) {
                    System.out.println();
                    print(n.child[j]);
                }
            }
        }
    }
}
