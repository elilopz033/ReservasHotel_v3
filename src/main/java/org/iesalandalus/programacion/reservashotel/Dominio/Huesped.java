package org.iesalandalus.programacion.reservashotel.Dominio;


import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Huesped {

//Atributos

    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;

//Expresiones para las validaciones

    private static final String ER_NOMBRE = "^[A-Za-z?-??-?]+(\\s[A-Za-z?-??-?]+)*$";
    private static final String ER_TELEFONO = "^[6789]\\d{8}$";
    private static final String ER_CORREO = "^([a-zA-Z0-9._%-]+)@([a-zA-Z0-9.-]+).([a-zA-Z]{2,6})$";
    private static final String ER_DNI = "(\\d{8})([A-Z])";
    public static final String FORMATO_FECHA = "dd/MM/yyyy";


//Método formateaNombre

    public String formateaNombre (String nombre) {
        String[] palabras = nombre.split("\\s+");
        StringBuilder nombreFormateado = new StringBuilder();

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                nombreFormateado.append(Character.toUpperCase(palabra.charAt(0)));
                nombreFormateado.append(palabra.substring(1).toLowerCase());
                nombreFormateado.append(" ");
            }
        }
        return nombreFormateado.toString().trim();
    }

    //Método comprobarLetraDni
    private boolean comprobarLetraDni(String dni) {
        String ER_DNI = "^(\\\\d{8})([A-HJ-NP-TV-Za-hj-np-tv-z])$";
        Pattern pattern = Pattern.compile(ER_DNI);
        Matcher matcher = pattern.matcher(dni);
        if (matcher.matches()) {
            String numeroDni = matcher.group(1);
            String letraDni = matcher.group(2);
            int resto = Integer.parseInt(numeroDni) %23;
            String letras =  "TRWAGMYFPDXBNJZSQVHLCKE";
            char letraCalculada = letras.charAt(resto);
            return letraDni.charAt(0) ==letraCalculada;
        }
        return false;
    }
    public String getIniciales() {
        String[] palabras = nombre.split("\\s+");
        StringBuilder iniciales = new StringBuilder();

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                iniciales.append(palabra.charAt(0));
            }
        }

        return iniciales.toString().toUpperCase();
    }

    //Métodos de mofificación
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (!nombre.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("Nombre no válido");
        }
        this.nombre = formateaNombre(nombre);
    }


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        // Validar el formato del DNI
        if (!dni.matches(ER_DNI) || (!comprobarLetraDni(dni))) {
            throw new IllegalArgumentException("Formato de DNI no válido");
        }
        this.dni = dni.toUpperCase();
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        // Validar el formato del teléfono
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("Formato de teléfono no válido");
        }
        this.telefono = telefono;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        // Validar el formato del correo
        if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("Formato de correo no válido");
        }
        this.correo = correo;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Huesped(String nombre, String telefono, String correo, String dni, LocalDate fechaNacimiento) {
        setNombre(formateaNombre(nombre));
        setTelefono(telefono);
        setCorreo(correo);
        setDni(dni);
        setFechaNacimiento(fechaNacimiento);
    }
    public Huesped(Huesped huesped) {
        setNombre(formateaNombre(huesped.nombre));
        setTelefono(huesped.telefono);
        setCorreo(huesped.correo);
        setDni(huesped.dni);
        setFechaNacimiento(huesped.fechaNacimiento);
    }
    public String toString() {
        return "Huesped{" + "nombre='" + nombre +
                '\'' + ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' + ", dni='" +
                dni + '\'' + ", fechaNacimiento=" + fechaNacimiento + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Huesped huesped = (Huesped) obj;
        return dni.equals(huesped.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }




}
