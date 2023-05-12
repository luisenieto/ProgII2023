/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.controladores;

import java.time.LocalDateTime;
import productos.modelos.Producto;
import usuarios.modelos.Encargado;
import usuarios.modelos.Usuario;
import usuarios.modelos.Empleado;
import usuarios.modelos.Cliente;
import usuarios.modelos.GestorUsuarios;
import interfaces.IGestorUsuarios;
import interfaces.IGestorProductos;
import interfaces.IGestorPedidos;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import pedidos.modelos.ProductoDelPedido;
import pedidos.modelos.GestorPedidos;
import pedidos.modelos.Pedido;
import productos.modelos.Categoria;
import productos.modelos.Estado;
import productos.modelos.GestorProductos;
import usuarios.modelos.Perfil;


/**
 *
 * @author root
 */
public class ControladorPrincipal  {
    public static void main(String[] args) {
        IGestorProductos gp = GestorProductos.instanciar();
        

//      Creación de productos

/*
        System.out.println(gp.crearProducto(1, "Producto1", 1.0f, Categoria.ENTRADA, Estado.DISPONIBLE));       
        System.out.println(gp.crearProducto(2, "Producto2", 2.0f, Categoria.PLATO_PRINCIPAL, Estado.DISPONIBLE));
        System.out.println(gp.crearProducto(3, "Producto3", 3.0f, Categoria.POSTRE, Estado.DISPONIBLE));
        System.out.println(gp.crearProducto(3, "Producto4", 4.0f, Categoria.POSTRE, Estado.DISPONIBLE));
        //producto repetido
        System.out.println(gp.crearProducto(0, "Producto4", 4.0f, Categoria.POSTRE, Estado.DISPONIBLE));
        //código inválido
        System.out.println(gp.crearProducto(4, null, 4.0f, Categoria.POSTRE, Estado.DISPONIBLE));
        //sin descripción
        System.out.println(gp.crearProducto(4, "", 4.0f, Categoria.POSTRE, Estado.DISPONIBLE));
        //descripción inválida
        System.out.println(gp.crearProducto(4, "Producto4", 0.0f, Categoria.POSTRE, Estado.DISPONIBLE));
        //precio inválido
        System.out.println(gp.crearProducto(4, "Producto4", 4.0f, null, Estado.DISPONIBLE));
        //sin categoría
        System.out.println(gp.crearProducto(4, "Producto4", 4.0f, Categoria.POSTRE, null));
        //sin estado

*/

//      Trabajo con productos


        System.out.println("Productos");
        System.out.println("=========");
        for(Producto p : gp.menu()) {
            if (p.verPrecio() > 0) { //para no mostrar los 3 productos con precio negativo
                p.mostrar();
                System.out.println();
            }
        }
        System.out.println();

        Producto unProducto1 = gp.obtenerProducto(1);
        Producto unProducto2 = gp.obtenerProducto(2);
        
        System.out.println(gp.existeEsteProducto(unProducto1));
        Producto unProducto10 = new Producto(10, "Producto10", Categoria.ENTRADA, Estado.DISPONIBLE, 10.0f);
        //producto inexistente
        System.out.println(gp.existeEsteProducto(unProducto10));
               
        List<Producto> productosBuscados = gp.verProductosPorCategoria(Categoria.POSTRE);
        System.out.println("Productos filtrados");
        System.out.println("===================");
        for(Producto p : productosBuscados) {
            if (p.verPrecio() > 0) { //para no mostrar los 3 productos con precio negativo
                p.mostrar();
                System.out.println();
            }
        }
        System.out.println();
        
        productosBuscados = gp.buscarProductos("Producto");
        System.out.println("Productos buscados");
        System.out.println("==================");
        for(Producto p : productosBuscados) {
            if (p.verPrecio() > 0) { //para no mostrar los 3 productos con precio negativo
                p.mostrar();
                System.out.println();
            }
        }
        System.out.println();
      
        System.out.println(gp.modificarProducto(unProducto1, 100, "Producto11", 10.0f, Categoria.PLATO_PRINCIPAL, Estado.NO_DISPONIBLE));
        //se le cambia la descripción, precio, categoría y estado
        //el código, por más que se lo pasa, no se modifica
        productosBuscados = gp.buscarProductos("Producto11");
        System.out.println("Productos buscados");
        System.out.println("==================");
        for(Producto p : productosBuscados) {
            if (p.verPrecio() > 0) { //para no mostrar los 3 productos con precio negativo
                p.mostrar();
                System.out.println();
            }
        }
        System.out.println();
        
        System.out.println(gp.borrarProducto(unProducto1));
        //sí se puede
        
        System.out.println("Productos");
        System.out.println("=========");
        for(Producto p : gp.menu()) {
            if (p.verPrecio() > 0) { //para no mostrar los 3 productos con precio negativo
                p.mostrar();
                System.out.println();
            }
        }
        System.out.println();

    } 
}
