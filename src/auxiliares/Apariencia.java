/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliares;

import javax.swing.UIManager;

/**
 * Clase para manejar la apariencia de las ventanas
 */
public class Apariencia {
    
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
