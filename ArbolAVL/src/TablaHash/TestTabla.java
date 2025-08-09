package TablaHash;

public class TestTabla {
    public static void main(String[] args) {
        
        //PARA LA CREACION DE LA TABLA, OBLIGAR A COLOCAR UN TAMAÑO QUE SEA
        // UN NUMERO PRIMO, PORQUE SI NO, NO SE PODRA CREAR LA TABLA

        int tamanio = 11;
        TablaHash tablaHash = new TablaHash(tamanio);
        
        //Se insertan valores a la tabla
        tablaHash.insertar(8);
        tablaHash.insertar(25);
        tablaHash.insertar(13);
        tablaHash.insertar(6);
        tablaHash.insertar(16);
        tablaHash.insertar(11);
        tablaHash.insertar(27);
        tablaHash.insertar(9);
        
        //Se muestra la tabla
        tablaHash.mostrarTablaHash();
        
        //Se elimina un elemento
        System.out.println("Eliminar 16");
        tablaHash.borrar(16);
        
        //Se muestra la tabla
        tablaHash.mostrarTablaHash();
        
        System.out.println("Insertar 15");
        tablaHash.insertar(15);
        //Se muestra la tabla
        tablaHash.mostrarTablaHash();

        System.out.println("Insertar 16");
        tablaHash.insertar(16);
        //Se muestra la tabla
        tablaHash.mostrarTablaHash();

        System.out.println("Insertar 23");
        tablaHash.insertar(23);
        //Se muestra la tabla
        tablaHash.mostrarTablaHash();
        
        //Recupera el objeto si es que existe
        int valorBuscado = 13;
        
        if (tablaHash.buscar(valorBuscado) != -2) {
            System.out.println("Existe " + valorBuscado + " en la posicion " + tablaHash.buscar(valorBuscado));
        } else {
            System.out.println("No se encuentra el valor " + valorBuscado);
        }
    }
    
}
