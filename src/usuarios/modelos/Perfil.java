/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.modelos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author root
 */
public enum Perfil {
    CLIENTE("Cliente"),
    EMPLEADO("Empleado"),
    ENCARGADO("Encargado");
    
    private String valor;
    
    private Perfil(String valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return this.valor;
    }    
    
    /**
     * Transforma una cadena en el valor de su enumeración correspondiente
     * @param perfil cadena que representa un perfil
     * @return Perfil  - enumeración Perfil
     */
    public static Perfil verPerfil(String perfil) {
        Map<String, Perfil> mapeo = new HashMap<>();
        for (Perfil p : Perfil.values()) {
            mapeo.put(p.toString(), p);
        }
        return mapeo.get(perfil);
    }
}
