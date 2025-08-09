package TablaHash;

public class TablaHash {

    private int[] arregloHash;
    private int tamanio;
    private int tamanioMax;
    private int vacio = -1;

    public TablaHash(int tamanio) {
        tamanioMax = tamanio;
        arregloHash = new int[tamanioMax];
        for (int i = 0; i < arregloHash.length; i++) {
            arregloHash[i] = vacio;
        }
    }

    public int obtenerPrimoAnterior() {

        int numerPrimo = 0;

        for (int i = tamanioMax - 1; i >= 1; i--) {

            boolean esPrimo = true;

            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    esPrimo = false;
                }
            }

            if (esPrimo) {
                numerPrimo = i;
                break;
            }
        }
        return numerPrimo;
    }

    public int funcionHash(int clave) {
        return clave % tamanioMax;
    }

    public int funcionHash2(int clave) {
        return obtenerPrimoAnterior() - (clave % obtenerPrimoAnterior());
    }

    public void insertar(int clave) {

        if (tamanio == tamanioMax) {

            System.out.println("La tabla está llena");
            return;
        }

        int i = 1;
        int hash1 = funcionHash(clave);
        int indice = hash1;

        while (arregloHash[indice] != -1 && i < tamanioMax) {
            indice = (hash1 + i * funcionHash2(clave)) % tamanioMax;
            i++;
        }

        arregloHash[indice] = clave;
        tamanio++;
    }

    public void borrar(int clave) {// REVISAR

        int posicion = buscar(clave);

        if (posicion != -2) {
            arregloHash[posicion] = -1;
            tamanio--;
        } else {
            System.out.println("No se encontro la clave en la tabla");
        }
    }

    public int buscar(int clave) {// REVISAR

        int i = 1;
        int hash1 = funcionHash(clave);
        int posicion = hash1;

        while (arregloHash[posicion] != -1 && i < tamanioMax) {
            if (arregloHash[posicion] == clave) {
                return posicion;
            }

            posicion = (posicion + i * funcionHash2(clave)) % tamanioMax;
            i++;
        }

        return -2;
    }

    public void mostrarTablaHash() {

        System.out.println("Tabla Hash (DOBLE HASH):");

        for (int i = 0; i < tamanioMax; i++) {

            if (arregloHash[i] == -1) {
                System.out.println(i + " --> vacío");
            } else {
                System.out.println(i + " --> " + arregloHash[i]);
            }
        }
        System.out.println("");
    }
}