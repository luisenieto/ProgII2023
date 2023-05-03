/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.controladores;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import pedidos.modelos.ProductoDelPedido;
import pedidos.vistas.VentanaAMProductoDelPedido;
import productos.modelos.Categoria;
import productos.modelos.ModeloComboCategorias;
import productos.modelos.ModeloComboProductos;
import productos.modelos.Producto;
import interfaces.IControladorAMProductoDelPedido;
import interfaces.IGestorProductos;
import productos.modelos.GestorProductos;

/**
 *
 * @author root
 */
public class ControladorAMProductoDelPedido implements IControladorAMProductoDelPedido {
    private VentanaAMProductoDelPedido ventana;
    private List<ProductoDelPedido> productosDelPedido;
    private ProductoDelPedido productoDelPedidoAModificar;
    
    /**
     * Constructor
     * @param ventanaPadre (VentanaAPedido en este caso)
     * @param productosDelPedido lista de productos del pedido (permite agregarlos)
     * @param productoDelPedidoAModificar producto del pedido que se está modificando
     */
    public ControladorAMProductoDelPedido(Dialog ventanaPadre, List<ProductoDelPedido> productosDelPedido, ProductoDelPedido productoDelPedidoAModificar) {
        this.ventana = new VentanaAMProductoDelPedido(this, ventanaPadre);        
        this.productosDelPedido = productosDelPedido;
        this.productoDelPedidoAModificar = productoDelPedidoAModificar;
        this.ventana.setLocationRelativeTo(null);   
        this.configurarComboCategorias();

//        this.ventana.setTitle(this.generarTituloVentana());                
        
        this.ventana.setVisible(true);  
    }
    
    /**
     * Configura el combo de categorias
    */
    private void configurarComboCategorias() {
        ModeloComboCategorias mcc = new ModeloComboCategorias();
        this.ventana.verCategoria().setModel(mcc); 
        Categoria categoria = null;
        if (this.productoDelPedidoAModificar == null) { //alta
            categoria = Categoria.values()[0]; //se selecciona la primera categoria
            mcc.seleccionarCategoria(categoria);
        }
        else { //modificación
            categoria = this.productoDelPedidoAModificar.verProducto().verCategoria();
            mcc.seleccionarCategoria(categoria);
            this.ventana.verCategoria().setEnabled(false);
            //sólo se puede modificar la cantidad
        }
        this.configurarComboProductos(categoria);
    }
    
    /**
     * Configura el combo de productos según la categoría especificada
     * @param categoria categoría con la cual se configura el combo de productos
    */
    private void configurarComboProductos(Categoria categoria) {        
        ModeloComboProductos mcp = new ModeloComboProductos(categoria);
        this.ventana.verProducto().setModel(mcp);                
        if (this.productoDelPedidoAModificar == null) { //alta
            IGestorProductos gp = GestorProductos.instanciar();
            List<Producto> productosPorCategoria = gp.verProductosPorCategoria(categoria);            
            Producto producto = null;
            if (!productosPorCategoria.isEmpty()) { //si hay productos con esa categoría
                producto = productosPorCategoria.get(0);
                mcp.seleccionarProducto(producto);
                //se selecciona el primer producto de la categoría
            }
            else { //no hay productos con esa categoría
                this.ventana.verCantidad().setEnabled(false);
                this.ventana.verAceptar().setEnabled(false);
            }
        }
        else { //modificación
            mcp.seleccionarProducto(this.productoDelPedidoAModificar.verProducto());
            this.ventana.verProducto().setEnabled(false);
            this.ventana.verCantidad().setText(Integer.toString(this.productoDelPedidoAModificar.verCantidad()));
        }
    }
    
    @Override
    public void seleccionarCategoria(ItemEvent evt) { 
        if (evt.getStateChange() == ItemEvent.SELECTED) {                        
            Categoria categoria = ((ModeloComboCategorias)this.ventana.verCategoria().getModel()).obtenerCategoria();        
            this.configurarComboProductos(categoria);
        }        
    }

    @Override
    public void txtCantidadPresionarTecla(KeyEvent evt) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void btnAceptarClic(ActionEvent evt) {
        Producto producto = ((ModeloComboProductos)this.ventana.verProducto().getModel()).obtenerProducto();
        int cantidad = Integer.parseInt(this.ventana.verCantidad().getText().trim());        
        //faltan las comprobaciones        
        if (this.productoDelPedidoAModificar == null) { //agregado de un producto al pedido
            //Al agregar un producto, puede ser que el mismo no esté o que ya esté en la lista de productos del pedido
            //si no está, se lo agrega
            //si ya está, se suman la cantidades (para que la lista no quede con prod1, cant: 1, prod1: cant: 3 por ejemplo
            ProductoDelPedido pdp = new ProductoDelPedido(producto, cantidad);
            if (!this.productosDelPedido.contains(pdp)) //no está en la lista
                this.productosDelPedido.add(pdp);
            else { //ya está
                int posicion = this.productosDelPedido.indexOf(pdp);
                ProductoDelPedido pdpOriginal = this.productosDelPedido.get(posicion);
                pdpOriginal.asignarCantidad(pdpOriginal.verCantidad() + cantidad);
                this.productosDelPedido.set(posicion, pdpOriginal);
            }
        }
        else { //modificación del producto de un pedido
            int posicion = this.productosDelPedido.indexOf(this.productoDelPedidoAModificar);
            this.productoDelPedidoAModificar.asignarCantidad(cantidad);
            this.productosDelPedido.set(posicion, this.productoDelPedidoAModificar);
        }
        this.ventana.dispose();
    }

    @Override
    public void btnCancelarClic(ActionEvent evt) {
        this.ventana.dispose();
    }
    
    
}
