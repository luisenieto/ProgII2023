/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.modelos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author root
 */
public enum Estado {
    CREADO("Creado"), 
    PROCESANDO("Procesando"),
    ENTREGADO("Entregado");
    
    private String valor;
    
    private Estado(String valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return this.valor;
    }
    
    /**
     * Transforma una cadena en el valor de su enumeración correspondiente
     * @param estado cadena que representa un estado
     * @return Estado  - enumeración Estado
    */
    public static Estado verEstado(String estado) {
        Map<String, Estado> mapeo = new HashMap<>();
        for (Estado e : Estado.values()) {
            mapeo.put(e.toString(), e);
        }
        return mapeo.get(estado);
    }
}
