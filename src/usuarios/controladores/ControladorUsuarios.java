/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.controladores;

import interfaces.IControladorAMUsuario;
import interfaces.IControladorUsuarios;
import interfaces.IGestorPermisos;
import interfaces.IGestorUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import permisos.modelos.GestorPermisos;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.ModeloTablaUsuarios;
import usuarios.modelos.Usuario;
import usuarios.vistas.VentanaUsuarios;

/**
 *
 * @author root
 */
public class ControladorUsuarios implements IControladorUsuarios {
    private VentanaUsuarios ventana;
    private IGestorUsuarios gu = GestorUsuarios.instanciar();
    private Usuario usuarioLogueado = gu.verUsuarioLogueado();
    private IGestorPermisos gp = GestorPermisos.instanciar();
    
    private int filaSeleccionada = -1;
    //permite que cuando se vuelva a la ventana, se seleccione la fila que estaba seleccionada
    private boolean yaSeAgregoListenerATabla = false;
    //permite llamar una sola vez al método que le agrega el listener a la tabla
    

    public ControladorUsuarios(JFrame ventanaPadre) {
        this.ventana = new VentanaUsuarios(ventanaPadre, this);        
        this.ventana.setLocationRelativeTo(null);        
        this.ventana.setTitle(this.generarTituloVentana());
        
        this.ventana.verNuevo().setEnabled(!this.gp.crearUsuarios(this.usuarioLogueado).isEmpty());
        this.ventana.verApellido().setEnabled(this.gp.buscarUsuarios(this.usuarioLogueado));
        this.ventana.verBuscar().setEnabled(this.gp.buscarUsuarios(this.usuarioLogueado));
        
        this.ventana.setVisible(true);  
    }
    
    /**
     * Genera el título que se muestra en la ventana
     * @return String  - título que se muestra en la ventana
    */
    private String generarTituloVentana() {  
        return TITULO + " - " + this.usuarioLogueado.verApellido() + ", " + this.usuarioLogueado.verNombre() + " | " + this.usuarioLogueado.verPerfil();
    }
    
    @Override
    public void btnNuevoClic(ActionEvent evt) {
        IControladorAMUsuario controlador = new ControladorAMUsuario(this.ventana, null);
    }

    @Override
    public void btnModificarClic(ActionEvent evt) {
        JTable tablaUsuarios = this.ventana.verUsuarios();
        ModeloTablaUsuarios mtu = (ModeloTablaUsuarios)tablaUsuarios.getModel();
        Usuario usuarioSeleccionado = mtu.verUsuario(this.filaSeleccionada);
        IControladorAMUsuario controlador = new ControladorAMUsuario(this.ventana, usuarioSeleccionado);
    }

    @Override
    public void btnBorrarClic(ActionEvent evt) {        
        JTable tablaUsuarios = this.ventana.verUsuarios();
        this.filaSeleccionada = tablaUsuarios.getSelectedRow();
        if (this.filaSeleccionada != -1) { //hay una fila seleccionada
            int opcion = JOptionPane.showOptionDialog(null, CONFIRMACION, TITULO, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Sí", "No"}, this);       
            if (opcion == JOptionPane.YES_OPTION) { //se quiere borrar el usuario
                ModeloTablaUsuarios mtu = (ModeloTablaUsuarios)tablaUsuarios.getModel();
                Usuario usuarioSeleccionado = mtu.verUsuario(this.filaSeleccionada);
                if (usuarioSeleccionado != null) {
                    IGestorUsuarios gu = GestorUsuarios.instanciar();
                    String resultado = gu.borrarUsuario(this.usuarioLogueado, usuarioSeleccionado);
                    if (resultado.equals(IGestorUsuarios.EXITO)) { //se pudo borrar el usuario
                        mtu = new ModeloTablaUsuarios(this.usuarioLogueado);
                        if (mtu.getRowCount() > 0) {
                            this.filaSeleccionada = 0;
                            tablaUsuarios.setRowSelectionInterval(this.filaSeleccionada, this.filaSeleccionada);
                        }
                        else
                            this.filaSeleccionada = -1;
                    }
                    else { //no se pudo borrar el usuario
                        JOptionPane.showMessageDialog(null, resultado, TITULO, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        
    }
    
    @Override
    public void ventanaObtenerFoco(WindowEvent evt) {
        JTable tablaUsuarios = this.ventana.verUsuarios();        
        ModeloTablaUsuarios mtu = new ModeloTablaUsuarios(this.usuarioLogueado);
        tablaUsuarios.setModel(mtu);
        
        if (mtu.getRowCount() > 0) { //si hay filas, se selecciona la primera
            if (this.filaSeleccionada == -1)
                this.filaSeleccionada = 0;
            tablaUsuarios.setRowSelectionInterval(this.filaSeleccionada, this.filaSeleccionada);
            Usuario usuarioSeleccionado = mtu.verUsuario(this.filaSeleccionada);
            this.ventana.verModificar().setEnabled(this.gp.modificarUsuarios(this.usuarioLogueado, usuarioSeleccionado));
            this.ventana.verBorrar().setEnabled(this.gp.borrarUsuarios(this.usuarioLogueado, usuarioSeleccionado));
        }
        else {
            this.filaSeleccionada = -1;   
            this.ventana.verModificar().setEnabled(false);
            this.ventana.verBorrar().setEnabled(false);
        }
        
        if (!this.yaSeAgregoListenerATabla) {
            this.agregarListenerATabla(tablaUsuarios, mtu);
            this.yaSeAgregoListenerATabla = true;
        }
    } 
    
    /**
     * Permite agregarle un listener a la tabla de forma tal que 
     * se habiliten/deshabiliten los botones "Modificar" y "Borrar" según el usuario seleccionado y usuario logueado
     * @param tablaUsuarios tabla de usuarios
     * @param mtu modelo de la tabla
     */
    private void agregarListenerATabla(JTable tablaUsuarios, ModeloTablaUsuarios mtu) {
        tablaUsuarios.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                //En esta tabla se puede seleccionar sólo un elemento a la vez,
                //por lo que cuando se realiza una nueva selección,
                //el elemento seleccionado anteriormente se convierte en no seleccionado,
                //disparándose 2 eventos cada vez que se selecciona un elemento diferente
                //Para evitar responder al evento cuando un elemento deja de estar seleccionado
                //y luego cuando otro queda seleccionado, se comprueba que esta secuencia de eventos
                //esté terminada mediante getValueIsAdjusting()
                if (tablaUsuarios.getSelectedRow() != -1)
                    this.filaSeleccionada = tablaUsuarios.getSelectedRow();
                //int indice = tablaUsuarios.convertRowIndexToModel(fila);
                //si se ordena la tabla de usuarios por alguna columna, este orden no va a coincidir con el orden en el que están guardados los usuarios                
                Usuario usuarioSeleccionado = mtu.verUsuario(this.filaSeleccionada);
                this.ventana.verModificar().setEnabled(this.gp.modificarUsuarios(this.usuarioLogueado, usuarioSeleccionado));
                this.ventana.verBorrar().setEnabled(this.gp.borrarUsuarios(this.usuarioLogueado, usuarioSeleccionado));
            }
        });
    }

    @Override
    public void btnVolverClic(ActionEvent evt) {
        this.ventana.dispose();
    }

    @Override
    public void txtApellidoPresionarTecla(KeyEvent evt) {
        //Diferencia entre keyPressed, keyTyped y keyReleased:
            //https://math.hws.edu/eck/cs124/javanotes3/c6/s5.html#:~:text=The%20keyPressed%20method%20is%20called,the%20user%20types%20a%20character
        char caracter = evt.getKeyChar();
        if (Character.isLetter(caracter) || Character.isDigit(caracter))
            this.buscar(this.ventana.verApellido().getText().trim());
        else {
            int codigoTecla = evt.getKeyCode();
            if (codigoTecla == KeyEvent.VK_BACK_SPACE || codigoTecla == KeyEvent.VK_DELETE)
                this.buscar(this.ventana.verApellido().getText().trim());
        }
    }

    @Override
    public void txtApellidoPresionarEnter(ActionEvent evt) {
        //Detección de Enter en JTextField
            //https://stackoverflow.com/questions/4419667/detect-enter-press-in-jtextfield        
        this.buscar(this.ventana.verApellido().getText().trim());
    }
    
    

    @Override
    public void btnBuscarClic(ActionEvent evt) {
        this.buscar(this.ventana.verApellido().getText().trim());
    }
    
    private void buscar(String cadenaBusqueda) {
        ModeloTablaUsuarios mtu;
        JTable tablaUsuarios = this.ventana.verUsuarios(); 
        if (cadenaBusqueda.isEmpty())
            mtu = new ModeloTablaUsuarios(this.usuarioLogueado);
        else
            mtu = new ModeloTablaUsuarios(this.usuarioLogueado, cadenaBusqueda);
        tablaUsuarios.setModel(mtu);
    }
}
