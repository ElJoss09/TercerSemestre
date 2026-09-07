package Tareas.Tarea3;

/**
 * TDA Lista Circular - Inserción y Eliminación Controlada
 * 
 * @author Justin Alexander Guanoquiza Aguaguiña
 * @institution Universidad Técnica de Ambato
 */

public class ListaCircularControlada {
    private Nodo cabeza;
    private Nodo cola;
    private int tamanio;

    public ListaCircularControlada() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    public void mostrar() {
        if (cabeza == null) {
            System.out.println("Lista vacía.");
            return;
        }
        Nodo actual = cabeza;
        do {
            System.out.print(actual.getValor() + " -> ");
            actual = actual.getSiguiente();
        } while (actual != cabeza);
        System.out.println("(inicio)");
    }

    // OPERACIÓN: Insertar por posición
    public void insertarPosicion(int posicion, int valor) {
        System.out.println("Antes de insertar " + valor + " en pos " + posicion + ":");
        mostrar();

        if (posicion <= 0 || cabeza == null) {
            Nodo nuevo = new Nodo(valor);
            if (cabeza == null) {
                cabeza = nuevo;
                cola = nuevo;
            } else {
                nuevo.setSiguiente(cabeza);
                cabeza = nuevo;
                cola.setSiguiente(cabeza);
            }
        } else {
            Nodo actual = cabeza;
            for (int i = 0; i < posicion - 1 && actual.getSiguiente() != cabeza; i++) {
                actual = actual.getSiguiente();
            }
            Nodo nuevo = new Nodo(valor);
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
            if (actual == cola)
                cola = nuevo;
        }
        tamanio++;
        System.out.println("Después de insertar:");
        mostrar();
        System.out.println("-------------------------");
    }

    // OPERACIÓN: Eliminar por posición
    public void eliminarPosicion(int posicion) {
        System.out.println("Antes de eliminar en pos " + posicion + ":");
        mostrar();

        if (cabeza == null)
            return;

        if (posicion == 0 || tamanio == 1) {
            if (cabeza == cola) {
                cabeza = null;
                cola = null;
            } else {
                cabeza = cabeza.getSiguiente();
                cola.setSiguiente(cabeza);
            }
        } else {
            Nodo actual = cabeza;
            for (int i = 0; i < posicion - 1 && actual.getSiguiente() != cola; i++) {
                actual = actual.getSiguiente();
            }
            Nodo aEliminar = actual.getSiguiente();
            actual.setSiguiente(aEliminar.getSiguiente());
            if (aEliminar == cola)
                cola = actual;
        }
        tamanio--;
        System.out.println("Después de eliminar:");
        mostrar();
        System.out.println("-------------------------");
    }

    // OPERACIÓN: Eliminar por valor
    public void eliminarValor(int valor) {
        System.out.println("Antes de eliminar valor " + valor + ":");
        mostrar();

        if (cabeza == null)
            return;

        if (cabeza.getValor() == valor) {
            eliminarPosicion(0);
            return;
        }

        Nodo actual = cabeza;
        do {
            if (actual.getSiguiente().getValor() == valor) {
                Nodo aEliminar = actual.getSiguiente();
                actual.setSiguiente(aEliminar.getSiguiente());
                if (aEliminar == cola)
                    cola = actual;
                tamanio--;
                break;
            }
            actual = actual.getSiguiente();
        } while (actual != cabeza);

        System.out.println("Después de eliminar:");
        mostrar();
        System.out.println("-------------------------");
    }

    public static void main(String[] args) {
        ListaCircularControlada lista = new ListaCircularControlada();
        lista.insertarPosicion(0, 10);
        lista.insertarPosicion(1, 20);
        lista.insertarPosicion(2, 30);
        lista.insertarPosicion(1, 15);
        lista.eliminarPosicion(2);
        lista.eliminarValor(30);
    }
}
