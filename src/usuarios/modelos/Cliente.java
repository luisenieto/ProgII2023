/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    
    /**
     * Agrega el pedido al conjunto de pedidos del cliente
     * Si el pedido no está, lo agrega, y si está lo reemplaza
     * @param pedido pedido a agregar
     */
    public void agregarPedido(Pedido pedido) {
        if (pedido != null) {
            if (!this.pedidos.contains(pedido)) //no está el pedido
                this.pedidos.add(pedido);
            else { //ya está el pedido
                int posicion = this.pedidos.indexOf(pedido);
                this.pedidos.set(posicion, pedido);
            }
        }
    }
    
    public void cancelarPedido(Pedido pedido) {
        if (pedido != null && this.pedidos.contains(pedido))
            this.pedidos.remove(pedido);
    }

    @Override
    public List<Pedido> verPedidos() {
        Comparator<Pedido> cmp = (p1, p2) -> p2.verNumero() - p1.verNumero();
        Collections.sort(this.pedidos, cmp);
        return this.pedidos;
    }
    
    
    
}
