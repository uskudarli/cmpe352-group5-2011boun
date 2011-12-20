/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applet_algorithm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;

/**
 *
 * @author bibi
 */
public class SearchResultPanel extends JDialog{
    Vector<Map> searchResults;

    public SearchResultPanel(Vector<Map> res){
        searchResults = res;
        Vector<String> str = null;
        for(int i=0;i<res.size();i++){
            str.add(res.get(i).getMapName()+" - "+res.get(i).getMapOwner());
        }

        listView = new JList(str.toArray());
        loadButton = new JButton("Load");

        setLayout(new BorderLayout());
        setTitle("Search results");
        add(listView);
        pack();
        setVisible(true);
    }

    private JList listView;
    private JButton loadButton;
}
