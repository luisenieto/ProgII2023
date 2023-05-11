/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.modelos;

import interfaces.IGestorPedidos;
import interfaces.IGestorPermisos;
import interfaces.IGestorUsuarios;
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

/**
 *
 * @author root
 */
public class GestorUsuarios implements IGestorUsuarios {
    private final String NOMBRE_ARCHIVO = "./Usuarios.txt";
    //nombre del archivo con los usuarios
    private final char SEPARADOR = ':'; 
    //caracter usado como separador   
    
    private static GestorUsuarios gestor;
    
    private List<Usuario> usuarios = new ArrayList<>();
    
    private Usuario usuarioLogueado = null;
    //usuario actualmente logueado
    
    /**
     * Constructor
    */
    private GestorUsuarios() {
        String resultado = this.leerArchivo();
        if ((resultado.equals(LECTURA_ERROR)) || (resultado.equals(CREACION_ERROR))) {
            //JOptionPane.showMessageDialog(null, resultado, IControladorPrincipal.TITULO, JOptionPane.ERROR_MESSAGE);
            //this.problemasConArchivo = true;
        } else {
            //this.problemasConArchivo = false;
        }
    }
    
    /**
     * Método estático que permite crear una única instancia de GestorUsuarios
     * @return GestorUsuarios
    */
    public static GestorUsuarios instanciar() {
        if (gestor == null) {
            gestor = new GestorUsuarios();
        }
        return gestor;
    }
    
    @Override
    public Usuario validarUsuario(String correo, String clave) {
        for(Usuario u : this.usuarios) {
            if (u.verCorreo().equals(correo) && u.verClave().equals(clave)) {
                this.usuarioLogueado = u;
                return this.usuarioLogueado;
            }
        }
        return this.usuarioLogueado;
    }

    @Override
    public Usuario verUsuarioLogueado() {
        return this.usuarioLogueado;
    }

    @Override
    public void cerrarSesion() {
         this.usuarioLogueado = null;
    }

    @Override
    public String crearUsuario(Usuario usuarioLogueado, String correo, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida) {
        Usuario usuario;
        IGestorPermisos gp = GestorPermisos.instanciar();
        List<Perfil> perfilesHabilitados = gp.crearUsuarios(usuarioLogueado);
        if (!perfilesHabilitados.contains(perfil))
            return ERROR_PERMISOS;
        
        String resultadoValidacion = this.validarDatos(correo, apellido, nombre, perfil, clave, claveRepetida);
        if (!resultadoValidacion.equals(VALIDACION_EXITO))
            return resultadoValidacion;               
        else {
            switch(perfil) {
                case ENCARGADO :
                    usuario = new Encargado(correo, clave, apellido, nombre);
                    break;
                case EMPLEADO :
                    usuario = new Empleado(correo, clave, apellido, nombre);
                    break;
                default:
                    usuario = new Cliente(correo, clave, apellido, nombre);
                    break;
            }
            if (!this.usuarios.contains(usuario)) { //no existe el usuario
                    this.usuarios.add(usuario);
                    //Collections.sort(this.usuarios);
                    String resultado = this.escribirArchivo();
                    return (resultado.equals(ESCRITURA_OK) ? EXITO : resultado);
            }
            else //ya existe un usuario con ese correo
                    return USUARIOS_DUPLICADOS;
        }
    }
    
    /**
     * Valida que los datos del usuario sean correctos
     * @param correo correo del usuario
     * @param apellido apellido del usuario
     * @param nombre nombre del usuario
     * @param perfil perfil del usuario
     * @param clave clave del usuario
     * @param claveRepetida clave repetida del usuario
     * @return String  - cadena con el resultado de la operación (ERROR_CORREO || ERROR_APELLIDO || ERROR_NOMBRE || ERROR_PERFIL || ERROR_CLAVES || VALIDACION_EXITO)
    */
    private String validarDatos(String correo, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida) {
        if (!this.validarCorreo(correo))
            return ERROR_CORREO;

        if (!this.validarApellido(apellido))
                return ERROR_APELLIDO;
    
        if (!this.validarNombre(nombre))
            return ERROR_NOMBRE;

        if (!this.validarPerfil(perfil))
            return ERROR_PERFIL;

        if (!this.validarClaves(clave, claveRepetida))
            return ERROR_CLAVES;
        
        return VALIDACION_EXITO;
    }
    
    /**
     * Valida que el correo del usuario tengan el caracter "@"
     * @param correo dni del usuario
     * @return boolean  - true si el correo del usuario tiene el caracter "@"
     */
    private boolean validarCorreo(String correo) {
        return (correo != null) && (correo.contains("@"));
    }
    
    /**
     * Valida que el apellido del usuario sea correcto
     * El apellido es correcto si no es nulo ni una cadena vacía
     * @param apellido apellido del usuario
     * @return boolean  - true si el apellido del usuario es correcto, false en caso contrario
     */
    private boolean validarApellido(String apellido) {
        return (apellido != null) && (!apellido.trim().isEmpty());
    }
    
    /**
     * Valida que el nombre del usuario sea correcto
     * El nombre es correcto si no es nulo ni una cadena vacía
     * @param nombre nombre del usuario
     * @return boolean  - true si el nombre del usuario es correcto, false en caso contrario
    */
    private boolean validarNombre(String nombre) {
        return (nombre != null) && (!nombre.trim().isEmpty());
    }
    
    /**
     * Valida que el perfil del usuario sea correcto
     * El perfil es correcto si no es nulo
     * @param perfil perfil del usuario
     * @return boolean  - true si el perfil del usuario es correcto, false en caso contrario
    */
    private boolean validarPerfil(Perfil perfil) {
        return perfil != null;
    }

        /**
     * Valida que las 2 claves para el usuario sean correctas (no vacías e iguales)
     * @param clave clave del usuario
     * @param claveRepetida clave (repetida) del usuario
     * @return boolean  - true si las 2 claves para el usuario son correctas, false en caso contrario
    */
    private boolean validarClaves(String clave, String claveRepetida) {
        return (clave != null) && 
                (!clave.trim().isEmpty()) && 
                (claveRepetida != null) && 
                (!claveRepetida.trim().isEmpty()) && 
                (clave.equals(claveRepetida));
    }

    @Override
    public String modificarUsuario(Usuario usuarioLogueado, Usuario usuarioAModificar, String correo, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida) {
        if (!this.existeEsteUsuario(usuarioAModificar))
            return USUARIO_INEXISTENTE;
        
        IGestorPermisos gp = GestorPermisos.instanciar();
        if (!gp.modificarUsuarios(usuarioLogueado, usuarioAModificar))
            return ERROR_PERMISOS;        
        
        String resultadoValidacion = this.validarDatos(correo, apellido, nombre, perfil, clave, claveRepetida);
        if (!resultadoValidacion.equals(VALIDACION_EXITO))
            return resultadoValidacion; 
        
        Usuario usuarioNuevo = this.obtenerUsuario(correo);
        if (!usuarioAModificar.equals(usuarioNuevo))
            return USUARIOS_DUPLICADOS;
        
        
        //Si el usuario logueado es encargado, y quiere modificarse a sí mismo, no puede cambiarse el perfil
        if (usuarioLogueado.verPerfil() == Perfil.ENCARGADO && usuarioLogueado.equals(usuarioAModificar) && usuarioLogueado.verPerfil() != perfil)
            return PERFIL_CAMBIO;
        
        //Si el usuario logueado es empleado, y quiere modificar un encargado, no puede
        if (usuarioLogueado.verPerfil() == Perfil.EMPLEADO && usuarioAModificar.verPerfil() == Perfil.ENCARGADO)
            return ERROR_PERMISOS;
        
        //Si el usuario logueado es empleado, y quiere modificar un empleado, no puede
        if (usuarioLogueado.verPerfil() == Perfil.EMPLEADO && usuarioAModificar.verPerfil() == Perfil.EMPLEADO)
            return ERROR_PERMISOS;
        
        //Si el usuario logueado es empleado, y quiere modificar un cliente, no puede cambiarle el perfil a encargado|empleado
        if (usuarioLogueado.verPerfil() == Perfil.EMPLEADO && usuarioAModificar.verPerfil() == Perfil.CLIENTE && (perfil == Perfil.ENCARGADO || perfil == Perfil.EMPLEADO))
            return ERROR_PERMISOS;
        
        //Si el usuario logueado es cliente, no puede cambiar nada a nadie
        if (usuarioLogueado.verPerfil() == Perfil.CLIENTE)
            return ERROR_PERMISOS;        
        else {
            int posicion = this.usuarios.indexOf(usuarioAModificar);
            usuarioAModificar.asignarCorreo(correo);
            usuarioAModificar.asignarApellido(apellido);
            usuarioAModificar.asignarNombre(nombre);
            usuarioAModificar.asignarClave(clave);
            switch(perfil) {
                case ENCARGADO :
                    usuarioAModificar = new Encargado(correo, clave, apellido, nombre);
                    break;
                case EMPLEADO :
                    usuarioAModificar = new Empleado(correo, clave, apellido, nombre);
                    break;
                default:
                    usuarioAModificar = new Cliente(correo, clave, apellido, nombre);
                    break;
            }
            this.usuarios.set(posicion, usuarioAModificar);
            String resultado = this.escribirArchivo();
            return (resultado.equals(ESCRITURA_OK) ? EXITO : resultado);
        }                            
    }
    
    

    @Override
    public List<Usuario> verUsuarios(Usuario usuarioLogueado) {
        IGestorPermisos gp = GestorPermisos.instanciar();
        if (gp.verUsuarios(usuarioLogueado)) {
            Comparator<Usuario> cmp = (u1, u2) -> {
                if (u1.verApellido().compareToIgnoreCase(u2.verApellido()) == 0)
                    return u1.verNombre().compareToIgnoreCase(u2.verNombre());
                else
                    return u1.verApellido().compareToIgnoreCase(u2.verApellido());
            };
            Collections.sort(this.usuarios, cmp);
            return this.usuarios;
        }
        else {
            List<Usuario> listaVacia = new ArrayList<>();
            return listaVacia;
        }
    }

    @Override
    public List<Usuario> buscarUsuarios(Usuario usuarioLogueado, String apellido) {
        List<Usuario> usuariosBuscados = new ArrayList<>();
        IGestorPermisos gp = GestorPermisos.instanciar();
        if (gp.buscarUsuarios(usuarioLogueado)) {
            if (apellido != null) { 
                for(Usuario usuario : this.usuarios) {
                    if (usuario.verApellido().toLowerCase().contains(apellido.toLowerCase()))
                        usuariosBuscados.add(usuario);
                }                
            }
            return usuariosBuscados;
        }
        else 
            return usuariosBuscados;
    }

    @Override
    public String borrarUsuario(Usuario usuarioLogueado, Usuario usuario) {
        IGestorPermisos gPer = GestorPermisos.instanciar();
        if (gPer.borrarUsuarios(usuarioLogueado, usuario)) {
            if (this.existeEsteUsuario(usuario)) {
                if (usuario instanceof Cliente) {
                    IGestorPedidos gp = GestorPedidos.instanciar();
                    if (gp.hayPedidosConEsteCliente((Cliente)usuario))  //hay al menos un pedido con este cliente
                        return PEDIDO_CON_CLIENTE;
                    else { //no hay pedidos con este cliente
                        this.usuarios.remove(usuario);
                        String resultado = this.escribirArchivo();
                        return (resultado.equals(ESCRITURA_OK) ? EXITO : ESCRITURA_ERROR);                
                    }
                }
                else {
                    this.usuarios.remove(usuario);
                    String resultado = this.escribirArchivo();
                    return (resultado.equals(ESCRITURA_OK) ? EXITO : ESCRITURA_ERROR);                
                }
            }
            else
                return USUARIO_INEXISTENTE;
        }
        else
            return ERROR_PERMISOS;
    }

    @Override
    public boolean existeEsteUsuario(Usuario usuario) {
//        if (usuario == null)
//            return false;
//        else
        return this.usuarios.contains(usuario);    
    }

    @Override
    public Usuario obtenerUsuario(String correo) {
        if (correo == null)
            return null;
        else {
            for(Usuario u : this.usuarios) {
                if (u.verCorreo().equals(correo))
                    return u;
            }
            return null;
        }
    }
    
    

    
    /**
     * Lee del archivo de texto y carga el ArrayList empleando un try con
     * recursos
     * https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
     * Formato del archivo: nombre1 nombre2 nombre3
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
                String correo = vector[0];
                String clave = vector[1];
                String apellido = vector[2];
                String nombre = vector[3];
                String cadenaPerfil = vector[4];
                Perfil perfil = Perfil.verPerfil(cadenaPerfil);
                Usuario usuario;
                switch(perfil) {
                    case CLIENTE :
                        usuario = new Cliente(correo, clave, apellido, nombre);
                        break;
                    case EMPLEADO :
                        usuario = new Empleado(correo, clave, apellido, nombre);
                        break;
                    default:  
                        usuario = new Encargado(correo, clave, apellido, nombre);
                        break;
                }                
                this.usuarios.add(usuario);
            }
            return LECTURA_OK;
        } catch (IOException ioe) {
            return LECTURA_ERROR;
        }
    }    
    
    /**
     * Crea el archivo
     *
     * @return String - cadena con el resultado de la operacion (CREACION_OK |
     * CREACION_ERROR)
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
            for(Usuario usuario : this.usuarios) {
                String cadena = usuario.verCorreo() + SEPARADOR;
                cadena += usuario.verClave() + SEPARADOR;
                cadena += usuario.verApellido() + SEPARADOR;
                cadena += usuario.verNombre() + SEPARADOR;
                cadena += usuario.verPerfil();
                bw.write(cadena);
                bw.newLine();
            }
            return ESCRITURA_OK;
        } 
        catch (IOException ioe) {
            return ESCRITURA_ERROR;            
        }
    }         
    
}
