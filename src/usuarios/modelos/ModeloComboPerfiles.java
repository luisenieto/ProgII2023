/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.modelos;

import interfaces.IGestorPermisos;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import permisos.modelos.GestorPermisos;

/**
 *
 * @author root
 */
public class ModeloComboPerfiles extends DefaultComboBoxModel {
    
    /**
     * Constructor
     * @param usuario usuario que determina los tipos de usuario (perfiles) que puede crear
    */
    public ModeloComboPerfiles(Usuario usuario) { 
        IGestorPermisos gp = GestorPermisos.instanciar();
        List<Perfil> perfiles = gp.crearUsuarios(usuario);
        for (Perfil perfil : perfiles) {
            this.addElement(perfil); 
        }
    }
    
    /**
     * Devuelve el perfil seleccionado
     * @return Perfil  - perfil seleccionado
    */
    public Perfil obtenerPerfil() { 
        return (Perfil)this.getSelectedItem();
    }
    
    /**
     * Selecciona el Perfil especificado
     * @param perfil perfil
    */
    public void seleccionarPerfil(Perfil perfil) {
        this.setSelectedItem(perfil);
    }
}
