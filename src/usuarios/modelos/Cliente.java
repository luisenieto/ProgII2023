/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.modelos;

import java.util.ArrayList;
import pedidos.modelos.Pedido;

/**
 *
 * @author root
 */
public class Cliente extends Usuario {
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    public Cliente(String correo, String clave, String apellido, String nombre) {
        super(correo, clave, apellido, nombre);
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
    public ArrayList<Pedido> verPedidos() {
        return this.pedidos;
    }
    
    
}
