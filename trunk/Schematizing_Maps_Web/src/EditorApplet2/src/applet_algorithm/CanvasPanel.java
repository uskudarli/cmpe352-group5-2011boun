/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applet_algorithm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 *
 * @author Bahtiyar Kaba
 */
public class CanvasPanel extends JPanel{

    //panel variables declaration
    public static Vector<MyPoint> points;
    public static Vector<Connection> connections;
    public static MyPoint selectedPoint1;
    public static MyPoint selectedPoint2;
    public static String mapDescription;
    public static int dragIndex;
    public static int deleteIndex;

    private static JPopupMenu popMenu;
    private static JMenuItem deleteItem;
    private static JTextField addDescription;
    //
    public CanvasPanel(){
        points = new Vector<MyPoint>();
        connections =new Vector<Connection>();
        mapDescription = "";
        popMenu = new JPopupMenu();
        deleteItem = new JMenuItem("Delete");
        addDescription = new JTextField("");
        addDescription.setToolTipText("Add a description");
        deleteItem.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                     if(deleteIndex !=-1){
                         for(int i=0;i<points.size();i++){
                             for(int j=0;j<points.get(i).outgoingPoints.size();j++){
                                 if(points.get(deleteIndex).point_id == points.get(i).outgoingPoints.get(j).point_id){
                                 points.get(i).outgoingPoints.remove(j);
                                 }
                             }
                         }
                         if(selectedPoint1==points.get(deleteIndex))
                             selectedPoint1 = null;
                         points.remove(deleteIndex);
                     }
                     repaint();
            }

        });
        addDescription.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                     if(deleteIndex !=-1){
                         points.get(deleteIndex).description=CanvasPanel.addDescription.getText();
                         CanvasPanel.addDescription.setText("");
                     }
                     repaint();
            }
        });
        popMenu.add(deleteItem);
        popMenu.add(addDescription);
        deleteIndex = -1;
        dragIndex = -1;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));



        addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON1){
                    //handle left click event
                    int i;
                    loop1:for(i =0;i<points.size();i++){
                        if(points.get(i).isMyNeighbour(e.getPoint())){
                            if(selectedPoint1 == null){
                                selectedPoint1 = points.get(i);
                            } else if(!selectedPoint1.isMyNeighbour(e.getPoint())) {
                                selectedPoint2 = points.get(i);
                                int j;
                                loop2:for(j=0;j<selectedPoint1.outgoingPoints.size();j++){
                                    if(selectedPoint2 == selectedPoint1.outgoingPoints.get(j))
                                        break loop2;
                                }
                                if(j == selectedPoint1.outgoingPoints.size()){
                                    //selectedpoint1 does not have seleceted point 2 as the outgoing point
                                    selectedPoint1.outgoingPoints.add(selectedPoint2);
                                   selectedPoint2.outgoingPoints.add(selectedPoint1); //bu eklendi! deneme:Ervin
                                   connections.add(new Connection(selectedPoint1,selectedPoint2,CanvasApplet.currentColor));

                                   
                                }
                                selectedPoint1 = points.get(i);
                                selectedPoint2 = null;
                            }
                            break loop1;
                        }
                    }
                    if(i==points.size()){
                        //no points is selected
                        if(selectedPoint1 == null){
                        points.add(new MyPoint(e.getX(),e.getY()));
                        selectedPoint1 = null;
                        } else {
                            selectedPoint1 = null;
                        }
                    }

                    repaint();
                } else if(e.getButton() == MouseEvent.BUTTON3){
                    //handle right click

                    loop2:for(int i=0;i<points.size();i++){
                        if(points.get(i).isMyNeighbour(e.getPoint())){
                            popMenu.show(e.getComponent(), e.getX(), e.getY());
                            deleteIndex = i;
                            break loop2;
                        }
                    }

                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                for(int i=0;i<points.size();i++){
                    if(points.get(i).isMyNeighbour(e.getPoint())){
                        dragIndex = i;
                        break;
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                dragIndex = -1;
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }



        });
        addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                if(dragIndex !=-1){
                    points.get(dragIndex).p = e.getPoint();

                    repaint();
                }
            }
        });

        setSize(500,500);

    }

    public Dimension getPreferredSize() {
                return new Dimension(500,500);
        }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(backgroundImage != null){
            g.drawImage(backgroundImage,0,0,this);
        }
        for(int i=0;i<points.size();i++){
            points.get(i).drawMe(g);
            if(points.get(i) == selectedPoint1 || points.get(i) == selectedPoint2){
                points.get(i).drawSelectedHandle(g);
            }
        }
    }

    public MyPoint returnSmallesPoint(){
        MyPoint min = points.get(0);
        for(int i=0;i<points.size();i++){
            if((Point.distance(0, 0, points.get(i).getX(), points.get(i).getY()))<(Point.distance(0, 0, min.getX(), min.getY()))){
                min = points.get(i);
            }
        }
        return min;
    }
    public static Image backgroundImage = null;

}

class Connection{
    public MyPoint p1;
    public MyPoint p2;
    public Color c;
    public ArrayList<Connection> startingPoints;
    public Connection(){
        p1=null;
        p2=null;
        c=Color.red;
    }

    public Connection(MyPoint p_1,MyPoint p_2,Color c_){
        p1=p_1;
        p2=p_2;
        c=c_;
    }

    public  ArrayList<Connection> findStartingPoints(){
        //find starting points.
        startingPoints =new ArrayList<Connection>();
        Color temp = null;
        for(int i=0;i<CanvasPanel.connections.size();i++){
            temp = CanvasPanel.connections.get(i).c;
            int j;
            inner:for(j=0;j<startingPoints.size();j++){
                if(temp==startingPoints.get(j).c){
                    break inner;
                }
            }
            Connection conn = CanvasPanel.connections.get(i);
            if(j==startingPoints.size()){
                //no same color found.
                if(Point.distance(conn.p1.getX(), conn.p1.getY(), 0, 0)<Point.distance(conn.p2.getX(), conn.p2.getY(), 0, 0)){
                    startingPoints.add(new Connection(conn.p1,null,conn.c));
                } else {
                    startingPoints.add(new Connection(conn.p2,null,conn.c));
                }
            } else {
                //same color found.
                Connection conn2 = startingPoints.get(j);
                if(Point.distance(conn.p1.getX(), conn.p1.getY(), 0, 0)<Point.distance(conn2.p1.getX(), conn2.p1.getY(), 0, 0)){
                    startingPoints.get(j).p1=conn.p1;
                }
                if(Point.distance(conn.p2.getX(), conn.p2.getY(), 0, 0)<Point.distance(conn2.p1.getX(), conn2.p1.getY(), 0, 0))
                    {
                    startingPoints.get(j).p1=conn.p2;
                }
            }
        }
        return startingPoints;
    }
}