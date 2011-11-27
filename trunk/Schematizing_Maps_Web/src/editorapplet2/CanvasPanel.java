/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package editorapplet2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author Bahtiyar Kaba
 */
public class CanvasPanel extends JPanel{

    //panel variables declaration
    public static Vector<MyPoint> points;
    public static MyPoint selectedPoint1;
    public static MyPoint selectedPoint2;
    public static int dragIndex;
    public static int deleteIndex;

    private static JPopupMenu popMenu;
    private static JMenuItem deleteItem;
    //
    public CanvasPanel(){
        points = new Vector<MyPoint>();
        popMenu = new JPopupMenu();
        deleteItem = new JMenuItem("Delete");
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
        popMenu.add(deleteItem);
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
                                MyPoint bigger = selectedPoint1;
                                MyPoint smaller = selectedPoint2;
                                if(Point.distance(bigger.p.x, bigger.p.y, 0, 0)<Point.distance(smaller.p.x, smaller.p.y, 0, 0)){
                                    smaller = selectedPoint1;
                                    bigger = selectedPoint2;
                                }
                                int j;
                                inside:for(j=0;j<smaller.outgoingPoints.size();j++){
                                    if(smaller.outgoingPoints.get(j)==bigger)
                                        break inside;
                                }
                                if(j== smaller.outgoingPoints.size())
                                {
                                    if(smaller == selectedPoint1){
                                        selectedPoint1.outgoingPoints.add(selectedPoint2);
                                    } else selectedPoint2.outgoingPoints.add(selectedPoint1);
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

    }

    public Dimension getPreferredSize() {
		return new Dimension(500,500);
	}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0;i<points.size();i++){
            points.get(i).drawMe(g);
            if(points.get(i) == selectedPoint1 || points.get(i) == selectedPoint2){
                points.get(i).drawSelectedHandle(g);
            }
        }
    }

}
