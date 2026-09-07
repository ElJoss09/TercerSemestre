package Tareas.Tarea3;

/**
 * TDA Lista Circular - Operaciones Básicas
 * 
 * @author Justin Alexander Guanoquiza Aguaguiña
 * @institution Universidad Técnica de Ambato
 */

// CONCEPTO: Clase que representa el nodo de la estructura
class Nodo {
    // CONCEPTO: Encapsulamiento (atributos privados)
    private int valor;
    private Nodo siguiente;

    public Nodo(int valor) {
        this.valor = valor;
        this.siguiente = this;
    }

    public int getValor() {
        return valor;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

// CONCEPTO: TDA que administra la estructura circular
public class ListaCircularBasica {
    private Nodo cabeza;
    private Nodo cola;
    private int tamanio;

    public ListaCircularBasica() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    // OPERACIÓN: Verificar si está vacía
    public boolean estaVacia() {
        return cabeza == null;
    }

    // OPERACIÓN: Contar elementos
    public int contarElementos() {
        return tamanio;
    }

    // OPERACIÓN: Insertar al inicio
    public void insertarInicio(int valor) {
        // CONCEPTO: Instanciación de objetos
        Nodo nuevo = new Nodo(valor);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
            cola.setSiguiente(cabeza);
        }
        tamanio++;
    }

    // OPERACIÓN: Insertar al final
    public void insertarFinal(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            cola = nuevo;
            cola.setSiguiente(cabeza);
        }
        tamanio++;
    }

    // OPERACIÓN: Mostrar todos los elementos
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("Lista vacía.");
            return;
        }
        Nodo actual = cabeza;
        System.out.print("Lista: ");
        do {
            System.out.print(actual.getValor() + " -> ");
            actual = actual.getSiguiente();
        } while (actual != cabeza);
        System.out.println("(vuelve al inicio)");
    }

    public static void main(String[] args) {
        ListaCircularBasica lista = new ListaCircularBasica();
        System.out.println("¿Está vacía? " + lista.estaVacia());
        lista.insertarInicio(10);
        lista.insertarFinal(20);
        lista.insertarInicio(5);
        lista.mostrar();
        System.out.println("Total elementos: " + lista.contarElementos());
    }
}
