/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applet_algorithm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 *
 * @author bibi
 */
public class SearchResultPanel extends JDialog{
    Vector<Map> searchResults;

    public SearchResultPanel(Vector<Map> res){
        searchResults = res;
        Vector<String> str = new Vector<String>();
        for(int i=0;i<res.size();i++){
            str.add(res.get(i).getMapName()+" - "+res.get(i).getMapOwner());
        }

        listView = new JList(str.toArray());
        listView.setVisibleRowCount(10);
        listView.setAutoscrolls(true);
        listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Map map = searchResults.get(listView.getSelectedIndex());
                CanvasApplet.setMap(map);
                
                CanvasApplet.canvasPanel.repaint();
                //dispose();
            }

        });



        setLayout(new FlowLayout());
        setTitle("Search results");
        setLocation(new Point(200, 200));
        add(listView);
        add(loadButton);
        pack();
        setVisible(true);
    }

    private static List<String> getTagValues(final String str,Pattern pat) {
    final List<String> tagValues = new ArrayList<String>();
    final Matcher matcher = pat.matcher(str);
    while (matcher.find()) {
        tagValues.add(matcher.group(1));
    }
    return tagValues;
    }

    private JList listView;
    private JButton loadButton;
}
