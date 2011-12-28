/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import applet_algorithm.mysql_UTIL;

/**
 *
 * @author Patron
 */
@WebServlet(name = "Servlet_Login", urlPatterns = {"/Servlet_Login"})
public class Servlet_Login extends HttpServlet {

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
        HttpSession session = request.getSession();
        String user_name = request.getParameter("lgn_username").toString();
        String password = request.getParameter("lgn_password").toString();
        String adv=request.getParameter("userType");
        
        
                try {
                    //Nurettin : simdi ilk kez girdigimde OK, ama
                    //logout edip tekrar girdigimde user:Ozgur pass:12345 diye
                    //bu kez girmiyor, dbde yok diyor, neden acaba?
                    if(!mysql_UTIL.checkUser(user_name, password)){
                        response.sendRedirect("login_error.jsp");
                    }else{
                        Cookie cookie = new Cookie(user_name,"LOGGED_IN");
                        cookie.setMaxAge(60*60);
                        response.addCookie(cookie);
                         session.setAttribute("username",user_name);
                        session.setAttribute("userType", adv);
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
