/*
 * To change this template, choose Tools | Templates
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CanvasAppletMenuBottom.java
 *
 * Created on 23.Kas.2011, 00:28:13
 */

package applet_algorithm;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.jibble.simpleftp.SimpleFTP;
/**
 *
 * @author bibi
 */
public class CanvasAppletMenuBottom extends javax.swing.JPanel {

    /** Creates new form CanvasAppletMenuBottom */
    public CanvasAppletMenuBottom() {
        initComponents();
        schematize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schematizeActionPerformed(evt);
            }
        });
        searchButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                searchButtonActionPerformed();
            }

        });
        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                saveButtonActionPerformed();
            }
        });
                exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        schematize = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        searchKeys = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();


        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(500, 50));

        schematize.setText("Start Schematizing");
        schematize.setName("schematize"); // NOI18N
        searchButton.setText("Search Maps");
        saveButton.setText("Save Map");
        exportButton.setText("Export");
       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(schematize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveButton)
                .addGap(18, 18, 18)
                .addComponent(exportButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(searchKeys, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(searchButton)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(schematize, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(searchButton)
                    .addComponent(searchKeys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton)
                    .addComponent(exportButton))
                .addContainerGap())
        );
    }// </editor-fold>

    private void schematizeActionPerformed(ActionEvent e){
        MyPoint p = new MyPoint(0,0);
        Vector<MyPoint> temp = new Vector<MyPoint>();
        Vector<MyPoint> accs = new Vector<MyPoint>();
        //p.outgoingPoints.add(CanvasApplet.canvasPanel.returnSmallesPoint());
        try{

            if(CanvasApplet.isDefault){
                ArrayList<Connection> strpoints = CanvasApplet.canvasPanel.connections.get(0).findStartingPoints();
                for(int i=0;i<strpoints.size();i++){
                    p.outgoingPoints.add(strpoints.get(i).p1);
                    Algorithm alg = new Algorithm(p,"",1);
                    p = alg.Schematize();
                    accs = p.returnaccesiblepoints();
                    for(int j=0;j<accs.size();j++){
                        temp.add(accs.get(j));
                    }
                    p= new MyPoint(0,0);
                }
            } else{
                ArrayList<Connection> strpoints = CanvasApplet.canvasPanel.connections.get(0).findStartingPoints();
                for(int i=0;i<strpoints.size();i++){
                    p.outgoingPoints.add(strpoints.get(i).p1);
                    Algorithm deneme = new Algorithm(p,"",1,CanvasApplet.angleMultiple,CanvasApplet.ew,CanvasApplet.ns,CanvasApplet.dist);
                    p = deneme.Schematize();
                    accs = p.returnaccesiblepoints();
                    for(int j=0;j<accs.size();j++){
                        temp.add(accs.get(j));
                    }
                    p= new MyPoint(0,0);
                }
            }
        CanvasApplet.canvasPanel.points=temp;
        schematize.setEnabled(false);
        CanvasApplet.canvasPanel.repaint();
        }catch(IOException f){}
    }
    private void searchButtonActionPerformed(){
        //SEARCH OLAYI BURADA OLACAK!!! @EYLUL,YEKTA,OZGUR
        Vector<Map> searchResults = mysql_UTIL.searchMaps(CanvasApplet.username, searchKeys.getText());
        if(searchResults == null){
                    JOptionPane.showMessageDialog(null, "No results found.");
        } else{
                 new SearchResultPanel(searchResults);
        }
    }

    private void saveButtonActionPerformed(){
        
        Map map = new Map(CanvasApplet.topMenu.getDesc(),true,CanvasApplet.canvasPanel.points,CanvasApplet.canvasPanel.connections);
        map.setXMLData();
        map.setKeywords();
        map.setMapOwner(CanvasApplet.username);
        mysql_UTIL.saveMap(map);
    }

    public void enableSchematizing(){
        schematize.setEnabled(true);
    }

      private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {
          //JOptionPane.showMessageDialog(null, "png mi jpg mi");
          String path="";
          JFileChooser fc = new JFileChooser();
          //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
              int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                path =file.getAbsolutePath();

            } else {
            System.out.println("File access cancelled by user.");
            }
            int i=0;

            synchronized(this){
                try{
                    this.wait(1000);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
              



                      Robot objRobot = null;
                 try
                 {
                    objRobot = new Robot();
                 } catch(Exception ex)
                 {

         }

//Create the image
BufferedImage exportImage =objRobot.createScreenCapture(new Rectangle(CanvasApplet.canvasPanel.getLocationOnScreen().x,CanvasApplet.canvasPanel.getLocationOnScreen().y, CanvasApplet.canvasPanel.getLocationOnScreen().x+CanvasApplet.canvasPanel.getSize().width, CanvasApplet.canvasPanel.getLocationOnScreen().y+CanvasApplet.canvasPanel.getSize().height-80));


//Setup to write the BufferedImage to a file
String pathToFile = path;
File outputDirectory = new File(pathToFile);
File outputFile = new File(pathToFile+".png");

//Here we make sure the directory exists.
/*
 * Returns TRUE if:
 *  The directory is MISSING
 *  and/or the directory IS NOT a directory
 */
if(!outputDirectory.exists() || !outputDirectory.isDirectory()){
    outputDirectory.mkdirs(); //Make the directory
} // Else do nothing

//Write the file
try { //Attempt the write
    ImageIO.write(exportImage, "png", outputFile);
} catch (IOException e) { //For some reason it failed so...
    e.printStackTrace(); //... why did it fail?
}
SimpleFTP ftp = new SimpleFTP();
        try {
            ftp.connect("titan.cmpe.boun.edu.tr", 8092, "project5", "s8u4p");
            ftp.cwd("/home/project5/tomcat/webapps/Images");
            ftp.stor(outputFile);
            ftp.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(CanvasAppletMenuBottom.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }



        }






    // Variables declaration - do not modify
    private javax.swing.JButton exportButton;
    private javax.swing.JButton schematize;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchKeys;
    // End of variables declaration

    


}

