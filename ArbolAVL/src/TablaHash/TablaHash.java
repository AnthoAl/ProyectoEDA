package TablaHash;

public class TablaHash {

    private int[] arregloHash;  //Arreglo donde se guardarán las claves.
    private int tamanioOcupado; //Número de claves que se han ingresado en el arreglo.
    private int tamanioTabla;   //Tamaño del arreglo.
    private int vacio = -1;     //Valor que se utiliza para indicar que una posición del arreglo está vacía.
    private int eliminado = -2; //Valor que se utiliza para indicar que una clave del arreglo ha sido eliminada (borrado lógico).

    // Constructor que inicializa el arreglo con un tamaño especificado por el usuario.
    public TablaHash(int tamanio) {                    
        tamanioTabla = tamanio;                        //Se asigna el tamaño del arreglo.
        arregloHash = new int[tamanioTabla];           //Se crea el arreglo con el tamaño especificado.
        for (int i = 0; i < arregloHash.length; i++) { //Se recorre el arreglo y se inicializan todas las posiciones con el valor vacío.
            arregloHash[i] = vacio; 
        }
    }

    //Método que retorna el número primo anterior al tamaño de la tabla.
    public int obtenerPrimoAnterior() { 

        int numeroPrimo = 0; //Variable que se utiliza para almacenar el número primo.

        for (int i = tamanioTabla - 1; i >= 2; i--) { //Se recorre el rango de números que hay desde el tamaño de la tabla menos 1 hasta el 2, que es el número primo más pequeño.

            boolean esPrimo = true; //Variable que indica si el número es primo.

            for (int j = 2; j * j <= i; j++) { //Se verifica si el número es divisible por algún otro número.
                if (i % j == 0) {
                    esPrimo = false; //Si el número es divisible, entonces no es primo.
                }
            }

            if (esPrimo) { //Si el número es primo, entonces se asigna a la variable numeroPrimo.
                numeroPrimo = i;
                break;
            }
        }
        return numeroPrimo; 
    }

    //Método que calcula el índice de una clave en el arreglo a través de la fórmula de hash.
    public int funcionHash(int clave) { 
        return clave % tamanioTabla;
    }

    //Método que emplea la segunda función de hash para retornar un valor en base a la clave dada si se ha producido una colisión.
    public int funcionHash2(int clave) {
        return obtenerPrimoAnterior() - (clave % obtenerPrimoAnterior()); //Se utiliza la primera fórmula común para la segunda función de hash vista en clase.
    }

    //Método para insertar una clave en el arreglo.
    public void insertar(int clave) {

        if (tamanioOcupado == tamanioTabla) { //Se verifica si el arreglo está lleno. Si lo está, entonces se imprime un mensaje de error.
            System.out.println("No se puede insertar la clave porque la tabla está llena");
            return;
        }

        if (buscar(clave) != -2) { //Se verifica si la clave ya existe. Si es así, entonces se imprime un mensaje de error.
            System.out.println("La clave " + clave + " ya existe en la tabla");
            return;
        }

        int i = 1;                      //Se inicializa el número de intentos en 1 puesto que si al ingresar una clave no se produce una colisión, entonces el número de intentos será 1.
        int hash1 = funcionHash(clave); //Se calcula el índice de la clave mediante la primera función de hash.
        int indice = hash1;             //Se almacena el índice calculado en la variable indice.

        while (arregloHash[indice] != vacio && i < tamanioTabla && arregloHash[indice] != eliminado) { //Se verifica si el índice calculado está ocupado.
            indice = (hash1 + i * funcionHash2(clave)) % tamanioTabla;                                 //Si lo está, se calcula el nuevo índice mediante la segunda función de hash.
            i++;                                                                                       //Y se incrementa el número de intentos.
        }

        arregloHash[indice] = clave; //Se inserta la clave en el indice calculado.
        tamanioOcupado++;            //Se actualiza el número de claves almacenadas en el arreglo.
    }

    //Método para eliminar una clave del arreglo.
    public void borrar(int clave) {

        int posicion = buscar(clave); //Se busca la posición de la clave en el arreglo.

        if (posicion != -1) {                   //Se verifica si la clave existe. Si es así, entonces se puede eliminar.
            arregloHash[posicion] = eliminado;  //Se utiliza el valor eliminado para indicar que se ha eliminado la clave en esa posición.
            tamanioOcupado--;                   //Se actualiza el número de claves almacenadas en el arreglo.
        } else {
            System.out.println("No se encontro la clave en la tabla"); //Si la clave no existe, entonces se imprime un mensaje de error.
        }
    }

    public int buscar(int clave) {

        int i = 1;                       //Se inicializa el número de intentos en 1.
        int hash1 = funcionHash(clave);  //Se calcula el índice de la clave mediante la primera función de hash.
        int posicion = hash1;            //Se almacena el índice calculado en la variable posicion.

        while (arregloHash[posicion] != vacio && i < tamanioTabla) { //Se verifica si el índice calculado está ocupado.
            if (arregloHash[posicion] == clave) {                    //Si lo está y la clave coincide con la almacenada en el arreglo, entonces se retorna la posición.
                return posicion;                                     
            }
                       
            posicion = (hash1 + i * funcionHash2(clave)) % tamanioTabla; //Si no coincide, entonces se calcula el nuevo índice mediante la segunda función de hash.
            i++;                                                         //Y se incrementa el número de intentos.
        }

        return -1; //Se retorna -1 para indicar que la clave no se encuentra en el arreglo.
    }

    //Método para imprimir el arreglo.
    public void mostrarTablaHash() {

        System.out.println("Tabla Hash (DOBLE HASH):"); 

        for (int i = 0; i < tamanioTabla; i++) { //Se recorre cada uno de los índices del arreglo.

            if (arregloHash[i] == vacio) {                        //Si el índice está vacío, entonces se imprime un mensaje indicando que está vacío.
                System.out.println(i + " --> vacío");     
            } else if (arregloHash[i] == eliminado) {
                System.out.println(i + " --> eliminado");         //Si una clave fue eliminada de ese índice, entonces se imprime un mensaje indicando que está vacío.
            } else {
                System.out.println(i + " --> " + arregloHash[i]); //Si el índice está ocupado, entonces se imprime el valor almacenado.
            }
        }
        System.out.println("");
    }
}