package org.iesalandalus.programacion.reservashotel.Dominio;
public enum TipoHabitacion {
    INDIVIDUAL("Individual", 1),
    DOBLE("Doble", 2),
    MATRIMONIAL("Matrimonial", 2),
    SUITE("Suite", 4);

    private final String tipo;
    private final int capacidadMaxima;
    private String cadenaAMostrar;

    // Constructor
    TipoHabitacion(String tipo, int capacidadMaxima) {
        this.tipo = tipo;
        this.capacidadMaxima = capacidadMaxima;
        this.cadenaAMostrar = tipo + " - Máximo " + capacidadMaxima + " personas";
    }

    // Getter
    public String getCadenaAMostrar() {
        return cadenaAMostrar;
    }

    // Método toString

    public String toString() {
        return cadenaAMostrar;
    }
}
