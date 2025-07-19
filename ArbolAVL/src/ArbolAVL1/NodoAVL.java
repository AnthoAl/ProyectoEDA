package ArbolAVL1;

public class NodoAVL {
	int valor;
	NodoAVL derecho;
	NodoAVL izquierdo;
	int altura;

	public NodoAVL(int valor) {
		this.valor = valor;
		this.altura = 1;
	}
}
