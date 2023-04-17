/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import pedidos.modelos.Estado;
import pedidos.modelos.Pedido;
import pedidos.modelos.ProductoDelPedido;
import productos.modelos.Producto;
import usuarios.modelos.Cliente;
import usuarios.modelos.Usuario;

/**
 *
 * @author root
 */
public interface IGestorPedidos {
    //Constantes para las operaciones de E/S
    public static final String CREACION_ERROR = "Error al crear el archivo de pedidos";
    public static final String CREACION_OK = "Se pudo crear el archivo de pedidos";
    public static final String LECTURA_ERROR = "Error al leer los pedidos";
    public static final String LECTURA_OK = "Se pudieron leer los pedidos";
    public static final String ESCRITURA_OK = "Se pudieron guardar los pedidos";  
    public static final String ESCRITURA_ERROR = "Error al guardar los pedidos";
    
    //Constantes para el ABM de pedidos
    public static final String EXITO = "Pedido creado/modificado/cancelado con éxito";
    public static final String ERROR_FECHA = "La fecha del pedido es incorrecta";  
    public static final String ERROR_HORA = "La hora del pedido es incorrecta";  
    public static final String ERROR_PRODUCTOS_DEL_PEDIDO = "El pedido no tiene productos";  
    public static final String ERROR_CLIENTE = "El pedido no tiene un cliente"; 
    public static final String ERROR_ESTADO = "El pedido no tiene un estado"; 
    public static final String ERROR_CANCELAR = "No se puede cancelar el pedido en este estado"; 
    public static final String ERROR_PERMISOS = "No se tienen los permisos para realizar esta funcionalidad";  
    public static final String PEDIDOS_DUPLICADOS = "Ya existe un pedido con ese número";
    public static final String VALIDACION_EXITO = "El pedido tiene los datos correctos";
    
    /**
     * Devuelve true si hay al menos un pedido con el usuario especificado, false en caso contrario
     * @param usuario usuario a buscar
     * @return boolean  - true si hay al menos un pedido con el usuario especificado, false en caso contrario
    */
    public boolean hayPedidosConEsteUsuario(Usuario usuario);
    
    /**
     * Devuelve true si hay al menos un pedido con el producto especificado, false en caso contrario
     * @param producto producto a buscar
     * @return boolean  - true si hay al menos un pedido con el producto especificado, false en caso contrario
    */
    public boolean hayPedidosConEsteProducto(Producto producto);
    
    /**
     * Crea un nuevo pedido
     * Que se pueda crear un pedido, o no, depende de los permisos del usuario logueado
     * @param usuarioLogueado usuario actualmente logueado
     * @param fecha fecha del pedido
     * @param hora hora del pedido
     * @param productosDelPedido lista con los productos y sus cantidades
     * @param cliente cliente del pedido
     * @return cadena con el resultado de la operación (EXITO || ESCRITURA_ERROR || PEDIDOS_DUPLICADOS || ERROR_FECHA || ERROR_HORA || ERROR_PRODUCTOS_DEL_PEDIDO || ERROR_CLIENTE)
    */                                                                    
    public String crearPedido(Usuario usuarioLogueado, LocalDate fecha, LocalTime hora, List<ProductoDelPedido> productosDelPedido, Cliente cliente);

    
    /**
     * Modificar un pedido
     * Que se pueda modificar un pedido, o no, depende de los permisos del usuario logueado
     * @param usuarioLogueado usuario actualmente logueado
     * @param pedidoAModificar pedido a modificar
     * @param fecha fecha del pedido
     * @param hora hora del pedido
     * @param productosDelPedido lista con los productos y sus cantidades
     * @param cliente cliente del pedido
     * @return cadena con el resultado de la operación (EXITO || ESCRITURA_ERROR || PEDIDOS_DUPLICADOS || ERROR_FECHA || ERROR_HORA || ERROR_PRODUCTOS_DEL_PEDIDO || ERROR_CLIENTE)
    */                                                                    
    public String modificarPedido(Usuario usuarioLogueado, Pedido pedidoAModificar, LocalDate fecha, LocalTime hora, List<ProductoDelPedido> productosDelPedido, Cliente cliente);
    
    /**
     * Devuelve todos los pedidos ordenados por ....
     * Si el usuario logueado es Encargado o Empleado, devuelve todos los pedidos
     * Si el usuario logueado es Cliente, devuelve sólo los suyos
     * Este método es necesario para las clases ....
     * @param usuarioLogueado usuario actualmente logueado
     * @return List<Pedido>  - lista de pedidos ordenados por ...
    */                                                                           
    public List<Pedido> verPedidos(Usuario usuarioLogueado);  
    
    /**
     * Busca si existen pedidos con ....
     * Este método es usado por la clase ModeloTablaPedidos
     * @param descripcion descripción del producto a buscar
     * @return List<Pedido>  - lista de pedidos, ordenados ....
    */                                                                            
//    public List<Pedido> buscarPedidos(/*String descripcion*/); 
    
    /**
     * Cancela un pedido 
     * Que se pueda cancelar un pedido, o no, depende que el estado del mismo sea Estado.CREANDO y de los permisos del usuario actualmente logueado
     * @param usuarioLogueado usuario actualmente logueado
     * @param pedido pedido a cancelar
     * @return String  - cadena con el resultado de la operación (EXITO | ESCRITURA_ERROR | ERROR_CANCELAR)
    */
    public String cancelarPedido(Usuario usuarioLogueado, Pedido pedido);
    
    /**
     * Devuelve true si existe el producto especificado, false en caso contrario
     * @param producto producto a buscar
     * @return boolean  - true si existe el producto especificado, false en caso contrario
    */
//    public boolean existeEsteProducto(Producto producto);
    
}
