/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;

/**
 *
 * @author root
 */
public interface IGestorUsuarios {
    //Constantes para el ABM de usuarios
    public static final String EXITO = "Usuario creado/modificado/borrado con éxito";
    public static final String ERROR_CORREO = "El correo del usuario es incorrecto";  
    public static final String ERROR_APELLIDO = "El apellido del usuario es incorrecto";  
    public static final String ERROR_NOMBRE = "El nombre del usuario es incorrecto";  
    public static final String ERROR_CLAVES = "Las claves especificadas no coinciden o son incorrectas";  
    public static final String ERROR_PERFIL = "El perfil del usuario es incorrecto";  
    public static final String PERFIL_CAMBIO = "El usuario no puede cambiarse de perfil";  
    public static final String ERROR_PERMISOS = "No se tienen los permisos para realizar esta funcionalidad";  
    public static final String USUARIOS_DUPLICADOS = "Ya existe un usuario con ese correo";    
    public static final String USUARIO_INEXISTENTE = "No existe el usuario especificado";   
    public static final String PEDIDO_CON_CLIENTE = "No se puede borrar el usuario porque hay pedidos con el mismo";
    public static final String VALIDACION_EXITO = "Los datos del usuario son correctos";
    
    /**
     * Crea un nuevo usuario
     * Que se pueda crear un usuario, o no, depende de los permisos del usuario logueado
     * @param correo correo del usuario
     * @param apellido apellido del usuario
     * @param nombre nombre del usuario
     * @param perfil perfil del usuario
     * @param clave clave del usuario
     * @param claveRepetida clave (repetida) del usuario
     * @return cadena con el resultado de la operación (EXITO || ESCRITURA_ERROR || USUARIOS_DUPLICADOS || ERROR_CORREO || ERROR_APELLIDO || ERROR_NOMBRE || ERROR_PERFIL || ERROR_CLAVES || ERROR_PERMISOS)
    */                                                                    
    public String crearUsuario(String correo, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida);
    
    /**
     * Devuelve todos los usuarios
     * Que se devuelvan todos los usuarios, o una lista vacía, depende de los permisos del usuario logueado
     * Este método es necesario para las clases ModeloTablaUsuarios
     * @return ArrayList<Usuario>  - lista de usuarios
    */                                                                           
    public ArrayList<Usuario> verUsuarios();
    
    /**
     * Busca si existen usuarios con el apellido especificado (total o parcialmente)
     * Que se busquen los usuarios con el apellido especificado, o se devuelva una lista vacía, depende de los permisos del usuario logueado
     * Este método es usado por la clase ModeloTablaUsuarios
     * @param apellido apellido del usuario a buscar
     * @return ArrayList<Usuario>  - lista de usuarios
    */                                                                            
    public ArrayList<Usuario> buscarUsuarios(String apellido); 
    
    /**
     * Borra un usuario siempre y cuando el usuario logueado pueda hacerlo y no haya pedidos con el mismo
     * @param usuario usuario a borrar
     * @return String  - cadena con el resultado de la operación (EXITO | ESCRITURA_ERROR | USUARIO_INEXISTENTE | PEDIDO_CON_USUARIO | ERROR_PERMISOS)
    */
    public String borrarUsuario(Usuario usuario);
    
    /**
     * Devuelve true si existe el usuario especificado, false en caso contrario
     * @param usuario usuario a buscar
     * @return boolean  - true si existe el usuario especificado, false en caso contrario
    */
    public boolean existeEsteUsuario(Usuario usuario);
    
    /**
     * Obtiene el usuario con el correo especificado
     * Si no hay un usuario con el correo, devuelve null
     * @param correo correo del usuario a buscar
     * @return Usuario  - usuario con el correo especificado, o null
    */                                                                            
    public Usuario obtenerUsuario(String correo);    
}
