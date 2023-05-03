/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permisos.modelos;

import interfaces.IGestorPermisos;
import java.util.ArrayList;
import java.util.List;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;

/**
 *
 * @author root
 */
public class GestorPermisos implements IGestorPermisos {
    private static GestorPermisos gestor;
    
    private GestorPermisos() {
        
    }
    
    /**
     * Método estático que permite crear una única instancia de GestorPermisos
     * @return GestorUsuarios
    */
    public static GestorPermisos instanciar() {
        if (gestor == null) {
            gestor = new GestorPermisos();
        }
        return gestor;
    }

    //Si el usuario logueado es Cliente, tiene deshabilitado el botón de Usuarios
    //Si el usuario logueado es Empleado o Encargado, tiene habilitado el botón de Usuarios
    @Override
    public boolean habilitarBotonUsuarios(Usuario usuarioLogueado) {
        return !usuarioLogueado.verPerfil().equals(Perfil.CLIENTE);
    }

    @Override
    public boolean habilitarBotonProductos(Usuario usuarioLogueado) {
        return true;
    }

    @Override
    public boolean habilitarBotonPedidos(Usuario usuarioLogueado) {
        return true;
    }

    /**
     * Un encargado puede crear cualquier tipo de usuario
     * Un empleado sólo puede crear clientes
     * Un cliente no puede crear ningún tipo de usuario
     */
    @Override
    public List<Perfil> crearUsuarios(Usuario usuarioLogueado) {
        List<Perfil> perfiles = new ArrayList<>();
        switch(usuarioLogueado.verPerfil()) {
            case ENCARGADO :
                perfiles.add(Perfil.ENCARGADO);
                perfiles.add(Perfil.EMPLEADO);
                perfiles.add(Perfil.CLIENTE);
                break;
            case EMPLEADO :
                perfiles.add(Perfil.CLIENTE);
                break;
            default:
                break;
        }
        return perfiles;
    } 

    /**
     * Un encargado puede modificar cualquier tipo de usuario
     * Un empleado sólo puede modificar clientes
     * Un cliente no puede modificar ningún tipo de usuario
     */
    @Override
    public boolean modificarUsuarios(Usuario usuarioLogueado, Usuario usuarioAModificar) {
        switch(usuarioLogueado.verPerfil()) {
            case ENCARGADO : 
                return true;
            case EMPLEADO :
                return usuarioAModificar.verPerfil().equals(Perfil.CLIENTE);
            default:
                return false;
        }
    }

    /**
     * Un encargado puede borrar cualquier tipo de usuario, salvo a él mismo
     * Un empleado sólo puede borrar clientes
     * Un cliente no puede borrar ningún tipo de usuario
     */
    @Override
    public boolean borrarUsuarios(Usuario usuarioLogueado, Usuario usuarioABorrar) {
        switch(usuarioLogueado.verPerfil()) {
            case ENCARGADO : 
                return !usuarioLogueado.equals(usuarioABorrar);
            case EMPLEADO :
                return usuarioABorrar.verPerfil().equals(Perfil.CLIENTE);
            default:
                return false;
        }
    }

    /**
     * Un encargado puede listar cualquier tipo de usuario
     * Un empleado puede listar cualquier tipo de usuario
     * Un cliente no puede listar ningún tipo de usuario
    */
    @Override
    public boolean verUsuarios(Usuario usuarioLogueado) {
        return !usuarioLogueado.verPerfil().equals(Perfil.CLIENTE);
    }

    /**
     * Un encargado puede buscar usuarios
     * Un empleado puede buscar usuarios
     * Un cliente no puede buscar usuarios
    */
    @Override
    public boolean buscarUsuarios(Usuario usuarioLogueado) {
        return !usuarioLogueado.verPerfil().equals(Perfil.CLIENTE);
    }
    
    

    /**
     * Un encargado puede crear productos
     * Un empleado puede crear productos
     * Un cliente no puede crear productos
    */
    @Override
    public boolean crearProductos(Usuario usuarioLogueado) {
        switch(usuarioLogueado.verPerfil()) {
            case CLIENTE : 
                return false;
            default:
                return true;
        }
    }

    /**
     * Un encargado puede modificar productos
     * Un empleado puede modificar productos
     * Un cliente no puede modificar productos
    */
    @Override
    public boolean modificarProductos(Usuario usuarioLogueado) {
        switch(usuarioLogueado.verPerfil()) {
            case CLIENTE : 
                return false;
            default:
                return true;
        }
    }

    /**
     * Un encargado puede borrar productos
     * Un empleado puede borrar productos
     * Un cliente no puede borrar productos
    */
    @Override
    public boolean borrarProductos(Usuario usuarioLogueado) {
        switch(usuarioLogueado.verPerfil()) {
            case CLIENTE : 
                return false;
            default:
                return true;
        }
    }

    /**
     * Un encargado puede ver el menú
     * Un empleado puede ver el menú
     * Un cliente puede ver el menú
    */
    @Override
    public boolean menu(Usuario usuarioLogueado) {
        return true;
    } 

    /**
     * Un encargado puede buscar productos
     * Un empleado puede buscar productos
     * Un cliente puede buscar productos
    */
    @Override
    public boolean buscarProductos(Usuario usuarioLogueado) {
        return true;
    }
    
    

    /**
     * Un encargado no puede crear pedidos
     * Un empleado no puede crear pedidos
     * Un cliente sí puede crear pedidos
    */
    @Override
    public boolean crearPedidos(Usuario usuarioLogueado) {
        return usuarioLogueado.verPerfil() == Perfil.CLIENTE;        
    }

    /**
     * Un encargado sí puede modificar pedidos
     * Un empleado sí puede modificar pedidos
     * Un cliente sí puede modificar sus pedidos, los de otro cliente no
    */
    @Override
    public boolean modificarPedidos(Usuario usuarioLogueado) {
        return true;
    }

    /**
     * Un encargado sí puede modificar el estado de los pedidos
     * Un empleado sí puede modificar el estado de los pedidos
     * Un cliente no puede modificar el estado de los pedidos
    */
    @Override
    public boolean cambiarEstadoPedidos(Usuario usuarioLogueado) {
        return !(usuarioLogueado.verPerfil() == Perfil.CLIENTE);
    }

    
    /**
     * Un encargado sí puede cancelar pedidos
     * Un empleado sí puede cancelar pedidos
     * Un cliente sí puede cancelar sus pedidos, los de otro cliente no
    */
    @Override
    public boolean cancelarPedidos(Usuario usuarioLogueado) {
        return true;
    }

    /**
     * Un encargado sí puede ver los pedidos (todos)
     * Un empleado sí puede ver los pedidos (todos)
     * Un cliente sí puede ver sus pedidos, los de otro cliente no
    */
    @Override
    public boolean verPedidos(Usuario usuarioLogueado) {
        return true;
    }
    
    
}
