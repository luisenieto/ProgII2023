/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author root
 */
public interface IControladorLogin {
/**
     * Acción a ejecutar cuando se selecciona el botón Acceder
     * @param evt evento
     */                        
    public void btnAccederClic(ActionEvent evt);

    /**
     * Acción a ejecutar cuando se selecciona el botón Cancelar
     * @param evt evento
     */                        
    public void btnCancelarClic(ActionEvent evt);  
    
    /**
     * Acción a ejecutar cuando se presiona una tecla en el campo txtCorreo
     * @param evt evento
    */
    public void txtCorreoPresionarTecla(KeyEvent evt);
    
    /**
     * Acción a ejecutar cuando se presiona una tecla en el campo passClave
     * @param evt evento
    */
    public void passClavePresionarTecla(KeyEvent evt);
}
