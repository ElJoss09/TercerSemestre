package Tareas.Tarea3;

/**
 * TDA Lista Circular - Simulación Round-Robin
 * 
 * @author Justin Alexander Guanoquiza Aguaguiña
 * @institution Universidad Técnica de Ambato
 */

class Proceso {
    String nombre;
    int tiempoRestante;

    public Proceso(String nombre, int tiempoRestante) {
        this.nombre = nombre;
        this.tiempoRestante = tiempoRestante;
    }
}

class NodoProceso {
    Proceso proceso;
    NodoProceso siguiente;

    public NodoProceso(Proceso p) {
        this.proceso = p;
        this.siguiente = this;
    }
}

public class SimuladorRoundRobin {
    private NodoProceso cabeza;
    private NodoProceso cola;

    public void agregarProceso(String nombre, int tiempo) {
        NodoProceso nuevo = new NodoProceso(new Proceso(nombre, tiempo));
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        }
    }

    public void ejecutarSimulacion(int quantum) {
        System.out.println("=== INICIO ROUND-ROBIN (Quantum: " + quantum + ") ===");

        while (cabeza != null) {
            Proceso actual = cabeza.proceso;
            System.out.println("Ejecutando: " + actual.nombre + " (Tiempo inicial: " + actual.tiempoRestante + ")");

            actual.tiempoRestante -= quantum;

            if (actual.tiempoRestante <= 0) {
                System.out.println("-> Proceso " + actual.nombre + " TERMINADO.");
                eliminarCabeza();
            } else {
                System.out.println("-> Proceso " + actual.nombre + " pausado. Restante: " + actual.tiempoRestante);
                // Mover cabeza al siguiente para el próximo turno
                cabeza = cabeza.siguiente;
                cola = cola.siguiente;
            }
            mostrarEstado();
            System.out.println("-");
        }
        System.out.println("=== TODOS LOS PROCESOS COMPLETADOS ===");
    }

    private void eliminarCabeza() {
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.siguiente;
            cola.siguiente = cabeza;
        }
    }

    private void mostrarEstado() {
        if (cabeza == null) {
            System.out.println("Estado actual de lista: [Vacía]");
            return;
        }
        NodoProceso temp = cabeza;
        System.out.print("Estado actual de lista: ");
        do {
            System.out.print("[" + temp.proceso.nombre + ":" + temp.proceso.tiempoRestante + "] -> ");
            temp = temp.siguiente;
        } while (temp != cabeza);
        System.out.println();
    }

    public static void main(String[] args) {
        SimuladorRoundRobin rr = new SimuladorRoundRobin();
        rr.agregarProceso("P1", 5);
        rr.agregarProceso("P2", 3);
        rr.agregarProceso("P3", 6);
        rr.ejecutarSimulacion(2);
    }
}
