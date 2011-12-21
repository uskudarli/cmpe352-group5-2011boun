/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applet_algorithm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
        Vector<String> str = null;
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
                Vector<Connection> connections = new Vector<Connection>();
                Vector<MyPoint> points = new Vector<MyPoint>();
                String xmldata = map.getXMLFormat();
                String[] edgeArray = (String[]) getTagValues(xmldata,Pattern.compile("<Edge>(.+?)</Edge>")).toArray();
                String[] pointArray = (String[]) getTagValues(xmldata,Pattern.compile("<Point>(.+?)</Point>")).toArray();
                for(int i=0;i<edgeArray.length;i++){
                    String[] x1Points = (String[]) getTagValues(edgeArray[i],Pattern.compile("<Point1_X>(.+?)</Point1_X>")).toArray();
                    String[] y1Points = (String[]) getTagValues(edgeArray[i],Pattern.compile("<Point1_Y>(.+?)</Point1_Y>")).toArray();
                    String[] x2Points = (String[]) getTagValues(edgeArray[i],Pattern.compile("<Point2_X>(.+?)</Point2_X>")).toArray();
                    String[] y2Points = (String[]) getTagValues(edgeArray[i],Pattern.compile("<Point2_Y>(.+?)</Point2_Y>")).toArray();
                    //Color
                    connections.add(new Connection(new MyPoint(Integer.parseInt(x1Points[0]),Integer.parseInt(y1Points[0])),new MyPoint(Integer.parseInt(x2Points[0]),Integer.parseInt(y2Points[0])),Color.BLUE));
                }
                for(int j=0;j<pointArray.length;j++){
                    String[] xPoints = (String[]) getTagValues(pointArray[j],Pattern.compile("<Point_X>(.+?)</Point_X>")).toArray();
                    String[] yPoints = (String[]) getTagValues(pointArray[j],Pattern.compile("<Point_X>(.+?)</Point_X>")).toArray();
                    MyPoint myp = new MyPoint(Integer.parseInt(xPoints[0]),Integer.parseInt(yPoints[0]));
                    String[] desc = (String[]) getTagValues(pointArray[j],Pattern.compile("<Description>(.+?)</Description>")).toArray();
                    myp.description =desc[0];
                    points.add(myp);
                }

                CanvasApplet.canvasPanel.connections = connections;
                CanvasApplet.canvasPanel.points = points;
                CanvasApplet.canvasPanel.repaint();
            }

        });



        setLayout(new BorderLayout());
        setTitle("Search results");
        add(listView);
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
