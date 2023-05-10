/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.controladores;

import javax.swing.UIManager;
import productos.vistas.VentanaAMProducto;
import usuarios.vistas.VentanaAMCliente;
import usuarios.vistas.VentanaAMEmpleado;
import usuarios.vistas.VentanaAMEncargado;

/**
 *
 * @author root
 */
public class ControladorPrincipalGUI {
    public static void main(String[] args) {
        establecerLookAndFeel("Nimbus"); 
        //asigna el look and feel "Nimbus" a la ventana
        
        //Se crea la ventana
        VentanaAMProducto ventana = new VentanaAMProducto(null);
        
        //Se centra la ventana
        ventana.setLocationRelativeTo(null);
        
        ventana.setTitle("Nuevo producto");
        //Se asigna un título a la ventana
        
        //Se hace visible la ventana
        ventana.setVisible(true);
        

/*
        //Se crea la ventana
        VentanaAMCliente ventana = new VentanaAMCliente(null);
        
        //Se centra la ventana
        ventana.setLocationRelativeTo(null);
        
        ventana.setTitle("Nuevo cliente");
        //Se asigna un título a la ventana
        
        //Se hace visible la ventana
        ventana.setVisible(true);
*/        
        
/*
        //Se crea la ventana
        VentanaAMEmpleado ventana = new VentanaAMEmpleado(null);
        
        //Se centra la ventana
        ventana.setLocationRelativeTo(null);
        
        ventana.setTitle("Nuevo empleado");
        //Se asigna un título a la ventana
        
        //Se hace visible la ventana
        ventana.setVisible(true);
*/

/*
        //Se crea la ventana
        VentanaAMEncargado ventana = new VentanaAMEncargado(null);
        
        //Se centra la ventana
        ventana.setLocationRelativeTo(null);
        
        ventana.setTitle("Nuevo encargado");
        //Se asigna un título a la ventana
        
        //Se hace visible la ventana
        ventana.setVisible(true);
*/
    }
    
    /**
     * Asigna el look and feel especificado a la ventana
     * @param laf cadena con el nombre del look and feel
     */
    public static void establecerLookAndFeel(String laf) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (laf.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } 
            catch (Exception e2) {
            }
        }
    }
}
