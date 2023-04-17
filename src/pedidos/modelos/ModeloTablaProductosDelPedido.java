/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.modelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author root
 */
public class ModeloTablaProductosDelPedido extends AbstractTableModel {
    public static final String COLUMNA_PRODUCTO = "Producto";
    public static final String COLUMNA_CANTIDAD = "Cantidad";
    //constantes para los nombres de las columnas
    
    private List<ProductoDelPedido> productosDelPedido = new ArrayList<>();
    //los datos los saca del pedido
    
    private List<String> nombresColumnas = Arrays.asList(new String[] {COLUMNA_PRODUCTO, COLUMNA_CANTIDAD});       
    //colección para guardar los nombres de las columnas
    
    /**
    * Constructor
    * @param pedido pedido del cual se obtienen sus productos y cantidades
    */                                                        
    public ModeloTablaProductosDelPedido(Pedido pedido) {
        if (pedido != null)
            this.productosDelPedido = pedido.verProductosDelPedido();
        else 
            this.productosDelPedido = new ArrayList<>(); //lista vacía
        
    }
    
    /**
    * Constructor
    * @param productosDelPedido productos del pedido
    */                                                        
//    public ModeloTablaProductosDelPedido(List<ProductoDelPedido> productosDelPedido) {
//        this.productosDelPedido = productosDelPedido;
//    }
    
    /**
    * Obtiene el valor de la celda especificada
    * @param fila fila de la celda
    * @param columna columna de la celda
    * @return Object  - valor de la celda
    */                        
    @Override
    public Object getValueAt(int fila, int columna) {
        ProductoDelPedido productoDelPedido = this.productosDelPedido.get(fila);
        switch (columna) {
            case 0: return productoDelPedido.verProducto().verDescripcion();
            default: return productoDelPedido.verCantidad();
        }
    }
    
    /**
    * Obtiene la cantidad de columnas de la tabla
    * @return int  - cantidad de columnas de la tabla
    */                            
    @Override
    public int getColumnCount() { 
        return this.nombresColumnas.size();
    }

    /**
    * Obtiene la cantidad de filas de la tabla
    * @return int  - cantidad de filas de la tabla
    */                        
    @Override
    public int getRowCount() { 
        return this.productosDelPedido.size();
    }

    /**
    * Obtiene el nombre de una columna
    * @param columna columna sobre la que se quiere obtener el nombre
    * @return String  - nombre de la columna especificada
    */                        
    @Override
    public String getColumnName(int columna) {
        return this.nombresColumnas.get(columna);
    }
    
    /**
    * Devuelve el ProductoDelPedido correspondiente a la fila especificada dentro de la tabla
    * Si se especifica una fila inválida devuelve null
    * @param fila fila dentro de la tabla
    * @return ProductoDelPedido  - objeto ProductoDelPedido correspondiente a la fila que se especifica
    * @see ProductoDelPedido
    */        
    public ProductoDelPedido verProductoDelPedido(int fila) {
        try {
            return this.productosDelPedido.get(fila);
        }
        catch(IndexOutOfBoundsException e) {
            return null;
        }
    }
}
