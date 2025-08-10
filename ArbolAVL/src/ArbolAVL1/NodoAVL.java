package ArbolAVL1;

public class NodoAVL {
	int valor;//el valor entero que contiene el nodo
	NodoAVL derecho;//puntero hijo derecho
	NodoAVL izquierdo;//puntero hijo izquierdo
	int altura;//se agrega la altura para posteriormente calcular el balance

	public NodoAVL(int valor) {
		this.valor = valor;//se inicializa el valor con el parametro del constructor
		this.altura = 1;//se inicializa la altura del nodo con 1, por ser un nodo hoja
	}
}
