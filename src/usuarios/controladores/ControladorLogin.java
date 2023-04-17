/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.controladores;

import auxiliares.Apariencia;
import interfaces.IControladorLogin;
import interfaces.IControladorPrincipal;
import interfaces.IGestorUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import principal.controladores.ControladorPrincipal;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;
import usuarios.vistas.VentanaLogin;

/**
 *
 * @author root
 */
public class ControladorLogin implements IControladorLogin {
    private VentanaLogin ventana;

    public ControladorLogin() {
        this.ventana = new VentanaLogin(this);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }

    @Override
    public void btnAccederClic(ActionEvent evt) {
        String correo = this.ventana.verCorreo().getText();
        String clave = new String(this.ventana.verClave().getPassword());
        IGestorUsuarios gu = GestorUsuarios.instanciar();
        Usuario u = gu.validarUsuario(correo, clave);
        if (u != null) { //existe un usuario con ese correo y clave
            this.ventana.dispose();
            IControladorPrincipal controlador = new ControladorPrincipal();
        }
        else {
            JOptionPane.showMessageDialog(null, IGestorUsuarios.VALIDACION_ERROR, IControladorPrincipal.TITULO, JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void btnCancelarClic(ActionEvent evt) {
        System.exit(0);
    }

    @Override
    public void txtCorreoPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER)
            this.btnAccederClic(null);
    }

    @Override
    public void passClavePresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER)
            this.btnAccederClic(null);
    }
    
    
    
    
    
    
    public static void main(String[] args) {
        Apariencia.establecerLookAndFeel("Nimbus"); //asigna el look and feel "Nimbus" a la ventana
        IControladorLogin cl = new ControladorLogin();        
    }
 
}
