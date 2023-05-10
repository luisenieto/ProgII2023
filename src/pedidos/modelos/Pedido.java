/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import productos.modelos.Producto;
import usuarios.modelos.Cliente;

/**
 *
 * @author root
 */
public class Pedido {
    private int numero;
    private LocalDateTime fechaYHora;
    private ArrayList<ProductoDelPedido> productosDelPedido = new ArrayList<>();
    private Cliente cliente;
    private Estado estado;
    
    /**
     * Constructor (sirve para cuando se crea un pedido, donde el estado es Estado.Creando)
     * @param numero número del pedido
     * @param fechaYHora fecha y hora del pedido
     * @param productosDelPedido productos del pedido
     * @param cliente cliente del pedido
    */    
    public Pedido(int numero, LocalDateTime fechaYHora, ArrayList<ProductoDelPedido> productosDelPedido, Cliente cliente) {
        this(numero, fechaYHora, productosDelPedido, Estado.CREADO, cliente);
    }
    
    /**
     * Constructor (sirve para cuando se leen los pedidos del archivo)
     * @param numero número del pedido
     * @param fechaYHora fecha y hora del pedido
     * @param productosDelPedido productos del pedido
     * @param estado estado del pedido
     * @param cliente cliente del pedido
    */
    public Pedido(int numero, LocalDateTime fechaYHora, ArrayList<ProductoDelPedido> productosDelPedido, Estado estado, Cliente cliente) {
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

    public LocalDate verFecha() {
        return this.fechaYHora.toLocalDate();
    }
    
    public LocalTime verHora() {
        return this.fechaYHora.toLocalTime();
    }

    public Cliente verCliente() {
        return cliente;
    }

    public ArrayList<ProductoDelPedido> verProductosDelPedido() {
        return this.productosDelPedido;
    }

    public void asignarProductosDelPedido(ArrayList<ProductoDelPedido> productos) {
        this.productosDelPedido = productos;
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
    
    public void mostrar() {
        System.out.println("Número: " + this.numero);
        System.out.println("Fecha: " + this.fechaACadena(this.verFecha()));
        System.out.println("Hora: " + this.horaACadena(this.verHora()));
        System.out.println("\tProducto \tCantidad");
        System.out.println("\t======== \t========");
        for(ProductoDelPedido pdp : this.productosDelPedido) {
            Producto producto = pdp.verProducto();
            int cantidad = pdp.verCantidad();
            System.out.println("\t" + producto + "\t" + cantidad);
        }
        System.out.println("Cliente: " + this.cliente.verApellido() + ", " + this.cliente.verNombre());
        System.out.println("Estado: " + this.estado);
    }
    
    private String fechaACadena(LocalDate fecha) {
        String patron = "dd/MM/yyyy";
        String fechaEnCadena = fecha.format(DateTimeFormatter.ofPattern(patron));
        return fechaEnCadena;
    }
    
    private String horaACadena(LocalTime hora) {
        String patron = "hh:mm";
        String horaEnCadena = hora.format(DateTimeFormatter.ofPattern(patron));
        return horaEnCadena;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.numero;
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
        final Pedido other = (Pedido) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }
    
    
}
