/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 *
 * @author root
 */
public interface IControladorAMPedido {
    public static final String TITULO_NUEVO = "Nuevo pedido"; 
    public static final String TITULO_MODIFICAR = "Modificar pedido"; 
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Agregar
     * @param evt evento
    */                        
    public void btnAgregarClic(ActionEvent evt);
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Modificar
     * @param evt evento
    */                        
    public void btnModificarClic(ActionEvent evt);
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Quitar
     * @param evt evento
    */                        
    public void btnQuitarClic(ActionEvent evt);
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Guardar
     * @param evt evento
     */                        
    public void btnGuardarClic(ActionEvent evt);

    /**
     * Acción a ejecutar cuando se selecciona el botón Cancelar
     * @param evt evento
     */                        
    public void btnCancelarClic(ActionEvent evt);
    
    /**
     * Acción a ejecutar cuando la ventana obtenga el foco
     * @param evt evento
    */
    public void ventanaObtenerFoco(WindowEvent evt);
}
