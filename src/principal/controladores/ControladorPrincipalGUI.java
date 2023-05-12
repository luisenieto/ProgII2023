/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.controladores;

import interfaces.IControladorPrincipal;
import interfaces.IControladorProductos;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import principal.vistas.VentanaPrincipal;
import productos.controladores.ControladorProductos;


/**
 *
 * @author root
 */
public class ControladorPrincipalGUI implements IControladorPrincipal {
    private VentanaPrincipal ventana; 

    public ControladorPrincipalGUI() {
        this.ventana = new VentanaPrincipal(this);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setTitle(this.generarTituloVentana());
        this.ventana.setVisible(true);       
    }
    
    /**
     * Genera el título que se muestra en la ventana
     * @return String  - título que se muestra en la ventana
    */
    private String generarTituloVentana() {  
        return TITULO;
    }
    
    @Override
    public void btnUsuariosClic(ActionEvent evt) {
        
    }

    @Override
    public void btnProductosClic(ActionEvent evt) {
        IControladorProductos cp = new ControladorProductos(this.ventana);
    }

    @Override
    public void btnPedidosClic(ActionEvent evt) {
        
    }

    @Override
    public void btnSalirClic(ActionEvent evt) {
        this.ventana.dispose();
    }
    
    
    public static void main(String[] args) {
        establecerLookAndFeel("Nimbus"); 
        //asigna el look and feel "Nimbus" a la ventana

        IControladorPrincipal cp = new ControladorPrincipalGUI();
        
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
