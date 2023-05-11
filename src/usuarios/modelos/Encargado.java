/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.modelos;

import interfaces.IGestorPedidos;
import java.util.ArrayList;
import java.util.Objects;
import pedidos.modelos.GestorPedidos;
import pedidos.modelos.Pedido;


/**
 *
 * @author root
 */
public class Encargado extends Usuario {

    public Encargado(String correo, String clave, String apellido, String nombre) {
        super(correo, clave, apellido, nombre);
    }    

    @Override
    public ArrayList<Pedido> verPedidos() {
        IGestorPedidos gp = GestorPedidos.instanciar();
        return gp.verPedidos();
    }
    
    
}
