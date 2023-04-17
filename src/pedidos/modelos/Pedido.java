/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import usuarios.modelos.Cliente;

/**
 *
 * @author root
 */
public class Pedido {
    private int numero;
    private LocalDateTime fechaYHora;
    private List<ProductoDelPedido> productosDelPedido = new ArrayList<>();
    private Cliente cliente;
    private Estado estado;    

    /**
     * Constructor (sirve para cuando se crea un pedido, donde el estado es Estado.Creando)
     * @param numero número del pedido
     * @param fechaYHora fecha y hora del pedido
     * @param productosDelPedido productos del pedido
     * @param cliente cliente del pedido
    */    
    public Pedido(int numero, LocalDateTime fechaYHora, List<ProductoDelPedido> productosDelPedido, Cliente cliente) {
        this(numero, fechaYHora, productosDelPedido, Estado.CREANDO, cliente);
    }
    
    /**
     * Constructor (sirve para cuando se leen los pedidos del archivo)
     * @param numero número del pedido
     * @param fechaYHora fecha y hora del pedido
     * @param productosDelPedido productos del pedido
     * @param estado estado del pedido
     * @param cliente cliente del pedido
    */
    public Pedido(int numero, LocalDateTime fechaYHora, List<ProductoDelPedido> productosDelPedido, Estado estado, Cliente cliente) {
        this.numero = numero;
        this.fechaYHora = fechaYHora;
        this.productosDelPedido = productosDelPedido;
        this.cliente = cliente;
        this.estado = estado;
    }

    public int verNumero() {
        return numero;
    }

    public void asignarNumero(int numero) {
        this.numero = numero;
    }

//    public LocalDateTime verFechaYHora() {
//        return fechaYHora;
//    }
//
//    public void asignarFechaYHora(LocalDateTime fechaYHora) {
//        this.fechaYHora = fechaYHora;
//    }
    
    public LocalDate verFecha() {
        return this.fechaYHora.toLocalDate();
    }
    
    public LocalTime verHora() {
        return this.fechaYHora.toLocalTime();
    }

    public List<ProductoDelPedido> verProductosDelPedido() {
        return this.productosDelPedido;
    }

    public void asignarProductosDelPedido(List<ProductoDelPedido> productos) {
        this.productosDelPedido = productos;
    }

    public Cliente verCliente() {
        return cliente;
    }

    public void asignarCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Estado verEstado() {
        return estado;
    }

    public void asignarEstado(Estado estado) {
        this.estado = estado;
    }
    
    
    
}
