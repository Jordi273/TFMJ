package org.quickstart.TFMJ;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        javax.swing.SwingUtilities.invokeLater(() -> {

            new Controller();
        });
        
        
    }
}
