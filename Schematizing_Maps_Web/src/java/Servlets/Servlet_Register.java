/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import schematizing_maps.server_side.mysql_UTIL;

/**
 *
 * @author Patron
 */
@WebServlet(name = "Servlet_Register", urlPatterns = {"/Servlet_Register"})
public class Servlet_Register extends HttpServlet {
    private static mysql_UTIL db = new mysql_UTIL("titan.cmpe.boun.edu.tr", "3306", "project5", "s8u4p", "database5");
    
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
        String user_name = request.getParameter("username").toString();
        String password = request.getParameter("password").toString();
        String password2 = request.getParameter("password2").toString();
        
        PrintWriter out = response.getWriter();
        try {
                 
                        if(!mysql_UTIL.addUser(user_name, password)){
                            response.sendRedirect("registration_error.jsp");
                        }else{
                            //session.setAttribute(user_name,out);
                            response.sendRedirect("mainWindow.jsp?name="+user_name);
                        }
                    
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
