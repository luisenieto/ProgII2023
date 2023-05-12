/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
    
import java.awt.event.ActionEvent;

/**
 *
 * @author root
 */
public interface IControladorPrincipal {
    public static final String TITULO = "Bar";
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Usuarios
     * @param evt evento
     */                        
    public void btnUsuariosClic(ActionEvent evt); 
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Productos
     * @param evt evento
     */                        
    public void btnProductosClic(ActionEvent evt); 
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Pedidos
     * @param evt evento
     */                        
    public void btnPedidosClic(ActionEvent evt); 
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Cancelar
     * @param evt evento
     */                        
    public void btnSalirClic(ActionEvent evt); 
}
