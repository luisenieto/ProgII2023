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
        IGestorUsuarios gu = GestorUsuarios.instanciar();
        IGestorProductos gp = GestorProductos.instanciar();
        IGestorPedidos gPed = GestorPedidos.instanciar();
        
        System.out.println(gu.crearUsuario("cliente1@bar.com", "ApellidoCliente1", "NombreCliente1", Perfil.CLIENTE, "claveCliente1", "claveCliente1"));
        System.out.println(gu.crearUsuario("cliente2@bar.com", "ApellidoCliente2", "NombreCliente2", Perfil.CLIENTE, "claveCliente2", "claveCliente2"));
        System.out.println(gu.crearUsuario("cliente3@bar.com", "ApellidoCliente3", "NombreCliente3", Perfil.CLIENTE, "claveCliente3", "claveCliente3"));
        System.out.println(gu.crearUsuario("cliente3@bar.com", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, "claveCliente4", "claveCliente4"));
        //cliente duplicado
        System.out.println(gu.crearUsuario(null, "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, "claveCliente4", "claveCliente4"));
        //sin correo
        System.out.println(gu.crearUsuario("", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, "claveCliente4", "claveCliente4"));
        //correo inválido
        System.out.println(gu.crearUsuario("cliente4@bar.com", null, "NombreCliente4", Perfil.CLIENTE, "claveCliente4", "claveCliente4"));
        //sin apellido
        System.out.println(gu.crearUsuario("cliente4@bar.com", "", "NombreCliente4", Perfil.CLIENTE, "claveCliente4", "claveCliente4"));
        //apellido inválido
        System.out.println(gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", null, Perfil.CLIENTE, "claveCliente4", "claveCliente4"));
        //sin nombre
        System.out.println(gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", "", Perfil.CLIENTE, "claveCliente4", "claveCliente4"));
        //nombre inválido
        System.out.println(gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", "NombreCliente4", null, "claveCliente4", "claveCliente4"));
        //sin perfil
        System.out.println(gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, null, "claveCliente4"));
        //sin clave
        System.out.println(gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, "", "claveCliente4"));
        //clave inválida
        System.out.println(gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, "claveCliente4", null));
        //sin repetir la clave
        System.out.println(gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, "claveCliente4", ""));
        //clave repetida inválida
        System.out.println(gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, "claveCliente4", "claveCliente44"));
        //sin coincidir las claves
        
        System.out.println("Clientes");
        System.out.println("========");
        for(Usuario u : gu.verUsuarios()) {
            if (u instanceof Cliente) {
                u.mostrar();
                System.out.println();
            }
        }
        System.out.println();
        
        System.out.println(gu.crearUsuario("empleado1@bar.com", "ApellidoEmpleado1", "NombreEmpleado1", Perfil.EMPLEADO, "claveEmpleado1", "claveEmpleado1"));
        System.out.println(gu.crearUsuario("empleado2@bar.com", "ApellidoEmpleado2", "NombreEmpleado2", Perfil.EMPLEADO, "claveEmpleado2", "claveEmpleado2"));
        System.out.println(gu.crearUsuario("empleado3@bar.com", "ApellidoEmpleado3", "NombreEmpleado3", Perfil.EMPLEADO, "claveEmpleado3", "claveEmpleado3"));
        System.out.println(gu.crearUsuario("empleado3@bar.com", "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado4"));
        //empleado duplicado
        System.out.println(gu.crearUsuario(null, "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado4"));
        //sin correo
        System.out.println(gu.crearUsuario("", "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado4"));
        //correo inválido
        System.out.println(gu.crearUsuario("empleado4@bar.com", null, "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado4"));
        //sin apellido
        System.out.println(gu.crearUsuario("empleado4@bar.com", "", "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado4"));
        //apellido inválido
        System.out.println(gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", null, Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado4"));
        //sin nombre
        System.out.println(gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", "", Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado4"));
        //nombre inválido
        System.out.println(gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", "NombreEmpleado4", null, "claveEmpleado4", "claveEmpleado4"));
        //sin perfil
        System.out.println(gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, null, "claveEmpleado4"));
        //sin clave
        System.out.println(gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, "", "claveEmpleado4"));
        //clave sin especificar
        System.out.println(gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", null));
        //sin repetir clave        
        System.out.println(gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", ""));
        //clave repetida inválida
        System.out.println(gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado44"));
        //sin coincidir las claves
        
        System.out.println("Empleados");
        System.out.println("=========");
        for(Usuario u : gu.verUsuarios()) {
            if (u instanceof Empleado) {
                u.mostrar();
                System.out.println();
            }
        }
        System.out.println();

        System.out.println(gu.crearUsuario("encargado1@bar.com", "ApellidoEncargado1", "NombreEncargado1", Perfil.ENCARGADO, "claveEncargado1", "claveEncargado1"));
        System.out.println(gu.crearUsuario("encargado2@bar.com", "ApellidoEncargado2", "NombreEncargado2", Perfil.ENCARGADO, "claveEncargado2", "claveEncargado2"));
        System.out.println(gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado3", "NombreEncargado3", Perfil.ENCARGADO, "claveEncargado3", "claveEncargado3"));
        System.out.println(gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", "claveEncargado4"));
        //encargado duplicado
        System.out.println(gu.crearUsuario(null, "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", "claveEncargado4"));
        //sin correo
        System.out.println(gu.crearUsuario("", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", "claveEncargado4"));
        //correo inválido
        System.out.println(gu.crearUsuario("encargado3@bar.com", null, "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", "claveEncargado4"));
        //sin apellido
        System.out.println(gu.crearUsuario("encargado3@bar.com", "", "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", "claveEncargado4"));
        //apellido inválido
        System.out.println(gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", null, Perfil.ENCARGADO, "claveEncargado4", "claveEncargado4"));
        //sin nombre
        System.out.println(gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "", Perfil.ENCARGADO, "claveEncargado4", "claveEncargado4"));
        //nombre inválido
        System.out.println(gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "NombreEncargado4", null, "claveEncargado4", "claveEncargado4"));
        //sin perfil
        System.out.println(gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, null, "claveEncargado4"));
        //sin clave
        System.out.println(gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, "", "claveEncargado4"));
        //clave sin especificar
        System.out.println(gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", null));
        //sin repetir clave        
        System.out.println(gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", ""));
        //clave repetida inválida
        System.out.println(gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", "claveEncargado44"));
        //sin coincidir las claves

        System.out.println("Encargados");
        System.out.println("==========");
        for(Usuario u : gu.verUsuarios()) {
            if (u instanceof Encargado) {
                u.mostrar();
                System.out.println();
            }
        }
        System.out.println();

        
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

        System.out.println("Productos");
        System.out.println("=========");
        for(Producto p : gp.menu()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();

        Usuario unCliente1 = gu.obtenerUsuario("cliente1@bar.com");
        Producto unProducto1 = gp.obtenerProducto(1);
        Producto unProducto2 = gp.obtenerProducto(2);
        ArrayList<ProductoDelPedido> productosDelPedido1 = new ArrayList<>();
        ProductoDelPedido pdp1 = new ProductoDelPedido(unProducto1, 1);
        ProductoDelPedido pdp2 = new ProductoDelPedido(unProducto2, 2); 
        LocalDateTime fechaYHora = LocalDateTime.now();
        LocalDate fecha = fechaYHora.toLocalDate();
        LocalTime hora = fechaYHora.toLocalTime();
        if (!productosDelPedido1.contains(pdp1))
            productosDelPedido1.add(pdp1);
        if (!productosDelPedido1.contains(pdp2))
            productosDelPedido1.add(pdp2);
        System.out.println(gPed.crearPedido(fecha, hora, productosDelPedido1, (Cliente)unCliente1));

        Usuario unCliente2 = gu.obtenerUsuario("cliente2@bar.com");
        ArrayList<ProductoDelPedido> productosDelPedido2 = new ArrayList<>();
        ProductoDelPedido pdp3 = new ProductoDelPedido(unProducto1, 10);
        ProductoDelPedido pdp4 = new ProductoDelPedido(unProducto2, 20);
        ProductoDelPedido pdp5 = new ProductoDelPedido(unProducto1, 30);
        //producto repetido        
        if (!productosDelPedido2.contains(pdp3))
            productosDelPedido2.add(pdp3);
        if (!productosDelPedido2.contains(pdp4))
            productosDelPedido2.add(pdp4);
        if (!productosDelPedido2.contains(pdp5))
            productosDelPedido2.add(pdp5);
        System.out.println(gPed.crearPedido(fecha, hora, productosDelPedido2, (Cliente)unCliente2));

        System.out.println(gPed.crearPedido(null, hora, productosDelPedido2, (Cliente)unCliente1));
        //sin fecha
        System.out.println(gPed.crearPedido(fecha, null, productosDelPedido2, (Cliente)unCliente1));
        //sin hora
        ArrayList<ProductoDelPedido> productosDelPedido3 = new ArrayList<>();
        System.out.println(gPed.crearPedido(fecha, hora, null, (Cliente)unCliente1));
        //sin productos
        System.out.println(gPed.crearPedido(fecha, hora, productosDelPedido3, (Cliente)unCliente1));
        //sin productos
        System.out.println(gPed.crearPedido(fecha, hora, productosDelPedido2, null));
        //sin cliente
        
        System.out.println("Pedidos");
        System.out.println("=======");
        for(Pedido p : gPed.verPedidos()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();        

        
//        ((Cliente)unCliente1).agregarPedido(unPedido1);
//        ((Cliente)unCliente1).agregarPedido(unPedido1);
        //pedido repetido

        System.out.println("Pedidos de " + unCliente1.verApellido() + ", " + unCliente1.verNombre());
        for(Pedido p : unCliente1.verPedidos()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();        

        Usuario unEmpleado3 = gu.obtenerUsuario("empleado3@bar.com");
        System.out.println("Todos los pedidos (consultados por un empleado)");
        for(Pedido p : unEmpleado3.verPedidos()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
                
        Usuario unEncargado3 = gu.obtenerUsuario("encargado3@bar.com");
        System.out.println("Todos los pedidos (consultados por un encargado)");
        for(Pedido p : unEncargado3.verPedidos()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
        
        Pedido unPedido1 = gPed.obtenerPedido(1);
        ((Cliente)unCliente1).cancelarPedido(unPedido1);
        System.out.println("Pedidos de " + unCliente1.verApellido() + ", " + unCliente1.verNombre());
        for(Pedido p : unCliente1.verPedidos()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
        System.out.println(gu.existeEsteUsuario(unCliente1));
        Usuario unCliente10 = new Cliente("cliente10@bar.com", "claveCliente10", "ApellidoCliente10", "NombreCliente10");
        //usuario inexistente
        System.out.println(gu.existeEsteUsuario(unCliente10));
        
        System.out.println(gu.borrarUsuario(unCliente1));
        //pedido con cliente
        Usuario unCliente3 = gu.obtenerUsuario("cliente3@bar.com");
        System.out.println(gu.borrarUsuario(unCliente3));        
        //sí se puede
        
        System.out.println(gu.borrarUsuario(unEmpleado3));
        //sí se puede
        
        System.out.println(gu.borrarUsuario(unEncargado3));
        //sí se puede
        
        System.out.println("Usuarios");
        System.out.println("========");
        for(Usuario u : gu.verUsuarios()) {
            u.mostrar();
            System.out.println();
        }
        System.out.println();
        
        List<Usuario> usuariosBuscados = gu.buscarUsuarios("ApellidoCliente");
        System.out.println("Usuarios filtrados");
        System.out.println("==================");
        for(Usuario u : usuariosBuscados) {
            u.mostrar();
            System.out.println();
        }
        System.out.println();
        
        System.out.println(gp.existeEsteProducto(unProducto1));
        Producto unProducto10 = new Producto(10, "Producto10", Categoria.ENTRADA, Estado.DISPONIBLE, 10.0f);
        //producto inexistente
        System.out.println(gp.existeEsteProducto(unProducto10));
        
        System.out.println(gp.borrarProducto(unProducto1));
        //pedido con producto
        Producto unProducto3 = gp.obtenerProducto(3);
        System.out.println(gp.borrarProducto(unProducto3));        
        //sí se puede
        
        System.out.println("Productos");
        System.out.println("=========");
        for(Producto p : gp.menu()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
        
        List<Producto> productosBuscados = gp.verProductosPorCategoria(Categoria.POSTRE);
        System.out.println("Productos filtrados");
        System.out.println("===================");
        for(Producto p : productosBuscados) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
        
        productosBuscados = gp.buscarProductos("Producto");
        System.out.println("Productos buscados");
        System.out.println("==================");
        for(Producto p : productosBuscados) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
      
        System.out.println(gp.modificarProducto(unProducto1, 100, "Producto11", 10.0f, Categoria.PLATO_PRINCIPAL, Estado.NO_DISPONIBLE));
        //se le cambia la descripción, precio, categoría y estado
        //el código, por más que se lo pasa, no se modifica
        productosBuscados = gp.buscarProductos("Producto11");
        System.out.println("Productos buscados");
        System.out.println("==================");
        for(Producto p : productosBuscados) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
        
        System.out.println(gPed.existeEstePedido(unPedido1));
        Pedido unPedido10 = gPed.obtenerPedido(10);
        //pedido inexistente
        System.out.println(gPed.existeEstePedido(unPedido10));
        
        Pedido unPedido2 = gPed.obtenerPedido(2);
        System.out.println(gPed.cambiarEstado(unPedido2));
        
        System.out.println("Pedidos");
        System.out.println("=======");
        for(Pedido p : gPed.verPedidos()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();  
        
        System.out.println(gPed.cancelarPedido(unPedido2));
        //no se puede por el estado del pedido
        System.out.println(gPed.cancelarPedido(unPedido1));
        //sí se puede
        
        System.out.println("Pedidos");
        System.out.println("=======");
        for(Pedido p : gPed.verPedidos()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();  
        
    } 
}
