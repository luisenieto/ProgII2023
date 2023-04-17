/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.modelos;

import java.util.List;
import java.util.Objects;
import pedidos.modelos.Pedido;

/**
 *
 * @author root
 */
public abstract class Usuario {
    private String correo;
    private String clave;
    private String apellido;
    private String nombre;

    public Usuario(String correo, String clave, String apellido, String nombre) {
        this.correo = correo;
        this.clave = clave;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public String verClave() {
        return this.clave;
    }

    public void asignarClave(String clave) {
        this.clave = clave;
    }

    public String verCorreo() {
        return this.correo;
    }

    public void asignarCorreo(String correo) {
        this.correo = correo;
    }

    public String verApellido() {
        return apellido;
    }

    public void asignarApellido(String apellido) {
        this.apellido = apellido;
    }

    public String verNombre() {
        return nombre;
    }

    public void asignarNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.correo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        return true;
    }

    /**
     * Muestra el perfil del usuario
     * @return Perfil  - perfil del usuario
    */
    public abstract Perfil verPerfil();
    
    /**
     * Muestra los pedidos del usuario
     * Si el usuario es encargado o empleado, muestra los pedidos de todos los clientes
     * Si el usuario es cliente, sólo muestra sus pedidos
     * @return List<Pedido>  - lista de pedidos
    */
    public abstract List<Pedido> verPedidos();
}
