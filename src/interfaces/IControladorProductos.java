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
public interface IControladorProductos {
    public static final String TITULO = "Productos";
    public static final String CONFIRMACION = "¿Desea borrar el producto especificado?";
    
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
     * Acción a ejecutar cuando se selecciona el botón Borrar
     * @param evt evento
     */                        
    public void btnBorrarClic(ActionEvent evt);
    
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
     * Permite filtrar los productos a medida que se escribe en el campo de texto txtDescripcion
     *      Si se implementa este método:
     *      El método txtDescripcionPresionarEnter() no tiene sentido implementar
     *      El botón Buscar puede quedar oculto
     * @param evt evento
    */
    public void txtDescripcionPresionarTecla(KeyEvent evt);
    
    /**
     * Acción a ejecutar cuando se presiona Enter en el campo txtDescripcion
     * Permite filtrar los productos según lo escrito en el campo de texto txtDescripcion cuando se presiona Enter
     *      Si se implementa este método:
     *      El método txtDescripcionPresionarTecla() no tiene sentido implementar
     * @param evt evento
    */
    public void txtDescripcionPresionarEnter(ActionEvent evt);
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Buscar
     * Permite filtrar los productos según lo escrito en el campo de texto txtDescripcion
     * Si el botón Buscar está visible, y por lo tanto se implementa este método:
     *      El método txtDescripcionPresionarTecla() no tiene sentido implementar
     *      El método txtDescripcionPresionarEnter() hace la misma acción que éste
     * @param evt evento
    */                        
    public void btnBuscarClic(ActionEvent evt);
}
