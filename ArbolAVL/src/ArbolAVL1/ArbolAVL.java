package ArbolAVL1;

public class ArbolAVL {
	NodoAVL inicial;

	private int obtenerAltura(NodoAVL nodo) {
		return (nodo == null) ? 0 : nodo.altura;
	}

	private int obtenerBalance(NodoAVL nodo) {
		return (nodo == null) ? 0 : obtenerAltura(nodo.izquierdo) - obtenerAltura(nodo.derecho);
	}

	private NodoAVL rotacionDerecha(NodoAVL y) {
		NodoAVL x = y.izquierdo;
		NodoAVL T2 = x.derecho;

		x.derecho = y;
		y.izquierdo = T2;

		y.altura = 1 + Math.max(obtenerAltura(y.izquierdo), obtenerAltura(y.derecho));
		x.altura = 1 + Math.max(obtenerAltura(x.izquierdo), obtenerAltura(x.derecho));

		System.out.println("\nRotación derecha sobre nodo " + y.valor);
		return x;
	}

	/*
	 * Método para realizar la rotación simple izquierda (DD) sobre un nodo
	 */
	private NodoAVL rotacionIzquierda(NodoAVL x) { // Recibe como parámetro el nodo que tuvo un balance igual a -2
		NodoAVL y = x.derecho; // Se guarda en una variable tipo NodoAVL el hijo derecho del nodo x
		NodoAVL T2 = y.izquierdo; // Se guarda en una variable tipo NodoAVL el hijo izquierdo del nodo y

		y.izquierdo = x; // Para realizar la rotación, el nodo x pasa a ser hijo izquierdo del nodo y
		x.derecho = T2;  // Y el nodo T2 pasa a ser hijo derecho del nodo x

		x.altura = 1 + Math.max(obtenerAltura(x.izquierdo), obtenerAltura(x.derecho)); // Se actualiza la altura del nodo x
		y.altura = 1 + Math.max(obtenerAltura(y.izquierdo), obtenerAltura(y.derecho)); // Se actualiza la altura del nodo y

		System.out.println("\nRotación izquierda sobre nodo " + x.valor); // Se imprime un mensaje para indicar el tipo de rotación realizada
		return y; // Se devuelve el nuevo nodo raíz, en este caso, el nodo y
	}

	/*
	 * Inserta un valor en el árbol AVL.
	 * Este método llama al método recursivo insertarNodo empezando
	 * desde la raíz del árbol (inicial) y en el nivel 0.
	 */
	public void insertar(int valor) {
		inicial = insertarNodo(inicial, valor, 0);
	}

	/*
	 * Método recursivo para insertar un nuevo valor en el árbol AVL.
	 * 
	 * Funcionamiento:
	 * 1. Si el nodo actual es null:
	 * - Si es el nivel 0 → significa que el valor será la raíz.
	 * - Si no, será un nodo hoja en la posición correspondiente.
	 * En ambos casos, se crea y retorna un nuevo NodoAVL con el valor.
	 * 
	 * 2. Si el valor a insertar es menor que el del nodo actual:
	 * - Se continúa la inserción por el subárbol izquierdo.
	 * 
	 * 3. Si el valor a insertar es mayor que el del nodo actual:
	 * - Se continúa la inserción por el subárbol derecho.
	 * 
	 * 4. Si el valor es igual → no se inserta (los AVL no permiten duplicados).
	 * 
	 * 5. Después de insertar:
	 * - Se actualiza la altura del nodo actual.
	 * - Se calcula el factor de balance para verificar si el nodo quedó
	 * desbalanceado.
	 * - Si el balance es mayor a 1 o menor a -1, se aplican las rotaciones
	 * correspondientes (simples o dobles) para mantener la propiedad del AVL.
	 * 
	 * Tipos de rotaciones:
	 * - Rotación simple a la derecha (II): caso de inserción en el subárbol
	 * izquierdo-izquierdo.
	 * - Rotación simple a la izquierda (DD): caso de inserción en el subárbol
	 * derecho-derecho.
	 * - Rotación doble izquierda (ID): caso de inserción en el subárbol
	 * izquierdo-derecho.
	 * - Rotación doble derecha (DI): caso de inserción en el subárbol
	 * derecho-izquierdo.
	 */
	private NodoAVL insertarNodo(NodoAVL nodo, int valor, int nivel) {

		// Caso base: el nodo actual es null → insertar nuevo nodo aquí
		if (nodo == null) {
			if (nivel == 0) {
				System.out.println("Insertando " + valor + " como nodo raíz...");
			} else {
				System.out.println("\nInsertando " + valor + "...");
			}
			return new NodoAVL(valor);
		}

		// Si el valor es menor, insertar en el lado izquierdo
		if (valor < nodo.valor) {
			System.out.println("\nValor " + valor + " < " + nodo.valor + " → va al lado izquierdo");
			nodo.izquierdo = insertarNodo(nodo.izquierdo, valor, nivel + 1);

			// Si el valor es mayor, insertar en el lado derecho
		} else if (valor > nodo.valor) {
			System.out.println("\nValor " + valor + " > " + nodo.valor + " → va al lado derecho");
			nodo.derecho = insertarNodo(nodo.derecho, valor, nivel + 1);

			// Si es igual, no se inserta y se retorna el nodo actual
		} else {
			return nodo;
		}

		// Actualizar altura del nodo actual
		nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));

		// Calcular el balance del nodo actual
		int balance = obtenerBalance(nodo);

		// Caso IZQUIERDA-IZQUIERDA (rotación simple a la derecha)
		if (balance > 1 && valor < nodo.izquierdo.valor) {
			System.out.println("\nRealizando rotación simple a la derecha (II) en nodo " + nodo.valor);
			return rotacionDerecha(nodo);
		}

		// Caso DERECHA-DERECHA (rotación simple a la izquierda)
		if (balance < -1 && valor > nodo.derecho.valor) {
			System.out.println("\nRealizando rotación simple a la izquierda (DD) en nodo " + nodo.valor);
			return rotacionIzquierda(nodo);
		}

		// Caso IZQUIERDA-DERECHA (rotación doble izquierda)
		if (balance > 1 && valor > nodo.izquierdo.valor) {
			System.out.println("\nRealizando rotación doble a la izquierda (ID) en nodo " + nodo.valor);
			nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
			return rotacionDerecha(nodo);
		}

		// Caso DERECHA-IZQUIERDA (rotación doble derecha)
		if (balance < -1 && valor < nodo.derecho.valor) {
			System.out.println("\nRealizando rotación doble a la derecha (DI) en nodo " + nodo.valor);
			nodo.derecho = rotacionDerecha(nodo.derecho);
			return rotacionIzquierda(nodo);
		}

		// Si no hay desbalance, retornar el nodo actual
		return nodo;
	}

	public void eliminar(int valor) {
		if (buscar(valor)) {
			inicial = eliminarNodo(inicial, valor);
		}
	}

	private NodoAVL eliminarNodo(NodoAVL nodo, int valor) {

		if (nodo == null)
			return null;

		if (valor < nodo.valor) {
			nodo.izquierdo = eliminarNodo(nodo.izquierdo, valor);
		} else if (valor > nodo.valor) {
			nodo.derecho = eliminarNodo(nodo.derecho, valor);
		} else {
			// Nodo con un solo hijo o sin hijos
			if (nodo.izquierdo == null || nodo.derecho == null) {
				nodo = (nodo.izquierdo != null) ? nodo.izquierdo : nodo.derecho;
				System.out.println("\nSe ha eliminado el nodo con éxito");
			} else {
				// Nodo con dos hijos: buscar sucesor
				NodoAVL sucesor = obtenerMinimo(nodo.derecho);
				nodo.valor = sucesor.valor;
				nodo.derecho = eliminarNodo(nodo.derecho, sucesor.valor);
			}
		}

		// Si el nodo ya fue eliminado
		if (nodo == null)
			return null;

		// Actualizar altura
		nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));

		// Obtener balance del nodo
		int balance = obtenerBalance(nodo);

		// Rotaciones
		if (balance > 1 && obtenerBalance(nodo.izquierdo) >= 0) {
			return rotacionDerecha(nodo);
		}

		if (balance > 1 && obtenerBalance(nodo.izquierdo) < 0) {
			nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
			return rotacionDerecha(nodo);
		}

		if (balance < -1 && obtenerBalance(nodo.derecho) <= 0) {
			return rotacionIzquierda(nodo);
		}

		if (balance < -1 && obtenerBalance(nodo.derecho) > 0) {
			nodo.derecho = rotacionDerecha(nodo.derecho);
			return rotacionIzquierda(nodo);
		}

		return nodo;
	}

	private NodoAVL obtenerMinimo(NodoAVL nodo) {
		while (nodo.izquierdo != null) {
			nodo = nodo.izquierdo;
		}
		return nodo;
	}

	/*
	 * Busca un valor específico dentro del árbol AVL.
	 * Este método llama al método recursivo buscarNodo empezando desde la raíz del
	 * árbol (inicial) y en el nivel 0.
	 */
	public boolean buscar(int valor) {
		return buscarNodo(inicial, valor, 0);
	}

	/*
	 * Método recursivo para buscar un valor en el árbol AVL.
	 * Funcionamiento:
	 * 1. Si el nodo actual es null, significa que se llegó al final de la rama
	 * y el valor no está en el árbol → retorna false.
	 * 2. Si el valor del nodo actual coincide con el buscado, muestra un mensaje
	 * indicando en qué nivel se encontró y retorna true.
	 * 3. Si el valor buscado es menor al valor del nodo actual,
	 * la búsqueda continúa por el subárbol izquierdo.
	 * 4. Si el valor buscado es mayor, continúa por el subárbol derecho.
	 */
	private boolean buscarNodo(NodoAVL nodo, int valor, int nivel) {
		// Caso base: el nodo actual es null → valor no encontrado
		if (nodo == null) {
			System.out.println("\nEl valor " + valor + " no se encontró en el árbol.");
			return false;
		}

		// Caso de éxito: el valor buscado está en el nodo actual
		if (valor == nodo.valor) {
			System.out.println("\nEl valor " + valor + " se ha encontrado en el nivel " + nivel + ".");
			return true;

			// Si el valor es menor, buscar en el subárbol izquierdo
		} else if (valor < nodo.valor) {
			return buscarNodo(nodo.izquierdo, valor, nivel + 1);

			// Si el valor es mayor, buscar en el subárbol derecho
		} else {
			return buscarNodo(nodo.derecho, valor, nivel + 1);
		}
	}

	/**
	 * Ejecuta el recorrido inorden de un árbol binario AVL.
	 * El recorrido inorden visita los nodos en este orden
	 * 1. Subárbol izquierdo
	 * 2. Nodo actual
	 * 3. Subárbol derecho
	 * 
	 * Si el árbol está vacío, muestra un mensaje indicándolo.
	 */
	public void ejecutarInorden() {

		// Verifica si el árbol está vacío (sin nodos)
		if (this.inicial == null) {
			System.out.println("\nEl árbol está vacío."); // Mensaje si no hay elementos
		} else {
			// Si hay nodos, inicia el recorrido inorden desde la raíz
			System.out.print("\nRecorrido Inorden: ");

			// Llama al método que realiza el recorrido inorden recursivamente
			recorridoInorden(this.inicial);

			// Salto de línea al finalizar el recorrido
			System.out.println();
		}
	}

	/*
	 * Realiza el recorrido inorden de manera recursiva.
	 * Funciona así:
	 * - Si el nodo es null, significa que se ha llegado al final de una rama y se
	 * detiene (caso base).
	 * - Primero recorre todo el subárbol izquierdo.
	 * - Luego muestra el valor del nodo actual.
	 * - Finalmente recorre todo el subárbol derecho.
	 */
	public void recorridoInorden(NodoAVL nodo) {
		if (nodo == null) {
			// Caso base: se ha llegado a un nodo vacío (hoja sin hijos)
			return;
		} else {
			// Recorrer recursivamente el subárbol izquierdo
			recorridoInorden(nodo.izquierdo);

			// Procesar (imprimir) el valor del nodo actual
			System.out.print(nodo.valor + " ");

			// Recorrer recursivamente el subárbol derecho
			recorridoInorden(nodo.derecho);
		}
	}

}
