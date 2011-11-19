/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet_Temp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import schematizing_maps.server_side.mysql_UTIL;

/**
 *
 * @author Patron
 */
public class SM_Servlet_Temp extends HttpServlet {
//private static mysql_UTIL db = new mysql_UTIL("localhost", "3306", "root", "xxxx", "project_451");
    private static mysql_UTIL db = new mysql_UTIL("localhost", "3306", "patron", "s8u4p", "database5");
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
        try {
            
            String user_name = request.getParameter("username").toString();
            String password = request.getParameter("password").toString();
            /*
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SM_Servlet_Temp</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SM_Servlet_Temp at " + request.getContextPath () + "</h1>");
            out.println("<p>UserName: "+user_name+"Password: "+password+"</p>");
            out.println("</body>");
            out.println("</html>");
            //TODO Go over the code with Nurettin, and see why it cannot find user: Ozgur pass:12345
            */
            if(!mysql_UTIL.checkUser(user_name, password)){
                response.sendRedirect("login_error.jsp");
            }else{
                HttpSession session = request.getSession();
                session.setAttribute("logon.successful", out);
                response.sendRedirect("login2.jsp");
                
            }
            /*out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SM_Servlet_Temp</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SM_Servlet_Temp at " + request.getContextPath () + "</h1>");
            out.println("<p>UserName: "+user_name+"Password: "+password+"</p>");
            out.println("</body>");
            out.println("</html>");*/
            
             
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
