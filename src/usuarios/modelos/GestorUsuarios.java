/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.modelos;

import interfaces.IGestorPedidos;
import interfaces.IGestorUsuarios;
import java.util.ArrayList;
import pedidos.modelos.GestorPedidos;

/**
 *
 * @author root
 */
public class GestorUsuarios implements IGestorUsuarios {
    private static GestorUsuarios gestor;
    
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    
   
    /**
     * Constructor
    */
    private GestorUsuarios() {
    }
    
    /**
     * Método estático que permite crear una única instancia de GestorUsuarios
     * @return GestorUsuarios
    */
    public static GestorUsuarios instanciar() {
        if (gestor == null) {
            gestor = new GestorUsuarios();
        }
        return gestor;
    }    

    @Override
    public String crearUsuario(String correo, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida) {
        Usuario usuario;
        
        String resultadoValidacion = this.validarDatos(correo, apellido, nombre, perfil, clave, claveRepetida);
        if (!resultadoValidacion.equals(VALIDACION_EXITO))
            return resultadoValidacion;               
        else {
            switch(perfil) {
                case ENCARGADO :
                    usuario = new Encargado(correo, clave, apellido, nombre);
                    break;
                case EMPLEADO :
                    usuario = new Empleado(correo, clave, apellido, nombre);
                    break;
                default:
                    usuario = new Cliente(correo, clave, apellido, nombre);
                    break;
            }
            if (!this.usuarios.contains(usuario)) { //no existe el usuario
                this.usuarios.add(usuario);
                return EXITO;
            }
            else //ya existe un usuario con ese correo
                return USUARIOS_DUPLICADOS;
        }
    }
    
    /**
     * Valida que los datos del usuario sean correctos
     * @param correo correo del usuario
     * @param apellido apellido del usuario
     * @param nombre nombre del usuario
     * @param perfil perfil del usuario
     * @param clave clave del usuario
     * @param claveRepetida clave repetida del usuario
     * @return String  - cadena con el resultado de la operación (ERROR_CORREO || ERROR_APELLIDO || ERROR_NOMBRE || ERROR_PERFIL || ERROR_CLAVES || VALIDACION_EXITO)
    */
    private String validarDatos(String correo, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida) {
        if (!this.validarCorreo(correo))
            return ERROR_CORREO;

        if (!this.validarApellido(apellido))
                return ERROR_APELLIDO;
    
        if (!this.validarNombre(nombre))
            return ERROR_NOMBRE;

        if (!this.validarPerfil(perfil))
            return ERROR_PERFIL;

        if (!this.validarClaves(clave, claveRepetida))
            return ERROR_CLAVES;
        
        return VALIDACION_EXITO;
    }
    
    /**
     * Valida que el correo del usuario tengan el caracter "@"
     * @param correo dni del usuario
     * @return boolean  - true si el correo del usuario tiene el caracter "@"
     */
    private boolean validarCorreo(String correo) {
        return (correo != null) && (correo.contains("@"));
    }
    
    /**
     * Valida que el apellido del usuario sea correcto
     * El apellido es correcto si no es nulo ni una cadena vacía
     * @param apellido apellido del usuario
     * @return boolean  - true si el apellido del usuario es correcto, false en caso contrario
     */
    private boolean validarApellido(String apellido) {
        return (apellido != null) && (!apellido.trim().isEmpty());
    }
    
    /**
     * Valida que el nombre del usuario sea correcto
     * El nombre es correcto si no es nulo ni una cadena vacía
     * @param nombre nombre del usuario
     * @return boolean  - true si el nombre del usuario es correcto, false en caso contrario
    */
    private boolean validarNombre(String nombre) {
        return (nombre != null) && (!nombre.trim().isEmpty());
    }
    
    /**
     * Valida que el perfil del usuario sea correcto
     * El perfil es correcto si no es nulo
     * @param perfil perfil del usuario
     * @return boolean  - true si el perfil del usuario es correcto, false en caso contrario
    */
    private boolean validarPerfil(Perfil perfil) {
        return perfil != null;
    }

    /**
     * Valida que las 2 claves para el usuario sean correctas (no vacías e iguales)
     * @param clave clave del usuario
     * @param claveRepetida clave (repetida) del usuario
     * @return boolean  - true si las 2 claves para el usuario son correctas, false en caso contrario
    */
    private boolean validarClaves(String clave, String claveRepetida) {
        return (clave != null) && 
                (!clave.trim().isEmpty()) && 
                (claveRepetida != null) && 
                (!claveRepetida.trim().isEmpty()) && 
                (clave.equals(claveRepetida));
    }

    @Override
    public ArrayList<Usuario> verUsuarios() {
        return this.usuarios;
    }

    @Override
    public ArrayList<Usuario> buscarUsuarios(String apellido) {
        ArrayList<Usuario> usuariosBuscados = new ArrayList<>();        
        if (apellido != null) { 
            for(Usuario usuario : this.usuarios) {
                if (usuario.verApellido().toLowerCase().contains(apellido.toLowerCase()))
                    usuariosBuscados.add(usuario);
            }                
        }
        return usuariosBuscados;        
    }

    @Override
    public String borrarUsuario(Usuario usuario) {        
        if (this.existeEsteUsuario(usuario)) {
            if (usuario instanceof Cliente) {
                IGestorPedidos gp = GestorPedidos.instanciar();
                if (gp.hayPedidosConEsteCliente((Cliente)usuario))  //hay al menos un pedido con este cliente
                    return PEDIDO_CON_CLIENTE;
                else { //no hay pedidos con este cliente
                    this.usuarios.remove(usuario);
                    return EXITO;                
                }
            }
            else {
                this.usuarios.remove(usuario);
                return EXITO;
            }
        }
        else
            return USUARIO_INEXISTENTE;        
    }

    @Override
    public boolean existeEsteUsuario(Usuario usuario) {
        return this.usuarios.contains(usuario);    
    }

    @Override
    public Usuario obtenerUsuario(String correo) {
        if (correo == null)
            return null;
        else {
            for(Usuario u : this.usuarios) {
                if (u.verCorreo().equals(correo))
                    return u;
            }
            return null;
        }
    }
    
    
}
