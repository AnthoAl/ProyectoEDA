package ArbolAVL1;

public class NodoAVL {
	int valor;
	NodoAVL derecho;
	NodoAVL izquierdo;
	int altura;//importante para posteriormente calcular el balance

	public NodoAVL(int valor) {
		this.valor = valor;
		this.altura = 1;//se inicializa la altura del nodo con 1
	}
}
