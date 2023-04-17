/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos.modelos;

import interfaces.IGestorProductos;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author root
 */
public class ModeloComboProductos extends DefaultComboBoxModel {
    
    /**
     * Constructor
     * @param categoria categoría de los productos a buscar
    */
    public ModeloComboProductos(Categoria categoria) {
        IGestorProductos gp = GestorProductos.instanciar();
        for (Producto producto : gp.verProductosPorCategoria(categoria)) {
            this.addElement(producto); 
        }
    }
    
    /**
     * Devuelve el producto seleccionado
     * @return Producto  - producto seleccionado
    */
    public Producto obtenerProducto() { 
        return (Producto)this.getSelectedItem();
    }
    
    /**
     * Selecciona el producto especificado
     * @param producto producto
    */
    public void seleccionarProducto(Producto producto) {
        this.setSelectedItem(producto);
    }
}
