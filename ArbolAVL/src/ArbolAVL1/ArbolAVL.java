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

	private NodoAVL rotacionIzquierda(NodoAVL x) {
		NodoAVL y = x.derecho;
		NodoAVL T2 = y.izquierdo;

		y.izquierdo = x;
		x.derecho = T2;

		x.altura = 1 + Math.max(obtenerAltura(x.izquierdo), obtenerAltura(x.derecho));
		y.altura = 1 + Math.max(obtenerAltura(y.izquierdo), obtenerAltura(y.derecho));

		System.out.println("\nRotación izquierda sobre nodo " + x.valor);
		return y;
	}

	public void insertar(int valor) {
		inicial = insertarNodo(inicial, valor, 0);
	}

	private NodoAVL insertarNodo(NodoAVL nodo, int valor, int nivel) {
		if (nodo == null) {
			if (nivel == 0) {
				System.out.println("Insertando " + valor + " como nodo raíz...");
			} else {
				System.out.println("\nInsertando " + valor + "...");
			}
			return new NodoAVL(valor);
		}

		if (valor < nodo.valor) {
			System.out.println("\nValor " + valor + " < " + nodo.valor + " → va al lado izquierdo");
			nodo.izquierdo = insertarNodo(nodo.izquierdo, valor, nivel + 1);
		} else if (valor > nodo.valor) {
			System.out.println("\nValor " + valor + " > " + nodo.valor + " → va al lado derecho");
			nodo.derecho = insertarNodo(nodo.derecho, valor, nivel + 1);
		} else {
			return nodo;
		}

		nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo), obtenerAltura(nodo.derecho));

		int balance = obtenerBalance(nodo);

		if (balance > 1 && valor < nodo.izquierdo.valor) {
			System.out.println("\nRealizando rotación simple a la derecha (II) en nodo " + nodo.valor);
			return rotacionDerecha(nodo);
		}

		if (balance < -1 && valor > nodo.derecho.valor) {
			System.out.println("\nRealizando rotación simple a la izquierda (DD) en nodo " + nodo.valor);
			return rotacionIzquierda(nodo);
		}

		if (balance > 1 && valor > nodo.izquierdo.valor) {
			System.out.println("\nRealizando rotación doble a la izquierda (ID) en nodo " + nodo.valor);
			nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
			return rotacionDerecha(nodo);
		}

		if (balance < -1 && valor < nodo.derecho.valor) {
			System.out.println("\nRealizando rotación doble a la derecha (DI) en nodo " + nodo.valor);
			nodo.derecho = rotacionDerecha(nodo.derecho);
			return rotacionIzquierda(nodo);
		}

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

	public boolean buscar(int valor) {
		return buscarNodo(inicial, valor, 0);
	}

	private boolean buscarNodo(NodoAVL nodo, int valor, int nivel) {
		if (nodo == null) {
			System.out.println("\nEl valor " + valor + " no se encontró en el árbol.");
			return false;
		}

		if (valor == nodo.valor) {
			System.out.println("\nEl valor " + valor + " se ha encontrado en el nivel " + nivel + ".");
			return true;
		} else if (valor < nodo.valor) {
			return buscarNodo(nodo.izquierdo, valor, nivel + 1);
		} else {
			return buscarNodo(nodo.derecho, valor, nivel + 1);
		}
	}

	public void ejecutarInorden() {
		if (this.inicial == null) {
			System.out.println("\nEl árbol está vacío.");
		} else {
			System.out.print("\nRecorrido Inorden: ");
			recorridoInorden(this.inicial);
			System.out.println();
		}
	}

	public void recorridoInorden(NodoAVL nodo) {
		if (nodo == null) { // caso base llega a nodo hoja
			return;
		} else {
			recorridoInorden(nodo.izquierdo);
			System.out.print(nodo.valor + " ");
			recorridoInorden(nodo.derecho);
		}
	}
}
