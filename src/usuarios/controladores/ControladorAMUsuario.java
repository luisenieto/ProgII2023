/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.controladores;

import interfaces.IControladorAMUsuario;
import interfaces.IGestorUsuarios;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.ModeloComboPerfiles;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;
import usuarios.vistas.VentanaAMUsuario;

/**
 *
 * @author root
 */
public class ControladorAMUsuario implements IControladorAMUsuario {

    private VentanaAMUsuario ventana;
    private IGestorUsuarios gu = GestorUsuarios.instanciar();
    private Usuario usuarioLogueado = gu.verUsuarioLogueado(); 
    private Usuario amUsuario; //usuario que se está creando/modificando

    /**
     * Constructor
     *
     * @param ventanaPadre (VentanaUsuarios en este caso)
     * @param amUsuario usuario que se quiere crear/modificar
     */
    public ControladorAMUsuario(Dialog ventanaPadre, Usuario amUsuario) {
        this.amUsuario = amUsuario;
        this.ventana = new VentanaAMUsuario(this, ventanaPadre);
        if (this.amUsuario == null) { //creación de usuario
            this.ventana.setTitle(TITULO_NUEVO);
        } else { //modificación de usuario
            this.ventana.setTitle(TITULO_MODIFICAR);
        }
        this.configurarCombo();
        this.configurarCampos(this.amUsuario);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);

    }

    /**
     * Configura el combo de perfiles según el usuario actualmente logueado
     */
    private void configurarCombo() {
        ModeloComboPerfiles mcp = new ModeloComboPerfiles(this.usuarioLogueado);
        this.ventana.verPerfil().setModel(mcp);
        
    }
    
    /**
     * Configura el combo de perfiles según el usuario que se está modificando
     * @param usuario usuario que se está modificando
     */
    private void configurarCampos(Usuario amUsuario) {
        if (amUsuario != null) { //modificación de usuario
            this.ventana.verCorreo().setText(amUsuario.verCorreo());
            this.ventana.verApellido().setText(amUsuario.verApellido());
            this.ventana.verNombre().setText(amUsuario.verNombre());
            ((ModeloComboPerfiles)this.ventana.verPerfil().getModel()).seleccionarPerfil(amUsuario.verPerfil());
            if (this.usuarioLogueado.verPerfil().equals(Perfil.ENCARGADO) && this.usuarioLogueado.equals(amUsuario))
                this.ventana.verPerfil().setEnabled(false);
                //un encargado no se puede modificar el perfil
            this.ventana.verClave().setText(amUsuario.verClave());
            this.ventana.verClaveRepetida().setText(amUsuario.verClave());
        }
    }

    @Override
    public void btnGuardarClic(ActionEvent evt) {
        String correo = this.ventana.verCorreo().getText().trim();
        String apellido = this.ventana.verApellido().getText().trim();
        String nombre = this.ventana.verNombre().getText().trim();
        Perfil perfil = ((ModeloComboPerfiles)this.ventana.verPerfil().getModel()).obtenerPerfil();
        String clave = new String(this.ventana.verClave().getPassword());
        String claveRepetida = new String(this.ventana.verClaveRepetida().getPassword());
        IGestorUsuarios gu = GestorUsuarios.instanciar();
        String resultado;
        if (this.amUsuario == null)  //creación            
            resultado = gu.crearUsuario(this.usuarioLogueado, correo, apellido, nombre, perfil, clave, claveRepetida);            
        else //modificación
            resultado = gu.modificarUsuario(this.usuarioLogueado, this.amUsuario, correo, apellido, nombre, perfil, clave, claveRepetida);
        if (!resultado.equals(IGestorUsuarios.EXITO))
            JOptionPane.showMessageDialog(null, resultado, TITULO_NUEVO, JOptionPane.ERROR_MESSAGE);
        else
            this.ventana.dispose(); 
    }

    @Override
    public void btnCancelarClic(ActionEvent evt) {
        this.ventana.dispose();
    }

    @Override
    public void txtApellidoPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER)
            this.btnGuardarClic(null);
    }

    @Override
    public void txtNombrePresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER)
            this.btnGuardarClic(null);
    }

    @Override
    public void txtCorreoPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER)
            this.btnGuardarClic(null);
    }

    @Override
    public void passClavePresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER)
            this.btnGuardarClic(null);
    }

    @Override
    public void passClaveRepetidaPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER)
            this.btnGuardarClic(null);
    }

    
}
