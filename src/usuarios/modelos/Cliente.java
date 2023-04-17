/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.modelos;

import java.util.ArrayList;
import java.util.List;
import pedidos.modelos.Pedido;

/**
 *
 * @author root
 */
public class Cliente extends Usuario {
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(String correo, String clave, String apellido, String nombre) {
        super(correo, clave, apellido, nombre);
    }

    @Override
    public Perfil verPerfil() {
        return Perfil.CLIENTE;
    }
    
    public void agregarPedido(Pedido pedido) {
        if (pedido != null && !this.pedidos.contains(pedido))
            this.pedidos.add(pedido);
    }
    
    public void cancelarPedido(Pedido pedido) {
        if (pedido != null && this.pedidos.contains(pedido))
            this.pedidos.remove(pedido);
    }

    @Override
    public List<Pedido> verPedidos() {
        return this.pedidos;
    }
    
    
    
}
