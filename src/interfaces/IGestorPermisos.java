/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;

/**
 *
 * @author root
 */
public interface IGestorPermisos {
    
/**
     * Determina si al usuario logueado se le habilita o no el botón para la gestión de usuarios
     * Este método es necesario para el mejor manejo de la interfaz gráfica, ya que
     * sirve para habilitar/deshabilitar el botón "Usuarios" en la ventana principal
     * @param usuarioLogueado usuario sobre el cual se quiere determinar la habilitación
     * @return boolean  - true o false según si el usuario tiene habilitado/deshabilitado el botón para gestionar usuarios
    */
    boolean habilitarBotonUsuarios(Usuario usuarioLogueado);
    
    /**
     * Determina si al usuario logueado se le habilita o no el botón para la gestión de productos
     * Este método es necesario para el mejor manejo de la interfaz gráfica, ya que
     * sirve para habilitar/deshabilitar el botón "Productos" en la ventana principal
     * @param usuarioLogueado usuario sobre el cual se quiere determinar la habilitación
     * @return boolean  - true o false según si el usuario tiene habilitado/deshabilitado el botón para gestionar productos
    */
    boolean habilitarBotonProductos(Usuario usuarioLogueado);

    /**
     * Determina si al usuario logueado se le habilita o no el botón para la gestión de pedidos
     * Este método es necesario para el mejor manejo de la interfaz gráfica, ya que
     * sirve para habilitar/deshabilitar el botón "Pedidos" en la ventana principal
     * @param usuarioLogueado usuario sobre el cual se quiere determinar la habilitación
     * @return boolean  - true o false según si el usuario tiene habilitado/deshabilitado el botón para gestionar pedidos
    */
    boolean habilitarBotonPedidos(Usuario usuarioLogueado);
    
    /**
     * Dado un usuario, determina según su perfil, los tipos de usuarios (perfiles) que puede crear
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @return List<Perfil>  - lista con los tipos de usuario que puede crear el usuario especificado
    */    
    List<Perfil> crearUsuarios(Usuario usuarioLogueado);
    
    /**
     * Determina si el usuario logueado puede modificar otro usuario según el perfil de este último
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @param usuarioAModificar usuario que se quiere modificar
     * @return boolean  - true/false según el usuario pueda o no modificar al usuario con el perfil especificado
    */    
    boolean modificarUsuarios(Usuario usuarioLogueado, Usuario usuarioAModificar);
    
    /**
     * Determina si el usuario logueado puede borrar otro usuario según el perfil de este último
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @param usuarioABorrar usuario que se quiere borrar
     * @return boolean  - true/false según el usuario pueda o no borrar al usuario con el perfil especificado
    */    
    boolean borrarUsuarios(Usuario usuarioLogueado, Usuario usuarioABorrar);
    
    /**
     * Determina si el usuario logueado puede listar usuarios
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @return boolean  - true/false según el usuario pueda o no listar usuarios
    */    
    boolean verUsuarios(Usuario usuarioLogueado);
    
    /**
     * Determina si el usuario logueado puede buscar usuarios
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @return boolean  - true/false según el usuario pueda o no buscar usuarios
    */ 
    boolean buscarUsuarios(Usuario usuarioLogueado);
    
    /**
     * Determina si el usuario logueado puede crear productos, según su perfil
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @return boolean  - true/false según el usuario pueda o no crear productos
    */    
    boolean crearProductos(Usuario usuarioLogueado);
    
    /**
     * Determina si el usuario logueado puede modificar productos, según su perfil
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @return boolean  - true/false según el usuario pueda o no modificar productos
    */    
    boolean modificarProductos(Usuario usuarioLogueado);
    
    /**
     * Determina si el usuario logueado puede borrar productos, según su perfil
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @return boolean  - true/false según el usuario pueda o no borrar productos
    */    
    boolean borrarProductos(Usuario usuarioLogueado);
    
    /**
     * Determina si el usuario usuarioLogueado puede o no ver el menú de productos, según su perfil
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @return boolean  - true/false según el usuario pueda o no ver el menú de productos
    */    
    boolean menu(Usuario usuarioLogueado); 
    
    /**
     * Determina si el usuario logueado puede buscar productos
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @return boolean  - true/false según el usuario pueda o no buscar productos
    */ 
    boolean buscarProductos(Usuario usuarioLogueado);
    
    /**
     * Determina si el usuario logueado puede crear pedidos, según su perfil
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @return boolean  - true/false según el usuario pueda o no crear pedidos
    */    
    boolean crearPedidos(Usuario usuarioLogueado);
    
    /**
     * Determina si el usuario logueado puede modificar pedidos, según su perfil
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @return boolean  - true/false según el usuario pueda o no modificar pedidos
    */    
    boolean modificarPedidos(Usuario usuarioLogueado);
    
    /**
     * Determina si el usuario logueado puede cambiar el estado de los pedidos, según su perfil
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @return boolean  - true/false según el usuario pueda o no cambiar el estado de los pedidos
    */        
    boolean cambiarEstadoPedidos(Usuario usuarioLogueado);
    
    /**
     * Determina si el usuario logueado puede borrar pedidos, según su perfil
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @return boolean  - true/false según el usuario pueda o no borrar pedidos
    */    
    boolean cancelarPedidos(Usuario usuarioLogueado);
    
    /**
     * Determina si el usuario usuarioLogueado puede listar pedidos, según su perfil
     * @param usuarioLogueado usuario sobre el cual se quiere determinar el permiso
     * @return boolean  - true/false según el usuario pueda o no listar pedidos
    */    
    boolean verPedidos(Usuario usuarioLogueado);        
}
