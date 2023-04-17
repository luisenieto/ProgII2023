/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos.controladores;

import interfaces.IControladorAMProducto;
import interfaces.IControladorProductos;
import interfaces.IGestorPermisos;
import interfaces.IGestorProductos;
import interfaces.IGestorUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import permisos.modelos.GestorPermisos;
import productos.modelos.GestorProductos;
import productos.modelos.ModeloTablaProductos;
import productos.modelos.Producto;
import productos.vistas.VentanaProductos;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.Usuario;

/**
 *
 * @author root
 */
public class ControladorProductos implements IControladorProductos {
    private VentanaProductos ventana;
    private IGestorPermisos gp = GestorPermisos.instanciar();
    private IGestorUsuarios gu = GestorUsuarios.instanciar();
    private Usuario usuarioLogueado = this.gu.verUsuarioLogueado();
    
    private int filaSeleccionada = -1;
    //permite que cuando se vuelva a la ventana, se seleccione la fila que estaba seleccionada
    private boolean yaSeAgregoListenerATabla = false;
    //permite llamar una sola vez al método que le agrega el listener a la tabla
    
    public ControladorProductos(JFrame ventanaPadre) {
        this.ventana = new VentanaProductos(ventanaPadre, this);
        this.ventana.setLocationRelativeTo(null);

        this.ventana.verNuevo().setEnabled(this.gp.crearProductos(this.usuarioLogueado));
        this.ventana.verModificar().setEnabled(this.gp.modificarProductos(this.usuarioLogueado));
        this.ventana.verBorrar().setEnabled(this.gp.borrarProductos(this.usuarioLogueado));
        this.ventana.verDescripcion().setEnabled(this.gp.menu(this.usuarioLogueado));
        this.ventana.verBuscar().setEnabled(this.gp.menu(this.usuarioLogueado));
        
        this.ventana.setTitle(this.generarTituloVentana());        
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
    private void agregarListenerATabla(JTable tablaProductos, ModeloTablaProductos mtp) {
        tablaProductos.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                //En esta tabla se puede seleccionar sólo un elemento a la vez,
                //por lo que cuando se realiza una nueva selección,
                //el elemento seleccionado anteriormente se convierte en no seleccionado,
                //disparándose 2 eventos cada vez que se selecciona un elemento diferente
                //Para evitar responder al evento cuando un elemento deja de estar seleccionado
                //y luego cuando otro queda seleccionado, se comprueba que esta secuencia de eventos
                //esté terminada mediante getValueIsAdjusting()
                if (tablaProductos.getSelectedRow() != -1)
                    this.filaSeleccionada = tablaProductos.getSelectedRow();
                //int indice = tablaUsuarios.convertRowIndexToModel(fila);
                //si se ordena la tabla de usuarios por alguna columna, este orden no va a coincidir con el orden en el que están guardados los usuarios
                Producto productoSeleccionado = mtp.verProducto(this.filaSeleccionada);
                this.ventana.verModificar().setEnabled(this.gp.modificarProductos(this.usuarioLogueado) && productoSeleccionado.verPrecio() > 0);
                this.ventana.verBorrar().setEnabled(this.gp.borrarProductos(this.usuarioLogueado) && productoSeleccionado.verPrecio() > 0);
            }
        });
    }

    @Override
    public void btnNuevoClic(ActionEvent evt) {
        IControladorAMProducto controlador = new ControladorAMProducto(this.ventana, null);
    }

    @Override
    public void btnModificarClic(ActionEvent evt) {
        JTable tablaProductos = this.ventana.verProductos();
        ModeloTablaProductos mtp = (ModeloTablaProductos)tablaProductos.getModel();
        Producto productoSeleccionado = mtp.verProducto(this.filaSeleccionada);
        IControladorAMProducto controlador = new ControladorAMProducto(this.ventana, productoSeleccionado);
    }

    @Override
    public void btnBorrarClic(ActionEvent evt) {
        JTable tablaProductos = this.ventana.verProductos();
        this.filaSeleccionada = tablaProductos.getSelectedRow();
        if (this.filaSeleccionada != -1) { //hay una fila seleccionada
            int opcion = JOptionPane.showOptionDialog(null, CONFIRMACION, TITULO, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Sí", "No"}, this);       
            if (opcion == JOptionPane.YES_OPTION) { //se quiere borrar el usuario
                ModeloTablaProductos mtp = (ModeloTablaProductos)tablaProductos.getModel();
                Producto productoSeleccionado = mtp.verProducto(this.filaSeleccionada);
                if (productoSeleccionado != null) {
                    IGestorProductos gProd = GestorProductos.instanciar();
                    String resultado = gProd.borrarProducto(this.usuarioLogueado, productoSeleccionado);
                    if (resultado.equals(IGestorProductos.EXITO)) { //se pudo borrar el producto
                        mtp = new ModeloTablaProductos(this.usuarioLogueado);
                        if (mtp.getRowCount() > 0) {
                            this.filaSeleccionada = 0;
                            tablaProductos.setRowSelectionInterval(this.filaSeleccionada, this.filaSeleccionada);
                        }
                        else
                            this.filaSeleccionada = -1;
                    }
                    else //se pudo borrar el producto
                        JOptionPane.showMessageDialog(null, resultado, TITULO, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    

    @Override
    public void ventanaObtenerFoco(WindowEvent evt) {      
        JTable tablaProductos = this.ventana.verProductos();
        ModeloTablaProductos mtp = new ModeloTablaProductos(this.usuarioLogueado);
        tablaProductos.setModel(mtp);
        
        if (mtp.getRowCount() > 0) { //si hay filas, se selecciona la primera
            if (this.filaSeleccionada == -1)
                this.filaSeleccionada = 0;
            tablaProductos.setRowSelectionInterval(this.filaSeleccionada, this.filaSeleccionada);
            Producto productoSeleccionado = mtp.verProducto(this.filaSeleccionada);
            this.ventana.verModificar().setEnabled(this.gp.modificarProductos(this.usuarioLogueado) && productoSeleccionado.verPrecio() > 0);
            this.ventana.verBorrar().setEnabled(this.gp.borrarProductos(this.usuarioLogueado) && productoSeleccionado.verPrecio() > 0);
        }
        else {
            this.filaSeleccionada = -1; 
            this.ventana.verModificar().setEnabled(false);
            this.ventana.verBorrar().setEnabled(false);
        }
        
        if (!this.yaSeAgregoListenerATabla) {
            this.agregarListenerATabla(tablaProductos, mtp);
            this.yaSeAgregoListenerATabla = true;
        }
    }

    @Override
    public void btnVolverClic(ActionEvent evt) {
        this.ventana.dispose();
    }

    @Override
    public void txtDescripcionPresionarTecla(KeyEvent evt) {    
        String descripcion = this.ventana.verDescripcion().getText().trim();
        System.out.println(descripcion);
//        char c = evt.getKeyChar();        
//        if (Character.isLetter(c) || Character.isDigit(c)) {
//            String cadenaBusqueda = Character.toString(c);
//            this.buscar(cadenaBusqueda);
//        }
            
            
//        System.out.print("txtDescripcionPresionarTecla: ");
//        System.out.println(c);
//        if (c == KeyEvent.VK_ENTER ||
//            c == KeyEvent.VK_BACK_SPACE ||
//            c == KeyEvent.VK_DELETE ||    
//            Character.isLetter(c) ||
//            Character.isDigit(c)) 
//                this.buscar();
//                this.btnBuscarClic(null);
    }

    @Override
    public void btnBuscarClic(ActionEvent evt) {
//        this.buscar();
//        System.out.print("btnBuscarClic: ");
//        ModeloTablaProductos mtp;
//        
//        String descripcion = this.ventana.verDescripcion().getText(); //.trim();
//        System.out.println(descripcion);
//        if (descripcion.isEmpty())
//            mtp = new ModeloTablaProductos();
//        else
//            mtp = new ModeloTablaProductos(descripcion);
//        this.configurarTabla(mtp);
    }
    
    private void buscar(String cadenaBusqueda) {
        //System.out.print("buscar: ");
        ModeloTablaProductos mtp;
        
        //String descripcion = this.ventana.verDescripcion().getText().trim();
        //System.out.println(descripcion);
        if (cadenaBusqueda.isEmpty())
            mtp = new ModeloTablaProductos(this.usuarioLogueado);
        else
            mtp = new ModeloTablaProductos(this.usuarioLogueado, cadenaBusqueda);
//        this.configurarTabla(mtp);
        
    }
    
    
    
    
}
