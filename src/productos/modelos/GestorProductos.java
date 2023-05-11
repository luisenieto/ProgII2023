/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos.modelos;

import interfaces.IGestorPedidos;
import interfaces.IGestorProductos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import pedidos.modelos.GestorPedidos;

/**
 *
 * @author root
 */
public class GestorProductos implements IGestorProductos {
    private static GestorProductos gestor;
    
    private List<Producto> productos = new ArrayList<>();
    
    /**
     * Constructor
     */
    private GestorProductos() {
        
    }
    
    /**
     * Método estático que permite crear una única instancia de GestorProductos
     * @return GestorProductos
    */
    public static GestorProductos instanciar() {
        if (gestor == null) {
            gestor = new GestorProductos();
        }
        return gestor;
    }

    @Override
    public String crearProducto(int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        Producto producto;
                
        String resultadoValidacion = this.validarDatos(codigo, descripcion, precio, categoria, estado);
        if (!resultadoValidacion.equals(VALIDACION_EXITO))
            return resultadoValidacion;                   
        else {
            producto = new Producto(codigo, descripcion, categoria, estado, precio);
            if (!this.productos.contains(producto)) { //no existe el producto
                this.productos.add(producto);
                return EXITO;
            }
            else //ya existe un producto con ese código
                return PRODUCTOS_DUPLICADOS;
        }
    }
    
    /**
     * Valida que los datos del producto sean correctos
     * @param codigo código del producto
     * @param descripcion descripción del producto
     * @param precio precio del producto
     * @param categoria categoría del producto
     * @param estado estado del producto
     * @return String  - cadena con el resultado de la operación (ERROR_CODIGO || ERROR_DESCRIPCION || ERROR_PRECIO || ERROR_CATEGORIA || ERROR_ESTADO || VALIDACION_EXITO)
    */
    private String validarDatos(int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        if (!this.validarCodigo(codigo))
            return ERROR_CODIGO;

        if (!this.validarDescripcion(descripcion))
            return ERROR_DESCRIPCION;
        
        if (!this.validarPrecio(precio))
                return ERROR_PRECIO;

        if (!this.validarCategoria(categoria))
            return ERROR_CATEGORIA;

        if (!this.validarEstado(estado))
            return ERROR_ESTADO;
        
        return VALIDACION_EXITO;
    }

    /**
     * Valida que el código del producto sea correcto
     * El código es correcto si es positivo
     * @param codigo código del producto
     * @return boolean  - true si el código del producto es correcto, false en caso contrario
    */
    private boolean validarCodigo(int codigo) {
        return codigo > 0;
    }
        
    /**
     * Valida que la descripción del producto sea correcta
     * La descripción es correcta si no es nula ni una cadena vacía
     * @param descripcion descripción del producto
     * @return boolean  - true si la descripción del producto es correcta, false en caso contrario
    */
    private boolean validarDescripcion(String descripcion) {
        return (descripcion != null) && (!descripcion.trim().isEmpty());
    }
    
    /**
     * Valida que el precio del producto sea correcto
     * El precio es correcto si es positivo
     * @param precio precio del producto
     * @return boolean  - true si el precio del producto es correcto, false en caso contrario
    */
    private boolean validarPrecio(float precio) {
        return precio > 0;
    }  
    
    /**
     * Valida que la categoría del producto sea correcta
     * La categoría es correcta si no es nula
     * @param categoria categoría del producto
     * @return boolean  - true si la categoría del producto es correcta, false en caso contrario
    */
    private boolean validarCategoria(Categoria categoria) {
        return categoria != null;
    }
    
    /**
     * Valida que el estado del producto sea correcto
     * El estado es correcto si no es nulo
     * @param estado estado del producto
     * @return boolean  - true si el estado del producto es correcto, false en caso contrario
    */
    private boolean validarEstado(Estado estado) {
        return estado != null;
    }

    @Override
    public String modificarProducto(Producto productoAModificar, int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        if (!this.existeEsteProducto(productoAModificar))
            return PRODUCTO_INEXISTENTE;
               
        String resultadoValidacion = this.validarDatos(codigo, descripcion, precio, categoria, estado);
        if (!resultadoValidacion.equals(VALIDACION_EXITO))
            return resultadoValidacion;
        else {
            int posicion = this.productos.indexOf(productoAModificar);
            productoAModificar.asignarDescripcion(descripcion);
            productoAModificar.asignarPrecio(precio);
            productoAModificar.asignarCategoria(categoria);
            productoAModificar.asignarEstado(estado);
            this.productos.set(posicion, productoAModificar);
            return EXITO;
        }
    }

    @Override
    public List<Producto> menu() {
        Comparator<Producto> cmp = (p1, p2) -> {
            if (p1.verCategoria() == p2.verCategoria())
                return p1.verDescripcion().compareToIgnoreCase(p2.verDescripcion());
            else
                return p1.verCategoria().compareTo(p2.verCategoria());
        };
        Collections.sort(this.productos, cmp);
        return this.productos;
    }

    @Override
    public ArrayList<Producto> buscarProductos(String descripcion) {
        ArrayList<Producto> productosBuscados = new ArrayList<>();
        if (descripcion != null) { 
            for(Producto producto : this.productos) {
                if (producto.verDescripcion().toLowerCase().contains(descripcion.toLowerCase()))
                    productosBuscados.add(producto);
            }            
        }
        Comparator<Producto> cmp = (p1, p2) -> {
            if (p1.verCategoria() == p2.verCategoria())
                return p1.verDescripcion().compareToIgnoreCase(p2.verDescripcion());
            else
                return p1.verCategoria().compareTo(p2.verCategoria());
        };
        Collections.sort(productosBuscados, cmp);
        return productosBuscados;    
    }

    @Override
    public String borrarProducto(Producto producto) {
        if (this.existeEsteProducto(producto)) {        
            IGestorPedidos gp = GestorPedidos.instanciar();
            if (gp.hayPedidosConEsteProducto(producto))  //hay al menos un pedido con este producto
                return PEDIDO_CON_PRODUCTO;
            else { //no hay pedidos con este producto
                this.productos.remove(producto);
                return EXITO;
            }
        }
        else
            return PRODUCTO_INEXISTENTE;
    }

    @Override
    public boolean existeEsteProducto(Producto producto) {
        return this.productos.contains(producto);
    }

    @Override
    public List<Producto> verProductosPorCategoria(Categoria categoria) {
        ArrayList<Producto> productosBuscados = new ArrayList<>();
        for(Producto p : this.productos) {
            if (p.verCategoria().equals(categoria))
                productosBuscados.add(p);
        }
        Comparator<Producto> cmp = (p1, p2) -> p1.verDescripcion().compareToIgnoreCase(p2.verDescripcion());            
        Collections.sort(productosBuscados, cmp);
        return productosBuscados;
    }

    @Override
    public Producto obtenerProducto(Integer codigo) {
        if (codigo == null)
            return null;
        else {
            for(Producto p : this.productos) {
                if (p.verCodigo() == codigo)
                    return p;
            }
            return null;
        }
    }
    
    
    
}
