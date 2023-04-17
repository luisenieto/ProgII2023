/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author root
 */
public interface IControladorAMProductoDelPedido {

    /**
     * Acción a ejecutar cuando se selecciona una categoría en el combo
     * @param evt evento
    */                        
    public void seleccionarCategoria(ItemEvent evt);
    
    /**
     * Acción a ejecutar cuando se presiona una tecla en el campo txtCantidad
     * @param evt evento
     */
    public void txtCantidadPresionarTecla(KeyEvent evt);
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Aceptar
     * @param evt evento
    */                        
    public void btnAceptarClic(ActionEvent evt);

    /**
     * Acción a ejecutar cuando se selecciona el botón Cancelar
     * @param evt evento
    */                        
    public void btnCancelarClic(ActionEvent evt);
}
