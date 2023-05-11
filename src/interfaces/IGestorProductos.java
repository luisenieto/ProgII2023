/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import productos.modelos.Categoria;
import productos.modelos.Estado;
import productos.modelos.Producto;

/**
 *
 * @author root
 */
public interface IGestorProductos {    
    //Constantes para el ABM de productos
    public static final String EXITO = "Producto creado/modificado/borrado con éxito";
    public static final String ERROR_CODIGO = "El código del producto es incorrecto";  
    public static final String ERROR_DESCRIPCION = "La descripción del producto es incorrecta";  
    public static final String ERROR_PRECIO = "El precio del producto es incorrecto";  
    public static final String ERROR_CATEGORIA = "La categoría del producto es incorrecta";  
    public static final String ERROR_ESTADO = "El precio del producto es incorrecto";  
    public static final String ERROR_PERMISOS = "No se tienen los permisos para realizar esta funcionalidad";  
    public static final String PRODUCTOS_DUPLICADOS = "Ya existe un producto con ese código";
    public static final String PRODUCTO_INEXISTENTE = "No existe el producto especificado";   
    public static final String PEDIDO_CON_PRODUCTO = "No se puede borrar el producto porque hay pedidos con el mismo";
    public static final String VALIDACION_EXITO = "Los datos del producto son correctos";
    
    /**
     * Crea un nuevo producto
     * Que se pueda crear un producto, o no, depende de los permisos del usuario logueado
     * @param codigo código del producto
     * @param descripcion descripción del producto
     * @param precio precio del producto
     * @param categoria categoría del producto
     * @param estado estado del producto
     * @return cadena con el resultado de la operación (EXITO || ESCRITURA_ERROR || PRODUCTOS_DUPLICADOS || ERROR_CODIGO || ERROR_DESCRIPCION || ERROR_PRECIO || ERROR_CATEGORIA || ERROR_ESTADO || ERROR_PERMISOS)
    */                                                                    
    public String crearProducto(int codigo, String descripcion, float precio, Categoria categoria, Estado estado);
    
    /**
     * Modifica un producto
     * Que se pueda modificar un producto, o no, depende de los permisos del usuario logueado
     * @param productoAModificar producto a modificar
     * @param codigo código del producto
     * @param descripcion descripción del producto
     * @param precio precio del producto
     * @param categoria categoría del producto
     * @param estado estado del producto
     * @return cadena con el resultado de la operación:
     *  EXITO || 
     *  ESCRITURA_ERROR || 
     *  USUARIOS_DUPLICADOS || 
     *  ERROR_CODIGO || 
     *  ERROR_DESCRIPCION ||
     *  ERROR_CATEGORIA || 
     *  ERROR_ESTADO || 
     *  PRODUCTO_INEXISTENTE || 
     *  ERROR_PERMISOS 
    */
    public String modificarProducto(Producto productoAModificar, int codigo, String descripcion, float precio, Categoria categoria, Estado estado);
    
    /**
     * Devuelve todos los productos 
     * Que se devuelvan todos los productos, o una lista vacía, depende de los permisos del usuario logueado
     * Este método es necesario para las clases ModeloTablaProductos
     * @return ArrayList<Producto>  - lista de productos 
    */                                                                           
    public ArrayList<Producto> menu();  
    
    /**
     * Busca si existen productos con la descripción especificada (total o parcialmente)
     * Este método es usado por la clase ModeloTablaProductos
     * @param descripcion descripción del producto a buscar
     * @return ArrayList<Producto>  - lista de productos
    */                                                                            
    public ArrayList<Producto> buscarProductos(String descripcion); 
    
    /**
     * Borra un producto siempre y cuando no haya pedidos con el mismo
     * @param producto producto a borrar
     * @return String  - cadena con el resultado de la operación (EXITO | ESCRITURA_ERROR | PRODUCTO_INEXISTENTE | PEDIDO_CON_PRODUCTO | ERROR_PERMISOS)
    */
    public String borrarProducto(Producto producto);
    
    /**
     * Devuelve true si existe el producto especificado, false en caso contrario
     * @param producto producto a buscar
     * @return boolean  - true si existe el producto especificado, false en caso contrario
    */
    public boolean existeEsteProducto(Producto producto);
    
    /**
     * Devuelve los productos con la categoría especificada
     * Si no hay productos con la categoría, devuelve una lista vacía
     * @param categoria categoría de los productos a buscar
     * @return ArrayList<Producto>  - lista de productos con la categoría especificada
    */                                                                            
    public ArrayList<Producto> verProductosPorCategoria(Categoria categoria); 
    
    /**
     * Obtiene el producto con el código especificado
     * Si no hay un producto con el código, devuelve null
     * @param codigo código del producto a buscar
     * @return Producto  - producto con el código especificado, o null
    */                                                                            
    public Producto obtenerProducto(Integer codigo);    
}
