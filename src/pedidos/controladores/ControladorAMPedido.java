/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.controladores;

import auxiliares.ManejoFechasYHoras;
import interfaces.IGestorPedidos;
import interfaces.IGestorUsuarios;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import pedidos.modelos.GestorPedidos;
import pedidos.modelos.ModeloTablaProductosDelPedido;
import pedidos.modelos.ProductoDelPedido;
import pedidos.vistas.VentanaAMPedido;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.Usuario;
import interfaces.IControladorAMProductoDelPedido;
import javax.swing.event.ListSelectionEvent;
import interfaces.IControladorAMPedido;
import javax.swing.JOptionPane;
import pedidos.modelos.Pedido;
import usuarios.modelos.Cliente;

/**
 *
 * @author root
 */
public class ControladorAMPedido implements IControladorAMPedido {
    private VentanaAMPedido ventana;
    private IGestorUsuarios gu = GestorUsuarios.instanciar();
    private Usuario usuarioLogueado = gu.verUsuarioLogueado();
//    private List<ProductoDelPedido> pdp = new ArrayList<>();
    private Pedido amPedido;
    //pedido que se quiere crear/modificar
    private int filaSeleccionada = -1;
    
    public ControladorAMPedido(Dialog ventanaPadre, Pedido amPedido) {
        this.ventana = new VentanaAMPedido(this, ventanaPadre);        
        this.ventana.setLocationRelativeTo(null);                
        this.ventana.setTitle(this.generarTituloVentana());                
        this.amPedido = amPedido;

        if (this.amPedido == null) { //nuevo pedido
            IGestorPedidos gp = GestorPedidos.instanciar();
            this.ventana.verNumero().setText(Integer.toString(gp.verPedidos(this.usuarioLogueado).size() + 1));            

            LocalDateTime fechaYHora = LocalDateTime.now();
            LocalDate fecha = fechaYHora.toLocalDate();
            LocalTime hora = fechaYHora.toLocalTime();

            this.ventana.verFecha().setText(ManejoFechasYHoras.transformarLocalDateEnCadena(fecha));
            this.ventana.verHora().setText(ManejoFechasYHoras.transformarLocalTimeEnCadena(hora));            
        }
        else { //modificación de un pedido
            this.ventana.verNumero().setText(Integer.toString(this.amPedido.verNumero()));            
            this.ventana.verFecha().setText(ManejoFechasYHoras.transformarLocalDateEnCadena(this.amPedido.verFecha()));
            this.ventana.verHora().setText(ManejoFechasYHoras.transformarLocalTimeEnCadena(this.amPedido.verHora()));            
        }
        
        this.ventana.verNumero().setEnabled(false);
        this.ventana.verFecha().setEnabled(false);
        this.ventana.verHora().setEnabled(false);
        
//        this.configurarTabla();
        this.ventana.setVisible(true);  
    }
    
    /**
     * Genera el título que se muestra en la ventana
     * @return String  - título que se muestra en la ventana
    */
    private String generarTituloVentana() {  
        return TITULO_NUEVO + " - " + this.usuarioLogueado.verApellido() + ", " + this.usuarioLogueado.verNombre();
    }
    
    
    /**
     * Configura la tabla de productos del pedido para detectar la selección de una fila
    */
    private void configurarTabla() {
        JTable tablaProductosDelPedido = this.ventana.verProductosDelPedido();
//        ModeloTablaProductosDelPedido mtpdp = new ModeloTablaProductosDelPedido(this.pdp);
//        tablaProductosDelPedido.setModel(mtpdp);

        tablaProductosDelPedido.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                //En esta tabla se puede seleccionar sólo un elemento a la vez,
                //por lo que cuando se realiza una nueva selección,
                //el elemento seleccionado anteriormente se convierte en no seleccionado,
                //disparándose 2 eventos cada vez que se selecciona un elemento diferente
                //Para evitar responder al evento cuando un elemento deja de estar seleccionado
                //y luego cuando otro queda seleccionado, se comprueba que esta secuencia de eventos
                //esté terminada mediante getValueIsAdjusting()
                this.filaSeleccionada = tablaProductosDelPedido.getSelectedRow();
                //int indice = tablaUsuarios.convertRowIndexToModel(fila);
                //si se ordena la tabla de usuarios por alguna columna, este orden no va a coincidir con el orden en el que están guardados los usuarios
//                Usuario usuarioSeleccionado = mtu.verUsuario(filaSeleccionada);
//                ventana.verModificar().setEnabled(gp.modificarUsuarios(this.usuarioLogueado, usuarioSeleccionado));
//                ventana.verBorrar().setEnabled(gp.borrarUsuarios(this.usuarioLogueado, usuarioSeleccionado));
//                this.ventana.verQuitar().setEnabled(this.filaSeleccionada != -1 && !this.pdp.isEmpty());
//                this.ventana.verModificar().setEnabled(this.filaSeleccionada != -1 && !this.pdp.isEmpty());
            }
        });
    }

    @Override
    public void btnAgregarClic(ActionEvent evt) {        
//        IControladorAMProductoDelPedido controlador = new ControladorAMProductoDelPedido(this.ventana, this.pdp, null);
    }

    @Override
    public void btnModificarClic(ActionEvent evt) {
        JTable tablaProductosDelPedido = this.ventana.verProductosDelPedido();
        this.filaSeleccionada = tablaProductosDelPedido.getSelectedRow();
        if (this.filaSeleccionada != -1) {
            ModeloTablaProductosDelPedido mtpdp = (ModeloTablaProductosDelPedido)tablaProductosDelPedido.getModel();
            ProductoDelPedido productoDelPedidoSeleccionado = mtpdp.verProductoDelPedido(this.filaSeleccionada);
//            IControladorAMProductoDelPedido controlador = new ControladorAMProductoDelPedido(this.ventana, this.pdp, productoDelPedidoSeleccionado);
        }
    }

    @Override
    public void btnQuitarClic(ActionEvent evt) {
//        JTable tablaProductosDelPedido = this.ventana.verProductosDelPedido();
//        this.filaSeleccionada = tablaProductosDelPedido.getSelectedRow();
//        if (this.filaSeleccionada != -1) {
//            ModeloTablaProductosDelPedido mtpdp = (ModeloTablaProductosDelPedido)tablaProductosDelPedido.getModel();
//            ProductoDelPedido productoDelPedidoSeleccionado = mtpdp.verProductoDelPedido(this.filaSeleccionada);
//            this.pdp.remove(productoDelPedidoSeleccionado);
//            mtpdp = new ModeloTablaProductosDelPedido(this.pdp);
//            tablaProductosDelPedido.setModel(mtpdp);
//            this.filaSeleccionada = -1;
//            this.ventana.verQuitar().setEnabled(this.filaSeleccionada != -1 && !this.pdp.isEmpty());
//            this.ventana.verModificar().setEnabled(this.filaSeleccionada != -1 && !this.pdp.isEmpty());
//        }
    }

    @Override
    public void btnGuardarClic(ActionEvent evt) {
/*        
        String fechaEnCadena = this.ventana.verFecha().getText();
        LocalDate fecha = ManejoFechasYHoras.transformarCadenaALocalDate(fechaEnCadena);
        
        String horaEnCadena = this.ventana.verHora().getText();
        LocalTime hora = ManejoFechasYHoras.transformarCadenaALocalTime(horaEnCadena);
        
        IGestorPedidos gp = GestorPedidos.instanciar();
//        if (this. == null) { //creación            
            String resultado = gp.crearPedido(this.usuarioLogueado, fecha, hora, this.pdp, (Cliente)this.usuarioLogueado);
            if (!resultado.equals(IGestorPedidos.EXITO))
                JOptionPane.showMessageDialog(null, resultado, TITULO_NUEVO, JOptionPane.ERROR_MESSAGE);
            else
                this.ventana.dispose(); 
//        }
//        else { //modificación
//            //
//        }
*/
    }

    @Override
    public void btnCancelarClic(ActionEvent evt) {
        this.ventana.dispose();
    }

    @Override
    public void ventanaObtenerFoco(WindowEvent evt) {
        ModeloTablaProductosDelPedido mtpdp = new ModeloTablaProductosDelPedido(this.amPedido);
        JTable tablaProductosDelPedido = this.ventana.verProductosDelPedido();
        tablaProductosDelPedido.setModel(mtpdp);        
    }
    
    
}
