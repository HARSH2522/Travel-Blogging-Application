/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import ejb.AdminBeanLocal;
import entity.TblUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Harsh
 */
public class UserServlet extends HttpServlet {

    @EJB
    private AdminBeanLocal adminBean;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            //For adding new user 
            //adminBean.addUser("vanshi", "Vanshita", "Dakoria", "vanshita@abc.com", "2.jpeg", 1234567888, "vanshi", new Date(),2);
            
            //For updating a user data
            //adminBean.updateUser(2,"vanshita", "Vanshi", "Shah", "vanshita@yaahoo.com", "1.jpeg", 1238888888, "vanshi@123", new Date(),2);
            
            //For deleting user
            //adminBean.deleteUser(2, 2);
            
            //For getting specific user
//            TblUser user = adminBean.findUserById(3);
//            out.println("Id : " + user.getUserid() + "<br>");
//            out.println("Username : " + user.getUsername() + "<br>");
//            out.println("Name : " + user.getFname() + " " + user.getLname() + "<br>");
//            

//            //For getting all users 
//            Collection<TblUser> users = adminBean.getAllUsers();
//            for (TblUser u : users) {
//                out.println("Id : " + u.getUserid() + "<br>");
//                out.println("Username : " + u.getUsername() + "<br>");
//                out.println("Name : " + u.getFname() + " " + u.getLname() + "<br>");
//                out.println("Email : " + u.getEmail() + "<br>");
//                out.println("Contact No : " + u.getContactNo() + "<br>");
//                out.println("Img : " + u.getProfileImg() + "<br>");
//                out.println("Date : " + u.getRegistrationDate() + "<br>");
//                out.println("Role : " + u.getGroupid().getRole() + "<br>");
//
//                out.print("<hr>");
//            }
            
            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
