import java.util.ArrayDeque;
import java.util.Deque;

public class GestorImpresiones { 

    private Deque<String> pendientes = new ArrayDeque<>(); // Comportamiento de Cola
    private Deque<String> historial = new ArrayDeque<>(); // Comportamiento de Pila

    // Registrar documento: entra al final de la cola
    public void registrarDocumento(String nombre) {
        pendientes.offerLast(nombre);
        System.out.println(" Registrado: " + nombre);
    }

    // Imprimir siguiente: sale el más antiguo de la cola y entra a la pila de
    // historial
    public void imprimirSiguiente() {
        if (pendientes.isEmpty()) {
            System.out.println(" No hay documentos pendientes para imprimir.");
            return;
        }
        String doc = pendientes.pollFirst();
        historial.push(doc);
        System.out.println(" Impreso y guardado en historial: " + doc);
    }

    // Recuperar última: saca del historial (cima) y vuelve al frente de la cola
    public void recuperarUltima() {
        if (historial.isEmpty()) {
            System.out.println(" El historial está vacío, nada que recuperar.");
            return;
        }
        String doc = historial.pop();
        pendientes.addFirst(doc);
        System.out.println(" Recuperado al frente de pendientes: " + doc);
    }

    public void mostrarEstado() {
        System.out.println("   -> Pendientes (frente a cola): " + pendientes);
        System.out.println("   -> Historial (cima a base): " + historial);
    }

    public static void main(String[] args) {
        GestorImpresiones gestor = new GestorImpresiones();

        // Secuencia de 7 operaciones combinadas
        System.out.println("--- 1. Registrar 'Reporte.pdf' ---");
        gestor.registrarDocumento("Reporte.pdf");

        System.out.println("\n--- 2. Registrar 'Tesis.docx' ---");
        gestor.registrarDocumento("Tesis.docx");

        System.out.println("\n--- 3. Imprimir siguiente ---");
        gestor.imprimirSiguiente();
        gestor.mostrarEstado();

        System.out.println("\n--- 4. Registrar 'Factura.pdf' ---");
        gestor.registrarDocumento("Factura.pdf");
        gestor.mostrarEstado();

        System.out.println("\n--- 5. Imprimir siguiente ---");
        gestor.imprimirSiguiente();
        gestor.mostrarEstado();

        System.out.println("\n--- 6. Recuperar última impresión ---");
        gestor.recuperarUltima();
        gestor.mostrarEstado();

        System.out.println("\n--- 7. Imprimir siguiente (reimprime la recuperada) ---");
        gestor.imprimirSiguiente();
        gestor.mostrarEstado();
    }
}
