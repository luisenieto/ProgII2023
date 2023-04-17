/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos.modelos;

import interfaces.IGestorPedidos;
import interfaces.IGestorPermisos;
import interfaces.IGestorProductos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import pedidos.modelos.GestorPedidos;
import permisos.modelos.GestorPermisos;
import usuarios.modelos.Usuario;

/**
 *
 * @author root
 */
public class GestorProductos implements IGestorProductos {
    private final String NOMBRE_ARCHIVO = "./Productos.txt";
    //nombre del archivo con los productos
    private final char SEPARADOR = ':'; 
    //caracter usado como separador   
    
    private static GestorProductos gestor;
    
    private List<Producto> productos = new ArrayList<>();
    
    /**
     * Constructor
     */
    private GestorProductos() {
        String resultado = this.leerArchivo();
        if ((resultado.equals(LECTURA_ERROR)) || (resultado.equals(CREACION_ERROR))) {
            //JOptionPane.showMessageDialog(null, resultado, IControladorPrincipal.TITULO, JOptionPane.ERROR_MESSAGE);
            //this.problemasConArchivo = true;
        } else {
            //this.problemasConArchivo = false;
        }
    }
    
    /**
     * Método estático que permite crear una única instancia de GestorProductos
     * @return GestorProductos
    */
    public static GestorProductos instanciar() {
        if (gestor == null) {
            gestor = new GestorProductos();
        }
        return gestor;
    }

    @Override
    public String crearProducto(Usuario usuarioLogueado, int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        Producto producto;
        
        IGestorPermisos gp = GestorPermisos.instanciar();
        if (!gp.crearProductos(usuarioLogueado))
            return ERROR_PERMISOS;
        
        String resultadoValidacion = this.validarDatos(codigo, descripcion, precio, categoria, estado);
        if (!resultadoValidacion.equals(VALIDACION_EXITO))
            return resultadoValidacion;                   
        else {
            producto = new Producto(codigo, descripcion, precio, categoria, estado);
            if (!this.productos.contains(producto)) { //no existe el producto
                this.productos.add(producto);
                //Collections.sort(this.usuarios);
                String resultado = this.escribirArchivo();
                return (resultado.equals(ESCRITURA_OK) ? EXITO : resultado);
            }
            else //ya existe un producto con ese código
                return PRODUCTOS_DUPLICADOS;
        }
    }
    
    /**
     * Valida que los datos del producto sean correctos
     * @param codigo código del producto
     * @param descripcion descripción del producto
     * @param precio precio del producto
     * @param categoria categoría del producto
     * @param estado estado del producto
     * @return String  - cadena con el resultado de la operación (ERROR_CODIGO || ERROR_DESCRIPCION || ERROR_PRECIO || ERROR_CATEGORIA || ERROR_ESTADO || VALIDACION_EXITO)
    */
    private String validarDatos(int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        if (!this.validarCodigo(codigo))
            return ERROR_CODIGO;

        if (!this.validarDescripcion(descripcion))
            return ERROR_DESCRIPCION;
        
        if (!this.validarPrecio(precio))
                return ERROR_PRECIO;

        if (!this.validarCategoria(categoria))
            return ERROR_CATEGORIA;

        if (!this.validarEstado(estado))
            return ERROR_ESTADO;
        
        return VALIDACION_EXITO;
    }

    /**
     * Valida que el código del producto sea correcto
     * El código es correcto si es positivo
     * @param codigo código del producto
     * @return boolean  - true si el código del producto es correcto, false en caso contrario
    */
    private boolean validarCodigo(int codigo) {
        return codigo > 0;
    }
        
    /**
     * Valida que la descripción del producto sea correcta
     * La descripción es correcta si no es nula ni una cadena vacía
     * @param descripcion descripción del producto
     * @return boolean  - true si la descripción del producto es correcta, false en caso contrario
    */
    private boolean validarDescripcion(String descripcion) {
        return (descripcion != null) && (!descripcion.trim().isEmpty());
    }
    
    /**
     * Valida que el precio del producto sea correcto
     * El precio es correcto si es positivo
     * @param precio precio del producto
     * @return boolean  - true si el precio del producto es correcto, false en caso contrario
    */
    private boolean validarPrecio(float precio) {
        return precio > 0;
    }  
    
    /**
     * Valida que la categoría del producto sea correcta
     * La categoría es correcta si no es nula
     * @param categoria categoría del producto
     * @return boolean  - true si la categoría del producto es correcta, false en caso contrario
    */
    private boolean validarCategoria(Categoria categoria) {
        return categoria != null;
    }
    
    /**
     * Valida que el estado del producto sea correcto
     * El estado es correcto si no es nulo
     * @param estado estado del producto
     * @return boolean  - true si el estado del producto es correcto, false en caso contrario
    */
    private boolean validarEstado(Estado estado) {
        return estado != null;
    }
    

    @Override
    public String modificarProducto(Usuario usuarioLogueado, Producto productoAModificar, int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        if (!this.existeEsteProducto(productoAModificar))
            return PRODUCTO_INEXISTENTE;
        
        IGestorPermisos gp = GestorPermisos.instanciar();
        if (!gp.modificarProductos(usuarioLogueado))
            return ERROR_PERMISOS;
        
        String resultadoValidacion = this.validarDatos(codigo, descripcion, precio, categoria, estado);
        if (!resultadoValidacion.equals(VALIDACION_EXITO))
            return resultadoValidacion;
        else {
            int posicion = this.productos.indexOf(productoAModificar);
            productoAModificar.asignarDescripcion(descripcion);
            productoAModificar.asignarPrecio(precio);
            productoAModificar.asignarCategoria(categoria);
            productoAModificar.asignarEstado(estado);
            this.productos.set(posicion, productoAModificar);
            String resultado = this.escribirArchivo();
            return (resultado.equals(ESCRITURA_OK) ? EXITO : resultado);
        }
    }
    
    
    @Override
    public List<Producto> menu(Usuario usuarioLogueado) {
        IGestorPermisos gp = GestorPermisos.instanciar();
        if (gp.menu(usuarioLogueado)) {
            Comparator<Producto> cmp = (p1, p2) -> {
                if (p1.verCategoria() == p2.verCategoria())
                    return p1.verDescripcion().compareToIgnoreCase(p2.verDescripcion());
                else
                    return p1.verCategoria().compareTo(p2.verCategoria());
            };
            Collections.sort(this.productos, cmp);
            return this.productos;
        }
        else {
            List<Producto> listaVacia = new ArrayList<>();
            return listaVacia;
        }
    }

    @Override
    public List<Producto> buscarProductos(Usuario usuarioLogueado, String descripcion) {
        List<Producto> productosBuscados = new ArrayList<>();
        IGestorPermisos gp = GestorPermisos.instanciar();
        if (gp.borrarProductos(usuarioLogueado)) {            
            if (descripcion != null) {            
                for(Producto producto : this.productos) {
                    if (producto.verDescripcion().toLowerCase().contains(descripcion.toLowerCase()))
                        productosBuscados.add(producto);
                }            
            }
            return productosBuscados;    
        }
        else {
            return productosBuscados;
        }
    }

    @Override
    public String borrarProducto(Usuario usuarioLogueado, Producto producto) {
        IGestorPermisos gPer = GestorPermisos.instanciar();
        if (!gPer.borrarProductos(usuarioLogueado))
            return ERROR_PERMISOS;                
        
        if (this.existeEsteProducto(producto)) {        
            IGestorPedidos gp = GestorPedidos.instanciar();
            if (gp.hayPedidosConEsteProducto(producto))  //hay al menos un pedido con este producto
                return PEDIDO_CON_PRODUCTO;
            else { //no hay pedidos con este producto
                this.productos.remove(producto);
                String resultado = this.escribirArchivo();
                return (resultado.equals(ESCRITURA_OK) ? EXITO : ESCRITURA_ERROR);                
            }
        }
        else
            return PRODUCTO_INEXISTENTE;
    }

    @Override
    public boolean existeEsteProducto(Producto producto) {
        return this.productos.contains(producto); 
    }

    @Override
    public List<Producto> verProductosPorCategoria(Categoria categoria) {
        List<Producto> productosBuscados = new ArrayList<>();
        for(Producto p : this.productos) {
            if (p.verCategoria().equals(categoria))
                productosBuscados.add(p);
        }
        Comparator<Producto> cmp = (p1, p2) -> p1.verDescripcion().compareTo(p2.verDescripcion());
        Collections.sort(productosBuscados, cmp);
        return productosBuscados;
    }

    @Override
    public Producto obtenerProducto(Integer codigo) {
        if (codigo == null)
            return null;
        else {
            for(Producto p : this.productos) {
                if (p.verCodigo() == codigo)
                    return p;
            }
            return null;
        }
    }
    
    
    
    
    
    /**
     * Lee del archivo de texto y carga el ArrayList empleando un try con
     * recursos
     * https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
     * Formato del archivo (suponiendo que la coma sea el separador):
     * codigo1,descripcion1,precio1,categoria1,estado1
     * codigo2,descripcion2,precio2,categoria2,estado2
     * codigo3,descripcion3,precio3,categoria1,estado1
     *
     * @return String - cadena con el resultado de la operacion (LECTURA_OK |
     * LECTURA_ERROR | | CREACION_OK | CREACION_ERROR)
     */
    private String leerArchivo() {
        File file = new File(NOMBRE_ARCHIVO);
        if (!file.exists()) {
            return this.crearArchivo();
        }

        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            String cadena;
            while ((cadena = br.readLine()) != null) {
                String[] vector = cadena.split(Character.toString(SEPARADOR));
                int codigo = Integer.parseInt(vector[0]);
                String descripcion = vector[1];
                float precio = Float.parseFloat(vector[2]);
                String cadenaCategoria = vector[3];
                Categoria categoria = Categoria.verCategoria(cadenaCategoria);
                String cadenaEstado = vector[4];
                Estado estado = Estado.verEstado(cadenaEstado);
                Producto producto = new Producto(codigo, descripcion, precio, categoria, estado);
                this.productos.add(producto);
            }
            
            this.productos.add(new Producto(-1, "", -1, Categoria.ENTRADA, Estado.DISPONIBLE));
            this.productos.add(new Producto(-2, "", -1, Categoria.PLATO_PRINCIPAL, Estado.DISPONIBLE));
            this.productos.add(new Producto(-3, "", -1, Categoria.POSTRE, Estado.DISPONIBLE));
            //estos 3 productos son para mostrar "más lindo" el menú
            return LECTURA_OK;
        } catch (IOException ioe) {
            return LECTURA_ERROR;
        }
    }    
    
    /**
     * Crea el archivo
     *
     * @return String - cadena con el resultado de la operacion (CREACION_OK | CREACION_ERROR)
     */
    private String crearArchivo() {
        File file = new File(NOMBRE_ARCHIVO);
        try ( FileWriter fw = new FileWriter(file)) {
            return CREACION_OK;
        } catch (IOException ioe) {
            return CREACION_ERROR;
        }
    }
    
    /**
     * Escribe en el archivo de texto el ArrayList
     * Formato del archivo (suponiendo que la coma sea el separador):
     *  correo1,clave1,apellido1,nombre1,perfil1
     *  correo2,clave2,apellido2,nombre2,perfil2
     *  correo3,clave3,apellido3,nombre3,perfil3 
     * @return String  - cadena con el resultado de la operacion (ESCRITURA_ERROR | ESCRITURA_OK)
     */
    private String escribirArchivo() {
        File file = new File(NOMBRE_ARCHIVO);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {     
            for(Producto producto : this.productos) {
                if (producto.verPrecio() > 0) {
                    String cadena = Integer.toString(producto.verCodigo()) + SEPARADOR;
                    cadena += producto.verDescripcion() + SEPARADOR;
                    cadena += Float.toString(producto.verPrecio()) + SEPARADOR;
                    cadena += producto.verCategoria().toString() + SEPARADOR;
                    cadena += producto.verEstado().toString();
                    bw.write(cadena);
                    bw.newLine();
                }
            }
            return ESCRITURA_OK;
        } 
        catch (IOException ioe) {
            return ESCRITURA_ERROR;            
        }
    }         
}
