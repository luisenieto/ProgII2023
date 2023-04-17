/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.modelos;

import auxiliares.ManejoFechasYHoras;
import interfaces.IGestorPedidos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;

/**
 *
 * @author root
 */
public class ModeloTablaPedidos extends AbstractTableModel {    
    private Usuario usuarioLogueado;
    
    public static final String COLUMNA_NUMERO = "Número";    
    public static final String COLUMNA_FECHA = "Fecha";
    public static final String COLUMNA_HORA = "Hora";
    public static final String COLUMNA_CLIENTE = "Cliente";
    public static final String COLUMNA_ESTADO = "Estado";
    //constantes para los nombres de las columnas
    
    private List<Pedido> pedidos = new ArrayList<>();
    //los datos los saca de GestorPedidos
    
    private List<String> nombresColumnas;// = Arrays.asList(new String[] {COLUMNA_NUMERO, COLUMNA_FECHA, COLUMNA_HORA, COLUMNA_ESTADO});       
    //colección para guardar los nombres de las columnas
    
    /**
    * Constructor
    * Permite mostrar los pedidos de todos los clientes
    */                                                        
//    public ModeloTablaPedidos() {
//        this.mostrarCliente = true;
//        this.nombresColumnas = Arrays.asList(new String[] {COLUMNA_NUMERO, COLUMNA_FECHA, COLUMNA_HORA, COLUMNA_CLIENTE, COLUMNA_ESTADO});       
//        //si se muestran los pedidos de todos los clientes, se debe usar una columna para mostrar el nombre del clinte
//        IGestorPedidos gp = GestorPedidos.instanciar();
//        this.pedidos = gp.verPedidos(); 
//    }
    
    /**
    * Constructor
    * Permite mostrar sólo los pedidos de un cliente
    * @param usuarioLogueado usuario actualmente logueado
    */                                                        
    public ModeloTablaPedidos(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        if (usuarioLogueado.verPerfil() == Perfil.CLIENTE) {
            this.nombresColumnas = Arrays.asList(new String[] {COLUMNA_NUMERO, COLUMNA_FECHA, COLUMNA_HORA, COLUMNA_ESTADO});       
            //si se muestran sólo los pedidos de un cliente, no hace falta usar una columna para mostrarlo
        }
        else {
            this.nombresColumnas = Arrays.asList(new String[] {COLUMNA_NUMERO, COLUMNA_FECHA, COLUMNA_HORA, COLUMNA_CLIENTE, COLUMNA_ESTADO});       
            //si se muestran los pedidos de todos los clientes, se debe usar una columna para mostrar el nombre del clinte
        }
        
        IGestorPedidos gp = GestorPedidos.instanciar();
        //Al instanciar GestorPedidos se leen del archivo los pedidos
        //y se agregan a los clientes los suyos
        this.pedidos = usuarioLogueado.verPedidos();
    }
    
    /**
    * Obtiene el valor de la celda especificada
    * @param fila fila de la celda
    * @param columna columna de la celda
    * @return Object  - valor de la celda
    */                        
    @Override
    public Object getValueAt(int fila, int columna) {
        Pedido pedido = this.pedidos.get(fila);
        if (this.usuarioLogueado.verPerfil() == Perfil.CLIENTE) {
            switch (columna) {
                case 0: return pedido.verNumero();
                case 1: return ManejoFechasYHoras.transformarLocalDateEnCadena(pedido.verFecha());
                case 2: return ManejoFechasYHoras.transformarLocalTimeEnCadena(pedido.verHora());
                default: return pedido.verEstado();                
            }
        }
        else {
            switch (columna) {
                case 0: return pedido.verNumero();
                case 1: return ManejoFechasYHoras.transformarLocalDateEnCadena(pedido.verFecha());
                case 2: return ManejoFechasYHoras.transformarLocalTimeEnCadena(pedido.verHora());
                case 3: return pedido.verCliente().verApellido() + ", " + pedido.verCliente().verNombre();
                default: return pedido.verEstado();
            }            
        }
    }
    
    /**
    * Obtiene la cantidad de columnas de la tabla
    * @return int  - cantidad de columnas de la tabla
    */                            
    @Override
    public int getColumnCount() { 
        return this.nombresColumnas.size();
    }

    /**
    * Obtiene la cantidad de filas de la tabla
    * @return int  - cantidad de filas de la tabla
    */                        
    @Override
    public int getRowCount() { 
        return this.pedidos.size();
    }

    /**
    * Obtiene el nombre de una columna
    * @param columna columna sobre la que se quiere obtener el nombre
    * @return String  - nombre de la columna especificada
    */                        
    @Override
    public String getColumnName(int columna) {
        return this.nombresColumnas.get(columna);
    }
    
    /**
    * Devuelve el Pedido correspondiente a la fila especificada dentro de la tabla
    * Si se especifica una fila inválida devuelve null
    * @param fila fila dentro de la tabla
    * @return Pedido  - objeto Pedido correspondiente a la fila que se especifica
    * @see Pedido
    */        
    public Pedido verPedido(int fila) {
        try {
            return this.pedidos.get(fila);
        }
        catch(IndexOutOfBoundsException e) {
            return null;
        }
    }
}
