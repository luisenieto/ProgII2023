/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.modelos;

import auxiliares.ManejoFechasYHoras;
import interfaces.IGestorPedidos;
import interfaces.IGestorPermisos;
import interfaces.IGestorProductos;
import interfaces.IGestorUsuarios;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import permisos.modelos.GestorPermisos;
import productos.modelos.GestorProductos;
import productos.modelos.Producto;
import usuarios.modelos.Cliente;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;

/**
 *
 * @author root
 */
public class GestorPedidos implements IGestorPedidos {
    private final String NOMBRE_ARCHIVO = "./Pedidos.txt";
    //nombre del archivo con los pedidos
    private final char SEPARADOR = ','; 
    //caracter usado como separador   
    
    private final String SEPARADOR_FECHA_HORA = " - ";
    //cadena usada como separador entre la fecha y la hora   
    
    private final String SEPARADOR_PRODUCTO_CANTIDAD = "::";
    //cadena usada como separador entre el producto y la cantidad
    
    private static GestorPedidos gestor;
    
    private List<Pedido> pedidos = new ArrayList<>();
    //tiene una copia de los pedidos de todos los clientes
    //permite que sean más rápidas operaciones como:
    //  obtener el siguiente número de pedido
    //  ver si hay pedidos de un determinado producto
    //  mostrar los pedidos de todos los clientes
    
    /**
     * Constructor
     */
    private GestorPedidos() {
        String resultado = this.leerArchivo();
        if ((resultado.equals(LECTURA_ERROR)) || (resultado.equals(CREACION_ERROR))) {
            //JOptionPane.showMessageDialog(null, resultado, IControladorPrincipal.TITULO, JOptionPane.ERROR_MESSAGE);
            //this.problemasConArchivo = true;
        } else {
            //this.problemasConArchivo = false;
        }
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
    public boolean hayPedidosConEsteUsuario(Usuario usuario) {
        return false;
    }

    @Override
    public boolean hayPedidosConEsteProducto(Producto producto) {
        return false;
    }
    
    @Override
    public String crearPedido(Usuario usuarioLogueado, LocalDate fecha, LocalTime hora, List<ProductoDelPedido> productosDelPedido, Cliente cliente) {
        IGestorPermisos gp = GestorPermisos.instanciar();
        if (!gp.crearPedidos(usuarioLogueado))
            return ERROR_PERMISOS;
        
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
                String resultado = this.escribirArchivo();
                return (resultado.equals(ESCRITURA_OK) ? EXITO : resultado);
            }
            else //ya existe un pedido con ese número
                return PEDIDOS_DUPLICADOS;
        }        
    }

    @Override
    public String modificarPedido(Usuario usuarioLogueado, Pedido pedidoAModificar, LocalDate fecha, LocalTime hora, List<ProductoDelPedido> productosDelPedido, Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    private String validarPedido(LocalDate fecha, LocalTime hora, List<ProductoDelPedido> productosDelPedido, Cliente cliente) {
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
    
    private String validarPedido(LocalDate fecha, LocalTime hora, List<ProductoDelPedido> productosDelPedido, Estado estado, Cliente cliente) {
        String resultadoValidacion = this.validarPedido(fecha, hora, productosDelPedido, cliente);
        if (resultadoValidacion.equals(VALIDACION_EXITO)) {
            if (!this.validarEstado(estado))
                return ERROR_ESTADO;
            
            return VALIDACION_EXITO;
        }
        else
            return resultadoValidacion;
    }

    @Override
    public String cancelarPedido(Usuario usuarioLogueado, Pedido pedido) {
        IGestorPermisos gp = GestorPermisos.instanciar();
        if (!gp.cancelarPedidos(usuarioLogueado))
            return ERROR_PERMISOS;
        
        if (pedido.verEstado() == Estado.CREANDO) {
            Cliente cliente = pedido.verCliente();
            if (usuarioLogueado.verPerfil() == Perfil.CLIENTE) { //el cliente sólo puede cancelar un pedido propio                
                if (usuarioLogueado.equals(cliente)) { //el usuario logueado quiere cancelar un pedido propio                    
                    cliente.cancelarPedido(pedido);
                    this.pedidos.remove(pedido);
                    String resultado = this.escribirArchivo();
                    return (resultado.equals(ESCRITURA_OK) ? EXITO : ESCRITURA_ERROR);                
                }
                else //el usuario logueado quiere cancelar un pedido ajeno
                    return ERROR_PERMISOS;
                
            }
            else { //un empleado o un encargado pueden cancelar cualquier pedido
                cliente.cancelarPedido(pedido);
                this.pedidos.remove(pedido);
                String resultado = this.escribirArchivo();
                return (resultado.equals(ESCRITURA_OK) ? EXITO : ESCRITURA_ERROR);                
            }                        
        }
        else
            return ERROR_CANCELAR;
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
    private boolean validarProductosDelPedido(List<ProductoDelPedido> productosDelPedido) {
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

//    @Override
//    public List<Pedido> buscarPedidos() {
//        return this.pedidos;
//    }

    @Override
    public List<Pedido> verPedidos(Usuario usuarioLogueado) {
        IGestorPermisos gp = GestorPermisos.instanciar();
        if (!gp.verPedidos(usuarioLogueado)) {
            List<Pedido> listaVacia = new ArrayList<>();
            return listaVacia;
        }
        else {    
            if (usuarioLogueado.verPerfil() == Perfil.ENCARGADO || usuarioLogueado.verPerfil() == Perfil.EMPLEADO)
                return this.pedidos; //todos los pedidos
            else
                return usuarioLogueado.verPedidos(); //sólo los pedidos del cliente
        }
    }
    
    /**
     * Escribe en el archivo de texto el ArrayList
     * Formato del archivo, suponiendo que:
     *      la coma sea el separador, 
     *      " - " sea el separador entre fecha y hora, 
     *      "::" sea el separador entre el producto y la cantidad:
     *  número1,dd/MM/aaaa - hh:mm,código producto1::cantidad1::código producto2::cantidad2::...,estado1,correo1
     *  número2,dd/MM/aaaa - hh:mm,código producto1::cantidad1,estado2,correo1
     *  número3,dd/MM/aaaa - hh:mm,código producto1::cantidad1::código producto2::cantidad2::código producto3::cantidad3::...,estado1,correo2
     * @return String  - cadena con el resultado de la operacion (ESCRITURA_ERROR | ESCRITURA_OK)
     */
    private String escribirArchivo() {
        File file = new File(NOMBRE_ARCHIVO);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {     
            for(Pedido pedido : this.pedidos) {
                String cadena = Integer.toString(pedido.verNumero()) + SEPARADOR;
                
                LocalDate fecha = pedido.verFecha();
                String fechaEnCadena = ManejoFechasYHoras.transformarLocalDateEnCadena(fecha);
                cadena += fechaEnCadena + SEPARADOR_FECHA_HORA;
                
                LocalTime hora = pedido.verHora();
                String horaEnCadena = ManejoFechasYHoras.transformarLocalTimeEnCadena(hora);
                cadena += horaEnCadena + SEPARADOR;
                
                for(int i = 0; i < pedido.verProductosDelPedido().size(); i++) {
                    ProductoDelPedido pdp = pedido.verProductosDelPedido().get(i);
                    cadena += Integer.toString(pdp.verProducto().verCodigo()) + SEPARADOR_PRODUCTO_CANTIDAD;
                    cadena += Integer.toString(pdp.verCantidad()); // + SEPARADOR;
                    if (i == pedido.verProductosDelPedido().size() - 1) //último
                        cadena += SEPARADOR;
                    else
                        cadena += SEPARADOR_PRODUCTO_CANTIDAD;
                }
                
                cadena += pedido.verEstado().toString() + SEPARADOR;
                
                cadena += pedido.verCliente().verCorreo();

                bw.write(cadena);
                bw.newLine();
            }
            return ESCRITURA_OK;
        } 
        catch (IOException ioe) {
            return ESCRITURA_ERROR;            
        }
    }
    
    /**
     * Lee del archivo de texto y carga el ArrayList empleando un try con
     * recursos
     * https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
     * Formato del archivo, suponiendo que:
     *      la coma sea el separador, 
     *      " - " sea el separador entre fecha y hora, 
     *      "::" sea el separador entre el producto y la cantidad:
     *  número1,dd/MM/aaaa - hh:mm,cantidad de productos,código producto1,cantidad1,código producto2,cantidad2,...,correo1
     *  número2,dd/MM/aaaa - hh:mm,cantidad de productos,código producto1,cantidad1,correo1
     *  número3,dd/MM/aaaa - hh:mm,cantidad de productos,código producto1,cantidad1,código producto2,cantidad2,código producto3,cantidad3,...,correo2
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
                
                int numero = Integer.parseInt(vector[0]);
                
                String fechaYHoraEnCadena = vector[1];
                LocalDateTime fechaYHora = this.transformarCadenaALocalDateTime(fechaYHoraEnCadena);                
                
                String productosYCantidades = vector[2];
                List<ProductoDelPedido> productosDelPedido = this.transformarCadenaALista(productosYCantidades);
                
                Estado estado = Estado.verEstado(vector[3]);
                        
                String correo = vector[4];
                Cliente cliente = this.obtenerCliente(correo);
                
                Pedido pedido = new Pedido(numero, fechaYHora, productosDelPedido, estado, cliente);
                this.pedidos.add(pedido);
                cliente.agregarPedido(pedido);
            }
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
    
    private LocalDateTime transformarCadenaALocalDateTime(String cadena) {
        String[] vector = cadena.split(SEPARADOR_FECHA_HORA);
        String fechaEnCadena = vector[0];
        String horaEnCadena = vector[1];
        LocalDate fecha = ManejoFechasYHoras.transformarCadenaALocalDate(fechaEnCadena);
        LocalTime hora = ManejoFechasYHoras.transformarCadenaALocalTime(horaEnCadena);
        return LocalDateTime.of(fecha, hora);
    }
    
    private List<ProductoDelPedido> transformarCadenaALista(String productosYCantidades) {
        List<ProductoDelPedido> productosDelPedido = new ArrayList<>();
        
        String[] vector = productosYCantidades.split(SEPARADOR_PRODUCTO_CANTIDAD);
        int i = 0;
        IGestorProductos gp = GestorProductos.instanciar();
        while(i < vector.length - 1) {
            int codigo = Integer.parseInt(vector[i]);
            Producto producto = gp.obtenerProducto(codigo);
            int cantidad = Integer.parseInt(vector[i + 1]);
            productosDelPedido.add(new ProductoDelPedido(producto, cantidad));
            i += 2;
        }
        
        return productosDelPedido;
    }
    
    private Cliente obtenerCliente(String correo) {
        IGestorUsuarios gu = GestorUsuarios.instanciar();
        Usuario u = gu.obtenerUsuario(correo);
        if (u != null && u.verPerfil() == Perfil.CLIENTE)
            return (Cliente)u;
        else
            return null;
    }
}
