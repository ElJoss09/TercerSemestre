package Tareas.Tarea3;

/**
 * TDA Lista Circular - Sistema de Playlist
 * 
 * @author Justin Alexander Guanoquiza Aguaguiña
 * @institution Universidad Técnica de Ambato
 */

class Cancion {
    String nombre;

    public Cancion(String nombre) {
        this.nombre = nombre;
    }
}

class NodoCancion {
    Cancion cancion;
    NodoCancion siguiente;

    public NodoCancion(Cancion c) {
        this.cancion = c;
        this.siguiente = this;
    }
}

public class PlaylistCircular {
    private NodoCancion cabeza;
    private NodoCancion cola;
    private NodoCancion reproduccionActual;

    public PlaylistCircular() {
        cabeza = null;
        cola = null;
        reproduccionActual = null;
    }

    public void agregarInicio(String nombre) {
        NodoCancion nuevo = new NodoCancion(new Cancion(nombre));
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            reproduccionActual = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            cola.siguiente = cabeza;
        }
    }

    public void agregarFinal(String nombre) {
        NodoCancion nuevo = new NodoCancion(new Cancion(nombre));
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            reproduccionActual = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        }
    }

    public void mostrarPlaylist() {
        if (cabeza == null) {
            System.out.println("Playlist vacía.");
            return;
        }
        NodoCancion actual = cabeza;
        System.out.print("Playlist: ");
        do {
            System.out.print("[" + actual.cancion.nombre + "] ");
            actual = actual.siguiente;
        } while (actual != cabeza);
        System.out.println();
    }

    public void reproducirSiguiente() {
        if (reproduccionActual == null)
            return;
        System.out.println("Reproduciendo: " + reproduccionActual.cancion.nombre);
        reproduccionActual = reproduccionActual.siguiente;
    }

    public void eliminarCancion(String nombre) {
        if (cabeza == null)
            return;

        NodoCancion actual = cabeza;
        NodoCancion previo = cola;

        do {
            if (actual.cancion.nombre.equals(nombre)) {
                System.out.println("Eliminando canción: " + nombre);
                if (actual == cabeza && actual == cola) { // Unico elemento
                    cabeza = null;
                    cola = null;
                    reproduccionActual = null;
                } else {
                    if (actual == cabeza)
                        cabeza = actual.siguiente;
                    if (actual == cola)
                        cola = previo;
                    previo.siguiente = actual.siguiente;

                    if (reproduccionActual == actual) {
                        reproduccionActual = actual.siguiente;
                    }
                }
                return;
            }
            previo = actual;
            actual = actual.siguiente;
        } while (actual != cabeza);
    }

    public static void main(String[] args) {
        PlaylistCircular playlist = new PlaylistCircular();
        playlist.agregarFinal("Canción 1");
        playlist.agregarFinal("Canción 2");
        playlist.agregarInicio("Intro");

        playlist.mostrarPlaylist();

        System.out.println("\nSimulando reproducción:");
        playlist.reproducirSiguiente(); // Intro
        playlist.reproducirSiguiente(); // Cancion 1
        playlist.reproducirSiguiente(); // Cancion 2
        playlist.reproducirSiguiente(); // Vuelve a Intro

        System.out.println();
        playlist.eliminarCancion("Canción 1");
        playlist.mostrarPlaylist();
    }
}
