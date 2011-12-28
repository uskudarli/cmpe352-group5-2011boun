/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import applet_algorithm.Map;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import applet_algorithm.mysql_UTIL;

/**
 *
 * @author px5x2
 */
@WebServlet(name = "save_load_Servlet", urlPatterns = {"/save_load_Servlet"})
public class save_load_Servlet extends HttpServlet {
    
    private static mysql_UTIL db = new mysql_UTIL("85.153.22.90", "3306", "project5", "662512", "project5");

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        ObjectOutputStream outputToApplet = null;       // for writing output to the applet
        ObjectInputStream inputFromApplet = null;       // for reading input coming from applet
        Map map = null;
        
        try {
            InputStream is = request.getInputStream();
            inputFromApplet = new ObjectInputStream(is);
            map = (Map) inputFromApplet.readObject();
            
            // we check the user name for integrity
            //
            String user = map.getMapOwner();
            
            if(mysql_UTIL.userExists(user)){
                //
                // save map
                //
                if(mysql_UTIL.saveMap(map)){
                    
                    // save successful
                    // TODO : inform user! btw use the "outputToApplet" stream
                }else{
                    // save unsuccessful
                    // TODO : inform user!
                }
            }else{
                // we cannot find corrensponding user in db
                // no need to further process
                // TODO : inform user!!
                is.close();
                inputFromApplet.close();
                is = null;
                inputFromApplet = null;
                return;
            }
            
            
            
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
