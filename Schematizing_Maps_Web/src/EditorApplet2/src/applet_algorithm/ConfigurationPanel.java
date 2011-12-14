/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConfigurationPanel.java
 *
 * Created on 23.Kas.2011, 01:04:39
 */

package applet_algorithm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Bahtiyar Kaba
 */
public class ConfigurationPanel extends javax.swing.JPanel {

    /** Creates new form ConfigurationPanel */
    public ConfigurationPanel() {
        
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        itemsPanel = new javax.swing.JPanel();
        angleMultipleComboBox = new javax.swing.JComboBox();
        angleMultipleLabel = new javax.swing.JLabel();
        eastWest = new javax.swing.JCheckBox();
        distance = new javax.swing.JCheckBox();
        northSouth = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(200, 225));

        jRadioButton1.setText("Use default configuration");
        jRadioButton1.setName("jRadioButton1"); // NOI18N
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jRadioButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton1StateChanged(evt);
            }
        });;

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setText("Configuration Properties");
        jLabel2.setName("jLabel2"); // NOI18N

        itemsPanel.setName("itemsPanel"); // NOI18N

        angleMultipleComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "45", "22.5", "90", "180" }));
        angleMultipleComboBox.setName("angleMultipleComboBox"); // NOI18N
         angleMultipleComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                angleMultipleComboBoxActionPerformed(evt);
            }
        });

        angleMultipleLabel.setText("Angle Multiple");
        angleMultipleLabel.setName("angleMultipleLabel"); // NOI18N

        eastWest.setText("East-West preservation");
        eastWest.setName("eastWest"); // NOI18N
        eastWest.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                eastWestStateChanged(evt);
            }
        });

        distance.setText("Distance Preservation");
        distance.setName("distance"); // NOI18N
        distance.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                distanceStateChanged(evt);
            }
        });

        northSouth.setText("North-South preservation");
        northSouth.setName("northSouth"); // NOI18N
        northSouth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                northSouthStateChanged(evt);
            }
        });

        javax.swing.GroupLayout itemsPanelLayout = new javax.swing.GroupLayout(itemsPanel);
        itemsPanel.setLayout(itemsPanelLayout);
        itemsPanelLayout.setHorizontalGroup(
            itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(distance)
                    .addComponent(eastWest)
                    .addComponent(northSouth)
                    .addGroup(itemsPanelLayout.createSequentialGroup()
                        .addComponent(angleMultipleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(angleMultipleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        itemsPanelLayout.setVerticalGroup(
            itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(eastWest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(northSouth)
                .addGap(3, 3, 3)
                .addComponent(distance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(angleMultipleLabel)
                    .addComponent(angleMultipleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(itemsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(9, 9, 9)
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addComponent(itemsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(391, 391, 391))
        );
    }// </editor-fold>

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if(defaultconfig){
            eastWest.setEnabled(true);
            northSouth.setEnabled(true);
            distance.setEnabled(true);
            angleMultipleLabel.setEnabled(true);
            angleMultipleComboBox.setEnabled(true);     
            defaultconfig = false;
        }else{
            itemsPanel.setEnabled(false);
            eastWest.setEnabled(false);
            northSouth.setEnabled(false);
            distance.setEnabled(false);
            angleMultipleLabel.setEnabled(false);
            angleMultipleComboBox.setEnabled(false);
            defaultconfig =true;
        }
    }

    private void distanceStateChanged(ChangeEvent e){
        if(distance.isSelected()){
            if(distance.isEnabled())
            {
                CanvasApplet.dist = true;
            }

        } else {
            if(distance.isEnabled())
            {
                CanvasApplet.dist = false;
            }
        }
    }
    private void eastWestStateChanged(ChangeEvent e){
        if(eastWest.isSelected()){
            if(eastWest.isEnabled())
            {
                CanvasApplet.ew = true;
            }
        } else {
            if(eastWest.isEnabled())
            {
            CanvasApplet.ew = false;
            }
        }
    }
    private void northSouthStateChanged(ChangeEvent e){
        if(northSouth.isSelected()){
            if(northSouth.isEnabled())
            {
                CanvasApplet.ns = true;
            }
        } else {
            if(northSouth.isEnabled())
            {
                CanvasApplet.ns = false;
            }
        }
    }
    private void angleMultipleComboBoxActionPerformed(ActionEvent e){
        CanvasApplet.angleMultiple = Double.parseDouble(angleMultipleComboBox.getSelectedItem().toString());
    }
    private void jRadioButton1StateChanged(ChangeEvent e){
        if(jRadioButton1.isSelected()){
            CanvasApplet.isDefault = true;
        }else {
            CanvasApplet.isDefault = false;
            updateMe();         
        }
    }
    private void updateMe(){
        if(distance.isSelected())
            CanvasApplet.dist = true;
        else CanvasApplet.dist = false;
        if(eastWest.isSelected())
            CanvasApplet.ew = true;
        else CanvasApplet.ew = false;
        if(northSouth.isSelected())
            CanvasApplet.ns = true;
        else CanvasApplet.ns = false;
        CanvasApplet.angleMultiple = Double.parseDouble(angleMultipleComboBox.getSelectedItem().toString());
    }


    // Variables declaration - do not modify
    private javax.swing.JComboBox angleMultipleComboBox;
    private javax.swing.JLabel angleMultipleLabel;
    private javax.swing.JCheckBox distance;
    private javax.swing.JCheckBox eastWest;
    private javax.swing.JPanel itemsPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JCheckBox northSouth;
    // End of variables declaration

    private boolean defaultconfig = false;
    public FileuploadPanel fileUpload;
}
