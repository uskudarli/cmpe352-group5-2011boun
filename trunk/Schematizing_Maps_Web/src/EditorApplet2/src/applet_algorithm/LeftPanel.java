/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applet_algorithm;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author bibi
 */
public class LeftPanel extends JPanel{
    public LeftPanel(){
    ConfigurationPanel configPanel = new ConfigurationPanel();
    FileuploadPanel fileUpload = new FileuploadPanel();
    setLayout(new BorderLayout());
    if(CanvasApplet.isAdvanced){
        add(configPanel,BorderLayout.CENTER);
        add(fileUpload,BorderLayout.SOUTH);
    } else {
            add(fileUpload,BorderLayout.CENTER);
    }
    }

}
