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
import java.util.ArrayList;
import pedidos.modelos.Pedido;
import pedidos.modelos.ProductoDelPedido;
import productos.modelos.Categoria;
import productos.modelos.Estado;


/**
 *
 * @author root
 */
public class ControladorPrincipal  {
    public static void main(String[] args) {
/*      Primera parte  
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Empleado> empleados = new ArrayList<>();
        ArrayList<Encargado> encargados = new ArrayList<>();
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        
        Cliente unCliente1 = new Cliente("cliente1@bar.com", "claveCliente1", "ApellidoCliente1", "NombreCliente1");        
        Cliente unCliente2 = new Cliente("cliente2@bar.com", "claveCliente2", "ApellidoCliente2", "NombreCliente2");       
        Cliente unCliente3 = new Cliente("cliente3@bar.com", "claveCliente3", "ApellidoCliente3", "NombreCliente3");
        Cliente unCliente4 = new Cliente("cliente3@bar.com", "claveCliente4", "ApellidoCliente4", "NombreCliente4"); 
        //cliente repetido
        
        if (!clientes.contains(unCliente1))
            clientes.add(unCliente1);
        if (!clientes.contains(unCliente2))
            clientes.add(unCliente2);
        if (!clientes.contains(unCliente3))
            clientes.add(unCliente3);
        if (!clientes.contains(unCliente4))
            clientes.add(unCliente4);
        
        System.out.println("Clientes");
        System.out.println("========");
        for(Cliente c : clientes) {
            c.mostrar();
            System.out.println();
        }
        System.out.println();        
                
        Empleado unEmpleado1 = new Empleado("empleado1@bar.com", "claveEmpleado1", "ApellidoEmpleado1", "NombreEmpleado1");        
        Empleado unEmpleado2 = new Empleado("empleado2@bar.com", "claveEmpleado2", "ApellidoEmpleado2", "NombreEmpleado2");        
        Empleado unEmpleado3 = new Empleado("empleado3@bar.com", "claveEmpleado3", "ApellidoEmpleado3", "NombreEmpleado3");
        Empleado unEmpleado4 = new Empleado("empleado3@bar.com", "claveEmpleado4", "ApellidoEmpleado4", "NombreEmpleado4");
        //empleado repetido
        
        if (!empleados.contains(unEmpleado1))
            empleados.add(unEmpleado1);
        if (!empleados.contains(unEmpleado2))
            empleados.add(unEmpleado2);
        if (!empleados.contains(unEmpleado3))
            empleados.add(unEmpleado3);
        if (!empleados.contains(unEmpleado4))
            empleados.add(unEmpleado4);
        
        System.out.println("Empleados");
        System.out.println("=========");
        for(Empleado e : empleados) {
            e.mostrar();
            System.out.println();
        }
        System.out.println();

        Encargado unEncargado1 = new Encargado("encargado1@bar.com", "claveEncargado1", "ApellidoEncargado1", "NombreEncargado1");
        Encargado unEncargado2 = new Encargado("encargado2@bar.com", "claveEncargado2", "ApellidoEncargado2", "NombreEncargado2");
        Encargado unEncargado3 = new Encargado("encargado3@bar.com", "claveEncargado3", "ApellidoEncargado3", "NombreEncargado3");
        Encargado unEncargado4 = new Encargado("encargado3@bar.com", "claveEncargado4", "ApellidoEncargado4", "NombreEncargado4");
        //encargado repetido

        if(!encargados.contains(unEncargado1))
            encargados.add(unEncargado1);
        if(!encargados.contains(unEncargado2))
            encargados.add(unEncargado2);
        if(!encargados.contains(unEncargado3))
            encargados.add(unEncargado3);
        if(!encargados.contains(unEncargado4))
            encargados.add(unEncargado4);
        
        System.out.println("Encargados");
        System.out.println("==========");
        for(Encargado e : encargados) {
            e.mostrar();
            System.out.println();
        }
        System.out.println();
        
        Producto unProducto1 = new Producto(1, "Producto1", Categoria.ENTRADA, Estado.DISPONIBLE, 1.0f);        
        Producto unProducto2 = new Producto(2, "Producto2", Categoria.PLATO_PRINCIPAL, Estado.DISPONIBLE, 2.0f);
        Producto unProducto3 = new Producto(3, "Producto3", Categoria.POSTRE, Estado.DISPONIBLE, 3.0f);
        Producto unProducto4 = new Producto(3, "Producto4", Categoria.POSTRE, Estado.DISPONIBLE, 4.0f);
        //producto repetido
        
        if(!productos.contains(unProducto1))
            productos.add(unProducto1);
        if(!productos.contains(unProducto2))
            productos.add(unProducto2);
        if(!productos.contains(unProducto3))
            productos.add(unProducto3);
        if(!productos.contains(unProducto4))
            productos.add(unProducto4);
        
        System.out.println("Productos");
        System.out.println("=========");
        for(Producto p : productos) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
                     
        ArrayList<ProductoDelPedido> productosDelPedido1 = new ArrayList<>();
        ProductoDelPedido pdp1 = new ProductoDelPedido(unProducto1, 1);
        ProductoDelPedido pdp2 = new ProductoDelPedido(unProducto2, 2);        
        if (!productosDelPedido1.contains(pdp1))
            productosDelPedido1.add(pdp1);
        if (!productosDelPedido1.contains(pdp2))
            productosDelPedido1.add(pdp2);
        Pedido unPedido1 = new Pedido(1, LocalDateTime.now(), productosDelPedido1, unCliente1);        
        
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
        Pedido unPedido2 = new Pedido(2, LocalDateTime.now(), productosDelPedido2, unCliente2);        
        
        ArrayList<ProductoDelPedido> productosDelPedido3 = new ArrayList<>();
        ProductoDelPedido pdp6 = new ProductoDelPedido(unProducto1, 100);
        ProductoDelPedido pdp7 = new ProductoDelPedido(unProducto2, 200);
        if (!productosDelPedido3.contains(pdp6))
            productosDelPedido3.add(pdp6);
        if (!productosDelPedido3.contains(pdp7))
            productosDelPedido3.add(pdp7);
        Pedido unPedido3 = new Pedido(2, LocalDateTime.now(), productosDelPedido3, unCliente3);        
        //pedido repetido

        if(!pedidos.contains(unPedido1))
            pedidos.add(unPedido1);
        if(!pedidos.contains(unPedido2))
            pedidos.add(unPedido2);
        if(!pedidos.contains(unPedido3))
            pedidos.add(unPedido3);
        
        System.out.println("Pedidos");
        System.out.println("=======");
        for(Pedido p : pedidos) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();        
*/

/*      Segunda parte

        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        Usuario unCliente1 = new Cliente("cliente1@bar.com", "claveCliente1", "ApellidoCliente1", "NombreCliente1");        
        Usuario unCliente2 = new Cliente("cliente2@bar.com", "claveCliente2", "ApellidoCliente2", "NombreCliente2");       
        Usuario unCliente3 = new Cliente("cliente3@bar.com", "claveCliente3", "ApellidoCliente3", "NombreCliente3");
        Usuario unCliente4 = new Cliente("cliente3@bar.com", "claveCliente4", "ApellidoCliente4", "NombreCliente4"); 
        //cliente repetido
        
        if (!usuarios.contains(unCliente1))
            usuarios.add(unCliente1);
        if (!usuarios.contains(unCliente2))
            usuarios.add(unCliente2);
        if (!usuarios.contains(unCliente3))
            usuarios.add(unCliente3);
        if (!usuarios.contains(unCliente4))
            usuarios.add(unCliente4);
        
        System.out.println("Clientes");
        System.out.println("========");
        for(Usuario c : usuarios) {
            if (c instanceof Cliente) {
                c.mostrar();
                System.out.println();
            }
        }
        System.out.println();        
                
        Usuario unEmpleado1 = new Empleado("empleado1@bar.com", "claveEmpleado1", "ApellidoEmpleado1", "NombreEmpleado1");        
        Usuario unEmpleado2 = new Empleado("empleado2@bar.com", "claveEmpleado2", "ApellidoEmpleado2", "NombreEmpleado2");        
        Usuario unEmpleado3 = new Empleado("empleado3@bar.com", "claveEmpleado3", "ApellidoEmpleado3", "NombreEmpleado3");
        Usuario unEmpleado4 = new Empleado("empleado3@bar.com", "claveEmpleado4", "ApellidoEmpleado4", "NombreEmpleado4");
        //empleado repetido
        
        if (!usuarios.contains(unEmpleado1))
            usuarios.add(unEmpleado1);
        if (!usuarios.contains(unEmpleado2))
            usuarios.add(unEmpleado2);
        if (!usuarios.contains(unEmpleado3))
            usuarios.add(unEmpleado3);
        if (!usuarios.contains(unEmpleado4))
            usuarios.add(unEmpleado4);
        
        System.out.println("Empleados");
        System.out.println("=========");
        for(Usuario e : usuarios) {
            if (e instanceof Empleado) {
                e.mostrar();
                System.out.println();
            }
        }
        System.out.println();

        Usuario unEncargado1 = new Encargado("encargado1@bar.com", "claveEncargado1", "ApellidoEncargado1", "NombreEncargado1");
        Usuario unEncargado2 = new Encargado("encargado2@bar.com", "claveEncargado2", "ApellidoEncargado2", "NombreEncargado2");
        Usuario unEncargado3 = new Encargado("encargado3@bar.com", "claveEncargado3", "ApellidoEncargado3", "NombreEncargado3");
        Usuario unEncargado4 = new Encargado("encargado3@bar.com", "claveEncargado4", "ApellidoEncargado4", "NombreEncargado4");
        //encargado repetido

        if(!usuarios.contains(unEncargado1))
            usuarios.add(unEncargado1);
        if(!usuarios.contains(unEncargado2))
            usuarios.add(unEncargado2);
        if(!usuarios.contains(unEncargado3))
            usuarios.add(unEncargado3);
        if(!usuarios.contains(unEncargado4))
            usuarios.add(unEncargado4);
        
        System.out.println("Encargados");
        System.out.println("==========");
        for(Usuario e : usuarios) {
            if (e instanceof Encargado) {
                e.mostrar();
                System.out.println();
            }
        }
        System.out.println();   
        
        System.out.println("Todos");
        System.out.println("=====");
        for(Usuario u : usuarios) {
            u.mostrar();
            System.out.println();
        }
        System.out.println();
*/

//      Tercera parte

        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        
        Usuario unCliente1 = new Cliente("cliente1@bar.com", "claveCliente1", "ApellidoCliente1", "NombreCliente1");        
        Usuario unCliente2 = new Cliente("cliente2@bar.com", "claveCliente2", "ApellidoCliente2", "NombreCliente2");       
        Usuario unCliente3 = new Cliente("cliente3@bar.com", "claveCliente3", "ApellidoCliente3", "NombreCliente3");
        Usuario unCliente4 = new Cliente("cliente3@bar.com", "claveCliente4", "ApellidoCliente4", "NombreCliente4"); 
        //cliente repetido
        
        if (!usuarios.contains(unCliente1))
            usuarios.add(unCliente1);
        if (!usuarios.contains(unCliente2))
            usuarios.add(unCliente2);
        if (!usuarios.contains(unCliente3))
            usuarios.add(unCliente3);
        if (!usuarios.contains(unCliente4))
            usuarios.add(unCliente4);
                        
        Usuario unEmpleado1 = new Empleado("empleado1@bar.com", "claveEmpleado1", "ApellidoEmpleado1", "NombreEmpleado1");        
        Usuario unEmpleado2 = new Empleado("empleado2@bar.com", "claveEmpleado2", "ApellidoEmpleado2", "NombreEmpleado2");        
        Usuario unEmpleado3 = new Empleado("empleado3@bar.com", "claveEmpleado3", "ApellidoEmpleado3", "NombreEmpleado3");
        Usuario unEmpleado4 = new Empleado("empleado3@bar.com", "claveEmpleado4", "ApellidoEmpleado4", "NombreEmpleado4");
        //empleado repetido
        
        if (!usuarios.contains(unEmpleado1))
            usuarios.add(unEmpleado1);
        if (!usuarios.contains(unEmpleado2))
            usuarios.add(unEmpleado2);
        if (!usuarios.contains(unEmpleado3))
            usuarios.add(unEmpleado3);
        if (!usuarios.contains(unEmpleado4))
            usuarios.add(unEmpleado4);
        
        Usuario unEncargado1 = new Encargado("encargado1@bar.com", "claveEncargado1", "ApellidoEncargado1", "NombreEncargado1");
        Usuario unEncargado2 = new Encargado("encargado2@bar.com", "claveEncargado2", "ApellidoEncargado2", "NombreEncargado2");
        Usuario unEncargado3 = new Encargado("encargado3@bar.com", "claveEncargado3", "ApellidoEncargado3", "NombreEncargado3");
        Usuario unEncargado4 = new Encargado("encargado3@bar.com", "claveEncargado4", "ApellidoEncargado4", "NombreEncargado4");
        //encargado repetido

        if(!usuarios.contains(unEncargado1))
            usuarios.add(unEncargado1);
        if(!usuarios.contains(unEncargado2))
            usuarios.add(unEncargado2);
        if(!usuarios.contains(unEncargado3))
            usuarios.add(unEncargado3);
        if(!usuarios.contains(unEncargado4))
            usuarios.add(unEncargado4);

        Producto unProducto1 = new Producto(1, "Producto1", Categoria.ENTRADA, Estado.DISPONIBLE, 1.0f);        
        Producto unProducto2 = new Producto(2, "Producto2", Categoria.PLATO_PRINCIPAL, Estado.DISPONIBLE, 2.0f);
        Producto unProducto3 = new Producto(3, "Producto3", Categoria.POSTRE, Estado.DISPONIBLE, 3.0f);
        Producto unProducto4 = new Producto(3, "Producto4", Categoria.POSTRE, Estado.DISPONIBLE, 4.0f);
        //producto repetido
        
        if(!productos.contains(unProducto1))
            productos.add(unProducto1);
        if(!productos.contains(unProducto2))
            productos.add(unProducto2);
        if(!productos.contains(unProducto3))
            productos.add(unProducto3);
        if(!productos.contains(unProducto4))
            productos.add(unProducto4);
        
        ArrayList<ProductoDelPedido> productosDelPedido1 = new ArrayList<>();
        ProductoDelPedido pdp1 = new ProductoDelPedido(unProducto1, 1);
        ProductoDelPedido pdp2 = new ProductoDelPedido(unProducto2, 2);        
        if (!productosDelPedido1.contains(pdp1))
            productosDelPedido1.add(pdp1);
        if (!productosDelPedido1.contains(pdp2))
            productosDelPedido1.add(pdp2);
        Pedido unPedido1 = new Pedido(1, LocalDateTime.now(), productosDelPedido1, (Cliente)unCliente1);        
        
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
        Pedido unPedido2 = new Pedido(2, LocalDateTime.now(), productosDelPedido2, (Cliente)unCliente2);        
        
        ArrayList<ProductoDelPedido> productosDelPedido3 = new ArrayList<>();
        ProductoDelPedido pdp6 = new ProductoDelPedido(unProducto1, 100);
        ProductoDelPedido pdp7 = new ProductoDelPedido(unProducto2, 200);
        if (!productosDelPedido3.contains(pdp6))
            productosDelPedido3.add(pdp6);
        if (!productosDelPedido3.contains(pdp7))
            productosDelPedido3.add(pdp7);
        Pedido unPedido3 = new Pedido(2, LocalDateTime.now(), productosDelPedido3, (Cliente)unCliente3);        
        //pedido repetido

        if(!pedidos.contains(unPedido1))
            pedidos.add(unPedido1);
        if(!pedidos.contains(unPedido2))
            pedidos.add(unPedido2);
        if(!pedidos.contains(unPedido3))
            pedidos.add(unPedido3);
        
        System.out.println("Pedidos");
        System.out.println("=======");
        for(Pedido p : pedidos) {
            p.mostrar();
            System.out.println();
        }
        System.out.println(); 
        
        ((Cliente)unCliente1).agregarPedido(unPedido1);
        ((Cliente)unCliente1).agregarPedido(unPedido1);
        //pedido repetido
        
        System.out.println("Pedidos de " + unCliente1.verApellido() + ", " + unCliente1.verNombre());
        for(Pedido p : unCliente1.verPedidos()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
        
        ((Cliente)unCliente1).cancelarPedido(unPedido1);
        System.out.println("Pedidos de " + unCliente1.verApellido() + ", " + unCliente1.verNombre());
        for(Pedido p : unCliente1.verPedidos()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
    } 

}
