/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applet_algorithm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Bahtiyar Kaba
 */
public class MyPoint {
    public Point p;
    public ArrayList<MyPoint> outgoingPoints;
    public int point_id;
    public static int lastPoint = 0;
    Vector<MyPoint> temp = new Vector<MyPoint>();

    MyPoint(int x,int y){
        p = new Point(x,y);
        point_id = MyPoint.lastPoint;
        lastPoint++;
        outgoingPoints = new ArrayList<MyPoint>();
    }

    public Vector<MyPoint> returnaccesiblepoints(){
        temp.add(this.outgoingPoints.get(0));
        return_accessible_helper(this.outgoingPoints.get(0));
        return temp;
    }
    public void return_accessible_helper(MyPoint p){
        if(!p.outgoingPoints.isEmpty()){
            for(int i=0;i<p.outgoingPoints.size();i++){
                temp.add(p.outgoingPoints.get(i));
                return_accessible_helper(p.outgoingPoints.get(i));
            }
        }
    }
    public void drawMe(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
        g2.fillOval(p.x-7, p.y-7, 14, 14);
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(3)); // set thickness to 4
        for(int i=0;i<outgoingPoints.size();i++){
            g2.drawLine(p.x, p.y, outgoingPoints.get(i).p.x, outgoingPoints.get(i).p.y);
        }
    }
    public void drawSelectedHandle(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(2));// set thicnknes to three
        g2.drawOval(p.x-10, p.y-10, 20, 20);
    }
    public boolean isMyNeighbour(Point ptemp){
        double dist = Point.distance(p.x, p.y, ptemp.x, ptemp.y);
        if(dist<7)
            return true;
        else return false;
    }
    public int getX(){
        return this.p.x;
    }
    public int getY(){
        return this.p.y;
    }
    public void setX(int _x){
        p.x=_x;
    }
    public void setY(int _y){
        p.y=_y;
    }
}