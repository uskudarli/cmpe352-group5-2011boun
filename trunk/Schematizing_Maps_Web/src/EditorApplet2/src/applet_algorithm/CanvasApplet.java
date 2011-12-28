/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applet_algorithm;

import java.awt.BorderLayout;
import java.awt.Color;
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
    public static void setMap(Map map){
        canvasPanel.setMap(map);
    }
    public void init() {
                try {
                    
                    
                    
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    //if(getParameter("usertype").equals("Advanced")){
                        isAdvanced = true;
                    //} else
                        isAdvanced =false;
                    //username = getParameter("username");
                    username = "nurettin";
                    canvasPanel = new CanvasPanel();
                    canvasPanel.setVisible(true);
                    canvasPanel.setBackground(Color.WHITE);
                    leftPanel = new LeftPanel();
                    bottomMenu= new CanvasAppletMenuBottom();
                    topMenu = new CanvasPanelMenu();
                    angleMultiple = 45;
                    ew = false;
                    ns = false;
                    dist = false;
                    isDefault = false;
                    currentColor = Color.RED;
                    getContentPane().add(canvasPanel,BorderLayout.CENTER);
                    getContentPane().add(bottomMenu,BorderLayout.SOUTH);
                    getContentPane().add(leftPanel,BorderLayout.EAST);
                    getContentPane().add(topMenu,BorderLayout.NORTH);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static CanvasPanel canvasPanel;
    public static CanvasAppletMenuBottom bottomMenu;
    public static LeftPanel leftPanel;
    public static CanvasPanelMenu topMenu;

    //algorithm variables
    public static double angleMultiple;
    public static boolean ew;
    public static boolean ns;
    public static boolean dist;
    public static boolean isDefault;
    public static boolean isAdvanced;
    public static String username ;

    public static Color currentColor = null;

}