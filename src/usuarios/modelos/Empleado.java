/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.modelos;

import interfaces.IGestorPedidos;
import java.util.List;
import pedidos.modelos.GestorPedidos;
import pedidos.modelos.Pedido;

/**
 *
 * @author root
 */
public class Empleado extends Usuario {

    public Empleado(String correo, String clave, String apellido, String nombre) {
        super(correo, clave, apellido, nombre);
    }

    @Override
    public Perfil verPerfil() {
        return Perfil.EMPLEADO;
    }

    @Override
    public List<Pedido> verPedidos() {
        IGestorPedidos gp = GestorPedidos.instanciar();
        return gp.verPedidos(this);
    }
    
    
    
}
