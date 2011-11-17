/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author px5x2
 */
public class SM_Servlet extends HttpServlet {
    
    
    private static mysql_UTIL db = new mysql_UTIL("localhost", "3306", "project5", "s8u4p", "project5");

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
            String user_name = request.getParameter("username");
            String password = request.getParameter("password");
            
            if(!mysql_UTIL.checkUser(user_name, password)){
                out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
                out.println("<BODY>Your login and password are invalid.<BR>");
                out.println("You may want to <A HREF=\"/login.html\">try again</A>");
                out.println("</BODY></HTML>");
            }else{
                HttpSession session = request.getSession();
                session.setAttribute("logon.successful", out);
                response.sendRedirect("login2.jsp");
                
            }
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SM_Servlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SM_Servlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
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
