/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.controladores;

import interfaces.IControladorPedidos;
import interfaces.IGestorPermisos;
import interfaces.IGestorUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import pedidos.modelos.ModeloTablaPedidos;
import pedidos.modelos.Pedido;
import pedidos.vistas.VentanaPedidos;
import permisos.modelos.GestorPermisos;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.Usuario;
import interfaces.IControladorAMPedido;
import interfaces.IGestorPedidos;
import javax.swing.JOptionPane;
import pedidos.modelos.GestorPedidos;

/**
 *
 * @author root
 */
public class ControladorPedidos implements IControladorPedidos {
    private VentanaPedidos ventana;
    private IGestorUsuarios gu = GestorUsuarios.instanciar();
    private Usuario usuarioLogueado = gu.verUsuarioLogueado();
    private IGestorPermisos gp = GestorPermisos.instanciar();
        
    private int filaSeleccionada = -1;
    //permite que cuando se vuelva a la ventana, se seleccione la fila que estaba seleccionada
    
    public ControladorPedidos(JFrame ventanaPadre) {
        this.ventana = new VentanaPedidos(ventanaPadre, this);        
        this.ventana.setLocationRelativeTo(null);        
//        ModeloTablaPedidos mtp = new ModeloTablaPedidos(this.usuarioLogueado);  
//        this.configurarTabla(mtp);
        this.ventana.setTitle(this.generarTituloVentana());
        this.ventana.verNuevo().setEnabled(this.gp.crearPedidos(this.usuarioLogueado));
        
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
     * Configura la tabla de usuarios para detectar la selección de una fila
     */
//    private void configurarTabla(ModeloTablaPedidos mtp) {
//        JTable tablaPedidos = this.ventana.verPedidos();
//        tablaPedidos.setModel(mtp);
//
//        tablaPedidos.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
//            if (!e.getValueIsAdjusting()) {
                //En esta tabla se puede seleccionar sólo un elemento a la vez,
                //por lo que cuando se realiza una nueva selección,
                //el elemento seleccionado anteriormente se convierte en no seleccionado,
                //disparándose 2 eventos cada vez que se selecciona un elemento diferente
                //Para evitar responder al evento cuando un elemento deja de estar seleccionado
                //y luego cuando otro queda seleccionado, se comprueba que esta secuencia de eventos
                //esté terminada mediante getValueIsAdjusting()
//                this.filaSeleccionada = tablaPedidos.getSelectedRow();
                //int indice = tablaUsuarios.convertRowIndexToModel(fila);
                //si se ordena la tabla de usuarios por alguna columna, este orden no va a coincidir con el orden en el que están guardados los usuarios
//                Pedido pedidoSeleccionado = mtp.verPedido(this.filaSeleccionada);
//                this.ventana.verModificar().setEnabled(gp.modificarUsuarios(this.usuarioLogueado, usuarioSeleccionado));
//                this.ventana.verBorrar().setEnabled(gp.borrarUsuarios(this.usuarioLogueado, usuarioSeleccionado));
//            }
//        });
//    }

    @Override
    public void btnNuevoClic(ActionEvent evt) {
        IControladorAMPedido controlador = new ControladorAMPedido(this.ventana, null);
    }

    @Override
    public void btnModificarClic(ActionEvent evt) {
        JTable tablaPedidos = this.ventana.verPedidos();
        this.filaSeleccionada = tablaPedidos.getSelectedRow();
        if (this.filaSeleccionada != -1) {
            ModeloTablaPedidos mtp = (ModeloTablaPedidos)tablaPedidos.getModel();
            Pedido pedidoSeleccionado = mtp.verPedido(this.filaSeleccionada);
            IControladorAMPedido controlador = new ControladorAMPedido(this.ventana, pedidoSeleccionado);
        }
    }

    @Override
    public void btnCancelarClic(ActionEvent evt) {
        JTable tablaPedidos = this.ventana.verPedidos();
        this.filaSeleccionada = tablaPedidos.getSelectedRow();
        if (this.filaSeleccionada != -1) { //hay una fila seleccionada
            int opcion = JOptionPane.showOptionDialog(null, CONFIRMACION, TITULO, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Sí", "No"}, this); 
            if (opcion == JOptionPane.YES_OPTION) { //se quiere cancela el pedido
                ModeloTablaPedidos mtp = (ModeloTablaPedidos)tablaPedidos.getModel();
                Pedido pedidoSeleccionado = mtp.verPedido(this.filaSeleccionada);
                if (pedidoSeleccionado != null) {
                    IGestorPedidos gp = GestorPedidos.instanciar();
                    String resultado = gp.cancelarPedido(this.usuarioLogueado, pedidoSeleccionado);
                    if (!resultado.equals(IGestorUsuarios.EXITO)) //no se pudo cancelar el pedido
                        JOptionPane.showMessageDialog(null, resultado, TITULO, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void ventanaObtenerFoco(WindowEvent evt) {
        JTable tablaPedidos = this.ventana.verPedidos();
        ModeloTablaPedidos mtp = new ModeloTablaPedidos(this.usuarioLogueado);
        tablaPedidos.setModel(mtp);
        
        if (mtp.getRowCount() > 0) { //si hay filas, se selecciona la primera
            if (this.filaSeleccionada == -1)
                this.filaSeleccionada = 0;
            tablaPedidos.setRowSelectionInterval(this.filaSeleccionada, this.filaSeleccionada);
//            this.ventana.verBtnBorrarProfesor().setEnabled(true);
//            this.ventana.verBtnModificarProfesor().setEnabled(true);            
        }
        else {
            this.filaSeleccionada = -1;   
//            this.ventana.verTxtApellidosProfesor().setEditable(false);
//            this.ventana.verBtnBorrarProfesor().setEnabled(false);
//            this.ventana.verBtnModificarProfesor().setEnabled(false);
        }                
    }

    @Override
    public void btnVolverClic(ActionEvent evt) {
        this.ventana.dispose();
    }

    @Override
    public void txtDescripcionPresionarTecla(KeyEvent evt) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void btnBuscarClic(ActionEvent evt) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
