package org.iesalandalus.programacion.reservashotel.Dominio;

import java.time.LocalDate;
import java.util.Objects;

public enum Regimen {
    SOLO_ALOJAMIENTO("Solo Alojamiento", 0),
    ALOJAMIENTO_Y_DESAYUNO("Alojamiento y Desayuno", 15),
    MEDIA_PENSION("Media Pensión", 30),
    PENSION_COMPLETA("Pensión Completa", 50);

    private final String tipo;
    private final double incrementoPorPersona;

    private Regimen(String tipo, double incrementoPorPersona) {
        this.tipo = tipo;
        this.incrementoPorPersona = incrementoPorPersona;
    }

    public String getTipo() {
        return tipo;
    }

    public double getIncrementoPorPersona() {
        return incrementoPorPersona;
    }

    @Override
    public String toString() {
        return tipo;
    }
}

