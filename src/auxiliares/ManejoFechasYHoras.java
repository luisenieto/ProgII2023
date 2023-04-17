/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliares;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.PatternSyntaxException;

/**
 *
 * @author root
 */
public class ManejoFechasYHoras {
    public static final String SEPARADOR_FECHA = "/";
    public static final String SEPARADOR_HORA = ":";
    public static final String FECHA_NULA = "-";
    public static final String HORA_NULA = "-";
    public static final String PATRON_FECHA = "dd/MM/yyyy";
    public static final String PATRON_HORA = "HH:mm";
    
    /**
     * Transforma una fecha expresada en cadena en un objeto LocalDate
     * Si la cadena que representa la fecha no se puede descomponer en dd mm y aaaa devuelve null
     * Si se intenta armar una fecha inválida, devuelve null
     * @param cadenaFecha cadena con la fecha a transformar
     * @return LocalDate  - objeto LocalDate transformado
    */
    public static LocalDate transformarCadenaALocalDate(String cadenaFecha) {
        try {
            String[] vector = cadenaFecha.split(SEPARADOR_FECHA);
            int dia = Integer.parseInt(vector[0]);
            int mes = Integer.parseInt(vector[1]);
            int anio = Integer.parseInt(vector[2]);
            try {
                return LocalDate.of(anio, mes, dia);
            }
            catch(DateTimeException e) {
                return null;
            }
        }
        catch(PatternSyntaxException e) { //la cadena que representa la fecha no se puede descomponer en dd mm y aaaa
            return null;
        }
    }
    
    /**
     * Dada una fecha, devuelve una cadena de la forma indicada en el patrón
     * Si la fecha es nula, devuelve el caracter usado para fechas nulas
     * @param fecha fecha a transformar
     * @return String  - cadena con la representación de la fecha
    */
    public static String transformarLocalDateEnCadena(LocalDate fecha) {        
        if (fecha != null) {
            return fecha.format(DateTimeFormatter.ofPattern(PATRON_FECHA));
        }
        else
            return FECHA_NULA;            
    }
    
    /**
     * Transforma una hora expresada en cadena en un objeto LocalTime
     * Si la cadena que representa la hora no se puede descomponer en hh y mm devuelve null
     * Si se intenta armar una hora inválida, devuelve null
     * @param cadenaHora cadena con la hora a transformar
     * @return LocalTime  - objeto LocalTime transformado
    */
    public static LocalTime transformarCadenaALocalTime(String cadenaHora) {
        try {
            String[] vector = cadenaHora.split(SEPARADOR_HORA);
            int horas = Integer.parseInt(vector[0]);
            int minutos = Integer.parseInt(vector[1]);
            try {
                return LocalTime.of(horas, minutos);
            }
            catch(DateTimeException e) {
                return null;
            }
        }
        catch(PatternSyntaxException e) { //la cadena que representa la fecha no se puede descomponer en dd mm y aaaa
            return null;
        }
    }
    
    /**
     * Dada una hora, devuelve una cadena de la forma indicada en el patrón
     * Si la hora es nula, devuelve el caracter usado para horas nulas
     * @param hora hora a transformar
     * @return String  - cadena con la representación de la hora
    */
    public static String transformarLocalTimeEnCadena(LocalTime hora) {        
        if (hora != null) {
            return hora.format(DateTimeFormatter.ofPattern(PATRON_HORA));
        }
        else
            return HORA_NULA;            
    }
}
