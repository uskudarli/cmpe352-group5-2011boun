/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applet_algorithm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;
import javax.swing.JApplet;

/**
 *
 * @author Bahtiyar Kaba
 */
public class CanvasApplet extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    public void init() {
                try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    canvasPanel = new CanvasPanel();
                    canvasPanel.setVisible(true);
                    canvasPanel.setBackground(Color.WHITE);
                    bottomMenu= new CanvasAppletMenuBottom();
                    getContentPane().add(canvasPanel,BorderLayout.CENTER);
                    getContentPane().add(bottomMenu,BorderLayout.SOUTH);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static CanvasPanel canvasPanel;
    public static CanvasAppletMenuBottom bottomMenu;
}