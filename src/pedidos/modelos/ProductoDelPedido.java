/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.modelos;

import java.util.Objects;
import productos.modelos.Producto;

/**
 *
 * @author root
 */
public class ProductoDelPedido {
    private Producto producto;
    private int cantidad;

    public ProductoDelPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto verProducto() {
        return producto;
    }

    public void asignarProducto(Producto producto) {
        this.producto = producto;
    }

    public int verCantidad() {
        return cantidad;
    }

    public void asignarCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.producto);
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
        final ProductoDelPedido other = (ProductoDelPedido) obj;
        if (!Objects.equals(this.producto, other.producto)) {
            return false;
        }
        return true;
    }
    
    
}
