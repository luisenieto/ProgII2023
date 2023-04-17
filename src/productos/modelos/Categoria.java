/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos.modelos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author root
 */
public enum Categoria {
    ENTRADA("Entrada"),
    PLATO_PRINCIPAL("Plato principal"),
    POSTRE("Postre");
    
    private String valor;
    
    private Categoria(String valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return this.valor;
    }
    
    /**
     * Transforma una cadena en el valor de su enumeración correspondiente
     * @param categoria cadena que representa una categoría
     * @return Categoria  - enumeración Categoria
     */
    public static Categoria verCategoria(String categoria) {
        Map<String, Categoria> mapeo = new HashMap<>();
        for (Categoria c : Categoria.values()) {
            mapeo.put(c.toString(), c);
        }
        return mapeo.get(categoria);
    }
}
