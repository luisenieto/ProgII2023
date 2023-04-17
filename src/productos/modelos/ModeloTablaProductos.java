/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos.modelos;

import interfaces.IGestorProductos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import usuarios.modelos.Usuario;

/**
 *
 * @author root
 */
public class ModeloTablaProductos extends AbstractTableModel {
    //public static final String COLUMNA_CODIGO = "Código";    
    public static final String COLUMNA_CATEGORIA = "Categoria";
    public static final String COLUMNA_DESCRIPCION = "Descripción";
    public static final String COLUMNA_PRECIO = "Precio";
    
    //public static final String COLUMNA_ESTADO = "Estado";
    //constantes para los nombres de las columnas
    
    private List<Producto> productos = new ArrayList<>();
    //los datos los saca de GestorProductos
    
    private List<String> nombresColumnas = Arrays.asList(new String[] {COLUMNA_CATEGORIA, COLUMNA_DESCRIPCION, COLUMNA_PRECIO});       
    //colección para guardar los nombres de las columnas
    
    /**
    * Constructor
    * @param usuarioLogueado usuario actualmente logueado
    * @param descripcion descripción para filtrar la búsqueda de productos
    */                                                        
    public ModeloTablaProductos(Usuario usuarioLogueado, String descripcion) {
        IGestorProductos gp = GestorProductos.instanciar();
        this.productos = gp.buscarProductos(usuarioLogueado, descripcion);
    }
    
    /**
    * Constructor
    * @param usuarioLogueado usuario actualmente logueado
    */                                                        
    public ModeloTablaProductos(Usuario usuarioLogueado) {
        IGestorProductos gp = GestorProductos.instanciar();
        this.productos = gp.menu(usuarioLogueado);
    }
    
    /**
    * Obtiene el valor de la celda especificada
    * @param fila fila de la celda
    * @param columna columna de la celda
    * @return Object  - valor de la celda
    */                        
    @Override
    public Object getValueAt(int fila, int columna) {
        Producto producto = this.productos.get(fila);
        switch (columna) {
            case 0: return producto.verPrecio() < 0 ? producto.verCategoria() : "";
            case 1: return producto.verDescripcion();
            default: return producto.verPrecio() > 0 ? producto.verPrecio() : "";
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
        return this.productos.size();
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
    * Devuelve el Producto correspondiente a la fila especificada dentro de la tabla
    * Si se especifica una fila inválida devuelve null
    * @param fila fila dentro de la tabla
    * @return Producto  - objeto Producto correspondiente a la fila que se especifica
    * @see Producto
    */        
    public Producto verProducto(int fila) {
        try {
            return this.productos.get(fila);
        }
        catch(IndexOutOfBoundsException e) {
            return null;
        }
    }
}
