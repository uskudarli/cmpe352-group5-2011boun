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
    ConfigurationPanel configPanel;
    FileuploadPanel fileUpload;
    public LeftPanel(){
    configPanel = new ConfigurationPanel();
    fileUpload = new FileuploadPanel();
    setLayout(new BorderLayout());
    if(CanvasApplet.isAdvanced){
        add(configPanel,BorderLayout.CENTER);
        add(fileUpload,BorderLayout.SOUTH);
    } else {
            add(fileUpload,BorderLayout.CENTER);
    }
    }

}
