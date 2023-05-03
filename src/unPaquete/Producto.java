/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unPaquete;

/**
 *
 * @author root
 */
public class Producto {
    int codigo;
    String descripcion;
    float precio;
    String categoria;
    String estado;

    
    void mostrar() {
        System.out.println(codigo);
        System.out.println(descripcion);
        System.out.println(precio);
        System.out.println(categoria);
        System.out.println(estado);
    }
    
    
    
}
