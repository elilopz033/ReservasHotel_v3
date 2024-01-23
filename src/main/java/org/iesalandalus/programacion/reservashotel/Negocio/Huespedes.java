package org.iesalandalus.programacion.reservashotel.Negocio;

import org.iesalandalus.programacion.reservashotel.Dominio.Huesped;
import javax.naming.OperationNotSupportedException;
import java.util.NoSuchElementException;

public class Huespedes {
    private int capacidad;
    private int tamano;
    private Huesped[] coleccionHuespedes;


    public Huespedes(int capacidad) {
        this.capacidad = capacidad;
        this.coleccionHuespedes = new Huesped[capacidad];
    }

    public Huesped[] get() {
        return copiaProfundaHuespedes();
    }

    private Huesped[] copiaProfundaHuespedes() {
        Huesped[] copiaProfunda = new Huesped[tamano];
        for (int i = 0; i < tamano; i++) {
            copiaProfunda[i] = new Huesped(coleccionHuespedes[i]);
        }
        return copiaProfunda;
    }


    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede insertar un hu?sped nulo.");
        }
        if (buscar(huesped) != null) {
            throw new OperationNotSupportedException("El hu?sped ya est? registrado y no se admiten repetidos.");
        }
        if (tamanoSuperado()) {
            throw new OperationNotSupportedException("No se pueden insertar m?s hu?spedes, se ha alcanzado la capacidad m?xima.");
        }
        if (capacidadSuperada()) {
            throw new IllegalStateException("Se ha superado la capacidad m?xima, esto no deber?a ocurrir.");
        }
        coleccionHuespedes[tamano++] = huesped;
    }

    public Huesped buscar(Huesped huesped) throws NoSuchElementException {
        for (int i = 0; i < tamano; i++) {
            if (coleccionHuespedes[i].equals(huesped)) {
                return coleccionHuespedes[i];
            }
        }
        return null;
    }

    public void borrar(Huesped huesped) throws NoSuchElementException {
        int indice = buscarIndice(huesped);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        } else {
            throw new NoSuchElementException("El hu?sped a borrar no se encuentra en la colecci?n.");
        }
    }

    private int buscarIndice(Huesped huesped) throws NoSuchElementException {
        for (int i = 0; i < tamano; i++) {
            if (coleccionHuespedes[i].equals(huesped)) {
                return i;
            }
        }
        return -1;
    }


    private boolean tamanoSuperado() {
        return tamano >= capacidad;
    }

    private boolean capacidadSuperada() {
        return tamano > capacidad;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionHuespedes[i] = coleccionHuespedes[i + 1];
        }
    }
}

