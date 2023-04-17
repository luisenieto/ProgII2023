/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.controladores;

import usuarios.controladores.ControladorLogin;
import interfaces.IControladorLogin;
import interfaces.IControladorPedidos;
import interfaces.IControladorPrincipal;
import interfaces.IControladorProductos;
import interfaces.IControladorUsuarios;
import interfaces.IGestorPermisos;
import interfaces.IGestorUsuarios;
import java.awt.event.ActionEvent;
import pedidos.controladores.ControladorPedidos;
import permisos.modelos.GestorPermisos;
import principal.vistas.VentanaPrincipal;
import productos.controladores.ControladorProductos;
import usuarios.controladores.ControladorUsuarios;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;

/**
 *
 * @author root
 */
public class ControladorPrincipal implements IControladorPrincipal {
    private VentanaPrincipal ventana;  
    private IGestorUsuarios gu = GestorUsuarios.instanciar();
    private Usuario usuarioLogueado = this.gu.verUsuarioLogueado();
    private IGestorPermisos gp = GestorPermisos.instanciar();

    public ControladorPrincipal() {
        this.ventana = new VentanaPrincipal(this);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setTitle(this.generarTituloVentana());
//        this.configurarBotones();
        this.ventana.setVisible(true);        
    }
    
    /**
     * Genera el título que se muestra en la ventana
     * @return String  - título que se muestra en la ventana
    */
    private String generarTituloVentana() {  
        return TITULO + " - " + this.usuarioLogueado.verApellido() + ", " + this.usuarioLogueado.verNombre() + " | " + this.usuarioLogueado.verPerfil();
    }
    
    /**
     * Habilita o deshabilita los botones según el perfil del usuario logueado
     */
    private void configurarBotones() {        
        this.ventana.verUsuarios().setEnabled(this.gp.habilitarBotonUsuarios(this.usuarioLogueado));
        this.ventana.verProductos().setEnabled(this.gp.habilitarBotonProductos(this.usuarioLogueado));
        this.ventana.verPedidos().setEnabled(this.gp.habilitarBotonPedidos(this.usuarioLogueado));        
    }
        
    @Override
    public void btnUsuariosClic(ActionEvent evt) {
        IControladorUsuarios cu = new ControladorUsuarios(this.ventana);
    }

    @Override
    public void btnProductosClic(ActionEvent evt) {
        IControladorProductos cp = new ControladorProductos(this.ventana);
    }

    @Override
    public void btnPedidosClic(ActionEvent evt) {
        IControladorPedidos cp = new ControladorPedidos(this.ventana);
    }

    @Override
    public void btnSalirClic(ActionEvent evt) {
        this.ventana.dispose();
        this.gu.cerrarSesion();
        IControladorLogin cl = new ControladorLogin();
    }
    
    
}
