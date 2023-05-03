/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unPaquete;

import java.util.ArrayList;


/**
 *
 * @author root
 */
public class ControladorPrincipal  {
    public static void main(String[] args) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Empleado> empleados = new ArrayList<>();
        ArrayList<Encargado> encargados = new ArrayList<>();
        ArrayList<Producto> productos = new ArrayList<>();
        
        Cliente unCliente1 = new Cliente();
        unCliente1.correo = "cliente1@bar.com";
        unCliente1.clave = "claveCliente1";
        unCliente1.apellido = "ApellidoCliente1";
        unCliente1.nombre = "NombreCliente1";
        
        Cliente unCliente2 = new Cliente();
        unCliente2.correo = "cliente2@bar.com";
        unCliente2.clave = "claveCliente2";
        unCliente2.apellido = "ApellidoCliente2";
        unCliente2.nombre = "NombreCliente2";
        
        Cliente unCliente3 = new Cliente();
        unCliente3.correo = "cliente3@bar.com";
        unCliente3.clave = "claveCliente3";
        unCliente3.apellido = "ApellidoCliente3";
        unCliente3.nombre = "NombreCliente3";
        
        clientes.add(unCliente1);
        clientes.add(unCliente2);
        clientes.add(unCliente3);
        
        System.out.println("Clientes");
        System.out.println("========");
        for(Cliente c : clientes) {
            c.mostrar();
            System.out.println();
        }
        System.out.println();        
        
        Empleado unEmpleado1 = new Empleado();
        unEmpleado1.correo = "empleado1@bar.com";
        unEmpleado1.clave = "claveEmpleado1";
        unEmpleado1.apellido = "ApellidoEmpleado1";
        unEmpleado1.nombre = "NombreEmpleado1";
        
        Empleado unEmpleado2 = new Empleado();
        unEmpleado2.correo = "empleado2@bar.com";
        unEmpleado2.clave = "claveEmpleado2";
        unEmpleado2.apellido = "ApellidoEmpleado2";
        unEmpleado2.nombre = "NombreEmpleado2";
        
        Empleado unEmpleado3 = new Empleado();
        unEmpleado3.correo = "empleado3@bar.com";
        unEmpleado3.clave = "claveEmpleado3";
        unEmpleado3.apellido = "ApellidoEmpleado3";
        unEmpleado3.nombre = "NombreEmpleado3";
                
        empleados.add(unEmpleado1);
        empleados.add(unEmpleado2);
        empleados.add(unEmpleado3);
        
        System.out.println("Empleados");
        System.out.println("=========");
        for(Empleado e : empleados) {
            e.mostrar();
            System.out.println();
        }
        System.out.println();
        
        Encargado unEncargado1 = new Encargado();
        unEncargado1.correo = "encargado1@bar.com";
        unEncargado1.clave = "claveEncargado1";
        unEncargado1.apellido = "ApellidoEncargado1";
        unEncargado1.nombre = "NombreEncargado1";
        
        Encargado unEncargado2 = new Encargado();
        unEncargado2.correo = "encargado2@bar.com";
        unEncargado2.clave = "claveEncargado2";
        unEncargado2.apellido = "ApellidoEncargado2";
        unEncargado2.nombre = "NombreEncargado2";        
        
        Encargado unEncargado3 = new Encargado();
        unEncargado3.correo = "encargado3@bar.com";
        unEncargado3.clave = "claveEncargado3";
        unEncargado3.apellido = "ApellidoEncargado3";
        unEncargado3.nombre = "NombreEncargado3";
        
        encargados.add(unEncargado1);
        encargados.add(unEncargado2);
        encargados.add(unEncargado3);
        
        System.out.println("Encargados");
        System.out.println("==========");
        for(Encargado e : encargados) {
            e.mostrar();
            System.out.println();
        }
        System.out.println();
        
        Producto unProducto1 = new Producto();
        unProducto1.codigo = 1;
        unProducto1.descripcion = "Producto1";
        unProducto1.categoria = "Entrada";
        unProducto1.estado = "Disponible";
        unProducto1.precio = 1.0f;
        
        Producto unProducto2 = new Producto();
        unProducto2.codigo = 2;
        unProducto2.descripcion = "Producto2";
        unProducto2.categoria = "Plato principal";
        unProducto2.estado = "Disponible";
        unProducto2.precio = 2.0f;
        
        Producto unProducto3 = new Producto();
        unProducto3.codigo = 3;
        unProducto3.descripcion = "Producto3";
        unProducto3.categoria = "Postre";
        unProducto3.estado = "Disponible";
        unProducto3.precio = 3.0f;
        
        productos.add(unProducto1);
        productos.add(unProducto2);
        productos.add(unProducto3);
        
        System.out.println("Productos");
        System.out.println("=========");
        for(Producto p : productos) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
        
        
        unCliente1.correo = "cliente10@bar.com";
        System.out.println("Clientes");
        System.out.println("========");
        for(Cliente c : clientes) {
            c.mostrar();
            System.out.println();
        }
        System.out.println();
    }
}
