/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos.controladores;

import interfaces.IControladorAMProducto;
import interfaces.IGestorProductos;
import interfaces.IGestorUsuarios;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import productos.modelos.Categoria;
import productos.modelos.Estado;
import productos.modelos.GestorProductos;
import productos.modelos.ModeloComboCategorias;
import productos.modelos.ModeloComboEstados;
import productos.modelos.Producto;
import productos.vistas.VentanaAMProducto;

/**
 *
 * @author root
 */
public class ControladorAMProducto implements IControladorAMProducto {
    private VentanaAMProducto ventana;
    private Producto amProducto;
    
    /**
     * Constructor
     *
     * @param ventanaPadre (VentanaUsuarios en este caso)
     * @param amProducto producto que se quiere crear/modificar
    */
    public ControladorAMProducto(Dialog ventanaPadre, Producto amProducto) {
        this.amProducto = amProducto;
        this.ventana = new VentanaAMProducto(this, ventanaPadre);
        if (this.amProducto == null) { //creación de producto
            this.ventana.setTitle(TITULO_NUEVO);
        } else { //modificación de producto
            this.ventana.setTitle(TITULO_MODIFICAR);
        }
        this.configurarComboCategorias();
        this.configurarComboEstados();
        this.configurarCampos();
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }
    
    /**
     * Configura el combo de categorias
     */
    private void configurarComboCategorias() {
        ModeloComboCategorias mcc = new ModeloComboCategorias();
        this.ventana.verCategoria().setModel(mcc);
        
    }
    
    /**
     * Configura el combo de estados
     */
    private void configurarComboEstados() {
        ModeloComboEstados mce = new ModeloComboEstados();
        this.ventana.verEstado().setModel(mce);
        
    }
    
    /**
     * Configura los campos
     */
    private void configurarCampos() {
        if (this.amProducto != null) { //modificación de producto
            this.ventana.verCodigo().setText(Integer.toString(amProducto.verCodigo()));
            this.ventana.verCodigo().setEnabled(false);
            this.ventana.verDescripcion().setText(amProducto.verDescripcion());
            this.ventana.verPrecio().setText(Float.toString(amProducto.verPrecio()));
            ((ModeloComboCategorias)this.ventana.verCategoria().getModel()).seleccionarCategoria(amProducto.verCategoria());
            ((ModeloComboEstados)this.ventana.verEstado().getModel()).seleccionarEstado(amProducto.verEstado());                                    
        }
    }

    @Override
    public void btnGuardarClic(ActionEvent evt) {
        int codigo = Integer.parseInt(this.ventana.verCodigo().getText().trim());
        String descripcion = this.ventana.verDescripcion().getText().trim();
        float precio = Float.parseFloat(this.ventana.verPrecio().getText().trim());
        Categoria categoria = ((ModeloComboCategorias)this.ventana.verCategoria().getModel()).obtenerCategoria();
        Estado estado = ((ModeloComboEstados)this.ventana.verEstado().getModel()).obtenerEstado();
        IGestorProductos gp = GestorProductos.instanciar();
        String resultado;
        if (this.amProducto == null) { //creación            
            resultado = gp.crearProducto(codigo, descripcion, precio, categoria, estado);
            if (!resultado.equals(IGestorProductos.EXITO))
                JOptionPane.showMessageDialog(null, resultado, TITULO_NUEVO, JOptionPane.ERROR_MESSAGE);
            else
                this.ventana.dispose(); 
        }
        else { //modificación
            resultado = gp.modificarProducto(this.amProducto, codigo, descripcion, precio, categoria, estado);
            if (!resultado.equals(IGestorProductos.EXITO))
                JOptionPane.showMessageDialog(null, resultado, TITULO_NUEVO, JOptionPane.ERROR_MESSAGE);
            else
                this.ventana.dispose();
        }
    }

    @Override
    public void btnCancelarClic(ActionEvent evt) {
        this.ventana.dispose();
    }

    @Override
    public void txtCodigoPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER)
            this.btnGuardarClic(null);
    }

    @Override
    public void txtDescripcionPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER)
            this.btnGuardarClic(null);
    }

    @Override
    public void txtPrecioPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER)
            this.btnGuardarClic(null);
    }    
    
}
