/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import ejb.AdminBeanLocal;
import entity.TblBlogPost;
import entity.TblPhotos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
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
@WebServlet(name = "PhotosServlet", urlPatterns = {"/PhotosServlet"})
public class PhotosServlet extends HttpServlet {

    @EJB
    private AdminBeanLocal adminBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PhotosServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            //To insert photos
            //adminBean.addPhotos("C:\\Users\\91738\\OneDrive\\Pictures\\Screenshots\\2.jpg.png", 1);
            
            //To update photos
            //adminBean.updatePhotos(1, "\"C:\\Users\\91738\\OneDrive\\Pictures\\Screenshots\\3.jpg - Copy.png\"", 1);
            
            //To delete Photos
            //adminBean.deletePhotos(1,1);
            
            Collection<TblPhotos> photos = adminBean.getAllPhotos();
            for (TblPhotos p : photos) {
                out.println("Id : " + p.getPhotoId()+ "<br>");
                out.println("Photo : " + p.getPhotoUrl()+ "<br>");
                out.println("Post : " + p.getBlogPostId().getBlogTitle()+ "<br>");

                out.print("<hr>");
            }
            
            
            out.println("<h1>Servlet PhotosServlet at " + request.getContextPath() + "</h1>");
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
