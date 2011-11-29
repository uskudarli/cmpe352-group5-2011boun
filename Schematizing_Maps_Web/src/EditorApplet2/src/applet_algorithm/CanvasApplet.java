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
                       angleMultiple = 45;
                       ew = false;
                       ns = false;
                       dist = false;
                       isDefault = false;
                    canvasPanel = new CanvasPanel();
                    canvasPanel.setVisible(true);
                    canvasPanel.setBackground(Color.WHITE);
                    leftPanel = new LeftPanel();
                    bottomMenu= new CanvasAppletMenuBottom();
                    getContentPane().add(canvasPanel,BorderLayout.CENTER);
                    getContentPane().add(bottomMenu,BorderLayout.SOUTH);
                    getContentPane().add(leftPanel,BorderLayout.EAST);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static CanvasPanel canvasPanel;
    public static CanvasAppletMenuBottom bottomMenu;
    public static LeftPanel leftPanel;

    //algorithm variables
    public static double angleMultiple;
    public static boolean ew;
    public static boolean ns;
    public static boolean dist;
    public static boolean isDefault;

}