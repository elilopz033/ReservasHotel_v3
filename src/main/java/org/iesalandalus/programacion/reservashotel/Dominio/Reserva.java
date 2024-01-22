package org.iesalandalus.programacion.reservashotel.Dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Objects;

public class Reserva {
    private static final int MAX_NUMERO_MESES_RESERVA = 6;
    private static final int MAX_HORAS_POSTERIOR_CHECKOUT = 12;
    public static final String FORMATO_FECHA_RESERVA = "dd/MM/yyyy";
    public static final String FORMATO_FECHA_HORA_RESERVA = "dd/MM/yyyy HH:mm";

    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    private int numeroPersonas;
    private Huesped huesped;
    private Habitacion habitacion;
    private Regimen regimen;
    private LocalDate checkin;
    private LocalDate checkout;
    private double precio;

    // Constructor con parámetros
    public Reserva(LocalDate fechaInicioReserva, LocalDate fechaFinReserva, int numeroPersonas, Huesped huesped,
                   Habitacion habitacion, Regimen regimen, LocalDate checkin, LocalDate checkout) {
        setFechaInicioReserva(fechaInicioReserva);
        setFechaFinReserva(fechaFinReserva);
        setNumeroPersonas(numeroPersonas);
        setHuesped(huesped);
        setHabitacion(habitacion);
        setRegimen(regimen);
        setCheckin(checkin);
        setCheckout(checkout);
        calcularPrecio();
    }
    // Constructor copia
    public Reserva(Reserva otraReserva) {
        this(otraReserva.fechaInicioReserva, otraReserva.fechaFinReserva, otraReserva.numeroPersonas,
                new Huesped(otraReserva.huesped), new Habitacion(otraReserva.habitacion),
                otraReserva.regimen, otraReserva.checkin, otraReserva.checkout);
        this.precio = otraReserva.precio;
    }
    // Métodos de modificación
    public void setFechaInicioReserva(LocalDate fechaInicioReserva) {
        if (fechaInicioReserva.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser anterior al día actual");
        }
        this.fechaInicioReserva = fechaInicioReserva;
    }
    public void setFechaFinReserva(LocalDate fechaFinReserva) {
        if (fechaFinReserva.isBefore(fechaInicioReserva)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        if (fechaFinReserva.isAfter(fechaInicioReserva.plusMonths(MAX_NUMERO_MESES_RESERVA))) {
            throw new IllegalArgumentException("La reserva no puede superar los " + MAX_NUMERO_MESES_RESERVA + " meses");
        }
        this.fechaFinReserva = fechaFinReserva;
    }
    public void setNumeroPersonas(int numeroPersonas) {
        if (numeroPersonas > habitacion.getTipoHabitacion().capacidadMaxima()) {
            throw new IllegalArgumentException("Número de personas no válido para el tipo de habitación");
        }
        this.numeroPersonas = numeroPersonas;
    }
    public void setHuesped(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("Huesped no válido");
        }
        this.huesped = huesped;
    }

    public void setHabitacion(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("Habitación no válida");
        }
        this.habitacion = habitacion;
    }

    public void setRegimen(Regimen regimen) {
        if (regimen == null) {
            throw new IllegalArgumentException("Régimen no válido");
        }
        this.regimen = regimen;
    }
    public void setCheckin(LocalDate checkin) {
        if (checkin.isBefore(fechaInicioReserva)) {
            throw new IllegalArgumentException("Checkin no puede ser anterior a la fecha de inicio");
        }
        this.checkin = checkin;
    }

    public void setCheckout(LocalDate checkout) {
        if (checkout.isBefore(checkin)) {
            throw new IllegalArgumentException("Checkout no puede ser anterior al checkin");
        }
        if (checkout.isAfter(checkin.plusHours(MAX_HORAS_POSTERIOR_CHECKOUT))) {
            throw new IllegalArgumentException("Checkout debe ser como máximo " + MAX_HORAS_POSTERIOR_CHECKOUT + " horas después del checkin");
        }
        this.checkout = checkout;
    }

    private void calcularPrecio() {
        double precioBase = habitacion.getPrecio();
        double incrementoPorPersona = regimen.getIncrementoPorPersona() * numeroPersonas;
        this.precio = precioBase + incrementoPorPersona;
    }

    // Otros métodos solicitados
    public LocalDate getFechaInicio() {
        return fechaInicioReserva;
    }

    public LocalDate getFechaFin() {
        return fechaFinReserva;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Reserva reserva = (Reserva) obj;
        return habitacion.equals(reserva.habitacion) && fechaInicioReserva.equals(reserva.fechaInicioReserva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(habitacion, fechaInicioReserva);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "fechaInicio=" + fechaInicioReserva +
                ", fechaFin=" + fechaFinReserva +
                ", numeroPersonas=" + numeroPersonas +
                ", huesped=" + huesped +
                ", habitacion=" + habitacion +
                ", regimen=" + regimen +
                ", checkin=" + checkin +
                ", checkout=" + checkout +
                ", precio=" + precio +
                '}';
    }

}
