/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.modelos;

import interfaces.IGestorUsuarios;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author root
 */
public class ModeloTablaUsuarios extends AbstractTableModel {
    public static final String COLUMNA_APELLIDO = "Apellido";    
    public static final String COLUMNA_NOMBRE = "Nombre";
    public static final String COLUMNA_PERFIL = "Perfil";
    //constantes para los nombres de las columnas
    
    private List<Usuario> usuarios = new ArrayList<>();
    //los datos los saca de GestorUsuarios
    
    private List<String> nombresColumnas = Arrays.asList(new String[] {COLUMNA_APELLIDO, COLUMNA_NOMBRE, COLUMNA_PERFIL});       
    //colección para guardar los nombres de las columnas
    
    /**
    * Constructor
    * @param usuarioLogueado usuario actualmente logueado
    * @param apellido apellido para filtrar la búsqueda de usuarios
    */                                                        
    public ModeloTablaUsuarios(Usuario usuarioLogueado, String apellido) {
        IGestorUsuarios gu = GestorUsuarios.instanciar();
        this.usuarios = gu.buscarUsuarios(usuarioLogueado, apellido);
    }
    
    /**
    * Constructor
    * @param usuarioLogueado usuario actualmente logueado
    */                                                        
    public ModeloTablaUsuarios(Usuario usuarioLogueado) {
        IGestorUsuarios gu = GestorUsuarios.instanciar();
        this.usuarios = gu.verUsuarios(usuarioLogueado);
    }
    
    /**
    * Obtiene el valor de la celda especificada
    * @param fila fila de la celda
    * @param columna columna de la celda
    * @return Object  - valor de la celda
    */                        
    @Override
    public Object getValueAt(int fila, int columna) {
        Usuario usuario = this.usuarios.get(fila);
        switch (columna) {
            case 0: return usuario.verApellido();
            case 1: return usuario.verNombre();
            default: return usuario.verPerfil();
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
        return this.usuarios.size();
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
    * Devuelve el Usuario correspondiente a la fila especificada dentro de la tabla
    * Si se especifica una fila inválida devuelve null
    * @param fila fila dentro de la tabla
    * @return Usuario  - objeto Usuario correspondiente a la fila que se especifica
    * @see Usuario
    */        
    public Usuario verUsuario(int fila) {
        try {
            return this.usuarios.get(fila);
        }
        catch(IndexOutOfBoundsException e) {
            return null;
        }
    }
}
