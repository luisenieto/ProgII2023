/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos.modelos;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author root
 */
public class ModeloComboEstados extends DefaultComboBoxModel {
    
    /**
     * Constructor
    */
    public ModeloComboEstados() { 
        for (Estado estado : Estado.values()) {
            this.addElement(estado); 
        }
    }
    
    /**
     * Devuelve el estado seleccionado
     * @return Estado  - estado seleccionado
    */
    public Estado obtenerEstado() { 
        return (Estado)this.getSelectedItem();
    }
    
    /**
     * Selecciona el Estado especificado
     * @param estado estado
    */
    public void seleccionarEstado(Estado estado) {
        this.setSelectedItem(estado);
    }
}
