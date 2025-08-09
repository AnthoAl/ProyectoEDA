package TablaHash;

public class TablaHash {

    private int[] arregloHash;
    private int tamanioOcupado;
    private int tamanioTabla;
    private int vacio = -1;
    private int eliminado = -2;

    public TablaHash(int tamanio) {
        tamanioTabla = tamanio;
        arregloHash = new int[tamanioTabla];
        for (int i = 0; i < arregloHash.length; i++) {
            arregloHash[i] = vacio;
        }
    }

    public int obtenerPrimoAnterior() {

        int numerPrimo = 0;

        for (int i = tamanioTabla - 1; i >= 1; i--) { // Primo minimo es 2

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
        return clave % tamanioTabla;
    }

    public int funcionHash2(int clave) {
        return obtenerPrimoAnterior() - (clave % obtenerPrimoAnterior());
    }

    public void insertar(int clave) {

        if (tamanioOcupado == tamanioTabla) {
            System.out.println("La tabla está llena");
            return;
        }

        // Verificar si ya existe
        if (buscar(clave) != -2) {
            System.out.println("La clave " + clave + " ya existe en la tabla");
            return;
        }

        int i = 1;
        int hash1 = funcionHash(clave);
        int indice = hash1;

        while (arregloHash[indice] != vacio && i < tamanioTabla && arregloHash[indice] != eliminado) {
            indice = (hash1 + i * funcionHash2(clave)) % tamanioTabla;
            i++;
        }

        arregloHash[indice] = clave;
        tamanioOcupado++;
    }

    public void borrar(int clave) {// REVISAR

        int posicion = buscar(clave);

        if (posicion != -2) {
            arregloHash[posicion] = eliminado; // borrado lógico
            tamanioOcupado--;
        } else {
            System.out.println("No se encontro la clave en la tabla");
        }
    }

    public int buscar(int clave) {// REVISAR

        int i = 1;
        int hash1 = funcionHash(clave);
        int posicion = hash1;

        while (arregloHash[posicion] != vacio && i < tamanioTabla) {
            if (arregloHash[posicion] == clave) {
                return posicion;
            }

            int hash2 = funcionHash2(clave);
            posicion = (hash1 + i * hash2) % tamanioTabla;
            i++;
        }

        return -2; // No se encontro
    }

    public void mostrarTablaHash() {

        System.out.println("Tabla Hash (DOBLE HASH):");

        for (int i = 0; i < tamanioTabla; i++) {

            if (arregloHash[i] == vacio) {
                System.out.println(i + " --> vacío");
            } else if (arregloHash[i] == eliminado) {
                System.out.println(i + " --> eliminado");
            } else {
                System.out.println(i + " --> " + arregloHash[i]);
            }
        }
        System.out.println("");
    }
}