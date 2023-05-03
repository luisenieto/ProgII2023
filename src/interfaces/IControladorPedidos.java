/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

/**
 *
 * @author root
 */
public interface IControladorPedidos {
    public static final String TITULO = "Pedidos";
    public static final String CONFIRMACION = "¿Desea cancelar el pedido especificado?";
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Nuevo
     * @param evt evento
    */                        
    public void btnNuevoClic(ActionEvent evt);
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Modificar
     * @param evt evento
    */                        
    public void btnModificarClic(ActionEvent evt);
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Cancelar
     * @param evt evento
    */                        
    public void btnCancelarClic(ActionEvent evt);
    
    /**
     * Acción a ejecutar cuando se selecciona el ítem del menú de popup
     * @param evt evento
    */                        
    public void itemMenuClic(ActionEvent evt);
    
    /**
     * Acción a ejecutar cuando la ventana obtenga el foco
     * @param evt evento
    */
    public void ventanaObtenerFoco(WindowEvent evt);
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Volver
     * @param evt evento
    */                        
    public void btnVolverClic(ActionEvent evt);
    
    /**
     * Acción a ejecutar cuando se presiona una tecla en el campo txtDescripcion
     * @param evt evento
    */
//    public void txtDescripcionPresionarTecla(KeyEvent evt);
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Buscar
     * @param evt evento
    */                        
//    public void btnBuscarClic(ActionEvent evt);    
}
