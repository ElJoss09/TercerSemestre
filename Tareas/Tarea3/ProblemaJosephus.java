package Tareas.Tarea3;

/**
 * TDA Lista Circular - Problema de Josephus
 * 
 * @author Justin Alexander Guanoquiza Aguaguiña
 * @institution Universidad Técnica de Ambato
 */

class NodoPersona {
    int id;
    NodoPersona siguiente;

    public NodoPersona(int id) {
        this.id = id;
        this.siguiente = this;
    }
}

public class ProblemaJosephus {

    public static void ejecutarJosephus(int n, int k) {
        System.out.println("=== Ejecutando Josephus (n=" + n + ", k=" + k + ") ===");

        // Crear círculo de n personas
        NodoPersona cabeza = new NodoPersona(1);
        NodoPersona anterior = cabeza;
        for (int i = 2; i <= n; i++) {
            NodoPersona nuevo = new NodoPersona(i);
            anterior.siguiente = nuevo;
            anterior = nuevo;
        }
        anterior.siguiente = cabeza; // Cerrar círculo

        NodoPersona actual = cabeza;
        NodoPersona previo = anterior;

        // Proceso de eliminación
        while (actual.siguiente != actual) {
            // Avanzar k-1 pasos
            for (int i = 0; i < k - 1; i++) {
                previo = actual;
                actual = actual.siguiente;
            }
            // Eliminar el k-ésimo
            System.out.println("Persona eliminada: " + actual.id);
            previo.siguiente = actual.siguiente;
            actual = previo.siguiente; // Mover al siguiente para la próxima iteración
        }
        System.out.println("-> SUPERVIVIENTE FINAL: " + actual.id + "\n");
    }

    public static void main(String[] args) {
        ejecutarJosephus(5, 2);
        ejecutarJosephus(7, 3);
    }
}
