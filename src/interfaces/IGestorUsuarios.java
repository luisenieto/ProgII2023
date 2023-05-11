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
public interface IGestorUsuarios {
    
    //Constantes para las operaciones de E/S
    public static final String LECTURA_ERROR = "Error al leer los usuarios";
    public static final String CREACION_ERROR = "Error al crear el archivo de usuarios";
    public static final String VALIDACION_ERROR = "No existe un usuario con ese correo y/o clave";
    public static final String LECTURA_OK = "Se pudieron leer los usuarios";
    public static final String CREACION_OK = "Se pudo crear el archivo de usuarios";
    public static final String ESCRITURA_OK = "Se pudieron guardar los usuarios";  
    public static final String ESCRITURA_ERROR = "Error al guardar los usuarios";
    
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
     * Dado un correo y una clave, valida si existe un usuario con esos datos
     * En caso de existir lo devuelve, y si no devuelve null
     * @param correo correo a buscar
     * @param clave clave a buscar
     * @return Usuario  - usuario correspondiente con el correo y clave especificados
    */
    Usuario validarUsuario(String correo, String clave);
    
    /**
     * Devuelve el usuario actualmente logueado
     * Si no hay un usuario logueado, devuelve null
     * @return Usuario  - usuario actualmente logueado
    */
    Usuario verUsuarioLogueado();
    
    /**
     * Cierra la sesión actual del usuario
    */
    void cerrarSesion();
    
    /**
     * Crea un nuevo usuario
     * Que se pueda crear un usuario, o no, depende de los permisos del usuario logueado
     * @param usuarioLogueado usuario actualmente logueado
     * @param correo correo del usuario
     * @param apellido apellido del usuario
     * @param nombre nombre del usuario
     * @param perfil perfil del usuario
     * @param clave clave del usuario
     * @param claveRepetida clave (repetida) del usuario
     * @return cadena con el resultado de la operación (EXITO || ESCRITURA_ERROR || USUARIOS_DUPLICADOS || ERROR_CORREO || ERROR_APELLIDO || ERROR_NOMBRE || ERROR_PERFIL || ERROR_CLAVES || ERROR_PERMISOS)
    */                                                                    
    public String crearUsuario(Usuario usuarioLogueado, String correo, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida);
    
    /**
     * Modifica un usuario
     * Que se pueda modificar un usuario, o no, depende de los permisos del usuario logueado
     * @param usuarioLogueado usuario actualmente logueado
     * @param usuarioAModificar usuario a modificar
     * @param correo nuevo correo del usuario
     * @param apellido nuevo apellido del usuario
     * @param nombre nuevo nombre del usuario
     * @param perfil nuevo perfil del usuario
     * @param clave nueva clave del usuario
     * @param claveRepetida nueva clave (repetida) del usuario
     * @return cadena con el resultado de la operación:
     *  EXITO || 
     *  ESCRITURA_ERROR || 
     *  USUARIOS_DUPLICADOS || 
     *  ERROR_CORREO || 
     *  ERROR_APELLIDO || 
     *  ERROR_NOMBRE || 
     *  ERROR_PERFIL || 
     *  ERROR_CLAVES || 
     *  ERROR_PERMISOS || 
     *  USUARIO_INEXISTENTE || 
     *  PERFIL_CAMBIO
    */                                                                    
    public String modificarUsuario(Usuario usuarioLogueado, Usuario usuarioAModificar, String correo, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida);
    
    /**
     * Devuelve todos los usuarios, ordenados alfabéticamente por apellido y luego alfabéticamente por el nombre
     * Que se devuelvan todos los usuarios, o una lista vacía, depende de los permisos del usuario logueado
     * Este método es necesario para las clases ModeloTablaUsuarios
     * @param usuarioLogueado usuario actualmente logueado
     * @return List<Usuario>  - lista de usuarios ordenados por apellido,nombre
    */                                                                           
    public List<Usuario> verUsuarios(Usuario usuarioLogueado);
    
    /**
     * Busca si existen usuarios con el apellido especificado (total o parcialmente)
     * La lista de usuarios que coincidan con el apellido especificado se devuelve ordenada alfabéticamente por apellido y luego alfabéticamente por el nombre
     * Que se busquen los usuarios con el apellido especificado, o se devuelva una lista vacía, depende de los permisos del usuario logueado
     * Este método es usado por la clase ModeloTablaUsuarios
     * @param usuarioLogueado usuario actualmente logueado
     * @param apellido apellido del usuario a buscar
     * @return List<Usuario>  - lista de usuarios, ordenados por apellidos y nombres, cuyos apellidos coincidan con el especificado
    */                                                                            
    public List<Usuario> buscarUsuarios(Usuario usuarioLogueado, String apellido); 
    
    /**
     * Borra un usuario siempre y cuando el usuario logueado pueda hacerlo y no haya pedidos con el mismo
     * @param usuarioLogueado usuario actualmente logueado
     * @param usuario usuario a borrar
     * @return String  - cadena con el resultado de la operación (EXITO | ESCRITURA_ERROR | USUARIO_INEXISTENTE | PEDIDO_CON_USUARIO | ERROR_PERMISOS)
    */
    public String borrarUsuario(Usuario usuarioLogueado, Usuario usuario);
    
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
