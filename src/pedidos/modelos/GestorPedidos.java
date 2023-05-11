/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.modelos;

import interfaces.IGestorPedidos;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import productos.modelos.Producto;
import usuarios.modelos.Cliente;
import usuarios.modelos.Usuario;

/**
 *
 * @author root
 */
public class GestorPedidos implements IGestorPedidos {
    private static GestorPedidos gestor;
    
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    
    /**
     * Constructor
     */
    private GestorPedidos() {
        
    }
    
    /**
     * Método estático que permite crear una única instancia de GestorPedidos
     * @return GestorPedidos
    */
    public static GestorPedidos instanciar() {
        if (gestor == null) {
            gestor = new GestorPedidos();
        }
        return gestor;
    }

    @Override
    public boolean hayPedidosConEsteCliente(Cliente cliente) {
        for(Pedido p : this.pedidos) {
            if (p.verCliente().equals(cliente))
                return true;
        }
        return false;
    }

    @Override
    public boolean hayPedidosConEsteProducto(Producto producto) {
        for(Pedido p : this.pedidos) {
            ArrayList<ProductoDelPedido> productosDelPedido = p.verProductosDelPedido();
            for(ProductoDelPedido pdp : productosDelPedido) {
                if(pdp.verProducto().equals(producto))
                    return true;
            }
        }
        return false;
    }

    @Override
    public String crearPedido(LocalDate fecha, LocalTime hora, ArrayList<ProductoDelPedido> productosDelPedido, Cliente cliente) {
        Pedido pedido;
        String resultadoValidacion = this.validarPedido(fecha, hora, productosDelPedido, cliente);
        if (!resultadoValidacion.equals(VALIDACION_EXITO))
            return resultadoValidacion;
        else {
            int numero = this.pedidos.size() + 1;
            LocalDateTime fechaYHora = LocalDateTime.of(fecha, hora);
            pedido = new Pedido(numero, fechaYHora, productosDelPedido, cliente);
            if (!this.pedidos.contains(pedido)) {
                this.pedidos.add(pedido);
                cliente.agregarPedido(pedido);
                return EXITO;
            }
            else //ya existe un pedido con ese número
                return PEDIDOS_DUPLICADOS;
        }        
    }
    
    private String validarPedido(LocalDate fecha, LocalTime hora, ArrayList<ProductoDelPedido> productosDelPedido, Cliente cliente) {
        if (!this.validarFecha(fecha))
            return ERROR_FECHA;
        
        if (!this.validarHora(hora))
            return ERROR_HORA;
        
        if (!this.validarProductosDelPedido(productosDelPedido))
            return ERROR_PRODUCTOS_DEL_PEDIDO;
        
        if (!this.validarCliente(cliente))
            return ERROR_CLIENTE;
        
        return VALIDACION_EXITO;
    }

    /**
     * Valida que la fecha del pedido sea correcta
     * La fecha del pedido es correcta si no es nula
     * @param fecha fecha del pedido
     * @return boolean  - true si la fecha del pedido es correcta, false en caso contrario
    */
    private boolean validarFecha(LocalDate fecha) {
        return fecha != null;
    }
    
    /**
     * Valida que la hora del pedido sea correcta
     * La hora del pedido es correcta si no es nula
     * @param hora hora del pedido
     * @return boolean  - true si la hora del pedido es correcta, false en caso contrario
    */
    private boolean validarHora(LocalTime hora) {
        return hora != null;
    }
    
    /**
     * Valida que la lista de productos del pedido sea correcta
     * La lista de productos del pedido es correcta si no es nula y no está vacía
     * @param productosDelPedido lista de productos del pedido
     * @return boolean  - true si la lista de productos del pedido es correcta, false en caso contrario
    */
    private boolean validarProductosDelPedido(ArrayList<ProductoDelPedido> productosDelPedido) {
        return (productosDelPedido != null) && (!productosDelPedido.isEmpty());
    }
    
    /**
     * Valida que el cliente del pedido sea correcto
     * El cliente del pedido es correcto si no es nulo
     * @param cliente cliente del pedido
     * @return boolean  - true si el cliente del pedido es correcto, false en caso contrario
    */
    private boolean validarCliente(Cliente cliente) {
        return cliente != null;
    }
    
    /**
     * Valida que el estado del pedido sea correcto
     * El estado del pedido es correcto si no es nulo
     * @param estado estado del pedido
     * @return boolean  - true si el estado del pedido es correcto, false en caso contrario
    */
    private boolean validarEstado(Estado estado) {
        return estado != null;
    }
        

    @Override
    public String cambiarEstado(Pedido pedidoAModificar) {
        if (!this.existeEstePedido(pedidoAModificar))
            return PEDIDO_INEXISTENTE;
        
        int posicion = this.pedidos.indexOf(pedidoAModificar);
        
        Estado estado = pedidoAModificar.verEstado();
        if (estado == Estado.CREADO)
            pedidoAModificar.asignarEstado(Estado.PROCESANDO);
        else if (estado == Estado.PROCESANDO)
            pedidoAModificar.asignarEstado(Estado.ENTREGADO);
        Cliente cliente = pedidoAModificar.verCliente();
        this.pedidos.set(posicion, pedidoAModificar);
        cliente.agregarPedido(pedidoAModificar);
        return EXITO;
    }

    @Override
    public ArrayList<Pedido> verPedidos() {
        return this.pedidos;
    }

    @Override
    public String cancelarPedido(Pedido pedido) {
        if (pedido.verEstado() == Estado.CREADO) {
            Cliente cliente = pedido.verCliente();
            cliente.cancelarPedido(pedido);
            this.pedidos.remove(pedido);
            return EXITO;
        }
        else
            return ERROR_CANCELAR;
    }

    @Override
    public boolean existeEstePedido(Pedido pedido) {
        return this.pedidos.contains(pedido);
    }

    @Override
    public Pedido obtenerPedido(Integer numero) {
        if (numero == null)
            return null;
        else {
            for(Pedido p : this.pedidos) {
                if (p.verNumero() == numero)
                    return p;
            }
            return null;
        }
    }
    
    
}
