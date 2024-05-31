/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import ejb.AdminBeanLocal;
import entity.TblBlogPost;
import entity.TblPlace;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Harsh
 */
@WebServlet(name = "BlogPostServlet", urlPatterns = {"/BlogPostServlet"})
public class BlogPostServlet extends HttpServlet {

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
            out.println("<title>Servlet BlogPostServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            //For adding new blogPost 
            //adminBean.addBlogPost("abc", "abc", new Date(), 2);
            
            //For updating a blogpost
            //adminBean.udpateBlogPost(2,"xyz", "xyz",new Date(),2);
            
            //For deleting user
            //adminBean.deleteBlogPost(2,2);
            
            
            //For getting all blogposts
            Collection<TblBlogPost> blogpost = adminBean.getAllBlogPost();
            
            for (TblBlogPost bp : blogpost) {
                out.print("1");
                out.println("Id : " + bp.getBlogPostId()+ "<br>");
                out.println("Title : " + bp.getBlogTitle()+ "<br>");
                out.println("Description : " + bp.getBlogDescription()+ "<br>");
                out.println("Date : " + bp.getDate()+ "<br>");
                out.println("Place : " + bp.getPlaceId().getPlaceName()+ "<br>");
                

                out.print("<hr>");
            }
            

            out.println("<h1>Servlet BlogPostServlet at " + request.getContextPath() + "</h1>");
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
